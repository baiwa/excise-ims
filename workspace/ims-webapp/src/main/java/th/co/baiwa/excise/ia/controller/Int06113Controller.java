package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.excise.ia.service.Int06113Service;

@Controller
@RequestMapping("api/ia/int0613")
public class Int06113Controller {

	@Autowired
	private Int06113Service int06113Service;
	
	@GetMapping("/sector")
	@ResponseBody
	public List<Lov> sector(){
		return int06113Service.sector();
	}
}
