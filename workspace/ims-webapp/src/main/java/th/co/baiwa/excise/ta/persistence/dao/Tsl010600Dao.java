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

import th.co.baiwa.excise.constant.ExciseConstants.TSL;
import th.co.baiwa.excise.ta.persistence.vo.Tsl010600FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Tsl010600Vo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class Tsl010600Dao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String SQL_SEARCH = " SELECT * FROM TA_YEAR_PLAN WHERE 1=1 AND USER_ID = ? ";

	public List<Tsl010600Vo> search(Tsl010600FormVo tsl010600FormVo) {
		StringBuilder sql = new StringBuilder(SQL_SEARCH);
		List<Object> params = new ArrayList<>();
		params.add(StringUtils.trim(tsl010600FormVo.getUser()));

		if (StringUtils.isNotBlank(tsl010600FormVo.getExciseId())) {
			sql.append(" AND EXCISE_ID = ? ");
			params.add(StringUtils.trim(tsl010600FormVo.getExciseId()));
		}
		
		sql.append(" AND ANALYSIS_NUMBER = ( ");
		sql.append(" SELECT MAX(ANALYSIS_NUMBER) ");
		sql.append(" FROM TA_YEAR_PLAN ");
		sql.append(" ) ");

		String sqlLimit = OracleUtils.limitForDataTable(sql, tsl010600FormVo.getStart(), tsl010600FormVo.getLength());
		List<Tsl010600Vo> list = jdbcTemplate.query(sqlLimit, params.toArray(), Tsl010300RowMapper);
		return list;
	}
	
	public long count(Tsl010600FormVo tsl010600FormVo) {
		StringBuilder sql = new StringBuilder(SQL_SEARCH);
		List<Object> params = new ArrayList<>();
		params.add(StringUtils.trim(tsl010600FormVo.getUser()));

		if (StringUtils.isNotBlank(tsl010600FormVo.getExciseId())) {
			sql.append(" AND EXCISE_ID = ? ");
			params.add(StringUtils.trim(tsl010600FormVo.getExciseId()));
		}
		
		sql.append(" AND ANALYSIS_NUMBER = ( ");
		sql.append(" SELECT MAX(ANALYSIS_NUMBER) ");
		sql.append(" FROM TA_YEAR_PLAN ");
		sql.append(" ) ");
		
		String countSql = OracleUtils.countForDatatable(sql);
		long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}

	private RowMapper<Tsl010600Vo> Tsl010300RowMapper = new RowMapper<Tsl010600Vo>() {
		@Override
		public Tsl010600Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Tsl010600Vo vo = new Tsl010600Vo();
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
			vo.setAnalysisNumber(rs.getInt("ANALYSIS_NUMBER"));

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
			
			return vo;
		}
	};

}
