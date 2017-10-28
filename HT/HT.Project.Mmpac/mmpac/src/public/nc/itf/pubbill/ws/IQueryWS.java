package nc.itf.pubbill.ws;

public interface IQueryWS {

	/**
	 * @param queryType 查询类型
	 * @param json 查询参数的JSON格式
	 * @return
	 * @throws Exception
	 */
	public String queryNCData(String queryType, String json) throws Exception;
	
}
