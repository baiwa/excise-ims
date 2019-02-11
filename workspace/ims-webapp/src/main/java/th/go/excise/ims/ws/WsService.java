package th.go.excise.ims.ws;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



@Service
public class WsService {

	private static final Logger logger = LoggerFactory.getLogger(WsService.class);

	

	public String postRestful(String endPoint, String json) {
		logger.info("Body Service request : " + json);
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
		ResponseEntity<String> response = restTemplate.exchange(endPoint, HttpMethod.POST, entity, String.class);
		logger.info("Body Service response: " + response.getBody());
		return response.getBody();
	}

	public String getRestful(String endPoint) {
		logger.info("Restful HTTP GET : to" + endPoint);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(headers);
		ResponseEntity<String> response = restTemplate.exchange(endPoint, HttpMethod.GET, entity, String.class);
		logger.info("Body Service response: " + response.getBody());
		return response.getBody();
	}
}
