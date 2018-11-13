package th.co.baiwa.excise.ta.controller;

import java.beans.PropertyEditorSupport;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ta.persistence.vo.PlanFromWsVo;
import th.co.baiwa.excise.ta.service.PlanFromWsHeaderService;

@Controller
@RequestMapping("api/taxAudit/selectList")
public class TaxAuditSelectListController {

	private Logger logger = LoggerFactory.getLogger(FilterExisePlanHeaderController.class);
	
	@Autowired
	private PlanFromWsHeaderService planFromWsHeaderService;
	
	@PostMapping("/saveToTaPlanSearchRick")
	@ResponseBody
	public void saveToTaPlanSearchRick(@ModelAttribute PlanFromWsVo vo) {
		logger.debug("saveToTaPlanSearchRick");
		try {
			planFromWsHeaderService.findExciseIdOrderByPercenTax(vo);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
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
}
















