package th.go.excise.ims.ta.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.go.excise.ims.ta.vo.TaxAuditAnalysisVo;

@Repository
public class TaxAuditAnalysisRepository {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<TaxAuditAnalysisVo> findAll() {
		String sqlTemplate = " SELECT * FROM TA_ANALYSIS_DATA_DETAIL ";
		StringBuilder sql = new StringBuilder(sqlTemplate);
		List<TaxAuditAnalysisVo> list = new ArrayList<>();
		list = jdbcTemplate.query(sql.toString(), rowMapper);
		return list;
	}
	
	protected RowMapper<TaxAuditAnalysisVo> rowMapper = new RowMapper<TaxAuditAnalysisVo>() {
		@Override
		public TaxAuditAnalysisVo mapRow(ResultSet rs, int arg1) throws SQLException {
			TaxAuditAnalysisVo vo = new TaxAuditAnalysisVo();
			vo.setAnalysisDataDetailId(rs.getBigDecimal("ANALYSIS_DATA_DETAIL_ID"));
			vo.setAnalysisDataId(rs.getBigDecimal("ANALYSIS_DATA_ID"));
			vo.setExciseFileUploadId(rs.getBigDecimal("TA_EXCISE_FILE_UPLOAD_ID"));
			vo.setExciseId(rs.getBigDecimal("EXCISE_ID"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(rs.getDate("CREATED_DATE"));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			return vo;
		}
	};
	
}
