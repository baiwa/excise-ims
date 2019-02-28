package th.go.excise.ims.ta.persistence.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import org.springframework.util.CollectionUtils;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ta.vo.YearMonthVo;

import java.util.List;

public class TaWorksheetCondMainHdrRepositoryImpl implements TaWorksheetCondMainHdrRepositoryCustom {

    @Autowired
    private CommonJdbcTemplate commonJdbcTemplate;

    @Override
    public YearMonthVo findMonthStartByAnalysisNumber(String analysisNumber) {

        if (StringUtils.isBlank(analysisNumber)) {
            analysisNumber = "";
        }

        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT YEAR_MONTH_START, YEAR_MONTH_END, MONTH_NUM AS MONTH_TOTAL ");
        sql.append("   ,TO_NUMBER(SUBSTR(YEAR_MONTH_START,5,2)) AS MONTH_START ");
        sql.append(" FROM TA_WORKSHEET_COND_MAIN_HDR ");
        sql.append(" WHERE IS_DELETED = ? ");
        sql.append("   AND ANALYSIS_NUMBER = ? ");

        List<YearMonthVo> yearMonthVo = commonJdbcTemplate.query(sql.toString(),
                new Object[]{
                        FLAG.N_FLAG,
                        analysisNumber
                },
                new BeanPropertyRowMapper<>(YearMonthVo.class)
        );
        if (CollectionUtils.isEmpty(yearMonthVo)) {
            return new YearMonthVo();
        }

        return yearMonthVo.get(0);
    }

}
