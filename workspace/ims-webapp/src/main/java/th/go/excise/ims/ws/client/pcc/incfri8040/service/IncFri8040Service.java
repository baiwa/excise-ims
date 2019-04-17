package th.go.excise.ims.ws.client.pcc.incfri8040.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import th.go.excise.ims.ws.client.pcc.common.service.PccRequestHeaderService;
import th.go.excise.ims.ws.client.pcc.incfri8040.oxm.IncFri8040Request;
import th.go.excise.ims.ws.client.pcc.incfri8040.oxm.IncFri8040Response;
import th.go.excise.ims.ws.client.pcc.incfri8040.oxm.IncomeList;
import th.go.excise.ims.ws.client.pcc.incfri8040.oxm.ResponseData;

@Service
public class IncFri8040Service {
	@Value("${ws.excise.endpointIncFri8040}")
	private String endpoint;

	@Autowired
	private PccRequestHeaderService pccRequestHeaderService;

	public List<IncomeList> postRestFul(IncFri8040Request incFri8040Request) throws IOException {
		List<IncomeList> incomeListList = new ArrayList<>();
		String json = pccRequestHeaderService.postRestful(endpoint, incFri8040Request);
		Gson gson = new Gson();
		IncFri8040Response pccResponseHeader = gson.fromJson(json, IncFri8040Response.class);
		if ("OK".equals(pccResponseHeader.getResponseCode())) {
			ResponseData incFri8040Response =  pccResponseHeader.getResponseData();
			incomeListList = incFri8040Response.getIncomeList();
		}
		return incomeListList;
	}
}
