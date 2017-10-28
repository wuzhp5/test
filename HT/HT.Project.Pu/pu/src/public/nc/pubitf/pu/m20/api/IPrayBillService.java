package nc.pubitf.pu.m20.api;

import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;

public interface IPrayBillService {

	public void adjustRequireDate(PraybillVO[] aggVO) throws BusinessException; 
	
}
