package nc.ui.uapbd.supcalendar.ace.handler;

import nc.ui.pubapp.uif2app.event.IAppEventHandler;
import nc.ui.pubapp.uif2app.event.OrgChangedEvent;
import nc.ui.pubapp.uif2app.view.util.BillPanelUtils;
import nc.ui.uif2.editor.BillForm;
import nc.vo.uif2.LoginContext;

/**
 * <b> ��֯�л��¼� </b>
 * 
 * @author author
 * @version tempProject version
 */
@SuppressWarnings("restriction")
public class AceOrgChangeHandler implements IAppEventHandler<OrgChangedEvent> {

	private BillForm billForm;

	@Override
	public void handleAppEvent(OrgChangedEvent e) {
		LoginContext context = this.billForm.getModel().getContext();
		// ���в��չ���
		BillPanelUtils.setOrgForAllRef(this.billForm.getBillCardPanel(), context);
	}

	public BillForm getBillForm() {
		return billForm;
	}

	public void setBillForm(BillForm billForm) {
		this.billForm = billForm;
	}

}
