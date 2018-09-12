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

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.constant.IaConstant.IA_REGIS_TRACK_CONTROL.STATUS;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.ia.persistence.dao.IaFollowUpProjectDao;
import th.co.baiwa.excise.ia.persistence.entity.IaFollowUpProject;

import th.co.baiwa.excise.ia.persistence.repository.IaFollowUpProjectRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int111FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int111Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int11ShiftDateVo;

@Service
public class IaFollowUpProjectService {
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

	
	private Logger log = LoggerFactory.getLogger(IaFollowUpProjectService.class);
	
	
	@Autowired
	private IaFollowUpProjectDao iaFollowUpProjectDao;
	
	@Autowired
	private IaFollowUpProjectRepository iaFollowUpProjectRepository;
	
	public ResponseDataTable<Int111Vo> searchIaFollowUpProject(Int111FormVo formVo) {
		ResponseDataTable<Int111Vo> response = new ResponseDataTable<Int111Vo>();
		List<Int111Vo> iaFollowUpProjectList = iaFollowUpProjectDao.searchCriteria(formVo);
		response.setData(iaFollowUpProjectList);
		return response;
	}
	
	public void saveOrUpdate(Int111FormVo vo) {
		log.info("SaveOrUpdate IaFollowUpproject Project Name : {}", vo.getProjectName());
		IaFollowUpProject iaFollowUpProject = new IaFollowUpProject();
		if (StringUtils.isNotBlank(vo.getFollowUpProjectId())) {
			iaFollowUpProject.setFollowUpProjectId(Long.valueOf(vo.getFollowUpProjectId()));
			iaFollowUpProject.setIsDeleted(FLAG.N_FLAG);
		}
		iaFollowUpProject.setProjectName(vo.getProjectName());
		iaFollowUpProject.setInformRectorBnum(vo.getInformRectorBnum());
		iaFollowUpProject.setInformRectorDate(DateConstant.convertStrDDMMYYYYToDate(vo.getInformRectorDate()));
		iaFollowUpProject.setFollowUp1Bnum(vo.getFollowUp1Bnum());
		iaFollowUpProject.setFollowUp1Date(DateConstant.convertStrDDMMYYYYToDate(vo.getFollowUp1Date()));
		iaFollowUpProject.setMaturity145(DateConstant.convertStrDDMMYYYYToDate(vo.getMaturity145()));
		iaFollowUpProject.setMaturity160(DateConstant.convertStrDDMMYYYYToDate(vo.getMaturity160()));
		iaFollowUpProject.setPerformance1Bnum(vo.getPerformance1Bnum());
		iaFollowUpProject.setPerformance1Date(DateConstant.convertStrDDMMYYYYToDate(vo.getPerformance1Date()));
		iaFollowUpProject.setTrackResult1Bnum(vo.getTrackResult1Bnum());
		iaFollowUpProject.setTrackResult1Date(DateConstant.convertStrDDMMYYYYToDate(vo.getTrackResult1Date()));
		iaFollowUpProject.setFollowUp2Bnum(vo.getFollowUp2Bnum());
		iaFollowUpProject.setFollowUp2Date(DateConstant.convertStrDDMMYYYYToDate(vo.getFollowUp2Date()));
		iaFollowUpProject.setMaturity260(DateConstant.convertStrDDMMYYYYToDate(vo.getMaturity260()));
		iaFollowUpProject.setPerformance2Bnum(vo.getPerformance2Bnum());
		iaFollowUpProject.setPerformance2Date(DateConstant.convertStrDDMMYYYYToDate(vo.getPerformance2Date()));
		iaFollowUpProject.setTrackResult2Bnum(vo.getTrackResult2Bnum());
		iaFollowUpProject.setTrackResult2Date(DateConstant.convertStrDDMMYYYYToDate(vo.getTrackResult2Date()));
		
		iaFollowUpProject.setStatus(vo.getStatus());
		if (StringUtils.isNotBlank(vo.getVersion())) {
			iaFollowUpProject.setVersion(Integer.parseInt(vo.getVersion()));
		}
		iaFollowUpProjectRepository.save(iaFollowUpProject);
	}
	
