package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.excise.ia.service.Int02m51Service;

@Controller
@RequestMapping("api/ia/int02m51")
public class Int02m51Controller {
	
	@Autowired
	private Int02m51Service int02m51Service;
	
	
	@GetMapping("/sector")
	@ResponseBody
	public List<Lov>sector(){
		return int02m51Service.sector();
	}
	
	
}
