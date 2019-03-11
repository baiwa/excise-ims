package th.go.excise.ims.ta.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.LabelValueBean;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ExciseDept;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetDtl;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetHdr;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondMainDtl;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;
import th.go.excise.ims.ta.service.DraftWorksheetService;
import th.go.excise.ims.ta.service.PlanWorkSheetSendService;
import th.go.excise.ims.ta.service.PlanWorksheetService;
import th.go.excise.ims.ta.service.WorksheetService;
import th.go.excise.ims.ta.util.TaxAuditUtils;
import th.go.excise.ims.ta.vo.*;

import java.util.List;

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
    public ResponseData<List<ExciseDept>> getAllTaSectorList() {
        logger.info("getAllTaSectorList");

        ResponseData<List<ExciseDept>> response = new ResponseData<>();
        List<ExciseDept> exciseSectorList = TaxAuditUtils.getExciseSectorList();
        if (!CollectionUtils.isEmpty(exciseSectorList)) {
            response.setData(exciseSectorList);
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } else {
            response.setMessage("Excise Sector List Not Found");
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
    public ResponseData<List<String>> findAllAnalysisNumber(@RequestBody TaxOperatorFormVo formVo) {
        ResponseData<List<String>> response = new ResponseData<>();

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
            e.printStackTrace();
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
            response.setData(draftWorksheetService.findAllDraftNumber(formVo));
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
    public ResponseData<List<PlanWorkSheetSendVo>> getPlanWorkSheetSend() {
        ResponseData<List<PlanWorkSheetSendVo>> responseData = new ResponseData<>();

        try {
            responseData.setData(planWorkSheetSendService.getPlanWorkSheetSend());
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
            e.printStackTrace();
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
            e.printStackTrace();
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

    // TODO Approval
    @PostMapping("/plan-selected-dtl-approve")
    @ResponseBody
    public DataTableAjax<PlanWorksheetDatatableVo> planDtlDatatableApprive(@RequestBody PlanWorksheetVo formVo) {
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
}
