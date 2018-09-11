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
import th.co.baiwa.excise.domain.Int0802Vo;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfHdr;
import th.co.baiwa.excise.utils.BeanUtils;

public class RiskAssInfHdrRepositoryImpl implements RiskAssInfHdrRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(RiskAssInfHdrRepositoryImpl.class);

	private final CommonJdbcTemplate commonJdbcTemplate;

	@Autowired
	public RiskAssInfHdrRepositoryImpl(CommonJdbcTemplate commonJdbcTemplate) {
		this.commonJdbcTemplate = commonJdbcTemplate;
	}

	private final String QUERY = "SELECT * FROM IA_RISK_ASS_INF_HDR WHERE IS_DELETED = ? ";

	@Override
	public List<RiskAssInfHdr> findByCriteria(RiskAssInfHdr riskAssInfHdr) {
		logger.info("findByCriteria");
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(QUERY);
		params.add(FLAG.N_FLAG);
		if (BeanUtils.isNotEmpty(riskAssInfHdr.getRiskAssInfHdrId())) {
			sql.append("AND RISK_ASS_INF_HDR_ID = ? ");
			params.add(riskAssInfHdr.getRiskAssInfHdrId());
		}
		if (BeanUtils.isNotEmpty(riskAssInfHdr.getBudgetYear())) {
			sql.append("AND BUDGET_YEAR = ? ");
			params.add(riskAssInfHdr.getBudgetYear());
		}
		if (BeanUtils.isNotEmpty(riskAssInfHdr.getRiskAssInfHdrName())) {
			sql.append(SqlGeneratorUtils.oracleSqlWhereCondition("RISK_ASS_INF_HDR_NAME",
					riskAssInfHdr.getRiskAssInfHdrName()));
			params.add(riskAssInfHdr.getRiskAssInfHdrName());
		}
		if (BeanUtils.isNotEmpty(riskAssInfHdr.getBudgetYear())) {
			sql.append(SqlGeneratorUtils.oracleSqlWhereCondition("BUDGET_YEAR", riskAssInfHdr.getBudgetYear()));
			params.add(riskAssInfHdr.getBudgetYear());
		}
		sql.append(" ORDER BY RISK_ASS_INF_HDR_ID ");
		return commonJdbcTemplate.executeQuery(sql.toString(), params.toArray(), riskAssInfHdrMappingRow);
	}

	private RowMapper<RiskAssInfHdr> riskAssInfHdrMappingRow = new RowMapper<RiskAssInfHdr>() {

		@Override
		public RiskAssInfHdr mapRow(ResultSet rs, int rowNum) throws SQLException {

			RiskAssInfHdr assInfHdr = new RiskAssInfHdr();
			assInfHdr.setRiskAssInfHdrId(rs.getLong("RISK_ASS_INF_HDR_ID"));
			assInfHdr.setRiskAssInfHdrName(rs.getString("RISK_ASS_INF_HDR_NAME"));
			assInfHdr.setRiskInfPaperName(rs.getString("RISK_INF_PAPER_NAME"));
			assInfHdr.setBudgetYear(rs.getString("BUDGET_YEAR"));
			assInfHdr.setIsDeleted(rs.getString("IS_DELETED"));
			assInfHdr.setActive(rs.getString("ACTIVE"));
			assInfHdr.setCreatedBy(rs.getString("CREATED_BY"));
			assInfHdr.setCreatedDate(rs.getDate("CREATED_DATE"));
			assInfHdr.setUpdatedBy(rs.getString("UPDATED_BY"));
			assInfHdr.setUpdatedDate(rs.getDate("UPDATED_DATE"));

			return assInfHdr;
		}
	};

	@Override
	public List<Int0802Vo> findInfNameByBudgetYear(String budgetYear) {
		logger.info("findByCriteria");
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT INF_NAME FROM ( ");

		sql.append(" SELECT D.INF_NAME FROM IA_RISK_ASS_INF_HDR H ");
		sql.append(" LEFT JOIN IA_RISK_ASS_INF_DTL D ");
		sql.append(" ON D.RISK_INF_HRD_ID = H.RISK_ASS_INF_HDR_ID ");
		sql.append(" WHERE H.IS_DELETED = 'N' ");
		sql.append(" AND H.BUDGET_YEAR = ? ");
		sql.append(" AND H.ACTIVE = 'Y' ");
		sql.append(" AND D.IS_DELETED = 'N' ");
		sql.append(" UNION ");

		sql.append(" SELECT O.INF_NAME FROM IA_RISK_ASS_INF_HDR H ");
		sql.append(" LEFT JOIN IA_RISK_ASS_INF_OTHER_DTL O ");
		sql.append(" ON O.RISK_INF_HRD_ID = H.RISK_ASS_INF_HDR_ID ");
		sql.append(" WHERE H.IS_DELETED = 'N' ");
		sql.append(" AND H.BUDGET_YEAR = ? ");
		sql.append(" AND H.ACTIVE = 'Y' ");
		sql.append(" AND O.IS_DELETED = 'N') ");

		params.add(budgetYear);
		params.add(budgetYear);
		return commonJdbcTemplate.executeQuery(sql.toString(), params.toArray(), infMapping);
	}

	private RowMapper<Int0802Vo> infMapping = new RowMapper<Int0802Vo>() {

		@Override
		public Int0802Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int0802Vo int0802Vo = new Int0802Vo();
			int0802Vo.setInfName(rs.getString("INF_NAME"));
			return int0802Vo;
		}

	};

	@Override
	public List<Int0802Vo> findData(String budgetYear, String infName) {
		logger.info("findByCriteria");
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		
		sql.append(" SELECT RISK_ASS_INF_HDR_NAME , INF_NAME,PERCENT,NVL(RL , 0) RL ,VALUE_TRANSLATION ");
		sql.append(" FROM ");
		sql.append(" ( ");
		
		sql.append(" SELECT H.RISK_ASS_INF_HDR_NAME, D.INF_NAME ,H.PERCENT, D.RL , D.VALUE_TRANSLATION ");
		sql.append(" FROM IA_RISK_ASS_INF_HDR H ");
		sql.append(" left JOIN IA_RISK_ASS_INF_DTL D ");
		sql.append(" ON D.RISK_INF_HRD_ID = H.RISK_ASS_INF_HDR_ID ");
		sql.append(" WHERE H.IS_DELETED = 'N' ");
		sql.append(" AND H.BUDGET_YEAR = ? ");
		sql.append(" AND H.ACTIVE = 'Y' ");
		sql.append(" AND D.IS_DELETED = 'N' ");
		sql.append(" AND D.INF_NAME = ? ");
		sql.append(" UNION ");
		
		sql.append(" SELECT H.RISK_ASS_INF_HDR_NAME ,o.INF_NAME,H.PERCENT, O.RL , O.VALUE_TRANSLATION  ");
		sql.append(" FROM IA_RISK_ASS_INF_HDR H ");
		sql.append(" left JOIN IA_RISK_ASS_INF_OTHER_DTL O ");
		sql.append(" ON O.RISK_INF_HRD_ID = H.RISK_ASS_INF_HDR_ID ");
		sql.append(" WHERE H.IS_DELETED = 'N' ");
		sql.append(" AND H.BUDGET_YEAR = ? ");
		sql.append(" AND H.ACTIVE = 'Y' ");
		sql.append(" AND O.IS_DELETED = 'N' ");
		sql.append(" AND O.INF_NAME = ? ");
		sql.append(" ) ");
		params.add(budgetYear);
		params.add(infName);
		params.add(budgetYear);
		params.add(infName);
		
		return commonJdbcTemplate.executeQuery(sql.toString(), params.toArray(), mappingData);
	}

	private RowMapper<Int0802Vo> mappingData = new RowMapper<Int0802Vo>() {

		@Override
		public Int0802Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int0802Vo int0802Vo = new Int0802Vo();
			int0802Vo.setInfName(rs.getString("INF_NAME"));
			int0802Vo.setInfName(rs.getString("RISK_ASS_INF_HDR_NAME"));
			int0802Vo.setRl(rs.getString("RL"));
			int0802Vo.setPercent(rs.getBigDecimal("PERCENT"));
			return int0802Vo;

		}

	};

	@Override
	public void updatePercent(BigDecimal percent, Long id) {
		logger.info("findByCriteria");
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

		sql.append(" UPDATE  IA_RISK_ASS_INF_HDR SET PERCENT = ? WHERE RISK_ASS_INF_HDR_ID = ? ");
		params.add(percent);
		params.add(id);
		commonJdbcTemplate.executeUpdate(sql.toString(), params.toArray());	
	}
	
}
