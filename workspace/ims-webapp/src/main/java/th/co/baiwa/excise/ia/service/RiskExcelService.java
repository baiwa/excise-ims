package th.co.baiwa.excise.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.entity.Condition;
import th.co.baiwa.buckwaframework.common.util.BeanUtils; 

@Service
public class RiskExcelService {
	// Set property Style Excel
	private CellStyle thStyle;
	private CellStyle cellCenter;
	private CellStyle cellRight;
	private CellStyle cellLeft;
	private CellStyle bgRed;
	private CellStyle bgYellow;
	private CellStyle bgGreen;
	private CellStyle topCenter;
	private CellStyle topRight;
	private CellStyle topLeft;
	private Font fontHeader;

	private XSSFWorkbook setUpExcel() {
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

		cellCenter = workbook.createCellStyle();
		cellCenter.setAlignment(HorizontalAlignment.CENTER);
		cellCenter.setBorderBottom(BorderStyle.THIN);
		cellCenter.setBorderLeft(BorderStyle.THIN);
		cellCenter.setBorderRight(BorderStyle.THIN);
		cellCenter.setBorderTop(BorderStyle.THIN);

		cellRight = workbook.createCellStyle();
		cellRight.setAlignment(HorizontalAlignment.RIGHT);
		cellRight.setBorderBottom(BorderStyle.THIN);
		cellRight.setBorderLeft(BorderStyle.THIN);
		cellRight.setBorderRight(BorderStyle.THIN);
		cellRight.setBorderTop(BorderStyle.THIN);

		cellLeft = workbook.createCellStyle();
		cellLeft.setAlignment(HorizontalAlignment.LEFT);
		cellLeft.setBorderBottom(BorderStyle.THIN);
		cellLeft.setBorderLeft(BorderStyle.THIN);
		cellLeft.setBorderRight(BorderStyle.THIN);
		cellLeft.setBorderTop(BorderStyle.THIN);

		bgRed = workbook.createCellStyle();
		bgRed.setAlignment(HorizontalAlignment.CENTER);
		bgRed.setBorderBottom(BorderStyle.THIN);
		bgRed.setBorderLeft(BorderStyle.THIN);
		bgRed.setBorderRight(BorderStyle.THIN);
		bgRed.setBorderTop(BorderStyle.THIN);
		bgRed.setFillForegroundColor(IndexedColors.RED.getIndex());
		bgRed.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		bgYellow = workbook.createCellStyle();
		bgYellow.setAlignment(HorizontalAlignment.CENTER);
		bgYellow.setBorderBottom(BorderStyle.THIN);
		bgYellow.setBorderLeft(BorderStyle.THIN);
		bgYellow.setBorderRight(BorderStyle.THIN);
		bgYellow.setBorderTop(BorderStyle.THIN);
		bgYellow.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		bgYellow.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		bgGreen = workbook.createCellStyle();
		bgGreen.setAlignment(HorizontalAlignment.CENTER);
		bgGreen.setBorderBottom(BorderStyle.THIN);
		bgGreen.setBorderLeft(BorderStyle.THIN);
		bgGreen.setBorderRight(BorderStyle.THIN);
		bgGreen.setBorderTop(BorderStyle.THIN);
		bgGreen.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		bgGreen.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		fontHeader = workbook.createFont();
		fontHeader.setBold(true);

		topCenter = workbook.createCellStyle();
		topCenter.setAlignment(HorizontalAlignment.CENTER);
		topCenter.setFont(fontHeader);

		topRight = workbook.createCellStyle();
		topRight.setAlignment(HorizontalAlignment.RIGHT);

		topLeft = workbook.createCellStyle();
		topLeft.setAlignment(HorizontalAlignment.LEFT);
		return workbook;
	}

