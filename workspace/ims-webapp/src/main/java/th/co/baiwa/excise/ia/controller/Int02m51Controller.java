package th.co.baiwa.excise.ia.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.ia.persistence.entity.IntCtrlAss;
import th.co.baiwa.excise.ia.service.Int02m51Service;
import th.co.baiwa.excise.utils.BeanUtils;

@Controller
@RequestMapping("api/ia/int02m51")
public class Int02m51Controller {
	
	private Logger logger = LoggerFactory.getLogger(Int02m51Controller.class);
	
	private final String ASSESSMENT_INTERNAL_AUDIT = "ASSESSMENT_INTERNAL_AUDIT";
	@Autowired
	private Int02m51Service int02m51Service;
	
	@PostMapping("/searchIntCtrlAss")
	@ResponseBody
	public List<IntCtrlAss> searchIntCtrlAss(IntCtrlAss intCtrlAss){
		logger.info("searchIntCtrlAss");
		
		return null;
		
	}
	
	@PostMapping("/downloadNewTempage")
	@ResponseBody
	public List<IntCtrlAss> downloadNewTempage(IntCtrlAss intput){
		logger.info("downloadNewTempage officeCode {} year {}" ,intput.getOfficeCode() , intput.getBudgetYear() );
		List<IntCtrlAss> intCtrlAsseList = int02m51Service.findByBudgetYearAndOfficeCode(intput.getBudgetYear(), intput.getOfficeCode());
		if(BeanUtils.isEmpty(intCtrlAsseList)) {
			logger.info("new Data");
			intCtrlAsseList = new ArrayList<IntCtrlAss>();
			IntCtrlAss intCtrlAss = null;
			List<Lov> lovConfigList = ApplicationCache.getListOfValueByValueType(ASSESSMENT_INTERNAL_AUDIT);
			for (Lov lov : lovConfigList) {
				intCtrlAss = new IntCtrlAss();
				intCtrlAss.setBudgetYear(intput.getBudgetYear());
				intCtrlAss.setOfficeCode(intput.getOfficeCode());
				intCtrlAss.setIntCtrlAssName(lov.getValue1());
				int02m51Service.insertIntCtrlAss(intCtrlAss);
			}
			intCtrlAsseList = int02m51Service.findByBudgetYearAndOfficeCode(intput.getBudgetYear(), intput.getOfficeCode());
		}else {
			logger.info("Exist Data");
		}
		return intCtrlAsseList;
		
	}
	
	
}
