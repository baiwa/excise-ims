package th.co.baiwa.excise.oa.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.excise.oa.persistence.vo.COP011SaveRequestFormVo;
import th.co.baiwa.excise.oa.service.COP011Service;

@Controller
@RequestMapping("api/oa/cop011")
public class COP011Controller {
	
	@Autowired
	private COP011Service cop011Service;
	
	@PostMapping("/saveOaOperPlan")
	@ResponseBody
	public Message saveOaOperPlan(@RequestBody COP011SaveRequestFormVo saveRequestFormVo) {
		Message message =  null;
		message = cop011Service.saveOaOperPlan(saveRequestFormVo);
		return message;
	}
	

	

}
