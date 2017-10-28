package nc.pub.sync.utils;

import java.util.Map;
import java.util.Set;

import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JSONUtil {

	/**
	 * map表头字段key值
	 */
	public static String HEAD = "head_fields";
	/**
	 * map表体字段key值
	 */
	public static String ITEM = "item_fields";

	/**
	 * 解析JSON
	 * 
	 * @param bill
	 * @param json
	 * @param constrs
	 * @throws BusinessException
	 */
	public static void parseJOSN(AbstractBill bill, JSONObject json, Map<String, Map<String, String>> constrs) throws BusinessException {
		transfer2VO(bill, json, constrs);
	}

	/**
	 * 子表信息校验
	 * 
	 * @param json
	 * @throws BusinessException
	 */
	public static JSONObject checkJSON(String content) throws BusinessException {
		JSONObject json = JSONObject.fromObject(content);
		if (!json.containsKey("item")) {
			throw new BusinessException("缺失子表（从表）信息！");
		}
		return json;
	}

	/**
	 * JSON信息转移到VO中
	 * 
	 * @param bill
	 * @param json
	 * @param constrs
	 * @throws BusinessException
	 */
	private static void transfer2VO(AbstractBill bill, JSONObject json, Map<String, Map<String, String>> constrs) throws BusinessException {
		Map<String, String> headFields = constrs.get(HEAD);
		ISuperVO headVO = bill.getParent();
		transfer2HeadVO(headVO, json, headFields);
		Map<String, String> itemFields = constrs.get(ITEM);
		CircularlyAccessibleValueObject[] itemVOs = bill.getChildrenVO();
		transfer2ItemVOs(itemVOs, json, itemFields);
	}

	/**
	 * JSON信息转移到headVO中
	 * 
	 * @param headVO
	 * @param json
	 * @param headFields
	 */
	private static void transfer2HeadVO(ISuperVO headVO, JSONObject json, Map<String, String> headFields) {
		Set<String> keys = headFields.keySet();
		for (String key : keys) {
			headVO.setAttributeValue(key, json.get(headFields.get(key)));
		}
	}

	/**
	 * JSON信息转移到itemVOs中
	 * 
	 * @param itemVOs
	 * @param json
	 * @param itemFields
	 */
	private static void transfer2ItemVOs(CircularlyAccessibleValueObject[] itemVOs, JSONObject json, Map<String, String> itemFields) {
		Set<String> keys = itemFields.keySet();
		JSONArray items = json.getJSONArray("item");
		for (int index = 0; index < items.size(); index++) {
			for (String key : keys) {
				itemVOs[index].setAttributeValue(key, items.getJSONObject(index).get(itemFields.get(key)));
			}
		}
	}

}
