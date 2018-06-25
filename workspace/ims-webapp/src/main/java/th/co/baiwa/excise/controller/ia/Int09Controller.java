package th.co.baiwa.excise.controller.ia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.ia.TravelCostWsIntegrate;
import th.co.baiwa.excise.service.ia.Int09Service;

@Controller
@RequestMapping("ia/int09")
public class Int09Controller {
	
	@Autowired
	private Int09Service int09Service;	
	
	@PostMapping("/create")
	@ResponseBody
	public TravelCostWsIntegrate create(@RequestBody  TravelCostWsIntegrate travelCostWorkSheetHeader) {
	System.out.println(travelCostWorkSheetHeader.getDepartmentName());
		return travelCostWorkSheetHeader;
	}
	
	
}
