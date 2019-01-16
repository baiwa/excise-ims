package th.co.baiwa.excise.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.constant.ExciseConstants.SEARCH_FLAG;
import th.co.baiwa.excise.constant.IaConstant.IA_REGIS_TRACK_CONTROL.STATUS;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.IaFollowUpDepartmentDao;
import th.co.baiwa.excise.ia.persistence.entity.IaFollowUpDepartment;
import th.co.baiwa.excise.ia.persistence.repository.IaFollowUpDepartmentRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int112Form;
import th.co.baiwa.excise.ia.persistence.vo.Int112FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int112Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int11ShiftDateVo;

@Service
public class IaFollowUpDepartmentService {
	
	private final String  REGIS_TRACK_CONTROL_STATUS = "REGIS_TRACK_CONTROL_STATUS"; 
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
	
	
	private Logger log = LoggerFactory.getLogger(IaFollowUpDepartmentService.class);
	
	@Autowired
	private IaFollowUpDepartmentDao iaFollowUpDepartmentDao;
	
	@Autowired
	private IaFollowUpDepartmentRepository iaFollowUpDepartmentRepository;
	
	public DataTableAjax<Int112Vo> searchIaFollowUpDepartment(Int112FormVo formVo) {
		DataTableAjax<Int112Vo> dataTableAjax = new DataTableAjax<Int112Vo>();
		
		if (SEARCH_FLAG.TRUE.equalsIgnoreCase(formVo.getSearchFlag())) {
			List<Int112Vo> iaFollowUpDepartmentList = iaFollowUpDepartmentDao.searchCriteria(formVo);
			long count = iaFollowUpDepartmentDao.countCriteria(formVo);
			
			dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			dataTableAjax.setData(iaFollowUpDepartmentList);
		}
		
		return dataTableAjax;
	}
	
	public List<LabelValueBean> getDepartmentDropdown() {
		return iaFollowUpDepartmentDao.queryDeapertment();
	}
	
	public List<LabelValueBean> getRegionDropdown(String id) {
		return iaFollowUpDepartmentDao.queryRegion(id);
	}
	
	public List<LabelValueBean> getDistrictDropdown(String id) {
		return iaFollowUpDepartmentDao.queryDistrict(id);
	}
	
