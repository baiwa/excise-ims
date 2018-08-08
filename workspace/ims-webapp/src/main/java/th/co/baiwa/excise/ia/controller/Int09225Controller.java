package th.co.baiwa.excise.ia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.ia.persistence.vo.Int09225FormVo;

@Controller
@RequestMapping("api/ia/int09225")
public class Int09225Controller {

	@PostMapping("/upload")
	@ResponseBody
	public Int09225FormVo upload(@ModelAttribute Int09225FormVo int09225FormVo) {
				
		return int09225FormVo;
	}
}
 