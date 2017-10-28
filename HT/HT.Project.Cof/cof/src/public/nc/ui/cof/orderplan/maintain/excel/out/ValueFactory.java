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
 * ����Ҫ���ƻ�aggvo�õ������ӱ�����excel���ֵ
 * 
 * @since 6.0
 * @version 2013-11-13 ����03:34:46
 * @author ruhuic
 */
public class ValueFactory {
	/**
	 * Ҫ���ƻ�aggvo
	 */
	private AggOrderplanVO aggvo;

	/**
	 * �������������key
	 */
	private List<String> parentitems;

	/**
	 * ������ӱ�����key
	 */
	private List<String> childitemkeys;

	/**
	 * ������������key
	 */
	private List<String> grandsonitemkeys;

	/**
	 * �������Map <key,value>=<�ӱ�����key,�������List(���ƻ���ʼʱ����������)>
	 */
	private Map<String, List<String>> grandprimarykeys = new HashMap<String, List<String>>();

	/**
	 * �ӱ�ֵ˳��Map,<key,value>=<˳��ֵ ,�ӱ�����>
	 */
	private Map<UFDouble, String> rownoMap = new HashMap<UFDouble, String>();

	/**
	 * ���ֵMap <key,value>=<�ӱ�����,���ֵlist<���ƻ���ʼʱ����������>>
	 */
	private HashMap<String, List<List<Object>>> grandsonValue;

	/**
	 * �������е��к�
	 */
	private UFDouble[] orderedRowno;

	/**
	 * �������ӱ�ֵ
	 */
	private HashMap<Integer, List<Object>> childvalue;

