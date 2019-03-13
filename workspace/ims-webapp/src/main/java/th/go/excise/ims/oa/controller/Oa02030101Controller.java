package th.go.excise.ims.oa.controller;

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
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.oa.service.Oa02030101Service;
import th.go.excise.ims.oa.vo.Oa02030101FormVo;

@Controller
@RequestMapping("/api/oa/02/03/01/01")
public class Oa02030101Controller {

	private static final Logger logger = LoggerFactory.getLogger(Oa02030101Controller.class);

	@Autowired
	private Oa02030101Service oa02030101Service;
 
	@PostMapping("/save/customerLicense")
	@ResponseBody 
	public ResponseData<Oa02030101FormVo> saveAll(@RequestBody Oa02030101FormVo form) {
		ResponseData<Oa02030101FormVo> responseData = new ResponseData<>();
		Oa02030101FormVo data = new Oa02030101FormVo();
		try {
			String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			form.setOffCode(officeCode);
			data = oa02030101Service.saveCustomerLicenAll(form);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
			logger.error("Oa02030101Controller::saveAll ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
 
}
