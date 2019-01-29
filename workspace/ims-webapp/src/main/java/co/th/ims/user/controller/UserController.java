package co.th.ims.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.th.baiwa.buckwaframework.common.bean.ResponseData;
import co.th.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import co.th.ims.user.domain.User;
import co.th.ims.user.service.UserService;

@RequestMapping("/api/users")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public ResponseData<List<User>> findAll() {
		ResponseData<List<User>> response = new ResponseData<List<User>>();
		List<User> users = new ArrayList<User>();
		try {
			users = userService.findAll();
			response.setMessage("SUCCESS");
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		} finally {
			response.setData(users);
		}
		return response;
	}
	
	@GetMapping("/{id}")
	public ResponseData<User> findOne(@PathVariable("id") String id) {
		ResponseData<User> response = new ResponseData<User>();
		try {
			User user = userService.findOne(id);
			response.setData(user);
			response.setMessage("SUCCESS");
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PostMapping("/")
	public ResponseData<?> save(@RequestBody User user) {
		ResponseData<?> response = new ResponseData<>();
		try {
			userService.save(user);
			response.setMessage("SUCCESS");
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@PutMapping("/{id}")
	public ResponseData<User> update(@PathVariable("id") String id, @RequestBody User user) {
		ResponseData<User> response = new ResponseData<User>();
		try {
			userService.update(user, id);
			response.setData(user);
			response.setMessage("SUCCESS");
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
	@DeleteMapping("/{id}")
	public ResponseData<?> delete(@PathVariable("id") String id) {
		ResponseData<?> response = new ResponseData<>();
		try {
			userService.delete(id);
			response.setMessage("SUCCESS");
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			response.setMessage(e.getMessage());
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

}
