package th.go.excise.ims.ws.client.pcc.incfri8020.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import th.go.excise.ims.ia.controller.Int02010101Controller;
import th.go.excise.ims.ws.client.pcc.common.service.PccRequestHeaderService;
import th.go.excise.ims.ws.client.pcc.incfri8020.oxm.IncFri8020Request;
import th.go.excise.ims.ws.client.pcc.incfri8020.oxm.IncFri8020Response;
import th.go.excise.ims.ws.client.pcc.incfri8020.oxm.IncomeList;
import th.go.excise.ims.ws.client.pcc.incfri8020.oxm.ResponseData;

@Service
public class IncFri8020Service {

	private static final Logger logger = LoggerFactory.getLogger(Int02010101Controller.class);
	
	@Value("${ws.excise.endpointIncFri8020}")
	private String endpoint;

	private final int MAX_DATA = 500;

	private final String RETURN_RESPONSE_STATUS_OK = "OK";
	@Autowired
	private PccRequestHeaderService pccRequestHeaderService;

	public List<IncomeList> postRestFul(IncFri8020Request incFri8000Request) throws IOException {
		logger.info("IncFri8020Request : postRestFul");
		List<IncomeList> incomeList = new ArrayList<>();
		String json = pccRequestHeaderService.postRestful(endpoint, incFri8000Request);
		Gson gson = new Gson();
		IncFri8020Response pccResponseHeader = gson.fromJson(json, IncFri8020Response.class);
		if (RETURN_RESPONSE_STATUS_OK.equals(pccResponseHeader.getResponseCode())) {
			ResponseData incFri8020Response = (ResponseData) pccResponseHeader.getResponseData();
			incomeList = incFri8020Response.getIncomeList();
		}
		return incomeList;
	}

	public void syncDataIncFri8020(IncFri8020Request incFri8020Request) {
		int pageNo = 1;
		List<IncomeList> incomeList;
		try {
			do {
				incFri8020Request.setPageNo(String.valueOf(pageNo));
				incFri8020Request.setDataPerPage(String.valueOf(MAX_DATA));
				incomeList = postRestFul(incFri8020Request);
				System.out.println(pageNo+"######################");
				for (IncomeList income : incomeList) {
					System.out.println(income.getReceiptNo());
				}
			} while (MAX_DATA == incomeList.size());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
