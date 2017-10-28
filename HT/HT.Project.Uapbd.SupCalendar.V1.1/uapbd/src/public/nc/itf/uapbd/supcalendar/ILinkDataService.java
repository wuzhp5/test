package nc.itf.uapbd.supcalendar;

import nc.vo.pub.BusinessException;

public interface ILinkDataService {

	public String querySourceByPk(String pk_group, String pk_org, String pk_material) throws BusinessException;

	public String queryPurchaseorgVesion(String pk_group, String pk_purchaseorg) throws BusinessException;
}
