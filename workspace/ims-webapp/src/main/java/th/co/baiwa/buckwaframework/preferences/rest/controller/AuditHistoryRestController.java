package th.co.baiwa.buckwaframework.preferences.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.SysAuditHistoryAction;
import th.co.baiwa.buckwaframework.preferences.service.SysAuditHistoryActionService;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;

@Controller
@RequestMapping("/api/audit/history")
public class AuditHistoryRestController {

	private static final Logger logger = LoggerFactory.getLogger(AuditHistoryRestController.class);
	
	@Autowired
	private SysAuditHistoryActionService sysAuditHistoryActionService;
	
	
	@PostMapping("/addHistory")
	@ResponseBody
	public void addHistory(@ModelAttribute SysAuditHistoryAction sysAuditHistoryAction) {
		String userLogin = UserLoginUtils.getCurrentUsername();
		logger.info("add audit log by : " , userLogin);
		sysAuditHistoryActionService.insertSysAuditHistoryAction(sysAuditHistoryAction);
	}
}
