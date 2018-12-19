package th.co.baiwa.excise.combobox.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.excise.combobox.dao.ConfigDao;
import th.co.baiwa.excise.combobox.entity.Combobox;
import th.co.baiwa.excise.combobox.entity.ConfigCreteria;
import th.co.baiwa.excise.ia.persistence.dao.QuestionnaireHeaderDao;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireHeader;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfHdr;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsHdr;
import th.co.baiwa.excise.ia.service.RiskAssInfService;
import th.co.baiwa.excise.ia.service.RiskAssRiskWsService;
@Service
public class ComboboxService {

	@Autowired
	private QuestionnaireHeaderDao QuestionnaireHeaderDao;
	
	@Autowired
	private RiskAssRiskWsService riskAssRiskWsHdrService;
	
	@Autowired
	private RiskAssInfService riskAssInfService;
	
	@Autowired
	private ConfigDao configDao;
	
	public List<Combobox> findQuestionnaireHeader(){
		List<Combobox> comboboxList = new ArrayList<Combobox>();
		List<QuestionnaireHeader> questionnaireHeaderList = QuestionnaireHeaderDao.findByCriteria(new QuestionnaireHeader());
		Combobox combobox = null;
		for (QuestionnaireHeader questionnaireHeader : questionnaireHeaderList) {
			combobox = new Combobox();
			combobox.setValue(questionnaireHeader.getQtnHeaderCode());
			combobox.setDescription(questionnaireHeader.getQtnHeaderName());
			comboboxList.add(combobox);
		}
		return comboboxList;
	}
	
	public List<Combobox> findByBudgetYear(String year){
		List<Combobox> comboboxList = new ArrayList<Combobox>();
		List<RiskAssRiskWsHdr> riskAssRiskWsHdrList = riskAssRiskWsHdrService.findByBudgetYear(year);
		Combobox combobox = null;
		for (RiskAssRiskWsHdr riskAssRiskWsHdr : riskAssRiskWsHdrList) {
			combobox = new Combobox();
			combobox.setValue(riskAssRiskWsHdr.getRiskHdrName());
			combobox.setDescription(riskAssRiskWsHdr.getRiskHdrName());
			comboboxList.add(combobox);
		}
		return comboboxList;
	}
	
	public List<Combobox> findByRiskInfName(String year){
		List<Combobox> comboboxList = new ArrayList<Combobox>();
		
		List<RiskAssInfHdr> riskAssInfHdrList = riskAssInfService.findByBudgetYear(year);
		Combobox combobox = null;
		for (RiskAssInfHdr riskAssInfHdr : riskAssInfHdrList) {
			combobox = new Combobox();
			combobox.setValue(riskAssInfHdr.getRiskAssInfHdrName());
			combobox.setDescription(riskAssInfHdr.getRiskAssInfHdrName());
			comboboxList.add(combobox);
		}
		return comboboxList;
	}
	
	public ConfigCreteria configCreteria() {
		Lov lov = configDao.getConfigCreteria();
		ConfigCreteria config = new ConfigCreteria();
		config.setMonthNonPay(lov.getValue1());
		config.setPercent1(lov.getValue2());
		config.setPercent2(lov.getValue3());
		config.setPercent3(lov.getValue4());
		return config;
	}
}
