package nc.pubitf.ic.extend.bc;

import nc.vo.ic.extend.bc.ICBackRelaVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * 为了给条码增加新功能，增加的接口。
 * 记录下出入库单单据的bid其是否已经下发和是否已经回传
 * 
 * @since 6.5
 * @version 2016-1-29 下午1:32:33
 * @author jilu
 */
public interface IBCICBackRelaService {

  /**
   * 新增一条数据
   * 
   * @param vos 出入库单据表体VO
   * @param billtype 出入库类型
   * @return
   * @throws BusinessException
   */
  public ICBackRelaVO[] insertICBackRelaVOs(String[] cgeneralbids,
      String billtype) throws BusinessException;

  /**
   * 设置为已回传
   * 
   * @param cgeneralbids 出入库单据表体主键
   * @throws BusinessException
   */
  public void setICBBack(String[] cgeneralbids) throws BusinessException;
}
