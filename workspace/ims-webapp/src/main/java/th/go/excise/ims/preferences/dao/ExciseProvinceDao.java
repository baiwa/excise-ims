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
import th.go.excise.ims.preferences.domain.ExciseProvince;

@Repository
public class ExciseProvinceDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<ExciseProvince> findByCriteria(ExciseProvince exciseProvince) {
		List<ExciseProvince> list = new ArrayList<ExciseProvince>();
		List<Object> param = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("SELECT * FROM EXCISE_PROVICE WHERE 1 = 1 ");
		if(exciseProvince != null) {
			
			if(exciseProvince.getGeoId() != null) {
				sql.append(" AND ").append(ExciseProvince.Field.GEO_ID).append(" = ? ");
				param.add(exciseProvince.getGeoId());
			}
			if(exciseProvince.getProviceId() != null) {
				sql.append(" AND ").append(ExciseProvince.Field.PROVINCE_ID).append(" = ? ");
				param.add(exciseProvince.getProviceId());
			}
			if(StringUtils.isNotBlank(exciseProvince.getProviceCode())) {
				sql.append(" AND ").append(ExciseProvince.Field.PROVINCE_CODE).append(" = ? ");
				param.add(exciseProvince.getProviceCode());
			}
			if(StringUtils.isNotBlank(exciseProvince.getProviceName())) {
				sql.append(" AND ").append(ExciseProvince.Field.PROVINCE_NAME).append(" = ? ");
				param.add(exciseProvince.getProviceName());
			}
		}
		list = jdbcTemplate.query(sql.toString(),param.toArray(), rowMapper);
		return list;
	}
	
	protected RowMapper<ExciseProvince> rowMapper = new RowMapper<ExciseProvince>() {
		@Override
		public ExciseProvince mapRow(ResultSet rs, int arg1) throws SQLException {
			ExciseProvince vo = new ExciseProvince();
			vo.setProviceId(rs.getBigDecimal(ExciseProvince.Field.PROVINCE_ID));
			vo.setGeoId(rs.getBigDecimal(ExciseProvince.Field.GEO_ID));
			vo.setProviceCode(rs.getString(ExciseProvince.Field.PROVINCE_CODE));
			vo.setProviceName(rs.getString(ExciseProvince.Field.PROVINCE_NAME));
			
			vo.setIsDeleted(rs.getString(BaseVo.Field.IS_DELETED));
			vo.setCreatedBy(rs.getString(BaseVo.Field.CREATED_BY));
			vo.setCreatedDate(rs.getDate(BaseVo.Field.CREATED_DATE));
			vo.setUpdatedBy(rs.getString(BaseVo.Field.UPDATED_BY));
			vo.setUpdatedDate(rs.getDate(BaseVo.Field.UPDATED_DATE));
			return vo;
		}
	};
}
