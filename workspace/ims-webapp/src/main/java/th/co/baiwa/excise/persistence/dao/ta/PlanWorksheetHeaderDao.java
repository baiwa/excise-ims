package th.co.baiwa.excise.persistence.dao.ta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.domain.ta.PlanWorksheetVo;
import th.co.baiwa.excise.domain.ta.RequestFilterMapping;
import th.co.baiwa.excise.entity.ta.PlanWorksheetHeader;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class PlanWorksheetHeaderDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private Logger logger = LoggerFactory.getLogger(PlanWorksheetHeaderDao.class);

	public String getAnalysNumber() {
		String sql = "SELECT TO_CHAR(ANALYS_NUMBER_SEQ.nextval, '00000') as SEQ FROM DUAL";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		String analysNumber = map != null ? (String) map.get("SEQ") : null;
		return analysNumber.trim();
	}
	public String getWorksheetNumber() {
		String sql = "SELECT TO_CHAR(WORKSHEET_NUMBER_SEQ.nextval, '00000') as SEQ FROM DUAL";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		String analysNumber = map != null ? (String) map.get("SEQ") : null;
		return analysNumber.trim();
	}

	public List<PlanWorksheetHeader> queryPlanWorksheetHeaderCriteria(PlanWorksheetHeader criteria) {

		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" select * from TA_PLAN_WORK_SHEET_HEADER ");
		sql.append(" where 1 = 1 ");

		if (StringUtils.isNotBlank(criteria.getAnalysNumber())) {
			sql.append(" and ANALYS_NUMBER = ? ");
			valueList.add(criteria.getAnalysNumber());

		}

		List<PlanWorksheetHeader> planWorksheetHeaderList = jdbcTemplate.query(sql.toString(), valueList.toArray(),
				fieldMapping);
		return planWorksheetHeaderList;

	}

	public void insertPlanWorksheetHeader(PlanWorksheetHeader value) {
		// inti SQL for insert to database
		StringBuilder sql = new StringBuilder(
				" INSERT INTO TA_PLAN_WORK_SHEET_HEADER (WORK_SHEET_HEADER_ID,ANALYS_NUMBER,EXCISE_ID,COMPANY_NAME,FACTORY_NAME,FACTORY_ADDRESS,EXCISE_OWNER_AREA,PRODUCT_TYPE,EXCISE_OWNER_AREA_1,TOTAL_AMOUNT,PERCENTAGE,TOTAL_MONTH,DECIDE_TYPE,FLAG,CREATED_BY,CREATED_DATETIME,UPDATE_BY,UPDATE_DATETIME,FIRST_MONTH,LAST_MONTH)");
		sql.append(" values(TA_PLAN_WS_HEADER_SEQ.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		// for to set Object
		jdbcTemplate.update(sql.toString(), planWorksheetHeaderToArrayObject(value));
	}

	public int updatePlanWorksheetHeaderFlag(String flag, String analysNum, String exciseId) {
		String sql = " UPDATE TA_PLAN_WORK_SHEET_HEADER SET FLAG = ? WHERE ANALYS_NUMBER = ? AND EXCISE_ID = ? ";
		return jdbcTemplate.update(sql, new Object[] { flag, analysNum, exciseId });
	}

	private Object[] planWorksheetHeaderToArrayObject(PlanWorksheetHeader value) {

		List<Object> valueList = new ArrayList<Object>();
		if (value != null) {
			valueList.add(value.getAnalysNumber());
			valueList.add(value.getExciseId());
			valueList.add(value.getCompanyName());
			valueList.add(value.getFactoryName());
			valueList.add(value.getFactoryAddress());
			valueList.add(value.getExciseOwnerArea());
			valueList.add(value.getProductType());
			valueList.add(value.getExciseOwnerArea1());
			valueList.add(value.getTotalAmount());
			valueList.add(value.getPercentage());
			valueList.add(value.getTotalMonth());
			valueList.add(value.getDecideType());
			valueList.add(value.getFlag());
			valueList.add(value.getCreateBy());
			valueList.add(value.getCreateDatetime());
			valueList.add(value.getUpdateBy());
			valueList.add(value.getUpdateDatetime());
			valueList.add(value.getFirstMonth());
			valueList.add(value.getLastMonth());
		}
		return valueList.toArray();
	}

	private RowMapper<PlanWorksheetHeader> fieldMapping = new RowMapper<PlanWorksheetHeader>() {
		@Override
		public PlanWorksheetHeader mapRow(ResultSet rs, int arg1) throws SQLException {
			PlanWorksheetHeader header = new PlanWorksheetHeader();
			header.setWorksheetHeaderId(rs.getBigDecimal("WORK_SHEET_HEADER_ID"));
			header.setWorkSheetNumber(rs.getString("WORK_SHEET_NUMBER"));
			header.setAnalysNumber(rs.getString("ANALYS_NUMBER"));
			header.setExciseId(rs.getString("EXCISE_ID"));
			header.setCompanyName(rs.getString("COMPANY_NAME"));
			header.setFactoryName(rs.getString("FACTORY_NAME"));
			header.setFactoryAddress(rs.getString("FACTORY_ADDRESS"));
			header.setExciseOwnerArea(rs.getString("EXCISE_OWNER_AREA"));
			header.setProductType(rs.getString("PRODUCT_TYPE"));
			header.setExciseOwnerArea1(rs.getString("EXCISE_OWNER_AREA_1"));
			header.setTotalAmount(rs.getBigDecimal("TOTAL_AMOUNT"));
			header.setPercentage(rs.getBigDecimal("PERCENTAGE"));
			header.setTotalMonth(rs.getBigDecimal("TOTAL_MONTH"));
			header.setDecideType(rs.getString("DECIDE_TYPE"));
			header.setFlag(rs.getString("FLAG"));
			header.setFirstMonth(rs.getBigDecimal("FIRST_MONTH"));
			header.setLastMonth(rs.getBigDecimal("LAST_MONTH"));
			header.setCreateBy(rs.getString("CREATED_BY"));
			header.setCreateDatetime(rs.getDate("CREATED_DATETIME"));
			header.setUpdateBy(rs.getString("UPDATE_BY"));
			header.setUpdateDatetime(rs.getTime("UPDATE_DATETIME"));
			return header;
		}
	};

	public List<PlanWorksheetVo> queryPlanWorksheetHeaderDetil(String analysNumber, int start, int length) {

		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from TA_PLAN_WORK_SHEET_HEADER H ");
		sql.append(" inner join TA_PLAN_WORK_SHEET_DETAIL D ");
		sql.append(" on D.EXCISE_ID = H.EXCISE_ID and D.ANALYS_NUMBER = H.ANALYS_NUMBER ");
		sql.append(" where H.ANALYS_NUMBER = ? ");
		sql.append(" order by H.WORK_SHEET_HEADER_ID ");
		valueList.add(analysNumber);
		List<PlanWorksheetVo> planWorksheetHeaderList = jdbcTemplate.query(
				OracleUtils.limitForDataTable(sql, start, length), valueList.toArray(), fieldMappingPlanWorksheetVo);
		return planWorksheetHeaderList;

	}
	
	public String queryWorkSheetNumber(String analysNumber) {
		String sql = " SELECT DISTINCT H.WORK_SHEET_NUMBER FROM TA_PLAN_WORK_SHEET_HEADER H WHERE H.WORK_SHEET_NUMBER IS NOT NULL AND H.ANALYS_NUMBER = ? ";
		List<Object> valueList = new ArrayList<Object>();
		valueList.add(analysNumber);
		List<String> result = jdbcTemplate.query(sql, valueList.toArray(), new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});
		return ( result == null || result.size() == 0 ) ? "" : result.get(0);
	}

	public List<PlanWorksheetHeader> queryPlanWorksheetHeader(RequestFilterMapping vo) {
		logger.debug("queryPlanWorksheetHeader");
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from TA_PLAN_WORK_SHEET_HEADER H ");
		sql.append(" where H.ANALYS_NUMBER = ? ");
		valueList.add(vo.getAnalysNumber());
		
		if (BeanUtils.isNotEmpty(vo.getProductType())) {
			sql.append(" AND H.PRODUCT_TYPE = ? ");
			valueList.add(vo.getProductType());
		}
		if (BeanUtils.isNotEmpty(vo.getFlag()) && !"NOT N".equals(vo.getFlag())) {
			sql.append(" AND H.FLAG = ? ");
			valueList.add(vo.getFlag());
		} else {
			sql.append(" AND H.FLAG != 'N' ");
		}

		if (BeanUtils.isNotEmpty(vo.getNum1()) && BeanUtils.isNotEmpty(vo.getNum2())
				&& BeanUtils.isNotEmpty(vo.getPercent1()) && BeanUtils.isNotEmpty(vo.getPercent2())) {
			String[] monthFrom = vo.getNum1().split(",");
			String[] monthTo = vo.getNum2().split(",");
			String[] percentFrom = vo.getPercent1().split(",");
			String[] percentTo = vo.getPercent2().split(",");
			if (BeanUtils.isEmpty(vo.getIndexFilter())) {
				for (int i = 0; i < monthFrom.length; i++) {
					if (i == 0) {
						sql.append(" AND ( ");
					}
					if (!"0".equals(monthFrom[i]) || !"0".equals(monthTo[i]) || !"0.00".equals(percentFrom[i])
							|| !"0.00".equals(percentTo[i])) {
						if (i == 0) {
							sql.append(" (( TOTAL_MONTH >= ?  AND TOTAL_MONTH <= ? ) AND (PERCENTAGE >= ? AND PERCENTAGE <= ?)) ");
						} else {
							sql.append("OR (( TOTAL_MONTH >= ?  AND TOTAL_MONTH <= ? ) AND (PERCENTAGE >= ? AND PERCENTAGE <= ?)) ");
						}
						valueList.add(monthFrom[i]);
						valueList.add(monthTo[i]);
						valueList.add(percentFrom[i]);
						valueList.add(percentTo[i]);
					}
					if (i == monthFrom.length - 1) {
						sql.append(" ) ");
					}
				}
			}else {
				sql.append(" AND ( ");
				sql.append(" (( TOTAL_MONTH >= ?  AND TOTAL_MONTH <= ? ) AND (PERCENTAGE >= ? AND PERCENTAGE <= ?)) ");
				sql.append(" ) ");
				int indexFilter = Integer.parseInt(vo.getIndexFilter());
				valueList.add(monthFrom[indexFilter]);
				valueList.add(monthTo[indexFilter]);
				valueList.add(percentFrom[indexFilter]);
				valueList.add(percentTo[indexFilter]);
			}

		}

		sql.append(" order by H.WORK_SHEET_HEADER_ID ");
		logger.info(sql.toString());
		List<PlanWorksheetHeader> planWorksheetHeaderList = jdbcTemplate.query(
				OracleUtils.limitForDataTable(sql, vo.getStart(), vo.getLength()), valueList.toArray(), fieldMapping);
		return planWorksheetHeaderList;

	}
	
	public List<PlanWorksheetHeader> queryPlanWorksheetHeaderFullDataNoPaging(RequestFilterMapping vo) {
		logger.debug("queryPlanWorksheetHeader");
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from TA_PLAN_WORK_SHEET_HEADER H ");
		sql.append(" where H.ANALYS_NUMBER = ? ");
		valueList.add(vo.getAnalysNumber());
		
		if (BeanUtils.isNotEmpty(vo.getProductType())) {
			sql.append(" AND H.PRODUCT_TYPE = ? ");
			valueList.add(vo.getProductType());
		}
		if (BeanUtils.isNotEmpty(vo.getFlag()) && !"NOT N".equals(vo.getFlag())) {
			sql.append(" AND H.FLAG = ? ");
			valueList.add(vo.getFlag());
		} else {
			sql.append(" AND H.FLAG != 'N' ");
		}
		
		if (BeanUtils.isNotEmpty(vo.getNum1()) && BeanUtils.isNotEmpty(vo.getNum2())
				&& BeanUtils.isNotEmpty(vo.getPercent1()) && BeanUtils.isNotEmpty(vo.getPercent2())) {
			String[] monthFrom = vo.getNum1().split(",");
			String[] monthTo = vo.getNum2().split(",");
			String[] percentFrom = vo.getPercent1().split(",");
			String[] percentTo = vo.getPercent2().split(",");
			if (BeanUtils.isEmpty(vo.getIndexFilter())) {
				for (int i = 0; i < monthFrom.length; i++) {
					if (i == 0) {
						sql.append(" AND ( ");
					}
					if (!"0".equals(monthFrom[i]) || !"0".equals(monthTo[i]) || !"0.00".equals(percentFrom[i])
							|| !"0.00".equals(percentTo[i])) {
						if (i == 0) {
							sql.append(" (( TOTAL_MONTH >= ?  AND TOTAL_MONTH <= ? ) AND (PERCENTAGE >= ? AND PERCENTAGE <= ?)) ");
						} else {
							sql.append("OR (( TOTAL_MONTH >= ?  AND TOTAL_MONTH <= ? ) AND (PERCENTAGE >= ? AND PERCENTAGE <= ?)) ");
						}
						valueList.add(monthFrom[i]);
						valueList.add(monthTo[i]);
						valueList.add(percentFrom[i]);
						valueList.add(percentTo[i]);
					}
					if (i == monthFrom.length - 1) {
						sql.append(" ) ");
					}
				}
			}else {
				sql.append(" AND ( ");
				sql.append(" (( TOTAL_MONTH >= ?  AND TOTAL_MONTH <= ? ) AND (PERCENTAGE >= ? AND PERCENTAGE <= ?)) ");
				sql.append(" ) ");
				int indexFilter = Integer.parseInt(vo.getIndexFilter());
				valueList.add(monthFrom[indexFilter]);
				valueList.add(monthTo[indexFilter]);
				valueList.add(percentFrom[indexFilter]);
				valueList.add(percentTo[indexFilter]);
			}
			
		}
		
		sql.append(" order by H.WORK_SHEET_HEADER_ID ");
		logger.info(sql.toString());
		List<PlanWorksheetHeader> planWorksheetHeaderList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMapping);
		return planWorksheetHeaderList;
		
	}

	public long queryCountByPlanWorksheetHeader(RequestFilterMapping vo) {
		logger.debug("queryCountByPlanWorksheetHeader");
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from TA_PLAN_WORK_SHEET_HEADER H ");
		sql.append(" where H.ANALYS_NUMBER = ? ");
		valueList.add(vo.getAnalysNumber());

		if (BeanUtils.isNotEmpty(vo.getProductType())) {
			sql.append(" AND H.PRODUCT_TYPE = ? ");
			valueList.add(vo.getProductType());
		}
		if (BeanUtils.isNotEmpty(vo.getFlag()) && !"NOT N".equals(vo.getFlag())) {
			sql.append(" AND H.FLAG = ? ");
			valueList.add(vo.getFlag());
		} else {
			sql.append(" AND H.FLAG != 'N' ");
		}

		if (BeanUtils.isNotEmpty(vo.getNum1()) && BeanUtils.isNotEmpty(vo.getNum2())
				&& BeanUtils.isNotEmpty(vo.getPercent1()) && BeanUtils.isNotEmpty(vo.getPercent2())) {
			String[] monthFrom = vo.getNum1().split(",");
			String[] monthTo = vo.getNum2().split(",");
			String[] percentFrom = vo.getPercent1().split(",");
			String[] percentTo = vo.getPercent2().split(",");
			if (BeanUtils.isEmpty(vo.getIndexFilter())) {
				for (int i = 0; i < monthFrom.length; i++) {
					if (i == 0) {
						sql.append(" AND ( ");
					}
					if (!"0".equals(monthFrom[i]) || !"0".equals(monthTo[i]) || !"0.00".equals(percentFrom[i])
							|| !"0.00".equals(percentTo[i])) {
						if (i == 0) {
							sql.append(" (( TOTAL_MONTH >= ?  AND TOTAL_MONTH <= ? ) AND (PERCENTAGE >= ? AND PERCENTAGE <= ?)) ");
						} else {
							sql.append("OR (( TOTAL_MONTH >= ?  AND TOTAL_MONTH <= ? ) AND (PERCENTAGE >= ? AND PERCENTAGE <= ?)) ");
						}
						valueList.add(monthFrom[i]);
						valueList.add(monthTo[i]);
						valueList.add(percentFrom[i]);
						valueList.add(percentTo[i]);
					}
					if (i == monthFrom.length - 1) {
						sql.append(" ) ");
					}
				}
			}else {
				sql.append(" AND ( ");
				sql.append(" (( TOTAL_MONTH >= ?  AND TOTAL_MONTH <= ? ) AND (PERCENTAGE >= ? AND PERCENTAGE <= ?)) ");
				sql.append(" ) ");
				int indexFilter = Integer.parseInt(vo.getIndexFilter());
				valueList.add(monthFrom[indexFilter]);
				valueList.add(monthTo[indexFilter]);
				valueList.add(percentFrom[indexFilter]);
				valueList.add(percentTo[indexFilter]);
			}

		}

		long count = jdbcTemplate.queryForObject(OracleUtils.countForDatatable(sql.toString()), valueList.toArray(),
				Long.class);
		return count;
	}

	private RowMapper<PlanWorksheetVo> fieldMappingPlanWorksheetVo = new RowMapper<PlanWorksheetVo>() {
		@Override
		public PlanWorksheetVo mapRow(ResultSet rs, int arg1) throws SQLException {
			PlanWorksheetVo header = new PlanWorksheetVo();
			header.setWorksheetHeaderId(rs.getBigDecimal("WORK_SHEET_HEADER_ID"));
			header.setAnalysNumber(rs.getString("ANALYS_NUMBER"));
			header.setExciseId(rs.getString("EXCISE_ID"));
			header.setCompanyName(rs.getString("COMPANY_NAME"));
			header.setFactoryName(rs.getString("FACTORY_NAME"));
			header.setFactoryAddress(rs.getString("FACTORY_ADDRESS"));
			header.setExciseOwnerArea(rs.getString("EXCISE_OWNER_AREA"));
			header.setProductType(rs.getString("PRODUCT_TYPE"));
			header.setExciseOwnerArea1(rs.getString("EXCISE_OWNER_AREA_1"));
			header.setTotalAmount(rs.getBigDecimal("TOTAL_AMOUNT"));
			header.setPercentage(rs.getBigDecimal("PERCENTAGE"));
			header.setTotalMonth(rs.getBigDecimal("TOTAL_MONTH"));
			header.setDecideType(rs.getString("DECIDE_TYPE"));
			header.setFlag(rs.getString("FLAG"));
			header.setFirstMonth(rs.getBigDecimal("FIRST_MONTH"));
			header.setLastMonth(rs.getBigDecimal("LAST_MONTH"));
			header.setMonth(rs.getString("MONTH"));
			header.setYear(rs.getString("YEAR"));
			header.setAmount(rs.getBigDecimal("AMOUNT"));

			return header;
		}
	};

	public List<String> queryAnalysNumberFromHeader() {
		String sql = "select DISTINCT ANALYS_NUMBER from TA_PLAN_WORK_SHEET_HEADER order by ANALYS_NUMBER desc";
		List<String> analysList = jdbcTemplate.query(sql, new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});
		return analysList;
	}

	private RowMapper<String> fieldMappingAnalysNumber = new RowMapper<String>() {
		@Override
		public String mapRow(ResultSet rs, int arg1) throws SQLException {

			return rs.getString("ANALYS_NUMBER");
		}
	};

	public List<String> getStartDateAndEndDateFromAnalysNumber(String analysNumber) {
		StringBuilder sql = new StringBuilder(" SELECT DISTINCT D.MONTH ");
		sql.append(" FROM TA_PLAN_WORK_SHEET_HEADER H ");
		sql.append(" INNER JOIN TA_PLAN_WORK_SHEET_DETAIL D ");
		sql.append(" ON H.ANALYS_NUMBER = D.ANALYS_NUMBER ");
		sql.append(" AND H.EXCISE_ID = D.EXCISE_ID ");
		sql.append(" WHERE H.ANALYS_NUMBER = ? ");
		List<String> listMonth = jdbcTemplate.query(sql.toString(), new Object[] { analysNumber },
				new RowMapper<String>() {
					public String mapRow(ResultSet rs, int rowNum) throws SQLException {
						return rs.getString(1);
					}
				});
		return listMonth;
	}
	
	public void updateStatusFlg(RequestFilterMapping vo) {
		logger.info("AnalysNumber: ", vo.getAnalysNumber());
		StringBuilder sql = new StringBuilder("UPDATE TA_PLAN_WORK_SHEET_HEADER SET FLAG = ? ,WORK_SHEET_NUMBER = ? ");
		sql.append(" Where ANALYS_NUMBER = ? AND ");
		sql.append(" (( TOTAL_MONTH >= ?  AND TOTAL_MONTH <= ? ) AND (PERCENTAGE >= ? AND PERCENTAGE <= ?)) ");
		if (BeanUtils.isNotEmpty(vo.getNum1()) && BeanUtils.isNotEmpty(vo.getNum2())&& BeanUtils.isNotEmpty(vo.getPercent1()) && BeanUtils.isNotEmpty(vo.getPercent2())) {
			String[] monthFrom = vo.getNum1().split(",");
			String[] monthTo = vo.getNum2().split(",");
			String[] percentFrom = vo.getPercent1().split(",");
			String[] percentTo = vo.getPercent2().split(",");
			for (int i = 0; i < monthFrom.length; i++) {
				if (!"0".equals(monthFrom[i]) || !"0".equals(monthTo[i]) || !"0.00".equals(percentFrom[i]) || !"0.00".equals(percentTo[i])) {
					List<Object> objList = new ArrayList<Object>();
					objList.add("N"+(i+1));
					objList.add(vo.getWorkShheetNumber());
					objList.add(vo.getAnalysNumber());
					objList.add(monthFrom[i]);
					objList.add(monthTo[i]);
					objList.add(percentFrom[i]);
					objList.add(percentTo[i]);
					jdbcTemplate.update(sql.toString(), objList.toArray());
				}
				
			}
			
		}
		
	}

}
