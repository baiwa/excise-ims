package th.co.baiwa.buckwaframework.preferences.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.UserManagement;
import th.co.baiwa.buckwaframework.preferences.service.UserManagementService;
import th.go.excise.ims.mockup.persistence.entity.ListOfValue;
import th.go.excise.ims.mockup.persistence.entity.SelectOptionValue;
import th.go.excise.ims.mockup.service.ListOfValueService;

@RestController
@RequestMapping("/api/preferences/userManagement")
public class UserManagementRestController {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageRestController.class);
	
	@Autowired
	private UserManagementService userManagementService;
	
	@Autowired
	private ListOfValueService listOfValueService;
	
	@GetMapping("search")
	public ResponseEntity<?> search(
			@RequestParam(name="draw") Integer draw, 
			@RequestParam(name="start") Integer start, 
			@RequestParam(name="length") Integer length,
			String username,
			String enabled,
			String accountNonExpired,
			String credentialsNonExpired,
			String accountNonLocked
			) {
		
		UserManagement userManagement = new UserManagement();
		userManagement.setUsername(username);
		userManagement.setEnabled(enabled);
		userManagement.setAccountNonExpired(accountNonExpired);
		userManagement.setCredentialsNonExpired(credentialsNonExpired);
		userManagement.setAccountNonLocked(accountNonLocked);
		logger.info("getAll for datatable");
		
		List<UserManagement> resultList = userManagementService.getUserManagementList(start, length, userManagement);
		Integer recordsTotal = userManagementService.countUserManagement();
		
		ResponseDataTable<UserManagement> response = new ResponseDataTable<UserManagement>();
		response.setDraw(draw);
		response.setStart(start);
		response.setLength(length);
		response.setData(resultList);
		response.setRecordsTotal(recordsTotal);
		response.setRecordsFiltered(recordsTotal);
		
		return new ResponseEntity<ResponseDataTable<UserManagement>>(response, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getUserManagement(@PathVariable("id") String id) {
		logger.info("getUserManagement [id=" + id + "]");
		
		UserManagement userManagement = userManagementService.getUserManagementByUserId(id);
		ResponseData<UserManagement> response = new ResponseData<UserManagement>();
		response.setData(userManagement);
		return new ResponseEntity<ResponseData<UserManagement>>(response, HttpStatus.OK);
	}
	
	@GetMapping("exist/{username}")
	public ResponseEntity<?> checkUsername(@PathVariable("username") String username) {
		logger.info("checkUsername [username=" + username + "]");
		
		UserManagement userManagement = userManagementService.getUserManagementByUsername(username);
		ResponseData<UserManagement> response = new ResponseData<UserManagement>();
		response.setData(userManagement);
		return new ResponseEntity<ResponseData<UserManagement>>(response, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody UserManagement userManagement) {
		logger.info("create [userManagement=" + userManagement.getUsername() + "]");
		
		UserManagement newUserManagement = userManagementService.insertUserManagement(userManagement);
		
		ResponseData<UserManagement> response = new ResponseData<UserManagement>();
		response.setData(newUserManagement);
		
		return new ResponseEntity<ResponseData<UserManagement>>(response, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody UserManagement userManagement) {
		logger.info("update [userManagement=" + userManagement.getUsername() + "]");
		
		userManagementService.updateUserManagement(userManagement);
		
		ResponseData<UserManagement> response = new ResponseData<UserManagement>();
		response.setData(userManagement);
		
		return new ResponseEntity<ResponseData<UserManagement>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") List<Long> id) {
		logger.info("delete [id=" + id + "]");
		
		userManagementService.deleteUserManagement(id);
		return new ResponseEntity<Message>(HttpStatus.NO_CONTENT);
	}
	
	@PostMapping("/setorList")
	@ResponseBody
	public List<SelectOptionValue> getSetorListByLov() {
		List<SelectOptionValue> sectorList = new ArrayList<SelectOptionValue>();
		List<ListOfValue> lovList = listOfValueService.queryLovByCriteria(new ListOfValue("SECTOR_LIST"));
		SelectOptionValue selectOption= null;
		for (ListOfValue listOfValue : lovList) {
			selectOption = new SelectOptionValue();
			selectOption.setValue(listOfValue.getValue());
			sectorList.add(selectOption);
		}
		return sectorList;
	}
	
}
