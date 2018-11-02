package th.co.baiwa.excise.epa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.vo.Epa031FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa031Vo;
import th.co.baiwa.excise.epa.service.Epa031Service;

@Controller
@RequestMapping("api/epa/epa031")
public class Epa031Controller {
	
	@Autowired
	private Epa031Service epa031Service;
	
	@PostMapping("/search")
	@ResponseBody
	public DataTableAjax<Epa031Vo> search(@RequestBody Epa031FormVo epa031FormVo) {
		return epa031Service.search(epa031FormVo);
	}

}
