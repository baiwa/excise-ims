package th.go.excise.ims.ws.client.pcc.regfri4000.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import th.go.excise.ims.ws.client.pcc.common.service.PccRequestHeaderService;
import th.go.excise.ims.ws.client.pcc.regfri4000.oxm.RegFri4000Request;
import th.go.excise.ims.ws.client.pcc.regfri4000.oxm.RegFri4000Response;
import th.go.excise.ims.ws.client.pcc.regfri4000.oxm.ResponseData;

@Service
public class RegFri4000RequestService {

	
	@Value("${ws.excise.endpointRegFri4000}")
	private String endpoint;
	
	@Autowired
	private PccRequestHeaderService pccRequestHeaderService;
	
	public ResponseData postRestFul(RegFri4000Request regFri4000Request) {
		String json = pccRequestHeaderService.postRestful(endpoint, regFri4000Request);
		Gson gson = new Gson();
		RegFri4000Response pccResponseHeader = gson.fromJson(json, RegFri4000Response.class);
		ResponseData regFri4000Response = (ResponseData) pccResponseHeader.getResponseData();
		return regFri4000Response;
	}
	
}
