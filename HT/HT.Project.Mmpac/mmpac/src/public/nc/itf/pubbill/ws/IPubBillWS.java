package nc.itf.pubbill.ws;

public interface IPubBillWS {

	/**
	 * 上传单据数据
	 * 
	 * @param billType
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public String uploadPubBill(String billType, String content) throws Exception;
	

}
