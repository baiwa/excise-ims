package th.co.baiwa.exampleproject.examplemodule.persistence.dao;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.dao.BatchSetter;
import th.co.baiwa.buckwaframework.common.persistence.dao.CommonJdbcDao;
import th.co.baiwa.exampleproject.examplemodule.persistence.entity.BuckwaTest;
import th.co.baiwa.exampleproject.examplemodule.persistence.mapper.BuckwaTestRowMapper;

@Repository("buckwaTestDao")
public class BuckwaTestDao {
	
	@Autowired
	private CommonJdbcDao commonJdbcDao;
	
	public List<BuckwaTest> findAll() {
		List<BuckwaTest> resultList = commonJdbcDao.executeQuery("SELECT * FROM buckwa_test",
			BuckwaTestRowMapper.getInstance()
		);
		return resultList;
	}
	
	public List<BuckwaTest> findAllWithBeanProperty() {
		List<BuckwaTest> resultList = commonJdbcDao.executeQuery("SELECT * FROM buckwa_test",
			BeanPropertyRowMapper.newInstance(BuckwaTest.class)
		);
		return resultList;
	}
	
	public int count() {
		int result = commonJdbcDao.executeQueryForObject("SELECT COUNT(1) FROM buckwa_test",
			Integer.class
		);
		return result;
	}
	
	public int insert(BuckwaTest buckwaTest) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO buckwa_test (col_varchar, col_int, col_double, col_decimal, col_timestamp, col_date, col_time) ");
		sql.append(" VALUES(?,?,?,?,?,?,?) ");
		
		return commonJdbcDao.executeInsert(sql.toString(), new Object[] {
			buckwaTest.getColVarchar(),
			buckwaTest.getColInt(),
			buckwaTest.getColDouble(),
			buckwaTest.getColDecimal(),
			new java.sql.Timestamp(buckwaTest.getColTimestamp().getTime()),
			new java.sql.Date(buckwaTest.getColDate().getTime()),
			new java.sql.Time(buckwaTest.getColTime().getTime())
		});
	}
	
	public int update(BuckwaTest buckwaTest) {
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE buckwa_test ");
		sql.append(" SET ");
		sql.append("   col_varchar = ?, ");
		sql.append("   col_date = ? ");
		sql.append(" WHERE COL_PK  = ? ");
		
		return commonJdbcDao.executeUpdate(sql.toString(), new Object[] {
			buckwaTest.getColVarchar(),
			new java.sql.Date(new Date().getTime()),
			buckwaTest.getColPk()
		});
	}
	
	public int[][] batchInsert(final List<BuckwaTest> buckwaTestList, int executeSize) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT INTO buckwa_test (col_varchar) ");
		sql.append(" VALUES(?) ");
		
		return commonJdbcDao.executeBatch(sql.toString(), new BatchSetter<BuckwaTest>() {
			@Override
			public List<BuckwaTest> getBatchObjectList() {
				return buckwaTestList;
			}
			
			@Override
			public Object[] toObjects(BuckwaTest obj) {
				return new Object[] {
					obj.getColVarchar()
				};
			}
			
			@Override
			public int getExecuteSize() {
				return executeSize;
			}
		});
	}
	
}
