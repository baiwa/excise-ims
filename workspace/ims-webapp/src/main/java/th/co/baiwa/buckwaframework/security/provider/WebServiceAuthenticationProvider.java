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

import baiwa.co.th.ws.Response;
import th.co.baiwa.buckwaframework.security.domain.UserDetails;
import th.co.baiwa.excise.ws.WebServiceExciseService;
import th.go.excise.dexsrvint.schema.authenandgetuserrole.AuthenAndGetUserRoleResponse;
import th.go.excise.dexsrvint.schema.ldapuserbase.MessageBase;

@Component("wsAuthenticationProvider")
public class WebServiceAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	
	private Logger logger = LoggerFactory.getLogger(WebServiceAuthenticationProvider.class);

	@Autowired
	private WebServiceExciseService webServiceExciseService;

	@Value("${application.env}")
	private String env;

	@Override
	protected void additionalAuthenticationChecks(org.springframework.security.core.userdetails.UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

	}

	@Override
	protected org.springframework.security.core.userdetails.UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		System.out.println("WebServiceAuthenticationProvider");
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
		grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
		grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		UserDetails userDetails = new UserDetails(name, password, grantedAuthorityList);
		
		if (!"excise".equals(env)) {
			Response response = webServiceExciseService.webServiceLdap(name, password);
			if ("200".equals(response.getStatusCode())) {
				userDetails.setOfficeId(response.getOffice());
				System.out.println("login success : " + response.getOffice());
				
			} else {
				throw new BadCredentialsException(response.getStatusMessage());
			}
		}else {
			AuthenAndGetUserRoleResponse authenAndGetUserRoleResponse = webServiceExciseService.ldpagAuthenAndGetUserRoleServiceProxy(name, password);
			MessageBase messageBase = authenAndGetUserRoleResponse.getMessage();
			if("000".equals(messageBase.getCode())) {
				
			}
			
			userDetails.setOfficeId(authenAndGetUserRoleResponse.getOfficeId());
		}
		
		return userDetails;
	}

}
