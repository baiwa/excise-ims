package th.go.excise.ims.ws.client.pcc.inquirytitle.service;

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
import th.go.excise.ims.ws.client.pcc.inquirytitle.oxm.InquiryTitle;
import th.go.excise.ims.ws.client.pcc.inquirytitle.oxm.InquiryTitleRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class InquiryTitleServiceTest {

	@Autowired
	private InquiryTitleService inquiryTitleService;

	@Test
	public void testInquiryBank() {
		InquiryTitleRequest inquiryTitle = new InquiryTitleRequest();
		
		try {
			List<InquiryTitle> inquiryTitleResponseList = inquiryTitleService.postRestFul(inquiryTitle);
			for (InquiryTitle inquiryTitleResponse : inquiryTitleResponseList) {
				System.out.println(inquiryTitleResponse);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
