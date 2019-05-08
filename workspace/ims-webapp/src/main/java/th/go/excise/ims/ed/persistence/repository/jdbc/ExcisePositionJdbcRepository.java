package th.go.excise.ims.ed.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ed.vo.Ed02DepartmentVo;
import th.go.excise.ims.ed.vo.Ed02PositionVo;
import th.go.excise.ims.ed.vo.Ed02Vo;

@Repository
public class ExcisePositionJdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Ed02PositionVo> listPosition() {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT * FROM EXCISE_POSITION WHERE IS_DELETED = 'N' ");
		List<Ed02PositionVo> datas = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(), listPosition);
		return datas;
	}

	private RowMapper<Ed02PositionVo> listPosition = new RowMapper<Ed02PositionVo>() {
		@Override
		public Ed02PositionVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ed02PositionVo vo = new Ed02PositionVo();
			vo.setEdPositionName(rs.getString("ED_POSITION_NAME"));
			vo.setEdPersonSeq(rs.getBigDecimal("ED_PERSON_SEQ"));
			return vo;
		}
	};
	
	public List<Ed02DepartmentVo> listDepartment00() {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT * FROM EXCISE_DEPARTMENT WHERE OFF_CODE LIKE '00%' ");
		List<Ed02DepartmentVo> datas = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(), listDepartment00);
		return datas;
	}
	
	private RowMapper<Ed02DepartmentVo> listDepartment00 = new RowMapper<Ed02DepartmentVo>() {
		@Override
		public Ed02DepartmentVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ed02DepartmentVo vo = new Ed02DepartmentVo();
			vo.setOffName(rs.getString("OFF_NAME"));
			vo.setOffCode(rs.getNString("OFF_CODE"));
			return vo;
		}
	};
	
	public List<Ed02DepartmentVo> listDepartment01() {
		StringBuilder sqlBuilder = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlBuilder.append(" SELECT * FROM EXCISE_DEPARTMENT WHERE OFF_CODE NOT LIKE '00%' ");
		List<Ed02DepartmentVo> datas = commonJdbcTemplate.query(sqlBuilder.toString(), params.toArray(), listDepartment01);
		return datas;
	}
	
	private RowMapper<Ed02DepartmentVo> listDepartment01 = new RowMapper<Ed02DepartmentVo>() {
		@Override
		public Ed02DepartmentVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ed02DepartmentVo vo = new Ed02DepartmentVo();
			vo.setOffName(rs.getString("OFF_NAME"));
			vo.setOffCode(rs.getNString("OFF_CODE"));
			return vo;
		}
	};
	
	
	
	
	
}
