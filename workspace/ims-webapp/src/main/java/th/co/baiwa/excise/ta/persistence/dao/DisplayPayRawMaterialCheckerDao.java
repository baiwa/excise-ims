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
import th.co.baiwa.excise.ta.persistence.vo.Ope0421Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0422Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0461FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0462FormVo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class DisplayPayRawMaterialCheckerDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String SQL = "SELECT * FROM TA_DISB_RMAT_WORKSHEET_HEADER WHERE 1=1 ";

	public Long count(Ope0462FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();
		if (StringUtils.isNotBlank(formVo.getExciseId())) {
			sql.append(" AND EXCISE_ID = ? ");
			params.add(formVo.getExciseId());
		}

		if (StringUtils.isNotBlank(formVo.getStartDate())) {
			sql.append(" AND START_DATE = ? ");
			params.add(formVo.getStartDate());
		}
		if (StringUtils.isNotBlank(formVo.getEndDate())) {
			sql.append(" AND END_DATE = ? ");
			params.add(formVo.getEndDate());
		}
		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}

	public List<Ope0421Vo> findAll(Ope0462FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL);
		List<Object> params = new ArrayList<>();

		if (StringUtils.isNotBlank(formVo.getExciseId())) {
			sql.append(" AND EXCISE_ID = ? ");
			params.add(formVo.getExciseId());
		}

		if (StringUtils.isNotBlank(formVo.getStartDate())) {
			sql.append(" AND START_DATE = ? ");
			params.add(formVo.getStartDate());
		}
		if (StringUtils.isNotBlank(formVo.getEndDate())) {
			sql.append(" AND END_DATE = ? ");
			params.add(formVo.getEndDate());
		}
		sql.append(" ORDER BY CREATED_DATE DESC ");

		String listSql = OracleUtils.limit(sql.toString(), formVo.getStart(),formVo.getLength());
		List<Ope0421Vo> list = jdbcTemplate.query(listSql, params.toArray(), headerRowmapper);
		return list;

	}

	private RowMapper<Ope0421Vo> headerRowmapper = new RowMapper<Ope0421Vo>() {
		@Override
		public Ope0421Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ope0421Vo vo = new Ope0421Vo();

			vo.setTaTaxReduceWsHdrId(rs.getString("TA_DISBURSE_RMAT_HEADER_ID"));
			vo.setExciseId(rs.getString("EXCISE_ID"));
			vo.setTaAnalysisId(rs.getString("TA_ANALYSIS_ID"));
			vo.setTaxationId(rs.getString("TAXATION_ID"));
			vo.setStartDate(rs.getString("START_DATE"));
			vo.setEndDate(rs.getString("END_DATE"));
			vo.setPdtType(rs.getString("PRODUCT_TYPE"));
			vo.setSubPdtType(rs.getString("SUB_PRODUCT_TYPE"));

			return vo;
		}
	};

	public List<LabelValueBean> findExciseId() {
		String sql = "SELECT DISTINCT EXCISE_ID FROM TA_DISB_RMAT_WORKSHEET_HEADER";
		List<LabelValueBean> list = jdbcTemplate.query(sql, exciseIdRowmapper);
		return list;

	}

	private RowMapper<LabelValueBean> exciseIdRowmapper = new RowMapper<LabelValueBean>() {
		@Override
		public LabelValueBean mapRow(ResultSet rs, int arg1) throws SQLException {
			return new LabelValueBean(rs.getString("EXCISE_ID"), rs.getString("EXCISE_ID"));
		}
	};
	
	//===================================> Details <======================================
		private final String SQL_DETAILS = "SELECT * FROM TA_DISB_RMAT_WORKSHEET_DETAIL WHERE 1=1 ";
		public Long countDetails(Ope0461FormVo formVo) {

			StringBuilder sql = new StringBuilder(SQL_DETAILS);
			List<Object> params = new ArrayList<>();
			if (StringUtils.isNotBlank(formVo.getId())) {
				
				sql.append(" AND TA_DISBURSE_RMAT_HEADER_ID = ? ");
				params.add(formVo.getId());
			}

			String countSql = OracleUtils.countForDatatable(sql);
			Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
			return count;
		}

		public List<Ope0422Vo> findDetails(Ope0461FormVo formVo) {

			StringBuilder sql = new StringBuilder(SQL_DETAILS);
			List<Object> params = new ArrayList<>();

			if (StringUtils.isNotBlank(formVo.getId())) {
				sql.append(" AND TA_DISBURSE_RMAT_HEADER_ID = ? ");
				params.add(formVo.getId());
			}
			sql.append(" ORDER BY CREATED_DATE DESC ");
			
			List<Ope0422Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), detailRowmapper);
			return list;

		}

		private RowMapper<Ope0422Vo> detailRowmapper = new RowMapper<Ope0422Vo>() {
			@Override
			public Ope0422Vo mapRow(ResultSet rs, int arg1) throws SQLException {
				Ope0422Vo vo = new Ope0422Vo();

				vo.setOrder(rs.getString("DISBURSE_RAW_MAT_DTL_ORDER"));
				vo.setTaxInv(rs.getString("RAW_MAT_REQUISITION"));
				vo.setDaybook(rs.getString("DAY_BOOK_07_01"));
				vo.setMonthBook(rs.getString("MONTH_BOOK_07_04"));
				vo.setExternalData(rs.getString("MAX_VALUES"));
				vo.setMaxalues(rs.getString("MONTHLY_REPORT"));
				vo.setResult(rs.getString("RESULT"));

				return vo;
			}
		};

}
