package th.go.excise.ims.ia.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.service.Int090101Service;
import th.go.excise.ims.ia.service.Int12040101Service;
import th.go.excise.ims.ia.vo.Int090101CompareFormVo;
import th.go.excise.ims.ia.vo.Int090101Vo;

@Controller
@RequestMapping("/api/ia/int09/01/01")
public class Int090101Controller {

	@Autowired
	private Int090101Service int090101Service;

	private static final Logger logger = LoggerFactory.getLogger(Int12040101Service.class);

	@PostMapping("/find-compare")
	@ResponseBody
	public ResponseData<List<Int090101Vo>> findCompare(@RequestBody Int090101CompareFormVo form) {
		ResponseData<List<Int090101Vo>> responseData = new ResponseData<List<Int090101Vo>>();
		List<Int090101Vo> data = new ArrayList<Int090101Vo>();
		try {
			data = int090101Service.findCompare(form);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int090101Controller::findCompare ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
}
