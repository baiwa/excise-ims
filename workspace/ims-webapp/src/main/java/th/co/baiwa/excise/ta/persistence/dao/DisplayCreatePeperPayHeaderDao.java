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
import th.co.baiwa.excise.ta.persistence.vo.Ope0451FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope045Vo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0461FormVo;
import th.co.baiwa.excise.ta.persistence.vo.Ope0462Vo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class DisplayCreatePeperPayHeaderDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	//===================================> Headers <======================================	
	private final String SQL = "SELECT * FROM TA_PDT_DRAWING_WS_HEADER WHERE 1=1 ";

	public Long count(Ope0451FormVo formVo) {

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

	public List<Ope0462Vo> findAll(Ope0451FormVo formVo) {

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
		List<Ope0462Vo> list = jdbcTemplate.query(listSql, params.toArray(), headerRowmapper);
		return list;

	}

	private RowMapper<Ope0462Vo> headerRowmapper = new RowMapper<Ope0462Vo>() {
		@Override
		public Ope0462Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ope0462Vo vo = new Ope0462Vo();

			vo.setTaTaxReduceWsHdrId(rs.getString("TA_PDT_DRAWING_WS_HEADER_ID"));
			vo.setExciseId(rs.getString("EXCISE_ID"));
			vo.setTaAnalysisId(rs.getString("TA_ANALYSIS_ID"));
			vo.setTaxationId(rs.getString("COMPANY_NAME"));
			vo.setStartDate(rs.getString("START_DATE"));
			vo.setEndDate(rs.getString("END_DATE"));
			vo.setPdtType(rs.getString("PDT_TYPE"));
			vo.setSubPdtType(rs.getString("SUB_PDT_TYPE"));

			return vo;
		}
	};

	public List<LabelValueBean> findExciseId() {
		String sql = "SELECT DISTINCT EXCISE_ID FROM TA_PDT_DRAWING_WS_HEADER";
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
	private final String SQL_DETAILS = "SELECT * FROM TA_PDT_DRAWING_WS_DTL WHERE 1=1 ";
	public Long countDetails(Ope0461FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL_DETAILS);
		List<Object> params = new ArrayList<>();
		if (StringUtils.isNotBlank(formVo.getId())) {
			
			sql.append(" AND TA_PDT_DRAWING_WS_HEADER_ID = ? ");
			params.add(formVo.getId());
		}

		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}

	public List<Ope045Vo> findDetails(Ope0461FormVo formVo) {

		StringBuilder sql = new StringBuilder(SQL_DETAILS);
		List<Object> params = new ArrayList<>();

		if (StringUtils.isNotBlank(formVo.getId())) {
			sql.append(" AND TA_PDT_DRAWING_WS_HEADER_ID = ? ");
			params.add(formVo.getId());
		}
		sql.append(" ORDER BY CREATED_DATE DESC ");
		
		List<Ope045Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), detailRowmapper);
		return list;

	}

	private RowMapper<Ope045Vo> detailRowmapper = new RowMapper<Ope045Vo>() {
		@Override
		public Ope045Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ope045Vo vo = new Ope045Vo();

			vo.setOrder(rs.getString("TA_PDT_DRAWING_WS_DTL_ORDER"));
			vo.setAmount1Out(rs.getString("VALUES_IN_BILL"));
			vo.setAmount2Out(rs.getString("VALUES_OF_PRODUCT"));
			vo.setAmountTable2(rs.getString("VALUES_OF_07_04"));
			vo.setAmountMax(rs.getString("MAX_VALUES"));
			vo.setAmountTable3(rs.getString("VALUES_OF_DRAWING"));
			vo.setDiff(rs.getString("RESULT"));

			return vo;
		}
	};

}
