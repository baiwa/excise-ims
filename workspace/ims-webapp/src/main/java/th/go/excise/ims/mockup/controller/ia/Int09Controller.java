package th.go.excise.ims.mockup.controller.ia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import th.go.excise.ims.mockup.domain.ia.TravelCostWorkSheetHeader;
import th.go.excise.ims.mockup.service.ia.Int09Service;

@Controller
@RequestMapping("ia/int09")
public class Int09Controller {
	
	@Autowired
	private Int09Service int09Service;	
	
	@PostMapping("/create")
	@ResponseBody
	public String create(@RequestBody  TravelCostWorkSheetHeader travelCostWorkSheetHeader) {
	System.out.println(travelCostWorkSheetHeader.getDepartmentName());
		return travelCostWorkSheetHeader.getDepartmentName();
	}
	
	@GetMapping("/create")
	@ResponseBody
	public String create() {
		return "EIEI";
	}

}
