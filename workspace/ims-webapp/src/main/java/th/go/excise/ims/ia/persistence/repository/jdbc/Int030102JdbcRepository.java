package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateTimeConverter;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;
import th.go.excise.ims.ia.vo.Int030102FormVo;
import th.go.excise.ims.ia.vo.Int030102Vo;

@Repository
public class Int030102JdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	private final String SQL_IA_RISK_FACTORS_MASTER = " SELECT * FROM IA_RISK_FACTORS_MASTER WHERE IS_DELETED = 'N' AND INSPECTION_WORK = ? ";

	public List<Int030102Vo> list(Int030102FormVo form) {
		List<Int030102Vo> iaRiskFactorsMasterList = new ArrayList<Int030102Vo>();
		StringBuilder sql = new StringBuilder("SELECT a.ID," + 
				"a.RISK_FACTORS_MASTER, " + 
				"b.BUDGET_YEAR, " + 
				"b.INSPECTION_WORK, " + 
				"b.STATUS, " + 
				"a.CREATED_BY, " + 
				"a.CREATED_DATE, " +
				"a.NOT_DELETE " + 
				"FROM IA_RISK_FACTORS_MASTER a " + 
				"LEFT JOIN IA_RISK_FACTORS_STATUS b " + 
				"ON a.ID = b.ID_MASTER " + 
				"WHERE a.IS_DELETED = 'N' AND " + 
				"b.INSPECTION_WORK = ? ");
		
		List<Object> params = new ArrayList<Object>();

		params.add(form.getInspectionWork());

		if (StringUtils.isNotBlank(form.getBudgetYear())) {
			sql.append(" AND b.BUDGET_YEAR = ? ");
			params.add(form.getBudgetYear());
		}
		sql.append(" ORDER BY a.CREATED_DATE ASC ");

		iaRiskFactorsMasterList = commonJdbcTemplate.query(sql.toString(), params.toArray(), listRowmapper);

		return iaRiskFactorsMasterList;

	}



	private RowMapper<Int030102Vo> listRowmapper = new RowMapper<Int030102Vo>() {
		@Override
		public Int030102Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Int030102Vo vo = new Int030102Vo();
			IaRiskFactorsMaster iarfm = new IaRiskFactorsMaster();
			iarfm.setId(rs.getBigDecimal("ID"));

			iarfm.setRiskFactorsMaster(rs.getString("RISK_FACTORS_MASTER"));
			iarfm.setBudgetYear(rs.getString("BUDGET_YEAR"));
			iarfm.setStatus(rs.getString("STATUS"));

			LocalDateTime createdDate = LocalDateTimeConverter
					.convertToEntityAttribute(rs.getTimestamp("CREATED_DATE"));
			iarfm.setCreatedDate(createdDate);
			iarfm.setCreatedBy(rs.getString("CREATED_BY"));

//			LocalDateTime updatedDate = LocalDateTimeConverter
//					.convertToEntityAttribute(rs.getTimestamp("UPDATED_DATE"));
//			iarfm.setUpdatedDate(updatedDate);
//			iarfm.setUpdatedBy(rs.getString("UPDATED_BY"));
			iarfm.setNotDelete(rs.getString("NOT_DELETE"));

			String date = checkAndConvertDateToString(rs.getDate("CREATED_DATE"));
			vo.setCreatedDateDesc(date);

//			String date2 = checkAndConvertDateToString(rs.getDate("UPDATED_DATE"));
//			vo.setUpdateDateDesc(date2);

			vo.setIaRiskFactorsMaster(iarfm);
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
		StringBuilder sql = new StringBuilder(" UPDATE IA_RISK_FACTORS_STATUS SET STATUS = ? WHERE ID_MASTER = ?");
		String status = ("N".equals(form.getStatus()) ? "Y" : "N");
		commonJdbcTemplate.update(sql.toString(), new Object[] { status, form.getId() });
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

	public void deleteOldFactors(Int030102FormVo form) {
		StringBuilder sql = new StringBuilder(
				" DELETE FROM IA_RISK_FACTORS WHERE BUDGET_YEAR = ? AND INSPECTION_WORK = ? ");
		commonJdbcTemplate.update(sql.toString(), new Object[] { form.getBudgetYear(), form.getInspectionWork() });

	}

	private RowMapper<IaRiskFactorsMaster> listRowmapperSave = new RowMapper<IaRiskFactorsMaster>() {
		@Override
		public IaRiskFactorsMaster mapRow(ResultSet rs, int arg1) throws SQLException {
			IaRiskFactorsMaster vo = new IaRiskFactorsMaster();

			vo.setRiskFactorsMaster(rs.getString("RISK_FACTORS_MASTER"));
			vo.setBudgetYear(rs.getString("BUDGET_YEAR"));
//		    		vo.setStatus(rs.getString("STATUS"));

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
}
