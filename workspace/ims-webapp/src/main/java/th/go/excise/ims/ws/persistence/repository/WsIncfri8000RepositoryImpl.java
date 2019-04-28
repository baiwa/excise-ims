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
import th.go.excise.ims.ws.persistence.entity.WsIncfri8000;

public class WsIncfri8000RepositoryImpl implements WsIncfri8000RepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsIncfri8000RepositoryImpl.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<WsIncfri8000> incfri8000List) {
		logger.info("batchInsert incfri8000List.size()={}", incfri8000List.size());
		
		final int BATCH_SIZE = 1000;
		
		String sql = SqlGeneratorUtils.genSqlInsert(
			"WS_INCFRI8000",
			Arrays.asList(
				"INCFRI8000_ID", "REG_ID", "NEW_REG_ID", "RECEIPT_NO", "RECEIPT_DATE",
				"TAX_AMOUNT", "PEN_AMOUNT", "ADD_AMOUNT", "REDUCE_AMOUNT", "CREDIT_AMOUNT",
				"OFFICE_RECEIVE_CODE", "TRN_DATE", "DEPOSIT_DATE", "SEND_DATE", "INCOME_CODE",
				"INCOME_TYPE", "CREATED_BY", "CREATED_DATE"
			),
			"WS_INCFRI8000_SEQ"
		);

		commonJdbcTemplate.batchUpdate(sql, incfri8000List, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsIncfri8000>() {
			public void setValues(PreparedStatement ps, WsIncfri8000 incfri8000) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
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
