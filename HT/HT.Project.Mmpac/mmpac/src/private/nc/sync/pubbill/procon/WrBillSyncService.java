package nc.sync.pubbill.procon;

import java.util.HashMap;
import java.util.Map;

import nc.bs.constrast.ConstrastService;
import nc.bs.framework.common.NCLocator;
import nc.itf.mmpac.wr.IWrMaintainService;
import nc.itf.sync.util.IUtilRequiresNew;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.pf.IPfExchangeService;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.pub.sync.utils.SyncUtil;
import nc.sync.pfxx.entity.PfxxServiceBaseInfo;
import nc.sync.pub.bc.ProcessBill2PfxxService;
import nc.vo.mmpac.dmo.entity.AggDmoVO;
import nc.vo.mmpac.dmo.entity.DmoVO;
import nc.vo.mmpac.wr.entity.AggWrVO;
import nc.vo.mmpac.wr.entity.WrItemVO;
import nc.vo.mmpps.mps0202.AggregatedPoVO;
import nc.vo.mmpps.mps0202.PoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import net.sf.json.JSONObject;

public class WrBillSyncService extends ProcessBill2PfxxService {

	private PfxxServiceBaseInfo info = new PfxxServiceBaseInfo();
	private Map<String, String> kv = new HashMap<String, String>();
	
	public WrBillSyncService() {
		super();
		info.setBillCodeField("vbillcode");
		info.setBillType("55A4");
		info.setFileName("procon");
		info.setItemNames(new String[] { "product" });
		info.setSender("MES");
	}

	public String service(JSONObject json) throws BusinessException {
		ConstrastService service = new ConstrastService();
		service.checkNotNull(json, service.getNotNullFields(this.info.getBillType(), false));
		//获取计划订单
		AggregatedPoVO poVO = queryAggregatedPoVO(json);
		//计划订单校验
		checkAggregatedPoVO(poVO, json);
		//计划订单确认
		if(poVO.getParentVO().getFbillstatus() == 0){
			poVO = NCLocator.getInstance().lookup(IUtilRequiresNew.class).doPoConfirm_RequiresNew(poVO);
		}
		//计划订单下达
		if(poVO.getParentVO().getFbillstatus() == 1){
			fillAggregatedPoVO(poVO, json.getString("cdeptid"));
			poVO = NCLocator.getInstance().lookup(IUtilRequiresNew.class).doPoRelease_RequiresNew(poVO);
		}
		AggWrVO aggWrVO = new AggWrVO();
		AggDmoVO aggDmoVO = queryAggDmoVO(poVO);
		if(aggDmoVO.getParentVO().getFbillstatus() == 1){
			aggDmoVO = NCLocator.getInstance().lookup(IUtilRequiresNew.class).putDmoBill_RequiresNew(aggDmoVO);
		}
		//刷新AggDmoVO
		aggDmoVO = queryAggDmoVO(poVO);
		aggWrVO = (AggWrVO) NCLocator.getInstance().lookup(IPfExchangeService.class).runChangeData("55C2", "55A4", aggDmoVO, null);
		fillAggWrVO(aggWrVO, json);
		//保存
		aggWrVO = NCLocator.getInstance().lookup(IWrMaintainService.class).insert(new AggWrVO[] { aggWrVO })[0];
		return SyncUtil.packSuccessInfo("生产报告【"+aggWrVO.getParentVO().getVbillcode()+"】在NC中已经完成保存.", aggWrVO.getPrimaryKey(), aggWrVO.getParentVO().getVbillcode());
	}

