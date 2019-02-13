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
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ta.persistence.entity.TaMasCondDtlTax;
import th.go.excise.ims.ta.persistence.entity.TaMasCondHdr;
import th.go.excise.ims.ta.service.MasterConditionService;
import th.go.excise.ims.ta.vo.MasterConditionHdrDtlVo;

@Controller
@RequestMapping("/api/ta/master-condition")
public class MasterConditionController {

	@Autowired
	MasterConditionService masterConditionService;

	@PostMapping("/create-master-condition")
	@ResponseBody
	public ResponseData<List<MasterConditionHdrDtlVo>> insertMaster(@RequestBody MasterConditionHdrDtlVo formVo) {
		ResponseData<List<MasterConditionHdrDtlVo>> responseData = new ResponseData<List<MasterConditionHdrDtlVo>>();
		try {
			masterConditionService.insertMaster(formVo);
			responseData.setData(null);
			responseData.setMessage("SUCCESS");
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage("ERROR");
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/update-master-condition")
	@ResponseBody
	public ResponseData<List<MasterConditionHdrDtlVo>> updateWorkSheet(@RequestBody MasterConditionHdrDtlVo formVo) {
		ResponseData<List<MasterConditionHdrDtlVo>> responseData = new ResponseData<List<MasterConditionHdrDtlVo>>();
		try {
			masterConditionService.updateWorkSheet(formVo);
			responseData.setData(null);
			responseData.setMessage("SUCCESS");
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage("ERROR");
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/findbudgetyearhdr")
	@ResponseBody
	public ResponseData<TaMasCondHdr> findByBudgetYearHdr(@RequestBody TaMasCondHdr formVo) {
		ResponseData<TaMasCondHdr> responseData = new ResponseData<TaMasCondHdr>();
		try {
			responseData.setData(masterConditionService.findByBudgetYearHdr(formVo));
			responseData.setMessage("SUCCESS");
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage("ERROR");
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/findbudgetyeardtl")
	@ResponseBody
	public ResponseData<List<TaMasCondDtlTax>> findByBudgetYearDtl(@RequestBody TaMasCondDtlTax formVo) {
		ResponseData<List<TaMasCondDtlTax>> responseData = new ResponseData<List<TaMasCondDtlTax>>();
		try {
			responseData.setData(masterConditionService.findByBudgetYearDtl(formVo));
			responseData.setMessage("SUCCESS");
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage("ERROR");
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@GetMapping("/find-allhdr")
	@ResponseBody
	public ResponseData<List<TaMasCondHdr>> findAllHdr() {
		ResponseData<List<TaMasCondHdr>> responseData = new ResponseData<List<TaMasCondHdr>>();
		try {
			responseData.setData(masterConditionService.findAllHdr());
			responseData.setMessage("SUCCESS");
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			responseData.setMessage("ERROR");
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
