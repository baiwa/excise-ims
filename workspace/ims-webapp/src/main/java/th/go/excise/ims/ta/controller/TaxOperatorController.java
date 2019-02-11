package th.go.excise.ims.ta.controller;

import java.util.ArrayList;
import java.util.List;

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

@Controller
@RequestMapping("/api/tax-operator")
public class TaxOperatorController {

	@Autowired
	private TaxOperatorService taxOperatorService;

	@PostMapping("/")
	@ResponseBody
	public ResponseData<List<TaxOperatorVo>> getOperator(@RequestBody TaxOperatorFormVo formVo) {

		ResponseData<List<TaxOperatorVo>> response = new ResponseData<>();

		try {
			response.setData(this.taxOperatorService.getOperator(formVo));
			response.setMessage("SUCCESS");
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setData(new ArrayList<>());
			response.setMessage("FAIL");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}
