package th.go.excise.ims.ws.client.pcc.inquiryHoliday;

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
import th.go.excise.ims.ws.client.pcc.inquiryHoliday.oxm.InquiryHoliday;
import th.go.excise.ims.ws.client.pcc.inquiryHoliday.oxm.InquiryHolidayRequest;
import th.go.excise.ims.ws.client.pcc.inquiryHoliday.service.InquiryHolidayService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class InquiryHolidayServiceTest {

	@Autowired
	private InquiryHolidayService inquiryHolidayService;

	@Test
	public void inquiryHoliday() {
		InquiryHolidayRequest holidayRequest  = new InquiryHolidayRequest();
		
		try {
			List<InquiryHoliday> inquiryHolidayResponseList = inquiryHolidayService.postRestFul(holidayRequest);
			for (InquiryHoliday inquiryHolidayResponse : inquiryHolidayResponseList) {
				System.out.println(inquiryHolidayResponse);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
