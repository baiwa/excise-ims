package th.co.baiwa.exampleproject.examplemodule.persistence.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.exampleproject.examplemodule.persistence.entity.BuckwaTest;

public class BuckwaTestRowMapper implements RowMapper<BuckwaTest> {
	
	private static class SingletonHolder {
		private static final BuckwaTestRowMapper instance = new BuckwaTestRowMapper();
	}
	
	public static BuckwaTestRowMapper getInstance() {
		return SingletonHolder.instance;
	}
	
	@Override
	public BuckwaTest mapRow(ResultSet rs, int rowNum) throws SQLException {
		BuckwaTest buckwaTest = new BuckwaTest();
		buckwaTest.setColPk(rs.getLong("col_pk"));
		buckwaTest.setColVarchar(rs.getString("col_varchar"));
		buckwaTest.setColInt(rs.getInt("col_int"));
		buckwaTest.setColDouble(rs.getDouble("col_double"));
		buckwaTest.setColDecimal(rs.getBigDecimal("col_decimal"));
		buckwaTest.setColTimestamp(rs.getDate("col_timestamp"));
		buckwaTest.setColDate(rs.getDate("col_date"));
		buckwaTest.setColTime(rs.getDate("col_time"));
		return buckwaTest;
	}
	
}
