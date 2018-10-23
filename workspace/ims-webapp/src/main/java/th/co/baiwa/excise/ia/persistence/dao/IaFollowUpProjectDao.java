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
import th.co.baiwa.excise.ia.persistence.vo.Int111FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int111Vo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class IaFollowUpProjectDao {

	// private Logger log = LoggerFactory.getLogger(IaFollowUpProjectDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private String SQL_SEARCH_CRITERIA = " SELECT * FROM IA_FOLLOW_UP_PROJECT WHERE IS_DELETED = 'N' ";
	private String SQL_EXPORT_DATA = " SELECT * FROM IA_FOLLOW_UP_PROJECT WHERE IS_DELETED = 'N' ";

	public long countCriteria(Int111FormVo formVo) {
		StringBuilder sql = new StringBuilder(SQL_SEARCH_CRITERIA);
		List<Object> params = new ArrayList<>();

		if (StringUtils.isNotBlank(formVo.getProjectName())) {
			sql.append(" AND PROJECT_NAME LIKE ? ");
			params.add("%" + formVo.getProjectName() + "%");
		}

		if (StringUtils.isNotBlank(formVo.getStatus())) {
			sql.append(" AND STATUS = ? ");
			params.add(formVo.getStatus());
		}
		
		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}
	
	public List<Int111Vo> searchCriteria(Int111FormVo formVo) {
		StringBuilder sql = new StringBuilder(SQL_SEARCH_CRITERIA);
		List<Object> params = new ArrayList<>();

		if (StringUtils.isNotBlank(formVo.getProjectName())) {
			sql.append(" AND PROJECT_NAME LIKE ? ");
			params.add("%" + formVo.getProjectName() + "%");
		}

		if (StringUtils.isNotBlank(formVo.getStatus())) {
			sql.append(" AND STATUS = ? ");
			params.add(formVo.getStatus());
		}

		String sqlLimit = OracleUtils.limitForDataTable(sql, formVo.getStart(), formVo.getLength());
		List<Int111Vo> list = jdbcTemplate.query(sqlLimit, params.toArray(), iaFollowUpProjectRowmapper);
		return list;
	}

	public List<Int111Vo> queryExportData(Int111FormVo formVo) {
		StringBuilder sql = new StringBuilder(SQL_EXPORT_DATA);
		List<Object> param = new ArrayList<>();

		if (StringUtils.isNotBlank(formVo.getProjectName())) {
			sql.append(" AND PROJECT_NAME LIKE ? ");
			param.add("%" + formVo.getProjectName() + "%");
		}

		if (StringUtils.isNotBlank(formVo.getStatus())) {
			sql.append(" AND STATUS = ? ");
			param.add(formVo.getStatus());
		}

		List<Int111Vo> list = jdbcTemplate.query(sql.toString(), param.toArray(), iaFollowUpProjectRowmapper);
		return list;
	}

	private RowMapper<Int111Vo> iaFollowUpProjectRowmapper = new RowMapper<Int111Vo>() {
		@Override
		public Int111Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int111Vo vo = new Int111Vo();
			vo.setFollowUpProjectId(String.valueOf(rs.getLong("FOLLOW_UP_PROJECT_ID")));
			vo.setProjectName(rs.getString("PROJECT_NAME"));
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
			vo.setFollowUp3Bnum(rs.getString("FOLLOW_UP3_BNUM"));
			vo.setFollowUp3Date(DateConstant.convertDateToStrDDMMYYYY(rs.getTimestamp("FOLLOW_UP3_DATE")));
			vo.setMaturity360(DateConstant.convertDateToStrDDMMYYYY(rs.getTimestamp("MATURITY3_60")));
			vo.setPerformance3Bnum(rs.getString("PERFORMANCE3_BNUM"));
			vo.setPerformance3Date(DateConstant.convertDateToStrDDMMYYYY(rs.getTimestamp("PERFORMANCE3_DATE")));
			vo.setTrackResult3Bnum(rs.getString("TRACK_RESULT3_BNUM"));
			vo.setTrackResult3Date(DateConstant.convertDateToStrDDMMYYYY(rs.getTimestamp("TRACK_RESULT3_DATE")));
			vo.setStatus(rs.getString("STATUS"));
			vo.setVersion(String.valueOf(rs.getInt("VERSION")));
			vo.setNote(rs.getString("NOTE"));
			return vo;
		}
	};

}
