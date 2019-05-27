package th.go.excise.ims.ta.service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondMainHdr;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondSubNoAudit;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondMainDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondMainHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondSubNoAuditRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaWsReg4000Repository;
import th.go.excise.ims.ta.util.TaxAuditUtils;
import th.go.excise.ims.ta.vo.TaxOperatorDatatableVo;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.WorksheetDateRangeVo;

@Service
public class WorksheetExportService {
	
	private static final Logger logger = LoggerFactory.getLogger(WorksheetExportService.class);
	
	private static final int FLUSH_ROWS = 2000;
	private static final String NO_TAX_AMOUNT = "-";
	private static final DateTimeFormatter dateFormatter_MMM_yyyy = DateTimeFormatter.ofPattern("MMM yyyy", ConvertDateUtils.LOCAL_TH);
	private static final DateTimeFormatter dateFormatter_MMM_yy = DateTimeFormatter.ofPattern("MMM yy", ConvertDateUtils.LOCAL_TH);
	
	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;
	@Autowired
	private DraftWorksheetService draftWorksheetService;
	
	@Autowired
	private TaWorksheetCondMainHdrRepository taWorksheetCondMainHdrRepository;
	@Autowired
	private TaWorksheetCondMainDtlRepository taWorksheetCondMainDtlRepository;
	@Autowired
	private TaWorksheetCondSubNoAuditRepository taWorksheetCondSubNoAuditRepository;
	@Autowired
	private TaWorksheetHdrRepository taWorksheetHdrRepository;
	@Autowired
	private TaWorksheetDtlRepository taWorksheetDtlRepository;
	
	public byte[] exportPreviewWorksheet(TaxOperatorFormVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		logger.info("exportPreviewWorksheet officeCode={}, budgetYear={}, startDate={}, endDate={}, dateRange={}",
			formVo.getOfficeCode(), formVo.getBudgetYear(), formVo.getDateStart(), formVo.getDateEnd(), formVo.getDateRange());
		
		// Prepare Data for Export
		formVo.setOfficeCode(officeCode);
		formVo.setStart(0);
		formVo.setLength(taWsReg4000Repository.countByCriteria(formVo).intValue());
		List<TaxOperatorDetailVo> previewVoList = draftWorksheetService.prepareTaxOperatorDetailVoList(formVo);
		List<TaxOperatorDatatableVo> taxOperatorDatatableVoList = TaxAuditUtils.prepareTaxOperatorDatatable(previewVoList, formVo);
		
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
		// Header Line 2
		row = sheet.createRow(rowNum);
		generateWorksheetHeader2(row, cell, cellHeader, formVo);
		rowNum++;
		
		/*
		 * Details
		 */
		rowNum = generateWorksheetDetail(workbook, sheet, row, rowNum, cell, taxOperatorDatatableVoList);
		
		// Column Width
		setColumnWidth(sheet, 0, formVo.getDateRange());
		setMergeCell(sheet, formVo.getDateRange());
		
		byte[] content = null;
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			workbook.write(outputStream);
			content = outputStream.toByteArray();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return content;
	}
	
	public byte[] exportDraftWorksheet(TaxOperatorFormVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		logger.info("exportXlsxDraftWorksheet officeCode={}, budgetYear={}, draftNumber={}", officeCode, formVo.getBudgetYear(), formVo.getDraftNumber());
		
		// Prepare Data for Export
		TaWorksheetHdr worksheetHdr = taWorksheetHdrRepository.findByAnalysisNumber(formVo.getDraftNumber());
		formVo.setBudgetYear(worksheetHdr.getBudgetYear());
		formVo.setAnalysisNumber(formVo.getDraftNumber());
		
		TaWorksheetCondMainHdr worksheetCondMainHdr = taWorksheetCondMainHdrRepository.findByAnalysisNumber(formVo.getDraftNumber());
		formVo.setDateStart(convertToThaiDate(worksheetCondMainHdr.getYearMonthStart()));
		formVo.setDateEnd(convertToThaiDate(worksheetCondMainHdr.getYearMonthEnd()));
		formVo.setDateRange(worksheetCondMainHdr.getMonthNum());
		
		formVo.setOfficeCode(officeCode);
		formVo.setStart(0);
		formVo.setLength(taWorksheetDtlRepository.countByCriteria(formVo).intValue());
		
		List<TaxOperatorDetailVo> worksheetVoList = taWorksheetDtlRepository.findByCriteria(formVo);
		List<TaxOperatorDatatableVo> taxOperatorDatatableVoList = TaxAuditUtils.prepareTaxOperatorDatatable(worksheetVoList, formVo);
		
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
		// Header Line 2
		row = sheet.createRow(rowNum);
		generateWorksheetHeader2(row, cell, cellHeader, formVo);
		rowNum++;
		
		/*
		 * Details
		 */
		rowNum = generateWorksheetDetail(workbook, sheet, row, rowNum, cell, taxOperatorDatatableVoList);
		
		// Column Width
		setColumnWidth(sheet, 0, formVo.getDateRange());
		setMergeCell(sheet, formVo.getDateRange());
		
		byte[] content = null;
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			workbook.write(outputStream);
			content = outputStream.toByteArray();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return content;
	}
	
