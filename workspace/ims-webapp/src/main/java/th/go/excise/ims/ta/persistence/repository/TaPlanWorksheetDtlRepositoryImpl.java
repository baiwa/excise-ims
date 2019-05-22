package th.go.excise.ims.ta.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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
import th.go.excise.ims.common.constant.ProjectConstants;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.preferences.persistence.entity.ExcisePerson;
import th.go.excise.ims.ta.vo.AuditCalendarCheckboxVo;
import th.go.excise.ims.ta.vo.AuditCalendarCriteriaFormVo;
import th.go.excise.ims.ta.vo.PersonAssignForm;
import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetDtlVo;
import th.go.excise.ims.ta.vo.PlanWorksheetSendTableVo;
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
		sql.append("   ,ED_SUBDEPT.SUBDEPT_SHORT_NAME SUBDEPTSHORTNAME ");
		sql.append("   ,ED_PERSON.ED_PERSON_NAME PERSON_NAME ");
		sql.append(" FROM TA_PLAN_WORKSHEET_DTL PLAN_DTL ");
		sql.append(" INNER JOIN TA_WS_REG4000 R4000 ON R4000.NEW_REG_ID = PLAN_DTL.NEW_REG_ID ");
		sql.append(" LEFT JOIN EXCISE_DEPARTMENT ED_SECTOR ON ED_SECTOR.OFF_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 2),'0000') ");
		sql.append(" LEFT JOIN EXCISE_DEPARTMENT ED_AREA ON ED_AREA.OFF_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 4),'00') ");
		sql.append(" LEFT JOIN EXCISE_SUBDEPT ED_SUBDEPT ON PLAN_DTL.AU_SUBDEPT_CODE = ED_SUBDEPT.SUBDEPT_CODE " );
		sql.append(" LEFT JOIN EXCISE_PERSON ED_PERSON ON PLAN_DTL.CREATED_BY = ED_PERSON.ED_LOGIN ");
		sql.append(" WHERE PLAN_DTL.IS_DELETED = 'N' ");
		sql.append("   AND R4000.IS_DELETED = 'N' ");
		sql.append("   AND PLAN_DTL.PLAN_NUMBER = ? ");

		params.add(formVo.getPlanNumber());
		if (StringUtils.isNotBlank(formVo.getOfficeCode())) {
			sql.append("   AND PLAN_DTL.OFFICE_CODE LIKE ? ");
			params.add(formVo.getOfficeCode());
		}
		
		if (StringUtils.isNotEmpty(formVo.getSubdeptCode())) {
			sql.append("   AND PLAN_DTL.AU_SUBDEPT_CODE = ? ");
			params.add(formVo.getSubdeptCode());
		}
		
		if (StringUtils.isNotEmpty(formVo.getUserLoginId())) {
			sql.append("   AND PLAN_DTL.AU_JOB_RESP LIKE ? ");
			params.add("%" + formVo.getUserLoginId() + "%");
		}
		
		if (StringUtils.isNotEmpty(formVo.getAuditStatus())) {
			sql.append("   AND PLAN_DTL.AUDIT_STATUS >= ? ");
			params.add(formVo.getAuditStatus());
		}
	}
	
	@Override
	public List<PlanWorksheetDatatableVo> findAllByCriteria(PlanWorksheetVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildByCriteriaQuery(sql, params, formVo);

		sql.append(" ORDER BY R4000.DUTY_CODE, R4000.OFFICE_CODE, R4000.NEW_REG_ID ");

		return commonJdbcTemplate.query(sql.toString(), params.toArray(), planDtlDatatableRowMapper);
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
			vo.setAuSubdeptCode(rs.getString("AU_SUBDEPT_CODE"));
			vo.setAuJobResp(rs.getString("AU_JOB_RESP"));
			vo.setPlanWorksheetDtlId(rs.getLong("PLAN_WORKSHEET_DTL_ID"));
			vo.setOfficeCode(rs.getString("OFFICE_CODE"));
//            vo.setDeptShortName(rs.getString("DEPTSHORTNAME"));
			vo.setDeptShortName(ApplicationCache.getExciseDepartment(vo.getOfficeCode()).getDeptShortName());
            vo.setSubdeptShortName(rs.getString("SUBDEPTSHORTNAME"));
            vo.setPersonName(rs.getString("PERSON_NAME"));
			String auditType = "-";
			if (StringUtils.isNotEmpty(rs.getString("AUDIT_TYPE"))) {
				auditType = ApplicationCache.getParamInfoByCode(PARAM_GROUP.TA_AUDIT_TYPE, rs.getString("AUDIT_TYPE")).getValue1();
			}
			vo.setAuditType(auditType);
			String auditDate = "-";
			String start = ConvertDateUtils.formatDateToString(rs.getDate("AUDIT_START_DATE"), ConvertDateUtils.DD_MM_YYYY);
			String end = ConvertDateUtils.formatDateToString(rs.getDate("AUDIT_END_DATE"), ConvertDateUtils.DD_MM_YYYY);
			if (StringUtils.isNotEmpty(start) && StringUtils.isNotEmpty(end)) {
				auditDate = start + " - " +end;
			}
			vo.setAuditDate(auditDate);
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
		sql.append("     ED_AREA.OFF_SHORT_NAME AREA_DESC, ");
		sql.append("     ED_AREA.OFF_NAME DEPTSHORTNAME, ");
		sql.append(" 	 ED_SUBDEPT.SUBDEPT_SHORT_NAME SUBDEPTSHORTNAME	");
		sql.append(" FROM TA_PLAN_WORKSHEET_DTL PLAN_DTL ");
		sql.append(" INNER JOIN TA_WS_REG4000 R4000 ");
		sql.append(" ON R4000.NEW_REG_ID = PLAN_DTL.NEW_REG_ID ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_SECTOR "); 
		sql.append(" ON ED_SECTOR.OFF_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 2),'0000') ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_AREA "); 
		sql.append(" ON ED_AREA.OFF_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 4),'00') ");
		sql.append(" LEFT JOIN EXCISE_DEPARTMENT  ED_DEPT ON ED_AREA.OFF_CODE = PLAN_DTL.OFFICE_CODE " );
		sql.append(" LEFT JOIN EXCISE_SUBDEPT ED_SUBDEPT ON PLAN_DTL.AU_SUBDEPT_CODE = ED_SUBDEPT.SUBDEPT_CODE " );
		sql.append(" WHERE PLAN_DTL.IS_DELETED = ? ");  
		sql.append(" AND PLAN_DTL.OFFICE_CODE = ? ");
        
        params.add(FLAG.N_FLAG);
        params.add(UserLoginUtils.getCurrentUserBean().getOfficeCode());

        // AUDIT_TYPE
        sql.append(" AND AUDIT_TYPE IN (");
        List<AuditCalendarCheckboxVo> auditType = formVo.getAuditType().stream()
        		.filter(e -> e.getCheckbox().equals(true))
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
        		.filter(e -> e.getCheckbox().equals(true))
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
            vo.setAuditStartDate(ConvertDateUtils.formatDateToString(rs.getDate("AUDIT_START_DATE"), "yyyy-MM-dd", ConvertDateUtils.LOCAL_TH));
            vo.setAuditEndDate(ConvertDateUtils.formatDateToString(rs.getDate("AUDIT_END_DATE"), "yyyy-MM-dd", ConvertDateUtils.LOCAL_TH));
            vo.setCusFullName(rs.getString("CUS_FULLNAME"));
            vo.setFacFullName(rs.getString("FAC_FULLNAME"));
            vo.setOfficeCodeR4000(rs.getString("OFFICE_CODE_R4000"));
            vo.setDutyCode(rs.getString("DUTY_CODE"));
            vo.setSecCode(rs.getString("SEC_CODE"));
            vo.setSecDesc(rs.getString("SEC_DESC"));
            vo.setAreaCode(rs.getString("AREA_CODE"));
            vo.setAreaDesc(rs.getString("AREA_DESC"));
            vo.setTitle(rs.getString("AREA_DESC"));
//            vo.setDeptShortName(rs.getString("DEPTSHORTNAME"));
//            vo.setSubdeptShortName(rs.getString("SUBDEPTSHORTNAME"));
            return vo;
        }
    };

	@Override
	public void updateStatusPlanWorksheetDtl(ExcisePerson formVo,String status) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" UPDATE TA_PLAN_WORKSHEET_DTL SET AUDIT_STATUS = ? , ");
		if (ProjectConstants.TA_AUDIT_STATUS.CODE_0301.equals(status)) {
			sql.append("  RECEIVED_BY = ? , RECEIVED_DATE = ?  ");
			
		}else if (ProjectConstants.TA_AUDIT_STATUS.CODE_0400.equals(status)) {
			sql.append("  ASSIGNED_SUBDEPT_BY = ? , ASSIGNED_SUBDEPT_DATE = ?  ");
		}
		
		sql.append(" WHERE OFFICE_CODE = ? ");
		
		params.add(status);
		params.add(formVo.getEdLogin());
		params.add(new Date());
		params.add(formVo.getEdOffcode());
