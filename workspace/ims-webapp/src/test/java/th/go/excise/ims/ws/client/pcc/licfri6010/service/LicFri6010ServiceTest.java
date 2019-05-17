package th.go.excise.ims.ws.client.pcc.licfri6010.service;

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
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;
import th.go.excise.ims.ws.client.pcc.common.util.PccServiceTestUtils;
import th.go.excise.ims.ws.client.pcc.licfri6010.model.RequestData;
import th.go.excise.ims.ws.client.pcc.licfri6010.model.ResponseData;
import th.go.excise.ims.ws.client.service.RestfulClientService;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class LicFri6010ServiceTest {

	@Autowired
	private LicFri6010Service licFri6010Service;

	//@Test
	public void test_execute() {
		try {
			RequestData requestData = new RequestData();
			requestData.setOffcode("100300");
			requestData.setYearMonthFrom("201801");
			requestData.setYearMonthTo("201802");
			requestData.setPageNo("1");
			requestData.setDataPerPage("10");
			ResponseData responseData = licFri6010Service.execute(requestData);
			responseData.getLicenseList().forEach(e -> System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE)));
		} catch (PccRestfulException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_execute_Manual() {
		String url = "http://webtest.excise.go.th/EDAuditServicesUAT/lic/LicFri6010";
		LicFri6010Service licFri6010Service = new LicFri6010Service(url, PccServiceTestUtils.getPccServiceProperties(), new RestfulClientService(), new Gson());
		
		try {
			RequestData requestData = new RequestData();
			requestData.setOffcode("100300");
			requestData.setYearMonthFrom("201801");
			requestData.setYearMonthTo("201801");
			requestData.setPageNo("1");
			requestData.setDataPerPage("10");
			ResponseData responseData = licFri6010Service.execute(requestData);
			responseData.getLicenseList().forEach(e -> System.out.println(ToStringBuilder.reflectionToString(e, ToStringStyle.MULTI_LINE_STYLE)));
		} catch (PccRestfulException e) {
			e.printStackTrace();
		}
	}
	
}
