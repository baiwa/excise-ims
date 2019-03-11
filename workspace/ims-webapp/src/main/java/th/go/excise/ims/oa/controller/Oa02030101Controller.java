package th.go.excise.ims.oa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ia.service.Int030101Service;
import th.go.excise.ims.ia.vo.Int02010101FormVo;
import th.go.excise.ims.oa.service.Oa020301Service;
import th.go.excise.ims.oa.vo.Oa020301FormVo;

@Controller
@RequestMapping("/api/oa/03/01")
public class Oa02030101Controller {

	private static final Logger logger = LoggerFactory.getLogger(Oa02030101Controller.class);

	@Autowired
	private Oa020301Service int030201Service;
 
	@GetMapping("/save/customerLicense")
	@ResponseBody 
	public ResponseData<Oa020301FormVo> saveAll(@RequestBody Oa020301FormVo form) {
		ResponseData<Oa020301FormVo> responseData = new ResponseData<>();
		Oa020301FormVo data = new Oa020301FormVo();
		try {   
			  
//			form = new Oa020301FormVo();
			data = int030201Service.saveCustomer(form);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
			logger.error("Oa020301Controller::saveAll ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		
		
		return responseData;
	}
 
}
