package th.co.baiwa.excise.ia.persistence.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ia.persistence.entity.TravelCostWsDetail;

@Repository
public class TravelCostWsDetailDao {
	
	 @Autowired
	 private JdbcTemplate jdbcTemplate;
	 
	 public int[] insertTravelCostWsDetail(List<TravelCostWsDetail> value) {
			//inti SQL for insert to database
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into IA_TRAVEL_COST_WS_DETAIL (WORK_SHEET_DETAIL_ID,HEADER_ID,NAME,LAST_NAME,POSITION,CATEGORY,DEGREE,ALLOWANCE_DATE,ALLOWANCE_COST,RENT_DATE,RENT_COST,TRAVEL_COST,OTHER_COST,SUM_COST,NOTE,CREATED_BY,CREATED_DATETIME,UPDATE_BY,UPDATE_DATETIME) ");
			sql.append(" values(WORK_SHEET_DETAIL_SEQ.nextval ,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			List<Object[]> obj = new ArrayList<Object[]>();
			for(TravelCostWsDetail val: value) {
				obj.add(TravelCostWsDetailToArrayObject(val));
			}
			//for to set Object
			return jdbcTemplate.batchUpdate(sql.toString() , obj);
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
				valueList.add(value.getCreatedDatetime());				
				valueList.add(value.getUpdateBy());				
				valueList.add(value.getUpdateDatetime());
			}
			return valueList.toArray();
		}
	
}
