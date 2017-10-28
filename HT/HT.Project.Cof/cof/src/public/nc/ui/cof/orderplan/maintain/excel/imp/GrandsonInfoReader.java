package nc.ui.cof.orderplan.maintain.excel.imp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Pattern;

import nc.ui.cof.orderplan.maintain.excel.common.CreateExcelSignTool;
import nc.ui.cof.orderplan.maintain.excel.common.ExportExcelSign;
import nc.ui.cof.orderplan.maintain.excel.common.FieldItem;
import nc.ui.cof.orderplan.maintain.excel.imp.itf.DataHandlerAgency;
import nc.vo.cof.orderplan.entity.AggOrderplanVO;
import nc.vo.cof.orderplan.entity.OrderplanBTPVO;
import nc.vo.cof.orderplan.entity.OrderplanBVO;
import nc.vo.ecapppub.plan.enumeration.plancycle.EnumTimeArea;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;

import com.yonyou.ec.plan.entity.BillTplConfigVO;
import com.yonyou.vo.ecapppub.plan.util.PlanStartTimeUtil;
import com.yonyou.vo.ecapppub.plan.util.PlanTimeBean;

/**
 * 孙表读取器
 * 
 * @since 6.0
 * @version 2013-10-31 下午02:25:47
 * @author ruhuic
 */
public class GrandsonInfoReader {
	/**
	 * 读取的excel工作薄
	 */
	private Sheet sheet;

	/**
	 * 更新和新增操作的子表列
	 */
	private OrderplanBVO[] childVo;

	/**
	 * 计划开始时间
	 */
	private UFDate startTime;

	/**
	 * 计划模板id
	 */
	private String plantemplateid;

	/**
	 * 孙表标识，记录确定区，短期预测区，长期预测区三个区域位于哪几列
	 */
	private Map<String, String> timesignMap;

	/**
	 * 错误信息
	 */
	private List<String> errorMsg = new ArrayList<String>();

	/**
	 * 组织id
	 */
	private String pk_orgid;

	/**
	 * 孙表导出项
	 */
	private String[] grandsonkeys;

	/**
	 * 子表主键标识，记录子表和孙表的主键值
	 */
	private List<String> childPrimaryKeys;

	private ExcelRegulation regulation;

	/**
	 * 子表的行数
	 */
	private int childNum;
	// 父项Reader
	private ParentInfoReader parentreader;
	// 子项Reader
	private ChildInfoReader childReader;

	/**
	 * 原始的aggvo
	 */
	private AggOrderplanVO originaggvo;

	private MapList<Integer, BillTplConfigVO> billConfigvo;

	public List<String> getErrorMsg() {
		return this.errorMsg;
	}

	public void setChildPrimaryKeys(List<String> childPrimaryKeys) {
		this.childPrimaryKeys = childPrimaryKeys;
	}

	public void setOriginAggvo(AggOrderplanVO aggvo) {
		this.originaggvo = aggvo;
	}

	public void setGrandsonKeys(String[] grandsonkeys) {
		this.grandsonkeys = grandsonkeys;
	}

	public List<String> getChildPrimaryKeys() {
		return this.childPrimaryKeys;
	}

	public GrandsonInfoReader(Sheet sheet) {
		this.sheet = sheet;
	}

	/**
	 * 初始化标识
	 * 
	 * @return
	 */
	public List<String> initSign() {

		List<String> errorMsg = new ArrayList<String>();
		Cell markCell = sheet.getRow(this.regulation.getSignPos()[0]).getCell(this.regulation.getSignPos()[1]);
		ExcelReadUtil util = new ExcelReadUtil();

		try {
			// 读取标识
			Object headSign = util.getCellValue(markCell, this.regulation.getSignPos()[0], this.regulation.getSignPos()[1]);
			convertSign((String) headSign);
		} catch (Exception e) {
			errorMsg.add(e.getMessage());
			return errorMsg;
		}
		return null;

	}

	private void convertSign(String timePhaseSign) throws BusinessException {
		this.timesignMap = CreateExcelSignTool.reverceTimePhase(timePhaseSign);
	}

