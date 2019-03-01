package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsData;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsDataRepository;

@Service
public class Int030401Service {
	
	@Autowired IaRiskFactorsDataRepository iaRiskFactorsDataRepository;
	
	public List<IaRiskFactorsData> factorsDataList(IaRiskFactorsData form) {
		List<IaRiskFactorsData> iaRiskFactorsDataList = new ArrayList<IaRiskFactorsData>();
		iaRiskFactorsDataList = iaRiskFactorsDataRepository.findByIdFactorsByInspectionWorkByBudgetYear(form.getIdFactors(), form.getInspectionWork(), form.getBudgetYear());
		
		List<IaRiskFactorsData> iaRiskFactorsDataListRes = new ArrayList<IaRiskFactorsData>();
		for (IaRiskFactorsData iaRiskFactorsData : iaRiskFactorsDataList) {
			IaRiskFactorsData datanew = new IaRiskFactorsData();
			datanew.setIdFactors(iaRiskFactorsData.getIdFactors());
			datanew.setProject(iaRiskFactorsData.getProject());
			datanew.setExciseCode(iaRiskFactorsData.getExciseCode());
			datanew.setSector(iaRiskFactorsData.getSector());
			datanew.setArea(iaRiskFactorsData.getArea());
			datanew.setRiskCost(iaRiskFactorsData.getRiskCost());
			iaRiskFactorsDataListRes.add(datanew);
		}
		//ID_FACTORS, e.PROJECT, e.EXCISE_CODE, e.SECTOR, e.AREA, e.RISK_COST
		return iaRiskFactorsDataListRes;
	}
}
