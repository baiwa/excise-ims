package th.go.excise.ims.ta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import th.co.baiwa.buckwaframework.common.bean.BusinessException;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ta.service.TaxOperatorService;
import th.go.excise.ims.ta.vo.CondGroupVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.TaxOperatorVo;
import th.go.excise.ims.ta.vo.YearMonthVo;

import java.util.List;

@Controller
@RequestMapping("/api/ta/tax-operator")
public class TaxOperatorController {

    @Autowired
    private TaxOperatorService taxOperatorService;

    @PostMapping("/")
    @ResponseBody
    public ResponseData<TaxOperatorVo> getOperator(@RequestBody TaxOperatorFormVo formVo) {

        ResponseData<TaxOperatorVo> response = new ResponseData<>();

        try {
            response.setData(this.taxOperatorService.getOperator(formVo));
            response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
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
            response.setData(this.taxOperatorService.findAllAnalysisNumber());
            response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
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
            response.setData(this.taxOperatorService.findCondGroupDtl(formVo));
            response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
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
            response.setData(this.taxOperatorService.monthStart(formVo));
            response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }
}
