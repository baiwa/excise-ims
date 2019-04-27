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

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.ia.controller.Int02010101Controller;
import th.go.excise.ims.ws.client.pcc.common.service.PccRequestHeaderService;
import th.go.excise.ims.ws.client.pcc.incfri8020.oxm.IncFri8020Request;
import th.go.excise.ims.ws.client.pcc.incfri8020.oxm.IncFri8020Response;
import th.go.excise.ims.ws.client.pcc.incfri8020.oxm.IncomeList;
import th.go.excise.ims.ws.client.pcc.incfri8020.oxm.ResponseData;
import th.go.excise.ims.ws.client.persistence.entity.WsIncfri8020Inc;
import th.go.excise.ims.ws.client.persistence.repository.WsIncfri8020IncRepository;

@Service
public class IncFri8020Service {

	private static final Logger logger = LoggerFactory.getLogger(Int02010101Controller.class);
	
	@Value("${ws.excise.endpointIncFri8020}")
	private String endpoint;

	private final int MAX_DATA = 500;

	private final String RETURN_RESPONSE_STATUS_OK = "OK";
	@Autowired
	private PccRequestHeaderService pccRequestHeaderService;
	
	@Autowired
	private WsIncfri8020IncRepository wsIncfri8020IncRepository;

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
		List<WsIncfri8020Inc> wsIncfri8020IncList;
		WsIncfri8020Inc wsInc = new WsIncfri8020Inc();
		try {
			do {
				incomeList = new ArrayList<>();
				wsIncfri8020IncList = new ArrayList<>();
				incFri8020Request.setPageNo(String.valueOf(pageNo));
				incFri8020Request.setDataPerPage(String.valueOf(MAX_DATA));
				incomeList = postRestFul(incFri8020Request);
				for (IncomeList inc : incomeList) {
					wsInc = new WsIncfri8020Inc();
					wsInc.setDepositDate(ConvertDateUtils.parseStringToDate(inc.getDepositDate(), ConvertDateUtils.YYYYMMDD));
					wsInc.setSendDate(ConvertDateUtils.parseStringToDate(inc.getSendDate(), ConvertDateUtils.YYYYMMDD));
					wsInc.setReceiptDate(ConvertDateUtils.parseStringToDate(inc.getReceiptDate(), ConvertDateUtils.YYYYMMDD));
					wsInc.setIncomeName(inc.getIncomeName());
					wsInc.setReceiptNo(inc.getReceiptNo());
					wsInc.setNetTaxAmt(NumberUtils.toBigDecimal(inc.getNettaxAmount()));
					wsInc.setNetLocAmt(NumberUtils.toBigDecimal(inc.getNetLocAmount()));
					wsInc.setLocOthAmt(NumberUtils.toBigDecimal(inc.getLocOthAmount()));
					wsInc.setLocExpAmt(NumberUtils.toBigDecimal(inc.getLocExpAmount()));
					wsInc.setOlderFundAmt(NumberUtils.toBigDecimal(inc.getOlderFundAmount()));
					wsInc.setTpbsFundAmt(NumberUtils.toBigDecimal(inc.getTpbsFundAmount()));
					wsInc.setSendAmt(NumberUtils.toBigDecimal(inc.getSendAmount()));
					wsInc.setStampAmt(NumberUtils.toBigDecimal(inc.getStampAmount()));
					wsInc.setCustomAmt(NumberUtils.toBigDecimal(inc.getCustomAmount()));
					wsInc.setTrnDate(ConvertDateUtils.parseStringToDate(inc.getTrnDate(), ConvertDateUtils.YYYYMMDD));
					wsInc.setOfficeReceive(inc.getOfficeReceive());
					wsInc.setIncomeCode(inc.getIncomeCode());
					wsInc.setReceiptNoOlderFund(inc.getReceiptNoOlderFund());
					wsInc.setReceiptNoTpbsFund(inc.getReceiptNoTpbsFund());
					wsInc.setReceiptNoSssFund(inc.getReceiptNoSssFund());
					wsInc.setReceiptNoSportFund(inc.getReceiptNoSportFund());
					wsInc.setSportFundAmt(NumberUtils.toBigDecimal(inc.getSportFundAmount()));
					wsInc.setPinNidId(inc.getPinNidId());
					wsInc.setNewRegId(inc.getNewRegId());
					wsInc.setCusName(inc.getCusName());
					wsInc.setFacName(inc.getFacName());
					wsIncfri8020IncList.add(wsInc);
					pageNo++;
				}
				wsIncfri8020IncRepository.batchInsert(wsIncfri8020IncList);
			} while (MAX_DATA == incomeList.size());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
