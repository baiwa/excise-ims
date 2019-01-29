package co.th.ims.role.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.th.baiwa.buckwaframework.common.bean.ResponseData;
import co.th.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import co.th.ims.role.domain.Role;
import co.th.ims.role.service.RoleService;

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
			response.setMessage("SUCCESS");
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			response.setMessage("FAIL");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}

		return response;
	}
}
