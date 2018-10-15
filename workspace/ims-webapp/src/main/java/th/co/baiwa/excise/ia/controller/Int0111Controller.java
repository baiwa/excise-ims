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

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.domain.CommonMessage;
import th.co.baiwa.excise.ia.persistence.entity.tax.IaTaxReceiptReq;
import th.co.baiwa.excise.ia.persistence.entity.tax.IaTaxReceiptVo;
import th.co.baiwa.excise.ia.persistence.entity.tax.TaxReceipt;
import th.co.baiwa.excise.ia.service.TaxReceiptService;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncFri8020;

@Controller
@RequestMapping("api/ia/int0111")
public class Int0111Controller {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TaxReceiptService taxReceiptService;
	
	@GetMapping("/")
	@ResponseBody
	public String api() {
		logger.info("Display API");
		return " API INT01-1-1 ";
	}
	
	@PostMapping("/")
	@ResponseBody
	public List<TaxReceipt> data(@RequestBody IncFri8020 req) {
		logger.info("Int0111Controller::data");
		List<TaxReceipt> iaTaxReceiptList = taxReceiptService.wsPulling(req);
		Collections.sort(iaTaxReceiptList, new Comparator<TaxReceipt>() {
		      @Override
		      public int compare(final TaxReceipt object1, final TaxReceipt object2) {
		    	  
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
	public CommonMessage<List<TaxReceipt>> save(@RequestBody IaTaxReceiptReq req) {
		logger.info("Int0111Controller::save");
		return taxReceiptService.save(req);
	}
	
	
	@PostMapping("/lov")
	@ResponseBody
	public List<Lov> getTaxReceipt(@RequestBody Lov lov) {
		logger.info("getTaxReceipt");
		return ApplicationCache.getListOfValueByValueType(lov.getType(), lov.getSubType());
	}
	
	@PostMapping("/searchSummaryTaxReceipt")
	@ResponseBody
	public List<IaTaxReceiptVo> searchSummaryTaxReceipt(@RequestBody IncFri8020 req) {
		String officeCode = req.getOfficeCode();
		String dateFrom = req.getYearMonthFrom();
		String dateTo = req.getYearMonthTo();
		String dateType = req.getDateType();
		logger.info("searchSummaryTaxReceipt {} {} {} {} {} {}", officeCode, dateFrom, dateTo, dateType, req.getPageNo(), req.getDataPerPage());
		IaTaxReceiptVo tax = new IaTaxReceiptVo();
		tax.setOfficeCode(officeCode);
		tax.setDateFrom(dateFrom);
		tax.setDateTo(dateTo);
		tax.setDateType(dateType);
		logger.info("searchSummaryTaxReceipt");
		return taxReceiptService.searchSummaryTaxReceipt(tax);
	}
	
	
}
