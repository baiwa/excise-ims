package th.co.baiwa.excise.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
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

import th.co.baiwa.excise.ia.persistence.vo.Int0610Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int065Vo;
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
		String[] tbTH1 = { "ลำดับที่", "เอกสารอ้างอิง", "", "", "รายการโอนเงิน", "ประเภทเงิน", "รหัสเงิน", "กิจกรรม",
				"งบ", "หมวด", "หมวดย่อย", "รายการ", "จำนวนเงิน", "หมายเหตุ" };
		for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[cellNum]);
			cell.setCellStyle(thStyle);
		}
		;
		String[] tbTH2 = { "ที่ กค", "เลขที่", "ว.ด.ป" };
		row = sheet.createRow(1);
		int cellNumtbTH2 = 1;
		for (int i = 0; i < tbTH2.length; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellValue(tbTH2[i]);
			cell.setCellStyle(thStyle);
			cellNumtbTH2++;
		}
		;

		for (int i = 4; i < tbTH1.length; i++) {
			cell = row.createCell(i);
			cell.setCellStyle(thStyle);
		}
		;

		cell = row.createCell(0);
		cell.setCellStyle(thStyle);

		/* set sheet */
		// merge(firstRow, lastRow, firstCol, lastCol)
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 3));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
		for (int i = 4; i <= 13; i++) {
			sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
		}

		// setColumnWidth
		for (int i = 1; i <= 13; i++) {
			if (i == 1 || i == 2 || i == 3) {
				sheet.setColumnWidth(i, 76 * 50);
			} else if (i == 10 || i == 11 || i == 13) {
				sheet.setColumnWidth(i, 76 * 150);
			} else {
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
			cell.setCellValue(StringUtils.defaultIfBlank(data.getMofNum(), ""));
			cell.setCellStyle(cellCenter);
			cellNum++;

			// RefNum
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(data.getRefNum(), ""));
			cell.setCellStyle(cellCenter);
			cellNum++;

			// getRefDateStr
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(data.getRefDateStr(), ""));
			cell.setCellStyle(cellCenter);
			cellNum++;

			// getTransferList
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(data.getTransferList(), ""));
			cell.setCellStyle(cellLeft);
			cellNum++;

			// getBudgetType
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(data.getBudgetType(), ""));
			cell.setCellStyle(cellLeft);
			cellNum++;

			// getBudgetCode
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(data.getBudgetCode(), ""));
			cell.setCellStyle(cellCenter);
			cellNum++;

			// getActivities
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(data.getActivities(), ""));
			cell.setCellStyle(cellLeft);
			cellNum++;

			// getBudget
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(data.getBudget(), ""));
			cell.setCellStyle(cellLeft);
			cellNum++;

			// getCtgBudget
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(data.getCtgBudget(), ""));
			cell.setCellStyle(cellLeft);
			cellNum++;

			// getSubCtgBudget
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(data.getSubCtgBudget(), ""));
			cell.setCellStyle(cellLeft);
			cellNum++;

			// getDescriptionList
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(data.getDescriptionList(), ""));
			cell.setCellStyle(cellLeft);
			cellNum++;

			// getAmount
			cell = row.createCell(cellNum);
			if (BeanUtils.isNotEmpty(data.getAmount())) {
				cell.setCellValue(formatter.format(data.getAmount()));
			} else {
				cell.setCellValue("");
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getNote
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(data.getNote(), ""));
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

	public ByteArrayOutputStream exportInt0610(List<Int0610Vo> dataList) throws IOException {
		/* create spreadsheet */
		XSSFWorkbook workbook = setUpExcel();
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* Header */
		String[] tbTH1 = { "ลำดับที่", "เลขที่เอกสารอ้างอิง", "วันที่ขอเบิก", "ประเภทงบประมาณ", "กิจกรรม", "งบ", "หมวด",
				"หมวดย่อย", "รายการ", "จำนวนเงินขอเบิก", "ภาษีหัก ณ ที่จ่าย", "หักประกันสังคม", "อื่นๆ",
				"จำนวนเงินขอรับ", "เลขที่เอกสารขอเบิก", "เลขที่เอกสารขอจ่าย", "หมายเหตุ" };
		for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[cellNum]);
			cell.setCellStyle(thStyle);
		}
		;

		/* set sheet */

		// setColumnWidth
		for (int i = 1; i <= 18; i++) {
			if (i == 2 || i == 9 || i == 10 || i == 11 || i == 12 || i == 13) {
				sheet.setColumnWidth(i, 76 * 60);
			} else if (i == 16) {
				sheet.setColumnWidth(i, 76 * 250);
			} else {
				sheet.setColumnWidth(i, 76 * 100);
			}

		}

		/* Detail */
		rowNum = 1;
		cellNum = 0;
		int no = 1;
		for (Int0610Vo data : dataList) {
			row = sheet.createRow(rowNum);
			// No.
			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			// RefNum
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(data.getRefnum(), ""));
			cell.setCellStyle(cellCenter);
			cellNum++;

			// getWithdrawaldate
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(data.getWithdrawaldate(), ""));
			cell.setCellStyle(cellCenter);
			cellNum++;

			// getBudgettype
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(data.getBudgettype(), ""));
			cell.setCellStyle(cellCenter);
			cellNum++;

			// getActivities
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(data.getActivities(), ""));
			cell.setCellStyle(cellCenter);
			cellNum++;

			// getBudgetname
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(data.getBudgetname(), ""));
			cell.setCellStyle(cellCenter);
			cellNum++;

			// getCategoryname
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(data.getCategoryname(), ""));
			cell.setCellStyle(cellCenter);
			cellNum++;

			// getListname
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(data.getListname(), ""));
			cell.setCellStyle(cellCenter);
			cellNum++;

			/// getItemdesc
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(data.getItemdesc(), ""));
			cell.setCellStyle(cellCenter);
			cellNum++;

			// getWithdrawalamount
			cell = row.createCell(cellNum);
			if (StringUtils.isNotBlank(data.getWithdrawalamount())) {
				cell.setCellValue(formatter.format(new BigDecimal(data.getWithdrawalamount())));
			} else {
				cell.setCellValue("");
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getWithholdingtax
			cell = row.createCell(cellNum);
			if (StringUtils.isNotBlank(data.getWithholdingtax())) {
				cell.setCellValue(formatter.format(new BigDecimal(data.getWithholdingtax())));
			} else {
				cell.setCellValue("");
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getSocialsecurity
			cell = row.createCell(cellNum);
			if (StringUtils.isNotBlank(data.getSocialsecurity())) {
				cell.setCellValue(formatter.format(new BigDecimal(data.getSocialsecurity())));
			} else {
				cell.setCellValue("");
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getAnotheramount
			cell = row.createCell(cellNum);
			if (StringUtils.isNotBlank(data.getAnotheramount())) {
				cell.setCellValue(formatter.format(new BigDecimal(data.getAnotheramount())));
			} else {
				cell.setCellValue("");
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getReceivedamount
			cell = row.createCell(cellNum);
			if (StringUtils.isNotBlank(data.getReceivedamount())) {
				cell.setCellValue(formatter.format(new BigDecimal(data.getReceivedamount())));
			} else {
				cell.setCellValue("");
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			/// getWithdrawaldocnum
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(data.getWithdrawaldocnum(), ""));
			cell.setCellStyle(cellCenter);
			cellNum++;

			/// getPaymentdocnum
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(data.getPaymentdocnum(), ""));
			cell.setCellStyle(cellCenter);
			cellNum++;

			/// getNote
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(data.getNote(), ""));
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
	
	
	
	
	

	public ByteArrayOutputStream exportInt065(List<Int065Vo> list) throws IOException {
		/* create spreadsheet */
		XSSFWorkbook workbook = setUpExcel();
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* Header */
		String[] tbTH1 = {"ลำดับ", "วันที่สั่งจ่าย", "เช็คเล่มที่ ", "ชื่อธนาคาร", "จำนวนเงินสั่งจ่ายในเช็ค", "ประเภทงบประมาณ","รายการ","ผู้รับเงิน"};
		for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[cellNum]);
			cell.setCellStyle(thStyle);
		}
		;

		/* set sheet */

		// setColumnWidth
		for (int i = 1; i <= 7; i++) {
			if (i == 1 || i == 2 ) {
				sheet.setColumnWidth(i, 76 * 60);
			}  else {
				sheet.setColumnWidth(i, 76 * 100);
			}

		}

		/* Detail */
		rowNum = 1;
		cellNum = 0;
		int no = 1;
        for(Int065Vo item : list) {
        	row = sheet.createRow(rowNum);
			// No.
			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);		
			cell.setCellValue(StringUtils.defaultIfBlank(item.getPaymentDate(), ""));
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);	
			cell.setCellValue(StringUtils.defaultIfBlank(item.getRefPayment(), ""));
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);		
			cell.setCellValue(StringUtils.defaultIfBlank(item.getBankName(), ""));
			
			cell.setCellStyle(cellLeft);
			cellNum++;
			
			cell = row.createCell(cellNum);		
			cell.setCellValue(StringUtils.defaultIfBlank(formatter.format(new BigDecimal(item.getAmount())), ""));		
			cell.setCellStyle(cellRight);
			cellNum++;
			
			cell = row.createCell(cellNum);		
			cell.setCellValue(StringUtils.defaultIfBlank(item.getBudgetType(), ""));
			cell.setCellStyle(cellLeft);
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(item.getItemDesc(), ""));			
			cell.setCellStyle(cellLeft);
			cellNum++;
			
			cell = row.createCell(cellNum);		
			cell.setCellValue(StringUtils.defaultIfBlank(item.getPayee(), ""));		
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

	
	
	public ByteArrayOutputStream exportInt066(List<Int065Vo> list) throws IOException {
		/* create spreadsheet */
		XSSFWorkbook workbook = setUpExcel();
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* Header */
		String[] tbTH1 = {"ลำดับที่", "ผู้รับเงิน", "เลขที่บัญชี ", "ประเภทค่าใช้จ่าย", "รายการ","จำนวนเงิน","วันที่โอน"};
		for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[cellNum]);
			cell.setCellStyle(thStyle);
		}
		;

		/* set sheet */

		// setColumnWidth
		for (int i = 1; i <= 6; i++) {
			if ( i == 1 ) {
				sheet.setColumnWidth(i, 76 * 100);
			}else if(i == 2) {
				sheet.setColumnWidth(i, 76 * 60);
			}
			else {
				sheet.setColumnWidth(i, 76 * 100);
			}

		}

		/* Detail */
		rowNum = 1;
		cellNum = 0;
		int no = 1;
        for(Int065Vo item : list) {
        	row = sheet.createRow(rowNum);
			// No.
			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);		
			cell.setCellValue(StringUtils.defaultIfBlank(item.getPayee(), ""));		
			cell.setCellStyle(cellLeft);
			cellNum++;
			
		
			cell = row.createCell(cellNum);	
			cell.setCellValue(StringUtils.defaultIfBlank(item.getRefPayment(), ""));
			cell.setCellStyle(cellCenter);
			cellNum++;
			
		
		
			cell = row.createCell(cellNum);		
			cell.setCellValue(StringUtils.defaultIfBlank(item.getBudgetType(), ""));
			cell.setCellStyle(cellLeft);
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(StringUtils.defaultIfBlank(item.getItemDesc(), ""));			
			cell.setCellStyle(cellLeft);
			cellNum++;
			
			cell = row.createCell(cellNum);		
			cell.setCellValue(StringUtils.defaultIfBlank(formatter.format(new BigDecimal(item.getAmount())), ""));		
			cell.setCellStyle(cellRight);
			cellNum++;
			
	
			
			cell = row.createCell(cellNum);		
			cell.setCellValue(StringUtils.defaultIfBlank(item.getPaymentDate(), ""));
			cell.setCellStyle(cellCenter);
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
