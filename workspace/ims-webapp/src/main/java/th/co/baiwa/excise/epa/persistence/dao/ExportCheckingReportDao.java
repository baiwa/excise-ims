package th.co.baiwa.excise.epa.persistence.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.util.OracleUtils;
import th.co.baiwa.excise.epa.persistence.vo.Epa014FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa014Vo;

@Repository
public class ExportCheckingReportDao {

	private final String HDR_SQL = "select * from EA_RE_05_01_HDR where 1=1 ";
	private final String DTL_SQL = "select * from EA_RE_05_01_DTL where 1=1 ";
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String SQL_SEARCH_DATA = " SELECT T1.*,  " + 
			"      T2.TAX_RE_05_02_ID TAX_RE_05_02_ID,  " + 
			"      T2.EXCISE_ID EXCISE_ID_2,  " + 
			"      T2.EXCISE_NAME EXCISE_NAME_2,  " + 
			"      T2.PRODUCT_LIST PRODUCT_LIST_2,  " + 
			"      T2.TYPE_LIST TYPE_LIST_2,  " + 
			"      T2.PRODUCT_NAME PRODUCT_NAME_2,  " + 
			"      T2.MODEL MODEL_2,  " + 
			"      T2.SIZE1 SIZE1_2,  " + 
			"      T2.AMOUNT AMOUNT_2,  " + 
			"      T2.PRICE PRICE_2,  " + 
			"      T2.PRICE_PER PRICE_PER_2,  " + 
			"      T2.AMOUNT_PER AMOUNT_PER_2,  " + 
			"      T2.TAX TAX_2,  " + 
			"      T2.DESTINATION DESTINATION_2,  " + 
			"      T2.DATE_DESTINATION DATE_DESTINATION_2,  " + 
			"      T2.TAX_RE_NUMBER TAX_RE_NUMBER_2,  " + 
			"      T2.OFFICE_AREA OFFICE_AREA_2,  " + 
			"      T2.DATE_OUT DATE_OUT_2,  " + 
			"      T3.EA_RE_INVENTORY_HDR_ID EA_RE_INVENTORY_HDR_ID_3,  " + 
			"      T3.EA_RE_INVENTORY_NUMBER EA_RE_INVENTORY_NUMBER_3,  " + 
			"      T3.EA_05_01_NUMBER EA_05_01_NUMBER_3,  " + 
			"      T3.EXPORT_NAME EXPORT_NAME_3,  " + 
			"      T3.EXCISE_OFFICE_SOURCE EXCISE_OFFICE_SOURCE_3,  " + 
			"      T3.CHECK_POINT_DEST CHECK_POINT_DEST_3,  " + 
			"      T3.EXCISE_OFFICE_DEST EXCISE_OFFICE_DEST_3,  " + 
			"      T3.DATE_OUT DATE_OUT_3,  " + 
			"      T3.PRODUCT_TYPE PRODUCT_TYPE_3,  " + 
			"      T3.PRODUCT_AMOUNT PRODUCT_AMOUNT_3,  " + 
			"      T3.VEHICLE_NUMBER VEHICLE_NUMBER_3,  " + 
			"      T3.LOGISTIC_WAY LOGISTIC_WAY_3,  " + 
			"      T3.CREATED_BY CREATED_BY_3,  " + 
			"      T3.CREATED_DATE CREATED_DATE_3,  " + 
			"      T3.UPDATED_BY UPDATED_BY_3,  " + 
			"      T3.UPDATED_DATE UPDATED_DATE_3,  " + 
			"      T3.IS_DELETED IS_DELETED_3,  " + 
			"      T3.VERSION VERSION_3,  " + 
			"      T3.EA_DATA_TYPE EA_DATA_TYPE_3,  " + 
			"      T3.MARKER MARKER_3,  " + 
			"      T3.RESULT RESULT_3,  " + 
			"      T3.COMMENT_1 COMMENT_1_3,  " + 
			"      T3.DATE_IN DATE_IN_3,  " + 
			"      T3.EXCISE_ID EXCISE_ID_3,  " + 
			"      T4.STAMP_NO STAMP_NO_4,  " + 
			"      T4.STAMP_NAME STAMP_NAME_4,  " + 
			"      T4.RESULT RESULT_4,  " + 
			"      T4.COMMENT_1 COMMENT_1_4  " + 
			"    FROM TAX_RE_05_01_1 T1  " + 
			"    JOIN TAX_RE_05_01_2 T2  " + 
			"    ON T1.EXCISE_ID = T2.EXCISE_ID  " + 
			"    JOIN EA_RE_INVENTORY_HDR T3  " + 
			"    ON T1.EXCISE_ID  = T3.EXCISE_ID  " + 
			"    JOIN EA_RE_INVENTORY_DTL T4  " + 
			"    ON T3.EA_RE_INVENTORY_HDR_ID = T4.EA_RE_INVENTORY_HDR_ID  " + 
			"    WHERE 1          =1 ";
	
