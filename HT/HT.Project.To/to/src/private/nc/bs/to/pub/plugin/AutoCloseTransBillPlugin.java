package nc.bs.to.pub.plugin;


import java.util.HashMap;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.impl.pubapp.env.BSContext;
import nc.impl.pubapp.pattern.data.view.ViewQuery;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.to.m5x.ITransOrderStateMaintain;
import nc.itf.uap.pf.IPFBusiAction;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.workflownote.WorkflownoteVO;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.to.m5x.entity.BillHeaderVO;
import nc.vo.to.m5x.entity.BillVO;
import nc.vo.to.m5x.entity.BillViewVO;


public class AutoCloseTransBillPlugin implements IBackgroundWorkPlugin{

	@Override
	public PreAlertObject executeTask(BgWorkingContext bgwc)
			throws BusinessException {

		String[] org = bgwc.getPk_orgs();
		String group = BSContext.getInstance().getGroupID();
		//1001ZZ100000000QJ3HZ : 车间备料调拨
		for(int i=0;i<org.length;i++){
			String sql ="select distinct(to_bill_b.cbill_bid)  from to_bill to_bill  left join to_bill_b to_bill_b on to_bill_b.cbillid = to_bill.cbillid  where to_bill.pk_org = '"+org[i] 
					+"'and to_bill.ctrantypeid='"+"1001ZZ100000000QJ3HZ"+"'and to_bill.fstatusflag <>'"+"7"
					+"'and to_bill.pk_group ='"+ group+"' and to_bill.dr=0 "+" and to_bill_b.dr=0";
			BillViewVO[] billViewVos = queryTransOrderForCloseBill(sql);
			for(int j = 0;j<billViewVos.length;j++){
				BillHeaderVO billheadvo = billViewVos[j].getHead();
				BillVO bill = billViewVos[j].changeToBill();
				if(billheadvo.getFstatusflag()==1){
					String actionName = "APPROVE"+billheadvo.getBillmaker();
					String billOrTranstype = billheadvo.getVtrantypecode();
					WorkflownoteVO workflowVo = null;
					AggregatedValueObject billvo = bill;
					Object userObj = null;
					HashMap<String,String> eParam = null;
					//跨模块调用
					IPFBusiAction pfb = NCLocator.getInstance().lookup(IPFBusiAction.class);
					Object obj =  pfb.processAction(actionName, billOrTranstype, workflowVo, billvo, userObj, eParam);
				}
			}
			BillViewVO[] billViewVOs = queryTransOrderForCloseBill(sql);
			ITransOrderStateMaintain impl = NCLocator.getInstance().lookup(ITransOrderStateMaintain.class);
			if(billViewVOs.length != 0){
				impl.closeTransOrderByView(billViewVOs);
			}
		}
		return null;
	}	

	private BillViewVO[] queryTransOrderForCloseBill(String sql) {
		DataAccessUtils utils = new DataAccessUtils();
		IRowSet set = utils.query(sql);
		if (set.size() == 0) {
			return new BillViewVO[0];
		}
		String[] ids = set.toOneDimensionStringArray();
		ViewQuery<BillViewVO> query = new ViewQuery<BillViewVO>(BillViewVO.class);
		BillViewVO[] views = query.query(ids);
		return views;
	}

}

