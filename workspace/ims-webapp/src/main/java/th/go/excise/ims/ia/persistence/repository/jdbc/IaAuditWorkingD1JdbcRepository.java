package th.go.excise.ims.ia.persistence.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;

@Repository
public class IaAuditWorkingD1JdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
}
