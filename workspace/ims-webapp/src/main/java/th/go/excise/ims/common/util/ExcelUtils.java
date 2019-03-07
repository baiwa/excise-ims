package th.go.excise.ims.common.util;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static CellStyle thStyle;
	private static CellStyle tdStyle;
	private static CellStyle cellCenter;
	private static CellStyle cellRight;
	private static CellStyle cellLeft;

	private static CellStyle headerCenter;
	private static CellStyle headerRight;
	private static CellStyle headerLeft;
	private static Font fontHeader;

	public static CellStyle getThStyle() {
		return thStyle;
	}

	public static CellStyle getTdStyle() {
		return tdStyle;
	}

	public static CellStyle getHeaderCenter() {
		return headerCenter;
	}

	public static XSSFWorkbook setUpExcel() {
		XSSFWorkbook workbook = new XSSFWorkbook();

		thStyle = workbook.createCellStyle();
		thStyle.setAlignment(HorizontalAlignment.CENTER);
		thStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		thStyle.setBorderBottom(BorderStyle.THIN);
		thStyle.setBorderLeft(BorderStyle.THIN);
		thStyle.setBorderRight(BorderStyle.THIN);
		thStyle.setBorderTop(BorderStyle.THIN);
		thStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		thStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		tdStyle = workbook.createCellStyle();
		tdStyle.setVerticalAlignment(VerticalAlignment.TOP);
		tdStyle.setBorderBottom(BorderStyle.THIN);
		tdStyle.setBorderLeft(BorderStyle.THIN);
		tdStyle.setBorderRight(BorderStyle.THIN);
		tdStyle.setBorderTop(BorderStyle.THIN);

		cellCenter = workbook.createCellStyle();
		cellCenter.setAlignment(HorizontalAlignment.CENTER);
		cellCenter.setVerticalAlignment(VerticalAlignment.TOP);
		cellCenter.setBorderBottom(BorderStyle.THIN);
		cellCenter.setBorderLeft(BorderStyle.THIN);
		cellCenter.setBorderRight(BorderStyle.THIN);
		cellCenter.setBorderTop(BorderStyle.THIN);

		cellRight = workbook.createCellStyle();
		cellRight.setAlignment(HorizontalAlignment.RIGHT);
		cellRight.setVerticalAlignment(VerticalAlignment.TOP);
		cellRight.setBorderBottom(BorderStyle.THIN);
		cellRight.setBorderLeft(BorderStyle.THIN);
		cellRight.setBorderRight(BorderStyle.THIN);
		cellRight.setBorderTop(BorderStyle.THIN);

		cellLeft = workbook.createCellStyle();
		cellLeft.setAlignment(HorizontalAlignment.LEFT);
		cellLeft.setVerticalAlignment(VerticalAlignment.TOP);
		cellLeft.setBorderBottom(BorderStyle.THIN);
		cellLeft.setBorderLeft(BorderStyle.THIN);
		cellLeft.setBorderRight(BorderStyle.THIN);
		cellLeft.setBorderTop(BorderStyle.THIN);
		cellLeft.setWrapText(true);

		fontHeader = workbook.createFont();
		fontHeader.setBold(true);

		headerCenter = workbook.createCellStyle();
		headerCenter.setAlignment(HorizontalAlignment.CENTER);
		headerCenter.setFont(fontHeader);

		headerRight = workbook.createCellStyle();
		headerRight.setAlignment(HorizontalAlignment.RIGHT);

		headerLeft = workbook.createCellStyle();
		headerLeft.setAlignment(HorizontalAlignment.LEFT);

		return workbook;
	}

}
