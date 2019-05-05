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
import th.go.excise.ims.ia.persistence.entity.IaGfledgerAccount;

public class IaGfledgerAccountRepositoryImpl implements IaGfledgerAccountRepositoryCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void insertBatch(List<IaGfledgerAccount> iaGfledgerAccountList) {

		String sql = SqlGeneratorUtils.genSqlInsert("IA_GFLEDGER_ACCOUNT", Arrays.asList("IA_GFLEDGER_ACCOUNT_SEQ", "ST_CODE", "DETERMINATON", "DOC_NO", "CODE", "TYPE", "DOC_DATE", "PK_CODE", "CURR_AMT", "SOURCE_MONEY", "KEY_REF_3", "DEP_CODE", "POSTING_DATE", "YEAR_MONTH", "TAX_AMT",
				"TAX_EXRMPT_AMT", "REF_CODE", "GL_ACC", "FORWARD_CLEARING_LIST", "CLG_I", "BUDGET_CODE", "KEY_REF_1", "KEY_REF_2", "DEPOSIT_ACC", "SUB_ACC", "DEPOSIT_NAME", "ACC_OWN", "DOC_HEADER_MSG", "TX_CODE", "CLRNG_DOC", "CREATED_BY"), "IA_GFLEDGER_ACCOUNT_SEQ");

		String username = UserLoginUtils.getCurrentUsername();

		commonJdbcTemplate.batchUpdate(sql, iaGfledgerAccountList, 1000, new ParameterizedPreparedStatementSetter<IaGfledgerAccount>() {
			public void setValues(PreparedStatement ps, IaGfledgerAccount entity) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(entity.getStCode());
				paramList.add(entity.getDeterminaton());
				paramList.add(entity.getDocNo());
				paramList.add(entity.getCode());
				paramList.add(entity.getType());
				paramList.add(entity.getDocDate());
				paramList.add(entity.getPkCode());
				paramList.add(entity.getCurrAmt());
				paramList.add(entity.getSourceMoney());
				paramList.add(entity.getKeyRef3());
				paramList.add(entity.getDepCode());
				paramList.add(entity.getPostingDate());
				paramList.add(entity.getYearMonth());
				paramList.add(entity.getTaxAmt());
				paramList.add(entity.getTaxExrmptAmt());
				paramList.add(entity.getRefCode());
				paramList.add(entity.getGlAcc());
				paramList.add(entity.getForwardClearingList());
				paramList.add(entity.getClgI());
				paramList.add(entity.getBudgetCode());
				paramList.add(entity.getKeyRef1());
				paramList.add(entity.getKeyRef2());
				paramList.add(entity.getDepositAcc());
				paramList.add(entity.getSubAcc());
				paramList.add(entity.getDepositName());
				paramList.add(entity.getAccOwn());
				paramList.add(entity.getDocHeaderMsg());
				paramList.add(entity.getTxCode());
				paramList.add(entity.getClrngDoc());
				paramList.add(username);
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});

	}

}
