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
import th.go.excise.ims.ia.persistence.entity.IaExpenses;
import th.go.excise.ims.ia.service.Int120401Service;
import th.go.excise.ims.ia.vo.Int120401FormVo;

@Controller
@RequestMapping("api/ia/int12/04/01")
public class Int120401Controller {
	@Autowired
	private Int120401Service int120401Service;

	private static final Logger logger = LoggerFactory.getLogger(Int120401Service.class);
	
	@PostMapping("/findByYearByCoa")
	@ResponseBody
	public DataTableAjax<IaExpenses> findByYearByCoa(@RequestBody Int120401FormVo form) {
		DataTableAjax<IaExpenses> response = new DataTableAjax<IaExpenses>();
		List<IaExpenses> systemUnworkingList = new ArrayList<IaExpenses>();

		try {
			systemUnworkingList = int120401Service.findByYearByCoa(form);
			response.setData(systemUnworkingList);

		} catch (Exception e) {
			logger.error("Int120401Service findByYearByCoa : ", e);
		}
		return response;
	}
}
