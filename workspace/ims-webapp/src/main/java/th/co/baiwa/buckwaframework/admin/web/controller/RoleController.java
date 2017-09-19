package th.co.baiwa.buckwaframework.admin.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/admin/role")
public class RoleController {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/init.htm", method = RequestMethod.GET)
	public ModelAndView init() {
		logger.info("init method");
		
		ModelAndView model = new ModelAndView();
		model.setViewName("roleList");
		return model;
	}
	
}
