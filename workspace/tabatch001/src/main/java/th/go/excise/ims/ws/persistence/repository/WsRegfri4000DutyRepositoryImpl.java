package th.go.excise.ims.ws.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.go.excise.ims.ws.persistence.entity.WsRegfri4000Duty;

public class WsRegfri4000DutyRepositoryImpl implements WsRegfri4000DutyRepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsRegfri4000DutyRepositoryImpl.class);
	
	private int batchSize;
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Autowired
	public WsRegfri4000DutyRepositoryImpl(
			@Value("${ws.excise.reg.regfri4000-duty.batch-size}") int batchSize,
			CommonJdbcTemplate commonJdbcTemplate) {
		this.batchSize = batchSize;
		this.commonJdbcTemplate = commonJdbcTemplate;
	}
	
	@Override
	public void batchInsert(List<WsRegfri4000Duty> regfri4000DutyList) {
		logger.info("batchInsert regfri4000DutyList.size()={}", regfri4000DutyList.size());
		
		List<String> insertColumnNames = new ArrayList<>();
		insertColumnNames.add("WS_REGFRI4000_DUTY_ID");
		insertColumnNames.add("NEW_REG_ID");
		insertColumnNames.add("REG_ID");
		insertColumnNames.add("GROUP_ID");
		insertColumnNames.add("GROUP_NAME");
		insertColumnNames.add("REG_DATE");
		insertColumnNames.add("PROD_DATE");
		insertColumnNames.add("IS_DELETED");
		insertColumnNames.add("CREATED_BY");
		insertColumnNames.add("CREATED_DATE");
		
		String sql = SqlGeneratorUtils.genSqlInsert("WS_REGFRI4000_DUTY", insertColumnNames, "WS_REGFRI4000_DUTY_SEQ");
		
		commonJdbcTemplate.batchUpdate(sql.toString(), regfri4000DutyList, batchSize, new ParameterizedPreparedStatementSetter<WsRegfri4000Duty>() {
			public void setValues(PreparedStatement ps, WsRegfri4000Duty regfri4000Duty) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				// Insert Statement
				paramList.add(regfri4000Duty.getNewRegId());
				paramList.add(regfri4000Duty.getRegId());
				paramList.add(regfri4000Duty.getGroupId());
				paramList.add(regfri4000Duty.getGroupName());
				paramList.add(regfri4000Duty.getRegDate());
				paramList.add(regfri4000Duty.getProdDate());
				paramList.add(FLAG.N_FLAG);
				paramList.add(regfri4000Duty.getCreatedBy());
				paramList.add(regfri4000Duty.getCreatedDate());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
