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
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD1;

public class IaAuditIncD1RepositoryImpl implements IaAuditIncD1RepositoryCuston {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<IaAuditIncD1> iaAuditIncD1List) {
		String sql = SqlGeneratorUtils.genSqlInsert("IA_AUDIT_INC_D1", Arrays.asList("IA_AUDIT_INC_D_ID", "AUDIT_INC_NO", "OFFICE_CODE", "DOC_CTL_NO", "RECEIPT_NO", "RECEIPT_DATE", "TAX_NAME", "TAX_CODE", "AMOUNT", "REMARK", "CREATED_BY"), "IA_AUDIT_INC_D1_SEQ");

		String username = UserLoginUtils.getCurrentUsername();

		commonJdbcTemplate.batchUpdate(sql, iaAuditIncD1List, 1000, new ParameterizedPreparedStatementSetter<IaAuditIncD1>() {
			public void setValues(PreparedStatement ps, IaAuditIncD1 iaAuditInc) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(iaAuditInc.getAuditIncNo());
				paramList.add(iaAuditInc.getOfficeCode());
				paramList.add(iaAuditInc.getDocCtlNo());
				paramList.add(iaAuditInc.getReceiptNo());
				paramList.add(iaAuditInc.getReceiptDate());
				paramList.add(iaAuditInc.getTaxName());
				paramList.add(iaAuditInc.getTaxCode());
				paramList.add(iaAuditInc.getAmount());
				paramList.add(iaAuditInc.getRemark());
				paramList.add(iaAuditInc.getCreatedBy());
				paramList.add(username);
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
