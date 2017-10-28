package nc.bs.constrast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.sync.constrast.ConstrastVO;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ConstrastService {

	private IUAPQueryBS bs = NCLocator.getInstance().lookup(IUAPQueryBS.class);
	private static final String ITEM = "item";
	private static final String HEAD = "head";
	private Map<String, Map<String, List<ConstrastVO>>> notNullCache = new HashMap<String, Map<String, List<ConstrastVO>>>();
	private Map<String, Map<String, List<ConstrastVO>>> allCache = new HashMap<String, Map<String, List<ConstrastVO>>>();
	private Map<String, List<ConstrastVO>> cache = new HashMap<String, List<ConstrastVO>>();

	
	/**
	 * @param billType
	 * @param json
	 * @param aggVO
	 * @param item
	 * @throws BusinessException
	 */
	public void executeConstrService(String billType, JSONObject json, AbstractBill aggVO, Class<? extends ISuperVO> item) throws BusinessException{
		checkNotNull(json, getNotNullFields(billType, false));
		Map<String, List<ConstrastVO>> constrVOs = queryAllFields(billType, false);
		json.getJSONArray(ITEM).size();
		initAggVO(aggVO, aggVO.getParent(), item, json.getJSONArray(ITEM).size());
		doConstrast(json, aggVO, constrVOs, item);
	}
	
	/**
	 * 初始化AggVO
	 * @param aggVO
	 * @param head
	 * @param item
	 * @param length
	 * @return
	 */
	public void initAggVO(AbstractBill aggVO, ISuperVO head, Class<? extends ISuperVO> item, int length) {
		try{
			aggVO.setParent(head);
			ISuperVO[] itemVOs = (ISuperVO[]) Array.newInstance(item, length);
			for(int index = 0; index < itemVOs.length; index++){
				itemVOs[index] = item.newInstance();
			}
			aggVO.setChildren(item, itemVOs);
		}catch(Exception e){
			throw new RuntimeException("初始化AggVO时出错。异常信息："+e.getMessage());
		}
	}
	
	/**
	 * 对照
	 * @param json
	 * @param aggVO
	 * @param constrVOs
	 * @param itemVO
	 */
	public void doConstrast(JSONObject json, AbstractBill aggVO, Map<String, List<ConstrastVO>> constrVOs, Class<? extends ISuperVO> itemVO){
		ISuperVO head = aggVO.getParent();
		List<ConstrastVO> headConstrs = constrVOs.get(HEAD);
		for(ConstrastVO constr : headConstrs){
			head.setAttributeValue(constr.getField(), json.getString(constr.getExfield()));
		}
		ISuperVO[] items = aggVO.getChildren(itemVO);
		JSONArray jar = json.getJSONArray(ITEM);
		List<ConstrastVO> itemConstrs = constrVOs.get(ITEM);
		for(int index = 0; index < items.length; index++){
			for(ConstrastVO constr : itemConstrs){
				items[index].setAttributeValue(constr.getField(), jar.getJSONObject(index).getString(constr.getExfield()));
			}
		}
	}
	
	/**
	 * 按单据类型查询对照表
	 * @param billType
	 * @return
	 * @throws BusinessException
	 */
	@SuppressWarnings("unchecked")
	private List<ConstrastVO> queryConstrastVOs(String billType) throws BusinessException{
		StringBuffer sql = new StringBuffer().append("select ");
		sql.append(ConstrastVO.getColumns4Sql());
		sql.append(" from "+ConstrastVO.getDefaultTable());
		sql.append(" where billtype = '"+billType+"' and field <> '~' and exfield <> '~'");
		return (List<ConstrastVO>) bs.executeQuery(sql.toString(), new BeanListProcessor(ConstrastVO.class));
	}
	
	/**
	 * @param billType
	 * @param isCache
	 * @return
	 * @throws BusinessException
	 */
	public List<ConstrastVO> queryConstrastVOs(String billType, Boolean isCache) throws BusinessException{
		if(isCache){
			if(this.allCache.containsKey(billType)){
				return this.cache.get(billType);
			}else{
				List<ConstrastVO> res = queryConstrastVOs(billType);
				this.cache.put(billType, res);
				return res;
			}
		}
		return queryConstrastVOs(billType);
	}
	
	/**
	 * 查询所有的对照字段信息
	 * @param billType
	 * @param isCache
	 * @return
	 * @throws BusinessException
	 */
	public Map<String, List<ConstrastVO>> queryAllFields(String billType, Boolean isCache) throws BusinessException{
		List<ConstrastVO> constrs = queryConstrastVOs(billType, isCache);
		if(isCache){
			if(this.allCache.containsKey(billType)){
				return this.allCache.get(billType);
			}else{
				Map<String, List<ConstrastVO>> res = groupAllFields(constrs);
				this.allCache.put(billType, groupAllFields(constrs));
				return res;
			}
		}
		return groupAllFields(constrs);
	}
	
	/**
	 * @param vos
	 * @return
	 */
	private Map<String, List<ConstrastVO>> groupAllFields(List<ConstrastVO> vos){
		List<ConstrastVO> head = new ArrayList<ConstrastVO>();
		List<ConstrastVO> item = new ArrayList<ConstrastVO>();
		for(ConstrastVO vo : vos){
			if(HEAD.equals(vo.getWherepart())){
				head.add(vo);
			}else if(ITEM.equals(vo.getWherepart())){
				item.add(vo);
			}
		}
		Map<String, List<ConstrastVO>> res = new HashMap<String, List<ConstrastVO>>();
		res.put(HEAD, head);
		res.put(ITEM, item);
		return res;
	}
	
	/**
	 * 获取不能为空的字段信息
	 * @param vos
	 * @return
	 */
	public Map<String, List<ConstrastVO>> getNotNullFields(List<ConstrastVO> vos){
		List<ConstrastVO> head = new ArrayList<ConstrastVO>();
		List<ConstrastVO> item = new ArrayList<ConstrastVO>();
		for(ConstrastVO vo : vos){
			if(vo.getIsnull().equalsIgnoreCase("N")){
				if(HEAD.equals(vo.getWherepart())){
					head.add(vo);
				}else if(ITEM.equals(vo.getWherepart())){
					item.add(vo);
				}
			}
		}
		Map<String, List<ConstrastVO>> res = new HashMap<String, List<ConstrastVO>>();
		res.put(HEAD, head);
		res.put(ITEM, item);
		return res;
	}
	
	/**
	 * 获取上传数据的不可为空字段
	 * @param billType 单据类型
	 * @param isnotNullCache 是否缓存
	 * @return
	 * @throws BusinessException
	 */
	public Map<String, List<ConstrastVO>> getNotNullFields(String billType, Boolean isnotNullCache) throws BusinessException{
		if(isnotNullCache){
			if(this.notNullCache.containsKey(billType)){
				return this.notNullCache.get(billType);
			}else{
				Map<String, List<ConstrastVO>> res = getNotNullFields(queryConstrastVOs(billType));
				this.notNullCache.put(billType, res);
				return res;
			}
		}
		return getNotNullFields(queryConstrastVOs(billType));
	}
	
	/**
	 * 校验上传数据的不可为空字段
	 * @param json 
	 * @param constrs
	 * @param where
	 * @return
	 * @throws BusinessException
	 */
	public List<String> checkNotNull(JSONObject json, List<ConstrastVO> constrs, String where) throws BusinessException{
		List<String> res = new ArrayList<String>();
		for(ConstrastVO constr : constrs){
			if(json.containsKey(constr.getExfield())){
				String content = json.getString(constr.getExfield());
				if(content == null || content.trim().equals("")){
					res.add("【"+constr.getExsysdes()+"】不能为空");
				}
			}else{
				res.add("字段【"+constr.getExsysdes()+"】不存在");
			}
		}
		return res;
	}
	
	/**
	 * 校验上传数据的不可为空字段
	 * @param json JSON
	 * @param constrs 所有的对照信息
	 * @throws BusinessException
	 */
	public void checkNotNull(JSONObject json, Map<String, List<ConstrastVO>> constrs) throws BusinessException{
		if(!json.containsKey(ITEM)){
			throw new BusinessException("明细内容不能为空");
		}
		StringBuffer res = new StringBuffer();
		List<String> headres = checkNotNull(json, constrs.get(HEAD), HEAD);
		if(headres.size() > 0){
			res.append(headres.toString());
		}
		JSONArray arr = json.getJSONArray(ITEM);
		int size = arr.size();
		for(int index = 0; index < size; index++){
			List<String> itemres = checkNotNull(arr.getJSONObject(index), constrs.get(ITEM), ITEM);
			if(itemres.size() > 0){
				res.append(itemres.toString());
			}
		}
		if(res.length() > 0){
			throw new BusinessException(res.toString());
		}
	}
	
}
