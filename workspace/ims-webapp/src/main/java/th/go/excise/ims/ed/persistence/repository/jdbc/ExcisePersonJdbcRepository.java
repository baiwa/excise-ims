package th.go.excise.ims.ed.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ed.vo.Ed02Vo;

@Repository
public class ExcisePersonJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Ed02Vo> listUser() {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT * FROM EXCISE_PERSON WHERE IS_DELETED = 'N' ORDER BY ED_PERSON_SEQ ASC ");
		List<Ed02Vo> datas = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(), listUserRowMapper);
		return datas;
	}

	private RowMapper<Ed02Vo> listUserRowMapper = new RowMapper<Ed02Vo>() {
		@Override
		public Ed02Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ed02Vo vo = new Ed02Vo();
			vo.setEdPersonId(rs.getString("ED_PERSON_ID"));
			vo.setEdPersonName(rs.getString("ED_PERSON_NAME"));
			vo.setEdPositionName(rs.getString("ED_POSITION_NAME"));
			return vo;
		}
	};
}
