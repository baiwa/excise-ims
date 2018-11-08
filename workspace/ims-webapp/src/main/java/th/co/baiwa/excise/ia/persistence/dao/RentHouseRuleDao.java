package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ia.persistence.entity.RentHouseRule;

@Repository
public class RentHouseRuleDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	public static final String CODE_POSITION_1001 = "1001";
	public static final String CODE_POSITION_1002 = "1002";
	public static final String CODE_POSITION_1003 = "1003";
	public static final String CODE_POSITION_1004 = "1004";
	
	private String sqlTemplate = " SELECT L.RULE_ID,L.CODE_POSITION ,L.YEAR ,L.COPY_NUMBER,L.POSITION,L.LEVELS,L.SALARY_FROM,L.SALARY_TO,L.RENT_AMOUNT FROM IA_RENT_HOUSE_RULE L ";
	
	public List<RentHouseRule> findAll() {
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(sqlTemplate); 

		List<RentHouseRule> findAll = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieAllMapping);
		return findAll;
	}
	
	
	public List<RentHouseRule> findAdministrativePosition() {
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(sqlTemplate); 
		sql.append(" WHERE L.CODE_POSITION = ? ");
		valueList.add(CODE_POSITION_1001);
		List<RentHouseRule> dataList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieAllMapping);
		return dataList;
	}
	
	public List<RentHouseRule> findDirectorPosition() {
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(sqlTemplate); 
		sql.append(" WHERE L.CODE_POSITION = ? ");
		valueList.add(CODE_POSITION_1002);
		List<RentHouseRule> dataList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieAllMapping);
		return dataList;
	}
	
	public List<RentHouseRule> findAcademicPosition() {
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(sqlTemplate); 
		sql.append(" WHERE L.CODE_POSITION = ? ");
		valueList.add(CODE_POSITION_1003);
		List<RentHouseRule> dataList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieAllMapping);
		return dataList;
	}

	public List<RentHouseRule> findGeneralPosition() {
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(sqlTemplate); 
		sql.append(" WHERE L.CODE_POSITION = ? ");
		valueList.add(CODE_POSITION_1004);
		List<RentHouseRule> dataList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieAllMapping);
		return dataList;
	}
	
	
	private RowMapper<RentHouseRule> fieAllMapping = new RowMapper<RentHouseRule>() {
		@Override
		public RentHouseRule mapRow(ResultSet rs, int arg1) throws SQLException {
			RentHouseRule rule = new RentHouseRule();
			rule.setRuleId(rs.getLong("RULE_ID"));
			rule.setYear(rs.getString("YEAR"));
			rule.setCopyNumber(rs.getString("COPY_NUMBER"));
			rule.setPosition(rs.getString("POSITION"));
			rule.setLevels(rs.getString("LEVELS"));
			rule.setSalaryFrom(rs.getBigDecimal("SALARY_FROM"));
			rule.setSalaryTo(rs.getBigDecimal("SALARY_TO"));
			rule.setRentAmount(rs.getBigDecimal("RENT_AMOUNT"));
			rule.setCodePosition(rs.getString("CODE_POSITION"));
			return rule;
		}
	};

}
