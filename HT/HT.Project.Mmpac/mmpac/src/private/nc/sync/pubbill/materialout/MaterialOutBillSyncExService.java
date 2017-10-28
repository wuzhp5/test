package nc.sync.pubbill.materialout;

import nc.itf.pubbill.proc.IProcessBillData;
import nc.vo.pub.BusinessException;
import net.sf.json.JSONObject;

public class MaterialOutBillSyncExService implements IProcessBillData {

	@Override
	public String ProccessData(String content) throws BusinessException {
		JSONObject json = JSONObject.fromObject(content);
		return new MaterialOutBillSyncService().service(json);
	}

}