	/**
	 * 转换标识
	 * 
	 * @param confirmcolumn
	 * @return
	 */
	private Integer[] getColumns(String confirmcolumn) {
		if (confirmcolumn != null) {
			String[] columns = confirmcolumn.split(ExportExcelSign.numInterger);
			Integer[] columnint = new Integer[columns.length];
			for (int i = 0; i < columns.length; i++) {
				columnint[i] = Integer.parseInt(columns[i]);
			}
			return columnint;
		}
		return null;
	}

	/**
	 * 创建vo
	 */
	public void createVos() {
		PlanStartTimeUtil timeUtil = new PlanStartTimeUtil();
		Map<Integer, List<PlanTimeBean>> dateBean = null;
		try {
			// 此时保证dateBean中的数是从小到大排序的
			dateBean = timeUtil.getDates(this.plantemplateid, startTime);
		} catch (Exception e) {
			this.errorMsg.add(e.getMessage());
			return;
		}
		// 得到确定区的标示
		String timephase = timesignMap.get(ExportExcelSign.confirmSign);
		// 确定区的值所在的列
		Integer[] columns = getColumns(timephase);
		Map<Integer, List<Map<String, Object>>> confirmValues = null;
		// 读取确定区的值
		if (columns != null) {
			confirmValues = getAllValues(columns[0], columns[1]);
		}

		timephase = timesignMap.get(ExportExcelSign.iplanSign);
		columns = getColumns(timephase);
		Map<Integer, List<Map<String, Object>>> iplanValues = null;
		if (columns != null) {
			iplanValues = getAllValues(columns[0], columns[1]);
		}

		timephase = timesignMap.get(ExportExcelSign.iforecastSign);
		columns = getColumns(timephase);
		Map<Integer, List<Map<String, Object>>> iforecastValues = null;
		if (columns != null) {
			iforecastValues = getAllValues(columns[0], columns[1]);
		}

		for (int i = 0; i < this.childVo.length; i++) {
			OrderplanBVO childVo = this.childVo[i];
			ArrayList<OrderplanBTPVO> bTPVOList = new ArrayList<OrderplanBTPVO>();
			if (confirmValues != null) {
				List<Map<String, Object>> confirmVos = confirmValues.get(i);
				List<OrderplanBTPVO> confirmList = getVos(confirmVos, dateBean.get(EnumTimeArea.CONFIRM_AREA.toInteger()),
						EnumTimeArea.CONFIRM_AREA.toInteger(), i);
				bTPVOList.addAll(confirmList);
			}
			if (iplanValues != null) {
				List<OrderplanBTPVO> iplanList = getVos(iplanValues.get(i), dateBean.get(EnumTimeArea.PLAN_AREA.toInteger()),
						EnumTimeArea.PLAN_AREA.toInteger(), i);
				bTPVOList.addAll(iplanList);
			}
			if (iforecastValues != null) {
				List<OrderplanBTPVO> iforecastList = getVos(iforecastValues.get(i), dateBean.get(EnumTimeArea.FORECAST_AREA.toInteger()),
						EnumTimeArea.FORECAST_AREA.toInteger(), i);
				bTPVOList.addAll(iforecastList);
			}
			// 添加子表主键和自身的主键
			if (this.regulation.getUpdateFlag()) {
				addPrimarykeyAndStatus(bTPVOList, childVo, i);
			}

			childVo.setOrderplanBTPVOs(bTPVOList.toArray(new OrderplanBTPVO[bTPVOList.size()]));
		}
	}

