package th.co.baiwa.excise.ia.controller;


import java.util.Collections;
import java.util.Comparator;
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

import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.excise.ia.persistence.entity.tax.IaTaxReceipt;
import th.co.baiwa.excise.ia.persistence.entity.tax.IaTaxReceiptReq;
import th.co.baiwa.excise.ia.service.Int0111Service;
import th.co.baiwa.excise.utils.BeanUtils;
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
	
	@PostMapping("/")
	@ResponseBody
	public List<IaTaxReceipt> data(@RequestBody IncFri8020 req) {
		logger.info("Int0111Controller::data");
		List<IaTaxReceipt> iaTaxReceiptList = int0111Service.wsPulling(req);
		Collections.sort(iaTaxReceiptList, new Comparator<IaTaxReceipt>() {
		      @Override
		      public int compare(final IaTaxReceipt object1, final IaTaxReceipt object2) {
		    	  
		    	  String rec1 = object1.getReceiptNo();
		    	  String rec2 = object2.getReceiptNo();
		    	  if(BeanUtils.isEmpty(rec1)) {
		    		  rec1 = "Z";
		    	  }
		    	  if(BeanUtils.isEmpty(rec2)) {
		    		  rec2 = "Z";
		    	  }
		    	  return rec1.compareTo(rec2);
		         
		      }
		  });
		return iaTaxReceiptList;
	}

	@PostMapping("/save")
	@ResponseBody
	public CommonMessage<List<IaTaxReceipt>> save(@RequestBody IaTaxReceiptReq req) {
		logger.info("Int0111Controller::save");
		return int0111Service.save(req);
	}
	
}
