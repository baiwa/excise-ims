package th.go.excise.ims.ia.persistence.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD2;
import th.go.excise.ims.ia.vo.IaAuditIncD2Vo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IaAuditIncD2RepositoryImpl implements IaAuditIncD2RepositoryCustom{

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<IaAuditIncD2Vo> iaAuditIncD2List) {
		String sql = SqlGeneratorUtils.genSqlInsert("IA_AUDIT_INC_D2", Arrays.asList("IA_AUDIT_INC_D2_ID"
				,"RECEIPT_DATE"
				,"AMOUNT"
				,"PRINT_PER_DAY"
				,"AUDIT_CHECK"
				,"REMARK","CREATED_BY"), "IA_AUDIT_INC_D2_SEQ");

		String username = UserLoginUtils.getCurrentUsername();

		commonJdbcTemplate.batchUpdate(sql, iaAuditIncD2List, 1000, new ParameterizedPreparedStatementSetter<IaAuditIncD2Vo>() {
			public void setValues(PreparedStatement ps, IaAuditIncD2Vo iaAuditInc) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(iaAuditInc.getReceiptDate());
				paramList.add(iaAuditInc.getAmount());
				paramList.add(iaAuditInc.getPrintPerDay());
				paramList.add(iaAuditInc.getAuditCheck());
				paramList.add(iaAuditInc.getRemark());
				paramList.add(username);
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
