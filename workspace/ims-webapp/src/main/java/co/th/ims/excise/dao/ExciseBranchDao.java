package co.th.ims.excise.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import co.th.baiwa.buckwaframework.common.bean.BaseVo;
import co.th.ims.excise.domain.ExciseBranch;

@Repository
public class ExciseBranchDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<ExciseBranch> findBySectorId(BigDecimal bigDecimal) {
		String sqlTemplate = " SELECT * FROM EXCISE_BRANCH WHERE 1=1 ";
		StringBuilder sql = new StringBuilder(sqlTemplate);
		List<ExciseBranch> list = new ArrayList<ExciseBranch>();
		list = jdbcTemplate.query(sql.toString(), rowMapper);
		return list;
	}
	
	protected RowMapper<ExciseBranch> rowMapper = new RowMapper<ExciseBranch>() {
		@Override
		public ExciseBranch mapRow(ResultSet rs, int arg1) throws SQLException {
			ExciseBranch vo = new ExciseBranch();
			
			vo.setAreaId(rs.getBigDecimal(ExciseBranch.Field.AREA_ID));
			vo.setBranchId(rs.getBigDecimal(ExciseBranch.Field.BRANCH_ID));
			vo.setBranchName(rs.getString(ExciseBranch.Field.BRANCH_NAME));
			vo.setBranchShotName(rs.getString(ExciseBranch.Field.BRANCH_SHOT_NAME));
			vo.setBranchShotName2(rs.getString(ExciseBranch.Field.BRANCH_SHOT_NAME2));
			vo.setOfficeCode(rs.getString(ExciseBranch.Field.OFFICE_CODE));
			
			vo.setIsDeleted(rs.getString(BaseVo.Field.IS_DELETED));
			vo.setCreatedBy(rs.getString(BaseVo.Field.CREATED_BY));
			vo.setCreatedDate(rs.getDate(BaseVo.Field.CREATED_DATE));
			vo.setUpdatedBy(rs.getString(BaseVo.Field.UPDATED_BY));
			vo.setUpdatedDate(rs.getDate(BaseVo.Field.UPDATED_DATE));
			return vo;
		}
	};
}
