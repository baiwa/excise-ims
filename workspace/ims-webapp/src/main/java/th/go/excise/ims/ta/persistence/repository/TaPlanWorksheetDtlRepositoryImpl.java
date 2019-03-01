package th.go.excise.ims.ta.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.vo.PlanWorksheetDatatableVo;
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
		sql.append("   ,ED_SECTOR.OFFICE_CODE SEC_CODE ");
		sql.append("   ,ED_SECTOR.DEPT_SHORT_NAME SEC_DESC ");
		sql.append("   ,ED_AREA.OFFICE_CODE AREA_CODE ");
		sql.append("   ,ED_AREA.DEPT_SHORT_NAME AREA_DESC ");
		sql.append("   ,PLAN_DTL.* ");
		sql.append(" FROM TA_PLAN_WORKSHEET_DTL PLAN_DTL ");
		sql.append(" INNER JOIN TA_WS_REG4000 R4000 ON R4000.NEW_REG_ID = PLAN_DTL.NEW_REG_ID ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_SECTOR ON ED_SECTOR.OFFICE_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 2),'0000') ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_AREA ON ED_AREA.OFFICE_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 4),'00') ");
		sql.append(" WHERE PLAN_DTL.IS_DELETED = 'N' ");
		sql.append("   AND R4000.IS_DELETED = 'N' ");
		sql.append("   AND PLAN_DTL.PLAN_NUMBER = ? ");

        params.add(formVo.getPlanNumber());
        if (StringUtils.isNotBlank(formVo.getOfficeCode())){
            sql.append("   AND PLAN_DTL.OFFICE_CODE = ? ");
            params.add(formVo.getOfficeCode());
        }
	}

	@Override
	public List<PlanWorksheetDatatableVo> findByCriteria(PlanWorksheetVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildByCriteriaQuery(sql, params, formVo);
		
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
			vo.setDutyDesc(ExciseUtils.getDutyDesc(rs.getString("DUTY_CODE")));
			vo.setSecCode(rs.getString("SEC_CODE"));
			vo.setSecDesc(rs.getString("SEC_DESC"));
			vo.setAreaCode(rs.getString("AREA_CODE"));
			vo.setAreaDesc(rs.getString("AREA_DESC"));
			vo.setPlanNumber(rs.getString("PLAN_NUMBER"));
			vo.setAnalysisNumber(rs.getString("ANALYSIS_NUMBER"));
			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			return vo;
		}
	};

}
