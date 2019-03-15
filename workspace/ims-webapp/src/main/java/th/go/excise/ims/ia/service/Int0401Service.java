package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.constant.IaConstants;
import th.go.excise.ims.ia.persistence.entity.IaInspectionPlan;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskSelectCase;
import th.go.excise.ims.ia.persistence.repository.IaInspectionPlanRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskSelectCaseRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0401JdbcRepository;
import th.go.excise.ims.ia.vo.Int030401FormVo;
import th.go.excise.ims.ia.vo.Int030401Vo;
import th.go.excise.ims.ia.vo.Int0401CalConfigVo;
import th.go.excise.ims.ia.vo.Int0401HeaderVo;
import th.go.excise.ims.ia.vo.Int0401ListVo;
import th.go.excise.ims.ia.vo.Int0401Vo;
import th.go.excise.ims.ia.vo.IntCalculateCriteriaVo;

@Service
public class Int0401Service {

	@Autowired
	private IaRiskSelectCaseRepository iaRiskSelectCaseRep;

	@Autowired
	private Int0401JdbcRepository int0401JdbcRep;

	@Autowired
	private IaInspectionPlanRepository iaInspectionPlanRepository;
	
	@Autowired
	private IaRiskFactorsConfigRepository iaRiskFactorsConfigRepository;
	
	@Autowired
	private Int030401Service int030401Service;
	

	public List<Int0401Vo> findByBudgetYearAndInspectionWork(String budgetYear, String inspectionWorkStr,
			String status) {
		BigDecimal inspectionWork = new BigDecimal(inspectionWorkStr);
		List<Int0401Vo> lists = new ArrayList<>();
		List<IaRiskSelectCase> selectCases = int0401JdbcRep.findRow(budgetYear, inspectionWork, status);
		List<IaRiskFactors> factors = int0401JdbcRep.findHead(budgetYear, inspectionWork);
		Int0401CalConfigVo config = int0401JdbcRep.findConfigAll(budgetYear, inspectionWork);
		
		
		if (config != null && config.getMedium() != null) {
			for (IaRiskSelectCase selectCase : selectCases) {
				Int0401Vo list = new Int0401Vo();
				List<Int0401ListVo> listVos = new ArrayList<>();
				
// ******************** CalculateCriteria **********************
				List<IntCalculateCriteriaVo> listsCal = new ArrayList<IntCalculateCriteriaVo>();
				
				BigDecimal riskRating = new BigDecimal(0);

				for (IaRiskFactors factor : factors) {
					Int0401ListVo listVo = new Int0401ListVo();
					
// ******************** CalculateCriteria **********************
					IntCalculateCriteriaVo calVo = new IntCalculateCriteriaVo();
					calVo = calculateCriteriaInt0401(factor.getDataEvaluate(),factor.getId(), budgetYear, inspectionWork,selectCase.getProjectCode(),selectCase.getExciseCode());
//					calVo = IntCalculateCriteriaUtil.calculateCriteriaAndGetConfigByIdFactors(new BigDecimal(5), factor.getId());
				
					if (listVo.getRiskRate() != null) {
						riskRating.add(listVo.getRiskRate());
					}
					listVos.add(listVo);
// ******************** CalculateCriteria **********************
					listsCal.add(calVo);
				}
				list.setId(selectCase.getId());
				list.setBudgetYear(selectCase.getBudgetYear());
				list.setProjectName(selectCase.getProject());
				list.setExciseCode(selectCase.getExciseCode());
				list.setSectorName(selectCase.getSector());
				list.setAreaName(selectCase.getArea());
				list.setInspectionWork(selectCase.getInspectionWork());
				list.setStatus(selectCase.getStatus());
				
				list.setLists(listVos);
// ******************** CalculateCriteria **********************			
				list.setListsCal(listsCal);
				
				list.setRiskItem(new BigDecimal(listVos.size()));
				
// ******************** CalculateCriteria All **********************				
				list.setRiskRate(new BigDecimal(10));
				list.setRiskText("สูง");
				
				
				lists.add(list);
			}
		}
		return lists;
	}
	
	
//	*****************  Set IntCalculateCriteriaVo Data_Evaluate = NEW  *****************  
	public IntCalculateCriteriaVo calculateCriteriaInt0401(String dataEvaluate,BigDecimal idFactors,String budgetYear, BigDecimal inspectionWork,String projectCode,String exciseCode) {
		IntCalculateCriteriaVo calVo = new IntCalculateCriteriaVo();
		
//		Data Evaluate = NEW
		if(IaConstants.IA_DATA_EVALUATE.NEW.equals(dataEvaluate)) {
//			Data Evaluate = NEW
			Int030401FormVo formNEW = new Int030401FormVo();
			IaRiskFactorsConfig config = iaRiskFactorsConfigRepository.findByIdFactors(idFactors);
			formNEW.setBudgetYear(budgetYear);
			formNEW.setInspectionWork(inspectionWork);
			formNEW.setIdFactors(idFactors);
			formNEW.setIdConfig(config.getId());
			List<Int030401Vo> list = int030401Service.factorsDataList(formNEW);
			for (Int030401Vo int030401Vo : list) {
				
				if(new BigDecimal(3).equals(inspectionWork)&&projectCode.equals(int030401Vo.getIaRiskFactorsData().getProjectCode())) {
					
					calVo.setCodeColor(int030401Vo.getIntCalculateCriteriaVo().getCodeColor());
					calVo.setColor(int030401Vo.getIntCalculateCriteriaVo().getColor());
					calVo.setRiskRate(int030401Vo.getIntCalculateCriteriaVo().getRiskRate());
					calVo.setTranslatingRisk(int030401Vo.getIntCalculateCriteriaVo().getTranslatingRisk());
					
				}else if(new BigDecimal(4).equals(inspectionWork)&&exciseCode.equals(int030401Vo.getIaRiskFactorsData().getExciseCode())) {
					
					calVo.setCodeColor(int030401Vo.getIntCalculateCriteriaVo().getCodeColor());
					calVo.setColor(int030401Vo.getIntCalculateCriteriaVo().getColor());
					calVo.setRiskRate(int030401Vo.getIntCalculateCriteriaVo().getRiskRate());
					calVo.setTranslatingRisk(int030401Vo.getIntCalculateCriteriaVo().getTranslatingRisk());
					
				}else if(new BigDecimal(5).equals(inspectionWork)&&exciseCode.equals(int030401Vo.getIaRiskFactorsData().getExciseCode())) {
					
					calVo.setCodeColor(int030401Vo.getIntCalculateCriteriaVo().getCodeColor());
					calVo.setColor(int030401Vo.getIntCalculateCriteriaVo().getColor());
					calVo.setRiskRate(int030401Vo.getIntCalculateCriteriaVo().getRiskRate());
					calVo.setTranslatingRisk(int030401Vo.getIntCalculateCriteriaVo().getTranslatingRisk());
					
				}
				
				
			}
			
		}
		
		return calVo;
		
	}
	

