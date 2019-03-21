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
import th.go.excise.ims.oa.persistence.entity.OaAchCustomer;
import th.go.excise.ims.oa.persistence.entity.OaAchCustomerLicenDtl;
import th.go.excise.ims.oa.vo.Oa040106FormVo;
import th.go.excise.ims.oa.vo.Oa0412Vo;

@Repository
public class Oa0412JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<OaAchCustomer> getDataFilter(Oa0412Vo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM OA_ACH_CUSTOMER WHERE 1=1 AND IS_DELETED='N' ");

		if (StringUtils.isNotBlank(request.getArea())) {
			sql.append(" AND OFFICE_CODE = ? ");
			params.add(request.getArea());
		} else if (StringUtils.isNotBlank(request.getSector())) {
			sql.append(" AND OFFICE_CODE = ? ");
			params.add(request.getSector());
		}

		sql.append(" ORDER BY CREATED_DATE DESC");

		String limit = OracleUtils.limitForDatable(sql.toString(), request.getStart(), request.getLength());
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<OaAchCustomer> datas = this.commonJdbcTemplate.query(limit, params.toArray(),
				new BeanPropertyRowMapper(OaAchCustomer.class));

		return datas;
	}

	/* count datatable */
	public Integer countDatafilter(Oa0412Vo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM OA_ACH_CUSTOMER WHERE IS_DELETED='N' ");

		if (StringUtils.isNotBlank(request.getArea())) {
			sql.append(" AND OFFICE_CODE = ? ");
			params.add(request.getArea());
		} else if (StringUtils.isNotBlank(request.getSector())) {
			sql.append(" AND OFFICE_CODE = ? ");
			params.add(request.getSector());
		}

		sql.append(" ORDER BY CREATED_DATE DESC");

		String sqlCount = OracleUtils.countForDataTable(sql.toString());
		Integer count = this.commonJdbcTemplate.queryForObject(sqlCount, params.toArray(), Integer.class);

		return count;
	}

	public List<Oa040106FormVo> findByCustomerIdAndLicenseType(BigDecimal customerId) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM OA_ACH_CUSTOMER_LICEN WHERE IS_DELETED='N' ");
		sql.append(" AND OA_CUSTOMER_ID = ? AND IS_DELETED = 'N' ");
		sql.append(" AND IS_DELETED = 'N' ");
		sql.append(" ORDER BY OA_CUSLICENSE_ID DESC ");
		params.add(customerId);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<Oa040106FormVo> lists = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(Oa040106FormVo.class));
		return lists;
	}

	public List<OaAchCustomerLicenDtl> findByLicenseId(BigDecimal licenseId) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM OA_ACH_CUSTOMER_LICEN_DTL WHERE IS_DELETED='N' ");
		sql.append(" AND OA_CUSLICENSE_ID = ? AND IS_DELETED = 'N' ");
		params.add(licenseId);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<OaAchCustomerLicenDtl> lists = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(OaAchCustomerLicenDtl.class));
		return lists;
	}

}
