package th.go.excise.ims.ia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ia.service.Int0913Service;
import th.go.excise.ims.ia.util.ExciseDepartmentUtil;
import th.go.excise.ims.ia.vo.ExciseDepartmentVo;
import th.go.excise.ims.ia.vo.Int091301ResultSearchVo;
import th.go.excise.ims.ia.vo.Int091301SaveVo;
import th.go.excise.ims.ia.vo.Int091301SearchVo;

@Controller
@RequestMapping("/api/ia/int091301")
public class Int0913Controller {

	@Autowired
	private Int0913Service int0913Service;
	
	@PostMapping("/find-091301-search")
	@ResponseBody
	public ResponseData<List<Int091301ResultSearchVo>> findIaUtilityBill(@RequestBody Int091301SearchVo request) {
		ResponseData<List<Int091301ResultSearchVo>> response = new ResponseData<List<Int091301ResultSearchVo>>();
		try {
			response.setData(int0913Service.findIaUtilityBill(request));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	
	@PostMapping("/find-091302-save")
	@ResponseBody
	public ResponseData<?> saveIaUtilityBill(@RequestBody Int091301SaveVo request) {
		ResponseData response = new ResponseData();
		try {
			int0913Service.saveIaUtilityBill(request);
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	@PostMapping("/find-091302-delete/{id}")
	@ResponseBody
	public ResponseData<?> deleteIaUtilityBill(@PathVariable("id") Long id) {
		ResponseData response = new ResponseData();
		try {
			int0913Service.deleteIaUtilityBillById(id);
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@GetMapping("/get-091301-department")
	@ResponseBody
	public ResponseData<ExciseDepartmentVo> getDepartment() {
		ResponseData<ExciseDepartmentVo> response = new ResponseData<ExciseDepartmentVo>();
		try {
			response.setData(ExciseDepartmentUtil.getExciseDepartment(UserLoginUtils.getCurrentUserBean().getOfficeCode()));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}