	public BigDecimal findStatusByBudgetYearAndInspectionWork(String budgetYear, String inspectionWorkStr,
			String status) {
		BigDecimal inspectionWork = new BigDecimal(inspectionWorkStr);
		List<IaRiskSelectCase> selectCases = int0401JdbcRep.findRow(budgetYear, inspectionWork, status);
		return new BigDecimal(selectCases.size());
	}

	public List<Int0401HeaderVo> findHeadByBudgetYearAndInspectionWork(String budgetYear, String inspectionWorkStr) {
		BigDecimal inspectionWork = new BigDecimal(inspectionWorkStr);
		List<IaRiskFactors> factors = int0401JdbcRep.findHead(budgetYear, inspectionWork);
		List<Int0401HeaderVo> lists = new ArrayList<>();
		for (IaRiskFactors factor : factors) {
			Int0401HeaderVo header = new Int0401HeaderVo();
			header.setName(factor.getRiskFactors());
			lists.add(header);
		}
		return lists;
	}

	public List<Int0401Vo> updateRowByStatus(List<BigDecimal> ids, String status) {
		List<IaRiskSelectCase> selectCases = (List<IaRiskSelectCase>) iaRiskSelectCaseRep.findAllById(ids);
		for (IaRiskSelectCase selectCase : selectCases) {
			selectCase.setStatus(status);
		}
		selectCases = (List<IaRiskSelectCase>) iaRiskSelectCaseRep.saveAll(selectCases);
		List<Int0401Vo> currentLists = new ArrayList<>();
		if (selectCases != null && selectCases.size() > 0) {
			String budgetYear = selectCases.get(0).getBudgetYear();
			BigDecimal inspectionWork = selectCases.get(0).getInspectionWork();
			String newStatus = "C".equalsIgnoreCase(status) ? "S" : "C";
			currentLists = findByBudgetYearAndInspectionWork(budgetYear, inspectionWork.toString(), newStatus);
		}
		return currentLists;
	}

	

	public void saveInspectionPlan(List<IaInspectionPlan> request) {
		iaInspectionPlanRepository.saveAll(request);
	}

}
