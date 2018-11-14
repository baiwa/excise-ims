package th.co.baiwa.excise.ta.controller;

import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ta.persistence.vo.PlanFromWsVo;
import th.co.baiwa.excise.ta.persistence.vo.Tsl010200FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Tsl010200Vo;
import th.co.baiwa.excise.ta.service.PlanFromWsHeaderService;

@Controller
@RequestMapping("api/taxAudit/selectList")
public class TaxAuditSelectListController {

	private Logger logger = LoggerFactory.getLogger(FilterExisePlanHeaderController.class);
	
	@Autowired
	private PlanFromWsHeaderService planFromWsHeaderService;
	
	
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				if (StringUtils.isNotBlank(text)) {
					setValue(LocalDate.parse(text, DateTimeFormatter.ofPattern(DateConstant.YYYY_MM_DD)));
				}
			}
			
			@Override
			public String getAsText() throws IllegalArgumentException {
				if (getValue() != null) {
					return DateTimeFormatter.ofPattern(DateConstant.YYYY_MM_DD).format((LocalDate) getValue());
				} else {
					return null;
				}
			}
		});
	}
	
	@PostMapping("/findCondition1")
	@ResponseBody
	public List<Tsl010200Vo> findCondition1(@RequestBody PlanFromWsVo vo) {
		logger.debug("saveToTaPlanSearchRick");
		List<Tsl010200Vo> list = planFromWsHeaderService.findCondition1(vo);
		return list;
	}
	@PostMapping("/findCondition2")
	@ResponseBody
	public List<Tsl010200Vo> findCondition2(@RequestBody PlanFromWsVo vo) {
		logger.debug("saveToTaPlanSearchRick");
		return planFromWsHeaderService.findCondition2(vo);
	}
	
	@PostMapping("/saveCondition1")
	@ResponseBody
	public List<Tsl010200Vo> saveCondition1(Tsl010200FormVo formVo){
		System.out.println(formVo);
		planFromWsHeaderService.saveCondition1(formVo);
		return formVo.getDataList();
		
	}
}
















