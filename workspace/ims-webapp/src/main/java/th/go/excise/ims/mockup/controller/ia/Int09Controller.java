package th.go.excise.ims.mockup.controller.ia;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("a/b")
public class Int09Controller {
	@PostMapping("/c")
	public ModelAndView test() {
		ModelAndView mav = new ModelAndView();
		mav.addObject("test controller");
		return mav;
	}

}
