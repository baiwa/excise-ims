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
import th.go.excise.ims.ws.persistence.entity.WsOasfri0100D;

public class WsOasfri0100DRepositoryImpl implements WsOasfri0100DRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsOasfri0100DRepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Override
	public void batchInsert(List<WsOasfri0100D> oasfri0100DList) {
		logger.info("batchInsert oasfri0100DList.size()={}", oasfri0100DList.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"OASFRI0100_D_SEQ",
			"DATA_TYPE",
			"FORMDOC_REC0142_NO",
			"DATA_SEQ",
			"DATA_ID",
			"DATA_NAME",
			"BAL_BF_QTY",
			"SEQ_NO",
			"ACCOUNT_NAME",
			"IN_QTY",
			"CREATED_BY",
			"CREATED_DATE"
		));
		
		String sql = SqlGeneratorUtils.genSqlInsert("WS_OASFRI0100_D", insertColumnNames, "WS_OASFRI0100_D_SEQ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), oasfri0100DList, BATCH_SIZE, new ParameterizedPreparedStatementSetter<WsOasfri0100D>() {
			public void setValues(PreparedStatement ps, WsOasfri0100D oasfri0100D) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				paramList.add(oasfri0100D.getDataType());
				paramList.add(oasfri0100D.getFormdocRec0142No());
				paramList.add(oasfri0100D.getDataSeq());
				paramList.add(oasfri0100D.getDataId());
				paramList.add(oasfri0100D.getDataName());
				paramList.add(oasfri0100D.getBalBfQty());
				paramList.add(oasfri0100D.getSeqNo());
				paramList.add(oasfri0100D.getAccountName());
				paramList.add(oasfri0100D.getInQty());
				paramList.add(oasfri0100D.getCreatedBy());
				paramList.add(oasfri0100D.getCreatedDate());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
}
