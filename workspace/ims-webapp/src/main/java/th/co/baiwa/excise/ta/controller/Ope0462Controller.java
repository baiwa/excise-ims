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
import th.co.baiwa.excise.ta.persistence.vo.Ope0462FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0462Vo;
import th.co.baiwa.excise.ta.service.Ope0462Service;

@Controller
@RequestMapping("api/ta/opo0462")
public class Ope0462Controller {

	@Autowired
	private Ope0462Service ope0462Service;

	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Ope0462Vo> findAll(@RequestBody Ope0462FormVo formVo){
		return ope0462Service.findAll(formVo);
	}

	@GetMapping("/findExciseId")
	@ResponseBody
	public List<LabelValueBean> findExciseId() {
		List<LabelValueBean> dataList = ope0462Service.findExciseId();
		return dataList;
	}

}
