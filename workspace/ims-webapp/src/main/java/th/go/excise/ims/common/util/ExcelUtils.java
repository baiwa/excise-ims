package th.go.excise.ims.common.util;

import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public abstract class ExcelUtils {

//	private static CellStyle thStyle;
//	private static CellStyle tdStyle;
//	private static CellStyle cellCenter;
//	private static CellStyle cellRight;
//	private static CellStyle cellLeft;
//
//	private static CellStyle headerCenter;
//	private static CellStyle headerRight;
//	private static CellStyle headerLeft;
//	private static Font fontHeader;
//
//	private static XSSFCellStyle thCpColor;

	private static final String defaultDatePattern = "dd/MM/yyyy HH:mm:ss";

	/**
	 * This method for the type of data in the cell, extracts the data and returns
	 * it as a string.
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

//	public static CellStyle getThStyle() {
//		return thStyle;
//	}
//
//	public static CellStyle getTdStyle() {
//		return tdStyle;
//	}
//
//	public static CellStyle getHeaderCenter() {
//		return headerCenter;
//	}
//
//	public static CellStyle getCellCenter() {
//		return cellCenter;
//	}
//
//	public static CellStyle getCellRight() {
//		return cellRight;
//	}
//
//	public static CellStyle getCellLeft() {
//		return cellLeft;
//	}
//
//	public static XSSFCellStyle getThCpColor() {
//		return thCpColor;
//	}

	public static XSSFCellStyle createThCellStyle(XSSFWorkbook workbook) {
		XSSFCellStyle thStyle = workbook.createCellStyle();
		thStyle.setAlignment(HorizontalAlignment.CENTER);
		thStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		thStyle.setBorderBottom(BorderStyle.THIN);
		thStyle.setBorderLeft(BorderStyle.THIN);
		thStyle.setBorderRight(BorderStyle.THIN);
		thStyle.setBorderTop(BorderStyle.THIN);
		thStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		thStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		thStyle.setWrapText(true);
		return thStyle;
	}

	public static XSSFCellStyle createTdCellStyle(XSSFWorkbook workbook) {
		XSSFCellStyle tdStyle = workbook.createCellStyle();
		tdStyle = workbook.createCellStyle();
		tdStyle.setVerticalAlignment(VerticalAlignment.TOP);
		tdStyle.setBorderBottom(BorderStyle.THIN);
		tdStyle.setBorderLeft(BorderStyle.THIN);
		tdStyle.setBorderRight(BorderStyle.THIN);
		tdStyle.setBorderTop(BorderStyle.THIN);
		return tdStyle;
	}

	public static XSSFCellStyle createCenterCellStyle(XSSFWorkbook workbook) {
		XSSFCellStyle cellCenter = workbook.createCellStyle();
		cellCenter = workbook.createCellStyle();
		cellCenter.setAlignment(HorizontalAlignment.CENTER);
		cellCenter.setVerticalAlignment(VerticalAlignment.TOP);
		cellCenter.setBorderBottom(BorderStyle.THIN);
		cellCenter.setBorderLeft(BorderStyle.THIN);
		cellCenter.setBorderRight(BorderStyle.THIN);
		cellCenter.setBorderTop(BorderStyle.THIN);
		cellCenter.setWrapText(true);
		return cellCenter;
	}

	public static XSSFCellStyle createRightCellStyle(XSSFWorkbook workbook) {
		XSSFCellStyle cellRight = workbook.createCellStyle();
		cellRight = workbook.createCellStyle();
		cellRight.setAlignment(HorizontalAlignment.RIGHT);
		cellRight.setVerticalAlignment(VerticalAlignment.TOP);
		cellRight.setBorderBottom(BorderStyle.THIN);
		cellRight.setBorderLeft(BorderStyle.THIN);
		cellRight.setBorderRight(BorderStyle.THIN);
		cellRight.setBorderTop(BorderStyle.THIN);
		cellRight.setWrapText(true);
		return cellRight;
	}

	public static XSSFCellStyle createLeftCellStyle(XSSFWorkbook workbook) {
		XSSFCellStyle cellLeft = workbook.createCellStyle();
		cellLeft = workbook.createCellStyle();
		cellLeft.setAlignment(HorizontalAlignment.LEFT);
		cellLeft.setVerticalAlignment(VerticalAlignment.TOP);
		cellLeft.setBorderBottom(BorderStyle.THIN);
		cellLeft.setBorderLeft(BorderStyle.THIN);
		cellLeft.setBorderRight(BorderStyle.THIN);
		cellLeft.setBorderTop(BorderStyle.THIN);
		cellLeft.setWrapText(true);
		cellLeft.setWrapText(true);
		return cellLeft;
	}

//	public static XSSFWorkbook setUpExcel() {
//		XSSFWorkbook workbook = new XSSFWorkbook();

//		fontHeader = workbook.createFont();
//		fontHeader.setBold(true);
//
//		headerCenter = workbook.createCellStyle();
//		headerCenter.setAlignment(HorizontalAlignment.CENTER);
//		headerCenter.setFont(fontHeader);
//
//		headerRight = workbook.createCellStyle();
//		headerRight.setAlignment(HorizontalAlignment.RIGHT);
//
//		headerLeft = workbook.createCellStyle();
//		headerLeft.setAlignment(HorizontalAlignment.LEFT);
//
//		thCpColor = workbook.createCellStyle();
//		thCpColor.setFillForegroundColor(new XSSFColor(new java.awt.Color(24, 75, 125)));
//		thCpColor.setAlignment(HorizontalAlignment.CENTER);
//		thCpColor.setVerticalAlignment(VerticalAlignment.CENTER);
//		thCpColor.setBorderBottom(BorderStyle.THIN);
//		thCpColor.setBorderLeft(BorderStyle.THIN);
//		thCpColor.setBorderRight(BorderStyle.THIN);
//		thCpColor.setBorderTop(BorderStyle.THIN);
//		thCpColor.setFillPattern(FillPatternType.SOLID_FOREGROUND);
//		thCpColor.setWrapText(true);
//
//		return workbook;
//	}

}
