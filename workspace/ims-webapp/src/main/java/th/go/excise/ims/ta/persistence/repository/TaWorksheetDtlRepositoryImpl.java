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
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetDtl;

public class TaWorksheetDtlRepositoryImpl implements TaWorksheetDtlCustom {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void insertBatch(List<TaWorksheetDtl> taWorksheetHdrList) throws SQLException {
		String sql = SqlGeneratorUtils.genSqlInsert("TA_WORKSHEET_DTL", Arrays.asList(
				"WORKSHEET_DTL_ID",
				"ANALYSIS_NUMBER", 
				"NEW_REG_ID",
				"COND_MAIN_GRP",
				"CENTER_SEL_FLAG",
				"SECTOR_SEL_FLAG",
				"AREA_SEL_FLAG" ,
				"CREATED_BY"), "TA_WORKSHEET_DTL_SEQ");

		commonJdbcTemplate.batchUpdate(sql, taWorksheetHdrList, 1000, new ParameterizedPreparedStatementSetter<TaWorksheetDtl>() {
			public void setValues(PreparedStatement ps, TaWorksheetDtl taWorksheetDtl) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(taWorksheetDtl.getAnalysisNumber());
				paramList.add(taWorksheetDtl.getNewRegId());
				paramList.add(taWorksheetDtl.getCondMainGrp());
				paramList.add(taWorksheetDtl.getCenterSelFlag());
				paramList.add(taWorksheetDtl.getSectorSelFlag());
				paramList.add(taWorksheetDtl.getAreaSelFlag());
				paramList.add(UserLoginUtils.getCurrentUsername());
				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}

		});
	}

}
