package nc.sync.pubbill.junit.plugin;

import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.sync.pubbill.procon.WrBillSyncService;
import nc.vo.pub.BusinessException;
import net.sf.json.JSONObject;

public class PluginJunit_Procon implements IBackgroundWorkPlugin {

	@Override
	public PreAlertObject executeTask(BgWorkingContext context) throws BusinessException {
		try{
			JSONObject json = JSONObject.fromObject(JunitJSONUitl.getJSON4Procon());
			new WrBillSyncService().service(json);
		}catch(BusinessException e){
			e.printStackTrace();
		}
		return null;
	}

}
