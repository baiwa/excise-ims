package th.go.excise.ims.ws.client.other.getJsonQt.service;

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

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ws.client.other.getJsonQt.model.GetJsonQt;
import th.go.excise.ims.ws.client.other.getJsonQt.model.RequestData;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class getJsonQtServiceTest {
	
	@Autowired
	private GetJsonQtService getJsonQtService;
	
	
	@Test
	public void test() {
		try {
			RequestData requestData = new RequestData();
			requestData.setYear("2561");
			requestData.setoCode("010000");
			GetJsonQt responseData = getJsonQtService.execute(requestData);
			responseData.getData().forEach(e -> System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
