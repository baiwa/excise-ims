package th.co.baiwa.buckwaframework.backend.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/backend")
public class BackendCommonController {
	
	private static final Logger logger = LoggerFactory.getLogger(BackendCommonController.class);
	
	@GetMapping(value = {"", "/"})
	public String home() {
		return "redirect:/backend/welcome.htm";
	}
	
	@GetMapping(value = "/welcome.htm")
	public ModelAndView welcome() {
		logger.info("welcome");
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("backend/welcome");
		
		return mav;
	}
	
}