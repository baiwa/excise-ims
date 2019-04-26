package th.go.excise.ims.ws.client.pcc.inquiryoffcodeaddress.service;

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
import th.go.excise.ims.ws.client.pcc.inquiryoffcodeaddress.oxm.InquiryOffcodeAddress;
import th.go.excise.ims.ws.client.pcc.inquiryoffcodeaddress.oxm.InquiryOffcodeAddressRequest;
import th.go.excise.ims.ws.client.pcc.inquiryoffcodeaddress.oxm.InquiryOffcodeAddressResponse;

@Service
public class InquiryOffcodeAddressService {

    @Value("${ws.excise.endpointInquiryOffcodeAddress}")
    private String endpoint;

    @Autowired
    private PccRequestHeaderService pccRequestHeaderService;
    
    @Autowired
	private WsService wsService;

    public List<InquiryOffcodeAddress> postRestFul(InquiryOffcodeAddressRequest inquiryOffcodeAddressRequest) throws IOException {
    	List<InquiryOffcodeAddress> licenseList = new ArrayList<>();
		
		PccRequestHeader requestRestful = new PccRequestHeader();
		requestRestful.setSystemId("systemid");
		requestRestful.setUserName("my_username");
		requestRestful.setPassword("my_password");
		requestRestful.setIpAddress("10.11.1.10");
		inquiryOffcodeAddressRequest.setOffcode("011000");
		requestRestful.setRequestData(inquiryOffcodeAddressRequest);
		Gson gson = new Gson();
		String json2 = gson.toJson(requestRestful);
		String json = wsService.post(endpoint, json2);
		
		gson = new Gson();
		InquiryOffcodeAddressResponse pccResponseHeader = gson.fromJson(json, InquiryOffcodeAddressResponse.class);
		if ("OK".equals(pccResponseHeader.getResponseCode())) {
			licenseList = pccResponseHeader.getResponseData();
		}
		return licenseList;
	}

}
