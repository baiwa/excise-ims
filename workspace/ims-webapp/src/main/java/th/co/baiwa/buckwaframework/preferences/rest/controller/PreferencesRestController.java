package th.co.baiwa.buckwaframework.preferences.rest.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.util.WebContextUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;

@RestController
@RequestMapping("/api/preferences/reload-cache")
@Api(value = "Operations reload cache in application")
public class PreferencesRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(PreferencesRestController.class);
	
	@GetMapping
	@ApiOperation(
		value = "Reload Cache",
		notes = "Reload Parameter and Message into Cache"
	)
	@ApiResponses(value = {
		@ApiResponse(code = HttpServletResponse.SC_OK, message = "Successfully reload cache")
	})
	public ResponseEntity<?> reloadCache() {
		logger.info("reloadCache");
		
		ApplicationCache applicationCache = (ApplicationCache) WebContextUtils.getBean("applicationCache");
		applicationCache.reloadCache();
		
		Map<String, String> map = new HashMap<>();
		map.put("status", "reload cache successful");
		
		ResponseData<Map<String, String>> response = new ResponseData<>();
		response.setData(map);
		
		return new ResponseEntity<ResponseData<Map<String, String>>>(response, HttpStatus.OK);
	}
	
}
