package th.go.excise.ims.ws.client.pcc.regfri4000.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import th.go.excise.ims.ws.client.pcc.common.service.PccRequestHeaderService;
import th.go.excise.ims.ws.client.pcc.regfri4000.oxm.RegFri4000Request;
import th.go.excise.ims.ws.client.pcc.regfri4000.oxm.RegFri4000Response;
import th.go.excise.ims.ws.client.pcc.regfri4000.oxm.RegMaster60List;
import th.go.excise.ims.ws.client.pcc.regfri4000.oxm.ResponseData;

@Service
public class RegFri4000RequestService {

	@Value("${ws.excise.endpointRegFri4000}")
	private String endpoint;

	@Autowired
	private PccRequestHeaderService pccRequestHeaderService;

	public List<RegMaster60List> postRestFul(RegFri4000Request regFri4000Request) throws IOException {
		String json = pccRequestHeaderService.postRestful(endpoint, regFri4000Request);
		Gson gson = new Gson();
		RegFri4000Response pccResponseHeader = gson.fromJson(json, RegFri4000Response.class);
		List<RegMaster60List> regMaster60Lists = new ArrayList<>();
		ResponseData regFri4000Response = (ResponseData) pccResponseHeader.getResponseData();
		if ("OK".equals(pccResponseHeader.getResponseCode())) {
			regMaster60Lists = regFri4000Response.getRegMaster60List();
		}
		return regMaster60Lists;
	}

}
