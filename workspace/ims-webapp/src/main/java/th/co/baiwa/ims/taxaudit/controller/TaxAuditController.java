package th.co.baiwa.ims.taxaudit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import th.co.baiwa.buckwaframework.common.bean.BusinessException;
import th.co.baiwa.buckwaframework.common.bean.FormResponse;
import th.co.baiwa.ims.taxaudit.service.TaxAuditService;
import th.co.baiwa.ims.taxaudit.vo.TaxAuditVo;

@RequestMapping("/api/tax-audit")
@Controller
public class TaxAuditController {
	@Autowired
	TaxAuditService taxAuditService;

	@GetMapping("/")
	public FormResponse<List<TaxAuditVo>> findAll() {
		FormResponse<List<TaxAuditVo>> response = new FormResponse<>();
		List<TaxAuditVo> list = new ArrayList<>();
		try {
			list = taxAuditService.findAll();
			response.setStatus(0);
			response.setData(list);
		} catch (BusinessException be) {
			response.setStatus(1);
			response.setMessage(be.getMessage());
		} catch (Exception e) {
			response.setStatus(1);
			response.setMessage(e.getMessage());
		}
		return response;
	}
}
