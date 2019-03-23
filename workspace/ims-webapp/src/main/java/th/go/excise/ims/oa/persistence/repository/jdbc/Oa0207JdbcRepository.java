package th.go.excise.ims.oa.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.go.excise.ims.oa.persistence.entity.OaCustomerLicenDetail;
import th.go.excise.ims.oa.vo.Oa020106FormVo;
import th.go.excise.ims.oa.vo.Oa0207Vo;
import th.go.excise.ims.oa.vo.Oa207CodeVo;

@Repository
public class Oa0207JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Oa207CodeVo> getDataFilter(Oa0207Vo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT L.IDENTIFY_NO AS IDENTIFY_NO, ");
		sql.append("   L.OFF_CODE         AS OFF_CODE, ");
		sql.append("   L.LICENSE_TYPE     AS LICENSE_TYPE ");
		sql.append(" FROM OA_CUSTOMER_LICEN L ");
		sql.append(" WHERE L.IS_DELETED='N' ");

		if (StringUtils.isNotBlank(request.getArea())) {
			sql.append(" AND L.OFF_CODE = ? ");
			params.add(request.getArea());
		} else if (StringUtils.isNotBlank(request.getSector())) {
			sql.append(" AND L.OFF_CODE = ? ");
			params.add(request.getSector());
		}
		
		sql.append(" GROUP BY L.IDENTIFY_NO, ");
		sql.append("   L.OFF_CODE, ");
		sql.append("   L.LICENSE_TYPE ");

//		sql.append(" ORDER BY L.CREATED_DATE DESC");

		String limit = OracleUtils.limitForDatable(sql.toString(), request.getStart(), request.getLength());
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Oa207CodeVo> datas = this.commonJdbcTemplate.query(limit, params.toArray(),
				new BeanPropertyRowMapper(Oa207CodeVo.class));

		return datas;
	}

	/* count datatable */
	public Integer countDatafilter(Oa0207Vo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT L.IDENTIFY_NO AS IDENTIFY_NO, ");
		sql.append("   L.OFF_CODE         AS OFF_CODE, ");
		sql.append("   L.LICENSE_TYPE     AS LICENSE_TYPE ");
		sql.append(" FROM OA_CUSTOMER_LICEN L ");
		sql.append(" WHERE L.IS_DELETED='N' ");

		if (StringUtils.isNotBlank(request.getArea())) {
			sql.append(" AND L.OFF_CODE = ? ");
			params.add(request.getArea());
		} else if (StringUtils.isNotBlank(request.getSector())) {
			sql.append(" AND L.OFF_CODE = ? ");
			params.add(request.getSector());
		}
		
		sql.append(" GROUP BY L.IDENTIFY_NO, ");
		sql.append("   L.OFF_CODE, ");
		sql.append("   L.LICENSE_TYPE ");

//		sql.append(" ORDER BY L.CREATED_DATE DESC");

		String sqlCount = OracleUtils.countForDataTable(sql.toString());
		Integer count = this.commonJdbcTemplate.queryForObject(sqlCount, params.toArray(), Integer.class);

		return count;
	}

	public List<Oa020106FormVo> findByCustomerIdAndLicenseType(BigDecimal customerId, String licenseType) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM OA_CUSTOMER_LICEN WHERE IS_DELETED='N' ");
		sql.append(" AND OA_CUSTOMER_ID = ? AND IS_DELETED = 'N' ");
		sql.append(" AND LICENSE_TYPE = ? AND IS_DELETED = 'N' ");
		sql.append(" ORDER BY OA_CUSLICENSE_ID DESC ");
		params.add(customerId);
		params.add(licenseType);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Oa020106FormVo> lists = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Oa020106FormVo.class));
		return lists;
	}

	public List<OaCustomerLicenDetail> findByLicenseId(BigDecimal licenseId) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM OA_CUSTOMER_LICEN_DETAIL WHERE IS_DELETED='N' ");
		sql.append(" AND OA_CUSLICENSE_ID = ? AND IS_DELETED = 'N' ");
		params.add(licenseId);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<OaCustomerLicenDetail> lists = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(OaCustomerLicenDetail.class));
		return lists;
	}

}
