package th.go.excise.ims.oa.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/find/customerLicenses/{customerId}/{licenseType}")
	@ResponseBody 
	public ResponseData<List<Oa02030101FormVo>> findAllName(@PathVariable("customerId") String customerIdStr, @PathVariable("licenseType") String licenseType) {
		ResponseData<List<Oa02030101FormVo>> responseData = new ResponseData<List<Oa02030101FormVo>>();
		List<Oa02030101FormVo> data = new ArrayList<Oa02030101FormVo>();
		try {
			data = oa02030101Service.findCustomerLicenList(customerIdStr, licenseType);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
			logger.error("Oa02030101Controller::findAllName ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/find/customerLicense/{licenseId}")
	@ResponseBody 
	public ResponseData<Oa02030101FormVo> findAll(@PathVariable("licenseId") String licenseIdStr) {
		ResponseData<Oa02030101FormVo> responseData = new ResponseData<>();
		Oa02030101FormVo data = new Oa02030101FormVo();
		try {
			data = oa02030101Service.findCustomerLicenAll(licenseIdStr);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
			logger.error("Oa02030101Controller::findAll ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
 
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
	
	@PutMapping("/update/customerLicense")
	@ResponseBody 
	public ResponseData<Oa02030101FormVo> updateAll(@RequestBody Oa02030101FormVo form) {
		ResponseData<Oa02030101FormVo> responseData = new ResponseData<>();
		Oa02030101FormVo data = new Oa02030101FormVo();
		try {
			String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
			form.setOffCode(officeCode);
			data = oa02030101Service.updateCustomerLicenAll(form);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) { 
			logger.error("Oa02030101Controller::updateAll ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
 
}
