package th.go.excise.ims.ws.client.other.getjsonpy2.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import th.go.excise.ims.ws.client.other.getjsonpy2.model.Datum;
import th.go.excise.ims.ws.client.other.getjsonpy2.model.GetJsonPy2;
import th.go.excise.ims.ws.client.service.RestfulClientService;

@Service
public class GetJsonPy2Service {

	@Autowired
	private RestfulClientService restfulClientService;
	
	public List<Datum> callGetJsonPy2(String year , String offCode) throws IOException {

		String url = "http://192.168.48.36:8080/excise-pm/rest/getJsonPy2?year="+year+"&oCode="+offCode;
		String json = restfulClientService.get(url);
		System.out.println("#################################################");
		System.out.println("#################################################");
		System.out.println("#################################################");
		System.out.println(json.replaceAll("\n", ""));
		System.out.println("#################################################");
		System.out.println("#################################################");
		System.out.println("#################################################");
		Gson gson = new Gson();
		GetJsonPy2 jpy2 = gson.fromJson(json.replaceAll("\n", ""), GetJsonPy2.class);
		
		System.out.println(jpy2.getData());
		return jpy2.getData();
		
	}

}
