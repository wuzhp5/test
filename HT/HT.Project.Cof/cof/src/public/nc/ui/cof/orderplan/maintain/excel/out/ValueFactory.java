package nc.ui.cof.orderplan.maintain.excel.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import nc.md.model.impl.MDEnum;
import nc.md.model.type.IType;
import nc.ui.cof.orderplan.maintain.excel.common.PrecisionHandler;
import nc.vo.cof.orderplan.entity.AggOrderplanVO;
import nc.vo.cof.orderplan.entity.OrderplanBTPVO;
import nc.vo.cof.orderplan.entity.OrderplanBVO;
import nc.vo.cof.orderplan.entity.OrderplanVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;

/**
 * 根据要货计划aggvo得到主表，子表，孙表的excel输出值
 * 
 * @since 6.0
 * @version 2013-11-13 下午03:34:46
 * @author ruhuic
 */
public class ValueFactory {
	/**
	 * 要货计划aggvo
	 */
	private AggOrderplanVO aggvo;

	/**
	 * 输出的主表属性key
	 */
	private List<String> parentitems;

	/**
	 * 输出的子表属性key
	 */
	private List<String> childitemkeys;

	/**
	 * 输出的孙表属性key
	 */
	private List<String> grandsonitemkeys;

	/**
	 * 孙表主键Map <key,value>=<子表主键key,孙表主键List(按计划开始时间升序排列)>
	 */
	private Map<String, List<String>> grandprimarykeys = new HashMap<String, List<String>>();

	/**
	 * 子表值顺序Map,<key,value>=<顺序值 ,子表主键>
	 */
	private Map<UFDouble, String> rownoMap = new HashMap<UFDouble, String>();

	/**
	 * 孙表值Map <key,value>=<子表主键,孙表值list<按计划开始时间升序排列>>
	 */
	private HashMap<String, List<List<Object>>> grandsonValue;

	/**
	 * 升序排列的行号
	 */
	private UFDouble[] orderedRowno;

	/**
	 * 导出的子表值
	 */
	private HashMap<Integer, List<Object>> childvalue;

	public ValueFactory(AggOrderplanVO vo) {
		this.aggvo = vo;
		// 处理精度
		PrecisionHandler handler = new PrecisionHandler();
		handler.handlePrecision(aggvo);
	}

	public void setParentItems(List<String> items) {
		this.parentitems = items;
	}

	public void setChildItems(List<String> items) {
		this.childitemkeys = items;
	}

	public void setGrandsonItems(List<String> items) {
		this.grandsonitemkeys = items;
	}

	/**
	 * 要货计划头信息的值
	 */
	public List<Object> getparentValues() {
		if (aggvo == null)
			return null;
		List<Object> valueList = new ArrayList<Object>();
		OrderplanVO orderplanvo = aggvo.getParentVO();
		List<String> itemkeys = this.parentitems;
		for (String headitem : itemkeys) {
			String attribute = headitem;
			Object attributevalue = orderplanvo.getAttributeValue(attribute);
			int modelType = orderplanvo.getMetaData().getAttribute(attribute).getModelType();

			if (modelType == IType.REF) {
				String[] attributevalues = com.yonyou.ec.commons.utils.VOCodeNameUtils.getBeanNames(orderplanvo, attribute,
						new String[] { (String) attributevalue });
				if (attributevalues != null && attributevalues.length > 0) {
					attributevalue = attributevalues[0];
				}
			}
			if (modelType == IType.ENUM) {
				MDEnum md = MDEnum.valueOf(orderplanvo.getMetaData().getAttribute(attribute).getEnumClass(), attributevalue);
				attributevalue = md.getName();
			}
			if (modelType == IType.TYPE_UFDate_BEGIN) {
				attributevalue = ((UFDate) attributevalue).toStdString();
			}
			valueList.add(attributevalue);
		}
		Object value = orderplanvo.getCorderplanid();
		valueList.add(0, value);
		return valueList;
	}