	public byte[] exportWorksheet(TaxOperatorFormVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		logger.info("exportWorksheet officeCode={}, analysisNumber={}", officeCode, formVo.getAnalysisNumber());
		
		// Prepare for Export
		TaWorksheetHdr worksheetHdr = taWorksheetHdrRepository.findByAnalysisNumber(formVo.getAnalysisNumber());
		formVo.setBudgetYear(worksheetHdr.getBudgetYear());
		
		TaWorksheetCondMainHdr worksheetCondMainHdr = taWorksheetCondMainHdrRepository.findByAnalysisNumber(formVo.getAnalysisNumber());
		formVo.setDateStart(convertToThaiDate(worksheetCondMainHdr.getYearMonthStart()));
		formVo.setDateEnd(convertToThaiDate(worksheetCondMainHdr.getYearMonthEnd()));
		formVo.setDateRange(worksheetCondMainHdr.getMonthNum());
		formVo.setOfficeCode(officeCode);
		formVo.setStart(0);
		
		List<TaxOperatorDetailVo> worksheetVoList = null;
		List<TaxOperatorDatatableVo> taxOperatorDatatableVoList = null;
		TaWorksheetCondSubNoAudit worksheetCondSubNoAudit = null;
		WorksheetDateRangeVo worksheetDateRangeVo = TaxAuditUtils.getWorksheetDateRangeVo(formVo.getDateStart(), formVo.getDateEnd(), formVo.getDateRange(), worksheetCondMainHdr.getCompType());
		
		// Label and Text
		List<String> sheetNameList = new ArrayList<>();
		sheetNameList.add("ข้อมูลตามเงื่อนไขคัดเลือก");
		sheetNameList.add("จดทะเบียนรายใหม่");
		
		// Create Workbook
		byte[] content = null;
		try (SXSSFWorkbook workbook = new SXSSFWorkbook(-1); // turn off auto-flushing and accumulate all rows in memory
			ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			
			/* Font */
		    Font headerFont = workbook.createFont();
		    headerFont.setFontHeightInPoints((short) 14);
		    headerFont.setFontName(ExcelUtils.FONT_NAME.TH_SARABUN_PSK);
		    headerFont.setBold(true);
		    
		    Font detailFont = workbook.createFont();
		    detailFont.setFontHeightInPoints((short) 14);
		    detailFont.setFontName(ExcelUtils.FONT_NAME.TH_SARABUN_PSK);
			
			/* Cell Style */
			CellStyle cellHeaderStyle = ExcelUtils.createThColorStyle(workbook, new XSSFColor(Color.LIGHT_GRAY));
			cellHeaderStyle.setFont(headerFont);
			CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
			cellCenter.setFont(detailFont);
			CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
			cellLeft.setFont(detailFont);
			CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);
			cellRight.setFont(detailFont);
			DecimalFormat decimalFormatTwoDigits = new DecimalFormat("#,##0.00");
			
			// Prepare Data for Export
			Sheet sheet = null;
			Row row = null;
			Cell cell = null;
			
			int sheetNameCount = 1;
			for (String sheetName : sheetNameList) {
				// Initial Data
				int rowNum = 0;
				int cellNum = 0;
				int condGroupCount = 0;
				int noTaxAuditYearNum = 0;
				
				if (sheetNameCount == 1) {
					formVo.setNewRegFlag(FLAG.N_FLAG);
					condGroupCount = 1 + taWorksheetCondMainDtlRepository.countByAnalysisNumber(formVo.getAnalysisNumber());
					worksheetCondSubNoAudit = taWorksheetCondSubNoAuditRepository.findByAnalysisNumber(formVo.getAnalysisNumber());
					noTaxAuditYearNum = worksheetCondSubNoAudit.getNoTaxAuditYearNum() != null ? worksheetCondSubNoAudit.getNoTaxAuditYearNum() : 3;
				} else if (sheetNameCount == 2) {
					formVo.setNewRegFlag(FLAG.Y_FLAG);
				}
				formVo.setLength(taWorksheetDtlRepository.countByCriteria(formVo).intValue());
				//formVo.setLength(10);
				
				// Prepare Data for Export
				worksheetVoList = taWorksheetDtlRepository.findByCriteria(formVo);
				taxOperatorDatatableVoList = TaxAuditUtils.prepareTaxOperatorDatatable(worksheetVoList, formVo);
				
				
				/*
				 * Sheet Name
				 */
				sheet = workbook.createSheet(sheetName);
				
				/*
				 * Column Header
				 */
				// Header Line 1
				row = sheet.createRow(rowNum);
				row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22));
				List<String> headerText1List = new ArrayList<>();
				headerText1List.add("ลำดับ");
				headerText1List.add("เลขทะเบียนสรรพสามิต");
				headerText1List.add("ชื่อผู้ประกอบการ");
				for (int i = 1; i <= condGroupCount; i++) {
					if (i == 1) {
						headerText1List.add("กลุ่มเงื่อนไข");
					} else {
						headerText1List.add("");
					}
				}
				headerText1List.add("การตรวจสอบภาษีย้อนหลัง 3 ปี");
				headerText1List.add("");
				headerText1List.add("");
				headerText1List.add("เปลี่ยนแปลง (ร้อยละ)");
				headerText1List.add("จำนวนเดือน\nไม่ชำระภาษี");
				headerText1List.add("ยอดชำระภาษี");
				headerText1List.add("");
				headerText1List.add("ชื่อโรงอุตสาหกรรม/สถานบริการ");
				headerText1List.add("ที่อยู่โรงอุตสาหกรรม/สถานบริการ");
				headerText1List.add("ภาค");
				headerText1List.add("พื้นที่");
				headerText1List.add("ทุนจดทะเบียน");
				headerText1List.add("วันที่จดทะเบียน");
				headerText1List.add("พิกัด");
				int monthNum = formVo.getDateRange() / 2;
				for (int i = 0; i < formVo.getDateRange(); i++) {
					if (i == 0) {
						headerText1List.add("การชำระภาษี " + String.valueOf(monthNum) + " เดือนแรก");
					} else if (i == (monthNum)) {
						headerText1List.add("การชำระภาษี " + String.valueOf(monthNum) + " เดือนหลัง");
					} else {
						headerText1List.add("");
					}
				}
				
