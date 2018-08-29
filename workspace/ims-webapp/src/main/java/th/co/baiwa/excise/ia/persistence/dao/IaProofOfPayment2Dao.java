package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.vo.Int091FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int091Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int09222FormVo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class IaProofOfPayment2Dao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String SQL_PROOF_OF_PAYMENT_PROCESS = "SELECT * FROM PROOF_OF_PAYMENT_PROCESS WHERE IS_DELETED='N' ";
	
	public Long count(Int091FormVo formVo) {
		
		StringBuilder sql = new StringBuilder(SQL_PROOF_OF_PAYMENT_PROCESS);
		List<Object> params = new ArrayList<>();
		
		if (StringUtils.isNotBlank(formVo.getYear())) {
			sql.append(" AND  TO_CHAR(FISCAL_YEAR, 'YYYY') = TO_CHAR( ? ,'YYYY') ");
			Date year = DateConstant.convertStrYYYYToDate(formVo.getYear());
			params.add(year);
			
		}
		if (StringUtils.isNotBlank(formVo.getDocumentType())) {
			sql.append(" AND DOCUMENT_TYPE = ?");
			params.add(formVo.getDocumentType());
			
		}
		String countSql = OracleUtils.countForDatatable(sql);
        Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
        return count;
    }

	public List<Int091Vo> findAll(Int091FormVo formVo) {
		
		StringBuilder sql = new StringBuilder(SQL_PROOF_OF_PAYMENT_PROCESS);
		List<Object> params = new ArrayList<>();
		
		if (StringUtils.isNotBlank(formVo.getYear())) {
			sql.append(" AND  TO_CHAR(FISCAL_YEAR, 'YYYY') = TO_CHAR( ? ,'YYYY') ");
			Date year = DateConstant.convertStrYYYYToDate(formVo.getYear());
			params.add(year);
			
		}
		if (StringUtils.isNotBlank(formVo.getDocumentType())) {
			sql.append(" AND DOCUMENT_TYPE = ?");
			params.add(formVo.getDocumentType());
			
		}
		
		sql.append(" ORDER BY CREATED_DATE desc ");
		//String sqlLimit = OracleUtils.limitForDataTable(sql.toString(), formVo.getStart(),formVo.getLength());
        List<Int091Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), budgetRowmapper);
        return list;
    }
	
	 private RowMapper<Int091Vo> budgetRowmapper = new RowMapper<Int091Vo>() {
	    	@Override
	    	public Int091Vo mapRow(ResultSet rs, int arg1) throws SQLException {
	    		Int091Vo vo = new Int091Vo();
	    		
	    		vo.setId(rs.getLong("ID"));
	    		vo.setCreatedDate(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("CREATED_DATE")));
	    		vo.setCreatedBy(rs.getString("CREATED_BY"));
	    		vo.setDocumentType(rs.getString("DOCUMENT_TYPE"));
	    		vo.setThosePicked(rs.getString("THOSE_PICKED"));
	    		vo.setFiscalYear(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("FISCAL_YEAR")));
	    		    		
	    		return vo;
	    	}
	    };
	    
	    public void delete (Long id) {
	    	jdbcTemplate.update(" UPDATE PROOF_OF_PAYMENT_PROCESS SET IS_DELETED = 'Y' WHERE ID = ? ",new Object[] {id});
	    }
	    public Long saveInt9222 (Int09222FormVo vo) {
	    	Long id = jdbcTemplate.queryForObject(" SELECT PROOF_OF_PAYMENT_PROCESS_SEQ.NEXTVAL FROM dual ",long.class);
	    	jdbcTemplate.update(" INSERT INTO PROOF_OF_PAYMENT_PROCESS( " + 
	    			"ID, " + 
	    			"CREATED_DATE, " + 
	    			"CREATED_BY, " + 
	    			"STATE_AGENCY_NAME, " + 
	    			"THOSE_PICKED,"+ 
	    			"FISCAL_YEAR,"+ 
	    			"IS_DELETED) " + 
	    			"VALUES( " + 
	    			"?, " + 
	    			"sysdate, " + 
	    			"?, " + 
	    			"?, " + 
	    			"?, " + 
	    			"?, " +
	    			"?) ",new Object[] {id,vo.getCreatedBy(),vo.getStateAgencyName(),vo.getThosePicked(),
	    					DateConstant.convertStrDD_MM_YYYYToDate(vo.getFiscalYear()),"N"});
	    	return id;
			    }
			   
		}
