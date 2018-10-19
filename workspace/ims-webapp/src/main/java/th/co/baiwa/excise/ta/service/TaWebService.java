package th.co.baiwa.excise.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ws.WebServiceExciseService;
import th.co.baiwa.excise.ws.entity.response.IncFri8000.res.IncFri8000Res;
import th.co.baiwa.excise.ws.entity.response.IncFri8000.res.ResponseData8000Res;
import th.co.baiwa.excise.ws.entity.response.regfri4000.res.RegMaster60List;
import th.co.baiwa.excise.ws.entity.response.regfri4000.res.Regfri4000Res;
import th.co.baiwa.excise.ws.entity.response.regfri4000.res.ResponseDataRes;

@Service
public class TaWebService {
	
	private final Integer PAGENO = 1000;
	private final String TYPE = "1";
	private final String ACTIVE = "1";
	
	@Autowired
	private WebServiceExciseService webServiceExciseService;	

	public void regFri4000() {
		
		List<RegMaster60List> regList;
		Integer runPageNo = 1;
		do {
						regList = new ArrayList<RegMaster60List>();
			Regfri4000Res res = webServiceExciseService.IncFri4000(TYPE, ACTIVE, runPageNo.toString(), PAGENO.toString());
			runPageNo++;
			ResponseDataRes result = res.getResponseData();
			regList = result.getRegMaster60List();
//			for (RegMaster60List intiReg : regList) {
//				
//			}
			
		} while(PAGENO == regList.size());			
	}
	
	public void intIncFri8000() {
//		IncFri8000Res re = webServiceExciseService.IncFri8000("201802", "201808", "Income", "1", "10");
//		ResponseData8000Res xxx = re.getResponseData();
	}
}
