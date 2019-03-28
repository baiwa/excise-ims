package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaPlanDayActivity;

@Repository
public class IaPlanDayActivityJdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public List<IaPlanDayActivity> findplanDayActByidDtl(BigDecimal idDtl) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append(" SELECT * FROM IA_PLAN_DAY_ACTIVITY WHERE 1=1 AND IS_DELETED='N' ");
		if (idDtl != null) {
			sql.append(" AND PLAN_DTL_ID = ? ");
			params.add(idDtl);
		}

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<IaPlanDayActivity> planDayAct = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(IaPlanDayActivity.class));
		return planDayAct;
	}

}
