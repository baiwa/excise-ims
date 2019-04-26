package th.go.excise.ims.ws.client.pcc.inquirybank.service;

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
import th.go.excise.ims.ws.client.pcc.inquirybank.oxm.InquiryBank;
import th.go.excise.ims.ws.client.pcc.inquirybank.oxm.InquiryBankRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class InquiryBankServiceTest {

	@Autowired
	private InquiryBankService inquiryBankService;

	@Test
	public void testInquiryBank() {
		InquiryBankRequest inquiryBank = new InquiryBankRequest();
		
		try {
			List<InquiryBank> inquiryBankResponseList = inquiryBankService.postRestFul(inquiryBank);
			for (InquiryBank inquiryBankResponse : inquiryBankResponseList) {
				System.out.println(inquiryBankResponse);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
