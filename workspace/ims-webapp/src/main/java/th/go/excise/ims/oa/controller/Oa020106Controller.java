package th.go.excise.ims.oa.controller;

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
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.oa.service.Oa020106Service;
import th.go.excise.ims.oa.vo.Oa020106ButtonVo;

@Controller
@RequestMapping("api/oa/02/01/06")
public class Oa020106Controller {
	
	private static final Logger logger = LoggerFactory.getLogger(Oa020106Controller.class);
	
	@Autowired
	private Oa020106Service oa020106Service;
	
	@GetMapping("/detail/{id}")
	@ResponseBody
	public ResponseData<Oa020106ButtonVo> findButtonById(@PathVariable("id") String idStr) {
		ResponseData<Oa020106ButtonVo> responseData = new ResponseData<Oa020106ButtonVo>();
		Oa020106ButtonVo data = new Oa020106ButtonVo();
		try {
			data = oa020106Service.findButtonById(idStr);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa020106Controller::findButtonById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

}
