package th.go.excise.ims.ta.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class SyncWebServiceTaxAuditSelectFactoryServiceTest {

	@Autowired 
	private SyncWebServiceTaxAuditSelectFactoryService syncWebServiceTaxAuditSelectFactoryService;
	@Test
	public void syncWebServiceTaxAuditSelectFactoryService() {
		try {
			syncWebServiceTaxAuditSelectFactoryService.suncData();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
