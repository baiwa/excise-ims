package th.go.excise.ims.oa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0102JdbcRepository;
import th.go.excise.ims.oa.utils.OaOfficeCode;
import th.go.excise.ims.oa.vo.Oa0102Vo;

@Service
public class Oa0102Service {

	@Autowired
	private Oa0102JdbcRepository oa0102JdbcRep;

	public List<Oa0102Vo> findAll(String offCode, int addDate) {
		return oa0102JdbcRep.findAll(OaOfficeCode.officeCodeLike(offCode), addDate);
	}

}
