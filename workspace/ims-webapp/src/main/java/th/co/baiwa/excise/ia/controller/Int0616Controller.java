package th.co.baiwa.excise.ia.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.constant.IaConstant;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int0614FormSearchVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0615FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0615Vo;
import th.co.baiwa.excise.ia.service.Int0616Service;

@Controller
@RequestMapping("api/ia/int0616")
public class Int0616Controller {

	@Autowired
	private Int0616Service int0616Service;

	@SuppressWarnings("unchecked")
	@PostMapping("/list1")
	@ResponseBody
	public DataTableAjax<Int0614FormSearchVo> list1(@RequestBody Int0614FormSearchVo vo,HttpServletRequest httpServletRequest) {

		List<Int0614FormSearchVo> dataSession = (List<Int0614FormSearchVo>) httpServletRequest.getSession().getAttribute(IaConstant.WITHDRAWAL.TITLE.NUMBER_WITHDRAW);
		DataTableAjax<Int0614FormSearchVo> list = new DataTableAjax<>();
		
		try {
			list = int0616Service.list1(vo,dataSession);
		} catch (Exception e) {
			httpServletRequest.getSession().setAttribute(IaConstant.WITHDRAWAL.TITLE.NUMBER_WITHDRAW, new ArrayList<>());
		}
		return list;

	}

	@SuppressWarnings("unchecked")
	@PostMapping("/list2")
	@ResponseBody
	public DataTableAjax<Int0615Vo> list1(@RequestBody Int0615FormVo vo, HttpServletRequest httpServletRequest) {
		
		DataTableAjax<Int0615Vo> list = new DataTableAjax<>();
		List<Int0615Vo> dataSession = (List<Int0615Vo>) httpServletRequest.getSession().getAttribute(IaConstant.WITHDRAWAL.TITLE.CHECK_NUMBER_WITHDRAW);	
		
		try {
			list = int0616Service.list2(vo,dataSession);
		} catch (Exception e) {
			 httpServletRequest.getSession().setAttribute(IaConstant.WITHDRAWAL.TITLE.CHECK_NUMBER_WITHDRAW, new ArrayList<>());
		}
		
		 
		 return list;
	}

}
