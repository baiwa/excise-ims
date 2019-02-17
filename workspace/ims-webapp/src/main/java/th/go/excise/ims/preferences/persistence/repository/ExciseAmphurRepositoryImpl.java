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
import th.go.excise.ims.preferences.persistence.entity.ExciseAmphur;

public class ExciseAmphurRepositoryImpl implements ExciseAmphurCustom{

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<ExciseAmphur> findByCriteria(ExciseAmphur exciseAmphur) {
		List<ExciseAmphur> list = new ArrayList<ExciseAmphur>();
		List<Object> param = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("SELECT * FROM EXCISE_AMPHUR WHERE 1 = 1");
		if (exciseAmphur != null) {

			if (exciseAmphur.getAmphurId() != null) {
				sql.append(" AND ").append(ExciseAmphur.Field.AMPHUR_ID).append(" = ? ");
				param.add(exciseAmphur.getAmphurId());
			}
			if (exciseAmphur.getGeoId() != null) {
				sql.append(" AND ").append(ExciseAmphur.Field.GEO_ID).append(" = ? ");
				param.add(exciseAmphur.getGeoId());
			}
			if (exciseAmphur.getProvinceId() != null) {
				sql.append(" AND ").append(ExciseAmphur.Field.PROVINCE_ID).append(" = ? ");
				param.add(exciseAmphur.getProvinceId());
			}
			if (StringUtils.isNotBlank(exciseAmphur.getAmphurCode())) {
				sql.append(" AND ").append(ExciseAmphur.Field.AMPHUR_CODE).append(" = ? ");
				param.add(exciseAmphur.getAmphurCode());
			}
			if (StringUtils.isNotBlank(exciseAmphur.getAmphurName())) {
				sql.append(" AND ").append(ExciseAmphur.Field.AMPHUR_NAME).append(" = ? ");
				param.add(exciseAmphur.getAmphurName());
			}

		}
		list = commonJdbcTemplate.query(sql.toString(), param.toArray(), rowMapper);
		return list;
	}

	protected RowMapper<ExciseAmphur> rowMapper = new RowMapper<ExciseAmphur>() {
		

		@Override
		public ExciseAmphur mapRow(ResultSet rs, int rowNum) throws SQLException {
			ExciseAmphur vo = new ExciseAmphur();

			vo.setAmphurId(rs.getBigDecimal(ExciseAmphur.Field.AMPHUR_ID));
			vo.setGeoId(rs.getBigDecimal(ExciseAmphur.Field.GEO_ID));
			vo.setProvinceId(rs.getBigDecimal(ExciseAmphur.Field.PROVINCE_ID));
			vo.setAmphurCode(rs.getString(ExciseAmphur.Field.AMPHUR_CODE));
			vo.setAmphurName(rs.getString(ExciseAmphur.Field.AMPHUR_NAME));
			
			vo.setIsDeleted(rs.getString(BaseEntity.Field.IS_DELETED));
			vo.setCreatedBy(rs.getString(BaseEntity.Field.CREATED_BY));
			vo.setCreatedDate(LocalDateTimeConverter.convertToEntityAttribute(rs.getTimestamp(BaseEntity.Field.CREATED_DATE)));
			vo.setUpdatedBy(rs.getString(BaseEntity.Field.UPDATED_BY));
			vo.setUpdatedDate(LocalDateTimeConverter.convertToEntityAttribute(rs.getTimestamp(BaseEntity.Field.UPDATED_DATE)));
			return vo;
		}
	};



}
