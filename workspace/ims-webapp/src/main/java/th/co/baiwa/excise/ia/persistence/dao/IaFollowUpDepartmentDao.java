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

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.ia.persistence.vo.Int112FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int112Vo;

@Repository
public class IaFollowUpDepartmentDao {

	private Logger log = LoggerFactory.getLogger(IaFollowUpDepartmentDao.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    private String SQL_SEARCH_CRITERIA = " SELECT * FROM IA_FOLLOW_UP_DEPARTMENT WHERE IS_DELETED = 'N' ";
    private String SQL_EXPORT_DATA = " SELECT * FROM IA_FOLLOW_UP_DEPARTMENT WHERE IS_DELETED = 'N' ";
    
    public List<Int112Vo> searchCriteria(Int112FormVo formVo) {
    	StringBuilder sql = new StringBuilder(SQL_SEARCH_CRITERIA);
    	List<Object> param = new ArrayList<>();
    	
    	if (StringUtils.isNotBlank(formVo.getExciseDepartment())) {
    		sql.append(" AND EXCISE_DEPARTMENT = ? ");
    		param.add(queryValue1SysLov(formVo.getExciseDepartment()));
    	}
    	
    	if (StringUtils.isNotBlank(formVo.getExciseRegion())) {
    		sql.append(" AND EXCISE_REGION = ? ");
    		param.add(queryValue1SysLov(formVo.getExciseRegion()));
    	}
    	
    	if (StringUtils.isNotBlank(formVo.getExciseDistrict())) {
    		sql.append(" AND EXCISE_DISTRICT = ? ");
    		param.add(queryDescSysLov(formVo.getExciseDistrict()));
    	}
    	
    	if (StringUtils.isNotBlank(formVo.getStatus())) {
    		sql.append(" AND STATUS = ? ");
    		param.add(formVo.getStatus());
    	}
    	
    	List<Int112Vo> list = jdbcTemplate.query(sql.toString(), param.toArray(), iaFollowUpDepartmentRowmapper);
    	return list;
    }
    
    public List<Int112Vo> queryExportData(Int112FormVo formVo) {
    	StringBuilder sql = new StringBuilder(SQL_EXPORT_DATA);
    	List<Object> param = new ArrayList<>();
    	
    	if (StringUtils.isNotBlank(formVo.getExciseDepartment())) {
    		sql.append(" AND EXCISE_DEPARTMENT = ? ");
    		param.add(queryValue1SysLov(formVo.getExciseDepartment()));
    	}
    	
    	if (StringUtils.isNotBlank(formVo.getExciseRegion())) {
    		sql.append(" AND EXCISE_REGION = ? ");
    		param.add(queryValue1SysLov(formVo.getExciseRegion()));
    	}
    	
    	if (StringUtils.isNotBlank(formVo.getExciseDistrict())) {
    		sql.append(" AND EXCISE_DISTRICT = ? ");
    		param.add(queryDescSysLov(formVo.getExciseDistrict()));
    	}
    	
    	if (StringUtils.isNotBlank(formVo.getStatus())) {
    		sql.append(" AND STATUS = ? ");
    		param.add(formVo.getStatus());
    	}
    	
    	List<Int112Vo> list = jdbcTemplate.query(sql.toString(), param.toArray(), iaFollowUpDepartmentRowmapper);
    	return list;
    }
    
    private RowMapper<Int112Vo> iaFollowUpDepartmentRowmapper = new RowMapper<Int112Vo>() {
		@Override
		public Int112Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int112Vo vo = new Int112Vo();
			vo.setFollowUpDepartmentId(String.valueOf(rs.getLong("FOLLOW_UP_DEPARTMENT_ID")));
			vo.setExciseDepartment(rs.getString("EXCISE_DEPARTMENT"));
			vo.setExciseRegion(rs.getString("EXCISE_REGION"));
			vo.setExciseDistrict(rs.getString("EXCISE_DISTRICT"));
			vo.setInformRectorBnum(rs.getString("INFORM_RECTOR_BNUM"));
			vo.setInformRectorDate(DateConstant.convertDateToStrDDMMYYYY(rs.getTimestamp("INFORM_RECTOR_DATE")));
			vo.setFollowUp1Bnum(rs.getString("FOLLOW_UP1_BNUM"));
			vo.setFollowUp1Date(DateConstant.convertDateToStrDDMMYYYY(rs.getTimestamp("FOLLOW_UP1_DATE")));
			vo.setMaturity145(DateConstant.convertDateToStrDDMMYYYY(rs.getTimestamp("MATURITY1_45")));
			vo.setMaturity160(DateConstant.convertDateToStrDDMMYYYY(rs.getTimestamp("MATURITY1_60")));
			vo.setPerformance1Bnum(rs.getString("PERFORMANCE1_BNUM"));
			vo.setPerformance1Date(DateConstant.convertDateToStrDDMMYYYY(rs.getTimestamp("PERFORMANCE1_DATE")));
			vo.setTrackResult1Bnum(rs.getString("TRACK_RESULT1_BNUM"));
			vo.setTrackResult1Date(DateConstant.convertDateToStrDDMMYYYY(rs.getTimestamp("TRACK_RESULT1_DATE")));
			vo.setFollowUp2Bnum(rs.getString("FOLLOW_UP2_BNUM"));
			vo.setFollowUp2Date(DateConstant.convertDateToStrDDMMYYYY(rs.getTimestamp("FOLLOW_UP2_DATE")));
			vo.setMaturity260(DateConstant.convertDateToStrDDMMYYYY(rs.getTimestamp("MATURITY2_60")));
			vo.setPerformance2Bnum(rs.getString("PERFORMANCE2_BNUM"));
			vo.setPerformance2Date(DateConstant.convertDateToStrDDMMYYYY(rs.getTimestamp("PERFORMANCE2_DATE")));
			vo.setTrackResult2Bnum(rs.getString("TRACK_RESULT2_BNUM"));
			vo.setTrackResult2Date(DateConstant.convertDateToStrDDMMYYYY(rs.getTimestamp("TRACK_RESULT2_DATE")));
			vo.setStatus(rs.getString("STATUS"));
			vo.setVersion(String.valueOf(rs.getInt("VERSION")));
			return vo;
		}
	};
	
