package th.go.excise.ims.preferences.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.preferences.vo.Ed0101Vo;
import th.go.excise.ims.preferences.vo.Ed01Vo;

@Repository
public class ExcisePersonJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Ed0101Vo> listUser() {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT * FROM EXCISE_PERSON WHERE IS_DELETED = 'N' ORDER BY ED_PERSON_SEQ ASC ");
		List<Ed0101Vo> datas = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(), listUserRowMapper);
		return datas;
	}

	private RowMapper<Ed0101Vo> listUserRowMapper = new RowMapper<Ed0101Vo>() {
		@Override
		public Ed0101Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ed0101Vo vo = new Ed0101Vo();
			vo.setEdPersonId(rs.getString("ED_PERSON_ID"));
			vo.setEdPersonName(rs.getString("ED_PERSON_NAME"));
			vo.setEdPositionName(rs.getString("ED_POSITION_NAME"));
			vo.setEdOffcode(rs.getString("ED_OFFCODE"));
			vo.setEdLogin(rs.getString("ED_LOGIN"));
			return vo;
		}
	};
	
	public List<Ed01Vo> getIdCard(String username) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM EXCISE_PERSON WHERE ED_LOGIN = ? AND IS_DELETED ='N' ");
		params.add(username);
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Ed01Vo> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Ed01Vo.class));
		return datas;
	}
	
	
}
