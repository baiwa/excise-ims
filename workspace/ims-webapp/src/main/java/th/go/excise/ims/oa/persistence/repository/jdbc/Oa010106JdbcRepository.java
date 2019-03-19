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
import th.go.excise.ims.oa.persistence.entity.OaHydCustomerLicenDtl;
import th.go.excise.ims.oa.vo.Oa010106ButtonVo;

@Repository
public class Oa010106JdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public Oa010106ButtonVo findButtonIdById(BigDecimal id) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" select LI.LICENSE_TYPE as LICENSE_TYPE, H.OA_HYDROCARB_ID as OA_HYDROCARB_ID, H.OA_PLAN_ID as OA_PLAN_ID, ");
		sql.append(" H.LICENSE_NO as LICENSE_NO, D.OA_HYDROCARB_DTL_ID as OA_HYDROCARB_DTL_ID, ");
		sql.append(" LI.OA_CUSLICENSE_ID as OA_CUSLICENSE_ID, LI.OA_CUSTOMER_ID as OA_CUSTOMER_ID from OA_HYDROCARB H ");
		sql.append(" inner join OA_HYD_CUSTOMER_LICEN LI on H.LICENSE_NO = LI.LICENSE_NO ");
		sql.append(" inner join OA_HYDROCARB_DTL D on D.OA_HYDROCARB_ID = H.OA_HYDROCARB_ID ");
		sql.append(" where H.IS_DELETED = 'N' and D.IS_DELETED = 'N' and LI.IS_DELETED = 'N' and H.OA_HYDROCARB_ID = ? ");
		params.add(id);
		Oa010106ButtonVo data = commonJdbcTemplate.queryForObject(sql.toString(), params.toArray(), dataRowmapper);
		return data;
	}

	private RowMapper<Oa010106ButtonVo> dataRowmapper = new RowMapper<Oa010106ButtonVo>() {
		@Override
		public Oa010106ButtonVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Oa010106ButtonVo vo = new Oa010106ButtonVo();
			vo.setLicenseNo(rs.getString("LICENSE_NO"));
			vo.setLicenseType(rs.getString("LICENSE_TYPE"));
			vo.setOaPlanId(rs.getBigDecimal("OA_PLAN_ID"));
			vo.setOaCuslicenseId(rs.getBigDecimal("OA_CUSLICENSE_ID"));
			vo.setOaCustomerId(rs.getBigDecimal("OA_CUSTOMER_ID"));
			vo.setOaHydrocarbDtlId(rs.getBigDecimal("OA_HYDROCARB_DTL_ID"));
			vo.setOaHydrocarbId(rs.getBigDecimal("OA_HYDROCARB_ID"));
			return vo;
		}
	};
	
	public List<OaHydCustomerLicenDtl> findByLicenseId(BigDecimal licenseId) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM OA_HYD_CUSTOMER_LICEN_DTL WHERE IS_DELETED='N' ");
		sql.append(" AND OA_CUSLICENSE_ID = ? AND IS_DELETED = 'N' ");
		params.add(licenseId);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<OaHydCustomerLicenDtl> lists = commonJdbcTemplate.query(sql.toString(), params.toArray(), new BeanPropertyRowMapper(OaHydCustomerLicenDtl.class));
		return lists;
	}
}
