package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.ia.persistence.vo.Int02m2Vo;
import th.co.baiwa.excise.ia.service.Int02m2Service;

@Controller
@RequestMapping("api/ia/int02m2")
public class Int02m2Controller {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Int02m2Service int02m2Service;
	

	@GetMapping("/")
	@ResponseBody
	public String api() {
		return "API IA INT02M2";
	}
	
	@PostMapping("/all")
	@ResponseBody
	public List<Int02m2Vo> findByCretiria(Int02m2Vo find) {
		return int02m2Service.findByCreteria(find);
	} 
	
	@GetMapping("/master")
	@ResponseBody
	public List<Int02m2Vo> findMaster() {
		return int02m2Service.findByCreteria();
	}
	
}