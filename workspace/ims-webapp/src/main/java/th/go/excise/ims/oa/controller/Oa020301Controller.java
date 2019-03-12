package th.go.excise.ims.oa.controller;

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

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.oa.persistence.entity.OaCustomer;
import th.go.excise.ims.oa.service.Oa020301Service;
import th.go.excise.ims.oa.vo.Oa020301FormVo;
import th.go.excise.ims.oa.vo.Oa020301Vo;

@Controller
@RequestMapping("/api/oa/03/01")
public class Oa020301Controller {

	private static final Logger logger = LoggerFactory.getLogger(Oa020301Controller.class);

	@Autowired
	private Oa020301Service int030201Service;

	@GetMapping("/customers/{id}")
	@ResponseBody
	public ResponseData<OaCustomer> findById(@PathVariable("id") String idStr) {
		ResponseData<OaCustomer> responseData = new ResponseData<>();
		OaCustomer data = new OaCustomer();
		try {
			data = int030201Service.findById(idStr);
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa020301Controller::findById ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/save/customer")
	@ResponseBody
	public ResponseData<OaCustomer> saveAll(@RequestBody Oa020301FormVo form) {
		ResponseData<OaCustomer> responseData = new ResponseData<>();
		OaCustomer data = new OaCustomer();
		try {
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

	@PutMapping("/update/customer/{id}")
	@ResponseBody
	public ResponseData<OaCustomer> updateAll(@RequestBody Oa020301FormVo form, @PathVariable("id") String idStr) {
		ResponseData<OaCustomer> responseData = new ResponseData<>();
		OaCustomer data = new OaCustomer();
		try {
			data = int030201Service.updateCustomer(form, idStr);
			responseData.setData(data);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Oa020301Controller::updateAll ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PostMapping("/filter")
	@ResponseBody
	public DataTableAjax<OaCustomer> filterByCriteria(@RequestBody Oa020301Vo request) {
		DataTableAjax<OaCustomer> response = new DataTableAjax<>();
		try {
			response = int030201Service.filterByCriteria(request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Oa020301Controller::filterByCriteria => ", e);
		}
		return response;
	}

}