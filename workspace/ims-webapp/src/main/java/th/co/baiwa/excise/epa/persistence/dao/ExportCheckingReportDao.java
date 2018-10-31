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

import th.co.baiwa.excise.epa.persistence.vo.Epa014FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa014Vo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class ExportCheckingReportDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private String SQL_SEARCH_DATA = " SELECT  " + 
			"  T1.TAX_RE_05_02_ID TAX_RE_05_02_ID,  " + 
			"  T1.EXCISE_ID EXCISE_ID_2,  " + 
			"  T1.EXCISE_NAME EXCISE_NAME_2,  " + 
			"  T1.PRODUCT_LIST PRODUCT_LIST_2,  " + 
			"  T1.TYPE_LIST TYPE_LIST_2,  " + 
			"  T1.PRODUCT_NAME PRODUCT_NAME_2,  " + 
			"  T1.MODEL MODEL_2,  " + 
			"  T1.SIZE1 SIZE1_2,  " + 
			"  T1.AMOUNT AMOUNT_2,  " + 
			"  T1.PRICE PRICE_2,  " + 
			"  T1.PRICE_PER PRICE_PER_2,  " + 
			"  T1.AMOUNT_PER AMOUNT_PER_2,  " + 
			"  T1.TAX TAX_2,  " + 
			"  T1.DESTINATION DESTINATION_2,  " + 
			"  T1.DATE_DESTINATION DATE_DESTINATION_2,  " + 
			"  T1.TAX_RE_NUMBER TAX_RE_NUMBER_2,  " + 
			"  T1.OFFICE_AREA OFFICE_AREA_2,  " + 
			"  T1.DATE_OUT DATE_OUT_2,  " + 
			"  T2.EA_RE_INVENTORY_NUMBER  " + 
			"FROM TAX_RE_05_01_2 T1  " + 
			"JOIN EA_RE_INVENTORY_HDR T2  " + 
			"ON T1.EXCISE_ID = T2.EXCISE_ID WHERE 1=1 ";
	
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
	
	private RowMapper<Epa014Vo> ExportCheckingReportRowMapper = new RowMapper<Epa014Vo>() {
		@Override
		public Epa014Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Epa014Vo vo = new Epa014Vo();
//			vo.setEaReInventoryHdrId(rs.getString("EA_RE_INVENTORY_HDR_ID"));
			vo.setEaReInventoryNumber(rs.getString("EA_RE_INVENTORY_NUMBER"));
//			vo.setEa0501Number(rs.getString("EA_05_01_NUMBER"));
//			vo.setExportName(rs.getString("EXPORT_NAME"));
//			vo.setExciseOfficeSource(rs.getString("EXCISE_OFFICE_SOURCE"));
//			vo.setCheckPointDest(rs.getString("CHECK_POINT_DEST"));
//			vo.setExciseOfficeDest(rs.getString("EXCISE_OFFICE_DEST"));
//			vo.setDateOut(rs.getString("DATE_OUT"));
//			vo.setProductType(rs.getString("PRODUCT_TYPE"));
//			vo.setProductAmount(rs.getString("PRODUCT_AMOUNT"));
//			vo.setVehicleNumber(rs.getString("VEHICLE_NUMBER"));
//			vo.setLogisticWay(rs.getString("LOGISTIC_WAY"));
//			vo.setCreatedBy(rs.getString("CREATED_BY"));
//			vo.setCreatedDate(rs.getString("CREATED_DATE"));
//			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
//			vo.setUpdatedDate(rs.getString("UPDATED_DATE"));
//			vo.setIsDeleted(rs.getString("IS_DELETED"));
//			vo.setVersion(rs.getString("VERSION"));
//			vo.setEaDataType(rs.getString("EA_DATA_TYPE"));
//			vo.setMarker(rs.getString("MARKER"));
//			vo.setResult(rs.getString("RESULT"));
//			vo.setComment1(rs.getString("COMMENT_1"));
//			vo.setDateIn(rs.getString("DATE_IN"));
//			vo.setExciseId(rs.getString("EXCISE_ID"));
			
//			vo.setTaxReType(rs.getString("TAX_RE_TYPE"));
//			vo.setExciseId(rs.getString("EXCISE_ID"));
//			vo.setExciseName(rs.getString("EXCISE_NAME"));
//			vo.setTaxPlusNo(rs.getString("TAX_PLUS_NO"));
//			vo.setExciseNo1(rs.getString("EXCISE_NO_1"));
//			vo.setExciseNo1Name(rs.getString("EXCISE_NO_1_NAME"));
//			vo.setKodangName(rs.getString("KODANG_NAME"));
//			vo.setExportType(rs.getString("EXPORT_TYPE"));
//			vo.setExportTypeBy(rs.getString("EXPORT_TYPE_BY"));
//			vo.setOfficeArea(rs.getString("OFFICE_AREA"));
//			vo.setOfficeSubArea(rs.getString("OFFICE_SUB_AREA"));
			
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
			return vo;
		}
	};

}
