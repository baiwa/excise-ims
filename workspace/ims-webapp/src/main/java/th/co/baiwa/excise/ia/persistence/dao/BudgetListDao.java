package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ia.persistence.entity.BudgetList;

@Repository
public class BudgetListDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<BudgetList> quryBudgetName() {

		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" SELECT DISTINCT L.BUDGET_ID,L.BUDGET_NAME from IA_BUDGET_LIST L ORDER BY L.BUDGET_ID ASC");

		List<BudgetList> budgetList = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingCombobox3);
		return budgetList;
	}

	private RowMapper<BudgetList> fieldMappingCombobox3 = new RowMapper<BudgetList>() {
		@Override
		public BudgetList mapRow(ResultSet rs, int arg1) throws SQLException {
			BudgetList en = new BudgetList();
			en.setBudgetId(rs.getString("BUDGET_ID"));
			en.setBudgetName(rs.getString("BUDGET_NAME"));
			return en;
		}
	};

	public List<BudgetList> getCtgBudget(BudgetList en) {
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" select DISTINCT L.CATEGORY_ID, L.CATEGORY_NAME from IA_BUDGET_LIST L ");
		sql.append(" WHERE L.BUDGET_ID = ? ");
		sql.append(" ORDER BY L.CATEGORY_ID ASC ");
		valueList.add(en.getBudgetId());
		
		List<BudgetList> ctgBudget = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingCombobox4);
		return ctgBudget;
	}
	
	private RowMapper<BudgetList> fieldMappingCombobox4 = new RowMapper<BudgetList>() {
		@Override
		public BudgetList mapRow(ResultSet rs, int arg1) throws SQLException {
			BudgetList en = new BudgetList();
			en.setCategoryId(rs.getString("CATEGORY_ID"));
			en.setCategoryName(rs.getString("CATEGORY_NAME"));
			return en;
		}
	};
	
	public List<BudgetList> getListName(BudgetList en) {
		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" select DISTINCT L.LIST_ID, L.LIST_NAME from IA_BUDGET_LIST L ");
		sql.append(" WHERE L.CATEGORY_ID = ? ");
		sql.append(" ORDER BY L.LIST_ID ASC ");
		valueList.add(en.getCategoryId());
		
		List<BudgetList> listName = jdbcTemplate.query(sql.toString(), valueList.toArray(), fieldMappingCombobox5);
		return listName;
	}
	
	private RowMapper<BudgetList> fieldMappingCombobox5 = new RowMapper<BudgetList>() {
		@Override
		public BudgetList mapRow(ResultSet rs, int arg1) throws SQLException {
			BudgetList en = new BudgetList();
			en.setListId(rs.getString("LIST_ID"));
			en.setListName(rs.getString("LIST_NAME"));
			return en;
		}
	};
}
