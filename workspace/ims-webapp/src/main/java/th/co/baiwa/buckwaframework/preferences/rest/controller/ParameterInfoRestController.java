package th.co.baiwa.buckwaframework.preferences.rest.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterInfo;
import th.co.baiwa.buckwaframework.preferences.service.ParameterInfoService;

@RestController
@RequestMapping("/api/preferences/prameter-info")
public class ParameterInfoRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ParameterInfoRestController.class);
	
	@Autowired
	private ParameterInfoService parameterInfoService;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		logger.info("getAll");
		
		ResponseData<Map<String, ParameterInfo>> response = new ResponseData<>();
//		String paramInfoCode;
//		response.setData(ApplicationCache.getParameterInfoByCode(paramInfoCode));
		
		return new ResponseEntity<ResponseData<Map<String, ParameterInfo>>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getParameterInfo(@PathVariable("id") long id) {
		logger.info("getParameterInfo [id=" + id + "]");
		
		ParameterInfo parameter = parameterInfoService.getParameterInfoById(id);
		ResponseData<ParameterInfo> response = new ResponseData<ParameterInfo>();
		response.setData(parameter);
		return new ResponseEntity<ResponseData<ParameterInfo>>(response, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ParameterInfo parameter, UriComponentsBuilder ucBuilder) {
		logger.info("create [parameter=" + parameter + "]");
		
		ParameterInfo newParameterInfo = parameterInfoService.insertParameterInfo(parameter);
		
		ResponseData<ParameterInfo> response = new ResponseData<ParameterInfo>();
		response.setData(newParameterInfo);
		
		return new ResponseEntity<ResponseData<ParameterInfo>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody ParameterInfo parameter, UriComponentsBuilder ucBuilder) {
		
		parameterInfoService.updateParameterInfo(parameter);
		
		HttpHeaders headers = new HttpHeaders();
		
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		logger.info("delete [id=" + id + "]");
		
		parameterInfoService.deleteParameterInfo(id);
		return new ResponseEntity<ParameterInfo>(HttpStatus.NO_CONTENT);
	}
	
}
