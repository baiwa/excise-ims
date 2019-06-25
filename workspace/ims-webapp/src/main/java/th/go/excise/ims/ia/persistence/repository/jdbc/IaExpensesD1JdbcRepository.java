package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.go.excise.ims.ia.persistence.entity.IaExpensesD1;

@Repository
public class IaExpensesD1JdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<IaExpensesD1> findDetail(String officeCode,String budgetYear,String expenseMonth,String expenseYear,String accountId){
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<>();
		sql.append( "SELECT * ");
		sql.append(" FROM IA_EXPENSES_D1  ");
		sql.append(" WHERE OFFICE_CODE = ? ");
		sql.append(" AND ACCOUNT_ID =? ");
		sql.append(" AND BUDGET_YEAR = ? ");
		sql.append(" AND EXPENSE_MONTH =? ");
		sql.append(" AND EXPENSE_YEAR =? ");
		sql.append(" AND IS_DELETED ='N' ");
		sql.append(" ORDER by DETAIL_SEQ_NO ASC ");
		params.add(officeCode);
		params.add(accountId);
		params.add(budgetYear);
		params.add(expenseMonth);
		params.add(expenseYear);
		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<IaExpensesD1> dataRes = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(IaExpensesD1.class));
		return dataRes;
	}
}