	/**
	 * 添加主键，状态等值
	 * 
	 * @param bTPVOList
	 * @param childVo
	 * @param rowno
	 */
	private void addPrimarykeyAndStatus(ArrayList<OrderplanBTPVO> bTPVOList, OrderplanBVO childVo, int rowno) {
		String orderplanbid = childVo.getCorderplanbid();
		OrderplanBTPVO[] btpvos = getRespondingBTPvos(orderplanbid, this.originaggvo.getChildrenBVO());
		int childstatus = childVo.getStatus();
		String[] keys = this.getGrandSonPrimaryKeys(rowno);
		int j = 0;
		for (OrderplanBTPVO btpvo : bTPVOList) {
			// 增加状态
			btpvo.setStatus(childstatus);
			if (childstatus == VOStatus.UPDATED) {
				OrderplanBTPVO originbtpvo = getOriginBtpvo(btpvos, keys[j]);
				if (originbtpvo == null)
					continue;
				// 添加不更新的值
				addNotUpdateValue(btpvo, originbtpvo);
				// 增加子表主键
				btpvo.setCorderplanbid(orderplanbid);
				// 增加自身主键
				addOrderplanbtpid(btpvo, keys[j]);
				// 增加ts值
				btpvo.setTs(originbtpvo.getTs());

			}
			j++;
		}

	}

	/**
	 * 添加不更新的值
	 * 
	 * @param btpvo
	 * @param origivo
	 */
	private void addNotUpdateValue(OrderplanBTPVO btpvo, OrderplanBTPVO origivo) {

		String[] attributenames = origivo.getAttributeNames();
		String[] notupdateattri = getNotUpdateAttri(attributenames);
		for (int i = 0; i < notupdateattri.length; i++) {
			Object newValue = origivo.getAttributeValue(notupdateattri[i]);
			btpvo.setAttributeValue(notupdateattri[i], newValue);

		}
	}

	private String[] getNotUpdateAttri(String[] attributenames) {
		List<String> result = new ArrayList<String>();
		String[] updateKeys = filterItemKeys(this.grandsonkeys);
		for (String attri : attributenames) {
			if (!contain(attri, updateKeys)) {
				result.add(attri);
			}

		}
		return result.toArray(new String[0]);
	}

	/**
	 * 进行excel导入时，根据模板配置规则门户可以编辑的字段可以进行修改，其他的不允许修改
	 * 
	 * @param itemKeys
	 * @return
	 */
	private String[] filterItemKeys(String[] itemKeys) {
		ArrayList<String> result = new ArrayList<String>();
		for (Entry<Integer, List<BillTplConfigVO>> entry : this.billConfigvo.entrySet()) {
			List<BillTplConfigVO> valueList = entry.getValue();
			for (BillTplConfigVO value : valueList) {
				String itemkey = value.getItemkey();
				if (value.getWebeditflag().booleanValue() && contain(itemkey, itemKeys)) {
					result.add(itemkey);

				}
			}

		}
		return result.toArray(new String[0]);
	}

	private boolean contain(String attri, String[] itemKeys) {
		if (itemKeys == null || itemKeys.length == 0 || attri == null)
			return false;
		for (int i = 0; i < itemKeys.length; i++) {
			if (attri.equals(itemKeys[i]))
				return true;
		}
		return false;
	}

	private OrderplanBTPVO getOriginBtpvo(OrderplanBTPVO[] btpvos, String key) {
		for (int i = 0; i < btpvos.length; i++) {
			if (btpvos[i].getCtimephaseid().equals(key))
				return btpvos[i];
		}
		return null;
	}

	private OrderplanBTPVO[] getRespondingBTPvos(String orderplanbid, OrderplanBVO[] bvos) {
		if (orderplanbid == null)
			return null;
		for (int i = 0; i < bvos.length; i++) {
			if (bvos[i].getCorderplanbid().equals(orderplanbid))
				return bvos[i].getOrderplanBTPVOs();
		}
		return null;

	}

	/**
	 * 添加自身主键
	 */
	private void addOrderplanbtpid(OrderplanBTPVO bTPVO, String key) {

		bTPVO.setCtimephaseid(key);

	}

