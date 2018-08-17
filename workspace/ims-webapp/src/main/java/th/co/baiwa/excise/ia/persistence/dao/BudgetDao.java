package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.ia.persistence.entity.Budget;
import th.co.baiwa.excise.ia.persistence.vo.Int09211FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int09213FormVo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class BudgetDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String SQL_IA_TRAVEL_COST_WS_HEADER = "SELECT * FROM IA_TRAVEL_COST_WS_HEADER WHERE 1=1";
	
	public Long count(Int09211FormVo formVo) {
		
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

	public List<Budget> findAll(Int09211FormVo formVo) {
		
		StringBuilder sql = new StringBuilder(SQL_IA_TRAVEL_COST_WS_HEADER);
		List<Object> params = new ArrayList<>();
		
		if (StringUtils.isNotBlank(formVo.getYear())) {
			sql.append(" AND  TO_CHAR(UPDATED_DATE, 'YYYY') = ? ");
			params.add(formVo.getYear());
			
		}
		if (StringUtils.isNotBlank(formVo.getDepartment())) {
			sql.append(" AND DEPARTMENT_NAME = ?");
			params.add(formVo.getDepartment());
			
		}
		
		String sqlLimit = OracleUtils.limitForDataTable(sql.toString(), formVo.getStart(),formVo.getLength());
        List<Budget> list = jdbcTemplate.query(sqlLimit, params.toArray(), budgetRowmapper);
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
	    		
	    		//String createdDate = DateConstant.DateToString(rs.getDate("CREATED_DATE"), "dd/MM/yyyy");
                Date date =  rs.getDate("CREATED_DATE");
                String createdDate = DateFormatUtils.format(rs.getDate("CREATED_DATE"), "dd/MM/yyyy", DateConstant.LOCAL_TH);
	    		String updatedDate = DateConstant.DateToString(rs.getDate("UPDATED_DATE"), "dd/MM/yyyy");
	    		String startDate = DateConstant.DateToString(rs.getDate("START_DATE"), "dd/MM/yyyy");
	    		String endDate = DateConstant.DateToString(rs.getDate("END_DATE"), "dd/MM/yyyy");
	    		
	    		vo.setCraetedDate(createdDate);	    		
	    		vo.setUpdatedDate(createdDate);
	    		vo.setStartDate(startDate);
	    		vo.setEndDate(endDate);
	    		
	    		return vo;
	    	}
	    };
	    
	    
	    public List<LabelValueBean> departmentDropdown() {
	    	String SQL = "SELECT * FROM SYS_LOV  WHERE TYPE='SECTOR_LIST' ";
	    	return jdbcTemplate.query(SQL, departmentDropdownRowmapper);
	    }
	    
		private RowMapper<LabelValueBean> departmentDropdownRowmapper = new RowMapper<LabelValueBean>() {
			@Override
			public LabelValueBean mapRow(java.sql.ResultSet rs, int rowNum) throws SQLException {
				LabelValueBean lv = new LabelValueBean(rs.getString("SUB_TYPE_DESCRIPTION"), rs.getString("SUB_TYPE"));

				return lv;
			}
		};
		
	

}
