package th.co.baiwa.excise.ia.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.entity.IntCtrlAss;
import th.co.baiwa.excise.ia.persistence.repository.IntCtrlAssRepository;
import th.co.baiwa.excise.ws.WebServiceExciseService;
import th.co.baiwa.excise.ws.entity.response.assessment.Assessment;

@Service
public class Int02m51Service {
	private Logger logger = LoggerFactory.getLogger(Int02m51Service.class);
	
	@Autowired
	private IntCtrlAssRepository intCtrlAssRepository;
	
	@Autowired
	private WebServiceExciseService webServiceExciseService;	
	
	
	public List<IntCtrlAss> findByBudgetYearAndOfficeCode(String budgetYear , String officeCode){
		logger.info("findByBudgetYearAndOfficeCode {} , {}" , budgetYear , officeCode);
		return intCtrlAssRepository.findByBudgetYearAndOfficeCode(budgetYear, officeCode);
	}
	
	public IntCtrlAss insertIntCtrlAss(IntCtrlAss intCtrlAss){
		logger.info("insertIntCtrlAss {} , {} , {}" , intCtrlAss.getBudgetYear() , intCtrlAss.getOfficeCode() , intCtrlAss.getIntCtrlAssName());
		return intCtrlAssRepository.save(intCtrlAss);
	}
	
	public Assessment assessmentForm1(IntCtrlAss intCtrlAss){
		logger.info("assessment {} , {} , {}" , intCtrlAss.getBudgetYear() , intCtrlAss.getOfficeCode());
		return webServiceExciseService.assessmentForm1(intCtrlAss.getBudgetYear(), intCtrlAss.getOfficeCode());
	}
	
	
	
	
	
}
