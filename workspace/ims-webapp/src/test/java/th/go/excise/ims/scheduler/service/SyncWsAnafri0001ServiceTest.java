package th.go.excise.ims.scheduler.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE.ANAFRI0001;
import th.go.excise.ims.ws.client.pcc.anafri0001.model.RequestData;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class SyncWsAnafri0001ServiceTest {
	
	@Autowired
	private SyncWsAnafri0001Service syncWsAnafri0001Service;
	
	@Test
	public void test_syncData() throws PccRestfulException {
		RequestData requestData = new RequestData();
		requestData.setRegistrationId("65052985684701004");
		requestData.setFormCode(ANAFRI0001.FORM_CODE_PS0307);
		requestData.setStartDate("20190401");
		requestData.setEndDate("20190430");
		
		syncWsAnafri0001Service.syncData(requestData);
	}
	
}
