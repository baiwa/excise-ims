package th.co.baiwa.excise.ia.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.excise.constant.IaConstant.IA_REGIS_TRACK_CONTROL.STATUS;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.ia.persistence.dao.IaFollowUpDepartmentDao;
import th.co.baiwa.excise.ia.persistence.repository.IaFollowUpDepartmentRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int112FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int112Vo;

@Service
public class IaFollowUpDepartmentService {

	private Logger log = LoggerFactory.getLogger(IaFollowUpDepartmentService.class);
	
	@Autowired
	private IaFollowUpDepartmentDao iaFollowUpDepartmentDao;
	
	@Autowired
	private IaFollowUpDepartmentRepository iaFollowUpDepartmentRepository;
	
	public ResponseDataTable<Int112Vo> searchIaFollowUpDepartment(Int112FormVo formVo) {
		ResponseDataTable<Int112Vo> response = new ResponseDataTable<Int112Vo>();
		List<Int112Vo> iaFollowUpDepartmentList = iaFollowUpDepartmentDao.searchCriteria(formVo);
		response.setData(iaFollowUpDepartmentList);
		return response;
	}
	
	public List<LabelValueBean> getStatusDropdown() {
		List<LabelValueBean> dropdownList = new ArrayList<>();
		dropdownList.add(new LabelValueBean(STATUS.TRACKING_FIRST_DESC, STATUS.TRACKING_FIRST_CODE));
		dropdownList.add(new LabelValueBean(STATUS.RESULT_OPT_FIRST_DESC, STATUS.RESULT_OPT_FIRST_CODE));
		dropdownList.add(new LabelValueBean(STATUS.REPORT_TRACKING_FIRST_DESC, STATUS.REPORT_TRACKING_FIRST_CODE));
		dropdownList.add(new LabelValueBean(STATUS.TRACKING_SECOND_DESC, STATUS.TRACKING_SECOND_CODE));
		dropdownList.add(new LabelValueBean(STATUS.RESULT_OPT_SECOND_DESC, STATUS.RESULT_OPT_SECOND_CODE));
		dropdownList.add(new LabelValueBean(STATUS.REPORT_TRACKING_SECOND_DESC, STATUS.REPORT_TRACKING_SECOND_CODE));
		dropdownList.add(new LabelValueBean(STATUS.COMPLETE_DESC, STATUS.COMPLETE_CODE));
		return dropdownList;
	}
	
	public List<LabelValueBean> getDepartmentDropdown() {
		return iaFollowUpDepartmentDao.queryDeapertment();
	}
	
