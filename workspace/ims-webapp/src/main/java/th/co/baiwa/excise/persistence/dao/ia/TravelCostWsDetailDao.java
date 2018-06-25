package th.co.baiwa.excise.persistence.dao.ia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.domain.ia.TravelCostWsDetail;
import th.co.baiwa.excise.entity.ta.PlanWorksheetHeader;

@Repository
public class TravelCostWsDetailDao {
	
	 @Autowired
	    private JdbcTemplate jdbcTemplate;
	 public void insertTravelCostWsDetail(TravelCostWsDetail value) {
			//inti SQL for insert to database
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into insert into IA_TRAVEL_COST_WS_DETAIL (WORK_SHEET_DETAIL_ID,HEADER_ID,NAME,LAST_NAME,POSITION,CATEGORY,DEGREE,ALLOWANCE_DATE,ALLOWANCE_COST,RENT_DATE,RENT_COST,TRAVEL_COST,OTHER_COST,SUM_COST,NOTE,CREATED_BY,CREATED_DATETIME,UPDATE_BY,UPDATE_DATETIME) ");
			sql.append(" values(WORK_SHEET_DETAIL_ID.nextval ,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
			//for to set Object
			jdbcTemplate.update(sql.toString() ,TravelCostWsDetailToArrayObject(value) );
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