	public List<Epa014Vo> search(Epa014FormVo epa014FormVo) {
		StringBuilder sql = new StringBuilder(SQL_SEARCH_DATA);
		List<Object> params = new ArrayList<>();
		
		if (StringUtils.isNotBlank(epa014FormVo.getExciseId())) {
			sql.append(" AND T1.EXCISE_ID = ? ");
			params.add(StringUtils.trim(epa014FormVo.getExciseId()));
		}
		
		String sqlLimit = OracleUtils.limitForDataTable(sql, epa014FormVo.getStart(), epa014FormVo.getLength());
		List<Epa014Vo> list = jdbcTemplate.query(sqlLimit, params.toArray(), ExportCheckingReportRowMapper);
		return list;
	}
	
	public long count(Epa014FormVo epa014FormVo) {
		StringBuilder sql = new StringBuilder(SQL_SEARCH_DATA);
		List<Object> params = new ArrayList<>();
		
		if (StringUtils.isNotBlank(epa014FormVo.getExciseId())) {
			sql.append(" AND T1.EXCISE_ID = ? ");
			params.add(StringUtils.trim(epa014FormVo.getExciseId()));
		}
		
		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}
	
	private RowMapper<Epa014Vo> ExportCheckingReportRowMapper = new RowMapper<Epa014Vo>() {
		@Override
		public Epa014Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Epa014Vo vo = new Epa014Vo();
			vo.setEaReInventoryHdrId(rs.getString("EA_RE_INVENTORY_HDR_ID_3"));
			vo.setEaReInventoryNumber(rs.getString("EA_RE_INVENTORY_NUMBER_3"));
			vo.setEa0501Number(rs.getString("EA_05_01_NUMBER_3"));
			vo.setExportName(rs.getString("EXPORT_NAME_3"));
			vo.setExciseOfficeSource(rs.getString("EXCISE_OFFICE_SOURCE_3"));
			vo.setCheckPointDest(rs.getString("CHECK_POINT_DEST_3"));
			vo.setExciseOfficeDest(rs.getString("EXCISE_OFFICE_DEST_3"));
			vo.setDateOut(rs.getString("DATE_OUT_3"));
			vo.setProductType(rs.getString("PRODUCT_TYPE_3"));
			vo.setProductAmount(rs.getString("PRODUCT_AMOUNT_3"));
			vo.setVehicleNumber(rs.getString("VEHICLE_NUMBER_3"));
			vo.setLogisticWay(rs.getString("LOGISTIC_WAY_3"));
			vo.setCreatedBy(rs.getString("CREATED_BY_3"));
			vo.setCreatedDate(rs.getString("CREATED_DATE_3"));
			vo.setUpdatedBy(rs.getString("UPDATED_BY_3"));
			vo.setUpdatedDate(rs.getString("UPDATED_DATE_3"));
			vo.setIsDeleted(rs.getString("IS_DELETED_3"));
			vo.setVersion(rs.getString("VERSION_3"));
			vo.setEaDataType(rs.getString("EA_DATA_TYPE_3"));
			vo.setMarker(rs.getString("MARKER_3"));
			vo.setResult(rs.getString("RESULT_3"));
			vo.setComment1(rs.getString("COMMENT_1_3"));
			vo.setDateIn(rs.getString("DATE_IN_3"));
			vo.setExciseId(rs.getString("EXCISE_ID_3"));
			
			vo.setTaxRe0501Id(rs.getString("TAX_RE_05_01_ID"));
			vo.setTaxReType(rs.getString("TAX_RE_TYPE"));
			vo.setExciseId(rs.getString("EXCISE_ID"));
			vo.setExciseName(rs.getString("EXCISE_NAME"));
			vo.setTaxPlusNo(rs.getString("TAX_PLUS_NO"));
			vo.setExciseNo1(rs.getString("EXCISE_NO_1"));
			vo.setExciseNo1Name(rs.getString("EXCISE_NO_1_NAME"));
			vo.setKodangName(rs.getString("KODANG_NAME"));
			vo.setExportType(rs.getString("EXPORT_TYPE"));
			vo.setExportTypeBy(rs.getString("EXPORT_TYPE_BY"));
			vo.setOfficeArea(rs.getString("OFFICE_AREA"));
			vo.setOfficeSubArea(rs.getString("OFFICE_SUB_AREA"));
			
			vo.setTaxRe0502Id(rs.getString("TAX_RE_05_02_ID"));
			vo.setExciseId2(rs.getString("EXCISE_ID_2"));
			vo.setExciseName2(rs.getString("EXCISE_NAME_2"));
			vo.setProductList2(rs.getString("PRODUCT_LIST_2"));
			vo.setTypeList2(rs.getString("TYPE_LIST_2"));
			vo.setProductName2(rs.getString("PRODUCT_NAME_2"));
			vo.setModel2(rs.getString("MODEL_2"));
			vo.setSize12(rs.getString("SIZE1_2"));
			BigDecimal sizeValue = new BigDecimal(0);
			if (!vo.getSize12().isEmpty()) {
				sizeValue = BigDecimal.valueOf(NumberUtils.toInt(vo.getSize12()));
			}
			vo.setAmount2(rs.getString("AMOUNT_2"));
			BigDecimal amountValue = new BigDecimal(0);
			if (!vo.getAmount2().isEmpty()) {
				amountValue = BigDecimal.valueOf(NumberUtils.toInt(vo.getAmount2()));
			}
			vo.setPrice2(rs.getString("PRICE_2"));
			vo.setPricePer2(rs.getString("PRICE_PER_2"));
			vo.setAmountPer2(rs.getString("AMOUNT_PER_2"));
			vo.setTax2(rs.getString("TAX_2"));
			vo.setDestination2(rs.getString("DESTINATION_2"));
			vo.setDateDestination2(rs.getString("DATE_DESTINATION_2"));
			vo.setTaxReNumber2(rs.getString("TAX_RE_NUMBER_2"));
			vo.setOfficeArea2(rs.getString("OFFICE_AREA_2"));
			vo.setDateOut2(rs.getString("DATE_OUT_2"));
			BigDecimal quantity = sizeValue.multiply(amountValue);
			vo.setQuantity(String.valueOf(quantity));
			
			vo.setStampNo(rs.getString("STAMP_NO_4"));
			vo.setStampName(rs.getString("STAMP_NAME_4"));
			vo.setResultDtl(rs.getString("RESULT_4"));
			vo.setComment1Dtl(rs.getString("COMMENT_1_4"));
			return vo;
		}
	};
	
	private String SQL_GET_INFO = " SELECT T1.EXCISE_NAME EXCISE_NAME,  " + 
			"  T1.EXPORT_TYPE EXPORT_TYPE,  " + 
			"  T2.EXCISE_NAME EXCISE_NAME_2,  " + 
			"  T2.EXCISE_ID EXCISE_ID_2  " + 
			"FROM TAX_RE_05_01_1 T1  " + 
			"JOIN TAX_RE_05_01_2 T2  " + 
			"ON T1.EXCISE_ID = T2.EXCISE_ID  " + 
			"WHERE 1         =1 ";

	public List<Epa014Vo> getInformation(Epa014FormVo epa014FormVo) {
		StringBuilder sql = new StringBuilder(SQL_GET_INFO);
		List<Object> params = new ArrayList<>();
		
		if (StringUtils.isNotBlank(epa014FormVo.getExciseId())) {
			sql.append(" AND T1.EXCISE_ID = ? ");
			params.add(epa014FormVo.getExciseId());
		}
		
		List<Epa014Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), GetInformationRowMapper);
		return list;
	}

	private RowMapper<Epa014Vo> GetInformationRowMapper = new RowMapper<Epa014Vo>() {
		@Override
		public Epa014Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Epa014Vo vo = new Epa014Vo();
			vo.setExciseName(rs.getString("EXCISE_NAME"));
			vo.setExportType(rs.getString("EXPORT_TYPE"));
			vo.setExciseName2(rs.getString("EXCISE_NAME_2"));
			vo.setExciseId2(rs.getString("EXCISE_ID_2"));
			return vo;
		}
	};

}
