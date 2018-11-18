package th.co.baiwa.excise.ia.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.constant.IaConstant;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int0614FormSearchVo;
import th.co.baiwa.excise.ia.service.Int0614Service;

@Controller
@RequestMapping("api/ia/int0614")
public class Int0614Controller {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private Int0614Service int0614Service;
	
	@PostMapping("/search")
	@ResponseBody
	public DataTableAjax<Int0614FormSearchVo> search(@ModelAttribute Int0614FormSearchVo form) {
		return int0614Service.search(form);
	}

	@PostMapping("/save")
	@ResponseBody
	public Int0614FormSearchVo save(@RequestBody Int0614FormSearchVo form,HttpServletRequest httpServletRequest){
		
		List<Int0614FormSearchVo> list = new ArrayList<>();		
		list = form.getDataList();
		httpServletRequest.getSession().setAttribute(IaConstant.WITHDRAWAL.TITLE.NUMBER_WITHDRAW, list);
		logger.info("Set session Int0614Controller");
		return form;
	}
}
