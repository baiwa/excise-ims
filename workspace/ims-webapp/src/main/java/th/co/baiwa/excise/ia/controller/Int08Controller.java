package th.co.baiwa.excise.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.DataTableRequest;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsHdr;
import th.co.baiwa.excise.ia.service.RiskAssRiskWsService;
import th.co.baiwa.excise.utils.BeanUtils;

@Controller
@RequestMapping("api/ia/int08")
public class Int08Controller {

	private Logger logger = LoggerFactory.getLogger(Int08Controller.class);

	@Autowired
	private RiskAssRiskWsService riskAssRiskWsHdrService;
	
	@Autowired
	public Int08Controller(RiskAssRiskWsService riskAssRiskWsHdrService) {
		this.riskAssRiskWsHdrService = riskAssRiskWsHdrService;
	}

	@PostMapping("/addRiskAssRiskWsHdr")
	@ResponseBody
	public Message addRiskAssRiskWsHdr(@RequestBody RiskAssRiskWsHdr riskAssRiskWsHdr) {
		logger.info("Add QtnReportHeader" + riskAssRiskWsHdr.getRiskHdrName());
		Message message =  null;
		if(BeanUtils.isNotEmpty(riskAssRiskWsHdr.getRiskHdrName())){
			message = riskAssRiskWsHdrService.createRiskAssRiskWsHdrRepository(riskAssRiskWsHdr);
		}else {
			message = ApplicationCache.getMessage("XXXXXX");
		}
		return message;
	}
	
	@PostMapping("/searchRiskAssRiskWsHdr")
	@ResponseBody
	public ResponseDataTable<RiskAssRiskWsHdr> searchRiskAssRiskWsHdr(DataTableRequest dataTableRequest) {
		logger.info("queryQtnReportHeaderByCriteria");
		return riskAssRiskWsHdrService.findByCriteriaForDatatable(new RiskAssRiskWsHdr(), dataTableRequest);
	}
	
	
	@PostMapping("/deleteRiskAssRiskWsHdr")
	@ResponseBody
	public Message deleteRiskAssRiskWsHdr(@RequestBody RiskAssRiskWsHdr riskAssRiskWsHdr) {
		logger.info("Delete QtnReportHeader" + riskAssRiskWsHdr.getRiskHdrName());
		Message message = riskAssRiskWsHdrService.deleteRiskAssRiskWsHdrRepository(riskAssRiskWsHdr);
		return message;
	}

	@PostMapping("/dataTableWebService1")
	@ResponseBody
	public ResponseDataTable<RiskAssRiskWsDtl> dataTableWebService1(DataTableRequest dataTableRequest) {
		logger.info("dataTableWebService1");
		return riskAssRiskWsHdrService.findRiskAssRiskDtlByWebService();
	}
	
	
	@PostMapping("/createBuggetYear")
	@ResponseBody
	public Message createBuggetYear(@RequestBody RiskAssRiskWsHdr riskAssRiskWsHdr) {
		logger.info("Add createBuggetYear" + riskAssRiskWsHdr.getBuggetYear());
		Message message =  null;
		if(BeanUtils.isNotEmpty(riskAssRiskWsHdr.getBuggetYear())){
			message = riskAssRiskWsHdrService.createBuggetYear(riskAssRiskWsHdr);
		}else {
			message = ApplicationCache.getMessage("XXXXXX");
		}
		return message;
	}
	
	
	public RiskAssRiskWsService getRiskAssRiskWsHdrService() {
		return riskAssRiskWsHdrService;
	}

	

}
