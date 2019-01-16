package th.co.baiwa.excise.epa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.vo.Epa032FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa032Vo;
import th.co.baiwa.excise.epa.service.Epa032Service;

@Controller
@RequestMapping("api/epa/epa032")
public class Epa032Controller {
	
	@Autowired
	private Epa032Service epa032Service;

	@PostMapping("/search")
	@ResponseBody
	public DataTableAjax<Epa032Vo> search(@RequestBody Epa032FormVo epa032FormVo) {
		return epa032Service.search(epa032FormVo);
	}
	
}
