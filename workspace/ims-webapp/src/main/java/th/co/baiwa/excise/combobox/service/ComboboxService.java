package th.co.baiwa.excise.combobox.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.combobox.entity.Combobox;
import th.co.baiwa.excise.ia.persistence.dao.QuestionnaireHeaderDao;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireHeader;

@Service
public class ComboboxService {

	@Autowired
	private QuestionnaireHeaderDao QuestionnaireHeaderDao;
	
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
	
}
