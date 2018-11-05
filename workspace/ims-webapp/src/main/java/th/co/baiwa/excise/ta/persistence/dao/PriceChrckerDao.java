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

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.ta.persistence.vo.Ope048FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope048Vo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class PriceChrckerDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String SQL_EXCISE_ID = "SELECT * FROM TA_EXCISE_ACC_03_07_HDR WHERE 1=1 ";
	private final String SQL_DETAIL = "SELECT * FROM TA_EXCISE_ACC_03_07_DTL WHERE 1=1";

	public List<LabelValueBean> findExciseIdAll() {
		List<LabelValueBean> list = jdbcTemplate.query(SQL_EXCISE_ID, exciseIdRowmapper);
		return list;
	}

	private RowMapper<LabelValueBean> exciseIdRowmapper = new RowMapper<LabelValueBean>() {
		@Override
		public LabelValueBean mapRow(ResultSet rs, int arg1) throws SQLException {
			return new LabelValueBean(rs.getString("EXCISE_ID"), rs.getString("EXCISE_ID"));
		}
	};

	public List<Ope048FormVo> findByExciseId(String exciseId) {
		StringBuilder sql = new StringBuilder(SQL_EXCISE_ID);
		sql.append("AND  EXCISE_ID = ? ");
		List<Ope048FormVo> list = jdbcTemplate.query(sql.toString(), new Object[] { exciseId }, createPeperRowmapper);
		return list;
	}

	private RowMapper<Ope048FormVo> createPeperRowmapper = new RowMapper<Ope048FormVo>() {
		@Override
		public Ope048FormVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ope048FormVo vo = new Ope048FormVo();
			vo.setTaExciseAcc0502Id(rs.getString("TA_EXCISE_ACC_03_07_HDR_ID"));
			vo.setExciseId(rs.getString("EXCISE_ID"));
			vo.setTaxFeeId(rs.getString("TAX_FEE_ID"));
			vo.setExciseName(rs.getString("EXCISE_NAME"));
			vo.setProductType(rs.getString("PRODUCT_TYPE"));
			return vo;
		}
	};

	 public Long count(Ope048FormVo formVo) {

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

	public List<Ope048Vo> findAll(Ope048FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL_DETAIL);
		List<Object> params = new ArrayList<>();

		if (StringUtils.isNotBlank(formVo.getExciseId())) {
			sql.append(" AND EXCISE_ID = ? ");
			params.add(formVo.getExciseId());
		}
		sql.append(" ORDER BY EXCISE_ID DESC ");
		
		List<Ope048Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), detailRowmapper);
		return list;

	}

	private RowMapper<Ope048Vo> detailRowmapper = new RowMapper<Ope048Vo>() {
		@Override
		public Ope048Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ope048Vo vo = new Ope048Vo();

			vo.setTa_exciseAcc0307DtlId(rs.getString("TA_EXCISE_ACC_03_07_DTL_ID"));
			vo.setExciseId(rs.getString("EXCISE_ID"));
			vo.setTaExciseAcc0307List(rs.getString("TA_EXCISE_ACC_03_07_LIST"));
			vo.setTaExcisePrice(rs.getBigDecimal("TA_EXCISE_PRICE"));
			vo.setTaExciseAcc0307Price(rs.getBigDecimal("TA_EXCISE_ACC_03_07_PRICE"));
			vo.setTaExciseAcc0307Price1(rs.getBigDecimal("TA_EXCISE_ACC_03_07_PRICE_1"));

			return vo;
		}
	};

}
