package th.go.excise.ims.mockup.persistence.dao.ta;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.go.excise.ims.mockup.persistence.entity.ta.PlanWorksheetHeader;

@Repository
public class PlanWorksheetHeaderDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	
	
	public String getAnalysNumber() {
		String sql = "SELECT TO_CHAR(ANALYS_NUMBER_SEQ.nextval, '00000') as SEQ FROM DUAL";
		Map<String, Object> map = jdbcTemplate.queryForMap(sql);
		String analysNumber = map != null ? (String)map.get("SEQ") : null;
		return analysNumber.trim();
	}
	
	
	public List<PlanWorksheetHeader> queryPlanWorksheetHeaderCriteria(PlanWorksheetHeader criteria) {

		List<Object> valueList = new ArrayList<Object>();
		StringBuilder sql = new StringBuilder(" select * from TA_PLAN_WORK_SHEET_HEADER ");
		sql.append(" where 1 = 1 ");

		if (criteria.getAnalysNumber() != null && criteria.getAnalysNumber().length() > 0) {
			sql.append(" and ANALYS_NUMBER = ? ");
			valueList.add(criteria.getAnalysNumber());

		}

		List<PlanWorksheetHeader> planWorksheetHeaderList = jdbcTemplate.query(sql.toString(), valueList.toArray(),
				fieldMapping);
		return planWorksheetHeaderList;

	}

	public void insertPlanWorksheetHeader(PlanWorksheetHeader value) {
		//inti SQL for insert to database
		StringBuilder sql = new StringBuilder(" INSERT INTO TA_PLAN_WORK_SHEET_HEADER (WORK_SHEET_HEADER_ID,ANALYS_NUMBER,EXCISE_ID,COMPANY_NAME,FACTORY_NAME,FACTORY_ADDRESS,EXCISE_OWNER_AREA,PRODUCT_TYPE,EXCISE_OWNER_AREA_1,TOTAL_AMOUNT,PERCENTAGE,TOTAL_MONTH,DECIDE_TYPE,FLAG,CREATED_BY,CREATED_DATETIME,UPDATE_BY,UPDATE_DATETIME,FIRST_MONTH,LAST_MONTH)"); 
		sql.append(" values(TA_PLAN_WS_HEADER_SEQ.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ");
//		sql.append(" values(TA_PLAN_WS_HEADER_SEQ.nectval,'"+value.getAnalysNumber()+"','"+value.getExciseId()+"','"+value.getCompanyName()+"','"+value.getFactoryName()+"','"+value.getFactoryAddress()+"',"
//				+ "'"+value.getExciseOwnerArea()+"','"+value.getProductType()+"','"+value.getExciseOwnerArea1()+"',"+value.getTotalAmount()+","+value.getPercentage()+","
//						+ "'"+value.getTotalMonth()+"','"+value.getDecideType()+"','"+value.getFlag()+"','"+value.getCreateBy()+"',SYSDATE,null,null,'"+value.getFirstMonth()+"','"+value.getLastMonth()+"') ");
		System.out.println(sql);
		//for to set Object
		jdbcTemplate.update(sql.toString() ,planWorksheetHeaderToArrayObject(value) );
//		jdbcTemplate.update(sql.toString()  );
	}

	
	
	private Object[] planWorksheetHeaderToArrayObject(PlanWorksheetHeader value) {

		List<Object> valueList = new ArrayList<Object>();
		if (value != null) {
			valueList.add(value.getAnalysNumber());
			valueList.add(value.getExciseId());
			valueList.add(value.getCompanyName());
			valueList.add(value.getFactoryName());
			valueList.add(value.getFactoryAddress());
			valueList.add(value.getExciseOwnerArea());
			valueList.add(value.getProductType());
			valueList.add(value.getExciseOwnerArea1());
			valueList.add(value.getTotalAmount());
			valueList.add(value.getPercentage());
			valueList.add(value.getTotalMonth());
			valueList.add(value.getDecideType());
			valueList.add(value.getFlag());
			valueList.add(value.getCreateBy());
			valueList.add(value.getCreateDatetime());
			valueList.add(value.getUpdateBy());
			valueList.add(value.getUpdateDatetime());
			valueList.add(value.getFirstMonth());
			valueList.add(value.getLastMonth());
		}
		return valueList.toArray();
	}

	private RowMapper<PlanWorksheetHeader> fieldMapping = new RowMapper<PlanWorksheetHeader>() {
		@Override
		public PlanWorksheetHeader mapRow(ResultSet rs, int arg1) throws SQLException {
			PlanWorksheetHeader header = new PlanWorksheetHeader();
			header.setWorksheetHeaderId(rs.getBigDecimal("WORK_SHEET_HEADER_ID"));
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
			header.setFirstMonth(rs.getBigDecimal("FIRST_MONTH"));
			header.setLastMonth(rs.getBigDecimal("LAST_MONTH"));
			header.setCreateBy(rs.getString("CREATED_BY"));
			header.setCreateDatetime(rs.getDate("CREATED_DATETIME"));
			header.setUpdateBy(rs.getString("UPDATE_BY"));
			header.setUpdateDatetime(rs.getTime("UPDATE_DATETIME"));
			return header;
		}
	};

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

}
