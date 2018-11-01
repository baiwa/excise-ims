package th.co.baiwa.excise.epa.persistence.dao;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.epa.persistence.vo.Epa012FormVo;
import th.co.baiwa.excise.epa.persistence.vo.Epa012Vo;
import th.co.baiwa.excise.utils.OracleUtils;

@Repository
public class ExportCheckingConnectDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private Logger log = LoggerFactory.getLogger(ExportCheckingConnectDao.class);
	
	private String SQL_SEARCH_DATA = " SELECT T1.*,  " + 
			"  T2.TAX_RE_05_02_ID TAX_RE_05_02_ID,  " + 
			"  T2.EXCISE_ID EXCISE_ID_2,  " + 
			"  T2.EXCISE_NAME EXCISE_NAME_2,  " + 
			"  T2.PRODUCT_LIST PRODUCT_LIST_2,  " + 
			"  T2.TYPE_LIST TYPE_LIST_2,  " + 
			"  T2.PRODUCT_NAME PRODUCT_NAME_2,  " + 
			"  T2.MODEL MODEL_2,  " + 
			"  T2.SIZE1 SIZE1_2,  " + 
			"  T2.AMOUNT AMOUNT_2,  " + 
			"  T2.PRICE PRICE_2,  " + 
			"  T2.PRICE_PER PRICE_PER_2,  " + 
			"  T2.AMOUNT_PER AMOUNT_PER_2,  " + 
			"  T2.TAX TAX_2,  " + 
			"  T2.DESTINATION DESTINATION_2,  " + 
			"  T2.DATE_DESTINATION DATE_DESTINATION_2,  " + 
			"  T2.TAX_RE_NUMBER TAX_RE_NUMBER_2,  " + 
			"  T2.OFFICE_AREA OFFICE_AREA_2,  " + 
			"  T2.DATE_OUT DATE_OUT_2  " + 
			"FROM TAX_RE_05_01_1 T1  " + 
			"JOIN TAX_RE_05_01_2 T2  " + 
			"ON T1.EXCISE_ID    = T2.EXCISE_ID  " + 
			"WHERE 1=1 ";
	
	public List<Epa012Vo> search(Epa012FormVo epa012FormVo) {
		StringBuilder sql = new StringBuilder(SQL_SEARCH_DATA);
		List<Object> params = new ArrayList<>();
		
		if (StringUtils.isNotBlank(epa012FormVo.getExciseId())) {
			sql.append(" AND T1.EXCISE_ID = ? ");
			params.add(epa012FormVo.getExciseId());
		}
		
		String sqlLimit = OracleUtils.limitForDataTable(sql, epa012FormVo.getStart(), epa012FormVo.getLength());
		List<Epa012Vo> list = jdbcTemplate.query(sqlLimit, params.toArray(), ExportCheckingConnectRowMapper);
		return list;
	}
	
	public long count(Epa012FormVo epa012FormVo) {
		StringBuilder sql = new StringBuilder(SQL_SEARCH_DATA);
		List<Object> params = new ArrayList<>();
		
		if (StringUtils.isNotBlank(epa012FormVo.getExciseId())) {
			sql.append(" AND T1.EXCISE_ID = ? ");
			params.add(epa012FormVo.getExciseId());
		}
		
		String countSql = OracleUtils.countForDatatable(sql);
		Long count = jdbcTemplate.queryForObject(countSql, params.toArray(), Long.class);
		return count;
	}
	
	private RowMapper<Epa012Vo> ExportCheckingConnectRowMapper = new RowMapper<Epa012Vo>() {
		@Override
		public Epa012Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Epa012Vo vo = new Epa012Vo();
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
		
			return vo;
		}
		
	};

	private String SQL_GET_INFO = " SELECT   " + 
			"T1.EXCISE_NAME EXCISE_NAME,  " + 
			"T1.EXPORT_TYPE EXPORT_TYPE,  " + 
			"T2.EXCISE_NAME EXCISE_NAME_2,  " + 
			"T2.EXCISE_ID EXCISE_ID_2  " + 
			"FROM TAX_RE_05_01_1 T1  " + 
			"JOIN TAX_RE_05_01_2 T2  " + 
			"ON T1.EXCISE_ID = T2.EXCISE_ID WHERE 1=1 ";
	
	public List<Epa012Vo> getInformation(Epa012FormVo epa012FormVo) {
		StringBuilder sql = new StringBuilder(SQL_GET_INFO);
		List<Object> params = new ArrayList<>();
		
		if (StringUtils.isNotBlank(epa012FormVo.getExciseId())) {
			sql.append(" AND T1.EXCISE_ID = ? ");
			params.add(epa012FormVo.getExciseId());
		}
		
		List<Epa012Vo> list = jdbcTemplate.query(sql.toString(), params.toArray(), GetInformationRowMapper);
		return list;
	}
	
	private RowMapper<Epa012Vo> GetInformationRowMapper = new RowMapper<Epa012Vo>() {
		@Override
		public Epa012Vo mapRow(ResultSet rs, int arg1) throws SQLException {
			Epa012Vo vo = new Epa012Vo();
			vo.setExciseName(rs.getString("EXCISE_NAME"));
			vo.setExportType(rs.getString("EXPORT_TYPE"));
			vo.setExciseName2(rs.getString("EXCISE_NAME_2"));
			vo.setExciseId2(rs.getString("EXCISE_ID_2"));
			return vo;
		}
	};

	private String SQL_INSERT_HDR = " INSERT INTO EA_RE_INVENTORY_HDR (  " + 
			"  EA_RE_INVENTORY_HDR_ID,  " + 
			"  EA_05_01_NUMBER,  " + 
			"  EXPORT_NAME,  " + 
			"  EXCISE_OFFICE_SOURCE,  " + 
			"  CHECK_POINT_DEST,  " + 
			"  EXCISE_OFFICE_DEST,  " + 
			"  DATE_OUT,  " + 
			"  DATE_IN,  " + 
			"  PRODUCT_TYPE,  " + 
			"  PRODUCT_AMOUNT,  " + 
			"  VEHICLE_NUMBER,  " + 
			"  LOGISTIC_WAY,  " + 
			"  EA_DATA_TYPE,  " + 
			"  EXCISE_ID  " + 
			")  " + 
			"VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
	
	private String SQL_INSERT_DTL = " INSERT INTO EA_RE_INVENTORY_DTL (  " + 
			"  EA_RE_INVENTORY_DTL_ID,  " + 
			"  EA_RE_INVENTORY_HDR_ID,  " + 
			"  EA_RE_INVENTORY_NUMBER,  " + 
			"  STAMP_NO,  " + 
			"  STAMP_NAME,  " + 
			"  RESULT,  " + 
			"  COMMENT_1  " + 
			")  " + 
			"VALUES (EA_RE_INVENTORY_DTL_SEQ.NEXTVAL,?,?,?,?,?,?) ";
	
	public void saveTaxDatas(Epa012FormVo epa012FormVo) {
		BigDecimal id = jdbcTemplate.queryForObject("SELECT EA_RE_INVENTORY_HDR_SEQ.NEXTVAL FROM DUAL", BigDecimal.class);
		int records = jdbcTemplate.update(SQL_INSERT_HDR, new Object[] {
				id,
				epa012FormVo.getTaxReNumber2(),
				epa012FormVo.getExciseName(),
				epa012FormVo.getOfficeArea(),
				epa012FormVo.getDestination2(),
				epa012FormVo.getDestination2(),
				epa012FormVo.getDateOut2(),
				"",
				epa012FormVo.getProductName2(),
				epa012FormVo.getQuantity(),
				"",
				"",
				"1",
				epa012FormVo.getExciseId()
		});
		log.info("Updated saveTaxDatas HDR: {} records", records);
		
		int records2 = 0;
		for (int i=0; i<epa012FormVo.getStampName().size(); i++) {
			records2 = jdbcTemplate.update(SQL_INSERT_DTL, new Object[] {
				id,
				epa012FormVo.getTaxReNumber2(),
				i+1,
				epa012FormVo.getStampName().get(i),
				epa012FormVo.getResult(),
				epa012FormVo.getRemark()
			});
		}
		 
		log.info("Updated saveTaxDatas DTL: {} records", records2);
	}

	public void saveFactoryDatas(Epa012FormVo epa012FormVo) {
		BigDecimal id = jdbcTemplate.queryForObject("SELECT EA_RE_INVENTORY_HDR_SEQ.NEXTVAL FROM DUAL", BigDecimal.class);
		int records = jdbcTemplate.update(SQL_INSERT_HDR, new Object[] {
				id,
				epa012FormVo.getTaxReNumber2(),
				epa012FormVo.getExciseName2(),
				epa012FormVo.getOfficeArea(),
				epa012FormVo.getDestination2(),
				epa012FormVo.getDestination2(),
				epa012FormVo.getDateOut2(),
				"",
				epa012FormVo.getProductName2(),
				epa012FormVo.getQuantity(),
				epa012FormVo.getVehicleNo(),
				epa012FormVo.getLogisticWay(),
				"2",
				epa012FormVo.getExciseId2()
		});
		log.info("Updated saveFactoryDatas HDR: {} records", records);
		
		int records2 = 0;
		for (int i=0; i<epa012FormVo.getStampName().size(); i++) {
			records2 = jdbcTemplate.update(SQL_INSERT_DTL, new Object[] {
				id,
				epa012FormVo.getTaxReNumber2(),
				i+1,
				epa012FormVo.getStampName().get(i),
				epa012FormVo.getResult(),
				epa012FormVo.getRemark()
			});
		}
		log.info("Updated saveTaxDatas DTL: {} records", records2);
	}
	
}
