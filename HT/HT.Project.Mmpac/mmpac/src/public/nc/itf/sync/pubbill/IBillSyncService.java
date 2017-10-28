package nc.itf.sync.pubbill;

import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import net.sf.json.JSONObject;

public interface IBillSyncService {
	
	public String service(JSONObject json) throws BusinessException;
	
	public void calculate(AbstractBill aggVO) throws BusinessException;
	
	public void handleFields(AbstractBill aggVO, Class<? extends ISuperVO> clazz) throws BusinessException; 
	
	public void handleHeadFields(ISuperVO headVO) throws BusinessException;
	
	public void handleBodyFields(ISuperVO[] bodyVOs, ISuperVO headVO) throws BusinessException;
	
}
