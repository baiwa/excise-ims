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
import th.go.excise.ims.ia.persistence.entity.IaGfmovementAccount;

public class IaGfmovementAccountRepositoryImpl implements IaGfmovementAccountRepositoryCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<IaGfmovementAccount> iaGfmovementAccounList) {

		String sql = SqlGeneratorUtils.genSqlInsert("IA_GFMOVEMENT_ACCOUNT",
				Arrays.asList("GFMOVEMENT_ACCOUNT_ID", "ACC_TYPE_NO", "ACC_TYPE_NAME", "GF_ACC_NO", "GF_DOC_DATE", "GF_DOC_NO", "GF_DOC_TYEP", "GF_REF_DOC", "CARE_INSTEAD", "DETERMINATON", "DEP_CODE", "DEBIT", "CREDIT", "CARRY_FORWARD", "CREATED_BY"), "IA_GFMOVEMENT_ACCOUNT_SEQ");

		String username = UserLoginUtils.getCurrentUsername();

		commonJdbcTemplate.batchUpdate(sql, iaGfmovementAccounList, 1000, new ParameterizedPreparedStatementSetter<IaGfmovementAccount>() {
			public void setValues(PreparedStatement ps, IaGfmovementAccount entity) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(entity.getAccTypeNo());
				paramList.add(entity.getAccTypeName());
				paramList.add(entity.getAccNo());
				paramList.add(entity.getGfAccNo());
				paramList.add(entity.getGfDocDate());
				paramList.add(entity.getGfDocNo());
				paramList.add(entity.getGfDocTyep());
				paramList.add(entity.getGfRefDoc());
				paramList.add(entity.getCareInstead());
				paramList.add(entity.getDeterminaton());
				paramList.add(entity.getDeptDisb());
				paramList.add(entity.getDebit());
				paramList.add(entity.getCredit());
				paramList.add(entity.getCarryForward());
				paramList.add(username);
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});

	}

}
