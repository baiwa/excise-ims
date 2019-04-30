package th.go.excise.ims.ia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD2;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncH;
import th.go.excise.ims.ia.service.Int0601Service;
import th.go.excise.ims.ia.vo.Int0601RequestVo;
import th.go.excise.ims.ia.vo.Int0601SaveVo;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8020Inc;

@Controller
@RequestMapping("/api/ia/int06/01")
public class Int0601Controller {

	@Autowired
	private Int0601Service int0601Service;

	@PostMapping("/find-tab1")
	@ResponseBody
	public ResponseData<List<WsIncfri8020Inc>> findTab1(@RequestBody Int0601RequestVo request) {
		ResponseData<List<WsIncfri8020Inc>> response = new ResponseData<List<WsIncfri8020Inc>>();
		try {
			response.setData(int0601Service.findTab1ByCriteria(request));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PostMapping("/save-hdr")
	@ResponseBody
	public ResponseData<IaAuditIncH> addWsIncfri8020Inc(@RequestBody Int0601SaveVo request) {
		ResponseData<IaAuditIncH> response = new ResponseData<IaAuditIncH>();
		try {
			response.setData(int0601Service.createIaAuditInc(request));
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/find-all-data-header")
	@ResponseBody
	public ResponseData<List<IaAuditIncH>> findAllDataHeader() {
		ResponseData<List<IaAuditIncH>> response = new ResponseData<List<IaAuditIncH>>();
		try {
			response.setData(int0601Service.findAllIaAuditIncH());
			response.setMessage(RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			response.setMessage(RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/find-tab2")
	@ResponseBody
	public ResponseData<List<IaAuditIncD2>> findTab2(@RequestBody Int0601RequestVo request) {
		ResponseData<List<IaAuditIncD2>> response = new ResponseData<List<IaAuditIncD2>>();
		try {
			response.setData(int0601Service.findIaAuditIncD2ByCriteria(request));
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
