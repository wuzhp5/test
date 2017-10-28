package nc.ui.uapbd.supcalendar.ace.serviceproxy;

import nc.bs.framework.common.NCLocator;
import nc.vo.uapbd.supcalendar.SupCalendarBaseVO;
import nc.itf.uapbd.ISupcalendarMaintain;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.ui.pubapp.uif2app.model.IQueryService;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.ui.uif2.model.IAppModelService;
import nc.vo.uif2.LoginContext;

/**
 * 示例单据的操作代理
 * 
 * @author author
 * @version tempProject version
 */
public class AceSupcalendarMaintainProxy implements IAppModelService, IQueryService {
	@Override
	public Object insert(Object object) throws Exception {
		ISupcalendarMaintain operator = NCLocator.getInstance().lookup(
				ISupcalendarMaintain.class);
		return operator.insert((SupCalendarBaseVO) object);
	}

	@Override
	public Object update(Object object) throws Exception {
		ISupcalendarMaintain operator = NCLocator.getInstance().lookup(
				ISupcalendarMaintain.class);
		return operator.update((SupCalendarBaseVO) object);
	}

	@Override
	public void delete(Object object) throws Exception {
		ISupcalendarMaintain operator = NCLocator.getInstance().lookup(
				ISupcalendarMaintain.class);
		operator.delete((SupCalendarBaseVO) object);
	}

	@Override
	public Object[] queryByWhereSql(String whereSql) throws Exception {
		ISupcalendarMaintain query = NCLocator.getInstance().lookup(
				ISupcalendarMaintain.class);
		return query.query(whereSql);
	}

	@Override
	public Object[] queryByDataVisibilitySetting(LoginContext context)
			throws Exception {
		return null;
	}
}
