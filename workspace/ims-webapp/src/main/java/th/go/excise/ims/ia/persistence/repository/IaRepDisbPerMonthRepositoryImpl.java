package th.go.excise.ims.ia.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ia.persistence.entity.IaRepDisbPerMonth;

public class IaRepDisbPerMonthRepositoryImpl implements IaRepDisbPerMonthRepositoryCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<IaRepDisbPerMonth> iaRepDisbPerMonthList) {

		String sql = SqlGeneratorUtils.genSqlInsert("IA_REP_DISB_PER_MONTH", Arrays.asList("IA_REP_DISB_PER_MONTH_ID", "DEPARTMENT_CODE", "PERIOD_FROM", "PERIOD_TO", "PERIOD_YEAR", "ACC_NO", "ACC_NAME", "CARRY_FORWARD", "BRING_FORWARD", "DEBIT", "CREDIT", "CREATED_BY"), "IA_REP_DISB_PER_MONTH_SEQ");

		String username = UserLoginUtils.getCurrentUsername();

		commonJdbcTemplate.batchUpdate(sql, iaRepDisbPerMonthList, 1000, new ParameterizedPreparedStatementSetter<IaRepDisbPerMonth>() {
			public void setValues(PreparedStatement ps, IaRepDisbPerMonth iaRepDisbPerMonth) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(iaRepDisbPerMonth.getDepartmentCode());
				paramList.add(iaRepDisbPerMonth.getPeriodFrom());
				paramList.add(iaRepDisbPerMonth.getPeriodTo());
				paramList.add(iaRepDisbPerMonth.getPeriodYear());
				paramList.add(iaRepDisbPerMonth.getAccNo());
				paramList.add(iaRepDisbPerMonth.getAccName());
				paramList.add(iaRepDisbPerMonth.getCarryForward());
				paramList.add(iaRepDisbPerMonth.getBringForward());
				paramList.add(iaRepDisbPerMonth.getDebit());
				paramList.add(iaRepDisbPerMonth.getCredit());
				paramList.add(username);
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});

	}

}
