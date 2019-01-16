package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.cop.persistence.vo.Cop0711Vo;
import th.co.baiwa.excise.cop.persistence.vo.Cop071Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int084FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int084Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int085FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int085Vo;
import th.co.baiwa.buckwaframework.common.util.OracleUtils;

@Repository
public class IaIncomeExciseAudDao {

	private static final Logger log = LoggerFactory.getLogger(IaIncomeExciseAudDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String SQL_IA_INCOME_EXCISE_AUD_RPT = " SELECT * FROM IA_INCOME_EXCISE_AUD_RPT WHERE IS_DELETED = 'N' AND IA_INCOME_EXCISE_AUD_ID = ? ";
	
	private final String SQL_IA_INCOME_EXCISE_AUD_RPT_STATUS = " SELECT r.*, nvl2(T.ID , 'Y' , 'N')AS STATUS_M FROM IA_INCOME_EXCISE_AUD_RPT R " + 
			"left join IA_RISK_TASK T " + 
			"on R.IA_INCOME_EXCISE_AUD_RPT_ID = T.CHECK_ID WHERE 1=1 ";
	

	    
		public Long countInt085(Int085FormVo formVo) {
			
			StringBuilder sql = new StringBuilder(SQL_IA_INCOME_EXCISE_AUD_RPT_STATUS);
			List<Object> params = new ArrayList<>();
			
			
			       

			if (StringUtils.isNotBlank(formVo.getStartDate())) {
				sql.append("  AND TO_NUMBER(SUBSTR(START_DATE,0,2)) >= ? ");
				sql.append("  AND TO_NUMBER(SUBSTR(START_DATE,4,4)) >= ? ");
				Long sM = Long.valueOf(formVo.getStartDate().split("/")[0]);
				Long sY = Long.valueOf(formVo.getStartDate().split("/")[1]);
				params.add(sM);
				params.add(sY);
			}
			            
            if (StringUtils.isNotBlank(formVo.getEndDate())) {
				sql.append("  AND TO_NUMBER(SUBSTR(END_DATE,0,2)) <= ? ");
				sql.append("  AND TO_NUMBER(SUBSTR(END_DATE,4,4)) <= ? ");
				Long eM = Long.valueOf(formVo.getEndDate().split("/")[0]);
				Long eY = Long.valueOf(formVo.getEndDate().split("/")[1]);
				params.add(eM);
				params.add(eY);
			}
			

			String countSql = OracleUtils.countForDatatable(sql);
	        Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
	        return count;
	    }

		public List<Int085Vo> findAllInt085(Int085FormVo formVo) {
			
			StringBuilder sql = new StringBuilder(SQL_IA_INCOME_EXCISE_AUD_RPT_STATUS);
			List<Object> params = new ArrayList<>();

			if (StringUtils.isNotBlank(formVo.getStartDate())) {
				sql.append("  AND TO_NUMBER(SUBSTR(START_DATE,0,2)) >= ? ");
				sql.append("  AND TO_NUMBER(SUBSTR(START_DATE,4,4)) >= ? ");
				Long sM = Long.valueOf(formVo.getStartDate().split("/")[0]);
				Long sY = Long.valueOf(formVo.getStartDate().split("/")[1]);
				params.add(sM);
				params.add(sY);
			}
			            
            if (StringUtils.isNotBlank(formVo.getEndDate())) {
				sql.append("  AND TO_NUMBER(SUBSTR(END_DATE,0,2)) <= ? ");
				sql.append("  AND TO_NUMBER(SUBSTR(END_DATE,4,4)) <= ? ");
				Long eM = Long.valueOf(formVo.getEndDate().split("/")[0]);
				Long eY = Long.valueOf(formVo.getEndDate().split("/")[1]);
				params.add(eM);
				params.add(eY);
			}
            
			sql.append(" ORDER BY OFFICE_CODE asc");
			log.info("findAllInt085 sql : {}",sql);
	        List<Int085Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), int085Rowmapper);
	        return list;
	    }
		
		 private RowMapper<Int085Vo> int085Rowmapper = new RowMapper<Int085Vo>() {
		    	@Override
		    	public Int085Vo mapRow(ResultSet rs, int arg1) throws SQLException {
		    		Int085Vo vo = new Int085Vo();
		    
		    	    vo.setId(rs.getLong("IA_INCOME_EXCISE_AUD_RPT_ID"));
		    	    vo.setOfficeCode(rs.getString("OFFICE_CODE"));
		    	    vo.setOfficeName(rs.getString("OFFICE_NAME"));
		    	    vo.setOrigin(rs.getString("ORIGIN"));
		    	    vo.setRiskScore(rs.getString("RISK_SCORE"));
		    	    vo.setRisk(rs.getString("RISK"));
		    	    vo.setOrigin(rs.getString("ORIGIN"));
		    	    vo.setRiskScore(rs.getString("RISK_SCORE"));
		    	    vo.setStatusM(rs.getString("STATUS_M"));
		    	    
		    	    vo.setRiskPersen(rs.getString("RISK_PERSEN"));
		    	    vo.setBillAll(rs.getString("BILL_ALL"));
		    	    vo.setBillWaste(rs.getString("BILL_WASTE"));
		    	    
		    	    
		    	    vo.setRiskNumber(rs.getString("RISK_NUMBER"));
		    	    vo.setRiskRemark(rs.getString("RISK_REMARK"));
		    	    vo.setStartDate(rs.getString("START_DATE"));
		    	    vo.setEndDate(rs.getString("END_DATE"));
		    	    
		    	    
		    	     
	  	
		    		return vo;
		    	}
		    };	
		    


			public List<Int084Vo> findAllInt084(Int084FormVo formVo) {
				
				StringBuilder sql = new StringBuilder(SQL_IA_INCOME_EXCISE_AUD_RPT);
				List<Object> params = new ArrayList<>();

					params.add(formVo.getIdHead());

				sql.append(" ORDER BY OFFICE_CODE asc");
				log.info("findAllInt084 sql : {}",sql);
		        List<Int084Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), int084Rowmapper);
		        return list;
		    }
			
			 private RowMapper<Int084Vo> int084Rowmapper = new RowMapper<Int084Vo>() {
			    	@Override
			    	public Int084Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			    		Int084Vo vo = new Int084Vo();
			    
			    	    vo.setId(rs.getLong("IA_INCOME_EXCISE_AUD_RPT_ID"));
			    	    vo.setOfficeCode(rs.getString("OFFICE_CODE"));
			    	    vo.setOfficeName(rs.getString("OFFICE_NAME"));
			    	    vo.setRisk(rs.getString("RISK"));
			    	    vo.setOrigin(rs.getString("ORIGIN"));
			    	    vo.setRiskScore(rs.getString("RISK_SCORE"));
			    	    
			    	    vo.setStartDate(rs.getString("START_DATE"));
			    	    vo.setEndDate(rs.getString("END_DATE") );
			    	    vo.setBillAll(rs.getString("BILL_ALL"));
			    	    vo.setBillWaste(rs.getString("BILL_WASTE"));
			    	    vo.setRiskRemark(rs.getString("RISK_REMARK"));
			    	    vo.setRiskPersen(rs.getString("RISK_PERSEN"));
			    	    
		  	
			    		return vo;
			    	}
			    };	
			    
				public Long countSaveInt084(Long id) {
					
					StringBuilder sql = new StringBuilder(" select count(*) from IA_INCOME_EXCISE_AUD_RPT WHERE IA_INCOME_EXCISE_AUD_ID = ?");
					List<Object> params = new ArrayList<>();
			
						params.add(id);
	
			        Long count = jdbcTemplate.queryForObject(sql.toString(), params.toArray(), Long.class);
			        return count;
			    }
		    
		    public Long saveDataInt084 (Int084Vo vo) {
		    	Long id = jdbcTemplate.queryForObject(" select IA_INCOME_EXCISE_AUD_RPT_SEQ.NEXTVAL from dual ",Long.class);
    	
		    	jdbcTemplate.update(" INSERT INTO IA_INCOME_EXCISE_AUD_RPT( " + 
		    			"IA_INCOME_EXCISE_AUD_RPT_ID, " +
		    			"IA_INCOME_EXCISE_AUD_ID, " +
		    			"OFFICE_NAME, " + 
		    			"OFFICE_CODE, " + 
		    			"RISK, " + 
		    			"ORIGIN, " + 
		    			"RISK_SCORE, " + 
		    			"RISK_PERSEN, " + 
		    			"BILL_ALL, " + 
		    			"BILL_WASTE, " + 
		    			"RISK_NUMBER, " + 
		    			"RISK_REMARK, " + 
		    			"START_DATE, " + 
		    			"END_DATE, " +
		    			"ASSIGN_TO, " +
		    			"CREATED_DATE, " +
		    			"CREATED_BY, " +
		    			"IS_DELETED, " + 
		    			"VERSION " + 
		    			")VALUES( " + 
		    			"?, " + 
		    			"?, " + 
		    			"?, " +  
		    			"?, " + 
		    			"?, " + 
		    			"?, " +  
		    			"?, " + 
		    			"?, " +  
		    			"?, " + 
		    			"?, " + 
		    			"?, " + 
		    			"?, " + 
		    			"?, " + 
		    			"?, " + 
		    			"?, " +
		    			"sysdate, " + 
		    			"?, " + 
		    			"?, " + 
		    			"?) ",new Object[] {
		    					id,
		    					vo.getIdHead(),
		    					vo.getOfficeName(),
		    					vo.getOfficeCode(),
		    					vo.getRiskNumber(),
		    					vo.getRiskNumber(),
		    					vo.getRiskRemark(),
		    					vo.getRiskPersen(),
		    					vo.getBillAll(),
		    					vo.getBillWaste(),
		    					vo.getRiskNumber(),
		    					vo.getRiskRemark(),
		    					vo.getStartDate(),
		    					vo.getEndDate(),
		    					"user00",
		    					UserLoginUtils.getCurrentUserBean().getUsername(),
		    					"N",
		    					"1"});
		    	  
		    	return id;
		}
	    
}
