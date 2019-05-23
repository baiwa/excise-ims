package th.go.excise.ims.ta.controller;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.LabelValueBean;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.security.domain.UserBean;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.constant.ProjectConstants;
import th.go.excise.ims.common.constant.ProjectConstants.EXCISE_OFFICE_CODE;
import th.go.excise.ims.common.constant.ProjectConstants.EXCISE_SUBDEPT_LEVEL;
import th.go.excise.ims.common.constant.ProjectConstants.TA_AUDIT_STATUS;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.preferences.persistence.entity.ExcisePerson;
import th.go.excise.ims.preferences.vo.ExciseDepartment;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetDtl;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetHdr;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondMainDtl;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;
import th.go.excise.ims.ta.service.DraftWorksheetService;
import th.go.excise.ims.ta.service.PlanWorkSheetSendService;
import th.go.excise.ims.ta.service.PlanWorksheetService;
import th.go.excise.ims.ta.service.WorksheetService;
import th.go.excise.ims.ta.util.TaxAuditUtils;
import th.go.excise.ims.ta.vo.CondGroupVo;
import th.go.excise.ims.ta.vo.PersonAssignForm;
import th.go.excise.ims.ta.vo.PlanWorkSheetSendVo;
import th.go.excise.ims.ta.vo.PlanWorksheetAssignVo;
import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetDtlCusVo;
import th.go.excise.ims.ta.vo.PlanWorksheetSendTableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetStatus;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.TaxOperatorVo;
import th.go.excise.ims.ta.vo.YearMonthVo;

@Controller
@RequestMapping("/api/ta/tax-operator")
public class TaxOperatorController {

	private static final Logger logger = LoggerFactory.getLogger(TaxOperatorController.class);

	@Autowired
	private DraftWorksheetService draftWorksheetService;

	@Autowired
	private WorksheetService worksheetService;

	@Autowired
	private PlanWorksheetService planWorksheetService;
	@Autowired
	private PlanWorkSheetSendService planWorkSheetSendService;

