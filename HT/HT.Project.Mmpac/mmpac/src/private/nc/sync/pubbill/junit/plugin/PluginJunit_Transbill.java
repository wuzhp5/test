package nc.sync.pubbill.junit.plugin;

import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.sync.pubbill.transbill.TransBillSyncService;
import nc.vo.pub.BusinessException;
import net.sf.json.JSONObject;

public class PluginJunit_Transbill implements IBackgroundWorkPlugin {

	@Override
	public PreAlertObject executeTask(BgWorkingContext context) throws BusinessException {
		JSONObject json = JSONObject.fromObject(JunitJSONUitl.getJSON4Transbill());
		try {
			new TransBillSyncService().service(json);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

}