	/**
	 * 查询计划订单
	 * @param vbillcode
	 * @return
	 * @throws BusinessException
	 */
	private AggregatedPoVO queryAggregatedPoVO(JSONObject json) throws BusinessException{
		String vplancode = json.getString("vplancode");
		JSONObject array = (JSONObject) json.getJSONArray("item").get(0);
		String productcode = (String) array.get("productcode");
		AggregatedPoVO aggVO = new AggregatedPoVO();
		String sql = "select * from mm_plo where nvl(dr,0) = 0 and vbillcode = '"+vplancode+"' and cmaterialvid in " +
				"(select pk_material from bd_material_v where nvl(dr,0) = 0 and code = '"+productcode+"')";
		PoVO headVO = (PoVO) bs.executeQuery(sql, new BeanProcessor(PoVO.class));
		if(headVO == null){
			throw new BusinessException("订单号【"+vplancode+"】，生产成品编码【"+productcode+"】的计划订单已被删除或不存在,请检查!");
		}
		aggVO.setParent(headVO);
		return aggVO;
	}
	
	
	/**
	 * 填充生产部门信息
	 * @param poVO
	 * @param deptcode
	 * @throws BusinessException
	 */
	private void fillAggregatedPoVO(AggregatedPoVO poVO, String deptcode) throws BusinessException{
		String sql = "select pk_vid,pk_dept from org_dept_v where nvl(dr,0) =  0 and code = '"+deptcode.trim()+"'";
		@SuppressWarnings("unchecked")
		Map<String, String> result = (Map<String, String>) bs.executeQuery(sql, new MapProcessor());
		poVO.getParentVO().setCproddeptid(result.get("pk_dept"));
		poVO.getParentVO().setCproddeptvid(result.get("pk_vid"));
	}
	
	/**
	 * 通过来源单据主键查询离散订单
	 * @param poVO
	 * @return
	 * @throws BusinessException
	 */
	private AggDmoVO queryAggDmoVO(AggregatedPoVO poVO) throws BusinessException{
		AggDmoVO aggDmoVO = new AggDmoVO();
		String sql  = "select * from mm_dmo where vsrcid =  '"+poVO.getPrimaryKey()+"' and nvl(dr,0) = 0";
		DmoVO headVO = (DmoVO) bs.executeQuery(sql, new BeanProcessor(DmoVO.class));
		if(headVO == null){
			throw new BusinessException("【"+poVO.getParentVO().getVbillcode()+"】计划订单生成的离散生产订单被删除!请登录到NC系统处理。");
		}
		aggDmoVO.setParent(headVO);
		return aggDmoVO;
	}
	
	/**
	 * 填充字段
	 * @param aggWrVO
	 * @param json
	 * @throws BusinessException
	 */
	private void fillAggWrVO(AggWrVO aggWrVO, JSONObject json) throws BusinessException{
		JSONObject array = (JSONObject) json.getJSONArray("item").get(0);
		String realcount = array.getString("realcount");
		String okdt = array.getString("okdt");
		String toplacecode = array.getString("toplacecode");
		String pk_group = aggWrVO.getParentVO().getPk_group();
		if("1".equals(toplacecode)){
			aggWrVO.getParentVO().setVtrantypeid(getVtrantypeId("55A4-01", pk_group));
			aggWrVO.getParentVO().setVtrantypecode("55A4-01");
		}else if("0".equals(toplacecode)){
			aggWrVO.getParentVO().setVtrantypecode("55A4-Cxx-01");
			aggWrVO.getParentVO().setVtrantypeid(getVtrantypeId("55A4-Cxx-01", pk_group));
		}else{
			throw new BusinessException("toplacecode 存放位置车间仓:0，物流仓:1,当前参数值【"+toplacecode+"】");
		}
		aggWrVO.getParentVO().setFbillstatus(new Integer(1));
		ISuperVO[] children = aggWrVO.getChildren(WrItemVO.class); 
		for(ISuperVO child : children){
			if(child == null)  continue;
			child.setAttributeValue("tbstarttime", new UFDate(true));
			child.setAttributeValue("tbendtime", new UFDateTime(okdt));
			String vbchangerate = (String) child.getAttributeValue("vbchangerate");
			UFDouble nbwrnum = new UFDouble(realcount);
			child.setAttributeValue("nbwrnum", nbwrnum);
			child.setAttributeValue("nbwrastnum", SyncUtil.calNassitNum(vbchangerate, nbwrnum));
		}
	}
	
	/**
	 * 校验计划订单
	 * @param poVO
	 * @param json
	 * @throws BusinessException
	 */
	private void checkAggregatedPoVO(AggregatedPoVO poVO, JSONObject json) throws BusinessException{
		checkProdmode(poVO);
		checkBom(poVO);
		checkNbwrnum(poVO, json);
	}
	
