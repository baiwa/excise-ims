package th.go.excise.ims.ws.client.ims.regp0101.service;

import java.io.IOException;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.google.gson.Gson;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ws.client.ims.regp0101.model.RequestData;
import th.go.excise.ims.ws.client.ims.regp0101.model.ResponseData;
import th.go.excise.ims.ws.client.service.RestfulClientService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class RegP0101ServiceTest {
	
	@Autowired
	private RegP0101Service regP0101Service;
	
	@Test
	public void test_execute() {
		try {
			RequestData requestData = new RequestData();
			requestData.setNewRegId("01055590925413001");
			ResponseData responseData = regP0101Service.execute(requestData);
			responseData.getDocList().forEach(e -> System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
//	@Test
	public void test_execute_Manual() {
		String url = "http://192.168.48.36:8080/excise-ims/rest/getJsonRegP0101";
		RegP0101Service regP0101Service = new RegP0101Service(url, new RestfulClientService(), new Gson());
		
		try {
			RequestData requestData = new RequestData();
			requestData.setNewRegId("01055590925413001");
			ResponseData responseData = regP0101Service.execute(requestData);
			responseData.getDocList().forEach(e -> System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
