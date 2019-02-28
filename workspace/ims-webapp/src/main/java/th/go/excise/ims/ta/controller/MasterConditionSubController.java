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
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.ta.persistence.entity.TaMasCondSubCapital;
import th.go.excise.ims.ta.persistence.entity.TaMasCondSubNoAudit;
import th.go.excise.ims.ta.persistence.entity.TaMasCondSubRisk;
import th.go.excise.ims.ta.service.MasterConditionSubService;
import th.go.excise.ims.ta.vo.MasCondSubCapitalFormVo;
import th.go.excise.ims.ta.vo.MasCondSubRiskFormVo;

@Controller
@RequestMapping("/api/ta/master-condition-sub")
public class MasterConditionSubController {

	private static final Logger logger = LoggerFactory.getLogger(MasterConditionSubController.class);
	
	@Autowired
	private MasterConditionSubService masterConditionSubService;

	@GetMapping("/get-product-service-type")
	@ResponseBody
	public ResponseData<List<ParamInfo>> getProductTypeAndServiceType() {
		ResponseData<List<ParamInfo>> response = new ResponseData<List<ParamInfo>>();
		try {
			response.setData(masterConditionSubService.getProductTypeAndServiceType());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(
					ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/insert-capital")
	@ResponseBody
	public ResponseData<MasCondSubCapitalFormVo> insertCapital(
			@RequestBody MasCondSubCapitalFormVo form) {
		ResponseData<MasCondSubCapitalFormVo> response = new ResponseData<>();
		try {
			masterConditionSubService.insertCapital(form);
			response.setMessage(
					ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(
					ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/get-capital-by-dutycode")
	@ResponseBody
	public ResponseData<TaMasCondSubCapital> getCapital(
			@RequestBody TaMasCondSubCapital form) {
		ResponseData<TaMasCondSubCapital> response = new ResponseData<>();
		try {
			response.setData(masterConditionSubService.getCapitalByDutyCode(form));
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(
					ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/insert-risk")
	@ResponseBody
	public ResponseData<MasCondSubRiskFormVo> insertCapital(@RequestBody MasCondSubRiskFormVo form) {
		ResponseData<MasCondSubRiskFormVo> response = new ResponseData<>();
		try {
			masterConditionSubService.insertRisk(form);
			response.setMessage(
					ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(
					ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/get-risk")
	@ResponseBody
	public ResponseData<List<TaMasCondSubRisk>> getRiskByBudgetYear(
			@RequestBody MasCondSubRiskFormVo form) {
		ResponseData<List<TaMasCondSubRisk>> response = new ResponseData<>();
		try {
			response.setData(masterConditionSubService.getRiskByBudgetYear(form));
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(
					ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/insert-noaudit")
	@ResponseBody
	public ResponseData<TaMasCondSubNoAudit> insertNoAudit(@RequestBody TaMasCondSubNoAudit form) {
		ResponseData<TaMasCondSubNoAudit> response = new ResponseData<>();
		try {
			masterConditionSubService.insertNoAudit(form);
			response.setMessage(
					ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(
					ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/get-noaudit")
	@ResponseBody
	public ResponseData<TaMasCondSubNoAudit> getNoAuditByBudgetYear(
			@RequestBody TaMasCondSubNoAudit form) {
		ResponseData<TaMasCondSubNoAudit> response = new ResponseData<>();
		try {
			response.setData(masterConditionSubService.getNoAuditByBudgetYear(form));
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(
					ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	

}
