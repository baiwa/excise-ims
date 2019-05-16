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
import th.go.excise.ims.ws.persistence.entity.WsOasfri0100D2;

	public class WsOasfri0100D2RepositoryImpl implements WsOasfri0100D2RepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsOasfri0100D2RepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public void batchInsert(List<WsOasfri0100D2> oasfri0100D2List) {
		logger.info("batchInsert oasfri0100D2List.size()={}", oasfri0100D2List.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"OASFRI0100_D2_SEQ",
			"DATA_TYPE",
			"FORMDOC_REC0142_NO",
			"DATA_ID",
			"SEQ_NO",
			"ACCOUNT_NAME",
			"IN_QTY",
			"CREATED_BY",
			"CREATED_DATE"
		));
		
		String sql = SqlGeneratorUtils.genSqlInsert("WS_OASFRI0100_D2", insertColumnNames, "WS_OASFRI0100_D2_SEQ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), oasfri0100D2List, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsOasfri0100D2>() {
			public void setValues(PreparedStatement ps, WsOasfri0100D2 oasfri0100D2) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				paramList.add(oasfri0100D2.getDataType());
				paramList.add(oasfri0100D2.getFormdocRec0142No());
				paramList.add(oasfri0100D2.getDataId());
				paramList.add(oasfri0100D2.getSeqNo());
				paramList.add(oasfri0100D2.getAccountName());
				paramList.add(oasfri0100D2.getInQty());
				paramList.add(oasfri0100D2.getCreatedBy());
				paramList.add(oasfri0100D2.getCreatedDate());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
