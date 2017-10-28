package nc.sync.pubbill.junit.plugin;

public class JunitJSONUitl {

	public static String getJSON4MaterialOut(){
		//55B42017090900004119
		String json = " {" +
				"\"vplancode\": \"55B42017090900004119\"," +
				"\"mesrequestcode\":\"111222\"," +
				"\"item\": [" +
				"{" +
				"\"taskcode\": \"T0353434\"," +
				"\"materialcode\": \"2091470008\"," +
				"\"needcount\": \"1\"," +
				"\"unitcode\": \"bag\"," +
				"\"requestdt\": \"2017-8-1 12:12:11\"," +
				"\"erpstaffcode\": \"zaix\"," +
				"\"tocdeptid\": \"1000001306\"," +
				"\"toplacecode\": \"606-00000003-03\"," +
				"\"fromcdeptid\": \"1000001306\"," +
				"\"fromplacecode\": \"606-00000001-01\"" +
				"}]" +
				"}";
		return json;
	}
	
	public static String getJSON4Procon(){
		String json = "{\"cdeptid\":\"130601\", " +
				"\"taskcode\":\"T0353434\"," +
				"\"vplancode\":\"55B42017090900004119\"," +
				"\"item\":[" +
				"{" +
				"\"productcode\":\"1062320101\"," +
				"\"targetcount\":\"1200\"," +
				"\"goodcount\":\"2\"," +
				"\"reworkcount\":\"221\"," +
				"\"scrapcount\":\"90\"," +
				"\"realcount\":\"2\"," +
				"\"okdt\":\"2017-8-1 12:12:11\"," +
				"\"erpstaffcode\":\"U123\"," +
				"\"devicecode\":\"D12\"," +
				" \"toplacecode\":\"0\"" +
				"}" +
				"]" +
				"}";
		return json;
	}
	
	public static String getJSON4Transbill(){
		String json = " {" +
				"\"vplancode\": \"55B42017081300000301\"," +
				"\"mesrequestcode\":\"1121222\"," +
				"\"item\": [" +
				"{" +
				"\"taskcode\": \"T0353434\"," +
				"\"materialcode\": \"2091470008\"," +
				"\"needcount\": \"1\"," +
				"\"unitcode\": \"bag\"," +
				"\"requestdt\": \"2017-8-1 12:12:11\"," +
				"\"erpstaffcode\": \"zaix\"," +
				"\"tocdeptid\": \"1000001306\"," +
				"\"toplacecode\": \"606-00000001-02\"," +
				"\"fromcdeptid\": \"1000001306\"," +
				"\"fromplacecode\": \"606-00000001-01\"" +
				"}]" +
				"}";
		return json;
	}

}
