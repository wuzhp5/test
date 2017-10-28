package nc.itf.pubbill.proc;

import nc.vo.pub.BusinessException;

public interface IProcessBillData {
	public String ProccessData(String content) throws BusinessException;
}
