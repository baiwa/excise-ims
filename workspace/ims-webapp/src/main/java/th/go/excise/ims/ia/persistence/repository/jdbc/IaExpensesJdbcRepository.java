package th.go.excise.ims.ia.persistence.repository.jdbc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaExpenses;
import th.go.excise.ims.ia.vo.Int120401FormVo;

@Repository
public class IaExpensesJdbcRepository {
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	private final String SQL = "SELECT * FROM IA_EXPENSES WHERE IS_DELETED = 'N' ";

	public List<IaExpenses> findByYearByCoa(Int120401FormVo formVo) {
		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();

		if (formVo.getAccountId() != null) {
			sql.append(" AND ACCOUNT_ID = ? ");
			params.add(formVo.getAccountId());
		}

		if (StringUtils.isNotBlank(formVo.getYear())) {
			Integer yearFormInt = Integer.parseInt(formVo.getYear()) - 544;
			Integer yearToInt = Integer.parseInt(formVo.getYear()) - 543;
			String yearFormStr = "01-DEC-" + yearFormInt.toString();
			String yearToStr = "30-NOV-" + yearToInt.toString();
			sql.append(" AND CREATED_DATE >= ? ");
			params.add(yearFormStr);
//			params.add(formVo.getYearFrom());
			sql.append(" AND CREATED_DATE <= ? ");
			params.add(yearToStr);
//			params.add(formVo.getYearTo());
		}

		@SuppressWarnings({ "unchecked", "rawtypes" })
		List<IaExpenses> data = commonJdbcTemplate.query(sql.toString(), params.toArray(),
				new BeanPropertyRowMapper(IaExpenses.class));
		return data;
	}
//
//	public Long count(Int120401FormVo formVo) {
//		StringBuilder sql = new StringBuilder(SQL);
//		List<Object> params = new ArrayList<>();
//
//		if (StringUtils.isNotBlank(formVo.getAccountId())) {
//			sql.append(" AND ACCOUNT_ID LIKE ?");
//			params.add("%" + StringUtils.trim(formVo.getAccountId()) + "%");
//		}
//		if (StringUtils.isNotBlank(formVo.getAccountName())) {
//			sql.append(" AND ACCOUNT_NAME LIKE ? ");
//			params.add("%" + StringUtils.trim(formVo.getAccountName()) + "%");
//		}
//		if (StringUtils.isNotBlank(formVo.getYear())) {
//			sql.append(" AND TO_CHAR(CREATED_DATE,'YYYYMMDD')  bETWEEN  ? AND ? ");
//			params.add(StringUtils.trim(formVo.getYearFrom()));
//			params.add(StringUtils.trim(formVo.getYearTo()));
//		}
//
//		String countSql = OracleUtils.countForDatatable(sql);
//		Long count = commonJdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
//		return count;
//	}
//	
//	public List<Int120401Vo> findAll(Int120401FormVo formVo) {
//		StringBuilder sql = new StringBuilder(SQL);
//		List<Object> params = new ArrayList<>();
//
//		if (StringUtils.isNotBlank(formVo.getAccountId())) {
//			sql.append(" AND ACCOUNT_ID LIKE ?");
//			params.add("%" + StringUtils.trim(formVo.getAccountId()) + "%");
//		}
//		if (StringUtils.isNotBlank(formVo.getAccountName())) {
//			sql.append(" AND ACCOUNT_NAME LIKE ? ");
//			params.add("%" + StringUtils.trim(formVo.getAccountName()) + "%");
//		}
//		if (StringUtils.isNotBlank(formVo.getYear())) {
//			sql.append(" AND TO_CHAR(CREATED_DATE,'YYYYMMDD')  bETWEEN  ? AND ? ");
//			params.add(StringUtils.trim(formVo.getYearFrom()));
//			params.add(StringUtils.trim(formVo.getYearTo()));
//		}
//
//		sql.append(" ORDER BY CREATED_DATE DESC ");
//		String limit = OracleUtils.limit(sql.toString(), formVo.getStart(), formVo.getLength());
//		List<Int120401Vo> list = commonJdbcTemplate.query(limit, params.toArray(), expensesRowmapper);
//		return list;
//
//	}
//	
//	private RowMapper<Int120401Vo> expensesRowmapper = new RowMapper<Int120401Vo>() {
//		@Override
//		public Int120401Vo mapRow(ResultSet rs, int arg1) throws SQLException {
//			Int120401Vo vo = new Int120401Vo();
//
//			vo.setId(rs.getString("ID"));
//			vo.setAccountId(rs.getString("ACCOUNT_ID"));
//			vo.setAccountName(rs.getString("ACCOUNT_NAME"));
//			vo.setServiceReceive(rs.getString("SERVICE_RECEIVE"));
//			vo.setServiceWithdraw(rs.getString("SERVICE_WITHDRAW"));
//			vo.setServiceBalance(rs.getString("SERVICE_BALANCE"));
//			vo.setSuppressReceive(rs.getString("SUPPRESS_RECEIVE"));
//			vo.setSuppressWithdraw(rs.getString("SUPPRESS_WITHDRAW"));
//			vo.setSuppressBalance(rs.getString("SUPPRESS_BALANCE"));
//			vo.setBudgetReceive(rs.getString("BUDGET_RECEIVE"));
//			vo.setBudgetWithdraw(rs.getString("BUDGET_WITHDRAW"));
//			vo.setBudgetBalance(rs.getString("BUDGET_BALANCE"));
//			vo.setSumReceive(rs.getString("SUM_RECEIVE"));
//			vo.setSumWithdraw(rs.getString("SUM_WITHDRAW"));
//			vo.setSumBalance(rs.getString("SUM_BALANCE"));
//			vo.setMoneyBudget(rs.getString("MONEY_BUDGET"));
//			vo.setMoneyOut(rs.getString("MONEY_OUT"));
//			vo.setAverageCost(rs.getString("AVERAGE_COST"));
//			vo.setAverageGive(rs.getString("AVERAGE_GIVE"));
//			vo.setAverageFrom(rs.getString("AVERAGE_FROM"));
//			vo.setAverageComeCost(rs.getString("AVERAGE_COME_COST"));
//			vo.setCreatedBy(rs.getString("CREATED_BY"));
//			vo.setCreatedDate(DateConstant.convertDateToStrDDMMYYYY(rs.getDate("CREATED_DATE")));
//			vo.setNote(rs.getString("NOTE"));
//			return vo;
//		}
//	};

}
