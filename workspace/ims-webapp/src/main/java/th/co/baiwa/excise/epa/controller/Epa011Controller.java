package th.co.baiwa.excise.epa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.vo.Epa011FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa011Vo;
import th.co.baiwa.excise.epa.service.Epa011Service;

@Controller
@RequestMapping("api/epa/epa011")
public class Epa011Controller {

	@Autowired
	private Epa011Service epa011Service;
	
	@PostMapping("/search")
	@ResponseBody
	public DataTableAjax<Epa011Vo> search(@RequestBody Epa011FormVo epa011FormVo) {
		return epa011Service.search(epa011FormVo);
	}
	
}
