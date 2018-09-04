package th.co.baiwa.excise.ws;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;

import baiwa.co.th.ws.Response;
import th.co.baiwa.excise.ws.WebServiceExciseService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WebServiceTest {

	@Autowired
	private WebServiceExciseService webServiceExciseService;
	
	
	
	//@Test
	public void IncFri8020() {
		webServiceExciseService.IncFri8020("000300", "201802", "201808", "Income", "0", "0");
	}
	@Test
	public void webServiceLdap() {
		Response response = webServiceExciseService.webServiceLdap("kek1", "password");
		Gson gson = new Gson();
		System.out.println(gson.toJson(response));
	}

	
	
	
	
	
}
