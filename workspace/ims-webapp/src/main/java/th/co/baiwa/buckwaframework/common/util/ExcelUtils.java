package th.co.baiwa.buckwaframework.common.util;

import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;

public abstract class ExcelUtils {
	
	private static final String defaultDatePattern = "dd/MM/yyyy HH:mm:ss";
	
	/**
	 * This method for the type of data in the cell, extracts the data and
	 * returns it as a string.
	 */
	// http://www.java-connect.com/apache-poi-tutorials/read-all-type-of-excel-cell-value-as-string-using-poi/
	public static String getCellValueAsString(Cell cell) {
		return getCellValueAsString(cell, defaultDatePattern);
	}
	
	public static String getCellValueAsString(Cell cell, String datePattern) {
		String strCellValue = null;
		
		if (cell != null) {
			switch (cell.getCellTypeEnum()) {
			case STRING:
				strCellValue = cell.getStringCellValue();
				break;
			case NUMERIC:
				if (DateUtil.isCellDateFormatted(cell)) {
					SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
					strCellValue = dateFormat.format(cell.getDateCellValue());
				} else {
					Double value = cell.getNumericCellValue();
					// Checking have decimal or not?
					if (value % 1 == 0) {
						// No Decimal
						Long longValue = value.longValue();
						strCellValue = longValue.toString();
					} else {
						// Decimal
						strCellValue = value.toString();
					}
				}
				break;
			case BOOLEAN:
				strCellValue = String.valueOf(cell.getBooleanCellValue());
				break;
			case BLANK:
				strCellValue = "";
				break;
			default:
				break;
			}
		}
		
		return strCellValue;
	}
	
}
