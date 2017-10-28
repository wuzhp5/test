package nc.impl.sync.util;

import nc.bs.bd.baseservice.ArrayClassConvertUtil;
import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.itf.mmpac.dmo.IDmoPutService;
import nc.itf.mmpac.issue.IIssueBusinessService;
import nc.itf.mmpps.plo.IPloReleaseService;
import nc.itf.mmpps.plo.IPloSimpleBusiService;
import nc.itf.sync.util.IUtilRequiresNew;
import nc.itf.uap.pf.IPFBusiAction;
import nc.vo.ic.m4e.entity.TransInVO;
import nc.vo.ic.m4y.entity.TransOutVO;
import nc.vo.mmpac.dmo.entity.AggDmoVO;
import nc.vo.mmpac.pickm.entity.PickmViewVO;
import nc.vo.mmpps.mps0202.AggregatedPoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFBoolean;

public class UtilRequiresNewImpl implements IUtilRequiresNew{

	private BaseDAO baseDAO = new BaseDAO();
	@Override
	public AggDmoVO putDmoBill_RequiresNew(AggDmoVO orginalAggVO) throws BusinessException{
		AggDmoVO[] aggDmoVOs = NCLocator.getInstance().lookup(IDmoPutService.class).put(new AggDmoVO[]{orginalAggVO});
		return aggDmoVOs[0];
	}
	
	@Override
	public AggregatedPoVO doPoRelease_RequiresNew(AggregatedPoVO poVO) throws BusinessException{
		AggregatedPoVO[] aggVOs = NCLocator.getInstance().lookup(IPloReleaseService.class).doPoRelease(new AggregatedPoVO[]{poVO}, UFBoolean.TRUE);
		return aggVOs[0];
	}

	@Override
	public AggregatedPoVO doPoConfirm_RequiresNew(AggregatedPoVO poVO) throws BusinessException {
		AggregatedPoVO[] aggregatedPoVOs = NCLocator.getInstance().lookup(IPloSimpleBusiService.class).doPoConfirm(new AggregatedPoVO[]{poVO});
		return aggregatedPoVOs[0];
	}

	@Override
	public PickmViewVO[] issue_RequiresNew(PickmViewVO[] view) throws BusinessException {
		CircularlyAccessibleValueObject[] objs = NCLocator.getInstance().lookup(IIssueBusinessService.class).issue(view, null, 0);
		return ArrayClassConvertUtil.convert(objs, PickmViewVO.class);
	}

	@Override
	public TransOutVO saveTransOutVO_RequiresNew(TransOutVO bill) throws BusinessException {
		bill.getParentVO().setStatus(VOStatus.NEW);
		for (CircularlyAccessibleValueObject bvo : bill.getChildrenVO()) {
			bvo.setStatus(VOStatus.NEW);
		}
		IPFBusiAction service = (IPFBusiAction)NCLocator.getInstance().lookup(IPFBusiAction.class);
		return (TransOutVO) service.processAction("WRITE", bill.getParentVO().getCtrantypeid(), null, bill, null, null);
	}

	@Override
	public TransInVO saveTransInVO_RequiresNew(TransInVO bill) throws BusinessException {
		bill.getParentVO().setStatus(VOStatus.NEW);
		for (CircularlyAccessibleValueObject bvo : bill.getChildrenVO()) {
			bvo.setStatus(VOStatus.NEW);
		}
		IPFBusiAction service = (IPFBusiAction)NCLocator.getInstance().lookup(IPFBusiAction.class);
		return (TransInVO) service.processAction("WRITE", bill.getParentVO().getCtrantypeid(), null, bill, null, null);
	}

	@Override
	public void updatePickVO_RequiresNew(String pk, String taskcode) throws BusinessException {
		String sql = "update mm_pickm set vdef2 = '"+taskcode+"' where cpickmid = '"+pk+"'";
		baseDAO.executeUpdate(sql);
	}
	
	
}
