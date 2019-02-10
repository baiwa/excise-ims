package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.repository.TaxAuditAnalysisRepository;
import th.go.excise.ims.ta.vo.TaxAuditAnalysisVo;

@Service
public class TaxAuditAnalysisService {

	@Autowired
	private TaxAuditAnalysisRepository taxAuditAnalysisDao;
	
	public List<TaxAuditAnalysisVo> findAll() {
		List<TaxAuditAnalysisVo> taxs = new ArrayList<>(); 
		taxs = taxAuditAnalysisDao.findAll();
		return taxs;
	}

}
