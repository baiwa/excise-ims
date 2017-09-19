//package th.co.baiwa.framework.security.dao;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.stereotype.Repository;
//
//import th.co.baiwa.framework.admin.model.User;
//import th.co.baiwa.framework.common.persistence.dao.AbstractCommonJdbcDao;
//
//@Repository("userDetailsDao")
//public class UserDetailsDaoImpl extends AbstractCommonJdbcDao implements UserDetailsDao {
//	
//	private final Logger logger = LoggerFactory.getLogger(this.getClass());
//	
//	// --------------------------------------------------
//	// User
//	// --------------------------------------------------
//	@Override
//	public User findByUsername(String username) {
//		logger.info("findByUsername username={}", username);
//		
//		User user = null;
//		
//		String SQL_GET_USER =
//			" SELECT user_id, username, password, enabled, account_non_expired, " +
//			"        credentials_non_expired, account_non_locked " +
//			" FROM adm_user " +
//			" WHERE is_deleted = 'N' " +
//			"   AND username = ? ";
//		
//		try {
//			user = executeQueryForObject(SQL_GET_USER,
//				new Object[] {
//					username
//				},
//				new RowMapper<User>() {
//					@Override
//					public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//						User user = new User();
//						user.setUserId(rs.getLong("user_id"));
//						user.setUsername(rs.getString("username"));
//						user.setPassword(rs.getString("password"));
//						user.setEnabled(rs.getString("enabled"));
//						user.setAccountNonExpired(rs.getString("account_non_expired"));
//						user.setCredentialsNonExpired(rs.getString("credentials_non_expired"));
//						user.setAccountNonLocked(rs.getString("account_non_locked"));
//						return user;
//					}
//				}
//			);
//		} catch (EmptyResultDataAccessException e) {
//			logger.warn("Can not find data in ADM_USER with username: " + username);
//		}
//		
//		logger.info("Returning user=" + user);
//		
//		return user;
//	}
//	
//	
//	// --------------------------------------------------
//	// GrantedAuthority
//	// --------------------------------------------------
//	@Override
//	public List<GrantedAuthority> findGrantedRoleByUserId(Long userId) {
//		logger.info("findGrantedRoleByUserId userId={}", userId);
//		
//		String SQL_GET_GRANTED_ROLE =
//			" SELECT ar.role_code " +
//			" FROM adm_user_role aur " +
//			" INNER JOIN adm_role ar ON ar.role_id = aur.role_id " +
//			"   AND ar.is_deleted = 'N' " +
//			" WHERE aur.is_deleted = 'N' " +
//			"   AND aur.user_id = ? ";
//		
//		List<GrantedAuthority> grantedRoleList = executeQuery(SQL_GET_GRANTED_ROLE,
//			new Object[] {
//				userId
//			},
//			new RowMapper<GrantedAuthority>() {
//				@Override
//				public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
//					return new SimpleGrantedAuthority(rs.getString("ROLE_CODE"));
//				}
//			}
//		);
//		
//		return grantedRoleList;
//	}
//	
//	@Override
//	public List<GrantedAuthority> findGrantedOperationByUserId(Long userId) {
//		logger.info("findGrantedOperationByUserId userId={}", userId);
//		
//		String SQL_GET_GRANTED_OPERATION =
//			" SELECT ao.operation_code " +
//			" FROM adm_user_operation auo " +
//			" INNER JOIN adm_operation ao ON AO.OPERATION_ID = AUO.OPERATION_ID " +
//			"   AND AO.IS_DELETED = 'N' " +
//			" WHERE AUO.IS_DELETED = 'N' " +
//			"   AND AUO.USER_ID = ? ";
//		
//		List<GrantedAuthority> grantedOperationList = executeQuery(SQL_GET_GRANTED_OPERATION,
//			new Object[] {
//				userId
//			},
//			new RowMapper<GrantedAuthority>() {
//				@Override
//				public GrantedAuthority mapRow(ResultSet rs, int rowNum) throws SQLException {
//					return new SimpleGrantedAuthority(rs.getString("OPERATION_CODE"));
//				}
//			}
//		);
//		
//		return grantedOperationList;
//	}
//
//}
