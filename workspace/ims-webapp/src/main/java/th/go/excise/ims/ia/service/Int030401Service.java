package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsData;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsDataRepository;
import th.go.excise.ims.ia.util.IntCalculateCriteriaUtil;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;
import th.go.excise.ims.ia.vo.Int030401FormVo;
import th.go.excise.ims.ia.vo.Int030401Vo;
import th.go.excise.ims.ia.vo.IntCalculateCriteriaVo;

@Service
public class Int030401Service {
	
	@Autowired 
	private IaRiskFactorsDataRepository iaRiskFactorsDataRepository;
	
	@Autowired 
	private Int030405Service int030405Service;
	
//	@Autowired
//	private IntCalculateCriteriaUtil intCalculateCriteriaUtil;
	
	public List<Int030401Vo> factorsDataList(Int030401FormVo form) {
		List<Int030401Vo>  Int030401VoList = new ArrayList<>();
		List<IaRiskFactorsData> iaRiskFactorsDataList = new ArrayList<IaRiskFactorsData>();
		iaRiskFactorsDataList = iaRiskFactorsDataRepository.findByIdFactorsByInspectionWorkByBudgetYear(form.getIdFactors(), form.getInspectionWork(), form.getBudgetYear());
		
		List<IaRiskFactorsData> iaRiskFactorsDataListRes = new ArrayList<IaRiskFactorsData>();
		for (IaRiskFactorsData iaRiskFactorsData : iaRiskFactorsDataList) {
			IaRiskFactorsData datanew = new IaRiskFactorsData();
			datanew.setIdFactors(iaRiskFactorsData.getIdFactors());
			datanew.setProjectCode(iaRiskFactorsData.getProjectCode());
			datanew.setProject(iaRiskFactorsData.getProject());
			datanew.setExciseCode(iaRiskFactorsData.getExciseCode());
			datanew.setSector(iaRiskFactorsData.getSector());
			datanew.setArea(iaRiskFactorsData.getArea());
			datanew.setRiskCost(iaRiskFactorsData.getRiskCost());
			datanew.setCreatedDate(iaRiskFactorsData.getCreatedDate());
			iaRiskFactorsDataListRes.add(datanew);
		}
		
		Int0301FormVo dataForm = new Int0301FormVo();
		dataForm.setBudgetYear(form.getBudgetYear());
		dataForm.setIdConfig(form.getIdConfig());
		dataForm.setInspectionWork(form.getInspectionWork());
		Int0301Vo getForm0304 = int030405Service.getForm0304(dataForm);
		
		int index=0;
		for (IaRiskFactorsData iaRiskFactorsData : iaRiskFactorsDataListRes) {
			
			Int030401Vo resDataCalSet = new Int030401Vo();
			IntCalculateCriteriaVo risk = new IntCalculateCriteriaVo();
			if(StringUtils.isNotBlank(iaRiskFactorsData.getRiskCost())) {
				risk = IntCalculateCriteriaUtil.calculateCriteria(new BigDecimal(iaRiskFactorsData.getRiskCost()) , getForm0304.getIaRiskFactorsConfig());
			}
			resDataCalSet.setIaRiskFactorsData(iaRiskFactorsData);
			resDataCalSet.setIntCalculateCriteriaVo(risk);
			Int030401VoList.add(index, resDataCalSet);
			index++;
		}
		
		return Int030401VoList;
	}
}
