package nc.vo.ic.onhand.define;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.ic.pub.env.ICBSContext;
import nc.itf.scmpub.reference.uap.bd.accesor.MaterialAccessor;
import nc.itf.scmpub.reference.uap.bd.accesor.StorDocAccessor;
import nc.vo.bd.accessor.IBDData;
import nc.vo.ic.general.define.ICBillBodyVO;
import nc.vo.ic.location.ICLocationVO;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.ic.onhand.pub.OnhandVOTools;
import nc.vo.ic.pub.define.ICPubMetaNameConst;
import nc.vo.ic.pub.lang.OnhandRes;
import nc.vo.ic.pub.util.CollectionUtils;
import nc.vo.ic.pub.util.NCBaseTypeUtils;
import nc.vo.ic.pub.util.StringUtil;
import nc.vo.ic.pub.util.VOEntityUtil;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.scale.ScaleUtils;
import nc.vo.pubapp.util.VORowNoUtils;

/**
 * <p>
 * <b>单据现存量捡货结果</b>
 * 
 * @version 本版本号
 * @since 上一版本号
 * @author yangb
 * @time 2010-4-16 下午01:05:00
 */
public class ICBillPickResults implements Serializable {

	private static final long serialVersionUID = 201008201108000513L;

	// 捡货后的单据表体VO
	private ICBillBodyVO[] curbillbodyvos;

	private Map<String, ICBillBodyVO[]> mapbodyvos;

	// 原始表体的自然序号与捡货结果的对应关系
	private transient Map<String, OnhandBalanceResult<ICBillOnhandReq>> maponhandbalanceresult;

	// 捡货前的单据表体VO
	private ICBillBodyVO[] originbillbodyvos;

	// 捡货后结果
	private List<OnhandBalanceResult<ICBillOnhandReq>> pickresults;

	// 精度的工具类
	private transient ScaleUtils scale;

	
	public Map<String, ICBillBodyVO[]> getMapbodyvos() {
		return mapbodyvos;
	}

	public void setMapbodyvos(Map<String, ICBillBodyVO[]> mapbodyvos) {
		this.mapbodyvos = mapbodyvos;
	}
	
	/**
	 * ICBillPickResults 的构造子
	 */
	public ICBillPickResults(List<OnhandBalanceResult<ICBillOnhandReq>> pickresults, ICBillBodyVO[] originbodyvos) {
		this.pickresults = pickresults;
		this.originbillbodyvos = originbodyvos;
	}

	/**
	 * 获取捡货后的表体ICBillBodyVOs
	 */
	public ICBillBodyVO[] getPickBodys(boolean isPushbill) {
		if (this.curbillbodyvos != null) {
			return this.curbillbodyvos;
		}
		List<ICBillBodyVO> retlist = new ArrayList<ICBillBodyVO>();
		ICBillBodyVO[] bodyvos = null;
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < this.originbillbodyvos.length; i++) {
			bodyvos = this.getPickBodys(i);
			if (bodyvos == null || bodyvos.length <= 0) {
				retlist.add(this.originbillbodyvos[i]);
				sb.append(this.processBodyNote(this.originbillbodyvos[i], null, isPushbill));
				continue;
			}
			sb.append(this.processBodyNote(this.originbillbodyvos[i], bodyvos, isPushbill));
			CollectionUtils.addArrayToList(retlist, bodyvos);
		}

