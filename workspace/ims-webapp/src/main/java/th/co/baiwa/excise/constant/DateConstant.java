package th.co.baiwa.excise.constant;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.support.ApplicationCache;

@Service
public class DateConstant {

	private static Logger logger = LoggerFactory.getLogger(DateConstant.class);

	public static final Locale LOCAL_TH = new Locale("th", "TH");
	public static final Locale LOCAL_EN = new Locale("en", "US");
	// public static final String[] MONTH_SHOT_NAMES = { "ม.ค.", "ก.พ.", "มี.ค.",
	// "เม.ย.", "พ.ค.", "มิ.ย.", "ก.ค.", "ส.ค.","ก.ย.", "ต.ค.", "พ.ย.", "ธ.ค." };
	 public static final String[] MONTH_NAMES = { "มกราคม", "กุมภาพันธ์",
	 "มีนาคม", "เมษายน", "พฤษภาคม", "มิถุนายน", "กรกฎาคม", "สิงหาคม", "กันยายน",
	 "ตุลาคม", "พฤศจิกายน", "ธันวาคม" };
	public static final String YYYYMMDD = "yyyyMMdd";
	public static final String MM_YYYY = "MM/yyyy";

	public final static String DD_MM_YYYY = "dd/MM/yyyy";
	public final static String DD_MM_YYYY_HH_mm = "dd/MM/yyyy HH:mm";
	public final static String DD_MM_YYYY_HH_MM_SS = "dd/MM/yyyy HH:mm:ss";
	public final static String YYYY = "yyyy";
	public final static String YYYY_MM_DD = "yyyy-MM-dd";

