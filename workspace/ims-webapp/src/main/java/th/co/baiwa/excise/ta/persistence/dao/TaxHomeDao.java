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

import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.ta.persistence.vo.TaxHomeFormVo;
import th.co.baiwa.excise.ta.persistence.vo.TaxHomeVo;
import th.co.baiwa.buckwaframework.common.util.BeanUtils; 
import th.co.baiwa.buckwaframework.common.util.OracleUtils;

@Repository
public class TaxHomeDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private final String SQL_SELECT_TYPE = "SELECT * FROM ta_year_plan WHERE ROWNUM <= 5 ";
	private final String SQL_SELECT_TYPE_COUNT = "SELECT COUNT(1) FROM ta_year_plan WHERE  RISK_TYPE = ? ";

	public Long countTableTyp(TaxHomeFormVo taxHomeFormVo) {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeId();
		StringBuilder sql = new StringBuilder(SQL_SELECT_TYPE_COUNT);
		List<Object> params = new ArrayList<>();
		params.add(taxHomeFormVo.getType());
		if (BeanUtils.isNotEmpty(officeCode) && !"00".equals(officeCode.substring(0, 2))) {
			sql.append("AND EXCISE_OFFICE_CODE like ? ");
			if ("00".equals(officeCode.substring(2, 4))) {
				officeCode = officeCode.substring(0, 2) + "____";
			} else if ("00".equals(officeCode.substring(4, 6))) {
				officeCode = officeCode.substring(0, 4) + "__";
			}
			params.add(officeCode);
		}
		sql.append(" AND ROWNUM <= 5");
		sql.append(" ORDER BY CREATED_DATE DESC ");
		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}

	public List<TaxHomeVo> selectType(TaxHomeFormVo taxHomeFormVo) {
		StringBuilder sql = new StringBuilder(SQL_SELECT_TYPE);
		List<Object> params = new ArrayList<>();

		if (StringUtils.isNotBlank(taxHomeFormVo.getType())) {
			sql.append(" WHERE RISK_TYPE = ? ");
			params.add(taxHomeFormVo.getType());
		}
		List<TaxHomeVo> list = jdbcTemplate.query(sql.toString(), params.toArray(), SelectTypeRowMapper);
		return list;
	}

	private RowMapper<TaxHomeVo> SelectTypeRowMapper = new RowMapper<TaxHomeVo>() {
		@Override
		public TaxHomeVo mapRow(ResultSet rs, int arg1) throws SQLException {
			TaxHomeVo vo = new TaxHomeVo();
			vo.setTaYearPlanId(rs.getString("TA_YEAR_PLAN_ID"));
			vo.setUserId(rs.getString("USER_ID"));
			vo.setExciseId(rs.getString("EXCISE_ID"));
			vo.setCompanyName(rs.getString("COMPANY_NAME"));
			vo.setCompanyAddress(rs.getString("COMPANY_ADDRESS"));
			vo.setExciseArea(rs.getString("EXCISE_AREA"));
			vo.setExciseSubArea(rs.getString("EXCISE_SUB_AREA"));
			vo.setProduct(rs.getString("PRODUCT"));
			vo.setRiskType(rs.getString("RISK_TYPE"));
			vo.setFlag(rs.getString("FLAG"));
			vo.setAnalysisNumber(rs.getString("ANALYSIS_NUMBER"));
			vo.setCreatedDate(rs.getDate("CREATED_DATE"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setVersion(rs.getString("VERSION"));
			vo.setIsDeleted(rs.getString("IS_DELETED"));
			return vo;
		}
	};
}
