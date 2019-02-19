package th.co.baiwa.buckwaframework.security.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.ApiOperation;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.DocumentConstants.MODULE_NAME;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.URL;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;

@Controller
public class AuthenController {
	
	private static final Logger logger = LoggerFactory.getLogger(AuthenController.class);
	
	@RequestMapping(value = URL.LOGIN_WEB, method = RequestMethod.GET)
	public ModelAndView login(
		@RequestParam(value = "error", required = false) String error,
		@RequestParam(value = "logout", required = false) String logout,
		HttpServletRequest request) {
		
		logger.info("login");
		
		if (StringUtils.isNotBlank(error)) {
			logger.warn("login error: " + error);
		}
		
		ModelAndView mav = new ModelAndView();
		if (error != null) {
			mav.addObject("error", getErrorMessage(request, WebAttributes.AUTHENTICATION_EXCEPTION));
		}
		
		if (logout != null) {
			mav.addObject("msg", "You've been logged out successfully.");
		}
		
		mav.setViewName("security/login");
		
		return mav;
	}
	
	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {
		
		Exception exception = (Exception) request.getSession().getAttribute(key);
		
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username or password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}
		
		return error;
	}
	
	@PostMapping("/api/security/on-login-success")
	@ApiOperation(
		tags = MODULE_NAME.AUTHENTICATION,
		value = "Checking Login?"
	)
	@ResponseBody
	public ResponseData<String> onLoginSuccess() {
		ResponseData<String> respData = new ResponseData<>();
		respData.setStatus(RESPONSE_STATUS.SUCCESS);
		respData.setMessage("Yeah!!");
		return respData;
	}
	
	@PostMapping("/api/security/authority-list")
	@ApiOperation(
		tags = MODULE_NAME.AUTHENTICATION,
		value = "Get Authority List"
	)
	@ResponseBody
	public ResponseData<List<String>> getGrantedAuthorityList() {
		Collection<GrantedAuthority> grantedAuthorityList = UserLoginUtils.getCurrentUserBean().getAuthorities();
		
		List<String> authorityList = new ArrayList<>();
		for (GrantedAuthority grantedAuthority : grantedAuthorityList) {
			authorityList.add(grantedAuthority.getAuthority());
		}
		
		ResponseData<List<String>> respData = new ResponseData<>();
		respData.setData(authorityList);
		respData.setStatus(RESPONSE_STATUS.SUCCESS);
		
		return respData;
	}
	
}
