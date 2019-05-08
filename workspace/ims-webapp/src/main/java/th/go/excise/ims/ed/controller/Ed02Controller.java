package th.go.excise.ims.ed.controller;

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
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ed.service.Ed02Service;
import th.go.excise.ims.ed.vo.Ed01Vo;
import th.go.excise.ims.ed.vo.Ed02DepartmentVo;
import th.go.excise.ims.ed.vo.Ed02PositionVo;
import th.go.excise.ims.ed.vo.Ed02Vo;

@Controller
@RequestMapping("/api/ed/ed02")
public class Ed02Controller {
	
	private Logger logger = LoggerFactory.getLogger(Ed02Controller.class);
	
	@Autowired
	private Ed02Service ed02Service;

	@GetMapping("/listUser")
	@ResponseBody
	public ResponseData<List<Ed02Vo>> listUser() {
		ResponseData<List<Ed02Vo>> responseData = new ResponseData<List<Ed02Vo>>();
		List<Ed02Vo> data = new ArrayList<>();
		try {
			data = ed02Service.listUser();
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Ed02Controller : listUser  ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/listPosition")
	@ResponseBody
	public ResponseData<List<Ed02PositionVo>> listPosition() {
		ResponseData<List<Ed02PositionVo>> responseData = new ResponseData<List<Ed02PositionVo>>();
		List<Ed02PositionVo> data = new ArrayList<>();
		try {
			data = ed02Service.listPosition();
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Ed02Controller : listUser  ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/listDepartment00")
	@ResponseBody
	public ResponseData<List<Ed02DepartmentVo>> listDepartment00() {
		ResponseData<List<Ed02DepartmentVo>> responseData = new ResponseData<List<Ed02DepartmentVo>>();
		List<Ed02DepartmentVo> data = new ArrayList<>();
		try {
			data = ed02Service.listDepartment00();
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Ed02Controller : listUser  ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}
	
	@GetMapping("/listDepartment01")
	@ResponseBody
	public ResponseData<List<Ed02DepartmentVo>> listDepartment01() {
		ResponseData<List<Ed02DepartmentVo>> responseData = new ResponseData<List<Ed02DepartmentVo>>();
		List<Ed02DepartmentVo> data = new ArrayList<>();
		try {
			data = ed02Service.listDepartment01();
			responseData.setData(data);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("Ed02Controller : listUser  ", e);
			responseData.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.ERROR500_CODE).getMessageTh());
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	
	
	
}
