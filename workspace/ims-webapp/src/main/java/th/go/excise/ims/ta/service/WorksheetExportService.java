package th.go.excise.ims.ta.service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondMainDtl;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondMainHdr;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondMainDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetCondMainHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaWsReg4000Repository;
import th.go.excise.ims.ta.util.TaxAuditUtils;
import th.go.excise.ims.ta.vo.TaxOperatorDatatableVo;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

@Service
public class WorksheetExportService {
	
	private static final Logger logger = LoggerFactory.getLogger(WorksheetExportService.class);
	
	private static final String NO_TAX_AMOUNT = "-";
	private static final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM yyyy", ConvertDateUtils.LOCAL_TH);
	
	@Autowired
	private TaWsReg4000Repository taWsReg4000Repository;
	@Autowired
	private DraftWorksheetService draftWorksheetService;
	
	@Autowired
	private TaWorksheetCondMainDtlRepository taWorksheetCondMainDtlRepository;
	@Autowired
	private TaWorksheetHdrRepository taWorksheetHdrRepository;
	@Autowired
	private TaWorksheetDtlRepository taWorksheetDtlRepository;
	@Autowired
	private TaWorksheetCondMainHdrRepository taWorksheetCondMainHdrRepository;
	
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
		
		final String COND_GROUP = "COND_GROUP";
		final String COND_GROUP_DESC = "COND_GROUP_DESC";
		final String COND_DTL_DESC = "COND_DTL_DESC";
		String SHEET_NAME = "เงื่อนไขที่ ";
		String NON_MAIN_COND_GROUP = "0";
		String NON_MAIN_COND_DESC = "รายที่ไม่อยู่ในเงื่อนไข";
		
		List<Map<String, String>> condMainList = new ArrayList<>();
		Map<String, String> condMainMap = null;
		List<TaWorksheetCondMainDtl> condMainDtlList = taWorksheetCondMainDtlRepository.findByAnalysisNumber(formVo.getAnalysisNumber());
		for (TaWorksheetCondMainDtl condMainDtl : condMainDtlList) {
			condMainMap = new HashMap<>();
			condMainMap.put(COND_GROUP, condMainDtl.getCondGroup());
			condMainMap.put(COND_GROUP_DESC, SHEET_NAME + condMainDtl.getCondGroup());
			condMainMap.put(COND_DTL_DESC, SHEET_NAME + condMainDtl.getCondGroup() + " " + condMainDtl.getCondDtlDesc());
			condMainList.add(condMainMap);
		}
		condMainMap = new HashMap<>();
		condMainMap.put(COND_GROUP, NON_MAIN_COND_GROUP);
		condMainMap.put(COND_GROUP_DESC, NON_MAIN_COND_DESC);
		condMainMap.put(COND_DTL_DESC, NON_MAIN_COND_DESC);
		condMainList.add(condMainMap);
		
		// Create Workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = null;
		Row row = null;
		Cell cell = null;
		int rowNum = 0;
		
		/* Cell Style */
		CellStyle cellHeader = ExcelUtils.createThColorStyle(workbook, new XSSFColor(Color.LIGHT_GRAY));
		
		List<TaxOperatorDetailVo> worksheetVoList = null;
		List<TaxOperatorDatatableVo> taxOperatorDatatableVoList = null;
		for (Map<String, String> map : condMainList) {
			// Prepare Data for Export
			formVo.setStart(0);
			formVo.setLength(taWorksheetDtlRepository.countByCriteria(formVo).intValue());
			formVo.setCond(map.get(COND_GROUP));
			
			worksheetVoList = taWorksheetDtlRepository.findByCriteria(formVo);
			taxOperatorDatatableVoList = TaxAuditUtils.prepareTaxOperatorDatatable(worksheetVoList, formVo);
			
			/*
			 * Sheet
			 */
			sheet = workbook.createSheet(map.get(COND_GROUP_DESC));
			rowNum = 0;
			
			/*
			 * Column Header
			 */
			// Header Line 1
			row = sheet.createRow(rowNum);
			cell = row.createCell(1);
			cell.setCellValue(map.get(COND_DTL_DESC));
			rowNum++;
			// Header Line 2
			row = sheet.createRow(rowNum);
			generateWorksheetHeader1(row, cell, cellHeader, formVo);
			rowNum++;
			// Header Line 3
			row = sheet.createRow(rowNum);
			generateWorksheetHeader2(row, cell, cellHeader, formVo);
			rowNum++;
			
			/*
			 * Details
			 */
			rowNum = generateWorksheetDetail(workbook, sheet, row, rowNum, cell, taxOperatorDatatableVoList);
			
			// Column Width
			setColumnWidth(sheet, 0, formVo.getDateRange());
			setMergeCellCondition(sheet, formVo.getDateRange());
		}
		
