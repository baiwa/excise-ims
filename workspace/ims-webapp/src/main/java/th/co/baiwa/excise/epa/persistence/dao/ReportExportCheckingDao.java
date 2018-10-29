package th.co.baiwa.excise.epa.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.epa.persistence.vo.Epa014FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa014Vo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class ReportExportCheckingDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String SQL_SEARCH_DATA = " SELECT T1.EXCISE_ID EXCISE_ID,  " + 
			"       T1.EXCISE_NAME EXCISE_NAME,  " + 
			"       T2.DESTINATION DESTINATION,  " + 
			"       T2.DATE_DESTINATION DATE_DESTINATION  " + 
			"FROM TAX_RE_05_01_1 T1  " + 
			"JOIN TAX_RE_05_01_2 T2 ON T1.EXCISE_ID = T2.EXCISE_ID  " + 
			"WHERE 1=1 ";
	
	public List<Epa014Vo> search(Epa014FormVo epa014FormVo) {
		StringBuilder sql = new StringBuilder(SQL_SEARCH_DATA);
		List<Object> params = new ArrayList<>();
		
		if (StringUtils.isNotBlank(epa014FormVo.getExciseId())) {
			sql.append(" AND T1.EXCISE_ID = ? ");
			params.add(epa014FormVo.getExciseId());
		}
		
		String sqlLimit = OracleUtils.limitForDataTable(sql, epa014FormVo.getStart(), epa014FormVo.getLength());
		List<Epa014Vo> list = jdbcTemplate.query(sqlLimit, params.toArray(), ExportCheckingConnectRowMapper);
		return list;
	}
	
	private RowMapper<Epa014Vo> ExportCheckingConnectRowMapper = new RowMapper<Epa014Vo>() {
		@Override
		public Epa014Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Epa014Vo vo = new Epa014Vo();
			vo.setExciseId(rs.getString("EXCISE_ID"));
			return vo;
		}
	};
}
