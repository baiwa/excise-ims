package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.dao.QuestionnaireHeaderDao;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireHeader;

 
@Service
public class QuestionnaireHeaderService {
	
	
	@Autowired
	private QuestionnaireHeaderDao QuestionnaireHeaderDao;
	
	public List<QuestionnaireHeader> findByCriteria(QuestionnaireHeader QuestionnaireHeader) {
		return QuestionnaireHeaderDao.findByCriteria(QuestionnaireHeader);
	}
	
	
	
}