	public ByteArrayOutputStream exportWsOtherDtl(String budgetYear , String riskName , String riskHeaderName , List<Condition> conditionList ,List<String> columnList, List<List<String>> detailList) throws IOException {
		XSSFWorkbook workbook = setUpExcel();
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		System.out.println("Creating excel");

		row = sheet.createRow(0);
		cell = row.createCell(cellNum);
		cell.setCellValue(riskHeaderName);
		cell.setCellStyle(topCenter);
		row = sheet.createRow(1);
		cell = row.createCell(cellNum);
		cell.setCellValue("ปีงบประมาณ  " + budgetYear);
		cell.setCellStyle(topCenter);
		row = sheet.createRow(2);
		cell = row.createCell(cellNum);
		cell.setCellValue("ปัจจัยเสี่ยง : " + riskName);
		cell.setCellStyle(topLeft);
		row = sheet.createRow(3);
		cell = row.createCell(cellNum);
		cell.setCellValue("เงื่อนไขความเสี่ยง :  ");
		cell.setCellStyle(topLeft);

		rowNum = 4;
		cellNum = 1;

		for (Condition con : conditionList) {
			row = sheet.createRow(rowNum);
			
			cell.setCellValue("\t\t" + riskName + "\tระหว่าง    " + con.getValue1() + "\tถึง\t"+ (BeanUtils.isEmpty(con.getValue2()) ? "-":con.getValue2())  +"\tระดับความเสี่ยง\t" + con.getConvertValue() + "\tคะแนนความเสี่ยง\t" + con.getValueRl());
			row.createCell(1).setCellValue(riskName);
			row.createCell(2).setCellValue("<>".equals(con.getCondition()) ? "ระหว่าง" : ">".equals(con.getCondition()) ? "มากกว่า" : "น้อยกว่า");
			row.createCell(3).setCellValue(con.getValue1()+"");
			row.createCell(4).setCellValue("ถึง");
			row.createCell(5).setCellValue(BeanUtils.isEmpty(con.getValue2()) ? "-" : con.getValue2()+"");
			row.createCell(6).setCellValue("ระดับความเสี่ยง");
			row.createCell(7).setCellValue(con.getConvertValue());
			row.createCell(8).setCellValue("คะแนนความเสี่ยง");
			row.createCell(9).setCellValue(con.getValueRl());
			
			cellNum++;
			rowNum++;
		}
		rowNum += 3;
		row = sheet.createRow(rowNum);
		
		for (cellNum = 0; cellNum < columnList.size(); cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(columnList.get(cellNum));
			cell.setCellStyle(thStyle);
		}
		

		cell = row.createCell(columnList.size());
		cell.setCellStyle(cellCenter);

		Row rowRisk = null;
		Cell cellRisk = null;
		int cellRiskNum = 4;
		rowRisk = sheet.createRow(10);
		cellRisk = rowRisk.createCell(cellRiskNum);
		cellRisk.setCellValue("RL");
		cellRisk.setCellStyle(thStyle);
		cellRiskNum++;
		cellRisk = rowRisk.createCell(cellRiskNum);
		cellRisk.setCellValue("แปลค่า");
		cellRisk.setCellStyle(thStyle);

		for (cellNum = 0; cellNum < columnList.size() - 1; cellNum++) {
			cell = rowRisk.createCell(cellNum);
			cell.setCellStyle(thStyle);
		}

		sheet.addMergedRegion(new CellRangeAddress(9, 9, 4, 5));
		for (int i = 0; i <= 3; i++) {
			sheet.addMergedRegion(new CellRangeAddress(9, 10, i, i));
		}
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 5));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 5));
		sheet.addMergedRegion(new CellRangeAddress(4, 4, 1, 5));
		sheet.addMergedRegion(new CellRangeAddress(5, 5, 1, 5));
		sheet.addMergedRegion(new CellRangeAddress(6, 6, 1, 5));
		rowNum +=2;
		for (int i = 0 ; i < detailList.size() ; i++) {
			List<String> detail = detailList.get(0);
			cell = row.createCell(1);
			cell.setCellValue(i+1);
			cell.setCellStyle(cellCenter);
			for (int j = 1 ; j < detail.size() ; j++) {
				cell = row.createCell(j);
				cell.setCellValue(detail.get(j));
				cell.setCellStyle(cellCenter);
			}
			rowNum++;
		}
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		workbook.write(outByteStream);
		return outByteStream;
	}
}
