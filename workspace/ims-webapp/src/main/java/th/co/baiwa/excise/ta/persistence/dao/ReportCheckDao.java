package th.co.baiwa.excise.ta.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.ta.persistence.vo.Ope04011FormVo;

@Repository
public class ReportCheckDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<LabelValueBean> findExciseId() {
		String sql = "select DISTINCT EXCISE_ID from TA_PLAN_WORK_SHEET_HEADER where flag='F' ";
		List<LabelValueBean> list = jdbcTemplate.query(sql, exciseIdRowmapper);
		return list;

	}

	private RowMapper<LabelValueBean> exciseIdRowmapper = new RowMapper<LabelValueBean>() {
		@Override
		public LabelValueBean mapRow(ResultSet rs, int arg1) throws SQLException {
			return new LabelValueBean(rs.getString("EXCISE_ID"), rs.getString("EXCISE_ID"));
		}
	};

	public List<Ope04011FormVo> findByExciseId(Ope04011FormVo formVo) {		
		StringBuilder sql = new StringBuilder("SELECT * FROM ");
		sql.append("(SELECT * FROM TA_PLAN_WORK_SHEET_HEADER WHERE FLAG='F' ORDER BY  ANALYS_NUMBER DESC ) WHERE 1=1 ");
		sql.append("AND EXCISE_ID = ? AND ROWNUM=1");
		 List<Ope04011FormVo> list = jdbcTemplate.query(sql.toString(), new Object[]{formVo.getExciseId()}, detailRowmapper);
		return list;

	}

	private RowMapper<Ope04011FormVo> detailRowmapper = new RowMapper<Ope04011FormVo>() {
		@Override
		public Ope04011FormVo mapRow(ResultSet rs, int arg1) throws SQLException {
			Ope04011FormVo vo = new Ope04011FormVo();
			
			vo.setAnlysisNumber(rs.getString("ANALYS_NUMBER"));				
			vo.setType(rs.getString("EXCISE_ID").substring(14, 15));
			vo.setCompanyName(rs.getString("COMPANY_NAME"));
			vo.setSubtype(rs.getString("PRODUCT_TYPE"));
			

			return vo;
		}
	};

}
