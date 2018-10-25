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

import th.co.baiwa.excise.cop.persistence.vo.Cop071FormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop071Vo;
import th.co.baiwa.excise.ia.persistence.vo.Int09TableDtlVo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class CopCheckFiscalYearDao {

	private static final Logger log = LoggerFactory.getLogger(CopCheckFiscalYearDao.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String SQL_TRAVEL_ESTIMATOR_PROCESS = "SELECT * FROM COP_CHECK_FISCAL_YEAR WHERE 1=1 ";
	
	
	public Long count(Cop071FormVo formVo) {
		
		StringBuilder sql = new StringBuilder(SQL_TRAVEL_ESTIMATOR_PROCESS);
		List<Object> params = new ArrayList<>();
		

		if (StringUtils.isNotBlank(formVo.getFiscalYear())) {
			sql.append(" AND  SUBSTR(FISCAL_YEAR,4,4)=?");
			params.add(formVo.getFiscalYear());
		}
		

		String countSql = OracleUtils.countForDatatable(sql);
        Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
        return count;
    }

	public List<Cop071Vo> findAll(Cop071FormVo formVo) {
		
		StringBuilder sql = new StringBuilder(SQL_TRAVEL_ESTIMATOR_PROCESS);
		List<Object> params = new ArrayList<>();


		if (StringUtils.isNotBlank(formVo.getFiscalYear())) {
			sql.append(" AND  SUBSTR(FISCAL_YEAR,4,4)=?");
			params.add(formVo.getFiscalYear());
		}

		
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
	    			"OUTSIDE_PLAN_WAIT "+ 
	    			")VALUES( " + 
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
	    		    		vo.getOutsidePlanWait()
	    		    		});
	    	
	    	return id;
	}
	
 
}
