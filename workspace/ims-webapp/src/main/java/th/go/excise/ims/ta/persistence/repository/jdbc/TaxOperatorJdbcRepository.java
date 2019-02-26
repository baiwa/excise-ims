package th.go.excise.ims.ta.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.vo.PlanWorkSheetVo;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

@Repository
public class TaxOperatorJdbcRepository {

    @Autowired
    private CommonJdbcTemplate commonJdbcTemplate;

    public Long countTaxOperator(TaxOperatorFormVo formVo) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT R4000.CUS_FULLNAME ");
        sql.append("   ,R4000.FAC_FULLNAME ");
        sql.append("   ,R4000.FAC_ADDRESS ");
        sql.append("   ,R4000.OFFICE_CODE OFFICE_CODE_R4000");
        sql.append("   ,ED_SECTOR.OFFICE_CODE SEC_CODE ");
        sql.append("   ,ED_SECTOR.DEPT_SHORT_NAME SEC_DESC ");
        sql.append("   ,ED_AREA.OFFICE_CODE AREA_CODE ");
        sql.append("   ,ED_AREA.DEPT_SHORT_NAME AREA_DESC ");
        sql.append("   ,TA_W_DTL.COND_MAIN_GRP ");
        sql.append("   , TA_W_HDR.ANALYSIS_NUMBER");
        sql.append("   ,TA_DW_DTL.* ");
        sql.append(" FROM TA_WORKSHEET_DTL TA_W_DTL ");
        sql.append(" INNER JOIN TA_WORKSHEET_HDR TA_W_HDR ON TA_W_DTL.ANALYSIS_NUMBER = TA_W_HDR.ANALYSIS_NUMBER ");
        sql.append("   AND TA_W_DTL.IS_DELETED = 'N' ");
        sql.append(" INNER JOIN TA_DRAFT_WORKSHEET_DTL TA_DW_DTL ON TA_DW_DTL.DRAFT_NUMBER = TA_W_HDR.DRAFT_NUMBER ");
        sql.append("   AND TA_DW_DTL.NEW_REG_ID = TA_W_DTL.NEW_REG_ID ");
        sql.append("   AND TA_DW_DTL.IS_DELETED = 'N' ");
        sql.append(" INNER JOIN TA_WS_REG4000 R4000 ON R4000.NEW_REG_ID = TA_W_DTL.NEW_REG_ID ");
        sql.append("   AND R4000.IS_DELETED = 'N' ");
        sql.append(" INNER JOIN ( ");
        sql.append("   SELECT OFFICE_CODE,DEPT_NAME,DEPT_SHORT_NAME ");
        sql.append("   FROM EXCISE_DEPARTMENT ");
        sql.append("   WHERE IS_DELETED = 'N' ");
        sql.append("     AND CONCAT (SUBSTR(OFFICE_CODE, 0, 2),'0000') = OFFICE_CODE ");
        sql.append(" ) ED_SECTOR ON ED_SECTOR.OFFICE_CODE = CONCAT (SUBSTR(R4000.OFFICE_CODE, 0, 2),'0000') ");
        sql.append(" INNER JOIN ( ");
        sql.append("   SELECT OFFICE_CODE,DEPT_NAME,DEPT_SHORT_NAME ");
        sql.append("   FROM EXCISE_DEPARTMENT ");
        sql.append("   WHERE IS_DELETED = 'N' ");
        sql.append("     AND CONCAT (SUBSTR(OFFICE_CODE, 0, 4),'00') = OFFICE_CODE ");
        sql.append(" ) ED_AREA ON ED_AREA.OFFICE_CODE = CONCAT (SUBSTR(R4000.OFFICE_CODE, 0, 4),'00') ");
        sql.append(" WHERE TA_W_HDR.IS_DELETED = 'N' ");
        sql.append("   AND TA_W_HDR.ANALYSIS_NUMBER = ? ");
        // sql.append("   AND TA_W_HDR.OFFICE_CODE = '010000' ");

        List<Object> params = new ArrayList<>();
        params.add(formVo.getAnalysisNumber());

        if (StringUtils.isNotBlank(formVo.getCond())) {
            sql.append("AND TA_W_DTL.COND_MAIN_GRP=? ");
            params.add(formVo.getCond());
        }

