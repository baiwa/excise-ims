package th.go.excise.ims.ws.client.pcc.inquiryIncmast.service;

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
import th.go.excise.ims.ws.client.pcc.inquiryIncmast.oxm.InquiryIncmast;
import th.go.excise.ims.ws.client.pcc.inquiryIncmast.oxm.InquiryIncmastRequest;
import th.go.excise.ims.ws.client.pcc.inquiryIncmast.oxm.InquiryIncmastResponse;
@Service
public class InquiryIncmastService {
	@Value("${ws.excise.endpointInquiryIncmast}")
	private String endpoint;

	@Autowired
	private PccRequestHeaderService pccRequestHeaderService;

	@Autowired
	private WsService wsService;
	
	public List<InquiryIncmast> postRestFul(InquiryIncmastRequest inquiryIncmastRequest) throws IOException {
		List<InquiryIncmast> licenseList = new ArrayList<>();
		
//		String json = pccRequestHeaderService.postRestful(endpoint, licFri6010Request);
		PccRequestHeader requestRestful = new PccRequestHeader();
		requestRestful.setSystemId("WSS");
		requestRestful.setUserName("wss001");
		requestRestful.setPassword("123456");
		requestRestful.setIpAddress("10.1.1.1");
		requestRestful.setRequestData(inquiryIncmastRequest);
		Gson gson = new Gson();
		String json2 = gson.toJson(requestRestful);
		String json = wsService.post(endpoint, json2);
		
		gson = new Gson();
		InquiryIncmastResponse pccResponseHeader = gson.fromJson(json, InquiryIncmastResponse.class);
		if ("OK".equals(pccResponseHeader.getResponseCode())) {
			licenseList = pccResponseHeader.getResponseData();
		}
		return licenseList;
	}
}
