package th.co.baiwa.excise.ta.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ta.persistence.vo.Ope0461Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope046FormVo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class DisplayCreatePeperChrckerDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String SQL = "SELECT * FROM TA_TAX_REDUCE_WS_DTL WHERE 1=1 ";


	public Long count(Ope046FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();
		if (StringUtils.isNotBlank(formVo.getExciseId())) {
			
			sql.append(" AND EXCISE_ID = ? ");
			params.add(formVo.getExciseId());
		}

		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}

	public List<Ope0461Vo> findAll(Ope046FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();

		if (StringUtils.isNotBlank(formVo.getExciseId())) {
			sql.append(" AND EXCISE_ID = ? ");
			params.add(formVo.getExciseId());
		}
		sql.append(" ORDER BY CREATED_DATE DESC ");
		
		//String listSql = OracleUtils.limit(sql.toString(), formVo.getStart(), formVo.getLength());
		List<Ope0461Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), detailRowmapper);
		return list;

	}

	private RowMapper<Ope0461Vo> detailRowmapper = new RowMapper<Ope0461Vo>() {
		@Override
		public Ope0461Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ope0461Vo vo = new Ope0461Vo();

			vo.setList(rs.getString("LIST"));
			vo.setTotalTax(rs.getBigDecimal("TOTAL_TAX"));
			vo.setPdtAmount1(rs.getBigDecimal("PDT_AMOUNT_1"));
			vo.setTaxPerPdt(rs.getBigDecimal("TAX_PER_PDT"));
			vo.setBillNo(rs.getString("BILL_NO"));
			vo.setTaxAmount(rs.getBigDecimal("TAX_AMOUNT"));
			vo.setPdtSAmount2(rs.getBigDecimal("PDT_AMOUNT_2"));
			vo.setMaxValues(rs.getBigDecimal("MAX_VALUES"));
			vo.setResult(rs.getBigDecimal("RESULT"));

			return vo;
		}
	};

}
