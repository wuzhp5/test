package nc.vo.ic.onhand.define;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import nc.vo.ic.general.define.ICBillBodyVO;
import nc.vo.ic.location.ICLocationVO;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.ic.onhand.entity.OnhandSNVO;
import nc.vo.ic.pub.define.ICBillTableInfo;
import nc.vo.ic.pub.util.NCBaseTypeUtils;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDouble;

/**
 * <p>
 * <b>��������Դ</b>
 * 
 * @version v60
 * @since ��һ�汾��
 * @author yangb
 * @time 2010-4-10 ����09:51:25
 */
public class BalanceOnhandRes implements Serializable {

  private static final long serialVersionUID = 2010090116080001L;

  // ��ⵥ
  private String cbilltype;

  private String cgeneralbid;

  private String cgeneralhid;

  private String crowno;
  
  /**
 * ��������
 */
private String vbodyuserdef4;

  public String getVbodyuserdef4() {
	return vbodyuserdef4;
}

public void setVbodyuserdef4(String vbodyuserdef4) {
	this.vbodyuserdef4 = vbodyuserdef4;
}

// ����ĵ�Ʒ
  private List<OnhandSNVO> listsnvo;

  // ����ĸ�����
  private UFDouble nastnum;

  // �����ë������
  private UFDouble ngrossnum;

  // �����������
  private UFDouble nnum;

  private OnhandDimVO onhanddimvo;

  private String vbillcode;

  private String vtrantypecode;

  /**
   * BalanceOnhandRes �Ĺ�����
   */
  public BalanceOnhandRes() {
    // Ĭ�Ϲ���
  }

  /**
   * BalanceOnhandRes �Ĺ�����
   */
  public BalanceOnhandRes(
      OnhandRes res) {
    this.onhanddimvo = res.getOnhanddimvo();
    this.cgeneralhid = res.getCgeneralhid();
    this.cgeneralbid = res.getCgeneralbid();
    this.cbilltype = res.getCbilltype();
    this.vbillcode = res.getVbillcode();
    this.vtrantypecode = res.getVtrantypecode();
    this.crowno = res.getCrowno();
    this.vbodyuserdef4=res.getVbodyuserdef4();
  }

