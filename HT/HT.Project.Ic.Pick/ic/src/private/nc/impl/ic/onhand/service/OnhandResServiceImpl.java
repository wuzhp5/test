package nc.impl.ic.onhand.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import nc.bs.bd.baseservice.ArrayClassConvertUtil;
import nc.bs.framework.common.NCLocator;
import nc.bs.ic.onhand.action.OnhandResBalance;
import nc.bs.ic.onhand.bp.OnhandBaseQry;
import nc.bs.ic.onhand.bp.OnhandDataFactory;
import nc.bs.ic.onhand.bp.OnhandLocationQry;
import nc.bs.ic.onhand.bp.OnhandSynQuery;
import nc.bs.ic.pub.env.ICBSContext;
import nc.impl.pubapp.env.BSContext;
import nc.itf.ic.onhand.OnhandResService;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.vo.ic.general.define.ICBillBodyVO;
import nc.vo.ic.general.define.ICBillHeadVO;
import nc.vo.ic.general.define.ICBillVO;
import nc.vo.ic.material.define.InvCalBodyVO;
import nc.vo.ic.onhand.define.ICBillOnhandReq;
import nc.vo.ic.onhand.define.ICBillPickResults;
import nc.vo.ic.onhand.define.OnhandBalanceResult;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.ic.onhand.entity.OnhandVO;
import nc.vo.ic.onhand.pub.HashVOUtils;
import nc.vo.ic.onhand.pub.OnhandQueryDim;
import nc.vo.ic.onhand.pub.OnhandSelectDim;
import nc.vo.ic.onhand.pub.OnhandVOTools;
import nc.vo.ic.pub.util.CollectionUtils;
import nc.vo.ic.pub.util.DimMatchedObj;
import nc.vo.ic.pub.util.StringUtil;
import nc.vo.ic.pub.util.VOEntityUtil;
import nc.vo.ic.pub.util.ValueCheckUtil;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;
import nc.vo.sc.m61.entity.SCOrderIssueVO;

/**
 * <p>
 * <b>结存资源服务</b>
 * 
 * @version v60
 * @since v60
 * @author yangb
 * @time 2010-4-15 下午10:14:36
 */
public class OnhandResServiceImpl implements OnhandResService {

	/**
	 * OnhandResServiceImpl 的构造子
	 */
	public OnhandResServiceImpl() {
		// 默认构造
	}

