package th.go.excise.ims.ia.controller;


import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ia.service.Int030403Service;
import th.go.excise.ims.ia.vo.Int030403FormVo;
import th.go.excise.ims.ia.vo.Int030403Vo;

@Controller
@RequestMapping("/api/ia/int03/04/03")
public class Int030403Controller {
	
	private Logger logger = LoggerFactory.getLogger(Int030403Controller.class);

	
	@Autowired
	private Int030403Service int030403Service;

	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<Int030403Vo> list(@RequestBody Int030403FormVo form) {
		DataTableAjax<Int030403Vo> response = new DataTableAjax<Int030403Vo>();
		List<Int030403Vo> iaRiskBudgetProject = new ArrayList<Int030403Vo>();
		try {	
			iaRiskBudgetProject = int030403Service.list(form);
			response.setData(iaRiskBudgetProject);
		} catch (Exception e) {
			logger.error("Int0301Controller : " , e);
		}
		return response;
	}
}
