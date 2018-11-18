package th.co.baiwa.excise.ia.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.constant.IaConstant;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int0615FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0615Vo;
import th.co.baiwa.excise.ia.service.Int0615Service;

@Controller
@RequestMapping("api/ia/int0615")
public class Int0615Controller {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private Int0615Service int0615Service;

	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Int0615Vo> findAll(@RequestBody Int0615FormVo formVo) {
		return int0615Service.findAll(formVo);
	}

	@PostMapping("/save")
	@ResponseBody
	public Int0615FormVo save(@RequestBody Int0615FormVo formVo, HttpServletRequest httpServletRequest) {

		List<Int0615Vo> list = new ArrayList<>();
		list = formVo.getDataList();
		httpServletRequest.getSession().setAttribute(IaConstant.WITHDRAWAL.TITLE.CHECK_NUMBER_WITHDRAW, list);
		logger.info("Set session Int0614Controller");
		return formVo;
	}

}
