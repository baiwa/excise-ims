package th.co.baiwa.excise.epa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.vo.Epa011DtlVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa011FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa011Vo;
import th.co.baiwa.excise.epa.persistence.vo.InvhdrFormVo;
import th.co.baiwa.excise.epa.service.Epa014Service;

@Controller
@RequestMapping("api/epa/epa014")
public class Epa014Controller {

	@Autowired
	private Epa014Service epa014Service;
	
	@PostMapping("/getDetail")
	@ResponseBody
	public Epa011Vo getDetail(@RequestBody Epa011FormVo epa011FormVo) {
		return epa014Service.getDetail(epa011FormVo);
	}
	
	@PostMapping("/searchDetail")
	@ResponseBody
	public DataTableAjax<Epa011DtlVo> searchDetail(@RequestBody Epa011FormVo epa011FormVo) {
		return epa014Service.searchDetail(epa011FormVo);
	}
	
	@PostMapping("/getInvDetailALL")
	@ResponseBody
	public InvhdrFormVo getInvDetail(@RequestBody InvhdrFormVo InvhdrFormVo) {
		 return epa014Service.getInvDetail(InvhdrFormVo);
	}
	
}
