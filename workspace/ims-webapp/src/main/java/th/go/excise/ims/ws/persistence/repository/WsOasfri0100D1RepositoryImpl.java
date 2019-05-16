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
import th.go.excise.ims.ws.persistence.entity.WsOasfri0100D1;

public class WsOasfri0100D1RepositoryImpl implements WsOasfri0100D1RepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsOasfri0100D1RepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public void batchInsert(List<WsOasfri0100D1> oasfri0100D1List) {
		logger.info("batchInsert oasfri0100D1List.size()={}", oasfri0100D1List.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"OASFRI0100_D1_SEQ",
			"DATA_TYPE",
			"FORMDOC_REC0142_NO",
			"DATA_SEQ",
			"DATA_ID",
			"DATA_NAME",
			"BAL_BF_QTY",
			"CREATED_BY",
			"CREATED_DATE"
		));
		
		String sql = SqlGeneratorUtils.genSqlInsert("WS_OASFRI0100_D1", insertColumnNames, "WS_OASFRI0100_D1_SEQ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), oasfri0100D1List, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsOasfri0100D1>() {
			public void setValues(PreparedStatement ps, WsOasfri0100D1 oasfri0100D1) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				paramList.add(oasfri0100D1.getDataType());
				paramList.add(oasfri0100D1.getFormdocRec0142No());
				paramList.add(oasfri0100D1.getDataSeq());
				paramList.add(oasfri0100D1.getDataId());
				paramList.add(oasfri0100D1.getDataName());
				paramList.add(oasfri0100D1.getBalBfQty());
				paramList.add(oasfri0100D1.getCreatedBy());
				paramList.add(oasfri0100D1.getCreatedDate());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
