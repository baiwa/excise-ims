package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateTimeConverter;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;
import th.go.excise.ims.ia.vo.Int030102FormVo;
import th.go.excise.ims.ia.vo.Int030102Vo;

@Repository
public class Int030102JdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public List<Int030102Vo> list(Int030102FormVo form) {
		List<Int030102Vo> iaRiskFactorsMasterList = new ArrayList<Int030102Vo>();
		StringBuilder sql = new StringBuilder(" SELECT  a.ID AS ID_MASTER_RES,  " + 
				"        c.ID_CONFIG AS ID_CONFIG_RES, " + 
				"				a.RISK_FACTORS_MASTER,   " + 
				"				b.BUDGET_YEAR,   " + 
				"				b.INSPECTION_WORK,   " + 
				"				b.STATUS,   " + 
				"				a.CREATED_BY AS CREATED_BY_RES,   " + 
				"				a.CREATED_DATE  AS CREATED_DATE_RES,  " + 
				"				a.NOT_DELETE , " + 
				"        c.* " + 
				"				FROM IA_RISK_FACTORS_MASTER a  " + 
				"				LEFT JOIN IA_RISK_FACTORS_STATUS b  " + 
				"				ON a.ID = b.ID_MASTER  " + 
				"        LEFT JOIN (SELECT e.id AS ID_CONFIG,d.ID_MASTER,e.* from IA_RISK_FACTORS d  " + 
				"                    FULL JOIN IA_RISK_FACTORS_CONFIG e  " + 
				"                    ON d.ID = e.ID_FACTORS  " + 
				"                    WHERE d.IS_DELETED = 'N'  " + 
				"                    AND d.INSPECTION_WORK =  ?  " + 
				"                    AND d.BUDGET_YEAR = ? ) c " + 
				"        ON a.ID = c.ID_MASTER  " + 
				"				WHERE a.IS_DELETED = 'N' AND  " + 
				"				b.INSPECTION_WORK = ? " + 
				"				AND b.BUDGET_YEAR = ? ");
		List<Object> params = new ArrayList<Object>();
		params.add(form.getInspectionWork());
		params.add(form.getBudgetYear());
		params.add(form.getInspectionWork());	
		params.add(form.getBudgetYear());
		sql.append(" ORDER BY a.CREATED_DATE,a.ID ASC");
		iaRiskFactorsMasterList = commonJdbcTemplate.query(sql.toString(), params.toArray(), listRowmapper);
		return iaRiskFactorsMasterList;
	}



	private RowMapper<Int030102Vo> listRowmapper = new RowMapper<Int030102Vo>() {
		@Override
		public Int030102Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int030102Vo vo = new Int030102Vo();
			IaRiskFactorsMaster iarfm = new IaRiskFactorsMaster();	
			IaRiskFactorsConfig iarfc = new IaRiskFactorsConfig();	
			

			iarfm.setId(rs.getBigDecimal("ID_MASTER_RES"));
			iarfm.setRiskFactorsMaster(rs.getString("RISK_FACTORS_MASTER"));
			iarfm.setBudgetYear(rs.getString("BUDGET_YEAR"));
			iarfm.setStatus(rs.getString("STATUS"));
			LocalDateTime createdDate = LocalDateTimeConverter
					.convertToEntityAttribute(rs.getTimestamp("CREATED_DATE_RES"));
			iarfm.setCreatedDate(createdDate);
			iarfm.setCreatedBy(rs.getString("CREATED_BY_RES"));
			iarfm.setNotDelete(rs.getString("NOT_DELETE"));
			String date = checkAndConvertDateToString(rs.getDate("CREATED_DATE_RES"));
			
			vo.setCreatedDateDesc(date);
			vo.setIaRiskFactorsMaster(iarfm);
			
			vo.setIdConfig(rs.getBigDecimal("ID_CONFIG_RES"));
			iarfc.setId(rs.getBigDecimal("ID_CONFIG_RES"));
			vo.setIaRiskFactorsConfig(iarfc);
	
			return vo;
		}
	};

	
	private String checkAndConvertDateToString(Date date) {
		String dateSting = "";
		if (date != null) {
			dateSting = ConvertDateUtils.formatDateToString(date, ConvertDateUtils.DD_MM_YYYY,
					ConvertDateUtils.LOCAL_TH);
		}
		return dateSting;
	}

	public void delete(Int030102FormVo form) {
		StringBuilder sql = new StringBuilder(" UPDATE IA_RISK_FACTORS_MASTER SET IS_DELETED = 'Y' WHERE ID = ? ");
		commonJdbcTemplate.update(sql.toString(), new Object[] { form.getId() });

	}
	
	public void listUpdateStatus(Int030102FormVo form) {
		StringBuilder sqlUpdateN = new StringBuilder(" UPDATE IA_RISK_FACTORS_STATUS c SET c.STATUS = 'N' WHERE c.BUDGET_YEAR = ? ");
		StringBuilder sqlUpdateY = new StringBuilder(" UPDATE IA_RISK_FACTORS_STATUS f SET f.STATUS = 'Y' WHERE f.BUDGET_YEAR = ? AND f.ID_MASTER IN " + 
				"( SELECT b.ID_MASTER " + 
				"  FROM IA_RISK_FACTORS_MASTER a  " + 
				"  LEFT JOIN (SELECT * FROM IA_RISK_FACTORS_STATUS WHERE BUDGET_YEAR = ? ) b  " + 
				"  ON a.ID = b.ID_MASTER " + 
				"  WHERE a.INSPECTION_WORK = ?  " + 
				"  AND b.status = 'Y') ");
		commonJdbcTemplate.update(sqlUpdateN.toString(), new Object[] { form.getBudgetYear() });
		commonJdbcTemplate.update(sqlUpdateY.toString(), new Object[] { form.getBudgetYear(),form.getBudgetYearTo(),form.getInspectionWork()});

	}

	public void editStatus(Int030102FormVo form) {
		StringBuilder sql = new StringBuilder(" UPDATE IA_RISK_FACTORS_STATUS SET STATUS = ? WHERE ID_MASTER = ? AND Budget_Year= ? AND Inspection_Work= ?");
		String status = ("N".equals(form.getStatus()) ? "Y" : "N");
		commonJdbcTemplate.update(sql.toString(), new Object[] { status, form.getId(),form.getBudgetYear(), form.getInspectionWork() });
	}

	public List<IaRiskFactorsMaster> listSaveFactors(Int030102FormVo form) {
		List<IaRiskFactorsMaster> iaRiskFactorsMasterList = new ArrayList<IaRiskFactorsMaster>();
		StringBuilder sql = new StringBuilder(
				" SELECT a.ID, " + 
				"a.RISK_FACTORS_MASTER, " + 
				"b.BUDGET_YEAR, " + 
				"b.INSPECTION_WORK, " + 
				"b.STATUS,  " + 
				"a.CREATED_BY, " + 
				"a.CREATED_DATE, " + 
				"a.NOT_DELETE " + 
				"FROM IA_RISK_FACTORS_MASTER a " + 
				"RIGHT JOIN IA_RISK_FACTORS_STATUS b " + 
				"ON a.ID = b.ID_MASTER " + 
				"WHERE b.STATUS = 'Y' " + 
				"AND a.IS_DELETED = 'N' " + 
				"AND b.INSPECTION_WORK = ? " + 
				"AND b.BUDGET_YEAR = ? ");
		iaRiskFactorsMasterList = commonJdbcTemplate.query(sql.toString(),
				new Object[] { form.getInspectionWork(), form.getBudgetYear() }, listRowmapperSave);
		return iaRiskFactorsMasterList;
	}

	public void updateFactorsIsDeleteY(Int030102FormVo form) {
		StringBuilder sql = new StringBuilder(
				" UPDATE IA_RISK_FACTORS SET Is_Deleted='Y'  WHERE BUDGET_YEAR = ? AND INSPECTION_WORK = ? ");
		commonJdbcTemplate.update(sql.toString(), new Object[] { form.getBudgetYear(), form.getInspectionWork() });
	}

	private RowMapper<IaRiskFactorsMaster> listRowmapperSave = new RowMapper<IaRiskFactorsMaster>() {
		@Override
		public IaRiskFactorsMaster mapRow(ResultSet rs, int arg1) throws SQLException {
			IaRiskFactorsMaster vo = new IaRiskFactorsMaster();
			vo.setId(rs.getBigDecimal("ID"));
			vo.setRiskFactorsMaster(rs.getString("RISK_FACTORS_MASTER"));
			vo.setBudgetYear(rs.getString("BUDGET_YEAR"));
			vo.setInspectionWork(rs.getBigDecimal("INSPECTION_WORK"));
			return vo;
		}

	};
	
	public int checkAndInsertTableFactorsStatus(Int030102FormVo form) {
		StringBuilder sql = new StringBuilder(
				" SELECT count(*) FROM IA_RISK_FACTORS_STATUS WHERE BUDGET_YEAR = ? AND INSPECTION_WORK = ? ");
		int count = commonJdbcTemplate.queryForObject(sql.toString(), new Object[] { form.getBudgetYear(), form.getInspectionWork() }, int.class);
		return count;
	}
	
	public int updateIsDelete(BigDecimal id) {
		StringBuilder sql = new StringBuilder(
				" UPDATE IA_RISK_FACTORS SET Is_Deleted = 'N'  WHERE ID = ? ");
		int count = commonJdbcTemplate.update(sql.toString(), new Object[] {id });
		return count;
	}
	
	public void saveRiskFactorsLevel(Int030102FormVo form) {
		StringBuilder sql = new StringBuilder();
		sql.append("   UPDATE IA_RISK_FACTORS_CONFIG C                       ");
		sql.append("   SET C.FACTORS_LEVEL   = ? ,                           ");
		sql.append("   C.START_DATE          = null,                         ");
		sql.append("   C.END_DATE            = null,                         ");
		sql.append("   C.INFO_USED_RISK      = null,                         ");
		sql.append("   C.INFO_USED_RISK_DESC = null,                         ");
		sql.append("   C.VERYLOW             = null,                         ");
		sql.append("   C.VERYLOW_START       = null,                         ");
		sql.append("   C.VERYLOW_END         = null,                         ");
		sql.append("   C.VERYLOW_RATING      = null,                         ");
		sql.append("   C.VERYLOW_COLOR       = null,                         ");
		sql.append("   C.VERYLOW_CONDITION   = null,                         ");
		sql.append("   C.LOW                 = null,                         ");
		sql.append("   C.LOW_START           = null,                         ");
		sql.append("   C.LOW_END             = null,                         ");
		sql.append("   C.LOW_RATING          = null,                         ");
		sql.append("   C.LOW_COLOR           = null,                         ");
		sql.append("   C.LOW_CONDITION       = null,                         ");
		sql.append("   C.MEDIUM              = null,                         ");
		sql.append("   C.MEDIUM_START        = null,                         ");
		sql.append("   C.MEDIUM_END          = null,                         ");
		sql.append("   C.MEDIUM_RATING       = null,                         ");
		sql.append("   C.MEDIUM_COLOR        = null,                         ");
		sql.append("   C.MEDIUM_CONDITION    = null,                         ");
		sql.append("   C.HIGH                = null,                         ");
		sql.append("   C.HIGH_START          = null,                         ");
		sql.append("   C.HIGH_END            = null,                         ");
		sql.append("   C.HIGH_RATING         = null,                         ");
		sql.append("   C.HIGH_COLOR          = null,                         ");
		sql.append("   C.HIGH_CONDITION      = null,                         ");
		sql.append("   C.VERYHIGH            = null,                         ");
		sql.append("   C.VERYHIGH_START      = null,                         ");
		sql.append("   C.VERYHIGH_END        = null,                         ");
		sql.append("   C.VERYHIGH_RATING     = null,                         ");
		sql.append("   C.VERYHIGH_COLOR      = null,                         ");
		sql.append("   C.VERYHIGH_CONDITION  = null                          ");
		sql.append("   WHERE C.ID IN (SELECT B.ID                            ");
		sql.append("                 FROM IA_RISK_FACTORS A                  ");
		sql.append("                 INNER JOIN IA_RISK_FACTORS_CONFIG B     ");
		sql.append("                 ON A.ID = B.ID_FACTORS                  ");
		sql.append("                 WHERE A.BUDGET_YEAR = ? )    ");
		
		commonJdbcTemplate.update(sql.toString(), new Object[] { form.getFactorsLevel(), form.getBudgetYear() });
	}

}
