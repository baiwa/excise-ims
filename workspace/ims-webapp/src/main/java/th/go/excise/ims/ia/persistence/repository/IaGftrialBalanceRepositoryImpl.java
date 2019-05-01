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
import th.go.excise.ims.ia.persistence.entity.IaGftrialBalance;

public class IaGftrialBalanceRepositoryImpl implements IaGftrialBalanceRepositorCustom {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<IaGftrialBalance> iaGftrialBalances) {

		String sql = SqlGeneratorUtils.genSqlInsert("IA_GFTRIAL_BALANCE", Arrays.asList("IA_GFTRIAL_BALANCE_ID", "DEPARTMENT_CODE", "PERIOD_FROM", "PERIOD_TO", "PERIOD_YEAR", "ACC_NO", "ACC_NAME", "CARRY_FORWARD", "BRING_FORWARD", "DEBIT", "CREDIT", "CREATED_BY"), "IA_GFTRIAL_BALANCE_SEQ");

		String username = UserLoginUtils.getCurrentUsername();

		commonJdbcTemplate.batchUpdate(sql, iaGftrialBalances, 1000, new ParameterizedPreparedStatementSetter<IaGftrialBalance>() {
			public void setValues(PreparedStatement ps, IaGftrialBalance entity) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(entity.getDepartmentCode());
				paramList.add(entity.getPeriodFrom());
				paramList.add(entity.getPeriodTo());
				paramList.add(entity.getPeriodYear());
				paramList.add(entity.getAccNo());
				paramList.add(entity.getAccName());
				paramList.add(entity.getCarryForward());
				paramList.add(entity.getBringForward());
				paramList.add(entity.getDebit());
				paramList.add(entity.getCredit());
				paramList.add(username);
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});

	}

}
