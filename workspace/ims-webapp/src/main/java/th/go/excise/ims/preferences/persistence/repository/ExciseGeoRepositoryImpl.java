package th.go.excise.ims.preferences.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.LocalDateTimeConverter;
import th.go.excise.ims.preferences.persistence.entity.ExciseGeo;

public class ExciseGeoRepositoryImpl implements ExciseGeoCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
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
		list = commonJdbcTemplate.query(sql.toString(),param.toArray(), rowMapper);
		return list;
	}
	
	protected RowMapper<ExciseGeo> rowMapper = new RowMapper<ExciseGeo>() {
		@Override
		public ExciseGeo mapRow(ResultSet rs, int arg1) throws SQLException {
			ExciseGeo vo = new ExciseGeo();
			
			vo.setGeoId(rs.getBigDecimal(ExciseGeo.Field.GEO_ID));
			vo.setGeoName(rs.getString(ExciseGeo.Field.GEO_NAME));
			
			vo.setIsDeleted(rs.getString(BaseEntity.Field.IS_DELETED));
			vo.setCreatedBy(rs.getString(BaseEntity.Field.CREATED_BY));
			vo.setCreatedDate(LocalDateTimeConverter.convertToEntityAttribute(rs.getTimestamp(BaseEntity.Field.CREATED_DATE)));
			vo.setUpdatedBy(rs.getString(BaseEntity.Field.UPDATED_BY));
			vo.setUpdatedDate(LocalDateTimeConverter.convertToEntityAttribute(rs.getTimestamp(BaseEntity.Field.UPDATED_DATE)));
			return vo;
		}
	};
}
