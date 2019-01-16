package th.co.baiwa.excise.ia.persistence.dao;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.co.baiwa.excise.ta.persistence.entity.ExciseDetail;
import th.co.baiwa.excise.ta.persistence.entity.ExciseTax;
import th.co.baiwa.buckwaframework.common.util.OracleUtils;

@Repository
public class ExciseDetailDao {
	
	private Logger logger = LoggerFactory.getLogger(ExciseDetailDao.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    
    // SELECT * FROM TA_PLAN_WORK_SHEET_HEADER WHERE EXCISE_ID = '2539-00033-3' AND ANALYS_NUMBER = '25610613-01-00220';
    private final String sqlPlan = " SELECT * FROM TA_PLAN_WORK_SHEET_HEADER WHERE EXCISE_ID = ? AND ANALYS_NUMBER = ? ";
    
    private final String sqlTax = " SELECT * FROM TA_EXCISE_TAX_RECEIVE WHERE TA_EXCISE_ID = ? ORDER BY TA_EXCISE_TAX_RECEIVE_ID DESC ";

    public List<ExciseDetail> queryByExciseId(String exciseId, String analysNum, int limit) {
    	logger.info("ExciseDetailDao.queryByExciseId exciseId: {}, analysNum: {}, limit: {}", exciseId, analysNum, limit);
        List<ExciseDetail> li = jdbcTemplate.query(OracleUtils.limitForDataTable(sqlPlan, 0, limit), new Object[] {exciseId, analysNum}, worksheetRowmapper);
        return li;
    }
    
    public List<ExciseTax> queryByTaxId(String exciseId, int limit) {
    	logger.info("ExciseDetailDao.queryByTaxId exciseId: {}, limit: {}", exciseId, limit);
    	List<ExciseTax> li = jdbcTemplate.query(OracleUtils.limitForDataTable(sqlTax, 0, limit), new Object[] {exciseId}, taxRowmapper);
        return li;
    }
    
    private RowMapper<ExciseTax> taxRowmapper = new RowMapper<ExciseTax>() {
    	@Override
    	public ExciseTax mapRow(ResultSet rs, int arg1) throws SQLException {
    		ExciseTax vo = new ExciseTax();
    		vo.setExciseTaxReceiveId(rs.getInt("TA_EXCISE_TAX_RECEIVE_ID"));
    		vo.setExciseId(rs.getString("TA_EXCISE_ID"));
    		vo.setExciseTaxReceiveMonth(rs.getString("TA_EXCISE_TAX_RECEIVE_MONTH"));
    		vo.setExciseTaxReceiveAmount(rs.getString("TA_EXCISE_TAX_RECEIVE_AMOUNT"));
    		vo.setCreatedBy(rs.getString("CREATED_BY"));
    		vo.setCreatedDate(rs.getDate("CREATED_DATE"));
    		vo.setUpdatedBy(rs.getString("UPDATED_BY"));
    		vo.setUpdatedDate(rs.getDate("UPDATED_DATE"));
    		return vo;
    	}
    };

    private RowMapper<ExciseDetail> worksheetRowmapper = new RowMapper<ExciseDetail>() {
        @Override
        public ExciseDetail mapRow(ResultSet rs, int arg1) throws SQLException {
            ExciseDetail vo = new ExciseDetail();
            ArrayList<ExciseTax> ve = new ArrayList<ExciseTax>();
            vo.setWorksheetHeaderId(rs.getBigDecimal("WORK_SHEET_HEADER_ID"));
			vo.setAnalysNumber(rs.getString("ANALYS_NUMBER"));
			vo.setExciseId(rs.getString("EXCISE_ID"));
			vo.setCompanyName(rs.getString("COMPANY_NAME"));
			vo.setFactoryName(rs.getString("FACTORY_NAME"));
			vo.setFactoryAddress(rs.getString("FACTORY_ADDRESS"));
			vo.setExciseOwnerArea(rs.getString("EXCISE_OWNER_AREA"));
			vo.setProductType(rs.getString("PRODUCT_TYPE"));
			vo.setExciseOwnerArea1(rs.getString("EXCISE_OWNER_AREA_1"));
			vo.setTotalAmount(rs.getBigDecimal("TOTAL_AMOUNT"));
			vo.setPercentage(rs.getBigDecimal("PERCENTAGE"));
			vo.setTotalMonth(rs.getBigDecimal("TOTAL_MONTH"));
			vo.setDecideType(rs.getString("DECIDE_TYPE"));
			vo.setFlag(rs.getString("FLAG"));
			vo.setFirstMonth(rs.getBigDecimal("FIRST_MONTH"));
			vo.setLastMonth(rs.getBigDecimal("LAST_MONTH"));
			vo.setCreatedBy(rs.getString("CREATED_BY"));
			vo.setCreatedDate(rs.getDate("CREATED_DATE"));
			vo.setUpdatedBy(rs.getString("UPDATED_BY"));
			vo.setUpdatedDate(rs.getTime("UPDATED_DATE"));
            vo.setExciseTax(ve);
            return vo;
        }
    };
}
