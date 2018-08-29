package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.ia.persistence.vo.Int09213FormVo;
import th.co.baiwa.excise.ia.service.Int09223Service;

@Controller
@RequestMapping("api/ia/int09223")
public class Int09223Controller {

	@Autowired
	private Int09223Service int09223Service;
	
	@PostMapping("/listDropdown")
	@ResponseBody
	public List<LabelValueBean> dropdownList(@RequestBody Int09213FormVo formVo){
		return int09223Service.dropdownListType(formVo);
	}
	
	@PostMapping("/listDropdown2")
	@ResponseBody
	public List<LabelValueBean> dropdownList2(@RequestBody Int09213FormVo formVo){
		return int09223Service.dropdownListType2(formVo);
	}
}

