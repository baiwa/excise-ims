package th.co.baiwa.excise.epa.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.epa.persistence.vo.Epa012FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa012Vo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class ExportCheckingConnectDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String SQL_SEARCH_DATA = " SELECT * FROM TAX_RE_05_01_2 WHERE 1=1 ";
	
	public List<Epa012Vo> search(Epa012FormVo epa012FormVo) {
		StringBuilder sql = new StringBuilder(SQL_SEARCH_DATA);
		List<Object> params = new ArrayList<>();
		
		if (StringUtils.isNotBlank(epa012FormVo.getExciseId())) {
			sql.append(" AND EXCISE_ID = ? ");
			params.add(epa012FormVo.getExciseId());
		}
		
		String sqlLimit = OracleUtils.limitForDataTable(sql, epa012FormVo.getStart(), epa012FormVo.getLength());
		List<Epa012Vo> list = jdbcTemplate.query(sqlLimit, params.toArray(), ExportCheckingConnectRowMapper);
		return list;
	}
	
	private RowMapper<Epa012Vo> ExportCheckingConnectRowMapper = new RowMapper<Epa012Vo>() {
		@Override
		public Epa012Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Epa012Vo vo = new Epa012Vo();
			vo.setTypeList(rs.getString("TYPE_LIST"));
			vo.setProductName(rs.getString("PRODUCT_NAME"));
			vo.setModel(rs.getString("MODEL"));
			vo.setSize(rs.getString("SIZE1"));
			vo.setAmount(rs.getString("AMOUNT"));
			vo.setPrice(rs.getString("PRICE"));
			vo.setPricePerTax(rs.getString("PRICE_PER"));
			vo.setAmountPer(rs.getString("AMOUNT_PER"));
			vo.setTax(rs.getString("TAX"));
			return vo;
		}
		
	};

}
