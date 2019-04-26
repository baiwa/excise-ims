package th.go.excise.ims.ws.client.pcc.inquiryDutyGroup;

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
import th.go.excise.ims.ws.client.pcc.InquiryDutyGroup.oxm.IncomeList;
import th.go.excise.ims.ws.client.pcc.InquiryDutyGroup.oxm.InquiryDutyGroupRequest;
import th.go.excise.ims.ws.client.pcc.InquiryDutyGroup.service.InquiryDutyGroupService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class InquiryDutyGroupServiceTest {

	@Autowired
	private InquiryDutyGroupService inquiryDutyGroupService;

	@Test
	public void InquiryDutyGroup() {
		InquiryDutyGroupRequest inquiryDutyGroupRequest = new InquiryDutyGroupRequest();
		
		try {
			List<IncomeList> inquiryDutyGroupResponseList = inquiryDutyGroupService.postRestFul(inquiryDutyGroupRequest);
			for (IncomeList inquiryDutyGroupResponse : inquiryDutyGroupResponseList) {
				System.out.println(inquiryDutyGroupResponse);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
