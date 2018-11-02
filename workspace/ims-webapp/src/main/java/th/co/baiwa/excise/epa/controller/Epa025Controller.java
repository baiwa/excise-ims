package th.co.baiwa.excise.epa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.vo.Epa025FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa025Vo;
import th.co.baiwa.excise.epa.service.Epa025Service;

@Controller
@RequestMapping("api/epa/epa025")
public class Epa025Controller {

	@Autowired
	private Epa025Service epa025Service;

	@PostMapping("/search")
	@ResponseBody
	public DataTableAjax<Epa025Vo> search(@RequestBody Epa025FormVo epa025FormVo) {
		return epa025Service.search(epa025FormVo);
	}
}