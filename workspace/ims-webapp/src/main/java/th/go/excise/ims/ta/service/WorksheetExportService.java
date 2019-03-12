package th.go.excise.ims.ta.service;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
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

import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.repository.TaDraftWorksheetDtlRepository;
import th.go.excise.ims.ta.util.TaxAuditUtils;
import th.go.excise.ims.ta.vo.TaxOperatorDatatableVo;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

@Service
public class WorksheetExportService {
	
	private static final Logger logger = LoggerFactory.getLogger(WorksheetExportService.class);
	
	private static final String NO_TAX_AMOUNT = "-";
	
	@Autowired
	private TaDraftWorksheetDtlRepository taDraftWorksheetDtlRepository;
	
	public byte[] exportPreviewWorksheet(TaxOperatorFormVo formVo) {
		logger.info("exportXlsxDraftWorksheet officeCode={}");
		
		return null;
	}
	public byte[] exportDraftWorksheet(TaxOperatorFormVo formVo) {
		logger.info("exportXlsxDraftWorksheet officeCode={}");
		
		// Prepare Data for Export
		formVo.setOfficeCode(ExciseUtils.whereInLocalOfficeCode(formVo.getOfficeCode()));
//		formVo.setStart(0);
//		formVo.setLength(taDraftWorksheetDtlRepository.countByCriteria(formVo).intValue());
		formVo.setStart(0);
		formVo.setLength(5); // FIXME
		List<TaxOperatorDetailVo> draftVoList = taDraftWorksheetDtlRepository.findByCriteria(formVo);
		List<TaxOperatorDatatableVo> taxOperatorDatatableVoList = TaxAuditUtils.prepareTaxOperatorDatatable(draftVoList, formVo);
		
		// Label and Text
		String SHEET_NAME = "รายชื่อผู้ประกอบการ";
		
		// Create Workbook
		XSSFWorkbook workbook = new XSSFWorkbook();
		
		/* Cell Style */
		CellStyle cellHeader = ExcelUtils.createThColorStyle(workbook, new XSSFColor(Color.LIGHT_GRAY));
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);
		
		Sheet sheet = workbook.createSheet(SHEET_NAME);
		Row row = null;
		Cell cell = null;
		int rowNum = 0;
		int cellNum = 0;
		
