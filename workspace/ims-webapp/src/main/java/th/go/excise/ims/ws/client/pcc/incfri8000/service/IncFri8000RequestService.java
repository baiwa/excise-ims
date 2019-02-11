package th.go.excise.ims.ws.client.pcc.incfri8000.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import th.go.excise.ims.ws.client.pcc.common.service.PccRequestHeaderService;
import th.go.excise.ims.ws.client.pcc.incfri8000.oxm.IncFri8000Request;
import th.go.excise.ims.ws.client.pcc.incfri8000.oxm.IncFri8000Response;
import th.go.excise.ims.ws.client.pcc.incfri8000.oxm.ResponseData;

@Service
public class IncFri8000RequestService {

	
	@Value("${ws.excise.endpointIncFri8000}")
	private String endpoint;
	
	@Autowired
	private PccRequestHeaderService pccRequestHeaderService;
	
	public ResponseData postRestFul(IncFri8000Request incFri8000Request) {
		String json = pccRequestHeaderService.postRestful(endpoint, incFri8000Request);
		Gson gson = new Gson();
		IncFri8000Response pccResponseHeader = gson.fromJson(json, IncFri8000Response.class);
		ResponseData IncFri8000Response = (ResponseData) pccResponseHeader.getResponseData();
		return IncFri8000Response;
	}
	
}
