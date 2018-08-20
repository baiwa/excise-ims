package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.ia.persistence.vo.Int09212FormVo;
import th.co.baiwa.excise.ia.service.HeaderDetailService;

@Controller
@RequestMapping("api/ia/int09212")
public class Int09212Controller {

//	private static Logger logger = LoggerFactory.getLogger(Int09212Controller.class);

	@Autowired
	private HeaderDetailService headerDetailService;

	@GetMapping("/dropdownHeader")
	@ResponseBody
	public List<LabelValueBean> dropdownHeader() {
		List<LabelValueBean> result = headerDetailService.dropdownHeader();
		return result;
	}

	@PostMapping("/listDropdown")
	@ResponseBody
	public List<LabelValueBean> dropdownList(@RequestBody Int09212FormVo formVo) {

		List<LabelValueBean> result = headerDetailService.dropdownListType(formVo);
		return result;
	}

	@PostMapping("/save")
	@ResponseBody
	public ResponseEntity<?> save(@RequestBody Int09212FormVo formVo) {
		try {
			Long headerId = headerDetailService.save(formVo);
			return new ResponseEntity<>(headerId,HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}

	

}
