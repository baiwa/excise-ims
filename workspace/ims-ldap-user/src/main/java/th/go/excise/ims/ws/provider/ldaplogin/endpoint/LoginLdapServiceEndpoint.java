package th.go.excise.ims.ws.provider.ldaplogin.endpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.ws.userldap.LoginLdap;
import th.co.baiwa.ws.userldap.Response;
import th.go.excise.ims.ldaplogin.persistence.entity.WsUser;
import th.go.excise.ims.ldaplogin.persistence.repository.WsUserRepository;

@Service
public class LoginLdapServiceEndpoint implements LoginLdap {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginLdapServiceEndpoint.class);
	
	@Autowired
	private WsUserRepository wsUserRepository;
	
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
		} else {
			response.setSuccess("false");
			response.setCode("ERR001");
			response.setDescription("Authentication Failed.");
		}
		
		return response;
	}

}
