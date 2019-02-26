package th.go.excise.ims.ta.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class TaPlanWorksheetDtlRepositoryImpl implements TaPlanWorksheetDtlRepositoryCustom {

    @Autowired
    private CommonJdbcTemplate commonJdbcTemplate;

    @Override
    public void updateIsDelete(String analysisNumber, String planNumber, String officeCode, String newRegId) {
        StringBuilder sql = new StringBuilder();
        sql.append(" UPDATE TA_PLAN_WORKSHEET_DTL ");
        sql.append(" SET IS_DELETED = ? ");
        sql.append(" WHERE PLAN_NUMBER = ? ");
        sql.append(" AND ANALYSIS_NUMBER = ? ");
        sql.append(" AND OFFICE_CODE = ? ");
        sql.append(" AND NEW_REG_ID = ? ");

        List<Object> params = new ArrayList<>();
        params.add("N");
        params.add(planNumber);
        params.add(analysisNumber);
        params.add(officeCode);
        params.add(newRegId);
        commonJdbcTemplate.update(sql.toString(), params.toArray());
    }
}
