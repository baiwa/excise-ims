package th.go.excise.ims.ws.client.pcc.inquiryDutyGroup.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import th.go.excise.ims.ws.WsService;
import th.go.excise.ims.ws.client.pcc.common.oxm.PccRequestHeader;
import th.go.excise.ims.ws.client.pcc.common.service.PccRequestHeaderService;
import th.go.excise.ims.ws.client.pcc.inquiryDutyGroup.oxm.DutyGroup;
import th.go.excise.ims.ws.client.pcc.inquiryDutyGroup.oxm.InquiryDutyGroupRequest;
import th.go.excise.ims.ws.client.pcc.inquiryDutyGroup.oxm.InquiryDutyGroupResponse;


@Service
public class InquiryDutyGroupService {

	@Value("${ws.excise.endpointInquiryDutyGroup}")
	private String endpoint;

	@Autowired
	private PccRequestHeaderService pccRequestHeaderService;

	@Autowired
	private WsService wsService;

	public List<DutyGroup> postRestFul(InquiryDutyGroupRequest inquiryDutyGroupRequest) throws IOException {
		List<DutyGroup> dutyGroupList = new ArrayList<>();

		// String json = pccRequestHeaderService.postRestful(endpoint, licFri6010Request);
		PccRequestHeader requestRestful = new PccRequestHeader();
		requestRestful.setSystemId("WSS");
		requestRestful.setUserName("wss001");
		requestRestful.setPassword("123456");
		requestRestful.setIpAddress("10.1.1.1");
		requestRestful.setRequestData(inquiryDutyGroupRequest);
		Gson gson = new Gson();
		String json2 = gson.toJson(requestRestful);
		String json = wsService.post(endpoint, json2);
		System.out.println(json);

		gson = new Gson();
		InquiryDutyGroupResponse pccResponseHeader = gson.fromJson(json, InquiryDutyGroupResponse.class);
		if ("OK".equals(pccResponseHeader.getResponseCode())) {
			dutyGroupList = pccResponseHeader.getResponseData();
		}

		return dutyGroupList;
	}
}
