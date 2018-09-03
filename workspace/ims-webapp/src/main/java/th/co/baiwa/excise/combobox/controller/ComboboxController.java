package th.co.baiwa.excise.combobox.controller;

import java.util.ArrayList;
import java.util.Calendar;
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
import th.co.baiwa.excise.combobox.entity.Combobox;
import th.co.baiwa.excise.combobox.service.ComboboxService;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfHdr;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsHdr;
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
	
	@PostMapping("/getYearBackCount")
	@ResponseBody
	public List<Combobox> getYearBackCount() {
		List<Combobox> yearComboboxList = new ArrayList<Combobox>();
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < 10 ; i++) {
			String year = DateConstant.DateToString(cal.getTime(), DateConstant.YYYY);
			yearComboboxList.add(new Combobox(year,year));
			cal.add(Calendar.YEAR, -1);
		}
		return yearComboboxList;
	}
	
	@PostMapping("/findByBudgetYear")
	@ResponseBody
	public List<Combobox> findByBudgetYear(@RequestBody RiskAssRiskWsHdr riskAssRiskWsHdr) {
		return comboboxService.findByBudgetYear(riskAssRiskWsHdr.getBudgetYear());
	}

	@PostMapping("/findByRiskInfName")
	@ResponseBody
	public List<Combobox> findByRiskInfName(@RequestBody RiskAssInfHdr riskAssInfHdr) {
		return comboboxService.findByRiskInfName(riskAssInfHdr.getBudgetYear());
	}
	
	@PostMapping("/getDropByTypeAndParentId")
	@ResponseBody
	public List<Lov> findDropByTypeAndParentId(@RequestBody Lov lov) {
		logger.info("findDropByTypeAndParentId Type : "+lov.getType() + "|| parentId : "+ lov.getLovIdMaster() );
		return ApplicationCache.getListOfValueByTypeParentId(lov.getType(), lov.getLovIdMaster());
	}
}
