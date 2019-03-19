package th.go.excise.ims.oa.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.go.excise.ims.oa.persistence.entity.OaCustomer;
import th.go.excise.ims.oa.vo.Oa0107Vo;

@Repository
public class Oa0107JdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<OaCustomer> getDataFilter(Oa0107Vo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM OA_HYD_CUSTOMER WHERE 1=1 AND IS_DELETED='N' ");
		
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
		List<OaCustomer> datas = this.commonJdbcTemplate.query(limit, params.toArray(),
				new BeanPropertyRowMapper(OaCustomer.class));
		
		return datas;
	}

	/* count datatable */
	public Integer countDatafilter(Oa0107Vo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM OA_HYD_CUSTOMER WHERE IS_DELETED='N' ");
		
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
}
