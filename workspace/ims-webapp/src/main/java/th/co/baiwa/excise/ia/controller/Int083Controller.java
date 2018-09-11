
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
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.DataTableRequest;
import th.co.baiwa.excise.domain.Int0801Vo;
import th.co.baiwa.excise.domain.Int0803Vo;
import th.co.baiwa.excise.domain.RiskFullDataVo;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcAreaDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcAreaHdr;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcNocDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcOtherDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcPenDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcRecDtl;
import th.co.baiwa.excise.ia.service.RiskAssExcAreaService;
import th.co.baiwa.excise.ta.persistence.vo.Ope041Vo;
import th.co.baiwa.excise.upload.service.UploadFileExciseService;
import th.co.baiwa.excise.utils.BeanUtils;

@Controller
@RequestMapping("api/ia/int083")
public class Int083Controller {

	private Logger logger = LoggerFactory.getLogger(Int083Controller.class);

	private final String WS_SESSION_DATA_1 = "WS_SESSION_DATA_INT08-3-3";
	private final String WS_SESSION_DATA_2 = "WS_SESSION_DATA_INT08-3-6";
	private final String WS_SESSION_DATA_3 = "WS_SESSION_DATA_INT08-3-7";
	private final String WS_SESSION_DATA_4 = "WS_SESSION_DATA_INT08-3-8";
	
	@Autowired
	private RiskAssExcAreaService riskAssExcAreaService;
	
	@Autowired
	private UploadFileExciseService uploadFileExciseService;
	
	@Autowired
	public Int083Controller(RiskAssExcAreaService riskAssExcAreaService) {
		this.riskAssExcAreaService = riskAssExcAreaService;
	}

	@PostMapping("/addRiskAssExcAreaHdr")
	@ResponseBody
	public Message addRiskAssExcAreaHdr(@RequestBody RiskAssExcAreaHdr riskAssRiskWsHdr) {
		logger.info("Add QtnReportHeader" + riskAssRiskWsHdr.getRiskHdrName());
		Message message =  null;
		if(BeanUtils.isNotEmpty(riskAssRiskWsHdr.getRiskHdrName())){
			message = riskAssExcAreaService.createRiskAssExcAreaHdrRepository(riskAssRiskWsHdr);
		}
		return message;
	}
	@PostMapping("/findRiskById")
	@ResponseBody
	public RiskAssExcAreaHdr findRiskById(@RequestBody RiskAssExcAreaHdr riskAssRiskWsHdr) {
		logger.info("findRiskById" + riskAssRiskWsHdr.getRiskHrdId());
		return riskAssExcAreaService.findById(riskAssRiskWsHdr.getRiskHrdId());
	}
	
	@PostMapping("/searchRiskAssExcAreaHdr")
	@ResponseBody
	public ResponseDataTable<RiskAssExcAreaHdr> searchRiskAssExcAreaHdr(DataTableRequest dataTableRequest , RiskAssExcAreaHdr riskAssRiskWsHdr) {
		logger.info("queryQtnReportHeaderByCriteria");
		return riskAssExcAreaService.findByCriteriaForDatatable(riskAssRiskWsHdr, dataTableRequest);
	}
	
	
	@PostMapping("/deleteRiskAssExcAreaHdr")
	@ResponseBody
	public Message deleteRiskAssExcAreaHdr(@RequestBody RiskAssExcAreaHdr riskAssRiskWsHdr) {
		logger.info("Delete QtnReportHeader" + riskAssRiskWsHdr.getRiskHdrName());
		Message message = riskAssExcAreaService.deleteRiskAssExcAreaHdrRepository(riskAssRiskWsHdr);
		return message;
	}

