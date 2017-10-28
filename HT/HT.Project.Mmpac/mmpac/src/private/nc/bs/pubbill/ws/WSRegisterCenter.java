package nc.bs.pubbill.ws;

import java.util.HashMap;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.pub.BusinessException;

/**
 * @author ��־ƽ
 * @creationtime 2017��6��8��
 */
public class WSRegisterCenter {

	private Map<String, String> reginfo = new HashMap<String, String>();
	private BaseDAO baseDAO = null;
	private static WSRegisterCenter center = null;

	private WSRegisterCenter() {
	}

	/**
	 * ʵ�ֵ�����
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
	 * ��ȡ��Ӧ�������͵Ĳ��ȫ����
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
				throw new BusinessException("��WebService�ӿ�δע�᡾" + billType + "������");
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
