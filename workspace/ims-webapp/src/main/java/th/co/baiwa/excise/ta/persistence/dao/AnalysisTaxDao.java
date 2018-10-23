package th.co.baiwa.excise.ta.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.domain.LabelValueBean;
import th.co.baiwa.excise.ta.persistence.entity.analysis.PlanWorksheetHeader;

@Repository
public class AnalysisTaxDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String SQL = "SELECT * FROM TA_PLAN_WORK_SHEET_HEADER WHERE FLAG != 'N' AND IS_DELETED='N' ";

	public List<LabelValueBean> findAllExciseIdNotN() {
		String sql = "SELECT EXCISE_ID FROM  TA_PLAN_WORK_SHEET_HEADER WHERE FLAG='F' ORDER BY EXCISE_ID ASC";
		
		List<LabelValueBean> list = jdbcTemplate.query(sql, exciseIdRowmapper);
		return list;
	}

	private RowMapper<LabelValueBean> exciseIdRowmapper = new RowMapper<LabelValueBean>() {
		@Override
		public LabelValueBean mapRow(ResultSet rs, int arg1) throws SQLException {
			return new LabelValueBean(rs.getString("EXCISE_ID"), rs.getString("EXCISE_ID"));
		}
	};

	public List<PlanWorksheetHeader> findByExciseId(String exciseId) {
		StringBuilder sql = new StringBuilder(SQL);
		sql.append("AND FLAG='F' AND EXCISE_ID = ? ");		
		List<PlanWorksheetHeader> list = jdbcTemplate.query(sql.toString(), new Object[] {exciseId},planWorksheetRowmapper);
		return list;
	}

	private RowMapper<PlanWorksheetHeader> planWorksheetRowmapper = new RowMapper<PlanWorksheetHeader>() {
		@Override
		public PlanWorksheetHeader mapRow(ResultSet rs, int arg1) throws SQLException {
			PlanWorksheetHeader vo = new PlanWorksheetHeader();
			vo.setAnalysNumber(rs.getString("ANALYS_NUMBER"));
			vo.setExciseId(rs.getString("EXCISE_ID"));
			vo.setCompanyName(rs.getString("COMPANY_NAME"));
			vo.setFactoryName(rs.getString("FACTORY_NAME"));
			vo.setFactoryAddress(rs.getString("FACTORY_ADDRESS"));
			vo.setProductType(rs.getString("PRODUCT_TYPE"));
			vo.setExciseOwnerArea1(rs.getString("EXCISE_OWNER_AREA_1"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			return vo;
		}
	};
}
