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
import th.go.excise.ims.preferences.persistence.entity.ExciseProvice;

public class ExciseProviceRepositoryImpl implements ExciseProviceCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public List<ExciseProvice> findByCriteria(ExciseProvice exciseProvice) {
		List<ExciseProvice> list = new ArrayList<ExciseProvice>();
		List<Object> param = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("SELECT * FROM EXCISE_PROVICE WHERE 1 = 1 ");
		if(exciseProvice != null) {
			
			if(exciseProvice.getGeoId() != null) {
				sql.append(" AND ").append(ExciseProvice.Field.GEO_ID).append(" = ? ");
				param.add(exciseProvice.getGeoId());
			}
			if(exciseProvice.getProvinceId()!= null) {
				sql.append(" AND ").append(ExciseProvice.Field.PROVINCE_ID).append(" = ? ");
				param.add(exciseProvice.getProvinceId());
			}
			if(StringUtils.isNotBlank(exciseProvice.getProvinceCode())) {
				sql.append(" AND ").append(ExciseProvice.Field.PROVINCE_CODE).append(" = ? ");
				param.add(exciseProvice.getProvinceCode());
			}
			if(StringUtils.isNotBlank(exciseProvice.getProvinceName())) {
				sql.append(" AND ").append(ExciseProvice.Field.PROVINCE_NAME).append(" = ? ");
				param.add(exciseProvice.getProvinceName());
			}
		}
		list = commonJdbcTemplate.query(sql.toString(),param.toArray(), rowMapper);
		return list;
	}
	
	protected RowMapper<ExciseProvice> rowMapper = new RowMapper<ExciseProvice>() {
		@Override
		public ExciseProvice mapRow(ResultSet rs, int arg1) throws SQLException {
			ExciseProvice vo = new ExciseProvice();
			vo.setProvinceId(rs.getBigDecimal(ExciseProvice.Field.PROVINCE_ID));
			vo.setGeoId(rs.getBigDecimal(ExciseProvice.Field.GEO_ID));
			vo.setProvinceCode(rs.getString(ExciseProvice.Field.PROVINCE_CODE));
			vo.setProvinceName(rs.getString(ExciseProvice.Field.PROVINCE_NAME));
			
			vo.setIsDeleted(rs.getString(BaseEntity.Field.IS_DELETED));
			vo.setCreatedBy(rs.getString(BaseEntity.Field.CREATED_BY));
			vo.setCreatedDate(LocalDateTimeConverter.convertToEntityAttribute(rs.getTimestamp(BaseEntity.Field.CREATED_DATE)));
			vo.setUpdatedBy(rs.getString(BaseEntity.Field.UPDATED_BY));
			vo.setUpdatedDate(LocalDateTimeConverter.convertToEntityAttribute(rs.getTimestamp(BaseEntity.Field.UPDATED_DATE)));
			return vo;
		}
	};

}
