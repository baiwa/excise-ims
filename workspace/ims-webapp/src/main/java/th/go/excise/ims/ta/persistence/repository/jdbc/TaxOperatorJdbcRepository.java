package th.go.excise.ims.ta.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;

@Repository
public class TaxOperatorJdbcRepository {

    @Autowired
    private CommonJdbcTemplate commonJdbcTemplate;

    public List<TaxOperatorDetailVo> getTaxOperator(String analysisNumber) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append("   R4000.CUS_FULLNAME,");
        sql.append("   R4000.FAC_FULLNAME,");
        sql.append("   R4000.FAC_ADDRESS,");
        sql.append("   R4000.OFFICE_CODE,");
        sql.append("   ED_SECTOR.OFFICE_CODE SEC_CODE,");
        sql.append("   ED_SECTOR.DEPT_SHORT_NAME SEC_DESC,");
        sql.append("   ED_AREA.OFFICE_CODE AREA_CODE,");
        sql.append("   ED_AREA.DEPT_SHORT_NAME AREA_DESC,");
        sql.append("   TA_W_HDR.*");
        sql.append(" FROM TA_WORKSHEET_HDR TA_W_HDR");
        sql.append(" INNER JOIN TA_WS_REG4000 R4000 ON R4000.NEW_REG_ID = TA_W_HDR.NEW_REG_ID AND R4000.IS_DELETED = 'N'");
        sql.append(" INNER JOIN (");
        sql.append("   SELECT OFFICE_CODE, DEPT_NAME, DEPT_SHORT_NAME");
        sql.append("   FROM EXCISE_DEPARTMENT");
        sql.append("   WHERE IS_DELETED = 'N'");
        sql.append("     AND CONCAT(SUBSTR(OFFICE_CODE, 0, 2),'0000') = OFFICE_CODE");
        sql.append(" ) ED_SECTOR ON ED_SECTOR.OFFICE_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 2),'0000')");
        sql.append(" INNER JOIN (");
        sql.append("   SELECT OFFICE_CODE, DEPT_NAME, DEPT_SHORT_NAME");
        sql.append("   FROM EXCISE_DEPARTMENT");
        sql.append("   WHERE IS_DELETED = 'N'");
        sql.append("     AND CONCAT(SUBSTR(OFFICE_CODE, 0, 4),'00') = OFFICE_CODE");
        sql.append(" ) ED_AREA ON ED_AREA.OFFICE_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 4),'00')");
        sql.append(" WHERE TA_W_HDR.IS_DELETED = 'N'");
        sql.append("   AND ANALYSIS_NUMBER = ?");

        return commonJdbcTemplate.query(sql.toString(), new Object[]{ analysisNumber }, taxOperatorrowMapper);
    }

    protected RowMapper<TaxOperatorDetailVo> taxOperatorrowMapper = new RowMapper<TaxOperatorDetailVo>() {
    	private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
    	
        @Override
        public TaxOperatorDetailVo mapRow(ResultSet rs, int arg1) throws SQLException {
            TaxOperatorDetailVo vo = new TaxOperatorDetailVo();

            vo.setNewRegId(rs.getString("NEW_REG_ID"));
            vo.setCusFullname(rs.getString("CUS_FULLNAME"));
            vo.setFacFullname(rs.getString("FAC_FULLNAME"));
            vo.setFacAddress(rs.getString("FAC_ADDRESS"));
            vo.setOfficeCode(rs.getString("OFFICE_CODE"));
            vo.setSecCode(rs.getString("SEC_CODE"));
            vo.setSecDesc(rs.getString("SEC_DESC"));
            vo.setAreaCode(rs.getString("AREA_CODE"));
            vo.setAreaDesc(rs.getString("AREA_DESC"));
            vo.setWorksheetHdrId(rs.getString("WORKSHEET_HDR_ID"));
            vo.setAnalysisNumber(rs.getString("ANALYSIS_NUMBER"));
            vo.setNewRegId(rs.getString("NEW_REG_ID"));
            vo.setSumTaxAmtG1(decimalFormat.format(rs.getBigDecimal("SUM_TAX_AMT_G1") == null ?BigDecimal.ZERO : rs.getBigDecimal("SUM_TAX_AMT_G1")));
            vo.setSumTaxAmtG2(decimalFormat.format(rs.getBigDecimal("SUM_TAX_AMT_G2") == null ? BigDecimal.ZERO : rs.getBigDecimal("SUM_TAX_AMT_G2")));
            vo.setTaxAmtChnPnt(decimalFormat.format(rs.getBigDecimal("TAX_AMT_CHN_PNT") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_AMT_CHN_PNT")));
            vo.setTaxMonthNo(decimalFormat.format(rs.getBigDecimal("TAX_MONTH_NO") == null ? BigDecimal.ZERO : rs.getBigDecimal("TAX_MONTH_NO")));
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
            vo.setCondTaxGrp(rs.getString("COND_TAX_GRP"));
            return vo;
        }
    };

    public List<String> listCondGroups(String analysisNumber) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT DISTINCT COND_TAX_GRP FROM ( ");
        sql.append("  SELECT R4000.CUS_FULLNAME, ");
        sql.append("         R4000.FAC_FULLNAME, ");
        sql.append("         R4000.FAC_ADDRESS, ");
        sql.append("         R4000.OFFICE_CODE, ");
        sql.append("         AREA.SEC_CODE, ");
        sql.append("         AREA.SEC_DESC, ");
        sql.append("         AREA.AREA_CODE, ");
        sql.append("         AREA.AREA_DESC, ");
        sql.append("         TA_W_HDR.* ");
        sql.append("         FROM TA_WORKSHEET_HDR TA_W_HDR ");
        sql.append("         INNER JOIN TA_WS_REG4000 R4000 ");
        sql.append("         ON R4000.NEW_REG_ID=TA_W_HDR.NEW_REG_ID ");
        sql.append("         INNER JOIN EXCISE_DEPARTMENT EXC_AR ");
        sql.append("         ON R4000.OFFICE_CODE=EXC_AR.OFFICE_CODE ");
        sql.append("         INNER JOIN ( ");
        sql.append("         SELECT DISTINCT ");
        sql.append("         CONCAT(SUBSTR(T1.OFFICE_CODE,0,2),'0000') SEC_CODE, ");
        sql.append("         T2.DEPT_SHORT_NAME SEC_DESC, ");
        sql.append("         CONCAT(SUBSTR(T1.OFFICE_CODE,0,4),'00') AREA_CODE, ");
        sql.append("         T3.DEPT_SHORT_NAME AREA_DESC ");
        sql.append("         FROM EXCISE_DEPARTMENT T1 ");
        sql.append("         INNER JOIN EXCISE_DEPARTMENT  T2 ");
        sql.append("         ON T2.OFFICE_CODE=CONCAT(SUBSTR(T1.OFFICE_CODE,0,2),'0000') ");
        sql.append("         INNER JOIN EXCISE_DEPARTMENT T3 ");
        sql.append("         ON T3.OFFICE_CODE = CONCAT(SUBSTR(T1.OFFICE_CODE,0,4),'00') ");
        sql.append("         ) AREA ");
        sql.append("         ON AREA.AREA_CODE=CONCAT(SUBSTR(EXC_AR.OFFICE_CODE,0,4),'00') ");
        sql.append("  ");
        sql.append("  ");
        sql.append("         WHERE TA_W_HDR.ANALYSIS_NUMBER= ? ");
        sql.append(" ) ORDER BY COND_TAX_GRP ASC ");

        return commonJdbcTemplate.queryForList(sql.toString(), new Object[]{ analysisNumber }, String.class);
    }
}