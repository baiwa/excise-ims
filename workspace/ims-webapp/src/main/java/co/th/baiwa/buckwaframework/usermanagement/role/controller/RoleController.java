package co.th.baiwa.buckwaframework.usermanagement.role.controller;

import java.util.List;

import co.th.baiwa.buckwaframework.common.constant.MessageContants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import co.th.baiwa.buckwaframework.common.bean.ResponseData;
import co.th.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import co.th.baiwa.buckwaframework.usermanagement.role.domain.Role;
import co.th.baiwa.buckwaframework.usermanagement.role.service.RoleService;

@RequestMapping("/api/role")
@RestController
public class RoleController {
	
	@Autowired
	private RoleService roleService;

	@GetMapping("/")
	public ResponseData<List<Role>> findAll() {

		ResponseData<List<Role>> response = new ResponseData<List<Role>>();

		try {
			response.setData(roleService.findAll());
			response.setMessage(MessageContants.STATUS.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			response.setMessage(MessageContants.STATUS.FAILED);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}

	@PutMapping("/update")
	public ResponseData<Role> update(@RequestBody Role role){
		ResponseData<Role> response = new ResponseData<Role>();
		try {
			roleService.update(role);
			response.setStatus(RESPONSE_STATUS.SUCCESS);
			response.setMessage(MessageContants.STATUS.SUCCESS);
		}catch (Exception e){
			response.setStatus(RESPONSE_STATUS.FAILED);
			response.setMessage(MessageContants.STATUS.FAILED);
		}

		return response;
	}

	@PostMapping("/create")
	public ResponseData<Role> create(@RequestBody Role role){
		ResponseData<Role> responseData = new ResponseData<>();

		try {
			roleService.create(role);
			responseData.setMessage(MessageContants.STATUS.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		}catch (Exception e){
			e.printStackTrace();
			responseData.setMessage(MessageContants.STATUS.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}

		return responseData;
	}
}