		/*
		 * Column Header
		 */
		// Header Line 1
		List<String> headerText1List = getHeaderText1List();
		row = sheet.createRow(rowNum);
		cellNum = 0;
		for (String headerText : headerText1List) {
			cell = row.createCell(cellNum);
			cell.setCellValue(new XSSFRichTextString(headerText));
			cell.setCellStyle(cellHeader);
			cell.setCellValue(headerText);
			cellNum++;
		}
		rowNum++;
		// Header Line 2
		List<String> headerText2List = getHeaderText2List();
		row = sheet.createRow(rowNum);
		cellNum = 0;
		for (String headerText : headerText2List) {
			cell = row.createCell(cellNum);
			cell.setCellValue(new XSSFRichTextString(headerText));
			cell.setCellStyle(cellHeader);
			cell.setCellValue(headerText);
			cellNum++;
		}
		rowNum++;
		
		
		/*
		 * Details
		 */
		DecimalFormat decimalFormatTwoDigits = new DecimalFormat("#,##0.00");
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
			cell.setCellValue(taxVo.getRegCapital());
			cell.setCellStyle(cellRight);
			// การชำระภาษีในสภาวะปกติ X เดือนแรก
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getSumTaxAmtG1());
			cell.setCellStyle(cellRight);
			// การชำระภาษีในสภาวะปกติ X เดือนหลัง
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getSumTaxAmtG2());
			cell.setCellStyle(cellRight);
			// เปลี่ยนแปลง (ร้อยละ)
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getTaxAmtChnPnt());
			cell.setCellStyle(cellRight);
			// เปอร์เซ็นต์ส่วนเบี่ยงเบนมาตรฐาน
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getTaxAmtSd());
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
			cell.setCellValue(taxVo.getTaxAmtMean());
			cell.setCellStyle(cellRight);
			// ค่าร้อยละสูงสุด
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getTaxAmtMaxPnt());
			cell.setCellStyle(cellRight);
			// ค่าร้อยละต่ำสุด
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getTaxAmtMinPnt());
			cell.setCellStyle(cellRight);
			// การชำระภาษี X เดือนแรก
			for (int i = 0; i < formVo.getDateRange() / 2; i++) {
				cell = row.createCell(cellNum++);
				cell.setCellValue(taxAmtFormat(taxVo.getTaxAmtList().get(i), decimalFormatTwoDigits));
				cell.setCellStyle(cellRight);
			}
			// การชำระภาษี X เดือนหลัง
			for (int i = formVo.getDateRange() / 2; i < formVo.getDateRange(); i++) {
				cell = row.createCell(cellNum++);
				cell.setCellValue(taxAmtFormat(taxVo.getTaxAmtList().get(i), decimalFormatTwoDigits));
				cell.setCellStyle(cellRight);
			}
			// พิกัดอื่นๆ
			cell = row.createCell(cellNum++);
			cell.setCellValue(taxVo.getOtherDutyName());
			cell.setCellStyle(cellLeft);
			
			no++;
			rowNum++;
		}
		
		// Column Width
		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, 7 * 256); // ลำดับ
		sheet.setColumnWidth(colIndex++, 26 * 256); // ทะเบียนสรรพสามิต เดิม/ใหม่
		sheet.setColumnWidth(colIndex++, 50 * 256); // ชื่อผู้ประกอบการ
		sheet.setColumnWidth(colIndex++, 50 * 256); // ชื่อโรงอุตสาหกรรม/สถานบริการ
		sheet.setColumnWidth(colIndex++, 50 * 256); // ที่อยู่โรงอุตสาหกรรม/สถานบริการ
		sheet.setColumnWidth(colIndex++, 10 * 256); // ภาค
		sheet.setColumnWidth(colIndex++, 15 * 256); // พื้นที่
		sheet.setColumnWidth(colIndex++, 50 * 256); // ทุนจดทะเบียน
		sheet.setColumnWidth(colIndex++, 50 * 256); // การชำระภาษีในสภาวะปกติ X เดือนแรก
		sheet.setColumnWidth(colIndex++, 50 * 256); // การชำระภาษีในสภาวะปกติ X เดือนหลัง
		sheet.setColumnWidth(colIndex++, 50 * 256); // เปลี่ยนแปลง (ร้อยละ)
		sheet.setColumnWidth(colIndex++, 50 * 256); // เปอร์เซ็นต์ส่วนเบี่ยงเบนมาตรฐาน
		sheet.setColumnWidth(colIndex++, 50 * 256); // ชำระภาษี (เดือน)
		sheet.setColumnWidth(colIndex++, 50 * 256); // การตรวจสอบภาษีย้อนหลัง 3 ปีงบประมาณ
		sheet.setColumnWidth(colIndex++, 50 * 256); // การตรวจสอบภาษีย้อนหลัง 2 ปีงบประมาณ
		sheet.setColumnWidth(colIndex++, 50 * 256); // การตรวจสอบภาษีย้อนหลัง 1 ปีงบประมาณ
		sheet.setColumnWidth(colIndex++, 50 * 256); // พิกัด
		sheet.setColumnWidth(colIndex++, 50 * 256); // เลขทะเบียนสรรพสามิตเก่า
		sheet.setColumnWidth(colIndex++, 50 * 256); // สถานะ/วันที่
		sheet.setColumnWidth(colIndex++, 50 * 256); // ค่าเฉลี่ยภาษี
		sheet.setColumnWidth(colIndex++, 50 * 256); // ค่าร้อยละสูงสุด
		sheet.setColumnWidth(colIndex++, 50 * 256); // ค่าร้อยละต่ำสุด
		sheet.setColumnWidth(colIndex++, 50 * 256); // การชำระภาษี X เดือนแรก
		sheet.setColumnWidth(colIndex++, 50 * 256); // การชำระภาษี X เดือนหลัง
		sheet.setColumnWidth(colIndex++, 50 * 256); // พิกัดอื่นๆ
		
		byte[] content = null;
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			workbook.write(outputStream);
			content = outputStream.toByteArray();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return content;
	}
	
	public byte[] exportXlsxWorksheet(TaxOperatorFormVo formVo) {
		return null;
	}
	
	private List<String> getHeaderText1List() {
		return Arrays.asList(
			"ลำดับ",
			"ทะเบียนสรรพสามิต เดิม/ใหม่",
			"ชื่อผู้ประกอบการ",
			"ชื่อโรงอุตสาหกรรม/สถานบริการ",
			"ที่อยู่โรงอุตสาหกรรม/สถานบริการ",
			"ภาค",
			"พื้นที่",
			"ทุนจดทะเบียน",
			"การชำระภาษีในสภาวะปกติ X เดือนแรก",
			"การชำระภาษีในสภาวะปกติ X เดือนหลัง",
			"เปลี่ยนแปลง (ร้อยละ)",
			"เปอร์เซ็นต์ส่วนเบี่ยงเบนมาตรฐาน",
			"ชำระภาษี (เดือน)",
			"การตรวจสอบภาษีย้อนหลัง 3 ปีงบประมาณ",
			"การตรวจสอบภาษีย้อนหลัง 2 ปีงบประมาณ",
			"การตรวจสอบภาษีย้อนหลัง 1 ปีงบประมาณ",
			"พิกัด",
			"เลขทะเบียนสรรพสามิตเก่า",
			"สถานะ/วันที่",
			"ค่าเฉลี่ยภาษี",
			"ค่าร้อยละสูงสุด",
			"ค่าร้อยละต่ำสุด",
			"การชำระภาษี X เดือนแรก",
			"การชำระภาษี X เดือนหลัง",
			"พิกัดอื่นๆ"
		);
	}
	
	private List<String> getHeaderText2List() {
		return Arrays.asList(
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			"",
			""
		);
	}
	
	private String taxAmtFormat(String taxAmt, DecimalFormat decimalFormat) {
		return !NO_TAX_AMOUNT.equals(taxAmt) ? decimalFormat.format(NumberUtils.nullToZero(NumberUtils.toBigDecimal(taxAmt))) : taxAmt;
	}
	
}
