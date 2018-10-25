package th.co.baiwa.excise.epa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.vo.Epa012FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa012Vo;
import th.co.baiwa.excise.epa.service.Epa012Service;

@Controller
@RequestMapping("api/epa/epa012")
public class Epa012Controller {

	@Autowired
	private Epa012Service epa012Service;
	
	@PostMapping("/search")
	@ResponseBody
	public DataTableAjax<Epa012Vo> search(@RequestBody Epa012FormVo epa012FormVo) {
		return epa012Service.search(epa012FormVo);
	}
	
}
