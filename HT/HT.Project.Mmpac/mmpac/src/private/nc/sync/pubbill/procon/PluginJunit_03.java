package nc.sync.pubbill.procon;

import java.util.ArrayList;
import java.util.List;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.itf.ic.m4d.IMaterialOutMaintain;
import nc.itf.ic.onhand.OnhandResService;
import nc.itf.uap.pf.IPFBusiAction;
import nc.itf.uap.pf.IPfExchangeService;
import nc.pub.sync.utils.SyncUtil;
import nc.pubitf.ic.m4d.api.IMaterialOutQueryAPI;
import nc.vo.ic.general.define.ICBillBodyVO;
import nc.vo.ic.general.define.ICBillVO;
import nc.vo.ic.general.util.ICLocationUtil;
import nc.vo.ic.location.ICLocationVO;
import nc.vo.ic.m4d.entity.MaterialOutBodyVO;
import nc.vo.ic.m4d.entity.MaterialOutVO;
import nc.vo.ic.m4y.entity.TransOutVO;
import nc.vo.ic.onhand.define.ICBillPickResults;
import nc.vo.ic.pub.util.CollectionUtils;
import nc.vo.ic.pub.util.ValueCheckUtil;
import nc.vo.ic.sncode.ICSnForLocationFields;
import nc.vo.ic.sncode.SnCodeForLocationVOSynchronizer;
import nc.vo.mmpac.pickm.entity.PickmHeadVO;
import nc.vo.mmpac.pickm.entity.PickmItemVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.VOStatus;
import nc.vo.to.m5x.entity.BillVO;
import net.sf.json.JSONObject;

public class PluginJunit_03 implements IBackgroundWorkPlugin{

	@Override
	public PreAlertObject executeTask(BgWorkingContext context) throws BusinessException {
		BillQuery<BillVO> query = new BillQuery<BillVO>(BillVO.class);
		BillVO[] bills = query.query(new String[]{"1001ZZ100000000QK1GR"});
		BillVO bill = bills[0];
		if(bill.getParentVO().getFstatusflag() != 4){
			throw new BusinessException("调拨订单【】未审批！请登录NC系统处理。");
		}
		TransOutVO outVO = (TransOutVO) NCLocator.getInstance().lookup(IPfExchangeService.class).runChangeData("5X", "4Y", bill, null);
		outVO.getParentVO().setStatus(VOStatus.NEW);
		for (CircularlyAccessibleValueObject bvo : outVO.getChildrenVO()) {
			bvo.setStatus(VOStatus.NEW);
		}
		IPFBusiAction service = (IPFBusiAction)NCLocator.getInstance().lookup(IPFBusiAction.class);
		service.processAction("WRITE", outVO.getParentVO().getCtrantypeid(), null, outVO, null, null);
		System.out.println(outVO == null);
		return null;
	}
	
}
