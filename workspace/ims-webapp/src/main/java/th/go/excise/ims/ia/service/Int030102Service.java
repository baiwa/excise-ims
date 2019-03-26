package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ExciseDept;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsStatus;
import th.go.excise.ims.ia.persistence.entity.IaRiskSelectCase;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsMasterRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsStatusRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskSelectCaseRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int030102JdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0401JdbcRepository;
import th.go.excise.ims.ia.vo.Int030102FormVo;
import th.go.excise.ims.ia.vo.Int030102Vo;

@Service
public class Int030102Service {

	@Autowired
	private Int030102JdbcRepository int030102JdbcRepository;
	
	@Autowired
	private UpdateStatusRiskFactorsService updateStatusRiskFactorsService;

	@Autowired
	private IaRiskFactorsMasterRepository iaRiskFactorsMasterRepository;

	@Autowired
	private IaRiskFactorsStatusRepository iaRiskFactorsStatusRepository;

	@Autowired
	private IaRiskFactorsRepository iaRiskFactorsRepository;

	@Autowired
	private IaRiskFactorsConfigRepository iaRiskFactorsConfigRepository;
	
	@Autowired
	private IaRiskSelectCaseRepository iaRiskSelectCaseRep;
	
	@Autowired
	private Int0401JdbcRepository int0401JdbcRepository;

	public List<Int030102Vo> list(Int030102FormVo form) {
		checkAndInsertTableFactorsStatus(form);
		List<Int030102Vo> iaRiskFactorsMasterList = new ArrayList<Int030102Vo>();
		iaRiskFactorsMasterList = int030102JdbcRepository.list(form);
		
		for(int i=3; i<=5;i++ ) {
			long count = int0401JdbcRepository.findCountRowWithoutStatus(ExciseUtils.getCurrentBudgetYear(), new BigDecimal(i));
			if (count == 0) {
				saveDataList(ExciseUtils.getCurrentBudgetYear(), new BigDecimal(i));
			}
		
		}
		
		return iaRiskFactorsMasterList;
	}
	
	public List<Int030102FormVo> budgetYearDropdown() {
		List<Int030102FormVo> response = int030102JdbcRepository.budgetYearDropdown();
		return response;
		
	}
	
