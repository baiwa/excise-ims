package th.co.baiwa.excise.ta.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.excise.ta.persistence.entity.PlanTaxAudit;
import th.co.baiwa.buckwaframework.common.util.BeanUtils; 
import th.co.baiwa.buckwaframework.common.util.OracleUtils;

public class PlanTaxAuditRepositoryImpl implements PlanTaxAuditCustom{
	private Logger logger = LoggerFactory.getLogger(PlanTaxAuditRepositoryImpl.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Autowired
	private JdbcTemplate JdbcTemplate;
	
	@Override
	public List<PlanTaxAudit> findPlanTaxAuditByCriteriaForDataTable(PlanTaxAudit planTaxAudit, int start, int length) {
		logger.info("findExciseIdOrderByPercenTax");
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sqlFindTaxditByCriteia(planTaxAudit, sql, params);
		return commonJdbcTemplate.executeQuery(OracleUtils.limitForDataTable(sql, start, length), params.toArray(), mappingSelectStarPlanTaxAudit);

	}
	
	@Override
	public long countPlanTaxAuditByCriteriaForDataTable(PlanTaxAudit planTaxAudit) {
		logger.info("findExciseIdOrderByPercenTax");
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sqlFindTaxditByCriteia(planTaxAudit, sql, params);
		return JdbcTemplate.queryForObject(OracleUtils.countForDatatable(sql), params.toArray(),Long.class);

	}
	
	
	private void sqlFindTaxditByCriteia(PlanTaxAudit planTaxAudit,StringBuilder sql, List<Object> params) {
		sql.append(" SELECT * FROM TA_PLAN_TAX_AUDIT T ");
		sql.append(" WHERE T.IS_DELETED = '").append(FLAG.N_FLAG).append("'");
		if(BeanUtils.isNotEmpty(planTaxAudit.getTaPlanTaxAuditId())) {
			sql.append(" AND T.TA_PLAN_TAX_AUDIT_ID = ?");
			params.add(planTaxAudit.getTaPlanTaxAuditId());
		}
		if(BeanUtils.isNotEmpty(planTaxAudit.getBudgetYear())) {
			sql.append(" AND T.BUDGET_YEAR = ? ");
			params.add(planTaxAudit.getBudgetYear());
		}
		if(BeanUtils.isNotEmpty(planTaxAudit.getAnalysNumber())) {
			sql.append(" AND T.ANALYS_NUMBER = ? ");
			params.add(planTaxAudit.getAnalysNumber());
		}
		if(BeanUtils.isNotEmpty(planTaxAudit.getStatusPlan())) {
			sql.append(" AND T.STATUS_PLAN = ? ");
			params.add(planTaxAudit.getStatusPlan());
		}
		sql.append(" ORDER BY TA_PLAN_TAX_AUDIT_ID DESC ");
	}
	
	
	private RowMapper<PlanTaxAudit> mappingSelectStarPlanTaxAudit = new RowMapper<PlanTaxAudit>() {
		@Override
		public PlanTaxAudit mapRow(ResultSet rs, int rowNum) throws SQLException {
			PlanTaxAudit planTaxAudit = new PlanTaxAudit();
			planTaxAudit.setTaPlanTaxAuditId(rs.getLong("TA_PLAN_TAX_AUDIT_ID"));
			planTaxAudit.setAnalysNumber(rs.getString("ANALYS_NUMBER"));
			planTaxAudit.setMonthFrom(rs.getString("MONTH_FROM"));
			planTaxAudit.setMonthTo(rs.getString("MONTH_TO"));
			planTaxAudit.setStatusPlan(rs.getString("STATUS_PLAN"));
			planTaxAudit.setBudgetYear(rs.getString("BUDGET_YEAR"));
			planTaxAudit.setIsDeleted(rs.getString("IS_DELETED"));
			planTaxAudit.setVersion(rs.getInt("VERSION"));
			planTaxAudit.setCreatedBy(rs.getString("CREATED_BY"));
			planTaxAudit.setCreatedDate(rs.getDate("CREATED_DATE"));
			planTaxAudit.setUpdatedBy(rs.getString("UPDATED_BY"));
			planTaxAudit.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			return planTaxAudit;
		}
	};
	
	@Override
	public long countDetailWorkSheetInCriteria(String analysNumber, Long backDate , String endDate) {
		logger.info("countDetailWorkSheetInCriteria");
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(1) FROM ( ");
		sql.append(" SELECT DISTINCT D.EXCISE_ID FROM TA_PLAN_WORK_SHEET_HEADER H ");
		sql.append(" INNER JOIN TA_PLAN_WORK_SHEET_DETAIL D ");
		sql.append(" ON D.ANALYS_NUMBER = H.ANALYS_NUMBER ");
		sql.append(" AND D.EXCISE_ID = H.EXCISE_ID ");
		sql.append(" WHERE H.IS_DELETED = 'N' ");
		sql.append(" AND D.IS_DELETED = 'N' ");
		sql.append(" AND H.ANALYS_NUMBER = ? ");
		sql.append(" AND D.MONTH IN ( ");
		sql.append(" SELECT REPLACE(TO_CHAR( ADD_MONTHS( to_date(?,'dd/mm/yyyy'), LEVEL-? ) , 'MON yy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI'), ' ', ' ' ) MONTH_AFTER ");
		sql.append(" 	FROM DUAL CONNECT BY LEVEL <= ? ");
		sql.append(" ) ");
		sql.append(" ) ");
		params.add(analysNumber);
		params.add(endDate);
		params.add(backDate);
		params.add(backDate);
		return JdbcTemplate.queryForObject(sql.toString(), params.toArray(),Long.class);
	}
	
	
}
