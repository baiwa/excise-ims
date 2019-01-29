package co.th.ims.user.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.th.baiwa.buckwaframework.common.bean.ResponseData;
import co.th.ims.user.domain.User;

@RequestMapping("/api/users")
@RestController
public class UserController {
	
	@GetMapping("/")
	public ResponseData<List<User>> findAll() {
		ResponseData<List<User>> response = new ResponseData<List<User>>();
		List<User> users = new ArrayList<User>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		users.add(new User());
		users.add(new User());
		users.add(new User());
		response.setData(users);
		return response;
	}
	
	@GetMapping("/{id}")
	public ResponseData<User> findOne(@PathVariable("id") String id) {
		return null;
	}
	
	@PostMapping("/")
	public ResponseData<?> save() {
		return null;
	}
	
	@PutMapping("/{id}")
	public ResponseData<User> update(@PathVariable("id") String id, @RequestBody User user) {
		return null;
	}
	
	@DeleteMapping("/{id}")
	public ResponseData<?> delete(@PathVariable("id") String id) {
		return null;
	}

}