	@PostMapping("/dataTableWebService1")
	@ResponseBody
	public ResponseDataTable<RiskAssExcAreaDtl> dataTableWebService1(DataTableRequest dataTableRequest,RiskAssExcAreaHdr riskAssExcAreaHdr,HttpServletRequest httpServletRequest) {
		logger.info("dataTableWebService1");
		List<RiskAssExcAreaDtl> riskAssExcAreaDtlList = null;
		httpServletRequest.getSession().setAttribute(WS_SESSION_DATA_1 , null);
		riskAssExcAreaDtlList = riskAssExcAreaService.findByGroupRiskHrdId(riskAssExcAreaHdr.getRiskHrdId());
		if(BeanUtils.isEmpty(riskAssExcAreaDtlList)) {
			riskAssExcAreaDtlList = riskAssExcAreaService.findRiskAssRiskDtlByWebService();
		}
		ResponseDataTable<RiskAssExcAreaDtl> responseDataTable = new ResponseDataTable<RiskAssExcAreaDtl>();
		httpServletRequest.getSession().setAttribute(WS_SESSION_DATA_1 , riskAssExcAreaDtlList);
		responseDataTable.setData(riskAssExcAreaDtlList);
		responseDataTable.setRecordsTotal((int) riskAssExcAreaDtlList.size());
		responseDataTable.setRecordsFiltered((int) riskAssExcAreaDtlList.size());
		return responseDataTable;
	}
	
	
	@PostMapping("/createBudgetYear")
	@ResponseBody
	public Message createBuggetYear(@RequestBody RiskAssExcAreaHdr riskAssRiskWsHdr) {
		System.out.println(UserLoginUtils.getCurrentUserBean().getOfficeCode());
		logger.info("Add createBuggetYear" + riskAssRiskWsHdr.getBudgetYear());
		Message message =  null;
		if(BeanUtils.isNotEmpty(riskAssRiskWsHdr.getBudgetYear())){
			message = riskAssExcAreaService.createBuggetYear(riskAssRiskWsHdr);
		}
		return message;
	}
	
	
	@PostMapping("/updateRiskAssExcAreaHdr")
	@ResponseBody
	public Message updateRiskAssExcAreaHdr(@RequestBody RiskAssExcAreaHdr riskAssRiskWsHdr,HttpServletRequest httpServletRequest) {
		Message message = null;
		logger.info("updateRiskAssExcAreaHdr" + riskAssRiskWsHdr.getRiskHrdId());
		try {
			riskAssExcAreaService.updateRiskAssExcAreaHdr(riskAssRiskWsHdr);
			@SuppressWarnings("unchecked")
			List<RiskAssExcAreaDtl> riskAssRiskWsDtlList = (List<RiskAssExcAreaDtl>) httpServletRequest.getSession().getAttribute(WS_SESSION_DATA_1);
			for (RiskAssExcAreaDtl riskAssRiskWsDtl : riskAssRiskWsDtlList) {
				riskAssRiskWsDtl.setRiskHrdId(riskAssRiskWsHdr.getRiskHrdId());
			}
			riskAssExcAreaService.updateRiskAssExcAreaDtl(riskAssRiskWsDtlList);
			message = ApplicationCache.getMessage("MSG_00002");
		} catch (Exception e) {
			e.printStackTrace();
			message = ApplicationCache.getMessage("MSG_00003");
		}
		
		
		return message;
	}
	
	
	
	@PostMapping("/saveRiskAssOther")
	@ResponseBody
	public Message saveRiskAssOther(@RequestBody RiskAssExcAreaHdr riskAssRiskWsHdr) {
		Message message = null;
		logger.info("saveRiskAssOther" + riskAssRiskWsHdr.getRiskHrdId());
		try {
			
			riskAssExcAreaService.updateRiskAssExcAreaHdr(riskAssRiskWsHdr);
			message = ApplicationCache.getMessage("MSG_00002");
		} catch (Exception e) {
			message = ApplicationCache.getMessage("MSG_00003");
		}
		
		
		return message;
	}
	
	@PostMapping("/saveRiskAssDtlOther")
	@ResponseBody
	public Message saveRiskAssDtlOther(@RequestBody Int0803Vo int0803Vo) {
		Message message = null;
		logger.info("saveRiskAssDtlOther");
		try {
			riskAssExcAreaService.updateRiskAssOtherDtl(int0803Vo.getRiskAssExcOtherDtlList());
			message = ApplicationCache.getMessage("MSG_00002");
		} catch (Exception e) {
			e.printStackTrace();
			message = ApplicationCache.getMessage("MSG_00003");
		}
		
		return message;
	}
	
