package nc.itf.sync.util;

import nc.vo.ic.m4e.entity.TransInVO;
import nc.vo.ic.m4y.entity.TransOutVO;
import nc.vo.mmpac.dmo.entity.AggDmoVO;
import nc.vo.mmpac.pickm.entity.PickmViewVO;
import nc.vo.mmpps.mps0202.AggregatedPoVO;
import nc.vo.pub.BusinessException;

public interface IUtilRequiresNew {
	
	/**
	 * ��ɢ��������Ͷ��
	 * @param orginalAggVO
	 * @return
	 * @throws BusinessException
	 */
	public AggDmoVO putDmoBill_RequiresNew(AggDmoVO orginalAggVO) throws BusinessException;
	
	/**
	 * ��ɢ���������´�
	 * @param poVO
	 * @return
	 * @throws BusinessException
	 */
	public AggregatedPoVO doPoRelease_RequiresNew(AggregatedPoVO poVO) throws BusinessException;
	
	/**
	 * MPS�ƻ�����ȷ��
	 * @param poVO
	 * @return
	 * @throws BusinessException
	 */
	public AggregatedPoVO doPoConfirm_RequiresNew(AggregatedPoVO poVO) throws BusinessException;
	
	/**
	 * ��������Զ����
	 * @param view
	 * @return
	 * @throws BusinessException
	 */
	public PickmViewVO[] issue_RequiresNew(PickmViewVO[] view) throws BusinessException;
	
	/**
	 * �������Ᵽ��
	 * @param bill
	 * @return
	 * @throws BusinessException
	 */
	public TransOutVO saveTransOutVO_RequiresNew(TransOutVO bill) throws BusinessException;
	
	/**
	 * ������ⵥ����
	 * @param bill
	 * @return
	 * @throws BusinessException
	 */
	public TransInVO saveTransInVO_RequiresNew(TransInVO bill) throws BusinessException;
	
	/**
	 * @param pk
	 * @param taskcode
	 * @throws BusinessException
	 */
	public void updatePickVO_RequiresNew(String pk, String taskcode) throws BusinessException;
}
