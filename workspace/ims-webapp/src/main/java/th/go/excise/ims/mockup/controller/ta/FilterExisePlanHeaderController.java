package th.go.excise.ims.mockup.controller.ta;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.go.excise.ims.mockup.domain.ta.PlanWorksheetHeaderDetail;
import th.go.excise.ims.mockup.service.ta.PlanWorksheetHeaderService;

@Controller
@RequestMapping("filter/exise")
public class FilterExisePlanHeaderController {

	private Logger logger = LoggerFactory.getLogger(FilterExisePlanHeaderController.class);
	@Autowired
	private PlanWorksheetHeaderService planWorksheetHeaderService;	
	
	@PostMapping("/list")
	@ResponseBody
	public ResponseDataTable<PlanWorksheetHeaderDetail> listdata(@ModelAttribute PlanWorksheetHeaderDetail vo) {
		logger.debug("analysNumber : "+vo.getAnalysNumber());
		System.out.println(vo.getAnalysNumber());
		return planWorksheetHeaderService.queryPlanWorksheetHeaderDetil(vo);
	}
	

}
