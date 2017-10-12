package th.co.baiwa.buckwaframework.backend.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import th.co.baiwa.buckwaframework.backend.service.SessionManagementService;

@Controller
@RequestMapping("/backend")
public class SessionRegistryController {
	
	private static final Logger logger = LoggerFactory.getLogger(SessionRegistryController.class);
	
	@Autowired
	private SessionManagementService sessionManagementService;
	
	@GetMapping("/session-registry.htm")
	public ModelAndView getActiveSessionList() {
		logger.info("getActiveSessionList");
		
		List<SessionInformation> sessionInfoList = sessionManagementService.getActiveSessionList();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("sessionInfoList", sessionInfoList);
		mav.setViewName("backend/session-resigtry");
		
		return mav;
	}
	
	@PostMapping(value="/session-registry.htm", params={"logoutRow"})
    public String removeRow(HttpServletRequest httpRequest) {
        String sessionId = httpRequest.getParameter("logoutRow");
        sessionManagementService.logoutSession(sessionId);
        return "redirect:/backend/session-registry.htm";
    }
	
}
