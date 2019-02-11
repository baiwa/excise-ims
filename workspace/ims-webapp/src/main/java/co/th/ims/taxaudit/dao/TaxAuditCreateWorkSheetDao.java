package co.th.ims.taxaudit.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.th.ims.taxaudit.vo.TaxAuditCreateWorkSheetFormVo;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;

@Repository
public class TaxAuditCreateWorkSheetDao {
	
	@Autowired
	CommonJdbcTemplate commonJdbcTemplate;
	
	@Autowired 
	JdbcTemplate jdbcTemplate;
	
	public Long insertWorkSheet(TaxAuditCreateWorkSheetFormVo taxAuditCreateWorkSheetFormVo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT								 ");
		sql.append(" INTO TA_MAS_COND_HDR        		 ");
		sql.append(" 	(          						 ");
		sql.append(" 		COND_HDR_ID, 				 ");
		sql.append(" 		BUDGET_YEAR, 				 ");
		sql.append(" 		MONTH_NUM, 		 			 ");
		sql.append(" 		AREA_SEE_FLAG, 				 ");
		sql.append(" 		AREA_SELECT_FLAG, 			 ");
		sql.append(" 		NO_AUDIT_YEAR_NUM, 			 ");
		sql.append(" 		CREATED_BY, 			  	 ");
		sql.append(" 		CREATED_DATE 				 ");
		sql.append(" 	) 	 							 ");
		sql.append(" 	VALUES 	 						 ");
		sql.append(" 	( 	 				   			 ");
		sql.append(" 		TA_MAS_COND_HDR_SEQ.nextval, ");
		sql.append(" 		?, 	 				 		 ");
		sql.append(" 		?, 	 				 		 ");
		sql.append(" 		'N', 	 				 	 ");
		sql.append(" 		'N', 	 				 	 ");
		sql.append(" 		?, 	 				 		 ");
		sql.append(" 		?, 	 				 		 ");
		sql.append(" 		? 	 				 		 ");
		sql.append(" 	) 	 				     	     ");
		
		List<Object> params = new ArrayList<>();
		params.add(taxAuditCreateWorkSheetFormVo.getBudgetYear());
		params.add(taxAuditCreateWorkSheetFormVo.getMonthNum());
		params.add(taxAuditCreateWorkSheetFormVo.getNoAuditYearNum());
		params.add(taxAuditCreateWorkSheetFormVo.getCreatedBy());
		params.add(taxAuditCreateWorkSheetFormVo.getCreatedDate());
//		int row = jdbcTemplate.update(sql.toString(), params.toArray());
//		Long row = commonJdbcTemplate.executeInsertWithKeyHolder(sql.toString(), params.toArray());
		
		return 0L;
	}
}
