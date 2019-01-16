package th.co.baiwa.excise.ta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.vo.Tsl010700FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Tsl010700Vo;
import th.co.baiwa.excise.ta.service.Tsl010700Service;

@Controller
@RequestMapping("api/taxAudit/checkDisplay")
public class Tsl010700Controller {

	@Autowired
	private Tsl010700Service tsl010700Service;
	 
	@PostMapping("/search")
	@ResponseBody
	public DataTableAjax<Tsl010700Vo> search(@RequestBody Tsl010700FormVo tsl010700FormVo) {
		return tsl010700Service.search(tsl010700FormVo);
	}
	
}
