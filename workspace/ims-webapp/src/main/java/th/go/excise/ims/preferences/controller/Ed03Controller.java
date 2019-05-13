package th.go.excise.ims.preferences.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.preferences.service.Ed03Service;
import th.go.excise.ims.preferences.vo.Ed03FormVo;
import th.go.excise.ims.preferences.vo.Ed03Vo;

@Controller
@RequestMapping("/api/ed/ed03")
public class Ed03Controller {
	private Logger logger = LoggerFactory.getLogger(Ed03Controller.class);

	@Autowired
	private Ed03Service ed03Service;

	@PostMapping("/find-by-dutyGroupName")
	@ResponseBody
	public ResponseData<List<Ed03Vo>> findByDutyGroupName(@RequestBody Ed03FormVo request) {
		ResponseData<List<Ed03Vo>> response = new ResponseData<List<Ed03Vo>>();
		try {
			response.setData(ed03Service.findByDutyGroupName(request));
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Ed0201Controller updateConfigposition : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/saveExciseCtrlDuty")
	@ResponseBody
	public ResponseData<String> saveExciseCtrlDuty(@RequestBody Ed03FormVo form) {
		ResponseData<String> response = new ResponseData<String>();
		try {
			ed03Service.saveExciseCtrlDuty(form);
			response.setData("SUCCESS");
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Error Ed03Controller saveExciseCtrlDuty : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PutMapping("/editExciseCtrlDuty/{id}")
	@ResponseBody
	public ResponseData<String> editExciseCtrlDuty(@PathVariable("id") Long id, @RequestBody Ed03FormVo form) {
		ResponseData<String> response = new ResponseData<String>();
		try {
			ed03Service.editExciseCtrlDuty(id, form);
			response.setData("SUCCESS");
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Error Ed03Controller saveExciseCtrlDuty : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SAVE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@DeleteMapping("/deleteById/{id}")
	@ResponseBody
	public ResponseData<String> deleteExciseCtrlDuty(@PathVariable("id") Long id) {
		ResponseData<String> response = new ResponseData<String>();
		try {
			ed03Service.deleteExciseCtrlDuty(id);
			response.setData("SUCCESS");
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Error Ed03Controller  deleteExciseCtrlDuty : ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.DELETE.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}
