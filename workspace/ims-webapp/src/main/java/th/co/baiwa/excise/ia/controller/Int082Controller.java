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
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfHdr;

import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfDtl;
import th.co.baiwa.excise.ia.service.RiskAssInfService;
import th.co.baiwa.excise.utils.BeanUtils;

@Controller
@RequestMapping("api/ia/int082")
public class Int082Controller {
	
	private Logger logger = LoggerFactory.getLogger(Int082Controller.class);
	
	private final String INF_SESSION_DATA = "INF_SESSION_DATA";
	
	@Autowired
	private RiskAssInfService riskAssInfService;
	
	@Autowired
	public Int082Controller(RiskAssInfService riskAssInfService) {
		this.riskAssInfService = riskAssInfService;
	}
	
	@PostMapping("/addRiskInfHdr")
	@ResponseBody
	public Message addRiskInfHdr(@RequestBody RiskAssInfHdr riskAssInfHdr) {
		logger.info("Add RiskAssInfHdr : "  + riskAssInfHdr.getRiskAssInfHdrName());
		Message message =  null;
		if(BeanUtils.isNotEmpty(riskAssInfHdr.getRiskAssInfHdrName())){
			message = riskAssInfService.createRiskAssInfHdrRepository(riskAssInfHdr);
		}else {
			message = ApplicationCache.getMessage("XXXXXX");
		}
		return message;
	}
	
	@PostMapping("/searchRiskInfHdr")
	@ResponseBody
	public ResponseDataTable<RiskAssInfHdr> searchRiskAssInfHdr(RiskAssInfHdr riskAssInfHdr ,DataTableRequest dataTableRequest) {
		logger.info("queryRiskInfHdrfindByBuggetYear");
		logger.info("BuggetYear : " + riskAssInfHdr.getBudgetYear());
		return riskAssInfService.findByCriteriaForDatatable( riskAssInfHdr, dataTableRequest);
	}
	
	@PostMapping("/deleteRiskInfHdr")
	@ResponseBody
	public Message deleteRiskAssRiskWsHdr(@RequestBody RiskAssInfHdr riskAssInfHdr) {
		logger.info("Del RiskAssInfHdr");
		Message message = riskAssInfService.deleteRiskAssInfHdrRepository(riskAssInfHdr);
		return message;
	}
	
	@PostMapping("/dataTableWebService")
	@ResponseBody
	public ResponseDataTable<RiskAssInfDtl> dataTableWebService(DataTableRequest dataTableRequest,HttpServletRequest httpServletRequest) {
		logger.info("dataTableWebService");
		httpServletRequest.getSession().setAttribute(INF_SESSION_DATA , null);
		
		ResponseDataTable<RiskAssInfDtl> responseDataTable = new ResponseDataTable<RiskAssInfDtl>();
		List<RiskAssInfDtl> riskAssInfDtlList = riskAssInfService.findRiskAssInfDtlByWebService();
		
		httpServletRequest.getSession().setAttribute(INF_SESSION_DATA , riskAssInfDtlList);
		responseDataTable.setData(riskAssInfDtlList);
		responseDataTable.setRecordsTotal((int) riskAssInfDtlList.size());
		responseDataTable.setRecordsFiltered((int) riskAssInfDtlList.size());
		return responseDataTable;
	}
	
	@PostMapping("/createBudgetYear")
	@ResponseBody
	public Message createBuggetYear(@RequestBody RiskAssInfHdr riskAssInfHdr) {
		logger.info("Add createBuggetYear : " + riskAssInfHdr.getBudgetYear());
		Message message =  null;
		if(BeanUtils.isNotEmpty(riskAssInfHdr.getBudgetYear())){
			message = riskAssInfService.createBudgetYear(riskAssInfHdr);
		}
		return message;
	}
	
	@PostMapping("/findRiskById")
	@ResponseBody
	public RiskAssInfHdr findRiskById(@RequestBody RiskAssInfHdr riskAssInfHdr) {
		logger.info("findRiskById : " + riskAssInfHdr.getRiskAssInfHdrId());
		return riskAssInfService.findById(riskAssInfHdr.getRiskAssInfHdrId());
	}
	
	@PostMapping("/updateRiskAssInfHdr")
	@ResponseBody
	public Message updateRiskAssInfHdr(@RequestBody RiskAssInfHdr riskAssInfHdr,HttpServletRequest httpServletRequest) {
		Message message = null;
		logger.info("updateRiskAssInfHdrById : " + riskAssInfHdr.getRiskAssInfHdrId());
		try {
			riskAssInfService.updateRiskAssInfHdr(riskAssInfHdr);
			@SuppressWarnings("unchecked")
			List<RiskAssInfDtl> riskAssInfDtlList = (List<RiskAssInfDtl>) httpServletRequest.getSession().getAttribute(INF_SESSION_DATA);
			for (RiskAssInfDtl riskAssInfDtl : riskAssInfDtlList) {
				riskAssInfDtl.setRiskAssInfDtlId(riskAssInfHdr.getRiskAssInfHdrId());
			}
			
			riskAssInfService.updateRiskAssInfDtl(riskAssInfDtlList);
			message = ApplicationCache.getMessage("MSG_00002");
		} catch (Exception e) {
			message = ApplicationCache.getMessage("MSG_00003");
		}
		
		
		return message;
	}
	
	
}
