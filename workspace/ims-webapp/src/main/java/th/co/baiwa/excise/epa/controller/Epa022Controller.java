package th.co.baiwa.excise.epa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.vo.Epa022FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa022Vo;
import th.co.baiwa.excise.epa.service.Epa022Service;

@Controller
@RequestMapping("api/epa/epa022")
public class Epa022Controller {

	@Autowired
	private Epa022Service epa022Service;
	
	@PostMapping("/search")
	@ResponseBody
	public DataTableAjax<Epa022Vo> search(@RequestBody Epa022FormVo epa022FormVo) {
		return epa022Service.search(epa022FormVo);
	}
	
}