	public void saveOrUpdate(Int112FormVo vo) {
		log.info("SaveOrUpdate IaFollowUpDepartment ExiseDepartment : {}", vo.getExciseDepartment());
		IaFollowUpDepartment iaFollowUpDepartment = new IaFollowUpDepartment();
		
		if (StringUtils.isNotBlank(vo.getFollowUpDepartmentId())) {
			iaFollowUpDepartment.setFollowUpDepartmentId(Long.valueOf(vo.getFollowUpDepartmentId()));
			iaFollowUpDepartment.setIsDeleted(FLAG.N_FLAG);
		}
		
		iaFollowUpDepartment.setExciseDepartment(iaFollowUpDepartmentDao.queryValue1SysLov(vo.getExciseDepartment()));
		iaFollowUpDepartment.setExciseRegion(iaFollowUpDepartmentDao.queryValue1SysLov(vo.getExciseRegion()));
		iaFollowUpDepartment.setExciseDistrict(iaFollowUpDepartmentDao.queryDescSysLov(vo.getExciseDistrict()));
		iaFollowUpDepartment.setInformRectorBnum(vo.getInformRectorBnum());
		iaFollowUpDepartment.setInformRectorDate(DateConstant.convertStrDDMMYYYYToDate(vo.getInformRectorDate()));
		iaFollowUpDepartment.setFollowUp1Bnum(vo.getFollowUp1Bnum());
		iaFollowUpDepartment.setFollowUp1Date(DateConstant.convertStrDDMMYYYYToDate(vo.getFollowUp1Date()));
		iaFollowUpDepartment.setMaturity145(DateConstant.convertStrDDMMYYYYToDate(vo.getMaturity145()));
		iaFollowUpDepartment.setMaturity160(DateConstant.convertStrDDMMYYYYToDate(vo.getMaturity160()));
		iaFollowUpDepartment.setPerformance1Bnum(vo.getPerformance1Bnum());
		iaFollowUpDepartment.setPerformance1Date(DateConstant.convertStrDDMMYYYYToDate(vo.getPerformance1Date()));
		iaFollowUpDepartment.setTrackResult1Bnum(vo.getTrackResult1Bnum());
		iaFollowUpDepartment.setTrackResult1Date(DateConstant.convertStrDDMMYYYYToDate(vo.getTrackResult1Date()));
		iaFollowUpDepartment.setFollowUp2Bnum(vo.getFollowUp2Bnum());
		iaFollowUpDepartment.setFollowUp2Date(DateConstant.convertStrDDMMYYYYToDate(vo.getFollowUp2Date()));
		iaFollowUpDepartment.setMaturity260(DateConstant.convertStrDDMMYYYYToDate(vo.getMaturity260()));
		iaFollowUpDepartment.setPerformance2Bnum(vo.getPerformance2Bnum());
		iaFollowUpDepartment.setPerformance2Date(DateConstant.convertStrDDMMYYYYToDate(vo.getPerformance2Date()));
		iaFollowUpDepartment.setTrackResult2Bnum(vo.getTrackResult2Bnum());
		iaFollowUpDepartment.setTrackResult2Date(DateConstant.convertStrDDMMYYYYToDate(vo.getTrackResult2Date()));
		iaFollowUpDepartment.setFollowUp3Bnum(vo.getFollowUp3Bnum());
		iaFollowUpDepartment.setFollowUp3Date(DateConstant.convertStrDDMMYYYYToDate(vo.getFollowUp3Date()));
		iaFollowUpDepartment.setMaturity360(DateConstant.convertStrDDMMYYYYToDate(vo.getMaturity360()));
		iaFollowUpDepartment.setPerformance3Bnum(vo.getPerformance3Bnum());
		iaFollowUpDepartment.setPerformance3Date(DateConstant.convertStrDDMMYYYYToDate(vo.getPerformance3Date()));
		iaFollowUpDepartment.setTrackResult3Bnum(vo.getTrackResult3Bnum());
		iaFollowUpDepartment.setTrackResult3Date(DateConstant.convertStrDDMMYYYYToDate(vo.getTrackResult3Date()));
		
		iaFollowUpDepartment.setStatus(vo.getStatus());
		if (StringUtils.isNotBlank(vo.getVersion())) {
			iaFollowUpDepartment.setVersion(Integer.parseInt(vo.getVersion()));
		}
		iaFollowUpDepartmentRepository.save(iaFollowUpDepartment);
	}

	public List<LabelValueBean> getStatusDropdown() {
		List<LabelValueBean> dropdownList = new ArrayList<>();
		List<Lov> statusList  = ApplicationCache.getListOfValueByValueType(REGIS_TRACK_CONTROL_STATUS);
		for (Lov lov : statusList) {
			dropdownList.add(new LabelValueBean(lov.getValue2(), lov.getValue1()));
		}
		
		return dropdownList;
	}
	