				cellNum = 0;
				for (String headerText : headerText1List) {
					cell = row.createCell(cellNum);
					cell.setCellValue(headerText);
					cell.setCellStyle(cellHeaderStyle);
					cellNum++;
				}
				rowNum++;
				
				// Header Line 2
				row = sheet.createRow(rowNum);
				if (sheetNameCount == 1) {
					row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 3));
				} else {
					row.setHeight((short) (ExcelUtils.COLUMN_HEIGHT_UNIT * 22 * 1));
				}
				List<String> headerText2List = new ArrayList<>();
				headerText2List.add("");
				headerText2List.add("");
				headerText2List.add("");
				for (int i = 1; i <= condGroupCount; i++) {
					if (i == 1) {
						headerText2List.add("เงื่อนไขที่ 1\nไม่ถูกตรวจภาษี\nย้อนหลัง " + noTaxAuditYearNum + " ปี");
					} else {
						headerText2List.add("เงื่อนไขที่ " + i);
					}
				}
				headerText2List.add(String.valueOf(Integer.parseInt(formVo.getBudgetYear()) - 3));
				headerText2List.add(String.valueOf(Integer.parseInt(formVo.getBudgetYear()) - 2));
				headerText2List.add(String.valueOf(Integer.parseInt(formVo.getBudgetYear()) - 1));
				headerText2List.add("");
				headerText2List.add("");
				headerText2List.add(generateDateRangeString(worksheetDateRangeVo.getSubLocalDateG1List()));
				headerText2List.add(generateDateRangeString(worksheetDateRangeVo.getSubLocalDateG2List()));
				headerText2List.add("");
				headerText2List.add("");
				headerText2List.add("");
				headerText2List.add("");
				headerText2List.add("");
				headerText2List.add("");
				headerText2List.add("");
				int startTaxAmtIndex = headerText2List.size();
				// Date G1
				headerText2List.addAll(generateDateString(worksheetDateRangeVo.getSubLocalDateG1List()));
				// Date G2
				headerText2List.addAll(generateDateString(worksheetDateRangeVo.getSubLocalDateG2List()));
				
				cellNum = 0;
				for (String headerText : headerText2List) {
					cell = row.createCell(cellNum);
					cell.setCellValue(headerText);
					cell.setCellStyle(cellHeaderStyle);
					cellNum++;
				}
				rowNum++;
				
				/*
				 * Details
				 */
				int no = 1;
				Method method = null;
				String condGroupFlag = null;
				for (TaxOperatorDatatableVo taxVo : taxOperatorDatatableVoList) {
					row = sheet.createRow(rowNum);
					cellNum = 0;
					// ลำดับ
					cell = row.createCell(cellNum++);
					cell.setCellValue(no);
					cell.setCellStyle(cellLeft);
					// เลขทะเบียนสรรพสามิต
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxVo.getNewRegId());
					cell.setCellStyle(cellLeft);
					// ชื่อผู้ประกอบการ
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxVo.getCusFullname());
					cell.setCellStyle(cellLeft);
					// กลุ่มเงื่อนไข
					for (int i = 1; i <= condGroupCount; i++) {
						cell = row.createCell(cellNum++);
						try {
							method = TaxOperatorDatatableVo.class.getDeclaredMethod("getCondG" + i);
							condGroupFlag = (String) method.invoke(taxVo);
							if (FLAG.Y_FLAG.equals(condGroupFlag)) {
								cell.setCellValue("X");
							} else {
								cell.setCellValue("");
							}
						} catch (Exception e) {
							logger.error(e.getMessage(), e);
						}
						cell.setCellStyle(cellCenter);
					}
					// การตรวจสอบภาษีย้อนหลัง 3 ปี
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxVo.getTaxAuditLast3());
					cell.setCellStyle(cellLeft);
					// การตรวจสอบภาษีย้อนหลัง 2 ปี
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxVo.getTaxAuditLast2());
					cell.setCellStyle(cellLeft);
					// การตรวจสอบภาษีย้อนหลัง 1 ปี
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxVo.getTaxAuditLast1());
					cell.setCellStyle(cellLeft);
					// เปลี่ยนแปลง (ร้อยละ)
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxAmtFormat(taxVo.getTaxAmtChnPnt(), decimalFormatTwoDigits));
					cell.setCellStyle(cellRight);
					// จำนวนเดือนไม่ชำระภาษี
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxVo.getNotPayTaxMonthNo());
					cell.setCellStyle(cellRight);
					// ยอดชำระภาษี วิเคราะห์
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxAmtFormat(taxVo.getSumTaxAmtG1(), decimalFormatTwoDigits));
					cell.setCellStyle(cellRight);
					// ยอดชำระภาษี เปรียบเทียบ
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxAmtFormat(taxVo.getSumTaxAmtG2(), decimalFormatTwoDigits));
					cell.setCellStyle(cellRight);
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
					// ทุนจดทะเบียน
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxAmtFormat(taxVo.getRegCapital(), decimalFormatTwoDigits));
					cell.setCellStyle(cellRight);
					// วันที่จดทะเบียน
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxVo.getRegDate());
					cell.setCellStyle(cellLeft);
					// พิกัด
					cell = row.createCell(cellNum++);
					cell.setCellValue(taxVo.getDutyName());
					cell.setCellStyle(cellLeft);
					// การชำระภาษี X เดือนแรก และ X เดือนหลัง
					for (String taxAmt : taxVo.getTaxAmtList()) {
						cell = row.createCell(cellNum++);
						cell.setCellValue(taxAmtFormat(taxAmt, decimalFormatTwoDigits));
						cell.setCellStyle(cellRight);
					}
					
					if (rowNum % FLUSH_ROWS == 0) {
						((SXSSFSheet) sheet).flushRows(FLUSH_ROWS); // retain ${flushRows} last rows and flush all others
						logger.debug("Writed {} row(s)", rowNum);
					}
					
					no++;
					rowNum++;
				}
				
				// Column Width
				int colIndex = 0;
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 7);  // ลำดับ
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 28); // เลขทะเบียนสรรพสามิต
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 50); // ชื่อผู้ประกอบการ
				for (int i = 1; i <= condGroupCount; i++) {
					sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // กลุ่มเงื่อนไข
				}
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // การตรวจสอบภาษีย้อนหลัง 3 ปี
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // การตรวจสอบภาษีย้อนหลัง 2 ปี
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // การตรวจสอบภาษีย้อนหลัง 1 ปี
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 20); // เปลี่ยนแปลง (ร้อยละ)
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 20); // จำนวนเดือนไม่ชำระภาษี
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 16); // ยอดชำระภาษี วิเคราะห์
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 16); // ยอดชำระภาษี เปรียบเทียบ
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 50); // ชื่อโรงอุตสาหกรรม/สถานบริการ
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 50); // ที่อยู่โรงอุตสาหกรรม/สถานบริการ
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 10); // ภาค
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // พื้นที่
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 18); // ทุนจดทะเบียน
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // วันที่จดทะเบียน
				sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 40); // พิกัด
				for (int i = 0; i < formVo.getDateRange(); i++) {
					sheet.setColumnWidth(colIndex++, ExcelUtils.COLUMN_WIDTH_UNIT * 15); // การชำระภาษี X เดือนแรก และ X เดือนหลัง
				}
				
				// Merge Column
				int mergeCellIndex = 0;
				for (String headerText : headerText2List) {
					if (StringUtils.isEmpty(headerText)) {
						sheet.addMergedRegion(new CellRangeAddress(0, 1, mergeCellIndex, mergeCellIndex));
					}
					mergeCellIndex++;
				}
				int afterCondGroupIndex = 3;
				if (sheetNameCount == 1) {
					afterCondGroupIndex += condGroupCount - 1;
					sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, afterCondGroupIndex)); // กลุ่มเงื่อนไข
				} else {
					afterCondGroupIndex--;
				}
				sheet.addMergedRegion(new CellRangeAddress(0, 0, afterCondGroupIndex + 1, afterCondGroupIndex + 3)); // การตรวจสอบภาษีย้อนหลัง 3 ปี
				sheet.addMergedRegion(new CellRangeAddress(0, 0, afterCondGroupIndex + 6, afterCondGroupIndex + 7)); // การชำระภาษีในสภาวะปกติ
				int halfDataRange = formVo.getDateRange() / 2;
				sheet.addMergedRegion(new CellRangeAddress(0, 0, startTaxAmtIndex, startTaxAmtIndex + halfDataRange - 1));
				sheet.addMergedRegion(new CellRangeAddress(0, 0, startTaxAmtIndex + halfDataRange, startTaxAmtIndex + formVo.getDateRange() - 1));
				
				sheetNameCount++;
			}
			
			workbook.write(out);
			content = out.toByteArray();
			
			// dispose of temporary files backing this workbook on disk
			workbook.dispose();
			//logger.debug("Writed {} row(s)", rowNum);
			
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		
		return content;
	}
	
	public byte[] exportCondSubWorksheet(TaxOperatorFormVo formVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		logger.info("exportCondSubWorksheet officeCode={}, budgetYear={}, draftNumber={}", officeCode, formVo.getBudgetYear(), formVo.getDraftNumber());
		
		// Prepare Data for Export
		TaWorksheetHdr worksheetHdr = taWorksheetHdrRepository.findByAnalysisNumber(formVo.getAnalysisNumber());
		formVo.setBudgetYear(worksheetHdr.getBudgetYear());
		
		TaWorksheetCondMainHdr worksheetCondMainHdr = taWorksheetCondMainHdrRepository.findByAnalysisNumber(formVo.getAnalysisNumber());
		formVo.setDateStart(convertToThaiDate(worksheetCondMainHdr.getYearMonthStart()));
		formVo.setDateEnd(convertToThaiDate(worksheetCondMainHdr.getYearMonthEnd()));
		formVo.setDateRange(worksheetCondMainHdr.getMonthNum());
		
		formVo.setOfficeCode(officeCode);
		formVo.setStart(0);
		formVo.setLength(taWorksheetDtlRepository.countByCriteria(formVo).intValue());
		
		List<TaxOperatorDetailVo> worksheetVoList = taWorksheetDtlRepository.findByCriteria(formVo);
		List<TaxOperatorDatatableVo> taxOperatorDatatableVoList = TaxAuditUtils.prepareTaxOperatorDatatable(worksheetVoList, formVo);
		
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
		// Header Line 2
		row = sheet.createRow(rowNum);
		generateWorksheetHeader2(row, cell, cellHeader, formVo);
		rowNum++;
		
		/*
		 * Details
		 */
		rowNum = generateWorksheetDetail(workbook, sheet, row, rowNum, cell, taxOperatorDatatableVoList);
		
		// Column Width
		setColumnWidth(sheet, 0, formVo.getDateRange());
		setMergeCell(sheet, formVo.getDateRange());
		
		byte[] content = null;
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			workbook.write(outputStream);
			content = outputStream.toByteArray();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return content;
	}
	
	private String convertToThaiDate(String date) {
		return ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(date, ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH);
	}
	
	private void generateWorksheetHeader1(Row row, Cell cell, CellStyle cellStyle, TaxOperatorFormVo formVo) {
		List<String> headerText1List = new ArrayList<>(Arrays.asList(
			"ลำดับ",
			"ทะเบียนสรรพสามิต เดิม/ใหม่",
			"ชื่อผู้ประกอบการ",
			"ชื่อโรงอุตสาหกรรม/สถานบริการ",
			"ที่อยู่โรงอุตสาหกรรม/สถานบริการ",
			"ภาค",
			"พื้นที่",
			"ทุนจดทะเบียน",
			"การชำระภาษีในสภาวะปกติ",
			"",
			"เปลี่ยนแปลง (ร้อยละ)",
			"เปอร์เซ็นต์ส่วนเบี่ยงเบนมาตรฐาน",
			"ชำระภาษี (เดือน)",
			"การตรวจสอบภาษีย้อนหลัง",
			"",
			"",
			"พิกัด",
			"เลขทะเบียนสรรพสามิตเก่า",
			"สถานะ/วันที่",
			"ค่าเฉลี่ยภาษี",
			"ค่าร้อยละสูงสุด",
			"ค่าร้อยละต่ำสุด"
		));
		int monthNum = formVo.getDateRange() / 2;
		for (int i = 0; i < formVo.getDateRange(); i++) {
			if (i == 0) {
				headerText1List.add("การชำระภาษี " + String.valueOf(monthNum) + " เดือนแรก");
			} else if (i == (monthNum)) {
				headerText1List.add("การชำระภาษี " + String.valueOf(monthNum) + " เดือนหลัง");
			} else {
				headerText1List.add("");
			}
		}
//		headerText1List.add("พิกัดอื่นๆ");
//		headerText1List.add("กลุ่มของการชำระภาษี");
//		headerText1List.add("ขนาดทุนจดทะเบียน");
//		headerText1List.add("ระดับความเสี่ยง");
//		headerText1List.add("ผู้ประกอบการที่ไม่มีการตรวจสอบภาษีในระยะเวลาที่กำหนด");
		
		int cellNum = 0;
		for (String headerText : headerText1List) {
			cell = row.createCell(cellNum);
			cell.setCellValue(new XSSFRichTextString(headerText));
			cell.setCellStyle(cellStyle);
			cell.setCellValue(headerText);
			cellNum++;
		}
	}
	
	private void generateWorksheetHeader2(Row row, Cell cell, CellStyle cellStyle, TaxOperatorFormVo formVo) {
		List<String> headerText2List = new ArrayList<>(Arrays.asList(
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			String.valueOf(formVo.getDateRange() / 2) + " เดือนแรก",
			String.valueOf(formVo.getDateRange() / 2) + " เดือนหลัง",
			"",
			"",
			"",
			String.valueOf(Integer.parseInt(formVo.getBudgetYear()) - 3),
			String.valueOf(Integer.parseInt(formVo.getBudgetYear()) - 2),
			String.valueOf(Integer.parseInt(formVo.getBudgetYear()) - 1),
			"",
			"",
			"",
			"",
			"",
			""
		));
		headerText2List.addAll(generateDateString(formVo.getDateStart(), formVo.getDateRange()));
//		headerText2List.add("");
	
		int cellNum = 0;
		for (String headerText : headerText2List) {
			cell = row.createCell(cellNum);
			cell.setCellValue(new XSSFRichTextString(headerText));
			cell.setCellStyle(cellStyle);
			cell.setCellValue(headerText);
			cellNum++;
		}
	}
	
	private List<String> generateDateString(String dateStart, int dateRange) {
		int prolepticYear = Integer.parseInt(dateStart.substring(3, 7));
		int month = Integer.parseInt(dateStart.substring(0, 2));
		ThaiBuddhistDate initThaiDate = ThaiBuddhistDate.of(prolepticYear, month, 1);
		ThaiBuddhistDate thaiDate = null;
		
		List<String> thaiDateList = new ArrayList<>();
		for (int i = 0; i < dateRange; i++) {
			thaiDate = initThaiDate.plus(i, ChronoUnit.MONTHS);
			thaiDateList.add(thaiDate.format(dateFormatter_MMM_yyyy));
		}
		return thaiDateList;
	}
	
	private List<String> generateDateString(List<LocalDate> localDateList) {
		List<String> thaiDateList = new ArrayList<>();
		for (LocalDate localDate : localDateList) {
			thaiDateList.add(ThaiBuddhistDate.from(localDate).format(dateFormatter_MMM_yyyy));
		}
		return thaiDateList;
	}
	
	private String generateDateRangeString(List<LocalDate> localDateList) {
		String text = null;
		if (localDateList != null && localDateList.size() > 0) {
			text = ThaiBuddhistDate.from(localDateList.get(0)).format(dateFormatter_MMM_yy);
			text += " - ";
			text += ThaiBuddhistDate.from(localDateList.get(localDateList.size() - 1)).format(dateFormatter_MMM_yy);
		} else {
			text = "";
		}
		return text;
	}
	
	private int generateWorksheetDetail(XSSFWorkbook workbook, Sheet sheet, Row row, int rowNum, Cell cell, List<TaxOperatorDatatableVo> taxOperatorDatatableVoList) {
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);
		DecimalFormat decimalFormatTwoDigits = new DecimalFormat("#,##0.00");
		
		int cellNum = 0;
		int no = 1;
		for (TaxOperatorDatatableVo taxVo : taxOperatorDatatableVoList) {
			row = sheet.createRow(rowNum);
			cellNum = 0;
			// ลำดับ
			cell = row.createCell(cellNum++);
			cell.setCellValue(no);
			cell.setCellStyle(cellLeft);
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
			// ทุนจดทะเบียน
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxAmtFormat(taxVo.getRegCapital(), decimalFormatTwoDigits));
			cell.setCellStyle(cellRight);
			// การชำระภาษีในสภาวะปกติ X เดือนแรก
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxAmtFormat(taxVo.getSumTaxAmtG1(), decimalFormatTwoDigits));
			cell.setCellStyle(cellRight);
			// การชำระภาษีในสภาวะปกติ X เดือนหลัง
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxAmtFormat(taxVo.getSumTaxAmtG2(), decimalFormatTwoDigits));
			cell.setCellStyle(cellRight);
			// เปลี่ยนแปลง (ร้อยละ)
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxAmtFormat(taxVo.getTaxAmtChnPnt(), decimalFormatTwoDigits));
			cell.setCellStyle(cellRight);
			// เปอร์เซ็นต์ส่วนเบี่ยงเบนมาตรฐาน
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxAmtFormat(taxVo.getTaxAmtSd(), decimalFormatTwoDigits));
			cell.setCellStyle(cellRight);
			// ชำระภาษี (เดือน)
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getTaxMonthNo());
			cell.setCellStyle(cellRight);
			// การตรวจสอบภาษีย้อนหลัง 3 ปีงบประมาณ
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getTaxAuditLast3());
			cell.setCellStyle(cellLeft);
			// การตรวจสอบภาษีย้อนหลัง 2 ปีงบประมาณ
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getTaxAuditLast2());
			cell.setCellStyle(cellLeft);
			// การตรวจสอบภาษีย้อนหลัง 1 ปีงบประมาณ
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getTaxAuditLast1());
			cell.setCellStyle(cellLeft);
			// พิกัด
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getDutyName());
			cell.setCellStyle(cellLeft);
			// เลขทะเบียนสรรพสามิตเก่า
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getOldRegId());
			cell.setCellStyle(cellLeft);
			// สถานะ/วันที่
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getRegStatus());
			cell.setCellStyle(cellLeft);
			// ค่าเฉลี่ยภาษี
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxAmtFormat(taxVo.getTaxAmtMean(), decimalFormatTwoDigits));
			cell.setCellStyle(cellRight);
			// ค่าร้อยละสูงสุด
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxAmtFormat(taxVo.getTaxAmtMaxPnt(), decimalFormatTwoDigits));
			cell.setCellStyle(cellRight);
			// ค่าร้อยละต่ำสุด
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxAmtFormat(taxVo.getTaxAmtMinPnt(), decimalFormatTwoDigits));
			cell.setCellStyle(cellRight);
			// การชำระภาษี X เดือนแรก และ X เดือนหลัง
			for (String taxAmt : taxVo.getTaxAmtList()) {
				cell = row.createCell(cellNum++);
				cell.setCellValue(taxAmtFormat(taxAmt, decimalFormatTwoDigits));
				cell.setCellStyle(cellRight);
			}
