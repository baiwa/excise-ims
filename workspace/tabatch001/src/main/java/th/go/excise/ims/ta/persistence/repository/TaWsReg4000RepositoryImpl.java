package th.go.excise.ims.ta.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;

public class TaWsReg4000RepositoryImpl implements TaWsReg4000RepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(TaWsReg4000RepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchMerge(List<TaWsReg4000> taWsReg4000List) {
		logger.info("batchMerge taWsReg4000List.size()={}", taWsReg4000List.size());

		final int BATCH_SIZE = 1000;

		List<String> updateColumnNames = new ArrayList<>();
		updateColumnNames.add("REG4000.REG_ID");
		updateColumnNames.add("REG4000.REG_STATUS");
		updateColumnNames.add("REG4000.REG_STATUS_DESC");
		updateColumnNames.add("REG4000.REG_STATUS_DATE");
		updateColumnNames.add("REG4000.REG_CAPITAL");
		updateColumnNames.add("REG4000.REG_DATE");
		updateColumnNames.add("REG4000.CUS_ID");
		updateColumnNames.add("REG4000.CUS_SEQ");
		updateColumnNames.add("REG4000.CUS_ADDR_SEQ");
		updateColumnNames.add("REG4000.CUS_FULLNAME");
		updateColumnNames.add("REG4000.CUS_ADDRESS");
		updateColumnNames.add("REG4000.CUS_HOUSE_NO");
		updateColumnNames.add("REG4000.CUS_ADDR_NO");
		updateColumnNames.add("REG4000.CUS_BUILD_NAME");
		updateColumnNames.add("REG4000.CUS_FLOOR_NO");
		updateColumnNames.add("REG4000.CUS_ROOM_NO");
		updateColumnNames.add("REG4000.CUS_MOO_NO");
		updateColumnNames.add("REG4000.CUS_VILLAGE");
		updateColumnNames.add("REG4000.CUS_SOI_NAME");
		updateColumnNames.add("REG4000.CUS_THN_NAME");
		updateColumnNames.add("REG4000.CUS_TAMBOL_CODE");
		updateColumnNames.add("REG4000.CUS_TAMBOL_NAME");
		updateColumnNames.add("REG4000.CUS_AMPHUR_CODE");
		updateColumnNames.add("REG4000.CUS_AMPHUR_NAME");
		updateColumnNames.add("REG4000.CUS_PROVINCE_CODE");
		updateColumnNames.add("REG4000.CUS_PROVINCE_NAME");
		updateColumnNames.add("REG4000.CUS_ZIP_CODE");
		updateColumnNames.add("REG4000.CUS_TELNO");
		updateColumnNames.add("REG4000.CUS_EMAIL");
		updateColumnNames.add("REG4000.CUS_URL");
		updateColumnNames.add("REG4000.FAC_ID");
		updateColumnNames.add("REG4000.FAC_SEQ");
		updateColumnNames.add("REG4000.FAC_ADDR_SEQ");
		updateColumnNames.add("REG4000.FAC_FULLNAME");
		updateColumnNames.add("REG4000.FAC_ADDRESS");
		updateColumnNames.add("REG4000.FAC_HOUSE_NO");
		updateColumnNames.add("REG4000.FAC_ADDR_NO");
		updateColumnNames.add("REG4000.FAC_BUILD_NAME");
		updateColumnNames.add("REG4000.FAC_FLOOR_NO");
		updateColumnNames.add("REG4000.FAC_ROOM_NO");
		updateColumnNames.add("REG4000.FAC_MOO_NO");
		updateColumnNames.add("REG4000.FAC_VILLAGE");
		updateColumnNames.add("REG4000.FAC_SOI_NAME");
		updateColumnNames.add("REG4000.FAC_THN_NAME");
		updateColumnNames.add("REG4000.FAC_TAMBOL_CODE");
		updateColumnNames.add("REG4000.FAC_TAMBOL_NAME");
		updateColumnNames.add("REG4000.FAC_AMPHUR_CODE");
		updateColumnNames.add("REG4000.FAC_AMPHUR_NAME");
		updateColumnNames.add("REG4000.FAC_PROVINCE_CODE");
		updateColumnNames.add("REG4000.FAC_PROVINCE_NAME");
		updateColumnNames.add("REG4000.FAC_ZIP_CODE");
		updateColumnNames.add("REG4000.FAC_TELNO");
		updateColumnNames.add("REG4000.FAC_EMAIL");
		updateColumnNames.add("REG4000.FAC_URL");
		updateColumnNames.add("REG4000.FAC_TYPE");
		updateColumnNames.add("REG4000.OFFICE_CODE");
		updateColumnNames.add("REG4000.ACTIVE_FLAG");
		updateColumnNames.add("REG4000.IS_DELETED");
		updateColumnNames.add("REG4000.UPDATED_BY");
		updateColumnNames.add("REG4000.UPDATED_DATE");
		updateColumnNames.forEach(e -> {
			e = e + " = ?";
		});

		List<String> insertColumnNames = new ArrayList<>();
		insertColumnNames.add("REG4000.WS_REG4000_ID");
		insertColumnNames.add("REG4000.NEW_REG_ID");
		insertColumnNames.add("REG4000.REG_ID");
		insertColumnNames.add("REG4000.REG_STATUS");
		insertColumnNames.add("REG4000.REG_STATUS_DESC");
		insertColumnNames.add("REG4000.REG_STATUS_DATE");
		insertColumnNames.add("REG4000.REG_CAPITAL");
		insertColumnNames.add("REG4000.REG_DATE");
		insertColumnNames.add("REG4000.CUS_ID");
		insertColumnNames.add("REG4000.CUS_SEQ");
		insertColumnNames.add("REG4000.CUS_ADDR_SEQ");
		insertColumnNames.add("REG4000.CUS_FULLNAME");
		insertColumnNames.add("REG4000.CUS_ADDRESS");
		insertColumnNames.add("REG4000.CUS_HOUSE_NO");
		insertColumnNames.add("REG4000.CUS_ADDR_NO");
		insertColumnNames.add("REG4000.CUS_BUILD_NAME");
		insertColumnNames.add("REG4000.CUS_FLOOR_NO");
		insertColumnNames.add("REG4000.CUS_ROOM_NO");
		insertColumnNames.add("REG4000.CUS_MOO_NO");
		insertColumnNames.add("REG4000.CUS_VILLAGE");
		insertColumnNames.add("REG4000.CUS_SOI_NAME");
		insertColumnNames.add("REG4000.CUS_THN_NAME");
		insertColumnNames.add("REG4000.CUS_TAMBOL_CODE");
		insertColumnNames.add("REG4000.CUS_TAMBOL_NAME");
		insertColumnNames.add("REG4000.CUS_AMPHUR_CODE");
		insertColumnNames.add("REG4000.CUS_AMPHUR_NAME");
		insertColumnNames.add("REG4000.CUS_PROVINCE_CODE");
		insertColumnNames.add("REG4000.CUS_PROVINCE_NAME");
		insertColumnNames.add("REG4000.CUS_ZIP_CODE");
		insertColumnNames.add("REG4000.CUS_TELNO");
		insertColumnNames.add("REG4000.CUS_EMAIL");
		insertColumnNames.add("REG4000.CUS_URL");
		insertColumnNames.add("REG4000.FAC_ID");
		insertColumnNames.add("REG4000.FAC_SEQ");
		insertColumnNames.add("REG4000.FAC_ADDR_SEQ");
		insertColumnNames.add("REG4000.FAC_FULLNAME");
		insertColumnNames.add("REG4000.FAC_ADDRESS");
		insertColumnNames.add("REG4000.FAC_HOUSE_NO");
		insertColumnNames.add("REG4000.FAC_ADDR_NO");
		insertColumnNames.add("REG4000.FAC_BUILD_NAME");
		insertColumnNames.add("REG4000.FAC_FLOOR_NO");
		insertColumnNames.add("REG4000.FAC_ROOM_NO");
		insertColumnNames.add("REG4000.FAC_MOO_NO");
		insertColumnNames.add("REG4000.FAC_VILLAGE");
		insertColumnNames.add("REG4000.FAC_SOI_NAME");
		insertColumnNames.add("REG4000.FAC_THN_NAME");
		insertColumnNames.add("REG4000.FAC_TAMBOL_CODE");
		insertColumnNames.add("REG4000.FAC_TAMBOL_NAME");
		insertColumnNames.add("REG4000.FAC_AMPHUR_CODE");
		insertColumnNames.add("REG4000.FAC_AMPHUR_NAME");
		insertColumnNames.add("REG4000.FAC_PROVINCE_CODE");
		insertColumnNames.add("REG4000.FAC_PROVINCE_NAME");
		insertColumnNames.add("REG4000.FAC_ZIP_CODE");
		insertColumnNames.add("REG4000.FAC_TELNO");
		insertColumnNames.add("REG4000.FAC_EMAIL");
		insertColumnNames.add("REG4000.FAC_URL");
		insertColumnNames.add("REG4000.FAC_TYPE");
		insertColumnNames.add("REG4000.OFFICE_CODE");
		insertColumnNames.add("REG4000.ACTIVE_FLAG");
		insertColumnNames.add("REG4000.DUTY_CODE");
		insertColumnNames.add("REG4000.CREATED_BY");
		insertColumnNames.add("REG4000.CREATED_DATE");

		StringBuilder sql = new StringBuilder();
		sql.append(" MERGE INTO TA_WS_REG4000 REG4000 ");
		sql.append(" USING DUAL ");
		sql.append(" ON ( ");
		sql.append("   REG4000.NEW_REG_ID = ? ");
		sql.append("   AND REG4000.DUTY_CODE = ? ");
		sql.append(" ) ");
		sql.append(" WHEN MATCHED THEN ");
		sql.append("   UPDATE SET ");
		sql.append(org.springframework.util.StringUtils.collectionToDelimitedString(updateColumnNames, ","));
		sql.append(" WHEN NOT MATCHED THEN ");
		sql.append("   INSERT (" + org.springframework.util.StringUtils.collectionToDelimitedString(insertColumnNames, ",") + ") ");
		sql.append("   VALUES (TA_WS_REG4000_SEQ.NEXTVAL" + StringUtils.repeat(",?", insertColumnNames.size() - 1) + ") ");

		commonJdbcTemplate.batchUpdate(sql.toString(), taWsReg4000List, BATCH_SIZE, new ParameterizedPreparedStatementSetter<TaWsReg4000>() {
			public void setValues(PreparedStatement ps, TaWsReg4000 wsReg4000) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				// Using Condition
				paramList.add(wsReg4000.getNewRegId());
				paramList.add(wsReg4000.getDutyCode());
				// Update Statement
				paramList.add(wsReg4000.getRegId());
				paramList.add(wsReg4000.getRegStatus());
				paramList.add(wsReg4000.getRegStatusDesc());
				paramList.add(wsReg4000.getRegStatusDate());
				paramList.add(wsReg4000.getRegCapital());
				paramList.add(wsReg4000.getRegDate());
				paramList.add(wsReg4000.getCusId());
				paramList.add(wsReg4000.getCusSeq());
				paramList.add(wsReg4000.getCusAddrSeq());
				paramList.add(wsReg4000.getCusFullname());
				paramList.add(wsReg4000.getCusAddress());
				paramList.add(wsReg4000.getCusHouseNo());
				paramList.add(wsReg4000.getCusAddrNo());
				paramList.add(wsReg4000.getCusBuildName());
				paramList.add(wsReg4000.getCusFloorNo());
				paramList.add(wsReg4000.getCusRoomNo());
				paramList.add(wsReg4000.getCusMooNo());
				paramList.add(wsReg4000.getCusVillage());
				paramList.add(wsReg4000.getCusSoiName());
				paramList.add(wsReg4000.getCusThnName());
				paramList.add(wsReg4000.getCusTambolCode());
				paramList.add(wsReg4000.getCusTambolName());
				paramList.add(wsReg4000.getCusAmphurCode());
				paramList.add(wsReg4000.getCusAmphurName());
				paramList.add(wsReg4000.getCusProvinceCode());
				paramList.add(wsReg4000.getCusProvinceName());
				paramList.add(wsReg4000.getCusZipCode());
				paramList.add(wsReg4000.getCusTelno());
				paramList.add(wsReg4000.getCusEmail());
				paramList.add(wsReg4000.getCusUrl());
				paramList.add(wsReg4000.getFacId());
				paramList.add(wsReg4000.getFacSeq());
				paramList.add(wsReg4000.getFacAddrSeq());
				paramList.add(wsReg4000.getFacFullname());
				paramList.add(wsReg4000.getFacAddress());
				paramList.add(wsReg4000.getFacHouseNo());
				paramList.add(wsReg4000.getFacAddrNo());
				paramList.add(wsReg4000.getFacBuildName());
				paramList.add(wsReg4000.getFacFloorNo());
				paramList.add(wsReg4000.getFacRoomNo());
				paramList.add(wsReg4000.getFacMooNo());
				paramList.add(wsReg4000.getFacVillage());
				paramList.add(wsReg4000.getFacSoiName());
				paramList.add(wsReg4000.getFacThnName());
				paramList.add(wsReg4000.getFacTambolCode());
				paramList.add(wsReg4000.getFacTambolName());
				paramList.add(wsReg4000.getFacAmphurCode());
				paramList.add(wsReg4000.getFacAmphurName());
				paramList.add(wsReg4000.getFacProvinceCode());
				paramList.add(wsReg4000.getFacProvinceName());
				paramList.add(wsReg4000.getFacZipCode());
				paramList.add(wsReg4000.getFacTelno());
				paramList.add(wsReg4000.getFacEmail());
				paramList.add(wsReg4000.getFacUrl());
				paramList.add(wsReg4000.getFacType());
				paramList.add(wsReg4000.getOfficeCode());
				paramList.add(wsReg4000.getActiveFlag());
				paramList.add(FLAG.N_FLAG);
				paramList.add(wsReg4000.getUpdatedBy());
				paramList.add(wsReg4000.getUpdatedDate());
				// Insert Statement
				paramList.add(wsReg4000.getNewRegId());
				paramList.add(wsReg4000.getRegId());
				paramList.add(wsReg4000.getRegStatus());
				paramList.add(wsReg4000.getRegStatusDesc());
				paramList.add(wsReg4000.getRegStatusDate());
				paramList.add(wsReg4000.getRegCapital());
				paramList.add(wsReg4000.getRegDate());
				paramList.add(wsReg4000.getCusId());
				paramList.add(wsReg4000.getCusSeq());
				paramList.add(wsReg4000.getCusAddrSeq());
				paramList.add(wsReg4000.getCusFullname());
				paramList.add(wsReg4000.getCusAddress());
				paramList.add(wsReg4000.getCusHouseNo());
				paramList.add(wsReg4000.getCusAddrNo());
				paramList.add(wsReg4000.getCusBuildName());
				paramList.add(wsReg4000.getCusFloorNo());
				paramList.add(wsReg4000.getCusRoomNo());
				paramList.add(wsReg4000.getCusMooNo());
				paramList.add(wsReg4000.getCusVillage());
				paramList.add(wsReg4000.getCusSoiName());
				paramList.add(wsReg4000.getCusThnName());
				paramList.add(wsReg4000.getCusTambolCode());
				paramList.add(wsReg4000.getCusTambolName());
				paramList.add(wsReg4000.getCusAmphurCode());
				paramList.add(wsReg4000.getCusAmphurName());
				paramList.add(wsReg4000.getCusProvinceCode());
				paramList.add(wsReg4000.getCusProvinceName());
				paramList.add(wsReg4000.getCusZipCode());
				paramList.add(wsReg4000.getCusTelno());
				paramList.add(wsReg4000.getCusEmail());
				paramList.add(wsReg4000.getCusUrl());
				paramList.add(wsReg4000.getFacId());
				paramList.add(wsReg4000.getFacSeq());
				paramList.add(wsReg4000.getFacAddrSeq());
				paramList.add(wsReg4000.getFacFullname());
				paramList.add(wsReg4000.getFacAddress());
				paramList.add(wsReg4000.getFacHouseNo());
				paramList.add(wsReg4000.getFacAddrNo());
				paramList.add(wsReg4000.getFacBuildName());
				paramList.add(wsReg4000.getFacFloorNo());
				paramList.add(wsReg4000.getFacRoomNo());
				paramList.add(wsReg4000.getFacMooNo());
				paramList.add(wsReg4000.getFacVillage());
				paramList.add(wsReg4000.getFacSoiName());
				paramList.add(wsReg4000.getFacThnName());
				paramList.add(wsReg4000.getFacTambolCode());
				paramList.add(wsReg4000.getFacTambolName());
				paramList.add(wsReg4000.getFacAmphurCode());
				paramList.add(wsReg4000.getFacAmphurName());
				paramList.add(wsReg4000.getFacProvinceCode());
				paramList.add(wsReg4000.getFacProvinceName());
				paramList.add(wsReg4000.getFacZipCode());
				paramList.add(wsReg4000.getFacTelno());
				paramList.add(wsReg4000.getFacEmail());
				paramList.add(wsReg4000.getFacUrl());
				paramList.add(wsReg4000.getFacType());
				paramList.add(wsReg4000.getOfficeCode());
				paramList.add(wsReg4000.getActiveFlag());
				paramList.add(wsReg4000.getDutyCode());
				paramList.add(wsReg4000.getCreatedBy());
				paramList.add(wsReg4000.getCreatedDate());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
