package th.co.baiwa.excise.epa.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.BusinessException;
import th.co.baiwa.buckwaframework.common.bean.FormResponse;
import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.epa.persistence.vo.Epa011DtlVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa011FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa011Vo;
import th.co.baiwa.excise.epa.persistence.vo.InvhdrFormVo;
import th.co.baiwa.excise.epa.service.Epa011Service;

@Controller
@RequestMapping("api/epa/epa011")
public class Epa011Controller {
	private static final Logger logger = LoggerFactory.getLogger(Epa011Controller.class);
	
	@Autowired
	private Epa011Service epa011Service;

	@PostMapping("/search")
	@ResponseBody
	public DataTableAjax<Epa011Vo> search(@RequestBody Epa011FormVo epa011FormVo) {
		return epa011Service.search(epa011FormVo);
	}

	@PostMapping("/searchDetail")
	@ResponseBody
	public DataTableAjax<Epa011DtlVo> searchDetail(@RequestBody Epa011FormVo epa011FormVo) {
		return epa011Service.searchDetail(epa011FormVo);
	}

	@PostMapping("/getDetail")
	@ResponseBody
	public Epa011Vo getDetail(@RequestBody Epa011FormVo epa011FormVo) {
		return epa011Service.getDetail(epa011FormVo);
	}

	@PostMapping("/getInvDetail")
	@ResponseBody
	public FormResponse<InvhdrFormVo> getInvDetail(@RequestBody InvhdrFormVo InvhdrFormVo) {
		FormResponse<InvhdrFormVo> response = new FormResponse<InvhdrFormVo>();
		try {
			InvhdrFormVo data = epa011Service.getInvDetail(InvhdrFormVo);
			response.setData(data);
			response.setStatus(0);
		} catch (BusinessException be) {
			response.setStatus(1);
			response.setMessage(be.getErrorDesc());
			logger.error("Epa011Controller::getInvDetail", be.getMessage());
		} catch (Exception e) {
			response.setStatus(1);
			response.setMessage(e.getMessage());
			logger.error("Epa011Controller::getInvDetail", e);
		}
		return response;
	}

	@PostMapping("/saveInv")
	@ResponseBody
	public InvhdrFormVo saveInv(@RequestBody InvhdrFormVo invhdrFormVo) {
		epa011Service.saveInv(invhdrFormVo);
		return invhdrFormVo;
	}

}
