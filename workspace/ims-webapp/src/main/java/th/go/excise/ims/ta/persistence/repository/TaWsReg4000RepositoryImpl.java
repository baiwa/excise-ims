package th.go.excise.ims.ta.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateConverter;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;
import th.go.excise.ims.ta.vo.OutsidePlanFormVo;
import th.go.excise.ims.ta.vo.OutsidePlanVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.WsReg4000Vo;
import th.go.excise.ims.ta.vo.WsRegfri4000FormVo;
import th.go.excise.ims.ws.client.pcc.regfri4000.model.RegDuty;

public class TaWsReg4000RepositoryImpl implements TaWsReg4000RepositoryCustom {

	private static final Logger logger = LoggerFactory.getLogger(TaWsReg4000RepositoryImpl.class);

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchMerge(List<TaWsReg4000> taWsReg4000List) {
		logger.info("batchMerge taWsReg4000List.size()={}", taWsReg4000List.size());
		
		final int BATCH_SIZE = 1000;
		
		List<String> updateColumnNames = new ArrayList<>(Arrays.asList(
			"REG4000.CUS_ID = ?",
			"REG4000.CUS_FULLNAME = ?",
			"REG4000.CUS_ADDRESS = ?",
			"REG4000.CUS_TELNO = ?",
			"REG4000.CUS_EMAIL = ?",
			"REG4000.CUS_URL = ?",
			"REG4000.FAC_ID = ?",
			"REG4000.FAC_FULLNAME = ?",
			"REG4000.FAC_ADDRESS = ?",
			"REG4000.FAC_TELNO = ?",
			"REG4000.FAC_EMAIL = ?",
			"REG4000.FAC_URL = ?",
			"REG4000.FAC_TYPE = ?",
			"REG4000.REG_ID = ?",
			"REG4000.REG_STATUS = ?",
			"REG4000.REG_DATE = ?",
			"REG4000.REG_CAPITAL = ?",
			"REG4000.OFFICE_CODE = ?",
			"REG4000.ACTIVE_FLAG = ?",
			"REG4000.IS_DELETED = ?",
			"REG4000.UPDATED_BY = ?",
			"REG4000.UPDATED_DATE = ?"
		));
		
		List<String> insertColumnNames = new ArrayList<>(Arrays.asList(
			"REG4000.WS_REG4000_ID",
			"REG4000.NEW_REG_ID",
			"REG4000.CUS_ID",
			"REG4000.CUS_FULLNAME",
			"REG4000.CUS_ADDRESS",
			"REG4000.CUS_TELNO",
			"REG4000.CUS_EMAIL",
			"REG4000.CUS_URL",
			"REG4000.FAC_ID",
			"REG4000.FAC_FULLNAME",
			"REG4000.FAC_ADDRESS",
			"REG4000.FAC_TELNO",
			"REG4000.FAC_EMAIL",
			"REG4000.FAC_URL",
			"REG4000.FAC_TYPE",
			"REG4000.REG_ID",
			"REG4000.REG_STATUS",
			"REG4000.REG_DATE",
			"REG4000.REG_CAPITAL",
			"REG4000.OFFICE_CODE",
			"REG4000.ACTIVE_FLAG",
			"REG4000.DUTY_CODE",
			"REG4000.CREATED_BY",
			"REG4000.CREATED_DATE"
		));
		
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
				paramList.add(wsReg4000.getCusId());
				paramList.add(wsReg4000.getCusFullname());
				paramList.add(wsReg4000.getCusAddress());
				paramList.add(wsReg4000.getCusTelno());
				paramList.add(wsReg4000.getCusEmail());
				paramList.add(wsReg4000.getCusUrl());
				paramList.add(wsReg4000.getFacId());
				paramList.add(wsReg4000.getFacFullname());
				paramList.add(wsReg4000.getFacAddress());
				paramList.add(wsReg4000.getFacTelno());
				paramList.add(wsReg4000.getFacEmail());
				paramList.add(wsReg4000.getFacUrl());
				paramList.add(wsReg4000.getFacType());
				paramList.add(wsReg4000.getRegId());
				paramList.add(wsReg4000.getRegStatus());
				paramList.add(wsReg4000.getRegDate());
				paramList.add(wsReg4000.getRegCapital());
				paramList.add(wsReg4000.getOfficeCode());
				paramList.add(wsReg4000.getActiveFlag());
				paramList.add(FLAG.N_FLAG);
				paramList.add(wsReg4000.getUpdatedBy());
				paramList.add(wsReg4000.getUpdatedDate());
				// Insert Statement
				paramList.add(wsReg4000.getNewRegId());
				paramList.add(wsReg4000.getCusId());
				paramList.add(wsReg4000.getCusFullname());
				paramList.add(wsReg4000.getCusAddress());
				paramList.add(wsReg4000.getCusTelno());
				paramList.add(wsReg4000.getCusEmail());
				paramList.add(wsReg4000.getCusUrl());
				paramList.add(wsReg4000.getFacId());
				paramList.add(wsReg4000.getFacFullname());
				paramList.add(wsReg4000.getFacAddress());
				paramList.add(wsReg4000.getFacTelno());
				paramList.add(wsReg4000.getFacEmail());
				paramList.add(wsReg4000.getFacUrl());
				paramList.add(wsReg4000.getFacType());
				paramList.add(wsReg4000.getRegId());
				paramList.add(wsReg4000.getRegStatus());
				paramList.add(wsReg4000.getRegDate());
				paramList.add(wsReg4000.getRegCapital());
				paramList.add(wsReg4000.getOfficeCode());
				paramList.add(wsReg4000.getActiveFlag());
				paramList.add(wsReg4000.getDutyCode());
				paramList.add(wsReg4000.getCreatedBy());
				paramList.add(wsReg4000.getCreatedDate());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

	private void buildByCriteriaQuery(StringBuilder sql, List<Object> params, TaxOperatorFormVo formVo) {
		sql.append(" SELECT R4000.*, R4000D.GROUP_ID, R4000D.GROUP_NAME ");
		sql.append(" FROM TA_WS_REG4000 R4000 ");
		sql.append(" INNER JOIN TA_WS_REG4000_DUTY R4000D ON R4000D.NEWREG_ID = R4000.NEW_REG_ID ");
		sql.append(" WHERE R4000.IS_DELETED = 'N' ");
		

		// Factory Type
		if (StringUtils.isNotBlank(formVo.getFacType())) {
			params.add(formVo.getFacType());
			sql.append(" AND R4000.FAC_TYPE = ?");
		}

		// Duty Code
		if (StringUtils.isNotBlank(formVo.getDutyCode())) {
			sql.append(" AND R4000D.GROUP_ID = ?");
			params.add(formVo.getDutyCode());
		}

		// Office Code
		if (StringUtils.isNotBlank(formVo.getOfficeCode())) {
			sql.append(" AND R4000.OFFICE_CODE LIKE ?");
			params.add(ExciseUtils.whereInLocalOfficeCode(formVo.getOfficeCode()));
		}

		// Fac fullname
		if (StringUtils.isNotBlank(formVo.getFacFullname())) {
			sql.append(" AND R4000.FAC_FULLNAME LIKE ?");
			params.add("%" + StringUtils.trim(formVo.getFacFullname()) + "%");
		}

		// Cus fullname
		if (StringUtils.isNotBlank(formVo.getCusFullname())) {
			sql.append(" AND R4000.CUS_FULLNAME LIKE ?");
			params.add("%" + StringUtils.trim(formVo.getCusFullname()) + "%");
		}
		
		// newRegId
		if(StringUtils.isNotBlank(formVo.getNewRegId())) {
			sql.append(" AND R4000.NEW_REG_ID = ?");
			params.add(StringUtils.trim(formVo.getNewRegId()));
		}
		
		if(StringUtils.isNotBlank(formVo.getCuscatId())) {
			sql.append(" AND R4000.CUSCAT_ID = ?");
			params.add(StringUtils.trim(formVo.getCuscatId()));
		}
		if(StringUtils.isNotBlank(formVo.getPinnitId())) {
			sql.append(" AND R4000.PINNIT_ID = ?");
			params.add(StringUtils.trim(formVo.getPinnitId()));
		}
		sql.append("   AND R4000.REG_STATUS IN ('1','2','3','41','51') "); // REG_STATUS = '1' is Active
	}

	@Override
	public List<WsReg4000Vo> findByCriteria(TaxOperatorFormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildByCriteriaQuery(sql, params, formVo);

		sql.append(" ORDER BY R4000D.GROUP_ID, R4000.OFFICE_CODE, R4000.NEW_REG_ID ");

		if (StringUtils.isNotBlank(formVo.getFlagPage())) {
			return this.commonJdbcTemplate.query(sql.toString(), params.toArray(), wsReg4000RowMapper);
		} else {
			return this.commonJdbcTemplate.query(
				OracleUtils.limitForDatable(sql.toString(), formVo.getStart(), formVo.getLength()),
				params.toArray(),
				wsReg4000RowMapper);
		}
	}

	@Override
	public Long countByCriteria(TaxOperatorFormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildByCriteriaQuery(sql, params, formVo);

		return this.commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()), params.toArray(), Long.class);
	}

	private static final RowMapper<WsReg4000Vo> wsReg4000RowMapper = new RowMapper<WsReg4000Vo>() {
		@Override
		public WsReg4000Vo mapRow(ResultSet rs, int rowNum) throws SQLException {
			WsReg4000Vo vo = new WsReg4000Vo();
			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			vo.setCusId(rs.getString("CUS_ID"));
			vo.setCusFullname(rs.getString("CUS_FULLNAME"));
			vo.setCusAddress(rs.getString("CUS_ADDRESS"));
			vo.setCusTelno(rs.getString("CUS_TELNO"));
			vo.setCusEmail(rs.getString("CUS_EMAIL"));
			vo.setCusUrl(rs.getString("CUS_URL"));
			vo.setFacId(rs.getString("FAC_ID"));
			vo.setFacFullname(rs.getString("FAC_FULLNAME"));
			vo.setFacAddress(rs.getString("FAC_ADDRESS"));
			vo.setFacTelno(rs.getString("FAC_TELNO"));
			vo.setFacEmail(rs.getString("FAC_EMAIL"));
			vo.setFacUrl(rs.getString("FAC_URL"));
			vo.setFacType(rs.getString("FAC_TYPE"));
			vo.setRegStatus(rs.getString("REG_STATUS"));
			vo.setRegDate(LocalDateConverter.convertToEntityAttribute(rs.getDate("REG_DATE")));
			vo.setRegCapital(rs.getString("REG_CAPITAL"));
			vo.setOfficeCode(rs.getString("OFFICE_CODE"));
			vo.setDutyGroupId(rs.getString("GROUP_ID"));
			vo.setDutyGroupDesc(rs.getString("GROUP_NAME"));
			vo.setMultiDutyFlag(rs.getString("MULTI_DUTY_FLAG"));
			return vo;
		}
	};

	private void sqlOutsidePlan(StringBuilder sql, List<Object> params, OutsidePlanFormVo formVo) {
		sql.append(" SELECT R4000.CUS_FULLNAME , ");
		sql.append("   R4000.NEW_REG_ID , ");
		sql.append("   R4000.FAC_FULLNAME , ");
		sql.append("   R4000.FAC_ADDRESS , ");
		sql.append("   R4000.OFFICE_CODE OFFICE_CODE_R4000 , ");
		sql.append("   R4000.DUTY_CODE , ");
		sql.append("   ED_SECTOR.OFF_CODE SEC_CODE , ");
		sql.append("   ED_SECTOR.OFF_SHORT_NAME SEC_DESC , ");
		sql.append("   ED_AREA.OFF_CODE AREA_CODE , ");
		sql.append("   ED_AREA.OFF_SHORT_NAME AREA_DESC,  ");
		sql.append("   R4000.REG_STATUS	, ");
		sql.append("   R4000.REG_DATE , ");
		sql.append("   DUTY_LIST.DUTY_GROUP_NAME ");
		sql.append(" FROM TA_WS_REG4000 R4000  ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_SECTOR ");
		sql.append(" ON ED_SECTOR.OFF_CODE = SUBSTR( R4000.OFFICE_CODE,  0, 2  ) || '0000' ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_AREA ");
		sql.append(" ON ED_AREA.OFF_CODE = SUBSTR( R4000.OFFICE_CODE,0,4)||'00' ");
		sql.append(" AND R4000.IS_DELETED      = 'N'  AND R4000.OFFICE_CODE  like ? ");
		params.add(formVo.getOfficeCode());
		
//		sql.append(" LEFT JOIN TA_WORKSHEET_DTL WK_DTL " );
//		sql.append(" ON WK_DTL.NEW_REG_ID = R4000.NEW_REG_ID");
		
		sql.append(" LEFT JOIN ( ");
		sql.append("     SELECT DUTY.NEWREG_ID , LISTAGG(DUTY.GROUP_NAME,',') WITHIN GROUP (ORDER BY DUTY.GROUP_NAME) AS DUTY_GROUP_NAME ");
		sql.append("     FROM TA_WS_REG4000_DUTY DUTY  ");
		sql.append("     GROUP BY  DUTY.NEWREG_ID ");
		sql.append(" ) DUTY_LIST ");
		sql.append(" ON DUTY_LIST.NEWREG_ID = R4000.NEW_REG_ID ");

		sql.append(" WHERE 1 = 1 ");

//		if (StringUtils.isNotBlank(formVo.getFacType())) {
//			params.add(formVo.getFacType());
//			sql.append("AND R4000.FAC_TYPE = ? ");
//		}
		// DUTY GROUP
		if (StringUtils.isNotBlank(formVo.getFacType())) {
			sql.append(" AND ECDG.DUTY_GROUP_TYPE = ?");
			params.add(formVo.getFacType());
		}

		if (StringUtils.isNotBlank(formVo.getCusFullname())) {
			params.add("%" + StringUtils.trim(formVo.getCusFullname()) + "%");
			sql.append(" AND R4000.CUS_FULLNAME LIKE ? ");
		}

		if (StringUtils.isNotBlank(formVo.getFacFullname())) {
			params.add("%" + StringUtils.trim(formVo.getFacFullname()) + "%");
			sql.append(" AND R4000.FAC_FULLNAME LIKE ? ");
		}

		if (StringUtils.isNotBlank(formVo.getDutyCode())) {
			params.add(formVo.getDutyCode());
			sql.append(" AND R4000.DUTY_CODE = ? ");
		}
		
		if (StringUtils.isNotBlank(formVo.getDutyCode())) {
			params.add(formVo.getDutyCode());
			sql.append(" AND R4000.DUTY_CODE = ? ");
		}
		
		if (StringUtils.isNotBlank(formVo.getCusType())) {
			params.add(formVo.getCusType());
			sql.append(" AND R4000.CUSCAT_ID = ? ");
		}
		
		if (StringUtils.isNotBlank(formVo.getCusId())) {
			params.add(formVo.getCusId());
			sql.append(" AND R4000.PINNIT_ID = ? ");
		}
		if (formVo.getFromDate() != null) {
			params.add(formVo.getFromDate());
			sql.append(" AND R4000.REG_DATE > ? ");
		}
		
		if (formVo.getToDate() != null) {
			params.add(formVo.getToDate());
			sql.append(" AND R4000.REG_DATE < ? ");
		}
	}

	@Override
	public List<OutsidePlanVo> outsidePlan(OutsidePlanFormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlOutsidePlan(sql, params, formVo);
		return commonJdbcTemplate.query(
				OracleUtils.limitForDatable(sql.toString(), formVo.getStart(), formVo.getLength()), params.toArray(),
				outsidePlanRowMapper);

	}

	@Override
	public Long countOutsidePlan(OutsidePlanFormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sqlOutsidePlan(sql, params, formVo);
		return this.commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()), params.toArray(),
				Long.class);
	}

	protected RowMapper<OutsidePlanVo> outsidePlanRowMapper = new RowMapper<OutsidePlanVo>() {
		@Override
		public OutsidePlanVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			OutsidePlanVo vo = new OutsidePlanVo();

			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			vo.setCusFullname(rs.getString("CUS_FULLNAME"));
			vo.setFacFullname(rs.getString("FAC_FULLNAME"));
			vo.setFacAddress(rs.getString("FAC_ADDRESS"));
			vo.setOfficeCodeR4000(rs.getString("OFFICE_CODE_R4000"));
			vo.setDutyCode(rs.getString("DUTY_CODE"));
			vo.setSecCode(rs.getString("SEC_CODE"));
			vo.setSecDesc(rs.getString("SEC_DESC"));
			vo.setAreaCode(rs.getString("AREA_CODE"));
			vo.setAreaDesc(rs.getString("AREA_DESC"));
//			vo.setDutyDesc(ExciseUtils.getDutyDesc(rs.getString("DUTY_CODE")));
			vo.setDutyDesc(rs.getString("DUTY_GROUP_NAME"));
			vo.setRegDate(ConvertDateUtils.formatDateToString(rs.getDate("REG_DATE"), ConvertDateUtils.DD_MM_YY));
			vo.setRegStatus(rs.getString("REG_STATUS") + " "
					+ ConvertDateUtils.formatDateToString(rs.getDate("REG_DATE"), ConvertDateUtils.DD_MM_YY));
			return vo;
		}
	};

	@Override
	public WsRegfri4000FormVo findByNewRegId(String newRegId) {
		logger.info("findByNewRegId newRegId={}", newRegId);

		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		
		sql.append(" SELECT R4000.*, R4000D.GROUP_ID, R4000D.GROUP_NAME ");
		sql.append(" FROM TA_WS_REG4000 R4000 ");
		sql.append(" INNER JOIN TA_WS_REG4000_DUTY R4000D ON R4000D.NEWREG_ID = R4000.NEW_REG_ID ");
		sql.append(" WHERE R4000.IS_DELETED = 'N' ");
		sql.append("   AND R4000.REG_STATUS IN ('1','2','3','41','51') "); // REG_STATUS = '1' is Active
		sql.append("   AND R4000.NEW_REG_ID = ? ");
		params.add(newRegId);

		WsRegfri4000FormVo result = commonJdbcTemplate.query(sql.toString(), params.toArray(), new ResultSetExtractor<WsRegfri4000FormVo>() {
			@Override
			public WsRegfri4000FormVo extractData(ResultSet rs) throws SQLException, DataAccessException {
				WsRegfri4000FormVo vo = new WsRegfri4000FormVo();
				List<RegDuty> regDutyList = new ArrayList<>();
				RegDuty regDuty = null;
				int i = 0;
				while (rs.next()) {
					if (i == 0) {
						vo.setNewregId(rs.getString("NEW_REG_ID"));
						vo.setNewRegId(rs.getString("NEW_REG_ID"));
						vo.setRegId(rs.getString("REG_ID"));
						vo.setRegStatus(rs.getString("REG_STATUS"));
						vo.setRegStatusDesc(rs.getString("REG_STATUS_DESC"));
						vo.setStatusDate(rs.getString("REG_STATUS_DATE"));
						vo.setCusId(rs.getString("CUS_ID"));
						vo.setCusFullname(rs.getString("CUS_FULLNAME"));
						vo.setCustomerAddress(rs.getString("CUS_ADDRESS"));
						vo.setFacId(rs.getString("FAC_ID"));
						vo.setFacFullname(rs.getString("FAC_FULLNAME"));
						vo.setFacAddress(rs.getString("FAC_ADDRESS"));
						vo.setOffcode(rs.getString("OFFICE_CODE"));
						vo.setCapital(rs.getString("REG_CAPITAL"));
						vo.setFactoryType(rs.getString("FAC_TYPE"));
					}
					regDuty = new RegDuty();
					regDuty.setGroupId(rs.getString("GROUP_ID"));
					regDuty.setGroupName(rs.getString("GROUP_NAME"));
					regDuty.setRegDate("REG_DATE");
					regDutyList.add(regDuty);
					i++;
				}
				vo.setRegDutyList(regDutyList);
				return vo;
			}
		});
		
		return result;
	}

	@Override
	public Map<String, List<String>> findDutyByNewRegId(List<String> newRegIdList) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT NEWREG_ID, REG_ID, GROUP_ID, GROUP_NAME ");
		sql.append(" FROM TA_WS_REG4000_DUTY ");
		sql.append(" WHERE NEWREG_ID IN ( ");
		sql.append(StringUtils.repeat("?", ",", newRegIdList.size()));
		sql.append(" ) ");
		sql.append(" ORDER BY NEWREG_ID, GROUP_ID ");
		
		List<Object> paramList = new ArrayList<>();
		paramList.addAll(newRegIdList);
		
		Map<String, List<String>> dutyMap = commonJdbcTemplate.query(sql.toString(), paramList.toArray(), new ResultSetExtractor<Map<String, List<String>>>() {
			public Map<String, List<String>> extractData(ResultSet rs) throws SQLException, DataAccessException {
				Map<String, List<String>> tmpDutyMap = new HashMap<>();
				List<String> dutyGroupList = null;
				while (rs.next()) {
					dutyGroupList = tmpDutyMap.get(rs.getString("NEWREG_ID"));
					if (dutyGroupList == null) {
						dutyGroupList = new ArrayList<>();
					}
					dutyGroupList.add(rs.getString("GROUP_NAME"));
					tmpDutyMap.put(rs.getString("NEWREG_ID"), dutyGroupList);
				}
				return tmpDutyMap;
			}
		});

		return dutyMap;
	}
	
}
