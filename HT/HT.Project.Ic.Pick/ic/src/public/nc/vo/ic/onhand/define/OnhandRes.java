package nc.vo.ic.onhand.define;

import java.io.Serializable;

import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.ic.onhand.entity.OnhandVO;
import nc.vo.ic.onhand.pub.OnhandVOTools;
import nc.vo.ic.pub.util.DimObj;
import nc.vo.ic.pub.util.NCBaseTypeUtils;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>�ִ�����Դ</b>
 * 
 * @version v60
 * @since v60
 * @author yangb
 * @time 2010-4-10 ����09:36:47
 */
public class OnhandRes implements Serializable, Cloneable, DimObj {

	private static final long serialVersionUID = 2010090116080005L;

	// ��ⵥ
	private String cbilltype;

	private String cgeneralbid;

	private String cgeneralhid;

	private String crowno;

	/**
	 * �������� �����ͻ�ͨ��
	 */
	private String vbodyuserdef4;

	public String getVbodyuserdef4() {
		return vbodyuserdef4;
	}

	public void setVbodyuserdef4(String vbodyuserdef4) {
		this.vbodyuserdef4 = vbodyuserdef4;
	}

	// �ɷ��丨����
	private UFDouble nuseableastnum;

	// �ɷ���ë������
	private UFDouble nuseablegrossnum;

	// �ɷ���������
	private UFDouble nuseablenum;

	private OnhandDimVO onhanddimvo;

	private String vbillcode;

	private String vtrantypecode;

	/**
	 * OnhandRes �Ĺ�����
	 */
	public OnhandRes() {
		// Ĭ�Ϲ���
	}

	/**
	 * OnhandRes �Ĺ�����
	 */
	public OnhandRes(OnhandVO handvo) {
		this.onhanddimvo = OnhandVOTools.getOnhandDimVO(handvo);
		this.nuseablenum = OnhandVOTools.calcHandNum(handvo);
		this.nuseableastnum = OnhandVOTools.calcHandAstNum(handvo);
		this.nuseablegrossnum = OnhandVOTools.calcHandGrossNum(handvo);
	}

	
	/**
	 * OnhandRes �Ĺ�����
	 */
	public OnhandRes(OnhandVO handvo,String vbodyuserdef4) {
		this.onhanddimvo = OnhandVOTools.getOnhandDimVO(handvo);
		this.nuseablenum = OnhandVOTools.calcHandNum(handvo);
		this.nuseableastnum = OnhandVOTools.calcHandAstNum(handvo);
		this.nuseablegrossnum = OnhandVOTools.calcHandGrossNum(handvo);
		this.vbodyuserdef4=vbodyuserdef4;
	}

	/**
	 * ���෽����д
	 * 
	 * @see java.lang.Object#clone()
	 */
	@Override
	public OnhandRes clone() {
		try {
			OnhandRes co = (OnhandRes) super.clone();
			if (co.onhanddimvo != null) {
				co.onhanddimvo = (OnhandDimVO) co.onhanddimvo.clone();
			}
			return co;
		} catch (Exception e) {
			ExceptionUtils.wrappException(e);
		}
		return new OnhandRes();
	}

	/**
	 * ���෽����д
	 */
	@Override
	public Object getAttributeValue(String attributeName) {
		return this.onhanddimvo.getAttributeValue(attributeName);
	}

	/**
	 * @return cbilltype
	 */
	public String getCbilltype() {
		return this.cbilltype;
	}

	/**
	 * @return cgeneralbid
	 */
	public String getCgeneralbid() {
		return this.cgeneralbid;
	}

	/**
	 * @return cgeneralhid
	 */
	public String getCgeneralhid() {
		return this.cgeneralhid;
	}

	/**
	 * @return crowno
	 */
	public String getCrowno() {
		return this.crowno;
	}

