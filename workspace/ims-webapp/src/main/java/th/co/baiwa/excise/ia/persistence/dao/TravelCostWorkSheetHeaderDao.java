package th.co.baiwa.excise.ia.persistence.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ia.persistence.entity.TravelCostWorkSheetHeader;
import th.co.baiwa.buckwaframework.common.util.BeanUtils; 

@Repository
public class TravelCostWorkSheetHeaderDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String sqlTemplate = " SELECT * FROM IA_TRAVEL_COST_WS_HEADER H WHERE 1 = 1 ";

	public List<TravelCostWorkSheetHeader> queryTravelCostWorksheetHeader(TravelCostWorkSheetHeader travel) {
		List<Object> paramList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(sqlTemplate);
		if (BeanUtils.isNotEmpty(travel.getWorkSheetHeaderId())) {
			sql.append(" AND WORK_SHEET_HEADER_ID = ? ");
			paramList.add(travel.getWorkSheetHeaderId());
		}
		List<TravelCostWorkSheetHeader> res = jdbcTemplate.query(sql.toString(), paramList.toArray(), rowMapper);
		return res;
	}
	
	public List<TravelCostWorkSheetHeader> queryTravelCostWorksheetHeader(String orderBy) {
		List<Object> paramList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(sqlTemplate);
		if (BeanUtils.isNotEmpty(orderBy)) {
			sql.append(" ORDER BY " + orderBy);
		}
		List<TravelCostWorkSheetHeader> res = jdbcTemplate.query(sql.toString(), paramList.toArray(), rowMapper);
		return res;
	}
	
	public int deleteTravelCostWorksheetHeader(BigDecimal id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM IA_TRAVEL_COST_WS_HEADER WHERE WORK_SHEET_HEADER_ID = ? ");
		return jdbcTemplate.update(sql.toString(), new Object[] {id});
	}

	public int insertTravelCostWorksheetHeader(TravelCostWorkSheetHeader value) {
		// inti SQL for insert to database
		StringBuilder sql = new StringBuilder();
		sql.append(
				" insert into IA_TRAVEL_COST_WS_HEADER (WORK_SHEET_HEADER_ID,WORK_SHEET_HEADER_NAME,DEPARTMENT_NAME,START_DATE,END_DATE,DESCRIPTION,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE) ");
		sql.append(" values(WORK_SHEET_HEADER_SEQ.nextval ,?,?,?,?,?,?,?,?,?)");
		// for to set Object
		return jdbcTemplate.update(sql.toString(), TravelCostWorkSheetHeaderToArrayObject(value));
	}

	private Object[] TravelCostWorkSheetHeaderToArrayObject(TravelCostWorkSheetHeader value) {

		List<Object> valueList = new ArrayList<Object>();
		if (value != null) {
			valueList.add(value.getWorkSheetHeaderName());
			valueList.add(value.getDepartmentName());
			valueList.add(value.getStartDate());
			valueList.add(value.getEndDate());
			valueList.add(value.getDescription());
			valueList.add(value.getCreatedBy());
			valueList.add(value.getCreatedDate());
			valueList.add(value.getUpdatedBy());
			valueList.add(value.getUpdatedDate());

		}
		return valueList.toArray();
	}

	private RowMapper<TravelCostWorkSheetHeader> rowMapper = new RowMapper<TravelCostWorkSheetHeader>() {

		@Override
		public TravelCostWorkSheetHeader mapRow(ResultSet rs, int arg1) throws SQLException {
			TravelCostWorkSheetHeader vo = new TravelCostWorkSheetHeader();
			vo.setWorkSheetHeaderId(rs.getBigDecimal("WORK_SHEET_HEADER_ID"));
			vo.setWorkSheetHeaderName(rs.getString("WORK_SHEET_HEADER_NAME"));
			vo.setDepartmentName(rs.getString("DEPARTMENT_NAME"));
			vo.setStartDate(rs.getDate("START_DATE"));
			vo.setEndDate(rs.getDate("END_DATE"));
			vo.setDescription(rs.getString("DESCRIPTION"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(rs.getDate("CREATED_DATE"));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(rs.getDate("UPDATED_DATE"));

			return vo;

		}

	};

}
