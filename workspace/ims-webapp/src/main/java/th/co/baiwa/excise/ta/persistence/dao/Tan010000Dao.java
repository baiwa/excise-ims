package th.co.baiwa.excise.ta.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.constant.ExciseConstants.TSL;
import th.co.baiwa.excise.ta.persistence.vo.Tan010000FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Tan010000Vo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class Tan010000Dao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String SQL_SEARCH = " SELECT *  " + 
			"FROM TA_YEAR_PLAN p  " + 
			"WHERE ANALYSIS_NUMBER =  " + 
			"  (SELECT MAX(ANALYSIS_NUMBER)  " + 
			"  FROM TA_YEAR_PLAN p  " + 
			"  WHERE P.CREATED_BY = ?  " + 
			"  ) ";

	public List<Tan010000Vo> search(Tan010000FormVo formVo) {
		StringBuilder sql = new StringBuilder(SQL_SEARCH);
		List<Object> params = new ArrayList<>();
		params.add(StringUtils.trim(formVo.getUser()));

		if (StringUtils.isNotBlank(formVo.getExciseId())) {
			sql.append(" AND EXCISE_ID = ? ");
			params.add(StringUtils.trim(formVo.getExciseId()));
		}
		if (StringUtils.isNotBlank(formVo.getStatus())) {
			sql.append(" AND STATUS = ? ");
			params.add(StringUtils.trim(formVo.getStatus()));
		}
		if (StringUtils.isNotBlank(formVo.getFiscalYear())) {
			sql.append(" AND extract(year from p.CREATED_DATE) = ? "); 
			params.add(Integer.valueOf(formVo.getFiscalYear())-543);
		}
		
//		sql.append(" AND ANALYSIS_NUMBER = ( ");
//		sql.append(" SELECT MAX(ANALYSIS_NUMBER) ");
//		sql.append(" FROM TA_YEAR_PLAN ");
//		sql.append(" ) ");

		String sqlLimit = OracleUtils.limitForDataTable(sql, formVo.getStart(), formVo.getLength());
		List<Tan010000Vo> list = jdbcTemplate.query(sqlLimit, params.toArray(), Tan010000RowMapper);
		return list;
	}
	
	public long count(Tan010000FormVo formVo) {
		StringBuilder sql = new StringBuilder(SQL_SEARCH);
		List<Object> params = new ArrayList<>();
		params.add(StringUtils.trim(formVo.getUser()));

		if (StringUtils.isNotBlank(formVo.getExciseId())) {
			sql.append(" AND EXCISE_ID = ? ");
			params.add(StringUtils.trim(formVo.getExciseId()));
		}
		if (StringUtils.isNotBlank(formVo.getStatus())) {
			sql.append(" AND STATUS = ? ");
			params.add(StringUtils.trim(formVo.getStatus()));
		}
		if (StringUtils.isNotBlank(formVo.getFiscalYear())) {
			sql.append(" AND extract(year from p.CREATED_DATE) = ? "); 
			params.add(Integer.valueOf(formVo.getFiscalYear())-543);
		}
//		sql.append(" AND ANALYSIS_NUMBER = ( ");
//		sql.append(" SELECT MAX(ANALYSIS_NUMBER) ");
//		sql.append(" FROM TA_YEAR_PLAN ");
//		sql.append(" ) ");
		
		String countSql = OracleUtils.countForDatatable(sql);
		long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}

	private RowMapper<Tan010000Vo> Tan010000RowMapper = new RowMapper<Tan010000Vo>() {
		@Override
		public Tan010000Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Tan010000Vo vo = new Tan010000Vo();
			vo.setTaYearPlanId(rs.getBigDecimal("TA_YEAR_PLAN_ID"));
			vo.setUserId(rs.getString("USER_ID"));
			vo.setExciseId(rs.getString("EXCISE_ID"));
			vo.setCompanyName(rs.getString("COMPANY_NAME"));
			vo.setCompanyAddress(rs.getString("COMPANY_ADDRESS"));
			vo.setExciseArea(rs.getString("EXCISE_AREA"));
			vo.setExciseSubArea(rs.getString("EXCISE_SUB_AREA"));
			vo.setProduct(rs.getString("PRODUCT"));
			vo.setRiskType(rs.getString("RISK_TYPE"));
			vo.setFlag(rs.getString("FLAG"));
			vo.setAnalysisNumber(rs.getString("ANALYSIS_NUMBER"));

			if (TSL.STATUS.RISK_1_CODE.equals(vo.getRiskType())) {
				vo.setRiskTypeDesc(TSL.STATUS.RISK_1_DESC);
			}
			if (TSL.STATUS.RISK_2_CODE.equals(vo.getRiskType())) {
				vo.setRiskTypeDesc(TSL.STATUS.RISK_2_DESC);
			}
			if (TSL.STATUS.RISK_3_CODE.equals(vo.getRiskType())) {
				vo.setRiskTypeDesc(TSL.STATUS.RISK_3_DESC);
			}
			if (TSL.STATUS.FLAG_1_CODE.equals(vo.getFlag())) {
				vo.setFlagDesc(TSL.STATUS.FLAG_1_DESC);
			}
			if (TSL.STATUS.FLAG_2_CODE.equals(vo.getFlag())) {
				vo.setFlagDesc(TSL.STATUS.FLAG_2_DESC);
			}
			
			vo.setStartDate(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("START_DATE")));
			vo.setEndDate(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("END_DATE")));
			vo.setStatus(rs.getString("STATUS"));
			vo.setPatternd(rs.getString("PATTERND"));
	
			
			return vo;
		}
	};
	
	public void editTan01 (Tan010000FormVo formVo) {
    	jdbcTemplate.update(" UPDATE TA_YEAR_PLAN SET " + 
    		    "START_DATE=?," +
    		    "END_DATE=?," +
    			"PATTERND=? WHERE TA_YEAR_PLAN_ID = ?",new Object[] {
    					DateConstant.convertStrDDMMYYYYToDate(formVo.getStartDate()),
    					DateConstant.convertStrDDMMYYYYToDate(formVo.getEndDate()),
    		    		formVo.getPatternd(),
    					formVo.getId()});
    	}

}
