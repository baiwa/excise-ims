package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfigAll;
import th.go.excise.ims.ia.vo.Int0301FormVo;

@Repository
public class Int030103JdbcRepository {

	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;
	
	public void listUpdatePercent(IaRiskFactorsConfig form) {		
		StringBuilder sql = new StringBuilder("UPDATE IA_RISK_FACTORS_CONFIG SET PERCENT = ? WHERE ID = ? ");
		commonJdbcTemplate.update(sql.toString(), new Object[] { form.getPercent() , form.getId() });
	}
	
	
	public List<IaRiskFactorsConfigAll> listConfigAll(IaRiskFactorsConfigAll form) {
		List<IaRiskFactorsConfigAll> response = new ArrayList<IaRiskFactorsConfigAll>();
		StringBuilder sql = new StringBuilder("SELECT * FROM IA_RISK_FACTORS_CONFIG_ALL WHERE INSPECTION_WORK = ?");	
		List<Object> params = new ArrayList<Object>();
		params.add(form.getInspectionWork());

		if (StringUtils.isNotBlank(form.getBudgetYear())) {
			sql.append(" AND BUDGET_YEAR = ? ");
			params.add(form.getBudgetYear());
		}
		response = commonJdbcTemplate.query(sql.toString(), params.toArray(), listConfigAllRowmapper);
		return response;		
	}
	
	private RowMapper<IaRiskFactorsConfigAll> listConfigAllRowmapper = new RowMapper<IaRiskFactorsConfigAll>() {
		@Override
		public IaRiskFactorsConfigAll mapRow(ResultSet rs, int arg1) throws SQLException {
			IaRiskFactorsConfigAll vo = new IaRiskFactorsConfigAll();		
			vo.setBudgetYear(rs.getString("BUDGET_YEAR"));
			vo.setInspectionWork(rs.getBigDecimal("INSPECTION_WORK"));
			
			vo.setHighStart(rs.getString("HIGH_START"));
			

		
			return vo;
		}
	};
}
