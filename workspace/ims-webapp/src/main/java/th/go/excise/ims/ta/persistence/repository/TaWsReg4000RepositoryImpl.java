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
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;
import th.go.excise.ims.ta.vo.FactoryVo;
import th.go.excise.ims.ta.vo.OutsidePlanFormVo;
import th.go.excise.ims.ta.vo.OutsidePlanVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.WsReg4000Vo;

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
		sql.append("   AND R4000.REG_STATUS = '1' "); // REG_STATUS = '1' is Active

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
		sql.append("  	R4000.REG_STATUS	, ");
		sql.append(" 	R4000.REG_DATE");
		sql.append(" FROM TA_WS_REG4000 R4000  ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_SECTOR ");
		sql.append(" ON ED_SECTOR.OFF_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 2),'0000') ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_AREA ");
		sql.append(" ON ED_AREA.OFF_CODE       = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 4),'00') ");
		sql.append(" AND R4000.IS_DELETED      = 'N' ");
		sql.append(" AND R4000.OFFICE_CODE  like ? ");

		params.add(formVo.getOfficeCode());

		if (StringUtils.isNotBlank(formVo.getFacType())) {
			params.add(formVo.getFacType());
			sql.append("AND R4000.FAC_TYPE = ? ");
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
			vo.setDutyDesc(ExciseUtils.getDutyDesc(rs.getString("DUTY_CODE")));
			vo.setRegStatus(rs.getString("REG_STATUS") + " "
					+ ConvertDateUtils.formatDateToString(rs.getDate("REG_DATE"), ConvertDateUtils.DD_MM_YY));
			return vo;
		}
	};

	private void buildFactoryQuery(StringBuilder sql, List<Object> params, String newRegId) {
		sql.append(
				" SELECT r.new_reg_id,r.cus_fullname,r.fac_fullname,r.fac_address,r.office_code,r.duty_code,ed_sector.off_short_name AS sec_desc ,ed_area.off_short_name AS area_desc," + 
				"    plan_worksheet.AUDIT_TYPE AS audit_type," + 
				"    plan_worksheet.AUDIT_START_DATE AS audit_start_date," + 
				"    plan_worksheet.AUDIT_END_DATE AS audit_end_date");
		sql.append(" FROM ta_ws_reg4000  r ");
		sql.append(" INNER JOIN excise_department  ed_sector ");
		sql.append(" ON ed_sector.off_code = CONCAT(SUBSTR(r.office_code, 0, 2),'0000') ");
		sql.append(" INNER JOIN excise_department  ed_area ");
		sql.append(" ON ed_area.off_code  = CONCAT(SUBSTR(r.office_code, 0, 4),'00') ");
		sql.append(" INNER JOIN ta_plan_worksheet_dtl plan_worksheet ON plan_worksheet.new_reg_id = r.new_reg_id ");
		sql.append(" WHERE r.is_deleted = ? ");
		sql.append(" AND plan_worksheet.OFFICE_CODE = ? ");

		params.add(FLAG.N_FLAG);
		params.add(UserLoginUtils.getCurrentUserBean().getOfficeCode());

		if (StringUtils.isNotBlank(newRegId)) {
			sql.append(" AND r.new_reg_id = ? ");
			params.add(StringUtils.trim(newRegId));
		}

	}

	@Override
	public FactoryVo findByNewRegId(String newRegId) {
		logger.debug("findByNewRegId newRegId={}", newRegId);

		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildFactoryQuery(sql, params, newRegId);

		FactoryVo datas = this.commonJdbcTemplate.queryForObject(sql.toString(), params.toArray(), new RowMapper<FactoryVo>() {
			@Override
			public FactoryVo mapRow(ResultSet rs, int rowNum) throws SQLException {
				FactoryVo factoryVo = new FactoryVo();
				factoryVo.setNewRegId(rs.getString("new_reg_id"));
				factoryVo.setCusFullname(rs.getString("cus_fullname"));
				factoryVo.setFacFullname(rs.getString("fac_fullname"));
				factoryVo.setFacAddress(rs.getString("fac_address"));
				factoryVo.setSecDesc(rs.getString("sec_desc"));
				factoryVo.setAreaDesc(rs.getString("area_desc"));
				factoryVo.setDutyCode(rs.getString("duty_code"));
				factoryVo.setDutyDesc(ExciseUtils.getDutyDesc(rs.getString("duty_code")));
				factoryVo.setAuditType(rs.getString("AUDIT_TYPE"));
				factoryVo.setAuditStartDate(ConvertDateUtils.formatDateToString(rs.getDate("AUDIT_START_DATE"), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				factoryVo.setAuditEndDate(ConvertDateUtils.formatDateToString(rs.getDate("AUDIT_END_DATE"), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
				return factoryVo;
			}
		});
		return datas;
	}

	@Override
	public List<WsReg4000Vo> findByCriteriaDuty(TaxOperatorFormVo formVo, String startMonth, String endMonth) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildByCriteriaDutyQuery(sql, params, formVo, startMonth, endMonth);

		sql.append(" ORDER BY R4000.DUTY_CODE, R4000.OFFICE_CODE, R4000.NEW_REG_ID ");
		if (StringUtils.isNotBlank(formVo.getFlagPage())) {
			return this.commonJdbcTemplate.query(sql.toString(), params.toArray(), wsReg4000RowMapper);

		} else {
			return this.commonJdbcTemplate.query(
				OracleUtils.limitForDatable(sql.toString(), formVo.getStart(), formVo.getLength()),
				params.toArray(), wsReg4000RowMapper);
		}
	}

	@Override
	public Long countByCriteriaDuty(TaxOperatorFormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		
		String ymStart = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(formVo.getDateStart(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
		String ymEnd = ConvertDateUtils.formatDateToString(ConvertDateUtils.parseStringToDate(formVo.getDateEnd(), ConvertDateUtils.MM_YYYY, ConvertDateUtils.LOCAL_TH), ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
		
		buildByCriteriaDutyQuery(sql, params, formVo, ymStart, ymEnd);

		return this.commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()), params.toArray(), Long.class);
	}
	
	private void buildByCriteriaDutyQuery(StringBuilder sql, List<Object> params, TaxOperatorFormVo formVo, String startMonth, String endMonth) {
		sql.append(" SELECT R4000.* ");
		sql.append(" FROM TA_WS_REG4000 R4000 ");
		sql.append(" INNER JOIN ( ");
		sql.append("   SELECT I.NEW_REG_ID, I.DUTY_CODE ");
		sql.append("   FROM ( ");
		sql.append("     SELECT NEW_REG_ID, DUTY_CODE, TAX_YEAR||LPAD(TAX_MONTH ,2 ,'0') AS YEAR_MONTH ");
		sql.append("     FROM TA_WS_INC8000_M ");
		sql.append("     WHERE IS_DELETED = 'N' ");
		sql.append("   ) I ");
		sql.append("   WHERE 1=1 ");
		sql.append("     AND I.YEAR_MONTH >= ? ");
		sql.append("     AND I.YEAR_MONTH <= ? ");
		sql.append("   GROUP BY I.NEW_REG_ID, I.DUTY_CODE ");
		sql.append(" ) I8000 ON I8000.NEW_REG_ID = R4000.NEW_REG_ID AND I8000.DUTY_CODE = R4000.DUTY_CODE ");
		sql.append(" WHERE R4000.IS_DELETED = 'N' ");

		params.add(startMonth);
		params.add(endMonth);
		
//		if (ApplicationCache.isCtrlDutyGroupByOfficeCode(formVo.getOfficeCode())) {
//			sql.append("   AND R4000.DUTY_CODE IN (SELECT DUTY_GROUP_CODE FROM EXCISE_CTRL_DUTY WHERE IS_DELETED = 'N' AND RES_OFFCODE = ?) ");
//			params.add(formVo.getOfficeCode());
//		} else {
//			List<String> dutyGroupIdList = ExciseUtils.getDutyGroupIdListByType(DUTY_GROUP_TYPE.PRODUCT, DUTY_GROUP_TYPE.SERVICE);
//			sql.append("   AND R4000.DUTY_CODE NOT IN (" + StringUtils.repeat("?", ",", dutyGroupIdList.size()) + ")");
//			params.addAll(dutyGroupIdList);
//		}
		
//		List<String> dutyGroupIdList = ExciseUtils.getDutyGroupIdListByType(DUTY_GROUP_TYPE.OTHER);
//		sql.append("   AND R4000.DUTY_CODE NOT IN (" + StringUtils.repeat("?", ",", dutyGroupIdList.size()) + ")");
//		params.addAll(dutyGroupIdList);
		
		// Factory Type
		if (StringUtils.isNotBlank(formVo.getFacType())) {
			params.add(formVo.getFacType());
			sql.append(" AND R4000.FAC_TYPE = ?");
		}

		// Duty Code
		if (StringUtils.isNotBlank(formVo.getDutyCode())) {
			sql.append(" AND R4000.DUTY_CODE = ?");
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
