package th.go.excise.ims.ta.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.SYSTEM_USER;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.go.excise.ims.ta.persistence.entity.TaWsInc8000M;

public class TaWsInc8000MRepositoryImpl implements TaWsInc8000MRepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(TaWsInc8000MRepositoryImpl.class);
	
	private int batchSize;
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Autowired
	public TaWsInc8000MRepositoryImpl(
			@Value("${ws.excise.inc.incfri8000M.batch-size}") int batchSize,
			CommonJdbcTemplate commonJdbcTemplate) {
		this.batchSize = batchSize;
		this.commonJdbcTemplate = commonJdbcTemplate;
	}

	@Override
	public void batchInsert(List<TaWsInc8000M> taWsInc8000MList) {
		logger.info("batchInsert taWsInc8000MList.size()={}", taWsInc8000MList.size());
		
		List<String> insertColumnNames = new ArrayList<>();
		insertColumnNames.add("WS_INC8000_M_ID");
		insertColumnNames.add("REG_ID");
		insertColumnNames.add("NEW_REG_ID");
		insertColumnNames.add("TAX_YEAR");
		insertColumnNames.add("TAX_MONTH");
		insertColumnNames.add("TAX_AMOUNT");
		insertColumnNames.add("DUTY_CODE");
		insertColumnNames.add("NET_TAX_AMOUNT");
		insertColumnNames.add("TAX_ADD_AMT");
		insertColumnNames.add("TAX_PEN_AMT");
		insertColumnNames.add("TAX_REDUCE_AMT");
		insertColumnNames.add("TAX_CREDIT_ED_AMT");
		insertColumnNames.add("TAX_YEAR_T");
		insertColumnNames.add("TAX_MONTH_T");
		insertColumnNames.add("TAX_EXCEPT_AMT");
		insertColumnNames.add("CUS_ID");
		insertColumnNames.add("FAC_ID");
		insertColumnNames.add("IS_DELETED");
		insertColumnNames.add("CREATED_BY");
		insertColumnNames.add("CREATED_DATE");
		
		String sql = SqlGeneratorUtils.genSqlInsert("TA_WS_INC8000_M", insertColumnNames, "TA_WS_INC8000_M_SEQ");
		
		commonJdbcTemplate.batchUpdate(sql, taWsInc8000MList, batchSize, new ParameterizedPreparedStatementSetter<TaWsInc8000M>() {
			public void setValues(PreparedStatement ps, TaWsInc8000M taWsInc8000M) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				// Insert Statement
				paramList.add(taWsInc8000M.getRegId());
				paramList.add(taWsInc8000M.getNewRegId());
				paramList.add(taWsInc8000M.getTaxYear());
				paramList.add(taWsInc8000M.getTaxMonth());
				paramList.add(taWsInc8000M.getTaxAmount());
				paramList.add(taWsInc8000M.getDutyCode());
				paramList.add(taWsInc8000M.getNetTaxAmount());
				paramList.add(taWsInc8000M.getTaxAddAmt());
				paramList.add(taWsInc8000M.getTaxPenAmt());
				paramList.add(taWsInc8000M.getTaxReduceAmt());
				paramList.add(taWsInc8000M.getTaxCreditEdAmt());
				paramList.add(taWsInc8000M.getTaxYearT());
				paramList.add(taWsInc8000M.getTaxMonthT());
				paramList.add(taWsInc8000M.getTaxExceptAmt());
				paramList.add(taWsInc8000M.getCusId());
				paramList.add(taWsInc8000M.getFacId());
				paramList.add(FLAG.N_FLAG);
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
