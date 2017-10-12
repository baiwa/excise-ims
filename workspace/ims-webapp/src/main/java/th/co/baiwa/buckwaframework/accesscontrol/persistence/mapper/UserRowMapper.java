package th.co.baiwa.buckwaframework.accesscontrol.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.User;

public class UserRowMapper implements RowMapper<User> {
	
	private static class SingletonHolder {
		private static final UserRowMapper instance = new UserRowMapper();
	}
	
	public static UserRowMapper getInstance() {
		return SingletonHolder.instance;
	}
	
	@Override
	public User mapRow(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUserId(rs.getLong("user_id"));
		user.setUsername(rs.getString("username"));
		user.setPassword(rs.getString("password"));
		user.setEnabled(rs.getString("enabled"));
		user.setAccountNonExpired(rs.getString("account_non_expired"));
		user.setCredentialsNonExpired(rs.getString("credentials_non_expired"));
		user.setAccountNonLocked(rs.getString("account_non_locked"));
		return user;
	}
	
}
