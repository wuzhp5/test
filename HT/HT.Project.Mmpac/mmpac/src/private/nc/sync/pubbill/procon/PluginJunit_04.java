package nc.sync.pubbill.procon;

import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.itf.mes.ws.serivce.IUploadBillWSService;
import nc.sync.pubbill.transbill.TransBillSyncService;
import nc.vo.pub.BusinessException;
import net.sf.json.JSONObject;

public class PluginJunit_04 implements IBackgroundWorkPlugin{

	@Override
	public PreAlertObject executeTask(BgWorkingContext context) throws BusinessException {
		try{
			//new TransBillSyncService().service(getJSON());
			IUploadBillWSService service = NCLocator.getInstance().lookup(IUploadBillWSService.class);
			for(int i = 0; i < 200; i++){
				service.uploadBill("farplan", getJSON().toString());
			}
		}catch(BusinessException e){
			e.fillInStackTrace();
		}
		return null;
	}
	
	private JSONObject getJSON(){
		StringBuffer sb = new StringBuffer();
		sb.append("{\"vplancode\":\"55B42017082000000988\",\"mesrequestcode\":\"MES0353434\",");
		sb.append("\"item\":");
		sb.append("[");
		sb.append("{\"taskcode\":\"T100\",\"materialcode\":\"1062320241\",\"needcount\":\"120\",\"unitcode\":\"bag\",\"requestdt\":\"2017-8-1 12:12:11\",\"erpstaffcode\":\"U123\",\"placecode\":\"606-00000001-02\"}");
		sb.append("]}");
		return JSONObject.fromObject(sb.toString());
	} 
	
}
