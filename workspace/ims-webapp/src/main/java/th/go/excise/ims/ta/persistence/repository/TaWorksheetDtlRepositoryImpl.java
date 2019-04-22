package th.go.excise.ims.ta.persistence.repository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import th.co.baiwa.buckwaframework.common.bean.LabelValueBean;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateConverter;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAM_GROUP;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.TA_CONFIG;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetDtl;
import th.go.excise.ims.ta.util.TaxAuditUtils;
import th.go.excise.ims.ta.vo.TaxDraftVo;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TaWorksheetDtlRepositoryImpl implements TaWorksheetDtlRepositoryCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void batchInsert(List<TaWorksheetDtl> worksheetDtlList) {
		String sql = SqlGeneratorUtils.genSqlInsert("TA_WORKSHEET_DTL",
				Arrays.asList("WORKSHEET_DTL_ID", "ANALYSIS_NUMBER", "NEW_REG_ID", "SUM_TAX_AMT_G1", "SUM_TAX_AMT_G2",
						"TAX_AMT_CHN_PNT", "TAX_MONTH_NO", "TAX_AUDIT_LAST3", "TAX_AUDIT_LAST2", "TAX_AUDIT_LAST1",
						"TAX_AMT_SD", "TAX_AMT_MEAN", "TAX_AMT_MAX_PNT", "TAX_AMT_MIN_PNT", "TAX_AMT_G1_M1",
						"TAX_AMT_G1_M2", "TAX_AMT_G1_M3", "TAX_AMT_G1_M4", "TAX_AMT_G1_M5", "TAX_AMT_G1_M6",
						"TAX_AMT_G1_M7", "TAX_AMT_G1_M8", "TAX_AMT_G1_M9", "TAX_AMT_G1_M10", "TAX_AMT_G1_M11",
						"TAX_AMT_G1_M12", "TAX_AMT_G2_M1", "TAX_AMT_G2_M2", "TAX_AMT_G2_M3", "TAX_AMT_G2_M4",
						"TAX_AMT_G2_M5", "TAX_AMT_G2_M6", "TAX_AMT_G2_M7", "TAX_AMT_G2_M8", "TAX_AMT_G2_M9",
						"TAX_AMT_G2_M10", "TAX_AMT_G2_M11", "TAX_AMT_G2_M12", "COND_MAIN_GRP", "COND_SUB_CAPITAL",
						"COND_SUB_RISK", "CREATED_BY", "CREATED_DATE"),
				"TA_WORKSHEET_DTL_SEQ");

		commonJdbcTemplate.batchUpdate(sql, worksheetDtlList, 1000,
				new ParameterizedPreparedStatementSetter<TaWorksheetDtl>() {
					public void setValues(PreparedStatement ps, TaWorksheetDtl worksheetDtl) throws SQLException {
						List<Object> paramList = new ArrayList<Object>();
						paramList.add(worksheetDtl.getAnalysisNumber());
						paramList.add(worksheetDtl.getNewRegId());

						paramList.add(worksheetDtl.getSumTaxAmtG1());
						paramList.add(worksheetDtl.getSumTaxAmtG2());
						paramList.add(worksheetDtl.getTaxAmtChnPnt());
						paramList.add(worksheetDtl.getTaxMonthNo());
						paramList.add(worksheetDtl.getTaxAuditLast3());
						paramList.add(worksheetDtl.getTaxAuditLast2());
						paramList.add(worksheetDtl.getTaxAuditLast1());
						paramList.add(worksheetDtl.getTaxAmtSd());
						paramList.add(worksheetDtl.getTaxAmtMean());
						paramList.add(worksheetDtl.getTaxAmtMaxPnt());
						paramList.add(worksheetDtl.getTaxAmtMinPnt());
						paramList.add(worksheetDtl.getTaxAmtG1M1());
						paramList.add(worksheetDtl.getTaxAmtG1M2());
						paramList.add(worksheetDtl.getTaxAmtG1M3());
						paramList.add(worksheetDtl.getTaxAmtG1M4());
						paramList.add(worksheetDtl.getTaxAmtG1M5());
						paramList.add(worksheetDtl.getTaxAmtG1M6());
						paramList.add(worksheetDtl.getTaxAmtG1M7());
						paramList.add(worksheetDtl.getTaxAmtG1M8());
						paramList.add(worksheetDtl.getTaxAmtG1M9());
						paramList.add(worksheetDtl.getTaxAmtG1M10());
						paramList.add(worksheetDtl.getTaxAmtG1M11());
						paramList.add(worksheetDtl.getTaxAmtG1M12());
						paramList.add(worksheetDtl.getTaxAmtG2M1());
						paramList.add(worksheetDtl.getTaxAmtG2M2());
						paramList.add(worksheetDtl.getTaxAmtG2M3());
						paramList.add(worksheetDtl.getTaxAmtG2M4());
						paramList.add(worksheetDtl.getTaxAmtG2M5());
						paramList.add(worksheetDtl.getTaxAmtG2M6());
						paramList.add(worksheetDtl.getTaxAmtG2M7());
						paramList.add(worksheetDtl.getTaxAmtG2M8());
						paramList.add(worksheetDtl.getTaxAmtG2M9());
						paramList.add(worksheetDtl.getTaxAmtG2M10());
						paramList.add(worksheetDtl.getTaxAmtG2M11());
						paramList.add(worksheetDtl.getTaxAmtG2M12());

						paramList.add(worksheetDtl.getCondMainGrp());
						paramList.add(worksheetDtl.getCondSubCapital());
						paramList.add(worksheetDtl.getCondSubRisk());
						paramList.add(worksheetDtl.getCreatedBy());
						paramList.add(worksheetDtl.getCreatedDate());
						commonJdbcTemplate.preparePs(ps, paramList.toArray());
					}
				});
	}

	@Override
	public void batchUpdate(List<TaWorksheetDtl> taWorksheetDtlList) {
		String sql = SqlGeneratorUtils.genSqlUpdate("TA_WORKSHEET_DTL",
				Arrays.asList("COND_MAIN_GRP", "COND_SUB_CAPITAL", "COND_SUB_RISK", "COND_SUB_NO_AUDIT"),
				Arrays.asList("ANALYSIS_NUMBER", "NEW_REG_ID"));

		commonJdbcTemplate.batchUpdate(sql, taWorksheetDtlList, 1000,
				new ParameterizedPreparedStatementSetter<TaWorksheetDtl>() {
					public void setValues(PreparedStatement ps, TaWorksheetDtl worksheetDtl) throws SQLException {
						List<Object> paramList = new ArrayList<>();

						paramList.add(worksheetDtl.getCondMainGrp());
						paramList.add(worksheetDtl.getCondSubCapital());
						paramList.add(worksheetDtl.getCondSubRisk());
						paramList.add(worksheetDtl.getCondSubNoAudit());

						paramList.add(worksheetDtl.getAnalysisNumber());
						paramList.add(worksheetDtl.getNewRegId());
						commonJdbcTemplate.preparePs(ps, paramList.toArray());
					}
				});
	}

	private void buildByCriteriaQuery(StringBuilder sql, List<Object> params, TaxOperatorFormVo formVo) {
		sql.append(" SELECT R4000.CUS_FULLNAME , ");
		sql.append("   R4000.FAC_FULLNAME , ");
		sql.append("   R4000.FAC_ADDRESS , ");
		sql.append("   R4000.DUTY_CODE , ");
		sql.append("   R4000.OFFICE_CODE OFFICE_CODE_R4000 , ");
		sql.append("   ED_SECTOR.OFFICE_CODE SEC_CODE , ");
		sql.append("   ED_SECTOR.OFF_SHORT_NAME SEC_DESC , ");
		sql.append("   ED_AREA.OFFICE_CODE AREA_CODE , ");
		sql.append("   ED_AREA.OFF_SHORT_NAME AREA_DESC , ");
		sql.append("   TA_PW_SEL.CENTRAL_SEL_FLAG , ");
		sql.append("   TA_PW_SEL.CENTRAL_SEL_OFFICE_CODE , ");
		sql.append("   TA_PW_SEL.CENTRAL_SEL_DATE , ");
		sql.append("   TA_PW_SEL.SECTOR_SEL_FLAG , ");
		sql.append("   TA_PW_SEL.SECTOR_SEL_OFFICE_CODE , ");
		sql.append("   TA_PW_SEL.SECTOR_SEL_DATE , ");
		sql.append("   TA_PW_SEL.AREA_SEL_FLAG , ");
		sql.append("   TA_PW_SEL.AREA_SEL_OFFICE_CODE , ");
		sql.append("   TA_PW_SEL.AREA_SEL_DATE, ");
		sql.append("   R4000.REG_STATUS, ");
		sql.append("   R4000.REG_DATE, ");
		sql.append("   R4000.REG_CAPITAL , ");
		sql.append("   TA_W_DTL.* ");
		sql.append(" FROM TA_WORKSHEET_DTL TA_W_DTL ");
		sql.append(" INNER JOIN TA_WORKSHEET_HDR TA_W_HDR ON TA_W_DTL.ANALYSIS_NUMBER = TA_W_HDR.ANALYSIS_NUMBER ");
		sql.append("   AND TA_W_DTL.IS_DELETED = 'N' ");
		sql.append(" INNER JOIN TA_WS_REG4000 R4000 ON R4000.NEW_REG_ID = TA_W_DTL.NEW_REG_ID ");
		sql.append("   AND R4000.IS_DELETED = 'N' ");
		sql.append(" INNER JOIN ( ");
		sql.append("   SELECT OFF_CODE OFFICE_CODE, ");
		sql.append("     OFF_NAME, ");
		sql.append("     OFF_SHORT_NAME ");
		sql.append("   FROM EXCISE_DEPARTMENT ");
		sql.append("   WHERE IS_DELETED = 'N' ");
		sql.append("     AND CONCAT (SUBSTR(OFF_CODE, 0, 2),'0000') = OFF_CODE ");
		sql.append(" ) ED_SECTOR ON ED_SECTOR.OFFICE_CODE = CONCAT (SUBSTR(R4000.OFFICE_CODE, 0, 2),'0000') ");
		sql.append(" INNER JOIN ( ");
		sql.append("   SELECT OFF_CODE OFFICE_CODE, ");
		sql.append("     OFF_NAME, ");
		sql.append("     OFF_SHORT_NAME ");
		sql.append("   FROM EXCISE_DEPARTMENT ");
		sql.append("   WHERE IS_DELETED = 'N' ");
		sql.append("     AND CONCAT (SUBSTR(OFF_CODE, 0, 4),'00') = OFF_CODE ");
		sql.append(" ) ED_AREA ON ED_AREA.OFFICE_CODE = CONCAT (SUBSTR(R4000.OFFICE_CODE, 0, 4),'00') ");
		sql.append(" LEFT JOIN TA_PLAN_WORKSHEET_SELECT TA_PW_SEL ON TA_PW_SEL.NEW_REG_ID = TA_W_DTL.NEW_REG_ID ");
		sql.append("   AND TA_PW_SEL.IS_DELETED = 'N' ");
		sql.append("   AND TA_PW_SEL.BUDGET_YEAR = ? ");
		sql.append(" WHERE TA_W_HDR.IS_DELETED = 'N' ");
		sql.append(" AND TA_W_HDR.ANALYSIS_NUMBER = ? ");

		params.add(formVo.getBudgetYear());
		params.add(formVo.getAnalysisNumber());

		if (StringUtils.isNotBlank(formVo.getCond())) {
			sql.append(" AND TA_W_DTL.COND_MAIN_GRP = ? ");
			params.add(formVo.getCond());
		}

		// TODO Check allow see Factory that selected by other with
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		// TA_CONFIG.SEE_FLAG == "Y|N"
		if (!ExciseUtils.isCentral(officeCode)) {
			ParamInfo paramInfo = ApplicationCache.getParamInfoByCode(PARAM_GROUP.TA_CONFIG, TA_CONFIG.SEE_FLAG);
			if (CommonConstants.FLAG.N_FLAG.equals(paramInfo.getValue1())) {
				if (ExciseUtils.isSector(officeCode)) {
					sql.append(" AND TA_PW_SEL.CENTRAL_SEL_FLAG IS NULL ");
				} else {
					sql.append(" AND TA_PW_SEL.CENTRAL_SEL_FLAG IS NULL AND TA_PW_SEL.SECTOR_SEL_FLAG IS NULL ");
				}
			}
		}

		if (StringUtils.isNotBlank(formVo.getCapital())) {
			sql.append("AND COND_SUB_CAPITAL=? ");
			params.add(formVo.getCapital());
		}
		if (StringUtils.isNotBlank(formVo.getRisk())) {
			sql.append("AND COND_SUB_RISK=? ");
			params.add(formVo.getRisk());
		}
		if (StringUtils.isNotBlank(formVo.getCondSubNoAuditFlag())) {
			sql.append("AND COND_SUB_NO_AUDIT=? ");
			params.add(formVo.getCondSubNoAuditFlag());
		}

		sql.append(" AND R4000.OFFICE_CODE LIKE ? ");
		params.add(ExciseUtils.whereInLocalOfficeCode(officeCode));
	}

	public List<TaxOperatorDetailVo> findByCriteria(TaxOperatorFormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildByCriteriaQuery(sql, params, formVo);

		sql.append(" ORDER BY R4000.DUTY_CODE, R4000.OFFICE_CODE, TA_W_DTL.NEW_REG_ID ");

		return commonJdbcTemplate.query(
				OracleUtils.limitForDatable(sql.toString(), formVo.getStart(), formVo.getLength()), params.toArray(),
				worksheetRowMapper);
	}

	public Long countByCriteria(TaxOperatorFormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildByCriteriaQuery(sql, params, formVo);

		return commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()), params.toArray(),
				Long.class);
	}

	private static final RowMapper<TaxOperatorDetailVo> worksheetRowMapper = new RowMapper<TaxOperatorDetailVo>() {
		@Override
		public TaxOperatorDetailVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			TaxOperatorDetailVo vo = new TaxOperatorDetailVo();
			TaxAuditUtils.commonSelectionWorksheetRowMapper(vo, rs);
			vo.setDraftNumber(rs.getString("ANALYSIS_NUMBER"));
			vo.setCondTaxGrp(rs.getString("COND_MAIN_GRP"));

			vo.setRegCapital(rs.getString("REG_CAPITAL"));
			vo.setRegStatus(rs.getString("REG_STATUS") + " " + ConvertDateUtils
					.formatDateToString(rs.getDate("REG_DATE"), ConvertDateUtils.DD_MM_YY, ConvertDateUtils.LOCAL_TH));

			vo.setCentralSelFlag(rs.getString("CENTRAL_SEL_FLAG"));
			if (FLAG.Y_FLAG.equals(vo.getCentralSelFlag())) {
				vo.setCentralSelDate(""); // FIXME
				vo.setCentralSelOfficeCode(rs.getString("CENTRAL_SEL_OFFICE_CODE"));
				if (StringUtils.isNotBlank(vo.getCentralSelOfficeCode())) {
					vo.setSelectBy(ApplicationCache.getExciseDept(vo.getCentralSelOfficeCode()).getDeptShortName());
				}

			}
			vo.setSectorSelFlag(rs.getString("SECTOR_SEL_FLAG"));
			if (StringUtils.isNotBlank(vo.getSectorSelFlag())) {
				vo.setSectorSelDate("");
				vo.setSectorSelOfficeCode(rs.getString("SECTOR_SEL_OFFICE_CODE"));
				if (StringUtils.isNotBlank(vo.getSectorSelOfficeCode())) {
					vo.setSelectBy(ApplicationCache.getExciseDept(vo.getSectorSelOfficeCode()).getDeptShortName());
				}
			}
			vo.setAreaSelFlag(rs.getString("AREA_SEL_FLAG"));
			if (FLAG.Y_FLAG.equals(vo.getAreaSelFlag())) {
				LocalDate localDate = LocalDateConverter.convertToEntityAttribute(rs.getDate("AREA_SEL_DATE"));
				vo.setAreaSelDate(ConvertDateUtils.formatLocalDateToString(localDate, ConvertDateUtils.DD_MM_YYYY,
						ConvertDateUtils.LOCAL_TH));
				vo.setAreaSelOfficeCode(rs.getString("AREA_SEL_OFFICE_CODE"));
				if (StringUtils.isNotBlank(vo.getAreaSelOfficeCode())) {
					vo.setSelectBy(ApplicationCache.getExciseDept(vo.getAreaSelOfficeCode()).getDeptShortName());
				}
			}

			return vo;
		}
	};

	@Override
	public List<TaxDraftVo> findByAnalysisNumber(String analysisNumber) {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT T.*, D.TAX_AMT_CHN_PNT, D.TAX_MONTH_NO ");
		sql.append(" FROM TA_WORKSHEET_DTL D ");
		sql.append(" INNER JOIN TA_WS_REG4000 T ON T.NEW_REG_ID = D.NEW_REG_ID ");
		sql.append(" WHERE T.IS_DELETED = 'N' ");
		sql.append("   AND D.IS_DELETED = 'N' ");
		sql.append("   AND D.ANALYSIS_NUMBER = ? ");
		paramList.add(analysisNumber);
		return this.commonJdbcTemplate.query(sql.toString(), paramList.toArray(), taxDraftVoRowMapper);
	}

	private RowMapper<TaxDraftVo> taxDraftVoRowMapper = new RowMapper<TaxDraftVo>() {
		@Override
		public TaxDraftVo mapRow(ResultSet rs, int rowNum) throws SQLException {
			TaxDraftVo vo = new TaxDraftVo();
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

	public List<LabelValueBean> groupCondSubCapital(String analysisNumber) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT ");
		sql.append("   CASE ");
		sql.append("     WHEN W_DTL.COND_SUB_CAPITAL IS NULL ");
		sql.append("     THEN '0' ");
		sql.append("     ELSE W_DTL.COND_SUB_CAPITAL ");
		sql.append("   END COND_SUB_CAPITAL, ");
		sql.append("   CASE ");
		sql.append("     WHEN S_INFO.VALUE_1 IS NULL ");
		sql.append("     THEN 'ทั้งหมด' ");
		sql.append("     ELSE S_INFO.VALUE_1 ");
		sql.append("   END COND_DESC ");
		sql.append(" FROM TA_WORKSHEET_DTL W_DTL ");
		sql.append(" LEFT JOIN SYS_PARAMETER_INFO S_INFO ");
		sql.append(" ON W_DTL.COND_SUB_CAPITAL=S_INFO.PARAM_CODE ");
		sql.append(" WHERE S_INFO.PARAM_GROUP_CODE='TA_SUB_COND_CAPITAL'");
		sql.append(" AND W_DTL.ANALYSIS_NUMBER = ? ");
		sql.append(" GROUP BY W_DTL.COND_SUB_CAPITAL, S_INFO.VALUE_1 ");
		sql.append(" ORDER BY W_DTL.COND_SUB_CAPITAL ");

		return commonJdbcTemplate.query(sql.toString(), new Object[] { analysisNumber }, groupCondSubCapitalRowMapper);
	}

	protected RowMapper<LabelValueBean> groupCondSubCapitalRowMapper = new RowMapper<LabelValueBean>() {
		@Override
		public LabelValueBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new LabelValueBean(rs.getString("COND_DESC"), rs.getString("COND_SUB_CAPITAL"));
		}
	};

	public List<LabelValueBean> groupCondSubRisk(String analysisNumber) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT W_DTL.COND_SUB_RISK , ");
		sql.append(" S_INFO.VALUE_1 CON_SUB_RISK_DESC ");
		sql.append(" FROM TA_WORKSHEET_DTL W_DTL ");
		sql.append(" LEFT JOIN SYS_PARAMETER_INFO S_INFO ");
		sql.append(" ON W_DTL.COND_SUB_RISK=S_INFO.PARAM_CODE ");
		sql.append(" WHERE S_INFO.PARAM_GROUP_CODE = 'TA_RISK_LEVEL' ");
		sql.append(" AND ANALYSIS_NUMBER = ? ");
		sql.append(" AND S_INFO.VALUE_3='Y' ");
		sql.append(" GROUP BY W_DTL.COND_SUB_RISK, S_INFO.VALUE_1  ");
		sql.append(" ORDER BY W_DTL.COND_SUB_RISK ");

		return commonJdbcTemplate.query(sql.toString(), new Object[] { analysisNumber }, groupCondSubRiskRowMapper);
	}

	protected RowMapper<LabelValueBean> groupCondSubRiskRowMapper = new RowMapper<LabelValueBean>() {
		@Override
		public LabelValueBean mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new LabelValueBean(rs.getString("CON_SUB_RISK_DESC"), rs.getString("COND_SUB_RISK"));
		}
	};

}
