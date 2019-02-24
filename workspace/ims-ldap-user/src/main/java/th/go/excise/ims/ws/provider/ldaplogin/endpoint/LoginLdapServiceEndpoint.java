package th.go.excise.ims.ws.provider.ldaplogin.endpoint;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import baiwa.co.th.ws.LoginLdap;
import baiwa.co.th.ws.Response;
import baiwa.co.th.ws.Role;
import baiwa.co.th.ws.Roles;
import th.go.excise.ims.ldaplogin.persistence.entity.WsRole;
import th.go.excise.ims.ldaplogin.persistence.entity.WsUser;
import th.go.excise.ims.ldaplogin.persistence.repository.WsUserRepository;
import th.go.excise.ims.ldaplogin.persistence.repository.WsUserRoleRepository;

@Service
public class LoginLdapServiceEndpoint implements LoginLdap {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginLdapServiceEndpoint.class);
	
	@Autowired
	private WsUserRepository wsUserRepository;
	
	@Autowired
	private WsUserRoleRepository wsUserRoleRepository;
	
	@Override
	public Response login(String username, String password) {
		logger.info("login username={}, password={}", username, password);
		
		WsUser wsUser = wsUserRepository.findByUsernameAndPassword(username, password);
		
		Response response = new Response();
		if (wsUser != null) {
			response.setSuccess("true");
			response.setCode("000");
			response.setDescription("Authentication Success");
			response.setOfficeCode(wsUser.getOfficeCode());
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
			
			Roles roles = prepareRoles(username);
			response.setRoles(roles);
		} else {
			response.setSuccess("false");
			response.setCode("ERR001");
			response.setDescription("Authentication Failed.");
		}
		
		return response;
	}
	
	public Roles prepareRoles(String username) {
		Roles roles = new Roles();
		Role role = null;
		
		List<WsRole> wsRoleList = wsUserRoleRepository.findByUsername(username);
		for (WsRole wsRole : wsRoleList) {
			role = new Role();
			role.setRoleName(wsRole.getRoleCode());
			roles.getRole().add(role);
		}
		
		return roles;
	}

}
