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
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.vo.Ope0411Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0462FormVo;
import th.co.baiwa.excise.ta.service.Ope0411Service;

@Controller
@RequestMapping("api/ta/opo0411")
public class Ope0411Controller {

	@Autowired
	private Ope0411Service ope0411Service;

	@PostMapping("/findAll")
	@ResponseBody
	public DataTableAjax<Ope0411Vo> findAll(@RequestBody Ope0462FormVo formVo){
		return ope0411Service.findAll(formVo);
	}

	@GetMapping("/findExciseId")
	@ResponseBody
	public List<LabelValueBean> findExciseId() {
		List<LabelValueBean> dataList = ope0411Service.findExciseId();
		return dataList;
	}

}
