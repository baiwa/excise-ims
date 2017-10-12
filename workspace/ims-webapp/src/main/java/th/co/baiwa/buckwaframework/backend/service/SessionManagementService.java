package th.co.baiwa.buckwaframework.backend.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.security.model.UserDetails;

@Service("sessionManagementService")
public class SessionManagementService {
	
	private static final Logger logger = LoggerFactory.getLogger(SessionManagementService.class);
	
	@Autowired
	private SessionRegistry sessionRegistry;
	
	public SessionInformation getSessionInformation(String sessionId) {
		return sessionRegistry.getSessionInformation(sessionId);
	}
	
	public List<SessionInformation> getActiveSessionList() {
		List<SessionInformation> activeSessions = new ArrayList<SessionInformation>();
		for (Object principal : sessionRegistry.getAllPrincipals()) {
			activeSessions.addAll(sessionRegistry.getAllSessions(principal, false));
		}
		return activeSessions;
	}
	
	public void logoutSession(String sessionId) {
		SessionInformation session = sessionRegistry.getSessionInformation(sessionId);
		if (session != null) {
			UserDetails userDetails = (UserDetails) session.getPrincipal();
			logger.info("Logout User:" + userDetails.getUsername() + ", SessionId:" + session.getSessionId());
			session.expireNow();
		}
	}
	
}
