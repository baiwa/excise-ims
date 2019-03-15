package th.go.excise.ims.ta.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAM_GROUP;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.vo.AuditCalendarCheckboxVo;
import th.go.excise.ims.ta.vo.AuditCalendarCriteriaFormVo;
import th.go.excise.ims.ta.vo.PlanMapVo;
import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetDtlVo;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;

public class TaPlanWorksheetDtlRepositoryImpl implements TaPlanWorksheetDtlRepositoryCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	private void buildByCriteriaQuery(StringBuilder sql, List<Object> params, PlanWorksheetVo formVo) {
		sql.append(" SELECT R4000.CUS_FULLNAME ");
		sql.append("   ,R4000.FAC_FULLNAME ");
		sql.append("   ,R4000.FAC_ADDRESS ");
		sql.append("   ,R4000.OFFICE_CODE OFFICE_CODE_R4000 ");
		sql.append("   ,R4000.DUTY_CODE ");
		sql.append("   ,ED_SECTOR.OFF_CODE SEC_CODE ");
		sql.append("   ,ED_SECTOR.OFF_SHORT_NAME SEC_DESC ");
		sql.append("   ,ED_AREA.OFF_CODE AREA_CODE ");
		sql.append("   ,ED_AREA.OFF_SHORT_NAME AREA_DESC ");
		sql.append("   ,PLAN_DTL.* ");
		sql.append(" FROM TA_PLAN_WORKSHEET_DTL PLAN_DTL ");
		sql.append(" INNER JOIN TA_WS_REG4000 R4000 ON R4000.NEW_REG_ID = PLAN_DTL.NEW_REG_ID ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_SECTOR ON ED_SECTOR.OFF_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 2),'0000') ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_AREA ON ED_AREA.OFF_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 4),'00') ");
		sql.append(" WHERE PLAN_DTL.IS_DELETED = 'N' ");
		sql.append("   AND R4000.IS_DELETED = 'N' ");
		sql.append("   AND PLAN_DTL.PLAN_NUMBER = ? ");

