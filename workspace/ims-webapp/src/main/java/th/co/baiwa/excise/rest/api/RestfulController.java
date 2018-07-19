package th.co.baiwa.excise.rest.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.ta.persistence.entity.PlanWorksheetHeader;
import th.co.baiwa.excise.ta.service.PlanWorksheetHeaderService;

@Controller
@RequestMapping("/api/preferences/restful")
public class RestfulController {
	
	private static final Logger logger = LoggerFactory.getLogger(RestfulController.class);

	@Autowired
	private PlanWorksheetHeaderService planWorksheetHeaderService;
	
	@GetMapping("/exciseList")
	@ResponseBody
	public List<PlanWorksheetHeader> exciseList(@ModelAttribute PlanWorksheetHeader vo) {
		logger.debug("analysNumber : " + vo.getAnalysNumber());
		System.out.println(vo.getAnalysNumber());
		return planWorksheetHeaderService.queryPlanWorksheetHeaderCriteria(vo);
	}
}
