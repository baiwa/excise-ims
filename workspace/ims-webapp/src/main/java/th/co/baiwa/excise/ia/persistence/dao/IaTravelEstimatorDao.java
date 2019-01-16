package th.co.baiwa.excise.ia.persistence.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.vo.Int09111And3FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0911FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int0911T2Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int0911Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int091FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int091Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int09FormDtlVo;
import th.co.baiwa.excise.ia.persistence.vo.Int09TableDtlVo;
import th.co.baiwa.buckwaframework.common.util.OracleUtils;

@Repository
public class IaTravelEstimatorDao {

	private static final Logger log = LoggerFactory.getLogger(IaTravelEstimatorDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String SQL_TRAVEL_ESTIMATOR_PROCESS = "SELECT * FROM TRAVEL_ESTIMATOR_PROCESS WHERE IS_DELETED='N' ";
	
	private final String SQL_TRAVEL_ESTIMATOR_DOCUMENT = "SELECT * FROM TRAVEL_ESTIMATOR_DOCUMENT WHERE IS_DELETED='N' AND ID_PROCESS = ?";
	
	private final String SQL_TRAVEL_ESTIMATOR_EVIDENCE = "SELECT * FROM TRAVEL_ESTIMATOR_EVIDENCE WHERE IS_DELETED='N' AND ID_PROCESS = ?";
	
	private final String SQL_TRAVEL_ESTIMATOR_DTL = "SELECT a.*,b.*,b.ID AS B_ID, b.NAME AS B_NAME,"
													+ " b.POSITION AS B_POSITION, b.PASSAGE AS B_PASSAGE, "
													+ " b.OTHER_EXPENSES AS B_OTHER_EXPENSES ,b.REMARK AS B_REMARK "
													+ " FROM TRAVEL_ESTIMATOR_DTL a inner join TRAVEL_ESTIMATOR_FORM_DTL b on a.id=b.id_dtl "
													+ " WHERE a.ID_PROCESS = ? AND a.DOCUMENT_TYPE = ?  AND IS_DELETED='N' ";
	
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
			
			if (StringUtils.isNotBlank(formVo.getPickedType())) {
				sql.append(" AND PICKED_TYPE = ?");
				params.add(formVo.getPickedType());
			}
			
			if (StringUtils.isNotBlank(formVo.getBudgetType())) {
				sql.append(" AND BUDGET_TYPE = ?");
				params.add(formVo.getBudgetType());
			}
				
			String countSql = OracleUtils.countForDatatable(sql);
	        Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
	        return count;
	    }