  /**
   * @param listsnvo
   *          Ҫ���õ� listsnvo
   */
  public void addSnVo(OnhandSNVO[] snvos) {
    if (snvos == null || snvos.length <= 0) {
      return;
    }
    if (this.listsnvo == null) {
      this.listsnvo = new ArrayList<OnhandSNVO>();
    }
    for (OnhandSNVO snvo : snvos) {
      this.listsnvo.add(snvo);
    }
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
   * @return listsnvo
   */
  public List<OnhandSNVO> getListsnvo() {
    return this.listsnvo;
  }

  /**
   * 
   */
  public ICLocationVO[] getLocatonVO(ICBillBodyVO body) {
    if ((this.onhanddimvo == null || this.onhanddimvo.getClocationid() == null)
        && (this.listsnvo == null || this.listsnvo.size() <= 0)) {
      return null;
    }
    ICBillTableInfo ti = ICBillTableInfo.getICBillTableInfo(body.getBillType());
    List<ICLocationVO> retlist = new ArrayList<ICLocationVO>();
    ICLocationVO locvo = null;
    UFDouble d1 = this.nnum;
    UFDouble d2 = this.nastnum;
    UFDouble d3 = this.ngrossnum;
    if (this.listsnvo != null && this.listsnvo.size() > 0) {
      for (int i = 0; i < this.listsnvo.size(); i++) {
        locvo = ti.createLocationVO();
        locvo.setStatus(VOStatus.NEW);
        this.setLocData(body, locvo, this.listsnvo.get(i));
        retlist.add(locvo);
        d1 = NCBaseTypeUtils.sub(d1, locvo.getNnum());
        d2 = NCBaseTypeUtils.sub(d2, locvo.getNassistnum());
        d3 = NCBaseTypeUtils.sub(d3, locvo.getNgrossnum());
      }
    }
    if (NCBaseTypeUtils.isGtZero(d1)
        && this.onhanddimvo.getClocationid() != null) {
      locvo = ti.createLocationVO();
      locvo.setStatus(VOStatus.NEW);
      this.setLocData(body, locvo);
      locvo.setNnum(d1);
      locvo.setNassistnum(d2);
      locvo.setNgrossnum(d3);
      retlist.add(locvo);
    }
    if (retlist.size() > 0) {
      return retlist.toArray((ICLocationVO[]) Array.newInstance(retlist.get(0)
          .getClass(), retlist.size()));
    }
    return null;
  }

  /**
   * @return nastnum
   */
  public UFDouble getNastnum() {
    return this.nastnum;
  }

  /**
   * @return ngrossnum
   */
  public UFDouble getNgrossnum() {
    return this.ngrossnum;
  }

  /**
   * @return nnum
   */
  public UFDouble getNnum() {
    return this.nnum;
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
   * @param cbilltype
   *          Ҫ���õ� cbilltype
   */
  public void setCbilltype(String cbilltype) {
    this.cbilltype = cbilltype;
  }

  /**
   * @param cgeneralbid
   *          Ҫ���õ� cgeneralbid
   */
  public void setCgeneralbid(String cgeneralbid) {
    this.cgeneralbid = cgeneralbid;
  }

  /**
   * @param cgeneralhid
   *          Ҫ���õ� cgeneralhid
   */
  public void setCgeneralhid(String cgeneralhid) {
    this.cgeneralhid = cgeneralhid;
  }

  /**
   * @param crowno
   *          Ҫ���õ� crowno
   */
  public void setCrowno(String crowno) {
    this.crowno = crowno;
  }

  /**
   * @param listsnvo
   *          Ҫ���õ� listsnvo
   */
  public void setListsnvo(List<OnhandSNVO> listsnvo) {
    this.listsnvo = listsnvo;
  }

  /**
   * @param nastnum
   *          Ҫ���õ� nastnum
   */
  public void setNastnum(UFDouble nastnum) {
    this.nastnum = nastnum;
  }

  /**
   * @param ngrossnum
   *          Ҫ���õ� ngrossnum
   */
  public void setNgrossnum(UFDouble ngrossnum) {
    this.ngrossnum = ngrossnum;
  }

  /**
   * @param nnum
   *          Ҫ���õ� nnum
   */
  public void setNnum(UFDouble nnum) {
    this.nnum = nnum;
  }

  /**
   * @param onhanddimvo
   *          Ҫ���õ� onhanddimvo
   */
  public void setOnhanddimvo(OnhandDimVO onhanddimvo) {
    this.onhanddimvo = onhanddimvo;
  }

  /**
   * @param vbillcode
   *          Ҫ���õ� vbillcode
   */
  public void setVbillcode(String vbillcode) {
    this.vbillcode = vbillcode;
  }

  /**
   * @param vtrantypecode
   *          Ҫ���õ� vtrantypecode
   */
  public void setVtrantypecode(String vtrantypecode) {
    this.vtrantypecode = vtrantypecode;
  }

  /**
   * ���û�λ����
   */
  private void setLocData(ICBillBodyVO bodyvo, ICLocationVO locvo) {
    locvo.setCgeneralbid(bodyvo.getCgeneralbid());
    locvo.setPk_group(bodyvo.getPk_group());
    locvo.setCorpoid(bodyvo.getCorpoid());
    locvo.setCorpvid(bodyvo.getCorpvid());
    locvo.setClocationid(this.onhanddimvo.getClocationid());
  }

  /**
   * ���õ�Ʒ��λ����
   */
  private void setLocData(ICBillBodyVO body, ICLocationVO locvo, OnhandSNVO snvo) {
    this.setLocData(body, locvo);
    locvo.setVserialcode(snvo.getVsncode());
    locvo.setVbarcode(snvo.getVbarcode());
    locvo.setVbarcodesub(snvo.getVbarcodesub());
    locvo.setVboxbarcode(snvo.getVboxbarcode());
    if (!NCBaseTypeUtils.isNullOrZero(body.getNnum())) {
      UFDouble oneneg = NCBaseTypeUtils.negUFDouble(UFDouble.ONE_DBL);
      if (NCBaseTypeUtils.isGtZero(body.getNnum())) {
        locvo.setNnum(UFDouble.ONE_DBL);
      }
      else {
        locvo.setNnum(oneneg);
      }
      if (!NCBaseTypeUtils.isNullOrZero(body.getNassistnum())) {
        // �˴���Ʒ��渨���������ֵ�����ݵ�Ʒ��������͸������򵥼���һ�µ��ݵ�Ʒ���������������1����
        UFDouble nasnum = null;
        if (snvo.getNonhandastnum() != null
            && !NCBaseTypeUtils.isNullOrZero(snvo.getNonhandnum())) {
          nasnum =
              NCBaseTypeUtils
                  .div(snvo.getNonhandastnum(), snvo.getNonhandnum());
        }
        else {
          nasnum = UFDouble.ONE_DBL;
        }
        if (NCBaseTypeUtils.isGtZero(body.getNassistnum())) {
          locvo.setNassistnum(nasnum);
        }
        else {
          locvo.setNassistnum(NCBaseTypeUtils.negUFDouble(nasnum));
        }
      }
      if (!NCBaseTypeUtils.isNullOrZero(body.getNgrossnum())) {
        if (NCBaseTypeUtils.isGtZero(body.getNgrossnum())) {
          locvo.setNgrossnum(UFDouble.ONE_DBL);
        }
        else {
          locvo.setNgrossnum(oneneg);
        }
      }
    }
  }

}
