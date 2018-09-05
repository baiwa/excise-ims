package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.jfree.util.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.vo.Int091FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int091Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int09222FormVo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class IaTravelEstimatorDao {

	private static final Logger log = LoggerFactory.getLogger(IaTravelEstimatorDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String SQL_TRAVEL_ESTIMATOR_PROCESS = "SELECT * FROM TRAVEL_ESTIMATOR_PROCESS WHERE IS_DELETED='N' ";
	
	private final String SQL_PROOF_OF_PAYMENT_PROCESS = "SELECT * FROM PROOF_OF_PAYMENT_PROCESS WHERE IS_DELETED='N' ";
	
	public Long count(Int091FormVo formVo) {
		
		StringBuilder sql = new StringBuilder(SQL_TRAVEL_ESTIMATOR_PROCESS);
		List<Object> params = new ArrayList<>();
		


		if (StringUtils.isNotBlank(formVo.getDateFrom())) {
			sql.append(" AND  TO_CHAR(CREATED_DATE, 'DD/MM/YYYY') >= TO_CHAR( ? ,'DD/MM/YYYY') ");
			Date date = DateConstant.convertStrDD_MM_YYYYToDate(formVo.getDateFrom());
			params.add(date);
		}
		if (StringUtils.isNotBlank(formVo.getDateTo())) {
			sql.append(" AND  TO_CHAR(CREATED_DATE, 'DD/MM/YYYY') <= TO_CHAR( ? ,'DD/MM/YYYY') ");
			Date date = DateConstant.convertStrDD_MM_YYYYToDate(formVo.getDateTo());
			params.add(date);
		}
		
		if (StringUtils.isNotBlank(formVo.getCreatedBy())) {
			sql.append(" AND CREATED_BY LIKE ?");
			params.add("%"+formVo.getCreatedBy()+"%");
		}
		if (StringUtils.isNotBlank(formVo.getPickedType())) {
			sql.append(" AND PICKED_TYPE = ?");
			params.add(formVo.getPickedType());
		}
		if (StringUtils.isNotBlank(formVo.getFiscalYear())) {
			sql.append(" AND FISCAL_YEAR = ?");
			params.add(formVo.getFiscalYear());
		}
		if (StringUtils.isNotBlank(formVo.getDepartureDate())) {
			sql.append(" AND  TO_CHAR(DEPARTURE_DATE, 'DD/MM/YYYY') <= TO_CHAR( ? ,'DD/MM/YYYY') ");
			Date date = DateConstant.convertStrDD_MM_YYYYToDate(formVo.getDepartureDate());
			params.add(date);
		}
		if (StringUtils.isNotBlank(formVo.getReturnDate())) {
			sql.append(" AND  TO_CHAR(RETURN_DATE, 'DD/MM/YYYY') <= TO_CHAR( ? ,'DD/MM/YYYY') ");
			Date date = DateConstant.convertStrDD_MM_YYYYToDate(formVo.getReturnDate());
			params.add(date);
		}
		if (StringUtils.isNotBlank(formVo.getTravelTo())) {
			sql.append(" AND TRAVEL_TO = ?");
			params.add(formVo.getTravelTo());
		}		
		if (StringUtils.isNotBlank(formVo.getStatus())) {
			sql.append(" AND STATUS = ?");
			params.add(formVo.getStatus());
		}
		String countSql = OracleUtils.countForDatatable(sql);
        Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
        return count;
    }

	public List<Int091Vo> findAll(Int091FormVo formVo) {
		
		StringBuilder sql = new StringBuilder(SQL_TRAVEL_ESTIMATOR_PROCESS);
		List<Object> params = new ArrayList<>();


		if (StringUtils.isNotBlank(formVo.getDateFrom())) {
			sql.append(" AND  TO_CHAR(CREATED_DATE, 'DD/MM/YYYY') >= TO_CHAR( ? ,'DD/MM/YYYY') ");
			Date date = DateConstant.convertStrDD_MM_YYYYToDate(formVo.getDateFrom());
			params.add(date);
		}
		if (StringUtils.isNotBlank(formVo.getDateTo())) {
			sql.append(" AND  TO_CHAR(CREATED_DATE, 'DD/MM/YYYY') <= TO_CHAR( ? ,'DD/MM/YYYY') ");
			Date date = DateConstant.convertStrDD_MM_YYYYToDate(formVo.getDateTo());
			params.add(date);
		}
		
		if (StringUtils.isNotBlank(formVo.getCreatedBy())) {
			sql.append(" AND CREATED_BY LIKE ?");
			params.add("%"+formVo.getCreatedBy()+"%");
		}
		if (StringUtils.isNotBlank(formVo.getPickedType())) {
			sql.append(" AND PICKED_TYPE = ?");
			params.add(formVo.getPickedType());
		}
		if (StringUtils.isNotBlank(formVo.getFiscalYear())) {
			sql.append(" AND FISCAL_YEAR = ?");
			params.add(formVo.getFiscalYear());
		}
		if (StringUtils.isNotBlank(formVo.getDepartureDate())) {
			sql.append(" AND  TO_CHAR(DEPARTURE_DATE, 'DD/MM/YYYY') <= TO_CHAR( ? ,'DD/MM/YYYY') ");
			Date date = DateConstant.convertStrDD_MM_YYYYToDate(formVo.getDepartureDate());
			params.add(date);
		}
		if (StringUtils.isNotBlank(formVo.getReturnDate())) {
			sql.append(" AND  TO_CHAR(RETURN_DATE, 'DD/MM/YYYY') <= TO_CHAR( ? ,'DD/MM/YYYY') ");
			Date date = DateConstant.convertStrDD_MM_YYYYToDate(formVo.getReturnDate());
			params.add(date);
		}
		if (StringUtils.isNotBlank(formVo.getTravelTo())) {
			sql.append(" AND TRAVEL_TO = ?");
			params.add(formVo.getTravelTo());
		}		
		if (StringUtils.isNotBlank(formVo.getStatus())) {
			sql.append(" AND STATUS = ?");
			params.add(formVo.getStatus());
		}
		
		
		sql.append(" ORDER BY CREATED_DATE desc ");
        List<Int091Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), travelEstimatorProcessRowmapper);
        return list;
    }
	
	 private RowMapper<Int091Vo> travelEstimatorProcessRowmapper = new RowMapper<Int091Vo>() {
	    	@Override
	    	public Int091Vo mapRow(ResultSet rs, int arg1) throws SQLException {
	    		Int091Vo vo = new Int091Vo();
	    
	    	    vo.setId(rs.getLong("ID"));
	    	    vo.setCreatedDate(DateConstant.convertDateToStrDD_MM_YYYY_HH_mm(rs.getDate("CREATED_DATE")));
	    	    vo.setCreatedBy(rs.getString("CREATED_BY"));
	    	    vo.setPickedType(rs.getString("PICKED_TYPE"));
	    	    vo.setFiscalYear(rs.getString("FISCAL_YEAR"));
	    	    vo.setDepartureDate(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("DEPARTURE_DATE")));
	    	    vo.setReturnDate(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("RETURN_DATE")));
	    	    vo.setTravelTo(rs.getString("TRAVEL_TO"));
	    	    vo.setTravelToDescription(rs.getString("TRAVEL_TO_DESCRIPTION"));
	    	    vo.setStatus(rs.getString("STATUS"));
	    	    vo.setIsDeleted(rs.getString("IS_DELETED"));
	    	    vo.setErrorMsg(DateConstant.convertDateToStrDD_MM_YYYY_HH_mm(rs.getDate("ERROR_MSG")));
	    	    	
	    		return vo;
	    	}
	    };
	    
	    public void delete (Long id) {
	    	jdbcTemplate.update(" UPDATE PROOF_OF_PAYMENT_PROCESS SET IS_DELETED = 'Y' WHERE ID = ? ",new Object[] {id});
	    }
	    public Long add091 (Int091FormVo vo) {
	    	Long id = jdbcTemplate.queryForObject(" SELECT TRAVEL_ESTIMATOR_PROCESS_SEQ.NEXTVAL FROM dual ",long.class);
	    	
	    	vo.setTravelToDescription(getTravelToDescription(Integer.parseInt(vo.getTravelTo())));
	    	
	    	jdbcTemplate.update(" INSERT INTO TRAVEL_ESTIMATOR_PROCESS( " + 
	    			"ID, " + 
	    			"CREATED_DATE, " + 
	    			"CREATED_BY, " + 
	    			"PICKED_TYPE, " + 
	    			"FISCAL_YEAR,"+ 
	    			"DEPARTURE_DATE,"+ 
	    			"RETURN_DATE,"+ 
	    			"TRAVEL_TO,"+ 
	    			"TRAVEL_TO_DESCRIPTION,"+ 
	    			"STATUS,"+ 
	    			"IS_DELETED "+ 
	    			")VALUES( " + 
	    			"?, " + 
	    			"sysdate, " + 
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
	    					vo.getCreatedBy(),
	    					vo.getPickedType(),
	    					vo.getFiscalYear(),
	    					DateConstant.convertStrDDMMYYYYToDate(vo.getDepartureDate()),
	    					DateConstant.convertStrDDMMYYYYToDate(vo.getReturnDate()),
	    					vo.getTravelTo(),
	    					vo.getTravelToDescription(),
	    					"1166",
	    					"N"});
	    	return id;
			    }

	    public String getTravelToDescription(int id) {
	    	String s = "";
	     	log.info(" idLong : {}",id);
	    	s = jdbcTemplate.queryForObject("SELECT SUB_TYPE_DESCRIPTION FROM SYS_LOV WHERE TYPE='SECTOR_VALUE' AND LOV_ID = ? ",new Object[] {id}, String.class);
	    	log.info(" travelToDescription : ",s);
	   
	    	return s;
	    }
  
		}