	/**
	 * 填充的子表值
	 * 
	 * @return
	 */
	public HashMap<Integer, List<Object>> getChildValue() {
		if (this.childvalue != null) {
			return this.childvalue;
		}
		if (aggvo == null)
			return null;
		// 得到子表的导出项的值
		HashMap<String, List<Object>> childvalue = getBodyDimention();
		HashMap<Integer, List<Object>> result = new HashMap<Integer, List<Object>>();
		if (childvalue == null || childvalue.size() == 0) {
			return null;
		}
		// 在导出项的第一个位置上添加（子表主键+孙表主键值)元素
		for (int i = 0; i < this.orderedRowno.length; i++) {
			String key = this.rownoMap.get(orderedRowno[i]);
			List<Object> value = childvalue.get(key);
			// 根据子表主键得到子表主键和孙表主键值
			// 注意：存储主表子表主键是为了导入更新时用
			String keys = getChildGrandKeys(key);
			value.add(0, keys);
			result.put(i, value);
		}
		this.childvalue = result;
		return result;

	}

	/**
	 * 得到要货计划子表的值
	 */
	private HashMap<String, List<Object>> getBodyDimention() {
		if (aggvo == null)
			return null;
		// 初始化孙表值
		this.initGrandsonValue();
		OrderplanBVO[] orderpalnBvos = (OrderplanBVO[]) aggvo.getChildren(OrderplanBVO.class);
		// 导出的子表项
		List<String> itemkeys = this.childitemkeys;
		// 行号-子表主键map
		Map<UFDouble, String> irownoMap = new HashMap<UFDouble, String>();
		UFDouble replaceno = new UFDouble(-10);
		// 子表值map:key=子表主键，value=输出的子表值
		HashMap<String, List<Object>> childvalueMap = new HashMap<String, List<Object>>();
		if (orderpalnBvos == null || orderpalnBvos.length <= 0) {
			return childvalueMap;
		}
		// 子表中导出项为参照的<itemkey,参照id数组>
		Map<String, List<String>> itemValues = getAllRefvalues(orderpalnBvos);
		// 根据参照id得到参照的name
		Map<String, Object[]> itemref_idMap = getAllRefidValues(itemValues);
		for (int i = 0; i < orderpalnBvos.length; i++) {
			// 得到导出项的值
			List<Object> valueList = new ArrayList<Object>();
			for (String itemkey : itemkeys) {
				if("islay".equals(itemkey)){
					continue;
				}
				String attribute = itemkey;
				Object attributevalue = orderpalnBvos[i].getAttributeValue(attribute);
				int modelType = orderpalnBvos[i].getMetaData().getAttribute(attribute).getModelType();
				// 类型为参照时，输出参照名字
				if (modelType == IType.REF && attributevalue != null) {
					// 先根据参照map得到名字
					if (itemref_idMap != null && itemref_idMap.containsKey(itemkey)) {
						attributevalue = itemref_idMap.get(itemkey)[i];
					}
					// 如果参照map不存在，调用其他方法查询
					else {
						String[] attributevalues = com.yonyou.ec.commons.utils.VOCodeNameUtils.getBeanNames(orderpalnBvos[i], attribute,
								new String[] { (String) attributevalue });
						if (attributevalues != null && attributevalues.length > 0) {
							attributevalue = attributevalues[0];
						}
					}
				}
				valueList.add(attributevalue);
			}
			childvalueMap.put(orderpalnBvos[i].getCorderplanbid(), valueList);
			// 子表行号值
			String rowno = orderpalnBvos[i].getCrowno();
			// 子表行号不存在时，则用负的行号代替，防止此行丢失
			if (rowno == null || rowno.length() <= 0) {
				UFDouble replaceRowno = new UFDouble(replaceno);
				replaceRowno = replaceRowno.add(new UFDouble(-10));
				irownoMap.put(replaceRowno, orderpalnBvos[i].getCorderplanbid());
			} else {
				irownoMap.put(new UFDouble(orderpalnBvos[i].getCrowno()), orderpalnBvos[i].getCorderplanbid());
			}

		}
		this.rownoMap = irownoMap;
		// 按照升序排列行号
		this.orderedRowno = orderRowno(rownoMap);
		return childvalueMap;
	}

	/**
	 * 根据参照的id值得到参照的名字
	 * 
	 * @param itemValues
	 * @return
	 */
	private Map<String, Object[]> getAllRefidValues(Map<String, List<String>> itemValues) {

		if (itemValues == null || itemValues.size() == 0)
			return null;

		OrderplanBVO bvo = new OrderplanBVO();
		Map<String, Object[]> result = new HashMap<String, Object[]>();
		for (Entry<String, List<String>> entry : itemValues.entrySet()) {
			String key = entry.getKey();
			List<String> value = entry.getValue();
			String[] attributevalues = com.yonyou.ec.commons.utils.VOCodeNameUtils.getBeanNames(bvo, key, value.toArray(new String[0]));
			if (attributevalues == null || value.size() != attributevalues.length)
				continue;
			result.put(key, attributevalues);
		}
		return result;
	}

