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
import th.go.excise.ims.ia.service.Int0602Service;
import th.go.excise.ims.ia.vo.Int0602FormVo;
import th.go.excise.ims.ws.persistence.entity.WsLicfri6010;

@Controller
@RequestMapping("/api/ia/int06/02")
public class Int0602Controller {
	@Autowired
	private Int0602Service int0602Service;

	@PostMapping("/findByCriteria")
	@ResponseBody
	public ResponseData<List<WsLicfri6010>> findByCriteria(@RequestBody Int0602FormVo request) {
		ResponseData<List<WsLicfri6010>> response = new ResponseData<List<WsLicfri6010>>();
		try {
			response.setData(int0602Service.findByCriteria(request));
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
