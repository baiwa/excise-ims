package th.go.excise.ims.oa.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.persistence.jdbc.CommonJdbcTemplate;
import th.co.baiwa.buckwaframework.common.persistence.util.OracleUtils;
import th.go.excise.ims.oa.vo.Oa0206FormVo;
import th.go.excise.ims.oa.vo.Oa0206Vo;

@Repository
public class Oa0206JdbcRepository {
	
	@Autowired
	private CommonJdbcTemplate commonJdbcTemplate;

	public List<Oa0206Vo> getData(Oa0206FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
//		sql.append(" SELECT C.OA_CUSTOMER_ID , C.COMPANY_NAME , C.ADDRESS ,C.IDENTIFY_TYPE, CL.OA_CUSLICENSE_ID,CL.LICENSE_TYPE,CL.START_DATE,CL.END_DATE,CL.APPROVE  ");
//		sql.append(" ,CL.LICENSE_NO,C.IDENTIFY_NO ,C.WAREHOUSE_ADDRESS FROM OA_CUSTOMER C INNER JOIN OA_CUSTOMER_LICEN CL ON C.OA_CUSTOMER_ID = CL.OA_CUSTOMER_ID  ");
		sql.append(" SELECT * FROM OA_CUSTOMER_LICEN ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		sql.append(" ORDER BY START_DATE DESC");

		String limit = OracleUtils.limitForDatable(sql.toString(), request.getStart(), request.getLength());
		@SuppressWarnings({ "rawtypes", "unchecked" })
		List<Oa0206Vo> datas = this.commonJdbcTemplate.query(limit, params.toArray(),dataRowmapper);
		
		return datas;
	}
	
	public Integer countData(Oa0206FormVo request) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
//		sql.append(" SELECT C.OA_CUSTOMER_ID , C.COMPANY_NAME , C.ADDRESS ,C.IDENTIFY_TYPE, CL.OA_CUSLICENSE_ID,CL.LICENSE_TYPE,CL.START_DATE,CL.END_DATE,CL.APPROVE  ");
//		sql.append(" ,CL.LICENSE_NO,C.IDENTIFY_NO ,C.WAREHOUSE_ADDRESS FROM OA_CUSTOMER C INNER JOIN OA_CUSTOMER_LICEN CL ON C.OA_CUSTOMER_ID = CL.OA_CUSTOMER_ID  ");
		sql.append(" SELECT * FROM OA_CUSTOMER_LICEN ");
		sql.append(" WHERE IS_DELETED = 'N' ");
		sql.append(" ORDER BY START_DATE DESC");
		String sqlCount = OracleUtils.countForDataTable(sql.toString());
		Integer count = this.commonJdbcTemplate.queryForObject(sqlCount, params.toArray(), Integer.class);

		return count;
	}
	
	public Oa0206Vo getCustomerLicenseById(String licenseId) {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
//		sql.append(" SELECT C.OA_CUSTOMER_ID , C.COMPANY_NAME , C.ADDRESS ,C.IDENTIFY_TYPE, CL.OA_CUSLICENSE_ID,CL.LICENSE_TYPE,CL.START_DATE,CL.END_DATE,CL.APPROVE  ");
//		sql.append(" ,CL.LICENSE_NO,C.IDENTIFY_NO ,C.WAREHOUSE_ADDRESS FROM OA_CUSTOMER C INNER JOIN OA_CUSTOMER_LICEN CL ON C.OA_CUSTOMER_ID = CL.OA_CUSTOMER_ID  ");
		sql.append(" SELECT * FROM OA_CUSTOMER_LICEN ");
		sql.append(" WHERE IS_DELETED = 'N'  AND OA_CUSLICENSE_ID = ? ");
		
		params.add(licenseId);

//		String limit = OracleUtils.limitForDatable(sql.toString(), request.getStart(), request.getLength());
//		@SuppressWarnings({ "rawtypes", "unchecked" })
		Oa0206Vo data = this.commonJdbcTemplate.queryForObject( sql.toString(),params.toArray(),dataRowmapper);
		
		return data;
	}
	
	
	private RowMapper<Oa0206Vo> dataRowmapper = new RowMapper<Oa0206Vo>() {
		@Override
		public Oa0206Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Oa0206Vo vo = new Oa0206Vo();
			vo.setAddress(rs.getString("ADDRESS"));
//			vo.setCustomerId(rs.getBigDecimal("OA_CUSTOMER_ID"));
			vo.setOaCusLicenseId(rs.getBigDecimal("OA_CUSLICENSE_ID"));
			vo.setApprove(rs.getString("APPROVE"));
			vo.setCompanyName(rs.getString("COMPANY_NAME"));
			vo.setIdentityType(rs.getString("IDENTIFY_TYPE"));
			vo.setStartDate(rs.getDate("START_DATE"));
			vo.setEndDate(rs.getDate("END_DATE"));
			vo.setLicenseType(rs.getString("LICENSE_TYPE"));
			vo.setLicenseNo(rs.getString("LICENSE_NO"));
			vo.setIdentifyNo(rs.getString("IDENTIFY_NO"));
			vo.setWarehouseAddress(rs.getString("WAREHOUSE_ADDRESS"));
			return vo;
		}
	};


}