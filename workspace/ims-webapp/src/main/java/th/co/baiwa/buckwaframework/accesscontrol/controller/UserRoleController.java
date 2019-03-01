package th.co.baiwa.buckwaframework.accesscontrol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.accesscontrol.service.UserRoleService;
import th.co.baiwa.buckwaframework.accesscontrol.vo.UserRoleFormVo;
import th.co.baiwa.buckwaframework.accesscontrol.vo.UserRoleVo;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;

@Controller
@RequestMapping("/api/access-control/user/role")
public class UserRoleController {
	private static final Logger logger = LoggerFactory.getLogger(UserRoleController.class);

	private UserRoleService userRoleService;

	@Autowired
	public UserRoleController(UserRoleService userRoleService) {
		this.userRoleService = userRoleService;
	}

	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<UserRoleVo> listUserRole(@RequestBody UserRoleFormVo request) {
		DataTableAjax<UserRoleVo> response = new DataTableAjax<>();
		try {
			response = userRoleService.list(request);
		} catch (Exception e) {
			logger.error("UserRoleController listUserRole : ", e);
		}
		return response;
	}

	@PostMapping("/list-data")
	@ResponseBody
	public DataTableAjax<UserRoleVo> listData(@RequestBody UserRoleFormVo request) {

		DataTableAjax<UserRoleVo> response = new DataTableAjax<UserRoleVo>();

		try {
			response = userRoleService.listData(request);

		} catch (Exception e) {
			logger.error("UserRoleController listData : ", e);
		}
		return response;
	}

}
