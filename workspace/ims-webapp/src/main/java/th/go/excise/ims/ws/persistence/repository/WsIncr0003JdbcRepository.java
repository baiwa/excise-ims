package th.go.excise.ims.ws.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;

@Repository
public class WsIncr0003JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

}
