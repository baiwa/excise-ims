package th.go.excise.ims.ta.persistence.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ta.vo.YearMonthVo;

@Repository
public class TaWorksheetCondHdr {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@SuppressWarnings("unchecked")
	public YearMonthVo monthStart(String analysisNumber) {
		String sql = "SELECT YEAR_MONTH_START, YEAR_MONTH_END, MONTH_NUM MONTH_TOTAL, TO_NUMBER(SUBSTR(YEAR_MONTH_START,5,5)) MONTH_START  FROM TA_WORKSHEET_COND_HDR WHERE ANALYSIS_NUMBER = ? ";

		@SuppressWarnings("rawtypes")
		YearMonthVo obj = (YearMonthVo) this.commonJdbcTemplate.queryForObject(sql, new Object[] { analysisNumber }, new BeanPropertyRowMapper(YearMonthVo.class));
		return obj;
	}
}
