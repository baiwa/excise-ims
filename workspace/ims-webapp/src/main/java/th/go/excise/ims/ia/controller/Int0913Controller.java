package th.go.excise.ims.ia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.persistence.entity.IaUtilityBill;
import th.go.excise.ims.ia.service.Int0913Service;
import th.go.excise.ims.ia.vo.Int091301ResultSearchVo;
import th.go.excise.ims.ia.vo.Int091301SearchVo;

@Controller
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
	public ResponseData<IaUtilityBill> saveIaUtilityBill(@RequestBody IaUtilityBill request) {
		ResponseData<IaUtilityBill> response = new ResponseData<IaUtilityBill>();
		try {
			response.setData(int0913Service.saveIaUtilityBill(request));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	@PostMapping("/find-091302-delete/{}")
	@ResponseBody
	public ResponseData<IaUtilityBill> deleteIaUtilityBill(@PathVariable("id") Long id) {
		ResponseData<IaUtilityBill> response = new ResponseData<IaUtilityBill>();
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
}
