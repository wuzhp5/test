package nc.pub.sync.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.lang.UFDouble;

public class SyncUtil {
	private static BaseDAO baseDAO = null;
	private static String URL = null;
	private static Boolean isCache =  false;

	public static BaseDAO getBaseDAO() {
		if (baseDAO == null) {
			baseDAO = new BaseDAO();
		}
		return baseDAO;
	}

	public static String getURL() throws BusinessException {
		if (URL == null) {
			String sql = "select param from xx_params where pk_params = 'PARAMS0000000000001'";
			@SuppressWarnings("unchecked")
			List<Map<String, String>> results = (List<Map<String, String>>) getBaseDAO().executeQuery(sql, new MapListProcessor());
			if (results.size() == 0) {
				throw new BusinessException("xx_params表中未有主键为PARAMS0000000000001的数据。" + "在表xx_params表中插入URL并设置主键为'PARAMS0000000000001'");
			}
			if(isCache){
				URL = results.get(0).get("param").trim();
			}else{
				return results.get(0).get("param").trim();
			}
		}
		return URL;
	}

	public static Map<String, Map<String, String>> getConstrs(String code) throws BusinessException {

		return null;
	}

	public static void initSameFileds(ISuperVO vo, String[] fields, Object value) {
		for (String field : fields) {
			vo.setAttributeValue(field, value);
		}
	}

	public static String packSuccessInfo(String msg) {
		return "{\"successinfo\":\"" + msg + "\"}";
	}
	
	public static String packSuccessInfo(String msg, String pk_bill, String vbillcode) {
		return "{\"successinfo\":\"" + msg + "\",\"pk_bill\":\"" + pk_bill + "\",\"vbillcode\":\"" + vbillcode + "\"}";
	}

	public static String packErrorInfo(String msg) {
		return "{\"error\":\"" + msg + "\"}";
	}
	
	public static UFDouble calNassitNum(String rate, UFDouble nnum) {
		String[] splits = rate.split("/");
		UFDouble div = new UFDouble(splits[0]).div(new UFDouble(splits[1]));
		return nnum.div(div, 0);
	}
	
	public static boolean isNullOrBlank(String obj) throws BusinessException{
		if(obj == null || "".equals(obj.trim())){
			return true;
		}
		return false;
	}
	
	public static boolean isNullOrZero(List<?> obj) throws BusinessException{
		if(obj == null || obj.size() == 0){
			return true;
		}
		return false;
	}

	public static boolean isNullOrZero(Set<?> obj) throws BusinessException{
		if(obj == null || obj.size() == 0){
			return true;
		}
		return false;
	}
}
