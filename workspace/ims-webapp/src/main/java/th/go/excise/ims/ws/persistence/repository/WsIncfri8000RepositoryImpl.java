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
import th.go.excise.ims.ws.persistence.entity.WsIncfri8000;

public class WsIncfri8000RepositoryImpl implements WsIncfri8000RepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsIncfri8000RepositoryImpl.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchUpdate(List<WsIncfri8000> incfri8000List) {
		logger.info("batchUpdate incfri8000List.size()={}", incfri8000List.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> updateColumnNames = new ArrayList<>(Arrays.asList(
			"WINC8000.RECEIPT_NO = ?",
			"WINC8000.RECEIPT_DATE = ?",
			"WINC8000.TAX_AMOUNT = ?",
			"WINC8000.PEN_AMOUNT = ?",
			"WINC8000.ADD_AMOUNT = ?",
			"WINC8000.REDUCE_AMOUNT = ?",
			"WINC8000.CREDIT_AMOUNT = ?",
			"WINC8000.OFFICE_RECEIVE_CODE = ?",
			"WINC8000.TRN_DATE = ?",
			"WINC8000.DEPOSIT_DATE = ?",
			"WINC8000.SEND_DATE = ?",
			"WINC8000.INCOME_CODE = ?",
			"WINC8000.INCOME_TYPE = ?",
			"WINC8000.IS_DELETED = ?",
			"WINC8000.UPDATED_BY = ?",
			"WINC8000.UPDATED_DATE = ?"
		));
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"WINC8000.INCFRI8000_ID",
			"WINC8000.DATE_TYPE",
			"WINC8000.REG_ID",
			"WINC8000.NEW_REG_ID",
			"WINC8000.RECEIPT_NO",
			"WINC8000.RECEIPT_DATE",
			"WINC8000.TAX_AMOUNT",
			"WINC8000.PEN_AMOUNT",
			"WINC8000.ADD_AMOUNT",
			"WINC8000.REDUCE_AMOUNT",
			"WINC8000.CREDIT_AMOUNT",
			"WINC8000.OFFICE_RECEIVE_CODE",
			"WINC8000.TRN_DATE",
			"WINC8000.DEPOSIT_DATE",
			"WINC8000.SEND_DATE",
			"WINC8000.INCOME_CODE",
			"WINC8000.INCOME_TYPE",
			"WINC8000.CREATED_BY",
			"WINC8000.CREATED_DATE"
		));
		
		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO WS_INCFRI8000 WINC8000 ");
		sql.append(" USING DUAL ");
		sql.append(" ON ( ");
		sql.append("   WINC8000.DATE_TYPE = ? ");
		sql.append("   AND WINC8000.REG_ID = ? ");
		sql.append("   AND WINC8000.NEW_REG_ID = ? ");
		sql.append(" )");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (WS_INCFRI8000_SEQ.NEXTVAL" + org.apache.commons.lang3.StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");


		commonJdbcTemplate.batchUpdate(sql.toString(), incfri8000List, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsIncfri8000>() {
			public void setValues(PreparedStatement ps, WsIncfri8000 incfri8000) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				// Using Condition
				paramList.add(incfri8000.getDateType());
				paramList.add(incfri8000.getRegId());
				paramList.add(incfri8000.getNewRegId());
				// Update Statement
				paramList.add(incfri8000.getReceiptNo());
				paramList.add(incfri8000.getReceiptDate());
				paramList.add(incfri8000.getTaxAmount());
				paramList.add(incfri8000.getPenAmount());
				paramList.add(incfri8000.getAddAmount());
				paramList.add(incfri8000.getReduceAmount());
				paramList.add(incfri8000.getCreditAmount());
				paramList.add(incfri8000.getOfficeReceiveCode());
				paramList.add(incfri8000.getTrnDate());
				paramList.add(incfri8000.getDepositDate());
				paramList.add(incfri8000.getSendDate());
				paramList.add(incfri8000.getIncomeCode());
				paramList.add(incfri8000.getIncomeType());
				paramList.add(FLAG.N_FLAG);
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				// Insert Statement
				paramList.add(incfri8000.getDateType());
				paramList.add(incfri8000.getRegId());
				paramList.add(incfri8000.getNewRegId());
				paramList.add(incfri8000.getReceiptNo());
				paramList.add(incfri8000.getReceiptDate());
				paramList.add(incfri8000.getTaxAmount());
				paramList.add(incfri8000.getPenAmount());
				paramList.add(incfri8000.getAddAmount());
				paramList.add(incfri8000.getReduceAmount());
				paramList.add(incfri8000.getCreditAmount());
				paramList.add(incfri8000.getOfficeReceiveCode());
				paramList.add(incfri8000.getTrnDate());
				paramList.add(incfri8000.getDepositDate());
				paramList.add(incfri8000.getSendDate());
				paramList.add(incfri8000.getIncomeCode());
				paramList.add(incfri8000.getIncomeType());
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
}
