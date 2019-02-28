package th.co.baiwa.buckwaframework.accesscontrol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.accesscontrol.service.RoleOperationService;
import th.co.baiwa.buckwaframework.accesscontrol.vo.RoleOperationFormVo;
import th.co.baiwa.buckwaframework.accesscontrol.vo.RoleOperationVo;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;

@Controller
@RequestMapping("/api/access-control/role/operation")
public class RoleOperationController {

	private static final Logger logger = LoggerFactory.getLogger(RoleOperationController.class);

	private RoleOperationService roleOperationService;

	@Autowired
	public RoleOperationController(RoleOperationService roleOperationService) {
		this.roleOperationService = roleOperationService;
	}

	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<RoleOperationVo> listRoleOperation(@RequestBody RoleOperationFormVo request) {
		DataTableAjax<RoleOperationVo> response = new DataTableAjax<>();
		try {
			response = roleOperationService.list(request);
		} catch (Exception e) {
			logger.error("RoleOperationController listRoleOperation : ", e);
		}
		return response;
	}

	@PostMapping("/list-data")
	@ResponseBody
	public DataTableAjax<RoleOperationVo> listData(@RequestBody RoleOperationFormVo request) {

		DataTableAjax<RoleOperationVo> response = new DataTableAjax<RoleOperationVo>();

		try {
			response = roleOperationService.listData(request);

		} catch (Exception e) {
			logger.error("RoleOperationController listData : ", e);
		}
		return response;
	}

}
