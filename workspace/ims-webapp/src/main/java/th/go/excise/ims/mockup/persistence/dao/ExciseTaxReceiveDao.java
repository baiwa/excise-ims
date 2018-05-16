package th.go.excise.ims.mockup.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.go.excise.ims.mockup.persistence.entity.ExciseTaxReceive;

@Repository
public class ExciseTaxReceiveDao {
	@Autowired
	private JdbcTemplate JdbcTemplate;

	private final String sqlTaExciseId = " select * from ta_excise_tax_receive where TA_EXCISE_ID =  ? ";

	public List<ExciseTaxReceive> queryByExciseId(String register) {

		List<ExciseTaxReceive> list = JdbcTemplate.query(sqlTaExciseId, new Object[] { register },
				exciseRegisttionRowmapper);

		return list;
	}

	private RowMapper<ExciseTaxReceive> exciseRegisttionRowmapper = new RowMapper<ExciseTaxReceive>() {

		@Override
		public ExciseTaxReceive mapRow(ResultSet rs, int arg1) throws SQLException {
			ExciseTaxReceive vo = new ExciseTaxReceive();
			vo.setExciseTaxReceiveId(rs.getInt("TA_EXCISE_TAX_RECEIVE_ID"));
			vo.setExciseId(rs.getString("TA_EXCISE_ID"));
			vo.setExciseTaxReceiveMonth(rs.getString("TA_EXCISE_TAX_RECEIVE_MONTH"));
			vo.setExciseTaxReceiveAmount(rs.getString("TA_EXCISE_TAX_RECEIVE_AMOUNT"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDatetime(rs.getTimestamp("CREATED_DATETIME") != null ? rs.getTimestamp("CREATED_DATETIME").toLocalDateTime() : null);
			vo.setUpdateBy(rs.getString("UPDATE_BY"));
			vo.setUpdateDatetime(rs.getTimestamp("UPDATE_DATETIME") != null ? rs.getTimestamp("UPDATE_DATETIME").toLocalDateTime() : null);
			
//			if (rs.getTimestamp("UPDATE_DATETIME") != null) {
//				vo.setUpdateDatetime(rs.getTimestamp("UPDATE_DATETIME").toLocalDateTime());
//			} else {
//				vo.setUpdateDatetime(null);
//			}
			return vo;

		}

	};

}
