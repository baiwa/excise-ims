package th.co.baiwa.excise.cop.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.cop.persistence.vo.Cop061FormVo;
import th.co.baiwa.excise.cop.persistence.vo.Cop061Vo;
import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class ReportCheckOperationDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String SQL =        " SELECT * FROM OA_TAX_REDUCE_WS_HDR WHERE 1=1 ";
	private final String SQL_DETAIL = " SELECT * FROM OA_TAX_REDUCE_WS_DTL_MONEY WHERE 1=1 ";

	public List<LabelValueBean> findExciseIdAll() {
		List<LabelValueBean> list = jdbcTemplate.query(SQL, exciseIdRowmapper);
		return list;
	}

	private RowMapper<LabelValueBean> exciseIdRowmapper = new RowMapper<LabelValueBean>() {
		@Override
		public LabelValueBean mapRow(ResultSet rs, int arg1) throws SQLException {
			return new LabelValueBean(rs.getString("EXCISE_ID"), rs.getString("EXCISE_ID"));
		}
	};

	public List<Cop061FormVo> findByExciseId(String exciseId) {
		StringBuilder sql = new StringBuilder(SQL);
		sql.append("AND  EXCISE_ID = ? ");
		List<Cop061FormVo> list = jdbcTemplate.query(sql.toString(), new Object[] { exciseId }, createPeperRowmapper);
		return list;
	}

	private RowMapper<Cop061FormVo> createPeperRowmapper = new RowMapper<Cop061FormVo>() {
		@Override
		public Cop061FormVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Cop061FormVo vo = new Cop061FormVo();
			vo.setTaExciseAcc0502Id(rs.getString("TAX_REDUCE_WS_HDR_ID"));
			vo.setExciseId(rs.getString("EXCISE_ID"));
			vo.setTaxFeeId(rs.getString("TAXATION_ID"));
			vo.setExciseName(rs.getString("EXCISE_NAME"));
			vo.setProductType(rs.getString("PRODUCT_TYPE"));
			return vo;
		}
	};

	public Long count(Cop061FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL_DETAIL);
		List<Object> params = new ArrayList<>();
		if (StringUtils.isNotBlank(formVo.getExciseId())) {
			
			sql.append(" AND EXCISE_ID = ? ");
			params.add(formVo.getExciseId());
		}

		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}

	public List<Cop061Vo> findAll(Cop061FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL_DETAIL);
		List<Object> params = new ArrayList<>();

		if (StringUtils.isNotBlank(formVo.getExciseId())) {
			sql.append(" AND EXCISE_ID = ? ");
			params.add(formVo.getExciseId());
		}
		sql.append(" ORDER BY LIST DESC ");
		
		List<Cop061Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), detailRowmapper);
		return list;

	}

	private RowMapper<Cop061Vo> detailRowmapper = new RowMapper<Cop061Vo>() {
		@Override
		public Cop061Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Cop061Vo vo = new Cop061Vo();

			vo.setTaExciseAcc0502DtlId(rs.getString("ID"));
			vo.setExciseId(rs.getString("EXCISE_ID"));
			vo.setTaExciseAcc0502DtlList(rs.getString("LIST"));
			vo.setTaxAmount(rs.getBigDecimal("TAX_AMOUNT"));
			vo.setAmount(rs.getBigDecimal("AMOUNT"));
			vo.setTaxPerAmount(rs.getBigDecimal("TAX_PER_AMOUNT"));

			return vo;
		}
	};

}
