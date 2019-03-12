package th.go.excise.ims.ta.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateConverter;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaDraftWorksheetDtl;
import th.go.excise.ims.ta.util.TaxAuditUtils;
import th.go.excise.ims.ta.vo.TaxDratfVo;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

public class TaDraftWorksheetDtlRepositoryImpl implements TaDraftWorksheetDtlRepositoryCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<TaDraftWorksheetDtl> draftWorksheetDtlList) {
		String sql = SqlGeneratorUtils.genSqlInsert(
			"TA_DRAFT_WORKSHEET_DTL",
			Arrays.asList(
				"DRAFT_WORKSHEET_DTL_ID",
				"DRAFT_NUMBER", "NEW_REG_ID", "SUM_TAX_AMT_G1", "SUM_TAX_AMT_G2", "TAX_AMT_CHN_PNT", "TAX_MONTH_NO",
				"TAX_AUDIT_LAST3", "TAX_AUDIT_LAST2", "TAX_AUDIT_LAST1", "TAX_AMT_SD", "TAX_AMT_MEAN", "TAX_AMT_MAX_PNT", "TAX_AMT_MIN_PNT",
				"TAX_AMT_G1_M1", "TAX_AMT_G1_M2", "TAX_AMT_G1_M3", "TAX_AMT_G1_M4", "TAX_AMT_G1_M5", "TAX_AMT_G1_M6",
				"TAX_AMT_G1_M7", "TAX_AMT_G1_M8", "TAX_AMT_G1_M9", "TAX_AMT_G1_M10", "TAX_AMT_G1_M11", "TAX_AMT_G1_M12",
				"TAX_AMT_G2_M1", "TAX_AMT_G2_M2", "TAX_AMT_G2_M3", "TAX_AMT_G2_M4", "TAX_AMT_G2_M5", "TAX_AMT_G2_M6",
				"TAX_AMT_G2_M7", "TAX_AMT_G2_M8", "TAX_AMT_G2_M9", "TAX_AMT_G2_M10", "TAX_AMT_G2_M11", "TAX_AMT_G2_M12",
				"CREATED_BY", "CREATED_DATE"
			),
			"TA_DRAFT_WORKSHEET_DTL_SEQ");
		
