package th.go.excise.ims.preferences.persistence.repository;

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
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.ws.client.pcc.inquiryholiday.model.Holiday;

public class ExciseHolidayRepositoryImpl implements ExciseHolidayRepositoryCustom {
	private static final Logger logger = LoggerFactory.getLogger(ExciseHolidayRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchUpdate(List<Holiday> holidayList) {
		logger.info("batchUpdate");

		final int BATCH_SIZE = 1000;

		List<String> insertColumnNames = new ArrayList<>(Arrays.asList("EH.HOLIDAY_ID", "EH.HOLIDAY_DATE", "EH.BEGIN_DATE", "EH.CREATED_BY", "EH.CREATED_DATE"));

		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO EXCISE_HOLIDAY EH ");
		sql.append(" USING DUAL ");
		sql.append(" ON (EH.HOLIDAY_ID = ?) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append("     EH.HOLIDAY_DATE = ? ");
		sql.append("     EH.IS_DELETED = 'N', ");
		sql.append("     EH.UPDATED_BY = ?, ");
		sql.append("     EH.UPDATED_DATE = ? ");
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (EXCISE_BANK_SEQ.NEXTVAL" + org.apache.commons.lang3.StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");

		commonJdbcTemplate.batchUpdate(sql.toString(), holidayList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<Holiday>() {
			public void setValues(PreparedStatement ps, Holiday holiday) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				// Using Condition
				paramList.add(holiday.getHolidayDate());
				// Update Statement

				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				// Insert Statement
				paramList.add(holiday.getHolidayDate());
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