		public List<Int0911Vo> findAll0911(Int0911FormVo formVo) {
			StringBuilder sql = new StringBuilder(SQL_TRAVEL_ESTIMATOR_DOCUMENT);
			List<Object> params = new ArrayList<>();

			params.add(formVo.getIdProcess());
			
			if (StringUtils.isNotBlank(formVo.getPickedType())) {
				sql.append(" AND PICKED_TYPE = ?");
				params.add(formVo.getPickedType());
			}
			
			if (StringUtils.isNotBlank(formVo.getBudgetType())) {
				sql.append(" AND BUDGET_TYPE = ?");
				params.add(formVo.getBudgetType());
			}

			sql.append(" ORDER BY ID asc ");
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
		    	    vo.setPickedType(rs.getString("PICKED_TYPE"));
		    	    vo.setBudgetType(rs.getString("BUDGET_TYPE"));
		    	    vo.setStatus(rs.getString("STATUS"));
		    	    vo.setIsDeleted(rs.getString("IS_DELETED"));
		    	    vo.setErrorMsg(rs.getString("ERROR_MSG"));
		    	    	
		    		return vo;
		    	}
		    };
		    public Long countTableDtl(Int09111And3FormVo formVo) {
				StringBuilder sql = new StringBuilder(SQL_TRAVEL_ESTIMATOR_DTL);
				List<Object> params = new ArrayList<>();
				
				params.add(formVo.getIdProcess());
				params.add(formVo.getDocumentTypeCode());
					
				String countSql = OracleUtils.countForDatatable(sql);
		        Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		        return count;
		    }

			public List<Int09TableDtlVo> findAllTableDtl(Int09111And3FormVo formVo) {
				StringBuilder sql = new StringBuilder(SQL_TRAVEL_ESTIMATOR_DTL);
				List<Object> params = new ArrayList<>();

				params.add(formVo.getIdProcess());
				params.add(formVo.getDocumentTypeCode());
				
				sql.append(" ORDER BY a.ID asc ");
				
				log.info(" findAllTableDtl idProcess : {}",formVo.getIdProcess());
				log.info(" findAllTableDtl sql : {}",sql.toString());
				
		        List<Int09TableDtlVo> list = jdbcTemplate.query(sql.toString(), params.toArray(), travelEstimatorDtlRowmapper);
		        return list;
		    }
			
			 private RowMapper<Int09TableDtlVo> travelEstimatorDtlRowmapper = new RowMapper<Int09TableDtlVo>() {
			    	@Override
			    	public Int09TableDtlVo mapRow(ResultSet rs, int arg1) throws SQLException {
			    		Int09TableDtlVo vo = new Int09TableDtlVo();
			    		
			    	    vo.setId(rs.getLong("ID"));
			    		vo.setIdProcess(rs.getLong("ID_PROCESS"));
			    		vo.setDocumentType(rs.getString("DOCUMENT_TYPE"));
			    		vo.setName(rs.getString("NAME"));
			    		vo.setPosition(rs.getString("POSITION"));
			    		vo.setFeedDay(rs.getLong("FEED_DAY"));
			    		vo.setFeedMoney(rs.getBigDecimal("FEED_MONEY"));
			    		vo.setRoostDay(rs.getLong("ROOST_DAY"));
			    		vo.setRoostMoney(rs.getBigDecimal("ROOST_MONEY"));
			    		vo.setPassage(rs.getBigDecimal("PASSAGE"));
			    		vo.setOtherExpenses(rs.getBigDecimal("OTHER_EXPENSES"));
			    		vo.setTotalMoney(rs.getBigDecimal("TOTAL_MONEY"));
			    		vo.setRemark(rs.getString("REMARK"));
			    		
			    		Int09FormDtlVo formVo = new Int09FormDtlVo();
			    		
			    		formVo.setId(rs.getLong("B_ID"));
			    		formVo.setIdDtl(rs.getLong("ID_DTL"));
			    		formVo.setName(rs.getString("B_NAME"));
			    		formVo.setLastName(rs.getString("LAST_NAME"));
			    		formVo.setPosition(rs.getString("B_POSITION"));
			    		formVo.setType(rs.getString("TYPE"));
			    		formVo.setGrade(rs.getString("GRADE"));
			    		formVo.setPermissionDate(rs.getString("PERMISSION_DATE"));
			    		formVo.setWriteDate(rs.getString("WRITE_DATE"));
			    		formVo.setDepartureFrom(rs.getString("DEPARTURE_FROM"));
			    		formVo.setDepartureTo(rs.getString("DEPARTURE_TO"));
			    		formVo.setDepartureDate(rs.getString("DEPARTURE_DATE"));
			    		formVo.setReturnDate(rs.getString("RETURN_DATE"));
			    		
			    		formVo.setNumberDateAllowance(rs.getLong("NUMBER_DATE_ALLOWANCE"));
			    		formVo.setNumberHoursAllowance(rs.getLong("NUMBER_HOURS_ALLOWANCE"));
			    		formVo.setAllowanceR(rs.getBigDecimal("ALLOWANCE_R"));
			    		formVo.setAllowanceTotal(rs.getBigDecimal("ALLOWANCE_TOTAL"));
			    		
			    		formVo.setNumberDateRoost(rs.getLong("NUMBER_DATE_ROOST"));
			    		formVo.setRoostR(rs.getBigDecimal("ROOST_R"));
			    		formVo.setRoostTotal(rs.getBigDecimal("ROOST_TOTAL"));
			    		
			    		formVo.setPassage(rs.getBigDecimal("B_PASSAGE"));
			    		formVo.setOtherExpenses(rs.getBigDecimal("B_OTHER_EXPENSES"));
			    		formVo.setRemark(rs.getString("B_REMARK"));
			    		
			    		vo.setInt09FormDtlVo(formVo);
			    	    	
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
		    	log.info(" delete ID_PROCESS : {}",id);
		    	jdbcTemplate.update(" UPDATE TRAVEL_ESTIMATOR_DOCUMENT SET IS_DELETED = 'Y' WHERE ID = ? ",new Object[] {id});
		    }
  
		    public void delete0911T2(Long id) {
		    	log.info(" delete ID_PROCESS : {}",id);
		    	jdbcTemplate.update(" UPDATE TRAVEL_ESTIMATOR_EVIDENCE SET IS_DELETED = 'Y' WHERE ID = ? ",new Object[] {id});
		    }
		    public void approve0911(Long id,String status) {
		    	log.info(" delete ID_PROCESS : {}",id);
		    	jdbcTemplate.update(" UPDATE TRAVEL_ESTIMATOR_DOCUMENT SET STATUS = ? WHERE ID = ? ",new Object[] {status,id});
		    }
		    public void approve0911T2(Long id,String status) {
		    	log.info(" delete ID_PROCESS : {}",id);
		    	jdbcTemplate.update(" UPDATE TRAVEL_ESTIMATOR_EVIDENCE SET STATUS = ? WHERE ID = ? ",new Object[] {status,id});
		    }
		    
		    public void deleteTableDtl(Long id) {
		    	log.info(" delete ID_PROCESS : {}",id);
		    	jdbcTemplate.update(" UPDATE TRAVEL_ESTIMATOR_DTL SET IS_DELETED = 'Y' WHERE ID = ? ",new Object[] {id});
		    }
		    
		    public void deleteTravelEstimatorDtl(Long id,String documentType) {
		    	log.info(" delete ID_PROCESS : {}",id);
	
		    	 List<Long> listId = jdbcTemplate.query(" SELECT * FROM TRAVEL_ESTIMATOR_DTL t WHERE t.ID_PROCESS = ? AND t.DOCUMENT_TYPE= ? ",new Object[] {id,documentType},deleteTravelEstimatorDtlRowmapper);
		    	 for (Long idDtl : listId) {
		    		 jdbcTemplate.update(" DELETE FROM TRAVEL_ESTIMATOR_FORM_DTL t WHERE t.ID_DTL = ? ",new Object[] {idDtl});
				}
		    	 
		    	 jdbcTemplate.update(" DELETE FROM TRAVEL_ESTIMATOR_DTL t WHERE t.ID_PROCESS = ? AND t.DOCUMENT_TYPE= ? ",new Object[] {id,documentType});
			 }
				
			 private RowMapper<Long> deleteTravelEstimatorDtlRowmapper = new RowMapper<Long>() {
		    	@Override
		    	public Long mapRow(ResultSet rs, int arg1) throws SQLException {
		    		Long idDtl = rs.getLong("ID");
	
		    		return idDtl;
		    	}
			  };
			  

		    public Long addDocument (Long idProcess,String createdBy,String documentType,String subject,String pickedType,String budgetType) {
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
		    			"ERROR_MSG, "+ 
		    			"PICKED_TYPE, "+ 
		    			"BUDGET_TYPE "+ 
		    			")VALUES( " + 
		    			"?, " + 
		    			"?, " + 
		    			"sysdate, " + 
		    			"?, " + 
		    			"?, " + 
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
		    					"1884",
		    					"N",
		    					"NO",
		    					pickedType,
		    					budgetType});
		    	return id;
		}
		    
		    public Long saveDataDtl (Int09TableDtlVo vo) {
		    	Long id = jdbcTemplate.queryForObject(" SELECT TRAVEL_ESTIMATOR_DTL_SEQ.NEXTVAL FROM dual ",Long.class);

		    	jdbcTemplate.update(" INSERT INTO TRAVEL_ESTIMATOR_DTL( " + 
		    			"ID, " + 
		    			"ID_PROCESS, " + 
		    			"DOCUMENT_TYPE, " + 
		    			"NAME, " + 
		    			"POSITION, " + 
		    			"FEED_DAY, " + 
		    			"FEED_MONEY, " + 
		    			"ROOST_DAY, " + 
		    			"ROOST_MONEY, " + 
		    			"PASSAGE, " + 
		    			"OTHER_EXPENSES, " + 
		    			"TOTAL_MONEY, " + 
		    			"REMARK, "+ 
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
		    			"?, " + 
		    			"?, " + 
		    			"?, " + 
		    			"?, " + 
		    			"?) ",new Object[] {
		    					id,
		    					vo.getIdProcess(),
		    					vo.getDocumentType(),
		    					vo.getName(),
		    					vo.getPosition(),
		    					vo.getFeedDay(),
		    					vo.getFeedMoney(),
		    					vo.getRoostDay(),
		    					vo.getRoostMoney(),
		    					vo.getPassage(),
		    					vo.getOtherExpenses(),
		    					vo.getTotalMoney(),
		    					vo.getRemark(),
		    					"N"});
		    	
		    	return id;
		}
		public void editDataDtl (Int09TableDtlVo vo) {

	    	jdbcTemplate.update(" UPDATE TRAVEL_ESTIMATOR_DTL SET " + 
	    			"NAME=?, " + 
	    			"POSITION=?, " + 
	    			"FEED_DAY=?, " + 
	    			"FEED_MONEY=?, " + 
	    			"ROOST_DAY=?, " + 
	    			"ROOST_MONEY=?, " + 
	    			"PASSAGE=?, " + 
	    			"OTHER_EXPENSES=?, " + 
	    			"TOTAL_MONEY=?, " + 
	    			"REMARK=? WHERE ID = ?",new Object[] {
	    					vo.getName(),
	    					vo.getPosition(),
	    					vo.getFeedDay(),
	    					vo.getFeedMoney(),
	    					vo.getRoostDay(),
	    					vo.getRoostMoney(),
	    					vo.getPassage(),
	    					vo.getOtherExpenses(),
	    					vo.getTotalMoney(),
	    					vo.getRemark(),
	    					vo.getId()});
	    	
	}
		    public Long saveDataFormDtl (Long idDtl,Int09FormDtlVo formDtlVo) {
		    	Long id = jdbcTemplate.queryForObject(" SELECT TRAVEL_ESTIMATOR_FORM_DTL_SEQ.NEXTVAL FROM dual ",Long.class);

		    	jdbcTemplate.update(" INSERT INTO TRAVEL_ESTIMATOR_FORM_DTL( " + 
		    			"ID, " +
				    	"ID_DTL, " +
				    	"NAME,  " +
				    	"LAST_NAME,  " +
				    	"POSITION,  " +
				    	"TYPE,  " +
				    	"GRADE,  " +
				    	"PERMISSION_DATE,  " +
				    	"WRITE_DATE,  " +
				    	"DEPARTURE_FROM,  " +
				    	"DEPARTURE_TO,  " +
				    	"DEPARTURE_DATE,  " +
				    	"RETURN_DATE,  " +
						"NUMBER_DATE_ALLOWANCE,  " +
						"NUMBER_HOURS_ALLOWANCE,  " +
						"ALLOWANCE_R,  " +
						"ALLOWANCE_TOTAL,  " +
						"NUMBER_DATE_ROOST,  " +
						"ROOST_R,  " +
						"ROOST_TOTAL,  " +
				    	"PASSAGE,  " +
				    	"OTHER_EXPENSES,  " +
				    	"REMARK " +
		    			")VALUES( " + 
		    			"?, " +
				    	"?, " +
				    	"?,  " +
				    	"?,  " +
				    	"?,  " +
				    	"?,  " +
				    	"?,  " +
				    	"?,  " +
				    	"?,  " +
				    	"?,  " +
				    	"?,  " +
				    	"?,  " +
				    	"?,  " +
				    	"?,  " +
				    	"?,  " +
				    	"?,  " +
				    	"?,  " +
				    	"?,  " +
				    	"?,  " +
				    	"?,  " +
				    	"?,  " +
				    	"?,  " +
		    			"?) ",new Object[] {
		    					id,
		    					idDtl, 
		    					formDtlVo.getName(),  
		    					formDtlVo.getLastName(),  
		    					formDtlVo.getPosition(),  
		    					formDtlVo.getType(),  
		    					formDtlVo.getGrade(),  
		    					formDtlVo.getPermissionDate(),  
		    					formDtlVo.getWriteDate(),  
		    					formDtlVo.getDepartureFrom(),  
		    					formDtlVo.getDepartureTo(),  
		    					formDtlVo.getDepartureDate(),  
		    					formDtlVo.getReturnDate(),  
		    					
		    					formDtlVo.getNumberDateAllowance(),  
		    					formDtlVo.getNumberHoursAllowance(),  
		    					formDtlVo.getAllowanceR(),  
		    					formDtlVo.getAllowanceTotal(),  
		    					
		    					formDtlVo.getNumberDateRoost(),  
		    					formDtlVo.getRoostR(),  
		    					formDtlVo.getRoostTotal(),  
		    					
		    					formDtlVo.getPassage(),  
		    					formDtlVo.getOtherExpenses(),  
		    					formDtlVo.getRemark() 
		    					});
		    	
		    	return id;
		}
		    public void editDataFormDtl (Int09FormDtlVo formDtlVo) {

		    	jdbcTemplate.update(" UPDATE TRAVEL_ESTIMATOR_FORM_DTL SET " + 
				    	"NAME=?,  " +
				    	"LAST_NAME=?,  " +
				    	"POSITION=?,  " +
				    	"TYPE=?,  " +
				    	"GRADE=?,  " +
				    	"PERMISSION_DATE=?,  " +
				    	"WRITE_DATE=?,  " +
				    	"DEPARTURE_FROM=?,  " +
				    	"DEPARTURE_TO=?,  " +
				    	"DEPARTURE_DATE=?,  " +
				    	"RETURN_DATE=?,  " +
				    	"NUMBER_DATE_ALLOWANCE=?,  " +
				    	"NUMBER_HOURS_ALLOWANCE=?,  " +
				    	"ALLOWANCE_R=?,  " +
				    	"ALLOWANCE_TOTAL=?,  " +
				    	"NUMBER_DATE_ROOST=?,  " +
				    	"ROOST_R=?,  " +
				    	"ROOST_TOTAL=?,  " +
				    	"PASSAGE=?,  " +
				    	"OTHER_EXPENSES=?,  " +
				    	"REMARK=? WHERE ID=?  ",new Object[] {
		    					
		    					formDtlVo.getName(),  
		    					formDtlVo.getLastName(),  
		    					formDtlVo.getPosition(),  
		    					formDtlVo.getType(),  
		    					formDtlVo.getGrade(),  
		    					formDtlVo.getPermissionDate(),  
		    					formDtlVo.getWriteDate(),  
		    					formDtlVo.getDepartureFrom(),  
		    					formDtlVo.getDepartureTo(),  
		    					formDtlVo.getDepartureDate(),  
		    					formDtlVo.getReturnDate(),  
		    					
		    					formDtlVo.getNumberDateAllowance(),  
		    					formDtlVo.getNumberHoursAllowance(),  
		    					formDtlVo.getAllowanceR(),  
		    					formDtlVo.getAllowanceTotal(),  
		    					
		    					formDtlVo.getNumberDateRoost(),  
		    					formDtlVo.getRoostR(),  
		    					formDtlVo.getRoostTotal(), 
		    					
		    					formDtlVo.getPassage(),  
		    					formDtlVo.getOtherExpenses(),  
		    					formDtlVo.getRemark(),
		    					formDtlVo.getId()
		    					});
		    	
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
		    
		    public Lov getAllowanceRAndRoostR(Int09111And3FormVo formVo) {
				StringBuilder sql = new StringBuilder(" SELECT * FROM SYS_LOV WHERE TYPE='ACC_FEE' AND LOV_ID = ? ");
				List<Object> params = new ArrayList<>();
				Lov lov = new Lov();

				params.add(formVo.getId());
				
				log.info(" getAllowanceRAndRoostR LOV_ID : {}",formVo.getId());
				log.info(" getAllowanceRAndRoostR sql : {}",sql.toString());
				
		        List<Lov> list = jdbcTemplate.query(sql.toString(), params.toArray(), getAllowanceRAndRoostRRowmapper);
		        
		        lov.setValue1(list.get(0).getValue1());
		        lov.setValue2(list.get(0).getValue2());
		        lov.setValue3(list.get(0).getValue3());
		        lov.setValue4(list.get(0).getValue4());
		        lov.setValue5(list.get(0).getValue5());
		        
		        return lov;
		    }
			
			 private RowMapper<Lov> getAllowanceRAndRoostRRowmapper = new RowMapper<Lov>() {
			    	@Override
			    	public Lov mapRow(ResultSet rs, int arg1) throws SQLException {
			    		Lov vo = new Lov();
			    		
			    		vo.setValue1(rs.getString("VALUE1"));
			    		vo.setValue2(rs.getString("VALUE2"));
			    		vo.setValue3(rs.getString("VALUE3"));
			    		vo.setValue4(rs.getString("VALUE4"));
			    		vo.setValue5(rs.getString("VALUE5"));
			    	
			
			    	    	
			    		return vo;
			    	}
			    };
		    
}