		byte[] content = null;
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			workbook.write(outputStream);
			content = outputStream.toByteArray();
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
		headerText1List.add("พิกัดอื่นๆ");
		headerText1List.add("ขนาดทุนจดทะเบียน");
		headerText1List.add("ระดับความเสี่ยง");
		headerText1List.add("ผู้ประกอบการที่ไม่มีการตรวจสอบภาษีในระยะเวลาที่กำหนด");
		
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
		headerText2List.add("");
	
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
			thaiDateList.add(thaiDate.format(dateFormatter));
		}
		return thaiDateList;
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
			// พิกัดอื่นๆ
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getOtherDutyName());
			cell.setCellStyle(cellLeft);
			
			//ขนาดทุนจดทะเบียน
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getCondSubCapitalDesc());
			cell.setCellStyle(cellLeft);
			
			//ระดับความเสี่ยง
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getCondSubRiskDesc());
			cell.setCellStyle(cellLeft);
			
			//ผู้ประกอบการที่ไม่มีการตรวจสอบภาษีในระยะเวลาที่กำหนด
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getCondSubNoAuditDesc());
			cell.setCellStyle(cellLeft);
			
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
		sheet.setColumnWidth(colIndex++, 40 * 256); // พิกัด
		sheet.setColumnWidth(colIndex++, 28 * 256); // เลขทะเบียนสรรพสามิตเก่า
		sheet.setColumnWidth(colIndex++, 15 * 256); // สถานะ/วันที่
		sheet.setColumnWidth(colIndex++, 15 * 256); // ค่าเฉลี่ยภาษี
		sheet.setColumnWidth(colIndex++, 15 * 256); // ค่าร้อยละสูงสุด
		sheet.setColumnWidth(colIndex++, 15 * 256); // ค่าร้อยละต่ำสุด
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
		int startAfterTaxAmtIndex = startTaxAmtIndex + dateRange;
		sheet.addMergedRegion(new CellRangeAddress(0, 1, startAfterTaxAmtIndex, startAfterTaxAmtIndex));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, startAfterTaxAmtIndex+1, startAfterTaxAmtIndex+1));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, startAfterTaxAmtIndex+2, startAfterTaxAmtIndex+2));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, startAfterTaxAmtIndex+3, startAfterTaxAmtIndex+3));
	}
	
	private void setMergeCellCondition(Sheet sheet, int dateRange) {
		// Merge Cell
		int startTaxAmtIndex = 22;
		List<Integer> skipRowSpanIndex = Arrays.asList(new Integer[] {
			8, 9, 13, 14, 15
		});
		for (int i = 0; i < startTaxAmtIndex; i++) {
			if (!skipRowSpanIndex.contains(i)) {
				sheet.addMergedRegion(new CellRangeAddress(1, 2, i, i));
			}
		}
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 4));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 8, 9));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, 13, 15));
		
		int halfDataRange = dateRange / 2;
		sheet.addMergedRegion(new CellRangeAddress(1, 1, startTaxAmtIndex, startTaxAmtIndex + halfDataRange - 1));
		sheet.addMergedRegion(new CellRangeAddress(1, 1, startTaxAmtIndex + halfDataRange, startTaxAmtIndex + dateRange - 1));
		int startAfterTaxAmtIndex = startTaxAmtIndex + dateRange;
		sheet.addMergedRegion(new CellRangeAddress(1, 2, startAfterTaxAmtIndex, startAfterTaxAmtIndex));
	}
	
}
