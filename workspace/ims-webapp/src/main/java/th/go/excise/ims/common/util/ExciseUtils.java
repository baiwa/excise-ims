package th.go.excise.ims.common.util;

import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.temporal.ChronoField;
import java.util.regex.Pattern;

public class ExciseUtils {
	
	public static boolean isCentral(String officeCode) {
		return isValidOfficeCode(officeCode) && "00".equals(officeCode.substring(0, 2));
	}
	
	public static boolean isSector(String officeCode) {
		return isValidOfficeCode(officeCode) && Pattern.matches("([0-9]{1}[1-9]{1}0{4})", officeCode);
	}
	
	public static boolean isArea(String officeCode) {
		return isValidOfficeCode(officeCode) && Pattern.matches("([0-9]{1}[1-9]{1}[0-9]{1}[1-9]{1}0{2})", officeCode);
	}
	
	public static boolean isBranch(String officeCode) {
		return isValidOfficeCode(officeCode) && Pattern.matches("([0-9]{1}[1-9]{1}[0-9]{1}[1-9]{1}[0-9]{1}[1-9]{1})", officeCode);
	}
	
	public static String whereInLocalOfficeCode(String officeCode) {
		if (isValidOfficeCode(officeCode)) {
			if (isCentral(officeCode)) {
				officeCode = "%";
			} else if (isSector(officeCode)) {
				officeCode = officeCode.substring(0, 2) + "____";
			} else if (isArea(officeCode)) {
				officeCode = officeCode.substring(0, 4) + "__";
			}
		}
		return officeCode;
	}
	
	private static boolean isValidOfficeCode(String officeCode) {
		return officeCode != null && officeCode.length() == 6;
	}
	
	public static String getCurrentBudgetYear() {
		return getBudgetYearByLocalDate(LocalDate.now());
	}

	public static String getBudgetYearByLocalDate(LocalDate localDate) {
		int budgetYear = ThaiBuddhistDate.from(localDate).get(ChronoField.YEAR);
		if (localDate.getMonthValue() >= 10) {
			budgetYear++;
		}
		return String.valueOf(budgetYear);
	}
	
}
