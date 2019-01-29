package co.th.ims.user.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.th.ims.user.domain.User;

@Repository
public class UserDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<User> findAll() {
		StringBuilder sql = new StringBuilder();
		return null;
	}
	
}