//			// พิกัดอื่นๆ
//			cell = row.createCell(cellNum++);
//			cell.setCellValue(taxVo.getOtherDutyName());
//			cell.setCellStyle(cellLeft);
//			
//			//กลุ่มของการชำระภาษี
//			cell = row.createCell(cellNum++);
//			cell.setCellValue(taxVo.getCondTaxGrp());
//			cell.setCellStyle(cellLeft);
//			
//			//ขนาดทุนจดทะเบียน
//			cell = row.createCell(cellNum++);
//			cell.setCellValue(taxVo.getCondSubCapitalDesc());
//			cell.setCellStyle(cellLeft);
//			
//			//ระดับความเสี่ยง
//			cell = row.createCell(cellNum++);
//			cell.setCellValue(taxVo.getCondSubRiskDesc());
//			cell.setCellStyle(cellLeft);
//			
//			//ผู้ประกอบการที่ไม่มีการตรวจสอบภาษีในระยะเวลาที่กำหนด
//			cell = row.createCell(cellNum++);
//			cell.setCellValue(taxVo.getCondSubNoAuditDesc());
//			cell.setCellStyle(cellLeft);
			
			no++;
			rowNum++;
		}
		
		return rowNum;
	}
	
	private String taxAmtFormat(String taxAmt, DecimalFormat decimalFormat) {
		return !NO_TAX_AMOUNT.equals(taxAmt) ? decimalFormat.format(NumberUtils.nullToZero(NumberUtils.toBigDecimal(taxAmt))) : taxAmt;
	}
	
	private void setColumnWidth(Sheet sheet, int colIndex, int dateRange) {
		sheet.setColumnWidth(colIndex++, 7 * 256); // ลำดับ
		sheet.setColumnWidth(colIndex++, 28 * 256); // ทะเบียนสรรพสามิต เดิม/ใหม่
		sheet.setColumnWidth(colIndex++, 50 * 256); // ชื่อผู้ประกอบการ
		sheet.setColumnWidth(colIndex++, 50 * 256); // ชื่อโรงอุตสาหกรรม/สถานบริการ
		sheet.setColumnWidth(colIndex++, 50 * 256); // ที่อยู่โรงอุตสาหกรรม/สถานบริการ
		sheet.setColumnWidth(colIndex++, 10 * 256); // ภาค
		sheet.setColumnWidth(colIndex++, 15 * 256); // พื้นที่
		sheet.setColumnWidth(colIndex++, 18 * 256); // ทุนจดทะเบียน
		sheet.setColumnWidth(colIndex++, 15 * 256); // การชำระภาษีในสภาวะปกติ X เดือนแรก
		sheet.setColumnWidth(colIndex++, 15 * 256); // การชำระภาษีในสภาวะปกติ X เดือนหลัง
		sheet.setColumnWidth(colIndex++, 20 * 256); // เปลี่ยนแปลง (ร้อยละ)
		sheet.setColumnWidth(colIndex++, 30 * 256); // เปอร์เซ็นต์ส่วนเบี่ยงเบนมาตรฐาน
		sheet.setColumnWidth(colIndex++, 16 * 256); // ชำระภาษี (เดือน)
		sheet.setColumnWidth(colIndex++, 15 * 256); // การตรวจสอบภาษีย้อนหลัง 3 ปีงบประมาณ
		sheet.setColumnWidth(colIndex++, 15 * 256); // การตรวจสอบภาษีย้อนหลัง 2 ปีงบประมาณ
		sheet.setColumnWidth(colIndex++, 15 * 256); // การตรวจสอบภาษีย้อนหลัง 1 ปีงบประมาณ
		sheet.setColumnWidth(colIndex++, 15 * 256); // เดือน
		sheet.setColumnWidth(colIndex++, 15 * 256); // เดือน
		sheet.setColumnWidth(colIndex++, 15 * 256); // เดือน
		sheet.setColumnWidth(colIndex++, 15 * 256); // เดือน
		sheet.setColumnWidth(colIndex++, 15 * 256);
//		sheet.setColumnWidth(colIndex++, 40 * 256); // พิกัด
//		sheet.setColumnWidth(colIndex++, 28 * 256); // เลขทะเบียนสรรพสามิตเก่า
//		sheet.setColumnWidth(colIndex++, 15 * 256); // สถานะ/วันที่
//		sheet.setColumnWidth(colIndex++, 15 * 256); // ค่าเฉลี่ยภาษี
//		sheet.setColumnWidth(colIndex++, 15 * 256); // ค่าร้อยละสูงสุด
//		sheet.setColumnWidth(colIndex++, 15 * 256); // ค่าร้อยละต่ำสุด
		for (int i = 0; i < dateRange; i++) {
			sheet.setColumnWidth(colIndex++, 15 * 256); // การชำระภาษี X เดือนแรก และ X เดือนหลัง
		}
		sheet.setColumnWidth(colIndex++, 15 * 256); // พิกัดอื่นๆ
	}
	
	private void setMergeCell(Sheet sheet, int dateRange) {
		// Merge Cell
		int startTaxAmtIndex = 22;
		List<Integer> skipRowSpanIndex = Arrays.asList(new Integer[] {
			8, 9, 13, 14, 15
		});
		for (int i = 0; i < startTaxAmtIndex; i++) {
			if (!skipRowSpanIndex.contains(i)) {
				sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
			}
		}
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 8, 9));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 13, 15));
		
		int halfDataRange = dateRange / 2;
		sheet.addMergedRegion(new CellRangeAddress(0, 0, startTaxAmtIndex, startTaxAmtIndex + halfDataRange - 1));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, startTaxAmtIndex + halfDataRange, startTaxAmtIndex + dateRange - 1));
//		int startAfterTaxAmtIndex = startTaxAmtIndex + dateRange;
//		sheet.addMergedRegion(new CellRangeAddress(0, 1, startAfterTaxAmtIndex, startAfterTaxAmtIndex));
//		sheet.addMergedRegion(new CellRangeAddress(0, 1, startAfterTaxAmtIndex+1, startAfterTaxAmtIndex+1));
//		sheet.addMergedRegion(new CellRangeAddress(0, 1, startAfterTaxAmtIndex+2, startAfterTaxAmtIndex+2));
//		sheet.addMergedRegion(new CellRangeAddress(0, 1, startAfterTaxAmtIndex+3, startAfterTaxAmtIndex+3));
//		sheet.addMergedRegion(new CellRangeAddress(0, 1, startAfterTaxAmtIndex+4, startAfterTaxAmtIndex+4));
	}
	
}
