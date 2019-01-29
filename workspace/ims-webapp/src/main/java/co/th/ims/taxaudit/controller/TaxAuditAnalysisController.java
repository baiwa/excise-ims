package co.th.ims.taxaudit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.th.baiwa.buckwaframework.common.bean.ResponseData;
import co.th.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import co.th.ims.taxaudit.service.TaxAuditAnalysisService;
import co.th.ims.taxaudit.vo.TaxAuditAnalysisVo;

@RequestMapping("/api/tax-audit/analysis")
@RestController
public class TaxAuditAnalysisController {
	
	@Autowired
	private TaxAuditAnalysisService taxAuditAnalysisService;
	
	@GetMapping("/")
	public ResponseData<List<TaxAuditAnalysisVo>> findAll() {
		ResponseData<List<TaxAuditAnalysisVo>> response = new ResponseData<List<TaxAuditAnalysisVo>>();
		try {
			response.setData(taxAuditAnalysisService.findAll());
			response.setMessage("SUCCESS");
			response.setStatus(RESPONSE_STATUS.SUCCESS);
		} catch (Exception e) {
			response.setMessage("ERROR");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}
