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
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfigAll;
import th.go.excise.ims.ia.persistence.entity.IaRiskSelectCase;
import th.go.excise.ims.ia.persistence.repository.IaInspectionPlanRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigAllRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskSelectCaseRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int0401JdbcRepository;
import th.go.excise.ims.ia.util.IntCalculateCriteriaUtil;
import th.go.excise.ims.ia.vo.Int030401FormVo;
import th.go.excise.ims.ia.vo.Int030401Vo;
import th.go.excise.ims.ia.vo.Int030405FormVo;
import th.go.excise.ims.ia.vo.Int030405Vo;
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
	private IaRiskFactorsConfigAllRepository iaRiskFactorsConfigAllRepository;
	
	@Autowired
	private Int030401Service int030401Service;
	
	@Autowired
	private Int030405Service int030405Service;
	

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
				BigDecimal calRiskRatePercent = new BigDecimal(0);
				BigDecimal percentAll = new BigDecimal(0);
				
				for (IaRiskFactors factor : factors) {
					Int0401ListVo listVo = new Int0401ListVo();
					
// ******************** CalculateCriteria **********************
					IntCalculateCriteriaVo calVo = new IntCalculateCriteriaVo();
					if(IaConstants.IA_DATA_EVALUATE.NEW.equals(factor.getDataEvaluate())) {
						
						calVo = calNew(factor.getId(),budgetYear,inspectionWork,selectCase.getProjectCode(),selectCase.getExciseCode(),selectCase.getSystemCode());
					
					}else if(IaConstants.IA_DATA_EVALUATE.SYSTEM_UNWORKING.equals(factor.getDataEvaluate())) {
						
						calVo = calSystemUnworking(factor.getId(),budgetYear,inspectionWork,selectCase.getSystemCode());
						
					}
					
					
//					calVo = IntCalculateCriteriaUtil.calculateCriteriaAndGetConfigByIdFactors(new BigDecimal(5), factor.getId());
				
					if (listVo.getRiskRate() != null) {
						riskRating.add(listVo.getRiskRate());
					}
					if(calVo.getRiskRate()!=null&&calVo.getPercent()!=null) {
						calRiskRatePercent = calRiskRatePercent.add(calVo.getRiskRate().multiply(calVo.getPercent()));
						percentAll = percentAll.add(calVo.getPercent());
						
					}
					
					
					listVos.add(listVo);
// ******************** CalculateCriteria **********************
					listsCal.add(calVo);
				}
				list.setId(selectCase.getId());
				list.setBudgetYear(selectCase.getBudgetYear());
				
				list.setProjectCode(selectCase.getProjectCode());
				list.setProjectName(selectCase.getProject());
				
				list.setExciseCode(selectCase.getExciseCode());
				list.setSectorName(selectCase.getSector());
				list.setAreaName(selectCase.getArea());
				
				list.setSystemCode(selectCase.getSystemCode());
				list.setSystemName(selectCase.getSystemName());
				
				list.setInspectionWork(selectCase.getInspectionWork());
				list.setStatus(selectCase.getStatus());
				
				list.setLists(listVos);
// ******************** CalculateCriteria **********************			
				list.setListsCal(listsCal);
				
				list.setRiskItem(new BigDecimal(listVos.size()));
				
