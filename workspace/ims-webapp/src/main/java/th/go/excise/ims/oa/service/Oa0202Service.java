package th.go.excise.ims.oa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.oa.persistence.repository.jdbc.Oa0202JdbcRepository;
import th.go.excise.ims.oa.utils.OaOfficeCode;
import th.go.excise.ims.oa.vo.Oa0202Vo;

@Service
public class Oa0202Service {

	@Autowired
	private Oa0202JdbcRepository oa0202JdbcRep;

	public List<Oa0202Vo> findAll(String offCode, int addDate) {
		return oa0202JdbcRep.findAll(OaOfficeCode.officeCodeLike(offCode), addDate);
	}

}
