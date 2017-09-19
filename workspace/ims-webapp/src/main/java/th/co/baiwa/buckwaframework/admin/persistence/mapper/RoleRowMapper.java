package th.co.baiwa.buckwaframework.admin.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.admin.persistence.entity.Role;

public class RoleRowMapper implements RowMapper<Role> {
	
	private static class SingletonHolder {
		private static final RoleRowMapper instance = new RoleRowMapper();
	}
	
	public static RoleRowMapper getInstance() {
		return SingletonHolder.instance;
	}
	
	@Override
	public Role mapRow(ResultSet rs, int rowNum) throws SQLException {
		Role role = new Role();
		role.setRoleId(rs.getLong("role_id"));
		role.setRoleCode(rs.getString("role_code"));
		role.setRoleDesc(rs.getString("role_desc"));
		return role;
	}
	
}
