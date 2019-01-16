package th.co.baiwa.buckwaframework.common.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;

public class DateUtils {
	private static DateUtilsConfigurer DATE_UTILS_CONFIGURER;
	public static final long MILLISECONDS_PER_HOUR = 60 * 60 * 1000;
	public static final long MILLISECONDS_PER_DAY = 24 * MILLISECONDS_PER_HOUR;
	public static final Map<String, DateFormat> DATE_FORMAT_MAP = new HashMap<String, DateFormat>();

	public static boolean after(final Date date1, final Date date2) {
		return date1.compareTo(date2) > 0;
	}

	public static boolean before(final Date date1, final Date date2) {
		return date1.compareTo(date2) < 0;
	}

	public static boolean between(final Date date, final Date start, final Date end) {
		if (DateUtils.before(date, start)) {
			return false;
		} else if (DateUtils.after(date, end)) {
			return false;
		} else {
			return true;
		}
	}

	public static int compare(final Date date1, final Date date2) {
		return date1.compareTo(date2);
	}

	public static int diffDate(final Date date1, final Date date2) {
		final long millisecond1 = date1.getTime();
		final long millisecond2 = date2.getTime();
		final long diffMilliseconds = millisecond2 - millisecond1;
		return (int) (diffMilliseconds / DateUtils.MILLISECONDS_PER_DAY);
	}

	public static BigDecimal diffTime(final String time1, String time2) {
		BigDecimal diffTime = BigDecimal.ZERO;
		try {
			long startTime = DateUtils.DATE_UTILS_CONFIGURER.getShortSimpleTimeFormat().parse(time1).getTime();
			long endTime = DateUtils.DATE_UTILS_CONFIGURER.getShortSimpleTimeFormat().parse(time2).getTime();

			diffTime = BigDecimal.valueOf((endTime-startTime)).divide(BigDecimal.valueOf(MILLISECONDS_PER_HOUR), 2, RoundingMode.HALF_UP);
		} catch (ParseException e) {
			throw new IllegalArgumentException("Cannot parse date string input [" + time1 + ","+ time2+"] by using short time format [" + DateUtils.DATE_UTILS_CONFIGURER.getShortTimeFormat() + "]");
		}
		return diffTime;
	}

	public static String formatShortDate(final Date date) {
		return DateUtils.DATE_UTILS_CONFIGURER.getShortSimpleDateFormat().format(date);
	}
	
	public static String formatDate(final Date date, String format) {
		DateFormat dateFormat = DATE_FORMAT_MAP.get(format);
		if(dateFormat == null) {
			dateFormat = new SimpleDateFormat(format, DateUtils.DATE_UTILS_CONFIGURER.getDefaultLocale());
		}
		return dateFormat.format(date);
	}

	public static DateUtilsConfigurer getDateUtilsConfigurer() {
		return DateUtils.DATE_UTILS_CONFIGURER;
	}

	public static Date getNow() {
		return Calendar.getInstance(DATE_UTILS_CONFIGURER.getDefaultLocale()).getTime();
	}

	public static long getCurrentTimeInMillis() {
		return Calendar.getInstance(DATE_UTILS_CONFIGURER.getDefaultLocale()).getTimeInMillis();
	}

	public static String getShortSystemStartDate() {
		return DateUtils.DATE_UTILS_CONFIGURER.getShortSystemStartDate();
	}
	
	public static String getShortSystemEndDate() {
		return DateUtils.DATE_UTILS_CONFIGURER.getShortSystemEndDate();
	}

	public static String getShortTodayDate() {
		return DateUtils.formatShortDate(DateUtils.getTodayDate());
	}

	public static String getShortTomorrowDate() {
		return DateUtils.formatShortDate(DateUtils.getTomorrowDate());
	}

	public static String getShortYesterdayDate() {
		return DateUtils.formatShortDate(DateUtils.getYesterdayDate());
	}

	public static Date getSystemStartDate() {
		return DateUtils.DATE_UTILS_CONFIGURER.getSystemStartDate();
	}
	
	public static Date getSystemEndDate() {
		return DateUtils.DATE_UTILS_CONFIGURER.getSystemEndDate();
	}

	public static Date getTodayDate() {
		final Calendar calendar = Calendar.getInstance(DATE_UTILS_CONFIGURER.getDefaultLocale());
		DateUtils.removeTime(calendar);
		return calendar.getTime();
	}

	public static Date getTomorrowDate() {
		final Calendar calendar = Calendar.getInstance(DATE_UTILS_CONFIGURER.getDefaultLocale());
		DateUtils.removeTime(calendar);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime();
	}

	public static Date getYesterdayDate() {
		final Calendar calendar = Calendar.getInstance(DATE_UTILS_CONFIGURER.getDefaultLocale());
		DateUtils.removeTime(calendar);
		calendar.add(Calendar.DAY_OF_MONTH, -1);
		return calendar.getTime();
	}

	public static Date parseShortDate(final String shortDate) {
		try {
			return DateUtils.DATE_UTILS_CONFIGURER.getShortSimpleDateFormat().parse(shortDate);
		} catch (final Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Cannot parse date string input [" + shortDate + "] by using short date format [" + DateUtils.DATE_UTILS_CONFIGURER.getShortDateFormat() + "] (" + e.getMessage() + ")", e);
		}
	}
	
	public static Date parseDate(final String shortDate, String format) {
		try {
			DateFormat dateFormat = DATE_FORMAT_MAP.get(format);
			if(dateFormat == null) {
				dateFormat = new SimpleDateFormat(format, DateUtils.DATE_UTILS_CONFIGURER.getDefaultLocale());
			}
			return dateFormat.parse(shortDate);
		} catch (final Exception e) {
			e.printStackTrace();
			throw new IllegalArgumentException("Cannot parse date string input [" + shortDate + "] by using short date format [" + DateUtils.DATE_UTILS_CONFIGURER.getShortDateFormat() + "] (" + e.getMessage() + ")", e);
		}
	}

