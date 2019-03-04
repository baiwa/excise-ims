package th.go.excise.ims.ta.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetDtl;
import th.go.excise.ims.ta.util.TaxAuditUtils;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

public class TaWorksheetDtlRepositoryImpl implements TaWorksheetDtlRepositoryCustom {

    @Autowired
    private CommonJdbcTemplate commonJdbcTemplate;

    @Override
    public void batchInsert(List<TaWorksheetDtl> worksheetDtlList) {
        String sql = SqlGeneratorUtils.genSqlInsert(
                "TA_WORKSHEET_DTL",
                Arrays.asList(
                        "WORKSHEET_DTL_ID", "ANALYSIS_NUMBER", "NEW_REG_ID", "COND_MAIN_GRP",
                        "CREATED_BY", "CREATED_DATE"
                ),
                "TA_WORKSHEET_DTL_SEQ"
        );

        commonJdbcTemplate.batchUpdate(sql, worksheetDtlList, 1000, new ParameterizedPreparedStatementSetter<TaWorksheetDtl>() {
            public void setValues(PreparedStatement ps, TaWorksheetDtl worksheetDtl) throws SQLException {
                List<Object> paramList = new ArrayList<Object>();
                paramList.add(worksheetDtl.getAnalysisNumber());
                paramList.add(worksheetDtl.getNewRegId());
                paramList.add(worksheetDtl.getCondMainGrp());
                paramList.add(worksheetDtl.getCreatedBy());
                paramList.add(worksheetDtl.getCreatedDate());
                commonJdbcTemplate.preparePs(ps, paramList.toArray());
            }
        });
    }

    private void buildByCriteriaQuery(StringBuilder sql, List<Object> params, TaxOperatorFormVo formVo) {
        sql.append(" SELECT R4000.CUS_FULLNAME , ");
        sql.append("   R4000.FAC_FULLNAME , ");
        sql.append("   R4000.FAC_ADDRESS , ");
        sql.append("   R4000.DUTY_CODE , ");
        sql.append("   R4000.OFFICE_CODE OFFICE_CODE_R4000 , ");
        sql.append("   ED_SECTOR.OFFICE_CODE SEC_CODE , ");
        sql.append("   ED_SECTOR.OFF_SHORT_NAME SEC_DESC , ");
        sql.append("   ED_AREA.OFFICE_CODE AREA_CODE , ");
        sql.append("   ED_AREA.OFF_SHORT_NAME AREA_DESC , ");
        sql.append("   TA_W_DTL.COND_MAIN_GRP , ");
        sql.append("   TA_W_HDR.ANALYSIS_NUMBER , ");
        sql.append("   TA_DW_DTL.* ");
        sql.append(" FROM TA_WORKSHEET_DTL TA_W_DTL ");
        sql.append(" INNER JOIN TA_WORKSHEET_HDR TA_W_HDR ");
        sql.append(" ON TA_W_DTL.ANALYSIS_NUMBER = TA_W_HDR.ANALYSIS_NUMBER ");
        sql.append(" AND TA_W_DTL.IS_DELETED     = 'N' ");
        sql.append(" INNER JOIN TA_DRAFT_WORKSHEET_DTL TA_DW_DTL ");
        sql.append(" ON TA_DW_DTL.DRAFT_NUMBER = TA_W_HDR.DRAFT_NUMBER ");
        sql.append(" AND TA_DW_DTL.NEW_REG_ID  = TA_W_DTL.NEW_REG_ID ");
        sql.append(" AND TA_DW_DTL.IS_DELETED  = 'N' ");
        sql.append(" INNER JOIN TA_WS_REG4000 R4000 ");
        sql.append(" ON R4000.NEW_REG_ID  = TA_W_DTL.NEW_REG_ID ");
        sql.append(" AND R4000.IS_DELETED = 'N' ");
        sql.append(" INNER JOIN ");
        sql.append("   (SELECT OFF_CODE OFFICE_CODE, ");
        sql.append("     OFF_NAME, ");
        sql.append("     OFF_SHORT_NAME ");
        sql.append("   FROM EXCISE_DEPARTMENT ");
        sql.append("   WHERE IS_DELETED                              = 'N' ");
        sql.append("   AND CONCAT (SUBSTR(OFF_CODE, 0, 2),'0000') = OFF_CODE ");
        sql.append("   ) ED_SECTOR ");
        sql.append(" ON ED_SECTOR.OFFICE_CODE = CONCAT (SUBSTR(R4000.OFFICE_CODE, 0, 2),'0000') ");
        sql.append(" INNER JOIN ");
        sql.append("   (SELECT OFF_CODE OFFICE_CODE, ");
        sql.append("     OFF_NAME, ");
        sql.append("     OFF_SHORT_NAME ");
        sql.append("   FROM EXCISE_DEPARTMENT ");
        sql.append("   WHERE IS_DELETED                            = 'N' ");
        sql.append("   AND CONCAT (SUBSTR(OFF_CODE, 0, 4),'00') = OFF_CODE ");
        sql.append("   ) ED_AREA ");
        sql.append(" ON ED_AREA.OFFICE_CODE       = CONCAT (SUBSTR(R4000.OFFICE_CODE, 0, 4),'00') ");
        sql.append(" WHERE TA_W_HDR.IS_DELETED    = 'N' ");
        sql.append(" AND TA_W_HDR.ANALYSIS_NUMBER = ? ");
        ;

        params.add(formVo.getAnalysisNumber());

        if (StringUtils.isNotBlank(formVo.getCond())) {
            sql.append(" AND TA_W_DTL.COND_MAIN_GRP = ? ");
            params.add(formVo.getCond());
        }

        sql.append(" AND R4000.OFFICE_CODE LIKE ? ");
        params.add(ExciseUtils.whereInLocalOfficeCode(UserLoginUtils.getCurrentUserBean().getOfficeCode()));
    }

    public List<TaxOperatorDetailVo> findByCriteria(TaxOperatorFormVo formVo) {
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<>();
        buildByCriteriaQuery(sql, params, formVo);
        
        sql.append("ORDER BY R4000.DUTY_CODE , R4000.OFFICE_CODE , TA_DW_DTL.NEW_REG_ID ");

        return commonJdbcTemplate.query(OracleUtils.limitForDatable(sql.toString(), formVo.getStart(), formVo.getLength()), params.toArray(), worksheetRowMapper);
    }

    public Long countByCriteria(TaxOperatorFormVo formVo) {
        StringBuilder sql = new StringBuilder();
        List<Object> params = new ArrayList<>();
        buildByCriteriaQuery(sql, params, formVo);

        return commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()), params.toArray(), Long.class);
    }

    private static final RowMapper<TaxOperatorDetailVo> worksheetRowMapper = new RowMapper<TaxOperatorDetailVo>() {
        @Override
        public TaxOperatorDetailVo mapRow(ResultSet rs, int rowNum) throws SQLException {
            TaxOperatorDetailVo vo = new TaxOperatorDetailVo();
            TaxAuditUtils.commonSelectionWorksheetRowMapper(vo, rs);
            vo.setDraftNumber(rs.getString("ANALYSIS_NUMBER"));
            vo.setCondTaxGrp(rs.getString("COND_MAIN_GRP"));
            return vo;
        }
    };

}
