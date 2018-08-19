package th.co.baiwa.excise.ta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.form.OPEDataTable;
import th.co.baiwa.excise.ta.service.ReceiveRmatWsDetailService;

@Controller
@RequestMapping("api/ope")
public class OpeController {

	@Autowired
	private ReceiveRmatWsDetailService receiveRmatWsDetailService;

	@PostMapping("/SaveTable")
	@ResponseBody
	public ResponseEntity<?> listdata(@RequestBody List<OPEDataTable> allData) {
		try {
			receiveRmatWsDetailService.insertReceiveRmatWsDetailService(allData);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
