package th.go.excise.ims.preferences.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.ws.client.pcc.inquiryduedateps0112.model.DuedatePs0112;

public class ExciseDuedatePs0112RepositoryImpl implements ExciseDuedatePs0112RepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(ExciseDuedatePs0112RepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public void batchMerge(List<DuedatePs0112> duedatePs0112List) {
		logger.info("batchMerge duedatePs0112List.size()={}", duedatePs0112List.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> updateColumnNames = new ArrayList<>(Arrays.asList(
			"EDPS0112.DUEDATE = ?",
			"EDPS0112.IS_DELETED = ?",
			"EDPS0112.UPDATED_BY = ?",
			"EDPS0112.UPDATED_DATE = ?"
		));
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"EDPS0112.DUEDATE_PS0112_ID",
			"EDPS0112.YEAR",
			"EDPS0112.MONTH",
			"EDPS0112.DUEDATE",
			"EDPS0112.CREATED_BY",
			"EDPS0112.CREATED_DATE"
		));
		
		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO EXCISE_DUEDATE_PS0112 EDPS0112 ");
		sql.append(" USING DUAL ");
		sql.append(" ON (EDPS0112.YEAR = ? AND EDPS0112.MONTH = ?) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (EXCISE_DUEDATE_PS0112_SEQ.NEXTVAL" + org.apache.commons.lang3.StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), duedatePs0112List, BATCH_SIZE, new ParameterizedPreparedStatementSetter<DuedatePs0112>() {
			public void setValues(PreparedStatement ps, DuedatePs0112 duedatePs0112) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				// Using Condition
				paramList.add(duedatePs0112.getYear());
				paramList.add(duedatePs0112.getMonth());
				// Update Statement
				paramList.add(LocalDate.parse(duedatePs0112.getDuedate(), DateTimeFormatter.BASIC_ISO_DATE));
				paramList.add(FLAG.N_FLAG);
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				// Insert Statement
				paramList.add(duedatePs0112.getYear());
				paramList.add(duedatePs0112.getMonth());
				paramList.add(LocalDate.parse(duedatePs0112.getDuedate(), DateTimeFormatter.BASIC_ISO_DATE));
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
}
