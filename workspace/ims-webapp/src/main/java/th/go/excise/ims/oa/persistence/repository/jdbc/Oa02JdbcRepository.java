package th.go.excise.ims.oa.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;

import th.go.excise.ims.oa.persistence.entity.OaPlan;
import th.go.excise.ims.oa.vo.Oa0206FormVo;
import th.go.excise.ims.oa.vo.Oa0206Vo;

@Repository
public class Oa02JdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public List<OaPlan> findLubricantPlan(String year) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT PL.OA_PLAN_ID,PL.AUDIT_START,PL.AUDIT_END,PL.OFFICE_CODE,PL.STATUS,PL.FISCOL_YEAR,PL.REMARK  FROM OA_PLAN PL ");
		sql.append(" INNER JOIN OA_LICENSE_PLAN LP ON PL.OA_PLAN_ID = LP.OA_PLAN_ID ");
		sql.append(" INNER JOIN OA_CUSTOMER_LICEN CL ON LP.LICENSE_ID = CL.OA_CUSLICENSE_ID ");
		sql.append(" WHERE PL.IS_DELETED = 'N' AND PL.FISCOL_YEAR = ? ");
		sql.append( " GROUP BY  (PL.OA_PLAN_ID,PL.AUDIT_START,PL.AUDIT_END,PL.OFFICE_CODE,PL.STATUS,PL.FISCOL_YEAR,PL.REMARK) ");
		params.add(year);
		
		List<OaPlan> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),new BeanPropertyRowMapper(OaPlan.class));
		
		return datas;
	}
	
	
	public List<OaPlan> findHydrocarbonPlan(String year) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT PL.OA_PLAN_ID,PL.AUDIT_START,PL.AUDIT_END,PL.OFFICE_CODE,PL.STATUS,PL.FISCOL_YEAR,PL.REMARK  FROM OA_PLAN PL ");
		sql.append(" INNER JOIN OA_LICENSE_PLAN LP ON PL.OA_PLAN_ID = LP.OA_PLAN_ID ");
		sql.append(" INNER JOIN OA_HYD_CUSTOMER_LICEN CL ON LP.LICENSE_ID = CL.OA_CUSLICENSE_ID ");
		sql.append(" WHERE PL.IS_DELETED = 'N' AND PL.FISCOL_YEAR = ? ");
		sql.append( " GROUP BY  (PL.OA_PLAN_ID,PL.AUDIT_START,PL.AUDIT_END,PL.OFFICE_CODE,PL.STATUS,PL.FISCOL_YEAR,PL.REMARK) ");
		params.add(year);
		
		List<OaPlan> datas = this.commonJdbcTemplate.query(sql.toString(), params.toArray(),new BeanPropertyRowMapper(OaPlan.class));
		
		return datas;
	}

}
