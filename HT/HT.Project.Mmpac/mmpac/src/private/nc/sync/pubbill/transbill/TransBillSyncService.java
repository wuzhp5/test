package nc.sync.pubbill.transbill;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import nc.bs.config.params.ParamsService;
import nc.bs.constrast.ConstrastService;
import nc.bs.framework.common.NCLocator;
import nc.itf.to.m5x.ITransOrderMaintain;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.pub.sync.utils.SyncUtil;
import nc.sync.pfxx.entity.PfxxServiceBaseInfo;
import nc.sync.pub.bc.ProcessBill2PfxxService;
import nc.vo.mmpps.mps0202.AggregatedPoVO;
import nc.vo.mmpps.mps0202.PoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.sync.param.MaterialParams;
import nc.vo.to.m5x.entity.BillHeaderVO;
import nc.vo.to.m5x.entity.BillItemVO;
import nc.vo.to.m5x.entity.BillVO;
import net.sf.json.JSONObject;

public class TransBillSyncService extends ProcessBill2PfxxService{
	private PfxxServiceBaseInfo info = new PfxxServiceBaseInfo();
	private Map<String, String> cache = new HashMap<String, String>();
	public TransBillSyncService() {
		super();
		info.setBillCodeField("vbillcode");
		info.setBillType("5X");
		info.setFileName("transbill");
		info.setItemNames(new String[] { "bodyfk" });
		info.setSender("MES");
	}
	
	public String service(JSONObject json) throws BusinessException{
		ConstrastService service = new ConstrastService();
		//校验不可为空字段
		service.checkNotNull(json, service.getNotNullFields(this.info.getBillType(), false));
		BillVO bill = new BillVO();
		bill.setParent(new BillHeaderVO());
		service.executeConstrService(info.getBillType(), json, bill, BillItemVO.class);
		fillBillVO(bill);
		BillVO[] res = NCLocator.getInstance().lookup(ITransOrderMaintain.class).insertTransOrder(new BillVO[]{bill});
		if(res == null){
			return null;
		}
		return SyncUtil.packSuccessInfo("调拨订单【"+bill.getParentVO().getVbillcode()+"】在NC中已经完成保存.", bill.getPrimaryKey(), bill.getParentVO().getVbillcode());
	}
	
	/**
	 * 填充AggVO
	 * @param bill
	 * @throws BusinessException
	 */
	private void fillBillVO(BillVO bill) throws BusinessException{
		fillFixedValueBillHeadVO(bill.getParentVO()); 
		fillFixedValueBillBodyVOs(bill.getChildrenVO());
		fillBillHeadVO(bill.getParentVO(), bill.getChildrenVO()[0]);
		fillBillHeadVO(bill.getParentVO());
		fillBillItemVOs(bill.getChildrenVO(), bill.getParentVO());
	}
	
	/**
	 * 填充表头固定值
	 * @param headVO
	 * @throws BusinessException
	 */
	private void fillFixedValueBillHeadVO(BillHeaderVO headVO) throws BusinessException{
		Map<String, String> map = getFieldOfVO(info.getFileName(), info.getItemNames()).getHeadDefField();
		Set<String> keys = map.keySet();
		for(String key : keys){
			headVO.setAttributeValue(key, map.get(key));
		}
		headVO.setStatus(VOStatus.NEW);
	}
	
	/**
	 * 填充表体固定值
	 * @param itemVOs
	 * @throws BusinessException
	 */
	private void fillFixedValueBillBodyVOs(BillItemVO[] itemVOs) throws BusinessException{
		for(BillItemVO itemVO : itemVOs){
			fillFixedValueBillBodyVO(itemVO);
			itemVO.setStatus(VOStatus.NEW);
		}
	}
	
	/**
	 * 填充单表体固定值
	 * @param itemVO
	 * @throws BusinessException
	 */
	private void fillFixedValueBillBodyVO(BillItemVO itemVO) throws BusinessException{
		Map<String, String> map = getFieldOfVO(info.getFileName(), info.getItemNames()).getBodyDefField().get("bodyfk");
		Set<String> keys = map.keySet();
		for(String key : keys){
			itemVO.setAttributeValue(key, map.get(key));
		}
	}
	
