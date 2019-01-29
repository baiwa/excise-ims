package co.th.ims.role.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import co.th.ims.role.domain.Role;

@Repository
public class RoleDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<Role> findAll() {
		String sqlTemplate = " SELECT * FROM ADM_ROLE ";
		StringBuilder sql = new StringBuilder(sqlTemplate);
		List<Role> list = new ArrayList<Role>();
		list = jdbcTemplate.query(sql.toString(), rowMapper);
		return list;
		
	}
	
	private RowMapper<Role> rowMapper = new RowMapper<Role>() {
		
		@Override
		public Role mapRow(ResultSet rs, int arg1) throws SQLException {
			Role role = new Role();
			role.setRoleId(rs.getString("ROLE_ID"));
			role.setRoleCode(rs.getString("ROLE_CODE"));
			role.setRoleDesc(rs.getString("ROLE_DESC"));
			return role;
		}
	};
}
