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
import th.co.baiwa.excise.ia.persistence.entity.QtnReportHeader;
import th.co.baiwa.excise.ia.service.QtnReportHeaderService;
import th.co.baiwa.excise.utils.BeanUtils;

@Controller
@RequestMapping("ia/int02")
public class Int02Controller {

	private Logger logger = LoggerFactory.getLogger(Int02Controller.class);
	
	@Autowired
	private QtnReportHeaderService qtnReportHeaderService;

	
	
	@PostMapping("/addHeaderQuestionnaire")
	@ResponseBody
	public Message addHeaderQuestionnaire(@RequestBody QtnReportHeader qtnReportHeader) {
		logger.info("Add QtnReportHeader");
		Message message = null;
		Integer insertCountRow = qtnReportHeaderService.createQtnReportHeader(qtnReportHeader);
		if(BeanUtils.isNotEmpty(insertCountRow) && insertCountRow.intValue() == 1) {
			message = ApplicationCache.getMessage("MSG_00002");
		}else {
			message = ApplicationCache.getMessage("MSG_00003");
		}
		return message;
	}
	
	@PostMapping("/queryQtnReportHeaderByCriteria")
	@ResponseBody
	public ResponseDataTable<QtnReportHeader> queryQtnReportHeaderByCriteria(DataTableRequest dataTableRequest) {
		logger.info("queryQtnReportHeaderByCriteria");
		return qtnReportHeaderService.findByCriteriaForDatatable(new QtnReportHeader(),dataTableRequest);
	}
}