	@PostMapping("/findRiskOtherDtlByRiskHrdId")
	@ResponseBody
	public List<RiskAssExcOtherDtl> findRiskOtherDtlByRiskHrdId(@RequestBody RiskAssExcOtherDtl riskAssExcOtherDtl) {
		logger.info("findRiskOtherDtlByRiskHrdId" + riskAssExcOtherDtl.getRiskHrdId());
		return riskAssExcAreaService.findByRiskHrdId(riskAssExcOtherDtl.getRiskHrdId());
	}
	
	@PostMapping("excelINT083")
	@ResponseBody
	public List<RiskAssExcOtherDtl> excelINT081(@ModelAttribute Ope041Vo mainForm) throws Exception {
		List<RiskAssExcOtherDtl> excelData = new ArrayList<RiskAssExcOtherDtl>();
		if (mainForm.getFileExel() != null) {
			RiskAssExcOtherDtl row = new RiskAssExcOtherDtl();
			List<String[]> ListfileEx = uploadFileExciseService.readFileExcel(mainForm);
			for (int j = 1; j < ListfileEx.size(); j++) {
				String[] stringArr = ListfileEx.get(j);

				row = new RiskAssExcOtherDtl();
				for (int i = 0; i < stringArr.length; i++) {
					if (i == 0) {
						row.setRiskOtherDtlId(new Long(i + 1));
					} else if (i == 1) {
						row.setDepartmentName(stringArr[i]);
					} else if (i == 2) {
						row.setRiskCost(new BigDecimal(stringArr[i]));
					}
				}
				excelData.add(row);
			}
		}
		return excelData;
	}
	
	@PostMapping("/findByBudgetYear")
	@ResponseBody
	public List<RiskAssExcAreaHdr> findByBudgetYear(@RequestBody RiskAssExcAreaHdr riskAssExcAreaHdr) {
		return riskAssExcAreaService.findRiskAssExcAreaHdrByCriteria(riskAssExcAreaHdr);
	}
	
	@PostMapping("/searchFullRiskByBudgetYear")
	@ResponseBody
	public List<RiskFullDataVo> searchFullRiskByBudgetYear(@RequestBody Int0801Vo int0801Vo) {
		return riskAssExcAreaService.searchFullRiskByBudgetYear(int0801Vo.getBudgetYear(), int0801Vo.getRiskHrdNameList());
	}
	
	@PostMapping("/updateRiskPercent")
	@ResponseBody
	public List<RiskAssExcAreaHdr> updateRiskPercent(@RequestBody Int0803Vo  int0803Vo) {
		return riskAssExcAreaService.updatePercent(int0803Vo.getRiskAssExcAreaHdrList());
	}
	
	@PostMapping("/findRiskAssExcOtherDtlByHeaderId")
	@ResponseBody
	public ResponseDataTable<RiskAssExcOtherDtl> findRiskAssExcOtherDtlByHeaderId(DataTableRequest dataTableRequest,RiskAssExcAreaHdr riskAssExcAreaHdr) {
		logger.info("findRiskAssExcOtherDtlByHeaderId");
		List<RiskAssExcOtherDtl> riskAssOtherDtlList = riskAssExcAreaService.findByRiskHrdId(riskAssExcAreaHdr.getRiskHrdId());
		ResponseDataTable<RiskAssExcOtherDtl> responseDataTable = new ResponseDataTable<RiskAssExcOtherDtl>();
		responseDataTable.setData(riskAssOtherDtlList);
		responseDataTable.setRecordsTotal(riskAssOtherDtlList.size());
		responseDataTable.setRecordsFiltered(riskAssOtherDtlList.size());
		return responseDataTable;
	}
	
