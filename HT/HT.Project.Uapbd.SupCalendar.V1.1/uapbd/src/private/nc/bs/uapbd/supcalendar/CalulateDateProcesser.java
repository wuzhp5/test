package nc.bs.uapbd.supcalendar;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.jdbc.framework.processor.ColumnListProcessor;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.uapbd.supcalendar.SupCalendarBaseVO;
import nc.vo.uapbd.supcalendar.SupcalendarDateVO;

public class CalulateDateProcesser implements IRule<SupCalendarBaseVO> {

	private BaseDAO baseDAO = new BaseDAO();
	private Map<String, String> constrs = new HashMap<String, String>();

	public CalulateDateProcesser() {
		constrs.put("1", "january");
		constrs.put("2", "february");
		constrs.put("3", "march");
		constrs.put("4", "april");
		constrs.put("5", "may");
		constrs.put("6", "june");
		constrs.put("7", "july");
		constrs.put("8", "august");
		constrs.put("9", "september");
		constrs.put("10", "october");
		constrs.put("11", "november");
		constrs.put("12", "december");
	}

	@Override
	public void process(SupCalendarBaseVO[] baseVOs) {
		for (SupCalendarBaseVO baseVO : baseVOs) {
			Map<String, Map<String, String>> res = new HashMap<String, Map<String, String>>();
			List<String> days = queryNonworkdays(baseVO.getPk_workcalendar());
			Set<UFDate> deliverDays = new HashSet<UFDate>();
			if("1".equals(baseVO.getPk_model())){
				boolean sunday = !baseVO.getSunday().booleanValue();
				boolean monday = !baseVO.getMonday().booleanValue();
				boolean tuesday = !baseVO.getTuesday().booleanValue();
				boolean wednesday = !baseVO.getWednesday().booleanValue();
				boolean thursday = !baseVO.getThursday().booleanValue();
				boolean friday = !baseVO.getFriday().booleanValue();
				boolean saturday = !baseVO.getSaturday().booleanValue();
				if(sunday&&monday&&tuesday&&wednesday&&thursday&&friday&&saturday){
					ExceptionUtils.wrappBusinessException("\"周周期送货\"模式需选择周天！");
				}

				deliverDays = calDeliverDays(baseVO.getBegindate(), baseVO.getEnddate(), acquireWeeks(baseVO), days);
			}else{
				deliverDays = calDeliverDays(baseVO.getPk_model(), baseVO.getBegindate(), baseVO.getEnddate(), days);
			}
			for (UFDate day : deliverDays) {
				String year = day.getYear() + "";
				Map<String, String> info = new HashMap<String, String>();
				if (res.containsKey(year)) {
					info = res.get(year);
				} else {
					res.put(year, info);
				}
				String month = day.getMonth() + "";
				if (info.containsKey(month)) {
					info.put(month, info.get(month) + "," + day.getDay());
				} else {
					info.put(month, "" + day.getDay());
				}
			}
			SupcalendarDateVO[] dateVOs = baseVO.getSupcalendar_d();
			for (SupcalendarDateVO dateVO : dateVOs) {
				dateVO.setDef1("N");
				fillAttributeValue(res.get(dateVO.getYear()), dateVO);
			}
		}
	}

	public void fillAttributeValue(Map<String, String> res, SupcalendarDateVO dateVO) {
		Set<String> keys = res.keySet();
		for (String key : keys) {
			dateVO.setAttributeValue(constrs.get(key), sortDays(res.get(key)));
		}
	}

	@SuppressWarnings("unchecked")
	private List<String> queryNonworkdays(String pk_workcalendar) {
		StringBuffer sql = new StringBuffer();
		sql.append("select calendardate from bd_workcalendardate where ");
		sql.append("pk_workcalendar = '" + pk_workcalendar + "' and (datetype = 1 or datetype = 2) ");
		List<String> res = new ArrayList<String>();
		try {
			res = (List<String>) baseDAO.executeQuery(sql.toString(), new ColumnListProcessor());
		} catch (Exception e) {
			ExceptionUtils.wrappException(e);
		}
		return res;
	}

