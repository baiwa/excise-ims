package th.co.baiwa.excise.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.LovRepository;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.CheckStampAreaDao;
import th.co.baiwa.excise.ia.persistence.entity.IaStampDetail;
import th.co.baiwa.excise.ia.persistence.entity.IaStampFile;
import th.co.baiwa.excise.ia.persistence.repository.IaStamDetailRepository;
import th.co.baiwa.excise.ia.persistence.repository.IaStampFileRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int0511FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0511Vo;
import th.co.baiwa.excise.upload.service.ExcalService;

@Service
public class Int0511Service {

	
	@Autowired
	private ExcalService excalService;
	
	@Autowired
	private CheckStampAreaDao checkStampAreaDao;

	@Autowired
	private IaStamDetailRepository iaStamDetailRepository;
	
	@Autowired
	private IaStampFileRepository iaStampFileRepository;
	
	@Autowired
	private LovRepository lovRepository;

	
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

	
	private Logger log = LoggerFactory.getLogger(IaFollowUpProjectService.class);
	
	
	public DataTableAjax<Int0511Vo> findAll(Int0511FormVo formVo) {
	    
		DataTableAjax<Int0511Vo> dataTableAjax = new DataTableAjax<>();		
		
		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			
			formVo.setDateForm(DateConstant.convertStrDDMMYYYYToStrYYYYMMDD(formVo.getDateForm()));
		    formVo.setDateTo(DateConstant.convertStrDDMMYYYYToStrYYYYMMDD(formVo.getDateTo()));
			List<Int0511Vo> list = checkStampAreaDao.findAll(formVo);
			Long count = checkStampAreaDao.count(formVo);
			
			dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(list);
		}

		return dataTableAjax;
	}

	public List<LabelValueBean> sector() {
		return checkStampAreaDao.sector();
	}

	public List<LabelValueBean> area(String id) {
		return checkStampAreaDao.area(id);
	}

	@Transactional
	public void save(Int0511FormVo formVo) {
			
		Int0511Vo form = formVo.getData();		
		IaStampDetail entity = iaStamDetailRepository.findOne(Long.valueOf(form.getWorkSheetDetailId()));
		
		/*officeCode*/
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeId();
		entity.setOfficeCode(officeCode);
		Lov lov  = lovRepository.findBySubType(officeCode);
		entity.setOfficeDesc(lov.getSubTypeDescription());
		
		entity.setDateOfPay(DateConstant.convertStrDDMMYYYYToDate(form.getDateOfPay()));
		entity.setStatus(form.getStatus());
		
		entity.setBookNumberWithdrawStamp(form.getBookNumberWithdrawStamp());
		entity.setDateWithdrawStamp(DateConstant.convertStrDDMMYYYYToDate(form.getDateWithdrawStamp()));
		entity.setBookNumberDeliverStamp(form.getBookNumberDeliverStamp());
		entity.setDateDeliverStamp(DateConstant.convertStrDDMMYYYYToDate(form.getDateDeliverStamp()));
		entity.setFivePartNumber(form.getFivePartNumber());
		entity.setFivePartDate(DateConstant.convertStrDDMMYYYYToDate(form.getFivePartDate()));
		entity.setStampCheckDate(DateConstant.convertStrDDMMYYYYToDate(form.getStampCheckDate()));
		entity.setStampChecker(form.getStampChecker());
		entity.setStampType(form.getStampType());
		entity.setStampBrand(form.getStampBrand());
		entity.setNumberOfBook(form.getNumberOfBook());
		entity.setNumberOfStamp(form.getNumberOfStamp());
		entity.setValueOfStampPrinted(form.getValueOfStampPrinted());
		entity.setSumOfValue(form.getSumOfValue());
		entity.setSerialNumber(form.getSerialNumber());
		entity.setTaxStamp(form.getTaxStamp());
		entity.setStampCodeStart(form.getStampCodeStart());
		entity.setStampCodeEnd(form.getStampCodeEnd());
		entity.setNote(form.getNote());
		entity.setCreatedDate(DateConstant.convertStrDDMMYYYYToDate(form.getCreatedDate()));
		entity.setFileName(form.getFileName());
		iaStamDetailRepository.save(entity);		
	}
	
	public void delete(Int0511FormVo formvo) {
		
		IaStampDetail entity = iaStamDetailRepository.findOne(Long.valueOf(formvo.getData().getWorkSheetDetailId()));
		iaStamDetailRepository.delete(entity);
	}
	
	public List<String> listFile(String id){		
		List<String> fileName = new ArrayList<>();
		List<IaStampFile> list = iaStampFileRepository.findByDetailId(id);
		for (IaStampFile iaStampFile : list) {
			fileName.add(iaStampFile.getFileName());
		}
		return fileName;
	}
	

