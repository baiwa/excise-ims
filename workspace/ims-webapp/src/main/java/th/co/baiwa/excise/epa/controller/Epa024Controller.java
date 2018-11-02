package th.co.baiwa.excise.epa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.vo.Epa024FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa024Vo;
import th.co.baiwa.excise.epa.service.Epa024Service;

@Controller
@RequestMapping("api/epa/epa024")
public class Epa024Controller {

	@Autowired
	private Epa024Service epa024Service;

	@PostMapping("/search")
	@ResponseBody
	public DataTableAjax<Epa024Vo> search(@RequestBody Epa024FormVo epa024FormVo) {
		return epa024Service.search(epa024FormVo);
	}
}
