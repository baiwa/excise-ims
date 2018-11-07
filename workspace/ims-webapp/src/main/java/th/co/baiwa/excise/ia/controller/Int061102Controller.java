package th.co.baiwa.excise.ia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.ia.persistence.vo.Int061102FormVo;
import th.co.baiwa.excise.ia.service.Int061102Service;

@Controller
@RequestMapping("api/ia/int061102")
public class Int061102Controller {

	@Autowired
	private Int061102Service int061102Service;
	
	@PostMapping("/save")
	@ResponseBody
	public Int061102FormVo save(@RequestBody Int061102FormVo int061102FormVo) {
		int061102Service.save(int061102FormVo);
		return int061102FormVo;
	}
	
}
