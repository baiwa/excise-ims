package th.go.excise.ims.ta.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.TaxOperatorVo.TaxOperatorVoList;

@Repository
public class TaxOperatorJdbcRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;


	// TODO get operator
	// public List<TaxOperatorVo> getOperator(TaxOperatorFormVo formVo) {
	// StringBuilder sql = new StringBuilder();
	// sql.append(" SELECT ");
	// sql.append(" WS4000.NEW_REG_ID,");
	// sql.append(" WS4000.CUS_FULLNAME,");
	// sql.append(" WS4000.FAC_FULLNAME,  ");
	// sql.append(" WS4000.FAC_ADDRESS,");
	// sql.append(" WS4000.OFFICE_CODE,");
	// sql.append(" EXC_SEC.SECTOR_NAME2 SECTOR_NAME,");
	// sql.append(" EXC_AR.AREA_SHOT_NAME");
	// sql.append(" FROM TA_WS_REG4000 WS4000");
	// sql.append(" INNER JOIN TA_WS_INC8000_M WS8000 ");
	// sql.append("   ON WS4000.NEW_REG_ID=WS8000.NEW_REG_ID");
	// sql.append(" INNER JOIN EXCISE_AREA EXC_AR");
	// sql.append("   ON WS4000.OFFICE_CODE=EXC_AR.OFFICE_CODE");
	// sql.append(" INNER JOIN EXCISE_SECTOR EXC_SEC");
	// sql.append("  ON EXC_AR.SECTOR_ID=EXC_SEC.SECTOR_ID");
	// sql.append(" WHERE 1=1 AND EXC_SEC.SECTOR_NAME2 like'ภาคที่ 2' ");
	// sql.append(" AND CONCAT(");
	// sql.append("     TAX_YEAR,");
	// sql.append("     CASE");
	// sql.append("     WHEN  LENGTH(TAX_MONTH)=1 THEN CONCAT('0',TAX_MONTH)");
	// sql.append("     ELSE TAX_MONTH");
	// sql.append("     END");
	// sql.append("   ) BETWEEN ? AND ?");
	// sql.append(" GROUP BY ");
	// sql.append(" WS4000.NEW_REG_ID,");
	// sql.append(" WS4000.CUS_FULLNAME,");
	// sql.append(" WS4000.FAC_FULLNAME,  ");
	// sql.append(" WS4000.FAC_ADDRESS,");
	// sql.append(" WS4000.OFFICE_CODE,");
	// sql.append(" EXC_SEC.SECTOR_NAME2,");
	// sql.append(" EXC_AR.AREA_SHOT_NAME");
	// sql.append(" ORDER BY WS4000.NEW_REG_ID");
	//
	// List<Object> params = new ArrayList<>();
	// params.add(formVo.getDateStart());
	// params.add(formVo.getDateEnd());
	// return jdbcTemplate.query(sql.toString(), params.toArray(), rowMapper);
	// }
	//
	// protected RowMapper<TaxOperatorVo> rowMapper = new
	// RowMapper<TaxOperatorVo>() {
	// @Override
	// public TaxOperatorVo mapRow(ResultSet rs, int arg1) throws SQLException {
	// TaxOperatorVo vo = new TaxOperatorVo();
	// vo.setNewRegId(rs.getString("NEW_REG_ID"));
	// vo.setCusFullname(rs.getString("CUS_FULLNAME"));
	// vo.setFacFullname(rs.getString("FAC_FULLNAME"));
	// vo.setFacAddress(rs.getString("FAC_ADDRESS"));
	// vo.setOfficeCode(rs.getString("OFFICE_CODE"));;
	// vo.setSectorName(rs.getString("SECTOR_NAME"));
	// vo.setAreaShotName(rs.getString("AREA_SHOT_NAME"));
	// return vo;
	// }
	// };
	//
	// // TODO get tax pay operator
	// public List<String> getYearTax(TaxOperatorFormVo formVo) {
	// StringBuilder sql = new StringBuilder();
	// sql.append(" select  ");
	// sql.append("     DISTINCT ");
	// sql.append("     TAX_YEAR ");
	// sql.append(" from TA_WS_INC8000_M  ");
	// sql.append(" WHERE NEW_REG_ID=? ");
	// sql.append(" AND CONCAT( ");
	// sql.append("       TAX_YEAR, ");
	// sql.append("       CASE ");
	// sql.append("       WHEN  LENGTH(TAX_MONTH)=1 THEN concat('0',TAX_MONTH) ");
	// sql.append("       ELSE TAX_MONTH ");
	// sql.append("       END ");
	// sql.append("     ) between ? and ? ORDER BY TAX_YEAR ASC ");
	//
	// List<Object> params = new ArrayList<>();
	// params.add(formVo.getNewRegId());
	// params.add(formVo.getDateStart());
	// params.add(formVo.getDateEnd());
	// return jdbcTemplate.query(sql.toString(), params.toArray(),
	// yearRowMapper);
	// }
	//
	// protected RowMapper<String> yearRowMapper = new RowMapper<String>() {
	// @Override
	// public String mapRow(ResultSet rs, int arg1) throws SQLException {
	//
	// return rs.getString("TAX_YEAR");
	// }
	// };

	/*
	 * public List<TaxPay> getMonthTax(TaxOperatorFormVo formVo) { StringBuilder
	 * sql = new StringBuilder(); sql.append(" SELECT ");
	 * sql.append(" TAX_YEAR, "); sql.append(" TAX_MONTH,");
	 * sql.append(" CONCAT("); sql.append("   TAX_YEAR,CASE");
	 * sql.append("   WHEN  LENGTH(TAX_MONTH)=1 THEN CONCAT('0',TAX_MONTH)");
	 * sql.append("   ELSE TAX_MONTH"); sql.append("   END");
	 * sql.append(" ) TAX_DATE,"); sql.append(" SUM(TAX_AMOUNT)TAX_AMOUNT");
	 * sql.append(" FROM TA_WS_INC8000_M "); sql.append(" WHERE NEW_REG_ID=?");
	 * sql.append(" AND CONCAT("); sql.append("   TAX_YEAR,");
	 * sql.append("   CASE");
	 * sql.append("   WHEN  LENGTH(TAX_MONTH)=1 THEN CONCAT('0',TAX_MONTH)");
	 * sql.append("   ELSE TAX_MONTH"); sql.append("   END");
	 * sql.append(" ) BETWEEN ? AND ?"); sql.append(" GROUP BY ");
	 * sql.append(" TAX_YEAR,"); sql.append(" TAX_MONTH,");
	 * sql.append(" CONCAT(TAX_YEAR,CASE"); sql.append(
	 * "               WHEN  LENGTH(TAX_MONTH)=1 THEN CONCAT('0',TAX_MONTH)");
	 * sql.append("               ELSE TAX_MONTH");
	 * sql.append("               END"); sql.append("    )");
	 * sql.append(" ORDER BY     TAX_DATE ASC");
	 * 
	 * 
	 * List<Object> params = new ArrayList<>();
	 * params.add(formVo.getNewRegId()); params.add(formVo.getDateStart());
	 * params.add(formVo.getDateEnd()); return
	 * jdbcTemplate.query(sql.toString(), params.toArray(), MonthRowMapper); }
	 * 
	 * protected RowMapper<TaxPay> MonthRowMapper = new RowMapper<TaxPay>() {
	 * 
	 * @Override public TaxPay mapRow(ResultSet rs, int arg1) throws
	 * SQLException { TaxPay taxPay = new TaxPay();
	 * taxPay.setYear(rs.getString("TAX_YEAR"));
	 * taxPay.setMonth(rs.getString("TAX_MONTH"));
	 * taxPay.setTaxAmount(rs.getBigDecimal("TAX_AMOUNT")); return taxPay; } };
	 */

	// TODO get operator new
	public List<TaxOperatorVoList> getTaxOperator(TaxOperatorFormVo formVo) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT  ");
		sql.append("     R4000.CUS_FULLNAME, ");
		sql.append("     R4000.FAC_FULLNAME, ");
		sql.append("     R4000.FAC_ADDRESS, ");
		sql.append("     R4000.OFFICE_CODE, ");
		sql.append("      SEC.SECTOR_NAME2, ");
		sql.append("     EXC_AR.AREA_SHOT_NAME, ");
		sql.append("     TA_W_HDR.*  ");
		sql.append(" FROM TA_WORKSHEET_HDR TA_W_HDR  ");
		sql.append("   INNER JOIN TA_WS_REG4000 R4000 ");
		sql.append("     ON R4000.NEW_REG_ID=TA_W_HDR.NEW_REG_ID ");
		sql.append("   INNER JOIN EXCISE_AREA EXC_AR ");
		sql.append("     ON R4000.OFFICE_CODE=EXC_AR.OFFICE_CODE ");
		sql.append("   INNER JOIN EXCISE_SECTOR SEC ");
		sql.append("     ON SEC.SECTOR_ID=EXC_AR.SECTOR_ID ");
		sql.append("     WHERE ANALYSIS_NUMBER=? ");

		List<Object> params = new ArrayList<>();
		params.add(formVo.getAnalysisNumber());
		return jdbcTemplate.query(sql.toString(), params.toArray(), taxOperatorrowMapper);
	}

	protected RowMapper<TaxOperatorVoList> taxOperatorrowMapper = new RowMapper<TaxOperatorVoList>() {
		@Override
		public TaxOperatorVoList mapRow(ResultSet rs, int arg1) throws SQLException {
			TaxOperatorVoList vo = new TaxOperatorVoList();

			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			vo.setCusFullname(rs.getString("CUS_FULLNAME"));
			vo.setFacFullname(rs.getString("FAC_FULLNAME"));
			vo.setFacAddress(rs.getString("FAC_ADDRESS"));
			vo.setOfficeCode(rs.getString("OFFICE_CODE"));
			vo.setSectorName2(rs.getString("SECTOR_NAME2"));
			vo.setAreaShotName(rs.getString("AREA_SHOT_NAME"));
			vo.setWorksheetHdrId(rs.getString("WORKSHEET_HDR_ID"));
			vo.setAnalysisNumber(rs.getString("ANALYSIS_NUMBER"));
			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			vo.setSumTaxAmtG1(rs.getBigDecimal("SUM_TAX_AMT_G1"));
			vo.setSumTaxAmtG2(rs.getBigDecimal("SUM_TAX_AMT_G2"));
			vo.setTaxAmtChnPnt(rs.getBigDecimal("TAX_AMT_CHN_PNT"));
			vo.setTaxMonthNo(rs.getBigDecimal("TAX_MONTH_NO"));
			vo.setTaxAmtG1M1(rs.getBigDecimal("TAX_AMT_G1_M1"));
			vo.setTaxAmtG1M2(rs.getBigDecimal("TAX_AMT_G1_M2"));
			vo.setTaxAmtG1M3(rs.getBigDecimal("TAX_AMT_G1_M3"));
			vo.setTaxAmtG1M4(rs.getBigDecimal("TAX_AMT_G1_M4"));
			vo.setTaxAmtG1M5(rs.getBigDecimal("TAX_AMT_G1_M5"));
			vo.setTaxAmtG1M6(rs.getBigDecimal("TAX_AMT_G1_M6"));
			vo.setTaxAmtG1M7(rs.getBigDecimal("TAX_AMT_G1_M7"));
			vo.setTaxAmtG1M8(rs.getBigDecimal("TAX_AMT_G1_M8"));
			vo.setTaxAmtG1M9(rs.getBigDecimal("TAX_AMT_G1_M9"));
			vo.setTaxAmtG1M10(rs.getBigDecimal("TAX_AMT_G1_M10"));
			vo.setTaxAmtG1M11(rs.getBigDecimal("TAX_AMT_G1_M11"));
			vo.setTaxAmtG1M12(rs.getBigDecimal("TAX_AMT_G1_M12"));
			vo.setTaxAmtG2M1(rs.getBigDecimal("TAX_AMT_G2_M1"));
			vo.setTaxAmtG2M2(rs.getBigDecimal("TAX_AMT_G2_M2"));
			vo.setTaxAmtG2M3(rs.getBigDecimal("TAX_AMT_G2_M3"));
			vo.setTaxAmtG2M4(rs.getBigDecimal("TAX_AMT_G2_M4"));
			vo.setTaxAmtG2M5(rs.getBigDecimal("TAX_AMT_G2_M5"));
			vo.setTaxAmtG2M6(rs.getBigDecimal("TAX_AMT_G2_M6"));
			vo.setTaxAmtG2M7(rs.getBigDecimal("TAX_AMT_G2_M7"));
			vo.setTaxAmtG2M8(rs.getBigDecimal("TAX_AMT_G2_M8"));
			vo.setTaxAmtG2M9(rs.getBigDecimal("TAX_AMT_G2_M9"));
			vo.setTaxAmtG2M10(rs.getBigDecimal("TAX_AMT_G2_M10"));
			vo.setTaxAmtG2M11(rs.getBigDecimal("TAX_AMT_G2_M11"));
			vo.setTaxAmtG2M12(rs.getBigDecimal("TAX_AMT_G2_M12"));
			vo.setCondTaxGrp(rs.getString("COND_TAX_GRP"));
			return vo;
		}
	};

	public List<String> listCondGroups(TaxOperatorFormVo formVo) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT  ");
		sql.append("     DISTINCT TA_W_HDR.COND_TAX_GRP  ");
		sql.append(" FROM TA_WORKSHEET_HDR TA_W_HDR  ");
		sql.append("   INNER JOIN TA_WS_REG4000 R4000 ");
		sql.append("     ON R4000.NEW_REG_ID=TA_W_HDR.NEW_REG_ID ");
		sql.append("   INNER JOIN EXCISE_AREA EXC_AR ");
		sql.append("     ON R4000.OFFICE_CODE=EXC_AR.OFFICE_CODE ");
		sql.append("   INNER JOIN EXCISE_SECTOR SEC ");
		sql.append("     ON SEC.SECTOR_ID=EXC_AR.SECTOR_ID ");
		sql.append("     WHERE ANALYSIS_NUMBER=? ");

		List<Object> params = new ArrayList<>();
		params.add(formVo.getAnalysisNumber());
		return jdbcTemplate.queryForList(sql.toString(), params.toArray(), String.class);
	}

	
}