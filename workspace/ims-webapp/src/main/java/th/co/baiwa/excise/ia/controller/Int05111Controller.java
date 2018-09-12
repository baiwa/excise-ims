package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int05111FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int05111Vo;
import th.co.baiwa.excise.ia.service.Int05111Service;

@Controller
@RequestMapping("api/ia/int05111")
public class Int05111Controller {
	@Autowired
	private Int05111Service int05111Service;

	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Int05111Vo> findAll(@RequestBody Int05111FormVo formVo) {
		return int05111Service.findAll(formVo);
	}

	@GetMapping("/sector")
	@ResponseBody
	public List<LabelValueBean> sector() {
		return int05111Service.sector();
	}

	@PostMapping("/area")
	@ResponseBody
	public List<LabelValueBean> area(@RequestBody String id) {
		return int05111Service.area(id);
	}

	@PostMapping("/save")
	@ResponseBody
	public Int05111FormVo save(@RequestBody Int05111FormVo formVo) {
		int05111Service.save(formVo);
		return formVo;
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public Int05111FormVo delete(@RequestBody Int05111FormVo formVo) {
		int05111Service.delete(formVo);
		return formVo;
	}
	
	@PostMapping("/listFile")
	@ResponseBody
	public List<String> listFile(@RequestBody String id) {
		return int05111Service.listFile(id);		
	}
}
