package th.co.baiwa.ims.taxaudit.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.ims.taxaudit.dao.jdbc.TaxAuditDao;
import th.co.baiwa.ims.taxaudit.dao.jpa.repository.TaxAuditRepository;
import th.co.baiwa.ims.taxaudit.vo.TaxAuditVo;

@Service
public class TaxAuditService {

	@Autowired
	private TaxAuditRepository taxAuditRepository;

	@Autowired
	private TaxAuditDao taxAuditDao;

	public List<TaxAuditVo> findAll() throws Exception {
		List<TaxAuditVo> list = taxAuditDao.findAll();
		return list;
	}
}
