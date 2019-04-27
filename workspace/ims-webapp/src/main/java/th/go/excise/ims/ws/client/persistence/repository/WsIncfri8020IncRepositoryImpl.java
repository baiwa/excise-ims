package th.go.excise.ims.ws.client.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import com.ctc.wstx.util.StringUtil;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
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
		String sql = SqlGeneratorUtils.genSqlInsert("WS_INCFRI8020_INC", Arrays.asList("WS_INCFRI8020_INC_ID", "RECEIPT_DATE", "DEPOSIT_DATE", "SEND_DATE", "INCOME_NAME", "RECEIPT_NO", "NET_TAX_AMT", "NET_LOC_AMT", "LOC_OTH_AMT", "LOC_EXP_AMT", "SSS_FUND_AMT", "TPBS_FUND_AMT", "SPORT_FUND_AMT",
				"OLDER_FUND_AMT", "SEND_AMT", "STAMP_AMT", "CUSTOM_AMT", "TRN_DATE", "OFFICE_RECEIVE", "INCOME_CODE", "RECEIPT_NO_SSS_FUND", "RECEIPT_NO_TPBS_FUND", "RECEIPT_NO_SPORT_FUND", "RECEIPT_NO_OLDER_FUND", "PIN_NID_ID", "NEW_REG_ID", "CUS_NAME", "FAC_NAME", "CREATED_BY"), "WS_INCFRI8020_INC_SEQ");

		String username = UserLoginUtils.getCurrentUsername();

		commonJdbcTemplate.batchUpdate(sql, wsIncfri8020IncList, 1000, new ParameterizedPreparedStatementSetter<WsIncfri8020Inc>() {
			public void setValues(PreparedStatement ps, WsIncfri8020Inc wsIncfri8020Inc) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(wsIncfri8020Inc.getReceiptDate());
				paramList.add(wsIncfri8020Inc.getDepositDate());
				paramList.add(wsIncfri8020Inc.getSendDate());
				paramList.add(wsIncfri8020Inc.getIncomeName());
				paramList.add(wsIncfri8020Inc.getReceiptNo());
				paramList.add(wsIncfri8020Inc.getNetTaxAmt());
				paramList.add(wsIncfri8020Inc.getNetLocAmt());
				paramList.add(wsIncfri8020Inc.getLocOthAmt());
				paramList.add(wsIncfri8020Inc.getLocExpAmt());
				paramList.add(wsIncfri8020Inc.getSssFundAmt());
				paramList.add(wsIncfri8020Inc.getTpbsFundAmt());
				paramList.add(wsIncfri8020Inc.getSportFundAmt());
				paramList.add(wsIncfri8020Inc.getOlderFundAmt());
				paramList.add(wsIncfri8020Inc.getSendAmt());
				paramList.add(wsIncfri8020Inc.getStampAmt());
				paramList.add(wsIncfri8020Inc.getCustomAmt());
				paramList.add(wsIncfri8020Inc.getTrnDate());
				paramList.add(wsIncfri8020Inc.getOfficeReceive());
				paramList.add(wsIncfri8020Inc.getIncomeCode());
				paramList.add(wsIncfri8020Inc.getReceiptNoSssFund());
				paramList.add(wsIncfri8020Inc.getReceiptNoTpbsFund());
				paramList.add(wsIncfri8020Inc.getReceiptNoSportFund());
				paramList.add(wsIncfri8020Inc.getReceiptNoOlderFund());
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
		if (StringUtils.isNoneBlank(criteria.getOfficeReceive())) {
			sql.append(" AND WS.OFFICE_RECEIVE like ? ");
			paramList.add(ExciseUtils.whereInLocalOfficeCode(criteria.getOfficeReceive()));
		}
		
		if (StringUtils.isNotEmpty(criteria.getReceiptDateFrom())) {
			sql.append(" AND WS.RECEIPT_DATE >= ? ");
			paramList.add(ConvertDateUtils.parseStringToDate(criteria.getReceiptDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		}

		if (StringUtils.isNotEmpty(criteria.getReceiptDateTo())) {
			sql.append(" AND WS.RECEIPT_DATE <= ? ");
			paramList.add(ConvertDateUtils.parseStringToDate(criteria.getReceiptDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		}
		sql.append(" ORDER BY RECEIPT_NO ");
		return commonJdbcTemplate.query(sql.toString(), paramList.toArray(), mapping);
	}

	private RowMapper<WsIncfri8020Inc> mapping = new RowMapper<WsIncfri8020Inc>() {
		@Override
		public WsIncfri8020Inc mapRow(ResultSet rs, int arg1) throws SQLException {
			WsIncfri8020Inc vo = new WsIncfri8020Inc();
			vo.setWsIncfri8020IncId(rs.getLong("WS_INCFRI8020_INC_ID"));
			vo.setReceiptDate(rs.getDate("RECEIPT_DATE"));
			vo.setDepositDate(rs.getDate("DEPOSIT_DATE"));
			vo.setSendDate(rs.getDate("SEND_DATE"));
			vo.setIncomeName(rs.getString("INCOME_NAME"));
			vo.setReceiptNo(rs.getString("RECEIPT_NO"));
			vo.setNetTaxAmt(rs.getBigDecimal("NET_TAX_AMT"));
			vo.setNetLocAmt(rs.getBigDecimal("NET_LOC_AMT"));
			vo.setLocOthAmt(rs.getBigDecimal("LOC_OTH_AMT"));
			vo.setLocExpAmt(rs.getBigDecimal("LOC_EXP_AMT"));
			vo.setSssFundAmt(rs.getBigDecimal("SSS_FUND_AMT"));
			vo.setTpbsFundAmt(rs.getBigDecimal("TPBS_FUND_AMT"));
			vo.setSportFundAmt(rs.getBigDecimal("SPORT_FUND_AMT"));
			vo.setOlderFundAmt(rs.getBigDecimal("OLDER_FUND_AMT"));
			vo.setSendAmt(rs.getBigDecimal("SEND_AMT"));
			vo.setStampAmt(rs.getBigDecimal("STAMP_AMT"));
			vo.setCustomAmt(rs.getBigDecimal("CUSTOM_AMT"));
			vo.setTrnDate(rs.getDate("TRN_DATE"));
			vo.setOfficeReceive(rs.getString("OFFICE_RECEIVE"));
			vo.setIncomeCode(rs.getString("INCOME_CODE"));
			vo.setReceiptNoSssFund(rs.getString("RECEIPT_NO_SSS_FUND"));
			vo.setReceiptNoTpbsFund(rs.getString("RECEIPT_NO_TPBS_FUND"));
			vo.setReceiptNoSportFund(rs.getString("RECEIPT_NO_SPORT_FUND"));
			vo.setReceiptNoOlderFund(rs.getString("RECEIPT_NO_OLDER_FUND"));
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
