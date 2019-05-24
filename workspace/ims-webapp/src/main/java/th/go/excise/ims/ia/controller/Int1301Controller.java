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
import th.go.excise.ims.ia.service.Int1301Service;
import th.go.excise.ims.ia.vo.Int1301Filter;
import th.go.excise.ims.ia.vo.Int1301Vo;

@Controller
@RequestMapping("/api/ia/int1302")
public class Int1301Controller {
	private Logger logger = LoggerFactory.getLogger(Int1301Controller.class);
	
	@Autowired
	private Int1301Service int1301Service;
	
	@GetMapping("/get-ws-pm-assess")
	@ResponseBody
	public ResponseData<Int1301Vo> getWsPaAssess(Int1301Filter request){
		
		ResponseData<Int1301Vo> response =  new ResponseData<Int1301Vo>();
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

}
