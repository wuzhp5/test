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
 * <b>�����ִ���������</b>
 * 
 * @version ���汾��
 * @since ��һ�汾��
 * @author yangb
 * @time 2010-4-16 ����01:05:00
 */
public class ICBillPickResults implements Serializable {

	private static final long serialVersionUID = 201008201108000513L;

	// �����ĵ��ݱ���VO
	private ICBillBodyVO[] curbillbodyvos;

	private Map<String, ICBillBodyVO[]> mapbodyvos;

	// ԭʼ�������Ȼ�����������Ķ�Ӧ��ϵ
	private transient Map<String, OnhandBalanceResult<ICBillOnhandReq>> maponhandbalanceresult;

	// ���ǰ�ĵ��ݱ���VO
	private ICBillBodyVO[] originbillbodyvos;

	// �������
	private List<OnhandBalanceResult<ICBillOnhandReq>> pickresults;

	// ���ȵĹ�����
	private transient ScaleUtils scale;

	
	public Map<String, ICBillBodyVO[]> getMapbodyvos() {
		return mapbodyvos;
	}

	public void setMapbodyvos(Map<String, ICBillBodyVO[]> mapbodyvos) {
		this.mapbodyvos = mapbodyvos;
	}
	
	/**
	 * ICBillPickResults �Ĺ�����
	 */
	public ICBillPickResults(List<OnhandBalanceResult<ICBillOnhandReq>> pickresults, ICBillBodyVO[] originbodyvos) {
		this.pickresults = pickresults;
		this.originbillbodyvos = originbodyvos;
	}

