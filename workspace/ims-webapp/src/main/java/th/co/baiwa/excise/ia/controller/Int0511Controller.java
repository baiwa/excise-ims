package th.co.baiwa.excise.ia.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int0511FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0511Vo;
import th.co.baiwa.excise.ia.service.Int0511Service;

import java.util.List;

@Controller
@RequestMapping("api/ia/int0511")
public class Int0511Controller {

	@Autowired
	private Int0511Service int0511Service;

	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Int0511Vo> findAll(@RequestBody Int0511FormVo formVo) {
		return int0511Service.findAll(formVo);
	}

	@GetMapping("/sector")
	@ResponseBody
	public List<LabelValueBean> sector() {
		return int0511Service.sector();
	}

	@PostMapping("/area")
	@ResponseBody
	public List<LabelValueBean> area(@RequestBody String id) {
		return int0511Service.area(id);
	}

	@PostMapping("/save")
	@ResponseBody
	public Int0511FormVo save(@RequestBody Int0511FormVo formVo) {
		int0511Service.save(formVo);
		return formVo;
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public Int0511FormVo delete(@RequestBody Int0511FormVo formVo) {
		int0511Service.delete(formVo);
		return formVo;
	}
}
