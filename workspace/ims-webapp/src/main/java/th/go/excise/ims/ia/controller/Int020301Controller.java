package th.go.excise.ims.ia.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.service.Int020301Service;
import th.go.excise.ims.ia.vo.Int020301HeaderVo;
import th.go.excise.ims.ia.vo.Int020301InfoVo;

@Controller
@RequestMapping("/api/ia/int02/03/01")
public class Int020301Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Int020301Controller.class);

	@Autowired
	private Int020301Service int020301Service;
	
	@GetMapping("/header/{idSide}/{budgetYear}")
	@ResponseBody
	public ResponseData<List<Int020301HeaderVo>> findHeaderByIdHead(@PathVariable("idSide") String idSideStr, @PathVariable("budgetYear") String budgetYear) {
		ResponseData<List<Int020301HeaderVo>> responseData = new ResponseData<List<Int020301HeaderVo>>();
		List<Int020301HeaderVo> data = new ArrayList<>();
		try {
			data = int020301Service.findHeaderByIdSide(idSideStr, budgetYear);
			responseData.setData(data);
			responseData.setMessage("SUCCESS");
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int0203Controller::findByIdHead ", e);
			responseData.setMessage(RESPONSE_MESSAGE.ERROR500);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/info/{idSide}/{budgetYear}")
	@ResponseBody
	public ResponseData<List<Int020301InfoVo>> findInfoByIdHead(@PathVariable("idSide") String idSideStr, @PathVariable("budgetYear") String budgetYear) {
		ResponseData<List<Int020301InfoVo>> responseData = new ResponseData<List<Int020301InfoVo>>();
		List<Int020301InfoVo> data = new ArrayList<>();
		try {
			data = int020301Service.findInfoByIdSide(idSideStr, budgetYear);
			responseData.setData(data);
			responseData.setMessage("SUCCESS");
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int0203Controller::findByIdHead ", e);
			responseData.setMessage(RESPONSE_MESSAGE.ERROR500);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
}
