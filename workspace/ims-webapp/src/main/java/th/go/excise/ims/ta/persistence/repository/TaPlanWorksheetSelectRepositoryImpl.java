package th.go.excise.ims.ta.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;

public class TaPlanWorksheetSelectRepositoryImpl implements TaPlanWorksheetSelectRepositoryCustom {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public void batchInsert(String budgetYear, List<String> newRegIdList) {
		String sql = SqlGeneratorUtils.genSqlInsert(
			"TA_PLAN_WORKSHEET_SELECT",
			Arrays.asList(
				"PLAN_WORKSHEET_SELECT_ID", "BUDGET_YEAR", "NEW_REG_ID", "CREATED_BY", "CREATED_DATE"
			),
			"TA_PLAN_WORKSHEET_SELECT_SEQ"
		);
		
		String username = UserLoginUtils.getCurrentUsername();
		LocalDate createdDate = LocalDate.now();

		commonJdbcTemplate.batchUpdate(sql, newRegIdList, 1000, new ParameterizedPreparedStatementSetter<String>() {
			public void setValues(PreparedStatement ps, String newRegId) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(budgetYear);
				paramList.add(newRegId);
				paramList.add(username);
				paramList.add(createdDate);
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
