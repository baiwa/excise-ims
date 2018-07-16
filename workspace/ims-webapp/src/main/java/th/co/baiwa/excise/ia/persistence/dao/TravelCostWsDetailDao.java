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

import th.co.baiwa.excise.ia.persistence.entity.TravelCostWsDetail;
import th.co.baiwa.excise.utils.BeanUtils;

@Repository
public class TravelCostWsDetailDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static String sqlTemplete = " SELECT * FROM IA_TRAVEL_COST_WS_DETAIL D WHERE 1=1 ";

	public List<TravelCostWsDetail> queryTravelCostWsDetail(TravelCostWsDetail detail) {
		 List<Object> params = new ArrayList<Object>();
		 StringBuilder sql = new StringBuilder(sqlTemplete);
		 if (BeanUtils.isNotEmpty(detail.getHeaderId())) {
			 sql.append(" AND HEADER_ID=? ");
			 params.add(detail.getHeaderId());
		 }
		 List<TravelCostWsDetail> res = jdbcTemplate.query(sql.toString(), params.toArray(), rowMapper);
		 return res;
	 }
	
	public int deleteTravelCostWorksheetDetail(BigDecimal id) {
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE FROM IA_TRAVEL_COST_WS_DETAIL WHERE HEADER_ID = ? ");
		return jdbcTemplate.update(sql.toString(), new Object[] {id});
	}

	public int[] insertTravelCostWsDetail(List<TravelCostWsDetail> value) {
		// inti SQL for insert to database
		StringBuilder sql = new StringBuilder();
		sql.append(
				" insert into IA_TRAVEL_COST_WS_DETAIL (WORK_SHEET_DETAIL_ID,HEADER_ID,NAME,LAST_NAME,POSITION,CATEGORY,DEGREE,ALLOWANCE_DATE,ALLOWANCE_COST,RENT_DATE,RENT_COST,TRAVEL_COST,OTHER_COST,SUM_COST,NOTE,CREATED_BY,CREATED_DATE,UPDATED_BY,UPDATED_DATE) ");
		sql.append(" values(WORK_SHEET_DETAIL_SEQ.nextval ,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
		List<Object[]> obj = new ArrayList<Object[]>();
		for (TravelCostWsDetail val : value) {
			obj.add(TravelCostWsDetailToArrayObject(val));
		}
		// for to set Object
		return jdbcTemplate.batchUpdate(sql.toString(), obj);
	}

	private Object[] TravelCostWsDetailToArrayObject(TravelCostWsDetail value) {
		List<Object> valueList = new ArrayList<Object>();
		if (value != null) {
			valueList.add(value.getHeaderId());
			valueList.add(value.getName());
			valueList.add(value.getLastName());
			valueList.add(value.getPosition());
			valueList.add(value.getCategory());
			valueList.add(value.getDegree());
			valueList.add(value.getAllowanceDate());
			valueList.add(value.getAllowanceCost());
			valueList.add(value.getRentDate());
			valueList.add(value.getRentCost());
			valueList.add(value.getTravelCost());
			valueList.add(value.getOtherCost());
			valueList.add(value.getSumCost());
			valueList.add(value.getNote());
			valueList.add(value.getCreatedBy());
			valueList.add(value.getCreatedDate());
			valueList.add(value.getUpdatedBy());
			valueList.add(value.getUpdatedDate());
		}
		return valueList.toArray();
	}
	
	private RowMapper<TravelCostWsDetail> rowMapper = new RowMapper<TravelCostWsDetail>() {

		@Override
		public TravelCostWsDetail mapRow(ResultSet rs, int arg1) throws SQLException {
			TravelCostWsDetail vo = new TravelCostWsDetail();
			vo.setWorkSheetDetailId(rs.getBigDecimal("WORK_SHEET_DETAIL_ID"));
			vo.setHeaderId(rs.getString("HEADER_ID"));
			vo.setName(rs.getString("NAME"));
			vo.setLastName(rs.getString("LAST_NAME"));
			vo.setPosition(rs.getString("POSITION"));
			vo.setCategory(rs.getString("CATEGORY"));
			vo.setDegree(rs.getString("DEGREE"));
			vo.setAllowanceDate(rs.getBigDecimal("ALLOWANCE_DATE"));
			vo.setAllowanceCost(rs.getBigDecimal("ALLOWANCE_COST"));
			vo.setRentDate(rs.getBigDecimal("RENT_DATE"));
			vo.setRentCost(rs.getBigDecimal("RENT_COST"));
			vo.setTravelCost(rs.getBigDecimal("TRAVEL_COST"));
			vo.setOtherCost(rs.getBigDecimal("OTHER_COST"));
			vo.setSumCost(rs.getBigDecimal("SUM_COST"));
			vo.setNote(rs.getString("NOTE"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(rs.getDate("CREATED_DATE"));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			return vo;
		}

	};
	
}