	/**
	 * 填充表体
	 * @param itemVOs
	 * @param headVO
	 * @throws BusinessException
	 */
	private void fillBillItemVOs(BillItemVO[] itemVOs, BillHeaderVO headVO) throws BusinessException{
		for(BillItemVO itemVO : itemVOs){
			fillBillItemVO(itemVO, headVO);
		}
	}
	
	/**
	 * 填充但表体
	 * @param itemVO
	 * @param headVO
	 * @throws BusinessException
	 */
	private void fillBillItemVO(BillItemVO itemVO, BillHeaderVO headVO) throws BusinessException{
		String cinventoryid = itemVO.getCinventoryid();
		MaterialParams materialParams = paramService.queryMaterialParams(cinventoryid);
		itemVO.setCinventoryid(materialParams.getPk_material());
		itemVO.setCinventoryvid(materialParams.getPk_material_v());
		SyncUtil.initSameFileds(itemVO, new String[]{"cunitid", "cqtunitid"}, materialParams.getPk_measdoc());
		itemVO.setCastunitid(materialParams.getPk_apartmeasdoc());
		SyncUtil.initSameFileds(itemVO, new String[]{"vchangerate", "vqtunitrate"}, materialParams.getMeasrate());
		SyncUtil.initSameFileds(itemVO, new String[]{"dplanoutdate","dplanarrivedate"}, itemVO.getDbilldate());
		itemVO.setNqtunitnum(itemVO.getNnum());
		itemVO.setNastnum(SyncUtil.calNassitNum(materialParams.getMeasrate(), itemVO.getNnum()));
		itemVO.setPk_group(headVO.getPk_group());
		itemVO.setPk_org(headVO.getPk_org());
		String coutstordocid = itemVO.getCoutstordocid();
		String cinstordocid = itemVO.getCinstordocid();
		String pk_org = itemVO.getPk_org();
		String pk_group = itemVO.getPk_group();
		SyncUtil.initSameFileds(itemVO, new String[]{"coutstordocid","ctoutstordocid"}, getStrdoc(coutstordocid, pk_org, pk_group));
		SyncUtil.initSameFileds(itemVO, new String[]{"cinstordocid"}, getStrdoc(cinstordocid, pk_org, pk_group));
	}
	
	/**
	 * 设置仓库主键
	 * @param itemVO
	 * @throws BusinessException
	 */
	private String getStrdoc(String strdoc, String pk_org, String pk_group) throws BusinessException{
		if(!this.cache.containsKey(strdoc)){
			String pk_stordoc = queryCinstordocid(strdoc, pk_org, pk_group);
			if(pk_stordoc == null){
				throw new BusinessException("NC不存在编码为【"+strdoc+"】的仓库。");
			}
			this.cache.put(strdoc, pk_stordoc);
			return pk_stordoc;
		}
		return this.cache.get(strdoc);
	}
	
	/**
	 * 查询仓库主键
	 * @param itemVO
	 * @return
	 * @throws BusinessException
	 */
	private String queryCinstordocid(String strdoc, String pk_org, String pk_group) throws BusinessException{
		StringBuffer sql = new StringBuffer();
		sql.append("select pk_stordoc from bd_stordoc where nvl(dr,0) = 0 and code = '"+strdoc+"' ");
		sql.append("and pk_org = '"+pk_org+"' and pk_group = '"+pk_group+"'");
		return (String) bs.executeQuery(sql.toString(), new ColumnProcessor());
	}
	
