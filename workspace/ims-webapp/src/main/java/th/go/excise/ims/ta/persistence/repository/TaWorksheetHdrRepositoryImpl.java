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
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;

public class TaWorksheetHdrRepositoryImpl implements TaWorksheetHdrCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void insertBatch(List<TaWorksheetHdr> taWorksheetHdrList) throws SQLException {
		String sql = SqlGeneratorUtils.genSqlInsert("TA_WORKSHEET_HDR",
				Arrays.asList("worksheet_hdr_id", "analysis_number", "new_reg_id", "sum_tax_amt_g1", "sum_tax_amt_g2", "tax_amt_chn_pnt", "tax_month_no", "tax_amt_g1_m1", "tax_amt_g1_m2", "tax_amt_g1_m3", "tax_amt_g1_m4", "tax_amt_g1_m5", "tax_amt_g1_m6", "tax_amt_g1_m7", "tax_amt_g1_m8",
						"tax_amt_g1_m9", "tax_amt_g1_m10", "tax_amt_g1_m11", "tax_amt_g1_m12", "tax_amt_g2_m1", "tax_amt_g2_m2", "tax_amt_g2_m3", "tax_amt_g2_m4", "tax_amt_g2_m5", "tax_amt_g2_m6", "tax_amt_g2_m7", "tax_amt_g2_m8", "tax_amt_g2_m9", "tax_amt_g2_m10", "tax_amt_g2_m11",
						"tax_amt_g2_m12", "cond_tax_grp", "created_by"),
				"TA_WORKSHEET_HDR_SEQ");

		commonJdbcTemplate.batchUpdate(sql, taWorksheetHdrList, 1000, new ParameterizedPreparedStatementSetter<TaWorksheetHdr>() {
			public void setValues(PreparedStatement ps, TaWorksheetHdr taWorksheetHdr) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(taWorksheetHdr.getAnalysisNumber());
				paramList.add(taWorksheetHdr.getNewRegId());
				paramList.add(taWorksheetHdr.getSumTaxAmtG1());
				paramList.add(taWorksheetHdr.getSumTaxAmtG2());
				paramList.add(taWorksheetHdr.getTaxAmtChnPnt());
				paramList.add(taWorksheetHdr.getTaxMonthNo());
				paramList.add(taWorksheetHdr.getTaxAmtG1M1());
				paramList.add(taWorksheetHdr.getTaxAmtG1M2());
				paramList.add(taWorksheetHdr.getTaxAmtG1M3());
				paramList.add(taWorksheetHdr.getTaxAmtG1M4());
				paramList.add(taWorksheetHdr.getTaxAmtG1M5());
				paramList.add(taWorksheetHdr.getTaxAmtG1M6());
				paramList.add(taWorksheetHdr.getTaxAmtG1M7());
				paramList.add(taWorksheetHdr.getTaxAmtG1M8());
				paramList.add(taWorksheetHdr.getTaxAmtG1M9());
				paramList.add(taWorksheetHdr.getTaxAmtG1M10());
				paramList.add(taWorksheetHdr.getTaxAmtG1M11());
				paramList.add(taWorksheetHdr.getTaxAmtG1M12());
				paramList.add(taWorksheetHdr.getTaxAmtG2M1());
				paramList.add(taWorksheetHdr.getTaxAmtG2M2());
				paramList.add(taWorksheetHdr.getTaxAmtG2M3());
				paramList.add(taWorksheetHdr.getTaxAmtG2M4());
				paramList.add(taWorksheetHdr.getTaxAmtG2M5());
				paramList.add(taWorksheetHdr.getTaxAmtG2M6());
				paramList.add(taWorksheetHdr.getTaxAmtG2M7());
				paramList.add(taWorksheetHdr.getTaxAmtG2M8());
				paramList.add(taWorksheetHdr.getTaxAmtG2M9());
				paramList.add(taWorksheetHdr.getTaxAmtG2M10());
				paramList.add(taWorksheetHdr.getTaxAmtG2M11());
				paramList.add(taWorksheetHdr.getTaxAmtG2M12());
				paramList.add(taWorksheetHdr.getCondTaxGrp());
				paramList.add("SYSTEM");

				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}

		});
	}

}
