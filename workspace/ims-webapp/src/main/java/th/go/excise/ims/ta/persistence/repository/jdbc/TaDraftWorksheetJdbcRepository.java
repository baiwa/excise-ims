package th.go.excise.ims.ta.persistence.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ta.vo.YearMonthVo;

@Repository
public class TaDraftWorksheetJdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<String> analysisNumberDraft() {

		String sql = "SELECT DRAFT_NUMBER  FROM TA_DRAFT_WORKSHEET_HDR ORDER BY DRAFT_NUMBER DESC";
		List<String> analysisNumber = this.commonJdbcTemplate.queryForList(sql, String.class);
		return analysisNumber;
	}
	
	 public YearMonthVo monthStartDraft(String analysisNumber) {
	        StringBuilder sql = new StringBuilder();
	        sql.append(" SELECT  YEAR_MONTH_START, YEAR_MONTH_END, MONTH_NUM MONTH_TOTAL, TO_NUMBER(SUBSTR(YEAR_MONTH_START,5,5)) MONTH_START  ");
	        sql.append(" FROM TA_DRAFT_WORKSHEET_HDR ");
	        sql.append(" WHERE DRAFT_NUMBER =  ? ");

	        YearMonthVo obj = (YearMonthVo) this.commonJdbcTemplate.queryForObject(sql.toString(), new Object[]{analysisNumber}, new BeanPropertyRowMapper<YearMonthVo>(YearMonthVo.class));
	        return obj;
	    }
}
