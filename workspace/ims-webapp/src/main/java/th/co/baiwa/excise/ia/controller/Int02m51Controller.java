package th.co.baiwa.excise.ia.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.ia.persistence.entity.IntCtrlAss;
import th.co.baiwa.excise.ia.service.Int02m51Service;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.ws.entity.response.assessment.Assessment;
import th.co.baiwa.excise.ws.entity.response.assessment.AssessmentVo;
import th.co.baiwa.excise.ws.entity.response.assessment.Datum;
import th.co.baiwa.excise.ws.entity.response.assessment.TopicDetail;

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
	public List<IntCtrlAss> downloadNewTempage(@RequestBody IntCtrlAss intput){
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
	@PostMapping("/getAssessmentForm1")
	@ResponseBody
	public List<AssessmentVo> getAssessmentForm1(@RequestBody IntCtrlAss intput){
		logger.info("getAssessmentForm1 officeCode {} year {}" ,intput.getOfficeCode() , intput.getBudgetYear() );
		List<AssessmentVo> assessmentVoList = new ArrayList<AssessmentVo>();
		Assessment assessment = int02m51Service.assessmentForm1(intput);
		if(BeanUtils.isNotEmpty(assessment) && BeanUtils.isNotEmpty(assessment.getData())) {
			List<Datum> datumList = assessment.getData();
			Datum datum = new Datum();
			for (int i = 0; i < datumList.size(); i++) {
				datum = new Datum();
				datum = datumList.get(i);
				if(BeanUtils.isNotEmpty(datum.getTopicDetail())) {
					for (TopicDetail topic : datum.getTopicDetail()) {
						AssessmentVo assessmentVo = new AssessmentVo();
						if("1".equals(topic.getTopicLevel()) || "2".equals(topic.getTopicLevel())) {
							assessmentVo.setColumn1(topic.getTopicAnswer());
							
						}else if("3".equals(topic.getTopicLevel())) {
							assessmentVo.setColumn2(topic.getTopicAnswer());
							assessmentVoList.add(assessmentVo);
						}
						
					}
				}
			}
		}
		
		return assessmentVoList;
	}
	
	
}
