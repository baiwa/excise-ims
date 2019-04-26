package th.go.excise.ims.ws.client.pcc.licfri6010.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import th.go.excise.ims.ws.WsService;
import th.go.excise.ims.ws.client.pcc.common.oxm.PccRequestHeader;
import th.go.excise.ims.ws.client.pcc.common.service.PccRequestHeaderService;
import th.go.excise.ims.ws.client.pcc.licfri6010.oxm.LicFri6010Request;
import th.go.excise.ims.ws.client.pcc.licfri6010.oxm.LicFri6010Response;
import th.go.excise.ims.ws.client.pcc.licfri6010.oxm.LicenseList;
import th.go.excise.ims.ws.client.pcc.licfri6010.oxm.ResponseData;

@Service
public class LicFri6010Service {
	@Value("${ws.excise.endpointLicFri6010}")
	private String endpoint;

	@Autowired
	private PccRequestHeaderService pccRequestHeaderService;

	@Autowired
	private WsService wsService;
	
	public List<LicenseList> postRestFul(LicFri6010Request licFri6010Request) throws IOException {
		List<LicenseList> licenseList = new ArrayList<>();
		
//		String json = pccRequestHeaderService.postRestful(endpoint, licFri6010Request);
		PccRequestHeader requestRestful = new PccRequestHeader();
		requestRestful.setSystemId("LIC");
		requestRestful.setUserName("lic");
		requestRestful.setPassword("123456");
		requestRestful.setIpAddress("10.11.1.10");
		requestRestful.setRequestData(licFri6010Request);
		Gson gson = new Gson();
		String json2 = gson.toJson(requestRestful);
		String json = wsService.post(endpoint, json2);
		
		gson = new Gson();
		LicFri6010Response pccResponseHeader = gson.fromJson(json, LicFri6010Response.class);
		if ("OK".equals(pccResponseHeader.getResponseCode())) {
			ResponseData response =  pccResponseHeader.getResponseData();
			licenseList = response.getLicenseList();
		}
		return licenseList;
	}
}