        String officeCode = ExciseUtils.whereInLocalOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());
        sql.append("AND R4000.OFFICE_CODE LIKE ?");
        params.add(officeCode);
        String countSql = OracleUtils.countForDataTable(sql.toString());
        return commonJdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
    }

    public List<TaxOperatorDetailVo> getTaxOperator(TaxOperatorFormVo formVo) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT R4000.CUS_FULLNAME ");
        sql.append("   ,R4000.FAC_FULLNAME ");
        sql.append("   ,R4000.FAC_ADDRESS ");
        sql.append("   ,R4000.OFFICE_CODE OFFICE_CODE_R4000");
        sql.append("   ,ED_SECTOR.OFFICE_CODE SEC_CODE ");
        sql.append("   ,ED_SECTOR.DEPT_SHORT_NAME SEC_DESC ");
        sql.append("   ,ED_AREA.OFFICE_CODE AREA_CODE ");
        sql.append("   ,ED_AREA.DEPT_SHORT_NAME AREA_DESC ");
        sql.append("   ,TA_W_DTL.COND_MAIN_GRP ");
        sql.append("   , TA_W_HDR.ANALYSIS_NUMBER");
        sql.append("   ,TA_DW_DTL.* ");
        sql.append(" FROM TA_WORKSHEET_DTL TA_W_DTL ");
        sql.append(" INNER JOIN TA_WORKSHEET_HDR TA_W_HDR ON TA_W_DTL.ANALYSIS_NUMBER = TA_W_HDR.ANALYSIS_NUMBER ");
        sql.append("   AND TA_W_DTL.IS_DELETED = 'N' ");
        sql.append(" INNER JOIN TA_DRAFT_WORKSHEET_DTL TA_DW_DTL ON TA_DW_DTL.DRAFT_NUMBER = TA_W_HDR.DRAFT_NUMBER ");
        sql.append("   AND TA_DW_DTL.NEW_REG_ID = TA_W_DTL.NEW_REG_ID ");
        sql.append("   AND TA_DW_DTL.IS_DELETED = 'N' ");
        sql.append(" INNER JOIN TA_WS_REG4000 R4000 ON R4000.NEW_REG_ID = TA_W_DTL.NEW_REG_ID ");
        sql.append("   AND R4000.IS_DELETED = 'N' ");
        sql.append(" INNER JOIN ( ");
        sql.append("   SELECT OFFICE_CODE,DEPT_NAME,DEPT_SHORT_NAME ");
        sql.append("   FROM EXCISE_DEPARTMENT ");
        sql.append("   WHERE IS_DELETED = 'N' ");
        sql.append("     AND CONCAT (SUBSTR(OFFICE_CODE, 0, 2),'0000') = OFFICE_CODE ");
        sql.append(" ) ED_SECTOR ON ED_SECTOR.OFFICE_CODE = CONCAT (SUBSTR(R4000.OFFICE_CODE, 0, 2),'0000') ");
        sql.append(" INNER JOIN ( ");
        sql.append("   SELECT OFFICE_CODE,DEPT_NAME,DEPT_SHORT_NAME ");
        sql.append("   FROM EXCISE_DEPARTMENT ");
        sql.append("   WHERE IS_DELETED = 'N' ");
        sql.append("     AND CONCAT (SUBSTR(OFFICE_CODE, 0, 4),'00') = OFFICE_CODE ");
        sql.append(" ) ED_AREA ON ED_AREA.OFFICE_CODE = CONCAT (SUBSTR(R4000.OFFICE_CODE, 0, 4),'00') ");
        sql.append(" WHERE TA_W_HDR.IS_DELETED = 'N' ");
        sql.append("   AND TA_W_HDR.ANALYSIS_NUMBER = ? ");
        // sql.append("   AND TA_W_HDR.OFFICE_CODE = '010000' ");

        List<Object> params = new ArrayList<>();
        params.add(formVo.getAnalysisNumber());

        if (StringUtils.isNotBlank(formVo.getCond())) {
            sql.append("AND TA_W_DTL.COND_MAIN_GRP=? ");
            params.add(formVo.getCond());
        }
        String officeCode = ExciseUtils.whereInLocalOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode());

        sql.append("AND R4000.OFFICE_CODE LIKE ?");
        params.add(officeCode);
        String limit = OracleUtils.limitForDatable(sql.toString(), formVo.getStart(), formVo.getLength());
        return commonJdbcTemplate.query(limit, params.toArray(), taxOperatorrowMapper);
    }

    public List<TaxOperatorDetailVo> getTaxOperatorFromSelect(PlanWorkSheetVo formVo) {

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT R4000.CUS_FULLNAME ");
        sql.append("   ,R4000.FAC_FULLNAME ");
        sql.append("   ,R4000.FAC_ADDRESS ");
        sql.append("   ,R4000.OFFICE_CODE OFFICE_CODE_R4000");
        sql.append("   ,ED_SECTOR.OFFICE_CODE SEC_CODE ");
        sql.append("   ,ED_SECTOR.DEPT_SHORT_NAME SEC_DESC ");
        sql.append("   ,ED_AREA.OFFICE_CODE AREA_CODE ");
        sql.append("   ,ED_AREA.DEPT_SHORT_NAME AREA_DESC ");
        sql.append("   ,TA_W_DTL.COND_MAIN_GRP ");
        sql.append("   , TA_W_HDR.ANALYSIS_NUMBER");
        sql.append("   ,TA_DW_DTL.* ");
        sql.append(" FROM TA_WORKSHEET_DTL TA_W_DTL ");
        sql.append(" INNER JOIN TA_WORKSHEET_HDR TA_W_HDR ON TA_W_DTL.ANALYSIS_NUMBER = TA_W_HDR.ANALYSIS_NUMBER ");
        sql.append("   AND TA_W_DTL.IS_DELETED = 'N' ");
        sql.append(" INNER JOIN TA_DRAFT_WORKSHEET_DTL TA_DW_DTL ON TA_DW_DTL.DRAFT_NUMBER = TA_W_HDR.DRAFT_NUMBER ");
        sql.append("   AND TA_DW_DTL.NEW_REG_ID = TA_W_DTL.NEW_REG_ID ");
        sql.append("   AND TA_DW_DTL.IS_DELETED = 'N' ");
        sql.append(" INNER JOIN TA_WS_REG4000 R4000 ON R4000.NEW_REG_ID = TA_W_DTL.NEW_REG_ID ");
        sql.append("   AND R4000.IS_DELETED = 'N' ");
        sql.append(" INNER JOIN ( ");
        sql.append("   SELECT OFFICE_CODE,DEPT_NAME,DEPT_SHORT_NAME ");
        sql.append("   FROM EXCISE_DEPARTMENT ");
        sql.append("   WHERE IS_DELETED = 'N' ");
        sql.append("     AND CONCAT (SUBSTR(OFFICE_CODE, 0, 2),'0000') = OFFICE_CODE ");
        sql.append(" ) ED_SECTOR ON ED_SECTOR.OFFICE_CODE = CONCAT (SUBSTR(R4000.OFFICE_CODE, 0, 2),'0000') ");
        sql.append(" INNER JOIN ( ");
        sql.append("   SELECT OFFICE_CODE,DEPT_NAME,DEPT_SHORT_NAME ");
        sql.append("   FROM EXCISE_DEPARTMENT ");
        sql.append("   WHERE IS_DELETED = 'N' ");
        sql.append("     AND CONCAT (SUBSTR(OFFICE_CODE, 0, 4),'00') = OFFICE_CODE ");
        sql.append(" ) ED_AREA ON ED_AREA.OFFICE_CODE = CONCAT (SUBSTR(R4000.OFFICE_CODE, 0, 4),'00') ");
        sql.append(" WHERE TA_W_HDR.IS_DELETED = 'N' ");
        sql.append("   AND TA_W_HDR.ANALYSIS_NUMBER = ? ");
        // sql.append("   AND TA_W_HDR.OFFICE_CODE = '010000' ");

        List<Object> params = new ArrayList<>();
        params.add(formVo.getAnalysisNumber());

        if (formVo.getTypeCheckedAll()) {
            if (formVo.getIds().size() != 0) {
                sql.append(" AND TA_DW_DTL.NEW_REG_ID NOT IN (");
                for (int i = 0; i < formVo.getIds().size(); i++) {
                    sql.append("?");
                    params.add(formVo.getIds().get(i));
                    if (i == formVo.getIds().size() - 1) {
                        sql.append(")");
                    } else {
                        sql.append(",");
                    }
                }
            }
        } else {
            sql.append(" AND TA_DW_DTL.NEW_REG_ID IN (");
            for (int i = 0; i < formVo.getIds().size(); i++) {
                sql.append("?");
                params.add(formVo.getIds().get(i));
                if (i == formVo.getIds().size() - 1) {
                    sql.append(")");
                } else {
                    sql.append(",");
                }
            }
        }
        List<TaxOperatorDetailVo> list = commonJdbcTemplate.query(sql.toString(), params.toArray(), taxOperatorrowMapper);
        return list;
    }

    protected RowMapper<TaxOperatorDetailVo> taxOperatorrowMapper = new RowMapper<TaxOperatorDetailVo>() {
        @Override
        public TaxOperatorDetailVo mapRow(ResultSet rs, int arg1) throws SQLException {
            TaxOperatorDetailVo vo = new TaxOperatorDetailVo();

            vo.setCusFullname(rs.getString("CUS_FULLNAME"));
            vo.setFacFullname(rs.getString("FAC_FULLNAME"));
            vo.setFacAddress(rs.getString("FAC_ADDRESS"));
            vo.setOfficeCode(rs.getString("OFFICE_CODE_R4000"));
            vo.setSecCode(rs.getString("SEC_CODE"));
            vo.setSecDesc(rs.getString("SEC_DESC"));
            vo.setAreaCode(rs.getString("AREA_CODE"));
            vo.setAreaDesc(rs.getString("AREA_DESC"));
            // vo.setWorksheetHdrId(rs.getString("WORKSHEET_HDR_ID"));
            vo.setDraftNumber(rs.getString("DRAFT_NUMBER"));
            vo.setAnalysisNumber(rs.getString("ANALYSIS_NUMBER"));
            vo.setNewRegId(rs.getString("NEW_REG_ID"));
            vo.setSumTaxAmtG1(rs.getString("SUM_TAX_AMT_G1"));
            vo.setSumTaxAmtG2(rs.getString("SUM_TAX_AMT_G2"));
            vo.setTaxAmtChnPnt(rs.getString("TAX_AMT_CHN_PNT"));
            vo.setTaxMonthNo(rs.getString("TAX_MONTH_NO"));
            vo.setTaxAmtG1M1(rs.getString("TAX_AMT_G1_M1"));
            vo.setTaxAmtG1M2(rs.getString("TAX_AMT_G1_M2"));
            vo.setTaxAmtG1M3(rs.getString("TAX_AMT_G1_M3"));
            vo.setTaxAmtG1M4(rs.getString("TAX_AMT_G1_M4"));
            vo.setTaxAmtG1M5(rs.getString("TAX_AMT_G1_M5"));
            vo.setTaxAmtG1M6(rs.getString("TAX_AMT_G1_M6"));
            vo.setTaxAmtG1M7(rs.getString("TAX_AMT_G1_M7"));
            vo.setTaxAmtG1M8(rs.getString("TAX_AMT_G1_M8"));
            vo.setTaxAmtG1M9(rs.getString("TAX_AMT_G1_M9"));
            vo.setTaxAmtG1M10(rs.getString("TAX_AMT_G1_M10"));
            vo.setTaxAmtG1M11(rs.getString("TAX_AMT_G1_M11"));
            vo.setTaxAmtG1M12(rs.getString("TAX_AMT_G1_M12"));
            vo.setTaxAmtG2M1(rs.getString("TAX_AMT_G2_M1"));
            vo.setTaxAmtG2M2(rs.getString("TAX_AMT_G2_M2"));
            vo.setTaxAmtG2M3(rs.getString("TAX_AMT_G2_M3"));
            vo.setTaxAmtG2M4(rs.getString("TAX_AMT_G2_M4"));
            vo.setTaxAmtG2M5(rs.getString("TAX_AMT_G2_M5"));
            vo.setTaxAmtG2M6(rs.getString("TAX_AMT_G2_M6"));
            vo.setTaxAmtG2M7(rs.getString("TAX_AMT_G2_M7"));
            vo.setTaxAmtG2M8(rs.getString("TAX_AMT_G2_M8"));
            vo.setTaxAmtG2M9(rs.getString("TAX_AMT_G2_M9"));
            vo.setTaxAmtG2M10(rs.getString("TAX_AMT_G2_M10"));
            vo.setTaxAmtG2M11(rs.getString("TAX_AMT_G2_M11"));
            vo.setTaxAmtG2M12(rs.getString("TAX_AMT_G2_M12"));
            vo.setCondTaxGrp(rs.getString("COND_MAIN_GRP"));
            return vo;
        }
    };


    public Long countTaxOperatorDraft(TaxOperatorFormVo formVo) {

        List<Object> params = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT R4000.CUS_FULLNAME,");
        sql.append("   R4000.FAC_FULLNAME,");
        sql.append("   R4000.FAC_ADDRESS,");
        sql.append("   R4000.OFFICE_CODE,");
        sql.append("   ED_SECTOR.OFFICE_CODE SEC_CODE,");
        sql.append("   ED_SECTOR.DEPT_SHORT_NAME SEC_DESC,");
        sql.append("   ED_AREA.OFFICE_CODE AREA_CODE,");
        sql.append("   ED_AREA.DEPT_SHORT_NAME AREA_DESC,");
        sql.append("   TA_W_HDR.*");
        sql.append(" FROM TA_DRAFT_WORKSHEET_DTL TA_W_HDR");
        sql.append(" INNER JOIN TA_WS_REG4000 R4000");
        sql.append(" ON R4000.NEW_REG_ID = TA_W_HDR.NEW_REG_ID");
        sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_SECTOR");
        sql.append(" ON ED_SECTOR.OFFICE_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 2),'0000')");
        sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_AREA");
        sql.append(" ON ED_AREA.OFFICE_CODE    = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 4),'00')");
        sql.append(" WHERE TA_W_HDR.IS_DELETED = 'N'");
        sql.append(" AND R4000.IS_DELETED      = 'N'");
        sql.append(" AND TA_W_HDR.DRAFT_NUMBER = ?");

        params.add(formVo.getDraftNumber());

        if (StringUtils.isNotBlank(formVo.getOfficeCode())) {
            sql.append(" AND  R4000.OFFICE_CODE like ?");
            params.add(formVo.getOfficeCode());
        }
        if (StringUtils.isNotBlank(formVo.getFacType())) {
            sql.append(" AND R4000.FAC_TYPE=?");
            params.add(formVo.getFacType());
        }
        if (StringUtils.isNotBlank(formVo.getDutyCode())) {
            sql.append(" AND R4000.DUTY_CODE=?");
            params.add(formVo.getDutyCode());
        }

        String sqlCount = OracleUtils.countForDataTable(sql.toString());
        return commonJdbcTemplate.queryForObject(sqlCount, params.toArray(), Long.class);
    }

    public List<TaxOperatorDetailVo> getTaxOperatorDraft(TaxOperatorFormVo formVo) {

        List<Object> params = new ArrayList<>();

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT R4000.CUS_FULLNAME,");
        sql.append("   R4000.FAC_FULLNAME,");
        sql.append("   R4000.FAC_ADDRESS,");
        sql.append("   R4000.OFFICE_CODE OFFICE_CODE_R4000,");
        sql.append("   ED_SECTOR.OFFICE_CODE SEC_CODE,");
        sql.append("   ED_SECTOR.DEPT_SHORT_NAME SEC_DESC,");
        sql.append("   ED_AREA.OFFICE_CODE AREA_CODE,");
        sql.append("   ED_AREA.DEPT_SHORT_NAME AREA_DESC,");
        sql.append("   TA_W_HDR.*");
        sql.append(" FROM TA_DRAFT_WORKSHEET_DTL TA_W_HDR");
        sql.append(" INNER JOIN TA_WS_REG4000 R4000");
        sql.append(" ON R4000.NEW_REG_ID = TA_W_HDR.NEW_REG_ID");
        sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_SECTOR");
        sql.append(" ON ED_SECTOR.OFFICE_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 2),'0000')");
        sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_AREA");
        sql.append(" ON ED_AREA.OFFICE_CODE    = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 4),'00')");
        sql.append(" WHERE TA_W_HDR.IS_DELETED = 'N'");
        sql.append(" AND R4000.IS_DELETED      = 'N'");
        sql.append(" AND TA_W_HDR.DRAFT_NUMBER = ?");

        params.add(formVo.getDraftNumber());

        if (StringUtils.isNotBlank(formVo.getOfficeCode())) {
            sql.append(" AND  R4000.OFFICE_CODE like ?");
            params.add(formVo.getOfficeCode());
        }
        if (StringUtils.isNotBlank(formVo.getFacType())) {
            sql.append(" AND R4000.FAC_TYPE=?");
            params.add(formVo.getFacType());
        }
        if (StringUtils.isNotBlank(formVo.getDutyCode())) {
            sql.append(" AND R4000.DUTY_CODE=?");
            params.add(formVo.getDutyCode());
        }

        String limit = OracleUtils.limitForDatable(sql.toString(), formVo.getStart(), formVo.getLength());
        return commonJdbcTemplate.query(limit, params.toArray(), taxOperatorDraftrowMapper);
    }

    protected RowMapper<TaxOperatorDetailVo> taxOperatorDraftrowMapper = new RowMapper<TaxOperatorDetailVo>() {
        // private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

        @Override
        public TaxOperatorDetailVo mapRow(ResultSet rs, int arg1) throws SQLException {
            TaxOperatorDetailVo vo = new TaxOperatorDetailVo();

            vo.setNewRegId(rs.getString("NEW_REG_ID"));
            vo.setCusFullname(rs.getString("CUS_FULLNAME"));
            vo.setFacFullname(rs.getString("FAC_FULLNAME"));
            vo.setFacAddress(rs.getString("FAC_ADDRESS"));
            vo.setOfficeCode(rs.getString("OFFICE_CODE_R4000"));
            vo.setSecCode(rs.getString("SEC_CODE"));
            vo.setSecDesc(rs.getString("SEC_DESC"));
            vo.setAreaCode(rs.getString("AREA_CODE"));
            vo.setAreaDesc(rs.getString("AREA_DESC"));
            vo.setWorksheetHdrId(rs.getString("DRAFT_WORKSHEET_DTL_ID"));
            vo.setDraftNumber(rs.getString("DRAFT_NUMBER"));
            vo.setNewRegId(rs.getString("NEW_REG_ID"));
            vo.setSumTaxAmtG1(rs.getString("SUM_TAX_AMT_G1"));
            vo.setSumTaxAmtG2(rs.getString("SUM_TAX_AMT_G2"));
            vo.setTaxAmtChnPnt(rs.getString("TAX_AMT_CHN_PNT"));
            vo.setTaxMonthNo(rs.getString("TAX_MONTH_NO"));
            vo.setTaxAmtG1M1(rs.getString("TAX_AMT_G1_M1"));
            vo.setTaxAmtG1M2(rs.getString("TAX_AMT_G1_M2"));
            vo.setTaxAmtG1M3(rs.getString("TAX_AMT_G1_M3"));
            vo.setTaxAmtG1M4(rs.getString("TAX_AMT_G1_M4"));
            vo.setTaxAmtG1M5(rs.getString("TAX_AMT_G1_M5"));
            vo.setTaxAmtG1M6(rs.getString("TAX_AMT_G1_M6"));
            vo.setTaxAmtG1M7(rs.getString("TAX_AMT_G1_M7"));
            vo.setTaxAmtG1M8(rs.getString("TAX_AMT_G1_M8"));
            vo.setTaxAmtG1M9(rs.getString("TAX_AMT_G1_M9"));
            vo.setTaxAmtG1M10(rs.getString("TAX_AMT_G1_M10"));
            vo.setTaxAmtG1M11(rs.getString("TAX_AMT_G1_M11"));
            vo.setTaxAmtG1M12(rs.getString("TAX_AMT_G1_M12"));
            vo.setTaxAmtG2M1(rs.getString("TAX_AMT_G2_M1"));
            vo.setTaxAmtG2M2(rs.getString("TAX_AMT_G2_M2"));
            vo.setTaxAmtG2M3(rs.getString("TAX_AMT_G2_M3"));
            vo.setTaxAmtG2M4(rs.getString("TAX_AMT_G2_M4"));
            vo.setTaxAmtG2M5(rs.getString("TAX_AMT_G2_M5"));
            vo.setTaxAmtG2M6(rs.getString("TAX_AMT_G2_M6"));
            vo.setTaxAmtG2M7(rs.getString("TAX_AMT_G2_M7"));
            vo.setTaxAmtG2M8(rs.getString("TAX_AMT_G2_M8"));
            vo.setTaxAmtG2M9(rs.getString("TAX_AMT_G2_M9"));
            vo.setTaxAmtG2M10(rs.getString("TAX_AMT_G2_M10"));
            vo.setTaxAmtG2M11(rs.getString("TAX_AMT_G2_M11"));
            vo.setTaxAmtG2M12(rs.getString("TAX_AMT_G2_M12"));
            return vo;
        }
    };
}