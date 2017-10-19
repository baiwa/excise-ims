package th.co.baiwa.buckwaframework.preferences.rest.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.util.WebContextUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;

@RestController
@RequestMapping("/api/preferences/reload-cache")
public class PreferencesRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(PreferencesRestController.class);
	
	@GetMapping
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