	/**
	 * 得到子表中导出项为参照类型的所有参照id值
	 * 
	 * @param orderpalnBvos
	 * @return
	 */
	private Map<String, List<String>> getAllRefvalues(OrderplanBVO[] orderpalnBvos) {
		Map<String, List<String>> itemValues = new HashMap<String, List<String>>();
		for (String itemkey : childitemkeys) {
			if("islay".equals(itemkey)){
				continue;
			}
			List<String> refids = new ArrayList<String>();
			int modelType = orderpalnBvos[0].getMetaData().getAttribute(itemkey).getModelType();
			if (modelType == IType.REF) {
				for (int i = 0; i < orderpalnBvos.length; i++) {
					Object attributevalue = orderpalnBvos[i].getAttributeValue(itemkey);
					if (attributevalue != null)
						refids.add((String) attributevalue);
					else {
						refids.add(null);
					}
				}
			}
			itemValues.put(itemkey, refids);
		}
		return itemValues;
	}

	/**
	 * 对行号进行排序
	 * 
	 * @param rownoMap
	 * @return
	 */
	private UFDouble[] orderRowno(Map<UFDouble, String> rownoMap) {
		UFDouble[] rowno = rownoMap.keySet().toArray(new UFDouble[0]);
		Arrays.sort(rowno);
		return rowno;
	}

	/**
	 * 
	 * 根据子表主表得到子表主键和孙表主键值 格式为：子表key+":"+孙表1key+","+孙表2key+","+...
	 * 
	 * @param corderplanbid
	 * @return
	 */
	private String getChildGrandKeys(String corderplanbid) {

		if (this.grandprimarykeys.size() == 0)
			this.initGrandsonValue();
		List<String> btpid = this.grandprimarykeys.get(corderplanbid);
		StringBuilder sb = new StringBuilder();
		sb.append(corderplanbid);
		sb.append("%");
		String btpids = getBTPids(btpid);
		sb.append(btpids);
		return sb.toString();
	}

	private String getBTPids(List<String> btpids) {
		StringBuilder result = new StringBuilder();

		for (String btpid : btpids) {
			result.append(btpid);
			result.append(",");
		}
		return result.deleteCharAt(result.length() - 1).toString();
	}

	/**
	 * 得到要货计划孙表的值
	 */
	private HashMap<String, List<List<Object>>> getgrandsonValue() {
		if (grandsonValue == null) {
			initGrandsonValue();
		}
		return this.grandsonValue;

	}

	public HashMap<Integer, List<List<Object>>> getgrandsonValue1() {
		if (aggvo == null)
			return null;
		HashMap<String, List<List<Object>>> grandsonValue = getgrandsonValue();

		HashMap<Integer, List<List<Object>>> result = new HashMap<Integer, List<List<Object>>>();
		if (grandsonValue == null || grandsonValue.size() == 0)
			return null;
		for (int i = 0; i < this.orderedRowno.length; i++) {
			String key = this.rownoMap.get(orderedRowno[i]);
			List<List<Object>> value = grandsonValue.get(key);
			result.put(i, value);
		}

		return result;
	}

	/**
	 * 初始化孙表值
	 */
	private void initGrandsonValue() {
		OrderplanBVO[] orderpalnBvos = (OrderplanBVO[]) aggvo.getChildren(OrderplanBVO.class);
		HashMap<String, List<List<Object>>> result = new HashMap<String, List<List<Object>>>();

		for (int i = 0; i < orderpalnBvos.length; i++) {
			OrderplanBTPVO[] orderplanBTPVOs = orderpalnBvos[i].getOrderplanBTPVOs();
			List<List<Object>> valueList = getValueList(orderplanBTPVOs, orderpalnBvos[i]);
			result.put(orderpalnBvos[i].getCorderplanbid(), valueList);
		}
		this.grandsonValue = result;
	}

