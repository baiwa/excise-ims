package th.go.excise.ims.ta.persistence.repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.ta.persistence.entity.TaWsInc8000M;
import th.go.excise.ims.ta.vo.AnalysisFormVo;

public class TaWsInc8000MRepositoryImpl implements TaWsInc8000MRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(TaWsInc8000MRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public Map<String, List<TaWsInc8000M>> findByMonthRange(String startMonth, String endMonth) {
		logger.info("findByMonthRange startMonth={}, endMonth={}", startMonth, endMonth);

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT * FROM ( ");
		sql.append("   SELECT I.*, I.TAX_YEAR || DECODE(LENGTH(I.TAX_MONTH), 2 ,I.TAX_MONTH , '0' || I.TAX_MONTH) YEAR_MONTH ");
		sql.append("   FROM TA_WS_INC8000_M I ");
		sql.append(" ) INC ");
		sql.append(" WHERE INC.IS_DELETED = 'N' ");
		sql.append("   AND INC.YEAR_MONTH >= ? ");
		sql.append("   AND INC.YEAR_MONTH <= ? ");
		sql.append(" ORDER BY INC.YEAR_MONTH ");

		List<Object> paramList = new ArrayList<>();
		paramList.add(startMonth);
		paramList.add(endMonth);

		Map<String, List<TaWsInc8000M>> wsInc8000MMap = commonJdbcTemplate.query(sql.toString(), paramList.toArray(), new ResultSetExtractor<Map<String, List<TaWsInc8000M>>>() {
			public Map<String, List<TaWsInc8000M>> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<String, List<TaWsInc8000M>> dataMap = new HashMap<>();
				List<TaWsInc8000M> dataList = null;
				while (rs.next()) {
					dataList = dataMap.get(rs.getString(TaWsInc8000M.Field.NEW_REG_ID));
					if (dataList == null) {
						dataList = new ArrayList<>();
					}
					TaWsInc8000M taWsInc8000M = new TaWsInc8000M();
					taWsInc8000M.setNewRegId(rs.getString(TaWsInc8000M.Field.WS_INC8000_M_ID));
					taWsInc8000M.setTaxAmount(rs.getBigDecimal(TaWsInc8000M.Field.TAX_AMOUNT));
					taWsInc8000M.setTaxYear(rs.getString(TaWsInc8000M.Field.TAX_YEAR));
					taWsInc8000M.setTaxMonth(rs.getString(TaWsInc8000M.Field.TAX_MONTH));
					dataList.add(taWsInc8000M);
					dataMap.put(rs.getString(TaWsInc8000M.Field.NEW_REG_ID), dataList);
				}
				return dataMap;
			}
		});

		return wsInc8000MMap;
	}
	
	@Override
	public Map<String, Map<String, BigDecimal>> findByMonthRangeDuty(String officeCode, String startMonth, String endMonth) {
		logger.info("findByMonthRange officeCode={}, startMonth={}, endMonth={}", officeCode, startMonth, endMonth);

		StringBuilder sql = new StringBuilder();
		List<Object> paramList = new ArrayList<>();
		
		sql.append(" SELECT INC.* FROM ( ");
		sql.append(" SELECT NEW_REG_ID, DUTY_CODE, TAX_AMOUNT, TAX_YEAR||LPAD(TAX_MONTH ,2 ,'0') AS YEAR_MONTH ");
		sql.append(" FROM TA_WS_INC8000_M ");
		sql.append(" WHERE IS_DELETED = 'N' ");
//		if (ApplicationCache.isCtrlDutyGroupByOfficeCode(officeCode)) {
//			sql.append("   AND DUTY_CODE IN (SELECT DUTY_GROUP_CODE FROM EXCISE_CTRL_DUTY WHERE IS_DELETED = 'N' AND RES_OFFCODE = ?) ");
//			paramList.add(officeCode);
//		} else {
//			List<String> dutyGroupIdList = ExciseUtils.getDutyGroupIdListByType(DUTY_GROUP_TYPE.PRODUCT, DUTY_GROUP_TYPE.SERVICE);
//			sql.append("   AND DUTY_CODE IN (" + StringUtils.repeat("?", ",", dutyGroupIdList.size()) + ") ");
//			paramList.addAll(dutyGroupIdList);
//		}
		sql.append(" ) INC ");
		sql.append(" WHERE 1=1 ");
		sql.append("   AND INC.YEAR_MONTH >= ? ");
		sql.append("   AND INC.YEAR_MONTH <= ? ");
		sql.append(" ORDER BY INC.NEW_REG_ID, INC.DUTY_CODE, INC.YEAR_MONTH ");

		paramList.add(startMonth);
		paramList.add(endMonth);
		
		//==> Check Tax , NetTax
		String value = ApplicationCache.getParamInfoByCode("TA_CONFIG", "INCOME_TYPE").getValue1();

		Map<String, Map<String, BigDecimal>> incfri8000MMap = commonJdbcTemplate.query(sql.toString(), paramList.toArray(), new ResultSetExtractor<Map<String, Map<String, BigDecimal>>>() {
			public Map<String, Map<String, BigDecimal>> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<String, Map<String, BigDecimal>> newRegIdMap = new HashMap<>();
				Map<String, BigDecimal> incomeMap = null;
				String mainKey = null;
				while (rs.next()) {
					if (rs.getString("NEW_REG_ID") == null) {
						continue;
					}
					mainKey = rs.getString("NEW_REG_ID") + "|" + rs.getString("DUTY_CODE");
					incomeMap = newRegIdMap.get(mainKey);
					if (incomeMap == null) {
						incomeMap = new HashMap<>();
					}
					if(value.equals("TAX")) {
						
						incomeMap.put(rs.getString("YEAR_MONTH"), rs.getBigDecimal("TAX_AMOUNT"));
					}else {
						
						incomeMap.put(rs.getString("YEAR_MONTH"), rs.getBigDecimal("NET_TAX_AMOUNT"));
					}
					newRegIdMap.put(mainKey, incomeMap);
				}
				return newRegIdMap;
			}
		});

		return incfri8000MMap;
	}

	private void buildByAnalyzeCompareOldYearQuery(StringBuilder sql, List<Object> params, AnalysisFormVo formVo) {
		sql.append(" SELECT * ");
		sql.append(" FROM TA_WS_INC8000_M ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		sql.append(" AND NEW_REG_ID = ? ");
		sql.append(" AND TAX_YEAR || LPAD(TAX_MONTH, 2, 0) ");
		sql.append(" BETWEEN ? AND ? ");
		params.add(formVo.getNewRegId());
		params.add(formVo.getStartDate());
		params.add(formVo.getEndDate());
	}

	public List<TaWsInc8000M> findByAnalyzeCompareOldYear(AnalysisFormVo formVo) {

		StringBuilder sql1 = new StringBuilder();
		List<Object> params1 = new ArrayList<>();

		buildByAnalyzeCompareOldYearQuery(sql1, params1, formVo);

		List<TaWsInc8000M> data = commonJdbcTemplate.query(sql1.toString(), params1.toArray(), taWsInc8000MRowMapper);
		return data;

	}

	private static final RowMapper<TaWsInc8000M> taWsInc8000MRowMapper = new RowMapper<TaWsInc8000M>() {
		public TaWsInc8000M mapRow(ResultSet rs, int rowNum) throws SQLException {
			TaWsInc8000M vo = new TaWsInc8000M();
			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			vo.setTaxAmount(rs.getBigDecimal("TAX_AMOUNT"));
			vo.setTaxMonth(rs.getString("TAX_MONTH"));
			vo.setTaxYear(rs.getString("TAX_YEAR"));
			vo.setWsInc8000MId(rs.getLong("WS_INC8000_M_ID"));
			return vo;
		}
	};

	private void buildAnalysisIncomeCompareLastMonthQuery(StringBuilder sql, List<Object> params, AnalysisFormVo formVo) {
		sql.append(" SELECT * ");
		sql.append(" FROM ta_ws_inc8000_m ");
		sql.append(" WHERE is_deleted = ? ");

		params.add(FLAG.N_FLAG);

		if (StringUtils.isNotBlank(formVo.getNewRegId())) {
			sql.append(" AND new_reg_id = ? ");
			params.add(formVo.getNewRegId());
		}

		sql.append(" AND tax_year || LPAD(tax_month, 2, 0) ");

		sql.append(" BETWEEN ? ");
		params.add(formVo.getStartDate());

		sql.append(" AND ? ");
		params.add(formVo.getEndDate());

	}

	@Override
	public List<TaWsInc8000M> findByAnalysisIncomeCompareLastMonth(AnalysisFormVo formVo) {
		logger.debug("findByCriteria formVo.newregid={}, formVo.startDate={}, formVo.endDate={}", formVo.getNewRegId(), formVo.getStartDate(), formVo.getEndDate());
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildAnalysisIncomeCompareLastMonthQuery(sql, params, formVo);

		List<TaWsInc8000M> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(), new RowMapper<TaWsInc8000M>() {

			@Override
			public TaWsInc8000M mapRow(ResultSet rs, int rowNum) throws SQLException {
				TaWsInc8000M taWsInc8000M = new TaWsInc8000M();
				taWsInc8000M.setNewRegId(rs.getString("new_reg_id"));
				taWsInc8000M.setTaxAmount(rs.getBigDecimal("tax_amount"));
				taWsInc8000M.setTaxMonth(rs.getString("tax_month"));
				taWsInc8000M.setTaxYear(rs.getString("tax_year"));
				taWsInc8000M.setWsInc8000MId(rs.getLong("ws_inc8000_m_id"));
				return taWsInc8000M;
			}

		});
		return datas;
	}

}
