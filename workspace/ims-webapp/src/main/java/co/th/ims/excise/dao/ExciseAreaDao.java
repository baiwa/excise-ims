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
import co.th.ims.excise.domain.ExciseArea;

@Repository
public class ExciseAreaDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<ExciseArea> findAll() {
		String sqlTemplate = " SELECT * FROM EXCISE_AREA ";
		StringBuilder sql = new StringBuilder(sqlTemplate);
		List<ExciseArea> list = new ArrayList<ExciseArea>();
		list = jdbcTemplate.query(sql.toString(), rowMapper);
		return list;
	}
	
	protected RowMapper<ExciseArea> rowMapper = new RowMapper<ExciseArea>() {
		@Override
		public ExciseArea mapRow(ResultSet rs, int arg1) throws SQLException {
			ExciseArea vo = new ExciseArea();
			
			vo.setAreaId(rs.getBigDecimal(ExciseArea.Field.AREA_ID));
			vo.setSectorId(rs.getBigDecimal(ExciseArea.Field.SECTOR_ID));
			vo.setOfficeCode(rs.getString(ExciseArea.Field.OFFICE_CODE));
			vo.setAreaName(rs.getString(ExciseArea.Field.AREA_NAME));
			vo.setAreaShotName(rs.getString(ExciseArea.Field.AREA_SHOT_NAME));
			vo.setAreaShotName2(rs.getString(ExciseArea.Field.AREA_SHOT_NAME2));
			
			vo.setIsDeleted(rs.getString(BaseVo.Field.IS_DELETED));
			vo.setCreatedBy(rs.getString(BaseVo.Field.CREATED_BY));
			vo.setCreatedDate(rs.getDate(BaseVo.Field.CREATED_DATE));
			vo.setUpdatedBy(rs.getString(BaseVo.Field.UPDATED_BY));
			vo.setUpdatedDate(rs.getDate(BaseVo.Field.UPDATED_DATE));
			return vo;
		}
	};
}
