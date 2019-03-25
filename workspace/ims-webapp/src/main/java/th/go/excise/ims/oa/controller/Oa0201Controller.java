package th.go.excise.ims.oa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.oa.persistence.entity.OaLicensePlan;
import th.go.excise.ims.oa.service.Oa020102Service;
import th.go.excise.ims.oa.service.Oa0201Service;
import th.go.excise.ims.oa.vo.Oa0201FromVo;

@Controller
@RequestMapping("api/oa/02/01")
public class Oa0201Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Oa0201Controller.class);
	
	@Autowired
	private Oa0201Service oa0201Service;
	
	@PutMapping("/save")
	@ResponseBody
	public ResponseData<Oa0201FromVo> updateById(@RequestBody Oa0201FromVo request) {
		ResponseData<Oa0201FromVo> responseData = new ResponseData<Oa0201FromVo>();
		Oa0201FromVo data = new Oa0201FromVo();
		try {
			data = oa0201Service.saveOAPlan(request);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
			logger.error("Oa02010608Controller::updateById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	

}
