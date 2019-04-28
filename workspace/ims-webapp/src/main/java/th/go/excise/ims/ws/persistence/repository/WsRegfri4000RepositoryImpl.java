package th.go.excise.ims.ws.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.ws.persistence.entity.WsRegfri4000;

public class WsRegfri4000RepositoryImpl implements WsRegfri4000RepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsRegfri4000RepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<WsRegfri4000> regfri4000List) {
		logger.info("batchInsert regfri4000List.size()={}", regfri4000List.size());
		
		final int BATCH_SIZE = 1000;
		
		String sql = SqlGeneratorUtils.genSqlInsert(
			"WS_REGFRI4000",
			Arrays.asList(
				"REGFRI4000_ID", "NEW_REG_ID", "CUS_ID", "CUS_FULLNAME", "CUS_ADDRESS",
				"CUS_TELNO", "CUS_EMAIL", "CUS_URL", "FAC_ID", "FAC_FULLNAME",
				"FAC_ADDRESS", "FAC_TELNO", "FAC_EMAIL", "FAC_URL", "OFFICE_CODE",
				"ACTIVE_FLAG", "DUTY_CODE", "CREATED_BY", "CREATED_DATE"
			),
			"WS_REGFRI4000_SEQ");

		commonJdbcTemplate.batchUpdate(sql, regfri4000List, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsRegfri4000>() {
			public void setValues(PreparedStatement ps, WsRegfri4000 regfri4000) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(regfri4000.getNewRegId());
				paramList.add(regfri4000.getCusId());
				paramList.add(regfri4000.getCusFullname());
				paramList.add(regfri4000.getCusAddress());
				paramList.add(regfri4000.getCusTelno());
				paramList.add(regfri4000.getCusEmail());
				paramList.add(regfri4000.getCusUrl());
				paramList.add(regfri4000.getFacId());
				paramList.add(regfri4000.getFacFullname());
				paramList.add(regfri4000.getFacAddress());
				paramList.add(regfri4000.getFacTelno());
				paramList.add(regfri4000.getFacEmail());
				paramList.add(regfri4000.getFacUrl());
				paramList.add(regfri4000.getOfficeCode());
				paramList.add(regfri4000.getActiveFlag());
				paramList.add(regfri4000.getDutyCode());
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

	@Override
	public void truncate() {
		commonJdbcTemplate.update("TRUNCATE TABLE WS_REGFRI4000");
	}
	
}
