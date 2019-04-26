package th.go.excise.ims.ws.client.pcc.inquiryoffcodeaddress.service;

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
import th.go.excise.ims.ws.client.pcc.inquiryoffcodeaddress.oxm.InquiryOffcodeAddress;
import th.go.excise.ims.ws.client.pcc.inquiryoffcodeaddress.oxm.InquiryOffcodeAddressRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class InquiryOffcodeAddressServiceTest {

	@Autowired
	private InquiryOffcodeAddressService inquiryOffcodeAddressService;

	@Test
	public void testInquiryBank() {
		InquiryOffcodeAddressRequest inquiryOffcodeAddress = new InquiryOffcodeAddressRequest();
		
		try {
			List<InquiryOffcodeAddress> inquiryOffcodeAddressResponseList = inquiryOffcodeAddressService.postRestFul(inquiryOffcodeAddress);
			for (InquiryOffcodeAddress inquiryOffcodeAddressResponse : inquiryOffcodeAddressResponseList) {
				System.out.println(inquiryOffcodeAddressResponse);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
