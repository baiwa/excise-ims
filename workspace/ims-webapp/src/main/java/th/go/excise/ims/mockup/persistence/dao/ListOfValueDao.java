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

import th.go.excise.ims.mockup.persistence.entity.ListOfValue;

@Repository
public class ListOfValueDao {

	private Logger logger = LoggerFactory.getLogger(ListOfValueDao.class);

	@Autowired
	private JdbcTemplate JdbcTemplate;
	private String sqlSelectTable = "SELECT * FROM LIST_OF_VALUE WHERE 1 = 1 ";

	public List<ListOfValue> queryLovByCriteria(ListOfValue lov) {
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
			if (lov.getValue() != null && lov.getValue().length() > 0) {
				sql.append(" and VALUE = ?");
				objList.add(lov.getValue());
			}
		}
		logger.info("SQL : " + sql.toString());
		List<ListOfValue> list = JdbcTemplate.query(sql.toString(), objList.toArray(), lovMappingRow);
		return list;
	}

	private RowMapper<ListOfValue> lovMappingRow = new RowMapper<ListOfValue>() {

		@Override
		public ListOfValue mapRow(ResultSet rs, int arg1) throws SQLException {
			ListOfValue lov = new ListOfValue();
			lov.setLovId(rs.getBigDecimal("LOV_ID"));
			lov.setType(rs.getString("TYPE"));
			lov.setTypeDescription(rs.getString("TYPE_DESCRIPTION"));
			lov.setValue(rs.getString("VALUE"));
			lov.setValueDescription(rs.getString("VALUE_DESCRIPTION"));
			lov.setCreatedBy(rs.getString("CREATED_BY"));
			lov.setCreatedDatetime(rs.getDate("CREATED_DATETIME"));
			lov.setUpdateBy(rs.getString("UPDATE_BY"));
			lov.setUpdateDatetime(rs.getDate("UPDATE_DATETIME"));
			return lov;

		}

	};

}
