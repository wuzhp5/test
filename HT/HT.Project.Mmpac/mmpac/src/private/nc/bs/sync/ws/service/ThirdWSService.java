package nc.bs.sync.ws.service;

import nc.itf.pubbill.proc.IProcessBillData;
import nc.vo.pub.BusinessException;

public class ThirdWSService implements IProcessBillData{

	@Override
	public String ProccessData(String content) throws BusinessException {
		return "�������͡�third��,�Ѿ����տͻ�����������ݣ��������ݡ�"+content+"��.";
	}

}
