package th.co.baiwa.excise.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

import th.co.baiwa.excise.ia.persistence.vo.Int069Vo;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class CheckPaymentExcelService {
	private CellStyle thStyle;
	private CellStyle cellCenter;
	private CellStyle cellRight;
	private CellStyle cellLeft;

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

		topCenter = workbook.createCellStyle();
		topCenter.setAlignment(HorizontalAlignment.CENTER);
		topCenter.setFont(fontHeader);

		topRight = workbook.createCellStyle();
		topRight.setAlignment(HorizontalAlignment.RIGHT);

		topLeft = workbook.createCellStyle();
		topLeft.setAlignment(HorizontalAlignment.LEFT);
		return workbook;
	}
	
	DecimalFormat formatter = new DecimalFormat("#,##0.00");
	
	public ByteArrayOutputStream exportInt069(List<Int069Vo> dataList) throws IOException {
		/* create spreadsheet */
		XSSFWorkbook workbook = setUpExcel();
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* Header */
		String[] tbTH1 = {"ลำดับที่","เอกสารอ้างอิง","","","รายการโอนเงิน","ประเภทเงิน","รหัสเงิน","กิจกรรม","งบ","หมวด","หมวดย่อย","รายการ","จำนวนเงิน","หมายเหตุ"};
		for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[cellNum]);
			cell.setCellStyle(thStyle);
		};
		String[] tbTH2 = {"ที่ กค","เลขที่","ว.ด.ป"};
		row = sheet.createRow(1);
		int cellNumtbTH2 = 1;
		for (int i = 0; i < tbTH2.length; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellValue(tbTH2[i]);
			cell.setCellStyle(thStyle);
			cellNumtbTH2++;
		};
		

		for (int i = 4; i < tbTH1.length; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(thStyle);
		};
		
		cell = row.createCell(0);
		cell.setCellStyle(thStyle);

		/* set sheet */
		// merge(firstRow, lastRow, firstCol, lastCol)
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 3));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
		for(int i= 4;i<=13;i++) {
			sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
		}
		
		
		// setColumnWidth
		for (int i = 1; i <= 13; i++) {
			if(i==1|| i==2 ||i==3) {
				sheet.setColumnWidth(i, 76 * 50);
			}else if (i == 10 || i==11 || i==13) {
				sheet.setColumnWidth(i, 76 * 150);
			}else {
				sheet.setColumnWidth(i, 76 * 80);
			}
		
		}
		

		/* Detail */
		rowNum = 2;
		cellNum = 0;
		int no = 1;
		for (Int069Vo data : dataList) {
			row = sheet.createRow(rowNum);
			// No.
			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			
			// MofNum
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(data.getMofNum())) {
				cell.setCellValue(data.getMofNum());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			// RefNum
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(data.getRefNum())) {
				cell.setCellValue(data.getRefNum());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			// getRefDateStr
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(data.getRefDateStr())) {
				cell.setCellValue(data.getRefDateStr());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			// getTransferList
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(data.getTransferList())) {
				cell.setCellValue(data.getTransferList());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellLeft);
			cellNum++;

			
			// getBudgetType
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(data.getBudgetType())) {
				cell.setCellValue(data.getBudgetType());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellLeft);
			cellNum++;
			
			// getBudgetCode
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(data.getBudgetCode())) {
				cell.setCellValue(data.getBudgetCode());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			// getActivities
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(data.getActivities())) {
				cell.setCellValue(data.getActivities());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellLeft);
			cellNum++;
			
			// getBudget
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(data.getBudget())) {
				cell.setCellValue(data.getBudget());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellLeft);
			cellNum++;
			
			// getCtgBudget
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(data.getCtgBudget())) {
				cell.setCellValue(data.getCtgBudget());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellLeft);
			cellNum++;
			
			// getSubCtgBudget
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(data.getSubCtgBudget())) {
				cell.setCellValue(data.getSubCtgBudget());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellLeft);
			cellNum++;
			
			// getDescriptionList
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(data.getDescriptionList())) {
				cell.setCellValue(data.getDescriptionList());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellLeft);
			cellNum++;
			
			// getAmount
			cell = row.createCell(cellNum);
			if(BeanUtils.isNotEmpty(data.getAmount())) {
				cell.setCellValue(formatter.format(data.getAmount()));
				cell.setCellStyle(cellRight);
			}else {
				cell.setCellValue("-");
				cell.setCellStyle(cellCenter);
			}
			
			cellNum++;
			
			// getNote
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(data.getNote())) {
				cell.setCellValue(data.getNote());
			}else {
				cell.setCellValue("");
			}
			cell.setCellStyle(cellLeft);
			cellNum++;
			
			no++;
			rowNum++;
			cellNum = 0;
		}

		/* EndDetail */

		/* set write */
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		workbook.write(outByteStream);

		return outByteStream;
	}
	
	
	
	
	
	
}