public void exportFile(Int0511FormVo formVo, HttpServletResponse response) throws IOException {
		
		/* create spreadsheet */
		XSSFWorkbook workbook = setUpExcel();
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		System.out.println("Creating excel");
		
		
		/* create data spreadsheet */
		
		/* Header */
		String[] tbTH1 = { "ลำดับ", "โครงการ","วันที่ การรับ-จ่าย-ส่งคืน",
				           "สถานะ การรับ-จ่าย","หน่วยงาน/ผู้ประกอบการที่รับ-จ่ายแสตมป์","หนังสือขอเบิกแสตมป์",
				           "","หนังสือส่งแสตมป์","","เลขที่ใบ 5 ตอน","	ลงวันวันที่","วันที่ตรวจนับ","ผู้ตรวจนับ (1)",
				           "ผู้ตรวจนับ (2)", "ผู้ตรวจนับ (3)", "ชนิดแสตมป์/ขนาดบรรจุ	จำนวน (เล่ม)", "จำนวน (ดวง)", "มูลค่าที่พิมพ์ (บาทต่อดวง)", 
				           "รวมค่าพิมพ์ (บาท)", "ค่าภาษี (บาท)", "รหัสแสตมป์", "", "	หมายเหตุ", "จัดการ", 
				};
		for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[cellNum]);
			cell.setCellStyle(thStyle);
		};

		String[] tbTH2 = { "เลขที่หนังสือ", "ลงวันที่", "เลขที่หนังสือ", "ลงวันที่","รหัสแสตมป์ (เริ่ม)","รหัสแสตมป์ (ถึง)" };
		row = sheet.createRow(1);
		int cellNumtbTH2 = 2;
		for (int i = 0; i < tbTH2.length; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellValue(tbTH2[i]);
			cell.setCellStyle(thStyle);
			cellNumtbTH2++;
		};
		
//		cell = row.createCell(0);cell.setCellStyle(thStyle);
//		cell = row.createCell(1);cell.setCellStyle(thStyle);
//		cell = row.createCell(26);cell.setCellStyle(thStyle);
//		cell = row.createCell(27);cell.setCellStyle(thStyle);

		/* set sheet */
		// setColumnWidth
//		for (int i = 1; i <= 27; i++) {
//			if (i == 1) {
//				// วันครบกำหนดครั้งที่2
//				sheet.setColumnWidth(i, 76 * 255);
//			} else if (i == 27) {
//				// โครงการ => รายงานการติดตามครั้งที่ 2
//				sheet.setColumnWidth(i, 76 * 80);
//			} else if (i != 1 && i != 27)
//				// การติดตาม
//				sheet.setColumnWidth(i, 76 * 70);
//		}

		// merge(firstRow, lastRow, firstCol, lastCol)
