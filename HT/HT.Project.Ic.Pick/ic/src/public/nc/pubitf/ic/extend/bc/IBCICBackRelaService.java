package nc.pubitf.ic.extend.bc;

import nc.vo.ic.extend.bc.ICBackRelaVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;

/**
 * Ϊ�˸����������¹��ܣ����ӵĽӿڡ�
 * ��¼�³���ⵥ���ݵ�bid���Ƿ��Ѿ��·����Ƿ��Ѿ��ش�
 * 
 * @since 6.5
 * @version 2016-1-29 ����1:32:33
 * @author jilu
 */
public interface IBCICBackRelaService {

  /**
   * ����һ������
   * 
   * @param vos ����ⵥ�ݱ���VO
   * @param billtype ���������
   * @return
   * @throws BusinessException
   */
  public ICBackRelaVO[] insertICBackRelaVOs(String[] cgeneralbids,
      String billtype) throws BusinessException;

  /**
   * ����Ϊ�ѻش�
   * 
   * @param cgeneralbids ����ⵥ�ݱ�������
   * @throws BusinessException
   */
  public void setICBBack(String[] cgeneralbids) throws BusinessException;
}
