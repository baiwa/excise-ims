package th.go.excise.ims.ta.persistence.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;

public class TaPlanWorksheetDtlRepositoryImpl implements TaPlanWorksheetDtlRepositoryCustom {

    @Autowired
    private CommonJdbcTemplate commonJdbcTemplate;

    @Override
    public void deletePlanWorkSheet(String analysisNumber, String planNumber, String officeCode, String newRegId ,String createdBy) {
        StringBuilder sql = new StringBuilder();
        sql.append(" DELETE TA_PLAN_WORKSHEET_DTL ");
        sql.append(" WHERE PLAN_NUMBER = ? ");
        sql.append(" AND ANALYSIS_NUMBER = ? ");
        sql.append(" AND OFFICE_CODE = ? ");
        sql.append(" AND NEW_REG_ID = ? ");
        sql.append(" AND CREATED_BY = ? ");

        List<Object> params = new ArrayList<>();
        params.add(planNumber);
        params.add(analysisNumber);
        params.add(officeCode);
        params.add(newRegId);
        params.add(createdBy);
        commonJdbcTemplate.update(sql.toString(), params.toArray());
    }
}