//		int firstCol = 2;
//		while (firstCol < 12) {
//			for (int lastCol = 3; lastCol <= 13; lastCol += 2) {
//				sheet.addMergedRegion(new CellRangeAddress(0, 0, firstCol, lastCol));
//				firstCol += 2;
//			}
//		}
//		sheet.addMergedRegion(new CellRangeAddress(0, 0, 15, 16));
//		sheet.addMergedRegion(new CellRangeAddress(0, 0, 17, 18));
//		sheet.addMergedRegion(new CellRangeAddress(0, 0, 19, 20));
//		sheet.addMergedRegion(new CellRangeAddress(0, 0, 22, 23));
//		sheet.addMergedRegion(new CellRangeAddress(0, 0, 24, 25));
//		
//		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
//		sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
//		sheet.addMergedRegion(new CellRangeAddress(0, 1, 26,26));
//		sheet.addMergedRegion(new CellRangeAddress(0, 1, 27,27));
		/* End set sheet */
		
		
		/* Detail */
//		List<Int0511Vo> exportDataList = checkStampAreaDao.exportFile(formVo);

//		if( StringUtils.isNotBlank(formVo.getProjectName())||StringUtils.isNotBlank(formVo.getStatus())) {
//			exportDataList = checkStampAreaDao.exportFile(formVo);
//		}else if(StringUtils.isBlank(formVo.getProjectName()) && StringUtils.isBlank(formVo.getStatus())) {
//			exportDataList = checkStampAreaDao.exportFile(formVo);
//		}
//		
		rowNum = 2;
		cellNum = 0;
		int no = 1;
