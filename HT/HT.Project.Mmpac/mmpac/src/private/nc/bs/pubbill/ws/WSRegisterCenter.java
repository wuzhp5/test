package nc.bs.pubbill.ws;

import java.util.HashMap;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.pub.BusinessException;

/**
 * @author 吴志平
 * @creationtime 2017年6月8日
 */
public class WSRegisterCenter {

	private Map<String, String> reginfo = new HashMap<String, String>();
	private BaseDAO baseDAO = null;
	private static WSRegisterCenter center = null;

	private WSRegisterCenter() {
	}

	/**
	 * 实现单例化
	 * 
	 * @return
	 */
	public static WSRegisterCenter getInstance() {
		if (center == null) {
			center = new WSRegisterCenter();
		}
		return center;
	}

	/**
	 * 获取对应单据类型的插件全名称
	 * 
	 * @param billType
	 * @return
	 * @throws BusinessException
	 */
	public String getRegisterInfo(String billType) throws BusinessException {
		//
		if (this.reginfo.containsKey(billType)) {
			return this.reginfo.get(billType);
		} else {
			String sql = "select fullclassname from xx_wsregister where billtype = '" + billType + "'";
			String fullName = (String) getBaseDAO().executeQuery(sql, new ColumnProcessor());
			if (fullName == null) {
				throw new BusinessException("该WebService接口未注册【" + billType + "】服务");
			}
			return fullName;
		}
	}

	private BaseDAO getBaseDAO() {
		if (this.baseDAO == null) {
			this.baseDAO = new BaseDAO();
		}
		return this.baseDAO;
	}
}
