package nc.ui.cof.orderplan.maintain.excel.out;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.ui.cof.orderplan.maintain.excel.common.CreateExcelSignTool;
import nc.ui.cof.orderplan.maintain.excel.common.InputItemForCof;
import nc.ui.cof.orderplan.maintain.excel.out.impl.ChildLableRule;
import nc.ui.cof.orderplan.maintain.excel.out.impl.ChildValuerule;
import nc.ui.cof.orderplan.maintain.excel.out.impl.GrandsonLableRule;
import nc.ui.cof.orderplan.maintain.excel.out.impl.GrandsonValueRule;
import nc.ui.cof.orderplan.maintain.excel.out.impl.MergeLableRule;
import nc.ui.cof.orderplan.maintain.excel.out.impl.ParentRule;
import nc.ui.cof.orderplan.maintain.excel.out.infc.IRule;
import nc.vo.cof.orderplan.entity.AggOrderplanVO;
import nc.vo.pub.lang.UFDate;
import nc.ui.cof.orderplan.maintain.excel.out.impl.TopIntroRule;

/**
 * 
 * excel规则器工厂
 * 
 * @since 6.0
 * @version 2013-11-13 下午03:31:03
 * @author ruhuic
 */
public class RuleFactory {
	List<IRule> ruleList = new ArrayList<IRule>();

	public RuleFactory(List<InputItemForCof> items, String planTemplateid, UFDate startTime, AggOrderplanVO aggvo) {
		// 标题工厂
		LableFactory labelfactory = new LableFactory(items, planTemplateid, startTime);
		try {
			labelfactory.init();
		} catch (Exception e) {
			// ExceptionUtils.marsh(ex);
		}
		// 值工厂
		ValueFactory valuefactory = new ValueFactory(aggvo);
		valuefactory.setParentItems(labelfactory.getParentItemKeys());
		valuefactory.setChildItems(labelfactory.getChildItemKeys());
		valuefactory.setGrandsonItems(labelfactory.getGrandsonItemkeys());
		// 添加说明文字规则，在第一行上填写说明文字
		TopIntroRule infoRule = new TopIntroRule();
		ruleList.add(infoRule);
		// 主表规则
		ParentRule parentrule = new ParentRule();
		// 导出的主表的值
		Map<Integer, List<Object>> value = getParentValue(labelfactory, valuefactory);
		parentrule.setValue(value);
		ruleList.add(parentrule);
		// 合并标题区域
		MergeLableRule mergeRule = new MergeLableRule();
		Map<Integer, List<Object>> mergeValue = getMergeValue(labelfactory);
		mergeRule.setValue(mergeValue);
		int[] mergeSpan = getMergeSpan(labelfactory);
		mergeRule.setColumnMergeSpan(mergeSpan);
		ruleList.add(mergeRule);
		// 子表和孙表标题区域
		ChildLableRule childlablerule = new ChildLableRule();
		Map<Integer, List<Object>> childlabelvalue = getChildLableValue(labelfactory);
		childlablerule.setValue(childlabelvalue);
		ruleList.add(childlablerule);
		// 子表值区域
		ChildValuerule childvaluerule = new ChildValuerule();
		Map<Integer, List<Object>> childvalue = getChildValue(valuefactory);
		childvaluerule.setValue(childvalue);
		int[] spans = { labelfactory.getGrandsonItemkeys().size(), 1 };
		int toColumn = labelfactory.getChildItemKeys().size();
		childvaluerule.setTocolumn(toColumn);
		childvaluerule.setMergeSpan(spans);
		ruleList.add(childvaluerule);
		// 孙表标题区域
		GrandsonLableRule grandsonLableRule = new GrandsonLableRule();
		boolean isnull = aggvo == null ? true : false;
		int num;
		if (isnull || valuefactory.getChildValue() == null) {
			num = 1;
		} else {
			num = valuefactory.getChildValue().keySet().size();
		}
		Map<Integer, List<Object>> grandsonLableValue = getGrandsonLableValue(labelfactory, num);
		grandsonLableRule.setValue(grandsonLableValue);
		grandsonLableRule.setFromColumn(labelfactory.getChildItemKeys().size() + 1);
		ruleList.add(grandsonLableRule);
		// 孙表值区域
		GrandsonValueRule grandsonvaluerule = new GrandsonValueRule();
		grandsonvaluerule.setFromColumn(labelfactory.getChildItemKeys().size() + 2);
		HashMap<Integer, List<List<Object>>> grandsonvalue = getGrandsonValue(valuefactory);
		grandsonvaluerule.setValue(grandsonvalue);
		ruleList.add(grandsonvaluerule);
	}

