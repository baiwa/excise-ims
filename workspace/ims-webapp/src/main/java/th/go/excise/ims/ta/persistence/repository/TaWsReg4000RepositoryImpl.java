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

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.co.baiwa.buckwaframework.common.persistence.util.SqlGeneratorUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateTimeConverter;
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

	@Override
	public List<TaWsReg4000> findAllPagination(TaWsReg4000 taWsReg4000, int start, int length) throws SQLException {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder(" SELECT * FROM TA_WS_REG4000 WHERE IS_DELETED = '").append(FLAG.N_FLAG).append("' ");
		if(StringUtils.isNotBlank(taWsReg4000.getFacType())) {
			paramList.add(taWsReg4000.getFacType());
			sql.append(" AND FAC_TYPE = ?");
		}
		
		if(StringUtils.isNotBlank(taWsReg4000.getDutyCode())) {
			paramList.add(taWsReg4000.getDutyCode());
			sql.append(" AND DUTY_CODE = ?");
		}
		
		if(StringUtils.isNotBlank(taWsReg4000.getOfficeCode())) {
			paramList.add(taWsReg4000.getOfficeCode());
			sql.append(" AND OFFICE_CODE like ?");
		}
		
		sql.append(" ORDER BY DUTY_CODE , NEW_REG_ID ");
		return this.commonJdbcTemplate.query(OracleUtils.limitForDatable(sql.toString(), start, length),paramList.toArray(),  rowMappping);
	}
	@Override
	public Long countAll(TaWsReg4000 taWsReg4000) throws SQLException {
		List<Object> paramList = new ArrayList<>();
		StringBuilder sql = new StringBuilder(" SELECT * FROM TA_WS_REG4000 WHERE IS_DELETED = '").append(FLAG.N_FLAG).append("' ");;
		if(StringUtils.isNotBlank(taWsReg4000.getFacType())) {
			paramList.add(taWsReg4000.getFacType());
			sql.append(" AND FAC_TYPE = ?");
		}
		
		if(StringUtils.isNotBlank(taWsReg4000.getDutyCode())) {
			paramList.add(taWsReg4000.getDutyCode());
			sql.append(" AND DUTY_CODE = ?");
		}
		
		if(StringUtils.isNotBlank(taWsReg4000.getOfficeCode())) {
			paramList.add(taWsReg4000.getOfficeCode());
			sql.append(" AND OFFICE_CODE like ?");
		}
		
		sql.append(" ORDER BY DUTY_CODE , NEW_REG_ID ");
		return this.commonJdbcTemplate.queryForObject(OracleUtils.countForDataTable(sql.toString()), paramList.toArray(), Long.class);
	}
	
	
	
	protected RowMapper<TaWsReg4000> rowMappping = new RowMapper<TaWsReg4000>() {

		@Override
		public TaWsReg4000 mapRow(ResultSet re, int arg1) throws SQLException {
			TaWsReg4000 vo = new TaWsReg4000();
			vo.setWsReg4000Id(re.getLong("WS_REG4000_ID"));
			vo.setNewRegId(re.getString("NEW_REG_ID"));
			vo.setCusId(re.getString("CUS_ID"));
			vo.setCusFullname(re.getString("CUS_FULLNAME"));
			vo.setCusAddress(re.getString("CUS_ADDRESS"));
			vo.setCusTelno(re.getString("CUS_TELNO"));
			vo.setCusEmail(re.getString("CUS_EMAIL"));
			vo.setCusUrl(re.getString("CUS_URL"));
			vo.setFacId(re.getString("FAC_ID"));
			vo.setFacFullname(re.getString("FAC_FULLNAME"));
			vo.setFacAddress(re.getString("FAC_ADDRESS"));
			vo.setFacTelno(re.getString("FAC_TELNO"));
			vo.setFacEmail(re.getString("FAC_EMAIL"));
			vo.setFacUrl(re.getString("FAC_URL"));
			vo.setFacType(re.getString("FAC_TYPE"));
			vo.setRegDate(LocalDateTimeConverter.convertToEntityAttribute(re.getTimestamp("REG_DATE")));
			vo.setRegCapital(re.getString("REG_CAPITAL"));
			vo.setOfficeCode(re.getString("OFFICE_CODE"));
			vo.setActiveFlag(re.getString("ACTIVE_FLAG"));
			vo.setDutyCode(re.getString("DUTY_CODE"));
			
			return vo;
		}
	};

}
