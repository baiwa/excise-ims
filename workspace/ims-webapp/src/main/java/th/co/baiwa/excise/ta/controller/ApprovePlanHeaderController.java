package th.co.baiwa.excise.ta.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.ta.persistence.entity.PlanWorksheetHeaderDetail;
import th.co.baiwa.excise.ta.persistence.entity.RequestFilterMapping;
import th.co.baiwa.excise.ta.service.PlanWorksheetHeaderService;

@Controller
@RequestMapping("api/filter/exise/approve/")
public class ApprovePlanHeaderController {
	
	private Logger logger = LoggerFactory.getLogger(ApprovePlanHeaderController.class);	
	
	@Autowired
	private PlanWorksheetHeaderService planWorksheetHeaderService;
	
	private final String SECTOR = "ภาคที่ ";
	
	@PostMapping("/list")
	@ResponseBody
	public ResponseDataTable<PlanWorksheetHeaderDetail> listdata(@ModelAttribute RequestFilterMapping vo) {
		logger.debug("analysNumber : " + vo.getAnalysNumber());		
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeId();
		String sector = officeCode.substring(0,2);
		if (!"00".endsWith(sector)) {
			vo.setSector(SECTOR+Integer.toString(Integer.parseInt(sector)));
		}		
		return planWorksheetHeaderService.queryPlanWorksheetHeaderDetil(vo);
		
	}


}