	@PostMapping("/searchRiskAssRiskWsDtl")
	@ResponseBody
	public List<RiskAssExcAreaDtl> searchRiskAssRiskWsDtl(@RequestBody RiskAssExcAreaHdr riskAssRiskWsHdr) {
		logger.info("dataTableRiskAssRiskWsDtl");
		List<RiskAssExcAreaDtl> riskAssRiskWsHdrList = null;
		riskAssRiskWsHdrList = riskAssExcAreaService.findByGroupRiskHrdId(riskAssRiskWsHdr.getRiskHrdId());
		return riskAssRiskWsHdrList;
	}
	
	
	@PostMapping("/dataTableWebService2")
	@ResponseBody
	public ResponseDataTable<RiskAssExcRecDtl> dataTableWebService2(DataTableRequest dataTableRequest,RiskAssExcRecDtl riskAssExcRecDtl,HttpServletRequest httpServletRequest) {
		logger.info("dataTableWebService2");
		List<RiskAssExcRecDtl> riskAssExcRecDtlList = null;
		httpServletRequest.getSession().setAttribute(WS_SESSION_DATA_2 , null);
		riskAssExcRecDtlList = riskAssExcAreaService.findriskAssExcRecDtlByHrdId(riskAssExcRecDtl.getRiskHrdId());
		if(BeanUtils.isEmpty(riskAssExcRecDtlList)) {
			riskAssExcRecDtlList = riskAssExcAreaService.riskAssExcAreaDtlByWebService();
		}
		ResponseDataTable<RiskAssExcRecDtl> responseDataTable = new ResponseDataTable<RiskAssExcRecDtl>();
		httpServletRequest.getSession().setAttribute(WS_SESSION_DATA_2 , riskAssExcRecDtlList);
		responseDataTable.setData(riskAssExcRecDtlList);
		responseDataTable.setRecordsTotal((int) riskAssExcRecDtlList.size());
		responseDataTable.setRecordsFiltered((int) riskAssExcRecDtlList.size());
		return responseDataTable;
	}
	
	@PostMapping("/updateRiskAssExcRecDtl")
	@ResponseBody
	public Message updateRiskAssExcRecDtl(@RequestBody RiskAssExcAreaHdr riskAssRiskWsHdr,HttpServletRequest httpServletRequest) {
		Message message = null;
		logger.info("updateRiskAssExcAreaHdr" + riskAssRiskWsHdr.getRiskHrdId());
		try {
			riskAssExcAreaService.updateRiskAssExcAreaHdr(riskAssRiskWsHdr);
			@SuppressWarnings("unchecked")
			List<RiskAssExcRecDtl> riskAssRiskWsDtlList = (List<RiskAssExcRecDtl>) httpServletRequest.getSession().getAttribute(WS_SESSION_DATA_2);
			for (RiskAssExcRecDtl riskAssRiskWsDtl : riskAssRiskWsDtlList) {
				riskAssRiskWsDtl.setRiskHrdId(riskAssRiskWsHdr.getRiskHrdId());
			}
			riskAssExcAreaService.updateRiskAssExcRecDtl(riskAssRiskWsDtlList);
			message = ApplicationCache.getMessage("MSG_00002");
		} catch (Exception e) {
			e.printStackTrace();
			message = ApplicationCache.getMessage("MSG_00003");
		}
		
		
		return message;
	}
	
	
	
	
	@PostMapping("/dataTableWebService3")
	@ResponseBody
	public ResponseDataTable<RiskAssExcPenDtl> dataTableWebService3(DataTableRequest dataTableRequest,RiskAssExcRecDtl riskAssExcRecDtl,HttpServletRequest httpServletRequest) {
		logger.info("dataTableWebService3");
		List<RiskAssExcPenDtl> riskAssExcRecDtlList = null;
		httpServletRequest.getSession().setAttribute(WS_SESSION_DATA_3 , null);
		riskAssExcRecDtlList = riskAssExcAreaService.findRiskAssExcPenDtlByHrdId(riskAssExcRecDtl.getRiskHrdId());
		if(BeanUtils.isEmpty(riskAssExcRecDtlList)) {
			riskAssExcRecDtlList = riskAssExcAreaService.riskAssExcPenDtlByWebService();
		}
		ResponseDataTable<RiskAssExcPenDtl> responseDataTable = new ResponseDataTable<RiskAssExcPenDtl>();
		httpServletRequest.getSession().setAttribute(WS_SESSION_DATA_3 , riskAssExcRecDtlList);
		responseDataTable.setData(riskAssExcRecDtlList);
		responseDataTable.setRecordsTotal((int) riskAssExcRecDtlList.size());
		responseDataTable.setRecordsFiltered((int) riskAssExcRecDtlList.size());
		return responseDataTable;
	}
	
