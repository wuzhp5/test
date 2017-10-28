package nc.ui.uapbd.supcalendar.ace.handler;

import nc.ui.pub.bill.BillEditEvent;
import nc.ui.pub.bill.BillEditListener;
import nc.ui.pub.bill.BillEditListener2;
import nc.ui.pub.bill.BillItemHyperlinkEvent;
import nc.ui.pub.bill.BillItemHyperlinkListener;
import nc.ui.uif2.components.IAutoShowUpComponent;
import nc.ui.uif2.components.IAutoShowUpEventListener;
import nc.ui.uif2.components.ITabbedPaneAwareComponent;
import nc.ui.uif2.components.ITabbedPaneAwareComponentListener;

public class SupCalendarEditor extends nc.ui.uif2.editor.BillForm implements IAutoShowUpComponent, ITabbedPaneAwareComponent, BillEditListener,
		BillEditListener2, BillItemHyperlinkListener {

	private static final long serialVersionUID = -4814076550270688271L;

	protected void onEdit() {
		super.onEdit();
	}

	@Override
	public void hyperlink(BillItemHyperlinkEvent arg0) {
		
	}

	@Override
	public boolean beforeEdit(BillEditEvent arg0) {
		return false;
	}

	@Override
	public void addTabbedPaneAwareComponentListener(ITabbedPaneAwareComponentListener arg0) {
		
	}

	@Override
	public boolean canBeHidden() {
		return false;
	}

	@Override
	public boolean isComponentVisible() {
		return false;
	}

	@Override
	public void setComponentVisible(boolean arg0) {
		
	}

	@Override
	public void setAutoShowUpEventListener(IAutoShowUpEventListener arg0) {
		
	}

	@Override
	public void showMeUp() {
		
	}

}
