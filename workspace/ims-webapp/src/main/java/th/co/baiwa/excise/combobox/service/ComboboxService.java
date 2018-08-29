package th.co.baiwa.excise.combobox.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.combobox.entity.Combobox;
import th.co.baiwa.excise.ia.persistence.dao.QuestionnaireHeaderDao;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireHeader;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfHdr;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsHdr;
import th.co.baiwa.excise.ia.service.RiskAssRiskWsService;
import th.co.baiwa.excise.ia.service.RiskAssInfService;
@Service
public class ComboboxService {

	@Autowired
	private QuestionnaireHeaderDao QuestionnaireHeaderDao;
	
	@Autowired
	private RiskAssRiskWsService riskAssRiskWsHdrService;
	
	@Autowired
	private RiskAssInfService riskAssInfService;
	
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
}
