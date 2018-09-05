package th.co.baiwa.buckwaframework.security.provider;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	@Autowired
	private WebServiceExciseService webServiceExciseService;
	
	@Override
	protected void additionalAuthenticationChecks(org.springframework.security.core.userdetails.UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		
	}
	
	@Override
	protected org.springframework.security.core.userdetails.UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		System.out.println("WebServiceAuthenticationProvider");
		String name = authentication.getName();
		String password = authentication.getCredentials().toString();
		
		Response response = webServiceExciseService.webServiceLdap(name, password);
		if ("200".equals(response.getStatusCode())) {
			
			List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
			grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
			grantedAuthorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			
			UserDetails userDetails = new UserDetails(
				name,
				password,
				grantedAuthorityList
			);
			userDetails.setOfficeCode(response.getOffice());
			
			System.out.println("login success : " + response.getOffice());
			
			return userDetails;
		} else {
			throw new BadCredentialsException(response.getStatusMessage());
		}
	}
	
}
