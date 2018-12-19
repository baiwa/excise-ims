package th.co.baiwa.buckwaframework.accesscontrol.rest.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.co.baiwa.buckwaframework.accesscontrol.service.ExciseAuthenService;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.security.domain.UserDetails;

@RestController
@RequestMapping("/api/access-control/user-profile")
public class UserProfileRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(UserProfileRestController.class);
	
	@Autowired
	private ExciseAuthenService exciseAuthenService;
	
	@GetMapping
	public ResponseEntity<?> userProfile(Authentication authentication) {
		logger.info("userProfile : "+ authentication.getName());
		ResponseData<UserDetails> response = new ResponseData<>();
		if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) authentication.getPrincipal();
			userDetail.setExciseBaseControl(exciseAuthenService.getAuthenPage());
			response.setData(userDetail);
			userDetail.setRole(exciseAuthenService.findRoleByUserLogin());
		}
		return new ResponseEntity<ResponseData<UserDetails>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/authorities")
	public ResponseEntity<?> userAuthorities(Authentication authentication) {
		ResponseData<List<String>> response = new ResponseData<>();
		if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) authentication.getPrincipal();
			List<String> authorityList = new ArrayList<String>();
			for (GrantedAuthority authority : userDetail.getAuthorities()) {
				authorityList.add(authority.getAuthority());
			}
			response.setData(authorityList);
		}
		return new ResponseEntity<ResponseData<List<String>>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/has-role/{role}")
	public boolean hasRole(@PathVariable("role") String role, HttpServletRequest request) {
		return request.isUserInRole(role);
	}
	
	@GetMapping("/has-authority/{authority}")
	public boolean hasAuthority(@PathVariable("authority") String authority, Authentication authentication) {
		boolean authorized = false;
		if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
			Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
			authorized = authorities.contains(new SimpleGrantedAuthority(authority));
		}
		return authorized;
	}
	
}
