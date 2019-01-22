package co.th.ims.excise.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import co.th.baiwa.buckwaframework.common.bean.BaseVo;
import co.th.ims.excise.domain.ExciseSector;

@Repository
public class ExciseSectorDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<ExciseSector> findAll() {
		String sqlTemplate = " SELECT * FROM EXCISE_SECTOR ";
		StringBuilder sql = new StringBuilder(sqlTemplate);
		List<ExciseSector> list = new ArrayList<ExciseSector>();
		list = jdbcTemplate.query(sql.toString(), rowMapper);
		return list;
	}
	
	protected RowMapper<ExciseSector> rowMapper = new RowMapper<ExciseSector>() {
		@Override
		public ExciseSector mapRow(ResultSet rs, int arg1) throws SQLException {
			ExciseSector vo = new ExciseSector();
			
			vo.setSectorId(rs.getBigDecimal(ExciseSector.Field.SECTOR_ID));
			vo.setOfficeCode(rs.getString(ExciseSector.Field.OFFICE_CODE));
			vo.setSectorName(rs.getString(ExciseSector.Field.SECTOR_NAME));
			vo.setSectorName2(rs.getString(ExciseSector.Field.SECTOR_NAME2));
			vo.setSectorShotName(rs.getString(ExciseSector.Field.SECTOR_SHOT_NAME));
			
			vo.setIsDeleted(rs.getString(BaseVo.Field.IS_DELETED));
			vo.setCreatedBy(rs.getString(BaseVo.Field.CREATED_BY));
			vo.setCreatedDate(rs.getDate(BaseVo.Field.CREATED_DATE));
			vo.setUpdatedBy(rs.getString(BaseVo.Field.UPDATED_BY));
			vo.setUpdatedDate(rs.getDate(BaseVo.Field.UPDATED_DATE));
			return vo;
		}
	};
}
