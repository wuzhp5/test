package nc.ui.uapbd.supcalendar.ace.beforeedit;

import java.util.Map;

import nc.ui.pu.pub.editor.card.handler.AbstractCardHeadTailBeforeEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.ICardHeadTailBeforeEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent;
import nc.ui.uif2.editor.BillForm;

@SuppressWarnings("restriction")
public class CardHeadTailBeforeEditEventHandler extends AbstractCardHeadTailBeforeEditEventHandler {

	private BillForm billForm;

	@Override
	public void registerEventListener(Map<String, ICardHeadTailBeforeEditEventListener> listenerMap) {
	}

	public void handleAppEvent(CardHeadTailBeforeEditEvent e) {
		super.handleAppEvent(e);
	}

	public BillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(BillForm billForm) {
		this.billForm = billForm;
	}

}
