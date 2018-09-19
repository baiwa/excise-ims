package th.co.baiwa.excise.ws;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;

import baiwa.co.th.ws.Response;
//import th.co.baiwa.excise.ws.entity.response.incfri8020.ResponseData;
import th.co.baiwa.excise.ws.entity.response.licfri6020.LicFri6020;
import th.co.baiwa.excise.ws.entity.response.licfri6020.LicenseList;
import th.co.baiwa.excise.ws.entity.response.licfri6020.ResponseData;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebServiceTest {

	@Autowired
	private WebServiceExciseService webServiceExciseService;
	
	
	
	//@Test
//	public void IncFri8020() {
//		IncFri8020 re = webServiceExciseService.IncFri8020("000300", "201802", "201808", "Income", "0", "0");
//		ResponseData xxx = re.getResponseData();
//		for (IncomeList incFri8020Income : xxx.getIncomeList()) {
//			System.out.println(incFri8020Income.getIncomeName());
//		}
//	}
	@Test
	public void LicFri6020() {
		LicFri6020 re = webServiceExciseService.licFri6020("0105555155742", "", "1", "1000");
		ResponseData xxx = re.getResponseData();
		for (LicenseList incFri8020Income : xxx.getLicenseList()) {
			System.out.println(incFri8020Income.getLicName());
		}
	}
	//@Test
	public void webServiceLdap() {
		Response response = webServiceExciseService.webServiceLdap("kek1", "password");
		Gson gson = new Gson();
		System.out.println(gson.toJson(response));
	}

	
	
	
	
	
}
