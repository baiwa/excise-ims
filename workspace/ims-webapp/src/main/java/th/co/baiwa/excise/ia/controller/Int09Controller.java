package th.co.baiwa.excise.ia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.ia.persistence.entity.TravelCostWsIntegrate;
import th.co.baiwa.excise.ia.service.Int09Service;

@Controller
@RequestMapping("ia/int09")
public class Int09Controller {
	
	@Autowired
	private Int09Service int09Service;	
	
	@PostMapping("/create")
	@ResponseBody
	public TravelCostWsIntegrate create(@RequestBody  TravelCostWsIntegrate travelCostWorkSheetHeader) {
		int09Service.createTravelCostService(travelCostWorkSheetHeader);
		return travelCostWorkSheetHeader;
	}
	
	
}
