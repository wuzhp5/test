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
 * <b>现存量资源</b>
 * 
 * @version v60
 * @since v60
 * @author yangb
 * @time 2010-4-10 下午09:36:47
 */
public class OnhandRes implements Serializable, Cloneable, DimObj {

	private static final long serialVersionUID = 2010090116080005L;

	// 入库单
	private String cbilltype;

	private String cgeneralbid;

	private String cgeneralhid;

	private String crowno;

	/**
	 * 生产日期 （世纪华通）
	 */
	private String vbodyuserdef4;

	public String getVbodyuserdef4() {
		return vbodyuserdef4;
	}

	public void setVbodyuserdef4(String vbodyuserdef4) {
		this.vbodyuserdef4 = vbodyuserdef4;
	}

	// 可分配辅数量
	private UFDouble nuseableastnum;

	// 可分配毛重数量
	private UFDouble nuseablegrossnum;

	// 可分配主数量
	private UFDouble nuseablenum;

	private OnhandDimVO onhanddimvo;

	private String vbillcode;

	private String vtrantypecode;

	/**
	 * OnhandRes 的构造子
	 */
	public OnhandRes() {
		// 默认构造
	}

	/**
	 * OnhandRes 的构造子
	 */
	public OnhandRes(OnhandVO handvo) {
		this.onhanddimvo = OnhandVOTools.getOnhandDimVO(handvo);
		this.nuseablenum = OnhandVOTools.calcHandNum(handvo);
		this.nuseableastnum = OnhandVOTools.calcHandAstNum(handvo);
		this.nuseablegrossnum = OnhandVOTools.calcHandGrossNum(handvo);
	}

	
	/**
	 * OnhandRes 的构造子
	 */
	public OnhandRes(OnhandVO handvo,String vbodyuserdef4) {
		this.onhanddimvo = OnhandVOTools.getOnhandDimVO(handvo);
		this.nuseablenum = OnhandVOTools.calcHandNum(handvo);
		this.nuseableastnum = OnhandVOTools.calcHandAstNum(handvo);
		this.nuseablegrossnum = OnhandVOTools.calcHandGrossNum(handvo);
		this.vbodyuserdef4=vbodyuserdef4;
	}

	/**
	 * 父类方法重写
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
	 * 父类方法重写
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
	 * 辅主数量资源是否可以使用
	 */
	public boolean isAstnumUseable() {
		return NCBaseTypeUtils.isGtZero(this.nuseableastnum);
	}

	/**
	 * 毛重数量资源是否可以使用
	 */
	public boolean isGrossnumUseable() {
		return NCBaseTypeUtils.isGtZero(this.nuseablegrossnum);
	}

	/**
	 * 主数量资源是否可以使用
	 */
	public boolean isNumUseable() {
		return NCBaseTypeUtils.isGtZero(this.nuseablenum);
	}

	/**
	 * 资源是否可以使用
	 */
	public boolean isUseable() {
		return NCBaseTypeUtils.isGtZero(this.nuseablenum)
				|| NCBaseTypeUtils.isGtZero(this.nuseableastnum)
				|| NCBaseTypeUtils.isGtZero(this.nuseablegrossnum);
	}

	/**
	 * @param cbilltype
	 *            要设置的 cbilltype
	 */
	public void setCbilltype(String cbilltype) {
		this.cbilltype = cbilltype;
	}

	/**
	 * @param cgeneralbid
	 *            要设置的 cgeneralbid
	 */
	public void setCgeneralbid(String cgeneralbid) {
		this.cgeneralbid = cgeneralbid;
	}

	/**
	 * @param cgeneralhid
	 *            要设置的 cgeneralhid
	 */
	public void setCgeneralhid(String cgeneralhid) {
		this.cgeneralhid = cgeneralhid;
	}

	/**
	 * @param crowno
	 *            要设置的 crowno
	 */
	public void setCrowno(String crowno) {
		this.crowno = crowno;
	}

	/**
	 * @param nuseableastnum
	 *            要设置的 nuseableastnum
	 */
	public void setNuseableastnum(UFDouble nuseableastnum) {
		this.nuseableastnum = nuseableastnum;
	}

	/**
	 * @param nuseablegrossnum
	 *            要设置的 nuseablegrossnum
	 */
	public void setNuseablegrossnum(UFDouble nuseablegrossnum) {
		this.nuseablegrossnum = nuseablegrossnum;
	}

	/**
	 * @param nuseablenum
	 *            要设置的 nuseablenum
	 */
	public void setNuseablenum(UFDouble nuseablenum) {
		this.nuseablenum = nuseablenum;
	}

	/**
	 * @param onhanddimvo
	 *            要设置的 onhanddimvo
	 */
	public void setOnhanddimvo(OnhandDimVO onhanddimvo) {
		this.onhanddimvo = onhanddimvo;
	}

	/**
	 * @param vbillcode
	 *            要设置的 vbillcode
	 */
	public void setVbillcode(String vbillcode) {
		this.vbillcode = vbillcode;
	}

	/**
	 * @param vtrantypecode
	 *            要设置的 vtrantypecode
	 */
	public void setVtrantypecode(String vtrantypecode) {
		this.vtrantypecode = vtrantypecode;
	}

}
