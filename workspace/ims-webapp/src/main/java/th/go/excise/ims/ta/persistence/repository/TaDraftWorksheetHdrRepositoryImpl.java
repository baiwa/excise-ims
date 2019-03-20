package th.go.excise.ims.ta.persistence.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ta.vo.YearMonthVo;

public class TaDraftWorksheetHdrRepositoryImpl implements TaDraftWorksheetHdrRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(TaDraftWorksheetHdrRepositoryImpl.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public YearMonthVo findMonthStartByAnalysisNumber(String analysisNumber) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT YEAR_MONTH_START, ");
		sql.append("   CON_MAIN.YEAR_MONTH_END, ");
		sql.append("   CON_MAIN.MONTH_NUM                               AS MONTH_TOTAL , ");
		sql.append("   TO_NUMBER(SUBSTR(CON_MAIN.YEAR_MONTH_START,5,2)) AS MONTH_START, ");
		sql.append("   W_HDR.COND_SUB_CAPITAL_FLAG AS COND_SUB_CAPITAL_FLAG, ");
		sql.append("   W_HDR.COND_SUB_RISK_FLAG AS COND_SUB_RISK_FLAG, ");
		sql.append("   W_HDR.COND_SUB_NO_AUDIT_FLAG AS COND_SUB_NO_AUDIT_FLAG, ");
		sql.append("   W_HDR.WORKSHEET_STATUS");
		sql.append(" FROM TA_WORKSHEET_COND_MAIN_HDR CON_MAIN ");
		sql.append(" INNER JOIN TA_WORKSHEET_HDR W_HDR ");
		sql.append(" ON CON_MAIN.ANALYSIS_NUMBER=W_HDR.ANALYSIS_NUMBER ");
		sql.append(" WHERE CON_MAIN.IS_DELETED = ? ");
		sql.append(" AND CON_MAIN.ANALYSIS_NUMBER = ? ");
		YearMonthVo yearMonthVo = null;
		try {
			yearMonthVo = (YearMonthVo) commonJdbcTemplate.queryForObject(
				sql.toString(),
				new Object[] {
					FLAG.N_FLAG,
						analysisNumber
				},
				new BeanPropertyRowMapper<YearMonthVo>(YearMonthVo.class)
			);
		} catch (DataAccessException e) {
			logger.warn(e.getMessage());
			yearMonthVo = new YearMonthVo();
		}
		
		return yearMonthVo;
	}

}
