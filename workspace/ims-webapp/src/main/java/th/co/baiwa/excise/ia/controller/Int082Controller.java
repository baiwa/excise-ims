package th.co.baiwa.excise.ia.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.DataTableRequest;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfHdr;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfOtherDtl;

import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfDtl;
import th.co.baiwa.excise.ia.service.RiskAssInfService;
import th.co.baiwa.excise.ta.persistence.entity.Ope041Vo;
import th.co.baiwa.excise.upload.service.UploadFileExciseService;
import th.co.baiwa.excise.utils.BeanUtils;

@Controller
@RequestMapping("api/ia/int082")
public class Int082Controller {
	
	private Logger logger = LoggerFactory.getLogger(Int082Controller.class);
	
	private final String INF_SESSION_DATA = "INF_SESSION_DATA";
	
	@Autowired
	UploadFileExciseService uploadFileExciseService;
	
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
	
	@PostMapping("/findRiskAssInfOtherDtlByRiskHrdId")
	@ResponseBody
	public List<RiskAssInfOtherDtl> findRiskAssInfOtherDtlByRiskHrdId(@RequestBody RiskAssInfOtherDtl riskAssInfOtherDtl) {
		logger.info("findRiskAssInfOtherDtlByRiskHrdId : " + riskAssInfOtherDtl.getRiskInfHrdId());
		return riskAssInfService.findByOtherRiskHrdId(riskAssInfOtherDtl.getRiskInfHrdId());
	}
	
	@PostMapping("excelINT082")
	@ResponseBody
	public List<RiskAssInfOtherDtl> excelINT081(@ModelAttribute Ope041Vo mainForm) throws Exception {
		List<RiskAssInfOtherDtl> excelData = new ArrayList<RiskAssInfOtherDtl>();
		if (mainForm.getFileExel() != null) {
			RiskAssInfOtherDtl row = new RiskAssInfOtherDtl();
			List<String[]> ListfileEx = uploadFileExciseService.readFileExcel(mainForm);
			for (int j = 1; j < ListfileEx.size(); j++) {
				String[] stringArr = ListfileEx.get(j);

				row = new RiskAssInfOtherDtl();
				for (int i = 0; i < stringArr.length; i++) {
					if (i == 0) {
						row.setRiskAssInfOtherId(new Long(i + 1));
					} else if (i == 1) {
						row.setRiskAssInfOtherName(stringArr[i]);
						
					} else if (i == 2) {
						row.setRiskCost(new BigDecimal(stringArr[i]));
						
					} 
				}
				excelData.add(row);
			}
		}
		return excelData;
	}
	
	
	@PostMapping("/saveRiskInfPaperName")
	@ResponseBody
	public Message saveRiskInfPaperName(@RequestBody RiskAssInfHdr riskAssInfHdr) {
		Message message = null;
		logger.info("saveRiskInfPaperName" + riskAssInfHdr.getRiskAssInfHdrId());
		try {
			riskAssInfService.updateRiskAssInfHdr(riskAssInfHdr);
			
			message = ApplicationCache.getMessage("MSG_00002");
		} catch (Exception e) {
			message = ApplicationCache.getMessage("MSG_00003");
		}
		return message;
	}
	
	@PostMapping("/saveRiskAssInfOther")
	@ResponseBody
	public Message saveRiskAssInfOther(@RequestBody RiskAssInfOtherDtl riskAssInfOtherDtl) {
		Message message = null;
		logger.info("saveRiskAssDtlOther" + riskAssInfOtherDtl.getRiskInfHrdId());
		try {
			riskAssInfService.updateRiskAssInfOtherDtl(riskAssInfOtherDtl);
			message = ApplicationCache.getMessage("MSG_00002");
		} catch (Exception e) {
			message = ApplicationCache.getMessage("MSG_00003");
		}
		
		return message;
	}
	
	
}