	private List<OrderplanBTPVO> getVos(List<Map<String, Object>> confirmVos, List<PlanTimeBean> timeList, int timephase, int index) {
		List<OrderplanBTPVO> result = new ArrayList<OrderplanBTPVO>();
		String isLay = (String) this.childReader.getChildValuesMap().get(index).get("islay");
		Object days = this.childReader.getChildValuesMap().get(index).get("days");
		for (int i = 0; i < confirmVos.size(); i++) {
			if(confirmVos.get(i) == null){
				continue;
			}
			// 要货计划xls导入平铺开发，确认长周期去开始平铺
			if (timephase == 3) {
				List<Map<String, Object>> splits = splitConfirmVO(confirmVos.get(i), isLay, days.toString());
				for (Map<String, Object> split : splits) {
					OrderplanBTPVO vo = getBTPvo(split, timeList.get(i), timephase);
					result.add(vo);
				}
				// Map<String, Object> confirmVO = confirmVos.get(i);
			} else {
				OrderplanBTPVO vo = getBTPvo(confirmVos.get(i), timeList.get(i), timephase);
				result.add(vo);
			}
		}
		return result;
	}

	/**
	 * 周计划平铺到日
	 * @param confirmVO
	 * @param isLay
	 * @return
	 */
	public List<Map<String, Object>> splitConfirmVO(Map<String, Object> confirmVO, String isLay, String days) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		// 计划数量
		String nplanastnum = confirmVO.get("nplanastnum") == null ? null :confirmVO.get("nplanastnum").toString() ;
		// 确认数量
		String nconfirmedastnum = confirmVO.get("nconfirmedastnum") == null ? null : confirmVO.get("nconfirmedastnum").toString();
		if("Y".equals(isLay)){
			int limit = 6;
			if(days != null && !days.trim().equals("")){
				String pattern = "[1-7]";
				boolean flag = Pattern.matches(pattern, days.trim());
				if(flag){
					limit = Integer.parseInt(days.trim());
				}else{
					ExceptionUtils.wrappBusinessException("xls中的【平铺天数】填写有误，应填写1-6的自然数，请检查！");
				}
			}else{
				ExceptionUtils.wrappBusinessException("xls中的【平铺天数】不能为空，请检查！");
			}
			nplanastnum = getSplitNum(nplanastnum, limit);
			nconfirmedastnum = getSplitNum(nconfirmedastnum, limit);
			for(int index = 0;index < limit; index++) {
				Map<String, Object> res = new HashMap<String, Object>();
				res.put("nplanastnum", nplanastnum);
				res.put("nconfirmedastnum", nconfirmedastnum);
				list.add(res);
			}
			for(int index = 0; index < 7-limit; index++){
				Map<String, Object> res = new HashMap<String, Object>();
				res.put("nplanastnum", null);
				res.put("nconfirmedastnum", null);
				list.add(res);
			}
		}else{
			Map<String, Object> first = new HashMap<String, Object>();
			first.put("nplanastnum", nplanastnum);
			first.put("nconfirmedastnum", nconfirmedastnum);
			list.add(first);
			for (int i = 0; i < 6; i++) {
				Map<String, Object> res = new HashMap<String, Object>();
				res.put("nplanastnum", null);
				res.put("nconfirmedastnum", null);
				list.add(res);
			}
		}
		return list;
	}

	public String getSplitNum(String num, int days) {
		if (num == null) {
			return null;
		}
		return new UFDouble(num).div(days).setScale(0, UFDouble.ROUND_UP).toString();
	}

	/**
	 * 转换读取的值为btpvo
	 * 
	 * @param map
	 * @param planTimeBean
	 * @param timephase
	 * @return
	 */
	private OrderplanBTPVO getBTPvo(Map<String, Object> map, PlanTimeBean planTimeBean, int timephase) {
		OrderplanBTPVO vo = new OrderplanBTPVO();
		for (Entry<String, Object> entry : map.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			int modelType = vo.getMetaData().getAttribute(key).getModelType();
			DataHandlerAgency agency = new DataHandlerAgency(null);
			FieldItem item = new FieldItem();
			item.setItemKey(key);
			item.setModelType(modelType);
			try {
				agency.handeData(item, vo, value, this.getPk_orgid());
			} catch (Exception e) {

				this.errorMsg.add(e.getMessage());
			}

		}
		// 添加开始日期，结束日期和区域
		vo.setAttributeValue(OrderplanBTPVO.DBEGINDATE, planTimeBean.getStarttime());
		vo.setAttributeValue(OrderplanBTPVO.DENDDATE, planTimeBean.getEndtime());
		vo.setAttributeValue(OrderplanBTPVO.FTIMEAREA, timephase);
		return vo;
	}

	private String[] getGrandSonPrimaryKeys(int rownum) {
		String keys = this.childPrimaryKeys.get(rownum);
		if (keys != null && !keys.equals("null")) {
			String[] spitkeys = this.childPrimaryKeys.get(rownum).split("%");
			return spitkeys[1].split(",");
		}
		return null;

	}

	/**
	 * 读取开始列到结束列的值
	 * 
	 * @param fromcolumn
	 *            ：开始列
	 * @param tocolumn
	 *            ：结束列
	 * @return result:<key=行号，value="读取值"数组>,"读取值"=<key=itemkey,value=值>
	 */
	public Map<Integer, List<Map<String, Object>>> getAllValues(int fromcolumn, int tocolumn) {
		Map<Integer, List<Map<String, Object>>> result = new HashMap<Integer, List<Map<String, Object>>>();

		for (int i = 0; i < this.getChildNum(); i++) {
			// 第i行的开始行号值
			int fromrowNo = this.regulation.getValueRowNo() + i * this.grandsonkeys.length;
			// 第i行的结束行号值
			int torowNo = this.regulation.getValueRowNo() + (i + 1) * this.grandsonkeys.length;
			List<Map<String, Object>> valueList = getValueList(fromcolumn, tocolumn, fromrowNo, torowNo);
			result.put(i, valueList);
		}
		return result;
	}

	/**
	 * 读取excel数据
	 * 
	 * @param fromcolumn
	 *            ：开始列
	 * @param tocolumn
	 *            ：结束列
	 * @param fromrowNo
	 *            ：开始行
	 * @param torowNo
	 *            ：结束行
	 * @return
	 */
	private List<Map<String, Object>> getValueList(int fromcolumn, int tocolumn, int fromrowNo, int torowNo) {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (int i = fromcolumn; i <= tocolumn; i++) {
			Map<String, Object> element = getValueMap(fromrowNo, torowNo, i);
			result.add(element);
		}
		return result;
	}

	private Map<String, Object> getValueMap(int fromrowNo, int torowNo, int columnNo) {
		Map<String, Object> result = new HashMap<String, Object>();
		for (int i = fromrowNo; i < torowNo; i++) {
			Cell cell = sheet.getRow(i).getCell(columnNo);
			ExcelReadUtil util = new ExcelReadUtil();

			Object value;
			try {
				value = util.getCellValue(cell);
				result.put(this.grandsonkeys[i - fromrowNo], value);
			} catch (Exception e) {
				this.errorMsg.add(e.getMessage());
			}

		}

		return result;
	}

	public void setChildVo(OrderplanBVO[] childVo) {
		this.childVo = childVo;
	}

	public void setStartTime(UFDate startTime) {
		this.startTime = startTime;
	}

	public void setPlantemplateid(String plantemplateid) {
		this.plantemplateid = plantemplateid;
	}

	public String getPlantemplateid() {
		return this.plantemplateid;
	}

	public void setPk_orgid(String pk_orgid) {
		this.pk_orgid = pk_orgid;
	}

	public String getPk_orgid() {
		return this.pk_orgid;
	}

	public void setRegulation(ExcelRegulation regulation) {
		this.regulation = regulation;
	}

	public void setChildNum(int childNum) {
		this.childNum = childNum;
	}

	public int getChildNum() {
		return this.childNum;
	}

	public void setBillTPLConfigVO(MapList<Integer, BillTplConfigVO> configvo) {
		this.billConfigvo = configvo;
	}

	public ParentInfoReader getParentreader() {
		return parentreader;
	}

	public void setParentreader(ParentInfoReader parentreader) {
		this.parentreader = parentreader;
	}

	public ChildInfoReader getChildReader() {
		return childReader;
	}

	public void setChildReader(ChildInfoReader childReader) {
		this.childReader = childReader;
	}

}
