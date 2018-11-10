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
		logger.info("WebServiceAuthenticationProvider : {}" , username);
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
		grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
		grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		UserDetails userDetails = new UserDetails(name, password, grantedAuthorityList);
		
		if (!"excise".equals(env)) {
			Response response = webServiceExciseService.webServiceLdap(name, password);
			if ("000".equals(response.getCode())) {
				userDetails.setOfficeId(response.getOfficeCode());
				userDetails.setUserThaiName(response.getUserThaiName());
				userDetails.setUserThaiSurname(response.getUserThaiSurname());
				userDetails.setTitle(response.getTitle());
				logger.info("login success {}" , username);
				
			} else {
				throw new BadCredentialsException(response.getStatusCode());
			}
		}else {
//			AuthenAndGetUserRoleResponse authenAndGetUserRoleResponse = webServiceExciseService.ldpagAuthenAndGetUserRoleServiceProxy(name, password);
//			MessageBase messageBase = authenAndGetUserRoleResponse.getMessage();
//			if("000".equals(messageBase.getCode())) {
//				
//			}
//			
//			userDetails.setOfficeId(authenAndGetUserRoleResponse.getOfficeId());
//			userDetails.setUserThaiName(authenAndGetUserRoleResponse.getUserThaiName());
//			userDetails.setUserThaiSurname(authenAndGetUserRoleResponse.getUserThaiSurname());
//			userDetails.setTitle(authenAndGetUserRoleResponse.getTitle());
			//BeanUtils.copyProperties(userDetails, licenseList6010);
		}
		
		return userDetails;
	}

}