	public List<LabelValueBean> getStatusDropdown() {
		List<LabelValueBean> dropdownList = new ArrayList<>();
		dropdownList.add(new LabelValueBean(STATUS.TRACKING_FIRST_DESC, STATUS.TRACKING_FIRST_DESC));
		dropdownList.add(new LabelValueBean(STATUS.RESULT_OPT_FIRST_DESC, STATUS.RESULT_OPT_FIRST_DESC));
		dropdownList.add(new LabelValueBean(STATUS.REPORT_TRACKING_FIRST_DESC, STATUS.REPORT_TRACKING_FIRST_DESC));
		dropdownList.add(new LabelValueBean(STATUS.TRACKING_SECOND_DESC, STATUS.TRACKING_SECOND_DESC));
		dropdownList.add(new LabelValueBean(STATUS.RESULT_OPT_SECOND_DESC, STATUS.RESULT_OPT_SECOND_DESC));
		dropdownList.add(new LabelValueBean(STATUS.REPORT_TRACKING_SECOND_DESC, STATUS.REPORT_TRACKING_SECOND_DESC));
		dropdownList.add(new LabelValueBean(STATUS.COMPLETE_DESC, STATUS.COMPLETE_DESC));
		return dropdownList;
	}
	
	public Int111Vo findById(Long id) {
		IaFollowUpProject iaFollowUpProject = iaFollowUpProjectRepository.findOne(id);
		
		if (iaFollowUpProject == null) {
			iaFollowUpProject = new IaFollowUpProject();
		}
		
		Int111Vo vo = new Int111Vo();
		vo.setFollowUpProjectId(iaFollowUpProject.getFollowUpProjectId().toString());
		vo.setProjectName(iaFollowUpProject.getProjectName());
		vo.setInformRectorBnum(iaFollowUpProject.getInformRectorBnum());
		vo.setInformRectorDate(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpProject.getInformRectorDate()));
		vo.setFollowUp1Bnum(iaFollowUpProject.getFollowUp1Bnum());
		vo.setFollowUp1Date(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpProject.getFollowUp1Date()));
		vo.setMaturity145(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpProject.getMaturity145()));
		vo.setMaturity160(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpProject.getMaturity160()));
		vo.setPerformance1Bnum(iaFollowUpProject.getPerformance1Bnum());
		vo.setPerformance1Date(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpProject.getPerformance1Date()));
		vo.setTrackResult1Bnum(iaFollowUpProject.getTrackResult1Bnum());
		vo.setTrackResult1Date(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpProject.getTrackResult1Date()));
		vo.setFollowUp2Bnum(iaFollowUpProject.getFollowUp2Bnum());
		vo.setFollowUp2Date(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpProject.getFollowUp2Date()));
		vo.setMaturity260(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpProject.getMaturity260()));
		vo.setPerformance2Bnum(iaFollowUpProject.getPerformance2Bnum());
		vo.setPerformance2Date(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpProject.getPerformance2Date()));
		vo.setTrackResult2Bnum(iaFollowUpProject.getTrackResult2Bnum());
		vo.setTrackResult2Date(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpProject.getTrackResult2Date()));
		vo.setStatus(iaFollowUpProject.getStatus());
		vo.setVersion(iaFollowUpProject.getVersion().toString());
	
		return vo;
	}
	
	public void delete(List<Long> ids) {
		List<IaFollowUpProject> followUpProjectList = new ArrayList<>();
	
		for (Long id : ids) {
			log.info("Delete IaFollowUpproject Id : {}", id);
			IaFollowUpProject followUpProject = iaFollowUpProjectRepository.findOne(id);
			if (followUpProject != null) {
				followUpProject.setIsDeleted(FLAG.Y_FLAG);
				followUpProjectList.add(followUpProject);
			}
		}
		iaFollowUpProjectRepository.save(followUpProjectList);
	}
	
	public void closeJob(Int111FormVo vo) {
		Long id = Long.parseLong(vo.getFollowUpProjectId());
		log.info("Close Job IaFollowUpproject Id : {}", id);
		
		IaFollowUpProject followUpProject = iaFollowUpProjectRepository.findOne(id);
		if (followUpProject != null) {
			followUpProject.setStatus(STATUS.COMPLETE_DESC);
		}
		
		iaFollowUpProjectRepository.save(followUpProject);
		
	}
	
	public void shiftDate(Int11ShiftDateVo vo) {
		Date date = DateConstant.convertStrDDMMYYYYToDate(vo.getDate());
		Date shift45Date = DateUtils.addDays(date, 45);
		Date shift60Date = DateUtils.addDays(date, 60);
		vo.setShift45Date(DateConstant.convertDateToStrDDMMYYYY(shift45Date));
		vo.setShift60Date(DateConstant.convertDateToStrDDMMYYYY(shift60Date));
	}
	
