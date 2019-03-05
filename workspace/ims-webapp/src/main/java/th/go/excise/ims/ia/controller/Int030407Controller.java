package th.go.excise.ims.ia.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ia.service.Int030407Service;
import th.go.excise.ims.ia.vo.Int030407Vo;

@Controller
@RequestMapping("/api/ia/int03/04/07")
public class Int030407Controller {
	
	private Logger logger = LoggerFactory.getLogger(Int030407Controller.class);

	@Autowired
	private Int030407Service int030407Service;
	
	@GetMapping("/year/{budgetYear}/{idConfig}")
	@ResponseBody
	public ResponseData<List<Int030407Vo>> findByYear(@PathVariable("budgetYear") String budgetYear, @PathVariable("idConfig") String idConfig) {
		ResponseData<List<Int030407Vo>> response = new ResponseData<List<Int030407Vo>>();
		try {
			List<Int030407Vo> res = int030407Service.findByBudgetYear(budgetYear, idConfig);
			response.setData(res);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.SUCCESS);
			response.setStatus(RESPONSE_STATUS.SUCCESS);

		} catch (Exception e) {
			logger.error("Int030407Controller::findByYear => ", e);
			response.setMessage(ProjectConstant.RESPONSE_MESSAGE.ERROR500);
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
	
}
