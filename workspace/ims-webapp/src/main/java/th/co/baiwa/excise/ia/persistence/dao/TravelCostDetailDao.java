package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.ia.persistence.vo.Int09213Vo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class TravelCostDetailDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private static String SQL_TRAVEL_COST_DETAIL = " SELECT * FROM IA_TRAVEL_COST_WS_DETAIL D WHERE 1=1 ";

	public Long count() {

		StringBuilder sql = new StringBuilder(SQL_TRAVEL_COST_DETAIL);
		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, Long.class);
		return count;
	}

	public List<Int09213Vo> dataTravelCostWsDetail() {

		StringBuilder sql = new StringBuilder(SQL_TRAVEL_COST_DETAIL);
		List<Int09213Vo> list = jdbcTemplate.query(sql.toString(), dataTravelCostWsDetailRowmapper);
		return list;
	}
	 private RowMapper<Int09213Vo> dataTravelCostWsDetailRowmapper = new RowMapper<Int09213Vo>() {
	    	@Override
	    	public Int09213Vo mapRow(ResultSet rs, int arg1) throws SQLException {
	    		Int09213Vo vo = new Int09213Vo();
	    		
	    		vo.setWorkSheetDetailId(rs.getString("WORK_SHEET_DETAIL_ID"));
	    		vo.setHeaderId(rs.getString("HEADER_ID"));
	    		vo.setName(rs.getString("NAME"));
	    		vo.setLastName(rs.getString("LAST_NAME"));
	    		vo.setPosition(rs.getString("POSITION"));
	    		vo.setCategory(rs.getString("CATEGORY"));
	    		vo.setDegree(rs.getString("DEGREE"));
	    		vo.setAllowanceDate(rs.getBigDecimal("ALLOWANCE_DATE"));
	    		vo.setAllowanceCost(rs.getBigDecimal("ALLOWANCE_COST"));
	    		vo.setRentDate(rs.getBigDecimal("RENT_DATE"));
	    		vo.setRentCost(rs.getBigDecimal("RENT_COST"));
	    		vo.setTravelCost(rs.getBigDecimal("TRAVEL_COST"));
	    		vo.setOtherCost(rs.getBigDecimal("OTHER_COST"));
	    		vo.setSumCost(rs.getBigDecimal("SUM_COST"));
	    		vo.setNote(rs.getString("NOTE"));
	    		vo.setCreatedBy(rs.getString("CREATED_BY"));
	    		vo.setUpdatedBy(rs.getString("UPDATED_BY"));
	    		vo.setIsDeleted(rs.getString("IS_DELETED"));
	    		vo.setVersion(rs.getBigDecimal("VERSION"));
	    		
	    		//format date
	    		String createdDate = DateConstant.convertDateEnDDMMYYYYFormat(rs.getDate("CREATED_DATE"));
	    		String updatedDate = DateConstant.convertDateEnDDMMYYYYFormat(rs.getDate("UPDATED_DATE"));
	    		
	    		vo.setCreatedDate(createdDate);	    		
	    		vo.setUpdatedDate(updatedDate);
	    		
	    		return vo;
	    	}
	    };
	    
		public List<LabelValueBean> drodownList(String lovIdMaster,String typeLov){
			String SQL = "SELECT * FROM SYS_LOV WHERE TYPE=? AND LOV_ID_MASTER= ?";
			return jdbcTemplate.query(SQL,new Object[] {typeLov,lovIdMaster} ,ropdownListRowmapper);
		}
		private RowMapper<LabelValueBean> ropdownListRowmapper = new RowMapper<LabelValueBean>() {
		    	@Override
		    	public LabelValueBean mapRow(ResultSet rs, int arg1) throws SQLException {
		    		LabelValueBean  lv = new LabelValueBean(rs.getString("TYPE_DESCRIPTION"),rs.getString("LOV_ID"));
		    		return lv;
		    	}

		};
}
