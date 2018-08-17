
package th.co.baiwa.excise.ia.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

	private final String WS_SESSION_DATA = "WS_SESSION_DATA";
	
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
	@PostMapping("/findRiskById")
	@ResponseBody
	public RiskAssRiskWsHdr findRiskById(@RequestBody RiskAssRiskWsHdr riskAssRiskWsHdr) {
		logger.info("findRiskById" + riskAssRiskWsHdr.getRiskHrdId());
		return riskAssRiskWsHdrService.findById(riskAssRiskWsHdr.getRiskHrdId());
	}
	
	@PostMapping("/searchRiskAssRiskWsHdr")
	@ResponseBody
	public ResponseDataTable<RiskAssRiskWsHdr> searchRiskAssRiskWsHdr(DataTableRequest dataTableRequest , RiskAssRiskWsHdr riskAssRiskWsHdr) {
		logger.info("queryQtnReportHeaderByCriteria");
		return riskAssRiskWsHdrService.findByCriteriaForDatatable(riskAssRiskWsHdr, dataTableRequest);
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
	public ResponseDataTable<RiskAssRiskWsDtl> dataTableWebService1(DataTableRequest dataTableRequest,RiskAssRiskWsHdr riskAssRiskWsHdr,HttpServletRequest httpServletRequest) {
		logger.info("dataTableWebService1");
		List<RiskAssRiskWsDtl> riskAssRiskWsHdrList = null;
		httpServletRequest.getSession().setAttribute(WS_SESSION_DATA , null);
		riskAssRiskWsHdrList = riskAssRiskWsHdrService.findByGroupRiskHrdId(riskAssRiskWsHdr.getRiskHrdId());
		if(BeanUtils.isEmpty(riskAssRiskWsHdrList)) {
			riskAssRiskWsHdrList = riskAssRiskWsHdrService.findRiskAssRiskDtlByWebService();
		}
		ResponseDataTable<RiskAssRiskWsDtl> responseDataTable = new ResponseDataTable<RiskAssRiskWsDtl>();
		httpServletRequest.getSession().setAttribute(WS_SESSION_DATA , riskAssRiskWsHdrList);
		responseDataTable.setData(riskAssRiskWsHdrList);
		responseDataTable.setRecordsTotal((int) riskAssRiskWsHdrList.size());
		responseDataTable.setRecordsFiltered((int) riskAssRiskWsHdrList.size());
		return responseDataTable;
	}
	
	
	@PostMapping("/createBudgetYear")
	@ResponseBody
	public Message createBuggetYear(@RequestBody RiskAssRiskWsHdr riskAssRiskWsHdr) {
		logger.info("Add createBuggetYear" + riskAssRiskWsHdr.getBudgetYear());
		Message message =  null;
		if(BeanUtils.isNotEmpty(riskAssRiskWsHdr.getBudgetYear())){
			message = riskAssRiskWsHdrService.createBuggetYear(riskAssRiskWsHdr);
		}
		return message;
	}
	
	
	@PostMapping("/updateRiskAssRiskWsHdr")
	@ResponseBody
	public Message updateRiskAssRiskWsHdr(@RequestBody RiskAssRiskWsHdr riskAssRiskWsHdr,HttpServletRequest httpServletRequest) {
		Message message = null;
		logger.info("updateRiskAssRiskWsHdr" + riskAssRiskWsHdr.getRiskHrdId());
		try {
			riskAssRiskWsHdrService.updateRiskAssRiskWsHdr(riskAssRiskWsHdr);
			@SuppressWarnings("unchecked")
			List<RiskAssRiskWsDtl> riskAssRiskWsDtlList = (List<RiskAssRiskWsDtl>) httpServletRequest.getSession().getAttribute(WS_SESSION_DATA);
			for (RiskAssRiskWsDtl riskAssRiskWsDtl : riskAssRiskWsDtlList) {
				riskAssRiskWsDtl.setRiskHrdId(riskAssRiskWsHdr.getRiskHrdId());
			}
			riskAssRiskWsHdrService.updateRiskAssRiskWsDtl(riskAssRiskWsDtlList);
			message = ApplicationCache.getMessage("MSG_00002");
		} catch (Exception e) {
			message = ApplicationCache.getMessage("MSG_00003");
		}
		
		
		return message;
	}
	
	public RiskAssRiskWsService getRiskAssRiskWsHdrService() {
		return riskAssRiskWsHdrService;
	}

	

}
