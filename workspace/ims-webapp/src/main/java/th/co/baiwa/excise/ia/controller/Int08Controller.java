package th.co.baiwa.excise.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsHdr;
import th.co.baiwa.excise.ia.service.RiskAssRiskWsHdrService;

@Controller
@RequestMapping("api/ia/int08")
public class Int08Controller {

	private Logger logger = LoggerFactory.getLogger(Int08Controller.class);

	@Autowired
	private RiskAssRiskWsHdrService riskAssRiskWsHdrService;
	
	@Autowired
	public Int08Controller(RiskAssRiskWsHdrService riskAssRiskWsHdrService) {
		this.riskAssRiskWsHdrService = riskAssRiskWsHdrService;
	}

	@PostMapping("/addRiskAssRiskWsHdr")
	@ResponseBody
	public Message addRiskAssRiskWsHdr(@RequestBody RiskAssRiskWsHdr riskAssRiskWsHdr) {
		logger.info("Add QtnReportHeader");
		Message message = riskAssRiskWsHdrService.createRiskAssRiskWsHdrRepository(riskAssRiskWsHdr);
		return message;
	}

	public RiskAssRiskWsHdrService getRiskAssRiskWsHdrService() {
		return riskAssRiskWsHdrService;
	}

	

}