	public ValueFactory(AggOrderplanVO vo) {
		this.aggvo = vo;
		// ������
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
	 * Ҫ���ƻ�ͷ��Ϣ��ֵ
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
	 * �����ӱ�ֵ
	 * 
	 * @return
	 */
	public HashMap<Integer, List<Object>> getChildValue() {
		if (this.childvalue != null) {
			return this.childvalue;
		}
		if (aggvo == null)
			return null;
		// �õ��ӱ�ĵ������ֵ
		HashMap<String, List<Object>> childvalue = getBodyDimention();
		HashMap<Integer, List<Object>> result = new HashMap<Integer, List<Object>>();
		if (childvalue == null || childvalue.size() == 0) {
			return null;
		}
		// �ڵ�����ĵ�һ��λ������ӣ��ӱ�����+�������ֵ)Ԫ��
		for (int i = 0; i < this.orderedRowno.length; i++) {
			String key = this.rownoMap.get(orderedRowno[i]);
			List<Object> value = childvalue.get(key);
			// �����ӱ������õ��ӱ��������������ֵ
			// ע�⣺�洢�����ӱ�������Ϊ�˵������ʱ��
			String keys = getChildGrandKeys(key);
			value.add(0, keys);
			result.put(i, value);
		}
		this.childvalue = result;
		return result;

	}

	/**
	 * �õ�Ҫ���ƻ��ӱ��ֵ
	 */
	private HashMap<String, List<Object>> getBodyDimention() {
		if (aggvo == null)
			return null;
		// ��ʼ�����ֵ
		this.initGrandsonValue();
		OrderplanBVO[] orderpalnBvos = (OrderplanBVO[]) aggvo.getChildren(OrderplanBVO.class);
		// �������ӱ���
		List<String> itemkeys = this.childitemkeys;
		// �к�-�ӱ�����map
		Map<UFDouble, String> irownoMap = new HashMap<UFDouble, String>();
		UFDouble replaceno = new UFDouble(-10);
		// �ӱ�ֵmap:key=�ӱ�������value=������ӱ�ֵ
		HashMap<String, List<Object>> childvalueMap = new HashMap<String, List<Object>>();
		if (orderpalnBvos == null || orderpalnBvos.length <= 0) {
			return childvalueMap;
		}
		// �ӱ��е�����Ϊ���յ�<itemkey,����id����>
		Map<String, List<String>> itemValues = getAllRefvalues(orderpalnBvos);
		// ���ݲ���id�õ����յ�name
		Map<String, Object[]> itemref_idMap = getAllRefidValues(itemValues);
		for (int i = 0; i < orderpalnBvos.length; i++) {
			// �õ��������ֵ
			List<Object> valueList = new ArrayList<Object>();
			for (String itemkey : itemkeys) {
				if("islay".equals(itemkey)){
					continue;
				}
				String attribute = itemkey;
				Object attributevalue = orderpalnBvos[i].getAttributeValue(attribute);
				int modelType = orderpalnBvos[i].getMetaData().getAttribute(attribute).getModelType();
				// ����Ϊ����ʱ�������������
				if (modelType == IType.REF && attributevalue != null) {
					// �ȸ��ݲ���map�õ�����
					if (itemref_idMap != null && itemref_idMap.containsKey(itemkey)) {
						attributevalue = itemref_idMap.get(itemkey)[i];
					}
					// �������map�����ڣ���������������ѯ
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
			// �ӱ��к�ֵ
			String rowno = orderpalnBvos[i].getCrowno();
			// �ӱ��кŲ�����ʱ�����ø����кŴ��棬��ֹ���ж�ʧ
			if (rowno == null || rowno.length() <= 0) {
				UFDouble replaceRowno = new UFDouble(replaceno);
				replaceRowno = replaceRowno.add(new UFDouble(-10));
				irownoMap.put(replaceRowno, orderpalnBvos[i].getCorderplanbid());
			} else {
				irownoMap.put(new UFDouble(orderpalnBvos[i].getCrowno()), orderpalnBvos[i].getCorderplanbid());
			}

		}
		this.rownoMap = irownoMap;
		// �������������к�
		this.orderedRowno = orderRowno(rownoMap);
		return childvalueMap;
	}

	/**
	 * ���ݲ��յ�idֵ�õ����յ�����
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
	 * �õ��ӱ��е�����Ϊ�������͵����в���idֵ
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
	 * ���кŽ�������
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
	 * �����ӱ�����õ��ӱ��������������ֵ ��ʽΪ���ӱ�key+":"+���1key+","+���2key+","+...
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
	 * �õ�Ҫ���ƻ�����ֵ
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
	 * ��ʼ�����ֵ
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
	 * �õ�Ҫ���ƻ��ӱ�orderpalnBvo��Ӧ�����ֵlist,�����ƻ���ʼʱ���������У�
	 * ���ҷֱ���ȷ����������Ԥ�����ͳ���Ԥ�����ĵ�һ������ǰ�����ÿ��ʱ���ĺϼ�
	 * 
	 * @param orderplanBTPVOs
	 * @param orderpalnBvo
	 * @return
	 */
	private List<List<Object>> getValueList(OrderplanBTPVO[] orderplanBTPVOs, OrderplanBVO orderpalnBvo) {
		// Ҫ���ƻ��ӱ�����
		String orderplanbid = orderpalnBvo.getCorderplanbid();
		// Ҫ���ƻ������������
		List<String> timephaseids = new ArrayList<String>();
		int confirm = 0;
		int iplan = 0;
		int forecast = 0;
		// ȷ�����ϼ�
		List<Object> confirmsum = new ArrayList<Object>();
		// ����Ԥ�����ϼ�
		List<Object> iplansum = new ArrayList<Object>();
		// ����Ԥ�����ϼ�
		List<Object> forecastsum = new ArrayList<Object>();
		// ���տ�ʼʱ���������
		OrderplanBTPVO[] orderVos = orderBtpVos(orderplanBTPVOs);

		List<List<Object>> result = new ArrayList<List<Object>>();
		for (OrderplanBTPVO vo : orderVos) {
			// �õ������
			List<Object> value = getBTPValue(vo, orderpalnBvo);
			// �������뵽�ϼ����У���������ĺϼ�
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
			// ����������
			timephaseids.add(vo.getCtimephaseid());
			result.add(value);
		}
		this.grandprimarykeys.put(orderplanbid, timephaseids);
		// ���ȷ�����ϼ���
		if (confirm != 0) {
			result.add(0, confirmsum);
		}
		// ��Ӷ���Ԥ��ϼ���
		int iplanBegin = confirm == 0 ? 0 : confirm + 1;
		if (iplan != 0) {
			result.add(iplanBegin, iplansum);
		}
		// ��ӳ���Ԥ�����ϼ���
		if (forecast != 0) {
			int index = iplan == 0 ? iplanBegin : iplanBegin + iplan + 1;
			result.add(index, forecastsum);
		}
		return result;
	}

	/**
	 * ���տ�ʼʱ�����������������
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
	 * value��valuesum�Ķ�Ӧ��ֱ���ӣ���ֵ��valuesum�Ķ�Ӧ����
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
	 * Ҫ���ƻ������Ҫ������ֵ
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
