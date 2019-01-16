package th.co.baiwa.excise.ia.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.entity.VerifyAccountDetil;
import th.co.baiwa.excise.ia.service.Int072Service;

@Controller
@RequestMapping("api/ia/int072")
public class Int072Controller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Int072Service int072Service;
	
	@PostMapping("/datatable")
	@ResponseBody
	public DataTableAjax<VerifyAccountDetil> datatable(@RequestBody VerifyAccountDetil vo){
		logger.info("Query DATATABLE Int072!!");
		return int072Service.queryDatatable(vo);
	}

}
