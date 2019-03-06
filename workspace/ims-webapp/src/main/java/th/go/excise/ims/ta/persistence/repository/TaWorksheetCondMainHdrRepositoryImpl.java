package th.go.excise.ims.ta.persistence.repository;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ta.vo.YearMonthVo;

public class TaWorksheetCondMainHdrRepositoryImpl implements TaWorksheetCondMainHdrRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(TaWorksheetCondMainHdrRepositoryImpl.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public YearMonthVo findMonthStartByDraftNumber(String draftNumber) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT YEAR_MONTH_START, YEAR_MONTH_END, MONTH_NUM AS MONTH_TOTAL ");
		sql.append("   ,TO_NUMBER(SUBSTR(YEAR_MONTH_START,5,2)) AS MONTH_START ");
		sql.append(" FROM TA_WORKSHEET_COND_MAIN_HDR ");
		sql.append(" WHERE IS_DELETED = ? ");
		sql.append("   AND DRAFT_NUMBER = ? ");
		
		YearMonthVo yearMonthVo = null;
		try {
			yearMonthVo = commonJdbcTemplate.queryForObject(
					sql.toString(),
					new Object[] {
							FLAG.N_FLAG,
							StringUtils.defaultIfBlank(draftNumber, "")
					},
					new BeanPropertyRowMapper<>(YearMonthVo.class)
					);
		} catch (DataAccessException e) {
			logger.warn(e.getMessage());
			yearMonthVo = new YearMonthVo();
		}
		
		return yearMonthVo;
	}

	@Override
	public YearMonthVo findMonthStartByAnalysisNumber(String analysisNumber) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT YEAR_MONTH_START, YEAR_MONTH_END, MONTH_NUM AS MONTH_TOTAL ");
		sql.append("   ,TO_NUMBER(SUBSTR(YEAR_MONTH_START,5,2)) AS MONTH_START ");
		sql.append(" FROM TA_WORKSHEET_COND_MAIN_HDR ");
		sql.append(" WHERE IS_DELETED = ? ");
		sql.append("   AND ANALYSIS_NUMBER = ? ");
		
		YearMonthVo yearMonthVo = null;
		try {
			yearMonthVo = commonJdbcTemplate.queryForObject(
				sql.toString(),
				new Object[] {
					FLAG.N_FLAG,
					StringUtils.defaultIfBlank(analysisNumber, "")
				},
				new BeanPropertyRowMapper<>(YearMonthVo.class)
			);
		} catch (DataAccessException e) {
			logger.warn(e.getMessage());
			yearMonthVo = new YearMonthVo();
		}
		
		return yearMonthVo;
	}

}