	@PostMapping("/updateRiskAssExcPenDtl")
	@ResponseBody
	public Message updateRiskAssExcPenDtl(@RequestBody RiskAssExcAreaHdr riskAssRiskWsHdr,HttpServletRequest httpServletRequest) {
		Message message = null;
		logger.info("updateRiskAssExcAreaHdr" + riskAssRiskWsHdr.getRiskHrdId());
		try {
			riskAssExcAreaService.updateRiskAssExcAreaHdr(riskAssRiskWsHdr);
			@SuppressWarnings("unchecked")
			List<RiskAssExcPenDtl> riskAssRiskWsDtlList = (List<RiskAssExcPenDtl>) httpServletRequest.getSession().getAttribute(WS_SESSION_DATA_3);
			for (RiskAssExcPenDtl riskAssRiskWsDtl : riskAssRiskWsDtlList) {
				riskAssRiskWsDtl.setRiskHrdId(riskAssRiskWsHdr.getRiskHrdId());
			}
			riskAssExcAreaService.updateRiskAssExcPenDtl(riskAssRiskWsDtlList);
			message = ApplicationCache.getMessage("MSG_00002");
		} catch (Exception e) {
			e.printStackTrace();
			message = ApplicationCache.getMessage("MSG_00003");
		}
		return message;
	}
	
	@PostMapping("/dataTableWebService4")
	@ResponseBody
	public ResponseDataTable<RiskAssExcNocDtl> dataTableWebService4(DataTableRequest dataTableRequest,RiskAssExcRecDtl riskAssExcRecDtl,HttpServletRequest httpServletRequest) {
		logger.info("dataTableWebService4");
		List<RiskAssExcNocDtl> riskAssExcRecDtlList = null;
		httpServletRequest.getSession().setAttribute(WS_SESSION_DATA_4 , null);
		riskAssExcRecDtlList = riskAssExcAreaService.findRiskAssExcNocDtlByHrdId(riskAssExcRecDtl.getRiskHrdId());
		if(BeanUtils.isEmpty(riskAssExcRecDtlList)) {
			riskAssExcRecDtlList = riskAssExcAreaService.riskAssExcNocDtlByWebService();
		}
		ResponseDataTable<RiskAssExcNocDtl> responseDataTable = new ResponseDataTable<RiskAssExcNocDtl>();
		httpServletRequest.getSession().setAttribute(WS_SESSION_DATA_4 , riskAssExcRecDtlList);
		responseDataTable.setData(riskAssExcRecDtlList);
		responseDataTable.setRecordsTotal((int) riskAssExcRecDtlList.size());
		responseDataTable.setRecordsFiltered((int) riskAssExcRecDtlList.size());
		return responseDataTable;
	}
	
	@PostMapping("/updateRiskAssExcNocDtl")
	@ResponseBody
	public Message updateRiskAssExcNocDtl(@RequestBody RiskAssExcAreaHdr riskAssRiskWsHdr,HttpServletRequest httpServletRequest) {
		Message message = null;
		logger.info("updateRiskAssExcAreaHdr" + riskAssRiskWsHdr.getRiskHrdId());
		try {
			riskAssExcAreaService.updateRiskAssExcAreaHdr(riskAssRiskWsHdr);
			@SuppressWarnings("unchecked")
			List<RiskAssExcNocDtl> riskAssExcNocDtlList = (List<RiskAssExcNocDtl>) httpServletRequest.getSession().getAttribute(WS_SESSION_DATA_4);
			for (RiskAssExcNocDtl riskAssRiskWsDtl : riskAssExcNocDtlList) {
				riskAssRiskWsDtl.setRiskHrdId(riskAssRiskWsHdr.getRiskHrdId());
			}
			riskAssExcAreaService.updateRiskAssExcNocDtl(riskAssExcNocDtlList);
			message = ApplicationCache.getMessage("MSG_00002");
		} catch (Exception e) {
			e.printStackTrace();
			message = ApplicationCache.getMessage("MSG_00003");
		}
		return message;
	}
	@PostMapping("/updateStatusRisk")
	@ResponseBody
	public void updateStatusRisk(@RequestBody RiskAssExcAreaHdr risk) {
		logger.info("updateStatusRisk");
		riskAssExcAreaService.updateStatusRisk(risk);

	}
	

}
