package th.go.excise.ims.ia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ia.vo.Int120401FormVo;
import th.go.excise.ims.ia.vo.Int120401Vo;

@Controller
@RequestMapping("api/ia/int12/04/01")
public class Int120401Controller {
	@Autowired
	private Int120401Service int120401Service;
	
//	@PostMapping("/findAll")
//	@ResponseBody
//	public DataTableAjax<Int120401Vo> findAll(@RequestBody Int120401FormVo formVo){
//		return int120401Service.findAll(formVo);
//	}
}
