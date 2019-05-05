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
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants.SYSTEM_USER;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8000M;

public class WsIncfri8000MRepositoryImpl implements WsIncfri8000MRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsIncfri8000MRepositoryImpl.class);
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public void forceDeleteByDateType(String dateType, String taxYear, String taxMonth) {
		logger.info("forceDeleteByDateType dateType={}, taxYear={}, taxMonth={}", dateType, taxYear, taxMonth);
		
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE WS_INCFRI8000_M ");
		sql.append(" WHERE DATE_TYPE = ? ");
		sql.append("   AND TAX_YEAR = ? ");
		sql.append("   AND TAX_MONTH = ? ");
		
		int rowsAffected = commonJdbcTemplate.update(sql.toString(), new Object[] {
			dateType,
			taxYear,
			taxMonth
		});
		
		logger.debug("rowsAffected={}", rowsAffected);
	}
	
	@Override
	public void batchInsert(List<WsIncfri8000M> incfri8000MList) {
		logger.info("batchInsert incfri8000MList.size()={}", incfri8000MList.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"INCFRI8000_M_ID",
			"DATE_TYPE",
			"REG_ID",
			"NEW_REG_ID",
			"TAX_YEAR",
			"TAX_MONTH",
			"TAX_AMOUNT",
			"IS_DELETED",
			"CREATED_BY",
			"CREATED_DATE"
		));
		
		String sql = SqlGeneratorUtils.genSqlInsert("WS_INCFRI8000_M", insertColumnNames, "WS_INCFRI8000_M_SEQ");
		
		commonJdbcTemplate.batchUpdate(sql, incfri8000MList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsIncfri8000M>() {
			public void setValues(PreparedStatement ps, WsIncfri8000M incfri8000M) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				paramList.add(incfri8000M.getDateType());
				paramList.add(incfri8000M.getRegId());
				paramList.add(incfri8000M.getNewRegId());
				paramList.add(incfri8000M.getTaxYear());
				paramList.add(incfri8000M.getTaxMonth());
				paramList.add(incfri8000M.getTaxAmount());
				paramList.add(FLAG.N_FLAG);
				paramList.add(SYSTEM_USER.BATCH);
				paramList.add(LocalDateTime.now());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
}
