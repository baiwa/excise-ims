package th.co.baiwa.excise.ia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int05112Vo;
import th.co.baiwa.excise.ia.service.Int05112Service;

@Controller
@RequestMapping("api/ia/int05112")
public class Int05112Controller {

	@Autowired
	private Int05112Service int05112Service;
	
	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Int05112Vo> findAll(@RequestBody Int05112Vo req) {
		return int05112Service.findAll(req);
	}

}
