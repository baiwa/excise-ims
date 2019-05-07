package th.go.excise.ims.ia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.service.Int091201Service;
import th.go.excise.ims.ia.vo.Int091201Vo;

@Controller
@RequestMapping("/api/ia/int09/12/01")
public class Int091201Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Int091201Controller.class);
	
	@Autowired
	private Int091201Service int091201Service;
	
	@GetMapping("/getList")
	@ResponseBody
	public ResponseData<List<Int091201Vo>> getList() {
		ResponseData<List<Int091201Vo>> responseData = new ResponseData<List<Int091201Vo>>();
		try {
			responseData.setData(int091201Service.getList());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
			responseData.setMessage(RESPONSE_MESSAGE.SUCCESS);
		} catch (Exception e) {
			logger.error("Int091201Controller::getList => ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return responseData;
	}
}