		commonJdbcTemplate.batchUpdate(sql, draftWorksheetDtlList, 1000, new ParameterizedPreparedStatementSetter<TaDraftWorksheetDtl>() {
			public void setValues(PreparedStatement ps, TaDraftWorksheetDtl draftWorksheetDtl) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				paramList.add(draftWorksheetDtl.getDraftNumber());
				paramList.add(draftWorksheetDtl.getNewRegId());
				paramList.add(draftWorksheetDtl.getSumTaxAmtG1());
				paramList.add(draftWorksheetDtl.getSumTaxAmtG2());
				paramList.add(draftWorksheetDtl.getTaxAmtChnPnt());
				paramList.add(draftWorksheetDtl.getTaxMonthNo());
				paramList.add(draftWorksheetDtl.getTaxAuditLast3());
				paramList.add(draftWorksheetDtl.getTaxAuditLast2());
				paramList.add(draftWorksheetDtl.getTaxAuditLast1());
				paramList.add(draftWorksheetDtl.getTaxAmtSd());
				paramList.add(draftWorksheetDtl.getTaxAmtMean());
				paramList.add(draftWorksheetDtl.getTaxAmtMaxPnt());
				paramList.add(draftWorksheetDtl.getTaxAmtMinPnt());
				paramList.add(draftWorksheetDtl.getTaxAmtG1M1());
				paramList.add(draftWorksheetDtl.getTaxAmtG1M2());
				paramList.add(draftWorksheetDtl.getTaxAmtG1M3());
				paramList.add(draftWorksheetDtl.getTaxAmtG1M4());
				paramList.add(draftWorksheetDtl.getTaxAmtG1M5());
				paramList.add(draftWorksheetDtl.getTaxAmtG1M6());
				paramList.add(draftWorksheetDtl.getTaxAmtG1M7());
				paramList.add(draftWorksheetDtl.getTaxAmtG1M8());
				paramList.add(draftWorksheetDtl.getTaxAmtG1M9());
				paramList.add(draftWorksheetDtl.getTaxAmtG1M10());
				paramList.add(draftWorksheetDtl.getTaxAmtG1M11());
				paramList.add(draftWorksheetDtl.getTaxAmtG1M12());
				paramList.add(draftWorksheetDtl.getTaxAmtG2M1());
				paramList.add(draftWorksheetDtl.getTaxAmtG2M2());
				paramList.add(draftWorksheetDtl.getTaxAmtG2M3());
				paramList.add(draftWorksheetDtl.getTaxAmtG2M4());
				paramList.add(draftWorksheetDtl.getTaxAmtG2M5());
				paramList.add(draftWorksheetDtl.getTaxAmtG2M6());
				paramList.add(draftWorksheetDtl.getTaxAmtG2M7());
				paramList.add(draftWorksheetDtl.getTaxAmtG2M8());
				paramList.add(draftWorksheetDtl.getTaxAmtG2M9());
				paramList.add(draftWorksheetDtl.getTaxAmtG2M10());
				paramList.add(draftWorksheetDtl.getTaxAmtG2M11());
				paramList.add(draftWorksheetDtl.getTaxAmtG2M12());
				paramList.add(draftWorksheetDtl.getCreatedBy());
				paramList.add(draftWorksheetDtl.getCreatedDate());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}
	
	private void buildByCriteriaQuery(StringBuilder sql, List<Object> params, TaxOperatorFormVo formVo) {
		sql.append(" SELECT R4000.CUS_FULLNAME ");
		sql.append("   ,R4000.FAC_FULLNAME ");
		sql.append("   ,R4000.FAC_ADDRESS ");
		sql.append("   ,R4000.DUTY_CODE ");
		sql.append("   ,R4000.OFFICE_CODE OFFICE_CODE_R4000 ");
		sql.append("   ,ED_SECTOR.OFF_CODE SEC_CODE ");
		sql.append("   ,ED_SECTOR.OFF_SHORT_NAME SEC_DESC ");
		sql.append("   ,ED_AREA.OFF_CODE AREA_CODE ");
		sql.append("   ,ED_AREA.OFF_SHORT_NAME AREA_DESC, ");
		sql.append(" 	R4000.REG_CAPITAL, ");
		sql.append(" 	R4000.REG_DATE, ");
		sql.append(" 	R4000.REG_STATUS, ");
		sql.append("	TA_W_HDR.* ");
		sql.append(" FROM TA_DRAFT_WORKSHEET_DTL TA_W_HDR ");
		sql.append(" INNER JOIN TA_WS_REG4000 R4000 ON R4000.NEW_REG_ID = TA_W_HDR.NEW_REG_ID ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_SECTOR ON ED_SECTOR.OFF_CODE = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 2),'0000') ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_AREA ON ED_AREA.OFF_CODE    = CONCAT(SUBSTR(R4000.OFFICE_CODE, 0, 4),'00') ");
		sql.append(" WHERE TA_W_HDR.IS_DELETED = 'N' ");
		sql.append("   AND R4000.IS_DELETED = 'N' ");
		sql.append("   AND TA_W_HDR.DRAFT_NUMBER = ? ");
		
		params.add(formVo.getDraftNumber());
		
		if (StringUtils.isNotBlank(formVo.getOfficeCode())) {
			sql.append(" AND R4000.OFFICE_CODE like ? ");
			params.add(ExciseUtils.whereInLocalOfficeCode(formVo.getOfficeCode()));
		}
		if (StringUtils.isNotBlank(formVo.getFacType())) {
			sql.append(" AND R4000.FAC_TYPE = ? ");
			params.add(formVo.getFacType());
		}
		if (StringUtils.isNotBlank(formVo.getDutyCode())) {
			sql.append(" AND R4000.DUTY_CODE = ? ");
			params.add(formVo.getDutyCode());
		}
	}
	
	public List<TaxOperatorDetailVo> findByCriteria(TaxOperatorFormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildByCriteriaQuery(sql, params, formVo);
		
		sql.append(" ORDER BY R4000.DUTY_CODE, R4000.OFFICE_CODE, R4000.NEW_REG_ID ");
		
		return commonJdbcTemplate.query(OracleUtils.limitForDatable(sql.toString(), formVo.getStart(), formVo.getLength()), params.toArray(), draftWorksheetRowMapper);
	}
	
	public Long countByCriteria(TaxOperatorFormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildByCriteriaQuery(sql, params, formVo);
		
		return commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()), params.toArray(), Long.class);
	}
	
	private static final RowMapper<TaxOperatorDetailVo> draftWorksheetRowMapper = new RowMapper<TaxOperatorDetailVo>() {
		@Override
		public TaxOperatorDetailVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			TaxOperatorDetailVo vo = new TaxOperatorDetailVo();
			TaxAuditUtils.commonSelectionWorksheetRowMapper(vo, rs);
			vo.setDraftNumber(rs.getString("DRAFT_NUMBER"));
			vo.setRegCapital(rs.getString("REG_CAPITAL"));
			vo.setRegStatus(rs.getString("REG_STATUS")+" "+ ConvertDateUtils.formatDateToString(rs.getDate("REG_DATE"), ConvertDateUtils.DD_MM_YY, ConvertDateUtils.LOCAL_TH));
			return vo;
		}
	};
	
	@Override
	public List<TaxDratfVo> findByDraftNumber(String draftNumber) {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT T.*, D.TAX_AMT_CHN_PNT, D.TAX_MONTH_NO ");
		sql.append(" FROM TA_DRAFT_WORKSHEET_DTL D ");
		sql.append(" INNER JOIN TA_WS_REG4000 T ON T.NEW_REG_ID = D.NEW_REG_ID ");
		sql.append(" WHERE T.IS_DELETED = 'N' ");
		sql.append("   AND D.IS_DELETED = 'N' ");
		sql.append("   AND D.DRAFT_NUMBER = ? ");
		paramList.add(draftNumber);
		return this.commonJdbcTemplate.query(sql.toString(), paramList.toArray(), taxDraftVoRowMapper);
	}
	
	private RowMapper<TaxDratfVo> taxDraftVoRowMapper = new RowMapper<TaxDratfVo>() {
		@Override
		public TaxDratfVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			TaxDratfVo vo = new TaxDratfVo();
			vo.setNewRegId(rs.getString("NEW_REG_ID"));
			vo.setFacType(rs.getString("FAC_TYPE"));
			vo.setRegDate(LocalDateConverter.convertToEntityAttribute(rs.getDate("REG_DATE")));
			vo.setOfficeCode(rs.getString("OFFICE_CODE"));
			vo.setTaxAmtChnPnt(rs.getBigDecimal("TAX_AMT_CHN_PNT"));
			vo.setTaxMonthNo(rs.getInt("TAX_MONTH_NO"));
			vo.setRegCapital(rs.getString("REG_CAPITAL"));
			vo.setDutyCode(rs.getString("DUTY_CODE"));
			return vo;
		}
	};
	
}
