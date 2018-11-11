package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.entity.DisbursementRequest;
import th.co.baiwa.excise.ia.persistence.vo.Int061106FormVo;

@Repository
public class Int061106Dao {

	private static Logger log = LoggerFactory.getLogger(Int061106Dao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	private final String SQL_IA_DISBURSEMENT_REQUEST = " SELECT * FROM IA_DISBURSEMENT_REQUEST WHERE IS_DELETED = 'N' AND REQUEST_TYPE = ?";
	  

		public List<DisbursementRequest> findAllInt061106(Int061106FormVo formVo) {
			
			StringBuilder sql = new StringBuilder(SQL_IA_DISBURSEMENT_REQUEST);
			List<Object> params = new ArrayList<>();

				params.add(formVo.getWithdrawRequest());
			
            
//			sql.append(" ORDER BY OFFICE_CODE asc");
			log.info("findAllInt085 sql : {}",sql);
	        List<DisbursementRequest> list = jdbcTemplate.query(sql.toString(), params.toArray(), int061106Rowmapper);
	        return list;
	    }
		
		 private RowMapper<DisbursementRequest> int061106Rowmapper = new RowMapper<DisbursementRequest>() {
		    	@Override
		    	public DisbursementRequest mapRow(ResultSet rs, int arg1) throws SQLException {
		    		DisbursementRequest vo = new DisbursementRequest();
		    
		    	    vo.setId(rs.getBigDecimal("ID"));
		    	    
		    	    vo.setBillLading(rs.getString("BILL_LADING"));
		    	    vo.setPosition(rs.getString("POSITION"));
		    	    vo.setAffiliation(rs.getString("AFFILIATION"));
		    	    vo.setAmount(rs.getBigDecimal("AMOUNT"));
		    	    
		    	    vo.setCreatedDate(rs.getDate("CREATED_DATE"));
		    	    vo.setCreatedDateStr(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("CREATED_DATE")));
		    	    vo.setCreatedBy(rs.getString("CREATED_BY"));
		    	    
		    	    vo.setBillPay(rs.getString("BILL_PAY"));
		    	    vo.setPositionPay(rs.getString("POSITION_PAY"));
		    	    vo.setAffiliationPay(rs.getString("AFFILIATION_PAY"));
		    	    vo.setAmountPay(rs.getBigDecimal("AMOUNT_PAY"));
		    	    
		    	    vo.setCreatedDatePay(rs.getDate("CREATED_DATE_PAY"));
		    	    vo.setCreatedDatePayStr(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("CREATED_DATE_PAY")));
		    	  
		    	    vo.setCreatedByPay(rs.getString("CREATED_BY_PAY"));
		
		    	    vo.setUpdatedBy(rs.getString("UPDATED_BY"));
		    	    vo.setUpdatedDate(rs.getDate("UPDATED_DATE"));
		    	    
		    	    vo.setRequestType(rs.getString("REQUEST_TYPE"));
		    	    vo.setStatus(rs.getString("STATUS"));
		    	    vo.setIsDeleted(rs.getString("IS_DELETED"));
		    	    vo.setVersion(rs.getInt("VERSION"));
		    	   
		    	  
		    	    
		    	     
	  	
		    		return vo;
		    	}
		    };
		    
			public void updateBillLadingInt061106(Int061106FormVo form) {
				
				StringBuilder sql = new StringBuilder(" UPDATE IA_DISBURSEMENT_REQUEST SET BILL_LADING = ?,STATUS = '2063' WHERE ID = ? ");

				log.info("getBillLading : {}",form.getBillLading());
				log.info("getId : {}",form.getId());
					
				log.info("updateBillLadingInt061106 sql : {}",sql);
		       jdbcTemplate.update(sql.toString(),new Object[] {form.getBillLading(),form.getId()});
		    }
			
			public void updateBillPayInt061106(Int061106FormVo form) {
				
				StringBuilder sql = new StringBuilder();
				
				sql.append(" UPDATE IA_DISBURSEMENT_REQUEST SET ");
				
				sql.append(" BILL_PAY = ?, ");

				sql.append(" CREATED_DATE_PAY = sysdate , ");
				sql.append(" CREATED_BY_PAY = ? , ");
				sql.append(" POSITION_PAY = ? , ");
				sql.append(" AFFILIATION_PAY = ? , ");
				sql.append(" AMOUNT_PAY = ? , ");
				
				sql.append(" STATUS = '2064'");
				sql.append(" WHERE ID = ? ");
					
				log.info("updateBillLadingInt061106 sql : {}",sql);
		       jdbcTemplate.update(sql.toString(),new Object[] {
		    		   form.getBillPay(),
		    		   UserLoginUtils.getCurrentUserBean().getUsername(),
		    		   ApplicationCache.getListOfValueByValueType("SECTOR_LIST",UserLoginUtils.getCurrentUserBean().getOfficeId()).get(0).getSubTypeDescription(),
		    		   "ผู้ตรวจสอบภาษี",
		    		   form.getAmountPay(),
		    		   form.getId()
		    		   });
		    }
			
			
			
			
			
			public void updateApproveInt061106(Int061106FormVo form) {
				
				StringBuilder sql = new StringBuilder();
				
				sql.append(" UPDATE IA_DISBURSEMENT_REQUEST SET ");
				sql.append(" APPROVE_CREATED_DATE = sysdate , ");
				sql.append(" APPROVE_CREATED_BY = ? , ");
				sql.append(" APPROVE_POSITION = ? , ");
				sql.append(" APPROVE_AFFILIATION = ?,  ");
				
				sql.append(" STATUS = ?");
				sql.append(" WHERE ID = ? ");
					
				log.info("updateBillLadingInt061106 sql : {}",sql);
		       jdbcTemplate.update(sql.toString(),new Object[] {
		    		   UserLoginUtils.getCurrentUserBean().getUsername(),
	    		   		ApplicationCache.getListOfValueByValueType("SECTOR_LIST",UserLoginUtils.getCurrentUserBean().getOfficeId()).get(0).getSubTypeDescription(),
		    		   "ผู้ตรวจสอบ",
		    		   form.getStatus(),
		    		   form.getId()});
		    }
		    

}
