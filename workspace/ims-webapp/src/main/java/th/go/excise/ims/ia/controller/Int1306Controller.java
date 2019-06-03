package th.go.excise.ims.ia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.service.Int1306Service;
import th.go.excise.ims.ia.vo.Int1306FormVo;
import th.go.excise.ims.ia.vo.Int1306Vo;

@Controller
@RequestMapping("/api/ia/int13/06")
public class Int1306Controller {

	@Autowired
	private Int1306Service int1306Service;

	@PostMapping("/find-by-criteria")
	@ResponseBody
	public ResponseData<Int1306Vo> findByCriteria(@RequestBody Int1306FormVo request) {
		ResponseData<Int1306Vo> response = new ResponseData<Int1306Vo>();
		try {
			response.setData(int1306Service.findCriteria(request));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}
