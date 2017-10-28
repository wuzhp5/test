package nc.pubitf.pu.m20.api;

import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;

public interface IPushSave21ForM30 {

	public SaleOrderVO[] analyseOrder2SaleOrder(OrderVO[] orderVOs) throws BusinessException;
			
}
