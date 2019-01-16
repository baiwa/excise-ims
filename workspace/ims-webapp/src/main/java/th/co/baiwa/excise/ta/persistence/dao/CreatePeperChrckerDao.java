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
import th.co.baiwa.excise.ta.persistence.vo.Ope046FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope046Vo;
import th.co.baiwa.buckwaframework.common.util.OracleUtils;

@Repository
public class CreatePeperChrckerDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String SQL = "SELECT * FROM TA_EXCISE_ACC_05_02 WHERE 1=1 ";
	private final String SQL_DETAIL = "SELECT * FROM TA_EXCISE_ACC_05_02_DTL WHERE 1=1";

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

	public List<Ope046FormVo> findByExciseId(String exciseId) {
		StringBuilder sql = new StringBuilder(SQL);
		sql.append("AND  EXCISE_ID = ? ");
		List<Ope046FormVo> list = jdbcTemplate.query(sql.toString(), new Object[] { exciseId }, createPeperRowmapper);
		return list;
	}

	private RowMapper<Ope046FormVo> createPeperRowmapper = new RowMapper<Ope046FormVo>() {
		@Override
		public Ope046FormVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ope046FormVo vo = new Ope046FormVo();
			vo.setTaExciseAcc0502Id(rs.getString("TA_EXCISE_ACC_05_02_ID"));
			vo.setExciseId(rs.getString("EXCISE_ID"));
			vo.setTaxFeeId(rs.getString("TAX_FEE_ID"));
			vo.setExciseName(rs.getString("EXCISE_NAME"));
			vo.setProductType(rs.getString("PRODUCT_TYPE"));
			return vo;
		}
	};

	public Long count(Ope046FormVo formVo) {

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

	public List<Ope046Vo> findAll(Ope046FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL_DETAIL);
		List<Object> params = new ArrayList<>();

		if (StringUtils.isNotBlank(formVo.getExciseId())) {
			sql.append(" AND EXCISE_ID = ? ");
			params.add(formVo.getExciseId());
		}
		sql.append(" ORDER BY TA_EXCISE_ACC_05_02_DTL_LIST DESC ");
		
		List<Ope046Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), detailRowmapper);
		return list;

	}

	private RowMapper<Ope046Vo> detailRowmapper = new RowMapper<Ope046Vo>() {
		@Override
		public Ope046Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ope046Vo vo = new Ope046Vo();

			vo.setTaExciseAcc0502DtlId(rs.getString("TA_EXCISE_ACC_05_02_DTL_ID"));
			vo.setExciseId(rs.getString("EXCISE_ID"));
			vo.setTaExciseAcc0502DtlList(rs.getString("TA_EXCISE_ACC_05_02_DTL_LIST"));
			vo.setTaxAmount(rs.getBigDecimal("TAX_AMOUNT"));
			vo.setAmount(rs.getBigDecimal("AMOUNT"));
			vo.setTaxPerAmount(rs.getBigDecimal("TAX_PER_AMOUNT"));

			return vo;
		}
	};

}
