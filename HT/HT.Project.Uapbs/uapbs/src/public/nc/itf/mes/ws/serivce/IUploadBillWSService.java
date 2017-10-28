package nc.itf.mes.ws.serivce;

import nc.vo.pub.BusinessException;

public interface IUploadBillWSService {

	/**
	 * �����ϴ�
	 * @param type NC-MES�ӿ�����
	 * @param json �ϴ�JSON����
	 * @return ��ִ��Ϣ
	 * @throws BusinessException
	 */
	public String uploadBill(String type, String json) throws BusinessException; 
	
	/**
	 * VOת��JSON
	 * @param billType
	 * @param aggVO
	 * @return JSON StringType
	 * @throws BusinessException
	 */
	public String bill2Json(String billType, Object aggVO) throws BusinessException;
}
