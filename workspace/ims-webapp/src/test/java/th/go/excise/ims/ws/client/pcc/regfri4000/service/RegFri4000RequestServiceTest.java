package th.go.excise.ims.ws.client.pcc.regfri4000.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ws.client.pcc.regfri4000.oxm.RegFri4000Request;
import th.go.excise.ims.ws.client.pcc.regfri4000.oxm.ResponseData;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class RegFri4000RequestServiceTest {
	
	@Autowired
	private RegFri4000RequestService regFri4000RequestService; 
	
	@Test
	public void testRegFri4000() {
		RegFri4000Request incFri8000Request = new RegFri4000Request();
		incFri8000Request.setType("1");
		incFri8000Request.setNid("");
		incFri8000Request.setNewregId("");
		incFri8000Request.setActive("1");
		incFri8000Request.setPageNo("1");
		incFri8000Request.setDataPerPage("10");
		ResponseData regFri4000Response = regFri4000RequestService.postRestFul(incFri8000Request);
	}
}
