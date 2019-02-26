package th.co.baiwa.buckwaframework.accesscontrol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.Role;
import th.co.baiwa.buckwaframework.accesscontrol.service.RoleService;
import th.co.baiwa.buckwaframework.accesscontrol.vo.RoleFormVo;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_MESSAGE;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;

@Controller
@RequestMapping("/api/access-control/role")
public class RoleController {

	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);

	private RoleService roleService;

	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Role> listRole(@RequestBody RoleFormVo request) {
		logger.info("listRole");

		DataTableAjax<Role> response = new DataTableAjax<>();
		try {
			response = roleService.list(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	@PostMapping("/create")
	@ResponseBody
	public ResponseData<Role> createRole(@RequestBody Role request) {
		ResponseData<Role> responseData = new ResponseData<Role>();
		try {
			responseData.setData(roleService.createRole(request));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("RoleController::create ", e);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@PutMapping("/update/{id}")
	@ResponseBody
	public ResponseData<Role> update(@PathVariable("id") String idStr, @RequestBody Role role) {
		ResponseData<Role> responseData = new ResponseData<Role>();
		try {
			responseData.setData(roleService.updateRole(idStr, role));
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("RoleController::update ", e);
			responseData.setMessage(RESPONSE_MESSAGE.SAVE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

	@DeleteMapping("/delete/{id}")
	@ResponseBody
	public ResponseData<Role> delete(@PathVariable("id") String idStr) {
		ResponseData<Role> responseData = new ResponseData<Role>();
		try {
			responseData.setData(roleService.deleteRole(idStr));
			responseData.setMessage(RESPONSE_MESSAGE.DELETE.SUCCESS);
			responseData.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			logger.error("RoleController::delete ", e);
			responseData.setMessage(RESPONSE_MESSAGE.DELETE.FAILED);
			responseData.setStatus(RESPONSE_STATUS.FAILED);
		}
		return responseData;
	}

//	@GetMapping("/{id}")
//	public ResponseEntity<?> getRole(@PathVariable("id") long id) {
//		logger.info("getRole [id={}]", id);
//
//		Role role = roleService.getRoleById(id);
//		ResponseData<Role> response = new ResponseData<Role>();
//		response.setData(role);
//		return new ResponseEntity<ResponseData<Role>>(response, HttpStatus.OK);
//	}
//

}
