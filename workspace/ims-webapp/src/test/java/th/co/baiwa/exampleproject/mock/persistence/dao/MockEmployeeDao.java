package th.co.baiwa.exampleproject.mock.persistence.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.dao.CommonJdbcDao;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.exampleproject.mock.persistence.entity.MockEmployee;
import th.co.baiwa.exampleproject.mock.persistence.mapper.MockEmployeeRowMapper;

@Repository("mockEmployeeDao")
public class MockEmployeeDao {
	
	@Autowired
	private CommonJdbcDao commonJdbcDao;
	
	public MockEmployee findById(Long empId) {
		//String sql = "SELECT * FROM Z_MOCK_EMPLOYEE WHERE IS_DELETED = 'N' AND EMP_ID = ?";
		String sql = SqlGeneratorUtils.genSqlSelect("Z_MOCK_EMPLOYEE",
			Arrays.asList(
				"EMP_ID",
				"FIRST_NAME",
				"LAST_NAME",
				"SALARY",
				"WORKING_DATE",
				"IS_DELETED",
				"CREATED_BY",
				"CREATED_DATE",
				"UPDATED_BY",
				"UPDATED_DATE"
			),
			Arrays.asList(
				"IS_DELETED",
				"EMP_ID"
			)
		);
		return commonJdbcDao.executeQueryForObject(sql,
			new Object[] {
				FLAG.N_FLAG,
				empId
			},
			MockEmployeeRowMapper.getInstance()
		);
	}
	
	public Map<String, Object> findById_Map(Long empId) {
		String sql = "SELECT * FROM Z_MOCK_EMPLOYEE WHERE IS_DELETED = 'N' AND EMP_ID = ?";
		return commonJdbcDao.executeQueryForMap(sql,
			new Object[] {
				empId
			}
		);
	}
	
	public MockEmployee findByIdWithBeanProperty(Long empId) {
		String sql = "SELECT * FROM Z_MOCK_EMPLOYEE WHERE IS_DELETED = 'N' AND EMP_ID = ?";
		return commonJdbcDao.executeQueryForObject(sql,
			new Object[] {
				empId
			},
			BeanPropertyRowMapper.newInstance(MockEmployee.class)
		);
	}
	
	public List<MockEmployee> findAll() {
		String sql = "SELECT * FROM Z_MOCK_EMPLOYEE WHERE IS_DELETED = 'N'";
		return commonJdbcDao.executeQuery(sql, MockEmployeeRowMapper.getInstance());
	}
	
	public int count() {
		String sql = "SELECT COUNT(1) FROM Z_MOCK_EMPLOYEE WHERE IS_DELETED = 'N'";
		return commonJdbcDao.executeQueryForObject(sql, Integer.class);
	}
	
	public Long insert(MockEmployee mockEmployee) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO Z_MOCK_EMPLOYEE (FIRST_NAME, LAST_NAME, SALARY, WORKING_DATE) ");
		sql.append(" VALUES (?, ?, ?, ?) ");
		
		Long key = commonJdbcDao.executeInsertWithKeyHolder(sql.toString(), new Object[] {
			mockEmployee.getFirstName(),
			mockEmployee.getLastName(),
			mockEmployee.getSalary(),
			mockEmployee.getWorkingDate()
		});
		
