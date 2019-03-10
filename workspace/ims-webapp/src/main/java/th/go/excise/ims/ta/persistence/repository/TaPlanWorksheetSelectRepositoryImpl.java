package th.go.excise.ims.ta.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;

public class TaPlanWorksheetSelectRepositoryImpl implements TaPlanWorksheetSelectRepositoryCustom {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public void batchInsert(String budgetYear, List<String> newRegIdList) {
		String sql = SqlGeneratorUtils.genSqlInsert(
			"TA_PLAN_WORKSHEET_SELECT",
			Arrays.asList(
				"PLAN_WORKSHEET_SELECT_ID", "BUDGET_YEAR", "NEW_REG_ID"
			),
			"TA_PLAN_WORKSHEET_SELECT_SEQ"
		);

		commonJdbcTemplate.batchUpdate(sql, newRegIdList, 1000, new ParameterizedPreparedStatementSetter<String>() {
			public void setValues(PreparedStatement ps, String newRegId) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(budgetYear);
				paramList.add(newRegId);
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
