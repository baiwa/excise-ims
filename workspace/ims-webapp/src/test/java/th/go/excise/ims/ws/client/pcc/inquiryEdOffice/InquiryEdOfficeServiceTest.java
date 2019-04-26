package th.go.excise.ims.ws.client.pcc.inquiryEdOffice;

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
import th.go.excise.ims.ws.client.pcc.inquiryEdOffice.oxm.EdOffice;
import th.go.excise.ims.ws.client.pcc.inquiryEdOffice.oxm.InquiryEdOfficeRequest;
import th.go.excise.ims.ws.client.pcc.inquiryEdOffice.service.InquiryEdOfficeService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class InquiryEdOfficeServiceTest {

	@Autowired
	private InquiryEdOfficeService inquiryEdOfficeService;

	@Test
	public void inquiryEdOffice() {
		InquiryEdOfficeRequest officeRequest  = new InquiryEdOfficeRequest();
		
		try {
			List<EdOffice> inquiryEdOfficeResponseList = inquiryEdOfficeService.postRestFul(officeRequest);
			for (EdOffice inquiryEdOfficeResponse : inquiryEdOfficeResponseList) {
				System.out.println(inquiryEdOfficeResponse);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
