package nc.bs.sync.ws.service;

import nc.itf.pubbill.proc.IProcessBillData;
import nc.vo.pub.BusinessException;

public class ForthWSService implements IProcessBillData{

	@Override
	public String ProccessData(String content) throws BusinessException {
		return "服务类型【forth】,已经接收客户端请求的数据！数据内容【"+content+"】.";
	}

}
