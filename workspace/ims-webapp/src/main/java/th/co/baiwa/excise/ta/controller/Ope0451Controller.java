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
import th.co.baiwa.excise.ta.persistence.vo.Ope0451FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope045Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0461FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0462Vo;
import th.co.baiwa.excise.ta.service.Ope0451Service;

@Controller
@RequestMapping("api/ta/opo0451")
public class Ope0451Controller {

	@Autowired
	private Ope0451Service ope0451Service;

	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Ope0462Vo> findAll(@RequestBody Ope0451FormVo formVo){
		return ope0451Service.findAll(formVo);
	}

	@GetMapping("/findExciseId")
	@ResponseBody
	public List<LabelValueBean> findExciseId() {
		List<LabelValueBean> dataList = ope0451Service.findExciseId();
		return dataList;
	}

	@PostMapping("/findDetails")
	@ResponseBody
	public DataTableAjax<Ope045Vo> findDetails(@RequestBody Ope0461FormVo formVo){
		return ope0451Service.findDetails(formVo);
	}
}
