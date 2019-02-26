package th.co.baiwa.buckwaframework.accesscontrol.controller;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponentsBuilder;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.Role;
import th.co.baiwa.buckwaframework.accesscontrol.service.RoleService;
import th.co.baiwa.buckwaframework.accesscontrol.vo.RoleFormVo;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.bean.ResponseData;

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

	/*
	 * @GetMapping("list") public ResponseEntity<?> getAll(@RequestParam("draw")
	 * Integer draw, @RequestParam("start") Integer start, @RequestParam("length")
	 * Integer length) { logger.info("getAll");
	 * 
	 * List<Role> resultList = roleService.getRoleAll(); Integer recordsTotal =
	 * resultList.size();
	 * 
	 * ResponseDataTable<Role> response = new ResponseDataTable<Role>();
	 * response.setDraw(draw); response.setStart(start); response.setLength(length);
	 * response.setData(resultList); response.setRecordsTotal(recordsTotal);
	 * response.setRecordsFiltered(recordsTotal);
	 * 
	 * return new ResponseEntity<ResponseDataTable<Role>>(response, HttpStatus.OK);
	 * }
	 */

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
//	@PostMapping
//	public ResponseEntity<?> create(@RequestBody Role role, UriComponentsBuilder ucBuilder) {
//		logger.info("create [role={}]", role);
//
//		Role newRole = roleService.createRole(role);
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(
//				ucBuilder.path("/api/access-control/role/{id}").buildAndExpand(newRole.getRoleId()).toUri());
//
//		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
//	}
//
//	@PutMapping
//	public ResponseEntity<?> update(@RequestBody Role role, UriComponentsBuilder ucBuilder) {
//		logger.info("update [role={}]", role);
//
//		roleService.updateRole(role);
//		HttpHeaders headers = new HttpHeaders();
//		headers.setLocation(ucBuilder.path("/api/access-control/role/{id}").buildAndExpand(role.getRoleId()).toUri());
//
//		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
//	}
//
//	@DeleteMapping("/{id}")
//	public ResponseEntity<?> delete(@PathVariable("id") long id) {
//		logger.info("delete [id={}]", id);
//
//		roleService.deleteRole(id);
//		return new ResponseEntity<Role>(HttpStatus.NO_CONTENT);
//	}

}
