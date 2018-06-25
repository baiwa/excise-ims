package th.co.baiwa.excise.persistence.dao.ia;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.domain.ia.TravelCostWorkSheetHeader;
import th.co.baiwa.excise.entity.ta.PlanWorksheetHeader;

@Repository
public class TravelCostWorkSheetHeaderDao {

	 @Autowired
	    private JdbcTemplate jdbcTemplate;
	 public void insertTravelCostWorksheetHeader(TravelCostWorkSheetHeader value) {
			//inti SQL for insert to database
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into insert into IA_TRAVEL_COST_WS_HEADER (WORK_SHEET_HEADER_ID,WORK_SHEET_HEADER_NAME,DEPARTMENT_NAME,START_DATE,END_DATE,DESCRIPTION,CREATED_BY,CREATED_DATETIME,UPDATE_BY,UPDATE_DATETIME) ");
			sql.append(" values(WORK_SHEET_DETAIL_ID.nextval ,?,?,?,?,?,?,?,?,?)");
			//for to set Object
			jdbcTemplate.update(sql.toString() ,TravelCostWorkSheetHeaderToArrayObject(value) );
		}
	 private Object[] TravelCostWorkSheetHeaderToArrayObject(TravelCostWorkSheetHeader value) {

			List<Object> valueList = new ArrayList<Object>();
			if (value != null) {
				valueList.add(value.getWorkSheetHeaderName());
				valueList.add(value.getDepartmentName());
				valueList.add(value.getStartDate());
				valueList.add(value.getEndDate());
				valueList.add(value.getDescription());
				valueList.add(value.getDescription());
				valueList.add(value.getCreatedDatetime());
				valueList.add(value.getUpdateBy());
				valueList.add(value.getUpdateDatetime());
				
			}
			return valueList.toArray();
		}
	 
	
	
	
}
