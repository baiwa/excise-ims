package co.th.baiwa.buckwaframework.common.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;

public class AppDateUtils {

	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String DD_MM_YYYY = "dd/MM/yyyy";
	public static final String YYYY_MM_DD_DAT = "yyyy-MM-dd";
	public static final String YYYYMM = "yyyyMM";
	
	public static final Locale  LOCAL_TH = new Locale("th", "TH");
	public static final Locale  LOCAL_EN = new Locale("en", "US");
	
	public static String formatDateToString(Date date, String patten,Locale locale ) {

		String dateString = "";
		if (date != null) {
			dateString = DateFormatUtils.format(date, patten, locale);
		}
		return dateString;
	}

	public static Date parseStringToDate(String strDate, String patten, Locale locale) {
		Date dateString = null;
		try {
			if (StringUtils.isNotBlank(strDate)) {
				dateString = DateUtils.parseDate(strDate, locale, patten);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateString;
	}

//	public static long dateDifferentE(Date start, Date end) { // Require Local English
//		String strDataS = formatDateToString(start);
//		String strDataE = formatDateToString(end);
//
//		String[] splitDateStart = strDataS.split("/");
//		String[] splitDateEnd = strDataE.split("/");
//
//		LocalDate dateStart = LocalDate.of(Integer.parseInt(splitDateStart[2]), Integer.parseInt(splitDateStart[1]),
//				Integer.parseInt(splitDateStart[0]));
//		LocalDate dateEnd = LocalDate.of(Integer.parseInt(splitDateEnd[2]), Integer.parseInt(splitDateEnd[1]),
//				Integer.parseInt(splitDateEnd[0]));
//
//		long diffInDays = ChronoUnit.DAYS.between(dateStart, dateEnd);
//		return diffInDays;
//	}	
}

