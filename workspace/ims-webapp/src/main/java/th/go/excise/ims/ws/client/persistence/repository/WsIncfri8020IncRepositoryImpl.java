package th.go.excise.ims.ws.client.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ws.client.persistence.entity.WsIncfri8020Inc;

public class WsIncfri8020IncRepositoryImpl implements WsIncfri8020IncRepositoryCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<WsIncfri8020Inc> wsIncfri8020IncList) {
		String sql = SqlGeneratorUtils.genSqlInsert("WS_INCFRI8020_INC", Arrays.asList("IA_WS_INCFRI8020_INC_ID","DEPOSIT_DATE", "SEND_DATE", "RECEIPT_DATE", "INCOME_NAME", "RECEIPT_NO", "NET_TAX_AMOUNT", "NET_LOC_AMOUNT", "LOC_OTH_AMOUNT", "LOC_EXP_AMOUNT", "OLDER_FUND_AMOUNT", "TPBS_FUND_AMOUNT", "SEND_AMOUNT",
				"STAMP_AMOUNT", "CUSTOM_AMOUNT", "TRN_DATE", "OFFICE_RECEIVE", "INCOME_CODE", "RECEIPT_NO_OLDER_FUND", "RECEIPT_NO_TPBS_FUND", "RECEIPT_NO_SSS_FUND", "RECEIPT_NO_SPORT_FUND", "SPORT_FUND_AMOUNT", "PIN_NID_ID", "NEW_REG_ID", "CUS_NAME", "FAC_NAME", "CREATED_BY"),
				"WS_INCFRI8020_INC_SEQ");

		String username = UserLoginUtils.getCurrentUsername();

		commonJdbcTemplate.batchUpdate(sql, wsIncfri8020IncList, 1000, new ParameterizedPreparedStatementSetter<WsIncfri8020Inc>() {
			public void setValues(PreparedStatement ps, WsIncfri8020Inc wsIncfri8020Inc) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(wsIncfri8020Inc.getDepositDate());
				paramList.add(wsIncfri8020Inc.getSendDate());
				paramList.add(wsIncfri8020Inc.getReceiptDate());
				paramList.add(wsIncfri8020Inc.getIncomeName());
				paramList.add(wsIncfri8020Inc.getReceiptNo());
				paramList.add(wsIncfri8020Inc.getNetTaxAmount());
				paramList.add(wsIncfri8020Inc.getNetLocAmount());
				paramList.add(wsIncfri8020Inc.getLocOthAmount());
				paramList.add(wsIncfri8020Inc.getLocExpAmount());
				paramList.add(wsIncfri8020Inc.getOlderFundAmount());
				paramList.add(wsIncfri8020Inc.getTpbsFundAmount());
				paramList.add(wsIncfri8020Inc.getSendAmount());
				paramList.add(wsIncfri8020Inc.getStampAmount());
				paramList.add(wsIncfri8020Inc.getCustomAmount());
				paramList.add(wsIncfri8020Inc.getTrnDate());
				paramList.add(wsIncfri8020Inc.getOfficeReceive());
				paramList.add(wsIncfri8020Inc.getIncomeCode());
				paramList.add(wsIncfri8020Inc.getReceiptNoOlderFund());
				paramList.add(wsIncfri8020Inc.getReceiptNoTpbsFund());
				paramList.add(wsIncfri8020Inc.getReceiptNoSssFund());
				paramList.add(wsIncfri8020Inc.getReceiptNoSportFund());
				paramList.add(wsIncfri8020Inc.getSportFundAmount());
				paramList.add(wsIncfri8020Inc.getPinNidId());
				paramList.add(wsIncfri8020Inc.getNewRegId());
				paramList.add(wsIncfri8020Inc.getCusName());
				paramList.add(wsIncfri8020Inc.getFacName());
				paramList.add(username);
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
