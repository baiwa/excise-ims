package th.co.baiwa.excise.ia.persistence.repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.domain.Int0801Vo;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsHdr;
import th.co.baiwa.excise.utils.BeanUtils;

public class RiskAssRiskWsHdrRepositoryImpl implements RiskAssRiskWsHdrRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(RiskAssRiskWsHdrRepositoryImpl.class);

	private final CommonJdbcTemplate commonJdbcTemplate;

	@Autowired
	public RiskAssRiskWsHdrRepositoryImpl(CommonJdbcTemplate commonJdbcTemplate) {
		this.commonJdbcTemplate = commonJdbcTemplate;
	}

	private final String QUERY = "SELECT * FROM IA_RISK_ASS_RISK_WS_HDR WHERE IS_DELETED = ? ";

	@Override
	public List<RiskAssRiskWsHdr> findByCriteria(RiskAssRiskWsHdr riskAssRiskWsHdr) {
		logger.info("findByCriteria");
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(QUERY);
		params.add(FLAG.N_FLAG);
		if (BeanUtils.isNotEmpty(riskAssRiskWsHdr.getRiskHrdId())) {
			sql.append("AND RISK_HRD_ID = ? ");
			params.add(riskAssRiskWsHdr.getRiskHrdId());
		}
		if (BeanUtils.isNotEmpty(riskAssRiskWsHdr.getRiskHdrName())) {
			sql.append(SqlGeneratorUtils.oracleSqlWhereCondition("RISK_HDR_NAME", riskAssRiskWsHdr.getRiskHdrName()));
			params.add(riskAssRiskWsHdr.getRiskHdrName());
		}
		if (BeanUtils.isNotEmpty(riskAssRiskWsHdr.getBudgetYear())) {
			sql.append(SqlGeneratorUtils.oracleSqlWhereCondition("BUDGET_YEAR", riskAssRiskWsHdr.getBudgetYear()));
			params.add(riskAssRiskWsHdr.getBudgetYear());
		}
		sql.append(" ORDER BY RISK_HRD_ID ");
		return commonJdbcTemplate.executeQuery(sql.toString(), params.toArray(), riskAssRiskWsHdrMappingRow);
	}

	private RowMapper<RiskAssRiskWsHdr> riskAssRiskWsHdrMappingRow = new RowMapper<RiskAssRiskWsHdr>() {

		@Override
		public RiskAssRiskWsHdr mapRow(ResultSet rs, int arg1) throws SQLException {
			RiskAssRiskWsHdr riskAssRiskWsHdr = new RiskAssRiskWsHdr();
			riskAssRiskWsHdr.setRiskHrdId(rs.getLong("RISK_HRD_ID"));
			riskAssRiskWsHdr.setRiskHdrName(rs.getString("RISK_HDR_NAME"));
			riskAssRiskWsHdr.setBudgetYear(rs.getString("BUDGET_YEAR"));
			riskAssRiskWsHdr.setUserCheck(rs.getString("USER_CHECK"));
			riskAssRiskWsHdr.setIsDeleted(rs.getString("IS_DELETED"));
			riskAssRiskWsHdr.setActive(rs.getString("ACTIVE"));
			riskAssRiskWsHdr.setCreatedBy(rs.getString("CREATED_BY"));
			riskAssRiskWsHdr.setCreatedDate(rs.getDate("CREATED_DATE"));
			riskAssRiskWsHdr.setUpdatedBy(rs.getString("UPDATED_BY"));
			riskAssRiskWsHdr.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			return riskAssRiskWsHdr;

		}

	};

	@Override
	public List<Int0801Vo> findProjectBaseByBudgetYear(String budgetYear) {
		logger.info("findByCriteria");
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT PROJECT_BASE,DEPARTMENT_NAME FROM ( "); 
		sql.append(" SELECT D.PROJECT_BASE , D.DEPARTMENT_NAME FROM IA_RISK_ASS_RISK_WS_HDR H ");
		sql.append(" LEFT JOIN IA_RISK_ASS_RISK_WS_DTL D ");
		sql.append(" ON D.RISK_HRD_ID = H.RISK_HRD_ID ");
		sql.append(" WHERE H.IS_DELETED = 'N' ");
		sql.append(" AND H.BUDGET_YEAR = ? ");
		sql.append(" AND D.IS_DELETED = 'N' ");
		sql.append(" UNION ");
		sql.append(" SELECT O.PROJECT_BASE , o.DEPARTMENT_NAME FROM IA_RISK_ASS_RISK_WS_HDR H ");
		sql.append(" LEFT JOIN IA_RISK_ASS_OTHER_DTL O ");
		sql.append(" ON O.RISK_HRD_ID = H.RISK_HRD_ID ");
		sql.append(" WHERE H.IS_DELETED = 'N' ");
		sql.append(" AND H.BUDGET_YEAR = ? ");
		sql.append(" AND O.IS_DELETED = 'N') ");
		params.add(budgetYear);
		params.add(budgetYear);
		return commonJdbcTemplate.executeQuery(sql.toString(), params.toArray(), projectMapping);
	}

	private RowMapper<Int0801Vo> projectMapping = new RowMapper<Int0801Vo>() {

		@Override
		public Int0801Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int0801Vo int0801Vo = new Int0801Vo();
			int0801Vo.setProjectBase(rs.getString("PROJECT_BASE"));
			int0801Vo.setDepartmentName(rs.getString("DEPARTMENT_NAME"));
			return int0801Vo;

		}

	};

	@Override
	public List<Int0801Vo> findData(String budgetYear, String projectBase , String depName) {
		logger.info("findByCriteria");
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT RISK_HDR_NAME , PROJECT_BASE,NVL(RL , 0) RL ,VALUE_TRANSLATION ");
		sql.append(" FROM ");
		sql.append(" ( ");
		sql.append(" SELECT H.RISK_HDR_NAME, D.PROJECT_BASE , D.RL , D.VALUE_TRANSLATION , D.DEPARTMENT_NAME");
		sql.append(" FROM IA_RISK_ASS_RISK_WS_HDR H ");
		sql.append(" left JOIN IA_RISK_ASS_RISK_WS_DTL D ");
		sql.append(" ON D.RISK_HRD_ID = H.RISK_HRD_ID ");
		sql.append(" WHERE H.IS_DELETED = 'N' ");
		sql.append(" AND H.BUDGET_YEAR = ? ");
		sql.append(" AND D.IS_DELETED = 'N' ");
		sql.append(" AND D.PROJECT_BASE = ? ");
		sql.append(" AND D.DEPARTMENT_NAME = ? ");
		sql.append(" UNION ");
		sql.append(" SELECT H.RISK_HDR_NAME ,o.PROJECT_BASE, O.RL , O.VALUE_TRANSLATION , O.DEPARTMENT_NAME ");
		sql.append(" FROM IA_RISK_ASS_RISK_WS_HDR H ");
		sql.append(" left JOIN IA_RISK_ASS_OTHER_DTL O ");
		sql.append(" ON O.RISK_HRD_ID = H.RISK_HRD_ID ");
		sql.append(" WHERE H.IS_DELETED = 'N' ");
		sql.append(" AND H.BUDGET_YEAR = ? ");
		sql.append(" AND O.IS_DELETED = 'N' ");
		sql.append(" AND O.PROJECT_BASE = ? ");
		sql.append(" AND O.DEPARTMENT_NAME = ? ");
		sql.append(" ) ");
		params.add(budgetYear);
		params.add(projectBase);
		params.add(depName);
		params.add(budgetYear);
		params.add(projectBase);
		params.add(depName);
		return commonJdbcTemplate.executeQuery(sql.toString(), params.toArray(), mappingData);
	}

	private RowMapper<Int0801Vo> mappingData = new RowMapper<Int0801Vo>() {

		@Override
		public Int0801Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int0801Vo int0801Vo = new Int0801Vo();
			int0801Vo.setProjectBase(rs.getString("PROJECT_BASE"));
			int0801Vo.setProjectBase(rs.getString("RISK_HDR_NAME"));
			int0801Vo.setRl(rs.getString("RL"));
			return int0801Vo;

		}

	};

	@Override
	public void updatePercent(BigDecimal percent, Long id) {
		logger.info("findByCriteria");
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		sql.append(" UPDATE  IA_RISK_ASS_RISK_WS_HDR SET PERCENT = ? WHERE RISK_HRD_ID = ? ");
		params.add(percent);
		params.add(id);
		commonJdbcTemplate.executeUpdate(sql.toString(), params.toArray());
	}

}
