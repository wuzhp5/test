package nc.bs.config.params;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.cache.params.CacheVO;
import nc.vo.pub.BusinessException;

public class ParamsService {

	private Map<String, Object> map = new HashMap<String, Object>();
	private IUAPQueryBS bs = NCLocator.getInstance().lookup(IUAPQueryBS.class);
	private static ParamsService center = null;
	
	private ParamsService(){
		try {
			init();
		} catch (BusinessException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	public static ParamsService getInstance(){
		if(center == null){
			center = new ParamsService();
		}
		return center;
	}
	
	private void init() throws BusinessException{
		String sql =  "select pk_params,param,memo from xx_params";
		@SuppressWarnings("unchecked")
		List<CacheVO> vos = (List<CacheVO>) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(sql,
				new BeanListProcessor(CacheVO.class));
		for(CacheVO vo : vos){
			put(vo.getPk_params(), vo.getParam());
		}
	}
	
	public String getValue(String key) throws BusinessException {
		String sql = "select param from xx_params where pk_params = '"+key+"'";
		Object res = this.bs.executeQuery(sql, new ColumnProcessor());
		return res == null ? null : res.toString();
	}

	public void put(String key, Object value) {
		if(key == null || value == null){
			return;
		}
		this.map.put(key, value);
	}
}
