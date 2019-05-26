package th.go.excise.ims.ta.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
import th.co.baiwa.buckwaframework.security.constant.SecurityConstants;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetDtl;
import th.go.excise.ims.ta.util.TaxAuditUtils;
import th.go.excise.ims.ta.vo.TaxDraftVo;
import th.go.excise.ims.ta.vo.TaxOperatorDetailVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

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
				"TAX_AMT_G1_M12", "TAX_AMT_G1_M13", "TAX_AMT_G1_M14", "TAX_AMT_G1_M15", "TAX_AMT_G1_M16",
				"TAX_AMT_G1_M17", "TAX_AMT_G1_M18", "TAX_AMT_G2_M1", "TAX_AMT_G2_M2", "TAX_AMT_G2_M3",
				"TAX_AMT_G2_M4", "TAX_AMT_G2_M5", "TAX_AMT_G2_M6", "TAX_AMT_G2_M7", "TAX_AMT_G2_M8",
				"TAX_AMT_G2_M9", "TAX_AMT_G2_M10", "TAX_AMT_G2_M11", "TAX_AMT_G2_M12", "TAX_AMT_G2_M13",
				"TAX_AMT_G2_M14", "TAX_AMT_G2_M15", "TAX_AMT_G2_M16", "TAX_AMT_G2_M17", "TAX_AMT_G2_M18",
				"COND_MAIN_GRP", "COND_SUB_CAPITAL", "COND_SUB_RISK", "CREATED_BY", "CREATED_DATE",
				"LAST_AUDIT_YEAR"),
			"TA_WORKSHEET_DTL_SEQ");

		commonJdbcTemplate.batchUpdate(sql, worksheetDtlList, 1000, new ParameterizedPreparedStatementSetter<TaWorksheetDtl>() {
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
				paramList.add(worksheetDtl.getTaxAmtG1M13());
				paramList.add(worksheetDtl.getTaxAmtG1M14());
				paramList.add(worksheetDtl.getTaxAmtG1M15());
				paramList.add(worksheetDtl.getTaxAmtG1M16());
				paramList.add(worksheetDtl.getTaxAmtG1M17());
				paramList.add(worksheetDtl.getTaxAmtG1M18());
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
				paramList.add(worksheetDtl.getTaxAmtG2M13());
				paramList.add(worksheetDtl.getTaxAmtG2M14());
				paramList.add(worksheetDtl.getTaxAmtG2M15());
				paramList.add(worksheetDtl.getTaxAmtG2M16());
				paramList.add(worksheetDtl.getTaxAmtG2M17());
				paramList.add(worksheetDtl.getTaxAmtG2M18());

				paramList.add(worksheetDtl.getCondMainGrp());
				paramList.add(worksheetDtl.getCondSubCapital());
				paramList.add(worksheetDtl.getCondSubRisk());
				paramList.add(worksheetDtl.getCreatedBy());
				paramList.add(worksheetDtl.getCreatedDate());

				paramList.add(worksheetDtl.getLastAuditYear());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

	@Override
	public void batchUpdate(List<TaWorksheetDtl> taWorksheetDtlList) {
		String sql = SqlGeneratorUtils.genSqlUpdate("TA_WORKSHEET_DTL",
			Arrays.asList(
				"COND_MAIN_GRP",
				"COND_SUB_CAPITAL",
				"COND_SUB_RISK",
				"COND_SUB_NO_AUDIT",
				"COND_G1",
				"COND_G2",
				"COND_G3",
				"COND_G4",
				"COND_G5",
				"COND_G6",
				"COND_REG_DATE",
				"COND_SORTING",
				"UPDATED_BY",
				"UPDATED_DATE"
			),
			Arrays.asList("ANALYSIS_NUMBER", "NEW_REG_ID")
		);

		commonJdbcTemplate.batchUpdate(sql, taWorksheetDtlList, 1000, new ParameterizedPreparedStatementSetter<TaWorksheetDtl>() {
			public void setValues(PreparedStatement ps, TaWorksheetDtl worksheetDtl) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				paramList.add(worksheetDtl.getCondMainGrp());
				paramList.add(worksheetDtl.getCondSubCapital());
				paramList.add(worksheetDtl.getCondSubRisk());
				paramList.add(worksheetDtl.getCondSubNoAudit());
				paramList.add(worksheetDtl.getCondG1());
				paramList.add(worksheetDtl.getCondG2());
				paramList.add(worksheetDtl.getCondG3());
				paramList.add(worksheetDtl.getCondG4());
				paramList.add(worksheetDtl.getCondG5());
				paramList.add(worksheetDtl.getCondG6());
				paramList.add(worksheetDtl.getCondRegDate());
				paramList.add(worksheetDtl.getCondSorting());
				paramList.add(worksheetDtl.getUpdatedBy());
				paramList.add(worksheetDtl.getUpdatedDate());
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
		sql.append("   ED_SECTOR.OFF_CODE SEC_CODE , ");
		sql.append("   ED_SECTOR.OFF_SHORT_NAME SEC_DESC , ");
		sql.append("   ED_AREA.OFF_CODE AREA_CODE , ");
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
		sql.append("   TA_W_DTL.*, ");
		sql.append("   T_W_COND_DTL.RISK_LEVEL ");
		sql.append(" FROM TA_WORKSHEET_DTL TA_W_DTL ");
		sql.append(" INNER JOIN TA_WORKSHEET_HDR TA_W_HDR ON TA_W_DTL.ANALYSIS_NUMBER = TA_W_HDR.ANALYSIS_NUMBER ");
		sql.append("   AND TA_W_DTL.IS_DELETED = 'N' ");
		sql.append(" INNER JOIN TA_WS_REG4000 R4000 ON R4000.NEW_REG_ID = TA_W_DTL.NEW_REG_ID ");
		sql.append("   AND R4000.IS_DELETED = 'N' ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_SECTOR ON ED_SECTOR.OFF_CODE = CONCAT ( SUBSTR(R4000.OFFICE_CODE, 0, 2) ,'0000') ");
		sql.append("   AND ED_SECTOR.IS_DELETED = 'N' ");
		sql.append(" INNER JOIN EXCISE_DEPARTMENT ED_AREA ON ED_AREA.OFF_CODE = CONCAT ( SUBSTR(R4000.OFFICE_CODE, 0, 4) ,'00' ) ");
		sql.append("   AND ED_AREA.IS_DELETED = 'N' ");
		sql.append(" LEFT JOIN TA_WORKSHEET_COND_MAIN_DTL T_W_COND_DTL ON T_W_COND_DTL.ANALYSIS_NUMBER = TA_W_DTL.ANALYSIS_NUMBER ");
		sql.append("   AND TA_W_DTL.COND_MAIN_GRP = T_W_COND_DTL.COND_GROUP ");
		sql.append(" LEFT JOIN TA_PLAN_WORKSHEET_SELECT TA_PW_SEL ON TA_PW_SEL.NEW_REG_ID = TA_W_DTL.NEW_REG_ID ");
		sql.append("   AND TA_PW_SEL.IS_DELETED = 'N' ");
		sql.append("   AND TA_PW_SEL.BUDGET_YEAR = ? ");
		params.add(formVo.getBudgetYear());

		if (ApplicationCache.isCtrlDutyGroupByOfficeCode(formVo.getOfficeCode())) {
			sql.append(" INNER JOIN EXCISE_CTRL_DUTY CD ON CD.DUTY_GROUP_CODE = R4000.DUTY_CODE  AND CD.RES_OFFCODE = ? ");
			params.add(formVo.getOfficeCode());
		}

		sql.append(" WHERE 1 = 1 ");
		sql.append(" AND TA_W_DTL.ANALYSIS_NUMBER = ? ");
		sql.append(" AND TA_W_HDR.IS_DELETED = 'N' ");

		params.add(formVo.getAnalysisNumber());

		// DUTY GROUP
		if (StringUtils.isNotBlank(formVo.getFacType())) {
			sql.append(" AND R4000.FAC_TYPE = ?");
			params.add(formVo.getFacType());
		}
		// DUTY
		if (StringUtils.isNotBlank(formVo.getDutyCode())) {
			sql.append(" AND R4000.duty_code = ?");
			params.add(formVo.getDutyCode());
		}
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
			sql.append(" AND COND_SUB_CAPITAL = ? ");
			params.add(formVo.getCapital());
		}
		if (StringUtils.isNotBlank(formVo.getRisk())) {
			sql.append(" AND COND_SUB_RISK = ? ");
			params.add(formVo.getRisk());
		}
		if (StringUtils.isNotBlank(formVo.getCondSubNoAuditFlag())) {
			sql.append(" AND COND_SUB_NO_AUDIT = ? ");
			params.add(formVo.getCondSubNoAuditFlag());
		}
		if (StringUtils.isNotBlank(formVo.getNewRegId())) {
			sql.append(" AND TA_W_DTL.NEW_REG_ID LIKE ? OR R4000.CUS_FULLNAME LIKE ? ");
			params.add("%" + StringUtils.trim(formVo.getNewRegId()) + "%");
			params.add("%" + StringUtils.trim(formVo.getNewRegId()) + "%");
		}

		if (StringUtils.isNotBlank(formVo.getTaxAuditLast()) && !"0".equals(formVo.getTaxAuditLast())) {
			sql.append(" AND TA_W_DTL.LAST_AUDIT_YEAR < ? ");
			int lastYear = Integer.valueOf(formVo.getTaxAuditLast());
			int budgetYear = Integer.valueOf(formVo.getBudgetYear());
			int rs = budgetYear - lastYear;
			params.add(rs);
		}

		if (!UserLoginUtils.getGrantedAuthorityList().contains(new SimpleGrantedAuthority(SecurityConstants.ROLE.ADMIN))){
			if (StringUtils.isNotBlank(formVo.getOfficeCode()) && !ExciseUtils.isCentral(formVo.getOfficeCode())) {
				sql.append(" AND R4000.OFFICE_CODE LIKE ? ");
				params.add(ExciseUtils.whereInLocalOfficeCode(formVo.getOfficeCode()));
			}
		}

	}

	public List<TaxOperatorDetailVo> findByCriteria(TaxOperatorFormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildByCriteriaQuery(sql, params, formVo);

		//sql.append(" ORDER BY TA_W_DTL.COND_SUB_NO_AUDIT ASC, NVL(T_W_COND_DTL.RISK_LEVEL,0) DESC, R4000.DUTY_CODE ASC, R4000.OFFICE_CODE ASC, TA_W_DTL.NEW_REG_ID ASC ");
		sql.append(" ORDER BY TA_W_DTL.COND_SUB_NO_AUDIT ASC, NVL(TA_W_DTL.COND_SORTING,0) DESC, R4000.DUTY_CODE ASC, R4000.OFFICE_CODE ASC, TA_W_DTL.NEW_REG_ID ASC ");

		return commonJdbcTemplate.query(
			OracleUtils.limitForDatable(sql.toString(), formVo.getStart(), formVo.getLength()),
			params.toArray(),
			new RowMapper<TaxOperatorDetailVo>() {
				@Override
				public TaxOperatorDetailVo mapRow(ResultSet rs, int rowNum) throws SQLException {
					TaxOperatorDetailVo vo = new TaxOperatorDetailVo();
					TaxAuditUtils.commonSelectionWorksheetRowMapper(vo, rs);
					vo.setDraftNumber(rs.getString("ANALYSIS_NUMBER"));
					vo.setCondTaxGrp(rs.getString("COND_MAIN_GRP"));
					vo.setCondSubCapital(rs.getString("COND_SUB_CAPITAL"));
					vo.setCondSubRisk(rs.getString("COND_SUB_RISK"));
					vo.setCondSubNoAudit(rs.getString("COND_SUB_NO_AUDIT"));
					vo.setRiskLevel(rs.getString("RISK_LEVEL"));
					vo.setCondG1(rs.getString("COND_G1"));
					vo.setCondG2(rs.getString("COND_G2"));
					vo.setCondG3(rs.getString("COND_G3"));
					vo.setCondG4(rs.getString("COND_G4"));
					vo.setCondG5(rs.getString("COND_G5"));
					vo.setCondG6(rs.getString("COND_G6"));
					vo.setCondRegDate(rs.getString("COND_REG_DATE"));
					
					int taxMonthNo = Integer.parseInt(rs.getString("TAX_MONTH_NO"));
					String notPayTaxMonthNo = null;
					if (formVo.getTaxMonthNo().intValue() == taxMonthNo) {
						notPayTaxMonthNo = "-";
					} else {
						notPayTaxMonthNo = String.valueOf(formVo.getTaxMonthNo().intValue() - taxMonthNo);
					}
					vo.setNotPayTaxMonthNo(notPayTaxMonthNo);

					try {
						vo.setCondSubCapitalDesc(ApplicationCache.getParamInfoByCode("TA_SUB_COND_CAPITAL", rs.getString("COND_SUB_CAPITAL")).getValue1());
						vo.setCondSubRiskDesc(ApplicationCache.getParamInfoByCode("TA_RISK_LEVEL", rs.getString("COND_SUB_RISK")).getValue1());
						vo.setCondSubNoAuditDesc(rs.getString("COND_SUB_NO_AUDIT"));
					} catch (Exception e) {
						vo.setCondSubCapitalDesc("");
						vo.setCondSubRiskDesc("");
						vo.setCondSubNoAuditDesc("");
					}

					vo.setRegCapital(rs.getString("REG_CAPITAL"));
					vo.setRegStatus((StringUtils.isNotBlank(rs.getString("REG_STATUS")) ? rs.getString("REG_STATUS") : " ") + " " + ConvertDateUtils.formatDateToString(rs.getDate("REG_DATE"), ConvertDateUtils.DD_MM_YY, ConvertDateUtils.LOCAL_TH));
					vo.setRegDate(ConvertDateUtils.formatDateToString(rs.getDate("REG_DATE"), ConvertDateUtils.DD_MM_YY, ConvertDateUtils.LOCAL_TH));
					vo.setCentralSelFlag(rs.getString("CENTRAL_SEL_FLAG"));
					if (FLAG.Y_FLAG.equals(vo.getCentralSelFlag())) {
						LocalDate localDate = LocalDateConverter.convertToEntityAttribute(rs.getDate("CENTRAL_SEL_DATE"));
						vo.setCentralSelDate(ConvertDateUtils.formatLocalDateToString(localDate, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
						vo.setCentralSelOfficeCode(rs.getString("CENTRAL_SEL_OFFICE_CODE"));
						if (StringUtils.isNotBlank(vo.getCentralSelOfficeCode())) {
							vo.setSelectBy(ApplicationCache.getExciseDepartment(vo.getCentralSelOfficeCode()).getDeptShortName());
						}
					}
					vo.setSectorSelFlag(rs.getString("SECTOR_SEL_FLAG"));
					if (StringUtils.isNotBlank(vo.getSectorSelFlag())) {
						LocalDate localDate = LocalDateConverter.convertToEntityAttribute(rs.getDate("SECTOR_SEL_DATE"));
						vo.setSectorSelDate(ConvertDateUtils.formatLocalDateToString(localDate, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
						vo.setSectorSelOfficeCode(rs.getString("SECTOR_SEL_OFFICE_CODE"));
						if (StringUtils.isNotBlank(vo.getSectorSelOfficeCode())) {
							vo.setSelectBy(ApplicationCache.getExciseDepartment(vo.getSectorSelOfficeCode()).getDeptShortName());
						}
					}
					vo.setAreaSelFlag(rs.getString("AREA_SEL_FLAG"));
					if (FLAG.Y_FLAG.equals(vo.getAreaSelFlag())) {
						LocalDate localDate = LocalDateConverter.convertToEntityAttribute(rs.getDate("AREA_SEL_DATE"));
						vo.setAreaSelDate(ConvertDateUtils.formatLocalDateToString(localDate, ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
						vo.setAreaSelOfficeCode(rs.getString("AREA_SEL_OFFICE_CODE"));
						if (StringUtils.isNotBlank(vo.getAreaSelOfficeCode())) {
							vo.setSelectBy(ApplicationCache.getExciseDepartment(vo.getAreaSelOfficeCode()).getDeptShortName());
						}
					}

					return vo;
				}
			}
		);
	}

	public Long countByCriteria(TaxOperatorFormVo formVo) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		buildByCriteriaQuery(sql, params, formVo);

		return commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()), params.toArray(), Long.class);
	}

	@Override
	public List<TaxDraftVo> findByAnalysisNumber(String analysisNumber) {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT T.*, D.TAX_AMT_CHN_PNT, D.TAX_MONTH_NO, D.LAST_AUDIT_YEAR LAST_AUDIT_YEAR");
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
			vo.setLastAuditYear(rs.getString("LAST_AUDIT_YEAR"));
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
