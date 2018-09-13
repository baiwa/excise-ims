package th.co.baiwa.excise.ia.persistence.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
import th.co.baiwa.excise.ia.persistence.vo.Int09111FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int09TableDtlVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0911FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0911T2Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int0911Vo;
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
	
	private final String SQL_TRAVEL_ESTIMATOR_DOCUMENT = "SELECT * FROM TRAVEL_ESTIMATOR_DOCUMENT WHERE IS_DELETED='N' AND ID_PROCESS = ?";
	
	private final String SQL_TRAVEL_ESTIMATOR_EVIDENCE = "SELECT * FROM TRAVEL_ESTIMATOR_EVIDENCE WHERE IS_DELETED='N' AND ID_PROCESS = ?";
	
	private final String SQL_TRAVEL_ESTIMATOR_DTL = "SELECT * FROM TRAVEL_ESTIMATOR_DTL WHERE ID_PROCESS = ?";
	
	public Long count(Int091FormVo formVo) {
		
		StringBuilder sql = new StringBuilder(SQL_TRAVEL_ESTIMATOR_PROCESS);
		List<Object> params = new ArrayList<>();
		


		if (StringUtils.isNotBlank(formVo.getDateFrom())) {
			sql.append(" AND  TO_CHAR(CREATED_DATE, 'DD/MM/YYYY') >= TO_CHAR( ? ,'DD/MM/YYYY') ");
			Date date = DateConstant.convertStrDDMMYYYYToDate(formVo.getDateFrom());
			params.add(date);
		}
		if (StringUtils.isNotBlank(formVo.getDateTo())) {
			sql.append(" AND  TO_CHAR(CREATED_DATE, 'DD/MM/YYYY') <= TO_CHAR( ? ,'DD/MM/YYYY') ");
			Date date = DateConstant.convertStrDDMMYYYYToDate(formVo.getDateTo());
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
			Date date = DateConstant.convertStrDDMMYYYYToDate(formVo.getDepartureDate());
			params.add(date);
		}
		if (StringUtils.isNotBlank(formVo.getReturnDate())) {
			sql.append(" AND  TO_CHAR(RETURN_DATE, 'DD/MM/YYYY') <= TO_CHAR( ? ,'DD/MM/YYYY') ");
			Date date = DateConstant.convertStrDDMMYYYYToDate(formVo.getReturnDate());
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
			Date date = DateConstant.convertStrDDMMYYYYToDate(formVo.getDateFrom());
			params.add(date);
		}
		if (StringUtils.isNotBlank(formVo.getDateTo())) {
			sql.append(" AND  TO_CHAR(CREATED_DATE, 'DD/MM/YYYY') <= TO_CHAR( ? ,'DD/MM/YYYY') ");
			Date date = DateConstant.convertStrDDMMYYYYToDate(formVo.getDateTo());
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
			Date date = DateConstant.convertStrDDMMYYYYToDate(formVo.getDepartureDate());
			params.add(date);
		}
		if (StringUtils.isNotBlank(formVo.getReturnDate())) {
			sql.append(" AND  TO_CHAR(RETURN_DATE, 'DD/MM/YYYY') <= TO_CHAR( ? ,'DD/MM/YYYY') ");
			Date date = DateConstant.convertStrDDMMYYYYToDate(formVo.getReturnDate());
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
	    	    vo.setCreatedDate(DateConstant.convertDateToStrDDMMYYYYHHmm(rs.getDate("CREATED_DATE")));
	    	    vo.setCreatedBy(rs.getString("CREATED_BY"));
	    	    vo.setPickedType(rs.getString("PICKED_TYPE"));
	    	    vo.setFiscalYear(rs.getString("FISCAL_YEAR"));
	    	    vo.setDepartureDate(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("DEPARTURE_DATE")));
	    	    vo.setReturnDate(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("RETURN_DATE")));
	    	    vo.setTravelTo(rs.getString("TRAVEL_TO"));
	    	    vo.setTravelToDescription(rs.getString("TRAVEL_TO_DESCRIPTION"));
	    	    vo.setStatus(rs.getString("STATUS"));
	    	    vo.setIsDeleted(rs.getString("IS_DELETED"));
	    	    vo.setErrorMsg(rs.getString("ERROR_MSG"));
	    	    	
	    		return vo;
	    	}
	    };
	    
	    public void delete (Long id) {
	    	log.info(" idLong : {}",id);
	    	jdbcTemplate.update(" UPDATE TRAVEL_ESTIMATOR_PROCESS SET IS_DELETED = 'Y' WHERE ID = ? ",new Object[] {id});
	    }
	    public Long add091 (Int091FormVo vo) {
	    	Long id = jdbcTemplate.queryForObject(" SELECT TRAVEL_ESTIMATOR_PROCESS_SEQ.NEXTVAL FROM dual ",long.class);
	    	
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

	    
	    
	    public Int0911FormVo gethead(Int0911FormVo formVo) {
			StringBuilder sql = new StringBuilder(" SELECT * FROM TRAVEL_ESTIMATOR_PROCESS WHERE ID = ? ");
			Int0911FormVo data = new Int0911FormVo();
			log.info("formVo.getIdProcess() : {}",formVo.getIdProcess());
			
	        List<Int0911FormVo> list = jdbcTemplate.query(sql.toString(),new Object[] {formVo.getIdProcess()} , getheadRowmapper);
	       
	        log.info("list.get(0).getTravelTo() : {}",list.get(0).getTravelTo());
	    	data.setPickedType(list.get(0).getPickedType());
	    	data.setFiscalYear(list.get(0).getFiscalYear());
	    	data.setDepartureDate(list.get(0).getDepartureDate());
	    	data.setReturnDate(list.get(0).getReturnDate());
	    	data.setTravelTo(list.get(0).getTravelTo());
	    	data.setTravelToDescription(list.get(0).getTravelToDescription());
	        
	        return data;
	    }
		
		 private RowMapper<Int0911FormVo> getheadRowmapper = new RowMapper<Int0911FormVo>() {
		    	@Override
		    	public Int0911FormVo mapRow(ResultSet rs, int arg1) throws SQLException {
		    		Int0911FormVo vo = new Int0911FormVo();
		
		    	    vo.setId(rs.getLong("ID"));
		    	    vo.setPickedType(rs.getString("PICKED_TYPE"));
		    	    vo.setFiscalYear(rs.getString("FISCAL_YEAR"));
		    	    vo.setDepartureDate(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("DEPARTURE_DATE")));
		    	    vo.setReturnDate(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("RETURN_DATE")));
		    	    vo.setTravelTo(rs.getString("TRAVEL_TO"));
		    	    vo.setTravelToDescription(rs.getString("TRAVEL_TO_DESCRIPTION"));
		    	    	
		    		return vo;
		    	}
		    };
	    
		public Long count0911(Int0911FormVo formVo) {
			StringBuilder sql = new StringBuilder(SQL_TRAVEL_ESTIMATOR_DOCUMENT);
			List<Object> params = new ArrayList<>();
			
			params.add(formVo.getIdProcess());
				
			String countSql = OracleUtils.countForDatatable(sql);
	        Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
	        return count;
	    }

		public List<Int0911Vo> findAll0911(Int0911FormVo formVo) {
			StringBuilder sql = new StringBuilder(SQL_TRAVEL_ESTIMATOR_DOCUMENT);
			List<Object> params = new ArrayList<>();

			params.add(formVo.getIdProcess());

			sql.append(" ORDER BY CREATED_DATE desc ");
	        List<Int0911Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), travelEstimatorDocumentRowmapper);
	        return list;
	    }
		
		 private RowMapper<Int0911Vo> travelEstimatorDocumentRowmapper = new RowMapper<Int0911Vo>() {
		    	@Override
		    	public Int0911Vo mapRow(ResultSet rs, int arg1) throws SQLException {
		    		Int0911Vo vo = new Int0911Vo();
		
		    	    vo.setId(rs.getLong("ID"));
		    	    vo.setCreatedDate(DateConstant.convertDateToStrDDMMYYYYHHmm(rs.getDate("CREATED_DATE")));
		    	    vo.setCreatedBy(rs.getString("CREATED_BY"));
		    	    vo.setDocumentType(rs.getString("DOCUMENT_TYPE"));
		    	    vo.setSubject(rs.getString("SUBJECT"));
		    	    vo.setStatus(rs.getString("STATUS"));
		    	    vo.setIsDeleted(rs.getString("IS_DELETED"));
		    	    vo.setErrorMsg(rs.getString("ERROR_MSG"));
		    	    	
		    		return vo;
		    	}
		    };
		    public Long count09111(Int09111FormVo formVo) {
				StringBuilder sql = new StringBuilder(SQL_TRAVEL_ESTIMATOR_DTL);
				List<Object> params = new ArrayList<>();
				
				params.add(formVo.getIdProcess());
					
				String countSql = OracleUtils.countForDatatable(sql);
		        Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		        return count;
		    }

			public List<Int09TableDtlVo> findAll09111(Int09111FormVo formVo) {
				StringBuilder sql = new StringBuilder(SQL_TRAVEL_ESTIMATOR_DTL);
				List<Object> params = new ArrayList<>();

				params.add(formVo.getIdProcess());
				sql.append(" ORDER BY ID desc ");
				
				log.info(" findAll09111 idProcess : {}",formVo.getIdProcess());
				log.info(" findAll09111 sql : {}",sql.toString());
				
		        List<Int09TableDtlVo> list = jdbcTemplate.query(sql.toString(), params.toArray(), travelEstimatorDtlRowmapper);
		        return list;
		    }
			
			 private RowMapper<Int09TableDtlVo> travelEstimatorDtlRowmapper = new RowMapper<Int09TableDtlVo>() {
			    	@Override
			    	public Int09TableDtlVo mapRow(ResultSet rs, int arg1) throws SQLException {
			    		Int09TableDtlVo vo = new Int09TableDtlVo();
			    		
			    	    vo.setId(rs.getLong("ID"));
			    		vo.setIdProcess(rs.getLong("ID_PROCESS"));
			    		vo.setName(rs.getString("NAME"));
			    		vo.setPosition(rs.getString("POSITION"));
			    		vo.setFeedDay(rs.getBigDecimal("FEED_DAY"));
			    		vo.setFeedMoney(rs.getBigDecimal("FEED_MONEY"));
			    		vo.setRoostDay(rs.getBigDecimal("ROOST_DAY"));
			    		vo.setRoostMoney(rs.getBigDecimal("ROOST_MONEY"));
			    		vo.setPassage(rs.getBigDecimal("PASSAGE"));
			    		vo.setOtherExpenses(rs.getBigDecimal("OTHER_EXPENSES"));
			    		vo.setTotalMoney(rs.getBigDecimal("TOTAL_MONEY"));
			    		vo.setRemark(rs.getString("REMARK"));
			    	    	
			    		return vo;
			    	}
			    };
			    
		    
		    public Long count0911T2(Int0911FormVo formVo) {
				StringBuilder sql = new StringBuilder(SQL_TRAVEL_ESTIMATOR_EVIDENCE);
				List<Object> params = new ArrayList<>();
				
				params.add(formVo.getIdProcess());
					
				String countSql = OracleUtils.countForDatatable(sql);
		        Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		        return count;
		    }

			public List<Int0911T2Vo> findAll0911T2(Int0911FormVo formVo) {
				StringBuilder sql = new StringBuilder(SQL_TRAVEL_ESTIMATOR_EVIDENCE);
				List<Object> params = new ArrayList<>();

				params.add(formVo.getIdProcess());

				sql.append(" ORDER BY CREATED_DATE desc ");
		        List<Int0911T2Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), travelEstimatorDocumentT2Rowmapper);
		        return list;
		    }
			
			 private RowMapper<Int0911T2Vo> travelEstimatorDocumentT2Rowmapper = new RowMapper<Int0911T2Vo>() {
			    	@Override
			    	public Int0911T2Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			    		Int0911T2Vo vo = new Int0911T2Vo();
			
			    	    vo.setId(rs.getLong("ID"));
			    	    vo.setCreatedDate(DateConstant.convertDateToStrDDMMYYYYHHmm(rs.getDate("CREATED_DATE")));
			    	    vo.setCreatedBy(rs.getString("CREATED_BY"));
			    	    vo.setDocumentName(rs.getString("DOCUMENT_NAME"));
			    	    vo.setDocumantSize(rs.getString("DOCUMANT_SIZE"));
			    	    vo.setStatus(rs.getString("STATUS"));
			    	    vo.setIsDeleted(rs.getString("IS_DELETED"));
			    	    vo.setErrorMsg(rs.getString("ERROR_MSG"));
			    	    	
			    		return vo;
			    	}
			    };
		    
		    public void delete0911(Long id) {
		    	log.info(" idLong : {}",id);
		    	jdbcTemplate.update(" UPDATE TRAVEL_ESTIMATOR_DOCUMENT SET IS_DELETED = 'Y' WHERE ID = ? ",new Object[] {id});
		    }
  
		    public void delete0911T2(Long id) {
		    	log.info(" idLong : {}",id);
		    	jdbcTemplate.update(" UPDATE TRAVEL_ESTIMATOR_EVIDENCE SET IS_DELETED = 'Y' WHERE ID = ? ",new Object[] {id});
		    }


		    public Long addDocument (Long idProcess,String createdBy,String documentType,String subject) {
		    	Long id = jdbcTemplate.queryForObject(" SELECT TRAVEL_ESTIMATOR_DOCUMENT_SEQ.NEXTVAL FROM dual ",Long.class);
	
		    	jdbcTemplate.update(" INSERT INTO TRAVEL_ESTIMATOR_DOCUMENT( " + 
		    			"ID, " + 
		    			"ID_PROCESS, " + 
		    			"CREATED_DATE, " + 
		    			"CREATED_BY, " + 
		    			"DOCUMENT_TYPE, " + 
		    			"SUBJECT,"+ 
		    			"STATUS,"+ 
		    			"IS_DELETED, "+ 
		    			"ERROR_MSG "+ 
		    			")VALUES( " + 
		    			"?, " + 
		    			"?, " + 
		    			"sysdate, " + 
		    			"?, " + 
		    			"?, " + 
		    			"?, " + 
		    			"?, " + 
		    			"?, " + 
		    			"?) ",new Object[] {
		    					id,
		    					idProcess,
		    					createdBy,
		    					documentType,
		    					subject,
		    					"1166",
		    					"N",
		    					"NO"});
		    	return id;
		}
		    
		    public Long saveDataDtl (Int09TableDtlVo vo) {
		    	Long id = jdbcTemplate.queryForObject(" SELECT TRAVEL_ESTIMATOR_DTL_SEQ.NEXTVAL FROM dual ",Long.class);
		    	vo.setTotalMoney(vo.getFeedMoney().add(vo.getRoostMoney()).add(vo.getPassage().add(vo.getOtherExpenses())));
		    	jdbcTemplate.update(" INSERT INTO TRAVEL_ESTIMATOR_DTL( " + 
		    			"ID, " + 
		    			"ID_PROCESS, " + 
		    			"NAME, " + 
		    			"POSITION, " + 
		    			"FEED_DAY, " + 
		    			"FEED_MONEY, " + 
		    			"ROOST_DAY, " + 
		    			"ROOST_MONEY, " + 
		    			"PASSAGE, " + 
		    			"OTHER_EXPENSES, " + 
		    			"TOTAL_MONEY, " + 
		    			"REMARK "+ 
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
		    			"?) ",new Object[] {
		    					id,
		    					vo.getIdProcess(),
		    					vo.getName(),
		    					vo.getPosition(),
		    					vo.getFeedDay(),
		    					vo.getFeedMoney(),
		    					vo.getRoostDay(),
		    					vo.getRoostMoney(),
		    					vo.getPassage(),
		    					vo.getOtherExpenses(),
		    					vo.getTotalMoney(),
		    					vo.getRemark()});
		    	
		    	return id;
		}
		    
		    
		    
		    public String addEvidence (Long idProcess,String createdBy,String documentName,String documantSize) {
		    	Long id = jdbcTemplate.queryForObject(" SELECT TRAVEL_ESTIMATOR_EVIDENCE_SEQ.NEXTVAL FROM dual ",long.class);
		    	String ext =  documentName.split("\\.")[1]; // get extension
		    	String name = documentName.split("\\.")[0]; // get name
		    	documentName= name+"_"+id.toString()+"_"+new SimpleDateFormat("dd-MM-yyyy").format(new Date())+"."+ext;
		    	jdbcTemplate.update(" INSERT INTO TRAVEL_ESTIMATOR_EVIDENCE( " + 
		    			"ID, " + 
		    			"ID_PROCESS, " + 
		    			"CREATED_DATE, " + 
		    			"CREATED_BY, " + 
		    			"DOCUMENT_NAME, " + 
		    			"DOCUMANT_SIZE,"+ 
		    			"STATUS,"+ 
		    			"IS_DELETED, "+ 
		    			"ERROR_MSG "+ 
		    			")VALUES( " + 
		    			"?, " + 
		    			"?, " + 
		    			"sysdate, " + 
		    			"?, " + 
		    			"?, " + 
		    			"?, " + 
		    			"?, " + 
		    			"?, " + 
		    			"?) ",new Object[] {
		    					id,
		    					idProcess,
		    					createdBy,
		    					documentName,
		    					documantSize,
		    					"1166",
		    					"N",
		    					"NO"});
		    	return documentName;
		}
		    
}
