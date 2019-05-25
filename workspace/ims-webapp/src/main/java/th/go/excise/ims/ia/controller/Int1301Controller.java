package th.go.excise.ims.ia.controller;

import java.util.List;

import org.apache.poi.ss.formula.functions.T;
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
import th.go.excise.ims.ia.persistence.entity.IaAuditPmassessH;
import th.go.excise.ims.ia.service.Int1301Service;
import th.go.excise.ims.ia.vo.IaAuditPmassessDVo;
import th.go.excise.ims.ia.vo.Int1301Filter;
import th.go.excise.ims.ia.vo.Int1301SaveVo;
import th.go.excise.ims.ia.vo.Int1301UpdateVo;

@Controller
@RequestMapping("/api/ia/int1302")
public class Int1301Controller {
	private Logger logger = LoggerFactory.getLogger(Int1301Controller.class);
	
	@Autowired
	private Int1301Service int1301Service;
	
	@PostMapping("/get-ws-pm-assess")
	@ResponseBody
	public ResponseData<Int1301SaveVo> getWsPmAssess(@RequestBody Int1301Filter request){
		
		ResponseData<Int1301SaveVo> response =  new ResponseData<Int1301SaveVo>();
		try {
			response.setData(int1301Service.getWsPaAssess(request));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@PostMapping("/get-ia-pm-assess")
	@ResponseBody
	public ResponseData<Int1301UpdateVo> getIaPmAssess(@RequestBody String auditPmassessNo){
		
		ResponseData<Int1301UpdateVo> response =  new ResponseData<Int1301UpdateVo>();
		try {
			response.setData(int1301Service.getIaPmAssess(auditPmassessNo));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@PostMapping("/save-ws-pm-assess")
	@ResponseBody
	public ResponseData<T> saveWsPmAssess(@RequestBody Int1301SaveVo request){
		
		ResponseData<T> response =  new ResponseData<T>();
		try {
			int1301Service.saveWsPmAssess(request);
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@GetMapping("/get-dropdown-auditpmassessno")
	@ResponseBody
	public ResponseData<List<IaAuditPmassessH>> getDropdownAuditPmassessNo(){
		
		ResponseData<List<IaAuditPmassessH>> response =  new ResponseData<List<IaAuditPmassessH>>();
		try {
			response.setData(int1301Service.getAuditPmassessNo());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}
	
	@PostMapping("/update-ia-pm-assess")
	@ResponseBody
	public ResponseData<T> updateIaPmAssess(@RequestBody List<IaAuditPmassessDVo> request){
		
		ResponseData<T> response =  new ResponseData<T>();
		try {
			int1301Service.updateIaPmAssess(request);
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.SUCCESS_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(ApplicationCache.getMessage(RESPONSE_MESSAGE.SAVE.FAILED_CODE).getMessageTh());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		
		return response;
	}

}
