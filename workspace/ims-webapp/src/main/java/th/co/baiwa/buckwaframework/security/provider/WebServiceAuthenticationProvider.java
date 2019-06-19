package th.co.baiwa.buckwaframework.security.provider;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import th.go.excise.dexsrvint.schema.authenandgetuserrole.AuthenAndGetUserRoleRequest;
import th.go.excise.dexsrvint.schema.authenandgetuserrole.AuthenAndGetUserRoleResponse;
import th.go.excise.dexsrvint.schema.ldapuserbase.RoleBase;
import th.go.excise.dexsrvint.wsdl.ldapgateway.ldpagauthenandgetuserrole.LDPAGAuthenAndGetUserRolePortType;
import th.go.excise.ims.preferences.persistence.entity.ExcisePerson;
import th.go.excise.ims.preferences.persistence.repository.ExcisePersonRepository;

@Component("wsAuthenticationProvider")
public class WebServiceAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	private Logger logger = LoggerFactory.getLogger(WebServiceAuthenticationProvider.class);

	@Value("${application.env}")
	private String env;

	// @Autowired
	// private WebServiceExciseService webServiceExciseService;

	@Autowired
	private LDPAGAuthenAndGetUserRolePortType loginLdapProxy;

	@Autowired
	private ExcisePersonRepository excisePersonRepository;

	@Override
	protected void additionalAuthenticationChecks(org.springframework.security.core.userdetails.UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

	}

	@Override
	protected org.springframework.security.core.userdetails.UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		logger.info("WebServiceAuthenticationProvider : {}", username);
		String password = authentication.getCredentials().toString();
		UserDetails userDetails = null;

		AuthenAndGetUserRoleRequest ldap = new AuthenAndGetUserRoleRequest();
		ldap.setUserId(username);
		ldap.setPassword(password);
		ldap.setApplicationId("TAX AUDIT");
		AuthenAndGetUserRoleResponse response = loginLdapProxy.ldpagAuthenAndGetUserRoleOperation(ldap);
		if ("000".equals(response.getMessage().getCode())) {
			List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
			grantedAuthorityList.add(new SimpleGrantedAuthority(ROLE.USER));
			if (username.contains("admin")) {
				grantedAuthorityList.add(new SimpleGrantedAuthority(ROLE.ADMIN));
			}
			for (RoleBase wsRole : response.getRoles().getRole()) {
				if(StringUtils.isNotBlank(wsRole.getRoleName())) {
					grantedAuthorityList.add(new SimpleGrantedAuthority(wsRole.getRoleName().split("=")[1]));
				}
			}
			userDetails = new UserDetails(username, password, grantedAuthorityList);
			userDetails.setUserThaiName(response.getUserThaiName());
			userDetails.setUserThaiSurname(response.getUserThaiSurname());
			userDetails.setTitle(response.getTitle());
			userDetails.setOfficeCode(response.getOfficeId());
			addAdditionalInfo(userDetails);
		} else {
			throw new BadCredentialsException(response.getMessage().getDescription());
		}
		return userDetails;
	}

	private void addAdditionalInfo(UserDetails userDetails) {
		ExcisePerson excisePerson = excisePersonRepository.findByEdLogin(userDetails.getUsername());
		if (excisePerson != null) {
			userDetails.setSubdeptCode(excisePerson.getAuSubdeptCode());
			userDetails.setSubdeptLevel(excisePerson.getAuSubdeptLevel());
		}
	}

}
