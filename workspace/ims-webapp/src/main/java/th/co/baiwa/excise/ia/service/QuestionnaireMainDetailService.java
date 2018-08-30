package th.co.baiwa.excise.ia.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireMain;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireMinor;
import th.co.baiwa.excise.ia.persistence.repository.QuestionnaireMainDetailRepository;
import th.co.baiwa.excise.ia.persistence.repository.QuestionnaireMinorDetailRepository;

@Service
public class QuestionnaireMainDetailService {
	private Logger logger = LoggerFactory.getLogger(QuestionnaireMainDetailService.class);
	
	@Autowired
	private QuestionnaireMainDetailRepository questionnaireMainDetailRepository;
	
	@Autowired
	private QuestionnaireMinorDetailRepository questionnaireMinorDetailRepository;
	
	public List<Object> findQtnMain(String headerCode) {
		List<QuestionnaireMain> mainList = questionnaireMainDetailRepository.findByHeaderCode(headerCode);
		
		List<Object> obj = new ArrayList<>();
		for (QuestionnaireMain questionnaireMain : mainList) {
			List<QuestionnaireMinor> minorList = questionnaireMinorDetailRepository.findByHeaderCode(questionnaireMain.getQtnMainDetailId());
			obj.add(questionnaireMain);
			for (QuestionnaireMinor questionnaireMinor : minorList) {
				obj.add(questionnaireMinor);
			}
		}
		return obj;
	}

//	public List<QuestionnaireMinor> findQtnMinor(String headerCode) {
//		return questionnaireMinorDetailRepository.findByHeaderCode(headerCode);
//	}
}
