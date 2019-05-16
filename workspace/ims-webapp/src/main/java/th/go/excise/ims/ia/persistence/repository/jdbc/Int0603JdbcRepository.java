package th.go.excise.ims.ia.persistence.repository.jdbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;

@Repository
public class Int0603JdbcRepository {

	private static final Logger logger = LoggerFactory.getLogger(Int0603JdbcRepository.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	
}
