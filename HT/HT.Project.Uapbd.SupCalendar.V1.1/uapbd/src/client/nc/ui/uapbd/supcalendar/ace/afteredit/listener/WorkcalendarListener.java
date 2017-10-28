package nc.ui.uapbd.supcalendar.ace.afteredit.listener;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.uif2.editor.BillForm;
import nc.vo.pub.lang.UFDate;

@SuppressWarnings("restriction")
public class WorkcalendarListener implements ICardHeadTailAfterEditEventListener{

	private BillForm billForm;
	boolean flag = true;
	public WorkcalendarListener(BillForm billForm){
		this.billForm = billForm;
	}
	
	@Override
	public void afterEdit(CardHeadTailAfterEditEvent e) {
		BillCardPanel billCard = billForm.getBillCardPanel();
		Object pk_workcalendar = billCard.getHeadItem("pk_workcalendar").getValueObject();
		if (pk_workcalendar != null) {
			billForm.getBillCardPanel().getBillModel("supcalendar_d").clearBodyData();
			UFDate beginDate = (UFDate) billCard.getHeadItem("begindate").getValueObject();
			UFDate endDate = (UFDate) billCard.getHeadItem("enddate").getValueObject();
			int beginYear = beginDate.getYear();
			int endYear = endDate.getYear();
			int count = endYear - beginYear + 1;
			for(int index = 0; index < count; index++){
				billForm.getBillCardPanel().getBillModel("supcalendar_d").addLine();
				billCard.setBodyValueAt(""+beginYear++, index, "year", "supcalendar_d");
			}
			flag = false;
		}else if(pk_workcalendar == null && !flag){
			billForm.getBillCardPanel().getBillModel("supcalendar_d").clearBodyData();
			flag = true;
		}
	}
	
}
