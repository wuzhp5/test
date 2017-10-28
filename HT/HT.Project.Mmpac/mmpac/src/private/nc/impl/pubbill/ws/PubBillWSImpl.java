package nc.impl.pubbill.ws;

import nc.bs.framework.common.InvocationInfoProxy;
import nc.bs.framework.common.NCLocator;
import nc.bs.pubbill.ws.WSRegisterCenter;
import nc.bs.sync.log.Logger;
import nc.itf.pubbill.proc.IProcessBillData;
import nc.itf.pubbill.ws.IPubBillWS;
import nc.itf.sync.log.ILogger;
import nc.pub.sync.utils.PropertiesUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.sync.log.LoggerVO;
import net.sf.json.JSONObject;

public class PubBillWSImpl implements IPubBillWS {

	/*
	 * (non-Javadoc)
	 * 
	 * @see nc.itf.pubbill.ws.IPubBillWS#uploadPubBill(java.lang.String,
	 * java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public String uploadPubBill(String billType, String content) throws Exception {
		String result = null;
		//指定系统默认数据源
		String datasource = PropertiesUtil.getInstance().getProperty().getProperty("datasource");
		InvocationInfoProxy.getInstance().setUserDataSource(datasource);
		String groupid = PropertiesUtil.getInstance().getProperty().getProperty("groupid");
		InvocationInfoProxy.getInstance().setGroupId(groupid);
		String userid = PropertiesUtil.getInstance().getProperty().getProperty("userid");
		InvocationInfoProxy.getInstance().setUserId(userid);
		
		try {
			String clazzName = WSRegisterCenter.getInstance().getRegisterInfo(billType);
			if (PluginsInstance.getInstance().hasInterface(clazzName)) {
				result = PluginsInstance.getInstance().getInterface(clazzName).ProccessData(content);
			} else {
				Class<IProcessBillData> proccess = (Class<IProcessBillData>) Class.forName(clazzName);
				IProcessBillData instance = proccess.newInstance();
				result = instance.ProccessData(content);
				PluginsInstance.getInstance().put(clazzName, instance);
			}
		} catch (Exception e) {
			e.printStackTrace();
			JSONObject json = new JSONObject();
			json.put("error", "异常原因：" + e.getMessage());
			result = json.toString();
		}
		try{
			insertLog4Sync(content, JSONObject.fromObject(result), billType);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	private void insertLog4Sync(String content, JSONObject json, String billType) throws BusinessException{
		LoggerVO vo = new LoggerVO();
		if(json.containsKey("error")){
			vo.setRes(json.getString("error"));
			vo.setBilltype(billType);
			vo.setIssuccess(UFBoolean.FALSE);
			vo.setContent(content);
		}else if(json.containsKey("successinfo")){
			vo.setRes(json.getString("successinfo"));
			vo.setBilltype(billType);
			vo.setIssuccess(UFBoolean.TRUE);
			vo.setContent(content);
			vo.setPk_bill(json.getString("pk_bill"));
			vo.setVbillcode(json.getString("vbillcode"));
		}
		Logger.downloadLog("单据类型【"+billType+"】", "MES上传JSON：【"+content+"】\r\n NC回执内容：【"+json.toString()+"】");
		NCLocator.getInstance().lookup(ILogger.class).log_RequiresNew(vo);
	}

}
