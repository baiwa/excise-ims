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
import th.go.excise.ims.preferences.domain.ExciseDistrict;

@Repository
public class ExciseDistrictDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<ExciseDistrict> findByCriteria(ExciseDistrict exiseDistrict) {
		List<ExciseDistrict> list = new ArrayList<ExciseDistrict>();
		List<Object> param = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("SELECT * FROM EXCISE_DISTRICT WHERE 1 = 1");
		if(exiseDistrict != null) {
			
			if(exiseDistrict.getDistrictId() != null) {
				sql.append(" AND ").append(ExciseDistrict.Field.DISTRICT_ID).append(" = ? ");
				param.add(exiseDistrict.getDistrictId());
			}
			if(exiseDistrict.getProvinceId() != null) {
				sql.append(" AND ").append(ExciseDistrict.Field.PROVINCE_ID).append(" = ? ");
				param.add(exiseDistrict.getProvinceId());
			}
			if(exiseDistrict.getGeoId() != null) {
				sql.append(" AND ").append(ExciseDistrict.Field.GEO_ID).append(" = ? ");
				param.add(exiseDistrict.getGeoId());
			}
			if(exiseDistrict.getAmphurId() != null) {
				sql.append(" AND ").append(ExciseDistrict.Field.AMPHUR_ID).append(" = ? ");
				param.add(exiseDistrict.getAmphurId());
			}
			if(StringUtils.isNotBlank(exiseDistrict.getDistrictCode())) {
				sql.append(" AND ").append(ExciseDistrict.Field.DISTRICT_CODE).append(" = ? ");
				param.add(exiseDistrict.getDistrictCode());
			}
			if(StringUtils.isNotBlank(exiseDistrict.getDistrictName())) {
				sql.append(" AND ").append(ExciseDistrict.Field.DISTRICT_NAME).append(" = ? ");
				param.add(exiseDistrict.getDistrictName());
			}

			
		}
		list = jdbcTemplate.query(sql.toString(),param.toArray(), rowMapper);
		return list;
	}
	
	protected RowMapper<ExciseDistrict> rowMapper = new RowMapper<ExciseDistrict>() {
		@Override
		public ExciseDistrict mapRow(ResultSet rs, int arg1) throws SQLException {
			ExciseDistrict vo = new ExciseDistrict();
			
			vo.setDistrictId(rs.getBigDecimal(ExciseDistrict.Field.DISTRICT_ID));
			vo.setProvinceId(rs.getBigDecimal(ExciseDistrict.Field.PROVINCE_ID));
			vo.setGeoId(rs.getBigDecimal(ExciseDistrict.Field.GEO_ID));
			vo.setAmphurId(rs.getBigDecimal(ExciseDistrict.Field.AMPHUR_ID));
			vo.setDistrictCode(rs.getString(ExciseDistrict.Field.DISTRICT_CODE));
			vo.setDistrictName(rs.getString(ExciseDistrict.Field.DISTRICT_NAME));
			
			vo.setIsDeleted(rs.getString(BaseVo.Field.IS_DELETED));
			vo.setCreatedBy(rs.getString(BaseVo.Field.CREATED_BY));
			vo.setCreatedDate(rs.getDate(BaseVo.Field.CREATED_DATE));
			vo.setUpdatedBy(rs.getString(BaseVo.Field.UPDATED_BY));
			vo.setUpdatedDate(rs.getDate(BaseVo.Field.UPDATED_DATE));
			return vo;
		}
	};

}
