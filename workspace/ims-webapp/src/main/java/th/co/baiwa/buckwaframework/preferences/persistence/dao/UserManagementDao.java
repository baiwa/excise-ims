package th.co.baiwa.buckwaframework.preferences.persistence.dao;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.dao.CommonJdbcDao;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.preferences.constant.UserManagementConstants;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.UserManagement;
import th.co.baiwa.buckwaframework.preferences.persistence.mapper.UserManagementRowMapper;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.utils.OracleUtils;

@Transactional
@Repository
public class UserManagementDao {

	private final String ADM_USER_SEQ = "ADM_USER_SEQ";
	private final String ADM_USER_ROLE_SEQ = "ADM_USER_ROLE_SEQ";
	private static final Logger logger = LoggerFactory.getLogger(UserManagementDao.class);
	
	@Autowired
	private CommonJdbcDao commonJdbcDao;
	
	@Autowired
	private SEQDao seqDao;
	
	
	public UserManagement findByUserId(String userId) {
		logger.debug("findByUserId userId={}", userId);
		
		String sql =
			" SELECT user_id, username, password,SECTOR, enabled, account_non_expired, " + 
			" credentials_non_expired, account_non_locked, is_deleted FROM adm_user " + 
			" WHERE is_deleted = ?" +
			" AND user_id = ? ";
		
		return commonJdbcDao.executeQueryForObject(sql,
			new Object[] {
				FLAG.N_FLAG,
				userId
			},
			UserManagementRowMapper.getInstance()
		);
	}
	
	public UserManagement findByUsername(String username) {
		logger.debug("findByUserId username={}", username);
		
		String sql =
			" SELECT user_id, username, password,SECTOR, enabled, account_non_expired, " + 
			" credentials_non_expired, account_non_locked, is_deleted FROM adm_user " + 
			" WHERE is_deleted = ?" +
			" AND username = ? ";
		
		return commonJdbcDao.executeQueryForObject(sql,
			new Object[] {
				FLAG.N_FLAG,
				username
			},
			UserManagementRowMapper.getInstance()
		);
	}
	
