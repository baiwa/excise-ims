package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateTimeConverter;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;
import th.go.excise.ims.ia.vo.Int030102FormVo;

@Repository
public class Int030102JdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	private final String SQL_IA_RISK_FACTORS_MASTER = " SELECT * FROM IA_RISK_FACTORS_MASTER WHERE IS_DELETED = 'N' AND INSPECTION_WORK = ? ";
	
	public List<IaRiskFactorsMaster> list(Int030102FormVo form){
		List<IaRiskFactorsMaster> iaRiskFactorsMasterList = new ArrayList<IaRiskFactorsMaster>();
		StringBuilder sql = new StringBuilder(SQL_IA_RISK_FACTORS_MASTER);
		List<Object> params = new ArrayList<Object>();
		
		params.add(form.getInspectionWork());
		
		if(StringUtils.isNotBlank(form.getBudgetYear())) {
			sql.append(" AND BUDGET_YEAR = ? ");
			params.add(form.getBudgetYear());
		}
		sql.append(" ORDER BY CREATED_DATE ASC ");
		
		iaRiskFactorsMasterList = commonJdbcTemplate.query(sql.toString(),params.toArray(), listRowmapper);
		
		return iaRiskFactorsMasterList;
	
	}
	
	
	  private RowMapper<IaRiskFactorsMaster> listRowmapper = new RowMapper<IaRiskFactorsMaster>() {
	       @Override
	       public IaRiskFactorsMaster mapRow(ResultSet rs, int arg1) throws SQLException {
	    	   IaRiskFactorsMaster  vo = new IaRiskFactorsMaster();
	    	   
	    		vo.setId(rs.getBigDecimal("ID"));
	    		vo.setRiskFactorsMaster(rs.getString("RISK_FACTORS_MASTER"));
	    		vo.setBudgetYear(rs.getString("BUDGET_YEAR"));
	    		vo.setStatus(rs.getString("STATUS"));
	    		
	    		LocalDateTime createdDate = LocalDateTimeConverter.convertToEntityAttribute(rs.getTimestamp("CREATED_DATE"));
	    		vo.setCreatedDate(createdDate);
	    		vo.setCreatedBy(rs.getString("CREATED_BY"));
	    		
	    		String date = checkAndConvertDateToString(rs .getDate("CREATED_DATE"));
	    		vo.setCreatedDateDesc(date); 
	    		
	    		LocalDateTime updatedDate = LocalDateTimeConverter.convertToEntityAttribute(rs.getTimestamp("UPDATED_DATE"));
	    		vo.setUpdatedDate(updatedDate);
	    		vo.setUpdatedBy(rs.getString("UPDATED_BY"));
	    		
	    		String date2 = checkAndConvertDateToString(rs .getDate("UPDATED_DATE"));
	    		vo.setUpdateDateDesc(date2);
	    	   
	    		vo.setNotDelete(rs.getString("NOT_DELETE"));
	        return vo;
	       }

	  };
	  
	  private String checkAndConvertDateToString(Date date) {
		String dateSting = "";
		if(date!= null) {
			dateSting = ConvertDateUtils.formatDateToString(date, ConvertDateUtils.DD_MM_YYYY,ConvertDateUtils.LOCAL_TH);
		}
		return dateSting;
	}
	  
		public void delete(Int030102FormVo form){
			StringBuilder sql = new StringBuilder(" UPDATE IA_RISK_FACTORS_MASTER SET IS_DELETED = 'Y' WHERE ID = ? ");
	
			commonJdbcTemplate.update(sql.toString(),new Object[] {form.getId()});
			
		
		}
		
		public void editStatus(Int030102FormVo form){
			StringBuilder sql = new StringBuilder(" UPDATE IA_RISK_FACTORS_MASTER SET STATUS = ? WHERE ID = ?");
			String status = ("N".equals(form.getStatus())?"Y":"N");			
			commonJdbcTemplate.update(sql.toString(),new Object[] {status,form.getId()});
			
		
		}
	 
}

