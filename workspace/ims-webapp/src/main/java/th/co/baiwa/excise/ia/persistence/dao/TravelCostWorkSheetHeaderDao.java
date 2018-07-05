package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ia.persistence.entity.TravelCostWorkSheetHeader;
import th.co.baiwa.excise.utils.BeanUtils;

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

	public int insertTravelCostWorksheetHeader(TravelCostWorkSheetHeader value) {
		// inti SQL for insert to database
		StringBuilder sql = new StringBuilder();
		sql.append(
				" insert into IA_TRAVEL_COST_WS_HEADER (WORK_SHEET_HEADER_ID,WORK_SHEET_HEADER_NAME,DEPARTMENT_NAME,START_DATE,END_DATE,DESCRIPTION,CREATED_BY,CREATED_DATETIME,UPDATE_BY,UPDATE_DATETIME) ");
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
			valueList.add(value.getCreatedDatetime());
			valueList.add(value.getUpdateBy());
			valueList.add(value.getUpdateDatetime());

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
			vo.setCreatedDatetime(rs.getDate("CREATED_DATETIME"));
			vo.setUpdateBy(rs.getString("UPDATE_BY"));
			vo.setUpdateDatetime(rs.getDate("UPDATE_DATETIME"));

			return vo;

		}

	};

}