	// TODO Common
	@PostMapping("/sector-list")
	@ResponseBody
	public ResponseData<List<ExciseDepartment>> getAllTaSectorList() {
		logger.info("getAllTaSectorList");

		ResponseData<List<ExciseDepartment>> response = new ResponseData<>();
		List<ExciseDepartment> exciseSectorList = TaxAuditUtils.getExciseSectorList();
		if (!CollectionUtils.isEmpty(exciseSectorList)) {
			response.setData(exciseSectorList);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} else {
			response.setMessage("Excise Sector List Not Found");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

	// TODO DRAFT
	@PostMapping("/preview-data")
	@ResponseBody
	public ResponseData<TaxOperatorVo> previewData(@RequestBody TaxOperatorFormVo formVo) {
		ResponseData<TaxOperatorVo> response = new ResponseData<>();

		try {
			response.setData(draftWorksheetService.getPreviewData(formVo));
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

	@PostMapping("/get-month-start-draft")
	@ResponseBody
	public ResponseData<YearMonthVo> getMonthStartDraft(@RequestBody TaxOperatorFormVo formVo) {
		ResponseData<YearMonthVo> response = new ResponseData<>();

		try {
			response.setData(draftWorksheetService.getMonthStart(formVo));
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

	@PostMapping("/draft")
	@ResponseBody
	public ResponseData<TaxOperatorVo> getOperatorDraft(@RequestBody TaxOperatorFormVo formVo) {
		ResponseData<TaxOperatorVo> response = new ResponseData<>();

		try {
			response.setData(draftWorksheetService.getDraftWorksheet(formVo));
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

	@PostMapping("/find-all-analysis-number-draft")
	@ResponseBody
	public ResponseData<List<String>> findAllDraftNumber(@RequestBody TaxOperatorFormVo formVo) {
		ResponseData<List<String>> response = new ResponseData<>();

		try {
			response.setData(draftWorksheetService.findAllAnalysisNumber(formVo));
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
	public ResponseData<?> saveDraft(@RequestBody TaxOperatorFormVo formVo) {
		ResponseData<YearMonthVo> response = new ResponseData<>();

		try {
			draftWorksheetService.saveDraftWorksheet(formVo);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

	@PostMapping("/get-worksheet-cond-dtl")
	@ResponseBody
	public ResponseData<List<TaWorksheetCondMainDtl>> worksheetCondMainDtls(@RequestBody TaxOperatorFormVo formVo) {
		ResponseData<List<TaWorksheetCondMainDtl>> response = new ResponseData<>();

		try {
			response.setData(worksheetService.worksheetCondMainDtls(formVo));
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

	@PostMapping("/check-evaluate-condition")
	@ResponseBody
	public ResponseData<TaWorksheetHdr> checkEvaluateCondition(@RequestBody TaxOperatorFormVo formVo) {
		ResponseData<TaWorksheetHdr> response = new ResponseData<>();

		try {
			response.setData(worksheetService.checkEvaluateCondition(formVo));
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

	// TODO worksheet header
	@PostMapping("/")
	@ResponseBody
	public ResponseData<TaxOperatorVo> getOperator(@RequestBody TaxOperatorFormVo formVo) {
		ResponseData<TaxOperatorVo> response = new ResponseData<>();

		try {
			response.setData(worksheetService.getWorksheet(formVo));
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

	@PostMapping("/group-cond-sub-capital")
	@ResponseBody
	public ResponseData<List<LabelValueBean>> groupCondSubCapital(@RequestBody TaxOperatorFormVo formVo) {
		ResponseData<List<LabelValueBean>> response = new ResponseData<>();

		try {
			response.setData(worksheetService.groupCondSubCapital(formVo.getAnalysisNumber()));
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

	@PostMapping("/group-cond-sub-risk")
	@ResponseBody
	public ResponseData<List<LabelValueBean>> groupCondSubRisk(@RequestBody TaxOperatorFormVo formVo) {
		ResponseData<List<LabelValueBean>> response = new ResponseData<>();

		try {
			response.setData(worksheetService.groupCondSubRisk(formVo.getAnalysisNumber()));
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

	@PostMapping("/find-all-analysis-number")
	@ResponseBody
	public ResponseData<List<TaWorksheetHdr>> findAllAnalysisNumber(@RequestBody TaxOperatorFormVo formVo) {
		ResponseData<List<TaWorksheetHdr>> response = new ResponseData<>();

		try {
			response.setData(worksheetService.findAllAnalysisNumber(formVo));
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
			response.setData(worksheetService.findCondGroupDtl(formVo));
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
			response.setData(worksheetService.getMonthStart(formVo));
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

	@PostMapping("/prepare-tax-grouping") // ==> SaveWorksheet
	@ResponseBody
	public ResponseData<?> prepareTaxGrouping(@RequestBody TaxOperatorFormVo formVo) {
		ResponseData<List<CondGroupVo>> response = new ResponseData<>();

		try {
			worksheetService.saveWorksheet(formVo.getDraftNumber(), formVo.getBudgetYear());
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	// TODO PLAN
	@PostMapping("/save-plan-work-sheet-hdr")
	@ResponseBody
	public ResponseData<?> savePlanWorkSheetHdr(@RequestBody PlanWorksheetVo formVo) {
		ResponseData<?> response = new ResponseData<>();

		try {
			planWorksheetService.savePlanWorksheetHdr(formVo);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

	@PostMapping("/find-one-budget-plan-header")
	@ResponseBody
	public ResponseData<TaPlanWorksheetHdr> CheckBudgetPlanHeader(@RequestBody PlanWorksheetVo formVo) {
		ResponseData<TaPlanWorksheetHdr> response = new ResponseData<>();

		try {
			response.setData(planWorksheetService.getPlanWorksheetHdr(formVo));
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

	@PostMapping("/save-plan-work-sheet-dtl")
	@ResponseBody
	public ResponseData<?> savePlanWorkSheetDtl(@RequestBody PlanWorksheetVo formVo) {
		ResponseData<?> response = new ResponseData<>();

		try {
			planWorksheetService.savePlanWorksheetDtl(formVo);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}
	
	@PostMapping("/save-plan-work-sheet-dtl-assing")
	@ResponseBody
	public ResponseData<?> savePlanWorkSheetDtlAndAssing(@RequestBody PlanWorksheetAssignVo formVo) {
		ResponseData<?> response = new ResponseData<>();

		try {
			planWorksheetService.savePlanWorksheetDtlAndAssign(formVo);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}


	@PostMapping("/find-plan-worksheet-dtl")
	@ResponseBody
	public ResponseData<List<TaPlanWorksheetDtl>> findPlanWorkSheetDtl(@RequestBody PlanWorksheetVo formVo) {
		ResponseData<List<TaPlanWorksheetDtl>> response = new ResponseData<>();

		try {
			response.setData(planWorksheetService.findPlanWorksheetDtl(formVo));
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

	@PostMapping("/get-plan-ws-send")
	@ResponseBody
	public ResponseData<List<PlanWorkSheetSendVo>> getPlanWorkSheetSend(@RequestBody PlanWorksheetVo formVo) {
		ResponseData<List<PlanWorkSheetSendVo>> responseData = new ResponseData<>();

		try {
			responseData.setData(planWorkSheetSendService.getPlanWorkSheetSend(formVo));
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}

		return responseData;
	}

	@PostMapping("/plan-selected-dtl")
	@ResponseBody
	public DataTableAjax<PlanWorksheetDatatableVo> planDtlDatatable(@RequestBody PlanWorksheetVo formVo) {
		UserBean userBean = UserLoginUtils.getCurrentUserBean();
		// Assign OfficeCode
		if (StringUtils.isEmpty(formVo.getOfficeCode())) {
			String officeCode = userBean.getOfficeCode();
			if (EXCISE_OFFICE_CODE.TA_CENTRAL_SELECTOR.equals(officeCode) || EXCISE_OFFICE_CODE.TA_CENTRAL_OPERATOR1.equals(officeCode) || EXCISE_OFFICE_CODE.TA_CENTRAL_OPERATOR2.equals(officeCode)) {
				formVo.setOfficeCode(officeCode);
			} else {
				formVo.setOfficeCode(ExciseUtils.whereInLocalOfficeCode(officeCode));
			}
		}
		// If User have SubdeptLevel = "3" Then Assign SubdeptLevel, Else Assign
		// SubdeptCode
		if (EXCISE_SUBDEPT_LEVEL.LV3.equals(userBean.getSubdeptLevel())) {
			formVo.setUserLoginId(userBean.getUsername());
		} else {
			formVo.setSubdeptCode(userBean.getSubdeptCode());
		}
		return planWorksheetService.planDtlDatatable(formVo);
	}

	@PostMapping("/check-submit-date-plan-worksheet-send")
	@ResponseBody
	public ResponseData<Boolean> checkSubmitDatePlanWorksheetSend(@RequestBody PlanWorksheetVo formVo) {

		ResponseData<Boolean> responseData = new ResponseData<>();

		try {
			responseData.setData(planWorksheetService.checkSubmitDatePlanWorksheetSend(formVo));
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}

		return responseData;
	}

	@DeleteMapping("/delete-plan-worksheet-dtl/{id}")
	@ResponseBody
	public ResponseData<?> deletePlanWorksheetDlt(@PathVariable("id") String id) {

		ResponseData<?> responseData = new ResponseData<>();

		try {
			planWorksheetService.deletePlanWorksheetDlt(id);
			responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}

		return responseData;
	}

	@PostMapping("/save-plan-worksheet-send")
	@ResponseBody
	public ResponseData<?> savePlanWorksheetSend(@RequestBody PlanWorksheetVo formVo) {

		ResponseData<?> responseData = new ResponseData<>();

		try {
			planWorksheetService.savePlanWorksheetSendToArea(formVo);
			responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}

		return responseData;
	}

	// TODO Approval
	@PostMapping("/plan-selected-dtl-central-approve")
	@ResponseBody
	public DataTableAjax<PlanWorksheetDatatableVo> planDtlDatatableCentralApprove(@RequestBody PlanWorksheetVo formVo) {
		UserBean userBean = UserLoginUtils.getCurrentUserBean();
		if(ExciseUtils.isCentral(userBean.getOfficeCode())) {
			formVo.setAuditStatus(TA_AUDIT_STATUS.CODE_0200);
		}
		if (StringUtils.isEmpty(formVo.getOfficeCode())) {
				formVo.setOfficeCode(ExciseUtils.whereInLocalOfficeCode(userBean.getOfficeCode()));
		}
		return planWorksheetService.planDtlDatatable(formVo);
	}

	@PostMapping("/plan-selected-dtl-approve")
	@ResponseBody
	public DataTableAjax<PlanWorksheetDatatableVo> planDtlDatatableApprove(@RequestBody PlanWorksheetVo formVo) {
		UserBean userBean = UserLoginUtils.getCurrentUserBean();
		// Assign OfficeCode
		if (StringUtils.isEmpty(formVo.getOfficeCode())) {
			String officeCode = userBean.getOfficeCode();
			if (EXCISE_OFFICE_CODE.TA_CENTRAL_SELECTOR.equals(officeCode) || EXCISE_OFFICE_CODE.TA_CENTRAL_OPERATOR1.equals(officeCode) || EXCISE_OFFICE_CODE.TA_CENTRAL_OPERATOR2.equals(officeCode)) {
				formVo.setOfficeCode(officeCode);
			} else {
				formVo.setOfficeCode(ExciseUtils.whereInLocalOfficeCode(officeCode));
			}
		}
		// If User have SubdeptLevel = "3" Then Assign SubdeptLevel, Else Assign
		// SubdeptCode
		if (EXCISE_SUBDEPT_LEVEL.LV3.equals(userBean.getSubdeptLevel())) {
			formVo.setUserLoginId(userBean.getUsername());
		} else {
			formVo.setSubdeptCode(userBean.getSubdeptCode());
		}
		return planWorksheetService.planDtlDatatable(formVo);
	}
	@PostMapping("/plan-selected-by-offcode")
	@ResponseBody
	public DataTableAjax<PlanWorksheetDatatableVo> planDtlByOffCode(@RequestBody PlanWorksheetVo formVo) {
		UserBean userBean = UserLoginUtils.getCurrentUserBean();
		formVo.setOfficeCode(userBean.getOfficeCode());
		if (EXCISE_SUBDEPT_LEVEL.LV3.equals(userBean.getSubdeptLevel())) {
			formVo.setUserLoginId(userBean.getUsername());
		} else {
			formVo.setSubdeptCode(userBean.getSubdeptCode());
		}
		return planWorksheetService.planDtlDatatable(formVo);
	}
	@PostMapping("/plan-selected-by-offcode-assign")
	@ResponseBody
	public DataTableAjax<PlanWorksheetDatatableVo> planDtlByOffCodeAssign(@RequestBody PlanWorksheetVo formVo) {
		UserBean userBean = UserLoginUtils.getCurrentUserBean();
		formVo.setOfficeCode(userBean.getOfficeCode());
//		if (EXCISE_SUBDEPT_LEVEL.LV3.equals(userBean.getSubdeptLevel())) {
//			formVo.setUserLoginId(userBean.getUsername());
//		} else {
//			formVo.setSubdeptCode(userBean.getSubdeptCode());
//		}
		return planWorksheetService.planDtlDatatable(formVo);
	}
	
	
	@PostMapping("/get-plan-status")
	@ResponseBody
	public ResponseData<PlanWorksheetStatus> getPlanStatus(@RequestBody PlanWorksheetVo formVo) {
		ResponseData<PlanWorksheetStatus> response = new ResponseData<>();

		try {
			response.setData(planWorksheetService.getPlanHeaderStatus(formVo));
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

	@PostMapping("/update-plan-comment")
	@ResponseBody
	public ResponseData<?> insertComment(@RequestBody TaPlanWorksheetHdr formVo) {
		ResponseData<?> response = new ResponseData<>();

		try {
			planWorksheetService.insertComment(formVo);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	// TODO Get WS_REG4000 findBy NEW_REG_ID
	@PostMapping("/find-by-newRegId")
	@ResponseBody
	public ResponseData<PlanWorksheetDtlCusVo> findByNewRegId(@RequestBody PlanWorksheetDtlCusVo formVo) {
		ResponseData<PlanWorksheetDtlCusVo> response = new ResponseData<>();

		try {
			response.setData(planWorksheetService.findByNewRegId(formVo));
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/update-plan-worksheetDtl")
	@ResponseBody
	public ResponseData<TaPlanWorksheetDtl> updatePlanWorksheetDtl(@RequestBody PlanWorksheetDatatableVo formVo) {
		ResponseData<TaPlanWorksheetDtl> response = new ResponseData<>();

		try {
			response.setData(planWorksheetService.savePlanWorksheetDtlByAssing(formVo));
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	
	@PostMapping("/update-plan-worksheetDtl-list")
	@ResponseBody
	public ResponseData<TaPlanWorksheetDtl> updateListPlanWorksheetDtl(@RequestBody PersonAssignForm formVo) {
		ResponseData<TaPlanWorksheetDtl> response = new ResponseData<>();

		try {
			planWorksheetService.savePlanWorksheetDtlByAssingList(formVo);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/update-receive-plan-worksheetDtl")
	@ResponseBody
	public ResponseData<TaPlanWorksheetDtl> updateReceivePlanWorksheetDtl(@RequestBody ExcisePerson formVo) {
		ResponseData<TaPlanWorksheetDtl> response = new ResponseData<>();

		try {
			planWorksheetService.saveStatusPlanWorksheetDtlByAssing(formVo, ProjectConstants.TA_AUDIT_STATUS.CODE_0301);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/update-assign-plan-worksheetDtl")
	@ResponseBody
	public ResponseData<TaPlanWorksheetDtl> updateAssignPlanWorksheetDtl(@RequestBody ExcisePerson formVo) {
		ResponseData<TaPlanWorksheetDtl> response = new ResponseData<>();

		try {
			planWorksheetService.saveStatusPlanWorksheetDtlByAssing(formVo, ProjectConstants.TA_AUDIT_STATUS.CODE_0400);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}


	
	@PostMapping("/find-all-analysis-number-head")
	@ResponseBody
	public ResponseData<List<TaWorksheetHdr>> findAllAnalysisNumberHead(@RequestBody TaxOperatorFormVo formVo) {
		ResponseData<List<TaWorksheetHdr>> response = new ResponseData<>();

		try {
			response.setData(worksheetService.findAllAnalysisNumberHead(formVo));
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

	@PostMapping("/get-plan-ws-send-approve")
	@ResponseBody
	public ResponseData<List<PlanWorksheetSendTableVo>> findPlanWSSendApprove(@RequestBody PlanWorksheetVo formVo) {
		ResponseData<List<PlanWorksheetSendTableVo>> responseData = new ResponseData<>();

		try {
			responseData.setData(planWorkSheetSendService.getPlanWorkSheetSendToArea(formVo));
			responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}

		return responseData;
	}

	@PostMapping("/save-plan-worksheet-send-approve")
	@ResponseBody
	public ResponseData<?> savePlanWorksheetSendApprove(@RequestBody PlanWorksheetVo formVo) {

		ResponseData<?> responseData = new ResponseData<>();

		try {
			planWorksheetService.savePlanWorksheetSend(formVo);
			responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			responseData.setMessage(ApplicationCache.getMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}

		return responseData;
	}
	@PostMapping("/worksheet-for-assing")
	@ResponseBody
	public ResponseData<TaxOperatorVo> getOperatorAssign(@RequestBody TaxOperatorFormVo formVo) {
		ResponseData<TaxOperatorVo> response = new ResponseData<>();

		try {
			response.setData(worksheetService.getWorksheetForAssign(formVo));
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}

}
