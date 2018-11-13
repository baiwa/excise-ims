package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;

@Repository
public class Int0806Dao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Lov> getValue1(Lov en) {

		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT * FROM SYS_LOV L ");
		sql.append(" WHERE L.TYPE = ? ");
		valueList.add(en.getType());
		sql.append(" AND L.LOV_ID_MASTER is not null ");
		sql.append(" AND L.VALUE1 is not null ");

		List<Lov> dataList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingGetValue1);
		return dataList;
	}

	private RowMapper<Lov> fieldMappingGetValue1 = new RowMapper<Lov>() {
		@Override
		public Lov mapRow(ResultSet rs, int arg1) throws SQLException {
			Lov en = new Lov();
			en.setLovId(rs.getLong("LOV_ID"));
			en.setLovIdMaster(rs.getLong("LOV_ID_MASTER"));
			en.setType(rs.getString("TYPE"));
			en.setValue1(rs.getString("VALUE1"));
			return en;
		}
	};

}
