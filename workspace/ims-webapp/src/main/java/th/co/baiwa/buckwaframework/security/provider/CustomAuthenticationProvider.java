package th.co.baiwa.buckwaframework.security.provider;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.ROLE;
import th.co.baiwa.buckwaframework.security.domain.UserDetails;
import th.co.baiwa.buckwaframework.security.persistence.entity.WsRole;
import th.co.baiwa.buckwaframework.security.persistence.entity.WsUser;
import th.co.baiwa.buckwaframework.security.persistence.repository.WsUserRepository;
import th.co.baiwa.buckwaframework.security.persistence.repository.WsUserRoleRepository;
import th.go.excise.dexsrvint.schema.ldapuserbase.RoleBase;
import th.go.excise.dexsrvint.schema.ldapuserbase.RolesBase;
import th.go.excise.dexsrvint.schema.authenandgetuserrole.AuthenAndGetUserRoleResponse;
import th.go.excise.ims.preferences.persistence.entity.ExcisePerson;
import th.go.excise.ims.preferences.persistence.repository.ExcisePersonRepository;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {
	
	private Logger logger = LoggerFactory.getLogger(CustomAuthenticationProvider.class);
	
	@Autowired
	private WsUserRepository wsUserRepository;
	
	@Autowired
	private WsUserRoleRepository wsUserRoleRepository;
	
	@Autowired
	private ExcisePersonRepository excisePersonRepository;
	
	@Override
	protected void additionalAuthenticationChecks(org.springframework.security.core.userdetails.UserDetails userDetails, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {

	}

	@Override
	protected org.springframework.security.core.userdetails.UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		logger.info("CustomAuthenticationProvider : {}" , username);
		String password = authentication.getCredentials().toString();
		UserDetails userDetails = null;
		
		// Login with LoginLdapUser
		AuthenAndGetUserRoleResponse response = login(username, password);
		if ("000".equals(response.getMessage())) {
			// Assign Default ROLE_USER
			List<SimpleGrantedAuthority> grantedAuthorityList = new ArrayList<>();
			grantedAuthorityList.add(new SimpleGrantedAuthority(ROLE.USER));
			// Assign ROLE_ADMIN
			if (username.contains("admin")) {
				grantedAuthorityList.add(new SimpleGrantedAuthority(ROLE.ADMIN));
			}
			// Assign ROLE from WS
			for (RoleBase wsRole : response.getRoles().getRole()) {
				grantedAuthorityList.add(new SimpleGrantedAuthority(wsRole.getRoleName()));
			}
			
			// Create UserDetails
			userDetails = new UserDetails(username, password, grantedAuthorityList);
			
			// Assign Detail for UserDetailes
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
	
	public AuthenAndGetUserRoleResponse login(String username, String password) {
		logger.info("login username={}, password={}", username, password);
		
		WsUser wsUser = wsUserRepository.findByUsernameAndPassword(username, password);
		
		AuthenAndGetUserRoleResponse response = new AuthenAndGetUserRoleResponse();
		if (wsUser != null) {
			response.getMessage().setCode("000");
			response.getMessage().setDescription("Authentication Success");
			response.setTitle(wsUser.getTitle());
			response.setUserThaiName(wsUser.getUserThaiName());
			response.setUserThaiSurname(wsUser.getUserThaiSurname());
			response.setUserEngName(wsUser.getUserEngName());
			response.setUserEngSurname(wsUser.getUserEngSurname());
			response.setUserId(wsUser.getUserId());
			response.setEmail(wsUser.getEmail());
			response.setCnName(wsUser.getCnName());
			response.setOfficeId(wsUser.getOfficeId());
			response.setAccessAttr(wsUser.getAccessAttr());
			
			RolesBase roles = prepareRoles(username);
			response.setRoles(roles);
		} else {
			response.getMessage().setCode("ERR001");
			response.getMessage().setDescription("Authentication Failed.");
		}
		
		return response;
	}
	
	private RolesBase prepareRoles(String username) {
		RolesBase roles = new RolesBase();
		RoleBase role = null;
		List<WsRole> wsRoleList = wsUserRoleRepository.findByUsername(username);
		for (WsRole wsRole : wsRoleList) {
			role = new RoleBase();
			role.setRoleName(wsRole.getRoleCode());
			roles.getRole().add(role);
		}
		return roles;
	}
	
	private void addAdditionalInfo(UserDetails userDetails) {
		ExcisePerson excisePerson = excisePersonRepository.findByEdLogin(userDetails.getUsername());
		if (excisePerson != null) {
			userDetails.setSubdeptCode(excisePerson.getAuSubdeptCode());
			userDetails.setSubdeptLevel(excisePerson.getAuSubdeptLevel());
		}
	}

}
