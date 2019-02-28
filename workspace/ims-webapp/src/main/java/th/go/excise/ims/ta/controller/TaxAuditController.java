package th.go.excise.ims.ta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ta.service.TaxAuditService;
import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;

@Controller
@RequestMapping("/api/ta/tax-audit")
public class TaxAuditController {

    @Autowired
    private TaxAuditService taxAuditService;

    @PostMapping("/get-plan-dtl")
    @ResponseBody
    public  DataTableAjax<PlanWorksheetDatatableVo> getPlanWorksheetDtl(@RequestBody PlanWorksheetVo formVo){
        formVo.setOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
        return taxAuditService.getPlanWorksheetDtl(formVo);
    }
}
