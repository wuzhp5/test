package nc.vo.ic.extend.bc;

import nc.vo.pub.SuperVO;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDateTime;

/**
 * 记录库存出库单据是否已回传的表bc_icbackrela
 * 
 * @since 6.5
 * @version 2016-1-28 下午12:45:03
 * @author jilu
 */
public class ICBackRelaVO extends SuperVO {

  private static final long serialVersionUID = 1L;

  private String cbilltype;

  private String cgeneralbid;

  private UFBoolean bsend;

  private UFBoolean bback;

  private UFDateTime ts;

  /**
   * 出入库单据类型
   */
  public static final String CBILLTYPE = "cbilltype";

  /**
   * 主键，和单据表体主键相同
   */
  public static final String CGENERALBID = "cgeneralbid";

  /**
   * 是否已下发
   */
  public static final String BSEND = "bsend";

  /**
   * 是否已回传
   */
  public static final String BBACK = "bback";

  /**
   * 时间戳
   */
  public static final String TS = "ts";

  /**
   * 表名
   */
  public static final String TABLE_NAME = "bc_icbackrela";

  public String getCbilltype() {
    return cbilltype;
  }

  public String getCgeneralbid() {
    return cgeneralbid;
  }

  public UFBoolean getBsend() {
    return bsend;
  }

  public UFBoolean getBback() {
    return bback;
  }

  public UFDateTime getTs() {
    return ts;
  }

  public void setCbilltype(String cbilltype) {
    this.cbilltype = cbilltype;
  }

  public void setCgeneralbid(String cgeneralbid) {
    this.cgeneralbid = cgeneralbid;
  }

  public void setBsend(UFBoolean bsend) {
    this.bsend = bsend;
  }

  public void setBback(UFBoolean bback) {
    this.bback = bback;
  }

  public void setTs(UFDateTime ts) {
    this.ts = ts;
  }

  @Override
  public String getPKFieldName() {
    return CGENERALBID;
  }

  @Override
  public String getPrimaryKey() {
    return getCgeneralbid();
  }

  @Override
  public String getTableName() {
    return TABLE_NAME;
  }

}
