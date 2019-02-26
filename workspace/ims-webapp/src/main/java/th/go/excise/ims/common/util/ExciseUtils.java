package th.go.excise.ims.common.util;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class ExciseUtils {
	
	public static boolean isCentral(String officeCode) {
		return "00".equals(officeCode.substring(0, 2)) && officeCode != null && officeCode.length() == 6;
	}
	
	public static boolean isSector(String officeCode) {
		return Pattern.matches("^.{2}0{4}$", officeCode);
	}
	
	public static boolean isArea(String officeCode) {
		return Pattern.matches(officeCode.substring(0, 2) + "([0-9]{1}[1-9]{1}|[1-9][0-9])00", officeCode);
	}
	
	public static boolean isBranch(String officeCode) {
		return Pattern.matches(officeCode.substring(0, 4) + "([0-9]{1}[1-9]{1}|[1-9][0-9])", officeCode);
	}
	
	public static String whereInLocalOfficeCode(String officeCode) {
		if (StringUtils.isNotBlank(officeCode) && officeCode.length() == 6) {
			if (isCentral(officeCode)) {
				officeCode = "%";
			} else if(isSector(officeCode)) {
				officeCode = officeCode.substring(0, 2) + "____";
			}else if(isArea(officeCode)) {
				officeCode = officeCode.substring(0, 4) + "__";
			}
		} 
		return officeCode;
	}
	
}
