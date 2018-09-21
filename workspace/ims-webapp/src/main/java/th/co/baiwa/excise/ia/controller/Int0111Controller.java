package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.ia.persistence.entity.tax.IaTaxReceipt;
import th.co.baiwa.excise.ia.service.Int0111Service;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncFri8020;

@Controller
@RequestMapping("api/ia/int0111")
public class Int0111Controller {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Int0111Service int0111Service;
	
	@GetMapping("/")
	@ResponseBody
	public String api() {
		logger.info("Display API");
		return " API INT01-1-1 ";
	}
	
	@PostMapping("/ws/pull")
	@ResponseBody
	public List<IaTaxReceipt> wsPulling(@RequestBody IncFri8020 req) {
		logger.info("Web Service Pulling");
		return int0111Service.wsPulling(req);
	}
	
}
