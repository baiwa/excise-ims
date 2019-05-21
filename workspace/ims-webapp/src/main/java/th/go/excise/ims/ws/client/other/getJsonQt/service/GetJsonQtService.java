package th.go.excise.ims.ws.client.other.getJsonQt.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import okhttp3.HttpUrl;
import th.go.excise.ims.ws.client.other.getJsonQt.model.GetJsonQt;
import th.go.excise.ims.ws.client.other.getJsonQt.model.RequestData;
import th.go.excise.ims.ws.client.service.RestfulClientService;

@Service
public class GetJsonQtService {
	
	private String url;
	private RestfulClientService restfulClientService;
	private Gson gson;
	
	@Autowired
	public GetJsonQtService(
			@Value("${ws.excise.endpoint.pm.system-unworking}") String url,
			RestfulClientService restfulClientService,
			Gson gson) {
		this.url = url;
		this.restfulClientService = restfulClientService;
		this.gson = gson;
	}

	public GetJsonQt execute(RequestData requestData) throws IOException {
		HttpUrl.Builder urlBuilder = HttpUrl.parse(url).newBuilder();
		urlBuilder.addQueryParameter("year", requestData.getYear());
		urlBuilder.addQueryParameter("oCode", requestData.getoCode());
		String respJson = restfulClientService.get(urlBuilder.build().toString());
		GetJsonQt responseData = gson.fromJson(respJson, GetJsonQt.class);
		return responseData;
	}

}