	public List<LabelValueBean> getRegionDropdown(String id) {
		return iaFollowUpDepartmentDao.queryRegion(id);
	}
	
//	public void saveOrUpdate(Int111FormVo vo) {
//		log.info("SaveOrUpdate IaFollowUpproject Project Name : {}", vo.getProjectName());
//		IaFollowUpProject iaFollowUpProject = new IaFollowUpProject();
//		if (StringUtils.isNotBlank(vo.getFollowUpProjectId())) {
//			iaFollowUpProject.setFollowUpProjectId(Long.valueOf(vo.getFollowUpProjectId()));
//			iaFollowUpProject.setIsDeleted(FLAG.N_FLAG);
//		}
//		iaFollowUpProject.setProjectName(vo.getProjectName());
//		iaFollowUpProject.setInformRectorBnum(vo.getInformRectorBnum());
//		iaFollowUpProject.setInformRectorDate(DateConstant.convertStrDDMMYYYYToDate(vo.getInformRectorDate()));
//		iaFollowUpProject.setFollowUp1Bnum(vo.getFollowUp1Bnum());
//		iaFollowUpProject.setFollowUp1Date(DateConstant.convertStrDDMMYYYYToDate(vo.getFollowUp1Date()));
//		iaFollowUpProject.setMaturity145(DateConstant.convertStrDDMMYYYYToDate(vo.getMaturity145()));
//		iaFollowUpProject.setMaturity160(DateConstant.convertStrDDMMYYYYToDate(vo.getMaturity160()));
//		iaFollowUpProject.setPerformance1Bnum(vo.getPerformance1Bnum());
//		iaFollowUpProject.setPerformance1Date(DateConstant.convertStrDDMMYYYYToDate(vo.getPerformance1Date()));
//		iaFollowUpProject.setTrackResult1Bnum(vo.getTrackResult1Bnum());
//		iaFollowUpProject.setTrackResult1Date(DateConstant.convertStrDDMMYYYYToDate(vo.getTrackResult1Date()));
//		iaFollowUpProject.setFollowUp2Bnum(vo.getFollowUp2Bnum());
//		iaFollowUpProject.setFollowUp2Date(DateConstant.convertStrDDMMYYYYToDate(vo.getFollowUp2Date()));
//		iaFollowUpProject.setMaturity260(DateConstant.convertStrDDMMYYYYToDate(vo.getMaturity260()));
//		iaFollowUpProject.setPerformance2Bnum(vo.getPerformance2Bnum());
//		iaFollowUpProject.setPerformance2Date(DateConstant.convertStrDDMMYYYYToDate(vo.getPerformance2Date()));
//		iaFollowUpProject.setTrackResult2Bnum(vo.getTrackResult2Bnum());
//		iaFollowUpProject.setTrackResult2Date(DateConstant.convertStrDDMMYYYYToDate(vo.getTrackResult2Date()));
//		
//		iaFollowUpProject.setStatus(vo.getStatus());
//		if (StringUtils.isNotBlank(vo.getVersion())) {
//			iaFollowUpProject.setVersion(Integer.parseInt(vo.getVersion()));
//		}
//		iaFollowUpProjectRepository.save(iaFollowUpProject);
//	}
//	
//	public List<LabelValueBean> getStatusDropdown() {
//		List<LabelValueBean> dropdownList = new ArrayList<>();
//		dropdownList.add(new LabelValueBean(STATUS.TRACKING_FIRST_DESC, STATUS.TRACKING_FIRST_CODE));
//		dropdownList.add(new LabelValueBean(STATUS.RESULT_OPT_FIRST_DESC, STATUS.RESULT_OPT_FIRST_CODE));
//		dropdownList.add(new LabelValueBean(STATUS.REPORT_TRACKING_FIRST_DESC, STATUS.REPORT_TRACKING_FIRST_CODE));
//		dropdownList.add(new LabelValueBean(STATUS.TRACKING_SECOND_DESC, STATUS.TRACKING_SECOND_CODE));
//		dropdownList.add(new LabelValueBean(STATUS.RESULT_OPT_SECOND_DESC, STATUS.RESULT_OPT_SECOND_CODE));
//		dropdownList.add(new LabelValueBean(STATUS.REPORT_TRACKING_SECOND_DESC, STATUS.REPORT_TRACKING_SECOND_CODE));
//		dropdownList.add(new LabelValueBean(STATUS.COMPLETE_DESC, STATUS.COMPLETE_CODE));
//		return dropdownList;
//	}
//	
//	public Int111Vo findById(Long id) {
//		IaFollowUpProject iaFollowUpProject = iaFollowUpProjectRepository.findOne(id);
//		
//		if (iaFollowUpProject == null) {
//			iaFollowUpProject = new IaFollowUpProject();
//		}
//		
//		Int111Vo vo = new Int111Vo();
//		vo.setFollowUpProjectId(iaFollowUpProject.getFollowUpProjectId().toString());
//		vo.setProjectName(iaFollowUpProject.getProjectName());
//		vo.setInformRectorBnum(iaFollowUpProject.getInformRectorBnum());
//		vo.setInformRectorDate(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpProject.getInformRectorDate()));
//		vo.setFollowUp1Bnum(iaFollowUpProject.getFollowUp1Bnum());
//		vo.setFollowUp1Date(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpProject.getFollowUp1Date()));
//		vo.setMaturity145(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpProject.getMaturity145()));
//		vo.setMaturity160(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpProject.getMaturity160()));
//		vo.setPerformance1Bnum(iaFollowUpProject.getPerformance1Bnum());
//		vo.setPerformance1Date(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpProject.getPerformance1Date()));
//		vo.setTrackResult1Bnum(iaFollowUpProject.getTrackResult1Bnum());
//		vo.setTrackResult1Date(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpProject.getTrackResult1Date()));
//		vo.setFollowUp2Bnum(iaFollowUpProject.getFollowUp2Bnum());
//		vo.setFollowUp2Date(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpProject.getFollowUp2Date()));
//		vo.setMaturity260(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpProject.getMaturity260()));
//		vo.setPerformance2Bnum(iaFollowUpProject.getPerformance2Bnum());
//		vo.setPerformance2Date(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpProject.getPerformance2Date()));
//		vo.setTrackResult2Bnum(iaFollowUpProject.getTrackResult2Bnum());
//		vo.setTrackResult2Date(DateConstant.convertDateToStrDDMMYYYY(iaFollowUpProject.getTrackResult2Date()));
//		vo.setStatus(iaFollowUpProject.getStatus());
//		vo.setVersion(iaFollowUpProject.getVersion().toString());
//	
//		return vo;
//	}
//	
//	public void delete(List<Long> ids) {
//		List<IaFollowUpProject> followUpProjectList = new ArrayList<>();
//	
//		for (Long id : ids) {
//			log.info("Delete IaFollowUpproject Id : {}", id);
//			IaFollowUpProject followUpProject = iaFollowUpProjectRepository.findOne(id);
//			if (followUpProject != null) {
//				followUpProject.setIsDeleted(FLAG.Y_FLAG);
//				followUpProjectList.add(followUpProject);
//			}
//		}
//		iaFollowUpProjectRepository.save(followUpProjectList);
//	}
//	
//	public void closeJob(Int111FormVo vo) {
//		Long id = Long.parseLong(vo.getFollowUpProjectId());
//		log.info("Close Job IaFollowUpproject Id : {}", id);
//		
//		IaFollowUpProject followUpProject = iaFollowUpProjectRepository.findOne(id);
//		if (followUpProject != null) {
//			followUpProject.setStatus(STATUS.COMPLETE_DESC);
//		}
//		
//		iaFollowUpProjectRepository.save(followUpProject);
//		
//	}
//	
//	public void shiftDate(Int11ShiftDateVo vo) {
//		Date date = DateConstant.convertStrDDMMYYYYToDate(vo.getDate());
//		Date shift45Date = DateUtils.addDays(date, 45);
//		Date shift60Date = DateUtils.addDays(date, 60);
//		vo.setShift45Date(DateConstant.convertDateToStrDDMMYYYY(shift45Date));
//		vo.setShift60Date(DateConstant.convertDateToStrDDMMYYYY(shift60Date));
//	}
	
