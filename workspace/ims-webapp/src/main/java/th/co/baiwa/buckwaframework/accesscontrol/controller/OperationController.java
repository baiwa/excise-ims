package th.co.baiwa.buckwaframework.accesscontrol.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.Operation;
import th.co.baiwa.buckwaframework.accesscontrol.service.OperationService;
import th.co.baiwa.buckwaframework.accesscontrol.vo.OperationFormVo;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;

@Controller
@RequestMapping("/api/access-control/operation")
public class OperationController {
	
	private static final Logger logger = LoggerFactory.getLogger(OperationController.class);

	private OperationService operationService;

	@Autowired
	public OperationController(OperationService operationService) {
		this.operationService = operationService;
	}
	
	
	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Operation> listOperation(@RequestBody OperationFormVo operationFormVo) {
		logger.info("listOperation");

		DataTableAjax<Operation> response = new DataTableAjax<>();
		try {
			response = operationService.list(operationFormVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}


}
