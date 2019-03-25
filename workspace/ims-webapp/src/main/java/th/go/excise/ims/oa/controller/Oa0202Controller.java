package th.go.excise.ims.oa.controller;

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
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.oa.service.Oa0202Service;
import th.go.excise.ims.oa.vo.Oa0202Vo;

@Controller
@RequestMapping("/api/oa/02/02")
public class Oa0202Controller {

	private static final Logger logger = LoggerFactory.getLogger(Oa0207Controller.class);

	@Autowired
	private Oa0202Service oa0202Service;

	@GetMapping("/findAll/{addDate}")
	@ResponseBody
	public ResponseData<List<Oa0202Vo>> findAll(@PathVariable("addDate") int addDate) {
		ResponseData<List<Oa0202Vo>> responseData = new ResponseData<List<Oa0202Vo>>();
		List<Oa0202Vo> data = new ArrayList<Oa0202Vo>();
		try {
			String offCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			data = oa0202Service.findAll(offCode, addDate);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa0202Controller::findAll ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
}
