package th.co.baiwa.buckwaframework.common.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;

public class NumberUtils {
	private static NumberUtilsConfigurer NUMBER_UTILS_CONFIGURER;

	public static NumberUtilsConfigurer getNumberUtilsConfigurer() {
		return NumberUtils.NUMBER_UTILS_CONFIGURER;
	}

	public static void setNumberUtilsConfigurer(final NumberUtilsConfigurer numberUtilsConfigurer) {
		NumberUtils.NUMBER_UTILS_CONFIGURER = numberUtilsConfigurer;
	}

	public static BigDecimal toBigDecimal(final Object obj) {
		if (BeanUtils.isNull(obj)) {
			return null;
		}
		return new BigDecimal(NumberUtils.toNumber(obj).toString());
	}

	public static BigInteger toBigInteger(final Object obj) {
		if (BeanUtils.isNull(obj)) {
			return null;
		}
		return new BigInteger(NumberUtils.toNumber(obj).toString());
	}

	public static Boolean toBoolean(final Object obj) {
		if (BeanUtils.isNull(obj)) {
			return null;
		}
		return Boolean.parseBoolean(obj.toString());
	}

	public static Byte toByte(final Object obj) {
		if (BeanUtils.isNull(obj)) {
			return null;
		}
		return NumberUtils.toNumber(obj).byteValue();
	}

	public static Double toDouble(final Object obj) {
		if (BeanUtils.isNull(obj)) {
			return null;
		}
		return NumberUtils.toNumber(obj).doubleValue();
	}

	public static Float toFloat(final Object obj) {
		if (BeanUtils.isNull(obj)) {
			return null;
		}
		return NumberUtils.toNumber(obj).floatValue();
	}

	public static Integer toInteger(final Object obj) {
		if (BeanUtils.isNull(obj)) {
			return null;
		}
		return NumberUtils.toNumber(obj).intValue();
	}

	public static Long toLong(final Object obj) {
		if (BeanUtils.isNull(obj)) {
			return null;
		}
		return NumberUtils.toNumber(obj).longValue();
	}

	private static Number toNumber(final Object obj) {
		try {
			return NumberUtils.NUMBER_UTILS_CONFIGURER.getDefaultNumberFormat().parse(obj.toString());
		} catch (final ParseException e) {
			throw new IllegalArgumentException("Cannot parse text [" + obj.toString() + "] to number", e);
		}
	}

	public static Short toShort(final Object obj) {
		if (BeanUtils.isNull(obj)) {
			return null;
		}
		return NumberUtils.toNumber(obj).shortValue();
	}

	public static String toString(final Number number) {
		String result = null;
		if (!BeanUtils.isNull(number)) {
			final DecimalFormat df = new DecimalFormat();
			df.setGroupingSize(0);
			df.setMaximumFractionDigits(0);
			result = df.format(number);
		}
		return result;
	}

	public static String toString(final Number number, final int fraction) {
		String result = null;
		if (!BeanUtils.isNull(number)) {
			final DecimalFormat df = new DecimalFormat();
			df.setGroupingSize(0);
			df.setMaximumFractionDigits(fraction);
			df.setMinimumFractionDigits(fraction);
			result = df.format(number);
		}
		return result;
	}
	
	public static String toStringFormat(Long number,String format) {
		DecimalFormat formatter = new DecimalFormat(format);
		return formatter.format(number);
	}
	
	public static long stringToLong(String val) {
		BigDecimal strToLong = new BigDecimal(val);
		long returnValue = 0;
		try {
			returnValue = strToLong.longValue();
		} catch (Exception e) {

		}
		return returnValue;
	}
}
