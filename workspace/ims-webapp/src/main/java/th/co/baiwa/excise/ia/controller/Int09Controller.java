package th.co.baiwa.excise.ia.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.ia.persistence.entity.TravelCostWorkSheetHeader;
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
	
	@GetMapping("/lists")
	@ResponseBody
	public List<TravelCostWorkSheetHeader> lists() {
		List<TravelCostWorkSheetHeader> lists = int09Service.listTravelCostHeaderService(new TravelCostWorkSheetHeader());
		return lists;
	}
	
	@GetMapping("/lists/{id}")
	@ResponseBody
	public List<TravelCostWorkSheetHeader> lists(@PathVariable("id") String id) {
		TravelCostWorkSheetHeader travel = new TravelCostWorkSheetHeader();
		travel.setWorkSheetHeaderId(new BigDecimal(id));
		List<TravelCostWorkSheetHeader> lists = int09Service.listTravelCostHeaderService(travel);
		return lists;
	}
	
}
