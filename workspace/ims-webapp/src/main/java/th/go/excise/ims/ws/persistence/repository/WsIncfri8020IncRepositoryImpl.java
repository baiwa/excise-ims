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

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8020Inc;

public class WsIncfri8020IncRepositoryImpl implements WsIncfri8020IncRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsIncfri8020IncRepositoryImpl.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<WsIncfri8020Inc> incfri8020IncList) {
		logger.info("batchInsert incfri8020IncList.size()={}", incfri8020IncList.size());
		
		final int BATCH_SIZE = 1000;
		
		String sql = SqlGeneratorUtils.genSqlInsert(
			"WS_INCFRI8020_INC",
			Arrays.asList(
				"WS_INCFRI8020_INC_ID", "RECEIPT_DATE", "DEPOSIT_DATE", "SEND_DATE", "INCOME_NAME",
				"RECEIPT_NO", "NET_TAX_AMT", "NET_LOC_AMT", "LOC_OTH_AMT", "LOC_EXP_AMT",
				"SSS_FUND_AMT", "TPBS_FUND_AMT", "SPORT_FUND_AMT", "OLDER_FUND_AMT", "SEND_AMT",
				"STAMP_AMT", "CUSTOM_AMT", "TRN_DATE", "OFFICE_RECEIVE", "INCOME_CODE",
				"RECEIPT_NO_SSS_FUND", "RECEIPT_NO_TPBS_FUND", "RECEIPT_NO_SPORT_FUND", "RECEIPT_NO_OLDER_FUND", "PIN_NID_ID",
				"NEW_REG_ID", "CUS_NAME", "FAC_NAME","INC_CTL_NO", "CREATED_BY"
			),
			"WS_INCFRI8020_INC_SEQ");
		

		commonJdbcTemplate.batchUpdate(sql, incfri8020IncList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsIncfri8020Inc>() {
			public void setValues(PreparedStatement ps, WsIncfri8020Inc incfri8020Inc) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(incfri8020Inc.getReceiptDate());
				paramList.add(incfri8020Inc.getDepositDate());
				paramList.add(incfri8020Inc.getSendDate());
				paramList.add(incfri8020Inc.getIncomeName());
				paramList.add(incfri8020Inc.getReceiptNo());
				paramList.add(incfri8020Inc.getNetTaxAmt());
				paramList.add(incfri8020Inc.getNetLocAmt());
				paramList.add(incfri8020Inc.getLocOthAmt());
				paramList.add(incfri8020Inc.getLocExpAmt());
				paramList.add(incfri8020Inc.getSssFundAmt());
				paramList.add(incfri8020Inc.getTpbsFundAmt());
				paramList.add(incfri8020Inc.getSportFundAmt());
				paramList.add(incfri8020Inc.getOlderFundAmt());
				paramList.add(incfri8020Inc.getSendAmt());
				paramList.add(incfri8020Inc.getStampAmt());
				paramList.add(incfri8020Inc.getCustomAmt());
				paramList.add(incfri8020Inc.getTrnDate());
				paramList.add(incfri8020Inc.getOfficeReceive());
				paramList.add(incfri8020Inc.getIncomeCode());
				paramList.add(incfri8020Inc.getReceiptNoSssFund());
				paramList.add(incfri8020Inc.getReceiptNoTpbsFund());
				paramList.add(incfri8020Inc.getReceiptNoSportFund());
				paramList.add(incfri8020Inc.getReceiptNoOlderFund());
				paramList.add(incfri8020Inc.getPinNidId());
				paramList.add(incfri8020Inc.getNewRegId());
				paramList.add(incfri8020Inc.getCusName());
				paramList.add(incfri8020Inc.getFacName());
				paramList.add(incfri8020Inc.getIncCtlNo());
				paramList.add(SYSTEM_USER.BATCH);
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
}
