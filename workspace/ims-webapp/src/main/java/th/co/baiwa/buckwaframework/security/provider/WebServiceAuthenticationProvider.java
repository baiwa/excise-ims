package th.co.baiwa.buckwaframework.security.provider;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.ROLE;
import th.co.baiwa.buckwaframework.security.domain.UserDetails;
import th.co.baiwa.ims.ws.userldap.LoginLdap;
import th.co.baiwa.ims.ws.userldap.Response;
import th.co.baiwa.ims.ws.userldap.Role;

@Component("wsAuthenticationProvider")
public class WebServiceAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	
	private Logger logger = LoggerFactory.getLogger(WebServiceAuthenticationProvider.class);
	
	@Value("${application.env}")
	private String env;

//	@Autowired
//	private WebServiceExciseService webServiceExciseService;

	@Autowired
	private LoginLdap loginLdapProxy;
	
	@Override
	protected void additionalAuthenticationChecks(org.springframework.security.core.userdetails.UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

	}

	@Override
	protected org.springframework.security.core.userdetails.UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		logger.info("WebServiceAuthenticationProvider : {}" , username);
		String password = authentication.getCredentials().toString();
//		List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
//		grantedAuthorityList.add(new SimpleGrantedAuthority(ROLE.USER));
//		grantedAuthorityList.add(new SimpleGrantedAuthority(ROLE.ADMIN));
//		UserDetails userDetails = new UserDetails(username, password, grantedAuthorityList);
		UserDetails userDetails = null;
		
		if ("excise".equals(env)) {
//			Response response = webServiceExciseService.webServiceLdap(name, password);
//			if ("000".equals(response.getCode())) {
//				userDetails.setOfficeId(response.getOfficeCode());
//				userDetails.setUserThaiName(response.getUserThaiName());
//				userDetails.setUserThaiSurname(response.getUserThaiSurname());
//				userDetails.setTitle(response.getTitle());
//				logger.info("login success {}" , username);
//				
//			} else {
//				throw new BadCredentialsException(response.getStatusCode());
//			}
		}else {
			// Login with LoginLdapUser
			Response response = loginLdapProxy.login(username, password);
			if ("000".equals(response.getCode())) {
				// Assign Default ROLE_USER
				List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
				grantedAuthorityList.add(new SimpleGrantedAuthority(ROLE.USER));
				// Assign ROLE_ADMIN
				if (username.contains("admin")) {
					grantedAuthorityList.add(new SimpleGrantedAuthority(ROLE.ADMIN));
				}
				// Assign ROLE from WS
				for (Role wsRole : response.getRoles().getRole()) {
					grantedAuthorityList.add(new SimpleGrantedAuthority(wsRole.getRoleName()));
				}
				
				// Create UserDetails
				userDetails = new UserDetails(username, password, grantedAuthorityList);
				
				// Assign Detail for UserDetailes
				userDetails.setUserThaiName(response.getUserThaiName());
				userDetails.setUserThaiSurname(response.getUserThaiSurname());
				userDetails.setTitle(response.getTitle());
				userDetails.setOfficeCode(response.getOfficeCode());
			} else {
				throw new BadCredentialsException(response.getDescription());
			}
		}
		
		return userDetails;
	}

}