//		for (Int0511Vo detail : exportDataList) {
//			row = sheet.createRow(rowNum);
//			// No.
//			cell = row.createCell(cellNum);
//			cell.setCellValue(no);
//			cell.setCellStyle(cellCenter);
//			cellNum++;
//			
//			cell = row.createCell(cellNum);
//			cell.setCellValue(detail.getWorkSheetDetailId());
//			cell.setCellStyle(cellLeft);
//			cellNum++;
//			
//			cell = row.createCell(cellNum);
//			if(StringUtils.isNotBlank(detail.getDateOfPay())) {
//				cell.setCellValue(detail.getDateOfPay());
//			}else {
//				cell.setCellValue("-");
//			}
//			cell.setCellStyle(cellCenter);
//			cellNum++;
//			
//			cell = row.createCell(cellNum);
//			if(StringUtils.isNotBlank(detail.getStatus())) {
//				cell.setCellValue(detail.getStatus());
//			}else {
//				cell.setCellValue("-");
//			}
//			cell.setCellStyle(cellCenter);
//			cellNum++;
//			
//	
//			
//			cell = row.createCell(cellNum);
//			if(StringUtils.isNotBlank(detail.getStatus())) {
//				cell.setCellValue(detail.getStatus());
//			}else {
//				cell.setCellValue("-");
//			}
//			cell.setCellStyle(cellCenter);
//			cellNum++;
//			
//			cell = row.createCell(cellNum);
//			if(StringUtils.isNotBlank(detail.getOfficeDesc())) {
//				cell.setCellValue(detail.getOfficeDesc());
//			}else {
//				cell.setCellValue("-");
//			}
//			cell.setCellStyle(cellCenter);
//			cellNum++;
//			
//			cell = row.createCell(cellNum);
//			if(StringUtils.isNotBlank(detail.getOfficeCode())) {
//				cell.setCellValue(detail.getOfficeCode());
//			}else {
//				cell.setCellValue("-");
//			}
//			cell.setCellStyle(cellCenter);
//			cellNum++;
//			
//			
//			cell = row.createCell(cellNum);
//			if(StringUtils.isNotBlank(detail.getBookNumberWithdrawStamp())) {
//				cell.setCellValue(detail.getBookNumberWithdrawStamp());
//			}else {
//				cell.setCellValue("-");
//			}
//			cell.setCellStyle(cellCenter);
//			cellNum++;
//			
//			
//			cell = row.createCell(cellNum);
//			if(StringUtils.isNotBlank(detail.getBookNumberWithdrawStamp())) {
//				cell.setCellValue(detail.getBookNumberWithdrawStamp());
//			}else {
//				cell.setCellValue("-");
//			}
//			cell.setCellStyle(cellCenter);
//			cellNum++;		
//			
//			
//			cell = row.createCell(cellNum);
//			if(StringUtils.isNotBlank(detail.getDateWithdrawStamp())) {
//				cell.setCellValue(detail.getDateWithdrawStamp());
//			}else {
//				cell.setCellValue("-");
//			}
//			cell.setCellStyle(cellCenter);
//			cellNum++;
//			
//			cell = row.createCell(cellNum);
//			if(StringUtils.isNotBlank(detail.getBookNumberDeliverStamp())) {
//				cell.setCellValue(detail.getBookNumberDeliverStamp());
//			}else {
//				cell.setCellValue("-");
//			}
//			cell.setCellStyle(cellCenter);
//			cellNum++;
//			
//			
//			cell = row.createCell(cellNum);
//			if(StringUtils.isNotBlank(detail.getDateDeliverStamp())) {
//				cell.setCellValue(detail.getDateDeliverStamp());
//			}else {
//				cell.setCellValue("-");
//			}
//			cell.setCellStyle(cellCenter);
//			cellNum++;
//			
//			
//
//			cell = row.createCell(cellNum);
//			if(StringUtils.isNotBlank(detail.getFivePartNumber())) {
//				cell.setCellValue(detail.getFivePartNumber());
//			}else {
//				cell.setCellValue("-");
//			}
//			cell.setCellStyle(cellCenter);
//			cellNum++;
//			
//			cell = row.createCell(cellNum);
//			if(StringUtils.isNotBlank(detail.getFivePartDate())) {
//				cell.setCellValue(detail.getFivePartDate());
//			}else {
//				cell.setCellValue("-");
//			}
//			cell.setCellStyle(cellCenter);
//			cellNum++;
//			
//			
//			cell = row.createCell(cellNum);
//			if(StringUtils.isNotBlank(detail.getStampCheckDate())) {
//				cell.setCellValue(detail.getStampCheckDate());
//			}else {
//				cell.setCellValue("-");
//			}
//			cell.setCellStyle(cellCenter);
//			cellNum++;
//			
//			cell = row.createCell(cellNum);
//			if(StringUtils.isNotBlank(detail.getStampChecker())) {
//				cell.setCellValue(detail.getStampChecker());
//			}else {
//				cell.setCellValue("-");
//			}
//			cell.setCellStyle(cellCenter);
//			cellNum++;
//			
//			
//			cell = row.createCell(cellNum);
//			if(StringUtils.isNotBlank(detail.getStampType())) {
//				cell.setCellValue(detail.getStampType());
//			}else {
//				cell.setCellValue("-");
//			}
//			cell.setCellStyle(cellCenter);
//			cellNum++;
//			
//			
//			cell = row.createCell(cellNum);
//			if(StringUtils.isNotBlank(detail.getStampBrand())) {
//				cell.setCellValue(detail.getStampBrand());
//			}else {
//				cell.setCellValue("-");
//			}
//			cell.setCellStyle(cellCenter);
//			cellNum++;
//			
//			cell = row.createCell(cellNum);
//			if(StringUtils.isNotBlank(detail.getStampBrand())) {
//				cell.setCellValue(detail.getStampBrand());
//			}else {
//				cell.setCellValue("-");
//			}
//			cell.setCellStyle(cellCenter);
//			cellNum++;
//			
//		
//			
//			
//			no++;
//			rowNum++;
//			cellNum = 0;
//		}
		
		
		/*set	fileName*/		
		String fileName ="FollowUpProject";
		System.out.println(fileName);
		
		/* write it as an excel attachment */
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		workbook.write(outByteStream);
		byte [] outArray = outByteStream.toByteArray();
		response.setContentType("application/vnd.ms-excel");
		response.setContentLength(outArray.length);
		response.setHeader("Expires:", "0"); // eliminates browser caching
		response.setHeader("Content-Disposition", "attachment; filename="+fileName+".xlsx");
		OutputStream outStream = response.getOutputStream();
		outStream.write(outArray);
		outStream.flush();
		outStream.close();
		
		System.out.println("Done");
	}


}
