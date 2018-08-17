package th.co.baiwa.excise.ta.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.constant.CreatePaperConstants.CREATEPAPERCONSTANTS;
import th.co.baiwa.excise.domain.form.AccMonth0407DTLVo;
import th.co.baiwa.excise.ta.service.ReceiveRmatWsDetailService;

@Controller
@RequestMapping("api/ope")
public class OpeController {
	
	@Autowired
	private ReceiveRmatWsDetailService receiveRmatWsDetailService;	
	
	@PostMapping("/SaveTable")
	@ResponseBody
	public ResponseEntity<?> listdata(HttpServletRequest httpServletRequest) {
		List<AccMonth0407DTLVo> dataSesion = (List<AccMonth0407DTLVo>) httpServletRequest.getSession().getAttribute(CREATEPAPERCONSTANTS.TABLE_ACC_MONTH);
		try {
			receiveRmatWsDetailService.insertReceiveRmatWsDetailService(dataSesion);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
