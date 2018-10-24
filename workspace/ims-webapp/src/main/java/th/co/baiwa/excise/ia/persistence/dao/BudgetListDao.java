package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.entity.BudgetList;
import th.co.baiwa.excise.ia.persistence.vo.Int06101FormVo;

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
	
    public Long add06101 (Int06101FormVo formVo) {
    	Long id = jdbcTemplate.queryForObject(" SELECT IA_WITHDRAWAL_LIST_SEQ.NEXTVAL FROM dual ",long.class);
    	jdbcTemplate.update(" INSERT INTO IA_WITHDRAWAL_LIST (WITHDRAWAL_ID, " + 
    			"REF_NUM, " + 
    			"WITHDRAWAL_DATE, " + 
    			"ACTIVITIES, " + 
    			"BUDGET_ID, " + 
    			"CATEGORY_ID, " + 
    			"BUDGET_TYPE, " + 
    			"WITHDRAWAL_AMOUNT, " + 
    			"SOCIAL_SECURITY, " + 
    			"WITHHOLDING_TAX, " + 
    			"ANOTHER_AMOUNT, " + 
    			"RECEIVED_AMOUNT, " + 
    			"WITHDRAWAL_DOC_NUM, " + 
    			"PAYMENT_DOC_NUM, " + 
    			"ITEM_DESC, " + 
    			"LIST_ID, " + 
    			"NOTE) " + 
    			" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ", new Object[] {
    					id,
    					formVo.getRefnum(),    				
    				DateConstant.convertStrDDMMYYYYToDate(formVo.getWithdrawal()),
    					formVo.getActivity(),
    					formVo.getBudge(),
    					formVo.getCategory(),    					
    					formVo.getBudget(),
    					formVo.getAmountOfMoney(),
    					formVo.getDeductSocial(),
    					formVo.getWithholding(),
    					formVo.getOther(),
    					formVo.getAmountOfMoney1(),
    					formVo.getNumberRequested(),
    					formVo.getDocumentNumber(),
    					formVo.getItemDescription(),
    					formVo.getNote(),
    					formVo.getList()

    					
    					
    			});
    	//jdbcTemplate.update("",new Object[] {});
    	return id;
		    }

	
	
	
	
}
