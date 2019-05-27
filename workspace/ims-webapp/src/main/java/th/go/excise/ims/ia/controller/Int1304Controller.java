package th.go.excise.ims.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.service.Int1304Service;
import th.go.excise.ims.ia.vo.Int1304FormVo;
import th.go.excise.ims.ia.vo.Int1304Vo;

@Controller
@RequestMapping("api/ia/int13/04")
public class Int1304Controller {
	
	@Autowired
	private Int1304Service int1304Service;

	private static final Logger logger = LoggerFactory.getLogger(Int1304Controller.class);
	
	@GetMapping("/get-ws-qt")
	@ResponseBody
	public ResponseData<Int1304Vo> getWsQt(Int1304FormVo request){
		ResponseData<Int1304Vo> response =  new ResponseData<Int1304Vo>();
		try {
			response.setData(int1304Service.getWsQt(request));
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
