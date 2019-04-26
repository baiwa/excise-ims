package th.go.excise.ims.ws.client.pcc.inquiryHoliday.service;

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
import th.go.excise.ims.ws.client.pcc.inquiryHoliday.oxm.InquiryHoliday;
import th.go.excise.ims.ws.client.pcc.inquiryHoliday.oxm.InquiryHolidayRequest;
import th.go.excise.ims.ws.client.pcc.inquiryHoliday.oxm.InquiryHolidayResponse;
@Service
public class InquiryHolidayService {
	@Value("${ws.excise.endpointInquiryHoliday}")
	private String endpoint;

	@Autowired
	private PccRequestHeaderService pccRequestHeaderService;

	@Autowired
	private WsService wsService;
	
	public List<InquiryHoliday> postRestFul(InquiryHolidayRequest inquiryHolidayRequest) throws IOException {
		List<InquiryHoliday> licenseList = new ArrayList<>();
		
//		String json = pccRequestHeaderService.postRestful(endpoint, licFri6010Request);
		PccRequestHeader requestRestful = new PccRequestHeader();
		requestRestful.setSystemId("WSS");
		requestRestful.setUserName("wss001");
		requestRestful.setPassword("123456");
		requestRestful.setIpAddress("10.1.1.1");
		requestRestful.setRequestData(inquiryHolidayRequest);
		Gson gson = new Gson();
		String json2 = gson.toJson(requestRestful);
		String json = wsService.post(endpoint, json2);
		
		gson = new Gson();
		InquiryHolidayResponse pccResponseHeader = gson.fromJson(json, InquiryHolidayResponse.class);
		if ("OK".equals(pccResponseHeader.getResponseCode())) {
			licenseList = pccResponseHeader.getResponseData();
		}
		return licenseList;
	}
}
