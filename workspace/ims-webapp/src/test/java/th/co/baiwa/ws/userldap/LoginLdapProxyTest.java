package th.co.baiwa.ws.userldap;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.ims.ws.userldap.LoginLdap;
import th.co.baiwa.ims.ws.userldap.Response;
import th.go.excise.ims.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class LoginLdapProxyTest {
	
	@Autowired
	private LoginLdap loginLdapProxy;
	
	@Test
	public void test_login() {
		String user = "admin";
		String pass = "password";
		
		Response response = loginLdapProxy.login(user, pass);
		
		System.out.println(ToStringBuilder.reflectionToString(response, ToStringStyle.JSON_STYLE));
	}
	
}
