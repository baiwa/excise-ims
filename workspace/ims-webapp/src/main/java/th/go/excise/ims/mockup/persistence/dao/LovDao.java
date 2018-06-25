package th.go.excise.ims.mockup.persistence.dao;

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

import th.go.excise.ims.mockup.persistence.entity.sys.Lov;

@Repository
public class LovDao {

	private Logger logger = LoggerFactory.getLogger(LovDao.class);

	@Autowired
	private JdbcTemplate JdbcTemplate;
	private String sqlSelectTable = "SELECT * FROM SYS_LOV WHERE 1 = 1 ";

	public List<Lov> queryLovByCriteria(Lov lov) {
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
		
		sql.append(" order by  VALUE1 ");
		logger.info("SQL : " + sql.toString());
		List<Lov> list = JdbcTemplate.query(sql.toString(), objList.toArray(), lovMappingRow);
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
			lov.setCreatedDatetime(rs.getDate("CREATED_DATETIME"));
			lov.setUpdateBy(rs.getString("UPDATE_BY"));
			lov.setUpdateDatetime(rs.getDate("UPDATE_DATETIME"));
			return lov;

		}

	};

}
