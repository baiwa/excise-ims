package th.go.excise.ims.ws.client.pcc.common.service;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import th.go.excise.ims.ws.WsService;
import th.go.excise.ims.ws.client.pcc.common.oxm.PccRequestHeader;

@Service
public class PccRequestHeaderService {
	
	private static final Logger logger = LoggerFactory.getLogger(PccRequestHeaderService.class);

	@Value("${ws.excise.ipaddress}")
	private String ipAddress;

	@Value("${ws.excise.username}")
	private String username;

	@Value("${ws.excise.password}")
	private String password;

	@Value("${ws.excise.systemid}")
	private String systemId;

	@Value("${ws.excise.sourceSystem}")
	private String sourceSystem;

	@Value("${ws.excise.destinationSystem}")
	private String destinationSystem;

	@Value("${ws.excise.options}")
	private String options;

	@Autowired
	private WsService wsService;
	
	
	public String postRestful(String endPoint, Object object) throws IOException {
		logger.info(" postRestful endPoint : {}",  endPoint);
		PccRequestHeader requestRestful = new PccRequestHeader();
		requestRestful.setSystemId(systemId);
		requestRestful.setUserName(username);
		requestRestful.setPassword(password);
		requestRestful.setIpAddress(ipAddress);
		requestRestful.setRequestData(object);
		Gson gson = new Gson();
		String json = gson.toJson(requestRestful);
		return wsService.post(endPoint, json);
	}
	
	public String getRestful(String endPoint) throws IOException {
		logger.info(" getRestful endPoint : {}",  endPoint);
		return wsService.get(endPoint);
	}
	
	
	
}
