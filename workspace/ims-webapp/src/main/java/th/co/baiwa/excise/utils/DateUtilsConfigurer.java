package th.co.baiwa.excise.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class DateUtilsConfigurer implements InitializingBean {
	private Locale defaultLocale;
	private String shortDateFormat;
	private String shortTimeFormat;
	private String shortYearFormat;
//	private SimpleDateFormat shortSimpleDateFormat;
//	private SimpleDateFormat shortSimpleTimeFormat;
//	private SimpleDateFormat shortSimpleYearFormat;
	private String shortSystemStartDate;
	private String shortSystemEndDate;
	private Date systemStartDate;
	private Date systemEndDate;

	public DateUtilsConfigurer() {
		defaultLocale = Locale.US;//new Locale("th", "TH");
		shortDateFormat = "dd/MM/yyyy";
		shortTimeFormat = "HH:mm";
		shortSystemStartDate = "01/01/1970";
		shortSystemEndDate = "31/12/2049";
		shortYearFormat = "yy";
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		SimpleDateFormat shortSimpleDateFormat = new SimpleDateFormat(shortDateFormat, defaultLocale);
		systemStartDate = shortSimpleDateFormat.parse(shortSystemStartDate);
		systemEndDate = shortSimpleDateFormat.parse(shortSystemEndDate);
//		shortSimpleTimeFormat = new SimpleDateFormat(shortTimeFormat, defaultLocale);
//		shortSimpleYearFormat = new SimpleDateFormat(shortYearFormat, defaultLocale);
	}

	public void setDefaultLocale(Locale defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	public Locale getDefaultLocale() {
		return defaultLocale;
	}

	public void setShortDateFormat(String shortDateFormat) {
		this.shortDateFormat = shortDateFormat;
	}

	public String getShortDateFormat() {
		return shortDateFormat;
	}

	public String getShortTimeFormat() {
		return shortTimeFormat;
	}

	public SimpleDateFormat getShortSimpleDateFormat() {
//		return shortSimpleDateFormat;
		return new SimpleDateFormat(shortDateFormat, defaultLocale);
	}

	public String getShortSystemStartDate() {
		return shortSystemStartDate;
	}
	
	public void setShortSystemStartDate(String shortSystemStartDate) {
		this.shortSystemStartDate = shortSystemStartDate;
	}

	public String getShortSystemEndDate() {
		return shortSystemEndDate;
	}

	public void setShortSystemEndDate(String shortSystemEndDate) {
		this.shortSystemEndDate = shortSystemEndDate;
	}
	
	public Date getSystemStartDate() {
		return systemStartDate;
	}

	public Date getSystemEndDate() {
		return systemEndDate;
	}

	public SimpleDateFormat getShortSimpleTimeFormat() {
//		return shortSimpleTimeFormat;
		return new SimpleDateFormat(shortTimeFormat, defaultLocale);
	}

	public SimpleDateFormat getShortSimpleYearFormat() {
//		return shortSimpleYearFormat;
		return new SimpleDateFormat(shortYearFormat, defaultLocale);
	}
}
