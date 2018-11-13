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
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.vo.Ope0422Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0431Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0461FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0462FormVo;
import th.co.baiwa.excise.ta.service.Ope0431Service;

@Controller
@RequestMapping("api/ta/opo0431")
public class Ope0431Controller {

	@Autowired
	private Ope0431Service ope0431Service;

	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Ope0431Vo> findAll(@RequestBody Ope0462FormVo formVo){
		return ope0431Service.findAll(formVo);
	}

	@GetMapping("/findExciseId")
	@ResponseBody
	public List<LabelValueBean> findExciseId() {
		List<LabelValueBean> dataList = ope0431Service.findExciseId();
		return dataList;
	}
	
	@PostMapping("/findDetails")
	@ResponseBody
	public DataTableAjax<Ope0422Vo> findDetails(@RequestBody Ope0461FormVo formVo){
		return ope0431Service.findDetails(formVo);
	}

}
