package th.go.excise.ims.ws.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.SYSTEM_USER;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE;
import th.go.excise.ims.ta.persistence.entity.TaWsInc8000M;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8000;

public class WsIncfri8000RepositoryImpl implements WsIncfri8000RepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsIncfri8000RepositoryImpl.class);
	
	private int batchSize;
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Autowired
	public WsIncfri8000RepositoryImpl(
			@Value("${ws.excise.inc.incfri8000.batch-size}") int batchSize,
			CommonJdbcTemplate commonJdbcTemplate) {
		this.batchSize = batchSize;
		this.commonJdbcTemplate = commonJdbcTemplate;
	}
	
	@Override
	public void forceDeleteByDateType(String dateType, String dateStart, String dateEnd) {
		logger.info("forceDeleteByDateType dateType={}, dateStart={}, dateEnd={}", dateType, dateStart, dateEnd);
		
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE WS_INCFRI8000 ");
		sql.append(" WHERE DATE_TYPE = ? ");
		sql.append("   AND ( ");
		sql.append("     TRUNC(" + getColumnTypeByDateType(dateType) + ") >= TO_DATE(?,'YYYYMMDD') ");
		sql.append("     AND TRUNC(" + getColumnTypeByDateType(dateType) + ") <= TO_DATE(?,'YYYYMMDD') ");
		sql.append("   ) ");
		
		int rowsAffected = commonJdbcTemplate.update(sql.toString(), new Object[] {
			dateType,
			dateStart,
			dateEnd
		});
		
		logger.debug("rowsAffected={}", rowsAffected);
	}
	
	@Override
	public void batchInsert(List<WsIncfri8000> incfri8000List) {
		logger.info("batchInsert incfri8000List.size()={}", incfri8000List.size());
		
		List<String> insertColumnNames = new ArrayList<>();
		insertColumnNames.add("INCFRI8000_ID");
		insertColumnNames.add("DATE_TYPE");
		insertColumnNames.add("REG_ID");
		insertColumnNames.add("NEW_REG_ID");
		insertColumnNames.add("RECEIPT_NO");
		insertColumnNames.add("RECEIPT_DATE");
		insertColumnNames.add("TAX_AMOUNT");
		insertColumnNames.add("PEN_AMOUNT");
		insertColumnNames.add("ADD_AMOUNT");
		insertColumnNames.add("REDUCE_AMOUNT");
		insertColumnNames.add("CREDIT_AMOUNT");
		insertColumnNames.add("OFFICE_RECEIVE_CODE");
		insertColumnNames.add("TRN_DATE");
		insertColumnNames.add("DEPOSIT_DATE");
		insertColumnNames.add("SEND_DATE");
		insertColumnNames.add("INCOME_CODE");
		insertColumnNames.add("INCOME_TYPE");
		insertColumnNames.add("INC_CTL_NO");
		insertColumnNames.add("OFFLINE_STATUS");
		insertColumnNames.add("GROUP_ID");
		insertColumnNames.add("GROUP_NAME");
		insertColumnNames.add("IS_DELETED");
		insertColumnNames.add("CREATED_BY");
		insertColumnNames.add("CREATED_DATE");
		
		String sql = SqlGeneratorUtils.genSqlInsert("WS_INCFRI8000", insertColumnNames, "WS_INCFRI8000_SEQ");
		
		commonJdbcTemplate.batchUpdate(sql, incfri8000List, batchSize, new ParameterizedPreparedStatementSetter<WsIncfri8000>() {
			public void setValues(PreparedStatement ps, WsIncfri8000 incfri8000) throws SQLException {
				List<Object> paramList = new ArrayList<>();
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
				paramList.add(incfri8000.getIncCtlNo());
				paramList.add(incfri8000.getOfflineStatus());
				paramList.add(incfri8000.getGroupId());
				paramList.add(incfri8000.getGroupName());
				paramList.add(FLAG.N_FLAG);
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

	@Override
	public List<TaWsInc8000M> findFor8000M(String dateType, String dateStart, String dateEnd) {
		logger.info("findFor8000M dateType={}, dateStart={}, dateEnd={}", dateType, dateStart, dateEnd);
		
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT X.NEW_REG_ID ");
		sql.append("   ,X.REG_ID ");
		sql.append("   ,X.TAX_YEAR ");
		sql.append("   ,X.TAX_MONTH ");
		sql.append("   ,SUM(X.TAX_AMOUNT) AS TAX_AMOUNT ");
		sql.append("   ,X.DUTY_CODE ");
		sql.append("   ,SUM(X.NET_TAX_AMOUNT) AS NET_TAX_AMOUNT ");
		sql.append("   ,SUM(X.TAX_ADD_AMT) AS TAX_ADD_AMT ");
		sql.append("   ,SUM(X.TAX_PEN_AMT) AS TAX_PEN_AMT ");
		sql.append("   ,SUM(X.TAX_REDUCE_AMT) AS TAX_REDUCE_AMT ");
		sql.append("   ,SUM(X.TAX_CREDIT_ED_AMT) AS TAX_CREDIT_ED_AMT ");
		sql.append("   ,X.TAX_YEAR_T ");
		sql.append("   ,X.TAX_MONTH_T ");
		sql.append("   ,SUM(X.TAX_EXCEPT_AMT) AS TAX_EXCEPT_AMT ");
		sql.append(" FROM ( ");
		sql.append("   SELECT NEW_REG_ID ");
		sql.append("   ,REG_ID ");
		sql.append("   ,TO_CHAR(TRN_DATE, 'YYYY') AS TAX_YEAR ");
		sql.append("   ,TO_CHAR(TRN_DATE, 'MM') AS TAX_MONTH ");
		sql.append("   ,TAX_AMOUNT AS TAX_AMOUNT ");
		sql.append("   ,GROUP_ID AS DUTY_CODE ");
		sql.append("   ,NULL AS NET_TAX_AMOUNT ");
		sql.append("   ,ADD_AMOUNT AS TAX_ADD_AMT ");
		sql.append("   ,PEN_AMOUNT AS TAX_PEN_AMT ");
		sql.append("   ,REDUCE_AMOUNT AS TAX_REDUCE_AMT ");
		sql.append("   ,CREDIT_AMOUNT AS TAX_CREDIT_ED_AMT ");
		sql.append("   ,TO_CHAR(SEND_DATE, 'YYYY') AS TAX_YEAR_T ");
		sql.append("   ,TO_CHAR(SEND_DATE, 'MM') AS TAX_MONTH_T ");
		sql.append("   ,NULL AS TAX_EXCEPT_AMT ");
		sql.append("   FROM WS_INCFRI8000 ");
		sql.append("   WHERE IS_DELETED = 'N' ");
		sql.append("     AND DATE_TYPE = ? ");
		sql.append("     AND ( ");
		sql.append("       TRUNC(" + getColumnTypeByDateType(dateType) + ") >= TO_DATE(?,'YYYYMMDD') ");
		sql.append("       AND TRUNC(" + getColumnTypeByDateType(dateType) + ") <= TO_DATE(?,'YYYYMMDD') ");
		sql.append("     ) ");
		sql.append("     AND NEW_REG_ID IS NOT NULL ");
		sql.append("     AND GROUP_ID IS NOT NULL ");
		sql.append(" ) X ");
		sql.append(" GROUP BY X.NEW_REG_ID, X.REG_ID, X.TAX_YEAR, X.TAX_MONTH, X.DUTY_CODE, X.TAX_YEAR_T, X.TAX_MONTH_T ");
		
		List<TaWsInc8000M> taWsInc8000MList = commonJdbcTemplate.query(
			sql.toString(),
			new Object[] {
				dateType,
				dateStart,
				dateEnd
			},
			new RowMapper<TaWsInc8000M>() {
				@Override
				public TaWsInc8000M mapRow(ResultSet rs, int rowNum) throws SQLException {
					TaWsInc8000M taWsInc8000M = new TaWsInc8000M();
					taWsInc8000M.setRegId(rs.getString("REG_ID"));
					taWsInc8000M.setNewRegId(rs.getString("NEW_REG_ID"));
					taWsInc8000M.setTaxYear(rs.getString("TAX_YEAR"));
					taWsInc8000M.setTaxMonth(rs.getString("TAX_MONTH"));
					taWsInc8000M.setTaxAmount(rs.getBigDecimal("TAX_AMOUNT"));
					taWsInc8000M.setDutyCode(rs.getString("DUTY_CODE"));
					taWsInc8000M.setNetTaxAmount(rs.getBigDecimal("NET_TAX_AMOUNT"));
					taWsInc8000M.setTaxAddAmt(rs.getBigDecimal("TAX_ADD_AMT"));
					taWsInc8000M.setTaxPenAmt(rs.getBigDecimal("TAX_PEN_AMT"));
					taWsInc8000M.setTaxReduceAmt(rs.getBigDecimal("TAX_REDUCE_AMT"));
					taWsInc8000M.setTaxCreditEdAmt(rs.getBigDecimal("TAX_CREDIT_ED_AMT"));
					taWsInc8000M.setTaxYearT(rs.getString("TAX_YEAR_T"));
					taWsInc8000M.setTaxMonthT(rs.getString("TAX_MONTH_T"));
					taWsInc8000M.setTaxExceptAmt(rs.getBigDecimal("TAX_EXCEPT_AMT"));
					//taWsInc8000M.setCusId(rs.getString("CUS_ID"));
					//taWsInc8000M.setFacId(rs.getString("FAC_ID"));
					return taWsInc8000M;
				}
			}
		);
		
		return taWsInc8000MList;
	}
	
	private String getColumnTypeByDateType(String dateType) {
		if (WEB_SERVICE.INCFRI8000.DATE_TYPE_INCOME_CODE.equals(dateType)) {
			return "TRN_DATE";
		} else {
			return "RECEIPT_DATE";
		}
	}
}
