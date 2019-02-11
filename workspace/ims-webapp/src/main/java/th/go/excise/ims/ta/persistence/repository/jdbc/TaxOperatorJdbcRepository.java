package th.go.excise.ims.ta.persistence.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.TaxOperatorVo;
import th.go.excise.ims.ta.vo.TaxPay;

@Repository
public class TaxOperatorJdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    // TODO get operator
    public List<TaxOperatorVo> getOperator(TaxOperatorFormVo formVo) {
        StringBuilder sql = new StringBuilder();
        sql.append(" SELECT ");
        sql.append(" WS4000.NEW_REG_ID,");
        sql.append(" WS4000.CUS_FULLNAME,");
        sql.append(" WS4000.FAC_FULLNAME,  ");
        sql.append(" WS4000.FAC_ADDRESS,");
        sql.append(" WS4000.OFFICE_CODE,");
        sql.append(" EXC_SEC.SECTOR_NAME2 SECTOR_NAME,");
        sql.append(" EXC_AR.AREA_SHOT_NAME");
        sql.append(" FROM TA_WS_REG4000 WS4000");
        sql.append(" INNER JOIN TA_WS_INC8000_M WS8000 ");
        sql.append("   ON WS4000.NEW_REG_ID=WS8000.NEW_REG_ID");
        sql.append(" INNER JOIN EXCISE_AREA EXC_AR");
        sql.append("   ON WS4000.OFFICE_CODE=EXC_AR.OFFICE_CODE");
        sql.append(" INNER JOIN EXCISE_SECTOR EXC_SEC");
        sql.append("  ON EXC_AR.SECTOR_ID=EXC_SEC.SECTOR_ID");
        sql.append(" WHERE 1=1 ");
        sql.append(" AND CONCAT(");
        sql.append("     TAX_YEAR,");
        sql.append("     CASE");
        sql.append("     WHEN  LENGTH(TAX_MONTH)=1 THEN CONCAT('0',TAX_MONTH)");
        sql.append("     ELSE TAX_MONTH");
        sql.append("     END");
        sql.append("   ) BETWEEN ? AND ?");
        sql.append(" GROUP BY ");
        sql.append(" WS4000.NEW_REG_ID,");
        sql.append(" WS4000.CUS_FULLNAME,");
        sql.append(" WS4000.FAC_FULLNAME,  ");
        sql.append(" WS4000.FAC_ADDRESS,");
        sql.append(" WS4000.OFFICE_CODE,");
        sql.append(" EXC_SEC.SECTOR_NAME2,");
        sql.append(" EXC_AR.AREA_SHOT_NAME");
        sql.append(" ORDER BY WS4000.NEW_REG_ID");

        List<Object> params = new ArrayList<>();
        params.add(formVo.getDateStart());
        params.add(formVo.getDateEnd());
        return jdbcTemplate.query(sql.toString(), params.toArray(), rowMapper);
    }

    protected RowMapper<TaxOperatorVo> rowMapper = new RowMapper<TaxOperatorVo>() {
        @Override
        public TaxOperatorVo mapRow(ResultSet rs, int arg1) throws SQLException {
            TaxOperatorVo vo = new TaxOperatorVo();
            vo.setNewRegId(rs.getString("NEW_REG_ID"));
            vo.setCusFullname(rs.getString("CUS_FULLNAME"));
            vo.setFacFullname(rs.getString("FAC_FULLNAME"));
            vo.setFacAddress(rs.getString("FAC_ADDRESS"));
            vo.setOfficeCode(rs.getString("OFFICE_CODE"));;
            vo.setSectorName(rs.getString("SECTOR_NAME"));
            vo.setAreaShotName(rs.getString("AREA_SHOT_NAME"));
            return vo;
        }
    };
    // TODO get tax pay operator
    public List<String> getYearTax(TaxOperatorFormVo formVo) {
    	StringBuilder sql = new StringBuilder();
    	sql.append(" select  ");
    	sql.append("     DISTINCT ");
    	sql.append("     TAX_YEAR ");
    	sql.append(" from TA_WS_INC8000_M  ");
    	sql.append(" WHERE NEW_REG_ID=? ");
    	sql.append(" AND CONCAT( ");
    	sql.append("       TAX_YEAR, ");
    	sql.append("       CASE ");
    	sql.append("       WHEN  LENGTH(TAX_MONTH)=1 THEN concat('0',TAX_MONTH) ");
    	sql.append("       ELSE TAX_MONTH ");
    	sql.append("       END ");
    	sql.append("     ) between ? and ? ORDER BY TAX_YEAR ASC ");
    	
    	 List<Object> params = new ArrayList<>();
    	 params.add(formVo.getNewRegId());
    	 params.add(formVo.getDateStart());
         params.add(formVo.getDateEnd());
    	 return jdbcTemplate.query(sql.toString(), params.toArray(), yearRowMapper);
    }
    
    protected RowMapper<String> yearRowMapper = new RowMapper<String>() {
        @Override
        public String mapRow(ResultSet rs, int arg1) throws SQLException {
        	        
            return rs.getString("TAX_YEAR");
        }
    };

    public List<TaxPay> getMonthTax(TaxOperatorFormVo formVo) {
    	StringBuilder sql = new StringBuilder();
    	sql.append(" SELECT ");
    	sql.append(" TAX_YEAR, ");
    	sql.append(" TAX_MONTH,");
    	sql.append(" CONCAT(");
    	sql.append("   TAX_YEAR,CASE");
    	sql.append("   WHEN  LENGTH(TAX_MONTH)=1 THEN CONCAT('0',TAX_MONTH)");
    	sql.append("   ELSE TAX_MONTH");
    	sql.append("   END");
    	sql.append(" ) TAX_DATE,");
    	sql.append(" SUM(TAX_AMOUNT)TAX_AMOUNT");
    	sql.append(" FROM TA_WS_INC8000_M ");
    	sql.append(" WHERE NEW_REG_ID=?");
    	sql.append(" AND CONCAT(");
    	sql.append("   TAX_YEAR,");
    	sql.append("   CASE");
    	sql.append("   WHEN  LENGTH(TAX_MONTH)=1 THEN CONCAT('0',TAX_MONTH)");
    	sql.append("   ELSE TAX_MONTH");
    	sql.append("   END");
    	sql.append(" ) BETWEEN ? AND ?");
    	sql.append(" GROUP BY ");
    	sql.append(" TAX_YEAR,");
    	sql.append(" TAX_MONTH,");
    	sql.append(" CONCAT(TAX_YEAR,CASE");
    	sql.append("               WHEN  LENGTH(TAX_MONTH)=1 THEN CONCAT('0',TAX_MONTH)");
    	sql.append("               ELSE TAX_MONTH");
    	sql.append("               END");
    	sql.append("    )");
    	sql.append(" ORDER BY     TAX_DATE ASC");

    	
    	 List<Object> params = new ArrayList<>();
    	 params.add(formVo.getNewRegId());
    	 params.add(formVo.getDateStart());
         params.add(formVo.getDateEnd());
    	 return jdbcTemplate.query(sql.toString(), params.toArray(), MonthRowMapper);
    }
    
    protected RowMapper<TaxPay> MonthRowMapper = new RowMapper<TaxPay>() {
        @Override
        public TaxPay mapRow(ResultSet rs, int arg1) throws SQLException {
        	TaxPay taxPay = new TaxPay();
        	taxPay.setYear(rs.getString("TAX_YEAR"));
        	taxPay.setMonth(rs.getString("TAX_MONTH"));
        	taxPay.setTaxAmount(rs.getBigDecimal("TAX_AMOUNT"));
            return taxPay;
        }
    };
}

