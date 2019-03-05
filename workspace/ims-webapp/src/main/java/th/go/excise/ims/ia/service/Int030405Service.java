package th.go.excise.ims.ia.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ia.persistence.entity.IaRiskSystemUnworking;
import th.go.excise.ims.ia.persistence.repository.IaRiskSystemUnworkingRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int030405JdbcRepository;
import th.go.excise.ims.ia.util.IntCalculateCriteriaUtil;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;
import th.go.excise.ims.ia.vo.Int030405FormVo;
import th.go.excise.ims.ia.vo.Int030405Vo;
import th.go.excise.ims.ia.vo.IntCalculateCriteriaVo;

@Service
public class Int030405Service {
	private Logger logger = LoggerFactory.getLogger(Int030405Service.class);

	@Autowired
	private IaRiskSystemUnworkingRepository iaRiskSystemUnworkingRepository;

	@Autowired
	private Int030405JdbcRepository int030405JdbcRepository;

	@Autowired
	private IntCalculateCriteriaUtil intCalculateCriteriaUtil;

	public List<Int030405Vo> systemUnworkingList(Int030405FormVo form) {
		List<Int030405Vo> resDataCal = new ArrayList<Int030405Vo>();
		List<IaRiskSystemUnworking> systemUnworkingList = new ArrayList<IaRiskSystemUnworking>();
		systemUnworkingList = iaRiskSystemUnworkingRepository.findByBudgetYear(form.getBudgetYear());
		List<IaRiskSystemUnworking> res = new ArrayList<IaRiskSystemUnworking>();

		for (IaRiskSystemUnworking iaRiskSystemUnworking : systemUnworkingList) {
			IaRiskSystemUnworking dataSet = new IaRiskSystemUnworking();
			dataSet.setBudgetYear(form.getBudgetYear());
			dataSet.setBudgetYear(form.getBudgetYear());
			dataSet.setSystemcode(iaRiskSystemUnworking.getSystemcode());
			dataSet.setSystemname(iaRiskSystemUnworking.getSystemname());
			dataSet.setCountall(iaRiskSystemUnworking.getCountall());
			dataSet.setCounterror(iaRiskSystemUnworking.getCounterror());
			dataSet.setErrordetailError10(iaRiskSystemUnworking.getErrordetailError10());
			dataSet.setErrordetailError11(iaRiskSystemUnworking.getErrordetailError11());
			dataSet.setErrordetailError12(iaRiskSystemUnworking.getErrordetailError12());
			dataSet.setErrordetailError01(iaRiskSystemUnworking.getErrordetailError01());
			dataSet.setErrordetailError02(iaRiskSystemUnworking.getErrordetailError02());
			dataSet.setErrordetailError03(iaRiskSystemUnworking.getErrordetailError03());
			dataSet.setErrordetailError04(iaRiskSystemUnworking.getErrordetailError04());
			dataSet.setErrordetailError05(iaRiskSystemUnworking.getErrordetailError05());
			dataSet.setErrordetailError06(iaRiskSystemUnworking.getErrordetailError06());
			dataSet.setErrordetailError07(iaRiskSystemUnworking.getErrordetailError07());
			dataSet.setErrordetailError08(iaRiskSystemUnworking.getErrordetailError08());
			dataSet.setErrordetailError09(iaRiskSystemUnworking.getErrordetailError09());
			res.add(dataSet);
		}
		
		Int0301FormVo dataForm = new Int0301FormVo();
		dataForm.setBudgetYear(form.getBudgetYear());
		dataForm.setIdConfig(form.getIdConfig());
		dataForm.setInspectionWork(form.getInspectionWork());
		Int0301Vo getForm0304 = getForm0304(dataForm);
		
		
		int index=0;
		for (IaRiskSystemUnworking iaRiskSystemUnworking : res) {
			Int030405Vo resDataCalSet = new Int030405Vo();
			IntCalculateCriteriaVo risk = new IntCalculateCriteriaVo();
			if(StringUtils.isNoneBlank(iaRiskSystemUnworking.getCountall())) {
				risk = IntCalculateCriteriaUtil.calculateCriteria(new BigDecimal(iaRiskSystemUnworking.getCountall()) , getForm0304.getIaRiskFactorsConfig());
			}
			resDataCalSet.setIaRiskSystemUnworking(iaRiskSystemUnworking);
			resDataCalSet.setIntCalculateCriteriaVo(risk);
			resDataCal.add(index, resDataCalSet);
		}
//		
//		intCalculateCriteriaUtil.IntCalculateCriteriaVo()
//		resDataCal
//		IntCalculateCriteriaVo risk = IntCalculateCriteriaUtil.calculateCriteria(rateAmount, config.get());
		return resDataCal;
	}

	public Int0301Vo getForm0304(Int0301FormVo form) {
		Int0301Vo int0301Vo = new Int0301Vo();
		List<Int0301Vo> listServiceRes = new ArrayList<Int0301Vo>();
		listServiceRes = int030405JdbcRepository.getForm0304(form);
		int0301Vo = listServiceRes.get(0);
		return int0301Vo;
	}
}
