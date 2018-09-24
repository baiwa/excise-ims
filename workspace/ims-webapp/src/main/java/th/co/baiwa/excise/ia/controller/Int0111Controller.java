package th.co.baiwa.excise.ia.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.excise.ia.persistence.entity.tax.IaTaxReceipt;
import th.co.baiwa.excise.ia.persistence.entity.tax.IaTaxReceiptReq;
import th.co.baiwa.excise.ia.service.Int0111Service;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncFri8020;

import java.util.List;

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
	
	@PostMapping("/")
	@ResponseBody
	public List<IaTaxReceipt> data(@RequestBody IncFri8020 req) {
		logger.info("Int0111Controller::data");
		return int0111Service.wsPulling(req);
	}

	@PostMapping("/save")
	@ResponseBody
	public CommonMessage<List<IaTaxReceipt>> save(@RequestBody IaTaxReceiptReq req) {
		logger.info("Int0111Controller::save");
		return int0111Service.save(req);
	}
	
}
