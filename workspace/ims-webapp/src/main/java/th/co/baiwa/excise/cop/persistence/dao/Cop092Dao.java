package th.co.baiwa.excise.cop.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.util.BeanUtils;
import th.co.baiwa.excise.cop.persistence.vo.Cop092BudgetFormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop092BudgetVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop092ProductFormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop092ProductVo;

@Repository
public class Cop092Dao {

	private static final Logger log = LoggerFactory.getLogger(Cop092Dao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String SQL_SELECT_PRODUCT = "SELECT P.* FROM TA_MONTH_PRODUCT P WHERE 1=1 ";
	private final String SQL_SELECT_BUDGET = "SELECT B.* FROM TA_MONTH_BUDGET B WHERE 1=1 ";

	public List<Cop092ProductVo> findAll(Cop092ProductFormVo formVo) {
		StringBuilder sql = new StringBuilder(SQL_SELECT_PRODUCT);
		List<Object> params = new ArrayList<>();
		if (StringUtils.isNotBlank(formVo.getExcise())) {
			sql.append(" AND P.EXCISE = ? ");
			params.add(formVo.getExcise());
		}

		if (BeanUtils.isNotNull(formVo.getMonthBuget())) {
			sql.append(" AND extract(month from to_date(P.MONTH_BUDGET,'dd/mm/yyyy')) = extract(month from to_date(?,'dd/mm/yyyy')) ");
			params.add(formVo.getMonthBuget());
		}

		List<Cop092ProductVo> list = jdbcTemplate.query(sql.toString(), params.toArray(), cop092ProductRowmapper);
		return list;
	}
	
	public List<Cop092BudgetVo> findAll(Cop092BudgetFormVo formVo) {
		StringBuilder sql = new StringBuilder(SQL_SELECT_BUDGET);
		List<Object> params = new ArrayList<>();

		if (StringUtils.isNotBlank(formVo.getExcise())) {
			sql.append(" AND B.EXCISE_ID = ? ");
			params.add(formVo.getExcise());
		}

		if (BeanUtils.isNotNull(formVo.getMonthBuget())) {
			sql.append(" AND extract(month from to_date(B.MONTH_BUDGET,'dd/mm/yyyy')) = extract(month from to_date(?,'dd/mm/yyyy')) ");
			params.add(formVo.getMonthBuget());
		}

		List<Cop092BudgetVo> list = jdbcTemplate.query(sql.toString(), params.toArray(), cop092BudgetVoRowmapper);
		return list;
	}

	private RowMapper<Cop092ProductVo> cop092ProductRowmapper = new RowMapper<Cop092ProductVo>() {
		@Override
		public Cop092ProductVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Cop092ProductVo vo = new Cop092ProductVo();
			vo.setTaMonthProductId(rs.getString("TA_MONTH_PRODUCT_ID"));
			vo.setExcise(rs.getString("EXCISE"));
			vo.setMonthBudget(rs.getDate("MONTH_BUDGET"));
			vo.setList(rs.getString("LIST"));
			vo.setUnit(rs.getString("UNIT"));
			vo.setStock(rs.getString("STOCK"));
			vo.setGetPro(rs.getString("GET_PRO"));
			vo.setReceive(rs.getString("RECEIVE"));
			vo.setOther(rs.getString("OTHER"));
			vo.setReceiveTotal(rs.getString("RECEIVE_TOTAL"));
			vo.setDomDist(rs.getString("DOM_DIST"));
			vo.setForeignSale(rs.getString("FOREIGN_SALE"));
			vo.setInHouseUse(rs.getString("IN_HOUSE_USE"));
			vo.setWarehouse(rs.getString("WAREHOUSE"));
			vo.setCorrupt(rs.getString("CORRUPT"));
			vo.setOther1(rs.getString("OTHER_1"));
			vo.setTotal(rs.getString("TOTAL"));
			return vo;
		}
	};
	
	private RowMapper<Cop092BudgetVo> cop092BudgetVoRowmapper = new RowMapper<Cop092BudgetVo>() {
		@Override
		public Cop092BudgetVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Cop092BudgetVo vo = new Cop092BudgetVo();
			vo.setTaMonthBudgetId(rs.getString("TA_MONTH_BUDGET_ID"));
			vo.setExciseId(rs.getString("EXCISE_ID"));
			vo.setMonthBudget(rs.getDate("MONTH_BUDGET"));
			vo.setList(rs.getString("LIST"));
			vo.setUnit(rs.getString("UNIT"));
			vo.setStock(rs.getString("STOCK"));
			vo.setReceive(rs.getString("RECEIVE"));
			vo.setOther(rs.getString("OTHER"));
			vo.setReceiveTotal(rs.getString("RECEIVE_TOTAL"));
			vo.setProductInLine(rs.getString("PRODUCT_IN_LINE"));
			vo.setCorrupt(rs.getString("CORRUPT"));
			vo.setOther(rs.getString("OTHER"));
			return vo;
		}
	};

}