	public Int112Vo findById(Long id) {
		IaFollowUpDepartment iaFollowUpDepartment = iaFollowUpDepartmentRepository.findOne(id);
		
		if (iaFollowUpDepartment == null) {
			iaFollowUpDepartment = new IaFollowUpDepartment();
		}
		
		Int112Vo vo = new Int112Vo();
		vo.setFollowUpDepartmentId(iaFollowUpDepartment.getFollowUpDepartmentId().toString());
		vo.setExciseDepartment(iaFollowUpDepartmentDao.queryLovIdSysLovByValue3(iaFollowUpDepartment.getExciseDepartment()));
		vo.setExciseRegion(iaFollowUpDepartmentDao.queryLovIdSysLovBySubTypeDescription(iaFollowUpDepartment.getExciseRegion()));
		vo.setExciseDistrict(iaFollowUpDepartmentDao.queryLovIdSysLov(iaFollowUpDepartment.getExciseDistrict()));
		vo.setInformRectorBnum(iaFollowUpDepartment.getInformRectorBnum());
		vo.setInformRectorDate(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpDepartment.getInformRectorDate()));
		vo.setFollowUp1Bnum(iaFollowUpDepartment.getFollowUp1Bnum());
		vo.setFollowUp1Date(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpDepartment.getFollowUp1Date()));
		vo.setMaturity145(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpDepartment.getMaturity145()));
		vo.setMaturity160(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpDepartment.getMaturity160()));
		vo.setPerformance1Bnum(iaFollowUpDepartment.getPerformance1Bnum());
		vo.setPerformance1Date(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpDepartment.getPerformance1Date()));
		vo.setTrackResult1Bnum(iaFollowUpDepartment.getTrackResult1Bnum());
		vo.setTrackResult1Date(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpDepartment.getTrackResult1Date()));
		vo.setFollowUp2Bnum(iaFollowUpDepartment.getFollowUp2Bnum());
		vo.setFollowUp2Date(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpDepartment.getFollowUp2Date()));
		vo.setMaturity260(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpDepartment.getMaturity260()));
		vo.setPerformance2Bnum(iaFollowUpDepartment.getPerformance2Bnum());
		vo.setPerformance2Date(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpDepartment.getPerformance2Date()));
		vo.setTrackResult2Bnum(iaFollowUpDepartment.getTrackResult2Bnum());
		vo.setTrackResult2Date(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpDepartment.getTrackResult2Date()));
		vo.setFollowUp3Bnum(iaFollowUpDepartment.getFollowUp3Bnum());
		vo.setFollowUp3Date(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpDepartment.getFollowUp3Date()));
		vo.setMaturity360(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpDepartment.getMaturity360()));
		vo.setPerformance3Bnum(iaFollowUpDepartment.getPerformance3Bnum());
		vo.setPerformance3Date(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpDepartment.getPerformance3Date()));
		vo.setTrackResult3Bnum(iaFollowUpDepartment.getTrackResult3Bnum());
		vo.setTrackResult3Date(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpDepartment.getTrackResult3Date()));
		vo.setStatus(iaFollowUpDepartment.getStatus());
		vo.setVersion(iaFollowUpDepartment.getVersion().toString());
	
		return vo;
	}
	
	public void delete(List<Long> ids) {
		List<IaFollowUpDepartment> followUpDepartmentList = new ArrayList<>();
	
		for (Long id : ids) {
			log.info("Delete IaFollowUpDepartment Id : {}", id);
			IaFollowUpDepartment followUpDepartment = iaFollowUpDepartmentRepository.findOne(id);
			if (followUpDepartment != null) {
				followUpDepartment.setIsDeleted(FLAG.Y_FLAG);
				followUpDepartmentList.add(followUpDepartment);
			}
		}
		iaFollowUpDepartmentRepository.save(followUpDepartmentList);
	}
	
	public void closeJob(Int112FormVo vo) {
		Long id = Long.parseLong(vo.getFollowUpDepartmentId());
		log.info("Close Job IaFollowUpDepartment Id : {}", id);
		
		IaFollowUpDepartment followUpDepartment = iaFollowUpDepartmentRepository.findOne(id);
		if (followUpDepartment != null) {
			followUpDepartment.setStatus(STATUS.COMPLETE_DESC);
		}
		
		iaFollowUpDepartmentRepository.save(followUpDepartment);
		
	}
	
	public void notecloseJob(Int112Form form) {
		IaFollowUpDepartment iaFollow = iaFollowUpDepartmentRepository.findOne(Long.valueOf(form.getId()));
		iaFollow.setNote(form.getNote());
		iaFollow.setStatus(STATUS.COMPLETE_DESC);
		iaFollowUpDepartmentRepository.save(iaFollow);
	}
	
	
	public void shiftDate(Int11ShiftDateVo vo) {
		Date date = DateConstant.convertStrDDMMYYYYToDate(vo.getDate());
		Date shift45Date = DateUtils.addDays(date, 45);
		Date shift60Date = DateUtils.addDays(date, 60);
		vo.setShift45Date(DateConstant.convertDateToStrDDMMYYYY(shift45Date));
		vo.setShift60Date(DateConstant.convertDateToStrDDMMYYYY(shift60Date));
	}
	
