package th.co.baiwa.excise.ia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.excise.ia.persistence.entity.QtnFinalRepHeader;

@Controller
@RequestMapping("ia/int02")
public class Int02Controller {

	@PostMapping("/addHeaderQuestionnaire")
	@ResponseBody
	public Message addHeaderQuestionnaire(@RequestBody  QtnFinalRepHeader qtnFinalRepHeader) {
	System.out.println(qtnFinalRepHeader.getQtnFinalRepHdrName());
	Message message = null;
		return message;
	}
}
