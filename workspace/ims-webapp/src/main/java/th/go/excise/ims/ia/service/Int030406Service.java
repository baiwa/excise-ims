package th.go.excise.ims.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaRiskCheckPeriod;
import th.go.excise.ims.ia.persistence.repository.IaRiskCheckPeriodRepository;
import th.go.excise.ims.ia.util.IntCalculateCriteriaUtil;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;
import th.go.excise.ims.ia.vo.Int030406FormVo;
import th.go.excise.ims.ia.vo.Int030406Vo;
import th.go.excise.ims.ia.vo.IntCalculateCriteriaVo;

@Service
public class Int030406Service {

	@Autowired
	private Int030405Service int030405Service;
	
	
	@Autowired
	private IaRiskCheckPeriodRepository iaRiskCheckPeriodRepository;
	
	public List<Int030406Vo> checkPeriodList(Int030406FormVo form) {
		List<Int030406Vo> resDataCal = new ArrayList<Int030406Vo>();
		List<IaRiskCheckPeriod> systemUnworkingList = new ArrayList<IaRiskCheckPeriod>();
		systemUnworkingList = iaRiskCheckPeriodRepository.findAllOderLongTimeDesc();
		List<IaRiskCheckPeriod> res = new ArrayList<IaRiskCheckPeriod>();

		for (IaRiskCheckPeriod systemList : systemUnworkingList) {
			IaRiskCheckPeriod dataSet = new IaRiskCheckPeriod();
			dataSet.setDateStart(systemList.getDateStart());
			dataSet.setDateEnd(systemList.getDateEnd());
			dataSet.setSectorName(systemList.getSectorName());
			dataSet.setAreaName(systemList.getAreaName());
			dataSet.setLongTime(systemList.getLongTime());
			res.add(dataSet);
		}
		
		Int0301FormVo dataForm = new Int0301FormVo();
		dataForm.setBudgetYear(form.getBudgetYear());
		dataForm.setIdConfig(form.getIdConfig());
		dataForm.setInspectionWork(form.getInspectionWork());
		Int0301Vo getForm0304 = int030405Service.getForm0304(dataForm);

		
		int index=0;
		for (IaRiskCheckPeriod list : res) {
			Int030406Vo resDataCalSet = new Int030406Vo();
			IntCalculateCriteriaVo risk = new IntCalculateCriteriaVo();
//			if(StringUtils.isNotBlank(list.getKpiExpenseActualAmount())) {
				risk = IntCalculateCriteriaUtil.calculateCriteria(list.getLongTime() , getForm0304.getIaRiskFactorsConfig());
//			}
			resDataCalSet.setIaRiskCheckPeriod(list);
			
			resDataCalSet.setDateFrom(ConvertDateUtils.formatDateToString(list.getDateStart(), ConvertDateUtils.DD_MM_YYYY));
			resDataCalSet.setDateTo(ConvertDateUtils.formatDateToString(list.getDateEnd(), ConvertDateUtils.DD_MM_YYYY));
			resDataCalSet.setIntCalculateCriteriaVo(risk);
			resDataCal.add(index, resDataCalSet);
			
		}

		return resDataCal;
	}
}
