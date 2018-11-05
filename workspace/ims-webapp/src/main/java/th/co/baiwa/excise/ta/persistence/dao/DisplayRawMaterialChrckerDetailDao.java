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

import th.co.baiwa.excise.ta.persistence.vo.Ope0412Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0461FormVo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class DisplayRawMaterialChrckerDetailDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String SQL = "SELECT * FROM TA_RECEIVE_RMAT_WS_DETAIL WHERE 1=1 ";


	public Long count(Ope0461FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();
		if (StringUtils.isNotBlank(formVo.getTaTaxReduceWsHdrId())) {
			
			sql.append(" AND TA_RECEIVE_RMAT_HEADER_ID = ? ");
			params.add(formVo.getTaTaxReduceWsHdrId());
		}

		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}

	public List<Ope0412Vo> findAll(Ope0461FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();

		if (StringUtils.isNotBlank(formVo.getTaTaxReduceWsHdrId())) {
			sql.append(" AND TA_RECEIVE_RMAT_HEADER_ID = ? ");
			params.add(formVo.getTaTaxReduceWsHdrId());
		}
		sql.append(" ORDER BY CREATED_DATE DESC ");
		
		List<Ope0412Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), detailRowmapper);
		return list;

	}

	private RowMapper<Ope0412Vo> detailRowmapper = new RowMapper<Ope0412Vo>() {
		@Override
		public Ope0412Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ope0412Vo vo = new Ope0412Vo();

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
