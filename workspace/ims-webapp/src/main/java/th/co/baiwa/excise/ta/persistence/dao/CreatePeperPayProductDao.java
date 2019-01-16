package th.co.baiwa.excise.ta.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.ta.persistence.vo.Ope045FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope045Vo;
import th.co.baiwa.buckwaframework.common.util.OracleUtils;

@Repository
public class CreatePeperPayProductDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String SQL = "SELECT d2.*,d3.MONTH_RECIEVE MONTH_RECIEVE_T3 FROM"
			+ " (SELECT * FROM TA_EXCISE_ACC_MONTH0407D2 WHERE EXCISE_ID= ? ) d2"
			+ " INNER JOIN TA_EXCISE_ACC_MONTH0407D3 d3" + " ON d2.PRODUCT=d3.PRODUCT";

	public Long count(Ope045FormVo formVo) {

		List<Object> params = new ArrayList<>();
		params.add(formVo.getExciseId());
		String countSql = OracleUtils.countForDatatable(SQL);
		Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}

	public List<Ope045Vo> findAll(Ope045FormVo formVo) {

		List<Object> params = new ArrayList<>();
		params.add(formVo.getExciseId());

		List<Ope045Vo> list = jdbcTemplate.query(SQL, params.toArray(), headerRowmapper);
		return list;

	}

	private RowMapper<Ope045Vo> headerRowmapper = new RowMapper<Ope045Vo>() {
		@Override
		public Ope045Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ope045Vo vo = new Ope045Vo();

			vo.setId(rs.getString("ID"));
			vo.setOrder(rs.getString("PRODUCT"));
			vo.setAmountTable2(rs.getString("MONTH_RECIEVE"));
			vo.setAmountTable3(rs.getString("MONTH_RECIEVE_T3"));			

			return vo;
		}
	};

	public List<LabelValueBean> exciseidList() {
		String sql = "SELECT DISTINCT EXCISE_ID FROM TA_EXCISE_ACC_05_02 ";
		List<LabelValueBean> list = jdbcTemplate.query(sql, exciseIdRowmapper);
		return list;

	}

	private RowMapper<LabelValueBean> exciseIdRowmapper = new RowMapper<LabelValueBean>() {
		@Override
		public LabelValueBean mapRow(ResultSet rs, int arg1) throws SQLException {
			return new LabelValueBean(rs.getString("EXCISE_ID"), rs.getString("EXCISE_ID"));
		}
	};

	public List<Ope045FormVo> findByExciseId(String exciseId) {
		StringBuilder sql = new StringBuilder("SELECT * FROM TA_EXCISE_ACC_05_02 WHERE 1=1 ");
		sql.append("AND  EXCISE_ID = ? ");
		List<Ope045FormVo> list = jdbcTemplate.query(sql.toString(), new Object[] { exciseId }, createPeperRowmapper);
		return list;
	}

	private RowMapper<Ope045FormVo> createPeperRowmapper = new RowMapper<Ope045FormVo>() {
		@Override
		public Ope045FormVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ope045FormVo vo = new Ope045FormVo();
			vo.setTaExciseAcc0502Id(rs.getString("TA_EXCISE_ACC_05_02_ID"));
			vo.setExciseId(rs.getString("EXCISE_ID"));
			vo.setTaxFeeId(rs.getString("TAX_FEE_ID"));
			vo.setExciseName(rs.getString("EXCISE_NAME"));
			vo.setProductType(rs.getString("PRODUCT_TYPE"));
			return vo;
		}
	};

}
