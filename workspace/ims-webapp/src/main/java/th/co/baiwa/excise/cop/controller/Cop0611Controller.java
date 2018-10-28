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

import th.co.baiwa.excise.cop.service.Cop0611Service;
import th.co.baiwa.excise.ta.persistence.vo.Ope041DataTable;

@Controller
@RequestMapping("api/cop0611")
public class Cop0611Controller {
	
	@Autowired
	private Cop0611Service cop0611Service;
	
	@PostMapping("/saveTable")
	@ResponseBody
	public ResponseEntity<?> listdata(@RequestBody List<Ope041DataTable> allData) {
		try {
			cop0611Service.insertDisbRmatWsService(allData);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	

}
