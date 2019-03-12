package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.go.excise.ims.ia.persistence.entity.IaRiskProEf;
import th.go.excise.ims.ia.persistence.entity.IaRiskProEfKpi;
import th.go.excise.ims.ia.persistence.entity.IaRiskProEfTask;
import th.go.excise.ims.ia.persistence.repository.IaRiskProEfKpiRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskProEfRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskProEfTaskRepository;
import th.go.excise.ims.ia.util.IntCalculateCriteriaUtil;
import th.go.excise.ims.ia.vo.IaRiskProEfKpiVo;
import th.go.excise.ims.ia.vo.IaRiskProEfTaskVo;
import th.go.excise.ims.ia.vo.IaRiskProEfVo;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;
import th.go.excise.ims.ia.vo.Int030404FormVo;
import th.go.excise.ims.ia.vo.Int030404Vo;
import th.go.excise.ims.ia.vo.IntCalculateCriteriaVo;

@Service
public class Int030404Service {
	@Autowired
	private IaRiskProEfRepository iaRiskProEfRepository;

	@Autowired
	private IaRiskProEfTaskRepository iaRiskProEfTaskRepository;

	@Autowired
	private IaRiskProEfKpiRepository iaRiskProEfKpiRepository;

	@Autowired
	private Int030405Service int030405Service;

	@Autowired
	private IntCalculateCriteriaUtil intCalculateCriteriaUtil;

	private Logger logger = LoggerFactory.getLogger(Int030404Service.class);

	@Transactional
	public List<Int030404Vo> projectEfficiencyList(Int030404FormVo form) {

		Int0301FormVo dataForm = new Int0301FormVo();
		dataForm.setBudgetYear(form.getBudgetYear());
		dataForm.setIdConfig(form.getIdConfig());
		dataForm.setInspectionWork(form.getInspectionWork());
		Int0301Vo getForm0304 = int030405Service.getForm0304(dataForm);
		
		List<Int030404Vo> pedata = new  ArrayList<Int030404Vo>();
		Int030404Vo vo = null;
		
		List<IaRiskProEf> peList = iaRiskProEfRepository.findByProjectYear(form.getBudgetYear());
		IaRiskProEfVo peSet = null;

		for (IaRiskProEf peData : peList) {
			vo = new Int030404Vo();
			int kpiCount = 0;
			double ruleOfThreeSum = 0d;
			peSet = new IaRiskProEfVo();
			
			List<IaRiskProEfTask> tastList = iaRiskProEfTaskRepository.findByPeId(peData.getId());
			
			List<IaRiskProEfTaskVo> tastSetList = new ArrayList<IaRiskProEfTaskVo>();
			IaRiskProEfTaskVo tastSet = null;
			
			if(tastList.size() == 0) {
//				List<IaRiskProEfKpiVo> kpiSetList = new ArrayList<IaRiskProEfKpiVo>();
//				tastSet = new IaRiskProEfTaskVo();
//				tastSet.setTaskId(null);
//				tastSet.setTaskDescriptionText(null);
//				tastSet.setIaRiskProEfKpiVo(kpiSetList);
//				tastSetList.add(tastSet);
				
				peSet.setProjectId(peData.getProjectId());
				peSet.setProjectYear(peData.getProjectYear());
				peSet.setProjectName(peData.getProjectName());
				peSet.setProjectTypeName(peData.getProjectTypeName());
				peSet.setOwnerOfficeId(peData.getOwnerOfficeId());
				peSet.setOwnerOfficeName(peData.getOwnerOfficeName());
//				peSet.setCountKpi(null );
//				peSet.setAverageData(null);
//				peSet.setIaRiskProEfTaskVo(tastSetList);
				IntCalculateCriteriaVo risk = new IntCalculateCriteriaVo();
				vo.setIntCalculateCriteriaVo(risk);
				vo.setIaRiskProEfVo(peSet);
				pedata.add(vo);
				continue;
			}
			
			for (IaRiskProEfTask tastData : tastList) {
				tastSet = new IaRiskProEfTaskVo();
				List<IaRiskProEfKpi> kpiList = iaRiskProEfKpiRepository.findByTaskId(tastData.getId());
				
				List<IaRiskProEfKpiVo> kpiSetList = new ArrayList<IaRiskProEfKpiVo>();
				IaRiskProEfKpiVo kpiSet = null;
				
				if(kpiList.size() == 0) {
					continue;
				}
				
				for (IaRiskProEfKpi kpiData : kpiList) {
					kpiSet = new IaRiskProEfKpiVo();
					kpiSet.setKpiId(kpiData.getKpiId());
					kpiSet.setKpiCode(kpiData.getKpiCode());
					kpiSet.setKpiName(kpiData.getKpiName());
					kpiSet.setKpiActivityCode(kpiData.getKpiActivityCode());
					kpiSet.setKpiActivityName(kpiData.getKpiActivityName());
					kpiSet.setKpiTargetAmount(kpiData.getKpiTargetAmount());
					kpiSet.setKpiExpenseActualAmount(kpiData.getKpiExpenseActualAmount());
					kpiSetList.add(kpiSet);
					ruleOfThreeSum += calRuleOfThree(kpiData.getKpiExpenseActualAmount().doubleValue(),kpiData.getKpiTargetAmount().doubleValue());
					kpiCount++;
				}
				tastSet.setTaskId(tastData.getTaskId());
				tastSet.setTaskDescriptionText(tastData.getTaskDescriptionText());
				tastSet.setIaRiskProEfKpiVo(kpiSetList);
				tastSetList.add(tastSet);
			}
			peSet.setProjectId(peData.getProjectId());
			peSet.setProjectYear(peData.getProjectYear());
			peSet.setProjectName(peData.getProjectName());
			peSet.setProjectTypeName(peData.getProjectTypeName());
			peSet.setOwnerOfficeId(peData.getOwnerOfficeId());
			peSet.setOwnerOfficeName(peData.getOwnerOfficeName());
			
			peSet.setCountKpi(new BigDecimal(kpiCount) );
			double average = ruleOfThreeSum/kpiCount;
			logger.info(new BigDecimal(average).toString());
			peSet.setAverageData(new BigDecimal(average));
			peSet.setIaRiskProEfTaskVo(tastSetList);
			IntCalculateCriteriaVo risk = new IntCalculateCriteriaVo();
			
			if(average != 0) {				
				risk = IntCalculateCriteriaUtil.calculateCriteria(new BigDecimal(average) , getForm0304.getIaRiskFactorsConfig());
			}
			
			vo.setIntCalculateCriteriaVo(risk);
			vo.setIaRiskProEfVo(peSet);
			pedata.add(vo);
			
		}
		return pedata;
	}
	
	private double calRuleOfThree(double kpiExpenseActualAmount,double kpiTargetAmount ) {
		double ruleOfThree = 0d;
		
		if(kpiExpenseActualAmount>kpiTargetAmount) {
			ruleOfThree = (((kpiExpenseActualAmount/kpiTargetAmount)*100)-100)*-1;
		} else {
			ruleOfThree = (kpiExpenseActualAmount/kpiTargetAmount)*100;
		}
		
		return ruleOfThree;
	}
}
