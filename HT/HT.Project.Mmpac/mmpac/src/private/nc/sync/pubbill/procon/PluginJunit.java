package nc.sync.pubbill.procon;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.pa.PreAlertObject;
import nc.bs.pub.taskcenter.BgWorkingContext;
import nc.bs.pub.taskcenter.IBackgroundWorkPlugin;
import nc.itf.mmpac.dmo.IDmoMaintainService;
import nc.itf.uap.pf.IPfExchangeService;
import nc.uap.oba.docx4j.model.datastorage.XPathEnhancerParser.main_return;
import nc.vo.mmpac.dmo.entity.AggDmoVO;
import nc.vo.mmpac.wr.entity.AggWrVO;
import nc.vo.mmpac.wr.entity.WrItemVO;
import nc.vo.mmpac.wr.entity.WrVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDouble;
import net.sf.json.JSONObject;

public class PluginJunit implements IBackgroundWorkPlugin{

	@Override
	public PreAlertObject executeTask(BgWorkingContext context) throws BusinessException {
		try{
			new WrBillSyncService().service(getJson());
		}catch(BusinessException e){
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		return null;
	}

	private AggWrVO queryAggWrVO() throws BusinessException{
		AggWrVO aggVO = new AggWrVO();
		BaseDAO baseDAO = new BaseDAO();
		WrVO headVO = (WrVO) baseDAO.retrieveByPK(WrVO.class, "1001ZZ100000000QE4O4");
		WrItemVO itemVO = (WrItemVO) baseDAO.retrieveByPK(WrItemVO.class, "1001ZZ100000000QE4O6");
		aggVO.setParent(headVO);
		aggVO.setChildrenVO(new WrItemVO[] { itemVO });
		return aggVO;
	}
	
	private static JSONObject getJson(){
		StringBuffer json = new StringBuffer();
		json.append("{\"vplancode\":\"55B42017081800001043\",\"taskcode\":\"T0353434\",\"pk_org\":\"1000001306\",\"cdeptid\":\"130601\",");
		json.append("\"item\":[");
		json.append("{\"productcode\":\"1061320100\",\"targetcount\":\"1200\",\"goodcount\":\"990\",\"reworkcount\":\"221\",\"scrapcount\":\"90\",\"realcount\":\"1211\",\"okdt\":\"2017-8-1 12:12:11\",\"erpstaffcode\":\"U123\",\"devicecode\":\"D12\"}");
		json.append("]}");
		return JSONObject.fromObject(json.toString());
	}
	public static void main(String[] args) {
		System.out.println(getJson().toString());
	}
	
}
