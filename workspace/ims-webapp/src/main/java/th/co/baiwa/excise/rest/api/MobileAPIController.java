package th.co.baiwa.excise.rest.api;

import java.util.ArrayList;
import java.util.List;

import javax.management.Notification;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.ia.persistence.vo.InputIncomeExciseAudit;
import th.co.baiwa.excise.ia.persistence.vo.RequestApiSaveIncome;
import th.co.baiwa.excise.ia.persistence.vo.ResponseApiSaveIncome;
import th.co.baiwa.excise.ia.persistence.vo.ResponseMobileCheckIncomeExciseAudit;
import th.co.baiwa.excise.ia.service.IncomeExciseAudService;
import th.co.baiwa.excise.ia.service.MobileCheckExciseIncomeService;

@Controller
@RequestMapping("mobile-api")
public class MobileAPIController {

	private static final Logger logger = LoggerFactory.getLogger(MobileAPIController.class);

	
	
	@Autowired
	private IncomeExciseAudService incomeExciseAudService;
	
	@Autowired
	private MobileCheckExciseIncomeService mobileCheckExciseIncomeService;

	@PostMapping("/mobileCheckIncomeExicse")
	@ResponseBody
	public ResponseMobileCheckIncomeExciseAudit mobileCheckIncomeExicse(@RequestBody InputIncomeExciseAudit user) {
		logger.info("mobileCheckIncomeExicse : {}" , user.getUsername());
		ResponseMobileCheckIncomeExciseAudit resVo = incomeExciseAudService.findbyAssignToAndStatus(user);
		return resVo;
	}
	
	
	@PostMapping("/updateRiskTask")
	@ResponseBody
	public ResponseApiSaveIncome updateRiskTask(@RequestBody RequestApiSaveIncome requestApiSaveIncome) {
		logger.info("updateRiskTask");
		return mobileCheckExciseIncomeService.updateRiskTask(requestApiSaveIncome);
	}
	
	
	
	@PostMapping("/simpleNotificationMockup")
	@ResponseBody
	public List<Notification>simpleNotificationMockup() {
		logger.info("simpleNotificationMockup");
		List<Notification> notificationList = new ArrayList<Notification>();
		notificationList.add( new Notification("TaxAudit", "แจ้งเตือน", 1, "สร้างกระดาษทำการเสร็จ"));
		notificationList.add( new Notification("TaxAudit", "แจ้งเตือนขออนุมัติแผ่นการตรวจสอบ", 1, "รบกวนขออนุมัติแผนการตรวจสอบภาษีประจำปีงบประมาณ 2562"));
		return notificationList;
	}
	
	
}
