package th.co.baiwa.buckwaframework.common.util;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

public class ConvertDateUtils {

    public static final String YYYYMMDD = "yyyyMMdd";
    public static final String YYYYMM = "yyyyMM";
    public static final String YYYY = "yyyy";
    public static final String MM_YYYY = "MM/yyyy";
    public static final String DD_MM_YYYY = "dd/MM/yyyy";
    public static final String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";
    public static final String DD_MM_YYYY_HHMMSS = "dd/MM/yyyy HH:mm:ss";

    public static final Locale LOCAL_TH = new Locale("th", "TH");
    public static final Locale LOCAL_EN = new Locale("en", "US");

    public static String formatDateToString(Date date, String patten, Locale locale) {

        String dateString = "";
        if (date != null) {
            dateString = DateFormatUtils.format(date, patten, locale);
        }
        return dateString;
    }

    public static String formatDateToString(Date date, String patten) {

        String dateString = "";
        if (date != null) {
            dateString = DateFormatUtils.format(date, patten, LOCAL_TH);
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

    public static Date parseStringToDate(String strDate, String patten) {
        Date dateString = null;
        try {
            if (StringUtils.isNotBlank(strDate)) {
                dateString = DateUtils.parseDate(strDate, LOCAL_TH, patten);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return dateString;
    }
    
    public static LocalDate parseStringThaiDateToLocalDate(String strDate, String patten) {
    	if (StringUtils.isNotBlank(strDate)) {
    		LocalDate localDate = LocalDate.parse(strDate, DateTimeFormatter.ofPattern(patten, LOCAL_TH));
    		ThaiBuddhistDate thaiDate = ThaiBuddhistDate.of(localDate.getYear(), localDate.getMonthValue(), localDate.getDayOfMonth());
    		return LocalDate.from(thaiDate);
    	} else {
    		return LocalDate.now();
    	}
    }

}
