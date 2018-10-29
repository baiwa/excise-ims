package th.co.baiwa.excise.epa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.vo.Epa014FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa014Vo;
import th.co.baiwa.excise.epa.service.Epa014Service;

@Controller
@RequestMapping("api/epa/epa014")
public class Epa014Controller {

	@Autowired
	private Epa014Service epa014Service;
	
	@PostMapping("/search")
	@ResponseBody DataTableAjax<Epa014Vo> search(@RequestBody Epa014FormVo epa014FormVo) {
		return epa014Service.search(epa014FormVo);
	}
	
}
