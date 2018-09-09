package th.co.baiwa.excise.ia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.ia.persistence.vo.Int053FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int053Vo;
import th.co.baiwa.excise.ia.service.Int053Service;

@Controller
@RequestMapping("api/ia/int053")
public class Int053Controller {

	@Autowired
	private Int053Service int053Service;
	
	@PostMapping("/upload")
	@ResponseBody
	public ResponseEntity<?> upload(@ModelAttribute Int053FormVo formVo) {
		return new ResponseEntity<Int053Vo>(int053Service.upload(formVo.getFile()), HttpStatus.OK);
	}
}
