package th.go.excise.ims.ia.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ia.persistence.entity.IaGftrialBalance;
import th.go.excise.ims.ia.vo.Int0802SearchVo;
import th.go.excise.ims.ia.vo.Int0802Vo;

public class IaGftrialBalanceRepositoryImpl implements IaGftrialBalanceRepositorCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<IaGftrialBalance> iaGftrialBalances) {

		String sql = SqlGeneratorUtils.genSqlInsert("IA_GFTRIAL_BALANCE",
				Arrays.asList("IA_GFTRIAL_BALANCE_ID", "DEPT_DISB", "PERIOD_FROM", "PERIOD_TO", "PERIOD_YEAR",
						"ACC_NO", "ACC_NAME", "CARRY_FORWARD", "BRING_FORWARD", "DEBIT", "CREDIT", "CREATED_BY"),
				"IA_GFTRIAL_BALANCE_SEQ");

		String username = UserLoginUtils.getCurrentUsername();

		commonJdbcTemplate.batchUpdate(sql, iaGftrialBalances, 1000,
				new ParameterizedPreparedStatementSetter<IaGftrialBalance>() {
					public void setValues(PreparedStatement ps, IaGftrialBalance entity) throws SQLException {
						List<Object> paramList = new ArrayList<Object>();
						paramList.add(entity.getDeptDisb());
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

	@Override
	public List<IaGftrialBalance> findByGfDisburseUnit(String gfDisburseUnit) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT DISTINCT PERIOD_FROM ");
		sql.append(" FROM IA_GFTRIAL_BALANCE ");
		sql.append(" WHERE IS_DELETED = 'N' ");

		sql.append(" AND DEPT_DISB LIKE ? ");
		params.add("%".concat(gfDisburseUnit.trim()));

		sql.append(" ORDER BY PERIOD_FROM ");
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaGftrialBalance> response = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(IaGftrialBalance.class));
		return response;
	}
	
	@Override
	public List<Int0802Vo> findDiferrenceByCondition(Int0802SearchVo reqeust) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT G.ACC_NO, G.ACC_NAME, SUM(G.BRING_FORWARD) BRING_FORWARD, SUM(G.CARRY_FORWARD) CARRY_FORWARD, ");
		sql.append(" 	SUM(G.DEBIT) DEBIT, SUM(G.CREDIT) CREDIT, GA.PK_CODE, sum(GA.CURR_AMT) CURR_AMT ");
		sql.append(" FROM IA_GFTRIAL_BALANCE G ");
		sql.append(" LEFT JOIN EXCISE_ORG_GFMIS E ");
		sql.append(" 	ON G.DEPT_DISB  = '00000' || E.GF_COST_CENTER ");
		sql.append(" LEFT JOIN IA_GFLEDGER_ACCOUNT GA ");
		sql.append(" 	ON GA.GL_ACC_NO = G.ACC_NO ");
		sql.append(" WHERE G.IS_DELETED = 'N' ");
		sql.append(" 	AND E.IS_DELETED = 'N' ");
		sql.append(" 	AND GA.IS_DELETED = 'N' ");
		
		if(StringUtils.isNotBlank(reqeust.getPeriodFrom())) {
			sql.append(" 	AND G.PERIOD_FROM <= ? ");
			params.add(reqeust.getPeriodFrom());
		}
		
		if(StringUtils.isNotBlank(reqeust.getPeriodTo())) {
			sql.append(" 	AND G.PERIOD_FROM >= ? ");
			params.add(reqeust.getPeriodTo());
		}
		
		if(StringUtils.isNotBlank(reqeust.getGfDisburseUnit())) {
			sql.append("  	AND E.GF_DISBURSE_UNIT = ? ");
			params.add(reqeust.getGfDisburseUnit());
		}
		
		if(StringUtils.isNotBlank(reqeust.getBudgetYear())) {
			sql.append("  	AND G.PERIOD_YEAR = ? ");
			params.add(ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(reqeust.getBudgetYear(), ConvertDateUtils.YYYY), ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_EN));
		}
		
		sql.append(" GROUP BY G.ACC_NO, G.ACC_NAME, GA.PK_CODE ");
		sql.append(" ORDER BY G.ACC_NO ");
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Int0802Vo> response = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Int0802Vo.class));
		return response;
	}

}
