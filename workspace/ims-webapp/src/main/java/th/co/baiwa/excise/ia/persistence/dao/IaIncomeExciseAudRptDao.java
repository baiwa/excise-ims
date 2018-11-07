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

import th.co.baiwa.excise.cop.persistence.vo.Cop071Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int084FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int084Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int085FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int085Vo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class IaIncomeExciseAudRptDao {

	private static final Logger log = LoggerFactory.getLogger(IaIncomeExciseAudRptDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String SQL_IA_INCOME_EXCISE_AUD_RPT = " SELECT * FROM IA_INCOME_EXCISE_AUD_RPT WHERE IS_DELETED='N' ";
	
	private final String SQL_IA_INCOME_EXCISE_AUD_RPT_STATUS = " SELECT r.*, nvl2(T.ID , 'Y' , 'N')AS STATUS_M FROM IA_INCOME_EXCISE_AUD_RPT R " + 
			"left join IA_RISK_TASK T " + 
			"on R.IA_INCOME_EXCISE_AUD_RPT_ID = T.CHECK_ID ";
	
	
	public Long countInt084(Int084FormVo formVo) {
		
		StringBuilder sql = new StringBuilder(SQL_IA_INCOME_EXCISE_AUD_RPT);
		List<Object> params = new ArrayList<>();
		

//		if (StringUtils.isNotBlank(formVo.getFiscalYear())) {
//			sql.append(" AND  SUBSTR(FISCAL_YEAR,4,4)=?");
//			params.add(formVo.getFiscalYear());
//		}
		

		String countSql = OracleUtils.countForDatatable(sql);
        Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
        return count;
    }

	public List<Int084Vo> findAllInt084(Int084FormVo formVo) {
		
		StringBuilder sql = new StringBuilder(SQL_IA_INCOME_EXCISE_AUD_RPT);
		List<Object> params = new ArrayList<>();


//		if (StringUtils.isNotBlank(formVo.getFiscalYear())) {
//			sql.append(" AND  SUBSTR(FISCAL_YEAR,4,4)=?");
//			params.add(formVo.getFiscalYear());
//		}
		sql.append(" ORDER BY OFFICE_CODE asc");
		log.info("findAllCop071 sql : {}",sql);
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
  	
	    		return vo;
	    	}
	    };	
	    
		public Long countInt085(Int085FormVo formVo) {
			
			StringBuilder sql = new StringBuilder(SQL_IA_INCOME_EXCISE_AUD_RPT_STATUS);
			List<Object> params = new ArrayList<>();
			

//			if (StringUtils.isNotBlank(formVo.getFiscalYear())) {
//				sql.append(" AND  SUBSTR(FISCAL_YEAR,4,4)=?");
//				params.add(formVo.getFiscalYear());
//			}
			

			String countSql = OracleUtils.countForDatatable(sql);
	        Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
	        return count;
	    }

		public List<Int085Vo> findAllInt085(Int085FormVo formVo) {
			
			StringBuilder sql = new StringBuilder(SQL_IA_INCOME_EXCISE_AUD_RPT_STATUS);
			List<Object> params = new ArrayList<>();


//			if (StringUtils.isNotBlank(formVo.getFiscalYear())) {
//				sql.append(" AND  SUBSTR(FISCAL_YEAR,4,4)=?");
//				params.add(formVo.getFiscalYear());
//			}
			sql.append(" ORDER BY OFFICE_CODE asc");
			log.info("findAllCop071 sql : {}",sql);
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
		    	    vo.setRisk(rs.getString("RISK"));
		    	    vo.setOrigin(rs.getString("ORIGIN"));
		    	    vo.setRiskScore(rs.getString("RISK_SCORE"));
		    	    vo.setStatusM(rs.getString("STATUS_M"));
	  	
		    		return vo;
		    	}
		    };		 
	    
}