/*	public FileSystemResource exportFile(Int111FormVo formVo) throws Exception {
		File file = File.createTempFile("temp", ".xlsx");
		
		Workbook workbook = new XSSFWorkbook();

		List<Int111Vo> exportDataList = iaFollowUpProjectDao.queryExportData(formVo);
		
//		this.createWorkSheetExcel(workbook);
		
		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream(file);
		workbook.write(fileOut);
		workbook.close();
		fileOut.close();
		log.info("finish write file.");

		return new FileSystemResource(file);
	}*/
	
/*	private void createWorkSheetExcel(Workbook workbook) throws Exception {

	}*/

	public void exportExcelByToffee(Int111FormVo formVo, HttpServletResponse response) throws IOException {
		
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
		String[] tbTH1 = { "ลำดับ", "โครงการ", "รายงานผลอธิบดี", "", "แจ้งติดตามหน่วยรับตรวจครั้งที่ 1", "",
				"วันครบกำหนดครั้งที่ 1", "", "หน่วยรับตรวจแจ้งผลการดำเนินงานครั้งที่ 1", "",
				"รายงานการติดตามครั้งที่ 1", "", "แจ้งติดตามหน่วยรับตรวจครั้งที่ 2", "", "วันครบกำหนดครั้งที่2",
				"หน่วยรับตรวจแจ้งผลการดำเนินงานครั้งที่ 2", "", "รายงานการติดตามครั้งที่ 2", "", "สถานะการติดตาม" };
		for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[cellNum]);
			cell.setCellStyle(thStyle);
		};

		String[] tbTH2 = { "เลขหนังสือ", "วันที่", "เลขหนังสือ", "วันที่", "45 วัน", "60 วัน", "เลขหนังสือ", "วันที่",
				"เลขหนังสือ", "วันที่", "เลขหนังสือ", "วันที่", "60 วัน", "เลขหนังสือ", "วันที่", "เลขหนังสือ",
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
		cell = row.createCell(19);cell.setCellStyle(thStyle);

		/* set sheet */
		// setColumnWidth
		for (int i = 1; i <= 19; i++) {
			if (i == 1) {
				// วันครบกำหนดครั้งที่2
				sheet.setColumnWidth(i, 76 * 255);
			} else if (i == 19) {
				// โครงการ => รายงานการติดตามครั้งที่ 2
				sheet.setColumnWidth(i, 76 * 150);
			} else if (i != 1 && i != 19)
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
		
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(0, 1, 19,19));
		/* End set sheet */
		
		
		/* Detail */
		List<Int111Vo> exportDataList = null;
		if( StringUtils.isNotBlank(formVo.getProjectName())||StringUtils.isNotBlank(formVo.getStatus())) {
			exportDataList = iaFollowUpProjectDao.searchCriteria(formVo);
		}else if(StringUtils.isBlank(formVo.getProjectName()) && StringUtils.isBlank(formVo.getStatus())) {
			exportDataList = iaFollowUpProjectDao.queryExportData(formVo);
		}
		
		rowNum = 2;
		cellNum = 0;
		int no = 1;
		for (Int111Vo detail : exportDataList) {
			row = sheet.createRow(rowNum);
			// No.
			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(detail.getProjectName());
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
			if(StringUtils.isNotBlank(detail.getStatus())) {
				cell.setCellValue(detail.getStatus());
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