	/**
	 * 检查计划数量
	 * @param poVO
	 * @param json
	 * @throws BusinessException
	 */
	private void checkNbwrnum(AggregatedPoVO poVO, JSONObject json) throws BusinessException{
		StringBuffer sql = new StringBuffer();
		sql.append("select sum(mm_wr_product.nbwrnum) as nbwrnum from mm_wr inner join mm_wr_product on ");
		sql.append("mm_wr.pk_wr = mm_wr_product.pk_wr where nvl(mm_wr.dr,0) = 0 and nvl(mm_wr_product.dr,0) = 0 ");
		sql.append("and mm_wr.vdef1 = '"+poVO.getParentVO().getVbillcode()+"' ");
		sql.append("and mm_wr.pk_group = '"+poVO.getParentVO().getPk_group()+"' ");
		sql.append("and mm_wr.pk_org = '"+poVO.getParentVO().getPk_org()+"' ");
		Integer nbwrnum = (Integer) bs.executeQuery(sql.toString(), new ColumnProcessor());
		UFDouble num = new UFDouble();
		if(nbwrnum == null){
			num = UFDouble.ZERO_DBL;
		}else{
			num = new UFDouble(nbwrnum);
		}
		JSONObject arry = (JSONObject) json.getJSONArray("item").get(0);
		UFDouble reqnum = new UFDouble(arry.getString("realcount"));
		if(poVO.getParentVO().getNastnum().compareTo(num.add(reqnum)) < 0){
			throw new BusinessException("计划订单【"+poVO.getParentVO().getVbillcode()+"】计划产出数量【"+poVO.getParentVO().getNastnum()+"】，" +
					"现生产报告累计完工数量【"+num+"】,MES上传实际数【"+reqnum+"】.生产数量已经超出计划产出数量！！！");
		}
	}
	
	/**
	 * 校验生产模式
	 * @param poVO
	 * @throws BusinessException
	 */
	private void checkProdmode(AggregatedPoVO poVO) throws BusinessException{
		Integer prodmode = queryProdmode(poVO.getParentVO().getCmaterialid());
		//生产模式校验
		if(prodmode == 1){
			throw new BusinessException("计划订单【"+poVO.getParentVO().getVbillcode()+"】的物料生产模式为“流程制造”，非“离散制造”，请检查！");
		}
	}
	
	/**
	 * 检查计划订单生产物料是否有BOM信息
	 * @param poVO
	 * @throws BusinessException
	 */
	private void checkBom(AggregatedPoVO poVO) throws BusinessException{
		if(!isExistBom(poVO.getParentVO().getCmaterialvid(), poVO.getParentVO().getCmaterialid())){
			throw new BusinessException("计划订单【"+poVO.getParentVO().getVbillcode()+"】的计划生产物料未建立BOM，请检查！");
		}
	}
	
	/**
	 * 查询物料生产模式
	 * @param pk_material
	 * @return
	 * @throws BusinessException
	 */
	private Integer queryProdmode(String pk_material) throws BusinessException{
		StringBuffer sql =  new StringBuffer();
		sql.append("select prodmode from bd_materialprod where nvl(dr,0) = 0 and pk_material = '"+pk_material+"'"); 
		Integer result = (Integer) bs.executeQuery(sql.toString(), new ColumnProcessor());
		return result;
	} 
	
	/**
	 * 查询是否查找BOM
	 * @param pk_material
	 * @param pk_source
	 * @return
	 * @throws BusinessException
	 */
	private boolean isExistBom(String pk_material, String pk_source) throws BusinessException{
		StringBuffer sql = new StringBuffer();
		sql.append("select null from bd_bom where nvl(dr,0) = 0 and hfversiontype = 1 and hbdefault = 'Y' and hcmaterialid = '"+pk_source+"' and hcmaterialvid = '"+pk_material+"'");
		if(getBaseDAO().executeUpdate(sql.toString()) > 0){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取报告类型主键
	 * @param code
	 * @param pk_group
	 * @return
	 * @throws BusinessException
	 */
	private String getVtrantypeId(String code, String pk_group) throws BusinessException{
		if(this.kv.containsKey(code)){
			return kv.get(code);
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select pk_billtypeid from  bd_billtype ");
		sql.append(" where ( istransaction = 'Y' and pk_group='0001A110000000000IKT' and nvl(islock, 'N')='N' ");
		sql.append("and   1=1  and parentbilltype='55A4'  and pk_group='0001A110000000000IKT' and pk_billtypecode = '"+code+"')");
		String pk_billtypeid = (String) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(sql.toString(), new ColumnProcessor());
		if(pk_billtypeid == null){
			throw new BusinessException("NC不存在报告类型【"+code+"】，请维护！");
		}
		this.kv.put(code, pk_billtypeid);
		return pk_billtypeid;
	}
	
}
