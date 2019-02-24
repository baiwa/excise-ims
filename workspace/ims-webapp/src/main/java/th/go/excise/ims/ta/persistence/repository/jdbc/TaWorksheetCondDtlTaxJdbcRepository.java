package th.go.excise.ims.ta.persistence.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ta.vo.CondGroupVo;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TaWorksheetCondDtlTaxJdbcRepository {

    @Autowired
    private CommonJdbcTemplate commonJdbcTemplate;

    public List<CondGroupVo> findCondGroupDtl(String analysisNumber) {

        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT  ");
        sql.append(" ANALYSIS_NUMBER, ");
        sql.append(" COND_GROUP, ");
        sql.append(" TAX_MONTH_START, ");
        sql.append(" TAX_MONTH_END, ");
        sql.append(" RANGE_START, ");
        sql.append(" RANGE_END ");
        sql.append(" FROM TA_WORKSHEET_COND_MAIN_DTL  ");
        sql.append(" WHERE IS_DELETED='N' ");
        sql.append(" AND ANALYSIS_NUMBER=? ORDER BY COND_GROUP ASC");

        List<Object> params = new ArrayList<>();
        params.add(analysisNumber);
        return commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper<CondGroupVo>(CondGroupVo.class));
    }
}
