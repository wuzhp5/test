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
		//校验不可为空字段
		service.checkNotNull(json, service.getNotNullFields(this.info.getBillType(), false));
		//NC计划订单号
		String vplancode = json.getString("vplancode");
		//MES工单
		String taskcode = json.getString("taskcode");
		//按计划订单查询
		AggPickmVO aggPickmVO = queryAggPickmVO(vplancode);
		//更新MES到备料计划，再推单到材料出库
		NCLocator.getInstance().lookup(IUtilRequiresNew.class).updatePickVO_RequiresNew(aggPickmVO.getPrimaryKey(), taskcode);
		//查询库存材料出库单
		MaterialOutVO originBill = queryMaterialOutVO(aggPickmVO, taskcode);
		checkPickMaterialOut(originBill);
		if(originBill == null){
			//匹配校验备料计划
			AggPickmVO pickVO = handlePickVO(aggPickmVO, json);
			//领料
			issue(pickVO, json);
			//领料后重新查询材料出库单
			originBill = queryMaterialOutVO(aggPickmVO, taskcode);
		}
		MaterialOutVO bill = (MaterialOutVO) originBill.clone();
		//开始捡货
		pickAuto(bill);
		//出库单更新
		MaterialOutVO[] vos = updateMaterialOutVO(bill, originBill);
		if(vos == null){
			throw new BusinessException("材料出库【"+bill.getParentVO().getVbillcode()+"】修改失败，失败原因【未知原因】");
		}
		//日志载入
		return SyncUtil.packSuccessInfo("材料出库【"+bill.getParentVO().getVbillcode()+"】在NC中已经完成出库.", bill.getPrimaryKey(), bill.getParentVO().getVbillcode());
	}
	
	public AggPickmVO queryAggPickmVO(String vplancode) throws BusinessException{
		String sql = "select cpickmid from mm_pickm where nvl(dr, 0) = 0 and vdef1= '"+vplancode+"'";
		String praimaryKey = (String) bs.executeQuery(sql, new ColumnProcessor());
		if(praimaryKey == null){
			throw new BusinessException("未找到计划订单【"+vplancode+"】的备料计划。");
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
			//是否发料
			UFBoolean bcandeliver = (UFBoolean) itemVO.getAttributeValue("bcandeliver");
			if(!bcandeliver.booleanValue()){
				continue;
			}
			//领料标志领料标志 0=正常，1=超额
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
	 * 自动捡货
	 * @param outVO
	 * @return
	 * @throws BusinessException
	 */
	private void pickAuto(MaterialOutVO outVO) throws BusinessException{
		try{
			ICLocationUtil.loadLocationVOs(new ICBillVO[] { outVO });
			ICBillPickResults res = NCLocator.getInstance().lookup(OnhandResService.class).pickAuto(outVO);
			if(res == null){
				throw new BusinessException("现存量不足");
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
			throw new BusinessException("材料出库【"+outVO.getParentVO().getVbillcode()+"】捡货失败，失败原因【"+e.getMessage()+"】");
		}
	}
	
	/**
	 * 备料计划领料
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
			throw new BusinessException("计划订单【"+aggPickmVO.getParentVO().getVbillcode()+"】自动领料失败，失败原因【"+e.getMessage()+"】");
		}
	}
	
	/**
	 * 更新出库单
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
			throw new BusinessException("材料出库【"+bill.getParentVO().getVbillcode()+"】修改失败，失败原因【"+e.getMessage()+"】");
		}
	}
	
	private AggPickmVO handlePickVO(AggPickmVO pickVO, JSONObject json) throws BusinessException{
		Map<String, JSONObject> jsonMap = matchMaterial(json);
		return getRealAggPickmVO(jsonMap, pickVO);
	}
	
	/**
	 * 物料匹配
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
			throw new BusinessException("NC不存在物料【"+condition.toString()+"】，请检查！");
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
			throw new BusinessException("NC不存在物料【"+msg.deleteCharAt(msg.length()-1).toString()+"】,请检查！");
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
	 * 获取匹配后备料计划
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
				//nplanoutnum  计划出库主数量  
				UFDouble nplanoutnum = itemVO.getNplanoutnum();
				//naccoutnum  累计出库主数量 
				UFDouble naccoutnum = itemVO.getNaccoutnum();
				if(num.compareTo(nplanoutnum.sub(naccoutnum)) > 0){
					checkNum.append("物料【"+json.getString("materialcode")+"】已超出库，NC计划出库主数量【"+nplanoutnum+"】,累计出库主数量 【"+naccoutnum+"】,MES上传出库主数量【"+num+"】;/r/n");
				}
				if(checkNum.length() == 0){
					//初始化领料时各类数量
					SyncUtil.initSameFileds(itemVO, new String[]{"noutableastnum","noutablenum","nplanoutastnum","nplanoutnum","nthisneedastnum","nthisneednum",
							"nthisarrangeastnum","nthisarrangenum","nlimitoutastnum","nlimitoutnum","nbcckastnum","nbccknum"}, num);
					//初始化发料仓库
					String coutstockid = translatorStordoc(json.getString("placecode"), itemVO.getPk_group(), itemVO.getPk_org());
					if(SyncUtil.isNullOrBlank(coutstockid)){
						checkStorDoc.append("物料【"+json.getString("materialcode")+"】所在行的仓库编码【"+json.getString("placecode")+"】NC不存在；/r/n");
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
				resMsg.append("NC备料计划【"+pickVO.getParentVO().getVbillcode()+"】中不存在物料编码【"+checkMaterial.deleteCharAt(checkMaterial.length()-1).toString()+"】;/r/n");
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
	 * 翻译发料仓库
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
	 * 检查是否已经捡货
	 * @param originBill
	 * @throws BusinessException
	 */
	private void checkPickMaterialOut(MaterialOutVO originBill) throws BusinessException{
		if(originBill != null){
			UFDouble nnum =  originBill.getBodys()[0].getNnum();
			String taskcode = originBill.getHead().getVdef2();
			if(nnum != null && UFDouble.ZERO_DBL.compareTo(nnum) != 0){
				throw new BusinessException("MES工单【"+taskcode+"】已经成功同步到NC材料出库单【"+originBill.getHead().getVbillcode()+"】并已经成功捡货，请勿重复提交！");
			}
		}
	}
	
}
