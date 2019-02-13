package th.co.baiwa.buckwaframework.common.util;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class ConvertDateUtils {

	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String YYYYMM = "yyyyMM";
	public static final String MM_YYYY = "MM/yyyy";
	public static final String DD_MM_YYYY = "dd/MM/yyyy";
	public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

	public static final Locale LOCAL_TH = new Locale("th", "TH");
	public static final Locale LOCAL_EN = new Locale("en", "US");

	public static String formatDateToString(Date date, String patten, Locale locale) {

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

}
