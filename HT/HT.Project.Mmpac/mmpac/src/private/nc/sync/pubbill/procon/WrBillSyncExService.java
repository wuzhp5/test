package nc.sync.pubbill.procon;

import nc.itf.pubbill.proc.IProcessBillData;
import nc.vo.pub.BusinessException;
import net.sf.json.JSONObject;

public class WrBillSyncExService implements IProcessBillData {

	@Override
	public String ProccessData(String content) throws BusinessException {
		JSONObject json = JSONObject.fromObject(content);
		return new WrBillSyncService().service(json);
	}

}
