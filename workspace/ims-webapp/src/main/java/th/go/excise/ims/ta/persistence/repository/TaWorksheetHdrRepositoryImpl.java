package th.go.excise.ims.ta.persistence.repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;

public class TaWorksheetHdrRepositoryImpl implements TaWorksheetHdrCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	private final String TABLE_NAME = "TA_WORKSHEET_HDR";

	@Override
	public List<TaWorksheetHdr> findSubConditionRegCapital(BigDecimal from, BigDecimal to) throws SQLException {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder("SELECT * FROM ").append(TABLE_NAME).append(" WHERE IS_DELETED = '").append(FLAG.N_FLAG).append("' ");
		if (from != null) {
			sql.append(" AND REG_CAPITAL > ? ");
			paramList.add(from);
		}
		if (to != null) {
			sql.append(" AND REG_CAPITAL < ? ");
			paramList.add(to);
		}
		sql.append(" ORDER BY REG_CAPITAL DESC ");

		List<TaWorksheetHdr> taWorksheetHdrList = this.commonJdbcTemplate.query(sql.toString(), paramList.toArray(), rowMappping);
		return taWorksheetHdrList;
	}

	protected RowMapper<TaWorksheetHdr> rowMappping = new RowMapper<TaWorksheetHdr>() {
		@Override
		public TaWorksheetHdr mapRow(ResultSet rs, int arg1) throws SQLException {
			TaWorksheetHdr vo = new TaWorksheetHdr();
			vo.setAnalysisNumber(rs.getString("ANALYSIS_NUMBER"));
			return vo;
		}
	};

}