		// 世纪华通自动拣货增加物料行号提示
		if (!StringUtil.isSEmptyOrNull(sb.toString())) {
			ExceptionUtils.wrappBusinessException("以下物料现存量不足:\n" + sb.toString().substring(0, sb.toString().length() - 1));
		}
		this.curbillbodyvos = CollectionUtils.listToArray(retlist);
		// 捡货后表体数据整理，如换算率 批次 相关
		this.processBodyData(this.curbillbodyvos);
		return this.curbillbodyvos;
	}

	public ICBillBodyVO[] getPickBodys() {
		return this.getPickBodys(false);
		/*
		 * if (this.curbillbodyvos != null) { return this.curbillbodyvos; }
		 * List<ICBillBodyVO> retlist = new ArrayList<ICBillBodyVO>();
		 * ICBillBodyVO[] bodyvos = null; StringBuffer sb = new StringBuffer();
		 * for (int i = 0; i < this.originbillbodyvos.length; i++) { bodyvos =
		 * this.getPickBodys(i); if (bodyvos == null || bodyvos.length <= 0) {
		 * retlist.add(this.originbillbodyvos[i]);
		 * sb.append(this.processBodyNote(this.originbillbodyvos[i], null));
		 * continue; } sb.append(this.processBodyNote(this.originbillbodyvos[i],
		 * bodyvos)); CollectionUtils.addArrayToList(retlist, bodyvos); }
		 * 
		 * // 世纪华通自动拣货增加物料行号提示 if (!StringUtil.isSEmptyOrNull(sb.toString())) {
		 * ExceptionUtils
		 * .wrappBusinessException("以下物料现存量不足:\n"+sb.toString().substring
		 * (0,sb.toString().length()-1)); } this.curbillbodyvos =
		 * CollectionUtils.listToArray(retlist); // 捡货后表体数据整理，如换算率 批次 相关
		 * this.processBodyData(this.curbillbodyvos); return
		 * this.curbillbodyvos;
		 */
	}

	/**
	 * 处理表体备注，如果原始行应发数量大于对应拣货结果总结存，报现存量不足
	 * 
	 * @param orivo
	 *            原始行
	 * @param curvos
	 *            拣货结果
	 * @param curvos
	 *            是否推单，是-提示信息为来源单据行号/否-单据行号
	 */

	private String processBodyNote(ICBillBodyVO orivo, ICBillBodyVO[] curvos, boolean isPushbill) {
		if (null == curvos) {
			IBDData mdata = MaterialAccessor.getDocByPk(orivo.getCmaterialvid());
			String csrcrowno = orivo.getVsourcerowno();
			String crowno = orivo.getCrowno();
			if (null == csrcrowno)
				csrcrowno = crowno;
			String error = "行号:" + (isPushbill ? csrcrowno : crowno) + ", 物料编码 :" + mdata.getCode() + "\n";
			return error;
		}
		UFDouble shouldNum = orivo.getNshouldnum();
		UFDouble shouldasNum = orivo.getNshouldassistnum();
		UFDouble totalNum = UFDouble.ZERO_DBL;
		UFDouble totalasNum = UFDouble.ZERO_DBL;
		for (ICBillBodyVO curvo : curvos) {
			totalNum = NCBaseTypeUtils.add(totalNum, curvo.getNnum());
			totalasNum = NCBaseTypeUtils.add(totalasNum, curvo.getNassistnum());
		}

		if (NCBaseTypeUtils.isGtZero(NCBaseTypeUtils.sub(shouldNum, totalNum))
				|| NCBaseTypeUtils.isGtZero(NCBaseTypeUtils.sub(shouldasNum, totalasNum))) {
			IBDData mdata = MaterialAccessor.getDocByPk(orivo.getCmaterialvid());
			// IBDData wdata =
			// StorDocAccessor.getDocByPk(orivo.getCbodywarehouseid());
			// String error =
			// wdata.getName().toString() + "仓库" + mdata.getName().toString()
			// + "物料现存量不足";
			String csrcrowno = orivo.getVsourcerowno();
			String crowno = orivo.getCrowno();
			if (null == csrcrowno)
				csrcrowno = crowno;
			String error = "行号:" + (isPushbill ? csrcrowno : crowno) + ", 物料编码 :" + mdata.getCode() + "\n";
			// ExceptionUtils.wrappBusinessException(error);

			// for (ICBillBodyVO curvo : curvos) {
			// if (StringUtil.isSEmptyOrNull(curvo.getVnotebody())) {
			// curvo.setVnotebody(OnhandRes.getOnhandErro());
			// }
			// }
			return error;
		}
		return "";
	}

	/**
	 * 获取捡货后的表体ICBillBodyVO
	 */
	public ICBillBodyVO[] getPickBodys(int row) {
		if (this.mapbodyvos != null && this.mapbodyvos.containsKey(String.valueOf(row))) {
			return this.mapbodyvos.get(String.valueOf(row));
		}
		OnhandBalanceResult<ICBillOnhandReq> result = this.getMapResults().get(String.valueOf(row));
		if (result == null) {
			return null;
		}
		List<ICBillBodyVO> retlist = new ArrayList<ICBillBodyVO>();
		if (result.getResults() == null || result.getResults().size() <= 0) {
			ICBillBodyVO[] vos = (ICBillBodyVO[]) Array.newInstance(result.getOnhandReq().getBodyvo().getClass(), 1);
			vos[0] = result.getOnhandReq().getBodyvo();
			return vos;
		}
		// 先按现存量维度分组，不包括货位+对应入库单行ID
		Map<String, List<BalanceOnhandRes>> mapgroup = this.groupBalanceOnhandRes(result.getResults());
		ICBillBodyVO bodyvo = (ICBillBodyVO) result.getOnhandReq().getBodyvo().clone();
		for (List<BalanceOnhandRes> lbalances : mapgroup.values()) {
			if (retlist.size() > 0) {
				bodyvo = (ICBillBodyVO) result.getOnhandReq().getBodyvo().clone();
				bodyvo.setStatus(VOStatus.NEW);
				bodyvo.setCgeneralbid(null);
				bodyvo.setCrowno(null);
				bodyvo.setPseudoColumn(bodyvo.getPseudoColumn());
				// bodyvo.setNshouldnum(null);
				// bodyvo.setNshouldassistnum(null);
			}
			// 因为存在实发数量拣货,需要
			this.synBodyData(bodyvo, lbalances.get(0));
			ICLocationVO[] locs = null;
			List<ICLocationVO> listlocs = new ArrayList<ICLocationVO>();
			// 因为存在实发数量拣货，需清除原有的数量
			bodyvo.setNnum(null);
			bodyvo.setNassistnum(null);
			for (BalanceOnhandRes balance : lbalances) {
				UFDouble nnum = NCBaseTypeUtils.add(bodyvo.getNnum(), balance.getNnum());
				UFDouble nassistnum = NCBaseTypeUtils.add(bodyvo.getNassistnum(), balance.getNastnum());
				bodyvo.setNnum(this.getScale().adjustNumScale(nnum, bodyvo.getCunitid()));
				bodyvo.setNassistnum(this.getScale().adjustNumScale(nassistnum, bodyvo.getCastunitid()));
				// 不处理毛重
				/*
				 * bodyvo.setNgrossnum(NCBaseTypeUtils.add(bodyvo.getNgrossnum(),
				 * balance.getNgrossnum()));
				 */
				locs = balance.getLocatonVO(bodyvo);
				if (locs == null || locs.length <= 0) {
					continue;
				}
				CollectionUtils.addArrayToList(listlocs, locs);
			}
			if (listlocs.size() > 0) {
				// add by jilu for
				// 世纪华通，拣货时先进先出，货位相同的行没有合并行，估计是按照入库时间查询出来后，就没有合行处理
				String[] groupkeys = new String[] { ICLocationVO.CLOCATIONID, ICLocationVO.VBARCODE, ICLocationVO.VSERIALCODE };
				String[] numfields = new String[] { ICLocationVO.NNUM, ICLocationVO.NASSISTNUM, ICLocationVO.NGROSSNUM };
				ICLocationVO[] listlocsCombined = VOEntityUtil.sumVOs(groupkeys, numfields, listlocs.toArray(new ICLocationVO[0]));

				for (int i = 0; i < listlocsCombined.length; i++) {
					listlocsCombined[i].setStatus(listlocs.get(0).getStatus());
				}

				bodyvo.setLocationVOs(listlocsCombined);
				// end
				// bodyvo.setLocationVOs(listlocs.toArray((ICLocationVO[]) Array
				// .newInstance(listlocs.get(0).getClass(), listlocs.size())));
			}
			retlist.add(bodyvo);
		}
		ICBillBodyVO[] retvos = CollectionUtils.listToArray(retlist);

		if (this.mapbodyvos == null) {
			this.mapbodyvos = new HashMap<String, ICBillBodyVO[]>();
		}
		this.mapbodyvos.put(String.valueOf(row), retvos);
		return retvos;
	}

	/**
   * 
   */
	private Map<String, OnhandBalanceResult<ICBillOnhandReq>> getMapResults() {
		if (this.maponhandbalanceresult == null) {
			this.maponhandbalanceresult = new HashMap<String, OnhandBalanceResult<ICBillOnhandReq>>();
			if (this.pickresults != null && this.pickresults.size() > 0) {
				for (OnhandBalanceResult<ICBillOnhandReq> result : this.pickresults) {
					this.maponhandbalanceresult.put(result.getOnhandReq().getRowno(), result);
				}
			}
		}
		return this.maponhandbalanceresult;
	}

	/**
	 * 按现存量维度分组+对应入库单表体ID，不包括货位
	 */
	private Map<String, List<BalanceOnhandRes>> groupBalanceOnhandRes(List<BalanceOnhandRes> lbalances) {
		String key = null;
		Map<String, List<BalanceOnhandRes>> mapgroup = new HashMap<String, List<BalanceOnhandRes>>();
		List<BalanceOnhandRes> lbalance = null;
		for (BalanceOnhandRes balance : lbalances) {
			key = OnhandVOTools.getAstDimContentKey(balance.getOnhanddimvo());
			key = key + balance.getCgeneralbid();
			lbalance = mapgroup.get(key);
			if (lbalance == null) {
				lbalance = new ArrayList<BalanceOnhandRes>();
				mapgroup.put(key, lbalance);
			}
			lbalance.add(balance);
		}
		return mapgroup;
	}

	/**
	 * 捡货后表体数据整理，如换算率 批次 相关
	 */
	private ICBillBodyVO[] processBodyData(ICBillBodyVO[] bodyvos) {
		if (bodyvos == null || bodyvos.length <= 0) {
			return bodyvos;
		}
		// 处理行号
		VORowNoUtils.setVOsRowNoByRule(bodyvos, ICPubMetaNameConst.CROWNO);
		// ScaleUtils scaleutils = ScaleUtils.getScaleUtilAtBS();
		// TODO 精度未处理 等待公共方法
		ICBSContext context = new ICBSContext();
		for (int i = 0; i < bodyvos.length; i++) {
			bodyvos[i].setNcostmny(NCBaseTypeUtils.mult(bodyvos[i].getNcostprice(), bodyvos[i].getNnum()));
			bodyvos[i].setNplannedmny(NCBaseTypeUtils.mult(bodyvos[i].getNplannedprice(), bodyvos[i].getNnum()));
			// 实发数量和主数量都为空时，不补业务日期
			if (bodyvos[i].getNnum() == null && bodyvos[i].getNassistnum() == null) {
				continue;
			}
			bodyvos[i].setDbizdate(context.getBizDate());
		}
		return bodyvos;
	}

	/**
	 * 设置捡货后的货位数据
	 */
	// private void synLocData(ICBillBodyVO bodyvo,BalanceOnhandRes
	// balance,ICLocationVO locvo) {
	// locvo.setCgeneralbid(bodyvo.getCgeneralbid());
	// locvo.setPk_group(bodyvo.getPk_group());
	// locvo.setCorpoid(bodyvo.getCorpoid());
	// locvo.setCorpvid(bodyvo.getCorpvid());
	// locvo.setNinspacenum(balance.getNnum());
	// locvo.setNinspaceassistnum(balance.getNastnum());
	// locvo.setNingrossnum(balance.getNgrossnum());
	// }
	/**
	 * 设置捡货后的表体数据
	 */
	private void synBodyData(ICBillBodyVO bodyvo, BalanceOnhandRes res) {
		if (bodyvo == null || res == null) {
			return;
		}
		// 现存量维度的单位和单据界面的辅单位不能同步
		String[] onhandDims = StringUtil.getArrayWithOutStr(OnhandDimVO.getDimContentFields(), OnhandDimVO.CASTUNITID);
		OnhandDimVO handdimvo = res.getOnhanddimvo();
		for (String field : OnhandDimVO.getDimContentFields()) {
			if (field.equals(OnhandDimVO.VCHANGERATE)) {
				String vchangerate = handdimvo.getVchangerate();
				// 如果换算率不记结存，不同步换算率
				if (StringUtil.isSEmptyOrNull(vchangerate)) {
					onhandDims = StringUtil.getArrayWithOutStr(onhandDims, OnhandDimVO.VCHANGERATE);
				}
				break;
			}
		}
		VOEntityUtil.copyVOByVO(bodyvo, onhandDims, res.getOnhanddimvo(), onhandDims);

		// 生产日期
		bodyvo.setVbdef4(res.getVbodyuserdef4());

		bodyvo.setCcorrespondtype(res.getCbilltype());
		bodyvo.setCcorrespondtranstype(res.getVtrantypecode());
		bodyvo.setCcorrespondcode(res.getVbillcode());
		bodyvo.setCcorrespondhid(res.getCgeneralhid());
		bodyvo.setCcorrespondbid(res.getCgeneralbid());
		bodyvo.setCcorrespondrowno(res.getCrowno());
	}

	private ScaleUtils getScale() {
		if (this.scale == null) {
			this.scale = ScaleUtils.getScaleUtilAtBS();
		}
		return this.scale;
	}
}
