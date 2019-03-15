package th.go.excise.ims.ta.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ta.service.TaxAuditService;
import th.go.excise.ims.ta.vo.AuditCalendarCheckboxVo;
import th.go.excise.ims.ta.vo.AuditCalendarCriteriaFormVo;
import th.go.excise.ims.ta.vo.OutsidePlanFormVo;
import th.go.excise.ims.ta.vo.OutsidePlanVo;
import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetDtlVo;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;

@Controller
@RequestMapping("/api/ta/tax-audit")
public class TaxAuditController {

    private static final Logger logger = LoggerFactory.getLogger(TaxAuditController.class);

    @Autowired
    private TaxAuditService taxAuditService;

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

    //TODO outside-plan
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

}
