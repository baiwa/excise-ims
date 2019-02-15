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
import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;

public class TaWsReg4000RepositoryImpl implements TaWsReg4000Custom {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	@Override
	public void insertBatch(List<TaWsReg4000> taWsReg4000List) throws SQLException {
		String sql = SqlGeneratorUtils.genSqlInsert("TA_WS_REG4000",
				Arrays.asList("WS_REG4000_ID", "NEW_REG_ID", "CUS_ID", "CUS_FULLNAME", "CUS_ADDRESS", "CUS_TELNO", "CUS_EMAIL", "CUS_URL", "FAC_ID", "FAC_FULLNAME", "FAC_ADDRESS", "FAC_TELNO", "FAC_EMAIL", "FAC_URL", "OFFICE_CODE", "ACTIVE_FLAG", "DUTY_CODE", "CREATED_BY"), "TA_WS_REG4000_SEQ");

		commonJdbcTemplate.batchUpdate(sql, taWsReg4000List, 1000, new ParameterizedPreparedStatementSetter<TaWsReg4000>() {
			public void setValues(PreparedStatement ps, TaWsReg4000 taWsReg4000) throws SQLException {
				List<Object> paramList = new ArrayList<Object>();
				paramList.add(taWsReg4000.getNewRegId());
				paramList.add(taWsReg4000.getCusId());
				paramList.add(taWsReg4000.getCusFullname());
				paramList.add(taWsReg4000.getCusAddress());
				paramList.add(taWsReg4000.getCusTelno());
				paramList.add(taWsReg4000.getCusEmail());
				paramList.add(taWsReg4000.getCusUrl());
				paramList.add(taWsReg4000.getFacId());
				paramList.add(taWsReg4000.getFacFullname());
				paramList.add(taWsReg4000.getFacAddress());
				paramList.add(taWsReg4000.getFacTelno());
				paramList.add(taWsReg4000.getFacEmail());
				paramList.add(taWsReg4000.getFacUrl());
				paramList.add(taWsReg4000.getOfficeCode());
				paramList.add(taWsReg4000.getActiveFlag());
				paramList.add(taWsReg4000.getDutyCode());
				paramList.add("SYSTEM");

				commonJdbcTemplate.preparePs(ps, paramList.toArray());
			}

		});

	}

	@Override
	public void truncateTaWsReg4000() throws SQLException {
		commonJdbcTemplate.update("TRUNCATE TABLE TA_WS_REG4000");
		
	}

}
