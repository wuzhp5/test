package nc.ui.uapbd.supcalendar.ace.serviceproxy;

import nc.ui.pubapp.uif2app.model.BaseBillModelDataManager;
import nc.vo.pubapp.AppContext;

@SuppressWarnings("restriction")
public class SupcalendarModelDataManager extends BaseBillModelDataManager {

	@Override
	public void initModel() {
		//�Զ�������
		String pk_group = AppContext.getInstance().getPkGroup();
		String sqlwhere = " and pk_group = '" + pk_group + "' ";
		super.initModelBySqlWhere(sqlwhere);
		super.refresh();
	}

}
