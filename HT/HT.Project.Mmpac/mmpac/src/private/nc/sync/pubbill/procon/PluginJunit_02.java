package nc.sync.pubbill.procon;

import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.sync.pubbill.materialout.MaterialOutBillSyncService;
import nc.vo.pub.BusinessException;
import net.sf.json.JSONObject;

public class PluginJunit_02 implements IBackgroundWorkPlugin{

	@Override
	public PreAlertObject executeTask(BgWorkingContext context) throws BusinessException {
		try{
			new MaterialOutBillSyncService().service(getMaterialOutJSON());
		}catch(BusinessException e){
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	private JSONObject getMaterialOutJSON(){
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"vplancode\": \"55B42017081800000981\",");
		sb.append("\"taskcode\": \"T0353434\",");
		sb.append("\"item\": [");
		sb.append(" {");
		sb.append("\"materialcode\": \"030407004\",");
		sb.append("\"outcount\": \"120\",");
		sb.append("\"unitcode\": \"bag\",");
		sb.append("\"outdt\": \"2017-8-1 12:12:11\",");
		sb.append("\"erpstaffcode\": \"U123\",");
		sb.append("\"placecode\": \"C221\"");
		sb.append(" }");
		sb.append(" ]");
		sb.append("}");
		return JSONObject.fromObject(sb.toString());
	}
	
	public static void main(String[] args) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		sb.append("\"vplancode\": \"55B42017081600000361\",");
		sb.append("\"taskcode\": \"T0353434\",");
		sb.append("\"item\": [");
		sb.append(" {");
		sb.append("\"materialcode\": \"030407004\",");
		sb.append("\"outcount\": \"120\",");
		sb.append("\"unitcode\": \"bag\",");
		sb.append("\"outdt\": \"2017-8-1 12:12:11\",");
		sb.append("\"erpstaffcode\": \"U123\",");
		sb.append("\"placecode\": \"C221\"");
		sb.append(" }");
		sb.append(" ]");
		sb.append("}");
		System.out.println(sb.toString());
	}
	
}
