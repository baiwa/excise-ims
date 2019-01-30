package co.th.baiwa.buckwaframework.usermanagement.user.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import co.th.baiwa.buckwaframework.usermanagement.user.domain.User;

@Repository
public class UserDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public int save(User user) {
		String template = " insert into EXCISE_USER (USERNAME, PASSWORD, ENABLED, IS_DELETED, CREATED_BY, CREATED_DATE) values(?,?,'Y','N',?,SYSDATE) ";
		StringBuilder sql = new StringBuilder(template);
		List<Object> params = new ArrayList<>();
		params.add(user.getUsername());
		params.add(passwordEncoder.encode(user.getPassword()));
		params.add(user.getCreatedBy());
		return jdbcTemplate.update(sql.toString(), params.toArray());
	}

	public int update(User user, BigDecimal id) {
		String template = " update EXCISE_USER set PASSWORD=?, ENABLED=?, UPDATED_BY=?, UPDATED_DATE=SYSDATE where USER_ID=? ";
		StringBuilder sql = new StringBuilder(template);
		List<Object> params = new ArrayList<>();
		// set
		params.add(passwordEncoder.encode(user.getPassword()));
		params.add(user.getEnabled());
		params.add(user.getUpdatedBy());
		// where
		params.add(id);
		return jdbcTemplate.update(sql.toString(), params.toArray());
	}

	public int delete(String updatedBy, BigDecimal id) {
		String template = " update EXCISE_USER set IS_DELETED='Y', UPDATED_BY=?, UPDATED_DATE=SYSDATE where USER_ID=? ";
		StringBuilder sql = new StringBuilder(template);
		List<Object> params = new ArrayList<>();
		// set
		params.add(updatedBy);
		// where
		params.add(id);
		return jdbcTemplate.update(sql.toString(), params.toArray());
	}

	public List<User> findAll() {
		String template = " select * from EXCISE_USER where IS_DELETED = 'N' ";
		StringBuilder sql = new StringBuilder(template);
		List<User> users = jdbcTemplate.query(sql.toString(), rowMapper);
		return users;
	}

	public User findOne(BigDecimal id) {
		String template = " select * from EXCISE_USER where IS_DELETED = 'N' AND USER_ID = ? ";
		StringBuilder sql = new StringBuilder(template);
		List<Object> params = new ArrayList<>();
		params.add(id);
		User user = jdbcTemplate.queryForObject(sql.toString(), params.toArray(), rowMapper);
		return user;
	}
	
	public User findUsername(String username) {
		String template = " select * from EXCISE_USER where IS_DELETED = 'N' AND USERNAME = ? ";
		StringBuilder sql = new StringBuilder(template);
		List<Object> params = new ArrayList<>();
		params.add(username);
		User user = jdbcTemplate.queryForObject(sql.toString(), params.toArray(), rowMapperPass);
		return user;
	}

	protected RowMapper<User> rowMapper = new RowMapper<User>() {
		@Override
		public User mapRow(ResultSet rs, int arg1) throws SQLException {
			User vo = new User();
			vo.setUserId(rs.getBigDecimal("USER_ID"));
			vo.setUsername(rs.getString("USERNAME"));
			vo.setEnabled(rs.getString("ENABLED"));
			vo.setIsDeleted(rs.getString("IS_DELETED"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(rs.getDate("CREATED_DATE"));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			return vo;
		}
	};
	
	protected RowMapper<User> rowMapperPass = new RowMapper<User>() {
		@Override
		public User mapRow(ResultSet rs, int arg1) throws SQLException {
			User vo = new User();
			vo.setUserId(rs.getBigDecimal("USER_ID"));
			vo.setUsername(rs.getString("USERNAME"));
			vo.setPassword(rs.getString("PASSWORD"));
			vo.setEnabled(rs.getString("ENABLED"));
			vo.setIsDeleted(rs.getString("IS_DELETED"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(rs.getDate("CREATED_DATE"));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			return vo;
		}
	};

}
