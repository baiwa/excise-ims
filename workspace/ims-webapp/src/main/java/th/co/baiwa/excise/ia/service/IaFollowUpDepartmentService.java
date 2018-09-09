package th.co.baiwa.excise.ia.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.constant.IaConstant.IA_REGIS_TRACK_CONTROL.STATUS;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.ia.persistence.dao.IaFollowUpDepartmentDao;
import th.co.baiwa.excise.ia.persistence.entity.IaFollowUpDepartment;
import th.co.baiwa.excise.ia.persistence.repository.IaFollowUpDepartmentRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int112FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int112Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int11ShiftDateVo;

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
		dropdownList.add(new LabelValueBean(STATUS.TRACKING_FIRST_DESC, STATUS.TRACKING_FIRST_DESC));
		dropdownList.add(new LabelValueBean(STATUS.RESULT_OPT_FIRST_DESC, STATUS.RESULT_OPT_FIRST_DESC));
		dropdownList.add(new LabelValueBean(STATUS.REPORT_TRACKING_FIRST_DESC, STATUS.REPORT_TRACKING_FIRST_DESC));
		dropdownList.add(new LabelValueBean(STATUS.TRACKING_SECOND_DESC, STATUS.TRACKING_SECOND_DESC));
		dropdownList.add(new LabelValueBean(STATUS.RESULT_OPT_SECOND_DESC, STATUS.RESULT_OPT_SECOND_DESC));
		dropdownList.add(new LabelValueBean(STATUS.REPORT_TRACKING_SECOND_DESC, STATUS.REPORT_TRACKING_SECOND_DESC));
		dropdownList.add(new LabelValueBean(STATUS.COMPLETE_DESC, STATUS.COMPLETE_DESC));
		return dropdownList;
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
		
		iaFollowUpDepartment.setStatus(vo.getStatus());
		if (StringUtils.isNotBlank(vo.getVersion())) {
			iaFollowUpDepartment.setVersion(Integer.parseInt(vo.getVersion()));
		}
		iaFollowUpDepartmentRepository.save(iaFollowUpDepartment);
	}

	public Int112Vo findById(Long id) {
		IaFollowUpDepartment iaFollowUpDepartment = iaFollowUpDepartmentRepository.findOne(id);
		
		if (iaFollowUpDepartment == null) {
			iaFollowUpDepartment = new IaFollowUpDepartment();
		}
		
		Int112Vo vo = new Int112Vo();
		vo.setFollowUpDepartmentId(iaFollowUpDepartment.getFollowUpDepartmentId().toString());
		vo.setExciseDepartment(iaFollowUpDepartmentDao.queryLovIdSysLovByValue1(iaFollowUpDepartment.getExciseDepartment()));
		vo.setExciseRegion(iaFollowUpDepartmentDao.queryLovIdSysLovByValue1(iaFollowUpDepartment.getExciseRegion()));
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
	
	public void shiftDate(Int11ShiftDateVo vo) {
		Date date = DateConstant.convertStrDDMMYYYYToDate(vo.getDate());
		Date shift45Date = DateUtils.addDays(date, 45);
		Date shift60Date = DateUtils.addDays(date, 60);
		vo.setShift45Date(DateConstant.convertDateToStrDDMMYYYY(shift45Date));
		vo.setShift60Date(DateConstant.convertDateToStrDDMMYYYY(shift60Date));
	}
	
	public FileSystemResource exportFile(Int112FormVo formVo) throws Exception {
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
	}
	
	private void createWorkSheetExcel(Workbook workbook, String pageSheet, Integer tranId, String fileId) throws Exception {
		
	}
}
