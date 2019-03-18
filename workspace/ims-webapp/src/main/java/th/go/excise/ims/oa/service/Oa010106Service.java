package th.go.excise.ims.oa.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.repository.jdbc.Oa010106JdbcRepository;
import th.go.excise.ims.oa.vo.Oa010106ButtonVo;

@Service
public class Oa010106Service {
	
	@Autowired
	private Oa010106JdbcRepository oa010106JdbcRep;
	
	public Oa010106ButtonVo findButtonById(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		return oa010106JdbcRep.findButtonIdById(id);
	}
	
}