	/**
	 * 填充表头信息
	 * @param headVO
	 * @throws BusinessException
	 */
	private void fillBillHeadVO(BillHeaderVO headVO) throws BusinessException{
		headVO.setCreationtime(new UFDateTime(headVO.getDbilldate().toDate()));
		AggregatedPoVO aggregatedPoVO = queryAggregatedPoVO(headVO.getVdef4());
		PoVO poVO = aggregatedPoVO.getParentVO();
		headVO.setPk_group(poVO.getPk_group());
		headVO.setCbiztypeid(ParamsService.getInstance().getValue("PARAMS0000000000006"));
		headVO.SetCtrantypeid(ParamsService.getInstance().getValue("PARAMS0000000000005"));
		headVO.setVtrantypecode(ParamsService.getInstance().getValue("PARAMS0000000000002"));
		String pk_org = headVO.getPk_org();
		Map<String, String> inRes = queryStockAndFinancePK(pk_org);
		String cinstockorgvid = headVO.getCinstockorgvid();
		Map<String, String> outRes = queryStockAndFinancePK(cinstockorgvid);
		// cinstockorgid
		SyncUtil.initSameFileds(headVO, new String[]{"cinstockorgid"}, inRes.get("pk_stockorg"));
		// cinstockorgvid
		SyncUtil.initSameFileds(headVO, new String[]{"cinstockorgvid"}, inRes.get("pk_stockorg_v"));
		// cinfinanceorgid
		SyncUtil.initSameFileds(headVO, new String[]{"cinfinanceorgid"}, inRes.get("pk_financeorg"));
		// cinfinanceorgvid
		SyncUtil.initSameFileds(headVO, new String[]{"cinfinanceorgvid"}, inRes.get("pk_financeorg_v"));
		// pk_org ctoutstockorgid
		SyncUtil.initSameFileds(headVO, new String[]{"pk_org", "ctoutstockorgid"}, outRes.get("pk_stockorg"));
		// pk_org_v ctoutstockorgvid
		SyncUtil.initSameFileds(headVO, new String[]{"pk_org_v", "ctoutstockorgvid"}, outRes.get("pk_stockorg_v"));
		// coutfinanceorgid ctoutfinanceorgid
		SyncUtil.initSameFileds(headVO, new String[]{"coutfinanceorgid", "ctoutfinanceorgid"}, outRes.get("pk_financeorg"));
		// coutfinanceorgvid ctoutfinanceorgvid
		SyncUtil.initSameFileds(headVO, new String[]{"coutfinanceorgvid", "ctoutfinanceorgvid"}, outRes.get("pk_financeorg_v"));
		checkMesCodeUnique(headVO);
	}
	
	/**s
	 * 填充表头信息
	 * @param headVO
	 * @param itemVO
	 * @throws BusinessException
	 */
	private void fillBillHeadVO(BillHeaderVO headVO, BillItemVO itemVO) throws BusinessException{
		headVO.setDbilldate(itemVO.getDbilldate());
		headVO.setPk_org(itemVO.getPk_org());
		headVO.setCinstockorgvid(itemVO.getCinstockorgid());
		
	}
	
	/**
	 * 查询计划订单
	 * @param vbillcode
	 * @return
	 * @throws BusinessException
	 */
	private AggregatedPoVO queryAggregatedPoVO(String vbillcode) throws BusinessException{
		AggregatedPoVO aggVO = new AggregatedPoVO();
		String sql = "select * from mm_plo where vbillcode = '"+vbillcode+"' and dr = 0";
		PoVO headVO = (PoVO) bs.executeQuery(sql, new BeanProcessor(PoVO.class));
		if(headVO == null){
			throw new BusinessException("【"+vbillcode+"】计划订单已被删除或不存在!");
		}
		aggVO.setParent(headVO);
		return aggVO;
	}
	
	private void checkMesCodeUnique(BillHeaderVO headVO) throws BusinessException{
		String sql = "select vbillcode from to_bill where nvl(dr,0) = 0 and pk_group = '"+headVO.getPk_group()+"' and pk_org = '"+headVO.getPk_org()
				+"' and vdef3 = '"+headVO.getVdef3()+"'";
		String vbillcode = (String) bs.executeQuery(sql, new ColumnProcessor());
		if(vbillcode != null){
			throw new BusinessException("MES请求订单编码【"+headVO.getVdef3()+"】已经在NC中生成订单【"+vbillcode+"】,请登录NC核对！");
		}
	}
	
	/**
	 * 
	 * @param code
	 * @return
	 * @throws BusinessException
	 */
	public Map<String, String> queryStockAndFinancePK(String code) throws BusinessException{
		String sql = "select stock.pk_stockorg,stock.pk_vid pk_stockorg_v,finance.pk_financeorg pk_financeorg,finance." +
				"pk_vid pk_financeorg_v from org_stockorg stock inner join org_financeorg finance on " +
				"stock.pk_financeorg = finance.pk_financeorg where nvl(stock.dr,0) = 0 and stock.enablestate= 2 " +
				"and stock.islastversion= 'Y' and stock.code = '"+code+"'";
		@SuppressWarnings("unchecked")
		Map<String, String> result = (Map<String, String>) bs.executeQuery(sql, new MapProcessor());
		if(result == null || result.size() == 0){
			throw new BusinessException("NC不存在编码【"+code+"】的库存组织。");
		}
		return result;
	}
	
}
