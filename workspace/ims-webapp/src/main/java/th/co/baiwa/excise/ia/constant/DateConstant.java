package th.co.baiwa.excise.ia.constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class DateConstant {

	public static final Locale LOCAL_TH = new Locale("th", "TH");
	public static final String[] MONTH_SHOT_NAMES = { "ม.ค.", "ก.พ.", "มี.ค.", "เม.ย.", "พ.ค.", "มิ.ย.", "ก.ค.", "ส.ค.","ก.ย.", "ต.ค.", "พ.ย.", "ธ.ค." };
	public static final String YYYYMMDD = "yyyyMMdd";
	
	public static void main(String[] args) {
		List<String> monthList = startBackDate(new Date(), 6);
	}

	public static List<String> startBackDate(Date date, int backDate) {
		List<String> monthList = new ArrayList<>();
		Calendar calendar = Calendar.getInstance(LOCAL_TH);
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -(backDate));
		for (int i = 0; i < backDate; i++) {
			Date initTime = calendar.getTime();
			monthList.add(MONTH_SHOT_NAMES[initTime.getMonth()] + " " + checkYearThai(initTime));
			calendar.add(Calendar.MONTH, 1);
		}
		return monthList;
	}
	
	public static List<String> sortMonthShotList(List<String> monthNameList){
		List<Integer> data = new ArrayList<Integer>();
		Map<Integer, String> mapping = new HashMap<Integer, String>();
		for (String month : monthNameList) {
			String[] split = month.split(" ");
			String monthNumber = (Arrays.asList(MONTH_SHOT_NAMES).indexOf(split[0])+1)+"";
			if(monthNumber.length() == 1) {
				monthNumber = "0"+monthNumber;
			}
			Integer dataSet = Integer.parseInt("25"+split[1]+monthNumber);
			data.add(dataSet);
			mapping.put(dataSet, month);
		}
		List<String> monthListReturn = new ArrayList<String>();
		Collections.sort(data);
		for (Integer integer : data) {
			monthListReturn.add(mapping.get(integer));
			//System.out.println(mapping.get(integer));
		}
		return monthListReturn;
	}
	

	public static String checkYearThai(Date date) {
		String pattern = "yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date).substring(2);

	}
	
	public static Date StringtoDate(String txt, String pattern) throws ParseException {
		
		return StringtoDate(txt, pattern, LOCAL_TH);
	}
	
	public static String DateToString(Date date, String pattern) {
		return DateToString(date, pattern, LOCAL_TH);
	}

	
	public static Date StringtoDate(String txt, String pattern, Locale locale) throws ParseException {
		final SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.parse(txt);
	}

	
	
	public static String DateToString(Date date, String pattern, Locale locale) {
		SimpleDateFormat format = new SimpleDateFormat(pattern, locale);
		return format.format(date);
	}
}
