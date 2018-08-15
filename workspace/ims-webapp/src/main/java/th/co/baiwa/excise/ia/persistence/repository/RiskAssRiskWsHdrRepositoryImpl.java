package th.co.baiwa.excise.ia.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.excise.constant.DateConstant;
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
		if(BeanUtils.isNotEmpty(riskAssRiskWsHdr.getRiskHrdId())) {
			sql.append("AND RISK_HRD_ID = ? ");
			params.add(riskAssRiskWsHdr.getRiskHrdId());
		}
		if(BeanUtils.isNotEmpty(riskAssRiskWsHdr.getRiskHdrName())) {
			sql.append(SqlGeneratorUtils.oracleSqlWhereCondition("RISK_HDR_NAME", riskAssRiskWsHdr.getRiskHdrName()));
			params.add(riskAssRiskWsHdr.getRiskHdrName());
		}
		if(BeanUtils.isNotEmpty(riskAssRiskWsHdr.getBudgetYear())) {
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
			riskAssRiskWsHdr.setCreatedDate(rs.getDate("CREATED_DATE" , Calendar.getInstance(DateConstant.LOCAL_TH)));
			riskAssRiskWsHdr.setUpdatedBy(rs.getString("UPDATED_BY"));
			riskAssRiskWsHdr.setUpdatedDate(rs.getDate("UPDATED_DATE", Calendar.getInstance(DateConstant.LOCAL_TH)));
			return riskAssRiskWsHdr;

		}

	};

}
