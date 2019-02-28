package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaMasCondSubCapital;
import th.go.excise.ims.ta.persistence.entity.TaMasCondSubNoAudit;
import th.go.excise.ims.ta.persistence.entity.TaMasCondSubRisk;
import th.go.excise.ims.ta.persistence.repository.TaMasCondSubCapitalRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondSubNoAuditRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondSubRiskRepository;
import th.go.excise.ims.ta.vo.MasCondSubCapitalFormVo;
import th.go.excise.ims.ta.vo.MasCondSubRiskFormVo;
import th.go.excise.ims.ta.vo.MasCondSubRiskListFormVo;

@Service
public class MasterConditionSubService {

	@Autowired
	private TaMasCondSubCapitalRepository capitalRepository;

	@Autowired
	private TaMasCondSubNoAuditRepository noAuditRepository;

	@Autowired
	private TaMasCondSubRiskRepository riskRepository;

	public List<ParamInfo> getProductTypeAndServiceType() {
		return ExciseUtils.getProductTypeAndServiceType();
	}

	public void insertCapital(MasCondSubCapitalFormVo form) {
		TaMasCondSubCapital capital = null;
		List<TaMasCondSubCapital> saveCapital = new ArrayList<TaMasCondSubCapital>();
		List<TaMasCondSubCapital> oldCapital = capitalRepository.findByBudgetYear(form.getBudgetYear());
		List<ParamInfo> productService = ExciseUtils.getProductTypeAndServiceType();
		if (form.getDutyCode().equals("0")) {
			if (0 < oldCapital.size()) {
				for (ParamInfo paramInfo : productService) {
					capital = capitalRepository.findByBudgetYearAndDutyCode(form.getBudgetYear(),
							paramInfo.getParamCode());
					capital.setHugeCapitalAmount(form.getHugeCapitalAmount());
					capital.setLargeCapitalAmount(form.getLargeCapitalAmount());
					capital.setMediumCapitalAmount(form.getMediumCapitalAmount());
					capital.setSmallCapitalAmount(form.getSmallCapitalAmount());
					saveCapital.add(capital);
				}
			} else {
				for (ParamInfo paramInfo : productService) {
					capital = new TaMasCondSubCapital();
					capital.setBudgetYear(form.getBudgetYear());
					capital.setDutyCode(paramInfo.getParamCode());
					capital.setHugeCapitalAmount(form.getHugeCapitalAmount());
					capital.setLargeCapitalAmount(form.getLargeCapitalAmount());
					capital.setMediumCapitalAmount(form.getMediumCapitalAmount());
					capital.setSmallCapitalAmount(form.getSmallCapitalAmount());
					saveCapital.add(capital);
				}
			}
		} else {
			if (0 < oldCapital.size()) {
				capital = capitalRepository.findByBudgetYearAndDutyCode(form.getBudgetYear(), form.getDutyCode());
				capital.setHugeCapitalAmount(form.getHugeCapitalAmount());
				capital.setLargeCapitalAmount(form.getLargeCapitalAmount());
				capital.setMediumCapitalAmount(form.getMediumCapitalAmount());
				capital.setSmallCapitalAmount(form.getSmallCapitalAmount());
				saveCapital.add(capital);
			} else {
				capital = new TaMasCondSubCapital();
				capital.setBudgetYear(form.getBudgetYear());
				capital.setDutyCode(form.getDutyCode());
				capital.setHugeCapitalAmount(form.getHugeCapitalAmount());
				capital.setLargeCapitalAmount(form.getLargeCapitalAmount());
				capital.setMediumCapitalAmount(form.getMediumCapitalAmount());
				capital.setSmallCapitalAmount(form.getSmallCapitalAmount());
				saveCapital.add(capital);
			}
		}
		capitalRepository.saveAll(saveCapital);
	}

	public List<TaMasCondSubCapital> getCapital(TaMasCondSubCapital form) {
		List<TaMasCondSubCapital> capital = new ArrayList<>();
		capital = capitalRepository.findByBudgetYear(form.getBudgetYear());
		return capital;
	}

	public TaMasCondSubCapital getCapitalByDutyCode(TaMasCondSubCapital form) {
		TaMasCondSubCapital capital = new TaMasCondSubCapital();
		capital = capitalRepository.findByBudgetYearAndDutyCode(form.getBudgetYear(), form.getDutyCode());
		return capital;
	}

	public void insertRisk(MasCondSubRiskFormVo form) {
		TaMasCondSubRisk risk = null;
		List<TaMasCondSubRisk> riskList = new ArrayList<>();
		List<TaMasCondSubRisk> oldRiskList = riskRepository.findByBudgetYear(form.getBudgetYear());
		
		if (CollectionUtils.isNotEmpty(oldRiskList)) {
			for (MasCondSubRiskListFormVo riskform : form.getRiskList()) {
				risk = riskRepository.findByBudgetYearAndDutyCode(form.getBudgetYear(), riskform.getCode());
				risk.setRiskLevel(riskform.getLevel());
				riskList.add(risk);
			}
		} else {
			for (MasCondSubRiskListFormVo riskform : form.getRiskList()) {
				risk = new TaMasCondSubRisk();
				risk.setBudgetYear(form.getBudgetYear());
				risk.setDutyCode(riskform.getCode());
				risk.setRiskLevel(riskform.getLevel());
				riskList.add(risk);
			}
		}
		riskRepository.saveAll(riskList);
	}
	
	public List<TaMasCondSubRisk> getRiskByBudgetYear(MasCondSubRiskFormVo form) {
		List<TaMasCondSubRisk> risk = new ArrayList<>();
		risk = riskRepository.findByBudgetYear(form.getBudgetYear());
		return risk;
	}
	
	public void insertNoAudit(TaMasCondSubNoAudit form) {
		TaMasCondSubNoAudit noAudit = null;
		TaMasCondSubNoAudit oldNoAudit = null;
		oldNoAudit = noAuditRepository.findByBudgetYear(form.getBudgetYear());
		if (null == oldNoAudit) {
			noAudit = new TaMasCondSubNoAudit();
			noAudit.setBudgetYear(form.getBudgetYear());
			noAudit.setNoTaxAuditYearNum(form.getNoTaxAuditYearNum());
		} else {
			noAudit = noAuditRepository.findByBudgetYear(form.getBudgetYear());
			noAudit.setNoTaxAuditYearNum(form.getNoTaxAuditYearNum());
		}
		noAuditRepository.save(noAudit);
	}
	
	public TaMasCondSubNoAudit getNoAuditByBudgetYear(TaMasCondSubNoAudit form) {
		TaMasCondSubNoAudit noAudit = new TaMasCondSubNoAudit();
		noAudit = noAuditRepository.findByBudgetYear(form.getBudgetYear());
		return noAudit;
	}

}
