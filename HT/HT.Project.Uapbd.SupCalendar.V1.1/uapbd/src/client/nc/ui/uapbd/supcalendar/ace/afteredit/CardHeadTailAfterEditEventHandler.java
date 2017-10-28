package nc.ui.uapbd.supcalendar.ace.afteredit;

import java.util.Map;

import nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.uapbd.supcalendar.ace.afteredit.listener.ModelListener;
import nc.ui.uapbd.supcalendar.ace.afteredit.listener.WorkcalendarListener;
import nc.ui.uif2.editor.BillForm;

@SuppressWarnings("restriction")
public class CardHeadTailAfterEditEventHandler extends AbstractCardHeadTailAfterEditEventHandler {

	private BillForm billForm;

	@Override
	public void registerEventListener(Map<String, ICardHeadTailAfterEditEventListener> listenerMap) {
		listenerMap.put("pk_workcalendar", new WorkcalendarListener(billForm));
		listenerMap.put("pk_model", new ModelListener(billForm));
	}
	
	public BillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(BillForm billForm) {
		this.billForm = billForm;
	}

}
