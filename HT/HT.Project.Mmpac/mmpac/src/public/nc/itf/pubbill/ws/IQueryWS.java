package nc.itf.pubbill.ws;

public interface IQueryWS {

	/**
	 * @param queryType ��ѯ����
	 * @param json ��ѯ������JSON��ʽ
	 * @return
	 * @throws Exception
	 */
	public String queryNCData(String queryType, String json) throws Exception;
	
}
