package th.go.excise.ims.ws.client.pcc.inquirytitle.service;

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
import th.go.excise.ims.ws.client.pcc.inquirytitle.oxm.InquiryTitle;
import th.go.excise.ims.ws.client.pcc.inquirytitle.oxm.InquiryTitleRequest;
import th.go.excise.ims.ws.client.pcc.inquirytitle.oxm.InquiryTitleResponse;

@Service
public class InquiryTitleService {

    @Value("${ws.excise.endpointInquiryTitle}")
    private String endpoint;

    @Autowired
    private PccRequestHeaderService pccRequestHeaderService;
    
    @Autowired
	private WsService wsService;

    public List<InquiryTitle> postRestFul(InquiryTitleRequest inquiryTitleRequest) throws IOException {
    	List<InquiryTitle> licenseList = new ArrayList<>();
		
		PccRequestHeader requestRestful = new PccRequestHeader();
		requestRestful.setSystemId("WSS");
		requestRestful.setUserName("wss001");
		requestRestful.setPassword("123456");
		requestRestful.setIpAddress("10.1.1.1");
		requestRestful.setRequestData(inquiryTitleRequest);
		Gson gson = new Gson();
		String json2 = gson.toJson(requestRestful);
		String json = wsService.post(endpoint, json2);
		
		gson = new Gson();
		InquiryTitleResponse pccResponseHeader = gson.fromJson(json, InquiryTitleResponse.class);
		if ("OK".equals(pccResponseHeader.getResponseCode())) {
			licenseList = pccResponseHeader.getResponseData();
		}
		return licenseList;
	}

}