	private List<IaRiskSelectCase> saveDataList(String budgetYear, BigDecimal inspectionWork) {
		// WEB SERVICE QUERY
		// NOW MOCKING DATA
		
//		Data mock inspectionWork 5
		

		List<String> dataList1 = new ArrayList<String>();
		dataList1.add("30100");
		dataList1.add("40100");
		dataList1.add("50100");

		List<String> dataList2 = new ArrayList<String>();
		dataList2.add("สำนักงานสรรพสามิตภาคที่ 3");
		dataList2.add("สำนักงานสรรพสามิตภาคที่ 4");
		dataList2.add("สำนักงานสรรพสามิตภาคที่ 5");

		List<String> dataList3 = new ArrayList<String>();
		dataList3.add("สำนักงานสรรพสามิตพื้นที่เชียงใหม่");
		dataList3.add("สำนักงานสรรพสามิตพื้นที่นครราชสีมา");
		dataList3.add("สำนักงานสรรพสามิตพื้นที่อุดรธานี");
		
//		Data mock inspectionWork 4 
		
		List<String> dataList6 = new ArrayList<String>();
		dataList6.add("2");
		dataList6.add("3");
		dataList6.add("4");

		List<String> dataList7 = new ArrayList<String>();
		dataList7.add("ระบบงานสารสนเทศหลัก http://Web.excise.go.th/EDINTRAWeb");
		dataList7.add("ระบบความปลอดภัยกลาง (SSO) http://authen.excise.go.th/oiddas");
		dataList7.add("ระบบงานอีเมล์กรมสรรพสามิต http://mail.excise.go.th");

		
//		Data mock inspectionWork 3
		
		List<String> dataList4 = new ArrayList<String>();
		dataList4.add("10");
		dataList4.add("20");
		dataList4.add("30");
		
		List<String> dataList5 = new ArrayList<String>();
		dataList5.add("แผนหลักเกณฑ์การประเมินผลการปฏิบัติราชการ");
		dataList5.add("โครงการตามยุทธศาตร์");
		dataList5.add("แผนบริหารความเสี่ยง");
		
		List<IaRiskSelectCase> selectCases = new ArrayList<>();
		if (inspectionWork.compareTo(new BigDecimal(3)) == 0) {
			selectCases = new ArrayList<>();
			IaRiskSelectCase selectCase = new IaRiskSelectCase();
			for (int i = 0; i < dataList4.size(); i++) {
				selectCase = new IaRiskSelectCase();
				selectCase.setProjectCode(dataList4.get(i));
				selectCase.setProject(dataList5.get(i));
				selectCase.setBudgetYear(budgetYear);
				selectCase.setInspectionWork(inspectionWork);
				selectCase.setStatus("C");
				selectCases.add(selectCase);
			}
			selectCases = (List<IaRiskSelectCase>) iaRiskSelectCaseRep.saveAll(selectCases);
		} else if (inspectionWork.compareTo(new BigDecimal(4)) == 0) {
			selectCases = new ArrayList<>();
			IaRiskSelectCase selectCase = new IaRiskSelectCase();
			for (int i = 0; i < dataList6.size(); i++) {
				selectCase = new IaRiskSelectCase();
				selectCase.setSystemCode(dataList6.get(i));
				selectCase.setSystemName(dataList7.get(i));
				selectCase.setBudgetYear(budgetYear);
				selectCase.setInspectionWork(inspectionWork);
				selectCase.setStatus("C");
				selectCases.add(selectCase);
			}
			selectCases = (List<IaRiskSelectCase>) iaRiskSelectCaseRep.saveAll(selectCases);
		} else if (inspectionWork.compareTo(new BigDecimal(5)) == 0) {
			selectCases = new ArrayList<>();
			List<ExciseDept> exciseSectorList = ApplicationCache.getExciseSectorList();
			IaRiskSelectCase selectCase = new IaRiskSelectCase();
			for (ExciseDept exciseDept : exciseSectorList) {
				
//	************* Insert Sactor ************* 
				selectCase = new IaRiskSelectCase();
				ExciseDept sector = ApplicationCache.getExciseDept(exciseDept.getOfficeCode().substring(0, 2)+"0000");
				selectCase.setExciseCode(exciseDept.getOfficeCode());
				selectCase.setSector(sector.getDeptName());
				
				if (!"0000".equals(exciseDept.getOfficeCode().substring(2, 6))) {
					
					ExciseDept area = ApplicationCache.getExciseDept(exciseDept.getOfficeCode());
					selectCase.setArea(area.getDeptName());
					
				} else {
					selectCase.setArea("");
				}
				selectCase.setBudgetYear(budgetYear);
				selectCase.setInspectionWork(inspectionWork);
				selectCase.setStatus("C");
				selectCases.add(selectCase);
				
//	************* Insert Area ************* 
				List<ExciseDept> exciseAreaList = ApplicationCache.getExciseAreaList(exciseDept.getOfficeCode());
				for (ExciseDept exciseDeptArea : exciseAreaList) {
					selectCase = new IaRiskSelectCase();
					ExciseDept sectorArea = ApplicationCache.getExciseDept(exciseDeptArea.getOfficeCode().substring(0, 2)+"0000");
					selectCase.setExciseCode(exciseDeptArea.getOfficeCode());
					selectCase.setSector(sectorArea.getDeptName());
					
					if (!"0000".equals(exciseDeptArea.getOfficeCode().substring(2, 6))) {
						
						ExciseDept area2 = ApplicationCache.getExciseDept(exciseDeptArea.getOfficeCode());
						selectCase.setArea(area2.getDeptName());
						
					} else {
						selectCase.setArea("");
					}
					selectCase.setBudgetYear(budgetYear);
					selectCase.setInspectionWork(inspectionWork);
					selectCase.setStatus("C");
					selectCases.add(selectCase);
				}
				
				
			}
			
			selectCases = (List<IaRiskSelectCase>) iaRiskSelectCaseRep.saveAll(selectCases);
		}
		return selectCases;
	}

	public void checkAndInsertTableFactorsStatus(Int030102FormVo form) {
		int count = int030102JdbcRepository.checkAndInsertTableFactorsStatus(form);
		if (count == 0) {
			List<IaRiskFactorsMaster> iaRiskFactorsMasterList = new ArrayList<IaRiskFactorsMaster>();
			iaRiskFactorsMasterList = iaRiskFactorsMasterRepository.findByInspectionWork(form.getInspectionWork());

			for (IaRiskFactorsMaster iaRiskS : iaRiskFactorsMasterList) {
				IaRiskFactorsStatus data = new IaRiskFactorsStatus();

				data.setIdMaster(iaRiskS.getId());
				data.setBudgetYear(form.getBudgetYear());
				data.setStatus("N");
				data.setInspectionWork(iaRiskS.getInspectionWork());

				data.setCreatedBy(UserLoginUtils.getCurrentUsername());
				data.setCreatedDate(LocalDateTime.now());
				iaRiskFactorsStatusRepository.save(data);
			}
		}
	}

