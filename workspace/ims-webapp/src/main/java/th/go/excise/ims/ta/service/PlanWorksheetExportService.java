package th.go.excise.ims.ta.service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;

@Service
public class PlanWorksheetExportService {

	private static final Logger logger = LoggerFactory.getLogger(PlanWorksheetExportService.class);

	@Autowired
	private PlanWorksheetService planWorksheetService;

	public byte[] exportPlanWorksheet(PlanWorksheetVo formVo) {
		logger.info("exportPlanWorksheet officeCode={}, budgetYear={}, startDate={}, endDate={}, dateRange={}",
				formVo.getOfficeCode(), formVo.getBudgetYear(), formVo.getPlanNumber());

		// Plan worksheet Data for Export
		List<PlanWorksheetDatatableVo> planList = planWorksheetService.planDtlDatatableAll(formVo);

		// Label and Text
		String SHEET_NAME = "รายชื่อผู้ประกอบการ";

		// Create Workbook
		XSSFWorkbook workbook = new XSSFWorkbook();

		/* Cell Style */
		CellStyle cellHeader = ExcelUtils.createThColorStyle(workbook, new XSSFColor(Color.LIGHT_GRAY));

		Sheet sheet = workbook.createSheet(SHEET_NAME);
		Row row = null;
		Cell cell = null;
		int rowNum = 0;

		/*
		 * Column Header
		 */
		// Header Line 1
		row = sheet.createRow(rowNum);
		generateWorksheetHeader1(row, cell, cellHeader, formVo);
		rowNum++;
//		// Header Line 2
//		row = sheet.createRow(rowNum);
//		generateWorksheetHeader2(row, cell, cellHeader, formVo);
//		rowNum++;

		/*
		 * Details
		 */
		rowNum = generateWorksheetDetail(workbook, sheet, row, rowNum, cell, planList);
		setColumnWidth(sheet, 0);

		
		// Column Width
//		setColumnWidth(sheet, 0, formVo.getDateRange());
//		setMergeCell(sheet, formVo.getDateRange());
//		
		byte[] content = null;
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			workbook.write(outputStream);
			content = outputStream.toByteArray();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return content;
	}

	private void generateWorksheetHeader1(Row row, Cell cell, CellStyle cellStyle, PlanWorksheetVo formVo) {
		List<String> headerText1List = new ArrayList<>(
				Arrays.asList("ลำดับ", "ทะเบียนสรรพสามิต เดิม/ใหม่", "ชื่อผู้ประกอบการ", "ชื่อโรงอุตสาหกรรม/สถานบริการ",
						"ที่อยู่โรงอุตสาหกรรม/สถานบริการ", "ภาค", "พื้นที่", "พิกัด"));

		int cellNum = 0;
		for (String headerText : headerText1List) {
			cell = row.createCell(cellNum);
			cell.setCellValue(new XSSFRichTextString(headerText));
			cell.setCellStyle(cellStyle);
			cell.setCellValue(headerText);
			cellNum++;
		}
	}

	private int generateWorksheetDetail(XSSFWorkbook workbook, Sheet sheet, Row row, int rowNum, Cell cell,
			List<PlanWorksheetDatatableVo> planList) {
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);

		int cellNum = 0;
		int no = 1;
		for (PlanWorksheetDatatableVo taxVo : planList) {
			row = sheet.createRow(rowNum);
			cellNum = 0;
			// ลำดับ
			cell = row.createCell(cellNum++);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			// ทะเบียนสรรพสามิต เดิม/ใหม่
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getNewRegId());
			cell.setCellStyle(cellCenter);
			// ชื่อผู้ประกอบการ
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getCusFullname());
			cell.setCellStyle(cellLeft);
			// ชื่อโรงอุตสาหกรรม/สถานบริการ
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getFacFullname());
			cell.setCellStyle(cellLeft);
			// ที่อยู่โรงอุตสาหกรรม/สถานบริการ
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getFacAddress());
			cell.setCellStyle(cellLeft);
			// ภาค
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getSecDesc());
			cell.setCellStyle(cellLeft);
			// พื้นที่
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getAreaDesc());
			cell.setCellStyle(cellLeft);

			// พิกัด
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getDutyDesc());
			cell.setCellStyle(cellLeft);

			no++;
			rowNum++;
		}

		return rowNum;
	}
	
	private void setColumnWidth(Sheet sheet, int colIndex) {
		sheet.setColumnWidth(colIndex++, 7 * 256); // ลำดับ
		sheet.setColumnWidth(colIndex++, 28 * 256); // ทะเบียนสรรพสามิต เดิม/ใหม่
		sheet.setColumnWidth(colIndex++, 50 * 256); // ชื่อผู้ประกอบการ
		sheet.setColumnWidth(colIndex++, 50 * 256); // ชื่อโรงอุตสาหกรรม/สถานบริการ
		sheet.setColumnWidth(colIndex++, 50 * 256); // ที่อยู่โรงอุตสาหกรรม/สถานบริการ
		sheet.setColumnWidth(colIndex++, 10 * 256); // ภาค
		sheet.setColumnWidth(colIndex++, 15 * 256); // พื้นที่
		sheet.setColumnWidth(colIndex++, 18 * 256); // ทุนจดทะเบียน		
		sheet.setColumnWidth(colIndex++, 40 * 256); // พิกัด		
		sheet.setColumnWidth(colIndex++, 15 * 256); // พิกัดอื่นๆ
	}
	

}
