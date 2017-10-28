package nc.ui.uapbd.supcalendar.ace.afteredit;

import java.util.Map;

import nc.ui.pu.pub.editor.card.handler.AbstractCardBodyAfterEditEventHandler;
import nc.ui.pu.pub.editor.card.listener.AbstractRelationCalculateListener;
import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.uif2.editor.BillForm;

@SuppressWarnings("restriction")
public class CardBodyAfterEditEventHandler extends AbstractCardBodyAfterEditEventHandler{

	private BillForm billForm;
	@Override
	public AbstractRelationCalculateListener getCalculateListener() {
		return null;
	}

	@Override
	public void registerEventListener(Map<String, ICardBodyAfterEditEventListener> listenerMap) {
		
	}
	
	public void handleAppEvent(CardBodyAfterEditEvent e){
		super.handleAppEvent(e);
	}

	public BillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(BillForm billForm) {
		this.billForm = billForm;
	}

}
