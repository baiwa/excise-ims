package th.go.excise.ims.ws.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ws.persistence.entity.WsRegfri4000;

public class WsRegfri4000RepositoryImpl implements WsRegfri4000RepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsRegfri4000RepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchMerge(List<WsRegfri4000> regfri4000List) {
		logger.info("batchMerge regfri4000List.size()={}", regfri4000List.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> updateColumnNames = new ArrayList<>(Arrays.asList(
			"WREG4000.CUS_ID = ?",
			"WREG4000.CUS_FULLNAME = ?",
			"WREG4000.CUS_ADDRESS = ?",
			"WREG4000.CUS_TELNO = ?",
			"WREG4000.CUS_EMAIL = ?",
			"WREG4000.CUS_URL = ?",
			"WREG4000.FAC_ID = ?",
			"WREG4000.FAC_FULLNAME = ?",
			"WREG4000.FAC_ADDRESS = ?",
			"WREG4000.FAC_TELNO = ?",
			"WREG4000.FAC_EMAIL = ?",
			"WREG4000.FAC_URL = ?",
			"WREG4000.FAC_TYPE = ?",
			"WREG4000.REG_ID = ?",
			"WREG4000.REG_STATUS = ?",
			"WREG4000.REG_DATE = ?",
			"WREG4000.REG_CAPITAL = ?",
			"WREG4000.OFFICE_CODE = ?",
			"WREG4000.ACTIVE_FLAG = ?",
			"WREG4000.DUTY_CODE = ?",
			"WREG4000.IS_DELETED = ?",
			"WREG4000.UPDATED_BY = ?",
			"WREG4000.UPDATED_DATE = ?"
		));
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"WREG4000.REGFRI4000_ID",
			"WREG4000.NEW_REG_ID",
			"WREG4000.CUS_ID",
			"WREG4000.CUS_FULLNAME",
			"WREG4000.CUS_ADDRESS",
			"WREG4000.CUS_TELNO",
			"WREG4000.CUS_EMAIL",
			"WREG4000.CUS_URL",
			"WREG4000.FAC_ID",
			"WREG4000.FAC_FULLNAME",
			"WREG4000.FAC_ADDRESS",
			"WREG4000.FAC_TELNO",
			"WREG4000.FAC_EMAIL",
			"WREG4000.FAC_URL",
			"WREG4000.FAC_TYPE",
			"WREG4000.REG_ID",
			"WREG4000.REG_STATUS",
			"WREG4000.REG_DATE",
			"WREG4000.REG_CAPITAL",
			"WREG4000.OFFICE_CODE",
			"WREG4000.ACTIVE_FLAG",
			"WREG4000.DUTY_CODE",
			"WREG4000.CREATED_BY",
			"WREG4000.CREATED_DATE"
		));
		
		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO WS_REGFRI4000 WREG4000 ");
		sql.append(" USING DUAL ");
		sql.append(" ON (WREG4000.NEW_REG_ID = ?) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (WS_REGFRI4000_SEQ.NEXTVAL" + org.apache.commons.lang3.StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), regfri4000List, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsRegfri4000>() {
			public void setValues(PreparedStatement ps, WsRegfri4000 regfri4000) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				// Using Condition
				paramList.add(regfri4000.getNewRegId());
				// Update Statement
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
				paramList.add(regfri4000.getFacType());
				paramList.add(regfri4000.getRegId());
				paramList.add(regfri4000.getRegStatus());
				paramList.add(regfri4000.getRegDate());
				paramList.add(regfri4000.getRegCapital());
				paramList.add(regfri4000.getOfficeCode());
				paramList.add(regfri4000.getActiveFlag());
				paramList.add(regfri4000.getDutyCode());
				paramList.add(FLAG.N_FLAG);
				paramList.add(regfri4000.getUpdatedBy());
				paramList.add(regfri4000.getUpdatedDate());
				// Insert Statement
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
				paramList.add(regfri4000.getFacType());
				paramList.add(regfri4000.getRegId());
				paramList.add(regfri4000.getRegStatus());
				paramList.add(regfri4000.getRegDate());
				paramList.add(regfri4000.getRegCapital());
				paramList.add(regfri4000.getOfficeCode());
				paramList.add(regfri4000.getActiveFlag());
				paramList.add(regfri4000.getDutyCode());
				paramList.add(regfri4000.getCreatedBy());
				paramList.add(regfri4000.getCreatedDate());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
}
