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
import co.th.ims.excise.domain.ExciseAmphur;

@Repository
public class ExciseAmphurDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	
	public List<ExciseAmphur> findByCriteria(BigDecimal amphurId) {
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(amphurId);
		String sqlTemplate = " SELECT * FROM EXCISE_AMPHUR WHERE AMPHUR_ID = ? ";
		StringBuilder sql = new StringBuilder(sqlTemplate);
		List<ExciseAmphur> list = new ArrayList<ExciseAmphur>();
		list = jdbcTemplate.query(sql.toString(),paramList.toArray(), rowMapper);
		return list;
	}
	
	protected RowMapper<ExciseAmphur> rowMapper = new RowMapper<ExciseAmphur>() {
		@Override
		public ExciseAmphur mapRow(ResultSet rs, int arg1) throws SQLException {
			ExciseAmphur vo = new ExciseAmphur();
			
			vo.setAmphurId(rs.getBigDecimal(ExciseAmphur.Field.AMPHUR_ID));
			vo.setGeoId(rs.getBigDecimal(ExciseAmphur.Field.GEO_ID));
			vo.setProvinceId(rs.getBigDecimal(ExciseAmphur.Field.PROVINCE_ID));
			vo.setAmphurCode(rs.getString(ExciseAmphur.Field.AMPHUR_CODE));
			vo.setAmphurName(rs.getString(ExciseAmphur.Field.AMPHUR_NAME));
			
			vo.setIsDeleted(rs.getString(BaseVo.Field.IS_DELETED));
			vo.setCreatedBy(rs.getString(BaseVo.Field.CREATED_BY));
			vo.setCreatedDate(rs.getDate(BaseVo.Field.CREATED_DATE));
			vo.setUpdatedBy(rs.getString(BaseVo.Field.UPDATED_BY));
			vo.setUpdatedDate(rs.getDate(BaseVo.Field.UPDATED_DATE));
			return vo;
		}
	};

}
