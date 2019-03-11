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

	@PostMapping("/save/customer")
	@ResponseBody
	public ResponseData<Oa020301FormVo> saveAll(@RequestBody Oa020301FormVo form) {
		ResponseData<Oa020301FormVo> responseData = new ResponseData<>();
		Oa020301FormVo data = new Oa020301FormVo();
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
