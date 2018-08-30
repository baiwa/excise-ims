package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.excise.domain.DataTableRequest;
import th.co.baiwa.excise.ia.persistence.entity.QtnMaster;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireHeader;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireMain;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireMinor;
import th.co.baiwa.excise.ia.service.QuestionnaireHeaderService;
import th.co.baiwa.excise.ia.service.QuestionnaireMainDetailService;

@Controller
@RequestMapping("api/ia/int02m3")
public class Int02m3Controller {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	QuestionnaireHeaderService questionnaireHeaderService;
	
	@Autowired
	QuestionnaireMainDetailService questionnaireMainDetailService;
	
	@PostMapping("/showInit")
	@ResponseBody
	public ResponseDataTable<QuestionnaireHeader> qtnHeader(DataTableRequest dataTableRequest) {
		logger.info("Query qtnHeader");
		return questionnaireHeaderService.findByCriteriaJpa();
	}
	
	@PostMapping("/getQtnMainList")
	@ResponseBody
	public List<Object> qtnMain(@RequestBody QuestionnaireMain qtnMain) {
		logger.info("Query qtnMain");
		return questionnaireMainDetailService.findQtnMain(qtnMain.getHeaderCode());
	}
	
//	@PostMapping("/getQtnMinorList")
//	@ResponseBody
//	public List<QuestionnaireMinor> qtnMinor(@RequestBody QuestionnaireMinor qtnMinor) {
//		logger.info("Query qtnMinor");
//		return questionnaireMainDetailService.findQtnMinor(qtnMinor.getHeaderCode());
//	}
	
	@PostMapping("/saveQtnHeader")
	@ResponseBody
	public CommonMessage<QuestionnaireHeader> saveQtnHeader(@RequestBody QuestionnaireHeader questionnaireHeader) {
		logger.info("Saved to saveQtnHeader");
		return questionnaireHeaderService.saveQtnHeader(questionnaireHeader);
	}
	
	@PostMapping("/updateQtnHeader/{id}")
	@ResponseBody
	public CommonMessage<QuestionnaireHeader> updateQtnHeader(@PathVariable("id") String id, @RequestBody QuestionnaireHeader questionnaireHeader) {
		logger.info("Saved to updateQtnHeader {}", id);
		return questionnaireHeaderService.updateQtnHeader(id, questionnaireHeader);
	}
	
	@DeleteMapping("/deleteQtnHeader/{id}")
	@ResponseBody
	public Message deleteQtnHeader(@PathVariable("id") String ids) {
		return questionnaireHeaderService.deleteQtnHeader(ids);
	}
}