	/**
	 * ��ȡ�����ı���ICBillBodyVOs
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

		// ���ͻ�ͨ�Զ�������������к���ʾ
		if (!StringUtil.isSEmptyOrNull(sb.toString())) {
			ExceptionUtils.wrappBusinessException("���������ִ�������:\n" + sb.toString().substring(0, sb.toString().length() - 1));
		}
		this.curbillbodyvos = CollectionUtils.listToArray(retlist);
		// �����������������绻���� ���� ���
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
		 * // ���ͻ�ͨ�Զ�������������к���ʾ if (!StringUtil.isSEmptyOrNull(sb.toString())) {
		 * ExceptionUtils
		 * .wrappBusinessException("���������ִ�������:\n"+sb.toString().substring
		 * (0,sb.toString().length()-1)); } this.curbillbodyvos =
		 * CollectionUtils.listToArray(retlist); // �����������������绻���� ���� ���
		 * this.processBodyData(this.curbillbodyvos); return
		 * this.curbillbodyvos;
		 */
	}

	/**
	 * ������屸ע�����ԭʼ��Ӧ���������ڶ�Ӧ�������ܽ�棬���ִ�������
	 * 
	 * @param orivo
	 *            ԭʼ��
	 * @param curvos
	 *            ������
	 * @param curvos
	 *            �Ƿ��Ƶ�����-��ʾ��ϢΪ��Դ�����к�/��-�����к�
	 */

	private String processBodyNote(ICBillBodyVO orivo, ICBillBodyVO[] curvos, boolean isPushbill) {
		if (null == curvos) {
			IBDData mdata = MaterialAccessor.getDocByPk(orivo.getCmaterialvid());
			String csrcrowno = orivo.getVsourcerowno();
			String crowno = orivo.getCrowno();
			if (null == csrcrowno)
				csrcrowno = crowno;
			String error = "�к�:" + (isPushbill ? csrcrowno : crowno) + ", ���ϱ��� :" + mdata.getCode() + "\n";
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
			// wdata.getName().toString() + "�ֿ�" + mdata.getName().toString()
			// + "�����ִ�������";
			String csrcrowno = orivo.getVsourcerowno();
			String crowno = orivo.getCrowno();
			if (null == csrcrowno)
				csrcrowno = crowno;
			String error = "�к�:" + (isPushbill ? csrcrowno : crowno) + ", ���ϱ��� :" + mdata.getCode() + "\n";
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
	 * ��ȡ�����ı���ICBillBodyVO
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
		// �Ȱ��ִ���ά�ȷ��飬��������λ+��Ӧ��ⵥ��ID
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
			// ��Ϊ����ʵ���������,��Ҫ
			this.synBodyData(bodyvo, lbalances.get(0));
			ICLocationVO[] locs = null;
			List<ICLocationVO> listlocs = new ArrayList<ICLocationVO>();
			// ��Ϊ����ʵ����������������ԭ�е�����
			bodyvo.setNnum(null);
			bodyvo.setNassistnum(null);
			for (BalanceOnhandRes balance : lbalances) {
				UFDouble nnum = NCBaseTypeUtils.add(bodyvo.getNnum(), balance.getNnum());
				UFDouble nassistnum = NCBaseTypeUtils.add(bodyvo.getNassistnum(), balance.getNastnum());
				bodyvo.setNnum(this.getScale().adjustNumScale(nnum, bodyvo.getCunitid()));
				bodyvo.setNassistnum(this.getScale().adjustNumScale(nassistnum, bodyvo.getCastunitid()));
				// ������ë��
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
				// ���ͻ�ͨ�����ʱ�Ƚ��ȳ�����λ��ͬ����û�кϲ��У������ǰ������ʱ���ѯ�����󣬾�û�к��д���
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
	 * ���ִ���ά�ȷ���+��Ӧ��ⵥ����ID����������λ
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
	 * �����������������绻���� ���� ���
	 */
	private ICBillBodyVO[] processBodyData(ICBillBodyVO[] bodyvos) {
		if (bodyvos == null || bodyvos.length <= 0) {
			return bodyvos;
		}
		// �����к�
		VORowNoUtils.setVOsRowNoByRule(bodyvos, ICPubMetaNameConst.CROWNO);
		// ScaleUtils scaleutils = ScaleUtils.getScaleUtilAtBS();
		// TODO ����δ���� �ȴ���������
		ICBSContext context = new ICBSContext();
		for (int i = 0; i < bodyvos.length; i++) {
			bodyvos[i].setNcostmny(NCBaseTypeUtils.mult(bodyvos[i].getNcostprice(), bodyvos[i].getNnum()));
			bodyvos[i].setNplannedmny(NCBaseTypeUtils.mult(bodyvos[i].getNplannedprice(), bodyvos[i].getNnum()));
			// ʵ����������������Ϊ��ʱ������ҵ������
			if (bodyvos[i].getNnum() == null && bodyvos[i].getNassistnum() == null) {
				continue;
			}
			bodyvos[i].setDbizdate(context.getBizDate());
		}
		return bodyvos;
	}

	/**
	 * ���ü����Ļ�λ����
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
	 * ���ü����ı�������
	 */
	private void synBodyData(ICBillBodyVO bodyvo, BalanceOnhandRes res) {
		if (bodyvo == null || res == null) {
			return;
		}
		// �ִ���ά�ȵĵ�λ�͵��ݽ���ĸ���λ����ͬ��
		String[] onhandDims = StringUtil.getArrayWithOutStr(OnhandDimVO.getDimContentFields(), OnhandDimVO.CASTUNITID);
		OnhandDimVO handdimvo = res.getOnhanddimvo();
		for (String field : OnhandDimVO.getDimContentFields()) {
			if (field.equals(OnhandDimVO.VCHANGERATE)) {
				String vchangerate = handdimvo.getVchangerate();
				// ��������ʲ��ǽ�棬��ͬ��������
				if (StringUtil.isSEmptyOrNull(vchangerate)) {
					onhandDims = StringUtil.getArrayWithOutStr(onhandDims, OnhandDimVO.VCHANGERATE);
				}
				break;
			}
		}
		VOEntityUtil.copyVOByVO(bodyvo, onhandDims, res.getOnhanddimvo(), onhandDims);

		// ��������
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