	/**
	 * 得到合并数
	 * 
	 * @param labelfactory
	 * @return
	 */
	private int[] getMergeSpan(LableFactory labelfactory) {

		Map<Integer, Integer> valueMap = labelfactory.gettimeSize();
		int[] result = new int[2 + valueMap.keySet().size()];
		int childkeysize = labelfactory.getChildItemKeys().size();
		result[0] = 1;
		result[1] = childkeysize + 1;
		int i = 2;
		for (Entry<Integer, Integer> value : valueMap.entrySet()) {
			result[i] = value.getValue() + 1;
			i++;
		}
		return result;
	}

	/**
	 * 孙表标题值
	 * 
	 * @param labelfactory
	 * @param num
	 * @return
	 */
	private Map<Integer, List<Object>> getGrandsonLableValue(LableFactory labelfactory, int num) {

		List<String> lables = labelfactory.getGrandsonLables();
		Map<Integer, List<Object>> result = new HashMap<Integer, List<Object>>();
		for (int i = 0; i < num; i++) {
			for (int j = 0; j < lables.size(); j++) {
				List<Object> value = new ArrayList<Object>();
				value.add(lables.get(j));
				result.put(i * lables.size() + j, value);
			}
		}
		return result;
	}

	/**
	 * 孙表值
	 * 
	 * @param valuefactory
	 * @return
	 */
	private HashMap<Integer, List<List<Object>>> getGrandsonValue(ValueFactory valuefactory) {
		HashMap<Integer, List<List<Object>>> grandsonValue = valuefactory.getgrandsonValue1();
		return grandsonValue;
	}

	private Map<Integer, List<Object>> getChildValue(ValueFactory valuefactory) {

		return valuefactory.getChildValue();

	}

	/**
	 * 导出的子表标题值
	 * 
	 * @param labelfactory
	 * @return
	 */
	private Map<Integer, List<Object>> getChildLableValue(LableFactory labelfactory) {
		Map<Integer, List<Object>> result = new HashMap<Integer, List<Object>>();
		List<Object> lables = labelfactory.getChildLables();
		String childsign = labelfactory.getChildSign();
		String grandsonsign = labelfactory.getGrandsonSign();
		String sign = childsign + grandsonsign;
		lables.add(0, sign);
		lables.add(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0", "0ec72001-0017")/*
																									 * @
																									 * res
																									 * "栏目"
																									 */);
		List<Object> grandsonLables = labelfactory.getGrandsonRow();
		lables.addAll(grandsonLables);
		result.put(0, lables);
		return result;
	}

	private Map<Integer, List<Object>> getMergeValue(LableFactory labelfactory) {
		Map<Integer, List<Object>> result = new HashMap<Integer, List<Object>>();
		Map<Integer, Integer> value = labelfactory.gettimeSize();
		int childkeysize = labelfactory.getChildItemKeys().size();
		List<Object> lables = CreateExcelSignTool.createMergeLables(value, childkeysize);
		Object timesign = labelfactory.getGrandsonTimesign();
		lables.add(0, timesign);
		result.put(0, lables);
		return result;
	}

	/**
	 * 导出的主表标题以及主表值
	 * 
	 * @param labelfactory
	 * @param valuefactory
	 * @return
	 */
	private Map<Integer, List<Object>> getParentValue(LableFactory labelfactory, ValueFactory valuefactory) {
		Map<Integer, List<Object>> result = new HashMap<Integer, List<Object>>();
		List<Object> lables = labelfactory.getParentLables();
		lables.add(0, labelfactory.getParentSign());
		result.put(0, lables);
		result.put(1, valuefactory.getparentValues());
		return result;
	}

	public List<IRule> getAllRules() {
		return this.ruleList;
	}
}