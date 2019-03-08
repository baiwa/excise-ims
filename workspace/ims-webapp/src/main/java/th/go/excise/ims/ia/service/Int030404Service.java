package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaRiskProjectEfficiency;
import th.go.excise.ims.ia.persistence.repository.IaRiskProjectEfficiencyRepository;
import th.go.excise.ims.ia.util.IntCalculateCriteriaUtil;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;
import th.go.excise.ims.ia.vo.Int030404FormVo;
import th.go.excise.ims.ia.vo.Int030404Vo;
import th.go.excise.ims.ia.vo.IntCalculateCriteriaVo;

@Service
public class Int030404Service {
	@Autowired
	private IaRiskProjectEfficiencyRepository iaRiskProjectEfficiencyRepository;
	
	@Autowired
	private Int030405Service int030405Service;
	
	@Autowired
	private IntCalculateCriteriaUtil intCalculateCriteriaUtil;
	
	public List<Int030404Vo> projectEfficiencyList(Int030404FormVo form) {
		List<Int030404Vo> resDataCal = new ArrayList<Int030404Vo>();
		List<IaRiskProjectEfficiency> systemUnworkingList = new ArrayList<IaRiskProjectEfficiency>();
		systemUnworkingList = iaRiskProjectEfficiencyRepository.findByProjectYear(form.getBudgetYear());
		List<IaRiskProjectEfficiency> res = new ArrayList<IaRiskProjectEfficiency>();

		for (IaRiskProjectEfficiency systemList : systemUnworkingList) {
			IaRiskProjectEfficiency dataSet = new IaRiskProjectEfficiency();
			dataSet.setProjectName(systemList.getProjectName());
			dataSet.setKpiExpenseActualAmount(systemList.getKpiExpenseActualAmount());
			dataSet.setOwnerOfficeName(systemList.getOwnerOfficeName());
			dataSet.setProjectName(systemList.getProjectName());
			res.add(dataSet);
		}
		
		Int0301FormVo dataForm = new Int0301FormVo();
		dataForm.setBudgetYear(form.getBudgetYear());
		dataForm.setIdConfig(form.getIdConfig());
		dataForm.setInspectionWork(form.getInspectionWork());
		Int0301Vo getForm0304 = int030405Service.getForm0304(dataForm);

		
		int index=0;
		for (IaRiskProjectEfficiency list : res) {
			Int030404Vo resDataCalSet = new Int030404Vo();
			IntCalculateCriteriaVo risk = new IntCalculateCriteriaVo();
//			if(StringUtils.isNotBlank(list.getKpiExpenseActualAmount())) {
				risk = IntCalculateCriteriaUtil.calculateCriteria(list.getKpiExpenseActualAmount() , getForm0304.getIaRiskFactorsConfig());
//			}
			resDataCalSet.setIaRiskProjectEfficiency(list);
			resDataCalSet.setIntCalculateCriteriaVo(risk);
			resDataCal.add(index, resDataCalSet);
		}

		return resDataCal;
	}

}
