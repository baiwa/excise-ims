package th.go.excise.ims.mockup.controller.ia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import th.go.excise.ims.mockup.domain.ia.TravelCostWorkSheetHeader;
import th.go.excise.ims.mockup.service.ia.Int09Service;

@Controller
@RequestMapping("ia/int09")
public class Int09Controller {
	
	@Autowired
	private Int09Service int09Service;
	
	@PostMapping("/creaet")
	public String creaet(@ModelAttribute TravelCostWorkSheetHeader travelCostWorkSheetHeader) {
	System.out.println("555555"+travelCostWorkSheetHeader.getDepartmentName());
		return "";
	}

}
