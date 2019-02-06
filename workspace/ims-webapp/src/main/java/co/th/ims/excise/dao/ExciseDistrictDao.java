package co.th.ims.excise.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import co.th.baiwa.buckwaframework.common.bean.BaseVo;
import co.th.ims.excise.domain.ExiseDistrict;

public class ExciseDistrictDao {
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<ExiseDistrict> findByCriteria(ExiseDistrict exiseDistrict) {
		List<ExiseDistrict> list = new ArrayList<ExiseDistrict>();
		List<Object> param = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("SELECT * FROM EXCISE_DISTRICT WHERE 1 = 1");
		if(exiseDistrict != null) {
			
			if(exiseDistrict.getDistrictId() != null) {
				sql.append(" AND ").append(ExiseDistrict.Field.DISTRICT_ID).append(" ? ");
				param.add(exiseDistrict.getDistrictId());
			}
			if(exiseDistrict.getProvinceId() != null) {
				sql.append(" AND ").append(ExiseDistrict.Field.PROVINCE_ID).append(" ? ");
				param.add(exiseDistrict.getProvinceId());
			}
			if(exiseDistrict.getGeoId() != null) {
				sql.append(" AND ").append(ExiseDistrict.Field.GEO_ID).append(" ? ");
				param.add(exiseDistrict.getGeoId());
			}
			if(exiseDistrict.getAmphurId() != null) {
				sql.append(" AND ").append(ExiseDistrict.Field.AMPHUR_ID).append(" ? ");
				param.add(exiseDistrict.getAmphurId());
			}
			if(StringUtils.isNotBlank(exiseDistrict.getDistrictCode())) {
				sql.append(" AND ").append(ExiseDistrict.Field.DISTRICT_CODE).append(" ? ");
				param.add(exiseDistrict.getDistrictCode());
			}
			if(StringUtils.isNotBlank(exiseDistrict.getDistrictName())) {
				sql.append(" AND ").append(ExiseDistrict.Field.DISTRICT_NAME).append(" ? ");
				param.add(exiseDistrict.getDistrictName());
			}

			
		}
		list = jdbcTemplate.query(sql.toString(),param.toArray(), rowMapper);
		return list;
	}
	
	protected RowMapper<ExiseDistrict> rowMapper = new RowMapper<ExiseDistrict>() {
		@Override
		public ExiseDistrict mapRow(ResultSet rs, int arg1) throws SQLException {
			ExiseDistrict vo = new ExiseDistrict();
			
			vo.setDistrictId(rs.getBigDecimal(ExiseDistrict.Field.DISTRICT_ID));
			vo.setProvinceId(rs.getBigDecimal(ExiseDistrict.Field.PROVINCE_ID));
			vo.setGeoId(rs.getBigDecimal(ExiseDistrict.Field.GEO_ID));
			vo.setAmphurId(rs.getBigDecimal(ExiseDistrict.Field.AMPHUR_ID));
			vo.setDistrictCode(rs.getString(ExiseDistrict.Field.DISTRICT_CODE));
			vo.setDistrictName(rs.getString(ExiseDistrict.Field.DISTRICT_NAME));
			
			vo.setIsDeleted(rs.getString(BaseVo.Field.IS_DELETED));
			vo.setCreatedBy(rs.getString(BaseVo.Field.CREATED_BY));
			vo.setCreatedDate(rs.getDate(BaseVo.Field.CREATED_DATE));
			vo.setUpdatedBy(rs.getString(BaseVo.Field.UPDATED_BY));
			vo.setUpdatedDate(rs.getDate(BaseVo.Field.UPDATED_DATE));
			return vo;
		}
	};

}
