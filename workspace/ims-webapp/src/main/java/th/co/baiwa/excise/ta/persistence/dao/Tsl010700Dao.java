package th.co.baiwa.excise.ta.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ta.persistence.vo.Tsl010700FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Tsl010700Vo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class Tsl010700Dao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private final String SQL_SEARCH = " SELECT * FROM TA_MONTH_BUDGET WHERE 1=1  ";

	public List<Tsl010700Vo> search(Tsl010700FormVo tsl010700FormVo) {
		StringBuilder sql = new StringBuilder(SQL_SEARCH);
		List<Object> params = new ArrayList<>();
		
		if (StringUtils.isNotBlank(tsl010700FormVo.getDateCalendar())) {
			sql.append(" AND TO_CHAR(MONTH_BUDGET, 'YYYYMM') = ? ");
			Date date = DateConstant.convertStrToDate(tsl010700FormVo.getDateCalendar(), DateConstant.MM_YYYY, DateConstant.LOCAL_TH);
			String dateStr = DateConstant.convertDateToStr(date, "YYYYMM");
			params.add(dateStr);
		}
		
		String sqlLimit = OracleUtils.limitForDataTable(sql, tsl010700FormVo.getStart(), tsl010700FormVo.getLength());
		List<Tsl010700Vo> list = jdbcTemplate.query(sqlLimit, params.toArray(), Tsl010700RowMapper);
		return list;
	}

	public long count(Tsl010700FormVo tsl010700FormVo) {
		StringBuilder sql = new StringBuilder(SQL_SEARCH);
		List<Object> params = new ArrayList<>();
		
		if (StringUtils.isNotBlank(tsl010700FormVo.getDateCalendar())) {
			sql.append(" AND TO_CHAR(MONTH_BUDGET, 'YYYYMM') = ? ");
			Date date = DateConstant.convertStrToDate(tsl010700FormVo.getDateCalendar(), DateConstant.MM_YYYY, DateConstant.LOCAL_TH);
			String dateStr = DateConstant.convertDateToStr(date, "YYYYMM");
			params.add(dateStr);
		}
		
		String countSql = OracleUtils.countForDatatable(sql);
		long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}
	
	private RowMapper<Tsl010700Vo> Tsl010700RowMapper = new RowMapper<Tsl010700Vo>() {
		@Override
		public Tsl010700Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Tsl010700Vo vo = new Tsl010700Vo();
			vo.setTaMonthBudgetId(rs.getString("TA_MONTH_BUDGET_ID"));
			vo.setExciseId(rs.getString("EXCISE_ID"));
			vo.setMonthBudget(rs.getDate("MONTH_BUDGET"));
			vo.setList(rs.getString("LIST"));
			vo.setUnit(rs.getString("UNIT"));
			vo.setStock(rs.getString("STOCK"));
			vo.setReceive(rs.getString("RECEIVE"));
			vo.setReceiveTotal(rs.getString("RECEIVE_TOTAL"));
			vo.setProductInline(rs.getString("PRODUCT_IN_LINE"));
			vo.setProductOutline(rs.getString("PRODUCT_OUT_LINE"));
			vo.setCorrupt(rs.getString("CORRUPT"));
			vo.setOther(rs.getString("OTHER"));
			return vo;
		}
	};

}
