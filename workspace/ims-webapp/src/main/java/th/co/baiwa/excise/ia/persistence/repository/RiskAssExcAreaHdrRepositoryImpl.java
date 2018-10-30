package th.co.baiwa.excise.ia.persistence.repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.excise.domain.Int0803Vo;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssExcAreaHdr;
import th.co.baiwa.excise.utils.BeanUtils;

public class RiskAssExcAreaHdrRepositoryImpl implements RiskAssExcAreaHdrRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(RiskAssExcAreaHdrRepositoryImpl.class);

	private final CommonJdbcTemplate commonJdbcTemplate;

	@Autowired
	public RiskAssExcAreaHdrRepositoryImpl(CommonJdbcTemplate commonJdbcTemplate) {
		this.commonJdbcTemplate = commonJdbcTemplate;
	}

	private final String QUERY = "SELECT * FROM IA_RISK_ASS_EXC_AREA_HRD WHERE IS_DELETED = ? ";

	@Override
	public List<RiskAssExcAreaHdr> findByCriteria(RiskAssExcAreaHdr riskAssRiskWsHdr) {
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

	private RowMapper<RiskAssExcAreaHdr> riskAssRiskWsHdrMappingRow = new RowMapper<RiskAssExcAreaHdr>() {

		@Override
		public RiskAssExcAreaHdr mapRow(ResultSet rs, int arg1) throws SQLException {
			RiskAssExcAreaHdr riskAssRiskWsHdr = new RiskAssExcAreaHdr();
			riskAssRiskWsHdr.setRiskHrdId(rs.getLong("RISK_HRD_ID"));
			riskAssRiskWsHdr.setRiskHdrName(rs.getString("RISK_HDR_NAME"));
			riskAssRiskWsHdr.setBudgetYear(rs.getString("BUDGET_YEAR"));
			riskAssRiskWsHdr.setIsDeleted(rs.getString("IS_DELETED"));
			riskAssRiskWsHdr.setActive(rs.getString("ACTIVE"));
			riskAssRiskWsHdr.setCreatedBy(rs.getString("CREATED_BY"));
			riskAssRiskWsHdr.setCreatedDate(rs.getDate("CREATED_DATE"));
			riskAssRiskWsHdr.setUpdatedBy(rs.getString("UPDATED_BY"));
			riskAssRiskWsHdr.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			
			riskAssRiskWsHdr.setCheckLastName(rs.getString("CHECK_LAST_NAME"));
			riskAssRiskWsHdr.setCheckPosition(rs.getString("CHECK_POSITION"));
			riskAssRiskWsHdr.setCheckUserName(rs.getString("CHECK_USER_NAME"));
			riskAssRiskWsHdr.setCheckUserTitle(rs.getString("CHECK_USER_TITLE"));
			riskAssRiskWsHdr.setCreateLastName(rs.getString("CREATE_LAST_NAME"));
			riskAssRiskWsHdr.setCreatePosition(rs.getString("CREATE_POSITIONN"));
			riskAssRiskWsHdr.setCreateUserName(rs.getString("CREATE_USER_NAME"));
			riskAssRiskWsHdr.setCreateUserTitle(rs.getString("CREATE_USER_TITLE"));
			return riskAssRiskWsHdr;

		}

	};

	@Override
	public List<Int0803Vo> findProjectBaseByBudgetYear(String budgetYear) {
		logger.info("findByCriteria");
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT DEPARTMENT_NAME FROM ( ");
		sql.append(" SELECT D.DEPARTMENT_NAME FROM IA_RISK_ASS_EXC_AREA_HRD H ");
		sql.append(" LEFT JOIN IA_RISK_ASS_EXC_AREA_DTL D ");
		sql.append(" ON D.RISK_HRD_ID = H.RISK_HRD_ID ");
		sql.append(" WHERE H.IS_DELETED = 'N' ");
		sql.append(" AND H.BUDGET_YEAR = ? ");
		sql.append(" AND H.ACTIVE = 'Y' ");
		sql.append(" AND D.IS_DELETED = 'N' ");
		sql.append(" UNION ");
		sql.append(" SELECT o.DEPARTMENT_NAME FROM IA_RISK_ASS_EXC_AREA_HRD H ");
		sql.append(" LEFT JOIN IA_RISK_ASS_EXC_OTHER_DTL O ");
		sql.append(" ON O.RISK_HRD_ID = H.RISK_HRD_ID ");
		sql.append(" WHERE H.IS_DELETED = 'N' ");
		sql.append(" AND H.BUDGET_YEAR = ? ");
		sql.append(" AND H.ACTIVE = 'Y' ");
		sql.append(" AND O.IS_DELETED = 'N' ");
		sql.append(" UNION ");
		sql.append(" SELECT o.DEPARTMENT_NAME FROM IA_RISK_ASS_EXC_AREA_HRD H ");
		sql.append(" LEFT JOIN IA_RISK_ASS_EXC_NOC_DTL O ");
		sql.append(" ON O.RISK_HRD_ID = H.RISK_HRD_ID ");
		sql.append(" WHERE H.IS_DELETED = 'N' ");
		sql.append(" AND H.BUDGET_YEAR = ? ");
		sql.append(" AND H.ACTIVE = 'Y' ");
		sql.append(" AND O.IS_DELETED = 'N' ");
		sql.append(" UNION ");
		sql.append(" SELECT o.DEPARTMENT_NAME FROM IA_RISK_ASS_EXC_AREA_HRD H ");
		sql.append(" LEFT JOIN IA_RISK_ASS_EXC_OV3D_DTL O ");
		sql.append(" ON O.RISK_HRD_ID = H.RISK_HRD_ID ");
		sql.append(" WHERE H.IS_DELETED = 'N' ");
		sql.append(" AND H.BUDGET_YEAR = ? ");
		sql.append(" AND H.ACTIVE = 'Y' ");
		sql.append(" AND O.IS_DELETED = 'N' ");
		sql.append(" UNION ");
		sql.append(" SELECT o.DEPARTMENT_NAME FROM IA_RISK_ASS_EXC_AREA_HRD H ");
		sql.append(" LEFT JOIN IA_RISK_ASS_EXC_PEN_DTL O ");
		sql.append(" ON O.RISK_HRD_ID = H.RISK_HRD_ID ");
		sql.append(" WHERE H.IS_DELETED = 'N' ");
		sql.append(" AND H.BUDGET_YEAR = ? ");
		sql.append(" AND H.ACTIVE = 'Y' ");
		sql.append(" AND O.IS_DELETED = 'N' ");
		sql.append(" UNION ");
		sql.append(" SELECT o.DEPARTMENT_NAME FROM IA_RISK_ASS_EXC_AREA_HRD H ");
		sql.append(" LEFT JOIN IA_RISK_ASS_EXC_REC_DTL O ");
		sql.append(" ON O.RISK_HRD_ID = H.RISK_HRD_ID ");
		sql.append(" WHERE H.IS_DELETED = 'N' ");
		sql.append(" AND H.BUDGET_YEAR = ? ");
		sql.append(" AND H.ACTIVE = 'Y' ");
		sql.append(" AND O.IS_DELETED = 'N') ");
		params.add(budgetYear);
		params.add(budgetYear);
		params.add(budgetYear);
		params.add(budgetYear);
		params.add(budgetYear);
		params.add(budgetYear);
		return commonJdbcTemplate.executeQuery(sql.toString(), params.toArray(), projectMapping);
	}

	private RowMapper<Int0803Vo> projectMapping = new RowMapper<Int0803Vo>() {

		@Override
		public Int0803Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int0803Vo int0803Vo = new Int0803Vo();
			int0803Vo.setDepartmentName(rs.getString("DEPARTMENT_NAME"));
			return int0803Vo;

		}

	};

	@Override
	public List<Int0803Vo> findData(String budgetYear,  String depName) {
		logger.info("findByCriteria");
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT RISK_HDR_NAME ,NVL(RL , 0) RL ,VALUE_TRANSLATION "); 
		sql.append(" FROM ");
		sql.append(" ( ");
		sql.append(" SELECT H.RISK_HDR_NAME , D.RL , D.VALUE_TRANSLATION , D.DEPARTMENT_NAME ");
		sql.append(" FROM IA_RISK_ASS_EXC_AREA_HRD H ");
		sql.append(" left JOIN IA_RISK_ASS_EXC_AREA_DTL D ");
		sql.append(" ON D.RISK_HRD_ID = H.RISK_HRD_ID ");
		sql.append(" WHERE H.IS_DELETED = 'N' ");
		sql.append(" AND H.BUDGET_YEAR = ? ");
		sql.append(" AND H.ACTIVE = 'Y' ");
		sql.append(" AND D.IS_DELETED = 'N' ");
		sql.append(" AND D.DEPARTMENT_NAME = ? ");
		sql.append(" UNION ");
		sql.append(" SELECT H.RISK_HDR_NAME , O.RL , O.VALUE_TRANSLATION , O.DEPARTMENT_NAME ");
		sql.append(" FROM IA_RISK_ASS_EXC_AREA_HRD H ");
		sql.append(" left JOIN IA_RISK_ASS_EXC_NOC_DTL O ");
		sql.append(" ON O.RISK_HRD_ID = H.RISK_HRD_ID ");
		sql.append(" WHERE H.IS_DELETED = 'N' ");
		sql.append(" AND H.BUDGET_YEAR = ? ");
		sql.append(" AND H.ACTIVE = 'Y' ");
		sql.append(" AND O.IS_DELETED = 'N' ");
		sql.append(" AND O.DEPARTMENT_NAME = ? ");
		sql.append(" UNION ");
		sql.append(" SELECT H.RISK_HDR_NAME , O.RL , O.VALUE_TRANSLATION , O.DEPARTMENT_NAME ");
		sql.append(" FROM IA_RISK_ASS_EXC_AREA_HRD H ");
		sql.append(" left JOIN IA_RISK_ASS_EXC_OTHER_DTL O ");
		sql.append(" ON O.RISK_HRD_ID = H.RISK_HRD_ID ");
		sql.append(" WHERE H.IS_DELETED = 'N' ");
		sql.append(" AND H.BUDGET_YEAR = ? ");
		sql.append(" AND H.ACTIVE = 'Y' ");
		sql.append(" AND O.IS_DELETED = 'N' ");
		sql.append(" AND O.DEPARTMENT_NAME = ? ");
		sql.append(" UNION ");
		sql.append(" SELECT H.RISK_HDR_NAME , O.RL , O.VALUE_TRANSLATION , O.DEPARTMENT_NAME ");
		sql.append(" FROM IA_RISK_ASS_EXC_AREA_HRD H ");
		sql.append(" left JOIN IA_RISK_ASS_EXC_OV3D_DTL O ");
		sql.append(" ON O.RISK_HRD_ID = H.RISK_HRD_ID ");
		sql.append(" WHERE H.IS_DELETED = 'N' ");
		sql.append(" AND H.BUDGET_YEAR = ? ");
		sql.append(" AND H.ACTIVE = 'Y' ");
		sql.append(" AND O.IS_DELETED = 'N' ");
		sql.append(" AND O.DEPARTMENT_NAME = ? ");
		sql.append(" UNION ");
		sql.append(" SELECT H.RISK_HDR_NAME , O.RL , O.VALUE_TRANSLATION , O.DEPARTMENT_NAME ");
		sql.append(" FROM IA_RISK_ASS_EXC_AREA_HRD H ");
		sql.append(" left JOIN IA_RISK_ASS_EXC_PEN_DTL O ");
		sql.append(" ON O.RISK_HRD_ID = H.RISK_HRD_ID ");
		sql.append(" WHERE H.IS_DELETED = 'N' ");
		sql.append(" AND H.BUDGET_YEAR = ? ");
		sql.append(" AND H.ACTIVE = 'Y' ");
		sql.append(" AND O.IS_DELETED = 'N' ");
		sql.append(" AND O.DEPARTMENT_NAME = ? ");
		sql.append(" UNION ");
		sql.append(" SELECT H.RISK_HDR_NAME , O.RL , O.VALUE_TRANSLATION , O.DEPARTMENT_NAME ");
		sql.append(" FROM IA_RISK_ASS_EXC_AREA_HRD H ");
		sql.append(" left JOIN IA_RISK_ASS_EXC_REC_DTL O ");
		sql.append(" ON O.RISK_HRD_ID = H.RISK_HRD_ID ");
		sql.append(" WHERE H.IS_DELETED = 'N' ");
		sql.append(" AND H.BUDGET_YEAR = ? ");
		sql.append(" AND H.ACTIVE = 'Y' ");
		sql.append(" AND O.IS_DELETED = 'N' ");
		sql.append(" AND O.DEPARTMENT_NAME = ? ");
		sql.append(" ) ");
		params.add(budgetYear);
		params.add(depName);
		params.add(budgetYear);
		params.add(depName);
		params.add(budgetYear);
		params.add(depName);
		params.add(budgetYear);
		params.add(depName);
		params.add(budgetYear);
		params.add(depName);
		params.add(budgetYear);
		params.add(depName);
		return commonJdbcTemplate.executeQuery(sql.toString(), params.toArray(), mappingData);
	}

	private RowMapper<Int0803Vo> mappingData = new RowMapper<Int0803Vo>() {

		@Override
		public Int0803Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int0803Vo int0801Vo = new Int0803Vo();
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

		sql.append(" UPDATE  IA_RISK_ASS_EXC_AREA_HRD SET PERCENT = ? WHERE RISK_HRD_ID = ? ");
		params.add(percent);
		params.add(id);
		commonJdbcTemplate.executeUpdate(sql.toString(), params.toArray());
	}
	
	
	
	@Override
	public List<RiskAssExcAreaHdr> findRiskAssExcAreaHdrByCriteria(RiskAssExcAreaHdr riskAssExcAreaHdr) {
		logger.info("findByCriteria");
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM IA_RISK_ASS_EXC_AREA_HRD WHERE IS_DELETED = ? ");
		params.add(FLAG.N_FLAG);
		if(BeanUtils.isNotEmpty(riskAssExcAreaHdr.getRiskHrdId())) {
			sql.append(" AND RISK_HRD_ID = ? ");
			params.add(riskAssExcAreaHdr.getRiskHrdId());
		}
		if(BeanUtils.isNotEmpty(riskAssExcAreaHdr.getRiskHdrName())) {
			sql.append(" AND RISK_HDR_NAME = ? ");
			params.add(riskAssExcAreaHdr.getRiskHdrName());
		}
		if(BeanUtils.isNotEmpty(riskAssExcAreaHdr.getBudgetYear())) {
			sql.append(" AND BUDGET_YEAR = ? ");
			params.add(riskAssExcAreaHdr.getBudgetYear());
		}
		if(BeanUtils.isNotEmpty(riskAssExcAreaHdr.getActive())) {
			sql.append(" AND ACTIVE = ? ");
			params.add(riskAssExcAreaHdr.getActive());
		}
		
		if(BeanUtils.isNotEmpty(riskAssExcAreaHdr.getRiskHrdPaperName())) {
			sql.append(" AND RISK_HDR_PAPER_NAME = ? ");
			params.add(riskAssExcAreaHdr.getRiskHrdPaperName());
		}
		return commonJdbcTemplate.executeQuery(sql.toString(), params.toArray(), riskAssExcAreaHdrMapping);
	}
	
	private RowMapper<RiskAssExcAreaHdr> riskAssExcAreaHdrMapping = new RowMapper<RiskAssExcAreaHdr>() {

		@Override
		public RiskAssExcAreaHdr mapRow(ResultSet rs, int arg1) throws SQLException {
			RiskAssExcAreaHdr riskAssExcAreaHdr = new RiskAssExcAreaHdr();
			riskAssExcAreaHdr.setRiskHrdId(rs.getLong("RISK_HRD_ID"));
			riskAssExcAreaHdr.setRiskHdrName(rs.getString("RISK_HDR_NAME"));
			riskAssExcAreaHdr.setActive(rs.getString("ACTIVE"));
			riskAssExcAreaHdr.setRiskHrdPaperName(rs.getString("RISK_HDR_PAPER_NAME"));
			riskAssExcAreaHdr.setBudgetYear(rs.getString("BUDGET_YEAR"));
			riskAssExcAreaHdr.setPercent(rs.getBigDecimal("PERCENT"));
			riskAssExcAreaHdr.setIsDeleted(rs.getString("IS_DELETED"));
			riskAssExcAreaHdr.setVersion(rs.getInt("VERSION"));
			riskAssExcAreaHdr.setCreatedBy(rs.getString("CREATED_BY"));
			riskAssExcAreaHdr.setCreatedDate(rs.getDate("CREATED_DATE"));
			riskAssExcAreaHdr.setUpdatedBy(rs.getString("UPDATED_BY"));
			riskAssExcAreaHdr.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			riskAssExcAreaHdr.setCheckLastName(rs.getString("CHECK_LAST_NAME"));
			riskAssExcAreaHdr.setCheckPosition(rs.getString("CHECK_POSITION"));
			riskAssExcAreaHdr.setCheckUserName(rs.getString("CHECK_USER_NAME"));
			riskAssExcAreaHdr.setCheckUserTitle(rs.getString("CHECK_USER_TITLE"));
			riskAssExcAreaHdr.setCreateLastName(rs.getString("CREATE_LAST_NAME"));
			riskAssExcAreaHdr.setCreatePosition(rs.getString("CREATE_POSITIONN"));
			riskAssExcAreaHdr.setCreateUserName(rs.getString("CREATE_USER_NAME"));
			riskAssExcAreaHdr.setCreateUserTitle(rs.getString("CREATE_USER_TITLE"));
			return riskAssExcAreaHdr;

		}

	};

}
