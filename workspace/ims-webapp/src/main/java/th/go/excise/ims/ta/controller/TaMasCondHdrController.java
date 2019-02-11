package th.go.excise.ims.ta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ta.persistence.entity.TaMasCondDtlTax;
import th.go.excise.ims.ta.persistence.entity.TaMasCondHdr;
import th.go.excise.ims.ta.service.TaMasCondHdrService;
import th.go.excise.ims.ta.vo.TaMasCondHdrDtlVo;

@Controller
@RequestMapping("/api/tax-audit/create-work-sheet")
public class TaMasCondHdrController {
	
	@Autowired
	TaMasCondHdrService taMasCondHdrService;
	
	@PostMapping("/createworksheet")
	@ResponseBody
	public ResponseData<List<TaMasCondHdrDtlVo>> insertWorkSheet(@RequestBody TaMasCondHdrDtlVo formVo) {
		ResponseData<List<TaMasCondHdrDtlVo>> responseData = new ResponseData<List<TaMasCondHdrDtlVo>>();
		try {
			taMasCondHdrService.insertWorkSheet(formVo);
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
			responseData.setData(taMasCondHdrService.findByBudgetYearHdr(formVo));
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
			responseData.setData(taMasCondHdrService.findByBudgetYearDtl(formVo));
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
