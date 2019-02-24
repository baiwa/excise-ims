package th.go.excise.ims.ta.controller;

import java.util.List;

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
import th.go.excise.ims.ta.persistence.entity.TaMasCondMainDtl;
import th.go.excise.ims.ta.persistence.entity.TaMasCondMainHdr;
import th.go.excise.ims.ta.service.MasterConditionMainService;
import th.go.excise.ims.ta.vo.ConditionMessageVo;
import th.go.excise.ims.ta.vo.MasterConditionMainHdrDtlVo;

@Controller
@RequestMapping("/api/ta/master-condition-main")
public class MasterConditionMainController {

    @Autowired
    private MasterConditionMainService masterConditionService;

    @PostMapping("/create")
    @ResponseBody
    public ResponseData<List<MasterConditionMainHdrDtlVo>> insertMaster(@RequestBody MasterConditionMainHdrDtlVo formVo) {
        ResponseData<List<MasterConditionMainHdrDtlVo>> responseData = new ResponseData<List<MasterConditionMainHdrDtlVo>>();
        try {
            masterConditionService.insertMaster(formVo);
            responseData.setData(null);
            responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
            responseData.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
            responseData.setStatus(RESPONSE_STATUS.FAILED);
        }
        return responseData;
    }

    @PostMapping("/update")
    @ResponseBody
    public ResponseData<List<MasterConditionMainHdrDtlVo>> updateMaster(@RequestBody MasterConditionMainHdrDtlVo formVo) {
        ResponseData<List<MasterConditionMainHdrDtlVo>> responseData = new ResponseData<List<MasterConditionMainHdrDtlVo>>();
        try {
            masterConditionService.updateMaster(formVo);
            responseData.setData(null);
            responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
            responseData.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
            responseData.setStatus(RESPONSE_STATUS.FAILED);
        }
        return responseData;
    }

    @PostMapping("/findbudgetyearhdr")
    @ResponseBody
    public ResponseData<TaMasCondMainHdr> findByBudgetYearHdr(@RequestBody TaMasCondMainHdr formVo) {
        ResponseData<TaMasCondMainHdr> responseData = new ResponseData<TaMasCondMainHdr>();
        try {
            responseData.setData(masterConditionService.findByBudgetYearHdr(formVo));
            responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
            responseData.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
            responseData.setStatus(RESPONSE_STATUS.FAILED);
        }
        return responseData;
    }

    @PostMapping("/findbudgetyeardtl")
    @ResponseBody
    public ResponseData<List<TaMasCondMainDtl>> findByBudgetYearDtl(@RequestBody TaMasCondMainDtl formVo) {
        ResponseData<List<TaMasCondMainDtl>> responseData = new ResponseData<List<TaMasCondMainDtl>>();
        try {
            responseData.setData(masterConditionService.findByBudgetYearDtl(formVo));
            responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
            responseData.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
            responseData.setStatus(RESPONSE_STATUS.FAILED);
        }
        return responseData;
    }

    @GetMapping("/find-allhdr")
    @ResponseBody
    public ResponseData<List<TaMasCondMainHdr>> findAllHdr() {
        ResponseData<List<TaMasCondMainHdr>> responseData = new ResponseData<List<TaMasCondMainHdr>>();
        try {
            responseData.setData(masterConditionService.findAllHdr());
            responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
            responseData.setStatus(RESPONSE_STATUS.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            responseData.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
            responseData.setStatus(RESPONSE_STATUS.FAILED);
        }
        return responseData;
    }

    @GetMapping("/condition-message")
    @ResponseBody
    public ResponseData<ConditionMessageVo> conditionMessage() {
        ResponseData<ConditionMessageVo> response = new ResponseData<>();
        try{
            response.setData(this.masterConditionService.conditionMessage());
            response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
            response.setStatus(RESPONSE_STATUS.SUCCESS);
        }catch (Exception e){
            response.setData(this.masterConditionService.conditionMessage());
            response.setStatus(RESPONSE_STATUS.FAILED);
        }
        return response;
    }

}
