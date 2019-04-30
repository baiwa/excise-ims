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

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.ws.client.pcc.inquiryincmast.model.IncomeMaster;

public class ExciseIncMastRepositoryImpl implements ExciseIncMastRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(ExciseIncMastRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchUpdate(List<IncomeMaster> incMastList) {
		logger.info("batchUpdate");

		final int BATCH_SIZE = 1000;

		List<String> insertColumnNames = new ArrayList<>(Arrays.asList("EIM.INC_MAST_ID", "EIM.INC_CODE",
				"EIM.INC_TYPE", "EIM.INC_NAME", "EIM.INC_NAME_PRINT", "EIM.INC_FLAG", "EIM.ACCSEND_CODE",
				"EIM.ACC_CODE", "EIM.CD_FLAG", "EIM.FORM_CODE", "EIM.GROUP_ID", "EIM.INCCOD_CD", "EIM.INCCOD_EXP",
				"EIM.INCCOD_FLAG", "EIM.INCCOD_OTH", "EIM.INCCOD_PRNSTAMP", "EIM.INCGRP_CODE", "EIM.INCGRP_FLAG",
				"EIM.LOCTYP_FLAG", "EIM.LOC_FLAG", "EIM.MONEY_TYPE", "EIM.PRNSTAMP_FLAG", "EIM.REC_FLAG",
				"EIM.BEGIN_DATE", "EIM.CREATED_BY", "EIM.CREATED_DATE"));

		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO EXCISE_INC_MAST EIM ");
		sql.append(" USING DUAL ");
		sql.append(" ON (EIM.INC_CODE = ?) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append("     EIM.INC_TYPE = ?, ");
		sql.append("     EIM.INC_NAME = ?, ");
		sql.append("     EIM.INC_NAME_PRINT = ?, ");
		sql.append("     EIM.INC_FLAG = ?, ");
		sql.append("     EIM.ACCSEND_CODE = ?, ");
		sql.append("     EIM.ACC_CODE = ?, ");
		sql.append("     EIM.CD_FLAG = ?, ");
		sql.append("     EIM.FORM_CODE = ?, ");
		sql.append("     EIM.GROUP_ID = ?, ");
		sql.append("     EIM.INCCOD_CD = ?, ");
		sql.append("     EIM.INCCOD_EXP = ?, ");
		sql.append("     EIM.INCCOD_FLAG = ?, ");
		sql.append("     EIM.INCCOD_OTH = ?, ");
		sql.append("     EIM.INCCOD_PRNSTAMP = ?, ");
		sql.append("     EIM.INCGRP_CODE = ?, ");
		sql.append("     EIM.INCGRP_FLAG = ?, ");
		sql.append("     EIM.LOCTYP_FLAG = ?, ");
		sql.append("     EIM.LOC_FLAG = ?, ");
		sql.append("     EIM.MONEY_TYPE = ?, ");
		sql.append("     EIM.PRNSTAMP_FLAG = ?, ");
		sql.append("     EIM.REC_FLAG = ?, ");
		sql.append("     EIM.IS_DELETED = 'N', ");
		sql.append("     EIM.UPDATED_BY = ?, ");
		sql.append("     EIM.UPDATED_DATE = ? ");
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT ("
				+ org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (EXCISE_INC_MAST_SEQ.NEXTVAL"
				+ org.apache.commons.lang3.StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");

		commonJdbcTemplate.batchUpdate(sql.toString(), incMastList, BATCH_SIZE,
				new ParameterizedPreparedStatementSetter<IncomeMaster>() {
					public void setValues(PreparedStatement ps, IncomeMaster incMast) throws SQLException {
						List<Object> paramList = new ArrayList<Object>();
						// Using Condition
						paramList.add(incMast.getIncCode());
						// Update Statement
						paramList.add(incMast.getIncType());
						paramList.add(incMast.getIncName());
						paramList.add(incMast.getIncNamePrint());
						paramList.add(incMast.getIncFlag());
						paramList.add(incMast.getAccsendCode());
						paramList.add(incMast.getAccCode());
						paramList.add(incMast.getCdFlag());
						paramList.add(incMast.getFormCode());
						paramList.add(incMast.getGroupId());
						paramList.add(incMast.getInccodCd());
						paramList.add(incMast.getInccodExp());
						paramList.add(incMast.getInccodFlag());
						paramList.add(incMast.getInccodOth());
						paramList.add(incMast.getInccodPrnstamp());
						paramList.add(incMast.getIncgrpCode());
						paramList.add(incMast.getIncgrpFlag());
						paramList.add(incMast.getLoctypFlag());
						paramList.add(incMast.getLocFlag());
						paramList.add(incMast.getMoneyType());
						paramList.add(incMast.getPrnstampFlag());
						paramList.add(incMast.getRecFlag());
						paramList.add(SYSTEM_USER.BATCH);
						paramList.add(LocalDateTime.now());
						// Insert Statement
						paramList.add(incMast.getIncCode());
						paramList.add(incMast.getIncType());
						paramList.add(incMast.getIncName());
						paramList.add(incMast.getIncNamePrint());
						paramList.add(incMast.getIncFlag());
						paramList.add(incMast.getAccsendCode());
						paramList.add(incMast.getAccCode());
						paramList.add(incMast.getCdFlag());
						paramList.add(incMast.getFormCode());
						paramList.add(incMast.getGroupId());
						paramList.add(incMast.getInccodCd());
						paramList.add(incMast.getInccodExp());
						paramList.add(incMast.getInccodFlag());
						paramList.add(incMast.getInccodOth());
						paramList.add(incMast.getInccodPrnstamp());
						paramList.add(incMast.getIncgrpCode());
						paramList.add(incMast.getIncgrpFlag());
						paramList.add(incMast.getLoctypFlag());
						paramList.add(incMast.getLocFlag());
						paramList.add(incMast.getMoneyType());
						paramList.add(incMast.getPrnstampFlag());
						paramList.add(incMast.getRecFlag());
						paramList.add(LocalDate.parse(incMast.getBeginDate(), DateTimeFormatter.BASIC_ISO_DATE));
						paramList.add(SYSTEM_USER.BATCH);
						paramList.add(LocalDateTime.now());
						commonJdbcTemplate.preparePs(ps, paramList.toArray());
					}
				});
	}

}