	public void delete(Int030102FormVo form) {
//		iaRiskFactorsMasterRepository.deleteById(form.getId());
		int030102JdbcRepository.delete(form);
	}

	public void editStatus(Int030102FormVo form) {
//		iaRiskFactorsMasterRepository.deleteById(form.getId());
		int030102JdbcRepository.editStatus(form);
		updateStatusRiskFactorsService.updateStatusAddIaRiskFactorsMaster(form.getBudgetYear(), form.getInspectionWork());
	}

	public void save(Int030102FormVo form) {

		List<IaRiskFactorsMaster> iaRiskFactorsMasterList = int030102JdbcRepository.listSaveFactors(form);

		List<IaRiskFactors> iaRiskFactorsList = iaRiskFactorsRepository
				.findByInspectionWorkByBudgetYear(form.getInspectionWork(), form.getBudgetYear());

		int030102JdbcRepository.updateFactorsIsDeleteY(form);

		for (IaRiskFactorsMaster iaRiskFactorsMaster : iaRiskFactorsMasterList) {
			IaRiskFactors data = new IaRiskFactors();
			String check = "Add";
			BigDecimal id = new BigDecimal(0);
			for (IaRiskFactors checkFactors : iaRiskFactorsList) {
				if (checkFactors.getIdMaster().equals(iaRiskFactorsMaster.getId())) {
					id = checkFactors.getId();
					check = "Update";
					break;
				}
			}

			if ("Add".equals(check)) {
				data.setIdMaster(iaRiskFactorsMaster.getId());
				data.setBudgetYear(iaRiskFactorsMaster.getBudgetYear());
				data.setRiskFactors(iaRiskFactorsMaster.getRiskFactorsMaster());
				data.setInspectionWork(iaRiskFactorsMaster.getInspectionWork());
				data.setSide(iaRiskFactorsMaster.getSide());
				data.setDataEvaluate(iaRiskFactorsMaster.getDataEvaluate());
				data.setStatusScreen(IaConstants.IA_STATUS_RISK_FACTORS.STATUS_1_CODE);
				data.setCreatedBy(UserLoginUtils.getCurrentUsername());
				data.setCreatedDate(LocalDateTime.now());
				IaRiskFactors resultIdFactors = iaRiskFactorsRepository.save(data);
				IaRiskFactorsConfig dataConfig = new IaRiskFactorsConfig();
				dataConfig.setIdFactors(resultIdFactors.getId());
				dataConfig.setFactorsLevel(new BigDecimal(3));

				dataConfig.setCreatedBy(UserLoginUtils.getCurrentUsername());
				dataConfig.setCreatedDate(LocalDateTime.now());
				iaRiskFactorsConfigRepository.save(dataConfig);

			} else if ("Update".equals(check)) {
				int030102JdbcRepository.updateIsDelete(id);
				// iaRiskFactorsRepository.rrrrrr(id);
			}

		}

	}

	public void saveRiskFactorsLevel(Int030102FormVo form) {
		int030102JdbcRepository.saveRiskFactorsLevel(form);
		int030102JdbcRepository.claerDateCir(form);
		int030102JdbcRepository.saveRiskFactClerAll(form);
		updateStatusRiskFactorsService.updateStatusSaveRiskFactorsLevel(form.getBudgetYear(),form.getInspectionWork());
	}
	
	public void updateStatus(Int030102FormVo form) {
		int030102JdbcRepository.listUpdateStatus(form);
		save(form);
	}
	
	public IaRiskFactors factorList(IaRiskFactorsMaster form) {
		IaRiskFactors factor = new IaRiskFactors();
		List<IaRiskFactors> factorsList = iaRiskFactorsRepository.findByIdMaster(form.getId());
		for (IaRiskFactors iaRiskFactors : factorsList) {
			factor.setId(iaRiskFactors.getId());
			factor.setDataEvaluate(iaRiskFactors.getDataEvaluate());
		}
		return factor;
	}
}
