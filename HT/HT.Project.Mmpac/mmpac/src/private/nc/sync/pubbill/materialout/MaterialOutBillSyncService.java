package nc.sync.pubbill.materialout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.bd.baseservice.ArrayClassConvertUtil;
import nc.bs.constrast.ConstrastService;
import nc.bs.framework.common.NCLocator;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.itf.ic.m4d.IMaterialOutMaintain;
import nc.itf.ic.onhand.OnhandResService;
import nc.itf.sync.util.IUtilRequiresNew;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.pub.sync.utils.SyncUtil;
import nc.pubitf.ic.m4d.api.IMaterialOutQueryAPI;
import nc.sync.pfxx.entity.PfxxServiceBaseInfo;
import nc.sync.pub.bc.ProcessBill2PfxxService;
import nc.vo.ic.general.define.ICBillBodyVO;
import nc.vo.ic.general.define.ICBillVO;
import nc.vo.ic.general.util.ICLocationUtil;
import nc.vo.ic.m4d.entity.MaterialOutBodyVO;
import nc.vo.ic.m4d.entity.MaterialOutVO;
import nc.vo.ic.onhand.define.ICBillPickResults;
import nc.vo.mmpac.pickm.entity.AggPickmVO;
import nc.vo.mmpac.pickm.entity.PickmHeadVO;
import nc.vo.mmpac.pickm.entity.PickmItemVO;
import nc.vo.mmpac.pickm.entity.PickmViewVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDouble;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MaterialOutBillSyncService extends ProcessBill2PfxxService{
	private PfxxServiceBaseInfo info = new PfxxServiceBaseInfo();
	private BillQuery<AggPickmVO> query = new BillQuery<AggPickmVO>(AggPickmVO.class);
	public MaterialOutBillSyncService() {
		super();
		info.setBillCodeField("vbillcode");
		info.setBillType("4D");
		info.setFileName("materialout");
		info.setItemNames(new String[] { "cgeneralbid" });
		info.setSender("MES");
	}
	
	public String service(JSONObject json) throws BusinessException{
		ConstrastService service = new ConstrastService();
		//У�鲻��Ϊ���ֶ�
		service.checkNotNull(json, service.getNotNullFields(this.info.getBillType(), false));
		//NC�ƻ�������
		String vplancode = json.getString("vplancode");
		//MES����
		String taskcode = json.getString("taskcode");
		//���ƻ�������ѯ
		AggPickmVO aggPickmVO = queryAggPickmVO(vplancode);
		//����MES�����ϼƻ������Ƶ������ϳ���
		NCLocator.getInstance().lookup(IUtilRequiresNew.class).updatePickVO_RequiresNew(aggPickmVO.getPrimaryKey(), taskcode);
		//��ѯ�����ϳ��ⵥ
		MaterialOutVO originBill = queryMaterialOutVO(aggPickmVO, taskcode);
		checkPickMaterialOut(originBill);
		if(originBill == null){
			//ƥ��У�鱸�ϼƻ�
			AggPickmVO pickVO = handlePickVO(aggPickmVO, json);
			//����
			issue(pickVO, json);
			//���Ϻ����²�ѯ���ϳ��ⵥ
			originBill = queryMaterialOutVO(aggPickmVO, taskcode);
		}
		MaterialOutVO bill = (MaterialOutVO) originBill.clone();
		//��ʼ���
		pickAuto(bill);
		//���ⵥ����
		MaterialOutVO[] vos = updateMaterialOutVO(bill, originBill);
		if(vos == null){
			throw new BusinessException("���ϳ��⡾"+bill.getParentVO().getVbillcode()+"���޸�ʧ�ܣ�ʧ��ԭ��δ֪ԭ��");
		}
		//��־����
		return SyncUtil.packSuccessInfo("���ϳ��⡾"+bill.getParentVO().getVbillcode()+"����NC���Ѿ���ɳ���.", bill.getPrimaryKey(), bill.getParentVO().getVbillcode());
	}
	
	public AggPickmVO queryAggPickmVO(String vplancode) throws BusinessException{
		String sql = "select cpickmid from mm_pickm where nvl(dr, 0) = 0 and vdef1= '"+vplancode+"'";
		String praimaryKey = (String) bs.executeQuery(sql, new ColumnProcessor());
		if(praimaryKey == null){
			throw new BusinessException("δ�ҵ��ƻ�������"+vplancode+"���ı��ϼƻ���");
		}
		return query.query(new String[]{praimaryKey})[0];
	}
	
	/**
	 * @param pickmVO
	 * @return
	 */
	public PickmViewVO[] initPickmViewVO(AggPickmVO pickmVO, JSONObject json){
		PickmHeadVO headVO = pickmVO.getParentVO();
		headVO.setStatus(VOStatus.UPDATED);
		ISuperVO[] itemVOs = pickmVO.getChildren(PickmItemVO.class);
		List<PickmViewVO> views = new ArrayList<PickmViewVO>();
		for(ISuperVO itemVO : itemVOs){
			itemVO.setStatus(VOStatus.UPDATED);
			//�Ƿ���
			UFBoolean bcandeliver = (UFBoolean) itemVO.getAttributeValue("bcandeliver");
			if(!bcandeliver.booleanValue()){
				continue;
			}
			//���ϱ�־���ϱ�־ 0=������1=����
			itemVO.setAttributeValue("fissueflag", 0);
			PickmViewVO view = new PickmViewVO();
			view.setVO(headVO);
			view.setVO(itemVO);
			view.setDirty(false);
			view.setStatus(1);
			views.add(view);
		}
		return views.toArray(new PickmViewVO[]{new PickmViewVO()});
	}
	
	
	/**
	 * @param id
	 * @return
	 * @throws BusinessException
	 */
	private MaterialOutVO queryMaterialOutVO(AggPickmVO aggPickmVO, String taskcode) throws BusinessException{
		String sql = "select item.cgeneralhid from ic_material_h head inner join ic_material_b item on head.cgeneralhid = item.cgeneralhid where nvl(item.dr,0) = 0 and item.cpickmhid = '"+aggPickmVO.getParent().getPrimaryKey()+"' " +
				"and item.cpickmtranstype = '"+aggPickmVO.getParentVO().getVbusitypeid()+"' and head.vdef2 = '"+taskcode+"' group by item.cgeneralhid";
		String id = (String) bs.executeQuery(sql, new ColumnProcessor());
		if(id == null || "".trim().equals(id)){
			return null;
		}
		MaterialOutVO[] outVOs = NCLocator.getInstance().lookup(IMaterialOutQueryAPI.class).queryVOByIDs(new String[]{id});
		if(outVOs == null || outVOs.length == 0){
			return null;
		}
		return outVOs[0];
	}
	
	/**
	 * �Զ����
	 * @param outVO
	 * @return
	 * @throws BusinessException
	 */
	private void pickAuto(MaterialOutVO outVO) throws BusinessException{
		try{
			ICLocationUtil.loadLocationVOs(new ICBillVO[] { outVO });
			ICBillPickResults res = NCLocator.getInstance().lookup(OnhandResService.class).pickAuto(outVO);
			if(res == null){
				throw new BusinessException("�ִ�������");
			}
			ICBillBodyVO[] bodyVOs = res.getPickBodys();
			MaterialOutBodyVO[] materialOutBodyVOs = outVO.getBodys();
			for(int index = 0; index < bodyVOs.length; index++){
				materialOutBodyVOs[index].setAttributeValue("nassistnum", bodyVOs[index].getAttributeValue("nassistnum"));
				materialOutBodyVOs[index].setAttributeValue("nnum", bodyVOs[index].getAttributeValue("nnum"));
				materialOutBodyVOs[index].setAttributeValue("dbizdate", bodyVOs[index].getAttributeValue("dbizdate"));
				materialOutBodyVOs[index].setAttributeValue("pk_batchcode", bodyVOs[index].getAttributeValue("pk_batchcode"));
				materialOutBodyVOs[index].setAttributeValue("vbatchcode", bodyVOs[index].getAttributeValue("vbatchcode"));
				materialOutBodyVOs[index].setAttributeValue("clocationid", bodyVOs[index].getAttributeValue("clocationid"));
				materialOutBodyVOs[index].setLocationVOs(bodyVOs[index].getLocationVOs());
			}
		}catch(BusinessException e){
			throw new BusinessException("���ϳ��⡾"+outVO.getParentVO().getVbillcode()+"�����ʧ�ܣ�ʧ��ԭ��"+e.getMessage()+"��");
		}
	}
	
	/**
	 * ���ϼƻ�����
	 * @param aggPickmVO
	 * @param json
	 * @throws BusinessException
	 */
	private void issue(AggPickmVO aggPickmVO, JSONObject json) throws BusinessException{
		try{
			PickmViewVO[] view = initPickmViewVO(aggPickmVO, json);
			if(view != null){
				NCLocator.getInstance().lookup(IUtilRequiresNew.class).issue_RequiresNew(view);
			}
		}catch(BusinessException e){
			throw new BusinessException("�ƻ�������"+aggPickmVO.getParentVO().getVbillcode()+"���Զ�����ʧ�ܣ�ʧ��ԭ��"+e.getMessage()+"��");
		}
	}
	
	/**
	 * ���³��ⵥ
	 * @param bill
	 * @param originBill
	 * @return
	 * @throws BusinessException
	 */
	private MaterialOutVO[] updateMaterialOutVO(MaterialOutVO bill, MaterialOutVO originBill) throws BusinessException{
		try{
			bill.getParent().setStatus(VOStatus.UPDATED);
			ICBillBodyVO[] bodyVOs = bill.getChildrenVO();
			for(ICBillBodyVO bodyVO : bodyVOs){
				bodyVO.setStatus(VOStatus.UPDATED);
			}
			MaterialOutVO[] bills = NCLocator.getInstance().lookup(IMaterialOutMaintain.class).update(new MaterialOutVO[]{bill}, new MaterialOutVO[]{originBill});
			return bills;
		}catch(BusinessException e){
			throw new BusinessException("���ϳ��⡾"+bill.getParentVO().getVbillcode()+"���޸�ʧ�ܣ�ʧ��ԭ��"+e.getMessage()+"��");
		}
	}
	
	private AggPickmVO handlePickVO(AggPickmVO pickVO, JSONObject json) throws BusinessException{
		Map<String, JSONObject> jsonMap = matchMaterial(json);
		return getRealAggPickmVO(jsonMap, pickVO);
	}
	
	/**
	 * ����ƥ��
	 * @param json
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	private Map<String, JSONObject> matchMaterial(JSONObject json) throws BusinessException{
		JSONArray array = json.getJSONArray("item");
		StringBuffer condition = new StringBuffer();
		Map<String, JSONObject> orignalMap = new HashMap<String, JSONObject>();
		for(int index = 0; index < array.size(); index++){
			String materialcode = array.getJSONObject(index).getString("materialcode");
			condition.append("'"+materialcode+"',");
			orignalMap.put(materialcode, array.getJSONObject(index));
		}
		condition.deleteCharAt(condition.length()-1);
		StringBuffer sql = new StringBuffer("select code,pk_material from bd_material_v where code in ( ");
		sql.append(condition);
		sql.append(")");
		List<Map<String, String>> results = (List<Map<String, String>>) this.bs.executeQuery(sql.toString(), new MapListProcessor());
		if(results == null || results.size() == 0){
			throw new BusinessException("NC���������ϡ�"+condition.toString()+"�������飡");
		}
		Map<String, String> map = new HashMap<String, String>();
		for(Map<String, String> result : results){
			map.put(result.get("code"), result.get("pk_material"));
		}
		Set<String> codes = orignalMap.keySet();
		Set<String> ncCodes = map.keySet();
		StringBuffer msg = new StringBuffer();
		Map<String, JSONObject> newMap = new HashMap<String, JSONObject>();
		for(String code : codes){
			if(!ncCodes.contains(code)){
				msg.append(code+",");
			}
			if(msg.length() == 0){
				newMap.put(map.get(code), orignalMap.get(code));
			}
		}
		if(msg.length() > 0){
			throw new BusinessException("NC���������ϡ�"+msg.deleteCharAt(msg.length()-1).toString()+"��,���飡");
		}
		return newMap;
	}
	
	/**
	 * 
	 * @param itemVOs
	 * @return
	 * @throws BusinessException
	 */
	private Map<String, PickmItemVO> getPickMap(PickmItemVO[] itemVOs) throws BusinessException{
		Map<String, PickmItemVO> pickMap = new HashMap<String, PickmItemVO>();
		for(PickmItemVO itemVO : itemVOs){
			pickMap.put(itemVO.getCbmaterialid(), itemVO);
		}
		return pickMap;
	}
	
	/**
	 * ��ȡƥ����ϼƻ�
	 * @param jsonMap
	 * @param pickVO
	 * @return
	 * @throws BusinessException
	 */
	private AggPickmVO getRealAggPickmVO(Map<String, JSONObject> jsonMap, AggPickmVO pickVO) throws BusinessException{
		PickmItemVO[] itemVOs = ArrayClassConvertUtil.convert(pickVO.getChildren(PickmItemVO.class), PickmItemVO.class);
		Map<String, PickmItemVO> pickMap = getPickMap(itemVOs);
		Set<String> jsonKeys = jsonMap.keySet();
		Set<String> pickKeys = pickMap.keySet();
		StringBuffer checkMaterial = new StringBuffer();
		StringBuffer checkNum = new StringBuffer();
		StringBuffer checkStorDoc = new StringBuffer();
		List<PickmItemVO> newPickVOs = new ArrayList<PickmItemVO>();
		for(String jsonKey : jsonKeys){
			if(!pickKeys.contains(jsonKey)){
				checkMaterial.append(jsonMap.get(jsonKey).getString("materialcode")+",");
			}
			if(checkMaterial.length() == 0){
				PickmItemVO itemVO = (PickmItemVO) pickMap.get(jsonKey).clone();
				JSONObject json = jsonMap.get(jsonKey);
				UFDouble num = new UFDouble(json.getString("outcount"));
				//nplanoutnum  �ƻ�����������  
				UFDouble nplanoutnum = itemVO.getNplanoutnum();
				//naccoutnum  �ۼƳ��������� 
				UFDouble naccoutnum = itemVO.getNaccoutnum();
				if(num.compareTo(nplanoutnum.sub(naccoutnum)) > 0){
					checkNum.append("���ϡ�"+json.getString("materialcode")+"���ѳ����⣬NC�ƻ�������������"+nplanoutnum+"��,�ۼƳ��������� ��"+naccoutnum+"��,MES�ϴ�������������"+num+"��;/r/n");
				}
				if(checkNum.length() == 0){
					//��ʼ������ʱ��������
					SyncUtil.initSameFileds(itemVO, new String[]{"noutableastnum","noutablenum","nplanoutastnum","nplanoutnum","nthisneedastnum","nthisneednum",
							"nthisarrangeastnum","nthisarrangenum","nlimitoutastnum","nlimitoutnum","nbcckastnum","nbccknum"}, num);
					//��ʼ�����ϲֿ�
					String coutstockid = translatorStordoc(json.getString("placecode"), itemVO.getPk_group(), itemVO.getPk_org());
					if(SyncUtil.isNullOrBlank(coutstockid)){
						checkStorDoc.append("���ϡ�"+json.getString("materialcode")+"�������еĲֿ���롾"+json.getString("placecode")+"��NC�����ڣ�/r/n");
					}
					if(checkStorDoc.length() == 0){
						itemVO.setAttributeValue("coutstockid", coutstockid);
						newPickVOs.add(itemVO);
					}
				}
			}
		}
		if(checkMaterial.length() > 0 || checkNum.length() > 0 || checkStorDoc.length() > 0){
			StringBuffer resMsg = new StringBuffer();
			if(checkMaterial.length() > 0){
				resMsg.append("NC���ϼƻ���"+pickVO.getParentVO().getVbillcode()+"���в��������ϱ��롾"+checkMaterial.deleteCharAt(checkMaterial.length()-1).toString()+"��;/r/n");
			}
			resMsg.append(checkNum);
			resMsg.append(checkStorDoc);
			throw new BusinessException(resMsg.toString());
		}
		AggPickmVO newPickVO = (AggPickmVO) pickVO.clone();
		newPickVO.setChildren(PickmItemVO.class, ArrayClassConvertUtil.convert(newPickVOs.toArray(), PickmItemVO.class));
		return newPickVO;
	}
	
	/**
	 * ���뷢�ϲֿ�
	 * @param code
	 * @param pk_group
	 * @param pk_org
	 * @return
	 * @throws BusinessException
	 */
	private String translatorStordoc(String code, String pk_group, String pk_org) throws BusinessException{
		String sql = "select pk_stordoc from bd_stordoc where nvl(dr,0) = 0 and pk_group = '"+pk_group+"' and pk_org = " +
				"(select pk_stockorg  from org_stockorg where code = (select code from org_factory where pk_factory = '"+pk_org+"') and nvl(dr,0) = 0) and code = '"+code+"'";
		return (String) getBaseDAO().executeQuery(sql, new ColumnProcessor());
	}
	
	/**
	 * ����Ƿ��Ѿ����
	 * @param originBill
	 * @throws BusinessException
	 */
	private void checkPickMaterialOut(MaterialOutVO originBill) throws BusinessException{
		if(originBill != null){
			UFDouble nnum =  originBill.getBodys()[0].getNnum();
			String taskcode = originBill.getHead().getVdef2();
			if(nnum != null && UFDouble.ZERO_DBL.compareTo(nnum) != 0){
				throw new BusinessException("MES������"+taskcode+"���Ѿ��ɹ�ͬ����NC���ϳ��ⵥ��"+originBill.getHead().getVbillcode()+"�����Ѿ��ɹ�����������ظ��ύ��");
			}
		}
	}
	
}
