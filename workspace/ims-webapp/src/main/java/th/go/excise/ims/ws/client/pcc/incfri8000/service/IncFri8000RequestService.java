package th.go.excise.ims.ws.client.pcc.incfri8000.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import th.go.excise.ims.ws.client.pcc.common.service.PccRequestHeaderService;
import th.go.excise.ims.ws.client.pcc.incfri8000.oxm.IncFri8000Request;
import th.go.excise.ims.ws.client.pcc.incfri8000.oxm.IncFri8000Response;
import th.go.excise.ims.ws.client.pcc.incfri8000.oxm.IncomeList;
import th.go.excise.ims.ws.client.pcc.incfri8000.oxm.ResponseData;

@Service
public class IncFri8000RequestService {

	@Value("${ws.excise.endpointIncFri8000}")
	private String endpoint;

	@Autowired
	private PccRequestHeaderService pccRequestHeaderService;

	public List<IncomeList> postRestFul(IncFri8000Request incFri8000Request) throws IOException {
		List<IncomeList> incomeListList = new ArrayList<>();
		String json = pccRequestHeaderService.postRestful(endpoint, incFri8000Request);
		Gson gson = new Gson();
		IncFri8000Response pccResponseHeader = gson.fromJson(json, IncFri8000Response.class);
		if ("OK".equals(pccResponseHeader.getResponseCode())) {
			ResponseData incFri8000Response = (ResponseData) pccResponseHeader.getResponseData();
			incomeListList = incFri8000Response.getIncomeList();
		}
		return incomeListList;
	}

}
