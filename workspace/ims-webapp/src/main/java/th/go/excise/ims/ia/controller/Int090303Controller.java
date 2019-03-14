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
import th.go.excise.ims.ia.persistence.entity.IaCheckControlRegis;
import th.go.excise.ims.ia.service.Int090303Service;
import th.go.excise.ims.ia.vo.Int0900303FormVo;

@Controller
@RequestMapping("/api/ia/int09/03/03")
public class Int090303Controller {

	private Logger logger = LoggerFactory.getLogger(Int090303Controller.class);
	
	@Autowired
	private Int090303Service int090303Service;

	@PostMapping("/list")
	@ResponseBody
	public DataTableAjax<IaCheckControlRegis> list(@RequestBody Int0900303FormVo form) {
		DataTableAjax<IaCheckControlRegis> response = new DataTableAjax<IaCheckControlRegis>();
		List<IaCheckControlRegis> iaCheckControlRegis = new ArrayList<IaCheckControlRegis>();
		try {	
			iaCheckControlRegis = int090303Service.list(form);
			response.setData(iaCheckControlRegis);
		} catch (Exception e) {
			logger.error("Int090303Controller : " , e);
		}
		return response;
	}
	
	@PostMapping("/budgetTypeDropdown")
	@ResponseBody
	public List<Int0900303FormVo> budgetYearDropdown() {
		List<Int0900303FormVo> response = new ArrayList<Int0900303FormVo>();
		try {	
			response = int090303Service.budgetTypeDropdown();
		} catch (Exception e) {
			logger.error("Int090303Controller BudgetYearDropdown : ", e);
		}
		return response;
	}
	
}
