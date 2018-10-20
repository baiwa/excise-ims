package th.co.baiwa.excise.ta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.ta.persistence.entity.analysis.PlanWorksheetHeader;
import th.co.baiwa.excise.ta.service.AnalysisService;

@Controller
@RequestMapping("api/ta/analysis")
public class AnalysisController {

	@Autowired
	private AnalysisService analysisService;

	@GetMapping("/findExciseId")
	@ResponseBody
	public List<LabelValueBean> findExciseId() {
		List<LabelValueBean> dataList = analysisService.findExciseId();
		return dataList;
	}
	
	@PostMapping("/findByExciseId")
	@ResponseBody
	public PlanWorksheetHeader findByExciseId(@RequestBody String exciseId) {
		return analysisService.findByExciseId(exciseId);	
	}
}
