package th.go.excise.ims.ws.client.pcc.inquiryHospital.service;

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
import th.go.excise.ims.ws.client.pcc.inquiryHoliday.oxm.InquiryHolidayRequest;
import th.go.excise.ims.ws.client.pcc.inquiryHospital.oxm.InquiryHospital;
import th.go.excise.ims.ws.client.pcc.inquiryHospital.oxm.InquiryHospitalRequest;
import th.go.excise.ims.ws.client.pcc.inquiryHospital.service.InquiryHospitalService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class InquiryHospitalServiceTest {

	@Autowired
	private InquiryHospitalService inquiryHospitalService;

	@Test
	public void inquiryHospital() {
		InquiryHospitalRequest hospitalRequest  = new InquiryHospitalRequest();
		
		try {
			List<InquiryHospital> inquiryHospitalResponseList = inquiryHospitalService.postRestFul(hospitalRequest);
			for (InquiryHospital inquiryHospitalResponse : inquiryHospitalResponseList) {
				System.out.println(inquiryHospitalResponse);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
