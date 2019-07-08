package th.co.baiwa.buckwaframework.security.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;

@WebListener
public class SessionListener implements HttpSessionListener {
	
	@Value("${application.timeout}")
	private String applicationTimeout;
	
	@Override
	public void sessionCreated(HttpSessionEvent event) {
		if (StringUtils.isNotEmpty(applicationTimeout)) {
			int sessionTimeout = Integer.parseInt(applicationTimeout);
			event.getSession().setMaxInactiveInterval(sessionTimeout * 60);
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent event) {
		
	}

}