	public List<LabelValueBean> queryDeapertment() {
		String SQL = "SELECT * FROM SYS_LOV WHERE TYPE='SECTOR_VALUE' AND LOV_ID_MASTER IS NULL ORDER BY SUB_TYPE ASC";
		return jdbcTemplate.query(SQL, departmentRowMapper);
	}

	private RowMapper<LabelValueBean> departmentRowMapper = new RowMapper<LabelValueBean>() {
		@Override
		public LabelValueBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new LabelValueBean(rs.getString("VALUE1"), rs.getString("LOV_ID"));
		}
	};
	
	public List<LabelValueBean> queryRegion(String id) {
		String SQL = "SELECT * FROM SYS_LOV WHERE LOV_ID_MASTER=? AND TYPE='SECTOR_VALUE' ";
		return jdbcTemplate.query(SQL, new Object[] { id }, regionRowmapper);
	}

	private RowMapper<LabelValueBean> regionRowmapper = new RowMapper<LabelValueBean>() {
		@Override
		public LabelValueBean mapRow(java.sql.ResultSet rs, int rowNum) throws SQLException {
			return new LabelValueBean(rs.getString("VALUE1"), rs.getString("LOV_ID"));
		}
	};
	
	public List<LabelValueBean> queryDistrict(String id) {
		String SQL = "SELECT * FROM SYS_LOV WHERE LOV_ID_MASTER=? AND TYPE='SECTOR_VALUE' ";
		return jdbcTemplate.query(SQL, new Object[] { id }, districtRowmapper);
	}

	private RowMapper<LabelValueBean> districtRowmapper = new RowMapper<LabelValueBean>() {
		@Override
		public LabelValueBean mapRow(java.sql.ResultSet rs, int rowNum) throws SQLException {
			return new LabelValueBean(rs.getString("SUB_TYPE_DESCRIPTION"), rs.getString("LOV_ID"));
		}
	};
	
	public String queryValue1SysLov(String id) {
		String SQL = "SELECT VALUE1 FROM SYS_LOV WHERE LOV_ID=? AND TYPE='SECTOR_VALUE' ";
		String value1 = "";
		value1 = jdbcTemplate.queryForObject(SQL, new Object[] { id }, String.class);
		return value1;
	}
	
	public String queryLovIdSysLovByValue1(String value1) {
		String SQL = "SELECT LOV_ID FROM SYS_LOV WHERE VALUE1=? AND TYPE='SECTOR_VALUE' ";
		String lovId = "";
		lovId = jdbcTemplate.queryForObject(SQL, new Object[] { value1 }, String.class);
		return lovId;
	}
	
	public String queryDescSysLov(String id) {
		String SQL = "SELECT SUB_TYPE_DESCRIPTION FROM SYS_LOV WHERE LOV_ID=? AND TYPE='SECTOR_VALUE' ";
		String desc = "";
		desc = jdbcTemplate.queryForObject(SQL, new Object[] { id }, String.class);
		return desc;
	}
	
	public String queryLovIdSysLov(String subDesc) {
		String SQL = "SELECT LOV_ID FROM SYS_LOV WHERE SUB_TYPE_DESCRIPTION=? AND TYPE='SECTOR_VALUE' ";
		String lovId = "";
		lovId = jdbcTemplate.queryForObject(SQL, new Object[] { subDesc }, String.class);
		return lovId;
	}
}
