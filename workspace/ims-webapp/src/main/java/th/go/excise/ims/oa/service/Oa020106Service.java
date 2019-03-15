package th.go.excise.ims.oa.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.repository.jdbc.Oa020106JdbcRepository;
import th.go.excise.ims.oa.vo.Oa020106ButtonVo;

@Service
public class Oa020106Service {
	
	@Autowired
	private Oa020106JdbcRepository oa020106JdbcRep;
	
	public Oa020106ButtonVo findButtonById(String idStr) {
		BigDecimal id = new BigDecimal(idStr);
		return oa020106JdbcRep.findButtonIdById(id);
	}

}
