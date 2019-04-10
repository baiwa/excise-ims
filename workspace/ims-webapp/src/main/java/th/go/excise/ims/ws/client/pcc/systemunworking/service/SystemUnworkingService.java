package th.go.excise.ims.ws.client.pcc.systemunworking.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import th.go.excise.ims.ws.client.pcc.common.service.PccRequestHeaderService;
import th.go.excise.ims.ws.client.pcc.systemunworking.oxm.ResponseData;

@Service
public class SystemUnworkingService {
	
	private Logger logger = LoggerFactory.getLogger(SystemUnworkingService.class);
	
    @Value("${ws.excise.wsSystemUnworking}")
    private String endpoint;

    @Autowired
    private PccRequestHeaderService pccRequestHeaderService;
    
	public ResponseData getRestFul(String year,String month) throws IOException {
		ResponseData responseData = new ResponseData();
		String param = "?year="+year+"&month="+month;
		String json = pccRequestHeaderService.getRestful(endpoint +param);
		logger.info("endpoint : "+endpoint + param);
//		logger.info("json : "+json);
		Gson gson = new Gson();
		responseData = gson.fromJson(json, ResponseData.class);
		return responseData;
	}

}