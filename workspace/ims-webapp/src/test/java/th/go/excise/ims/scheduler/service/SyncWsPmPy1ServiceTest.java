package th.go.excise.ims.scheduler.service;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ws.client.pm.py1.model.RequestData;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class SyncWsPmPy1ServiceTest {
	
	@Autowired
	private SyncWsPmPy1Service syncWsPmPy1Service;
	
	@Test
	public void test_syncData() throws IOException {
		RequestData requestData = new RequestData();
		requestData.setOfficeCode("020000");
		requestData.setYear("2561");
		
		syncWsPmPy1Service.syncData(requestData);
	}
	
}
