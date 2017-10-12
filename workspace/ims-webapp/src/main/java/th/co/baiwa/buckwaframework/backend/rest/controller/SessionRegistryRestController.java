package th.co.baiwa.buckwaframework.backend.rest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.co.baiwa.buckwaframework.backend.service.SessionManagementService;

@RestController
@RequestMapping("/api/backend/session")
public class SessionRegistryRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(SessionRegistryRestController.class);
	
	@Autowired
	private SessionManagementService sessionManagementService;
	
	@GetMapping
	public List<SessionInformation> getActiveSessionList() {
		logger.info("getActiveSessionList");
		return sessionManagementService.getActiveSessionList();
	}
	
}
