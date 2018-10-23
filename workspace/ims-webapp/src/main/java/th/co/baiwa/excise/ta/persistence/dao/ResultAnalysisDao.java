package th.co.baiwa.excise.ta.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ta.persistence.vo.ResultAnalysisRequestvo;
import th.co.baiwa.excise.ta.persistence.vo.ResultAnalysisVo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class ResultAnalysisDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	/*ProdcutsTax*/
	public Long countfindProdcutsTax(ResultAnalysisRequestvo vo) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT T1.PS_03_07_LIST ,T1.PS_03_07_DETAIL, T2.PS_07_04_DETAIL , (T1.PS_03_07_DETAIL - T2.PS_07_04_DETAIL) DIFF FROM TA_PS_03_07 T1");
		sql.append(" INNER JOIN TA_PS_07_04 T2 ON T1.EXCISE_ID = T2.EXCISE_ID AND T1.PS_03_07_LIST = T2.PS_07_04_LIST");
        sql.append(" WHERE T1.EXCISE_ID = ? ORDER BY T1.EXCISE_ID DESC");
        
		String countSql = OracleUtils.countForDatatable(sql.toString());
		Long count = jdbcTemplate.queryForObject(countSql, new Object[] {vo.getExciseId()}, Long.class);
		return count;
	}

	public List<ResultAnalysisVo> findProdcutsTax(ResultAnalysisRequestvo vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT T1.PS_03_07_LIST ,T1.PS_03_07_DETAIL, T2.PS_07_04_DETAIL , (T1.PS_03_07_DETAIL - T2.PS_07_04_DETAIL) DIFF FROM TA_PS_03_07 T1");
		sql.append(" INNER JOIN TA_PS_07_04 T2 ON T1.EXCISE_ID = T2.EXCISE_ID AND T1.PS_03_07_LIST = T2.PS_07_04_LIST");
        sql.append(" WHERE T1.EXCISE_ID = ? ORDER BY T1.EXCISE_ID DESC");        
		
		List<ResultAnalysisVo> list = jdbcTemplate.query(sql.toString(), new Object[] {vo.getExciseId()}, prodcutsTaxamRowmapper);
		return list;

	}

	private RowMapper<ResultAnalysisVo> prodcutsTaxamRowmapper = new RowMapper<ResultAnalysisVo>() {
		@Override
		public ResultAnalysisVo mapRow(ResultSet rs, int arg1) throws SQLException {
			ResultAnalysisVo vo = new ResultAnalysisVo();
			vo.setOrder(rs.getString("PS_03_07_LIST"));
			vo.setDataTax0307(rs.getString("PS_03_07_DETAIL"));
			vo.setBudget(rs.getString("PS_07_04_DETAIL"));
			vo.setDifference(rs.getString("DIFF"));
					
			return vo;
		}
	};
	
	/*Price*/
	public Long countfindPrice(ResultAnalysisRequestvo vo) {

		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT T1.PS_03_07_LIST ,T1.PS_03_07_DETAIL, T2.PS_02_01_DETAIL , (T1.PS_03_07_DETAIL - T2.PS_02_01_DETAIL) DIFF FROM TA_PS_03_07 T1");
		sql.append(" INNER JOIN TA_PS_02_01 T2 ON T1.EXCISE_ID = T2.EXCISE_ID AND T1.PS_03_07_LIST=T2.PS_02_01_LIST");
        sql.append(" WHERE T1.EXCISE_ID = ? ORDER BY T1.EXCISE_ID DESC");

		String countSql = OracleUtils.countForDatatable(sql.toString());
		Long count = jdbcTemplate.queryForObject(countSql, new Object[] {vo.getExciseId()}, Long.class);
		return count;
	}

	public List<ResultAnalysisVo> findPrice(ResultAnalysisRequestvo vo) {
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT T1.PS_03_07_LIST ,T1.PS_03_07_DETAIL, T2.PS_02_01_DETAIL , (T1.PS_03_07_DETAIL - T2.PS_02_01_DETAIL) DIFF FROM TA_PS_03_07 T1");
		sql.append(" INNER JOIN TA_PS_02_01 T2 ON T1.EXCISE_ID = T2.EXCISE_ID AND T1.PS_03_07_LIST=T2.PS_02_01_LIST");
        sql.append(" WHERE T1.EXCISE_ID = ? ORDER BY T1.EXCISE_ID DESC");

		List<ResultAnalysisVo> list = jdbcTemplate.query(sql.toString(), new Object[] {vo.getExciseId()}, priceRowmapper);
		return list;

	}

	private RowMapper<ResultAnalysisVo> priceRowmapper = new RowMapper<ResultAnalysisVo>() {
		@Override
		public ResultAnalysisVo mapRow(ResultSet rs, int arg1) throws SQLException {
			ResultAnalysisVo vo = new ResultAnalysisVo();
			vo.setOrder(rs.getString("PS_03_07_LIST"));
			vo.setDataTax0307(rs.getString("PS_03_07_DETAIL"));
			vo.setBudget(rs.getString("PS_02_01_DETAIL"));
			vo.setDifference(rs.getString("DIFF"));
					
			return vo;
		}
	};
		
	/*ValueProductTax*/
	public Long countfindValueProductTax(ResultAnalysisRequestvo vo) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT T1.PS_03_07_LIST ,T1.PS_03_07_DETAIL, T2.RE_RETAIL_PRICE_DETAIL , (T1.PS_03_07_DETAIL - T2.RE_RETAIL_PRICE_DETAIL) DIFF FROM TA_PS_03_07 T1");
		sql.append(" INNER JOIN TA_RE_RETAIL_PRICE T2 ON T1.EXCISE_ID = T2.EXCISE_ID AND T1.PS_03_07_LIST=T2.RE_RETAIL_PRICE_LIST");
        sql.append(" WHERE T1.EXCISE_ID = ? ORDER BY T1.EXCISE_ID DESC");

		String countSql = OracleUtils.countForDatatable(sql.toString());
		Long count = jdbcTemplate.queryForObject(countSql, new Object[] {vo.getExciseId()}, Long.class);
		return count;
	}

	public List<ResultAnalysisVo> findValueProductTax(ResultAnalysisRequestvo vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT T1.PS_03_07_LIST ,T1.PS_03_07_DETAIL, T2.RE_RETAIL_PRICE_DETAIL , (T1.PS_03_07_DETAIL - T2.RE_RETAIL_PRICE_DETAIL) DIFF FROM TA_PS_03_07 T1");
		sql.append(" INNER JOIN TA_RE_RETAIL_PRICE T2 ON T1.EXCISE_ID = T2.EXCISE_ID AND T1.PS_03_07_LIST=T2.RE_RETAIL_PRICE_LIST");
        sql.append("  WHERE T1.EXCISE_ID = ? ORDER BY T1.EXCISE_ID DESC");
		
		List<ResultAnalysisVo> list = jdbcTemplate.query(sql.toString(), new Object[] {vo.getExciseId()}, valueProductTaxRowmapper);
		return list;

	}

	private RowMapper<ResultAnalysisVo> valueProductTaxRowmapper = new RowMapper<ResultAnalysisVo>() {
		@Override
		public ResultAnalysisVo mapRow(ResultSet rs, int arg1) throws SQLException {
			ResultAnalysisVo vo = new ResultAnalysisVo();
			vo.setOrder(rs.getString("PS_03_07_LIST"));
			vo.setDataTax0307(rs.getString("PS_03_07_DETAIL"));
			vo.setBudget(rs.getString("RE_RETAIL_PRICE_DETAIL"));
			vo.setDifference(rs.getString("DIFF"));
					
			return vo;
		}
	};
	
	/*tax rate*/
	public Long countfindTaxRate(ResultAnalysisRequestvo vo) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT T1.PS_03_07_LIST ,T1.PERCENTAGE PERCENTAGE_03_07, T2.PERCENTAGE PERCENTAGE_02_01, (T1.PERCENTAGE - T2.PERCENTAGE) DIFF  FROM TA_PS_03_07 T1");
		sql.append(" INNER JOIN  TA_PS_02_01 T2 ON T1.EXCISE_ID = T2.EXCISE_ID AND T1.PS_03_07_LIST=T2.PS_02_01_LIST");
        sql.append(" WHERE T1.EXCISE_ID = ? ORDER BY T1.EXCISE_ID DESC");

		String countSql = OracleUtils.countForDatatable(sql.toString());
		Long count = jdbcTemplate.queryForObject(countSql, new Object[] {vo.getExciseId()}, Long.class);
		return count;
	}

	public List<ResultAnalysisVo> findTaxRate(ResultAnalysisRequestvo vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT T1.PS_03_07_LIST ,T1.PERCENTAGE PERCENTAGE_03_07, T2.PERCENTAGE PERCENTAGE_02_01, (T1.PERCENTAGE - T2.PERCENTAGE) DIFF  FROM TA_PS_03_07 T1");
		sql.append(" INNER JOIN  TA_PS_02_01 T2 ON T1.EXCISE_ID = T2.EXCISE_ID AND T1.PS_03_07_LIST=T2.PS_02_01_LIST");
        sql.append(" WHERE T1.EXCISE_ID = ? ORDER BY T1.EXCISE_ID DESC");
		
		List<ResultAnalysisVo> list = jdbcTemplate.query(sql.toString(), new Object[] {vo.getExciseId()}, taxRateRowmapper);
		return list;

	}

	private RowMapper<ResultAnalysisVo> taxRateRowmapper = new RowMapper<ResultAnalysisVo>() {
		@Override
		public ResultAnalysisVo mapRow(ResultSet rs, int arg1) throws SQLException {
			ResultAnalysisVo vo = new ResultAnalysisVo();
			vo.setOrder(rs.getString("PS_03_07_LIST"));
			vo.setDataTax0307(rs.getString("PERCENTAGE_03_07"));
			vo.setBudget(rs.getString("PERCENTAGE_02_01"));
			vo.setDifference(rs.getString("DIFF"));
					
			return vo;
		}
	};
	

	/*Submit payment*/
	public Long countfindSubmitPayment(ResultAnalysisRequestvo vo) {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT T1.PS_03_07_LIST ,t1.TAX_CAL,t2.TAX_PAY   ,(t2.TAX_PAY - t1.TAX_CAL) diff FROM TA_PS_03_07 T1");
		sql.append(" INNER JOIN  TA_TAX_PAY t2 ON T1.EXCISE_ID = t2.EXCISE_ID AND T1.PS_03_07_LIST=T2.TA_TAX_PAY_LIST");
        sql.append(" WHERE T1.EXCISE_ID = ? ORDER BY T1.EXCISE_ID DESC");

		String countSql = OracleUtils.countForDatatable(sql.toString());
		Long count = jdbcTemplate.queryForObject(countSql, new Object[] {vo.getExciseId()}, Long.class);
		return count;
	}

	public List<ResultAnalysisVo> findSubmitPayment(ResultAnalysisRequestvo vo) {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT T1.PS_03_07_LIST ,t1.TAX_CAL,t2.TAX_PAY   ,(t2.TAX_PAY - t1.TAX_CAL) diff FROM TA_PS_03_07 T1");
		sql.append(" INNER JOIN  TA_TAX_PAY t2 ON T1.EXCISE_ID = t2.EXCISE_ID AND T1.PS_03_07_LIST=T2.TA_TAX_PAY_LIST");
        sql.append(" WHERE T1.EXCISE_ID = ? ORDER BY T1.EXCISE_ID DESC");
        
		List<ResultAnalysisVo> list = jdbcTemplate.query(sql.toString(), new Object[] {vo.getExciseId()}, submitPaymentRowmapper);
		return list;

	}

	private RowMapper<ResultAnalysisVo> submitPaymentRowmapper = new RowMapper<ResultAnalysisVo>() {
		@Override
		public ResultAnalysisVo mapRow(ResultSet rs, int arg1) throws SQLException {
			ResultAnalysisVo vo = new ResultAnalysisVo();
			vo.setOrder(rs.getString("PS_03_07_LIST"));
			vo.setDataTax0307(rs.getString("TAX_PAY"));
			vo.setBudget(rs.getString("TAX_CAL"));
			vo.setDifference(rs.getString("DIFF"));
					
			return vo;
		}
	};

}
