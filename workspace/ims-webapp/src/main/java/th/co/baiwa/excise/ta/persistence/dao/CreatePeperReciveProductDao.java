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
import th.co.baiwa.excise.ta.persistence.vo.Ope044FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope044Vo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class CreatePeperReciveProductDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String SQL = "SELECT * FROM TA_EXCISE_ACC_MONTH_04_07_DTL WHERE 1=1 ";

	public Long count(Ope044FormVo formVo) {

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

	public List<Ope044Vo> findAll(Ope044FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();

		if (StringUtils.isNotBlank(formVo.getExciseId())) {
			sql.append(" AND EXCISE_ID = ? ");
			params.add(formVo.getExciseId());
		}		
		
		List<Ope044Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), headerRowmapper);
		return list;

	}

	private RowMapper<Ope044Vo> headerRowmapper = new RowMapper<Ope044Vo>() {
		@Override
		public Ope044Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ope044Vo vo = new Ope044Vo();
			
			vo.setOrder(rs.getString("PRODUCT_1"));
			vo.setAmount1(rs.getString("AMOUNT_1"));
			vo.setAmount2(rs.getString("AMOUNT_2"));
			
			return vo;
		}
	};
	
	
	public List<LabelValueBean> exciseidList() {
		String sql = "SELECT DISTINCT EXCISE_ID FROM TA_PLAN_WORK_SHEET_HEADER WHERE FLAG='F' ";
		List<LabelValueBean> list = jdbcTemplate.query(sql, exciseIdRowmapper);
		return list;

	}

	private RowMapper<LabelValueBean> exciseIdRowmapper = new RowMapper<LabelValueBean>() {
		@Override
		public LabelValueBean mapRow(ResultSet rs, int arg1) throws SQLException {
			return new LabelValueBean(rs.getString("EXCISE_ID"), rs.getString("EXCISE_ID"));
		}
	};
	
	
	public List<Ope044FormVo> findByExciseId(String exciseId) {
		StringBuilder sql = new StringBuilder("SELECT * FROM TA_PLAN_WORK_SHEET_HEADER WHERE FLAG='F' ");
		sql.append("AND  EXCISE_ID = ? ");
		List<Ope044FormVo> list = jdbcTemplate.query(sql.toString(), new Object[] { exciseId }, createPeperRowmapper);
		return list;
	}

	private RowMapper<Ope044FormVo> createPeperRowmapper = new RowMapper<Ope044FormVo>() {
		@Override
		public Ope044FormVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ope044FormVo vo = new Ope044FormVo();
			vo.setTaExciseAcc0502Id(rs.getString("WORK_SHEET_HEADER_ID"));
			vo.setExciseId(rs.getString("EXCISE_ID"));
			vo.setTaxFeeId(rs.getString("ANALYS_NUMBER"));
			vo.setExciseName(rs.getString("COMPANY_NAME"));
			vo.setProductType(rs.getString("PRODUCT_TYPE"));
			return vo;
		}
	};

}
