package th.go.excise.ims.ta.persistence.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ta.vo.YearMonthVo;

import java.util.List;

@Repository
public class TaWorksheetCondHdrJdbcRepository {
    @Autowired
    private CommonJdbcTemplate commonJdbcTemplate;

    public List<String> findAllAnalysisNumber() {
        String sql = "SELECT ANALYSIS_NUMBER FROM TA_WORKSHEET_COND_HDR ORDER BY ANALYSIS_NUMBER DESC";
        List<String> listAnalysisNumber = this.commonJdbcTemplate.queryForList(sql.toString(), String.class);
        return listAnalysisNumber;
    }

    public YearMonthVo monthStart(String analysisNumber) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT  YEAR_MONTH_START, YEAR_MONTH_END, MONTH_NUM MONTH_TOTAL, TO_NUMBER(SUBSTR(YEAR_MONTH_START,5,5)) MONTH_START  ");
        sql.append(" FROM TA_WORKSHEET_COND_HDR ");
        sql.append(" WHERE ANALYSIS_NUMBER =  ? ");

        YearMonthVo obj = (YearMonthVo) this.commonJdbcTemplate.queryForObject(sql.toString(), new Object[]{analysisNumber}, new BeanPropertyRowMapper<YearMonthVo>(YearMonthVo.class));
        return obj;
    }
}