/*	public FileSystemResource exportFile(Int112FormVo formVo) throws Exception {
		File file = File.createTempFile("temp", ".xlsx");
		
		Workbook workbook = new XSSFWorkbook();

//		this.createWorkSheetExcel(workbook);
		
		List<Int112Vo> exportDataList = iaFollowUpDepartmentDao.queryExportData(formVo);
		
		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream(file);
		workbook.write(fileOut);
		workbook.close();
		fileOut.close();
		log.info("finish write file.");

		return new FileSystemResource(file);
	}*/
	
/*	private void createWorkSheetExcel(Workbook workbook, String pageSheet, Integer tranId, String fileId) throws Exception {
		
	}*/
	
	public void exportFollowUpDepartment(Int112FormVo formVo, HttpServletResponse response) throws IOException {
		
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
		String[] tbTH1 = { "ลำดับ", "สำนักงานสรรพสามิต", "รายงานผลอธิบดี", "", "แจ้งติดตามหน่วยรับตรวจครั้งที่ 1", "",
				"วันครบกำหนดครั้งที่ 1", "", "หน่วยรับตรวจแจ้งผลการดำเนินงานครั้งที่ 1", "",
				"รายงานการติดตามครั้งที่ 1", "", "แจ้งติดตามหน่วยรับตรวจครั้งที่ 2", "", "วันครบกำหนดครั้งที่2",
				"หน่วยรับตรวจแจ้งผลการดำเนินงานครั้งที่ 2", "", "รายงานการติดตามครั้งที่ 2", "", "แจ้งติดตามหน่วยรับตรวจครั้งที่ 3", "", "วันครบกำหนดครั้งที่3",
				"หน่วยรับตรวจแจ้งผลการดำเนินงานครั้งที่ 3", "", "รายงานการติดตามครั้งที่ 3","", "สถานะการติดตาม","หมายเหตุ" };
		for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[cellNum]);
			cell.setCellStyle(thStyle);
		};

		String[] tbTH2 = { "เลขหนังสือ", "วันที่", "เลขหนังสือ", "วันที่", "45 วัน", "60 วัน", "เลขหนังสือ", "วันที่",
				"เลขหนังสือ", "วันที่", "เลขหนังสือ", "วันที่", "60 วัน", "เลขหนังสือ", "วันที่", "เลขหนังสือ",
				"วันที่", "เลขหนังสือ", "วันที่", "60 วัน", "เลขหนังสือ", "วันที่", "เลขหนังสือ",
				"วันที่" };
		row = sheet.createRow(1);
		int cellNumtbTH2 = 2;
		for (int i = 0; i < tbTH2.length; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellValue(tbTH2[i]);
			cell.setCellStyle(thStyle);
			cellNumtbTH2++;
		};
		
		cell = row.createCell(0);cell.setCellStyle(thStyle);
		cell = row.createCell(1);cell.setCellStyle(thStyle);
		cell = row.createCell(26);cell.setCellStyle(thStyle);
		cell = row.createCell(27);cell.setCellStyle(thStyle);

		/* set sheet */
		// setColumnWidth
		for (int i = 1; i <= 27; i++) {
			if (i == 1) {
				// วันครบกำหนดครั้งที่2
				sheet.setColumnWidth(i, 76 * 255);
			} else if (i == 27) {
				// โครงการ => รายงานการติดตามครั้งที่ 2
				sheet.setColumnWidth(i, 76 * 80);
			} else if (i != 1 && i != 27)
				// การติดตาม
				sheet.setColumnWidth(i, 76 * 70);
		}

		// merge(firstRow, lastRow, firstCol, lastCol)
		int firstCol = 2;
		while (firstCol < 12) {
			for (int lastCol = 3; lastCol <= 13; lastCol += 2) {
				sheet.addMergedRegion(new CellRangeAddress(0, 0, firstCol, lastCol));
				firstCol += 2;
			}
		}
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 15, 16));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 17, 18));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 19, 20));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 22, 23));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 24, 25));
		
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 26,26));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 27,27));
		/* End set sheet */
		

		/* Detail */
		List<Int112Vo> exportDataList = null;
		if( StringUtils.isNotBlank(formVo.getExciseDepartment())|| StringUtils.isNotBlank(formVo.getExciseRegion())|| StringUtils.isNotBlank(formVo.getExciseDistrict())||StringUtils.isNotBlank(formVo.getStatus()) ) {
			exportDataList = iaFollowUpDepartmentDao.searchCriteria(formVo);
		}else if(StringUtils.isBlank(formVo.getExciseDepartment()) && StringUtils.isBlank(formVo.getExciseRegion())&& StringUtils.isBlank(formVo.getExciseDistrict())&& StringUtils.isBlank(formVo.getStatus())) {
			exportDataList = iaFollowUpDepartmentDao.queryExportData(formVo);
		}
		
		rowNum = 2;
		cellNum = 0;
		int no = 1;
		for (Int112Vo detail : exportDataList) {
			row = sheet.createRow(rowNum);
			// No.
			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(detail.getExciseDepartment()+" "+detail.getExciseRegion()+" "+detail.getExciseDistrict());
			cell.setCellStyle(cellLeft);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getInformRectorBnum())) {
				cell.setCellValue(detail.getInformRectorBnum());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getInformRectorDate())) {
				cell.setCellValue(detail.getInformRectorDate());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getFollowUp1Bnum())) {
				cell.setCellValue(detail.getFollowUp1Bnum());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getFollowUp1Date())) {
				cell.setCellValue(detail.getFollowUp1Date());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getMaturity145())) {
				cell.setCellValue(detail.getMaturity145());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getMaturity160())) {
				cell.setCellValue(detail.getMaturity160());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getPerformance1Bnum())) {
				cell.setCellValue(detail.getPerformance1Bnum());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getPerformance1Date())) {
				cell.setCellValue(detail.getPerformance1Date());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getTrackResult1Bnum())) {
				cell.setCellValue(detail.getTrackResult1Bnum());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getTrackResult1Date())) {
				cell.setCellValue(detail.getTrackResult1Date());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getFollowUp2Bnum())) {
				cell.setCellValue(detail.getFollowUp2Bnum());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getFollowUp2Date())) {
				cell.setCellValue(detail.getFollowUp2Date());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getMaturity260())) {
				cell.setCellValue(detail.getMaturity260());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getPerformance2Bnum())) {
				cell.setCellValue(detail.getPerformance2Bnum());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getPerformance2Date())) {
				cell.setCellValue(detail.getPerformance2Date());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getTrackResult2Bnum())) {
				cell.setCellValue(detail.getTrackResult2Bnum());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getTrackResult2Date())) {
				cell.setCellValue(detail.getTrackResult2Date());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getFollowUp3Bnum())) {
				cell.setCellValue(detail.getFollowUp3Bnum());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getFollowUp3Date())) {
				cell.setCellValue(detail.getFollowUp3Date());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getMaturity360())) {
				cell.setCellValue(detail.getMaturity360());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getPerformance3Bnum())) {
				cell.setCellValue(detail.getPerformance3Bnum());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getPerformance3Date())) {
				cell.setCellValue(detail.getPerformance3Date());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getTrackResult3Bnum())) {
				cell.setCellValue(detail.getTrackResult3Bnum());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getTrackResult3Date())) {
				cell.setCellValue(detail.getTrackResult3Date());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getStatus())) {
				cell.setCellValue(detail.getStatus());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if(StringUtils.isNotBlank(detail.getNote())) {
				cell.setCellValue(detail.getNote());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			no++;
			rowNum++;
			cellNum = 0;
		}
		
		
		/*set	fileName*/		
		String fileName ="FollowUpDepartment";
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
