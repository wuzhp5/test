package nc.itf.mes.ws.serivce;

import nc.vo.pub.BusinessException;

public interface IUploadBillWSService {

	/**
	 * 数据上传
	 * @param type NC-MES接口类型
	 * @param json 上传JSON数据
	 * @return 回执信息
	 * @throws BusinessException
	 */
	public String uploadBill(String type, String json) throws BusinessException; 
	
	/**
	 * VO转换JSON
	 * @param billType
	 * @param aggVO
	 * @return JSON StringType
	 * @throws BusinessException
	 */
	public String bill2Json(String billType, Object aggVO) throws BusinessException;
}