		return key;
	}
	
	public Long insertNew(MockEmployee mockEmployee) {
		String sql = SqlGeneratorUtils.genSqlInsert("Z_MOCK_EMPLOYEE", Arrays.asList(
			"FIRST_NAME",
			"LAST_NAME",
			"SALARY",
			"WORKING_DATE"
		));
		
		Long key = commonJdbcDao.executeInsertWithKeyHolder(sql.toString(), new Object[] {
			mockEmployee.getFirstName(),
			mockEmployee.getLastName(),
			mockEmployee.getSalary(),
			mockEmployee.getWorkingDate()
		});
		
		return key;
	}
	
	public Long insertBySimpleJdbcInsert(MockEmployee mockEmployee) {
		SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(commonJdbcDao.getJdbcTemplate());
		jdbcInsert.withTableName("Z_MOCK_EMPLOYEE");
		jdbcInsert.usingGeneratedKeyColumns("EMP_ID");
		jdbcInsert.usingColumns(new String[] {
			"FIRST_NAME",
			"LAST_NAME",
			"SALARY",
			"WORKING_DATE"
		});
		
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(mockEmployee);
		Number newId = jdbcInsert.executeAndReturnKey(parameterSource);
		
		return newId.longValue();
	}
	
	public int update(MockEmployee mockEmployee) {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE Z_MOCK_EMPLOYEE ");
		sql.append(" SET ");
		sql.append("   FIRST_NAME = ?, ");
		sql.append("   SALARY = ?, ");
		sql.append("   UPDATED_BY = ?, ");
		sql.append("   UPDATED_DATE = ? ");
		sql.append(" WHERE EMP_ID = ? ");
		
		int updateRow = commonJdbcDao.executeUpdate(sql.toString(), new Object[] {
			mockEmployee.getFirstName(),
			mockEmployee.getSalary(),
			"UNITTEST_USER",
			new Date(),
			mockEmployee.getEmpId()
		});
		
		return updateRow;
	}
	
	public int updateNew(MockEmployee mockEmployee) {
		String sql = SqlGeneratorUtils.genSqlUpdate("Z_MOCK_EMPLOYEE",
			Arrays.asList(
				"FIRST_NAME",
				"SALARY",
				"UPDATED_BY",
				"UPDATED_DATE"
			),
			Arrays.asList(
				"EMP_ID"
			)
		);
		
		int updateRow = commonJdbcDao.executeUpdate(sql.toString(), new Object[] {
			mockEmployee.getFirstName(),
			mockEmployee.getSalary(),
			"UNITTEST_USER",
			new Date(),
			mockEmployee.getEmpId()
		});
		
		return updateRow;
	}
	
	public int deleteById(Long empId) {
		String sql = SqlGeneratorUtils.genSqlUpdate("Z_MOCK_EMPLOYEE",
			Arrays.asList(
				"IS_DELETED",
				"UPDATED_BY",
				"UPDATED_DATE"
			),
			Arrays.asList(
				"EMP_ID"
			)
		);
		
		int updateRow = commonJdbcDao.executeUpdate(sql.toString(), new Object[] {
			FLAG.Y_FLAG,
			"UNITTEST_USER",
			new Date(),
			empId
		});
		
		return updateRow;
	}
	
	public int[] batchInsert(List<MockEmployee> mockEmployeeList) {
		String sql = SqlGeneratorUtils.genSqlInsert("Z_MOCK_EMPLOYEE", Arrays.asList(
			"FIRST_NAME",
			"LAST_NAME",
			"SALARY",
			"WORKING_DATE"
		));
		
		return commonJdbcDao.executeBatchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				commonJdbcDao.preparePs(ps, new Object[] {
					mockEmployeeList.get(i).getFirstName(),
					mockEmployeeList.get(i).getLastName(),
					mockEmployeeList.get(i).getSalary(),
					mockEmployeeList.get(i).getWorkingDate()
				});
			}
			
			@Override
			public int getBatchSize() {
				return mockEmployeeList.size();
			}
		});
	}
	
	public int[] batchUpdate(List<MockEmployee> mockEmployeeList) {
		String sql = SqlGeneratorUtils.genSqlUpdate("Z_MOCK_EMPLOYEE",
			Arrays.asList(
				"FIRST_NAME",
				"LAST_NAME",
				"UPDATED_BY",
				"UPDATED_DATE"
			),
			Arrays.asList(
				"EMP_ID"
			)
		);
		
		return commonJdbcDao.executeBatchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				commonJdbcDao.preparePs(ps, new Object[] {
					mockEmployeeList.get(i).getFirstName(),
					mockEmployeeList.get(i).getLastName(),
					"UNITTEST_USER",
					new Date(),
					mockEmployeeList.get(i).getEmpId()
				});
			}
			
			@Override
			public int getBatchSize() {
				return mockEmployeeList.size();
			}
		});
	}
	
}
