package nc.itf.uapbd.supcalendar;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.vo.pub.BusinessException;

public class LinkDataServiceImpl implements ILinkDataService {

	@Override
	public String querySourceByPk(String pk_group, String pk_org, String pk_material) throws BusinessException {
		StringBuffer sql = new StringBuffer();
		sql.append("");
		sql.append("");
		sql.append("");
		NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(null, null);
		return null;
	}

	@Override
	public String queryPurchaseorgVesion(String pk_group, String pk_purchaseorg) throws BusinessException {
		return null;
	}

}