	public static List<String> startBackDate(Date date, int backDate) {
		List<String> monthList = new ArrayList<>();
		Calendar calendar = Calendar.getInstance(LOCAL_TH);
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, -(backDate));
		for (int i = 0; i < backDate; i++) {
			Date initTime = calendar.getTime();
			monthList.add(monthShotName()[initTime.getMonth()] + " " + checkYearThai(initTime));
			calendar.add(Calendar.MONTH, 1);
		}
		return monthList;
	}

	public static List<String> sortMonthShotList(List<String> monthNameList) {
		List<Integer> data = new ArrayList<Integer>();
		Map<Integer, String> mapping = new HashMap<Integer, String>();
		for (String month : monthNameList) {
			String[] split = month.split(" ");
			String monthNumber = (Arrays.asList(monthShotName()).indexOf(split[0]) + 1) + "";
			if (monthNumber.length() == 1) {
				monthNumber = "0" + monthNumber;
			}
			Integer dataSet = Integer.parseInt("25" + split[1] + monthNumber);
			data.add(dataSet);
			mapping.put(dataSet, month);
		}
		List<String> monthListReturn = new ArrayList<String>();
		Collections.sort(data);
		for (Integer integer : data) {
			monthListReturn.add(mapping.get(integer));
			// System.out.println(mapping.get(integer));
		}
		return monthListReturn;
	}

	public static String[] monthShotName() {
		List<String> valueList = new ArrayList<String>();
		List<Lov> lovList = ApplicationCache.getListOfValueByValueType("MONTH_SHOT_NAMES");
		for (Lov lov : lovList) {
			valueList.add(lov.getValue1());
		}
		return valueList.toArray(new String[0]);
	}

	public static String[] monthName() {
		List<String> valueList = new ArrayList<String>();
		List<Lov> lovList = ApplicationCache.getListOfValueByValueType("MONTH_NAMES");
		for (Lov lov : lovList) {
			valueList.add(lov.getValue1());
		}

		return valueList.toArray(new String[0]);
	}

	public static String checkYearThai(Date date) {
		String pattern = "yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date).substring(2);

	}

	public static Date StringtoDate(String txt, String pattern) {

		try {
			return StringtoDate(txt, pattern, LOCAL_TH);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
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

	public static String convertDateToStrDDMMYYYY(Date date) {
		String dateString = "";
		try {
			if (date != null) {
				dateString = DateFormatUtils.format(date, DD_MM_YYYY, LOCAL_TH);
			}
		} catch (Exception e) {
			logger.error("Error convertDateToStrDDMMYYYY : ", e);
		}
		return dateString;
	}

	public static Date convertStrDDMMYYYYToDate(String ddMMyyyy) {
		Date dateString = null;
		try {
			if (StringUtils.isNotBlank(ddMMyyyy)) {
				dateString = DateUtils.parseDate(ddMMyyyy, LOCAL_TH, DD_MM_YYYY);
			}
		} catch (Exception e) {
			logger.error("Error convertStrDDMMYYYYToDate : ", e);
		}
		return dateString;
	}

	public static Date convertStrYYYYToDate(String yyyy) {
		Date date = null;
		try {
			if (StringUtils.isNotBlank(yyyy)) {
				date = DateUtils.parseDate(yyyy, LOCAL_TH, YYYY);
			}
		} catch (Exception e) {
			logger.error("Error convertStrYYYYToDate : ", e);
		}
		return date;
	}

	public static Date convertStringDDMMYYYYHHmmToDate(String DDMMYYYYHHmm) {
		Date date = null;
		try {
			if (DDMMYYYYHHmm != null) {
				date = DateUtils.parseDate(DDMMYYYYHHmm, LOCAL_TH, DD_MM_YYYY_HH_mm);
			}
		} catch (Exception e) {
			logger.error("Error convertDateToStrDDMMYYYY : ", e);
		}
		return date;
	}

	public static String convertDateToStrDDMMYYYYHHmm(Date date) {
		String dateString = "";
		try {
			if (date != null) {
				dateString = DateFormatUtils.format(date, DD_MM_YYYY_HH_mm, LOCAL_TH);
			}
		} catch (Exception e) {
			logger.error("Error convertDateToStrDDMMYYYY : ", e);
		}
		return dateString;
	}

	public static String convertStrDDMMYYYYToStrYYYYMMDD(String ddmmyyyy) {
		String dateString = "";
		try {
			if (StringUtils.isNotBlank(ddmmyyyy)) {
				Date date = DateUtils.parseDate(StringUtils.trim(ddmmyyyy), LOCAL_TH, DD_MM_YYYY);
				dateString = DateFormatUtils.format(date, YYYYMMDD, LOCAL_EN);
			}
		} catch (Exception e) {
			logger.error("Error convertStrDDMMYYYYToStrYYYYMMDD : ", e);
			e.printStackTrace();
		}
		return dateString;
	}

	/* super convert */
	public static String convertDateToStr(Date date, String pattern) {
		String dateString = "";
		try {
			if (date != null) {
				dateString = DateFormatUtils.format(date, pattern, LOCAL_TH);
			}
		} catch (Exception e) {
			logger.error("Error convertDateToStr : ", e);
		}
		return dateString;
	}

	public static String convertDateToStr(Date date, String pattern, Locale locale) {
		String dateString = "";
		try {
			if (date != null) {
				dateString = DateFormatUtils.format(date, pattern, locale);
			}
		} catch (Exception e) {
			logger.error("Error convertDateToStr : ", e);
		}
		return dateString;
	}

	public static Date convertStrToDate(String strDate, String patern) {
		Date date = null;
		try {
			if (StringUtils.isNotBlank(strDate)) {
				date = DateUtils.parseDate(StringUtils.trim(strDate), LOCAL_TH, patern);
			}
		} catch (Exception e) {
			logger.error("Error convertStrToDate : ", e);
			e.printStackTrace();
		}
		return date;
	}

	public static Date convertStrToDate(String strDate, String patern, Locale locale) {
		Date date = null;
		try {
			if (StringUtils.isNotBlank(strDate)) {
				date = DateUtils.parseDate(StringUtils.trim(strDate), locale, patern);
			}
		} catch (Exception e) {
			logger.error("Error convertStrToDate : ", e);
			e.printStackTrace();
		}
		return date;
	}

	/* end super convert */
	public static String convertStrToStrPreviousYear(String strDate) {
		String dateString = "";
		try {
			if (StringUtils.isNotBlank(strDate)) {
				Date date = DateUtils.parseDate(StringUtils.trim(strDate), LOCAL_TH, YYYY);
				Date previousYear = DateUtils.addYears(date, -1);
				dateString = DateFormatUtils.format(previousYear, YYYY, LOCAL_TH);
			}
		} catch (Exception e) {
			logger.error("Error convertStrToStrPreviousYear : ", e);
			e.printStackTrace();
		}
		return dateString;
	}

	public static LocalDate dateToLocalDadte(Date date) {
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Instant instant = date.toInstant();
		LocalDate localDate = instant.atZone(defaultZoneId).toLocalDate();
		return localDate;
	}
	public static String getMonthTextTH(int month) {
		String[] months = {
		         "มกราคม",
		         "กุมภาพันธ์",
		         "มีนาคม",
		         "เมษายน",
		         "พฤษภาคม",
		         "มิถุนายน",
		         "กรกฎาคม",
		         "สิงหาคม",
		         "กันยายน",
		         "ตุลาคม",
		         "พฤศจิกายน",
		         "ธันวาคม"};
		
		return months[month] ;//(BeanUtils.isEmpty(month))?months[month]:"";
	}
	
	
}
