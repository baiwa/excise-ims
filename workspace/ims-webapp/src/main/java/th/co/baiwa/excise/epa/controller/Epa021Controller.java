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
import th.co.baiwa.excise.epa.service.Epa021Service;

@Controller
@RequestMapping("api/epa/epa021")
public class Epa021Controller {

	@Autowired
	private Epa021Service epa021Service;
	
	@PostMapping("/search")
	@ResponseBody
	public DataTableAjax<Epa011Vo> search(@RequestBody Epa011FormVo epa011FormVo) {
		return epa021Service.search(epa011FormVo);
	}

	@PostMapping("/searchDetail")
	@ResponseBody
	public DataTableAjax<Epa011DtlVo> searchDetail(@RequestBody Epa011FormVo epa011FormVo) {
		return epa021Service.searchDetail(epa011FormVo);
	}
	
	
	@PostMapping("/getDetail")
	@ResponseBody
	public Epa011Vo getDetail(@RequestBody Epa011FormVo epa011FormVo) {
		return epa021Service.getDetail(epa011FormVo);
	}
	
	@PostMapping("/getInvDetail")
	@ResponseBody
	public InvhdrFormVo getInvDetail(@RequestBody InvhdrFormVo InvhdrFormVo) {
		 return epa021Service.getInvDetailFac(InvhdrFormVo);
	}
	
	@PostMapping("/saveInv")
	@ResponseBody
	public InvhdrFormVo saveInv(@RequestBody InvhdrFormVo invhdrFormVo) {
		epa021Service.saveInv(invhdrFormVo);
		return invhdrFormVo;
	}
	
	
	@PostMapping("/getInvDetailReport")
	@ResponseBody
	public InvhdrFormVo getInvDetailReport(@RequestBody InvhdrFormVo InvhdrFormVo) {
		 return epa021Service.getInvDetailReport(InvhdrFormVo);
	}
	
}
