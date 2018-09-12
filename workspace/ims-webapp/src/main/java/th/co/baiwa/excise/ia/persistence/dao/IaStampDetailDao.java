package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.constant.ExciseConstants;
import th.co.baiwa.excise.ia.persistence.vo.Int05112DetailVo;

@Repository
public class IaStampDetailDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Int05112DetailVo> findAll() {
		String SQL = "SELECT * FROM IA_STAMP_DETAIL WHERE TO_CHAR(DATE_OF_PAY,'YYYY')=?";
		StringBuilder sql = new StringBuilder(SQL);
		String year = DateConstant.convertDateToStr(new Date(), ExciseConstants.FORMAT_DATE.YYYY,ExciseConstants.LOCALE.EN);
		List<Object> params = new ArrayList<>();
		params.add(year);
		
		//String sqlLimit = OracleUtils.limitForDataTable(sql.toString(), formVo.getStart(),formVo.getLength());
        List<Int05112DetailVo> list = jdbcTemplate.query(sql.toString(), params.toArray(), iaStampDetailRowmapper);
        return list;
    }
	
	 private RowMapper<Int05112DetailVo> iaStampDetailRowmapper = new RowMapper<Int05112DetailVo>() {
	    	@Override
	    	public Int05112DetailVo mapRow(ResultSet rs, int arg1) throws SQLException {
	    		Int05112DetailVo vo = new Int05112DetailVo();
	    		vo.setWorkSheetDetailId(rs.getLong("WORK_SHEET_DETAIL_ID"));
	    		vo.setExciseDepartment(rs.getString("EXCISE_DEPARTMENT"));
	    		vo.setExciseRegion(rs.getString("EXCISE_REGION"));
	    		vo.setExciseDistrict(rs.getString("EXCISE_DISTRICT"));
	    		vo.setDateOfPay(rs.getDate("DATE_OF_PAY"));
	    		vo.setStatus(rs.getString("STATUS"));
	    		vo.setDepartmentName(rs.getString("DEPARTMENT_NAME"));
	    		vo.setBookNumberWithdraw_Stamp(rs.getString("BOOK_NUMBER_WITHDRAW_STAMP"));
	    		vo.setDateWithdrawStamp(DateConstant.convertDateToStr(rs.getDate("DATE_WITHDRAW_STAMP"), "dd/MM/yyyy"));
	    		vo.setBookNumberDeliverStamp(rs.getString("BOOK_NUMBER_DELIVER_STAMP"));
	    		vo.setDateDeliverStamp(DateConstant.convertDateToStr(rs.getDate("DATE_DELIVER_STAMP"), "dd/MM/yyyy"));
	    		vo.setFivePartNumber(rs.getString("FIVE_PART_NUMBER"));
	    		vo.setFivePartDate(DateConstant.convertDateToStr(rs.getDate("FIVE_PART_DATE"), "dd/MM/yyyy"));;
	    		vo.setStampCheckDate(DateConstant.convertDateToStr(rs.getDate("STAMP_CHECK_DATE"), "dd/MM/yyyy"));
	    		vo.setStampChecker(rs.getString("STAMP_CHECKER"));
	    		vo.setStampType(rs.getString("STAMP_TYPE"));
	    		vo.setStampBrand(rs.getString("STAMP_BRAND"));
	    		vo.setNumberOfBook(rs.getInt("NUMBER_OF_BOOK"));
	    		vo.setNumberOfStamp(rs.getInt("NUMBER_OF_STAMP"));
	    		vo.setValueOfStampPrinted(rs.getBigDecimal("VALUE_OF_STAMP_PRINTED"));
	    		vo.setSumOfValue(rs.getBigDecimal("SUM_OF_VALUE"));
	    		vo.setSerialNumber(rs.getString("SERIAL_NUMBER"));
	    		vo.setTaxStamp(rs.getBigDecimal("TAX_STAMP"));
	    		vo.setStampCodeStart(rs.getString("STAMP_CODE_START"));
	    		vo.setStampCodeEnd(rs.getString("STAMP_CODE_END"));
	    		vo.setNote(rs.getString("NOTE"));
	    		vo.setStampTypeId(rs.getLong("STAMP_TYPE_ID"));
	    		vo.setStampBrandId(rs.getLong("STAMP_BRAND_ID"));
	    			    		
	    		return vo;
	    	}
	    };
}
