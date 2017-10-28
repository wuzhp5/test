package nc.itf.sync.util;

import nc.vo.ic.m4e.entity.TransInVO;
import nc.vo.ic.m4y.entity.TransOutVO;
import nc.vo.mmpac.dmo.entity.AggDmoVO;
import nc.vo.mmpac.pickm.entity.PickmViewVO;
import nc.vo.mmpps.mps0202.AggregatedPoVO;
import nc.vo.pub.BusinessException;

public interface IUtilRequiresNew {
	
	/**
	 * 离散生产订单投放
	 * @param orginalAggVO
	 * @return
	 * @throws BusinessException
	 */
	public AggDmoVO putDmoBill_RequiresNew(AggDmoVO orginalAggVO) throws BusinessException;
	
	/**
	 * 离散生产订单下达
	 * @param poVO
	 * @return
	 * @throws BusinessException
	 */
	public AggregatedPoVO doPoRelease_RequiresNew(AggregatedPoVO poVO) throws BusinessException;
	
	/**
	 * MPS计划订单确认
	 * @param poVO
	 * @return
	 * @throws BusinessException
	 */
	public AggregatedPoVO doPoConfirm_RequiresNew(AggregatedPoVO poVO) throws BusinessException;
	
	/**
	 * 库存出入库自动捡货
	 * @param view
	 * @return
	 * @throws BusinessException
	 */
	public PickmViewVO[] issue_RequiresNew(PickmViewVO[] view) throws BusinessException;
	
	/**
	 * 调拨出库保存
	 * @param bill
	 * @return
	 * @throws BusinessException
	 */
	public TransOutVO saveTransOutVO_RequiresNew(TransOutVO bill) throws BusinessException;
	
	/**
	 * 调拨入库单保存
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
