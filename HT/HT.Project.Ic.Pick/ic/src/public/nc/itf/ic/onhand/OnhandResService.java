package nc.itf.ic.onhand;

import java.util.Map;

import nc.vo.ic.general.define.ICBillVO;
import nc.vo.ic.onhand.define.ICBillPickResults;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.ic.onhand.entity.OnhandVO;
import nc.vo.ic.onhand.pub.OnhandSelectDim;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.sc.m61.entity.SCOrderIssueVO;

/**
 * <p>
 * <b>�ִ�����Դ����</b>
 * 
 * @version v60
 * @since v60
 * @author yangb
 * @time 2010-4-15 ����11:58:24
 */
public interface OnhandResService {

  /**
   * ί�ⷢ�ϵĽ�����ݻ�ȡ
   * 
   * @param billvos
   * @return
   * @throws BusinessException
   */
  public SCOrderIssueVO[] getSCOrderIssueVOs(AggregatedValueObject[] billvos)
      throws BusinessException;

  /**
   * ���������������Զ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param billvo
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author yangb
   * @time 2010-9-2 ����10:14:27
   */
  public ICBillPickResults pickAuto(ICBillVO billvo) throws BusinessException;
  
  
  /**
   * ���������������Զ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param billvo
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author yangb
   * @time 2010-9-2 ����10:14:27
   */
  public ICBillPickResults pickAuto(ICBillVO billvo,boolean falg) throws BusinessException;

  /**
   * ���������������Զ����
   * <p>
   * <b>����˵��</b>
   * 
   * @param bills
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author yangb
   * @time 2010-9-2 ����10:16:47
   */
  public ICBillVO[] pickAuto(ICBillVO[] bills) throws BusinessException;

  /**
   * ����������������ѯ���ݵ��ִ���(�Զ���������ƽ��ʹ�ã����˷��Զ���������ƽ������)
   * 
   * @author yangb
   * @time 2010-6-5 ����10:43:05
   */
  public OnhandVO[] queryOnhandVOByBills(ICBillVO[] bills)
      throws BusinessException;

  /**
   * ����������������ѯ�ִ���
   * <p>
   * ���ϸ�ƥ��Ĳ�ѯ��ֻ�÷ǿ�ά���ֶ������� <b>Ĭ�ϲ�ѯ����ά���ֶΣ��������ܴ����������úͲ����õĽ��</b>
   * 
   * @param dimvos ��ѯά��
   * @since 6.0
   * @author yangb
   * @time 2010-7-8 ����09:18:24
   */
  public OnhandVO[] queryOnhandVOByDims(OnhandDimVO[] dimvos)
      throws BusinessException;

  /**
   * ����������������ѯ�ִ���
   * <p>
   * <b>����˵��</b>
   * 
   * @param select Ҫ��ѯ���ֶ�
   * @param dimvos ����ά��
   * @param bqueryuseablestate
   * @param bytranstype
   * @return <p>
   * @since 6.0
   * @author liuzy
   * @time 2010-7-8 ����09:18:24
   */
  public OnhandVO[] queryOnhandVOByDims(OnhandSelectDim select,
      OnhandDimVO[] dimvos, boolean bqueryuseablestate, String bytranstype)
      throws BusinessException;

  /**
   * �������������������ִ���ά�Ȳ�ѯ���õĴ�����ΪԤ���ṩ�� ������ѯ���״̬���õĴ���
   * 
   * @author yangb
   * @time 2010-6-5 ����10:43:05
   */
  public OnhandVO[] queryUseableOnhand(OnhandDimVO[] dimvos)
      throws BusinessException;

  /**
   * �������������������ִ���ά�Ȳ�ѯ���õĴ�����Ϊ�������ṩ�� ������ѯ���״̬���õĴ���,
   * δ��ȥ��Ӱ��������Ĳֿ�
   * 
   * @author yangb
   * @time 2010-6-5 ����10:43:05
   */
  public OnhandVO[] queryUseableOnhandForAtp(OnhandDimVO[] dimvos)
      throws BusinessException;
 
  /**
   * ��ѯ�ϴ�����λ
   * 
   * @param pk_calbody
   * @param cwarehouseid
   * @param cmateiralvids
   * @return Map<����Vid�� ��λID>
   * @throws BusinessException
   */
  public Map<String,String> queryLastInLocation(String pk_calbody,String cwarehouseid, String[] cmateiralvids) 
      throws BusinessException;
  
  /**
   * 
   * ��ѯ������λ
   * @param pk_calbody
   * @param cwarehouseid
   * @param cmaterialvids
   * @return
   * @throws BusinessException
   */
  public Map<String, String> queryOnhandLocation(String pk_calbody, String cwarehouseid, String[] cmaterialvids) 
      throws BusinessException;
  
  
  /**
   * ��ѯ��������    (ʵ�ַ�����δ������������ѯ)
   * ʹ�ó�������ѯ��������
   * �����˷�Ʒ�֣���Ӱ��������Ĳֿ� �������״̬Ϊ�����õ�����
   * 
   * @param dimvos
   * @param bextendWarehouse �Ƿ�Ĭ�ϰ��ֿ�չ��
   * @return
   * @throws BusinessException
   */
  public OnhandVO[] queryAtpOnhand(OnhandDimVO[] dimvos, boolean bextendWarehouse) throws BusinessException;
  
  /**
   * ��ѯ��������    (ʵ�ַ�����δ������������ѯ)
   * ʹ�ó����� ������������ͬ��������ʱ��ѯ�ִ���
   * @param dimvos
   * @param tupdatetime
   * @param endtime
   * @param bextendWarehouse �Ƿ�Ĭ�ϰ��ֿ�չ��
   * @return
   * @throws BusinessException
   */
  public OnhandVO[] queryAtpOnhandUP(OnhandDimVO[] dimvos,UFDateTime tupdatetime,
      UFDateTime endtime, boolean bextendWarehouse) throws BusinessException;

  
}
