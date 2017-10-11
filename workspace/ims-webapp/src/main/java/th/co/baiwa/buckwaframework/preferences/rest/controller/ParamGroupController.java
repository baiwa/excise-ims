package th.co.baiwa.buckwaframework.preferences.rest.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.util.UriComponentsBuilder;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterGroup;
import th.co.baiwa.buckwaframework.preferences.service.ParameterService;

@Controller
@RequestMapping(value = "/api/preferences/prameterGroup")
public class ParamGroupController {
	
	private static final Logger logger = LoggerFactory.getLogger(ParamGroupController.class);
	
	@Autowired
	private ParameterService parameterService;
	
	@GetMapping
	public ResponseEntity<?> getAll() {
		logger.info("getAll");
		
		ResponseData<Map<String, ParameterGroup>> response = new ResponseData<>();
//		String paramGroupCode;
//		response.setData(ApplicationCache.getParameterGroupByCode(paramGroupCode));
		
		return new ResponseEntity<ResponseData<Map<String, ParameterGroup>>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getParameterGroup(@PathVariable("id") long id) {
		logger.info("getParameterGroup [id=" + id + "]");
		
		ParameterGroup parameter = parameterService.getParameterGroupById(id);
		ResponseData<ParameterGroup> response = new ResponseData<ParameterGroup>();
		response.setData(parameter);
		return new ResponseEntity<ResponseData<ParameterGroup>>(response, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ParameterGroup parameter, UriComponentsBuilder ucBuilder) {
		logger.info("create [parameter=" + parameter + "]");
		
		ParameterGroup newParameterGroup = parameterService.insertParameterGroup(parameter);
		
		ResponseData<ParameterGroup> response = new ResponseData<ParameterGroup>();
		response.setData(newParameterGroup);
		
		return new ResponseEntity<ResponseData<ParameterGroup>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody ParameterGroup parameter, UriComponentsBuilder ucBuilder) {
		
		parameterService.updateParameterGroup(parameter);
		
		HttpHeaders headers = new HttpHeaders();
		
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		logger.info("delete [id=" + id + "]");
		
		parameterService.deleteParameterGroup(id);
		return new ResponseEntity<ParameterGroup>(HttpStatus.NO_CONTENT);
	}
	
}
