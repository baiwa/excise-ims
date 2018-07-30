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

import io.swagger.annotations.ApiOperation;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.DocumentConstants.MODULE_NAME;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterGroup;
import th.co.baiwa.buckwaframework.preferences.service.ParameterGroupService;

@RestController
@RequestMapping("/api/preferences/prameter-group")
public class ParameterGroupRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(ParameterGroupRestController.class);
	
	private final ParameterGroupService parameterGroupService;
	
	@Autowired
	public ParameterGroupRestController(ParameterGroupService parameterGroupService) {
		this.parameterGroupService = parameterGroupService;
	}
	
	@GetMapping
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get All Parameter Group"
	)
	public ResponseEntity<?> getAll() {
		logger.info("getAll");
		
		ResponseData<Map<String, ParameterGroup>> response = new ResponseData<>();
//		String paramGroupCode;
//		response.setData(ApplicationCache.getParameterGroupByCode(paramGroupCode));
		
		return new ResponseEntity<ResponseData<Map<String, ParameterGroup>>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Get Parameter Group by Id"
	)
	public ResponseEntity<?> getParameterGroup(@PathVariable("id") long id) {
		logger.info("getParameterGroup [id={}]", id);
		
		ParameterGroup parameter = parameterGroupService.getParameterGroupById(id);
		ResponseData<ParameterGroup> response = new ResponseData<ParameterGroup>();
		response.setData(parameter);
		return new ResponseEntity<ResponseData<ParameterGroup>>(response, HttpStatus.OK);
	}
	
	@PostMapping
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Create Parameter Group"
	)
	public ResponseEntity<?> create(@RequestBody ParameterGroup parameter, UriComponentsBuilder ucBuilder) {
		logger.info("create [parameter={}]", parameter);
		
		ParameterGroup newParameterGroup = parameterGroupService.insertParameterGroup(parameter);
		
		ResponseData<ParameterGroup> response = new ResponseData<ParameterGroup>();
		response.setData(newParameterGroup);
		
		return new ResponseEntity<ResponseData<ParameterGroup>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Update Parameter Group"
	)
	public ResponseEntity<?> update(@RequestBody ParameterGroup parameter, UriComponentsBuilder ucBuilder) {
		logger.info("update [parameter={}]", parameter);
		
		parameterGroupService.updateParameterGroup(parameter);
		
		HttpHeaders headers = new HttpHeaders();
		
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	@ApiOperation(
		tags = MODULE_NAME.PREFERENCES,
		value = "Delete Parameter Group"
	)
	public ResponseEntity<?> delete(@PathVariable("id") long id) {
		logger.info("delete [id={}]", id);
		
		parameterGroupService.deleteParameterGroup(id);
		return new ResponseEntity<ParameterGroup>(HttpStatus.NO_CONTENT);
	}
	
}
