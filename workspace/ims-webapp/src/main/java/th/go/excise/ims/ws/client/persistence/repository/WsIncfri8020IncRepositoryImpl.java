package th.go.excise.ims.ws.client.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateTimeConverter;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ia.vo.Int0601Vo;
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
	
	@Override
	public List<WsIncfri8020Inc> findByCriteria(Int0601Vo criteria) {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder(" SELECT * FROM WS_INCFRI8020_INC WS");
		sql.append(" WHERE WS.IS_DELETED = '").append(FLAG.N_FLAG).append("'");
		if(StringUtils.isNoneBlank(criteria.getOfficeReceive())) {
			paramList.add(ExciseUtils.whereInLocalOfficeCode(criteria.getOfficeReceive()));
			sql.append(" AND WS.OFFICE_RECEIVE like ? ");
		}
		
		if(criteria.getReceiptDateFrom() != null) {
			paramList.add(criteria.getReceiptDateFrom());
			sql.append(" AND WS.RECEIPT_DATE >= ? ");
		}
		
		if(criteria.getReceiptDateTo() != null) {
			paramList.add(criteria.getReceiptDateTo());
			sql.append(" AND WS.RECEIPT_DATE <= ? ");
		}
		sql.append(" ORDER BY RECEIPT_NO ");
		return commonJdbcTemplate.query(sql.toString(),paramList.toArray(),  mapping);
	}

	
	private RowMapper<WsIncfri8020Inc> mapping = new RowMapper<WsIncfri8020Inc>() {
		@Override
		public WsIncfri8020Inc mapRow(ResultSet rs, int arg1) throws SQLException {
			WsIncfri8020Inc vo = new WsIncfri8020Inc();
			vo.setIaWsIncfri8020IncId(rs.getLong("IA_WS_INCFRI8020_INC_ID"));
			vo.setDepositDate(rs.getDate("DEPOSIT_DATE"));
			vo.setSendDate(rs.getDate("SEND_DATE"));
			vo.setReceiptDate(rs.getDate("RECEIPT_DATE"));
			vo.setIncomeName(rs.getString("INCOME_NAME"));
			vo.setReceiptNo(rs.getString("RECEIPT_NO"));
			vo.setNetTaxAmount(rs.getBigDecimal("NET_TAX_AMOUNT"));
			vo.setNetLocAmount(rs.getBigDecimal("NET_LOC_AMOUNT"));
			vo.setLocOthAmount(rs.getBigDecimal("LOC_OTH_AMOUNT"));
			vo.setLocExpAmount(rs.getBigDecimal("LOC_EXP_AMOUNT"));
			vo.setOlderFundAmount(rs.getBigDecimal("OLDER_FUND_AMOUNT"));
			vo.setTpbsFundAmount(rs.getBigDecimal("TPBS_FUND_AMOUNT"));
			vo.setSendAmount(rs.getBigDecimal("SEND_AMOUNT"));
			vo.setStampAmount(rs.getBigDecimal("STAMP_AMOUNT"));
			vo.setCustomAmount(rs.getBigDecimal("CUSTOM_AMOUNT"));
			vo.setTrnDate(rs.getDate("TRN_DATE"));
			vo.setOfficeReceive(rs.getString("OFFICE_RECEIVE"));
			vo.setIncomeCode(rs.getString("INCOME_CODE"));
			vo.setReceiptNoOlderFund(rs.getString("RECEIPT_NO_OLDER_FUND"));
			vo.setReceiptNoTpbsFund(rs.getString("RECEIPT_NO_TPBS_FUND"));
			vo.setReceiptNoSssFund(rs.getString("RECEIPT_NO_SSS_FUND"));
			vo.setReceiptNoSportFund(rs.getString("RECEIPT_NO_SPORT_FUND"));
			vo.setSportFundAmount(rs.getBigDecimal("SPORT_FUND_AMOUNT"));
			vo.setPinNidId(rs.getString("PIN_NID_ID"));
			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			vo.setCusName(rs.getString("CUS_NAME"));
			vo.setFacName(rs.getString("FAC_NAME"));
			vo.setIsDeleted(rs.getString("IS_DELETED"));
			vo.setVersion(rs.getInt("VERSION"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(LocalDateTimeConverter.convertToEntityAttribute(rs.getTimestamp("CREATED_DATE")));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(LocalDateTimeConverter.convertToEntityAttribute(rs.getTimestamp("UPDATED_DATE")));

			
			return vo;
		}
	};
}
