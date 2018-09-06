package th.co.baiwa.excise.ia.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
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
import th.co.baiwa.excise.ia.persistence.dao.IaFollowUpProjectDao;
import th.co.baiwa.excise.ia.persistence.entity.IaFollowUpProject;
import th.co.baiwa.excise.ia.persistence.repository.IaFollowUpProjectRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int111FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int111Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int11ShiftDateVo;

@Service
public class IaFollowUpProjectService {

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
	
	public FileSystemResource exportFile(Int111FormVo formVo) throws Exception {
		File file = File.createTempFile("temp", ".xlsx");
		
		Workbook workbook = new XSSFWorkbook();

		this.createWorkSheetExcel(workbook);
		
		// Write the output to a file
		FileOutputStream fileOut = new FileOutputStream(file);
		workbook.write(fileOut);
		workbook.close();
		fileOut.close();
		log.info("finish write file.");

		return new FileSystemResource(file);
	}
	
	private void createWorkSheetExcel(Workbook workbook) throws Exception {

	}
}
