package th.co.baiwa.excise.ta.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ta.persistence.entity.PlanWorksheetHeader;
import th.co.baiwa.excise.ta.persistence.entity.RequestFilterMapping;
import th.co.baiwa.excise.ta.persistence.vo.Ope041Vo;
import th.co.baiwa.excise.ta.persistence.vo.PlanWorksheetVo;
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
		if (StringUtils.isNotBlank(criteria.getExciseId())) {
			sql.append(" and EXCISE_ID = ? ");
			valueList.add(criteria.getExciseId());
		}
		if (StringUtils.isNotBlank(criteria.getMonthDate())) {
			sql.append(" and MONTH_DATE = ? ");
			valueList.add(criteria.getMonthDate());
		}
		if (criteria.getFullMonth() != null) {
			sql.append(" and FULL_MONTH = ? ");
			valueList.add(criteria.getFullMonth());
		}

		List<PlanWorksheetHeader> planWorksheetHeaderList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMapping);
		return planWorksheetHeaderList;

	}

	public void insertPlanWorksheetHeader(List<PlanWorksheetHeader> valueList) {
		// inti SQL for insert to database
		StringBuilder sql = new StringBuilder(
				" INSERT INTO TA_PLAN_WORK_SHEET_HEADER (WORK_SHEET_HEADER_ID,ANALYS_NUMBER,EXCISE_ID,COMPANY_NAME,FACTORY_NAME,FACTORY_ADDRESS,EXCISE_OWNER_AREA,PRODUCT_TYPE,EXCISE_OWNER_AREA_1,TOTAL_AMOUNT,PERCENTAGE,TOTAL_MONTH,DECIDE_TYPE,FLAG,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE,FIRST_MONTH,LAST_MONTH,MONTH_DATE,FULL_MONTH)");
		sql.append(" values(TA_PLAN_WS_HEADER_SEQ.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		// for to set Object
		List<Object[]> objArrayOfList = new ArrayList<Object[]>();
		for (PlanWorksheetHeader value : valueList) {
			objArrayOfList.add(planWorksheetHeaderToArrayObject(value));
		}
		jdbcTemplate.batchUpdate(sql.toString(), objArrayOfList);
	}

	public int updatePlanWorksheetHeaderFlag(String flag, String analysNum, String exciseId, String viewStatus ,String sector , String central) {

		List<Object> objList = new ArrayList<Object>();
		String sql = " UPDATE TA_PLAN_WORK_SHEET_HEADER " + "SET FLAG = ? ";
		objList.add(flag);
		if (BeanUtils.isNotEmpty(viewStatus)) {
			sql += " , VIEW_STATUS = ? ";
			objList.add(viewStatus);
		}
		if (BeanUtils.isNotEmpty(sector)) {
			sql += " , sector = ? ";
			objList.add(sector);
		}
		if (BeanUtils.isNotEmpty(central)) {
			sql += " , central = ? ";
			objList.add(central);
		}
		sql += "WHERE ANALYS_NUMBER = ? AND EXCISE_ID = ? ";
		objList.add(analysNum);
		objList.add(exciseId);
		return jdbcTemplate.update(sql, objList.toArray());
	}
	public int updateForCenterApproveStep1(List<String> exciseList , String analysNum) {
		
		List<Object> objList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE TA_PLAN_WORK_SHEET_HEADER H ");
		sql.append(" set H.VIEW_STATUS = 'S' ");
		sql.append(" where H.FLAG = 'S' ");
		sql.append(" and H.ANALYS_NUMBER = ? ");
		sql.append(" and H.VIEW_STATUS = 'C' ");
		objList.add(analysNum);
		if(BeanUtils.isNotEmpty(exciseList)) {
			sql.append(" And H.EXCISE_ID not in (");
			for (int i = 0; i < exciseList.size(); i++) {
				sql.append("?");
				if( i != exciseList.size()-1 ) {
					sql.append(",");
				}
				objList.add(exciseList.get(i));
			}
			sql.append(")");
			return jdbcTemplate.update(sql.toString(), objList.toArray());
		}
		return 0;
	}
	public int updateForCenterApproveStep2(List<String> exciseList , String analysNum) {
		
		List<Object> objList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE TA_PLAN_WORK_SHEET_HEADER H ");
		sql.append(" set H.FLAG = 'F' ,  ");
		sql.append("  H.CENTRAL = 'Y'   ");
		sql.append(" where H.FLAG = 'S' ");
		sql.append(" and H.ANALYS_NUMBER = ? ");
		sql.append(" and H.VIEW_STATUS = 'C' ");
		objList.add(analysNum);
		if(BeanUtils.isNotEmpty(exciseList)) {
			sql.append(" And H.EXCISE_ID in (");
			for (int i = 0; i < exciseList.size(); i++) {
				sql.append("?");
				if( i != exciseList.size()-1 ) {
					sql.append(",");
				}
				objList.add(exciseList.get(i));
			}
			sql.append(")");
			return jdbcTemplate.update(sql.toString(), objList.toArray());
		}
		return 0;
		
	}
	public int updateForSectorApprove(List<String> exciseList , String analysNum) {
		
		List<Object> objList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE TA_PLAN_WORK_SHEET_HEADER H ");
		sql.append(" set H.FLAG = 'F', ");
		sql.append("  H.SECTOR = 'Y' ");
		sql.append(" where H.FLAG = 'S' ");
		sql.append(" and H.ANALYS_NUMBER = ? ");
		sql.append(" and H.VIEW_STATUS = 'S' ");
		objList.add(analysNum);
		if(BeanUtils.isNotEmpty(exciseList)) {
			sql.append(" And H.EXCISE_ID in (");
			for (int i = 0; i < exciseList.size(); i++) {
				sql.append("?");
				if( i != exciseList.size()-1 ) {
					sql.append(",");
				}
				objList.add(exciseList.get(i));
			}
			sql.append(")");
			return jdbcTemplate.update(sql.toString(), objList.toArray());
		}
		return 0;
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
			valueList.add(value.getCreatedBy());
			valueList.add(value.getCreatedDate());
			valueList.add(value.getUpdatedBy());
			valueList.add(value.getUpdatedDate());
			valueList.add(value.getFirstMonth());
			valueList.add(value.getLastMonth());
			valueList.add(value.getMonthDate());
			valueList.add(value.getFullMonth());
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
			header.setViewStatus(rs.getString("VIEW_STATUS"));
			header.setFirstMonth(rs.getBigDecimal("FIRST_MONTH"));
			header.setLastMonth(rs.getBigDecimal("LAST_MONTH"));
			header.setCreatedBy(rs.getString("CREATED_BY"));
			header.setCreatedDate(rs.getDate("CREATED_DATE"));
			header.setUpdatedBy(rs.getString("UPDATED_BY"));
			header.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			header.setFullMonth(rs.getBigDecimal("FULL_MONTH"));
			header.setMonthDate(rs.getString("MONTH_DATE"));
			header.setCentral(rs.getString("CENTRAL"));
			header.setSector(rs.getString("SECTOR"));
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
		List<PlanWorksheetVo> planWorksheetHeaderList = jdbcTemplate.query(OracleUtils.limitForDataTable(sql, start, length), valueList.toArray(), fieldMappingPlanWorksheetVo);
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
		return (result == null || result.size() == 0) ? "" : result.get(0);
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

		if (BeanUtils.isNotEmpty(vo.getSector())) {
			sql.append(" AND H.EXCISE_OWNER_AREA_1 = ? ");
			valueList.add(vo.getSector());
		}
		if (BeanUtils.isNotEmpty(vo.getViewStatus())) {
			sql.append(" AND H.VIEW_STATUS = ? ");
			valueList.add(vo.getViewStatus());
		}

		if (BeanUtils.isNotEmpty(vo.getFlag()) && !"NOT N".equals(vo.getFlag())) {
			sql.append(" AND H.FLAG = ? ");
			valueList.add(vo.getFlag());
		} else {
			sql.append(" AND H.FLAG != 'N' ");
		}
		if (BeanUtils.isNotEmpty(vo.getNum1()) && BeanUtils.isNotEmpty(vo.getNum2()) && BeanUtils.isNotEmpty(vo.getPercent1()) && BeanUtils.isNotEmpty(vo.getPercent2())) {
			String[] monthFrom = vo.getNum1().split(",");
			String[] monthTo = vo.getNum2().split(",");
			String[] percentFrom = vo.getPercent1().split(",");
			String[] percentTo = vo.getPercent2().split(",");
			if (BeanUtils.isEmpty(vo.getIndexFilter()) || "N".equals(vo.getIndexFilter())) {
				for (int i = 0; i < monthFrom.length; i++) {
					if (i == 0) {
						if ("N".equals(vo.getIndexFilter())) {
							sql.append(" AND not( ");
						} else {
							sql.append(" AND ( ");
						}
					}
					if (!"0".equals(monthFrom[i]) || !"0".equals(monthTo[i]) || !"0.00".equals(percentFrom[i]) || !"0.00".equals(percentTo[i])) {
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
			} else {
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
		List<PlanWorksheetHeader> planWorksheetHeaderList = jdbcTemplate.query(OracleUtils.limitForDataTable(sql, vo.getStart(), vo.getLength()), valueList.toArray(), fieldMapping);
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

		if (BeanUtils.isNotEmpty(vo.getSector())) {
			sql.append(" AND H.EXCISE_OWNER_AREA_1 = ? ");
			valueList.add(vo.getSector());
		}
		if (BeanUtils.isNotEmpty(vo.getViewStatus())) {
			sql.append(" AND H.VIEW_STATUS = ? ");
			valueList.add(vo.getViewStatus());
		}

		if (BeanUtils.isNotEmpty(vo.getFlag()) && !"NOT N".equals(vo.getFlag()) && !"NOT N S".equals(vo.getFlag())) {
			sql.append(" AND H.FLAG = ? ");
			valueList.add(vo.getFlag());
		} else if ("NOT N".equals(vo.getFlag())) {
			sql.append(" AND H.FLAG != 'N' ");
		} else if ("NOT N S".equals(vo.getFlag())) {
			sql.append(" AND H.FLAG != 'N' ");
			sql.append(" AND H.FLAG != 'S' ");
		}

		if (BeanUtils.isNotEmpty(vo.getNum1()) && BeanUtils.isNotEmpty(vo.getNum2()) && BeanUtils.isNotEmpty(vo.getPercent1()) && BeanUtils.isNotEmpty(vo.getPercent2())) {
			String[] monthFrom = vo.getNum1().split(",");
			String[] monthTo = vo.getNum2().split(",");
			String[] percentFrom = vo.getPercent1().split(",");
			String[] percentTo = vo.getPercent2().split(",");
			if (BeanUtils.isEmpty(vo.getIndexFilter()) || "N".equals(vo.getIndexFilter())) {
				for (int i = 0; i < monthFrom.length; i++) {
					if ("N".equals(vo.getIndexFilter())) {
						sql.append(" AND not( ");
					} else {
						sql.append(" AND ( ");
					}
					if (!"0".equals(monthFrom[i]) || !"0".equals(monthTo[i]) || !"0.00".equals(percentFrom[i]) || !"0.00".equals(percentTo[i])) {
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
			} else {
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
		
		if (BeanUtils.isNotEmpty(vo.getViewStatus())) {
			sql.append(" AND H.VIEW_STATUS = ? ");
			valueList.add(vo.getViewStatus());
		}
		if (BeanUtils.isNotEmpty(vo.getFlag()) && !"NOT N".equals(vo.getFlag()) && !"NOT N S".equals(vo.getFlag())) {
			sql.append(" AND H.FLAG = ? ");
			valueList.add(vo.getFlag());
		} else if ("NOT N".equals(vo.getFlag())) {
			sql.append(" AND H.FLAG != 'N' ");
		} else if ("NOT N S".equals(vo.getFlag())) {
			sql.append(" AND H.FLAG != 'N' ");
			sql.append(" AND H.FLAG != 's' ");
		}
		if (BeanUtils.isNotEmpty(vo.getNum1()) && BeanUtils.isNotEmpty(vo.getNum2()) && BeanUtils.isNotEmpty(vo.getPercent1()) && BeanUtils.isNotEmpty(vo.getPercent2())) {
			String[] monthFrom = vo.getNum1().split(",");
			String[] monthTo = vo.getNum2().split(",");
			String[] percentFrom = vo.getPercent1().split(",");
			String[] percentTo = vo.getPercent2().split(",");
			if (BeanUtils.isEmpty(vo.getIndexFilter()) || "N".equals(vo.getIndexFilter())) {
				for (int i = 0; i < monthFrom.length; i++) {
					if (i == 0) {
						if ("N".equals(vo.getIndexFilter())) {
							sql.append(" AND not( ");
						} else {
							sql.append(" AND ( ");
						}
					}
					if (!"0".equals(monthFrom[i]) || !"0".equals(monthTo[i]) || !"0.00".equals(percentFrom[i]) || !"0.00".equals(percentTo[i])) {
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
			} else {
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
		long count = jdbcTemplate.queryForObject(OracleUtils.countForDatatable(sql.toString()), valueList.toArray(), Long.class);
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

	public List<String> getStartDateAndEndDateFromAnalysNumber(String analysNumber) {
		StringBuilder sql = new StringBuilder(" SELECT DISTINCT D.MONTH ");
		sql.append(" FROM TA_PLAN_WORK_SHEET_HEADER H ");
		sql.append(" INNER JOIN TA_PLAN_WORK_SHEET_DETAIL D ");
		sql.append(" ON H.ANALYS_NUMBER = D.ANALYS_NUMBER ");
		sql.append(" AND H.EXCISE_ID = D.EXCISE_ID ");
		sql.append(" WHERE H.ANALYS_NUMBER = ? ");
		List<String> listMonth = jdbcTemplate.query(sql.toString(), new Object[] { analysNumber }, new RowMapper<String>() {
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
		if (BeanUtils.isNotEmpty(vo.getNum1()) && BeanUtils.isNotEmpty(vo.getNum2()) && BeanUtils.isNotEmpty(vo.getPercent1()) && BeanUtils.isNotEmpty(vo.getPercent2())) {
			String[] monthFrom = vo.getNum1().split(",");
			String[] monthTo = vo.getNum2().split(",");
			String[] percentFrom = vo.getPercent1().split(",");
			String[] percentTo = vo.getPercent2().split(",");
			for (int i = 0; i < monthFrom.length; i++) {
				if (!"0".equals(monthFrom[i]) || !"0".equals(monthTo[i]) || !"0.00".equals(percentFrom[i]) || !"0.00".equals(percentTo[i])) {
					List<Object> objList = new ArrayList<Object>();
					objList.add("N" + (i + 1));
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

	public List<String> queryProductTypeList(PlanWorksheetHeader criteria) {

		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT DISTINCT PRODUCT_TYPE FROM TA_PLAN_WORK_SHEET_HEADER ");
		sql.append(" WHERE 1 = 1 ");

		if (StringUtils.isNotBlank(criteria.getAnalysNumber())) {
			sql.append(" and ANALYS_NUMBER = ? ");
			valueList.add(criteria.getAnalysNumber());
		}
		if (StringUtils.isNotBlank(criteria.getMonthDate())) {
			sql.append(" and MONTH_DATE = ? ");
			valueList.add(criteria.getMonthDate());
		}
		if (criteria.getFullMonth() != null) {
			sql.append(" and FULL_MONTH = ? ");
			valueList.add(criteria.getFullMonth());
		}

		List<String> productType = jdbcTemplate.query(sql.toString(), valueList.toArray(), new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});
		return productType;

	}

	public List<String> queryExciseIdFlagSFromHeader() {
		String sql = "select DISTINCT H.EXCISE_ID from TA_PLAN_WORK_SHEET_HEADER H where H.ANALYS_NUMBER is not null and h.FLAG = 'F' ";
		List<String> exciseList = jdbcTemplate.query(sql, new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});
		return exciseList;
	}

	public List<Object> queryExciseIdFlagSDataList(String exciseId) {
		String sql = "select * from TA_PLAN_WORK_SHEET_HEADER H where H.EXCISE_ID = ? and H.ANALYS_NUMBER is not null";
		List<Object> exciseList = jdbcTemplate.query(sql.toString(), new Object[] { exciseId }, new RowMapper<Object>() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("analysNumber", rs.getString("ANALYS_NUMBER"));
				map.put("companyName", rs.getString("COMPANY_NAME"));
				map.put("productType", rs.getString("PRODUCT_TYPE"));

				return map;
			}
		});
		return exciseList;
	}

	public List<Ope041Vo> queryExciseIdFromAccDTL(String exciseId, String type, Date date, int backMonth) {

		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();

//		sql.append(" WITH THAI_MONTH AS ");
//		sql.append(" ( SELECT REPLACE(TO_CHAR( add_MONTHS( ? , LEVEL-? ) , 'MON yy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI'), '  ', ' ' ) MONTH_AFTER FROM dual CONNECT BY LEVEL <= ? ) ");
		sql.append(" SELECT * ");
		sql.append(" FROM TA_EXCISE_ACC_MONTH_04_07_DTL D ");
		sql.append(" WHERE D.EXCISE_ID = ? ");
		sql.append(" AND D.TYPE = ?  ");
//		sql.append(" AND D.ACC_MONTH IN (SELECT REPLACE(TO_CHAR( add_MONTHS( Sysdate, LEVEL-12 ) , 'MON yy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI'), '  ', ' ' ) Month_AFTER   FROM dual CONNECT BY LEVEL <= 12)  ");

//		valueList.add(date);
//		valueList.add(backMonth);
//		valueList.add(backMonth);
		valueList.add(exciseId.trim());
		valueList.add(type);

		List<Ope041Vo> exciseIdFromAccDTLList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingAccMonthVo);
		return exciseIdFromAccDTLList;

	}

	public List<String> queryExciseIdFindByAddress(String exciseId) {
		StringBuilder sql = new StringBuilder(" SELECT FACTORY_ADDRESS ");
		sql.append(" FROM TA_PLAN_WORK_SHEET_HEADER H ");
		sql.append(" WHERE H.EXCISE_ID = ? ");
		sql.append(" ORDER BY H.WORK_SHEET_HEADER_ID DESC");
		List<String> address = jdbcTemplate.query(sql.toString(), new Object[] { exciseId }, new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});
		return address;
	}

	private RowMapper<Ope041Vo> fieldMappingAccMonthVo = new RowMapper<Ope041Vo>() {
		@Override
		public Ope041Vo mapRow(ResultSet rs, int rowNum) throws SQLException {
			Ope041Vo ac = new Ope041Vo();
			ac.setId(rs.getString("TA_EXCISE_ACC_DTL_04_07_ID"));
			ac.setProduct1(rs.getString("PRODUCT_1"));
			ac.setProduct2(rs.getString("PRODUCT_2"));
			ac.setProduct3(rs.getString("PRODUCT_3"));
			ac.setProduct4(rs.getString("PRODUCT_4"));
			ac.setProduct5(rs.getString("PRODUCT_5"));
			ac.setProduct6(rs.getString("PRODUCT_6"));
			ac.setMonthRecieve1(rs.getString("MONTH_RECIEVE_1"));
			ac.setMonthRecieve2(rs.getString("MONTH_RECIEVE_2"));
			ac.setMonthRecieve3(rs.getString("MONTH_RECIEVE_3"));
			ac.setMonthRecieve4(rs.getString("MONTH_RECIEVE_4"));
			ac.setMonthRecieve5(rs.getString("MONTH_RECIEVE_5"));
			ac.setMonthRecieve6(rs.getString("MONTH_RECIEVE_6"));
			ac.setAccMonth(rs.getString("ACC_MONTH"));
			ac.setExciseId(rs.getString("EXCISE_ID"));
			ac.setType(rs.getString("TYPE"));
			return ac;
		}
	};

	public List<String> getMonthFormPlanWorksheetHeader(Date date, Long backMonth) {

		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT REPLACE(TO_CHAR( add_MONTHS( ? , LEVEL-? ) , ");
		sql.append(" 'MON yy', 'NLS_CALENDAR=''THAI BUDDHA'' NLS_DATE_LANGUAGE=THAI') ");
		sql.append(" , '  ', ' ' ) MONTH_AFTER ");
		sql.append(" FROM dual ");
		sql.append(" CONNECT BY LEVEL <= ? ");
		valueList.add(date);
		valueList.add(backMonth);
		valueList.add(backMonth);
		List<String> monthList = jdbcTemplate.query(sql.toString(), valueList.toArray(), new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});
		return monthList;

	}
	
	
	public List<String> findSectorByNotInExciseAndAnalysNumber(String analysNumber, List<String> exciseIdList) {
		List<Object> valueList = new ArrayList<Object>();
		valueList.add(analysNumber);
		StringBuilder sql = new StringBuilder(" select DISTINCT H.EXCISE_OWNER_AREA_1 from TA_PLAN_WORK_SHEET_HEADER H where H.ANALYS_NUMBER = ?  AND H.FLAG = 'S' and H.EXCISE_ID not in (");
		for (int i = 0; i < exciseIdList.size(); i++) {
			sql.append("?");
			if (i != exciseIdList.size() - 1) {
				sql.append(",");
			}
			valueList.add(exciseIdList.get(i));
		}
		sql.append(")");
		List<String> result = jdbcTemplate.query(sql.toString(), valueList.toArray(), new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});
		return result;
	}
	public List<String> findSectorByExciseAndAnalysNumber(String analysNumber, List<String> exciseIdList) {
		List<Object> valueList = new ArrayList<Object>();
		valueList.add(analysNumber);
		StringBuilder sql = new StringBuilder(" select DISTINCT H.EXCISE_OWNER_AREA_1 from TA_PLAN_WORK_SHEET_HEADER H where H.ANALYS_NUMBER = ?  AND H.FLAG = 'S' and H.EXCISE_ID in (");
		for (int i = 0; i < exciseIdList.size(); i++) {
			sql.append("?");
			if (i != exciseIdList.size() - 1) {
				sql.append(",");
			}
			valueList.add(exciseIdList.get(i));
		}
		sql.append(")");
		List<String> result = jdbcTemplate.query(sql.toString(), valueList.toArray(), new RowMapper<String>() {
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString(1);
			}
		});
		return result;
	}

}
