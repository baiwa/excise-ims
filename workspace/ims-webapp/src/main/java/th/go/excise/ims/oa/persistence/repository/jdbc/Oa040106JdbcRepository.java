package th.go.excise.ims.oa.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.oa.persistence.entity.OaCustomerLicenDetail;
import th.go.excise.ims.oa.vo.Oa020106ButtonVo;

@Repository
public class Oa040106JdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public Oa020106ButtonVo findButtonIdById(BigDecimal id) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" select LI.LICENSE_TYPE as LICENSE_TYPE, L.OA_LUBRICANTS_ID as OA_LUBRICANTS_ID, L.OA_PLAN_ID as OA_PLAN_ID, ");
		sql.append(" L.LICENSE_NO as LICENSE_NO, D.OA_LUBRICANTS_DTL_ID as OA_LUBRICANTS_DTL_ID, ");
		sql.append(" LI.OA_CUSLICENSE_ID as OA_CUSLICENSE_ID, LI.OA_CUSTOMER_ID as OA_CUSTOMER_ID from OA_LUBRICANTS L ");
		sql.append(" inner join OA_CUSTOMER_LICEN LI on L.LICENSE_NO = LI.LICENSE_NO ");
		sql.append(" inner join OA_LUBRICANTS_DTL D on D.OA_LUBRICANTS_ID = L.OA_LUBRICANTS_ID ");
		sql.append(" where L.IS_DELETED = 'N' and D.IS_DELETED = 'N' and LI.IS_DELETED = 'N' and L.OA_LUBRICANTS_ID = ? ");
		params.add(id);
		Oa020106ButtonVo data = commonJdbcTemplate.queryForObject(sql.toString(), params.toArray(), dataRowmapper);
		return data;
	}

	private RowMapper<Oa020106ButtonVo> dataRowmapper = new RowMapper<Oa020106ButtonVo>() {
		@Override
		public Oa020106ButtonVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Oa020106ButtonVo vo = new Oa020106ButtonVo();
			vo.setLicenseNo(rs.getString("LICENSE_NO"));
			vo.setLicenseType(rs.getString("LICENSE_TYPE"));
			vo.setOaPlanId(rs.getBigDecimal("OA_PLAN_ID"));
			vo.setOaCuslicenseId(rs.getBigDecimal("OA_CUSLICENSE_ID"));
			vo.setOaCustomerId(rs.getBigDecimal("OA_CUSTOMER_ID"));
			vo.setOaLubricantsDtlId(rs.getBigDecimal("OA_LUBRICANTS_DTL_ID"));
			vo.setOaLubricantsId(rs.getBigDecimal("OA_LUBRICANTS_ID"));
			return vo;
		}
	};
	
	public List<OaCustomerLicenDetail> findByLicenseId(BigDecimal licenseId) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM OA_CUSTOMER_LICEN_DETAIL WHERE IS_DELETED='N' ");
		sql.append(" AND OA_CUSLICENSE_ID = ? AND IS_DELETED = 'N' ");
		params.add(licenseId);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<OaCustomerLicenDetail> lists = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(OaCustomerLicenDetail.class));
		return lists;
	}
}