//		if (StringUtils.isNotBlank(formVo.getAuSubdeptCode())) {
//			sql.append(" AND AU_SUBDEPT_CODE = ? ");
//			params.add(formVo.getAuSubdeptCode());
//		}
		
		commonJdbcTemplate.update(sql.toString(),params.toArray());
		
	}

	@Override
	public List<PlanWorksheetSendTableVo> findPlanWorksheetByDtl(PlanWorksheetVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append( " SELECT COUNT(AUDIT_STATUS) COUNT_PLAN ,DPT.OFF_NAME,DPT.OFF_CODE OFFICE_CODE,SEND.SUBMIT_DATE ");
		sql.append( " ,SEND.SEND_DATE,DTL.AUDIT_STATUS FROM EXCISE_DEPARTMENT DPT ");
		sql.append( " LEFT JOIN TA_PLAN_WORKSHEET_DTL DTL ON DTL.OFFICE_CODE = DPT.OFF_CODE " );
		sql.append( " LEFT JOIN TA_PLAN_WORKSHEET_SEND SEND ON SEND.OFFICE_CODE = DPT.OFF_CODE AND SEND.BUDGET_YEAR =  ?  " );
		sql.append( " WHERE DPT.OFF_CODE LIKE ?  " );
		sql.append( " GROUP BY DPT.OFF_CODE,DPT.OFF_NAME,SEND.SUBMIT_DATE,SEND.SEND_DATE,DTL.AUDIT_STATUS " );
		sql.append( " ORDER BY DPT.OFF_CODE ASC ");
		params.add(formVo.getBudgetYear());
		params.add(formVo.getOfficeCode());

		
		return commonJdbcTemplate.query(sql.toString(), params.toArray(), taPlanWorksheetDtlSendRowMapper);
	}
	
	private static final RowMapper<PlanWorksheetSendTableVo> taPlanWorksheetDtlSendRowMapper = new RowMapper<PlanWorksheetSendTableVo>() {
        public PlanWorksheetSendTableVo mapRow(ResultSet rs, int rowNum) throws SQLException {
        	PlanWorksheetSendTableVo vo = new PlanWorksheetSendTableVo();
        	vo.setCountPlan(rs.getInt("COUNT_PLAN"));
        	vo.setOfficeName(rs.getString("OFF_NAME"));
        	vo.setOfficeCode(rs.getString("OFFICE_CODE"));
        	vo.setSendDate(rs.getDate("SEND_DATE"));
        	vo.setSubmitDate(rs.getDate("SUBMIT_DATE"));
        	vo.setAuditStatus(rs.getString("AUDIT_STATUS"));
//            vo.setAuditStartDate(ConvertDateUtils.formatDateToString(rs.getDate("AUDIT_START_DATE"), "yyyy-MM-dd", ConvertDateUtils.LOCAL_TH));
//            vo.setAuditEndDate(ConvertDateUtils.formatDateToString(rs.getDate("AUDIT_END_DATE"), "yyyy-MM-dd", ConvertDateUtils.LOCAL_TH));
            return vo;
        }
    };

	@Override
	public void updateStatusPlanWorksheetDtlByList(PersonAssignForm formVo, String status) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append(" UPDATE TA_PLAN_WORKSHEET_DTL SET AUDIT_STATUS = ? , ");
		sql.append( " AU_SUBDEPT_CODE = ? ,ASSIGNED_OFFICER_BY = ? ,ASSIGNED_OFFICER_DATE = ? ");
		sql.append(" WHERE PLAN_WORKSHEET_DTL_ID IN  ( ");
		
		for (int i = 0; i < formVo.getListCompany().size() ; i++) {
			if (i == formVo.getListCompany().size() - 1) {
				sql.append(" ? ");
			}else {
				sql.append(" ?, ");
			}
			
		}
		sql.append( " ) "); 
		
		params.add(ProjectConstants.TA_AUDIT_STATUS.CODE_0400);
		params.add(formVo.getAuSubdeptCode());
		params.add(UserLoginUtils.getCurrentUserBean().getUsername());
		params.add(new Date());
		
		for (int i = 0; i < formVo.getListCompany().size(); i++) {
			params.add(formVo.getListCompany().get(i).getPlanWorksheetDtlId());
		}
		commonJdbcTemplate.update(sql.toString(),params.toArray());
		
	}
	
	

    
}
