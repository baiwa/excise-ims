package th.co.baiwa.excise.ta.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.domain.LabelValueBean;

@Repository
public class AnalysisTaxDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String SQL = "SELECT * FROM TA_PLAN_WORK_SHEET_HEADER WHERE FLAG != 'N' AND IS_DELETED='N' ";
 
	public List<LabelValueBean> findAllExciseIdNotN() {

		StringBuilder sql = new StringBuilder(SQL);
        
        sql.append(" ORDER BY EXCISE_ID DESC ");
		List<LabelValueBean> list = jdbcTemplate.query(sql.toString(), exciseIdRowmapper);
		return list;
	}

	private RowMapper<LabelValueBean> exciseIdRowmapper = new RowMapper<LabelValueBean>() {
		@Override
		public LabelValueBean mapRow(ResultSet rs, int arg1) throws SQLException {
			return new LabelValueBean(rs.getString("EXCISE_ID"), rs.getString("EXCISE_ID"));
		}
	};
}
