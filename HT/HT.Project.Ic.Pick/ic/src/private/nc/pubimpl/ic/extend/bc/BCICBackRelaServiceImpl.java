package nc.pubimpl.ic.extend.bc;

import nc.bs.uap.lock.PKLock;
import nc.pubitf.ic.extend.bc.IBCICBackRelaService;
import nc.vo.ic.extend.bc.ICBackRelaVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class BCICBackRelaServiceImpl implements IBCICBackRelaService {

  @Override
  public ICBackRelaVO[] insertICBackRelaVOs(String[] cgeneralbids,
      String billtype) throws BusinessException {
    try {
      return BCICBackRelaTool.insertICBackRelaVOs(cgeneralbids, billtype);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public void setICBBack(String[] cgeneralbids) throws BusinessException {
    if (cgeneralbids == null || cgeneralbids.length == 0) {
      return;
    }

    try {
      // �ȼ�����PK����tsУ���Ȳ�����
      PKLock.getInstance().addBatchDynamicLock(cgeneralbids);

      BCICBackRelaTool.setBBack(cgeneralbids);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
  }

}
