package th.co.baiwa.buckwaframework.security.persistence.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import th.co.baiwa.buckwaframework.common.persistence.dao.CommonJdbcDao;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.security.persistence.entity.UserAttempt;
import th.co.baiwa.buckwaframework.security.persistence.mapper.UserAttemptRowMapper;

@Repository("UserAttemptDao")
public class UserAttemptDao {
	
	private static final Logger logger = LoggerFactory.getLogger(UserAttemptDao.class);
	
	@Autowired
	private CommonJdbcDao commonJdbcDao;
	
	public void insert(UserAttempt userAttempt) {
		String sql = SqlGeneratorUtils.genSqlInsert("adm_user_attempt", Arrays.asList(
			"username",
			"attempts",
			"last_modified"
		));
		
		commonJdbcDao.executeInsert(sql, new Object[] {
			userAttempt.getUsername(),
			userAttempt.getAttempts(),
			userAttempt.getLastModified(),
		});
	}
	
	public void update(UserAttempt userAttempt) {
		String sql = SqlGeneratorUtils.genSqlUpdate("adm_user_attempt",
			Arrays.asList(
				"attempts",
				"last_modified"
			),
			Arrays.asList("user_attempt_id")
		);
		
		commonJdbcDao.executeInsert(sql, new Object[] {
			userAttempt.getUsername(),
			userAttempt.getAttempts(),
			new Date(),
		});
	}
	
	public UserAttempt findByUsername(String username) {
		logger.info("findByUsername username={}", username);
		
		String sql =
			" SELECT user_attempt_id, username, attempts, last_modified " +
			" FROM adm_user_attempt " +
			" WHERE username = ? ";
		
		UserAttempt userAttempt = commonJdbcDao.executeQueryForObject(sql,
			new Object[] {
				username
			},
			UserAttemptRowMapper.getInstance()
		);
		
		return userAttempt;
	}

}
