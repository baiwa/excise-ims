package th.co.baiwa.excise.combobox.controller;

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
import th.co.baiwa.excise.combobox.entity.Combobox;
import th.co.baiwa.excise.combobox.service.ComboboxService;
import th.co.baiwa.excise.ta.service.PlanWorksheetHeaderService;

@Controller
@RequestMapping("api/combobox/controller")
public class ComboboxController {
	
	private Logger logger = LoggerFactory.getLogger(ComboboxController.class);
	
	@Autowired
	private ComboboxService comboboxService;
	
	@Autowired
	private PlanWorksheetHeaderService planWorksheetHeaderService;	
	
	@PostMapping("/comboboxHeaderQuestionnaire")
	@ResponseBody
	public List<Combobox> comboboxHeaderQuestionnaire() {
		logger.info("get comboboxHeaderQuestionnaire");
		List<Combobox> conboboxList = comboboxService.findQuestionnaireHeader();
		return conboboxList;
	}
	
	@PostMapping("/getAnalysNumber")
	@ResponseBody
	public List<String> getAnalysNumber() {
		List<String> li = planWorksheetHeaderService.queryAnalysNumberFromHeader();
		return li;
	}
	
	@PostMapping("/getCoordinates")
	@ResponseBody
	public List<Lov> getCoordinates() {
		List<Lov> li = ApplicationCache.getListOfValueByValueType("PRODUCT_TYPE");
		return li;
	}
	
	
	@PostMapping("/getSector")
	@ResponseBody
	public List<Lov> getSector() {
		List<Lov> li = ApplicationCache.getListOfValueByValueType("SECTOR_VALUE");
		return li;
	}
	
	@PostMapping("/getExciseId")
	@ResponseBody
	public List<String> getExciseId() {
		List<String> li = planWorksheetHeaderService.getExciseIdFlagSFromHeader();
		return li;
	}
	
	@PostMapping("/getExciseBaseControl")
	@ResponseBody
	public List<Lov> getExciseBaseControl() {
		List<Lov> lov = ApplicationCache.getListOfValueByValueType("EXCISE_BASE_CONTROL");
		return lov;
	}
	
	@PostMapping("/getRiskName")
	@ResponseBody
	public List<Lov> getRiskName() {
		List<Lov> lov = ApplicationCache.getListOfValueByValueType("RISK_NAME");
		return lov;
	}
	
	@PostMapping("/getRiskLevel")
	@ResponseBody
	public List<Lov> getRiskLevel() {
		List<Lov> lov = ApplicationCache.getListOfValueByValueType("RISK_LEVEL");
		return lov;
	}

}
