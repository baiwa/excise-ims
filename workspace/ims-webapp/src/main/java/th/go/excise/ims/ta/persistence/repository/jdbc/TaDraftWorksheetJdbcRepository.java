package th.go.excise.ims.ta.persistence.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;

@Repository
public class TaDraftWorksheetJdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<String> analysisNumberDraft() {

		String sql = "SELECT DISTINCT DRAFT_NUMBER  FROM TA_DRAFT_WORKSHEET_DTL ORDER BY DRAFT_NUMBER DESC";
		List<String> analysisNumber = this.commonJdbcTemplate.queryForList(sql, String.class);
		return analysisNumber;
	}
}
