package th.go.excise.ims.ta.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ta.service.RecordMessageService;
import th.go.excise.ims.ta.service.TaxAuditService;
import th.go.excise.ims.ta.vo.AuditCalendarCheckboxVo;
import th.go.excise.ims.ta.vo.AuditCalendarCriteriaFormVo;
import th.go.excise.ims.ta.vo.FactoryVo;
import th.go.excise.ims.ta.vo.FormDocTypeVo;
import th.go.excise.ims.ta.vo.OutsidePlanFormVo;
import th.go.excise.ims.ta.vo.OutsidePlanVo;
import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetDtlVo;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;
import th.go.excise.ims.ta.vo.WsRegfri4000FormVo;

@Controller
@RequestMapping("/api/ta/tax-audit")
public class TaxAuditController {

	private static final Logger logger = LoggerFactory.getLogger(TaxAuditController.class);

	@Autowired
	private TaxAuditService taxAuditService;

	@Autowired
	private RecordMessageService recordMessageService;

	@PostMapping("/get-operator-details")
	@ResponseBody
	public ResponseData<WsRegfri4000FormVo> getOperatorDetails(@RequestBody WsRegfri4000FormVo wsRegfri4000FormVo) {
		logger.info("getOperatorDetails newRegId={}", wsRegfri4000FormVo.getNewRegId());

		ResponseData<WsRegfri4000FormVo> response = new ResponseData<>();
		try {
			WsRegfri4000FormVo formVo = taxAuditService.getOperatorDetails(wsRegfri4000FormVo);
			response.setData(formVo);
			response.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
		} catch (Exception e) {
			response.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
			response.setMessage(e.getMessage());
		}

		return response;
	}

	@PostMapping("/get-plan-dtl")
	@ResponseBody
	public DataTableAjax<PlanWorksheetDatatableVo> getPlanWorksheetDtl(@RequestBody PlanWorksheetVo formVo) {
		formVo.setOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
		return taxAuditService.getPlanWorksheetDtl(formVo);
	}

	@PostMapping("/get-audit-type")
	@ResponseBody
	public ResponseData<List<?>> getAuditType(@RequestBody AuditCalendarCheckboxVo form) {
		ResponseData<List<?>> res = new ResponseData<>();
		try {
			res.setData(taxAuditService.getAuditType(form));
			res.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			res.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			res.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			res.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
		}
		return res;
	}

	@PostMapping("/get-audit-status")
	@ResponseBody
	public ResponseData<List<?>> getAuditStatus(@RequestBody AuditCalendarCheckboxVo form) {
		ResponseData<List<?>> res = new ResponseData<>();
		try {
			res.setData(taxAuditService.getAuditStatus(form));
			res.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			res.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			res.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			res.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
		}
		return res;
	}

	// TODO outside-plan
	@PostMapping("/outside-plan")
	@ResponseBody
	public DataTableAjax<OutsidePlanVo> outsidePlan(@RequestBody OutsidePlanFormVo formVo) {
		return taxAuditService.outsidePlan(formVo);
	}

	@PostMapping("/get-plan-ws-dtl")
	@ResponseBody
	public ResponseData<List<PlanWorksheetDtlVo>> getPlanWsDtl(@RequestBody AuditCalendarCriteriaFormVo formVo) {
		ResponseData<List<PlanWorksheetDtlVo>> res = new ResponseData<>();
		try {
			res.setData(taxAuditService.getPlanWsDtl(formVo));
			res.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			res.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			res.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			res.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
		}
		return res;
	}

	@GetMapping("/find-by-new-reg-id/{id}")
	@ResponseBody
	public ResponseData<FactoryVo> getFactoryByNewRegId(@PathVariable("id") String idStr) {
		ResponseData<FactoryVo> responseData = new ResponseData<FactoryVo>();
		try {
			responseData.setData(taxAuditService.getFactoryByNewRegId(idStr));
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("TaxAuditController::getFactoryByNewRegId ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@GetMapping("/get-doc-type")
	@ResponseBody
	public ResponseData<List<FormDocTypeVo>> getTypeDoc() {
		ResponseData<List<FormDocTypeVo>> responseData = new ResponseData<List<FormDocTypeVo>>();
		try {
			responseData.setData(recordMessageService.getTypeDoc());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("TaxAuditController::getFactoryByNewRegId ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@PostMapping("/update-plan-ws-dtl")
	@ResponseBody
	public ResponseData<List<PlanWorksheetDtlVo>> savePlanWsDtl(@RequestBody PlanWorksheetDtlVo formVo) {
		ResponseData<List<PlanWorksheetDtlVo>> res = new ResponseData<>();
		try {
			taxAuditService.savePlanWsDtl(formVo);
			res.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			res.setStatus(ProjectConstant.RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			res.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			res.setStatus(ProjectConstant.RESPONSE_STATUS.FAILED);
		}
		return res;
	}

}
