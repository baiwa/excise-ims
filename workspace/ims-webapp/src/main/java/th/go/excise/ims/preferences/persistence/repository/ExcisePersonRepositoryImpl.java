package th.go.excise.ims.preferences.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.preferences.vo.ExcisePersonVoSelect;

public class ExcisePersonRepositoryImpl implements ExcisePersonRepositoryCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public List<ExcisePersonVoSelect> findByName(String officeCode, String name) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT * FROM EXCISE_PERSON  ");
		sql.append(" WHERE ED_PERSON_NAME LIKE ? ");
		sql.append(" AND ED_OFFCODE = ?  ");
		sql.append(" AND IS_DELETED = 'N' ");
		params.add("%" + name + "%");
		params.add(officeCode);

		return commonJdbcTemplate.query(sql.toString(), params.toArray(), edPersonRowMapper);

	}

	private static final RowMapper<ExcisePersonVoSelect> edPersonRowMapper = new RowMapper<ExcisePersonVoSelect>() {
		@Override
		public ExcisePersonVoSelect mapRow(ResultSet rs, int rowNum) throws SQLException {
			ExcisePersonVoSelect person = new ExcisePersonVoSelect();
			person.setEdLogin(rs.getString("ED_LOGIN"));
			person.setEdPersonName(rs.getString("ED_PERSON_NAME"));
			person.setEdPositionName(rs.getString("ED_POSITION_NAME"));
			person.setEdOffcode(rs.getString("ED_OFFCODE"));
			person.setName(rs.getString("ED_PERSON_NAME"));
			person.setValue(rs.getString("ED_LOGIN"));
			person.setEdLogin(rs.getString("ED_LOGIN"));
			return person;
		}
	};

	@Override
	public List<ExcisePersonVoSelect> findByEdLogin(String edLogin) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" SELECT * FROM EXCISE_PERSON  ");
		sql.append(" WHERE ED_PERSON_NAME LIKE ? ");
		sql.append(" AND IS_DELETED = 'N' ");
		params.add(" IN (" + edLogin + ")");

		return commonJdbcTemplate.query(sql.toString(), params.toArray(), edPersonRowMapper);
	}

}
