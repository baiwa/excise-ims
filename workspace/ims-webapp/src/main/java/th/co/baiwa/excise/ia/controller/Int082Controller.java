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
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfHdr;
import th.co.baiwa.excise.ia.service.RiskAssInfService;
import th.co.baiwa.excise.utils.BeanUtils;

@Controller
@RequestMapping("api/ia/int082")
public class Int082Controller {
	
	private Logger logger = LoggerFactory.getLogger(Int082Controller.class);
	
	@Autowired
	private RiskAssInfService riskAssInfService;
	
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
	public ResponseDataTable<RiskAssInfHdr> searchRiskAssInfHdr(DataTableRequest dataTableRequest) {
		logger.info("queryRiskInfHdrByCriteria");
		return riskAssInfService.findByCriteriaForDatatable(new RiskAssInfHdr() , dataTableRequest);
	}
	
	@PostMapping("/deleteRiskInfHdr")
	@ResponseBody
	public Message deleteRiskAssRiskWsHdr(@RequestBody RiskAssInfHdr riskAssInfHdr) {
		logger.info("Del RiskAssInfHdr");
		Message message = riskAssInfService.deleteRiskAssInfHdrRepository(riskAssInfHdr);
		return message;
	}
	
	
}
