package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ia.persistence.entity.IaProcurement;
import th.co.baiwa.buckwaframework.common.util.BeanUtils; 

@Repository
public class ProcurementDao {
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<IaProcurement> oderByfilter(IaProcurement en) {
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT * FROM IA_PROCUREMENT P ");
		sql.append(" WHERE 1 = 1 ");
		
		if(BeanUtils.isNotEmpty(en.getExciseDepartment())) {
			sql.append(" AND P.EXCISE_DEPARTMENT = ? ");
			valueList.add(en.getExciseDepartment());
		}
		if(BeanUtils.isNotEmpty(en.getExciseRegion())) {
			sql.append(" AND P.EXCISE_REGION = ? ");
			valueList.add(en.getExciseRegion());
		}
		if(BeanUtils.isNotEmpty(en.getExciseDistrict())) {
			sql.append(" AND P.EXCISE_DISTRICT = ? ");
			valueList.add(en.getExciseDistrict());
		}
		if(BeanUtils.isNotEmpty(en.getBudgetYear())) {
			sql.append(" AND P.BUDGET_YEAR = ? ");
			valueList.add(en.getBudgetYear());
		}
		if(BeanUtils.isNotEmpty(en.getBudgetType())) {
			sql.append(" AND P.BUDGET_TYPE = ? ");
			valueList.add(en.getBudgetType());
		}
		if(BeanUtils.isNotEmpty(en.getSupplyChoice())) {
			sql.append(" AND P.SUPPLY_CHOICE = ? ");
			valueList.add(en.getSupplyChoice());
		}
		sql.append(" AND P.IS_DELETED = 'N' ");
		sql.append(" ORDER BY P.PROCUREMENT_ID ASC ");
		

		List<IaProcurement> filterList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingFilterList);
		return filterList;
	}

	private RowMapper<IaProcurement> fieldMappingFilterList = new RowMapper<IaProcurement>() {
		@Override
		public IaProcurement mapRow(ResultSet rs, int arg1) throws SQLException {
			IaProcurement vo = new IaProcurement();
			vo.setProcurementId(rs.getLong("PROCUREMENT_ID"));
			vo.setBudgetYear(rs.getString("BUDGET_YEAR"));
			vo.setPoNumber(rs.getString("PO_NUMBER"));
			vo.setProjectCodeEgp(rs.getString("PROJECT_CODE_EGP"));
			vo.setProjectName(rs.getString("PROJECT_NAME"));
			vo.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			return vo;
		}
	};

}