	@Override
	public SCOrderIssueVO[] getSCOrderIssueVOs(AggregatedValueObject[] billvos) throws BusinessException {
		if (ValueCheckUtil.isNullORZeroLength(billvos)) {
			return null;
		}
		try {
			return OnhandDataFactory.getOnhandDataSource(billvos[0]).getSCOrderIssueVOs(billvos);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	/**
	 * 01
	 */
	@Override
	public ICBillPickResults pickAuto(ICBillVO billvo) throws BusinessException {
		try {
			List<OnhandBalanceResult<ICBillOnhandReq>> retults = this.pickAutoInner(new ICBillVO[] { billvo });
			if (retults == null || retults.size() <= 0) {
				return null;
			}
			ICBillPickResults ret = new ICBillPickResults(retults, billvo.getBodys());
			ret.getPickBodys();
			return ret;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	private Map<String, ICBillVO> splitICBillVOs(ICBillVO billVO, Map<String, String> index) throws BusinessException {
		ICBillBodyVO[] bodyVOs = billVO.getBodys();
		ICBillVO oldVOs = (ICBillVO) billVO.clone();
		ICBillVO newVOs = (ICBillVO) billVO.clone();
		List<ICBillBodyVO> oldBodyVOs = new ArrayList<ICBillBodyVO>();
		List<ICBillBodyVO> newBodyVOs = new ArrayList<ICBillBodyVO>();
		int row = 0 ;
		for (ICBillBodyVO bodyVO : bodyVOs) {
			bodyVO.setCrowno((row+1)*10+"");
			index.put(bodyVO.getCrowno(), ""+row++);
			if (isRunOldPickMeth(bodyVO)) {
				oldBodyVOs.add(bodyVO);
			}else{
				newBodyVOs.add(bodyVO);
			}
		}
		Map<String, ICBillVO> res = new HashMap<String, ICBillVO>();
		if (oldBodyVOs.size() != 0) {
			oldVOs.setChildren(bodyVOs[0].getClass(), ArrayClassConvertUtil.convert(oldBodyVOs.toArray(), bodyVOs[0].getClass()));
			res.put("old", oldVOs);
		}
		if (newBodyVOs.size() != 0) {
			newVOs.setChildren(bodyVOs[0].getClass(), ArrayClassConvertUtil.convert(newBodyVOs.toArray(), bodyVOs[0].getClass()));
			res.put("new", newVOs);
		}
		return res;
	}

	private boolean isRunOldPickMeth(ICBillBodyVO bodyVO) throws BusinessException {
		List<String> results = queryPickInfo(bodyVO);
		for (String result : results) {
			if(!Pattern.matches("[0-9]{8}", result)){
				return true;
			}
			String year = result.substring(0, 4);
			String month = result.substring(4, 6);
			String day = result.substring(6, 8);
			try {
				new UFDate(year + "-" + month + "-" + day);
			} catch (Exception e) {
				return true;
			}
		}
		return false;
	}

	private List<String> queryPickInfo(ICBillBodyVO bodyVO) throws BusinessException {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT handdim.vbatchcode ,bd_rack.name AS rackname ");
		sql.append("FROM ic_onhandnum ic_onhandnum INNER JOIN ic_onhanddim handdim ON ic_onhandnum.pk_onhanddim = handdim.pk_onhanddim ");
		sql.append("LEFT JOIN scm_batchcode scm_batchcode ON handdim.pk_batchcode = scm_batchcode.pk_batchcode LEFT JOIN ");
		sql.append("bd_rack bd_rack ON handdim.clocationid = bd_rack.pk_rack ");
		sql.append("WHERE 1 = 1 ");
		sql.append("AND ic_onhandnum.dr = 0 ");
		sql.append("AND handdim.pk_group = '" + bodyVO.getPk_group() + "' ");
		sql.append("AND handdim.pk_org = '" + bodyVO.getPk_org() + "' ");
		sql.append("AND handdim.cwarehouseid = '" + bodyVO.getCbodywarehouseid() + "' ");
		sql.append("AND handdim.cmaterialvid = '" + bodyVO.getCmaterialvid() + "' ");
		sql.append("AND handdim.cmaterialoid = '" + bodyVO.getCmaterialoid() + "' ");
		sql.append("AND handdim.castunitid = '" + bodyVO.getCastunitid() + "' ");
		//sql.append("AND handdim.clocationid = '" + bodyVO.getClocationid() + "' ");
		sql.append("AND ic_onhandnum.nonhandnum > 0 ");
		@SuppressWarnings("unchecked")
		List<String> res = (List<String>) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(sql.toString(), new ColumnListProcessor());
		return res;
	}

	private ICBillPickResults combineICBillPickResults(ICBillPickResults oldRet, ICBillPickResults newRet, Map<String, String> index){
		if(oldRet == null){
			return newRet;
		}
		if(newRet == null){
			return oldRet;
		}
		Map<String, ICBillBodyVO[]> res = new HashMap<String, ICBillBodyVO[]>();
		Set<String> keys = oldRet.getMapbodyvos().keySet();
		for(String key : keys){
			ICBillBodyVO[] bodyVOs = oldRet.getMapbodyvos().get(key);
			String row = index.get(bodyVOs[0].getCrowno());
			index.keySet();
			res.put(row, bodyVOs);
		}
		Set<String> nkeys = newRet.getMapbodyvos().keySet();
		for(String key : nkeys){
			ICBillBodyVO[] bodyVOs = newRet.getMapbodyvos().get(key);
			String row = index.get(bodyVOs[0].getCrowno());
			res.put(row, bodyVOs);
		}
		newRet.setMapbodyvos(res);
		return newRet;
	}
	
	/**
	 * 自动捡货变更：查询的所有符合条件的批次号为八位日期型，则按标识按生产日期拣货；否则按系统预设逻辑捡货 
	 * by wuzhiping 2017-10-21 15:12:45
	 */
	@Override
	public synchronized ICBillPickResults pickAuto(ICBillVO billvo, boolean falg) throws BusinessException {
		try {
			Map<String, String> index = new HashMap<String, String>();
			Map<String, ICBillVO> res = splitICBillVOs(billvo, index);
			ICBillPickResults oldRet = null;
			if(res.containsKey("old")){
				oldRet = pickAuto(res.get("old"));
			}
			ICBillPickResults newRet = null;
			if(res.containsKey("new")){
				// 标识按生产日期拣货
				BSContext.getInstance().setSession("ManufactureDatePickAutoAction", true);
				
				List<OnhandBalanceResult<ICBillOnhandReq>> results = this.pickAutoInner(new ICBillVO[] { res.get("new") });
				if (results == null || results.size() <= 0) {
					return null;
				}
				ICBillPickResults ret = new ICBillPickResults(results, res.get("new").getBodys());
				ret.getPickBodys();
				newRet = ret;
			}
			return combineICBillPickResults(oldRet, newRet, index);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		} finally {
			// 用完释放Session
			BSContext.getInstance().removeSession("ManufactureDatePickAutoAction");
		}
		return null;
	}

	/**
   * 
   */
	@Override
	public ICBillVO[] pickAuto(ICBillVO[] bills) throws BusinessException {
		List<OnhandBalanceResult<ICBillOnhandReq>> retults = this.pickAutoInner(bills);
		if (retults == null || retults.size() <= 0) {
			return null;
		}
		try {
			List<ICBillBodyVO> lbodys = new ArrayList<ICBillBodyVO>();
			for (ICBillVO bill : bills) {
				ICBillBodyVO[] bodys = bill.getBodys();
				if (ValueCheckUtil.isNullORZeroLength(bodys)) {
					continue;
				}
				for (ICBillBodyVO body : bodys) {
					lbodys.add(body);
				}
			}
			ICBillPickResults ret = new ICBillPickResults(retults, lbodys.toArray(new ICBillBodyVO[lbodys.size()]));

			ICBillBodyVO[] pickbodys = ret.getPickBodys();
			MapList<Integer, ICBillBodyVO> mapPickBodyVOs = new MapList<Integer, ICBillBodyVO>();
			for (ICBillBodyVO pickbody : pickbodys) {
				mapPickBodyVOs.put(pickbody.getPseudoColumn(), pickbody);
			}
			for (ICBillVO bill : bills) {
				lbodys = mapPickBodyVOs.get(bill.getHead().getPseudoColumn());
				if (ValueCheckUtil.isNullORZeroLength(lbodys)) {
					continue;
				}
				bill.setChildrenVO(CollectionUtils.listToArray(lbodys));
			}
			return bills;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	/**
	 * 方法功能描述：查询单据的现存量(自动主辅计量平衡使用，过滤非自动主辅计量平衡物料)
	 * <p>
	 * 非严格匹配的查询，只用非空维度字段做过滤 <b>默认查询所有维度字段，不做汇总处理，包括可用和不可用的结存</b>
	 * 
	 * @param bills
	 *            查询维度
	 * @since 6.0
	 * @author yangb
	 * @time 2010-7-8 下午09:18:24
	 */
	@Override
	public OnhandVO[] queryOnhandVOByBills(ICBillVO[] bills) throws BusinessException {
		try {
			List<OnhandDimVO> ldimvos = OnhandVOTools.getOnhandDimVOs(bills, new ICBSContext().getInvInfo());
			if (ValueCheckUtil.isNullORZeroLength(ldimvos)) {
				return null;
			}
			ICBSContext context = new ICBSContext();
			OnhandDimVO[] dimvos = ldimvos.toArray(new OnhandDimVO[ldimvos.size()]);
			OnhandVOTools.getRealOnhandDim(context.getInvInfo(), dimvos);
			InvCalBodyVO[] invCalVOs = context.getInvInfo().getInvCalBodyVO(VOEntityUtil.getVOsValues(dimvos, OnhandDimVO.PK_ORG, String.class),
					VOEntityUtil.getVOsValues(dimvos, OnhandDimVO.CMATERIALVID, String.class));
			List<OnhandDimVO> lbalancevos = new ArrayList<OnhandDimVO>();
			Set<String> keysset = new HashSet<String>();
			String key = null;
			for (int i = 0; i < dimvos.length; i++) {
				if (!ValueCheckUtil.isTrue(invCalVOs[i].getAutobalancemeas())) {
					continue;
				}
				key = HashVOUtils.getContentKey(dimvos[i]);
				if (keysset.contains(key)) {
					continue;
				}
				lbalancevos.add(dimvos[i]);
			}
			if (ValueCheckUtil.isNullORZeroLength(lbalancevos)) {
				return null;
			}
			return this.queryOnhandVOByDims(lbalancevos.toArray(new OnhandDimVO[lbalancevos.size()]));
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	/**
	 * 方法功能描述：查询现存量
	 * <p>
	 * 非严格匹配的查询，只用非空维度字段做过滤 <b>默认查询所有维度字段，不做汇总处理，包括可用和不可用的结存</b>
	 * 
	 * @param dimvos
	 *            查询维度
	 * @since 6.0
	 * @author yangb
	 * @time 2010-7-8 下午09:18:24
	 */
	@Override
	public OnhandVO[] queryOnhandVOByDims(OnhandDimVO[] dimvos) throws BusinessException {
		try {
			OnhandSelectDim select = new OnhandSelectDim();
			select.setSum(false);
			select.addSelectFields(CollectionUtils.combineArrs(OnhandDimVO.getDimContentFields(), new String[] { OnhandDimVO.PK_ONHANDDIM,
					OnhandDimVO.VHASHCODE, OnhandDimVO.VSUBHASHCODE }));
			return this.queryOnhandVOByDims(select, dimvos, false, null);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	@Override
	public OnhandVO[] queryOnhandVOByDims(OnhandSelectDim select, OnhandDimVO[] dimvos, boolean bqueryuseablestate, String bytranstype)
			throws BusinessException {
		try {
			List<String> ltranstypes = null;
			if (bytranstype != null) {
				ltranstypes = new ArrayList<String>();
				ltranstypes.add(bytranstype);
			}
			return new OnhandBaseQry().queryOnhandVOByDims(select, dimvos, bqueryuseablestate, ltranstypes);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	/**
	 * 方法功能描述：根据现存量维度查询可用的存量（为预留提供） 仅仅查询库存状态可用的存量
	 * 
	 * @author yangb
	 * @time 2010-6-5 下午10:43:05
	 */
	@Override
	public OnhandVO[] queryUseableOnhand(OnhandDimVO[] dimvos) throws BusinessException {
		if (ValueCheckUtil.isNullORZeroLength(dimvos)) {
			return null;
		}
		try {
			OnhandSelectDim select = new OnhandSelectDim();
			select.setSum(false);
			select.addSelectFields(CollectionUtils.combineArrs(OnhandDimVO.getDimContentFields(), new String[] { OnhandDimVO.PK_ONHANDDIM,
					OnhandDimVO.VHASHCODE, OnhandDimVO.VSUBHASHCODE }));
			return this.queryOnhandVOByDims(select, dimvos, true, null);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	/**
	 * 方法功能描述：根据现存量维度查询可用的存量（为可用量提供） 仅仅查询库存状态可用的存量 未除去不影响可用量的仓库
	 * 
	 * @author yangb
	 * @time 2010-6-5 下午10:43:05
	 */
	@Override
	public OnhandVO[] queryUseableOnhandForAtp(OnhandDimVO[] dimvos) throws BusinessException {
		try {
			return new OnhandBaseQry().queryOnhandVOByDims(null, dimvos, true, null);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	/**
	 * 自动拣批次
	 */
	private List<OnhandBalanceResult<ICBillOnhandReq>> pickAutoInner(ICBillVO[] billvos) {
		if (ValueCheckUtil.isNullORZeroLength(billvos)) {
			return null;
		}
		List<String> ltranstypes = new ArrayList<String>();
		List<ICBillOnhandReq> lreqs = new ArrayList<ICBillOnhandReq>();
		int count = 0, totalrownum = 0;
		Integer billno = null;
		for (ICBillVO billvo : billvos) {
			ICBillBodyVO[] bodys = billvo.getBodys();
			ICBillHeadVO head = billvo.getHead();
			ICBillOnhandReq req = null;
			billno = Integer.valueOf(count);
			head.setPseudoColumn(billno);
			for (int i = 0; i < bodys.length; i++) {
				// 支持录入实发数量拣货
				/*
				 * // 填写了实发的则不捡货, 红字单据拣货和v57保持一致 if
				 * (!NCBaseTypeUtils.isNullOrZero(bodys[i].getNnum()) ||
				 * !NCBaseTypeUtils.isNullOrZero(bodys[i].getNassistnum())) { //
				 * 不拣货也需要自动加1，否则既有实发和应发行的时候，会串行（委外材料核销） totalrownum++; continue;
				 * }
				 */
				bodys[i].setPk_org(head.getPk_org());
				bodys[i].setPk_org_v(head.getPk_org_v());
				bodys[i].setCbodywarehouseid(head.getCwarehouseid());
				bodys[i].setPseudoColumn(billno);
				req = new ICBillOnhandReq(bodys[i]);
				req.setRowno(String.valueOf(totalrownum));
				totalrownum++;
				lreqs.add(req);
				if (!StringUtil.isSEmptyOrNull(head.getVtrantypecode())) {
					ltranstypes.add(head.getVtrantypecode());
				}
			}
			count++;
		}
		if (ValueCheckUtil.isNullORZeroLength(lreqs)) {
			return null;
		}
		ICBillOnhandReq[] reqs = new ICBillOnhandReq[lreqs.size()];
		reqs = lreqs.toArray(reqs);
		OnhandResBalance<ICBillOnhandReq> resb = new OnhandResBalance<ICBillOnhandReq>(reqs);
		resb.setCtranstype(ltranstypes);
		resb.onhandBalance();
		List<OnhandBalanceResult<ICBillOnhandReq>> results = new ArrayList<OnhandBalanceResult<ICBillOnhandReq>>();
		OnhandBalanceResult<ICBillOnhandReq> result = null;
		for (ICBillOnhandReq reqq : reqs) {
			result = resb.getResults(reqq);
			if (result != null) {
				results.add(result);
			}
		}
		return results;
	}

	@Override
	public Map<String, String> queryLastInLocation(String pk_calbody, String cwarehouseid, String[] cmaterialvids) throws BusinessException {
		try {
			return new OnhandLocationQry().queryLastInLocation(pk_calbody, cwarehouseid, cmaterialvids);
		} catch (Exception ex) {
			ExceptionUtils.marsh(ex);
			return new HashMap<String, String>();
		}

	}

	@Override
	public Map<String, String> queryOnhandLocation(String pk_calbody, String cwarehouseid, String[] cmaterialvids) throws BusinessException {
		try {
			return new OnhandLocationQry().queryOnhandLocation(pk_calbody, cwarehouseid, cmaterialvids);
		} catch (Exception ex) {
			ExceptionUtils.marsh(ex);
			return new HashMap<String, String>();
		}
	}

	@Override
	public OnhandVO[] queryAtpOnhand(OnhandDimVO[] dimvos, boolean bextendWarehouse) throws BusinessException {
		try {
			return new OnhandSynQuery().queryOnhandForAtp(null, getQueryDim(dimvos), bextendWarehouse);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return new OnhandVO[0];
	}

	private OnhandQueryDim[] getQueryDim(OnhandDimVO[] dimvos) {
		if (ValueCheckUtil.isNullORZeroLength(dimvos)) {
			return null;
		}
		DimMatchedObj<OnhandDimVO> handkey = null;
		OnhandQueryDim[] dimconds = new OnhandQueryDim[dimvos.length];
		for (int i = 0; i < dimvos.length; i++) {
			handkey = new DimMatchedObj<OnhandDimVO>(dimvos[i], OnhandDimVO.getDimContentFields());
			dimconds[i] = new OnhandQueryDim();
			dimconds[i].addDimCondition(handkey.getDimFields(), VOEntityUtil.getVOValues(dimvos[i], handkey.getDimFields()));
		}
		return dimconds;
	}

	@Override
	public OnhandVO[] queryAtpOnhandUP(OnhandDimVO[] dimvos, UFDateTime tupdatetime, UFDateTime endtime, boolean bextendWarehouse)
			throws BusinessException {
		return new OnhandSynQuery().queryOnhandForAtpUP(null, this.getQueryDim(dimvos), tupdatetime, endtime, bextendWarehouse);
	}

}
