package nc.pubitf.so.m30.api;

import nc.itf.annotation.Component;
import nc.itf.annotation.OpenAPI;
import nc.itf.annotation.OpenLevel;
import nc.vo.pub.BusinessException;
import nc.vo.so.m30.entity.SaleOrderVO;

@Component("œ˙ €∂©µ•")
@OpenAPI(OpenLevel.OPENED)
public abstract interface ISaleOrderMaintain4Push
{
  public abstract SaleOrderVO[] insertBills(SaleOrderVO[] paramArrayOfSaleOrderVO)
    throws BusinessException;

  public abstract void deleteBillsByID(String[] paramArrayOfString)
    throws BusinessException;
}
