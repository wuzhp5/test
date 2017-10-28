package nc.impl.pu.m20.api;

import nc.bcmanage.bs.BusiCenterCache;
import nc.bs.pu.m20.PushSave21ForM30Service;
import nc.pubitf.pu.m20.api.IPushSave21ForM30;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;

public class PushSave21ForM30Impl implements IPushSave21ForM30{

	private PushSave21ForM30Service service = new PushSave21ForM30Service();
	@Override
	public SaleOrderVO[] analyseOrder2SaleOrder(OrderVO[] orderVOs) throws BusinessException {
		return service.analyseOrder2SaleOrder(orderVOs);
	}
	
}
