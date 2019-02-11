package th.co.baiwa.buckwaframework.preferences.persistence.dao;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("seqDao")
public class SEQDao {
    
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public BigDecimal autoNumberRunningBySeqName(String seqName) {
		String sql = " SELECT "+seqName+".nextval as SEQ FROM DUAL ";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		BigDecimal analysNumber = map != null ? (BigDecimal) map.get("SEQ") : null;
		return analysNumber;
	}

}
