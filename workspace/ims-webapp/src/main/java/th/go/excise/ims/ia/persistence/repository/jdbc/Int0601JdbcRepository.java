package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateTimeConverter;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD3;
import th.go.excise.ims.ia.vo.IaAuditIncD2Vo;
import th.go.excise.ims.ia.vo.Int0601RequestVo;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8020Inc;

@Repository
public class Int0601JdbcRepository {

	private static final Logger logger = LoggerFactory.getLogger(Int0601JdbcRepository.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<WsIncfri8020Inc> findByCriteria(Int0601RequestVo criteria , String strOrder) {
		logger.info("findTab1ByCriteria");

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
		if (StringUtils.isNotEmpty(criteria.getTaxCode())) {
			sql.append(" AND WS.INCOME_CODE = ? ");
			paramList.add(criteria.getTaxCode());
		}
		sql.append(" AND WS.RECEIPT_NO IS NOT NULL ");
		sql.append(" ORDER BY ").append(strOrder);

		return commonJdbcTemplate.query(sql.toString(), paramList.toArray(), tab1RowMapper);
	}

	private RowMapper<WsIncfri8020Inc> tab1RowMapper = new RowMapper<WsIncfri8020Inc>() {
		@Override
		public WsIncfri8020Inc mapRow(ResultSet rs, int rowNum) throws SQLException {
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
			vo.setIncCtlNo(rs.getString("INC_CTL_NO"));
			vo.setOfflineStatus(rs.getString("OFFLINE_STATUS"));
			vo.setIsDeleted(rs.getString("IS_DELETED"));
			vo.setVersion(rs.getInt("VERSION"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(LocalDateTimeConverter.convertToEntityAttribute(rs.getTimestamp("CREATED_DATE")));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(LocalDateTimeConverter.convertToEntityAttribute(rs.getTimestamp("UPDATED_DATE")));

			return vo;
		}
	};

	public List<IaAuditIncD2Vo> findDataTab2(Int0601RequestVo criteria) {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder(" SELECT WS.RECEIPT_DATE RECEIPT_DATE, SUM(WS.NET_TAX_AMT) NET_TAX_AMT, COUNT(1) PRINT_PER_DAY FROM WS_INCFRI8020_INC WS ");
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
		sql.append(" AND WS.RECEIPT_NO IS NOT NULL ");
		sql.append(" GROUP BY WS.RECEIPT_DATE ");
		return commonJdbcTemplate.query(sql.toString(), paramList.toArray(), tab2RowMapper);
	}
	
	private RowMapper<IaAuditIncD2Vo> tab2RowMapper = new RowMapper<IaAuditIncD2Vo>() {
		@Override
		public IaAuditIncD2Vo mapRow(ResultSet rs, int rowNum) throws SQLException {
			IaAuditIncD2Vo vo = new IaAuditIncD2Vo();
		
			vo.setReceiptDate(ConvertDateUtils.formatDateToString(rs.getDate("RECEIPT_DATE"), ConvertDateUtils.YYYY_MM_DD, ConvertDateUtils.LOCAL_EN));
			vo.setAmount(rs.getBigDecimal("NET_TAX_AMT"));
			vo.setPrintPerDay(rs.getBigDecimal("PRINT_PER_DAY"));
			return vo;
		}
	};
	
	public List<IaAuditIncD3> findDataTab3(Int0601RequestVo criteria) {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append(" WS.INCOME_CODE TAX_CODE, ");
		sql.append(" WS.INCOME_NAME TAX_NAME, ");
		sql.append(" SUM(WS.NET_TAX_AMT) AMOUNT, ");
		sql.append(" COUNT(1) COUNT_RECEIPT ");
		sql.append(" FROM ");
		sql.append(" WS_INCFRI8020_INC WS ");
		
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
		sql.append(" AND WS.RECEIPT_NO IS NOT NULL ");
		sql.append(" GROUP BY ");
		sql.append(" WS.INCOME_CODE, ");
		sql.append(" WS.INCOME_NAME ");
		sql.append(" ORDER BY ");
		sql.append(" WS.INCOME_CODE ");
		return commonJdbcTemplate.query(sql.toString(), paramList.toArray(), tab3RowMapper);
	}
	
	private RowMapper<IaAuditIncD3> tab3RowMapper = new RowMapper<IaAuditIncD3>() {
		@Override
		public IaAuditIncD3 mapRow(ResultSet rs, int rowNum) throws SQLException {
			IaAuditIncD3 vo = new IaAuditIncD3();
			vo.setTaxCode(rs.getString("TAX_CODE"));
			vo.setTaxName(rs.getString("TAX_NAME"));
			vo.setAmount(rs.getBigDecimal("AMOUNT"));
			vo.setCountReceipt(rs.getBigDecimal("COUNT_RECEIPT"));
			return vo;
		}
	};
	
//	public List<IaAuditIncD4Vo> findDataTab4(Int0601RequestVo criteria) {
//		List<Object> paramList = new ArrayList<>();
//		StringBuilder sql = new StringBuilder(" SELECT WS.RECEIPT_DATE RECEIPT_DATE, SUM(WS.NET_TAX_AMT) NET_TAX_AMT, COUNT(1) PRINT_PER_DAY FROM WS_INCFRI8020_INC WS ");
//		sql.append(" WHERE WS.IS_DELETED = '").append(FLAG.N_FLAG).append("'");
//
//		if (StringUtils.isNoneBlank(criteria.getOfficeReceive())) {
//			sql.append(" AND WS.OFFICE_RECEIVE like ? ");
//			paramList.add(ExciseUtils.whereInLocalOfficeCode(criteria.getOfficeReceive()));
//		}
//
//		if (StringUtils.isNotEmpty(criteria.getReceiptDateFrom())) {
//			sql.append(" AND WS.RECEIPT_DATE >= ? ");
//			paramList.add(ConvertDateUtils.parseStringToDate(criteria.getReceiptDateFrom(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
//		}
//
//		if (StringUtils.isNotEmpty(criteria.getReceiptDateTo())) {
//			sql.append(" AND WS.RECEIPT_DATE <= ? ");
//			paramList.add(ConvertDateUtils.parseStringToDate(criteria.getReceiptDateTo(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
//		}
//		sql.append(" GROUP BY WS.RECEIPT_DATE ");
//		return commonJdbcTemplate.query(sql.toString(), paramList.toArray(), tab2RowMapper);
//	}
//	
//	private RowMapper<IaAuditIncD2Vo> tab2RowMapper = new RowMapper<IaAuditIncD2Vo>() {
//		@Override
//		public IaAuditIncD2Vo mapRow(ResultSet rs, int rowNum) throws SQLException {
//			IaAuditIncD2Vo vo = new IaAuditIncD2Vo();
//			RECEIPT_NO
//			RECEIPT_DATE
//			INCOME_NAME
//			INCOME_CODE
//			NET_TAX_AMT
//
//			vo.setReceiptDate(ConvertDateUtils.formatDateToString(rs.getDate("RECEIPT_DATE"), ConvertDateUtils.DD_MM_YYYY));
//			vo.setAmount(rs.getString("NET_TAX_AMT"));
//			vo.setPrintPerDay(rs.getString("PRINT_PER_DAY"));
//			return vo;
//		}
//	};

}
