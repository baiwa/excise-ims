package th.co.baiwa.excise.ta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.ta.persistence.vo.Ope04011FormVo;
import th.co.baiwa.excise.ta.service.Ope04011Service;

@Controller
@RequestMapping("api/ta/opo04011")
public class Ope04011Controller {

	@Autowired
	private Ope04011Service ope04011Service;
	

	@GetMapping("/findExciseId")
	@ResponseBody
	public List<LabelValueBean> findExciseId() {
		List<LabelValueBean> dataList = ope04011Service.findExciseId();
		return dataList;
	}
	
	@PostMapping("/findByExciseId")
	@ResponseBody
	public Ope04011FormVo findByExciseId(@RequestBody Ope04011FormVo formVo) {
		Ope04011FormVo form = ope04011Service.findByExciseId(formVo);
		return form;
	}
	
	@PostMapping("/save")
	@ResponseBody
	public Ope04011FormVo save(@RequestBody Ope04011FormVo formVo){
		ope04011Service.save(formVo);
		return formVo;
	}
}
