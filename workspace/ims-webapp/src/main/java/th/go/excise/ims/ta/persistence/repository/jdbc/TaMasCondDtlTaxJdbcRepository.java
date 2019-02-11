package th.go.excise.ims.ta.persistence.repository.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ta.vo.CondGroupVo;

@Repository
public class TaMasCondDtlTaxJdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<CondGroupVo> getCondGroup() {
		String sql = "SELECT * FROM TA_MAS_COND_DTL_TAX WHERE IS_DELETED='N' AND BUDGET_YEAR='2562'";

		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<CondGroupVo> datas = this.commonJdbcTemplate.query(sql, new BeanPropertyRowMapper(CondGroupVo.class));

		return datas;
	}
}
