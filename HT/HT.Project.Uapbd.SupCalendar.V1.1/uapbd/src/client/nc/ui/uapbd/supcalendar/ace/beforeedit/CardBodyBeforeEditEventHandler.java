package nc.ui.uapbd.supcalendar.ace.beforeedit;

import java.util.Map;

import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardBodyBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.uif2.editor.BillForm;

@SuppressWarnings("restriction")
public class CardBodyBeforeEditEventHandler extends AbstractCardBodyBeforeEditEventHandler {

	private BillForm billForm;

	@Override
	public void registerEventListener(Map<String, ICardBodyBeforeEditEventListener> listenerMap) {

	}
	
	public void handleAppEvent(CardBodyBeforeEditEvent e) {
		super.handleAppEvent(e);
	}

	public BillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(BillForm billForm) {
		this.billForm = billForm;
	}

}
