package nc.impl.pubbill.ws;

import nc.bs.pubbill.ws.WSRegisterCenter;
import nc.itf.pubbill.proc.IProcessBillData;
import nc.itf.pubbill.ws.IQueryWS;
import net.sf.json.JSONObject;

public class QueryWSImpl implements IQueryWS {

	@Override
	public String queryNCData(String queryType, String json) throws Exception {
		String result = null;
		try {
			String clazzName = WSRegisterCenter.getInstance().getRegisterInfo(queryType);
			if (PluginsInstance.getInstance().hasInterface(clazzName)) {
				result = PluginsInstance.getInstance().getInterface(clazzName).ProccessData(json);
			} else {
				@SuppressWarnings("unchecked")
				Class<IProcessBillData> proccess = (Class<IProcessBillData>) Class.forName(clazzName);
				IProcessBillData instance = proccess.newInstance();
				result = instance.ProccessData(json);
				PluginsInstance.getInstance().put(clazzName, instance);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject res = new JSONObject();
			res.put("error", "“Ï≥£‘≠“Ú£∫" + e.getMessage());
			result = res.toString();
		}
		return result;
	}

}
