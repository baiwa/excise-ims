package th.go.excise.ims.ta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ta.service.TaxOperatorService;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.TaxOperatorVo;
import th.go.excise.ims.ta.vo.YearMonthVo;

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
			response.setMessage("SUCCESS");
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage("FAIL");
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
			response.setMessage("SUCCESS");
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage("FAIL");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}
