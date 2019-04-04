package th.go.excise.ims.ia.controller;

import java.util.ArrayList;
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
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.persistence.entity.IaChartOfAcc;
import th.go.excise.ims.ia.service.Int12040101Service;

@Controller
@RequestMapping("/api/ia/int02/04/01/01")
public class Int12040101Controller {
	@Autowired
	private Int12040101Service int12040101Service;
	
	private static final Logger logger = LoggerFactory.getLogger(Int12040101Service.class);
	
	@GetMapping("/getChartOfAcc")
	@ResponseBody
	public ResponseData<List<IaChartOfAcc>> findAllQtnSide() {
		ResponseData<List<IaChartOfAcc>> responseData = new ResponseData<List<IaChartOfAcc>>();
		List<IaChartOfAcc> data = new ArrayList<>();
		try {
			data = int12040101Service.findAll();
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Int020101Controller::findAllQtnSide ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
}
