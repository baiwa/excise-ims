package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAM_GROUP;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaMasCondSubCapital;
import th.go.excise.ims.ta.persistence.entity.TaMasCondSubNoAudit;
import th.go.excise.ims.ta.persistence.entity.TaMasCondSubRisk;
import th.go.excise.ims.ta.persistence.repository.TaMasCondSubCapitalRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondSubNoAuditRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondSubRiskRepository;
import th.go.excise.ims.ta.vo.MasCondSubCapitalFormVo;
import th.go.excise.ims.ta.vo.MasCondSubCapitalVo;
import th.go.excise.ims.ta.vo.MasCondSubRiskFormVo;
import th.go.excise.ims.ta.vo.MasCondSubRiskVo;

@Service
public class MasterConditionSubService {

	@Autowired
	private TaMasCondSubCapitalRepository capitalRepository;

	@Autowired
	private TaMasCondSubNoAuditRepository noAuditRepository;

	@Autowired
	private TaMasCondSubRiskRepository riskRepository;

	public void insertCapital(MasCondSubCapitalFormVo form) {
		TaMasCondSubCapital capital = null;
		TaMasCondSubCapital oldCapital = null;
		TaMasCondSubCapital capitalTotal = null;
		oldCapital = capitalRepository.findByOfficeCodeAndBudgetYearAndDutyCode(UserLoginUtils.getCurrentUserBean().getOfficeCode(), form.getBudgetYear(), form.getDutyCode());
		capitalTotal = capitalRepository.findByOfficeCodeAndBudgetYearTotal(UserLoginUtils.getCurrentUserBean().getOfficeCode(), form.getBudgetYear());
		if (null == oldCapital) {
			capital = new TaMasCondSubCapital();
			capital.setBudgetYear(form.getBudgetYear());
			
			if (null == capitalTotal) {
				capital.setDutyCode(null);
				capital.setHugeCapitalAmount(form.getHugeCapitalAmount());
				capital.setLargeCapitalAmount(form.getLargeCapitalAmount());
				capital.setMediumCapitalAmount(form.getMediumCapitalAmount());
				capital.setSmallCapitalAmount(form.getSmallCapitalAmount());
			} else {
				capital.setDutyCode(form.getDutyCode());
				capital.setHugeCapitalAmount(capitalTotal.getHugeCapitalAmount());
				capital.setLargeCapitalAmount(capitalTotal.getLargeCapitalAmount());
				capital.setMediumCapitalAmount(capitalTotal.getMediumCapitalAmount());
				capital.setSmallCapitalAmount(capitalTotal.getSmallCapitalAmount());
			}
			capital.setOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
			capitalRepository.save(capital);
		} else {
			oldCapital.setHugeCapitalAmount(form.getHugeCapitalAmount());
			oldCapital.setLargeCapitalAmount(form.getLargeCapitalAmount());
			oldCapital.setMediumCapitalAmount(form.getMediumCapitalAmount());
			oldCapital.setSmallCapitalAmount(form.getSmallCapitalAmount());
			capitalRepository.save(oldCapital);
		}
	}

	public List<MasCondSubCapitalVo> getCapital(MasCondSubCapitalVo form) {
		List<MasCondSubCapitalVo> capitalList = new ArrayList<>();
		List<TaMasCondSubCapital> capitalOld = new ArrayList<>();
		List<ParamInfo> paramInfoList = null;
		paramInfoList = new ArrayList<>();
		paramInfoList.addAll(ApplicationCache.getParamInfoListByGroupCode(PARAM_GROUP.EXCISE_PRODUCT_TYPE));
		paramInfoList.addAll(ApplicationCache.getParamInfoListByGroupCode(PARAM_GROUP.EXCISE_SERVICE_TYPE));
		capitalOld = capitalRepository.findByOfficeCodeAndBudgetYearNotTotal(UserLoginUtils.getCurrentUserBean().getOfficeCode(), form.getCapital().getBudgetYear());

		for (int i = 0; i < capitalOld.size(); i++) {
			TaMasCondSubCapital voOld = capitalOld.get(i);
			capitalList.add(new MasCondSubCapitalVo());
			capitalList.get(i).setCapital(voOld);
			MasCondSubCapitalVo vo = capitalList.get(i);
			if (!StringUtils.equals("0000", vo.getCapital().getDutyCode())) {
				for (ParamInfo paramInfo : paramInfoList) {
					if (paramInfo.getParamCode().equals(vo.getCapital().getDutyCode())) {
						capitalList.get(i).setCapitalDesc(paramInfo.getValue1());
						break;
					}
				}
			} 
			else {
				capitalList.get(i).setCapital(capitalRepository.findByOfficeCodeAndBudgetYearTotal(UserLoginUtils.getCurrentUserBean().getOfficeCode(), form.getCapital().getBudgetYear()));
				capitalList.get(i).setCapitalDesc("เลือกทั้งหมด");
			}
		}
		return capitalList;
	}
	
