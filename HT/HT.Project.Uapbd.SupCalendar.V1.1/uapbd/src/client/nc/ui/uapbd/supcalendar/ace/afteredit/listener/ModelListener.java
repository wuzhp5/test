package nc.ui.uapbd.supcalendar.ace.afteredit.listener;

import nc.ui.pu.pub.editor.card.listener.ICardHeadTailAfterEditEventListener;
import nc.ui.pub.bill.BillCardPanel;
import nc.ui.pub.bill.BillModel;
import nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent;
import nc.ui.uif2.editor.BillForm;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.uapbd.supcalendar.SupCalendarBaseVO;

@SuppressWarnings("restriction")
public class ModelListener implements ICardHeadTailAfterEditEventListener{

	private BillForm billForm;
	public ModelListener(BillForm billForm){
		this.billForm = billForm;
	}
	@Override
	public void afterEdit(CardHeadTailAfterEditEvent e) {
		BillCardPanel billCard = billForm.getBillCardPanel();
		if("1".equals(billCard.getHeadItem("pk_model").getValueObject())){
			billCard.getHeadItem("sunday").setEdit(true);
			billCard.getHeadItem("monday").setEdit(true);
			billCard.getHeadItem("tuesday").setEdit(true);
			billCard.getHeadItem("wednesday").setEdit(true);
			billCard.getHeadItem("thursday").setEdit(true);
			billCard.getHeadItem("friday").setEdit(true);
			billCard.getHeadItem("saturday").setEdit(true);
			
		}else{
			billCard.getHeadItem("sunday").setEdit(false);
			billCard.getHeadItem("monday").setEdit(false);
			billCard.getHeadItem("tuesday").setEdit(false);
			billCard.getHeadItem("wednesday").setEdit(false);
			billCard.getHeadItem("thursday").setEdit(false);
			billCard.getHeadItem("friday").setEdit(false);
			billCard.getHeadItem("saturday").setEdit(false);
			billCard.getHeadItem("sunday").setValue(UFBoolean.FALSE);
			billCard.getHeadItem("monday").setValue(UFBoolean.FALSE);
			billCard.getHeadItem("tuesday").setValue(UFBoolean.FALSE);
			billCard.getHeadItem("wednesday").setValue(UFBoolean.FALSE);
			billCard.getHeadItem("thursday").setValue(UFBoolean.FALSE);
			billCard.getHeadItem("friday").setValue(UFBoolean.FALSE);
			billCard.getHeadItem("saturday").setValue(UFBoolean.FALSE);
		}
		SupCalendarBaseVO baseVO = (SupCalendarBaseVO) billForm.getModel().getSelectedData();
		String model = baseVO.getPk_model();
		BillModel dModel = billCard.getBillModel("supcalendar_d");
		if(!model.equals(billCard.getHeadItem("pk_model").getValueObject())){
			for(int index = 0; index < dModel.getRowCount(); index++){
				dModel.setValueAt(UFBoolean.TRUE, index, "def1");
				dModel.setRowState(index, BillModel.MODIFICATION);
			}
			dModel.getItemByKey("def1").setEdit(false);
		}else{
			dModel.getItemByKey("def1").setEdit(true);
			for(int index = 0; index < dModel.getRowCount(); index++){
				dModel.setValueAt(UFBoolean.FALSE, index, "def1");
			}
		}
	}
	
}