	private Set<UFDate> calDeliverDays(String pk_model, UFDate beginDate, UFDate endDate, List<String> nonWorkDays) {
		Set<UFDate> res = new HashSet<UFDate>();
		if("2".equals(pk_model)){
			setDeliverDays(res, beginDate, endDate, nonWorkDays, 1);
		}else if("3".equals(pk_model)){
			setDeliverDays(res, beginDate, endDate, nonWorkDays, 1);
			setDeliverDays(res, beginDate, endDate, nonWorkDays, 15);
		}
		return res;
	}

	private void setDeliverDays(Set<UFDate> res, UFDate beginDate, UFDate endDate, List<String> nonWorkDays, int day){
		int curYear = beginDate.getYear();
		int curMonth = beginDate.getMonth();
		UFDate date = new UFDate(beginDate.getYear()+"-"+curMonth+"-"+day);
		if(date.before(beginDate)){
			curMonth++;
			date = new UFDate(beginDate.getYear()+"-"+curMonth+"-"+day);
		}
		while (!date.after(endDate)) {
			UFDate tempDate = date;
			boolean flag = false;
			while (nonWorkDays.contains(tempDate.toString().substring(0, 10))) {
				tempDate = tempDate.getDateAfter(1);
				if (tempDate.after(endDate)) {
					flag = true;
					break;
				}
			}
			if (flag) break;
			res.add(tempDate);
			if(curMonth == 12){
				curMonth = 1;
				curYear++;
			}else{
				curMonth++;
			}
			date = new UFDate(curYear+"-"+curMonth+"-"+day);
		}
	}
	
	private Set<UFDate> calDeliverDays(UFDate beginDate, UFDate endDate, List<Integer> weeks, List<String> nonWorkDays) {
		Set<UFDate> res = new HashSet<UFDate>();
		for (int week = 0; week < 7; week++) {
			UFDate date = beginDate.getDateAfter(week);
			if (weeks.contains(new Integer(date.getWeek()))) {
				while (!date.after(endDate)) {
					UFDate tempDate = date;
					boolean flag = false;
					while (nonWorkDays.contains(tempDate.toString().substring(0, 10))) {
						tempDate = tempDate.getDateAfter(1);
						if (tempDate.after(endDate)) {
							flag = true;
							break;
						}
					}
					if (flag)
						break;
					res.add(tempDate);
					date = date.getDateAfter(7);
				}
			}
		}
		return res;
	}

	private List<Integer> acquireWeeks(SupCalendarBaseVO baseVO) {
		List<Integer> weeks = new ArrayList<Integer>();
		if ("Y".equals(baseVO.getAttributeValue("sunday").toString())) {
			weeks.add(0);
		}
		if ("Y".equals(baseVO.getAttributeValue("monday").toString())) {
			weeks.add(1);
		}
		if ("Y".equals(baseVO.getAttributeValue("tuesday").toString())) {
			weeks.add(2);
		}
		if ("Y".equals(baseVO.getAttributeValue("wednesday").toString())) {
			weeks.add(3);
		}
		if ("Y".equals(baseVO.getAttributeValue("thursday").toString())) {
			weeks.add(4);
		}
		if ("Y".equals(baseVO.getAttributeValue("friday").toString())) {
			weeks.add(5);
		}
		if ("Y".equals(baseVO.getAttributeValue("saturday").toString())) {
			weeks.add(6);
		}
		return weeks;
	}

	private String sortDays(String value) {
		String[] arr = value.split(",");
		for (int index = 0; index < arr.length; index++) {
			for (int temp = index + 1; temp < arr.length; temp++) {
				if (Integer.parseInt(arr[index]) > Integer.parseInt(arr[temp])) {
					String cur = arr[index];
					arr[index] = arr[temp];
					arr[temp] = cur;
				}
			}
		}
		StringBuffer res = new StringBuffer();
		for (String string : arr) {
			res.append(string + ",");
		}
		res.deleteCharAt(res.length() - 1);
		return res.toString();
	}

}