	private static void removeTime(final Calendar calendar) {
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
	}

	public static void setDateUtilsConfigurer(final DateUtilsConfigurer dateUtilsConfigurer) {
		DateUtils.DATE_UTILS_CONFIGURER = dateUtilsConfigurer;
	}

	public static Date addDay(Date date, int day) {
		Calendar calendar = Calendar.getInstance(DATE_UTILS_CONFIGURER.getDefaultLocale());
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, day);
		return calendar.getTime();
	}

	public static Date toEndOfDay(final Date date) {
		final Calendar calendar = Calendar.getInstance(DATE_UTILS_CONFIGURER.getDefaultLocale());
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 23);
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		return calendar.getTime();
	}

	public static Date toStartOfDay(final Date date) {
		final Calendar calendar = Calendar.getInstance(DATE_UTILS_CONFIGURER.getDefaultLocale());
		calendar.setTime(date);
		DateUtils.removeTime(calendar);
		return calendar.getTime();
	}

	public static Date getStartDateOfMonth(Date current) {
		Calendar calendar = Calendar.getInstance(DATE_UTILS_CONFIGURER.getDefaultLocale());
		calendar.setTime(current);
		removeTime(calendar);
		calendar.set(Calendar.DATE, calendar.getActualMinimum(Calendar.DATE));
		return calendar.getTime();
	}

	public static Date getEndDateOfMonth(Date current) {
		Calendar calendar = Calendar.getInstance(DATE_UTILS_CONFIGURER.getDefaultLocale());
		calendar.setTime(current);
		removeTime(calendar);
		calendar.set(Calendar.DATE, calendar.getActualMaximum(Calendar.DATE));
		return calendar.getTime();
	}
	
	public static Date getStartDateOfYear(Date current) {
		Calendar calendar = Calendar.getInstance(DATE_UTILS_CONFIGURER.getDefaultLocale());
	
		calendar.set(Calendar.YEAR,DateUtils.getCurrentFullYear());
		calendar.set(Calendar.MONTH,0);
		calendar.set(Calendar.DAY_OF_MONTH,1);
		
		return calendar.getTime();
	}
	
	public static Date getEndDateOfYear(Date current) {
		Calendar calendar = Calendar.getInstance(DATE_UTILS_CONFIGURER.getDefaultLocale());
	
		calendar.set(Calendar.YEAR,DateUtils.getCurrentFullYear());
		calendar.set(Calendar.MONTH,11);
		calendar.set(Calendar.DAY_OF_MONTH,31);
		return calendar.getTime();
	}
	

	public static SimpleDateFormat getDateFormat() {
		return DateUtils.DATE_UTILS_CONFIGURER.getShortSimpleDateFormat();
	}

	public static SimpleDateFormat getTimeFormat() {
		return DateUtils.DATE_UTILS_CONFIGURER.getShortSimpleTimeFormat();
	}

	public static Date getFirstTimeOfYear(Integer year) {
		Calendar calendar = Calendar.getInstance(DATE_UTILS_CONFIGURER.getDefaultLocale());
		calendar.set(year,0,1,0,0,0);
		return calendar.getTime();
	}

	public static Date getLastTimeOfYear(Integer year) {
		Calendar calendar = Calendar.getInstance(DATE_UTILS_CONFIGURER.getDefaultLocale());
		calendar.set(year,11,31,23,59,59);
		return calendar.getTime();
	}

	public static Date getFirstTimeOfMonth(Integer year ,Integer month) {
		Calendar calendar = Calendar.getInstance(DATE_UTILS_CONFIGURER.getDefaultLocale());
		calendar.set(year,month,1,0,0,0);
		return calendar.getTime();
	}

	public static Date getLastTimeOfMonth(Integer year ,Integer month) {
		Calendar calendar = Calendar.getInstance(DATE_UTILS_CONFIGURER.getDefaultLocale());
		calendar.set(year,month,1,23,59,59);
		Integer days = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(year,month,days,23,59,59);
		return calendar.getTime();
	}

	public static Date getChangeNowDate(Integer year ,Integer month ,Integer date) {
		Calendar calendar = Calendar.getInstance(DATE_UTILS_CONFIGURER.getDefaultLocale());
		calendar.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DATE),0,0,0);
		calendar.add(Calendar.DATE, date);
		calendar.add(Calendar.MONTH, month);
		calendar.add(Calendar.YEAR, year);
		return calendar.getTime();
	}

	public static Integer getCurrentFullYear(){
		return Calendar.getInstance(DATE_UTILS_CONFIGURER.getDefaultLocale()).get(Calendar.YEAR);
	}

	public static Integer getCurrentShortYear(){
		return NumberUtils.toInteger(DateUtils.DATE_UTILS_CONFIGURER.getShortSimpleYearFormat().format(getNow()));
	}

	public static Integer getCurrentMonth(){
		return Calendar.getInstance(DATE_UTILS_CONFIGURER.getDefaultLocale()).get(Calendar.MONTH) + 1;
	}
	
	public static String formatDateToDDMMYYYY(Date date){
		if(date == null) return null;
		return DateFormatUtils.format(date, "dd/MM/yyyy");
		
	}
	
	public static Date parseDateToDDMMYYYY(String date){
		if(StringUtils.isEmpty(date)) return null;
		try {
			return org.apache.commons.lang3.time.DateUtils.parseDate(date,"dd/MM/yyyy");
		} catch (ParseException e) {
			//SKIP
		}
		return null;
	}

}
