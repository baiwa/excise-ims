package th.co.baiwa.buckwaframework.preferences.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.UserManagement;

public class UserManagementRowMapper implements RowMapper<UserManagement> {
	
	private static class SingletonHolder {
		private static final UserManagementRowMapper instance = new UserManagementRowMapper();
	}
	
	public static UserManagementRowMapper getInstance() {
		return SingletonHolder.instance;
	}
	
	@Override
	public UserManagement mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserManagement userManagement = new UserManagement();
		userManagement.setUserId(rs.getLong("user_id"));
		userManagement.setSector(rs.getString("sector"));
		userManagement.setUsername(rs.getString("username"));
		userManagement.setPassword(rs.getString("password"));
		userManagement.setEnabled(rs.getString("enabled"));
		userManagement.setAccountNonExpired(rs.getString("account_non_expired"));
		userManagement.setCredentialsNonExpired(rs.getString("credentials_non_expired"));
		userManagement.setAccountNonLocked(rs.getString("account_non_locked"));
		return userManagement;
	}
}
