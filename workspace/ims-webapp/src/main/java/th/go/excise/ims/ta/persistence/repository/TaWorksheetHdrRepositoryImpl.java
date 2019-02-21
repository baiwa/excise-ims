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
import th.co.baiwa.buckwaframework.common.util.LocalDateTimeConverter;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;

public class TaWorksheetHdrRepositoryImpl implements TaWorksheetHdrCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	private final String TABLE_NAME = "TA_WORKSHEET_HDR";

	@Override
	public void insertBatch(List<TaWorksheetHdr> taWorksheetHdrList) throws SQLException {
		String sql = SqlGeneratorUtils.genSqlInsert(TABLE_NAME,
				Arrays.asList("worksheet_hdr_id", "analysis_number", "new_reg_id", "sum_tax_amt_g1", "sum_tax_amt_g2", "tax_amt_chn_pnt", "tax_month_no", "tax_amt_g1_m1", "tax_amt_g1_m2", "tax_amt_g1_m3", "tax_amt_g1_m4", "tax_amt_g1_m5", "tax_amt_g1_m6", "tax_amt_g1_m7", "tax_amt_g1_m8",
						"tax_amt_g1_m9", "tax_amt_g1_m10", "tax_amt_g1_m11", "tax_amt_g1_m12", "tax_amt_g2_m1", "tax_amt_g2_m2", "tax_amt_g2_m3", "tax_amt_g2_m4", "tax_amt_g2_m5", "tax_amt_g2_m6", "tax_amt_g2_m7", "tax_amt_g2_m8", "tax_amt_g2_m9", "tax_amt_g2_m10", "tax_amt_g2_m11",
						"tax_amt_g2_m12", "cond_tax_grp", "created_by"),
				"TA_WORKSHEET_HDR_SEQ");

		commonJdbcTemplate.batchUpdate(sql, taWorksheetHdrList, 1000, new ParameterizedPreparedStatementSetter<TaWorksheetHdr>() {
			public void setValues(PreparedStatement ps, TaWorksheetHdr taWorksheetHdr) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(taWorksheetHdr.getAnalysisNumber());
				paramList.add(taWorksheetHdr.getNewRegId());
				paramList.add(taWorksheetHdr.getSumTaxAmtG1());
				paramList.add(taWorksheetHdr.getSumTaxAmtG2());
				paramList.add(taWorksheetHdr.getTaxAmtChnPnt());
				paramList.add(taWorksheetHdr.getTaxMonthNo());
				paramList.add(taWorksheetHdr.getTaxAmtG1M1());
				paramList.add(taWorksheetHdr.getTaxAmtG1M2());
				paramList.add(taWorksheetHdr.getTaxAmtG1M3());
				paramList.add(taWorksheetHdr.getTaxAmtG1M4());
				paramList.add(taWorksheetHdr.getTaxAmtG1M5());
				paramList.add(taWorksheetHdr.getTaxAmtG1M6());
				paramList.add(taWorksheetHdr.getTaxAmtG1M7());
				paramList.add(taWorksheetHdr.getTaxAmtG1M8());
				paramList.add(taWorksheetHdr.getTaxAmtG1M9());
				paramList.add(taWorksheetHdr.getTaxAmtG1M10());
				paramList.add(taWorksheetHdr.getTaxAmtG1M11());
				paramList.add(taWorksheetHdr.getTaxAmtG1M12());
				paramList.add(taWorksheetHdr.getTaxAmtG2M1());
				paramList.add(taWorksheetHdr.getTaxAmtG2M2());
				paramList.add(taWorksheetHdr.getTaxAmtG2M3());
				paramList.add(taWorksheetHdr.getTaxAmtG2M4());
				paramList.add(taWorksheetHdr.getTaxAmtG2M5());
				paramList.add(taWorksheetHdr.getTaxAmtG2M6());
				paramList.add(taWorksheetHdr.getTaxAmtG2M7());
				paramList.add(taWorksheetHdr.getTaxAmtG2M8());
				paramList.add(taWorksheetHdr.getTaxAmtG2M9());
				paramList.add(taWorksheetHdr.getTaxAmtG2M10());
				paramList.add(taWorksheetHdr.getTaxAmtG2M11());
				paramList.add(taWorksheetHdr.getTaxAmtG2M12());
				paramList.add(taWorksheetHdr.getCondTaxGrp());
				paramList.add("SYSTEM");

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

			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			vo.setAnalysisNumber(rs.getString("ANALYSIS_NUMBER"));
			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			vo.setSumTaxAmtG1(rs.getBigDecimal("SUM_TAX_AMT_G1") == null ? BigDecimal.ZERO : rs.getBigDecimal("SUM_TAX_AMT_G1"));
			vo.setSumTaxAmtG2(rs.getBigDecimal("SUM_TAX_AMT_G2") == null ? BigDecimal.ZERO : rs.getBigDecimal("SUM_TAX_AMT_G2"));
			vo.setTaxAmtChnPnt(rs.getBigDecimal("TAX_AMT_CHN_PNT") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_CHN_PNT"));
			vo.setTaxMonthNo(rs.getBigDecimal("TAX_MONTH_NO") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_MONTH_NO"));
			vo.setTaxAmtG1M1(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G1_M1") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G1_M1")));
			vo.setTaxAmtG1M2(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G1_M2") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G1_M2")));
			vo.setTaxAmtG1M3(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G1_M3") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G1_M3")));
			vo.setTaxAmtG1M4(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G1_M4") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G1_M4")));
			vo.setTaxAmtG1M5(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G1_M5") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G1_M5")));
			vo.setTaxAmtG1M6(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G1_M6") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G1_M6")));
			vo.setTaxAmtG1M7(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G1_M7") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G1_M7")));
			vo.setTaxAmtG1M8(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G1_M8") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G1_M8")));
			vo.setTaxAmtG1M9(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G1_M9") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G1_M9")));
			vo.setTaxAmtG1M10(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G1_M10") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G1_M10")));
			vo.setTaxAmtG1M11(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G1_M11") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G1_M11")));
			vo.setTaxAmtG1M12(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G1_M12") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G1_M12")));
			vo.setTaxAmtG2M1(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G2_M1") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G2_M1")));
			vo.setTaxAmtG2M2(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G2_M2") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G2_M2")));
			vo.setTaxAmtG2M3(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G2_M3") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G2_M3")));
			vo.setTaxAmtG2M4(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G2_M4") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G2_M4")));
			vo.setTaxAmtG2M5(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G2_M5") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G2_M5")));
			vo.setTaxAmtG2M6(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G2_M6") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G2_M6")));
			vo.setTaxAmtG2M7(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G2_M7") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G2_M7")));
			vo.setTaxAmtG2M8(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G2_M8") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G2_M8")));
			vo.setTaxAmtG2M9(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G2_M9") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G2_M9")));
			vo.setTaxAmtG2M10(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G2_M10") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G2_M10")));
			vo.setTaxAmtG2M11(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G2_M11") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G2_M11")));
			vo.setTaxAmtG2M12(decimalFormat.format(rs.getBigDecimal("TAX_AMT_G2_M12") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_G2_M12")));
			vo.setFacType(rs.getString("FAC_TYPE"));
			vo.setRegDate(LocalDateTimeConverter.convertToEntityAttribute(rs.getTimestamp("REG_DATE")));
			vo.setRegCapital(decimalFormat.format(rs.getBigDecimal("REG_CAPITAL") == null ? BigDecimal.ZERO : rs.getBigDecimal("REG_CAPITAL")));
			return vo;
		}
	};

}
