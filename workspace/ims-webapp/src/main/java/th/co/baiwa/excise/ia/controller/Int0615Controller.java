package th.co.baiwa.excise.ia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int0615FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0615Vo;
import th.co.baiwa.excise.ia.service.Int0615Service;

@Controller
@RequestMapping("api/ia/int0615")
public class Int0615Controller {

	@Autowired
	private Int0615Service int0615Service;

	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Int0615Vo> findAll(@RequestBody Int0615FormVo formVo) {
		return int0615Service.findAll(formVo);
	}

	

}
