package co.th.ims.excise.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import co.th.baiwa.buckwaframework.common.bean.BaseVo;
import co.th.ims.excise.domain.ExciseProvice;

@Repository
public class ExciseProviceDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public List<ExciseProvice> findbyCriteria(ExciseProvice exciseProvice) {
		List<ExciseProvice> list = new ArrayList<ExciseProvice>();
		List<Object> param = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder("SELECT * FROM EXCISE_PROVICE WHERE 1 = 1");
		if(exciseProvice != null) {
			
			if(exciseProvice.getGeoId() != null) {
				sql.append(" AND ").append(ExciseProvice.Field.GEO_ID).append(" ? ");
				param.add(exciseProvice.getGeoId());
			}
			if(exciseProvice.getProviceId() != null) {
				sql.append(" AND ").append(ExciseProvice.Field.PROVINCE_ID).append(" ? ");
				param.add(exciseProvice.getProviceId());
			}
			if(StringUtils.isEmpty(exciseProvice.getProviceCode())) {
				sql.append(" AND ").append(ExciseProvice.Field.PROVINCE_CODE).append(" ? ");
				param.add(exciseProvice.getProviceCode());
			}
			if(StringUtils.isEmpty(exciseProvice.getProviceName())) {
				sql.append(" AND ").append(ExciseProvice.Field.PROVINCE_NAME).append(" ? ");
				param.add(exciseProvice.getProviceName());
			}
		}
		list = jdbcTemplate.query(sql.toString(),param.toArray(), rowMapper);
		return list;
	}
	
	protected RowMapper<ExciseProvice> rowMapper = new RowMapper<ExciseProvice>() {
		@Override
		public ExciseProvice mapRow(ResultSet rs, int arg1) throws SQLException {
			ExciseProvice vo = new ExciseProvice();
			vo.setProviceId(rs.getBigDecimal(ExciseProvice.Field.PROVINCE_ID));
			vo.setGeoId(rs.getBigDecimal(ExciseProvice.Field.GEO_ID));
			vo.setProviceCode(rs.getString(ExciseProvice.Field.PROVINCE_CODE));
			vo.setProviceName(rs.getString(ExciseProvice.Field.PROVINCE_NAME));
			
			vo.setIsDeleted(rs.getString(BaseVo.Field.IS_DELETED));
			vo.setCreatedBy(rs.getString(BaseVo.Field.CREATED_BY));
			vo.setCreatedDate(rs.getDate(BaseVo.Field.CREATED_DATE));
			vo.setUpdatedBy(rs.getString(BaseVo.Field.UPDATED_BY));
			vo.setUpdatedDate(rs.getDate(BaseVo.Field.UPDATED_DATE));
			return vo;
		}
	};
}
