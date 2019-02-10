package th.go.excise.ims.ta.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import th.co.baiwa.buckwaframework.common.bean.ResponseData;
import th.co.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import th.go.excise.ims.ta.service.TaxAuditAnalysisService;
import th.go.excise.ims.ta.vo.TaxAuditAnalysisVo;

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
