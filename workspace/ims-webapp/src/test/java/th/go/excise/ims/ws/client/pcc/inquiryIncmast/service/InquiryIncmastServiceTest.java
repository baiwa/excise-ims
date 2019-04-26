package th.go.excise.ims.ws.client.pcc.inquiryIncmast.service;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ws.client.pcc.inquiryIncmast.oxm.InquiryIncmast;
import th.go.excise.ims.ws.client.pcc.inquiryIncmast.oxm.InquiryIncmastRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class InquiryIncmastServiceTest {

	@Autowired
	private InquiryIncmastService inquiryIncmastService;

	@Test
	public void inquiryIncmastTest() {
		InquiryIncmastRequest inquiryIncmastRequest = new InquiryIncmastRequest();

		try {
			List<InquiryIncmast> inquiryIncmastResponseList = inquiryIncmastService.postRestFul(inquiryIncmastRequest);
			for (InquiryIncmast inquiryIncmastResponse : inquiryIncmastResponseList) {
				System.out.println(inquiryIncmastResponse);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
