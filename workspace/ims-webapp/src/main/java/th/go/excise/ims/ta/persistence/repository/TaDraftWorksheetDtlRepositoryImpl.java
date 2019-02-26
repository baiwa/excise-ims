package th.go.excise.ims.ta.persistence.repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.ParameterizedPreparedStatementSetter;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.go.excise.ims.ta.persistence.entity.TaDraftWorksheetDtl;

public class TaDraftWorksheetDtlRepositoryImpl implements TaDraftWorksheetDtlCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void save(List<TaDraftWorksheetDtl> draftWorksheetDtlList) throws SQLException {
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
			public void setValues(PreparedStatement ps, TaDraftWorksheetDtl taDraftWorksheetDtl) throws SQLException {
				List<Object> paramList = new ArrayList<>();
				paramList.add(taDraftWorksheetDtl.getDraftNumber());
				paramList.add(taDraftWorksheetDtl.getNewRegId());
				paramList.add(taDraftWorksheetDtl.getSumTaxAmtG1());
				paramList.add(taDraftWorksheetDtl.getSumTaxAmtG2());
				paramList.add(taDraftWorksheetDtl.getTaxAmtChnPnt());
				paramList.add(taDraftWorksheetDtl.getTaxMonthNo());
				paramList.add(taDraftWorksheetDtl.getTaxAuditLast3());
				paramList.add(taDraftWorksheetDtl.getTaxAuditLast2());
				paramList.add(taDraftWorksheetDtl.getTaxAuditLast1());
				paramList.add(taDraftWorksheetDtl.getTaxAmtSd());
				paramList.add(taDraftWorksheetDtl.getTaxAmtMean());
				paramList.add(taDraftWorksheetDtl.getTaxAmtMaxPnt());
				paramList.add(taDraftWorksheetDtl.getTaxAmtMinPnt());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG1M1());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG1M2());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG1M3());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG1M4());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG1M5());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG1M6());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG1M7());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG1M8());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG1M9());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG1M10());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG1M11());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG1M12());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG2M1());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG2M2());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG2M3());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG2M4());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG2M5());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG2M6());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG2M7());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG2M8());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG2M9());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG2M10());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG2M11());
				paramList.add(taDraftWorksheetDtl.getTaxAmtG2M12());
				paramList.add(taDraftWorksheetDtl.getCreatedBy());
				paramList.add(taDraftWorksheetDtl.getCreatedDate());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}
		});
	}

}
