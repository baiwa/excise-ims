package th.co.baiwa.buckwaframework.common.util;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;

public class NumberUtils {
	
	public static BigDecimal toBigDecimal(String input) {
		if (StringUtils.isNotBlank(input)) {
			return new BigDecimal(input.replaceAll(",", ""));
		} else {
			return null;
		}
	}
	
	public static BigDecimal nullToZero(BigDecimal val){
		return (val != null) ? val : BigDecimal.ZERO;
	}
	
	public static BigDecimal ZeroToOne(BigDecimal val){
		return (val.compareTo(BigDecimal.ZERO) == 0) ? BigDecimal.ONE : val;
	}
	
}
