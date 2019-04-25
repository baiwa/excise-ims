package th.go.excise.ims.ws.client.pcc.incfri8000.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ws.client.pcc.incfri8000.oxm.IncFri8000Request;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class IncFri8000ServiceTest {

	@Autowired
	private IncFri8000Service IncFri8000RequestService;

	@Test
	public void testIncFri8000() {
		IncFri8000Request incFri8000Request = new IncFri8000Request();
		incFri8000Request.setYearMonthFrom("201802");
		incFri8000Request.setYearMonthTo("201808");
		incFri8000Request.setDateType("Income");
		incFri8000Request.setPageNo("1");
//		incFri8000Request.setDataPerPage("10");
//		ResponseData IncFri8000Response = IncFri8000RequestService.postRestFul(incFri8000Request);
//		for (IncomeList incomeList : IncFri8000Response.getIncomeList()) {
//			System.out.println("ReceiptNo : "+ incomeList.getReceiptNo());
//		}
	}
	
}
