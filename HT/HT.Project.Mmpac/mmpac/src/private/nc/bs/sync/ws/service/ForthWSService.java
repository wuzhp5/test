package nc.bs.sync.ws.service;

import nc.itf.pubbill.proc.IProcessBillData;
import nc.vo.pub.BusinessException;

public class ForthWSService implements IProcessBillData{

	@Override
	public String ProccessData(String content) throws BusinessException {
		return "�������͡�forth��,�Ѿ����տͻ�����������ݣ��������ݡ�"+content+"��.";
	}

}
