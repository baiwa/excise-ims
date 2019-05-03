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
import th.go.excise.ims.ws.persistence.entity.WsIncfri8000Credit;

public class WsIncfri8000CreditRepositoryImpl implements WsIncfri8000CreditRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsIncfri8000CreditRepositoryImpl.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchUpdate(List<WsIncfri8000Credit> incfri8000CreditList) {
		logger.info("batchUpdate incfri8000CreditList.size()={}", incfri8000CreditList.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> updateColumnNames = new ArrayList<>(Arrays.asList(
			"WINC8000C.APPROVE_NO = ?",
			"WINC8000C.APPROVE_DATE = ?",
			"WINC8000C.IS_DELETED = ?",
			"WINC8000C.UPDATED_BY = ?",
			"WINC8000C.UPDATED_DATE = ?"
		));
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"WINC8000C.INCFRI8000_ID",
			"WINC8000C.DATE_TYPE",
			"WINC8000C.REG_ID",
			"WINC8000C.NEW_REG_ID",
			"WINC8000.APPROVE_NO",
			"WINC8000.APPROVE_DATE",
			"WINC8000C.CREATED_BY",
			"WINC8000C.CREATED_DATE"
		));
		
		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO WS_INCFRI8000_CREDIT WINC8000C ");
		sql.append(" USING DUAL ");
		sql.append(" ON ( ");
		sql.append("   WINC8000C.DATE_TYPE = ? ");
		sql.append("   AND WINC8000C.REG_ID = ? ");
		sql.append("   AND WINC8000C.NEW_REG_ID = ? ");
		sql.append(" )");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (WS_INCFRI8000_CREDIT_SEQ.NEXTVAL" + org.apache.commons.lang3.StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), incfri8000CreditList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsIncfri8000Credit>() {
			public void setValues(PreparedStatement ps, WsIncfri8000Credit incfri8000Credit) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				// Using Condition
				paramList.add(incfri8000Credit.getDateType());
				paramList.add(incfri8000Credit.getRegId());
				paramList.add(incfri8000Credit.getNewRegId());
				// Update Statement
				paramList.add(incfri8000Credit.getApproveNo());
				paramList.add(incfri8000Credit.getApproveDate());
				paramList.add(FLAG.N_FLAG);
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				// Insert Statement
				paramList.add(incfri8000Credit.getDateType());
				paramList.add(incfri8000Credit.getRegId());
				paramList.add(incfri8000Credit.getNewRegId());
				paramList.add(incfri8000Credit.getApproveNo());
				paramList.add(incfri8000Credit.getApproveDate());
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
