package th.co.baiwa.excise.ia.persistence.repository;

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
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfHdr;
import th.co.baiwa.excise.utils.BeanUtils;



public class RiskAssInfHdrRepositoryImpl implements RiskAssInfHdrRepositoryCustom{

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
		if(BeanUtils.isNotEmpty(riskAssInfHdr.getRiskAssInfHdrId())) {
			sql.append("AND RISK_ASS_INF_HDR_ID = ? ");
			params.add(riskAssInfHdr.getRiskAssInfHdrId());
		}
		if(BeanUtils.isNotEmpty(riskAssInfHdr.getRiskAssInfHdrName())) {
			sql.append(SqlGeneratorUtils.oracleSqlWhereCondition("RISK_ASS_INF_HDR_NAME", riskAssInfHdr.getRiskAssInfHdrName()));
			params.add(riskAssInfHdr.getRiskAssInfHdrName());
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
}
