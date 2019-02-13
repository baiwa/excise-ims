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

	public YearMonthVo monthStart(String analysisNumber) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT  YEAR_MONTH_START, YEAR_MONTH_END, MONTH_NUM MONTH_TOTAL, TO_NUMBER(SUBSTR(YEAR_MONTH_START,5,5)) MONTH_START  ");
		sql.append(" FROM TA_WORKSHEET_COND_HDR ");
		sql.append(" WHERE ANALYSIS_NUMBER =  ");
		sql.append(" (select ANALYSIS_NUMBER from  ");
		sql.append(" (select ANALYSIS_NUMBER from  ");
		sql.append("     TA_WORKSHEET_COND_HDR order by ANALYSIS_NUMBER DESC )  where rownum=1) ");

		YearMonthVo obj = (YearMonthVo) this.commonJdbcTemplate.queryForObject(sql.toString(), new BeanPropertyRowMapper<YearMonthVo>(YearMonthVo.class));
		return obj;
	}
}
