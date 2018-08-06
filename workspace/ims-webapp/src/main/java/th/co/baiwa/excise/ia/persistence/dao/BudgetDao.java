package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.entity.Budget;
import th.co.baiwa.excise.ia.persistence.vo.Int0911FormVo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class BudgetDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String SQL_IA_TRAVEL_COST_WS_HEADER = "SELECT * FROM IA_TRAVEL_COST_WS_HEADER WHERE 1=1";
	
	public Long count(Int0911FormVo formVo) {
		
		StringBuilder sql = new StringBuilder(SQL_IA_TRAVEL_COST_WS_HEADER);
		List<Object> params = new ArrayList<>();
		
		if (StringUtils.isNotBlank(formVo.getYear())) {
			sql.append(" AND  TO_CHAR(UPDATED_DATE, 'YYYYMMDD') = ? ");
			params.add(formVo.getYear());
			
		}
		if (StringUtils.isNotBlank(formVo.getDepartment())) {
			sql.append(" AND DEPARTMENT_NAME = ?");
			params.add(formVo.getDepartment());
			
		}
		String countSql = OracleUtils.countForDatatable(sql);
        Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
        return count;
    }

	public List<Budget> findAll(Int0911FormVo formVo) {
		
		StringBuilder sql = new StringBuilder(SQL_IA_TRAVEL_COST_WS_HEADER);
		List<Object> params = new ArrayList<>();
		
		if (StringUtils.isNotBlank(formVo.getYear())) {
			sql.append(" AND  TO_CHAR(UPDATED_DATE, 'YYYYMMDD') = ? ");
			params.add(formVo.getYear());
			
		}
		if (StringUtils.isNotBlank(formVo.getDepartment())) {
			sql.append(" AND DEPARTMENT_NAME = ?");
			params.add(formVo.getDepartment());
			
		}
        List<Budget> list = jdbcTemplate.query(sql.toString(), params.toArray(), budgetRowmapper);
        return list;
    }
	
	 private RowMapper<Budget> budgetRowmapper = new RowMapper<Budget>() {
	    	@Override
	    	public Budget mapRow(ResultSet rs, int arg1) throws SQLException {
	    		Budget vo = new Budget();
	    		
	    		vo.setId(rs.getString("WORK_SHEET_HEADER_ID"));
	    		vo.setWorkSheetHeaderName(rs.getString("WORK_SHEET_HEADER_NAME"));
	    		vo.setDepartment(rs.getString("DEPARTMENT_NAME"));
	    		vo.setStartDate(rs.getString("START_DATE"));
	    		vo.setEndDate(rs.getString("END_DATE"));
	    		vo.setDescription(rs.getString("DESCRIPTION"));
	    		vo.setCreatedBy(rs.getString("CREATED_BY"));	    		
	    		vo.setUpdatedBy(rs.getString("UPDATED_BY"));
	    		vo.setIsDate(rs.getString("IS_DELETED"));
	    		vo.setVersion(rs.getString("VERSION"));
	    		
	    		//format date
	    		String createdDate = DateConstant.convertDateEnDDMMYYYYFormat(rs.getDate("CREATED_DATE"));
	    		String updatedDate = DateConstant.convertDateEnDDMMYYYYFormat(rs.getDate("UPDATED_DATE"));
	    		String startDate = DateConstant.convertDateEnDDMMYYYYFormat(rs.getDate("START_DATE"));
	    		String endDate = DateConstant.convertDateEnDDMMYYYYFormat(rs.getDate("END_DATE"));
	    		
	    		vo.setCraetedDate(createdDate);	    		
	    		vo.setUpdatedDate(updatedDate);
	    		vo.setStartDate(startDate);
	    		vo.setEndDate(endDate);
	    		
	    		return vo;
	    	}
	    };

}
