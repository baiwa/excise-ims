package th.go.excise.ims.ta.persistence.repository;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;

public class TaWorksheetHdrRepositoryImpl implements TaWorksheetHdrCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	private final String TABLE_NAME = "TA_WORKSHEET_HDR";

	@Override
	public void insertBatch(List<TaWorksheetHdr> taWorksheetHdrList) throws SQLException {
		String sql = SqlGeneratorUtils.genSqlInsert(TABLE_NAME, Arrays.asList("WORKSHEET_HDR_ID", "OFFICE_CODE", "DRAFT_NUMBER", "ANALYSIS_NUMBER", "WORKSHEET_STATUS",  "CREATED_BY"), "TA_WORKSHEET_HDR_SEQ");

		commonJdbcTemplate.batchUpdate(sql, taWorksheetHdrList, 1000, new ParameterizedPreparedStatementSetter<TaWorksheetHdr>() {
			public void setValues(PreparedStatement ps, TaWorksheetHdr taWorksheetHdr) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(taWorksheetHdr.getOfficeCode());
				paramList.add(taWorksheetHdr.getDraftNumber());
				paramList.add(taWorksheetHdr.getAnalysisNumber());
				paramList.add(taWorksheetHdr.getWorksheetStatus());
				paramList.add(UserLoginUtils.getCurrentUsername());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}

		});
	}

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
		private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

		@Override
		public TaWorksheetHdr mapRow(ResultSet rs, int arg1) throws SQLException {
			TaWorksheetHdr vo = new TaWorksheetHdr();

			vo.setAnalysisNumber(rs.getString("ANALYSIS_NUMBER"));
			return vo;
		}
	};

}
