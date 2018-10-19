package th.co.baiwa.excise.ta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import th.co.baiwa.excise.ta.service.TaWebService;

@Controller
@RequestMapping("api/working/taWebService")
public class TaWebServiceController {

	@Autowired 
	private TaWebService taWebService;
	
	@GetMapping("/intIncFri8000")
	public void intIncFri8000() {
		taWebService.intIncFri8000();
	}
	
	@GetMapping("/regFri4000")
	public void regFri4000() {
		taWebService.regFri4000();
	}
}
