package th.co.baiwa.excise.cop.persistence.dao;

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

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.cop.persistence.vo.Cop0711FormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop0711Vo;
import th.co.baiwa.excise.cop.persistence.vo.Cop071FormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop071Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int09TableDtlVo;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class CopCheckFiscalYearDao {

	private static final Logger log = LoggerFactory.getLogger(CopCheckFiscalYearDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String SQL_COP_CHECK_FISCAL_YEAR = " SELECT * FROM COP_CHECK_FISCAL_YEAR WHERE IS_DELETED='N' ";
	private final String SQL_COP_CHECK_FISCAL_YEAR_DTL = " SELECT * FROM COP_CHECK_FISCAL_YEAR_DTL WHERE IS_DELETED='N' ";
	
	
	public Long countCop071(Cop071FormVo formVo) {
		
		StringBuilder sql = new StringBuilder(SQL_COP_CHECK_FISCAL_YEAR);
		List<Object> params = new ArrayList<>();
		

		if (StringUtils.isNotBlank(formVo.getFiscalYear())) {
			sql.append(" AND  SUBSTR(FISCAL_YEAR,4,4)=?");
			params.add(formVo.getFiscalYear());
		}
		

		String countSql = OracleUtils.countForDatatable(sql);
        Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
        return count;
    }

	public List<Cop071Vo> findAllCop071(Cop071FormVo formVo) {
		
		StringBuilder sql = new StringBuilder(SQL_COP_CHECK_FISCAL_YEAR);
		List<Object> params = new ArrayList<>();


		if (StringUtils.isNotBlank(formVo.getFiscalYear())) {
			sql.append(" AND  SUBSTR(FISCAL_YEAR,4,4)=?");
			params.add(formVo.getFiscalYear());
		}

		log.info("findAllCop071 sql : {}",sql);
        List<Cop071Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), cop071Rowmapper);
        return list;
    }
	
	 private RowMapper<Cop071Vo> cop071Rowmapper = new RowMapper<Cop071Vo>() {
	    	@Override
	    	public Cop071Vo mapRow(ResultSet rs, int arg1) throws SQLException {
	    		Cop071Vo vo = new Cop071Vo();
	    
	    	    vo.setId(rs.getLong("ID"));
	    	    vo.setFiscalYear(rs.getString("FISCAL_YEAR"));
	    		vo.setAsPlanNumber(rs.getInt("AS_PLAN_NUMBER"));
	    		vo.setAsPlanSuccess(rs.getInt("AS_PLAN_SUCCESS"));
	    		vo.setAsPlanWait(rs.getInt("AS_PLAN_WAIT"));
	    		vo.setOutsidePlanNumber(rs.getInt("OUTSIDE_PLAN_NUMBER"));
	    		vo.setOutsidePlanSuccess(rs.getInt("OUTSIDE_PLAN_SUCCESS"));
	    		vo.setOutsidePlanWait(rs.getInt("OUTSIDE_PLAN_WAIT"));
	    	    	
	    		return vo;
	    	}
	    };
	    
		public void editCop071 (Cop071Vo vo) {

	    	jdbcTemplate.update(" UPDATE COP_CHECK_FISCAL_YEAR SET " + 
	    		    "AS_PLAN_NUMBER=?," +
	    		    "AS_PLAN_SUCCESS=?," +
	    		    "AS_PLAN_WAIT=?," +
	    		    "OUTSIDE_PLAN_NUMBER=?," +
	    		    "OUTSIDE_PLAN_SUCCESS=?," +
	    			"OUTSIDE_PLAN_WAIT=? WHERE ID = ?",new Object[] {
	    					vo.getAsPlanNumber(),
	    		    		vo.getAsPlanSuccess(),
	    		    		vo.getAsPlanWait(),
	    		    		vo.getOutsidePlanNumber(),
	    		    		vo.getOutsidePlanSuccess(),
	    		    		vo.getOutsidePlanWait(),
	    					vo.getId()});
	    	}
		
	    public Long addCop071 (Cop071Vo vo) {
	    	Long id = jdbcTemplate.queryForObject(" SELECT COP_CHECK_FISCAL_YEAR_SEQ.NEXTVAL FROM dual ",Long.class);

	    	jdbcTemplate.update(" INSERT INTO COP_CHECK_FISCAL_YEAR( " + 
	    			"ID, " + 
	    			"FISCAL_YEAR, " +
	    			"AS_PLAN_NUMBER," +
	    		    "AS_PLAN_SUCCESS," +
	    		    "AS_PLAN_WAIT," +
	    		    "OUTSIDE_PLAN_NUMBER," +
	    		    "OUTSIDE_PLAN_SUCCESS," +
	    			"OUTSIDE_PLAN_WAIT, "+ 
	    			"IS_DELETED "+ 
	    			")VALUES( " + 
	    			"?, " + 
	    			"?, " + 
	    			"?, " + 
	    			"?, " + 
	    			"?, " + 
	    			"?, " + 
	    			"?, " + 
	    			"?, " + 
	    			"?) ",new Object[] {
	    					id,
	    					vo.getFiscalYear(),
	    					vo.getAsPlanNumber(),
	    		    		0,
	    		    		vo.getAsPlanWait(),
	    		    		vo.getOutsidePlanNumber(),
	    		    		0,
	    		    		vo.getOutsidePlanWait(),
	    		    		"N"});
	    	
	    	return id;
	}
	    
	    public Long countCop0711(Cop0711FormVo formVo) {
			
			StringBuilder sql = new StringBuilder(SQL_COP_CHECK_FISCAL_YEAR_DTL);
			List<Object> params = new ArrayList<>();
			

			if (!BeanUtils.isEmpty(formVo.getId())) {
				sql.append(" AND  ID_MASTER = ? ");
				params.add(formVo.getId());
			}
			if (!BeanUtils.isEmpty(formVo.getActionPlan())) {
				sql.append(" AND  ACTION_PLAN = ? ");
				params.add(formVo.getActionPlan());
			}
			

			String countSql = OracleUtils.countForDatatable(sql);
	        Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
	        return count;
	    }

		public List<Cop0711Vo> findAllCop0711(Cop0711FormVo formVo) {
			
			StringBuilder sql = new StringBuilder(SQL_COP_CHECK_FISCAL_YEAR_DTL);
			List<Object> params = new ArrayList<>();


			if (!BeanUtils.isEmpty(formVo.getId())) {
				sql.append(" AND  ID_MASTER = ? ");
				params.add(formVo.getId());
			}
			if (!BeanUtils.isEmpty(formVo.getActionPlan())) {
				sql.append(" AND  ACTION_PLAN = ? ");
				params.add(formVo.getActionPlan());
			}
			log.info("findAllCop0711 sql : {}",sql);
			
	        List<Cop0711Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), cop0711Rowmapper);
	        return list;
	    }
		
		 private RowMapper<Cop0711Vo> cop0711Rowmapper = new RowMapper<Cop0711Vo>() {
		    	@Override
		    	public Cop0711Vo mapRow(ResultSet rs, int arg1) throws SQLException {
		    		Cop0711Vo vo = new Cop0711Vo();
		    
		    	    vo.setId(rs.getLong("ID"));
		    	    vo.setIdMaster(rs.getLong("ID_MASTER"));
		    	    
		    		vo.setFiscalyear(rs.getString("FISCALYEAR"));
		    		vo.setEntrepreneurNo(rs.getString("ENTREPRENEUR_NO"));
		    		vo.setEntrepreneurName(rs.getString("ENTREPRENEUR_NAME"));
		    		vo.setEntrepreneurLoca(rs.getString("ENTREPRENEUR_LOCA"));
		    		vo.setCheckDate(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("CHECK_DATE")));
		    		vo.setActionPlan(rs.getString("ACTION_PLAN"));
		    		vo.setStatus(rs.getString("STATUS"));
		    	    	
		    		return vo;
		    	}
		    };
		    
		    public Long saveDataCop0711 (Cop0711Vo vo) {
		    	Long id = jdbcTemplate.queryForObject(" SELECT COP_CHECK_FISCAL_YEAR_DTL_SEQ.NEXTVAL FROM dual ",Long.class);

		    	jdbcTemplate.update(" INSERT INTO COP_CHECK_FISCAL_YEAR_DTL( " + 
		    			"ID, " + 
		    			"ID_MASTER, " + 
		    			"FISCALYEAR, " + 
		    			"ENTREPRENEUR_NO, " +
		    			"ENTREPRENEUR_NAME, " +
		    			"ENTREPRENEUR_LOCA, " +
		    			"CHECK_DATE, " +
		    			"ACTION_PLAN, " + 
		    			"STATUS, " + 
		    			"IS_DELETED "+ 
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
		    			"?) ",new Object[] {
		    					id,
		    					vo.getIdMaster(),
		    					vo.getFiscalyear(),
		    					vo.getEntrepreneurNo(),
		    					vo.getEntrepreneurName(),
		    					vo.getEntrepreneurLoca(),
		    					DateConstant.convertStrDDMMYYYYToDate(vo.getCheckDate()),
		    					vo.getActionPlan(),
		    					"1875",
		    					"N"});
		    	
		    	return id;
		}
			public void editDataCop0711 (Cop0711Vo vo) {

		    	jdbcTemplate.update(" UPDATE COP_CHECK_FISCAL_YEAR_DTL SET " + 
		    			"ENTREPRENEUR_NO=?, " +
		    			"ENTREPRENEUR_NAME=?, " +
		    			"ENTREPRENEUR_LOCA=?, " +
		    			"CHECK_DATE=?, " +
		    			"ACTION_PLAN=? WHERE ID = ? ",new Object[] {
		    					vo.getEntrepreneurNo(),
		    					vo.getEntrepreneurName(),
		    					vo.getEntrepreneurLoca(),
		    					DateConstant.convertStrDDMMYYYYToDate(vo.getCheckDate()),
		    					vo.getActionPlan(),
		    					vo.getId()
		    					});
		    	
		}
		    public void deleteCop0711 (Long id) {
		    	log.info(" deleteCop0711 ID : {}",id);
		    	jdbcTemplate.update(" UPDATE COP_CHECK_FISCAL_YEAR_DTL SET IS_DELETED = 'Y' WHERE ID = ? ",new Object[] {id});
		    }
		    
			public List<Cop0711Vo> getEntrepreneurCop0711(String entrepreneurNo) {
				
				StringBuilder sql = new StringBuilder(" SELECT * FROM TA_EXCISE_REGISTTION_NUMBER WHERE TA_EXCISE_ID = ? ");
				List<Object> params = new ArrayList<>();

				params.add(entrepreneurNo);
		
				log.info("getEntrepreneurCop0711 sql : {}",sql);
				
		        List<Cop0711Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), getEntrepreneurCop0711Rowmapper);
		        return list;
		    }
			
			 private RowMapper<Cop0711Vo> getEntrepreneurCop0711Rowmapper = new RowMapper<Cop0711Vo>() {
			    	@Override
			    	public Cop0711Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			    		Cop0711Vo vo = new Cop0711Vo();
			    
			    		vo.setEntrepreneurNo(rs.getString("TA_EXCISE_ID"));
			    		vo.setEntrepreneurName(rs.getString("TA_EXCISE_OPERATOR_NAME"));
			    		vo.setEntrepreneurLoca(rs.getString("TA_EXCISE_FAC_ADDRESS"));
			    		
			    		return vo;
			    	}
			    };
			    
			    public void updateStatusCopCheckFiscalYearDtl (String entrepreneurNo,String fiscalYear) {
			    	log.info(" Update Status Entrepreneur No : {}",entrepreneurNo);
			    	
			    	jdbcTemplate.update(" UPDATE COP_CHECK_FISCAL_YEAR_DTL SET STATUS = '1874' WHERE ENTREPRENEUR_NO = ? AND SUBSTR(FISCALYEAR,4,4) = ? ",new Object[] {entrepreneurNo,fiscalYear});
			    	
			    	Long id = jdbcTemplate.queryForObject(" SELECT ID_MASTER FROM COP_CHECK_FISCAL_YEAR_DTL WHERE ENTREPRENEUR_NO = ? AND SUBSTR(FISCALYEAR,4,4) = ? ",new Object[] {entrepreneurNo,fiscalYear},Long.class);
			    	
			    	log.info(" Update ID_MASTER : {}  entrepreneurNo {}  fiscalYear {}",id,entrepreneurNo,fiscalYear);
			    	
			    	String actionPlan = jdbcTemplate.queryForObject(" SELECT ACTION_PLAN FROM COP_CHECK_FISCAL_YEAR_DTL WHERE ENTREPRENEUR_NO = ? AND SUBSTR(FISCALYEAR,4,4) = ? ",new Object[] {entrepreneurNo,fiscalYear},String.class);
			    	
			    	if("1871".equals(actionPlan)) {
			    		
			    		jdbcTemplate.update(" UPDATE COP_CHECK_FISCAL_YEAR SET "
			    				+ "AS_PLAN_SUCCESS = (SELECT AS_PLAN_SUCCESS FROM COP_CHECK_FISCAL_YEAR WHERE ID = ? )+1,"
			    				+ "AS_PLAN_WAIT = (SELECT AS_PLAN_WAIT FROM COP_CHECK_FISCAL_YEAR WHERE ID = ? )-1 "
			    				+ "WHERE ID =  ? ",new Object[] {id,id,id});
			    		
			    	}else if("1872".equals(actionPlan)) {
			    		
			    		jdbcTemplate.update(" UPDATE COP_CHECK_FISCAL_YEAR SET "
			    				+ "OUTSIDE_PLAN_SUCCESS = (SELECT OUTSIDE_PLAN_SUCCESS FROM COP_CHECK_FISCAL_YEAR WHERE ID = ? )+1,"
			    				+ "OUTSIDE_PLAN_WAIT = (SELECT OUTSIDE_PLAN_WAIT FROM COP_CHECK_FISCAL_YEAR WHERE ID = ? )-1 "
			    				+ "WHERE ID =  ? ",new Object[] {id,id,id});
			    		
			    	}
			    	
			    	
			    }
			    
			 
	    
}
