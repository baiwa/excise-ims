package th.co.baiwa.excise.ta.persistence.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.co.baiwa.excise.ta.persistence.entity.PlanFromWsHeader;
import th.co.baiwa.excise.ta.persistence.entity.PlanWorksheetDetail;
import th.co.baiwa.excise.ta.persistence.entity.PlanWorksheetHeaderDetail;
import th.co.baiwa.excise.utils.BeanUtils;

public class PlanFromWsHeaderRepositoryImpl implements PlanFromWsHeaderCustom {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final CommonJdbcTemplate commonJdbcTemplate;
	
	@Autowired
	private  JdbcTemplate jdbcTemplate;

	@Autowired
	public PlanFromWsHeaderRepositoryImpl(CommonJdbcTemplate commonJdbcTemplate) {
		this.commonJdbcTemplate = commonJdbcTemplate;
	}

	@Override
	public List<PlanWorksheetHeaderDetail> findExciseIdOrderByPercenTax(List<String> monthLIst) {

		logger.info("findExciseIdOrderByPercenTax");
		List<Object> params = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT H.EXCISE_ID EXCISE_ID, ");
		sql.append(" H.EXCISE_OWNER_AREA EXCISE_OWNER_AREA, ");
		sql.append(" H.COMPANY_NAME COMPANY_NAME, ");
		sql.append(" SUM(D.AMOUNT) / COUNT(1) AVG ");
		sql.append(" FROM TA_PLAN_FROM_WS_HEADER H ");
		sql.append(" INNER JOIN TA_PLAN_FROM_WS_DETAIL D ");
		sql.append(" ON H.EXCISE_ID = D.EXCISE_ID ");
		sql.append(" WHERE D.MONTH IN (");
		for (int i = 0; i < monthLIst.size(); i++) {
			sql.append("?");
			if (i < monthLIst.size()) {
				sql.append(",");
			}
			params.add(monthLIst.get(i));
		}
		sql.append(") ");
		sql.append(" GROUP BY H.EXCISE_ID , ");
		sql.append(" H.EXCISE_OWNER_AREA , ");
		sql.append(" H.COMPANY_NAME ");
		sql.append(" ORDER BY AVG DESC ");
		return commonJdbcTemplate.executeQuery(sql.toString(), params.toArray(), mappingPlanWorksheetHeaderDetail);

	}

	private RowMapper<PlanWorksheetHeaderDetail> mappingPlanWorksheetHeaderDetail = new RowMapper<PlanWorksheetHeaderDetail>() {
		@Override
		public PlanWorksheetHeaderDetail mapRow(ResultSet rs, int arg1) throws SQLException {
			PlanWorksheetHeaderDetail planWorksheetHeaderDetail = new PlanWorksheetHeaderDetail();
			planWorksheetHeaderDetail.setExciseId(rs.getString("EXCISE_ID"));
			planWorksheetHeaderDetail.setExciseOwnerArea(rs.getString("EXCISE_OWNER_AREA"));
			planWorksheetHeaderDetail.setCompanyName(rs.getString("COMPANY_NAME"));
			planWorksheetHeaderDetail.setPercentage(rs.getBigDecimal("AVG"));
			return planWorksheetHeaderDetail;
		}
	};