	/**
	 * 
	 * 得到要货计划子表orderpalnBvo对应的孙表值list,即按计划开始时间升序排列，
	 * 并且分别在确定区，短期预测区和长期预测区的第一个孙表的前面加了每个时区的合计
	 * 
	 * @param orderplanBTPVOs
	 * @param orderpalnBvo
	 * @return
	 */
	private List<List<Object>> getValueList(OrderplanBTPVO[] orderplanBTPVOs, OrderplanBVO orderpalnBvo) {
		// 要货计划子表主键
		String orderplanbid = orderpalnBvo.getCorderplanbid();
		// 要货计划孙表主键数组
		List<String> timephaseids = new ArrayList<String>();
		int confirm = 0;
		int iplan = 0;
		int forecast = 0;
		// 确定区合计
		List<Object> confirmsum = new ArrayList<Object>();
		// 短期预测区合计
		List<Object> iplansum = new ArrayList<Object>();
		// 长期预测区合计
		List<Object> forecastsum = new ArrayList<Object>();
		// 按照开始时间排序孙表
		OrderplanBTPVO[] orderVos = orderBtpVos(orderplanBTPVOs);

		List<List<Object>> result = new ArrayList<List<Object>>();
		for (OrderplanBTPVO vo : orderVos) {
			// 得到输出项
			List<Object> value = getBTPValue(vo, orderpalnBvo);
			// 输出项加入到合计列中，算出输出项的合计
			if (vo.getFtimearea() == 1) {
				addValue(value, confirmsum);
				confirm++;
			} else if (vo.getFtimearea() == 2) {
				addValue(value, iplansum);
				iplan++;
			} else {
				addValue(value, forecastsum);
				forecast++;
			}
			// 添加孙表主键
			timephaseids.add(vo.getCtimephaseid());
			result.add(value);
		}
		this.grandprimarykeys.put(orderplanbid, timephaseids);
		// 添加确定区合计列
		if (confirm != 0) {
			result.add(0, confirmsum);
		}
		// 添加短期预测合计列
		int iplanBegin = confirm == 0 ? 0 : confirm + 1;
		if (iplan != 0) {
			result.add(iplanBegin, iplansum);
		}
		// 添加长期预测区合计列
		if (forecast != 0) {
			int index = iplan == 0 ? iplanBegin : iplanBegin + iplan + 1;
			result.add(index, forecastsum);
		}
		return result;
	}

	/**
	 * 按照开始时间升序排列孙表数组
	 * 
	 * @param orderplanBTPVOs
	 * @return
	 */
	private OrderplanBTPVO[] orderBtpVos(OrderplanBTPVO[] orderplanBTPVOs) {
		Map<UFDate, OrderplanBTPVO> valueMap = new HashMap<UFDate, OrderplanBTPVO>();
		for (OrderplanBTPVO vo : orderplanBTPVOs) {
			valueMap.put(vo.getDbegindate(), vo);
		}

		List<OrderplanBTPVO> result = new ArrayList<OrderplanBTPVO>();
		Set<UFDate> dateSets = valueMap.keySet();
		UFDate[] dateArrays = dateSets.toArray(new UFDate[0]);
		Arrays.sort(dateArrays, new Comparator<UFDate>() {

			@Override
			public int compare(UFDate o1, UFDate o2) {
				return o1.compareTo(o2);
			}
		});
		for (UFDate date : dateArrays) {
			OrderplanBTPVO value = valueMap.get(date);
			result.add(value);
		}
		return result.toArray(new OrderplanBTPVO[result.size()]);
	}

	/**
	 * 
	 * value和valuesum的对应项分别相加，赋值给valuesum的对应项上
	 * 
	 * @param value
	 * @param valuesum
	 */
	private void addValue(List<Object> value, List<Object> valuesum) {
		List<Object> newvalue = new ArrayList<Object>();
		if (valuesum.size() < value.size()) {
			valuesum.addAll(value);
			return;
		}

		for (int i = 0; i < value.size(); i++) {

			Object sum = valuesum.get(i);
			if (sum != null) {
				if (value.get(i) != null) {
					UFDouble newsum = ((UFDouble) sum).add((UFDouble) value.get(i));
					sum = newsum;
				}
			} else {
				sum = value.get(i);
			}
			newvalue.add(sum);
		}
		valuesum.clear();
		// valuesum.removeAll(valuesum);
		valuesum.addAll(newvalue);
	}

	/**
	 * 要货计划孙表需要输出项的值
	 * 
	 * @param vo
	 * @param orderpalnBvo
	 * @return
	 */
	private List<Object> getBTPValue(OrderplanBTPVO vo, OrderplanBVO orderpalnBvo) {
		List<Object> result = new ArrayList<Object>();

		for (String itemkey : grandsonitemkeys) {
			Object value = vo.getAttributeValue(itemkey);

			result.add(value);
		}
		return result;
	}

}
