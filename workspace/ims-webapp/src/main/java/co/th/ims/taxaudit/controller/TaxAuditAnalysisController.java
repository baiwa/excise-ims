package co.th.ims.taxaudit.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.th.baiwa.buckwaframework.common.bean.ResponseData;
import co.th.baiwa.buckwaframework.common.constant.ProjectConstant.RESPONSE_STATUS;
import co.th.ims.taxaudit.dao.jdbc.TaxAuditAnalysisDao;
import co.th.ims.taxaudit.dao.jpa.entity.TaxAuditAnalysisEntity;
import co.th.ims.taxaudit.dao.jpa.repository.TaxAuditAnalysisRepository;

@RequestMapping("/app/tax-audit/analysis")
@RestController
public class TaxAuditAnalysisController {
	
	@Autowired
	private TaxAuditAnalysisRepository taxAuditAnalysisRepository;
	
	@Autowired
	private TaxAuditAnalysisDao taxAuditAnalysisDao;
	
	@GetMapping("/")
	public ResponseData<List<TaxAuditAnalysisEntity>> findAll() {
		ResponseData<List<TaxAuditAnalysisEntity>> response = new ResponseData<>();
		List<TaxAuditAnalysisEntity> taxs = new ArrayList<>(); 
		try {
			taxs = (List<TaxAuditAnalysisEntity>) taxAuditAnalysisRepository.findAll();
			response.setData(taxs);
			response.setMessage("SUCCESS");
			response.setStatus(RESPONSE_STATUS.SUCCEED);
		} catch (Exception e) {
			response.setMessage("ERROR");
			response.setStatus(RESPONSE_STATUS.FAILED);
		}
		return response;
	}
}
