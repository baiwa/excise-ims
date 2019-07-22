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
import th.go.excise.ims.ia.service.Int0801Service;
import th.go.excise.ims.ia.vo.Int0801Tabs;
import th.go.excise.ims.ia.vo.Int090101CompareFormVo;
import th.go.excise.ims.ia.vo.Int090101Vo;
import th.go.excise.ims.ia.vo.SearchVo;

@Controller
@RequestMapping("/api/ia/int08/01")
public class Int0801Controller {
	private static final Logger logger = LoggerFactory.getLogger(Int0801Controller.class);

	@Autowired
	private Int0801Service int0801Service;

	@PostMapping("/search")
	@ResponseBody
	public ResponseData<List<Int0801Tabs>> findCompare(@RequestBody SearchVo request) {
		ResponseData<List<Int0801Tabs>> response = new ResponseData<List<Int0801Tabs>>();
		try {
			response.setData(int0801Service.search(request));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int090101Controller::findCompare ", e);
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}
