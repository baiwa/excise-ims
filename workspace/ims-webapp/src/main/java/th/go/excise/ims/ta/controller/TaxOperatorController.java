package th.go.excise.ims.ta.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ta.service.TaxAuditFactorySelectionService;
import th.go.excise.ims.ta.service.TaxOperatorService;
import th.go.excise.ims.ta.vo.CondGroupVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.TaxOperatorVo;
import th.go.excise.ims.ta.vo.YearMonthVo;

@Controller
@RequestMapping("/api/ta/tax-operator")
public class TaxOperatorController {

	private static final Logger logger = LoggerFactory.getLogger(TaxOperatorController.class);

	@Autowired
	private TaxOperatorService taxOperatorService;
	
	@Autowired
	private TaxAuditFactorySelectionService taxAuditFactorySelectionService;
	
	@PostMapping("/preview-data")
	@ResponseBody
	public ResponseData<TaxOperatorVo> previewData(@RequestBody TaxOperatorFormVo formVo) {
		ResponseData<TaxOperatorVo> response = new ResponseData<>();
		
		try {
			response.setData(taxAuditFactorySelectionService.getPreviewData(formVo));
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}

	@PostMapping("/")
	@ResponseBody
	public ResponseData<TaxOperatorVo> getOperator(@RequestBody TaxOperatorFormVo formVo) {
		ResponseData<TaxOperatorVo> response = new ResponseData<>();

		try {
			response.setData(taxOperatorService.getOperator(formVo));
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

	@GetMapping("/find-all-analysis-number")
	@ResponseBody
	public ResponseData<List<String>> findAllAnalysisNumber() {
		ResponseData<List<String>> response = new ResponseData<>();

		try {
			response.setData(taxOperatorService.findAllAnalysisNumber());
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@GetMapping("/find-all-analysis-number-draft")
	@ResponseBody
	public ResponseData<List<String>> findAllAnalysisNumberDraft() {
		ResponseData<List<String>> response = new ResponseData<>();

		try {
			response.setData(taxOperatorService.findAllAnalysisNumberDraft());
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}

	@PostMapping("/find-cond-group-dtl")
	@ResponseBody
	public ResponseData<List<CondGroupVo>> findCondGroupDtl(@RequestBody TaxOperatorFormVo formVo) {
		ResponseData<List<CondGroupVo>> response = new ResponseData<>();
		
		try {
			response.setData(taxOperatorService.findCondGroupDtl(formVo));
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}

	@PostMapping("/get-month-start")
	@ResponseBody
	public ResponseData<YearMonthVo> getMonthStart(@RequestBody TaxOperatorFormVo formVo) {
		ResponseData<YearMonthVo> response = new ResponseData<>();

		try {
			response.setData(taxOperatorService.monthStart(formVo));
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@PostMapping("/save-draft")
	@ResponseBody
	public ResponseData<?> saveDraft(@RequestBody TaxOperatorFormVo formVo){ 
		ResponseData<YearMonthVo> response = new ResponseData<>();

		try {
			this.taxOperatorService.saveDraft(formVo);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);	
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	
}