	/**
	 * @return nuseableastnum
	 */
	public UFDouble getNuseableastnum() {
		return this.nuseableastnum;
	}

	/**
	 * @return nuseablegrossnum
	 */
	public UFDouble getNuseablegrossnum() {
		return this.nuseablegrossnum;
	}

	/**
	 * @return nuseablenum
	 */
	public UFDouble getNuseablenum() {
		return this.nuseablenum;
	}

	/**
	 * @return onhanddimvo
	 */
	public OnhandDimVO getOnhanddimvo() {
		return this.onhanddimvo;
	}

	/**
	 * @return vbillcode
	 */
	public String getVbillcode() {
		return this.vbillcode;
	}

	/**
	 * @return vtrantypecode
	 */
	public String getVtrantypecode() {
		return this.vtrantypecode;
	}

	/**
	 * ����������Դ�Ƿ����ʹ��
	 */
	public boolean isAstnumUseable() {
		return NCBaseTypeUtils.isGtZero(this.nuseableastnum);
	}

	/**
	 * ë��������Դ�Ƿ����ʹ��
	 */
	public boolean isGrossnumUseable() {
		return NCBaseTypeUtils.isGtZero(this.nuseablegrossnum);
	}

	/**
	 * ��������Դ�Ƿ����ʹ��
	 */
	public boolean isNumUseable() {
		return NCBaseTypeUtils.isGtZero(this.nuseablenum);
	}

	/**
	 * ��Դ�Ƿ����ʹ��
	 */
	public boolean isUseable() {
		return NCBaseTypeUtils.isGtZero(this.nuseablenum)
				|| NCBaseTypeUtils.isGtZero(this.nuseableastnum)
				|| NCBaseTypeUtils.isGtZero(this.nuseablegrossnum);
	}

	/**
	 * @param cbilltype
	 *            Ҫ���õ� cbilltype
	 */
	public void setCbilltype(String cbilltype) {
		this.cbilltype = cbilltype;
	}

	/**
	 * @param cgeneralbid
	 *            Ҫ���õ� cgeneralbid
	 */
	public void setCgeneralbid(String cgeneralbid) {
		this.cgeneralbid = cgeneralbid;
	}

	/**
	 * @param cgeneralhid
	 *            Ҫ���õ� cgeneralhid
	 */
	public void setCgeneralhid(String cgeneralhid) {
		this.cgeneralhid = cgeneralhid;
	}

	/**
	 * @param crowno
	 *            Ҫ���õ� crowno
	 */
	public void setCrowno(String crowno) {
		this.crowno = crowno;
	}

	/**
	 * @param nuseableastnum
	 *            Ҫ���õ� nuseableastnum
	 */
	public void setNuseableastnum(UFDouble nuseableastnum) {
		this.nuseableastnum = nuseableastnum;
	}

	/**
	 * @param nuseablegrossnum
	 *            Ҫ���õ� nuseablegrossnum
	 */
	public void setNuseablegrossnum(UFDouble nuseablegrossnum) {
		this.nuseablegrossnum = nuseablegrossnum;
	}

	/**
	 * @param nuseablenum
	 *            Ҫ���õ� nuseablenum
	 */
	public void setNuseablenum(UFDouble nuseablenum) {
		this.nuseablenum = nuseablenum;
	}

	/**
	 * @param onhanddimvo
	 *            Ҫ���õ� onhanddimvo
	 */
	public void setOnhanddimvo(OnhandDimVO onhanddimvo) {
		this.onhanddimvo = onhanddimvo;
	}

	/**
	 * @param vbillcode
	 *            Ҫ���õ� vbillcode
	 */
	public void setVbillcode(String vbillcode) {
		this.vbillcode = vbillcode;
	}

	/**
	 * @param vtrantypecode
	 *            Ҫ���õ� vtrantypecode
	 */
	public void setVtrantypecode(String vtrantypecode) {
		this.vtrantypecode = vtrantypecode;
	}

}
