package th.go.excise.ims.ia.persistence.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;

public class IaAuditLicexpHRepositoryImpl implements IaAuditLicexpHRepositoryCustom {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public String generateAuditLicexpNo() {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT AUDIT_LICEXP_SEQ.NEXTVAL FROM DUAL");
		String dataRes = commonJdbcTemplate.queryForObject(sql.toString(), String.class);
		return StringUtils.leftPad(dataRes, 8, "0");
	}

}