		params.add(formVo.getPlanNumber());
		if (StringUtils.isNotBlank(formVo.getOfficeCode())) {
			sql.append("   AND PLAN_DTL.OFFICE_CODE = ? ");
			params.add(formVo.getOfficeCode());
		}
	}

	@Override
	public List<PlanWorksheetDatatableVo> findByCriteria(PlanWorksheetVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildByCriteriaQuery(sql, params, formVo);

		sql.append(" ORDER BY R4000.DUTY_CODE, R4000.OFFICE_CODE, R4000.NEW_REG_ID ");

		return commonJdbcTemplate.query(OracleUtils.limitForDatable(sql.toString(), formVo.getStart(), formVo.getLength()), params.toArray(), planDtlDatatableRowMapper);
	}

	@Override
	public Long countByCriteria(PlanWorksheetVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildByCriteriaQuery(sql, params, formVo);

		return commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()), params.toArray(), Long.class);
	}

	protected RowMapper<PlanWorksheetDatatableVo> planDtlDatatableRowMapper = new RowMapper<PlanWorksheetDatatableVo>() {
		@Override
		public PlanWorksheetDatatableVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			PlanWorksheetDatatableVo vo = new PlanWorksheetDatatableVo();
			vo.setCusFullname(rs.getString("CUS_FULLNAME"));
			vo.setFacFullname(rs.getString("FAC_FULLNAME"));
			vo.setFacAddress(rs.getString("FAC_ADDRESS"));
			vo.setOfficeCodeR4000(rs.getString("OFFICE_CODE_R4000"));
			vo.setDutyCode(rs.getString("DUTY_CODE"));
			vo.setDutyDesc(ExciseUtils.getDutyDesc(rs.getString("DUTY_CODE")));
			vo.setSecCode(rs.getString("SEC_CODE"));
			vo.setSecDesc(rs.getString("SEC_DESC"));
			vo.setAreaCode(rs.getString("AREA_CODE"));
			vo.setAreaDesc(rs.getString("AREA_DESC"));
			vo.setPlanNumber(rs.getString("PLAN_NUMBER"));
			vo.setAnalysisNumber(rs.getString("ANALYSIS_NUMBER"));
			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			vo.setAuditStatus(rs.getString("AUDIT_STATUS"));
			vo.setAuditStatusDesc(ApplicationCache.getParamInfoByCode(PARAM_GROUP.TA_AUDIT_STATUS, rs.getString("AUDIT_STATUS")).getValue1());
			return vo;
		}
	};
	
	private void buildByCalendarCriteriaQuery(StringBuilder sql, List<Object> params, AuditCalendarCriteriaFormVo formVo) {
		sql.append(" SELECT PLAN_DTL.*, "); 
		sql.append("     R4000.NEW_REG_ID, ");
		sql.append("     R4000.CUS_FULLNAME, ");
		sql.append("     R4000.FAC_FULLNAME, ");
		sql.append("     R4000.OFFICE_CODE OFFICE_CODE_R4000, ");
		sql.append("     R4000.DUTY_CODE, ");
		sql.append("     ED_SECTOR.OFF_CODE SEC_CODE, ");
		sql.append("     ED_SECTOR.OFF_SHORT_NAME SEC_DESC, ");
		sql.append("     ED_AREA.OFF_CODE AREA_CODE, ");
		sql.append("     ED_AREA.OFF_SHORT_NAME AREA_DESC ");
		sql.append(" FROM TA_PLAN_WORKSHEET_DTL PLAN_DTL ");
		sql.append(" INNER JOIN TA_WS_REG4000 R4000 ");
		sql.append(" ON R4000.NEW_REG_ID = PLAN_DTL.NEW_REG_ID ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_SECTOR "); 
		sql.append(" ON ED_SECTOR.OFF_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 2),'0000') ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_AREA "); 
		sql.append(" ON ED_AREA.OFF_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 4),'00') ");
		sql.append(" WHERE PLAN_DTL.IS_DELETED = ? ");  
		sql.append(" AND PLAN_DTL.OFFICE_CODE = ? ");
        
        params.add(FLAG.N_FLAG);
        params.add(UserLoginUtils.getCurrentUserBean().getOfficeCode());

        // AUDIT_TYPE
        sql.append(" AND AUDIT_TYPE IN (");
        List<AuditCalendarCheckboxVo> auditType = formVo.getAuditType().stream()
        		.filter(e -> e.getCheckbox().equals(false))
        		.collect(Collectors.toList());
        
        for (int i = 0; i < auditType.size(); i++) {
        	AuditCalendarCheckboxVo vo = auditType.get(i);
    		if (i == auditType.size()-1) {
    			params.add(vo.getParamCode());
    			sql.append(" ?");
			} else {
				params.add(vo.getParamCode());
				sql.append(" ?,");
			}
		}
        if (0 == auditType.size()) {
			params.add("S");
			sql.append(" ?, ");
			params.add("F");
			sql.append(" ?, ");
			params.add("C");
			sql.append(" ?, ");
			params.add("I");
			sql.append(" ? ");
		}
        sql.append(" )");

     // AUDIT_STATUS
        sql.append(" AND AUDIT_STATUS IN (");
        List<AuditCalendarCheckboxVo> auditStatus = formVo.getAuditStatus().stream()
        		.filter(e -> e.getCheckbox().equals(false))
        		.collect(Collectors.toList());
        
        for (int i = 0; i < auditStatus.size(); i++) {
        	AuditCalendarCheckboxVo vo = auditStatus.get(i);
    		if (i == auditStatus.size()-1) {
    			params.add(vo.getParamCode());
    			sql.append(" ?");
			} else {
				params.add(vo.getParamCode());
				sql.append(" ?,");
			}
		}
        if (0 == auditStatus.size()) {
        	params.add("T");
			sql.append(" ?, ");
			params.add("I");
			sql.append(" ?, ");
			params.add("W");
			sql.append(" ?, ");
			params.add("P");
			sql.append(" ? ");
		}
        sql.append(" )");
    }
	
	public List<PlanWorksheetDtlVo> findByCriteria(AuditCalendarCriteriaFormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		
		buildByCalendarCriteriaQuery(sql, params, formVo);
		
		return this.commonJdbcTemplate.query(sql.toString(), params.toArray(), taPlanWorksheetDtlRowMapper);
	}
	
	private static final RowMapper<PlanWorksheetDtlVo> taPlanWorksheetDtlRowMapper = new RowMapper<PlanWorksheetDtlVo>() {
        public PlanWorksheetDtlVo mapRow(ResultSet rs, int rowNum) throws SQLException {
        	PlanWorksheetDtlVo vo = new PlanWorksheetDtlVo();
            vo.setPlanWorksheetDtlId(rs.getLong("PLAN_WORKSHEET_DTL_ID"));
            vo.setPlanNumber(rs.getString("PLAN_NUMBER"));
            vo.setAnalysisNumber(rs.getString("ANALYSIS_NUMBER"));
            vo.setOfficeCode(rs.getString("OFFICE_CODE"));
            vo.setNewRegId(rs.getString("NEW_REG_ID"));
            vo.setSystemType(rs.getString("SYSTEM_TYPE"));
            vo.setPlanType(rs.getString("PLAN_TYPE"));
            vo.setAuditStatus(rs.getString("AUDIT_STATUS"));
            vo.setAuditType(rs.getString("AUDIT_TYPE"));
            vo.setStart(ConvertDateUtils.formatDateToString(rs.getDate("AUDIT_START_DATE"), "yyyy-MM-dd", ConvertDateUtils.LOCAL_TH));
            vo.setEnd(ConvertDateUtils.formatDateToString(rs.getDate("AUDIT_END_DATE"), "yyyy-MM-dd", ConvertDateUtils.LOCAL_TH));
            vo.setCusFullName(rs.getString("CUS_FULLNAME"));
            vo.setFacFullName(rs.getString("FAC_FULLNAME"));
            vo.setOfficeCodeR4000(rs.getString("OFFICE_CODE_R4000"));
            vo.setDutyCode(rs.getString("DUTY_CODE"));
            vo.setSecCode(rs.getString("SEC_CODE"));
            vo.setSecDesc(rs.getString("SEC_DESC"));
            vo.setAreaCode(rs.getString("AREA_CODE"));
            vo.setAreaDesc(rs.getString("AREA_DESC"));
            vo.setTitle(rs.getString("AREA_DESC"));
            return vo;
        }
    };

	@Override
	public List<PlanMapVo> findByInBudgetYearPlanDtl(List<String> budgetYears) {
		StringBuilder sql = new StringBuilder();

		sql.append(" SELECT  ");
		sql.append("   PLAN_HDR.BUDGET_YEAR, ");
		sql.append("   PLAN_HDR.ANALYSIS_NUMBER,	 ");
		sql.append("   PLAN_HDR.PLAN_NUMBER, ");
		sql.append("   PLAN_DTL.NEW_REG_ID, ");
		sql.append("   PLAN_DTL.OFFICE_CODE, ");
		sql.append("   PLAN_DTL.AUDIT_PLAN_CODE ");
		sql.append("  FROM TA_PLAN_WORKSHEET_HDR PLAN_HDR ");
		sql.append(" LEFT JOIN TA_PLAN_WORKSHEET_DTL PLAN_DTL ");
		sql.append(" ON PLAN_HDR.PLAN_NUMBER=PLAN_DTL.PLAN_NUMBER ");
		// sql.append(" WHERE PLAN_DTL.OFFICE_CODE=? ");
		// sql.append(" AND BUDGET_YEAR IN (?,?,?) ");
		sql.append(" WHERE PLAN_HDR.BUDGET_YEAR IN (?,?,?) ");

		List<Object> params = new ArrayList<>();
		// params.add(UserLoginUtils.getCurrentUserBean().getOfficeCode());

		for (String budgetYear : budgetYears) {
			params.add(budgetYear);
		}

		return commonJdbcTemplate.query(sql.toString(), params.toArray(), findByInBudgetYearPlanDtlRowMapper);
	}

	protected RowMapper<PlanMapVo> findByInBudgetYearPlanDtlRowMapper = new RowMapper<PlanMapVo>() {
		@Override
		public PlanMapVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			PlanMapVo vo = new PlanMapVo();
			vo.setBudgetYear(rs.getString("BUDGET_YEAR"));
			vo.setAnalysisNumber(rs.getString("ANALYSIS_NUMBER"));
			vo.setPlanNumber(rs.getString("PLAN_NUMBER"));
			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			vo.setOfficeCode(rs.getString("OFFICE_CODE"));
			vo.setAuditPlanCode(rs.getString("AUDIT_PLAN_CODE"));
			return vo;
		}
	};
}
