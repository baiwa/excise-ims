package th.go.excise.ims.ia.persistence.repository.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;

@Repository
public class Int030103JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public void listUpdatePercent(IaRiskFactorsConfig form) {		
		StringBuilder sql = new StringBuilder("UPDATE IA_RISK_FACTORS_CONFIG SET PERCENT = ? WHERE ID = ? ");
		commonJdbcTemplate.update(sql.toString(), new Object[] { form.getPercent() , form.getId() });
	}
}
