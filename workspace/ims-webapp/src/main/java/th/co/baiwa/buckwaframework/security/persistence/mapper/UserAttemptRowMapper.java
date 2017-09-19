package th.co.baiwa.buckwaframework.security.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.security.persistence.entity.UserAttempt;

public class UserAttemptRowMapper implements RowMapper<UserAttempt> {
	
	private static class SingletonHolder {
		private static final UserAttemptRowMapper instance = new UserAttemptRowMapper();
	}
	
	public static UserAttemptRowMapper getInstance() {
		return SingletonHolder.instance;
	}
	
	@Override
	public UserAttempt mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserAttempt userAttempt = new UserAttempt();
		userAttempt.setUserAttemptId(rs.getLong("user_attempt_id"));
		userAttempt.setUsername(rs.getString("username"));
		userAttempt.setAttempts(rs.getInt("attempts"));
		userAttempt.setLastModified(rs.getDate("last_modified"));
		return userAttempt;
	}
	
}
