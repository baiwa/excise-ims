package co.th.ims.taxaudit.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.th.ims.taxaudit.dao.TaxAuditAnalysisDao;
import co.th.ims.taxaudit.vo.TaxAuditAnalysisVo;

@Service
public class TaxAuditAnalysisService {

	@Autowired
	private TaxAuditAnalysisDao taxAuditAnalysisDao;
	
	public List<TaxAuditAnalysisVo> findAll() {
		List<TaxAuditAnalysisVo> taxs = new ArrayList<>(); 
		taxs = taxAuditAnalysisDao.findAll();
		return taxs;
	}

}
