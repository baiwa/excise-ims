package th.co.baiwa.excise.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.CommonManageReq;
import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.excise.domain.DataTableRequest;
import th.co.baiwa.excise.domain.ia.Int023MappingVO;
import th.co.baiwa.excise.ia.persistence.entity.QtnMaster;
import th.co.baiwa.excise.ia.persistence.entity.QtnReportHeader;
import th.co.baiwa.excise.ia.persistence.entity.QtnReportMain;
import th.co.baiwa.excise.ia.persistence.entity.QuestionnaireDetail;
import th.co.baiwa.excise.ia.persistence.vo.Int022Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int023FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int023Vo;
import th.co.baiwa.excise.ia.service.QtnMasterService;
import th.co.baiwa.excise.ia.service.QtnReportHeaderService;
import th.co.baiwa.excise.ia.service.QtnReportMainService;
import th.co.baiwa.excise.ia.service.QuestionnaireDetailService;
import th.co.baiwa.excise.utils.BeanUtils;

@Controller
@RequestMapping("api/ia/int02")
public class Int02Controller {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private QtnReportHeaderService qtnReportHeaderService;

	@Autowired
	private QtnReportMainService qtnReportMainService;
	
	@Autowired
	private QuestionnaireDetailService questionnaireDetailService;
	
	@Autowired
	private QtnMasterService qtnMasterService;
	
	/*
	 * 
	 * CRUD TABLE `IA_QTN_MASTER`
	 * 
	 * */
	@GetMapping("/qtn_master_by_id/{id}")
	@ResponseBody
	public QtnMaster qtnMasterById(@PathVariable("id") String id) {
		logger.info("Query qtnMasterById {}", id);
		return qtnMasterService.findByIdQtnMaster(id);
	}
	
	@PostMapping("/qtn_master/datatable")
	@ResponseBody
	public ResponseDataTable<QtnMaster> qtnMaster(DataTableRequest dataTableRequest) {
		logger.info("Query qtnMaster");
		return qtnMasterService.findAllQtnMaster();
	}
	
	@PostMapping("/save_qtn_master")
	@ResponseBody
	public CommonMessage<QtnMaster> saveQtnMaster(@RequestBody QtnMaster qtnMaster) {
		logger.info("Saved to saveMaster");
		return qtnMasterService.saveQtnMaster(qtnMaster);
	}
	
	@PostMapping("/update_qtn_master/{id}")
	@ResponseBody
	public CommonMessage<QtnMaster> updateQtnMaster(@PathVariable("id") String id, @RequestBody QtnMaster qtnMaster) {
		logger.info("Saved to updateQtnMaster {}", id);
		return qtnMasterService.updateQtnMaster(id, qtnMaster);
	}
	
	@DeleteMapping("/delete_qtn_master/{id}")
	@ResponseBody
	public Message deleteQtnMaster(@PathVariable("id") String id) {
		return qtnMasterService.deleteQtnMaster(id);
	}
	
	/*
	 * 
	 * CRUD TABLE `IA_QTN_REPORT_HEADER`
	 * 
	 * */
	@PostMapping("/save_qtn_report_header")
	@ResponseBody
	public Message saveQtnReportHeader(@RequestBody CommonManageReq<QtnReportHeader> req) {
		logger.info("Add saveQtnReportHeader");
		return qtnReportHeaderService.saveQtnReportHeader(req);
	}
	
	@DeleteMapping("/delete_qtn_report_header/{id}")
	@ResponseBody
	public Message deleteQtnReportHeader(@PathVariable("id") String id) {
		return qtnReportHeaderService.deleteQtnReportHeader(id);
	}
	
	@PostMapping("/qtn_report_header_by_master_id/datatable")
	@ResponseBody
	public ResponseDataTable<Int022Vo> qtnReportHeaderByMasterId(DataTableRequest dataTableRequest) {
		logger.info("qtn_report_header_by_master_id");
		return qtnReportHeaderService.findForNullDatatable(dataTableRequest);
	}

	@PostMapping("/qtn_report_header_by_master_id/datatable/{masterId}")
	@ResponseBody
	public ResponseDataTable<Int022Vo> qtnReportHeaderByMasterId(@PathVariable("masterId") String masterId, DataTableRequest dataTableRequest) {
		logger.info("qtn_report_header_by_master_id {}", masterId);
		QtnReportHeader qtn = new QtnReportHeader();
		qtn.setQtnMasterId(Long.parseLong(masterId));
		return qtnReportHeaderService.findByMasterIdForDatatable(qtn, dataTableRequest);
	}
	
	/*
	 * 
	 * CRUD TABLE `IA_QTN_REPORT_MAIN`
	 * 
	 * */
	@PostMapping("/save_qtn_report_detail")
	@ResponseBody
	public Message saveQtnReportDetail(@RequestBody CommonManageReq<Int023FormVo> vo) {
		logger.info("called saveQtnReportDetail");
		return qtnReportMainService.saveQtnReport(vo);
	}
	
	@PostMapping("/qtn_report_detail_by_hdr_id/datatable/{hdrId}")
	@ResponseBody
	public ResponseDataTable<Int023Vo> qtnReportDetailByHdrId(@PathVariable("hdrId") String hdrId, DataTableRequest dataTableRequest) {
		logger.info("qtn_report_detail_by_hdr_id {}", hdrId);
		QtnReportMain qtn = new QtnReportMain();
		qtn.setQtnReportHdrId(Long.parseLong(hdrId));
		return qtnReportMainService.findQtnReport(qtn, dataTableRequest);
	}
	
	@PostMapping("/deleteQtnReportHeaderByCriteria")
	@ResponseBody
	public Message deleteQtnReportHeaderByCriteria(QtnReportHeader qtnReportHeader) {
		logger.info("queryQtnReportHeaderByCriteria");
		Message message = null;
		qtnReportHeaderService.deleteQtnReportHeader(qtnReportHeader);
		message = ApplicationCache.getMessage("MSG_00002");
		return message;
	}

	@PostMapping("/createQuestionnaireDetail")
	@ResponseBody
	public Message createQuestionnaireDetail(@RequestBody Int023MappingVO int023MappingVO) {
		logger.info("createQuestionnaireDetail");
		System.out.println(int023MappingVO.toString());
		Message message = null;
		Integer insertCountRow = questionnaireDetailService.createQuestionnaireDetail(int023MappingVO);
		if (BeanUtils.isNotEmpty(insertCountRow) && insertCountRow.intValue() > 0) {
			message = ApplicationCache.getMessage("MSG_00002");
		} else {
			message = ApplicationCache.getMessage("MSG_00003");
		}
		return message;
	}
	
	@PostMapping("/queryQuestionnaireDetailByCriteria")
	@ResponseBody
	public ResponseDataTable<QuestionnaireDetail> queryQuestionnaireDetailByCriteria(DataTableRequest dataTableRequest) {
		logger.info("queryQuestionnaireDetailByCriteria");
		return questionnaireDetailService.findByCriteriaForDatatable(new QuestionnaireDetail(), dataTableRequest);
	}

}
