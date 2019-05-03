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
import th.go.excise.ims.ia.service.Int091303Service;
import th.go.excise.ims.ia.vo.Int091303ResultVo;
import th.go.excise.ims.ia.vo.Int091303SearchVo;

@Controller
@RequestMapping("/api/ia/int091303")
public class Int091303Controller {

	@Autowired
	private Int091303Service int091303Service;
	
	@PostMapping("/find-091303-search")
	@ResponseBody
	public ResponseData<List<Int091303ResultVo>> findIaUtilityBill(@RequestBody Int091303SearchVo request) {
		ResponseData<List<Int091303ResultVo>> response = new ResponseData<List<Int091303ResultVo>>();
		try {
			response.setData(int091303Service.int091303SearchVo(request));
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