package th.co.baiwa.excise.ta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.vo.Tsl010600FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Tsl010600Vo;
import th.co.baiwa.excise.ta.service.Tsl010600Service;

@Controller
@RequestMapping("api/taxAudit/display")
public class Tsl010600Controller {

	@Autowired
	private Tsl010600Service tsl010600Service;

	@PostMapping("/search")
	@ResponseBody
	public DataTableAjax<Tsl010600Vo> search(@RequestBody Tsl010600FormVo tsl010600FormVo) {
		String username = UserLoginUtils.getCurrentUserBean().getUsername();
		tsl010600FormVo.setUser(username);
		return tsl010600Service.search(tsl010600FormVo);
	}

}
