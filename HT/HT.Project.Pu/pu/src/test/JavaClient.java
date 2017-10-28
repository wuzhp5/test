import nc.vo.pub.lang.UFDate;

public class JavaClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(new UFDate(toDateString(2017, 1, 32)));
		StringBuffer buffer = new StringBuffer();
	}

	private static String toDateString(int year, int month, int day) {
		String strYear = String.valueOf(year);
		for (int j = strYear.length(); j < 4; ++j)
			strYear = "0" + strYear;
		String strMonth = String.valueOf(month);
		if (strMonth.length() < 2)
			strMonth = "0" + strMonth;
		String strDay = String.valueOf(day);
		if (strDay.length() < 2)
			strDay = "0" + strDay;
		return strYear + "-" + strMonth + "-" + strDay;
	}
}
