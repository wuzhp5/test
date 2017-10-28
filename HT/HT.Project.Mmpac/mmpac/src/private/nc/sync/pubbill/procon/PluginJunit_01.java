package nc.sync.pubbill.procon;

import nc.bs.dao.BaseDAO;
import nc.bs.logging.Logger;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.impl.pubapp.env.BSContext;
import nc.vo.mmpps.mps0202.PoVO;
import nc.vo.pub.BusinessException;
import net.sf.json.JSONObject;

public class PluginJunit_01 implements IBackgroundWorkPlugin{

	@Override
	public PreAlertObject executeTask(BgWorkingContext context) throws BusinessException {
		try{
			String sql = "insert into xx_sync_log (pk_bill,vbillcode,mescode,billtype,issuccess,content) values('1001Z91000000000LL1W','55B42017082000000985','T0353434','5X','Y','"+getJson()+"')";
			new BaseDAO().executeUpdate(sql);
			Logger.debug(BSContext.getInstance().getDate()+"³É¹¦£¡");
		}catch(BusinessException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		System.out.println(new PoVO().getAttributeNames());
		return null;
	}

	private JSONObject getJson(){
		StringBuffer json = new StringBuffer();
		json.append("{\"vplancode\":\"55B42017082000000991\",\"taskcode\":\"T0353434\",\"pk_org\":\"1000001306\",\"cdeptid\":\"130601\",");
		json.append("\"item\":[");
		json.append("{\"productcode\":\"1061320100\",\"targetcount\":\"1200\",\"goodcount\":\"990\",\"reworkcount\":\"221\",\"scrapcount\":\"90\",\"realcount\":\"1211\",\"okdt\":\"2017-8-1 12:12:11\",\"erpstaffcode\":\"U123\",\"devicecode\":\"D12\"}");
		json.append("]}");
		return JSONObject.fromObject(json.toString());
	}
	
}
