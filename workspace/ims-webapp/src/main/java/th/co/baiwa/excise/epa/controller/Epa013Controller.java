package th.co.baiwa.excise.epa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.vo.Epa013FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa013Vo;
import th.co.baiwa.excise.epa.service.Epa013Service;

@Controller
@RequestMapping("api/epa/epa013")
public class Epa013Controller {
	
	@Autowired
	private Epa013Service epa013Service;

	@PostMapping("/search")
	@ResponseBody
	public DataTableAjax<Epa013Vo> search(@RequestBody Epa013FormVo epa013FormVo) {
		return epa013Service.search(epa013FormVo);
	}
	
}
