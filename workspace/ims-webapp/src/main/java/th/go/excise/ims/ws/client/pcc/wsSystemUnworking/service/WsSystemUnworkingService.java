package th.go.excise.ims.ws.client.pcc.wsSystemUnworking.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import th.go.excise.ims.ws.client.pcc.common.service.PccRequestHeaderService;
import th.go.excise.ims.ws.client.pcc.wsSystemUnworking.oxm.ResponseData;

@Service
public class WsSystemUnworkingService {
	
	private Logger logger = LoggerFactory.getLogger(WsSystemUnworkingService.class);
	
    @Value("${ws.excise.wsSystemUnworking}")
    private String endpoint;

    @Autowired
    private PccRequestHeaderService pccRequestHeaderService;
    
	public ResponseData getRestFul(String budgetYear) throws IOException {
		ResponseData responseData = new ResponseData();
		String json = pccRequestHeaderService.getRestful(endpoint +"?year="+budgetYear);
		logger.info("endpoint : "+endpoint +"?year="+budgetYear);
//		logger.info("json : "+json);
		Gson gson = new Gson();
		responseData = gson.fromJson(json, ResponseData.class);
		return responseData;
	}

}