	public List<ParamInfo> getCapitalWithoutOld(TaMasCondSubCapital form) {
		List<TaMasCondSubCapital> capitalOld = new ArrayList<>();
		List<ParamInfo> productService = null;
		List<ParamInfo> remove = new ArrayList<>();
		productService = new ArrayList<>();
		productService.addAll(ApplicationCache.getParamInfoListByGroupCode(PARAM_GROUP.EXCISE_PRODUCT_TYPE));
		productService.addAll(ApplicationCache.getParamInfoListByGroupCode(PARAM_GROUP.EXCISE_SERVICE_TYPE));
		capitalOld = capitalRepository.findByOfficeCodeAndBudgetYearNotTotal(UserLoginUtils.getCurrentUserBean().getOfficeCode(), form.getBudgetYear());
		for (int i = 0; i < productService.size(); i++) {
			String paramCode = productService.get(i).getParamCode();
			for (TaMasCondSubCapital old : capitalOld) {
				if (paramCode.equals(old.getDutyCode())) {
					remove.add(productService.get(i));
				}
			}
		}
		productService.removeAll(remove);
		return productService;
	}

	public TaMasCondSubCapital getCapitalByDutyCode(TaMasCondSubCapital form) {
		TaMasCondSubCapital capital = new TaMasCondSubCapital();
		if ("null".equals(form.getDutyCode())) {
			capital = capitalRepository.findByOfficeCodeAndBudgetYearTotal(UserLoginUtils.getCurrentUserBean().getOfficeCode(), form.getBudgetYear());
		} else {
			capital = capitalRepository.findByOfficeCodeAndBudgetYearAndDutyCode(UserLoginUtils.getCurrentUserBean().getOfficeCode(), form.getBudgetYear(), form.getDutyCode());			
		}
		return capital;
	}
	
	public void deleteCapital(TaMasCondSubCapital form) {
		TaMasCondSubCapital capital = capitalRepository.findByOfficeCodeAndBudgetYearAndDutyCode(UserLoginUtils.getCurrentUserBean().getOfficeCode(), form.getBudgetYear(), form.getDutyCode());
		capital.setIsDeleted(FLAG.Y_FLAG);
		capitalRepository.save(capital);
	}

	public void insertRisk(MasCondSubRiskFormVo form) {
		TaMasCondSubRisk risk = null;
		List<TaMasCondSubRisk> riskList = new ArrayList<>();
		List<TaMasCondSubRisk> oldRiskList = riskRepository.findByBudgetYearAndOfficeCode(form.getBudgetYear(), UserLoginUtils.getCurrentUserBean().getOfficeCode());
		
		if (CollectionUtils.isNotEmpty(oldRiskList)) {
			for (int i = 0; i < form.getRiskList().size(); i++) {
				TaMasCondSubRisk riskform = form.getRiskList().get(i);
				risk = riskRepository.findByBudgetYearAndDutyCodeAndOfficeCode(form.getBudgetYear(), riskform.getDutyCode(), UserLoginUtils.getCurrentUserBean().getOfficeCode());
				risk.setRiskLevel(riskform.getRiskLevel());
				riskList.add(risk);
			}
		} else {
			for (int i = 0; i < form.getRiskList().size(); i++) {
				TaMasCondSubRisk riskform = form.getRiskList().get(i);
				risk = new TaMasCondSubRisk();
				risk.setBudgetYear(form.getBudgetYear());
				risk.setOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
				risk.setDutyCode(riskform.getDutyCode());
				risk.setRiskLevel(riskform.getRiskLevel());
				riskList.add(risk);
			}
		}
		riskRepository.saveAll(riskList);
	}
	