// ******************** CalculateCriteria All **********************
				
				
				
				
				list.setRiskRate(calRiskRatePercent.divide(percentAll));
				IaRiskFactorsConfig calVo = matchConfigAllWithConfig(budgetYear, inspectionWork);
				IntCalculateCriteriaVo calVoRes = IntCalculateCriteriaUtil.calculateCriteria(list.getRiskRate(),calVo);
				
				list.setRiskText(calVoRes.getTranslatingRisk());
				list.setRiskColor(calVoRes.getCodeColor());
				
				
				lists.add(list);
			}
		}
		return lists;
	}
	
	public IaRiskFactorsConfig matchConfigAllWithConfig(String budgetYear, BigDecimal inspectionWork) {
		IaRiskFactorsConfigAll conAll = new IaRiskFactorsConfigAll();
		IaRiskFactorsConfig con = new IaRiskFactorsConfig();
		conAll = iaRiskFactorsConfigAllRepository.findByBudgetYearByInspectionWork(budgetYear,inspectionWork);
		
		con.setId(conAll.getId());

		con.setFactorsLevel(conAll.getFactorsLevel());
	
			con.setVerylow(conAll.getVerylow());
			con.setVerylowStart(conAll.getVerylowStart());
			con.setVerylowEnd(conAll.getVerylowEnd());
			con.setVerylowCondition(conAll.getVerylowCondition());
			con.setVerylowRating(conAll.getVerylowRating());
			con.setVerylowColor(conAll.getVerylowColor());
	
	
			con.setLow(conAll.getLow());
			con.setLowStart(conAll.getLowStart());
			con.setLowEnd(conAll.getLowEnd());
			con.setLowCondition(conAll.getLowCondition());
			con.setLowRating(conAll.getLowRating());
			con.setLowColor(conAll.getLowColor());
	
	
			con.setMedium(conAll.getMedium());
			con.setMediumStart(conAll.getMediumStart());
			con.setMediumEnd(conAll.getMediumEnd());
			con.setMediumCondition(conAll.getMediumCondition());
			con.setMediumRating(conAll.getMediumRating());
			con.setMediumColor(conAll.getMediumColor());
	
	
			con.setHigh(conAll.getHigh());
			con.setHighStart(conAll.getHighStart());
			con.setHighEnd(conAll.getHighEnd());
			con.setHighCondition(conAll.getHighCondition());
			con.setHighRating(conAll.getHighRating());
			con.setHighColor(conAll.getHighColor());
	
	
			con.setVeryhigh(conAll.getVeryhigh());
			con.setVeryhighStart(conAll.getVeryhighStart());
			con.setVeryhighEnd(conAll.getVeryhighEnd());
			con.setVeryhighCondition(conAll.getVeryhighCondition());
			con.setVeryhighRating(conAll.getVeryhighRating());
			con.setVeryhighColor(conAll.getVeryhighColor());

		
		return con;
	}
	
	
//	*****************  Set IntCalculateCriteriaVo Data_Evaluate = NEW  *****************  
	public IntCalculateCriteriaVo calNew(BigDecimal idFactors,String budgetYear, BigDecimal inspectionWork,String projectCode,String exciseCode,String systemCode) {
		IntCalculateCriteriaVo calVo = new IntCalculateCriteriaVo();

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
					calVo.setPercent(config.getPercent());
					
				}else if(new BigDecimal(4).equals(inspectionWork)&&systemCode.equals(int030401Vo.getIaRiskFactorsData().getSystemCode())) {
					
					calVo.setCodeColor(int030401Vo.getIntCalculateCriteriaVo().getCodeColor());
					calVo.setColor(int030401Vo.getIntCalculateCriteriaVo().getColor());
					calVo.setRiskRate(int030401Vo.getIntCalculateCriteriaVo().getRiskRate());
					calVo.setTranslatingRisk(int030401Vo.getIntCalculateCriteriaVo().getTranslatingRisk());
					calVo.setPercent(config.getPercent());
					
				}else if(new BigDecimal(5).equals(inspectionWork)&&exciseCode.equals(int030401Vo.getIaRiskFactorsData().getExciseCode())) {
					
					calVo.setCodeColor(int030401Vo.getIntCalculateCriteriaVo().getCodeColor());
					calVo.setColor(int030401Vo.getIntCalculateCriteriaVo().getColor());
					calVo.setRiskRate(int030401Vo.getIntCalculateCriteriaVo().getRiskRate());
					calVo.setTranslatingRisk(int030401Vo.getIntCalculateCriteriaVo().getTranslatingRisk());
					calVo.setPercent(config.getPercent());
					
				}
	
			}
		
		return calVo;
		
	}
	
	public IntCalculateCriteriaVo calSystemUnworking(BigDecimal idFactors,String budgetYear, BigDecimal inspectionWork,String systemCode) {
		IntCalculateCriteriaVo calVo = new IntCalculateCriteriaVo();

//			Data Evaluate = System_Unworking
			Int030405FormVo form = new Int030405FormVo();
			IaRiskFactorsConfig config = iaRiskFactorsConfigRepository.findByIdFactors(idFactors);
			form.setBudgetYear(budgetYear);
			form.setInspectionWork(inspectionWork);
			form.setIdConfig(config.getId());
			List<Int030405Vo> list = int030405Service.systemUnworkingList(form);
			for (Int030405Vo int030405Vo : list) {
				
				if(int030405Vo.getIaRiskSystemUnworking().getSystemcode().equals(systemCode)) {
					
					calVo.setCodeColor(int030405Vo.getIntCalculateCriteriaVo().getCodeColor());
					calVo.setColor(int030405Vo.getIntCalculateCriteriaVo().getColor());
					calVo.setRiskRate(int030405Vo.getIntCalculateCriteriaVo().getRiskRate());
					calVo.setTranslatingRisk(int030405Vo.getIntCalculateCriteriaVo().getTranslatingRisk());
					calVo.setPercent(config.getPercent());
					
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
			IaRiskFactorsConfig config = iaRiskFactorsConfigRepository.findByIdFactors(factor.getId());
			header.setPercent(config.getPercent());
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
