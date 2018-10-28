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

import th.co.baiwa.excise.epa.persistence.vo.Epa011FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa011Vo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class ExportCheckingDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String SQL_SEARCH_DATA = " SELECT T1.EXCISE_ID EXCISE_ID, T1.EXCISE_NAME EXCISE_NAME, T2.DESTINATION DESTINATION, T2.DATE_DESTINATION DATE_DESTINATION " + 
			"FROM TAX_RE_05_01_1 T1  " + 
			"JOIN   " + 
			"TAX_RE_05_01_2 T2  " + 
			"ON T1.EXCISE_ID = T2.EXCISE_ID  " + 
			"WHERE 1=1 ";
	
	public long countExportCheckingData(Epa011FormVo epa011FormVo) {
		StringBuilder sql = new StringBuilder(SQL_SEARCH_DATA);
		List<Object> params = new ArrayList<>();
		
		if (StringUtils.isNotBlank(epa011FormVo.getExciseId())) {
			sql.append(" AND T1.EXCISE_ID = ? ");
			params.add(epa011FormVo.getExciseId());
		}
		
		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}

	public List<Epa011Vo> listExportCheckingData(Epa011FormVo epa011FormVo) {
		StringBuilder sql = new StringBuilder(SQL_SEARCH_DATA);
		List<Object> params = new ArrayList<>();
		
		if (StringUtils.isNotBlank(epa011FormVo.getExciseId())) {
			sql.append(" AND T1.EXCISE_ID = ? ");
			params.add(StringUtils.trim(epa011FormVo.getExciseId()));
		}
		
		String sqlLimit = OracleUtils.limitForDataTable(sql, epa011FormVo.getStart(), epa011FormVo.getLength());
		List<Epa011Vo> list = jdbcTemplate.query(sqlLimit, params.toArray(), ExportCheckingRowMapper);
		return list;
	}
	
	private RowMapper<Epa011Vo> ExportCheckingRowMapper = new RowMapper<Epa011Vo>() {
		@Override
		public Epa011Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Epa011Vo vo = new Epa011Vo();
			vo.setExciseId(rs.getString("EXCISE_ID"));
			vo.setExciseName(rs.getString("EXCISE_NAME"));
			vo.setDestination(rs.getString("DESTINATION"));
			vo.setDateDestination(rs.getString("DATE_DESTINATION"));
			return vo;
		}
	};

}
