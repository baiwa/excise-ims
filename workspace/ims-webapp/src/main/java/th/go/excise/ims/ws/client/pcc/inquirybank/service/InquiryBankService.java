package th.go.excise.ims.ws.client.pcc.inquirybank.service;

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
import th.go.excise.ims.ws.client.pcc.inquirybank.oxm.InquiryBank;
import th.go.excise.ims.ws.client.pcc.inquirybank.oxm.InquiryBankRequest;
import th.go.excise.ims.ws.client.pcc.inquirybank.oxm.InquiryBankResponse;

@Service
public class InquiryBankService {

    @Value("${ws.excise.endpointInquiryBank}")
    private String endpoint;

    @Autowired
    private PccRequestHeaderService pccRequestHeaderService;
    
    @Autowired
	private WsService wsService;

    public List<InquiryBank> postRestFul(InquiryBankRequest inquiryBankRequest) throws IOException {
    	List<InquiryBank> licenseList = new ArrayList<>();
		
		PccRequestHeader requestRestful = new PccRequestHeader();
		requestRestful.setSystemId("WSS");
		requestRestful.setUserName("wss001");
		requestRestful.setPassword("123456");
		requestRestful.setIpAddress("10.1.1.1");
		requestRestful.setRequestData(inquiryBankRequest);
		Gson gson = new Gson();
		String json2 = gson.toJson(requestRestful);
		String json = wsService.post(endpoint, json2);
		
		gson = new Gson();
		InquiryBankResponse pccResponseHeader = gson.fromJson(json, InquiryBankResponse.class);
		if ("OK".equals(pccResponseHeader.getResponseCode())) {
			licenseList = pccResponseHeader.getResponseData();
		}
		return licenseList;
	}

}
