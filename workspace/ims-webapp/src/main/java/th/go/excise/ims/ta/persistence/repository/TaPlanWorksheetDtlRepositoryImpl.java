package th.go.excise.ims.ta.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;

public class TaPlanWorksheetDtlRepositoryImpl implements TaPlanWorksheetDtlRepositoryCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void deletePlanWorkSheet(String analysisNumber, String planNumber, String officeCode, String newRegId, String createdBy) {
		StringBuilder sql = new StringBuilder();
		sql.append(" DELETE TA_PLAN_WORKSHEET_DTL ");
		sql.append(" WHERE PLAN_NUMBER = ? ");
		sql.append(" AND ANALYSIS_NUMBER = ? ");
		sql.append(" AND OFFICE_CODE = ? ");
		sql.append(" AND NEW_REG_ID = ? ");
		sql.append(" AND CREATED_BY = ? ");

		List<Object> params = new ArrayList<>();
		params.add(planNumber);
		params.add(analysisNumber);
		params.add(officeCode);
		params.add(newRegId);
		params.add(createdBy);
		commonJdbcTemplate.update(sql.toString(), params.toArray());
	}

	private StringBuilder sqlPlanDtlDatatable() {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT R4000.CUS_FULLNAME, ");
		sql.append("   R4000.FAC_FULLNAME, ");
		sql.append("   R4000.FAC_ADDRESS, ");
		sql.append("   PLAN_DTL.PLAN_NUMBER, ");
		sql.append("   R4000.OFFICE_CODE OFFICE_CODE_R4000, ");
		sql.append("   R4000.DUTY_CODE, ");
		sql.append("   ED_SECTOR.OFFICE_CODE SEC_CODE, ");
		sql.append("   ED_SECTOR.DEPT_SHORT_NAME SEC_DESC, ");
		sql.append("   ED_AREA.OFFICE_CODE AREA_CODE, ");
		sql.append("   ED_AREA.DEPT_SHORT_NAME AREA_DESC, ");
		sql.append("   TA_W_HDR.* ");
		sql.append(" FROM TA_WORKSHEET_DTL TA_W_HDR ");
		sql.append(" INNER JOIN TA_PLAN_WORKSHEET_DTL PLAN_DTL ");
		sql.append(" ON PLAN_DTL.ANALYSIS_NUMBER=TA_W_HDR.ANALYSIS_NUMBER AND PLAN_DTL.NEW_REG_ID=TA_W_HDR.NEW_REG_ID ");
		sql.append(" INNER JOIN TA_WS_REG4000 R4000 ");
		sql.append(" ON R4000.NEW_REG_ID = TA_W_HDR.NEW_REG_ID ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_SECTOR ");
		sql.append(" ON ED_SECTOR.OFFICE_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 2),'0000') ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_AREA ");
		sql.append(" ON ED_AREA.OFFICE_CODE    = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 4),'00') ");
		sql.append(" WHERE TA_W_HDR.IS_DELETED = 'N' ");
		sql.append(" AND R4000.IS_DELETED      = 'N' ");
		sql.append(" AND PLAN_DTL.IS_DELETED = 'N' ");
		sql.append(" AND PLAN_DTL.OFFICE_CODE=? ");
		sql.append(" AND PLAN_DTL.PLAN_NUMBER=? ");

		return sql;
	}

	@Override
	public Long countPlanDtlDatatable(PlanWorksheetVo formVo) {
		StringBuilder sql = new StringBuilder();
		sql = this.sqlPlanDtlDatatable();

		List<Object> params = new ArrayList<>();
		params.add(UserLoginUtils.getCurrentUserBean().getOfficeCode());
		params.add(formVo.getPlanNumber());

		String count = OracleUtils.countForDataTable(sql.toString());
		return commonJdbcTemplate.queryForObject(count, params.toArray(), Long.class);
	}

	@Override
	public List<PlanWorksheetDatatableVo> planDtlDatatable(PlanWorksheetVo formVo) {
		StringBuilder sql = new StringBuilder();
		sql = this.sqlPlanDtlDatatable();

		List<Object> params = new ArrayList<>();
		params.add(UserLoginUtils.getCurrentUserBean().getOfficeCode());
		params.add(formVo.getPlanNumber());

		String limit = OracleUtils.limitForDatable(sql.toString(), formVo.getStart(), formVo.getLength());
		List<PlanWorksheetDatatableVo> list = commonJdbcTemplate.query(limit, params.toArray(), planDtlDatatableRowMapper);
		return list;
	}

	protected RowMapper<PlanWorksheetDatatableVo> planDtlDatatableRowMapper = new RowMapper<PlanWorksheetDatatableVo>() {
		// private DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

		@Override
		public PlanWorksheetDatatableVo mapRow(ResultSet rs, int arg1) throws SQLException {
			PlanWorksheetDatatableVo vo = new PlanWorksheetDatatableVo();

			vo.setCusFullname(rs.getString("CUS_FULLNAME"));
			vo.setFacFullname(rs.getString("FAC_FULLNAME"));
			vo.setFacAddress(rs.getString("FAC_ADDRESS"));
			vo.setOfficeCodeR4000(rs.getString("OFFICE_CODE_R4000"));
			vo.setDutyDesc(ExciseUtils.getDutyDesc(rs.getString("DUTY_CODE")));
			vo.setSecCode(rs.getString("SEC_CODE"));
			vo.setSecDesc(rs.getString("SEC_DESC"));
			vo.setAreaCode(rs.getString("AREA_CODE"));
			vo.setAreaDesc(rs.getString("AREA_DESC"));
			vo.setPlanNumber(rs.getString("PLAN_NUMBER"));
			vo.setAnalysisNumber(rs.getString("ANALYSIS_NUMBER"));
			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			vo.setCondMainGrp(rs.getString("COND_MAIN_GRP"));			

			return vo;
		}
	};

}
