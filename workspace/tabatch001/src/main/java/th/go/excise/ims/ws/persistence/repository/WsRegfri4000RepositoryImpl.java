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
import th.go.excise.ims.ws.persistence.entity.WsRegfri4000;

public class WsRegfri4000RepositoryImpl implements WsRegfri4000RepositoryCustom {
	
	private static final Logger logger = LoggerFactory.getLogger(WsRegfri4000RepositoryImpl.class);

	private int batchSize;
	private CommonJdbcTemplate commonJdbcTemplate;
	
	@Autowired
	public WsRegfri4000RepositoryImpl(
			@Value("${ws.excise.reg.regfri4000.batch-size}") int batchSize,
			CommonJdbcTemplate commonJdbcTemplate) {
		this.batchSize = batchSize;
		this.commonJdbcTemplate = commonJdbcTemplate;
	}

	@Override
	public void batchInsert(List<WsRegfri4000> regfri4000List) {
		logger.info("batchInsert regfri4000List.size()={}", regfri4000List.size());
		
		List<String> insertColumnNames = new ArrayList<>();
		insertColumnNames.add("WS_REGFRI4000_ID");
		insertColumnNames.add("NEW_REG_ID");
		insertColumnNames.add("REG_ID");
		insertColumnNames.add("REG_STATUS");
		insertColumnNames.add("REG_STATUS_DESC");
		insertColumnNames.add("STATUS_DATE");
		insertColumnNames.add("CUS_ID");
		insertColumnNames.add("CUS_SEQ");
		insertColumnNames.add("CUS_ADDR_SEQ");
		insertColumnNames.add("CUS_FULLNAME");
		insertColumnNames.add("CUS_HOUSE_NO");
		insertColumnNames.add("CUS_ADDR_NO");
		insertColumnNames.add("CUS_BUILD_NAME");
		insertColumnNames.add("CUS_FLOOR_NO");
		insertColumnNames.add("CUS_ROOM_NO");
		insertColumnNames.add("CUS_MOO_NO");
		insertColumnNames.add("CUS_VILLAGE");
		insertColumnNames.add("CUS_SOI_NAME");
		insertColumnNames.add("CUS_THN_NAME");
		insertColumnNames.add("CUS_TAMBOL_CODE");
		insertColumnNames.add("CUS_TAMBOL_NAME");
		insertColumnNames.add("CUS_AMPHUR_CODE");
		insertColumnNames.add("CUS_AMPHUR_NAME");
		insertColumnNames.add("CUS_PROVINCE_CODE");
		insertColumnNames.add("CUS_PROVINCE_NAME");
		insertColumnNames.add("CUS_ZIP_CODE");
		insertColumnNames.add("CUS_TEL_NO");
		insertColumnNames.add("CUS_EMAIL");
		insertColumnNames.add("CUS_URL");
		insertColumnNames.add("FAC_ID");
		insertColumnNames.add("FAC_SEQ");
		insertColumnNames.add("FAC_ADDR_SEQ");
		insertColumnNames.add("FAC_FULLNAME");
		insertColumnNames.add("FAC_HOUSE_NO");
		insertColumnNames.add("FAC_ADDR_NO");
		insertColumnNames.add("FAC_BUILD_NAME");
		insertColumnNames.add("FAC_FLOOR_NO");
		insertColumnNames.add("FAC_ROOM_NO");
		insertColumnNames.add("FAC_MOO_NO");
		insertColumnNames.add("FAC_VILLAGE");
		insertColumnNames.add("FAC_SOI_NAME");
		insertColumnNames.add("FAC_THN_NAME");
		insertColumnNames.add("FAC_TAMBOL_CODE");
		insertColumnNames.add("FAC_TAMBOL_NAME");
		insertColumnNames.add("FAC_AMPHUR_CODE");
		insertColumnNames.add("FAC_AMPHUR_NAME");
		insertColumnNames.add("FAC_PROVINCE_CODE");
		insertColumnNames.add("FAC_PROVINCE_NAME");
		insertColumnNames.add("FAC_ZIP_CODE");
		insertColumnNames.add("FAC_TEL_NO");
		insertColumnNames.add("FAC_EMAIL");
		insertColumnNames.add("FAC_URL");
		insertColumnNames.add("CAPITAL");
		insertColumnNames.add("OFF_CODE");
		insertColumnNames.add("ACTIVE_FLAG");
		insertColumnNames.add("SYNC_DATE");
		insertColumnNames.add("IS_DELETED");
		insertColumnNames.add("CREATED_BY");
		insertColumnNames.add("CREATED_DATE");
		
		String sql = SqlGeneratorUtils.genSqlInsert("WS_REGFRI4000", insertColumnNames, "WS_REGFRI4000_SEQ");
		
		commonJdbcTemplate.batchUpdate(sql, regfri4000List, batchSize, new ParameterizedPreparedStatementSetter<WsRegfri4000>() {
			public void setValues(PreparedStatement ps, WsRegfri4000 regfri4000) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				// Insert Statement
				paramList.add(regfri4000.getNewRegId());
				paramList.add(regfri4000.getRegId());
				paramList.add(regfri4000.getRegStatus());
				paramList.add(regfri4000.getRegStatusDesc());
				paramList.add(regfri4000.getStatusDate());
				paramList.add(regfri4000.getCusId());
				paramList.add(regfri4000.getCusSeq());
				paramList.add(regfri4000.getCusAddrSeq());
				paramList.add(regfri4000.getCusFullname());
				paramList.add(regfri4000.getCusHouseNo());
				paramList.add(regfri4000.getCusAddrNo());
				paramList.add(regfri4000.getCusBuildName());
				paramList.add(regfri4000.getCusFloorNo());
				paramList.add(regfri4000.getCusRoomNo());
				paramList.add(regfri4000.getCusMooNo());
				paramList.add(regfri4000.getCusVillage());
				paramList.add(regfri4000.getCusSoiName());
				paramList.add(regfri4000.getCusThnName());
				paramList.add(regfri4000.getCusTambolCode());
				paramList.add(regfri4000.getCusTambolName());
				paramList.add(regfri4000.getCusAmphurCode());
				paramList.add(regfri4000.getCusAmphurName());
				paramList.add(regfri4000.getCusProvinceCode());
				paramList.add(regfri4000.getCusProvinceName());
				paramList.add(regfri4000.getCusZipCode());
				paramList.add(regfri4000.getCusTelNo());
				paramList.add(regfri4000.getCusEmail());
				paramList.add(regfri4000.getCusUrl());
				paramList.add(regfri4000.getFacId());
				paramList.add(regfri4000.getFacSeq());
				paramList.add(regfri4000.getFacAddrSeq());
				paramList.add(regfri4000.getFacFullname());
				paramList.add(regfri4000.getFacHouseNo());
				paramList.add(regfri4000.getFacAddrNo());
				paramList.add(regfri4000.getFacBuildName());
				paramList.add(regfri4000.getFacFloorNo());
				paramList.add(regfri4000.getFacRoomNo());
				paramList.add(regfri4000.getFacMooNo());
				paramList.add(regfri4000.getFacVillage());
				paramList.add(regfri4000.getFacSoiName());
				paramList.add(regfri4000.getFacThnName());
				paramList.add(regfri4000.getFacTambolCode());
				paramList.add(regfri4000.getFacTambolName());
				paramList.add(regfri4000.getFacAmphurCode());
				paramList.add(regfri4000.getFacAmphurName());
				paramList.add(regfri4000.getFacProvinceCode());
				paramList.add(regfri4000.getFacProvinceName());
				paramList.add(regfri4000.getFacZipCode());
				paramList.add(regfri4000.getFacTelNo());
				paramList.add(regfri4000.getFacEmail());
				paramList.add(regfri4000.getFacUrl());
				paramList.add(regfri4000.getCapital());
				paramList.add(regfri4000.getOffCode());
				paramList.add(regfri4000.getActiveFlag());
				paramList.add(regfri4000.getSyncDate());
				paramList.add(FLAG.N_FLAG);
				paramList.add(regfri4000.getCreatedBy());
				paramList.add(regfri4000.getCreatedDate());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
}
