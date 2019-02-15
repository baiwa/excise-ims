package th.go.excise.ims.preferences.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.bean.BaseVo;
import th.go.excise.ims.preferences.domain.ExciseGeo;

@Repository
public class ExciseGeoDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<ExciseGeo> findByCriteria(ExciseGeo exciseGeo) {
		List<ExciseGeo> list = new ArrayList<ExciseGeo>();
		List<Object> param = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("SELECT * FROM EXCISE_GEO WHERE 1 = 1 ");
		if(exciseGeo != null) {
			
			if(exciseGeo.getGeoId() != null) {
				sql.append(" AND ").append(ExciseGeo.Field.GEO_ID).append(" = ? ");
				param.add(exciseGeo.getGeoId());
			}
			
			if(StringUtils.isNotBlank(exciseGeo.getGeoName())) {
				sql.append(" AND ").append(ExciseGeo.Field.GEO_NAME).append(" = ? ");
				param.add(exciseGeo.getGeoName());
			}
			
		}
		list = jdbcTemplate.query(sql.toString(),param.toArray(), rowMapper);
		return list;
	}
	
	protected RowMapper<ExciseGeo> rowMapper = new RowMapper<ExciseGeo>() {
		@Override
		public ExciseGeo mapRow(ResultSet rs, int arg1) throws SQLException {
			ExciseGeo vo = new ExciseGeo();
			
			vo.setGeoId(rs.getBigDecimal(ExciseGeo.Field.GEO_ID));
			vo.setGeoName(rs.getString(ExciseGeo.Field.GEO_NAME));
			
			vo.setIsDeleted(rs.getString(BaseVo.Field.IS_DELETED));
			vo.setCreatedBy(rs.getString(BaseVo.Field.CREATED_BY));
			vo.setCreatedDate(rs.getDate(BaseVo.Field.CREATED_DATE));
			vo.setUpdatedBy(rs.getString(BaseVo.Field.UPDATED_BY));
			vo.setUpdatedDate(rs.getDate(BaseVo.Field.UPDATED_DATE));
			return vo;
		}
	};
}
