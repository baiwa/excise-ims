package th.go.excise.ims.ws.client.pcc.incfri8020.service;

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
import th.go.excise.ims.ws.client.pcc.incfri8020.oxm.IncFri8020Request;
import th.go.excise.ims.ws.client.pcc.incfri8020.oxm.IncomeList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class IncFri8020ServiceTest {
	
	@Autowired
	private IncFri8020Service IncFri8020RequestService;
	
	@Test
	public void testIncFri8020() throws IOException {
		IncFri8020Request incFri8020Request = new IncFri8020Request();
		incFri8020Request.setYearMonthFrom("201802");
		incFri8020Request.setYearMonthTo("201808");
		incFri8020Request.setOfficeCode("010100");
		incFri8020Request.setDateType("Income");
		incFri8020Request.setPageNo("1");
		incFri8020Request.setDataPerPage("10");
		List<IncomeList> IncFri8020Response = IncFri8020RequestService.postRestFul(incFri8020Request);
		for (IncomeList incomeList : IncFri8020Response) {
			System.out.println("ReceiptNo : "+ incomeList.getReceiptNo());
		}
	}
}
