package th.co.baiwa.buckwaframework.admin.persistence.dao;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.admin.persistence.entity.User;
import th.co.baiwa.buckwaframework.admin.persistence.mapper.UserRowMapper;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.dao.CommonJdbcDao;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;

@Repository("userDao")
public class UserDao {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDao.class);
	
	@Autowired
	private CommonJdbcDao commonJdbcDao;
	
	public List<User> findAll() {
		logger.debug("findAll");
		
		String sql =
			" SELECT user_id, username, password, enabled, account_non_expired, " +
			"        credentials_non_expired, account_non_locked " +
			" FROM adm_user " +
			" WHERE is_deleted = ? ";
		
		return commonJdbcDao.executeQuery(sql,
			new Object[] {
				FLAG.N_FLAG
			},
			UserRowMapper.getInstance()
		);
	}
	
	public User findById(Long userId) {
		logger.debug("findById userId={}", userId);
		
		String sql =
			" SELECT user_id, username, password, enabled, account_non_expired, " +
			"        credentials_non_expired, account_non_locked " +
			" FROM adm_user " +
			" WHERE is_deleted = ? " +
			"   AND user_id = ? ";
		
		return commonJdbcDao.executeQueryForObject(sql,
			new Object[] {
				FLAG.N_FLAG,
				userId
			},
			UserRowMapper.getInstance()
		);
	}
	
	public User findByUsername(String username) {
		logger.debug("findByUsername username={}", username);
		
		String sql =
			" SELECT user_id, username, password, enabled, account_non_expired, " +
			"        credentials_non_expired, account_non_locked " +
			" FROM adm_user " +
			" WHERE is_deleted = ? " +
			"   AND username = ? ";
		
		return commonJdbcDao.executeQueryForObject(sql,
			new Object[] {
				FLAG.N_FLAG,
				username
			},
			UserRowMapper.getInstance()
		);
	}
	
	public int count() {
		logger.debug("count");
		
		String sql = SqlGeneratorUtils.genSqlCount("adm_user", Arrays.asList("is_deleted"));
		
		return commonJdbcDao.executeQueryForObject(sql,
			new Object[] {
				FLAG.N_FLAG
			},
			Integer.class
		);
	}
	
	public Long insert(User user) {
		logger.debug("insert");
		
		String sql = SqlGeneratorUtils.genSqlInsert("adm_user", Arrays.asList(
			"username",
			"password",
			"enabled",
			"account_non_expired",
			"credentials_non_expired",
			"account_non_locked",
			"created_by",
			"created_date"
		));
		
		Long key = commonJdbcDao.executeInsertWithKeyHolder(sql.toString(), new Object[] {
			user.getUsername(),
			user.getPassword(),
			user.getEnabled(),
			user.getAccountNonExpired(),
			user.getCredentialsNonExpired(),
			user.getAccountNonLocked(),
			UserLoginUtils.getCurrentUsername(),
			new Date()
		});
		
		return key;
	}
	
	public int update(User user) {
		logger.debug("update");
		
		String sql = SqlGeneratorUtils.genSqlUpdate("adm_user",
			Arrays.asList(
				"username",
				"password",
				"enabled",
				"account_non_expired",
				"credentials_non_expired",
				"account_non_locked",
				"updated_by",
				"updated_date"
			),
			Arrays.asList(
				"user_id"
			)
		);
		
		int updateRow = commonJdbcDao.executeUpdate(sql.toString(), new Object[] {
			user.getUsername(),
			user.getPassword(),
			user.getEnabled(),
			user.getAccountNonExpired(),
			user.getCredentialsNonExpired(),
			user.getAccountNonLocked(),
			UserLoginUtils.getCurrentUsername(),
			new Date(),
			user.getUserId()
		});
		
		return updateRow;
	}
	
	public int delete(Long userId) {
		logger.debug("delete");
		
		String sql = SqlGeneratorUtils.genSqlUpdate("adm_user",
			Arrays.asList(
				"is_deleted",
				"updated_by",
				"updated_date"
			),
			Arrays.asList(
				"user_id"
			)
		);
		
		int deleteRow = commonJdbcDao.executeUpdate(sql.toString(), new Object[] {
			FLAG.Y_FLAG,
			UserLoginUtils.getCurrentUsername(),
			new Date(),
			userId
		});
		
		return deleteRow;
	}
	
}