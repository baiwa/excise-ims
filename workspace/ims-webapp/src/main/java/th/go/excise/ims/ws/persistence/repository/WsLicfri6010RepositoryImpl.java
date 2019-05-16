package th.go.excise.ims.ws.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.ws.persistence.entity.WsLicfri6010;

public class WsLicfri6010RepositoryImpl implements WsLicfri6010RepositoryCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchMerge(List<WsLicfri6010> WsLicfri6010List) {

		final int BATCH_SIZE = 1000;

		List<String> insertColumnNames = new ArrayList<>(
				Arrays.asList("WS_LICFRI6010_ID", "OFFCODE", "LIC_TYPE", "LIC_NO", "LIC_NAME", "LIC_FEE", "LIC_INTERIOR", "LIC_PRICE", "LIC_DATE", "START_DATE", "EXP_DATE", "SEND_DATE", "PRINT_CODE", "NID", "NEW_REG_ID", "CUS_FULLNAME", "FAC_FULLNAME", "INC_CODE", "CREATED_BY"));

		String sql = SqlGeneratorUtils.genSqlInsert("WS_LICFRI6010", insertColumnNames, "WS_LICFRI6010_SEQ");

		commonJdbcTemplate.batchUpdate(sql, WsLicfri6010List, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsLicfri6010>() {
			public void setValues(PreparedStatement ps, WsLicfri6010 entity) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				paramList.add(entity.getOffcode());
				paramList.add(entity.getLicType());
				paramList.add(entity.getLicNo());
				paramList.add(entity.getLicName());
				paramList.add(entity.getLicFee());
				paramList.add(entity.getLicInterior());
				paramList.add(entity.getLicPrice());
				paramList.add(entity.getLicDate());
				paramList.add(entity.getStartDate());
				paramList.add(entity.getExpDate());
				paramList.add(entity.getSendDate());
				paramList.add(entity.getPrintCount());
				paramList.add(entity.getNid());
				paramList.add(entity.getNewRegId());
				paramList.add(entity.getCusFullname());
				paramList.add(entity.getFacFullname());
				paramList.add(entity.getIncCode());
				paramList.add(SYSTEM_USER.BATCH);
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
