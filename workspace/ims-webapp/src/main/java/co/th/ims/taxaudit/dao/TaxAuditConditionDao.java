package co.th.ims.taxaudit.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import co.th.ims.taxaudit.vo.TaxAuditConditionFormVo;

@Repository
public class TaxAuditConditionDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	public void insertCondition(TaxAuditConditionFormVo formVo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" INSERT							 	 	 ");
		sql.append(" INTO TA_MAS_COND_DTL_TAX      		 	 ");
		sql.append(" 	(          						 	 ");
		sql.append(" 		COND_DTL_ID, 				 	 ");
		sql.append(" 		COND_HDR_ID, 				 	 ");
		sql.append(" 		COND_GROUP, 		 		 	 ");
		sql.append(" 		TAX_MONTH_START, 			 	 ");
		sql.append(" 		TAX_MONTH_END, 			 	 	 ");
		sql.append(" 		RANGE_START, 			 	 	 ");
		sql.append(" 		RANGE_END, 			 		 	 ");
		sql.append(" 		RISK_LEVEL, 			 		 ");
		sql.append(" 		CREATED_BY, 			 		 ");
		sql.append(" 		CREATED_DATE 			 		 ");
		sql.append(" 	) 	 							 	 ");
		sql.append(" 	VALUES 	 						 	 ");
		sql.append(" 	( 	 				   			 	 ");
		sql.append(" 		TA_MAS_COND_DTL_TAX_SEQ.nextval, ");
		sql.append(" 		?, 	 				 		 	 ");
		sql.append(" 		?, 	 				 		  	 ");
		sql.append(" 		?, 	 				 	 	 	 ");
		sql.append(" 		?, 	 				 	 	 	 ");
		sql.append(" 		?, 	 				 	 	 	 ");
		sql.append(" 		?, 	 				 	 	 	 ");
		sql.append(" 		?, 	 				 	 	 	 ");
		sql.append(" 		?, 	 				 	 	 	 ");
		sql.append(" 		? 	 				 	 	 	 ");
		sql.append(" 	) 	 				     	     	 ");

		List<Object> params = new ArrayList<>();
		params.add(formVo.getConditionHeaderId());
		params.add(formVo.getConditionGroup());
		params.add(formVo.getTaxMonthStart());
		params.add(formVo.getTaxMonthEnd());
		params.add(formVo.getRangeStart());
		params.add(formVo.getRangeEnd());
		params.add(formVo.getRiskLevel());
		params.add(formVo.getCreatedBy());
		params.add(formVo.getCreatedDate());

		int row = jdbcTemplate.update(sql.toString(), params);
	}
}
