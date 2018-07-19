package th.co.baiwa.excise.ta.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.ta.persistence.entity.PlanWorksheetHeaderDetail;
import th.co.baiwa.excise.ta.persistence.entity.RequestFilterMapping;
import th.co.baiwa.excise.ta.service.PlanWorksheetHeaderService;

@Controller
@RequestMapping("api/filter/exise")
public class FilterExisePlanHeaderController {

	private Logger logger = LoggerFactory.getLogger(FilterExisePlanHeaderController.class);

	@Autowired
	private PlanWorksheetHeaderService planWorksheetHeaderService;

	@PostMapping("/list")
	@ResponseBody
	public ResponseDataTable<PlanWorksheetHeaderDetail> listdata(@ModelAttribute RequestFilterMapping vo) {
		logger.debug("analysNumber : " + vo.getAnalysNumber());
		System.out.println(vo.getAnalysNumber());
		return planWorksheetHeaderService.queryPlanWorksheetHeaderDetil(vo);
	}

	@GetMapping("/getWorkSheetNumber/{id}")
	@ResponseBody
	public String getWorkSheetNumber(@PathVariable("id") String id) {
		return planWorksheetHeaderService.getWorkSheetNumber(id);
	}

	@PostMapping("/getStartEndDate")
	@ResponseBody
	public List<String> getStartDateAndEndDateFromAnalysNumber(@ModelAttribute PlanWorksheetHeaderDetail vo) {
		logger.debug("analysNumber : " + vo.getAnalysNumber());
		return planWorksheetHeaderService.getStartDateAndEndDateFromAnalysNumber(vo.getAnalysNumber());
	}

	@PostMapping("/updateStatusPlanWsHeader")
	@ResponseBody
	public void updateStatusPlanWsHeader(@ModelAttribute RequestFilterMapping vo) {
		logger.debug("vo.getNum1() : " + vo.getNum1());
		planWorksheetHeaderService.updateStatusFlg(vo);
	}

	@PostMapping("/listFullDataNoPaging")
	@ResponseBody
	public Message planWorkSheetHeader(@RequestBody RequestFilterMapping vo) {
		logger.debug("analysNumber : " + vo.getFlag());
		int count = planWorksheetHeaderService.updatePlanWorksheetHeaderByExciseList(vo);
		System.out.println(vo.getAnalysNumber());
		Message msg = null;
		if (count == 0) {
			msg = ApplicationCache.getMessage("MSG_00003");
		} else {
			msg = ApplicationCache.getMessage("MSG_00002");
		}
		return msg;
	}
	
	@GetMapping("/apiList")
	@ResponseBody
	public ResponseDataTable<PlanWorksheetHeaderDetail> apiList(@ModelAttribute RequestFilterMapping vo) {
		logger.debug("analysNumber : " + vo.getAnalysNumber());
		System.out.println(vo.getAnalysNumber());
		return planWorksheetHeaderService.queryPlanWorksheetHeaderDetil(vo);
	}

}
