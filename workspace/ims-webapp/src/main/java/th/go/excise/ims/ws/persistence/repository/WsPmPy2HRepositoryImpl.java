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

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.ws.persistence.entity.WsPmPy2H;

public class WsPmPy2HRepositoryImpl implements WsPmPy2HRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(WsPmPy2HRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchMerge(List<WsPmPy2H> pmPy2HList) {
		logger.info("batchMerge pmPy2HList.size()={}", pmPy2HList.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> updateColumnNames = new ArrayList<>(Arrays.asList(
//			"PY2.OFF_CODE = ? ",
			"PY2.OFF_NAME = ? ",
			"PY2.FORM_YEAR = ? ",
//			"PY2.FORM_CODE = ? ",
			"PY2.FORM_NAME = ? ",
			"PY2.FORM_ROUND = ? ",
			"PY2.FORM_STATUS = ? ",
			"PY2.FORM_STATUS_DESC = ? ",
			"PY2.IS_DELETED = ?",
			"PY2.UPDATED_BY = ?",
			"PY2.UPDATED_DATE = ?"
		));
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"PY2.PM_PY2_H_SEQ",
			"PY2.OFF_CODE",
			"PY2.OFF_NAME",
			"PY2.FORM_YEAR",
			"PY2.FORM_CODE",
			"PY2.FORM_NAME",
			"PY2.FORM_ROUND",
			"PY2.FORM_STATUS",
			"PY2.FORM_STATUS_DESC",
			"PY2.CREATED_BY",
			"PY2.CREATED_DATE"
		));
		
		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO WS_PM_PY2_H PY2 ");
		sql.append(" USING DUAL ");
		sql.append(" ON (PY2.OFF_CODE = ? AND PY2.FORM_CODE = ?) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (WS_PM_PY2_H_SEQ.NEXTVAL" + org.apache.commons.lang3.StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), pmPy2HList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsPmPy2H>() {
			public void setValues(PreparedStatement ps, WsPmPy2H pmPy2H) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				// Using Condition
				paramList.add(pmPy2H.getOffCode());
				paramList.add(pmPy2H.getFormCode());
				// Update Statement
				paramList.add(pmPy2H.getOffName());
				paramList.add(pmPy2H.getFormYear());
				paramList.add(pmPy2H.getFormName());
				paramList.add(pmPy2H.getFormRound());
				paramList.add(pmPy2H.getFormStatus());
				paramList.add(pmPy2H.getFormStatusDesc());
				paramList.add(FLAG.N_FLAG);
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				// Insert Statement
				paramList.add(pmPy2H.getOffCode());
				paramList.add(pmPy2H.getOffName());
				paramList.add(pmPy2H.getFormYear());
				paramList.add(pmPy2H.getFormCode());
				paramList.add(pmPy2H.getFormName());
				paramList.add(pmPy2H.getFormRound());
				paramList.add(pmPy2H.getFormStatus());
				paramList.add(pmPy2H.getFormStatusDesc());
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