	@Override
	public List<PlanFromWsHeader> findHeaderFromTemp(List<String> monthLIst, String analysNumber) {
		List<Object> params = new ArrayList<Object>();
		String userLogin = UserLoginUtils.getCurrentUsername();
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT DISTINCT ");
		sql.append(" '"+analysNumber+"' ANALYS_NUMBER , ");
		sql.append(" H.EXCISE_ID , ");
		sql.append(" H.COMPANY_NAME , ");
		sql.append(" H.FACTORY_NAME , ");
		sql.append(" H.FACTORY_ADDRESS , ");
		sql.append(" H.EXCISE_OWNER_AREA , ");
		sql.append(" H.PRODUCT_TYPE , ");
		sql.append(" H.EXCISE_OWNER_AREA_1 , ");
		sql.append(" H.TOTAL_AMOUNT , ");
		sql.append(" H.PERCENTAGE , ");
		sql.append(" H.TOTAL_MONTH , ");
		sql.append(" H.DECIDE_TYPE , ");
		sql.append(" 'S' FLAG, ");
		sql.append(" '"+userLogin+"' CREATED_BY , ");
		sql.append(" sysdate CREATED_DATE , ");
		sql.append(" null UPDATED_BY, ");
		sql.append(" null UPDATED_DATE, ");
		sql.append(" H.FIRST_MONTH , ");
		sql.append(" H.LAST_MONTH , ");
		sql.append(" H.WORK_SHEET_NUMBER , ");
		sql.append(" 'N' IS_DELETED, ");
		sql.append(" H.MONTH_DATE , ");
		sql.append(" H.FULL_MONTH , ");
		sql.append(" 1 VERSION , ");
		sql.append(" null VIEW_STATUS, ");
		sql.append(" null CENTRAL, ");
		sql.append(" null SECTOR");
		sql.append(" FROM TA_PLAN_FROM_WS_HEADER H ");
		sql.append(" LEFT JOIN TA_PLAN_FROM_WS_DETAIL D ");
		sql.append(" ON H.EXCISE_ID = D.EXCISE_ID ");
		if(BeanUtils.isNotEmpty(monthLIst)) {
			sql.append(" WHERE D.MONTH IN (" );
			for (int i = 0; i < monthLIst.size(); i++) {
				sql.append("?");
				if (i < monthLIst.size()-1) {
					sql.append(",");
				}
				params.add(monthLIst.get(i));
			}
			sql.append(") " ) ;
		}
		return commonJdbcTemplate.executeQuery(sql.toString(), params.toArray(), fieldMapping);
	}
	private RowMapper<PlanFromWsHeader> fieldMapping = new RowMapper<PlanFromWsHeader>() {
		@Override
		public PlanFromWsHeader mapRow(ResultSet rs, int arg1) throws SQLException {
			PlanFromWsHeader header = new PlanFromWsHeader();
			//header.setWorkSheetHeaderId(rs.getLong("TA_PLAN_WORK_SHEET_HEADER_ID"));
			header.setWorkSheetNumber(rs.getString("WORK_SHEET_NUMBER"));
			header.setAnalysNumber(rs.getString("ANALYS_NUMBER"));
			header.setExciseId(rs.getString("EXCISE_ID"));
			header.setCompanyName(rs.getString("COMPANY_NAME"));
			header.setFactoryName(rs.getString("FACTORY_NAME"));
			header.setFactoryAddress(rs.getString("FACTORY_ADDRESS"));
			header.setExciseOwnerArea(rs.getString("EXCISE_OWNER_AREA"));
			header.setProductType(rs.getString("PRODUCT_TYPE"));
			header.setExciseOwnerArea1(rs.getString("EXCISE_OWNER_AREA_1"));
			header.setTotalAmount(rs.getBigDecimal("TOTAL_AMOUNT"));
			header.setPercentage(rs.getBigDecimal("PERCENTAGE"));
			header.setTotalMonth(rs.getBigDecimal("TOTAL_MONTH"));
			header.setDecideType(rs.getString("DECIDE_TYPE"));
			header.setFlag(rs.getString("FLAG"));
			header.setViewStatus(rs.getString("VIEW_STATUS"));
			header.setFirstMonth(rs.getBigDecimal("FIRST_MONTH"));
			header.setLastMonth(rs.getBigDecimal("LAST_MONTH"));
			header.setCreatedBy(rs.getString("CREATED_BY"));
			header.setCreatedDate(rs.getDate("CREATED_DATE"));
			header.setUpdatedBy(rs.getString("UPDATED_BY"));
			header.setUpdatedDate(rs.getDate("UPDATED_DATE"));
			header.setFullMonth(rs.getBigDecimal("FULL_MONTH"));
			header.setMonthDate(rs.getString("MONTH_DATE"));
			header.setCentral(rs.getString("CENTRAL"));
			header.setSector(rs.getString("SECTOR"));
			return header;
		}
	};
	
	
	
	
}