	public List<UserManagement> findAll(Integer start, Integer length, UserManagement userManagement) {
		logger.debug("findAll");
		
		StringBuilder sqBuilder = new StringBuilder();
		sqBuilder.append(" SELECT user_id, username, password,SECTOR, enabled, account_non_expired, ");
		sqBuilder.append(" credentials_non_expired, account_non_locked, is_deleted ");
		sqBuilder.append(" FROM adm_user ");
		sqBuilder.append(" WHERE is_deleted = ? ");
		List<Object> params = new ArrayList<>();
		params.add(FLAG.N_FLAG);
		
		if(StringUtils.isNotBlank(userManagement.getUsername())){
			sqBuilder.append(" AND username = ? ");
			params.add(userManagement.getUsername());
		}
		if(StringUtils.isNotBlank(userManagement.getEnabled())){
			sqBuilder.append(" AND enabled = ? ");
			params.add(userManagement.getEnabled());
		}
		if(StringUtils.isNotBlank(userManagement.getSector())){
			sqBuilder.append(" AND SECTOR = ? ");
			params.add(userManagement.getEnabled());
		}
		if(StringUtils.isNotBlank(userManagement.getAccountNonExpired())){
			sqBuilder.append(" AND account_non_expired = ? ");
			params.add(userManagement.getAccountNonExpired());
		}
		if(StringUtils.isNotBlank(userManagement.getCredentialsNonExpired())){
			sqBuilder.append(" AND credentials_non_expired = ? ");
			params.add(userManagement.getCredentialsNonExpired());
		}
		if(StringUtils.isNotBlank(userManagement.getAccountNonLocked())){
			sqBuilder.append(" AND account_non_locked = ? ");
			params.add(userManagement.getAccountNonLocked());
		}
		
		sqBuilder.append(" order by username ");
		
		String sql = OracleUtils.limit(sqBuilder.toString(), start, length);
		logger.debug(sql);
		return commonJdbcDao.executeQuery(sql,params.toArray(),UserManagementRowMapper.getInstance()
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
	
	public Long insert(UserManagement userManagement) {
		logger.debug("insert");
		
		String sql = SqlGeneratorUtils.genSqlInsert("adm_user", Arrays.asList(
			"USER_ID",
			"username",
			"password",
			"SECTOR",
			"enabled",
			"account_non_expired",
			"credentials_non_expired",
			"account_non_locked",
			"IS_DELETED",
			"VERSION",
			"created_by",
			"created_date",
			"updated_by",
			"updated_date"
		));
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		BigDecimal userId = seqDao.autoNumberRunningBySeqName(ADM_USER_SEQ);
		Long key = (long) commonJdbcDao.executeUpdate(sql.toString(), new Object[] {
			userId,
			userManagement.getUsername(),
			passwordEncoder.encode(userManagement.getPassword()),
			userManagement.getSector(),
			userManagement.getEnabled(),
			userManagement.getAccountNonExpired(),
			userManagement.getCredentialsNonExpired(),
			userManagement.getAccountNonLocked(),
			"N",
			"1",
			UserLoginUtils.getCurrentUsername(),
			new Date(),
			UserLoginUtils.getCurrentUsername(),
			new Date()
		});
		
		if (key != 0) {
			String sqlUserAdmRole = SqlGeneratorUtils.genSqlInsert("adm_user_role", Arrays.asList(
				"USER_ROLE_ID",
				"user_id",
				"role_id",
				"IS_DELETED",
				"VERSION",
				"created_by",
				"created_date"
			));
			
			BigDecimal userRoleId = seqDao.autoNumberRunningBySeqName(ADM_USER_ROLE_SEQ);
			commonJdbcDao.executeUpdate(sqlUserAdmRole.toString(), new Object[] {
				userRoleId,
				userId,
				UserManagementConstants.ROLE.USER,
				"N",
				"1",
				UserLoginUtils.getCurrentUsername(),
				new Date()
			});
		}
		
		return key;
	}
	
	public int update(UserManagement userManagement) {
		logger.debug("update");
		List<String> field = new ArrayList<String>();
		List<Object> paramField = new ArrayList<Object>();
		field.add("enabled");
		field.add("account_non_expired");
		field.add("credentials_non_expired");
		field.add("account_non_locked");
		field.add("updated_by");
		field.add("updated_date");
		
		paramField.add(userManagement.getEnabled());
		paramField.add(userManagement.getAccountNonExpired());
		paramField.add(userManagement.getCredentialsNonExpired());
		paramField.add(userManagement.getAccountNonLocked());
		paramField.add(UserLoginUtils.getCurrentUsername());
		paramField.add(new Date());
		
		if(StringUtils.isNotBlank(userManagement.getPassword())) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			field.add("password");
			paramField.add(passwordEncoder.encode(userManagement.getPassword()));
		}
		
		
		String sql = SqlGeneratorUtils.genSqlUpdate("adm_user", field,
			Arrays.asList(
				"user_id"
			)
		);
		paramField.add(userManagement.getUserId());
		
		int updateRow = commonJdbcDao.executeUpdate(sql.toString(), paramField.toArray());
		logger.debug(sql.toString());
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
		
		if(deleteRow != 0) {
			String sqlUserAdmRole = SqlGeneratorUtils.genSqlUpdate("adm_user_role",
				Arrays.asList(
					"is_deleted",
					"updated_by",
					"updated_date"
				),
				Arrays.asList(
					"user_id"
				)
			);
			
			commonJdbcDao.executeUpdate(sqlUserAdmRole.toString(), new Object[] {
				FLAG.Y_FLAG,
				UserLoginUtils.getCurrentUsername(),
				new Date(),
				userId
			});
		}
		
		return deleteRow;
	}
	

}