	public FileSystemResource exportFile(Int112FormVo formVo) throws Exception {
		File file = File.createTempFile("temp", ".xlsx");
		
		Workbook workbook = new XSSFWorkbook();

//		this.createWorkSheetExcel(workbook);
		
		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream(file);
		workbook.write(fileOut);
		workbook.close();
		fileOut.close();
		log.info("finish write file.");

		return new FileSystemResource(file);
	}
	
	private void createWorkSheetExcel(Workbook workbook, String pageSheet, Integer tranId, String fileId) throws Exception {
//		String TOPIC = String.format("CDRs รายการโอนหนี้ T%s (#OS%s-FID:%s)", titleSheet, tranId, fileId);
//		String[] columns = this.HEADER_EXPORT_FILE;
//		String dateReport = DateFormatUtils.format(new Date(), "dd/MM/yyyy hh:mm:ss");
//		List<TransferringSummaryVo> summaryList = new ArrayList<>();
//		
//		// Style
//		// Header
//		Font fontHeader = workbook.createFont();
//		fontHeader.setBold(true);
//
//		Font headerFont = workbook.createFont();
//		headerFont.setBold(true);
//		headerFont.setColor(IndexedColors.RED.getIndex());
//		
//		// Date Display
//		CellStyle styleDate = workbook.createCellStyle();
//		styleDate.setAlignment(HorizontalAlignment.RIGHT);
//		styleDate.setFont(fontHeader);
//
//		// Topic Display
//		CellStyle styleTopic = workbook.createCellStyle();
//		styleTopic.setAlignment(HorizontalAlignment.CENTER);
//		styleTopic.setFont(fontHeader);
//
//		XSSFColor colorBlack = new XSSFColor(new Color(0, 0, 0));
//		// body header
//		CellStyle headerStyle = workbook.createCellStyle();
//		headerStyle.setFont(headerFont);
//		headerStyle.setBottomBorderColor(colorBlack.getIndex());
//		headerStyle.setLeftBorderColor(colorBlack.getIndex());
//		headerStyle.setRightBorderColor(colorBlack.getIndex());
//		headerStyle.setTopBorderColor(colorBlack.getIndex());
//		headerStyle.setBorderBottom(BorderStyle.THIN);
//		headerStyle.setBorderLeft(BorderStyle.THIN);
//		headerStyle.setBorderRight(BorderStyle.THIN);
//		headerStyle.setBorderTop(BorderStyle.THIN);
//		
//		// body border
//		CellStyle bodyStyle = workbook.createCellStyle();
//		bodyStyle.setBottomBorderColor(colorBlack.getIndex());
//		bodyStyle.setLeftBorderColor(colorBlack.getIndex());
//		bodyStyle.setRightBorderColor(colorBlack.getIndex());
//		bodyStyle.setTopBorderColor(colorBlack.getIndex());
//		bodyStyle.setBorderBottom(BorderStyle.THIN);
//		bodyStyle.setBorderLeft(BorderStyle.THIN);
//		bodyStyle.setBorderRight(BorderStyle.THIN);
//		bodyStyle.setBorderTop(BorderStyle.THIN);
//		
//		// End Style
//				
//		CreationHelper createHelper = workbook.getCreationHelper();
//		
//		Sheet sheet = workbook.createSheet("ตรวจสอบรอบบิล T" + pageSheet);
//
//		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 11));
//		sheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 11));
//		sheet.addMergedRegion(new CellRangeAddress(2, 2, 0, 11));
//
//		Row dateRow =  sheet.createRow(0);
//		Cell dateCell = dateRow.createCell(0);
//		dateCell.setCellValue(dateReport);
//		dateCell.setCellStyle(styleDate);
//				
//		Row topicRow =  sheet.createRow(1);
//		Cell topicCell = topicRow.createCell(0);
//		topicCell.setCellValue(TOPIC);
//		topicCell.setCellStyle(styleTopic);
//		
//		// Create a Row
//		Row headerRow = sheet.createRow(3);
//
//		// Creating cells
//		for (int i = 0; i < columns.length; i++) {
//			Cell cell = headerRow.createCell(i);
//			cell.setCellValue(columns[i]);
//			cell.setCellStyle(headerStyle);
//		}
//
//		// Create Cell Style for formatting Date
//		CellStyle dateCellStyle = workbook.createCellStyle();
//		dateCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("dd-MM-yyyy"));
//
//
//		int rowNum = 4;
//		if (!summaryList.isEmpty()) {
//			for (TransferringSummaryVo data : summaryList) {
////				Row row = sheet.createRow(rowNum++);
////				
////				Cell cell0 = row.createCell(0);
////				cell0.setCellValue(StringUtils.isNotBlank(data.getBa()) ? data.getBa() : "");
////				cell0.setCellStyle(bodyStyle);
////				
////				Cell cell1 = row.createCell(1);
////				cell1.setCellValue(StringUtils.isNotBlank(data.getIdTypeDisplay()) ? data.getIdTypeDisplay() : "");
////				cell1.setCellStyle(bodyStyle);
////				
////				Cell cell2 = row.createCell(2);
////				cell2.setCellValue(StringUtils.isNotBlank(data.getAcc()) ? data.getAcc() : "");
////				cell2.setCellStyle(bodyStyle);
////				
////				Cell cell3 = row.createCell(3);
////				cell3.setCellValue(StringUtils.isNotBlank(data.getPointOrigin()) ? data.getPointOrigin() : "");
////				cell3.setCellStyle(bodyStyle);
////				
////				Cell cell4 = row.createCell(4);
////				cell4.setCellValue(this.toCommarFormat(data.getRecs()));
////				cell4.setCellStyle(bodyStyle);
////				
////				Cell cell5 = row.createCell(5);
////				cell5.setCellValue(this.toMoneyFormat(data.getCharge()));
////				cell5.setCellStyle(bodyStyle);
////				
////				Cell cell6 = row.createCell(6);
////				cell6.setCellValue(this.toCommarFormat(data.getSecs()) );
////				cell6.setCellStyle(bodyStyle);
////				
////				Cell cell7 = row.createCell(7);
////				cell7.setCellValue(StringUtils.isNotBlank(data.getMinTransdt()) ? data.getMinTransdt() : "");
////				cell7.setCellStyle(bodyStyle);
////				
////				Cell cell8 = row.createCell(8);
////				cell8.setCellValue(StringUtils.isNotBlank(data.getMaxTransdt()) ? data.getMaxTransdt() : "");
////				cell8.setCellStyle(bodyStyle);
////				
////				Cell cell9 = row.createCell(9);
////				cell9.setCellValue(StringUtils.isNotBlank(data.getBperiod()) ? data.getBperiod() : "");
////				cell9.setCellStyle(bodyStyle);
////				
////				Cell cell10 = row.createCell(10);
////				cell10.setCellValue(StringUtils.isNotBlank(data.getRemark()) ? data.getRemark() : "");
////				cell10.setCellStyle(bodyStyle);
////				
////				String invoiceType = StringUtils.isNotBlank(data.getInvoiceType()) ? data.getInvoiceType() : "";
////				if ("1".equals(invoiceType)) {
////					invoiceType = "รวมบิล";
////				} else if ("0".equals(invoiceType)) {
////					invoiceType = "แยกบิล";
////				}
////				
////				Cell cell11 = row.createCell(11);
////				cell11.setCellValue(invoiceType);
////				cell11.setCellStyle(bodyStyle);
//			}
//		}
//
//		// Resize all columns to fit the content size
//		for (int i = 0; i < columns.length; i++) {
//			sheet.autoSizeColumn(i);
//		}
		
	}
}
