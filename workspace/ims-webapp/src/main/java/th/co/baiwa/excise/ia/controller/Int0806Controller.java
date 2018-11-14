package th.co.baiwa.excise.ia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.entity.DisbursementRequest;
import th.co.baiwa.excise.ia.persistence.entity.MoneyCheck;
import th.co.baiwa.excise.ia.persistence.vo.Int061105FormSearchVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0806FormSearchVo;
import th.co.baiwa.excise.ia.service.Int0806Service;

@Controller
@RequestMapping("api/ia/int0806")

public class Int0806Controller {
	
	@Autowired
	private Int0806Service int0806Service;
	
	@PostMapping("/getValue1")
	@ResponseBody
	public List<Lov> getValue1(@RequestBody Lov en) {
		return int0806Service.getValue1(en);

	}
	
	@PostMapping("/search")
	@ResponseBody
	public DataTableAjax<MoneyCheck> search(Int0806FormSearchVo en) {
		return int0806Service.search(en);
	}

}
