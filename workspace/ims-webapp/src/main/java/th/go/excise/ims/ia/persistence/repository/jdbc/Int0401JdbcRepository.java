package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.persistence.entity.IaRiskSelectCase;
import th.go.excise.ims.ia.vo.Int0401CalConfigVo;
import th.go.excise.ims.ia.vo.Int0401CalDataVo;
import th.go.excise.ims.ia.vo.Int0401CalVo;
import th.go.excise.ims.ia.vo.Int0401HeaderVo;
import th.go.excise.ims.ia.vo.Int0401ListVo;
import th.go.excise.ims.ia.vo.Int0401Vo;

@Repository
public class Int0401JdbcRepository {

	@Autowired
	private CommonJdbcTemplate jdbcTemplate;

	// ROW
	public List<IaRiskSelectCase> findRow(String budgetYear, BigDecimal inspectionWork, String status) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(
				" SELECT S.* FROM IA_RISK_SELECT_CASE S WHERE S.BUDGET_YEAR = ? AND S.INSPECTION_WORK = ? AND S.STATUS = ? AND S.IS_DELETED = 'N' ");
		List<Object> params = new ArrayList<>();
		params.add(budgetYear);
		params.add(inspectionWork);
		params.add(status);
		List<IaRiskSelectCase> lists = jdbcTemplate.query(sqlBuilder.toString(), params.toArray(), rowRowMapper);
		return lists;
	}
	// ROW RowMapper
	private RowMapper<IaRiskSelectCase> rowRowMapper = new RowMapper<IaRiskSelectCase>() {
		@Override
		public IaRiskSelectCase mapRow(ResultSet rs, int arg1) throws SQLException {
			IaRiskSelectCase vo = new IaRiskSelectCase();
			vo.setId(rs.getBigDecimal("ID"));
			vo.setBudgetYear(rs.getString("BUDGET_YEAR"));
			vo.setInspectionWork(rs.getBigDecimal("INSPECTION_WORK"));
			vo.setProject(rs.getString("PROJECT"));
			vo.setExciseCode(rs.getString("EXCISE_CODE"));
			vo.setSector(rs.getString("SECTOR"));
			vo.setArea(rs.getString("AREA"));
			vo.setStatus(rs.getString("STATUS"));
			return vo;
		}
	};

	// HEAD
	public List<IaRiskFactors> findHead(String budgetYear, BigDecimal inspectionWork) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(
				" SELECT F.* FROM IA_RISK_FACTORS F WHERE F.BUDGET_YEAR = ? AND F.INSPECTION_WORK = ? AND F.IS_DELETED = 'N' ");
		List<Object> params = new ArrayList<>();
		params.add(budgetYear);
		params.add(inspectionWork);
		List<IaRiskFactors> lists = jdbcTemplate.query(sqlBuilder.toString(), params.toArray(), headRowMapper);
		return lists;
	}
	// HEAD RowMapper
	private RowMapper<IaRiskFactors> headRowMapper = new RowMapper<IaRiskFactors>() {
		@Override
		public IaRiskFactors mapRow(ResultSet rs, int arg1) throws SQLException {
			IaRiskFactors vo = new IaRiskFactors();
			vo.setId(rs.getBigDecimal("ID"));
			vo.setIdMaster(rs.getBigDecimal("ID_MASTER"));
			vo.setBudgetYear(rs.getString("BUDGET_YEAR"));
			vo.setInspectionWork(rs.getBigDecimal("INSPECTION_WORK"));
			vo.setRiskFactors(rs.getString("RISK_FACTORS"));
			vo.setSide(rs.getString("SIDE"));
			vo.setStatusScreen(rs.getString("STATUS_SCREEN"));
			return vo;
		}
	};

	// DETAILS
	public List<Int0401CalVo> findDetails(String budgetYear, BigDecimal inspectionWork) {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.append(" SELECT C.ID_FACTORS AS C_ID_FACTORS,  C.*, D.*, ");
		sqlBuilder.append(" D.ID_FACTORS AS D_ID_FACTORS FROM IA_RISK_FACTORS F ");
		sqlBuilder.append(" INNER JOIN IA_RISK_FACTORS_DATA D ON D.ID_FACTORS = F.ID ");
		sqlBuilder.append(" INNER JOIN IA_RISK_FACTORS_CONFIG C ON F.ID = C.ID_FACTORS ");
		sqlBuilder.append(" WHERE F.BUDGET_YEAR = ? AND F.INSPECTION_WORK = ? ");
		sqlBuilder.append(" AND C.IS_DELETED = 'N' AND D.IS_DELETED = 'N' AND F.IS_DELETED = 'N' ");
		List<Object> params = new ArrayList<>();
		params.add(budgetYear);
		params.add(inspectionWork);
		List<Int0401CalVo> lists = jdbcTemplate.query(sqlBuilder.toString(), params.toArray(), detialsRowMapper);
		return lists;
	}
	// HEAD RowMapper
	private RowMapper<Int0401CalVo> detialsRowMapper = new RowMapper<Int0401CalVo>() {
		@Override
		public Int0401CalVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int0401CalVo vo = new Int0401CalVo();
			Int0401CalDataVo data = new Int0401CalDataVo();
			data.setIdFactors(rs.getBigDecimal("D_ID_FACTORS"));
			data.setIdSelect(rs.getBigDecimal("ID_SELECT"));
			data.setBudgetYear(rs.getString("BUDGET_YEAR"));
			data.setInspectionWork(rs.getBigDecimal("INSPECTION_WORK"));
			data.setProject(rs.getString("PROJECT"));
			data.setExciseCode(rs.getString("EXCISE_CODE"));
			data.setSector(rs.getString("SECTOR"));
			data.setArea(rs.getString("AREA"));
			data.setRiskCost(rs.getString("RISK_COST"));
			data.setRiskRate(rs.getString("RISK_RATE"));
			data.setRiskStep(rs.getString("RISK_STEP"));

			Int0401CalConfigVo config = new Int0401CalConfigVo();
			config.setIdFactors(rs.getBigDecimal("C_ID_FACTORS"));
			config.setStartDate(rs.getDate("START_DATE"));
			config.setEndDate(rs.getDate("END_DATE"));
			config.setInfoUsedRisk(rs.getString("INFO_USED_RISK"));
			config.setInfoUsedRiskDesc(rs.getString("INFO_USED_RISK_DESC"));
			
			config.setVerylow(rs.getString("VERYLOW"));
			config.setVerylowStart(rs.getString("VERYLOW_START"));
			config.setVerylowEnd(rs.getString("VERYLOW_END"));
			config.setVerylowRating(rs.getBigDecimal("VERYLOW_RATING"));
			config.setVerylowColor(rs.getString("VERYLOW_COLOR"));
			config.setVerylowCondition(rs.getString("VERYLOW_CONDITION"));
			
			vo.setConfig(config);
			vo.setData(data);
			return vo;
		}
	};

}
