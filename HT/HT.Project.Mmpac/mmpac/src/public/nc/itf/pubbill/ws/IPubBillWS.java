package nc.itf.pubbill.ws;

public interface IPubBillWS {

	/**
	 * �ϴ���������
	 * 
	 * @param billType
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public String uploadPubBill(String billType, String content) throws Exception;
	

}
