package th.go.excise.ims.ia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.service.Int12070102Service;
import th.go.excise.ims.ia.vo.HospitalVo;

@Controller
@RequestMapping("/api/ia/int12/07/01/02")
public class Int12070102Controller {

	private Logger logger = LoggerFactory.getLogger(Int12070102Controller.class);

	@Autowired
	private Int12070102Service int12070102Service;
	
	@GetMapping("/hospital")
	@ResponseBody
	public ResponseData<List<HospitalVo>> getHospital() {

		ResponseData<List<HospitalVo>> response = new ResponseData<>();
		try {
			response.setData(int12070102Service.getHospital());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("getHospital", e.getMessage());
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}
}
