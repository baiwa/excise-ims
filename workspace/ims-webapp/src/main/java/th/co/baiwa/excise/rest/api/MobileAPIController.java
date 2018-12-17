package th.co.baiwa.excise.rest.api;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.net.www.content.text.plain;
import th.co.baiwa.excise.ia.persistence.vo.InputIncomeExciseAudit;
import th.co.baiwa.excise.ia.persistence.vo.RequestApiSaveIncome;
import th.co.baiwa.excise.ia.persistence.vo.ResponseApiSaveIncome;
import th.co.baiwa.excise.ia.persistence.vo.ResponseMobileCheckIncomeExciseAudit;
import th.co.baiwa.excise.ia.service.IncomeExciseAudService;
import th.co.baiwa.excise.ia.service.MobileCheckExciseIncomeService;
import th.co.baiwa.excise.sys.domain.Notification;
import th.co.baiwa.excise.sys.service.NotificationService;
import th.co.baiwa.excise.ta.persistence.entity.PlanTaxAudit;
import th.co.baiwa.excise.ta.persistence.vo.NotificationFormVo;
import th.co.baiwa.excise.ta.persistence.vo.NotificationVo;
import th.co.baiwa.excise.ta.persistence.vo.PlanCriteriaVo;
import th.co.baiwa.excise.ta.persistence.vo.PlanTaxAuditVo;
import th.co.baiwa.excise.ta.service.PlanTaxAuditService;

@Controller
@RequestMapping("mobile-api")
public class MobileAPIController {

	private static final Logger logger = LoggerFactory.getLogger(MobileAPIController.class);

	
	
	@Autowired
	private IncomeExciseAudService incomeExciseAudService;
	
	@Autowired
	private MobileCheckExciseIncomeService mobileCheckExciseIncomeService;
	
	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private PlanTaxAuditService planTaxAuditService;

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
	public List<Notification> simpleNotificationMockup(@RequestBody Notification notification) {
		logger.info("simpleNotificationMockup");
		return notificationService.findNotificationByType(notification);
	}
	
	@PostMapping("/updateNotification")
	@ResponseBody
	public String updateNotification(@RequestBody NotificationFormVo notificationFormVo) {
		logger.info("updateNotification");
		String status = "Success";
		 try {
			 notificationService.updateNotification(notificationFormVo.getId());
		} catch (Exception e) {
			status = "fail";
			logger.info("updateNotification fail :",e);
		}
		return status;
	}
	
	
	@PostMapping("/viewTaCriteriaByTaPlanTaxAuditId")
	@ResponseBody
	public List<PlanCriteriaVo> viewTaCriteriaByTaPlanTaxAuditId(@RequestBody PlanTaxAuditVo planTaxAudit ) {
		logger.info("viewTaCriteriaByTaPlanTaxAuditId");
		List<PlanCriteriaVo> planCriteriaVoList = new ArrayList<PlanCriteriaVo>();
		 try {
			 planCriteriaVoList = notificationService.findPlanConditionDetail(planTaxAudit.getAnalysNumber());
			 notificationService.updateNotification(planTaxAudit.getId());
		} catch (Exception e) {
			logger.info("updateNotification fail :",e);
		}
		return planCriteriaVoList;
	}
	
	@PostMapping("/findPlanTaxAuditById")
	@ResponseBody
	public List<PlanCriteriaVo> findPlanTaxAuditById(@RequestBody PlanTaxAuditVo planTaxAudit ) {
		logger.info("findPlanTaxAuditById");
		List<PlanCriteriaVo> planCriteriaVoList = new ArrayList<PlanCriteriaVo>();
		 try {
			 planCriteriaVoList = notificationService.findPlanConditionDetail(planTaxAudit.getAnalysNumber());
			 notificationService.updateNotification(planTaxAudit.getId());
		} catch (Exception e) {
			logger.info("updateNotification fail :",e);
		}
		return planCriteriaVoList;
	}
	
	
	@PostMapping("/countNotification")
	@ResponseBody
	public List<NotificationVo> countNotification() {
		logger.info("countNotification");
		List<NotificationVo> notificationVoList = new ArrayList<NotificationVo>();
		 try {
			 notificationVoList = notificationService.countNotification();
		} catch (Exception e) {
			logger.info("countNotification fail :",e);
		}
		return notificationVoList;
	}
	
	@PostMapping("/findPlanTaxAuditViewById")
	@ResponseBody
	public PlanTaxAudit findPlanTaxAuditViewById(@RequestBody PlanTaxAuditVo planTaxAuditVo) {
		logger.info("findPlanTaxAuditOById");
		PlanTaxAudit planTaxAudit = null;
		try {
			planTaxAudit = planTaxAuditService.findPlanTaxAuditById(planTaxAuditVo.getId());
		} catch (Exception e) {
			logger.info("countNotification fail :",e);
		}
		return planTaxAudit;
	}
	
	
	
	
	
	
	
}
