package th.co.baiwa.excise.ia.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.excise.ia.persistence.entity.License;
import th.co.baiwa.excise.utils.BeanUtils;

public class LicenseRepositoryImpl implements LicenseRepositoryCustom{
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private final CommonJdbcTemplate commonJdbcTemplate;

	@Autowired
	public LicenseRepositoryImpl(CommonJdbcTemplate commonJdbcTemplate) {
		this.commonJdbcTemplate = commonJdbcTemplate;
	}
	
	@Override
	public List<License> searchLicenseByLicDateOffCode(String startDate, String endDate, String offCode) {
		logger.info("searchLicenseByLicDateOffCode {} , {} , {}" , startDate ,endDate , offCode );
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT COUNT(1) COUNT , ");
		sql.append(" LIC_NAME , ");
		sql.append(" PRINT_CODE , ");
		sql.append(" sum(LIC_PRICE) LIC_PRICE ");
		sql.append(" FROM IA_LICENSE ");
		sql.append(" WHERE IS_DELETED='N' ");
		sql.append(" AND LIC_DATE >=? ");
		sql.append(" AND LIC_DATE <=? ");
		sql.append(" AND OFF_CODE = ? ");
		sql.append(" GROUP BY LIC_NAME , PRINT_CODE ");
		sql.append(" order by LIC_NAME ");
		params.add(startDate);
		params.add(endDate);
		params.add(offCode);
		return commonJdbcTemplate.executeQuery(sql.toString(), params.toArray(), mappingData);
	}
	
	private RowMapper<License> mappingData = new RowMapper<License>() {

		@Override
		public License mapRow(ResultSet rs, int arg1) throws SQLException {
			License lic = new License();
			lic.setLicAmount(rs.getBigDecimal("COUNT"));
			lic.setLicName(rs.getString("LIC_NAME"));
			lic.setPrintCode(BeanUtils.isNotEmpty(rs.getString("PRINT_CODE")) ? rs.getString("PRINT_CODE") : "-");
			lic.setLicPrice(BeanUtils.isNotEmpty(rs.getString("LIC_PRICE")) ? rs.getString("LIC_PRICE") : "-");
			return lic;

		}

	};
	
}
