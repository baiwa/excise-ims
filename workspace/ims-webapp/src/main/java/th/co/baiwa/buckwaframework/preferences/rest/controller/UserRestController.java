package th.co.baiwa.buckwaframework.preferences.rest.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/api/user")
public class UserRestController {
	@GetMapping(value="/getUserProfile")
	@ResponseBody
	public th.co.baiwa.buckwaframework.security.model.UserDetails checkAlive(HttpServletRequest httpServletRequest){
		th.co.baiwa.buckwaframework.security.model.UserDetails userDetail = null;
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && !(auth instanceof AnonymousAuthenticationToken)) {
			userDetail = (th.co.baiwa.buckwaframework.security.model.UserDetails) auth.getPrincipal();
		}
		return userDetail;		
	}
}
