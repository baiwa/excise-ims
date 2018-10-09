package th.co.baiwa.excise.ia.persistence.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.constant.ExciseConstants.FORMAT_DATE;
import th.co.baiwa.excise.ia.persistence.vo.Int05112DetailVo;

@Repository
public class IaStampDetailDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public List<Int05112DetailVo> findAll(String dateFrom,String dateTo) {
		String SQL = "SELECT * FROM IA_STAMP_DETAIL WHERE TO_CHAR(DATE_OF_PAY,'YYYYMMDD')  BETWEEN  ? AND ? ";
		StringBuilder sql = new StringBuilder(SQL);		
		List<Object> params = new ArrayList<>();
		params.add(dateFrom);
		params.add(dateTo);
		
		//String sqlLimit = OracleUtils.limitForDataTable(sql.toString(), formVo.getStart(),formVo.getLength());
        List<Int05112DetailVo> list = jdbcTemplate.query(sql.toString(), params.toArray(), iaStampDetailRowmapper);
        return list;
    }
	
	 private RowMapper<Int05112DetailVo> iaStampDetailRowmapper = new RowMapper<Int05112DetailVo>() {
	    	@Override
	    	public Int05112DetailVo mapRow(ResultSet rs, int arg1) throws SQLException {
	    		Int05112DetailVo vo = new Int05112DetailVo();
	    		vo.setWorkSheetDetailId(rs.getLong("WORK_SHEET_DETAIL_ID"));
	    		vo.setDateOfPay(rs.getDate("DATE_OF_PAY"));
	    		vo.setStatus(rs.getString("STATUS"));
	    		vo.setBookNumberWithdraw_Stamp(rs.getString("BOOK_NUMBER_WITHDRAW_STAMP"));
	    		vo.setDateWithdrawStamp(DateConstant.convertDateToStr(rs.getDate("DATE_WITHDRAW_STAMP"), FORMAT_DATE.DDMMYYYY));
	    		vo.setBookNumberDeliverStamp(rs.getString("BOOK_NUMBER_DELIVER_STAMP"));
	    		vo.setDateDeliverStamp(DateConstant.convertDateToStr(rs.getDate("DATE_DELIVER_STAMP"), FORMAT_DATE.DDMMYYYY));
	    		vo.setFivePartNumber(rs.getString("FIVE_PART_NUMBER"));
	    		vo.setFivePartDate(DateConstant.convertDateToStr(rs.getDate("FIVE_PART_DATE"), FORMAT_DATE.DDMMYYYY));;
	    		vo.setStampCheckDate(DateConstant.convertDateToStr(rs.getDate("STAMP_CHECK_DATE"), FORMAT_DATE.DDMMYYYY));
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
