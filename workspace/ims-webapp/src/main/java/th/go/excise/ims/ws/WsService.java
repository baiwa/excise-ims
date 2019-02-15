package th.go.excise.ims.ws;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;



@Service
public class WsService {

	private static final Logger logger = LoggerFactory.getLogger(WsService.class);

	

//	public String postRestful(String endPoint, String json) {
//		logger.info("Body Service request : " + json);
//		RestTemplate restTemplate = new RestTemplate();
//		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<String> entity = new HttpEntity<String>(json, headers);
//		ResponseEntity<String> response = restTemplate.exchange(endPoint, HttpMethod.POST, entity, String.class);
//		logger.info("Body Service response: " + response.getBody());
//		return response.getBody();
//	}
//
//	public String getRestful(String endPoint) {
//		logger.info("Restful HTTP GET : to" + endPoint);
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders headers = new HttpHeaders();
//		headers.setContentType(MediaType.APPLICATION_JSON);
//		HttpEntity<String> entity = new HttpEntity<String>(headers);
//		ResponseEntity<String> response = restTemplate.exchange(endPoint, HttpMethod.GET, entity, String.class);
//		logger.info("Body Service response: " + response.getBody());
//		return response.getBody();
//	}
	
	OkHttpClient client = new OkHttpClient();
	private final MediaType JSON = MediaType.get("application/json; charset=utf-8");
	public String post(String url, String json) throws IOException {
	  RequestBody body = RequestBody.create(JSON, json);
	  Request request = new Request.Builder()
	      .url(url)
	      .post(body)
	      .build();
	  try (Response response = client.newCall(request).execute()) {
	    return response.body().string();
	  }
	}
}
