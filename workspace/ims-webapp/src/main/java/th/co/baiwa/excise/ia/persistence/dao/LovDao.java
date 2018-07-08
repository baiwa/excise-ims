package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.excise.utils.BeanUtils;

@Repository
public class LovDao {

	private Logger logger = LoggerFactory.getLogger(LovDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	private String sqlSelectTable = "SELECT * FROM SYS_LOV WHERE 1 = 1 ";

	public List<Lov> queryLovByCriteria(Lov lov , String orderBy) {
		logger.info("queryLovByCriteria");
		List<Object> objList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(sqlSelectTable);
		if (lov != null) {
			if (lov.getLovId() != null) {
				sql.append(" and LOV_ID = ?");
				objList.add(lov.getLovId());
			}
			if (lov.getType() != null && lov.getType().length() > 0) {
				sql.append(" and TYPE = ?");
				objList.add(lov.getType());
			}
			if (lov.getValue1() != null && lov.getValue1().length() > 0) {
				sql.append(" and VALUE1 = ?");
				objList.add(lov.getValue1());
			}
		}
		
		if(BeanUtils.isNotEmpty(orderBy)) {
			sql.append(" order by " + orderBy);
		}
		logger.info("SQL : " + sql.toString());
		List<Lov> list = jdbcTemplate.query(sql.toString(), objList.toArray(), lovMappingRow);
		return list;
	}

	private RowMapper<Lov> lovMappingRow = new RowMapper<Lov>() {

		@Override
		public Lov mapRow(ResultSet rs, int arg1) throws SQLException {
			Lov lov = new Lov();
			lov.setLovId(rs.getBigDecimal("LOV_ID"));
			lov.setLovIdMaster(rs.getBigDecimal("LOV_ID_MASTER"));
			lov.setType(rs.getString("TYPE"));
			lov.setTypeDescription(rs.getString("TYPE_DESCRIPTION"));
			lov.setSubType(rs.getString("SUB_TYPE"));
			lov.setSubTypeDescription(rs.getString("SUB_TYPE_DESCRIPTION"));
			lov.setValue1(rs.getString("VALUE1"));
			lov.setValue2(rs.getString("VALUE2"));
			lov.setValue3(rs.getString("VALUE3"));
			lov.setValue4(rs.getString("VALUE4"));
			lov.setValue5(rs.getString("VALUE5"));
			lov.setIsDeleted(rs.getString("IS_DELETED"));
			lov.setCreatedBy(rs.getString("CREATED_BY"));
			lov.setCreatedDate(rs.getDate("CREATED_DATE"));
			lov.setUpdatedBy(rs.getString("UPDATED_BY"));
			lov.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			return lov;

		}

	};
	
	
	public List<String> queryLovTypeList() {
		logger.info("queryLovTypeList");
		String sql = "SELECT DISTINCT L.TYPE FROM SYS_LOV L";
		List<String> lovList = jdbcTemplate.query(sql, new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});
		return lovList;
	}

}
