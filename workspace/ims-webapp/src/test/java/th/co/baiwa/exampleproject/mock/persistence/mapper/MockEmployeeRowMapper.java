package th.co.baiwa.exampleproject.mock.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.exampleproject.mock.persistence.entity.MockEmployee;

public class MockEmployeeRowMapper implements RowMapper<MockEmployee> {
	
	private static class SingletonHolder {
		private static final MockEmployeeRowMapper instance = new MockEmployeeRowMapper();
	}
	
	public static MockEmployeeRowMapper getInstance() {
		return SingletonHolder.instance;
	}
	
	@Override
	public MockEmployee mapRow(ResultSet rs, int rowNum) throws SQLException {
		MockEmployee mockUser = new MockEmployee();
		mockUser.setEmpId(rs.getLong("EMP_ID"));
		mockUser.setFirstName(rs.getString("FIRST_NAME"));
		mockUser.setLastName(rs.getString("LAST_NAME"));
		mockUser.setSalary(rs.getBigDecimal("SALARY"));
		mockUser.setWorkingDate(rs.getDate("WORKING_DATE"));
		return mockUser;
	}
	
}
