package th.co.baiwa.excise.epa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.vo.Epa021FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa021Vo;
import th.co.baiwa.excise.epa.service.Epa021Service;

@Controller
@RequestMapping("api/epa/epa021")
public class Epa021Controller {

	@Autowired
	private Epa021Service epa021Service;
	
	@PostMapping("/search")
	@ResponseBody
	public DataTableAjax<Epa021Vo> search(@RequestBody Epa021FormVo epa021FormVo) {
		return epa021Service.search(epa021FormVo);
	}
}
