package nc.ui.uapbd.supcalendar.ace.afteredit.listener;

import nc.ui.pu.pub.editor.card.listener.ICardBodyAfterEditEventListener;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent;
import nc.ui.uif2.editor.BillForm;

@SuppressWarnings("restriction")
public class MaterialListener implements ICardBodyAfterEditEventListener{

	private BillForm billForm;
	public MaterialListener(BillForm billForm){
		this.billForm = billForm;
	}
	@Override
	public void afterEdit(CardBodyAfterEditEvent event) {
		
	}

}
