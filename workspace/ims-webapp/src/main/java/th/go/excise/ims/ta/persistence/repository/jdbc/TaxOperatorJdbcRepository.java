package th.go.excise.ims.ta.persistence.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ta.vo.TaxOperatorVo.TaxOperatorVoList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TaxOperatorJdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CommonJdbcTemplate commonJdbcTemplate;

    public List<TaxOperatorVoList> getTaxOperator(String analysisNumber) {

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
        sql.append("     WHERE ANALYSIS_NUMBER= ?");

        return jdbcTemplate.query(sql.toString(), new Object[]{ analysisNumber }, taxOperatorrowMapper);
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

    public List<String> listCondGroups(String analysisNumber) {

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
        sql.append("     WHERE ANALYSIS_NUMBER=? ORDER BY TA_W_HDR.COND_TAX_GRP ASC");

        return jdbcTemplate.queryForList(sql.toString(), new Object[]{ analysisNumber }, String.class);
    }
}