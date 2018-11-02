package th.co.baiwa.excise.cop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.cop.service.Cop0612Service;
import th.co.baiwa.excise.ta.persistence.vo.Ope043DataTable;
import th.co.baiwa.excise.ta.service.MaterialsWsService;

@Controller
@RequestMapping("api/cop0612")
public class Cop0612Controller {
	
	@Autowired
	private Cop0612Service cop0612Service;
	
	
	@PostMapping("/saveTable")
	@ResponseBody
	public ResponseEntity<?> listdata(@RequestBody List<Ope043DataTable> allData) {
		try {
//			cop0612Service
			cop0612Service.cop0612Service(allData);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