	public List<MasCondSubRiskVo> getRiskByBudgetYear(TaMasCondSubRisk form) {
		List<TaMasCondSubRisk> riskList = new ArrayList<>();
		List<ParamInfo> riskInfo= new ArrayList<>();
		List<MasCondSubRiskVo> riskListNew = new ArrayList<>();
		//get EXCISE_PRODUCT_TYPE and EXCISE_SERVICE_TYPE
		List<ParamInfo> paramInfoList = null;
		paramInfoList = new ArrayList<>();
		paramInfoList.addAll(ApplicationCache.getParamInfoListByGroupCode(PARAM_GROUP.EXCISE_PRODUCT_TYPE));
		paramInfoList.addAll(ApplicationCache.getParamInfoListByGroupCode(PARAM_GROUP.EXCISE_SERVICE_TYPE));
		
		MasCondSubRiskVo risk = null;
		riskList = riskRepository.findByBudgetYearAndOfficeCode(form.getBudgetYear(), UserLoginUtils.getCurrentUserBean().getOfficeCode());
		if (CollectionUtils.isNotEmpty(riskList)) {
			for (int i = 0; i < riskList.size(); i++) {
				risk = new MasCondSubRiskVo();
				risk.setDutyCode(riskList.get(i).getDutyCode());
				risk.setRiskLevel(riskList.get(i).getRiskLevel());
				for (ParamInfo paramInfo : paramInfoList) {
					if (paramInfo.getParamCode().equals(risk.getDutyCode())) {
						risk.setRiskDesc(paramInfo.getValue1());
						break;
					}
				}
				
				riskListNew.add(risk);
			}
		} else {
			riskInfo = ExciseUtils.getProductTypeAndServiceType();
			for (int i = 0; i < riskInfo.size(); i++) {
				risk = new MasCondSubRiskVo();
				risk.setDutyCode(riskInfo.get(i).getParamCode());
				for (ParamInfo paramInfo : paramInfoList) {
					if (paramInfo.getParamCode().equals(risk.getDutyCode())) {
						risk.setRiskDesc(paramInfo.getValue1());
						break;		
					}
				}
				riskListNew.add(risk);
			}
		}
		return riskListNew;
	}
	
	public void insertNoAudit(TaMasCondSubNoAudit form) {
		TaMasCondSubNoAudit noAudit = null;
		TaMasCondSubNoAudit oldNoAudit = null;
		oldNoAudit = noAuditRepository.findByBudgetYearAndOfficeCode(form.getBudgetYear(), UserLoginUtils.getCurrentUserBean().getOfficeCode());
		if (null == oldNoAudit) {
			noAudit = new TaMasCondSubNoAudit();
			noAudit.setBudgetYear(form.getBudgetYear());
			noAudit.setOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
			noAudit.setNoTaxAuditYearNum(form.getNoTaxAuditYearNum());
		} else {
			noAudit = noAuditRepository.findByBudgetYearAndOfficeCode(form.getBudgetYear(), UserLoginUtils.getCurrentUserBean().getOfficeCode());
			noAudit.setNoTaxAuditYearNum(form.getNoTaxAuditYearNum());
		}
		noAuditRepository.save(noAudit);
	}
	
	public TaMasCondSubNoAudit getNoAuditByBudgetYear(TaMasCondSubNoAudit form) {
		TaMasCondSubNoAudit noAudit = new TaMasCondSubNoAudit();
		noAudit = noAuditRepository.findByBudgetYearAndOfficeCode(form.getBudgetYear(), UserLoginUtils.getCurrentUserBean().getOfficeCode());
		return noAudit;
	}

}
